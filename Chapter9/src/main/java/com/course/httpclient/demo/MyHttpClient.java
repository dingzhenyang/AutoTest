package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {
    @Test
    public void test() throws IOException {
        //用来存放结果
        String result;
        //1.创建HttpClient对象
        HttpClient client = new DefaultHttpClient();
        //2.创建请求方法的实例，并指定请求URL。因需要发送GET请求，创建HttpGet对象
        HttpGet get = new HttpGet("http://www.baidu.com");
        //3.调用HttpClient对象的execute()方法来发送请求，
        //4.并用HttpResponse获取响应信息
        HttpResponse response = client.execute(get);
        //5.利用HttpClient提供的工具类 EntityUtils，将Entity转为字符串
        //处理响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
}
