package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    //ResourceBundle.getBundle 用于读取resourse的配置文件
    //这里读取文件名为“application”的文件，文件名以properties结尾
   private static ResourceBundle bundle= ResourceBundle.getBundle("application", Locale.CHINA);;

    /**
     *
     * @param name
     * 为InterfaceName枚举值
     * @return
     * 返回测试接口的Url
     */
    public static String getUrl(InterfaceName name){
        String address = bundle.getString("test.url");
        String uri = "";
        //最终的测试地址
        String testUrl;
        if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");

        }

        if(name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }

        if(name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        if(name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }

        if(name == InterfaceName.ADDUSERINFO){
            uri = bundle.getString("addUser.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
}
