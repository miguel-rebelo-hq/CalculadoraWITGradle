package com.example.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

// Service responsible for performing mathematical operations
@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.add(b);
        logger.info("Sum performed: {} + {} = {}", a, b, result);
        return result;
    }

    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.subtract(b);
        logger.info("Subtraction performed: {} - {} = {}", a, b, result);
        return result;
    }

    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.multiply(b);
        logger.info("Multiplication performed: {} * {} = {}", a, b, result);
        return result;
    }

    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            logger.error("Attempting to divide by zero: {}/{}", a, b);
            throw new IllegalArgumentException("Division by zero not allowed.");
        }
        BigDecimal result = a.divide(b, 10, BigDecimal.ROUND_HALF_UP);
        logger.info("Division performed: {} / {} = {}", a, b, result);
        return result;
    }
}
