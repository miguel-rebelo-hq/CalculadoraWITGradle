package com.example.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

// Service responsible for consuming Kafka messages with
// mathematical operations, processing them and sending the result back via Kafka.

@Service
public class CalculatorConsumer {

    // Logger to record events and track code execution
    private static final Logger logger = LoggerFactory.getLogger(CalculatorConsumer.class);

    private final CalculatorService calculatorService;
    private final KafkaTemplate<String, Map<String, String>> kafkaTemplate;

    // Constructor of the class that receives
    // the calculator service and the Kafka template for sending answers.
    public CalculatorConsumer(CalculatorService calculatorService, KafkaTemplate<String, Map<String, String>> kafkaTemplate) {
        this.calculatorService = calculatorService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "calculator-requests", groupId = "calculator-group")
    public void processRequest(Map<String, String> request) {
        String requestId = request.get("id");
        MDC.put("requestId", requestId); // Added to MDC for structured logging

        String operation = request.get("operation");
        BigDecimal a = new BigDecimal(request.get("a"));
        BigDecimal b = new BigDecimal(request.get("b"));
        BigDecimal result;

        logger.info("Request ID: {} - Processing operation {} with values: a={}, b={}", requestId, operation, a, b);

        switch (operation) {
            case "sum":
                result = calculatorService.sum(a, b);
                break;
            case "sub":
                result = calculatorService.subtract(a, b);
                break;
            case "mul":
                result = calculatorService.multiply(a, b);
                break;
            case "div":
                result = calculatorService.divide(a, b);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        kafkaTemplate.send("calculator-responses", Map.of(
                "id", requestId,
                "result", result.toString()
        ));

        MDC.clear();
    }
}
