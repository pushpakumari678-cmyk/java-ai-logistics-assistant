package com.logistics.logistics_assistant;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTools {

    private final ShipmentController shipmentController;

    public ShipmentTools(ShipmentController shipmentController) {
        this.shipmentController = shipmentController;
    }

           @Tool(description = "Look up shipment details using a shipment ID, such as FDX101. Returns the shipment status, current location, and weather condition.")
    public Shipment getShipmentDetails(String shipmentId) {
        return shipmentController.getShipment(shipmentId);
    }
}