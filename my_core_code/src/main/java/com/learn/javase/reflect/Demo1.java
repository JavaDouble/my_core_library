package com.learn.javase.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
/**
 * 反射的演示
 * 动态加载类 引用类的相关信息 动态创建对象 动态找到属性和方法信息
 * @author Double
 *
 */
public class Demo1 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入类名:");//java.lang.Thread class java.net.Socket
		String className=sc.nextLine();
		//System.out.println(className);

		//动态加载类：
		Class<?> cla=Class.forName(className);
		System.out.println(cla);

		//cla引用了类的相关信息
		Field[] fields=cla.getDeclaredFields();
		for(Field field:fields){
			System.out.println(field);
		}

		//动态创建对象
		Object obj=cla.newInstance();
		System.out.println(obj);

		//动态找到属性信息
		System.out.println("输入属性名：");
		String name=sc.nextLine();

		//动态的在cla代表的类信息上找到属性信息
		Field fld=cla.getDeclaredField(name);
		System.out.println(fld);

		//在读取属性之前使用setAccessible(true)
		//方法就可以访问私有属性了。
		fld.setAccessible(true);
		//属性值=fld.get(被访问对象)
		Object val=fld.get(obj);
		System.out.println(val);

		//动态获取方法信息
		//从cla代表的类信息中获取全部的方法信息
		Method[] ms=cla.getDeclaredMethods();

		//Method提供了获取方法详细信息的方法:
		//获取方法名
		//String name = method.getName();
		//获取返回值类型
		//Class type = method.getReturnType();
		for(Method m:ms){
			System.out.println(m);
			System.out.println(m.getName());
			System.out.println(m.getReturnType());
			if(m.getName().startsWith("test")){
				//System.out.println("找到了!");
			}
		}
		sc.close();
	}
}
