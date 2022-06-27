package com.course.testng.suite;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PayZhiFuBao {
    @Test
    public void login(){
        System.out.println("支付宝支付成功");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest支付宝支付运行");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("afterTest支付宝支付运行");
    }
}
