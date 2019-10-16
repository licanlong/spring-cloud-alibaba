package com.licanlong.controller;

import com.google.common.collect.Maps;
import com.licanlong.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author licl
 * @date 2019/8/31
 *
 * restful风格 api
 *  GET http://localhost:8082/user/1
 *  POST http://localhost:8082/user
 *  PUT http://localhost:8082/user
 *  DELETE http://localhost:8082/user/1
 */
@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public Map<String, Object> getUser(@PathVariable("id") String id){
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("code",200);
        result.put("message","获取成功");
        result.put("data",new User(id,"张三","男"));
        return result;
    }

    @PostMapping("/user")
    public Map<String, Object> saveUser(@RequestBody User user){
        System.out.println(user);
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("code",200);
        result.put("message","保存成功");
        result.put("data","1");
        return result;
    }

    @PutMapping("/user")
    public Map<String, Object> updateUser(@RequestBody User user){
        System.out.println(user);
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("code",200);
        result.put("message","更新成功");
        result.put("data","");
        return result;
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Object> deleteUser(@PathVariable String id){
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("code",200);
        result.put("message","删除成功");
        result.put("data",id);
        return result;
    }

}
