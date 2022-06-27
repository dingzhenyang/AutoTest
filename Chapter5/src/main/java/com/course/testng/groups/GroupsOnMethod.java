package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups = "server")
    public void TestOnGroups(){
        System.out.println("服务器运行分组");
    }

    @AfterGroups("server")
    public void TestAfterGroups(){
        System.out.println("服务器在分组之后运行");
    }

    @BeforeGroups("server")
    public void TestBeforeGroups(){
        System.out.println("服务器在分组之前运行");
    }
    @AfterGroups("client")
    public void ClientTestAfterGroups(){
        System.out.println("客户端在分组之后运行");
    }

    @BeforeGroups("client")
    public void ClientTestBeforeGroups(){
        System.out.println("客户端在分组之前运行");
    }

    @Test(groups = "client")
    public void ClientOnGroups(){
        System.out.println("客户端运行分组");
    }
}
