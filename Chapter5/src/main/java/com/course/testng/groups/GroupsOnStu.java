package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnStu {

    public void stu1(){
        System.out.println("这是学生1运行11111111111111111");
    }

    public void stu2(){
        System.out.println("这是学生2运行22222222222222222");
    }
}
