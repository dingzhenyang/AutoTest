package com.course.testng.parameter.multiThread;

import org.junit.Test;

public class MultiThreadOnXml {
    @Test
    public void test1(){
        System.out.printf("Thread id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test2(){
        System.out.printf("Thread id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void test3(){
        System.out.printf("Thread id : %s%n",Thread.currentThread().getId());
    }
}
