package com.course.testng.parameter.multiThread;

//创建线程方式2：实现Runnable接口，重写run方法，执行线程需要丢入runnable接口实现类的对象，调用start方法
public class TestThread2 implements Runnable{
    private int nums;

    public void run() {
        for (int i = 0; i < nums; i++) {
            System.out.println(Thread.currentThread().getName() + "学习实现Runnable接口类1111");
        }
    }

    public TestThread2(int nums){
        this.nums = nums;
    }

    public static void main(String[] args) {

        //创建runnable接口的实现类的对象
        TestThread2 testThread2 = new TestThread2(100);
        //start()方法启动线程时调用testThread2这个对象的run方法
        //创建线程对象，通过线程对象来开启我们的线程，代理
        new Thread(testThread2).start();

        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() +"main方法里面的主线程22222222222");
        }
    }
}
