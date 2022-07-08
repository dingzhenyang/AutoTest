package com.course.testng.parameter.multiThread;
//测试：生产者消费者模型-->利用缓冲区解决：管程法
public class TestPC {
    public static void main(String[] args) {
        Container container = new Container(); //两条线程共同作用对象：装鸡的容器
        new Producer(container).start();//生产者线程
        new Consumer(container).start();//消费者线程
    }
}

//生产者，消费者，产品，容器
class Producer extends Thread{
    Container container;
    public Producer(Container container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            Chicken chicken = new Chicken(i);
            container.push(chicken);
            System.out.println("生产者生产鸡的总量：" + i);
        }
    }
}

//消费者
class Consumer extends Thread{
    Container container;
    public Consumer(Container container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            container.pop();
        }
    }

}

//产品-鸡
class Chicken{
    int id;//鸡的编号

    public Chicken(int id) {
        this.id = id;
    }
}

//容器
class Container{
    Chicken[] chickens = new Chicken[10]; //能装10只鸡的容器
    int count = 0; //计数器

    //生产者放入鸡
    synchronized void push(Chicken chicken){

        //容器满了，等待消费
        if (count == chickens.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //否则，放入鸡
        chickens[count] = chicken;
        System.out.println("容器里的鸡的数量-------------》" + (count+1));
        count++;
        //通知消费者
        this.notifyAll();
    }

    //消费者消费鸡
    synchronized void pop(){

        //容器空了，等待生产
        if (count == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //否则，消费鸡
        count--;
        System.out.println("消费者消费鸡的编号为：" + chickens[count].id);
        //通知生产者生产
        this.notifyAll();
    }

}