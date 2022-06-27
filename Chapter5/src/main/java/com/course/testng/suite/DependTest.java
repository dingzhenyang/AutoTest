package com.course.testng.suite;

import org.testng.annotations.Test;

public class DependTest {

    @Test
    public void test1(){
        System.out.println("运行test1");
        throw new RuntimeException();
    }

    //test2依赖test1，也就是test1是test2的前置条件，如果test1失败了，则test2忽略，不执行
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("运行test2");
    }
}
