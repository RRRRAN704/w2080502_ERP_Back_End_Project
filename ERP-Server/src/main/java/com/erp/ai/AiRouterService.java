package com.erp.ai;

import com.erp.service.ReportService;
import com.erp.vo.ContractAmountVO;
import com.erp.vo.NumberOfContractVO;
import com.erp.vo.NumberOfOrderVO;
import com.erp.vo.TopEmployeeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiRouterService {

    private final LlmClient llm;
    private final ReportService reportService;
    private final ObjectMapper om = new ObjectMapper();

    // default time == range past senven days
    private static LocalDate[] defaultRange() {
        LocalDate to = LocalDate.now().plusDays(1);
        LocalDate from = to.minusDays(7);
        return new LocalDate[]{from, to};
    }

    public Map<String, Object> ask(String question) {
        String json = llm.chatToJson(Prompts.NL2JSON, question);

        ObjectNode cmd;
        try {
            cmd = (ObjectNode) om.readTree(json);
        } catch (Exception e) {
            // default Json
            cmd = om.createObjectNode();
        }

        // auto fill all fields
        String action = getTextOr(cmd, "action", "unknown");

        LocalDate[] def = defaultRange();
        LocalDate from = parseDateOr(cmd, "from", def[0]);
        LocalDate to   = parseDateOr(cmd, "to",   def[1]);

        // Avoid anomalous intervals where the range spans from >= to (such as the same day or reversed sequence)
        if (!from.isBefore(to)) {
            from = def[0];
            to   = def[1];
        }

        Map<String, Object> out = new LinkedHashMap<String, Object>();
        // Return the parsed results of autofilled data
        ObjectNode normalized = om.createObjectNode();
        normalized.put("action", action);
        normalized.put("from", from.toString());
        normalized.put("to", to.toString());
        out.put("parsed", normalized);

        switch (action) {
            case "contractNumber": {
                NumberOfContractVO vo = reportService.getContractNumberStatistics(from, to);
                int total = sumCsvInt(vo.getNumberOfContractList());
                out.put("text", String.format("from %s to %s, number of contracts: %d",
                        from, to.minusDays(1), total));
                out.put("data", vo);
                break;
            }
            case "contractAmount": {
                ContractAmountVO vo = reportService.getContractAmountStatistics(from, to);
                BigDecimal total = sumCsvBig(vo.getContractAmountList());
                out.put("text", String.format("from %s to %s, contract amounts: GBP %s",
                        from, to.minusDays(1), total.toPlainString()));
                out.put("data", vo);
                break;
            }
            case "orderNumber": {
                NumberOfOrderVO vo = reportService.getOrderNumber(from, to);
                int total = sumCsvInt(vo.getOrderNumberList());
                out.put("text", String.format("from %s to %s, number of consultation orders: %d",
                        from, to.minusDays(1), total));
                out.put("data", vo);
                break;
            }
            case "topEmployee": {
                TopEmployeeVO vo = reportService.getTopEmployee(from, to);
                String nameStr = vo.getNameList();
                String amountStr = vo.getAmountList();  // "10000,8000,6000"

                String[] names = nameStr != null ? nameStr.split(",") : new String[0];
                String[] amounts = amountStr != null ? amountStr.split(",") : new String[0];

                StringBuilder sb = new StringBuilder("Top Five Employees: ");
                for (int i = 0; i < names.length && i < amounts.length; i++) {
                    sb.append(names[i].trim())
                            .append(" (contract amount: ")
                            .append(amounts[i].trim())
                            .append(")");
                    if (i < names.length - 1 && i < amounts.length - 1) sb.append(", ");
                }
                if (names.length == 0) sb.append("no data");

                out.put("text", sb.toString());
                out.put("data", vo);
                break;
            }
            default: {
                out.put("text",
                        "This question is not supported yet. Try: contract number / contract amount / order number / top 5 employees");
                out.put("data", new LinkedHashMap<String, Object>());
                break;
            }
        }
        return out;
    }

    // ========== Tool Methods: Safe Value Retrieval/Parsing ==========
    private static String getTextOr(ObjectNode n, String field, String defVal) {
        return (n != null && n.has(field) && !n.get(field).isNull())
                ? n.get(field).asText()
                : defVal;
    }

    private static LocalDate parseDateOr(ObjectNode n, String field, LocalDate defVal) {
        try {
            String v = getTextOr(n, field, null);
            if (v == null || v.trim().isEmpty()) return defVal;
            return LocalDate.parse(v.trim());
        } catch (Exception e) {
            return defVal;
        }
    }

    private static int sumCsvInt(String csv) {
        if (csv == null || csv.trim().isEmpty()) return 0;
        int s = 0;
        String[] parts = csv.split(",");
        for (int i = 0; i < parts.length; i++) {
            String t = parts[i].trim();
            if (!t.isEmpty()) s += Integer.parseInt(t);
        }
        return s;
    }

    private static BigDecimal sumCsvBig(String csv) {
        if (csv == null || csv.trim().isEmpty()) return BigDecimal.ZERO;
        BigDecimal s = BigDecimal.ZERO;
        String[] parts = csv.split(",");
        for (int i = 0; i < parts.length; i++) {
            String t = parts[i].trim();
            if (!t.isEmpty()) s = s.add(new BigDecimal(t));
        }
        return s;
    }
}