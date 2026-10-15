
package com.logistics.logistics_assistant;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class LogisticsRetrievalService {

    private final LogisticsKnowledgeBase knowledgeBase;

    public LogisticsRetrievalService(
            LogisticsKnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public List<String> retrieve(String question) {
        List<String> queryWords = Arrays.stream(
                    question.toLowerCase(Locale.ROOT)
                            .split("[^a-z0-9_]+"))
                .filter(word -> word.length() > 2)
                .distinct()
                .toList();

        return knowledgeBase.getPolicies().stream()
                .map(policy -> new PolicyScore(
                        policy,
                        (int) queryWords.stream()
                                .filter(word -> policy
                                        .toLowerCase(Locale.ROOT)
                                        .contains(word))
                                .count()))
                .filter(result -> result.score() > 0)
                .sorted(Comparator.comparingInt(
                        PolicyScore::score).reversed())
                .limit(3)
                .map(PolicyScore::text)
                .toList();
    }

    private record PolicyScore(String text, int score) {}
}
