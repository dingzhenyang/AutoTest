package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", tags = "这是我全部的post请求")
@RequestMapping(value = "/v1")
public class MyPostMethod {
    //这个变量是用来装我们的cookie信息的
    private static Cookie cookie;

    //用户登录成功获取到cookies，然后再访问其他接口获取到列表
    @PostMapping(value = "login")
    @ApiOperation(value = "登录接口，成功后获取cookies信息")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName", required = true) String userName,
                        @RequestParam(value = "passWord", required = true) String passWord){
        if(userName.equals("zhangsan") && passWord.equals("123456")){
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名或者密码错误";
    }

    @PostMapping(value = "getUserList")
    @ApiOperation(value = "获取用户列表")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        //获取cookies
        Cookie[] cookies = request.getCookies();
        User user = new User();
        //验证cookies是否合法
        for(Cookie c: cookies){
            if (c.getName().equals("login") && c.getValue().equals("true")
            && u.getUserName().equals("zhangsan") && u.getPassword().equals("123456")){

                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }

}
