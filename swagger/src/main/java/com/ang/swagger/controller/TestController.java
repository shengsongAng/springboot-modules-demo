package com.ang.swagger.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ssang
 * @create: 2021/6/24 0024 13:45
 **/
@Api("测试类接口")
@RestController
@RequestMapping("/dev-api")
public class TestController {
        private final static Map<Integer, UserEntity> users = new LinkedHashMap<Integer, UserEntity>();
        {
            users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
            users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
        }

        @ApiOperation("获取用户列表")
        @GetMapping("/list")
        public Object userList()
        {
            List<UserEntity> userList = new ArrayList<UserEntity>(users.values());
            return userList;
        }

        @ApiOperation("获取用户详细")
        @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
        @GetMapping("/{userId}")
        public Object getUser(@PathVariable Integer userId)
        {
            if (!users.isEmpty() && users.containsKey(userId))
            {
                return users.get(userId);
            }
            else
            {
                return "用户不存在";
            }
        }

        @ApiOperation("新增用户")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer"),
                @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String"),
                @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String"),
                @ApiImplicitParam(name = "mobile", value = "用户手机", dataType = "String")
        })
        @PostMapping("/save")
        public Object save(UserEntity user)
        {
            if (user==null || user.getUserId()==null)
            {
                return "用户ID不能为空";
            }
            return users.put(user.getUserId(), user);
        }

        @ApiOperation("更新用户")
        @PutMapping("/update")
        public Object update(@RequestBody UserEntity user)
        {
            return users.put(user.getUserId(), user);
        }

        @ApiOperation("删除用户信息")
        @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path")
        @DeleteMapping("/{userId}")
        public Object delete(@PathVariable Integer userId)
        {
            return userId;
        }
    }

    @ApiModel(value = "UserEntity", description = "用户实体")
    class UserEntity
    {
        @ApiModelProperty("用户ID")
        private Integer userId;

        @ApiModelProperty("用户名称")
        private String username;

        @ApiModelProperty("用户密码")
        private String password;

        @ApiModelProperty("用户手机")
        private String mobile;

        public UserEntity()
        {

        }

        public UserEntity(Integer userId, String username, String password, String mobile)
        {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.mobile = mobile;
        }

        public Integer getUserId()
        {
            return userId;
        }

        public void setUserId(Integer userId)
        {
            this.userId = userId;
        }

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public String getMobile()
        {
            return mobile;
        }

        public void setMobile(String mobile)
        {
            this.mobile = mobile;
        }
    }
