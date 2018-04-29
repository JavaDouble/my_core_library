package com.learn.oop;

import org.junit.Test;

//调试方式：Debug的演示(还有一个调试方式：打桩 就是System.out.println() 打桩输出 不建议使用)
public class DebugDemos {

	/*
	 * Debug的初级应用：断点/F5/F6:/F7/F8/监听的演示
	 * 断点：Debug的前提是必须有断点，断点的选取就是在有可能出错的代码的所在的行号前，鼠标左键双击，就打上一个断点。
	 * F5：逐步调试(进入到方法中)		F6:逐过程调试(不会进入方法中)
	 * F7:跳出当前的方法			 	F8:跳到下一个断点，若没有断点了则结束调试
	 * 监听：选中方法，右键watch
	 * 如何看变量的值？如何看表达式的值？ Variables和Expressions
	 */
	@Test
	public void test1() {
		int a=5,b=6;
		int n=plus(a, b);
		System.out.println(n);
		show();
		System.out.println("over");
	}

	public static int plus(int num1,int num2) {
		return num1+num2;
	}

	public static void show() {
		System.out.println(111);
		System.out.println(222);
		System.out.println(333);
		System.out.println(444);
	}

	/*
	 * 调试一个问题程序：程序逻辑为判断一个数是否是质数
	 */
	@Test
	public void test2() {
		int num=9;
		for(int i=2;i<num;i++){
			if(num%2==0){
				System.out.println(num+"不是质数");
				break;
			}else {
				System.out.println(num+"是质数");
				break;
			}
		}
	}
}
