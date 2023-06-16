package com.example.redis_basic_exam.rankingTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.redis_basic_exam.ranking.service.RankingService;

@SpringBootTest
public class simpleTest {

    @Autowired
    private RankingService rankingService;

    @Test
    @DisplayName("getTopRank - 10개")
    void getTopRank() {
        // 연결 로딩을 위한 의미없는 호출 추가
        rankingService.getTopRank(10);

        Instant start = Instant.now();
        List<String> topRanks = rankingService.getTopRank(10);
        Duration elapsed = Duration.between(start, Instant.now());
        System.out.println("elapsed: " + elapsed.toMillis() + "ms");
        System.out.println("topRanks: " + topRanks);
    }

    @Test
    @DisplayName("getUserRank")
    void getUserRank() {

        // 연결 로딩을 위한 의미없는 호출 추가
        rankingService.getTopRank(10);

        Instant start = Instant.now();
        Long userRank = rankingService.getUserRank("user_100");
        Duration elapsed = Duration.between(start, Instant.now());
        System.out.println("elapsed: " + elapsed.toMillis() + "ms");
        System.out.println("userRank: " + userRank);
    }

    @Test
    @DisplayName("insertScore")
    void insertScore() {
        for (int i = 0; i < 1000000; i++) {
            int score = (int) (Math.random() * 1000000);
            String userId = "user_" + i;
            rankingService.setUserScore(userId, score);
        }
    }

    @Test
    @DisplayName("List<> sort performance")
    void inMemorySortPerformance() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            int score = (int) (Math.random() * 1000000);
            list.add(score);
        }
        Instant start = Instant.now();
        // nlogn
        Collections.sort(list);
        Duration elapsed = Duration.between(start, Instant.now());
        System.out.println("elapsed: " + elapsed.toMillis() + "ms");
    }
}
