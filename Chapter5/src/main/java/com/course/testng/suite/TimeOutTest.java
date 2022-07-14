package com.course.testng.suite;

import org.junit.Test;

public class TimeOutTest {
    @Test(timeout = 3000)//单位为毫秒值
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeout = 2000)
    public void testFailed() throws InterruptedException {
        Thread.sleep(3000);
    }
}
