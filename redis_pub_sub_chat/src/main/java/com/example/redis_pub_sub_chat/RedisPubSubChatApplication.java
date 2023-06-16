package com.example.redis_pub_sub_chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisPubSubChatApplication implements CommandLineRunner {

	@Autowired
	private ChatService chatService;

	public static void main(String[] args) {
		SpringApplication.run(RedisPubSubChatApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Application Started..");
		chatService.enterChatRoom("chatroom");
	}
}
