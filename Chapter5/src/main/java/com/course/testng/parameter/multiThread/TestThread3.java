package com.course.testng.parameter.multiThread;

//买火车票的例子
//发现问题：多个线程操作同一个资源的情况下，线程不安全，数据紊乱
public class TestThread3 implements Runnable{
    //票数
    private int ticketNums = 10;
    public void run() {
        while (true){
            if(ticketNums <= 0){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestThread3 testThread3 = new TestThread3();
        new Thread(testThread3).start();
        new Thread(testThread3).start();
        new Thread(testThread3).start();
    }
}
