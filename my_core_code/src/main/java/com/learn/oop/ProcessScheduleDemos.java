package com.learn.oop;

//程序执行顺序的演示 经典阿里巴巴面试题 可以不断的改变程序的顺序，来找到执行顺序机制。
public class ProcessScheduleDemos {
	public static int k = 0;
	public static ProcessScheduleDemos t1 = new ProcessScheduleDemos("t1");
	public static ProcessScheduleDemos t2 = new ProcessScheduleDemos("t2");
	public static int i = print("i");
	public static int n = 99;
	public int j = print("j");

	{
		print("构造块");
	}

	static {
		print("静态块");
	}

	public ProcessScheduleDemos(String str) {
		System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
		++i;
		++n;
	}

	public static int print(String str) {
		System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
		++n;
		return ++i;
	}

	public static void main(String args[]) {
		ProcessScheduleDemos t = new ProcessScheduleDemos("init");
		/*
		 * 输出结果:
		  	1:j   i=0    n=0
			2:构造块   i=1    n=1
			3:t1   i=2    n=2
			4:j   i=3    n=3
			5:构造块   i=4    n=4
			6:t2   i=5    n=5
			7:i   i=6    n=6
			8:静态块   i=7    n=99
			9:j   i=8    n=100
			10:构造块   i=9    n=101
			11:init   i=10    n=102
		 */
	}
}
