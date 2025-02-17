package com.example.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// Controller responsible for exposing the calculator's REST API.
// Receives HTTP requests, forwards them to Kafka and waits for the result
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
    private final CalculatorProducer calculatorProducer;

    public CalculatorController(CalculatorProducer calculatorProducer) {
        this.calculatorProducer = calculatorProducer;
    }

    @GetMapping("/{operation}")
    public Map<String, String> calculate(@PathVariable String operation,
                                         @RequestParam BigDecimal a,
                                         @RequestParam BigDecimal b) throws ExecutionException, InterruptedException {
        // Get the requestId of the MDC to track the request
        String requestId = MDC.get("requestId");
        logger.info("Request ID: {} - Request for operation received: {} with values: a={}, b={}", requestId, operation, a, b);

        // Send the request to Kafka and wait for a reply
        String result = calculatorProducer.sendRequest(operation, a.toString(), b.toString()).get();

        logger.info("Request ID: {} - Operations {} completed. Result: {}", requestId, operation, result);
        return Map.of("result", result);
    }
}
