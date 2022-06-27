package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "class")
public class GroupsOnClass {

    public void class1(){
        System.out.println("这是班级11在运行11111111111");
    }

    public void class2(){
        System.out.println("这是班级22在运行22222222222");
    }
}
