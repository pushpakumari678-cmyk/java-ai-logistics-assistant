
package com.logistics.logistics_assistant;

import org.springframework.stereotype.Service;

@Service
public class LogisticsAssistantService {

    public String getAdvice(Shipment shipment) {

        if ("DELAYED".equalsIgnoreCase(shipment.getStatus())
                && shipment.getWeatherCondition()
                    .toLowerCase()
                    .contains("thunderstorm")) {

            return "Shipment " + shipment.getId()
                    + " is delayed due to severe weather in "
                    + shipment.getLocation()
                    + ". Prioritize safety, monitor weather updates, "
                    + "and notify the customer about the delay.";
        }

        if ("OUT_FOR_DELIVERY".equalsIgnoreCase(
                shipment.getStatus())) {
            return "Shipment " + shipment.getId()
                    + " is out for delivery near "
                    + shipment.getLocation()
                    + ". Monitor delivery progress.";
        }

        return "Shipment " + shipment.getId()
                + " is currently " + shipment.getStatus()
                + " near " + shipment.getLocation()
                + ". Continue tracking for updates.";
    }
}
