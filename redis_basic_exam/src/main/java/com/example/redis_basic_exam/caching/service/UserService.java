package com.example.redis_basic_exam.caching.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.redis_basic_exam.caching.dto.UserProfile;

@Service
public class UserService {

    @Autowired
    ExternalApiService externalApiService;

    @Autowired
    StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId) {
        String userName = null;

        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        /* 캐시에서 먼저 조회 후 없으면 캐시에 저장 */
        System.out.println("캐시에서 조회");
        String cachedName = ops.get("nameKey:" + userId);
        if (cachedName != null) {
            System.out.println("캐시에서 찾음");
            userName = cachedName;
        } else {
            userName = externalApiService.getUserName(userId);
            System.out.println("캐시에 저장");
            ops.set("nameKey:" + userId, userName, 5, TimeUnit.SECONDS);
        }
        // String userName = externalApiService.getUserName(userId);

        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(userName, userAge);
    }
}
