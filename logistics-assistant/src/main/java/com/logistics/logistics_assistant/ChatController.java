
package com.logistics.logistics_assistant;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ShipmentController shipmentController;
    private final LogisticsAssistantService assistantService;

    public ChatController(
            ShipmentController shipmentController,
            LogisticsAssistantService assistantService) {
        this.shipmentController = shipmentController;
        this.assistantService = assistantService;
    }

    @GetMapping("/{id}")
    public Map<String, String> chat(@PathVariable String id) {
        Shipment shipment = shipmentController.getShipment(id);
        String advice = assistantService.getAdvice(shipment);

        return Map.of(
            "shipmentId", id,
            "answer", advice
        );
    }
}
