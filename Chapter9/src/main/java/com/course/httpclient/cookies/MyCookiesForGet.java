package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CANADA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;

        //从配置文件中 拼接测试的url
        String uri = bundle.getString("getCookies.uri");
        String testUrl = url + uri;

        //测试逻辑代码书写
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //获取cookies信息
        store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for (Cookie cookie: cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = " + name + ", cookie value = " + value);
        }
    }

    //这个测试需要依赖上个测试，上个测试拿到cookies，这个测试需要携带cookie访问
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String uri = bundle.getString("get.with.cookies.uri");
        String testUrl = url + uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookie信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);

        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);

        String s = EntityUtils.toString(response.getEntity(), "utf-8");

        if (statusCode == 200){
            System.out.println("响应体内容：" + s);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostWithCookies() throws IOException {
        //拼接最终的测试地址
        String uri = bundle.getString("post.with.cookies.uri");
        String testUrl = url + uri;

        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testUrl);
        //设置请求头信息 设置header
        post.setHeader("Content-Type","application/json");

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "huhansan");
        param.put("age", "18");

        //将参数信息添加到方法中
        StringEntity stringEntity = new StringEntity(param.toString());
        post.setEntity(stringEntity);

        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(store);

        //执行post方法
        HttpResponse response = client.execute(post);

        //获取响应结果
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);

        //处理结果，判断返回结果是否符合预期
        //将返回的响应结果字符串转化为json对象
        JSONObject resultJson = new JSONObject(result);

        //获取到结果值
        String huhansan = resultJson.get("huhansan").toString();
        String status = resultJson.get("status").toString();

        //具体的判断返回结果的值
        Assert.assertEquals("success", huhansan);
        Assert.assertEquals("1", status);
    }
}
