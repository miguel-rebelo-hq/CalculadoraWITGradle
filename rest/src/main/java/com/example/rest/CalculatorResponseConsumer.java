package com.example.rest;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Map;

// Service responsible for consuming the answers sent by the 'calculator' module
// Through Kafka and pass them on to 'CalculatorProducer', which completes the pending requests.

@Service
public class CalculatorResponseConsumer {

    private final CalculatorProducer calculatorProducer;

    public CalculatorResponseConsumer(CalculatorProducer calculatorProducer) {
        this.calculatorProducer = calculatorProducer;
    }

    @KafkaListener(topics = "calculator-responses", groupId = "rest-group")
    public void receiveResponse(Map<String, String> response) {
        calculatorProducer.handleResponse(response);
    }
}
