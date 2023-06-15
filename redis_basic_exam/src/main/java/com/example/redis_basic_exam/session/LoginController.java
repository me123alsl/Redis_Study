package com.example.redis_basic_exam.session;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    HashMap<String, String> sessionMap = new HashMap<>();

    @Autowired
    StringRedisTemplate redisTemplate;

    // login?name=foo
    @GetMapping("/login")
    public String login(HttpSession httpSession, @RequestParam String name) {
        // Loacl Map
        // sessionMap.put(httpSession.getId(), name);

        // HttpSession
        httpSession.setAttribute("name", name);

        return "saved";
    }

    // myname -> foo
    @GetMapping("/myname")
    public String myname(HttpSession httpSession) {
        // Loacl Map
        // String myName = sessionMap.get(httpSession.getId());

        // HttpSession
        String myName = (String) httpSession.getAttribute("name");

        return myName;
    }
}
