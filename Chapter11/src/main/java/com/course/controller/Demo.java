package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "v1", tags = "这是我的第一个版本的demo")
@RequestMapping("v1")
public class Demo {
    //首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    @GetMapping(value = "getUserList")
    @ApiOperation(value = "可以获取到用户数", httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @PostMapping(value = "addUser")
    @ApiOperation(value = "新增一个用户数据", httpMethod = "POST")
    public int addUser(@RequestBody User user){
        int result = template.insert("addUser", user);
        return result;
    }

    @PostMapping(value = "editUser")
    @ApiOperation(value = "修改用户数据", httpMethod = "POST")
    public int editUser(@RequestBody User user){
        return template.update("editUser", user);
    }

    @PostMapping(value = "delUser")
    @ApiOperation(value = "删除用户数据", httpMethod = "POST")
    public int delUser(@RequestParam int id){
        return template.delete("delUser", id);
    }
}
