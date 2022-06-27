package com.course.testng.suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTaobao {
    @Test
    public void login(){
        System.out.println("淘宝登录成功");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest运行");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("afterTest运行");
    }
}
