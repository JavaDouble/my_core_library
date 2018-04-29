package com.learn.javase.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Junit3原型
 * @author Double
 *
 */
public class Demo2 {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入类名:");
		String className=sc.nextLine();
		//动态加载类
		Class<?> cls=Class.forName(className);
		//动态查询类中的全部方法信息
		Method[] methods=cls.getDeclaredMethods();
		//动态创建对象
		Object obj=cls.newInstance();
		for(Method method:methods){
			//System.out.println(method);
			if(method.getName().startsWith("test")){
				System.out.println(method);
				//执行/调用(invoke)方法 不能调用私有的方法
				//obj 被执行方法的对象
				//args 执行方法传递的参数
	/*			Object obj="ABC";
				method.invoke(obj, null);*/
				//如果没有参数就可以省略
				Object val=method.invoke(obj);
				System.out.println(val);
			}
		}
		sc.close();
	}
}
