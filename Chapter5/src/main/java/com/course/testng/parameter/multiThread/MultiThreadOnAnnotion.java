package com.course.testng.parameter.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {

    //invocationCount 用10个线程执行;threadPoolSize 线程池
    //如果不设线程池Size，则还是只会用1个线程挨着执行
    @Test(invocationCount = 10, threadPoolSize = 3)
    public void test(){
//        System.out.println(1);
        System.out.println("Thread id :" + Thread.currentThread().getId() + "; Thread name is " + Thread.currentThread().getName());
    }
}
