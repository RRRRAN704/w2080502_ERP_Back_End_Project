package com.erp.service.impl;

import com.erp.context.BaseContext;
import com.erp.dto.ClientDTO;
import com.erp.dto.ClientPageQueryDTO;
import com.erp.entity.Client;
import com.erp.mapper.ClientMapper;
import com.erp.mapper.ContractMapper;
import com.erp.mapper.OrderMapper;
import com.erp.result.PageResult;
import com.erp.service.ClientService;
import com.erp.service.ReportService;
import com.erp.vo.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public NumberOfContractVO getContractNumberStatistics(LocalDate begin, LocalDate end) {

        //store each date within a certain period
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        while(!begin.equals(end)){

            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //store number of contract recorded with a certain period
        List<Integer> contractNumberList = new ArrayList<>();
        for(LocalDate date : dateList){
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Integer contractCount = contractMapper.countByMap(map);
            contractCount = contractCount == null ? 0 : contractCount;
            contractNumberList.add(contractCount);
        }

        return NumberOfContractVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .numberOfContractList(StringUtils.join(contractNumberList, ","))
                .build();
    }

    @Override
    public ContractAmountVO getContractAmountStatistics(LocalDate begin, LocalDate end) {

        //store each date within a certain period
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        while(!begin.equals(end)){

            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        //store number of contract recorded with a certain period
        List<Integer> contractAmountList = new ArrayList<>();
        for(LocalDate date : dateList){
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Integer contractAmount = contractMapper.sumByMap(map);
            contractAmount = contractAmount == null ? 0 : contractAmount;
            contractAmountList.add(contractAmount);
        }

        return ContractAmountVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .contractAmountList(StringUtils.join(contractAmountList, ","))
                .build();
    }


    /**
     * get amount of orders within a certain period
     * @param begin
     * @param end
     * @return
     */
    @Override
    public NumberOfOrderVO getOrderNumber(LocalDate begin, LocalDate end) {

        //store each date within a certain period
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);

        while(!begin.equals(end)){

            begin = begin.plusDays(1);
            dateList.add(begin);
        }

        //store number of order created with a certain period
        List<Integer> orderNumberList = new ArrayList<>();
        for(LocalDate date : dateList){
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            Integer orderCount = orderMapper.countByMap(map);
            orderCount = orderCount == null ? 0 : orderCount;
            orderNumberList.add(orderCount);
        }

        return NumberOfOrderVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .orderNumberList(StringUtils.join(orderNumberList, ","))
                .build();
    }


    /**
     * get top 5 employees with the most contract amount
     * @param begin
     * @param end
     * @return
     */
    @Override
    public TopEmployeeVO getTopEmployee(LocalDate begin, LocalDate end) {


        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);

        List<Map<String, Object>> topEmployeeList = contractMapper.getTopEmployeeByContractAmount(beginTime, endTime);


        List<String> nameList = new ArrayList<>();
        List<String> amountList = new ArrayList<>();

        for (Map<String, Object> employee : topEmployeeList) {
            String name = (String) employee.get("name");
            Object totalAmount = employee.get("totalAmount");

            nameList.add(name);
            amountList.add(totalAmount.toString());
        }


        return TopEmployeeVO.builder()
                .nameList(StringUtils.join(nameList, ","))
                .amountList(StringUtils.join(amountList, ","))
                .build();
    }

    }

