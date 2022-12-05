package com.engynear.game.stats;

import java.util.HashMap;
import java.util.Map;

public class Leaderboard {
    private final Map<String, Integer> scores;
    public Leaderboard() {
        scores = new HashMap<>();
    }
    public void addResult(String name, int score) {
        scores.put(name, score);
    }
    public void print() {
        System.out.println("Leaderboard:");
        scores.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(10)
            .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
    public void printBest() {
        System.out.println("Best human result:");
        scores.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(1)
            .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}
