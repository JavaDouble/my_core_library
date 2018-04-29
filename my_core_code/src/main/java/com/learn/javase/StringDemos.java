package com.learn.javase;

import java.util.Scanner;

import org.junit.Test;

//String类常用方法的演示
public class StringDemos {

	/*
	 * 字符串是不变对象，意思是说，字符串一旦创建，不可改变，若想改变某个字符串内容一定会创建新对象。
	 *
	 * 当使用字面量（直接量）创建字符对象时，JVM会重用之前创建过的对象。
	 * 每当使用字面量创建对象时，JVM会首先查看堆中常量池中是否有该字符串内容的对象存在，若有则直接引用
	 * 没有则创建并缓存在常量池中以便下次引用。 java推荐使用字面量创建字符串对象，减少内存开销。
	 *
	 * 字符串String类源码，被final修饰，不能被继承。
	 */
	@Test
	public void test1() {
		String str 	= "Hello";
		String str1 = "Hello";
		System.out.println(str==str1);//true
		System.out.println(str.equals(str1));//true

		String str2 = new String("Hello");
		str=str+"!";//字符串改变 创建新字符串对象 str1没有改变
		System.out.println(str1==str2);//false ==不再指向同一对象
		System.out.println(str1.equals(str2));//true .equals字符串相同
		System.out.println(str.toString());//Hello!
		System.out.println(str1.toString());//hello
		System.out.println(str==str1);//false

		/*
		 * 编译器的一个优化措施：
		 * 当一个计算表达式计算符两边都是字面量时，会直接计算结果，然后将结果编译到.class文件中，所以下面代码在class
		 * 文件中样子是：String str3 = "Hello";
		 */
		String str3 = "He"+"llo";
		//赋值右边都是直接量时，只要是计算表达式，编译器生成.class文件之前会直接把运算结果赋给str3
		System.out.println(str1==str3);//true
		String s ="He";
		String str4 = s+"llo";//赋值右边不都是直接量时，会创建新的对象。
		System.out.println(str4.toString());//Hello
		System.out.println(str4==str1);//false
	}

	/*
	 * int length()获取当前字符串长度（字符的个数） 无论中文还是英文每个字符都是一个长度
	 */
	@Test
	public void test2() {
		String str 	="Hello";
		String str1 ="你好，String";
		System.out.println(str.length());//5
		System.out.println(str1.length());//9
	}

	/*
	 * int indexOf(String str）查看给定字符串在当前字符串中的位置，若当前字符串
	 * 不包括该内容则返回值为-1。否则是给定字符串第一个字符在当前字符串中对应的下标位置
	 * 检索指定字符串的位置
	 */
	@Test
	public void test3() {
		//java编程思想 	  0123456789012345
		String str = "thinking in java";

		int index = str.indexOf("in");
		System.out.println(index);//2

		/*
		 * int indexOf(String str,int fromIndex)
		 * 重载方法允许从指定位置处开始查找
		 * 从指定位置处开始检索第一次出现"in"的位置
		 */
		index = str.indexOf("in", 3);
		System.out.println(index);//5

		/*
		 * int lastIndexOf(String str)
		 * 检索最后一次出现in的位置。
		 */
		index = str.lastIndexOf("in");
		System.out.println(index);//9

		/*
		 * int lastIndexOf(String str,int fromIndex)
		 * 返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索。
		 */
		index = str.lastIndexOf("in",8);
		System.out.println(index);//5
	}

	/*
	 * String substring(int start,int end)
	 * 截取字符串，从start处开始到end处(不含end处字符) 的字符串截取出来并返回
	 *
	 * java API中有一个特点，通常使用两个数字表示范围时都是"含头不含尾"的[),下标都是从0开始，-1代表没有。
	 */
	@Test
	public void test4() {
		//             01234567890123
		String host = "www.oracle.com";

		String sub = host.substring(4,10);
		System.out.println(sub);
		/*
		 * 若只传入一个参数，为从指定位置处一直截取到字符串末尾。
		 */
		sub = host.substring(4);
		System.out.println(sub);
	}

	/**
	 * 编写一个程序，要求用户输入一个网址信息。
	 * 格式可能如下：www.baidu.com/www.baidu.com.cn/http://www.baidu.com
	 * 然后经过处理，输出域名，如：baidu
	 */
	@Test
	public void test5() {
		System.out.println("请输入一个网址：");
		Scanner scanner=new Scanner(System.in);
		String host=scanner.nextLine();
		//获取第一个点之后第一个字符的位置
		int start=host.indexOf(".")+1;
		//获取第二个点的位置
		int end=host.indexOf(".",start);
		//获取域名
		String sub=host.substring(start, end);
		System.out.println("域名："+sub);
		scanner.close();
	}

	/*
	 * String trim()去除当前字符串两边的空白字符
	 */
	@Test
	public void test6() {
		String str = "  hello		";
		System.out.println(str);
		String trim = str.trim();
		System.out.println(trim);
	}

	/*
	 * char charAt(int index)获取当前字符串中指定位置的字符
	 */
	@Test
	public void test7() {
		String str = "whatisjava？";
		System.out.println(str.charAt(4));
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			System.out.print(c);
		}

		//     0123456789012345
		str = "thinking in java";
		//查看第10个字符是什么？
		char c = str.charAt(9);
		System.out.println(c);

