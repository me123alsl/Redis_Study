package com.example.redis_basic_exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisBasicExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisBasicExamApplication.class, args);
	}

}
