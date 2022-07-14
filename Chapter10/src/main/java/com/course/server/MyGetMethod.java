package com.course.server;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response){
        //HttpServletRequest  装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        Cookie cookie2 = new Cookie("status", "10000");
        response.addCookie(cookie);
        response.addCookie(cookie2);

        return "恭喜你获得cookies信息成功";
    }

    /**
     * 要求客户端携带cookies访问
     */
    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "你必须携带cookies来访问";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "恭喜你访问成功";
            }
        }
        return "你必须携带cookies来访问";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url：key=value&key=value
     * 我们来模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param", method = RequestMethod.GET)
    public Map<String, Integer> getList(@RequestParam Integer star,
                                         @RequestParam Integer end){
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 400);
        myList.put("干脆面", 16);
        myList.put("衬衫", 300);
        return myList;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    public Map<String, Integer> myGetList(@PathVariable Integer start,
                          @PathVariable Integer end){
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 400);
        myList.put("干脆面", 16);
        myList.put("衬衫", 300);
        return myList;
    }

}
