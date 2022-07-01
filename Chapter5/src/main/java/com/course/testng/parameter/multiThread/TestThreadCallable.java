package com.course.testng.parameter.multiThread;

import java.util.concurrent.*;
/*
多线程创建方式3：实现callable接口
callable的好处：1、可以定义返回值；2、可以抛出异常
 */
public class TestThreadCallable implements Callable<Boolean> {
    private String url; //网络图片地址
    private String name; //保存的文件名

    public TestThreadCallable(String url, String name){
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    public Boolean call() throws Exception {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.download(url, name);
        System.out.println("下载了文件名为："+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程对象
        TestThreadCallable t1 = new TestThreadCallable("https://www.baidu.com/link?url=bmVlKJr5u3HhSCOvudbCAwq9B1man5_6cARYjMM21AGyfEfzwCH6I2_s43RL1uhDaBCNJ52_UWWGZIWuZDA7fGd3MRhLM-nIWdnceX8-0yvmAovknIRzadw9QQNvgE4i0fVglc4m26T1gNAOfsfzWEFlozFjJEZjHGn1Z94xN0-fQIQFX709qAGlIrF6chBzzUovgSl3x1UmTVfem0LHamiAuywGkC92R0JS8SZyk8sIyCujQ_zKOE5gAk1YwHSqdezJtNZPe-YsI-7Zccf7-7PHFNeh9SZOgAcnYxDgam65cpb461oGmIsxP5qGcu4rbbQZ_LZ-6nZt578Zp7N56jQWuPmyiSiF8YkBEDUPugcMPB4iaZLvMHIgVGgu-HNlIJcxEfKCREai30Z7mfo1pifeeYIqx4l1GiFb0m86hEHow6GNd2rAFRk_iKorcDrpDR9CD2vv9ZZcLL8SefFUjMN6ZAEU0WDTsjnacsCribJkjzBxHuz4zmk1MLEIcq9G-dwzT9S7_ewvrnPt2TDlmPgXQ0WfHtlHDxhP03MuD6tD9yDNGUMNdN-FM_eaJMp5TJzr9ERfXjLfJAe-4b6zs51EX18a8SRFD1xzEzF9P6v4H98r37P6jsfeZJnGSHp0vAgQLr4iWZCjtVOZwobl3FDrosr5PObG5X5QEEWqYuK&wd=&eqid=85031e8200013f660000000662bd0626", "file1.png");
        TestThreadCallable t2 = new TestThreadCallable("https://www.baidu.com/link?url=3zLBOebytGou0NyIP0rT2CmOya3XzixDO-j83jSgadFFYM8GkSn8FOjniULmJC-h2c3h5ZYA3jlyUGpbjuXFwrz4bbet7JR1AzDxm8HSvJwIT3QpFwvaWtDS2DF8TMBhOFM5ApTIyM75FazAlyGd_cIbliXIeTUyshZEgS7p_dvAnfTicLgYNznlVXKgMvulGo78LfK9pC9QpsoVByI5YK62mCxcZrOF1rZt2QwF3dhcx5SWp2Yo6FA7iIYpQWtLsMeoKSx4L4gJ8-UaIXF--HTO9vejvrhAp7hIN5VcaD96H0LqyuPB27tIPrm8V672LsEm9c8kJxXBVLDZ2yXt5aW2qJbKILBbX1azM4gLvFwwAedgm96cf6Ns4UK5DxXMX6nXHfOYQIuSpHnzgD7HtAEUI0qb7pZGagvazG-QFfSAlc2Gocg7LzOjSi9BqfqD4yccYukzQkVycMibaw0BgkhxOFKVfjA6CiaER6ZD7i3iSIikkwbbgSYibsF3q0OWufgLsfYBFFQqBYng8P5wPuHZgWIVjv351e3RujVXZDqXcG0bFdkBmGYIbGrvheqgYKqpkdKpbnkyZJJ2CEOTKNK38xl-GMaVCpmNqNeQxGca96MpZDUH_8KGAMTAjxY-gRWzItMT5eA0gJgphRsc2_&wd=&eqid=85031e8200013f660000000662bd0626", "file2");
        TestThreadCallable t3 = new TestThreadCallable("https://www.baidu.com/link?url=H9l119TUQUEAjyS9v3ggbhXAKZkIjbW8fxtX5Y2DJLhsgilSNUS3vOeTcpVMb6lcgQs5FOrlovalZjlR2JDt2f2PrvYvGiQ7H_obE8PW3OtYUmqJxrChCg8pIh-h7h7pMOO6XIJ8VjeyTvh7BYWmV_Qk0_6XTf68dWmL5bda_NFbwcWk0j6VCGRnvh4WaySX1uAofsxv8r25gXaQHmR8GOPQqiPHmmU_ZJGCQUqyj_2x4eyUhp6hH140lpCQb8ePw4nHt8MERJLP21Mu7ntaOH2dxHWAvxTbRlt-YA03uVkCbhVfxg_vmmLcH6_frv-QIOuGguykAbDByDUXt7j7M-vW7MZUHDQC5y4JeeB3v4uec_uxmpZqF9Rq1ZPYzPYW_SMOl-D1r35px1Ye5TjmbAtUVL-dQELRmaxZUskMOPv_MVM5uHaFwjRpiIGDOqf93Y5x1uvUU-3d08DqPV4rCzUm4_o1L026ltsyLgdVkxuGD94uIIfOHlGfWy62rStwLF81mdClkRLewc-0jNeOgSrXJ4r63vq0V_vtLMJoHQjhhjjDnVAN-5hxtPIFk2cBiI6gbCnEPsbxBI3LOPflK9viqlnUxP4BHLbnGQuNCFHGRdXLeqsvQMzSQ8isD2-qdJWfHZVhpoNPMdSQh6XQtK&wd=&eqid=85031e8200013f660000000662bd0626", "file3");
        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool (3);
        //提交执行
        Future< Boolean > result1= ser.submit (t1);
        Future< Boolean > result2= ser.submit (t2);
        Future< Boolean > result3= ser.submit (t3);
        //获取结果
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        boolean r3 = result3.get();
        //关闭服务
        ser.shutdownNow();
    }
}


