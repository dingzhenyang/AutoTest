package com.course.testng.parameter.multiThread;

public class TortoiseAndHareRace implements Runnable{
    //胜利者
    private static String winner;

    public void run() {
        for (int i = 0; i <= 100; i++) {

            //模拟兔子每跑10步休息3毫秒
            if (Thread.currentThread().getName().equals("hare") && i%10==0){
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //判断游戏是否结束
            if(gameOver(i)){
                break;
            }
            System.out.println(Thread.currentThread().getName() + "---> 跑了" + i + "步");
        }

    }

    //游戏结束
    private boolean gameOver(int steps){
        //判断是否有胜利者
        if (winner != null) {
            return true;
        }
        if (steps >= 100){
            winner = Thread.currentThread().getName();
            System.out.println(winner + " is winner");
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        TortoiseAndHareRace race = new TortoiseAndHareRace();

        new Thread(race, "tortoise").start();
        new Thread(race, "hare").start();
    }
}
