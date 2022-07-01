package com.course.testng.parameter.multiThread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
//创建线程方式1，继承Thread类，重写run方法，run方法中是线程执行体，用start方法开启线程
//总结：注意，线程开启不一定立即执行，由CPU调度执行
public class TestTread extends Thread{

    private String url;
    private String name;

    public TestTread(String url, String name){
        this.url = url;
        this.name = name;
    }

    //线程执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.download(url, name);
        System.out.println("下载了文件名为："+name);
    }

    //main线程，主线程
    public static void main(String[] args) {
        //创建一个线程对象
        TestTread t1 = new TestTread("https://www.baidu.com/link?url=bmVlKJr5u3HhSCOvudbCAwq9B1man5_6cARYjMM21AGyfEfzwCH6I2_s43RL1uhDaBCNJ52_UWWGZIWuZDA7fGd3MRhLM-nIWdnceX8-0yvmAovknIRzadw9QQNvgE4i0fVglc4m26T1gNAOfsfzWEFlozFjJEZjHGn1Z94xN0-fQIQFX709qAGlIrF6chBzzUovgSl3x1UmTVfem0LHamiAuywGkC92R0JS8SZyk8sIyCujQ_zKOE5gAk1YwHSqdezJtNZPe-YsI-7Zccf7-7PHFNeh9SZOgAcnYxDgam65cpb461oGmIsxP5qGcu4rbbQZ_LZ-6nZt578Zp7N56jQWuPmyiSiF8YkBEDUPugcMPB4iaZLvMHIgVGgu-HNlIJcxEfKCREai30Z7mfo1pifeeYIqx4l1GiFb0m86hEHow6GNd2rAFRk_iKorcDrpDR9CD2vv9ZZcLL8SefFUjMN6ZAEU0WDTsjnacsCribJkjzBxHuz4zmk1MLEIcq9G-dwzT9S7_ewvrnPt2TDlmPgXQ0WfHtlHDxhP03MuD6tD9yDNGUMNdN-FM_eaJMp5TJzr9ERfXjLfJAe-4b6zs51EX18a8SRFD1xzEzF9P6v4H98r37P6jsfeZJnGSHp0vAgQLr4iWZCjtVOZwobl3FDrosr5PObG5X5QEEWqYuK&wd=&eqid=85031e8200013f660000000662bd0626", "file1.png");
        TestTread t2 = new TestTread("https://www.baidu.com/link?url=3zLBOebytGou0NyIP0rT2CmOya3XzixDO-j83jSgadFFYM8GkSn8FOjniULmJC-h2c3h5ZYA3jlyUGpbjuXFwrz4bbet7JR1AzDxm8HSvJwIT3QpFwvaWtDS2DF8TMBhOFM5ApTIyM75FazAlyGd_cIbliXIeTUyshZEgS7p_dvAnfTicLgYNznlVXKgMvulGo78LfK9pC9QpsoVByI5YK62mCxcZrOF1rZt2QwF3dhcx5SWp2Yo6FA7iIYpQWtLsMeoKSx4L4gJ8-UaIXF--HTO9vejvrhAp7hIN5VcaD96H0LqyuPB27tIPrm8V672LsEm9c8kJxXBVLDZ2yXt5aW2qJbKILBbX1azM4gLvFwwAedgm96cf6Ns4UK5DxXMX6nXHfOYQIuSpHnzgD7HtAEUI0qb7pZGagvazG-QFfSAlc2Gocg7LzOjSi9BqfqD4yccYukzQkVycMibaw0BgkhxOFKVfjA6CiaER6ZD7i3iSIikkwbbgSYibsF3q0OWufgLsfYBFFQqBYng8P5wPuHZgWIVjv351e3RujVXZDqXcG0bFdkBmGYIbGrvheqgYKqpkdKpbnkyZJJ2CEOTKNK38xl-GMaVCpmNqNeQxGca96MpZDUH_8KGAMTAjxY-gRWzItMT5eA0gJgphRsc2_&wd=&eqid=85031e8200013f660000000662bd0626", "file2");
        TestTread t3 = new TestTread("https://www.baidu.com/link?url=H9l119TUQUEAjyS9v3ggbhXAKZkIjbW8fxtX5Y2DJLhsgilSNUS3vOeTcpVMb6lcgQs5FOrlovalZjlR2JDt2f2PrvYvGiQ7H_obE8PW3OtYUmqJxrChCg8pIh-h7h7pMOO6XIJ8VjeyTvh7BYWmV_Qk0_6XTf68dWmL5bda_NFbwcWk0j6VCGRnvh4WaySX1uAofsxv8r25gXaQHmR8GOPQqiPHmmU_ZJGCQUqyj_2x4eyUhp6hH140lpCQb8ePw4nHt8MERJLP21Mu7ntaOH2dxHWAvxTbRlt-YA03uVkCbhVfxg_vmmLcH6_frv-QIOuGguykAbDByDUXt7j7M-vW7MZUHDQC5y4JeeB3v4uec_uxmpZqF9Rq1ZPYzPYW_SMOl-D1r35px1Ye5TjmbAtUVL-dQELRmaxZUskMOPv_MVM5uHaFwjRpiIGDOqf93Y5x1uvUU-3d08DqPV4rCzUm4_o1L026ltsyLgdVkxuGD94uIIfOHlGfWy62rStwLF81mdClkRLewc-0jNeOgSrXJ4r63vq0V_vtLMJoHQjhhjjDnVAN-5hxtPIFk2cBiI6gbCnEPsbxBI3LOPflK9viqlnUxP4BHLbnGQuNCFHGRdXLeqsvQMzSQ8isD2-qdJWfHZVhpoNPMdSQh6XQtK&wd=&eqid=85031e8200013f660000000662bd0626", "file3");
        //调用start()方法开启线程
        t1.start();
        t2.start();
        t3.start();
    }
}


// 下载器
class WebDownloader {
    //下载方法
    public void download(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，download出现问题");
        }
    }
}