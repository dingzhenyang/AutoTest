package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
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

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue", description = "新增用户接口")
    public void addUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase userCase  = session.selectOne("addUserCase", 1);
        int id = session.selectOne("userCount");
        id = id + 1;
        System.out.println(userCase.toString());
        System.out.println(TestConfig.addUserUrl);

        String result = getResult(userCase, Integer.toString(id));
        Assert.assertEquals(result, userCase.getExpected());
    }

    private String getResult(AddUserCase userCase, String id) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("userName", userCase.getUserName());
        param.put("password", userCase.getPassword());
        param.put("sex", userCase.getSex());
        param.put("age", userCase.getAge());
        param.put("permission", userCase.getPermission());
        param.put("isDelete", userCase.getIsDelete());
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        post.setHeader("content-type","application/json");
        CloseableHttpResponse execute = TestConfig.client.execute(post);
        String s = EntityUtils.toString(execute.getEntity());

        return s;
    }
}
