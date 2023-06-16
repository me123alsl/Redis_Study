package com.example.redis_basic_exam.ranking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis_basic_exam.ranking.service.RankingService;

@RestController
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/setScore")
    public boolean setScore(
            @RequestParam("userId") String userId,
            @RequestParam("score") int score) {
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/getRank")
    public Long getUserRank(
            @RequestParam("userId") String userId) {
        return rankingService.getUserRank(userId);
    }

    @GetMapping("/getTopRank")
    public List<String> getTopRank(
            @RequestParam("limit") int limit) {
        return rankingService.getTopRank(limit);
    }
}
