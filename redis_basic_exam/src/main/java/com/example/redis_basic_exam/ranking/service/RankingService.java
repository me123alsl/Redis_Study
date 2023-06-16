package com.example.redis_basic_exam.ranking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final static String LEADERBOARD_KEY = "leaderboard";
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 유저의 점수를 설정
     * 
     * @param userId
     * @param score
     * @return 등록 성공 여부
     */
    public boolean setUserScore(String userId, int score) {
        // SortedSet의 경우 ZSet을 사용
        ZSetOperations<String, String> zSetops = redisTemplate.opsForZSet();
        zSetops.add(LEADERBOARD_KEY, userId, score);

        return true;
    }

    /**
     * 유저의 랭킹을 조회
     * 
     * @param userId
     * @return 유저 랭킹
     * 
     */
    public Long getUserRank(String userId) {
        ZSetOperations<String, String> zSetops = redisTemplate.opsForZSet();
        return zSetops.reverseRank(LEADERBOARD_KEY, userId);
    }

    /**
     * 내림차순으로 조회
     * 
     * @param limit
     * @return 랭킹 리스트
     */
    public List<String> getTopRank(int limit) {
        ZSetOperations<String, String> zSetops = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetops.reverseRange(
                LEADERBOARD_KEY, 0, limit - 1);
        return new ArrayList<>(rangeSet);
    }

}