		/*
		 * 检查字符串是否为回文：上海自来水来自海上
		 */
		String info = "上海自来水自来海上";
		for(int i=0;i<info.length()/2;i++){//第一种实现方法
			if(info.charAt(i)!=info.charAt(info.length()-1-i)){
				System.out.print("不");
				break;
			}
		}
		System.out.println("是回文");

/*		for(int i=0;i<info.length()/2;i++){//第二种实现方法
			if(info.charAt(i)!=info.charAt(info.length()-1-i)){
				System.out.println("不是回文");
				return;
			}
		}
		System.out.println("是回文");*/

/*		boolean flag =true;
		for(int i=0;i<info.length()/2;i++){//第三种实现方法
			if(info.charAt(i)!=info.charAt(info.length()-1-i)){
				flag=false;
				System.out.println("不是回文");
				break;
			}
		}
		if(flag){
			System.out.println("是回文");
		}*/

	}

	/*
	 * boolean startWith(String str)
	 * boolean endWith(String str)
	 * 判断当前字符串是否是以给定字符串开始或结束的
	 */
	@Test
	public void test8() {
		String str = "thinking in java";
		System.out.println(str.startsWith("thi"));//true
		System.out.println(str.endsWith("va"));//true
	}

	/*
	 * String toUpperCase()
	 * String toLowerCase()
	 * 将当前字符串中的英文部分转换成全大写或全小写
	 */
	@Test
	public void test9() {
		String str= "Hello";
		System.out.println(str.toUpperCase());
		System.out.println(str.toLowerCase());
		str.equalsIgnoreCase(str);
	}

	/*
	 * String提供了若干重载的静态方法valueOf(XXX xxx)
	 * 	作用是可以将java其他类型转换为字符串，常用于将基本类型转换为字符串使用。
	 */
	@Test
	public void test10() {
		int i = 123;
		String istr = String.valueOf(i);
		System.out.println(istr+4);

		double d = 123.123;
		String dstr = String.valueOf(d);
		System.out.println(dstr+4);

		istr = 123+"";
	}

	/*
	 * 频繁修改字符串带来的性能开销
	 */
	@Test
	public void test11() {
		String str = "a";
		for(int i=0;i<1000000;i++){
			str+=str;//java.lang.OutOfMemoryError 内存溢出
		}
		System.out.println("执行完毕!");
	}

	/*
	 * java.lang.StringBuilder  JDK1.5
	 * 由于String的设计不适合频繁修改内容，所以java提供了一个专门用来修改字符串内容的类
	 * StringBuilder其提供类编辑字符串内容的相关方法，性能良好。
	 *
	 * StringBuilder提供了用于修改字符串内容的相关方法。
	 * 其内部维护的是一个可变的字符数组，所以修改都是在当前对象内部完成的。
	 * 当频繁修改字符串内容时应当使用当前类来完成。
	 */
	@Test
	public void test12() {
		String str = "努力学习java";
		/*
		 * String -> StringBuilder
		 * 将String转换为StringBuilder 可以使用有参的构造方法。
		 * StringBuilder也有无参的构造方法，默认表示控字符串
		 */
		StringBuffer sb= new StringBuffer(str);
		/*
		 * 从StringBuilder转换为String，使用StringBuilder的toString方法即可。
		 */
		str=sb.toString();

		/*
		 * 追加字符串操作
		 * StringBuilder append(String str)	向当前字符串末尾追加给定字符串内容
		 * 有若干重载方法，参数类型不同，这些重载方法允许追加其他内容(先转换为字符串再追加)
		 */
		//努力学习java,为了找个好工作!
		sb.append(",为了找个好工作!");
		System.out.println(sb.toString());//努力学习java,为了找个好工作!

		StringBuilder builder = new StringBuilder("Hello");
		/*
		 * 追加字符串操作
		 * StringBuilder append(String str)
		 * 向当前字符串末尾追加给定字符串内容
		 */
		builder.append("World");

		/*
		 * 插入字符串操作
		 * StringBuilder insert(int offset, String str)
		 * 将给定字符串插入在指定位置，原位置及后续字符串顺序向后移动。
		 */
		builder.insert(5," ");
		builder.insert(11,"!");
		System.out.println(builder.toString());

		/*
		 * 删除字符串操作
		 * StringBuilder delete(int start,int end)
		 * 删除指定范围的字串符
		 */
		builder.delete(0,6);
		System.out.println(builder.toString());

		/*
		 * 将指定范围的字符串替换给定字符串
		 * StringBuilder replace(int start,int end,String str)
		 */
		builder.replace(0,5,"tarena");
		System.out.println(builder.toString());

		/*
		 * 反转当前字符串
		 * StringBuilder reverse()
		 */
		builder.reverse();
		System.out.println(builder.toString());
		StringBuilder info = new StringBuilder("上海自来水来自海上");
		System.out.println(info.reverse().equals(info));//检测回文

		/*
		 * String和StringBuilder
		 * 字符串的添加其实就是使用StringBUilder中append(String str)实现的
		 */
		String s ="AB"; String s1=s+"DE"+1;
		String s2= new StringBuilder(s).append("DE").append(1).toString();
		System.out.println(s1.equals(s2));//true
		System.out.println(s1==s2);//false
	}

	/*
	 * 使用StringBuilder频繁的修改字符串
	 */
	@Test
	public void test13() {
		StringBuilder sb = new StringBuilder("a");
		for(int i=0;i<1000000;i++){
			sb.append("a");
		}
		System.out.println("执行完毕!");
	}

	/*
	 * 使用StringBuffer频繁的修改字符串 多线程保证线程安全
	 * StringBuffer：JDK1.0 是线程安全的，同步处理的，性能稍慢
	 * StringBuilder：JDK1.5 是非线程安全的，并发处理的，性能稍快
	 */
	@Test
	public void test14() {
		StringBuffer sb = new StringBuffer("a");
		for(int i=0;i<1000000;i++){
			sb.append("a");
		}
		System.out.println("执行完毕!");
	}
}
