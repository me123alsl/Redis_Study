package com.example.redis_pub_sub_chat;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements MessageListener {

    @Autowired
    private RedisMessageListenerContainer container;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void enterChatRoom(String chatRoomName) {
        container.addMessageListener(this, new ChannelTopic(chatRoomName));
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equalsIgnoreCase("q")) {
                System.out.println("Exit!");
                break;
            }

            redisTemplate.convertAndSend(chatRoomName, line);
        }

        in.close();
    }

    // MessageListener interface 메시지 수신시 처리 함수
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message received: " + message.toString());
    }

}
