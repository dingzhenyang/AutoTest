package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {


    @BeforeTest(groups = "loginTrue",description = "测试准备工作,获取HttpClient对象")
    public void beforeTest(){
        //1.创建HttpClient对象
        TestConfig.defaultHttpClient = new DefaultHttpClient();
        //2.获取各个测试接口访问的url
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
    }

    @Test(groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        //3. 查询数据库获取测试用例的测试数据
        //获取一个执行sql语句的对象
        SqlSession session = DatabaseUtil.getSqlSession();
        //执行查询语句，返回登录用例的测试数据
        LoginCase loginCase = session.selectOne("loginCase",1);
        //输出登录用例的测试数据
        System.out.println(loginCase.toString());
        //输出服务器的登录地址
        System.out.println(TestConfig.loginUrl);

        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    @Test(description = "用户登陆失败接口")
    public void loginFalse() throws IOException {
        //3. 查询数据库获取测试用例的测试数据
        //获取一个执行sql语句的对象
        SqlSession session = DatabaseUtil.getSqlSession();
        //执行查询语句，返回登录用例的测试数据
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    private String getResult(LoginCase loginCase) throws IOException {
        //下边的代码为写完接口的测试代码
        //4.创建请求方法的实例，并指定请求URL。
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        //5.添加参数
        JSONObject param = new JSONObject();
        param.put("name",loginCase.getUserName());
        param.put("password",loginCase.getPassword());
        //6.设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //7.将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //8.执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //9.获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }
}
