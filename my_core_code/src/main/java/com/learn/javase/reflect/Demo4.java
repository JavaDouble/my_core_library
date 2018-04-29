package com.learn.javase.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
/**
 * Junit4原型
 * @author Double
 *
 */
public class Demo4 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入类名：");
		String className=sc.nextLine();
		//反复调用Class.forName()时，JVM只加载一次 符合单例模式
		Class<?> cls=Class.forName(className);
		//System.out.println(cls);
		Object obj=cls.newInstance();
		Method[] methods=cls.getDeclaredMethods();
		for(Method method:methods){
			//System.out.println(method);
			//检查方法上是否包含Test注解
			//参数是被检查的注解 返回值 如果为null表示方法上没有这个注解，
			//如果返回注解类型对象，表示包含注解。
			Object tmp=method.getAnnotation(Test.class);
			//System.out.println(tmp);
			if(tmp!=null){
				System.out.println("找到Test注解!");
				//临时打开权限
				method.setAccessible(true);
				method.invoke(obj);
			}else{
				System.out.println("没找到注解!");
			}
		}
		sc.close();
	}
}
