
package com.logistics.logistics_assistant;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiAIService {

    private final ChatClient chatClient;
    private final ShipmentTools shipmentTools;
    private final DeliveryRiskTools deliveryRiskTools;
    private final LogisticsRetrievalService retrievalService;

    public GeminiAIService(
            ChatClient.Builder builder,
            ShipmentTools shipmentTools,
            DeliveryRiskTools deliveryRiskTools,
            LogisticsRetrievalService retrievalService) {

        this.chatClient = builder.build();
        this.shipmentTools = shipmentTools;
        this.deliveryRiskTools = deliveryRiskTools;
        this.retrievalService = retrievalService;
    }

    public String askGemini(String question) {

        var policies = retrievalService.retrieve(question);

        String context = policies.isEmpty()
                ? "No matching policy was found."
                : String.join("\n\n", policies);

        return chatClient.prompt()
                .system("""
                    You are a logistics AI assistant.
                    Answer using the supplied policy context.
                    Do not invent policy details.
                    If the context does not contain the answer,
                    say that the policy information was not found.
                    When shipment details are needed, use the shipment
                    lookup tool. Use the delivery risk tool when relevant.
                    Keep the answer concise.
                    """)
                .user("""
                    Question:
                    %s

                    Retrieved logistics policy context:
                    %s
                    """.formatted(question, context))
                .tools(shipmentTools, deliveryRiskTools)
                .call()
                .content();
    }
}
