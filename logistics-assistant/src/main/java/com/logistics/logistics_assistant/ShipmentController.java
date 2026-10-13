
package com.logistics.logistics_assistant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ShipmentController {

    private final Map<String, Shipment> shipments = Map.of(
        "FDX101", new Shipment(
            "FDX101", "DELAYED", "Memphis",
            "Severe thunderstorms"
        ),
        "FDX102", new Shipment(
            "FDX102", "IN_TRANSIT", "Dallas",
            "Clear"
        ),
        "FDX103", new Shipment(
            "FDX103", "OUT_FOR_DELIVERY", "Noida",
            "Light rain"
        )
    );

    @GetMapping("/api/shipments/{id}")
    public Shipment getShipment(@PathVariable String id) {
        Shipment shipment = shipments.get(id);

        if (shipment == null) {
            throw new org.springframework.web.server
                .ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Shipment not found: " + id
                );
        }

        return shipment;
    }
}
