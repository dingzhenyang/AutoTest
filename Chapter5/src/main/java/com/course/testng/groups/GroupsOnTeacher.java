package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnTeacher {

    public void teacher1(){
        System.out.println("这是教师1111方法运行");
    }

    public void teacher2(){
        System.out.println("这是教师2222方法运行");
    }
}
