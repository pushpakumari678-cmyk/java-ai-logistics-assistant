
package com.logistics.logistics_assistant;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final LogisticsRetrievalService retrievalService;

    public RagController(
            LogisticsRetrievalService retrievalService) {
        this.retrievalService = retrievalService;
    }

    @GetMapping("/search")
    public Map<String, Object> search(
            @RequestParam String question) {

        List<String> policies =
                retrievalService.retrieve(question);

        return Map.of(
                "question", question,
                "retrievedPolicies", policies,
                "count", policies.size()
        );
    }
}
