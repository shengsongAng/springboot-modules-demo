package com.ang.mybatisplus.base.controller;


import com.ang.mybatisplus.base.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ssang
 * @since 2021-05-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public List list(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",1);
        List list = userService.list();
        return list;
    }
}
