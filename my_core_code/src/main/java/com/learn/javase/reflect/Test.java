package com.learn.javase.reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//编译，运行期都存在注解
//@Retention(RetentionPolicy.CLASS)
//类加载时，注解被删除 方法区不存在注解 .class文件中还存在注解
//@Retention(RetentionPolicy.SOURCE)
//源代码.java文件中存在注解，javac编译时，注解被删除
public @interface Test {

}
