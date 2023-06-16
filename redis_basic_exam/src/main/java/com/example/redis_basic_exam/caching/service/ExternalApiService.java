package com.example.redis_basic_exam.caching.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    /**
     * 0.5초걸려 호출되는 외부 API
     */
    public String getUserName(String userId) {
        // 다른 서비스나 DB에 대한 호출
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (userId.equals("A")) {
            return "Alice";
        } else if (userId.equals("B")) {
            return "Bob";
        } else {
            return "Guest";
        }
    }

    /**
     * 0.5초걸려 호출되는 외부 API
     */
    @Cacheable(cacheNames = "userAgeCache", key = "#userId")
    public int getUserAge(String userId) {
        // 다른 서비스나 DB에 대한 호출
        System.out.println("외부 API 호출 AGE");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (userId.equals("A")) {
            return 10;
        } else if (userId.equals("B")) {
            return 20;
        } else {
            return 99;
        }
    }
}
