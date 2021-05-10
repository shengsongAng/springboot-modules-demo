package com.ang.redis.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2021/5/10 0010 14:44
 **/
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/putSession")
    @ResponseBody
    public String putSession(@RequestParam("value") String value, HttpServletRequest request){
        request.getSession().setAttribute("value",value);
        return "OK";
    }

    @RequestMapping("/getSession")
    @ResponseBody
    public String getSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }

        System.out.println(request.getSession().getId()+":"+request.getSession().getAttribute("value").toString());
        return "OK";
    }

    @RequestMapping("/setJSON")
    @ResponseBody
    public String setJSON(@RequestParam("key") String key,@RequestParam("value") String value){
        redisTemplate.opsForValue().set(key, JSONObject.parse(value));
        return "OK";
    }

    @RequestMapping("/setString")
    @ResponseBody
    public String setString(@RequestParam("key") String key,@RequestParam("value") String value){
        redisTemplate.opsForValue().set(key, value);
        return "OK";
    }

    @RequestMapping("/getValue")
    @ResponseBody
    public Object getValue(@RequestParam("key") String key){
        return redisTemplate.opsForValue().get(key);
    }
}
