package com.course.cases;

import com.alibaba.fastjson.JSONArray;
import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "获取某个用户信息")
    public void getUserInfo() throws IOException {
        //获取测试数据
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase userInfoCase = session.selectOne("getUserInfoCase", 1);
        //获取某个用户信息的预期内容
        User expected = session.selectOne(userInfoCase.getExpected(), userInfoCase);
        //访问地址
        System.out.println(TestConfig.getUserInfoUrl);
        //服务器响应体内容
        String actual = getResult(userInfoCase);
        //断言
        Assert.assertEquals(expected.toString(), actual);

    }

    private String getResult(GetUserInfoCase userInfoCase) throws IOException {
        //实例化请求方法
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        //post中放入请求体
        JSONObject param = new JSONObject();

        param.put("id", userInfoCase.getUserId());
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //post设置请求头
        post.setHeader("content-type","application/json");
        //httpClient发送post请求
        CloseableHttpResponse execute = TestConfig.client.execute(post);
        //获取响应内容
        String s = EntityUtils.toString(execute.getEntity());
        List<User> userList= JSONArray.parseArray(s, User.class);
        String result = userList.get(0).toString();
        return result;
    }
}
