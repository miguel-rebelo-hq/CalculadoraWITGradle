package com.example.rest;

import org.slf4j.MDC;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

// Service responsible for sending requests to Kafka and processing the responses received.
// Acts as a message producer for the ‘calculator-requests’ topic
@Service
public class CalculatorProducer {

    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;
    private final Map<String, CompletableFuture<String>> pendingRequests = new ConcurrentHashMap<>();

    // Map to store pending requests and associate them with your future responses
    public CalculatorProducer(KafkaTemplate<String, Map<String, String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<String> sendRequest(String operation, String a, String b) {
        String requestId = MDC.get("requestId");
        CompletableFuture<String> future = new CompletableFuture<>();
        pendingRequests.put(requestId, future);

        kafkaTemplate.send("calculator-requests", Map.of(
                "id", requestId,
                "operation", operation,
                "a", a,
                "b", b
        ));

        return future;
    }

    public void handleResponse(Map<String, String> response) {
        String requestId = response.get("id");
        String result = response.get("result");

        CompletableFuture<String> future = pendingRequests.remove(requestId);
        if (future != null) {
            future.complete(result);
        } else {
            System.out.println("Request ID " + requestId + " not found in pending orders.");
        }
    }
}
