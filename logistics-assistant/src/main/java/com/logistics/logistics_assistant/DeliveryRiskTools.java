package com.logistics.logistics_assistant;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class DeliveryRiskTools {

    @Tool(description = "Assess delivery risk using shipment status and weather conditions. Returns HIGH, MEDIUM, or LOW risk with a reason.")
    public String assessDeliveryRisk(String status, String weatherCondition) {

        if (weatherCondition != null &&
                (weatherCondition.toLowerCase().contains("thunderstorm")
                || weatherCondition.toLowerCase().contains("severe"))) {
            return "HIGH risk: Severe weather may delay delivery. "
                    + "Monitor weather updates and notify the customer.";
        }

        if ("DELAYED".equalsIgnoreCase(status)) {
            return "MEDIUM risk: Shipment is delayed. "
                    + "Check the latest tracking updates.";
        }

        if ("OUT_FOR_DELIVERY".equalsIgnoreCase(status)) {
            return "LOW risk: Shipment is out for delivery. "
                    + "Monitor final delivery confirmation.";
        }

        return "LOW risk: No major risk identified from the available information.";
    }
}