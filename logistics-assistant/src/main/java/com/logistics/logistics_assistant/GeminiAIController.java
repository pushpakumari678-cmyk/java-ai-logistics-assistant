package com.logistics.logistics_assistant;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class GeminiAIController {

    private final GeminiAIService geminiAIService;

    public GeminiAIController(GeminiAIService geminiAIService) {
        this.geminiAIService = geminiAIService;
    }

    @GetMapping("/shipments/{id}")
    public Map<String, String> getAIAdvice(@PathVariable String id) {

       
String question = "For shipment " + id
        + ", use the shipment lookup tool first. "
        + "Then use the delivery risk tool with the returned status "
        + "and weather condition. Return a brief answer with "
        + "status, location, weather, risk level, and advice. "
        + "Do not invent facts.";

        String answer = geminiAIService.askGemini(question);

        return Map.of("shipmentId", id, "answer", answer);
    }
}