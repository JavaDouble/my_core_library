package com.learn.javase.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 执行/调用(invoke)方法 调用私有的方法
 * @author Double
 *
 */
public class Demo3 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入类名：");
		String className=sc.nextLine();
		Class<?> cls=Class.forName(className);
		//System.out.println(cls);
		Object obj=cls.newInstance();
		Method[] methods=cls.getDeclaredMethods();
		for(Method method:methods){
			System.out.println(method);
			//临时打开权限
			method.setAccessible(true);
			method.invoke(obj);
		}
		sc.close();
	}

}
