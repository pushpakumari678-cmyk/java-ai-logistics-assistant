
package com.logistics.logistics_assistant;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class LogisticsKnowledgeBase {

    private final List<String> policies;

    public LogisticsKnowledgeBase() throws IOException {
        ClassPathResource resource =
                new ClassPathResource("logistics-policy.txt");

        String content = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8);

        this.policies = List.of(content.split("\\n\\s*\\n"));
    }

    public List<String> getPolicies() {
        return policies;
    }
}
