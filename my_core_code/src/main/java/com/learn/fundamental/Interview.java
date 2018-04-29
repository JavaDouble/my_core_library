package com.learn.fundamental;

import java.util.Scanner;

import org.junit.Test;
//Java语法基础常见面试题锦集  温故知新计划：Java基础最多三天，文档/笔记/Demos/练习/面试题
public class Interview {

	/**
	 * 哪错了，原因，怎么改
	 * byte b1=5;
	 * byte b2=6;
	 * byte b3=b1+b2; //错误
	 * byte、char、short三种类型参与运算时，先一律转换成int类型再进行运算。右侧int类型，左侧byte类型
	 * 大类型向小类型转换需强制转换
	 *
	 * 哪错了，原因，怎么改
	 * 	short a=5;
	 *  a += 5;//正确 扩展赋值运算JVM会自动强制类型转换
	 *  a=a+5;//错误 强转
	 *  byte、char、short三种类型参与运算时，先一律转换成int类型再进行运算。右侧int类型，左侧byte类型
	 *  大类型向小类型转换需强制转换
	 */
	@Test
	public void test1() {
		byte b1=5;
		byte b2=6;
		byte b3=(byte)(b1+b2);

		short a=5;
		a += 5;
		a=(short)(a+5);
	}

	/**
	 * 写出下列输出结果并解释为什么？
	 * 考察知识点 运算符和表达式
	 */
	@Test
	public void test2() {
		double opr = 0;
		int compare = opr > 0 ? 1 : (opr < 0 ? -1 : 0);
		System.out.println("f(" + opr + ")=" + compare);

		char ch=true?'a'+2:0;
		System.out.println("ch="+ch);

		boolean b=true? false==false?true:false:true;
		System.out.println("b="+b);

		int i = 0;
		boolean re = false;
		re = ((i++) + i == 2) ? true : false;
		System.out.println("i=" + i + ",re="+re);

		Scanner sc=new Scanner(System.in);
		System.out.println("请输入年份(例如：2012)：");
		int year=sc.nextInt();
		sc.close();
		boolean flag=(year%4==0&&year%100!=0) || year%400==0;
		String msg = flag ? year + "是闰年" : year + "不是闰年";
	    System.out.println(msg);
	}

	/**
	 * 下列代码输出结果并解释为什么？
	 * 考察知识点：分支结构和for循环以及break的用法
	 */
	@Test
	public void test3() {
		int result = 0;
		for (int i = 0; i <= 10; i++) {
			if (i > 5) {
				break;
			}
			result += i;
		}
		System.out.println(result);

		int pigs = 5;
		boolean isOne = true;
		boolean isTwo = false;
		if ((pigs == 4) && !isTwo)
			System.out.print("first");//不执行
			System.out.print("second ");//执行
		if ((isTwo = true) && isOne) {
			System.out.print("third");//执行
		}
	}

	/**
	 * 下列代码输出结果并解释为什么？
	 * 考察知识点：分支结构switch...case和break混合用法
	 */
	@Test
	public void test4() {
		int i = 24, j = 32, h = 58;
		  switch (j - i) {
		  case 7:
			  h++;
		  case 8:
			  h++;
		  case 9:
			  h += 2;
		  case 10:
			  h += 3;
		  default:
			  h /= j;
		  }
		  System.out.println(h);
	}

	/**
	 * 交换两个数字和比较两个数字的方法
	 */
	@Test
	public void test5() {
		int s1=(int)(Math.random()*100);
		int s2=(int)(Math.random()*100);
		System.out.println("交换前，s1:"+s1+",s2="+s2);

		s1=s1+s2;
		s2=s1-s2;
		s1=s1-s2;
		System.out.println("交换后，s1:"+s1+",s2="+s2);

		s1=(int)(Math.random()*100);
		s2=(int)(Math.random()*100);
		System.out.println("交换前，s1:"+s1+",s2="+s2);

		int temp=s1;
		s1=s2;
		s2=temp;
		System.out.println("交换后，s1:"+s1+",s2="+s2);

		int max=s1>s2?s1:s2;
		int min=s1<s2?s1:s2;
		System.out.println("最大值是："+max+",最小值是："+min);
		if(s1>s2){
			System.out.println("最大值是："+s1+",最小值是："+s2);
		}else{
			System.out.println("最大值是："+s2+",最小值是："+s1);
		}
	}

	/**
	 * 用户从控制台接收三个整数，通过程序找出三个数中的最大值。 三个数字的排序
	 */
	@Test
	public void test6() {
        System.out.println("请依次输入三个整数：a,b,c（以空格隔开）");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int max=(a>b?a:b)<c?c:(a>b?a:b);
        //int max=(a>b?a:b)>c?(a>b?a:b):c;
        boolean flag=((a>b?a:b)<c?c:(a>b?a:b))==((a>b?a:b)>c?(a>b?a:b):c);
        System.out.println(flag);
        int min=(a<b?a:b)<c?(a<b?a:b):c;
        int middle=(((a>b?a:b)>c?(a>b?a:b):c)==a)?(b>c?b:c):(((a>b?a:b)>c?(a>b?a:b):c)==b?(a>c?a:c):(a>b?a:b));
        System.out.println("最大值为："+max);
        System.out.println("中间值为："+middle);
        System.out.println("最小值为："+min);
		System.out.print("三个数排序后："+min+","+middle+","+max);

/*		a=scanner.nextInt();
		b=scanner.nextInt();
		c=scanner.nextInt();
		scanner.close();
		if(a>b){
			int tmp=a;
			a=b;
			b=tmp;
		}
		if(a>c){
			int tmp=a;
			a=c;
			c=tmp;
		}
		if(b>c){
			int tmp=b;
			b=c;
			c=tmp;
		}
		System.out.print("三个数排序后："+a+","+b+","+c);*/
		scanner.close();
	}

	@Test
	public void test7() {

	}

	@Test
	public void test8() {

	}


	@Test
	public void test9() {

	}


	@Test
	public void test10() {

	}

	@Test
	public void test11() {

	}

	@Test
	public void test12() {

	}

	@Test
	public void test13() {

	}

	@Test
	public void test14() {

	}

	@Test
	public void test15() {

	}

	@Test
	public void test16() {

	}

	@Test
	public void test17() {

	}

	@Test
	public void test18() {

	}

	@Test
	public void test19() {

	}

	@Test
	public void test20() {

	}

}
