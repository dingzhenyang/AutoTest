package com.course.testng.parameter.multiThread;

public class TestThread1 extends Thread{
    //线程执行体
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " 我在学习多线程11111111111111");
        }
    }

    //主线程，在这里创建新的线程
    public static void main(String[] args) {
        //创建一个线程对象
        TestThread1 testThread1 = new TestThread1();
        //调用start()方法开启线程
        testThread1.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " 执行多线程2222222");
        }
    }
}
