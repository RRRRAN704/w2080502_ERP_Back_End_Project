package com.erp.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LlmClient {

    @Value("${llm.base-url}") private String baseUrl;
    @Value("${llm.model}")    private String model;
    @Value("${llm.api-key}")  private String apiKey;
    @Value("${llm.timeout-ms:12000}") private int timeoutMs;

    private final ObjectMapper om = new ObjectMapper();
    private final OkHttpClient http = new OkHttpClient();

    /** Make the model strictly return JSON objects (in string form) */
    public String chatToJson(String system, String user) {
        try {
            ObjectNode root = om.createObjectNode();
            root.put("model", model);

            ArrayNode msgs = root.putArray("messages");
            msgs.addObject().put("role", "system").put("content", system);
            msgs.addObject().put("role", "user").put("content", user);

            // Force JSON output
            ObjectNode fmt = root.putObject("response_format");
            fmt.put("type", "json_object");

            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json"), om.writeValueAsBytes(root));

            OkHttpClient client = http.newBuilder()
                    .callTimeout(java.time.Duration.ofMillis(timeoutMs))
                    .build();

            Request req = new Request.Builder()
                    .url(baseUrl + "/chat/completions")
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .post(body)
                    .build();

            try (Response resp = client.newCall(req).execute()) {
                String respBody = resp.body() != null ? resp.body().string() : "";
                if (!resp.isSuccessful()) {
                    throw new RuntimeException("LLM HTTP " + resp.code() + " - " + respBody);
                }
                JsonNode j = om.readTree(respBody);
                return j.at("/choices/0/message/content").asText("{}");
            }
        } catch (Exception e) {
            throw new RuntimeException("LLM API calls failure", e);
        }
    }
}