package com.learn.javase;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.junit.Test;


//JavaSE 练习题锦集
public class Exercises {

	//测试String常量池 获取String对象的长度
	@Test
	public void stringTest01() {
		String str 	="Hello";
        // 不会创建新的String对象，而是使用常量池中已有的"Hello".
		String str1 ="Hello";
		 // 使用new关键字会创建新的String对象.
		String str2	=new String("Hello");
		String str3=str2+null;
		System.out.println(str==str1);//true
		System.out.println(str2==str);//false
		System.out.println(str==str3);//false

		str ="Hello";
		str1="你好，String";
	     // 在内存中采用Unicode编码，每个字符占用两个字节.
        // 任何一个字符(无论中文还是英文)都算1个字符长度.
		System.out.print(str.length()+"\t");//5
		System.out.print(str1.length()+"\t");//9
	}

	//在一个字符串中检索另外一个字符串 在一个字符串中截取指定的字符串
	@Test
	public void stringTest02() {
		String str = "I can because i think i can";
		int index = str.indexOf("can");
		System.out.print(index+"\t");//2
		index = str.lastIndexOf("can");
		System.out.print(index+"\t");//24
		System.out.print(str.length()+"\t");//27
		index=str.indexOf("can",6);
		System.out.print(index+"\t");//24
		index = str.indexOf("Can");
		System.out.print(index+"\t");//-1

		str = "http://www.oracle.com";
		String subStr =str.substring(11,17);
		System.out.print(subStr+"\t");//oracle
		subStr = str.substring(7);
		System.out.println(subStr+"\t");//www.oracle.com

		str = "  good man  ";
		System.out.print(str.length()+"\t");//12
		String str1 = str.trim();
		System.out.print(str1+"\t");//good man
		System.out.print(str1.length()+"\t");//8
		System.out.println(str.length()==str1.length());//false

		str = "whatisjava?";
		for(int i=0;i<str.length();i++){
			char cha = str.charAt(i);
			System.out.print(cha);//whatisjava?
		}
		System.out.println();//换行

		str = "Thinking in Java";
		System.out.print(str.endsWith("Java")+"\t");//true
		System.out.print(str.startsWith("Thinking")+"\t");//true
		System.out.print(str.startsWith("t")+"\t");//false

		str	= "我喜欢Java";
		str	= str.toUpperCase();
		System.out.print(str+"\t");//我喜欢JAVA
		str = str.toLowerCase();
		System.out.print(str+"\t");//我喜欢java
	}

	@Test
	public void stringTest03() {
		double pi	=3.14159;
		int value 	=123;
		boolean flag =true;
		char[] cha 	= new char[]{'a','b','c','d','e'};
		char gender ='男';

		String str = String.valueOf(pi);
		System.out.print(str+"\t");
		str = String.valueOf(value);
		System.out.print(str+"\t");
		str = String.valueOf(flag);
		System.out.print(str+"\t");
		str = String.valueOf(cha);
		System.out.print(str+"\t");
		str = String.valueOf(gender);
		System.out.println(str);
	}

	//测试StringBuilder的append的方法
	@Test
	public void stringTest04() {
		StringBuilder builder = new StringBuilder("Programming Language:");
		builder.append("java").append("cpp").append("php").append("c#")
		.append("objective-c");
		System.out.println(builder.toString());
		//Programming Language:javacppphpc#object-c

		builder = new StringBuilder("javacppc#objective-c");
		builder.insert(9,"php");
		System.out.println(builder.toString());//javacppc#phpobjective-c

		builder = new StringBuilder("javaoraclecppc#php");
		builder.delete(4,4+6);
		System.out.println(builder.toString());//javacppc#php

		builder = new StringBuilder("javaoraclecppc#php");
		builder.replace(4,4+6,"google");
		System.out.println(builder.toString());//javagooglecppc#php

		builder = new StringBuilder("javaoraclecppc#php");
		builder.reverse();
		System.out.println(builder.toString());

		String s = "AB";String s1 = s+"DE"+1;
		String s2 = new StringBuilder(s).append("DE").append(1).toString();
		System.out.println(s1);//ABDE1
		System.out.println(s2);//ABDE1
		System.out.println(s1==s2);//false???
		System.out.println(s1.equals(s2));
	}

	/*
	 * 生成一个包含所有汉字的字符串
	 * 在Java中，字符类型事实上是一个16位无符号整数，这个值对应字符的编码,
	 * Java字符类型采用Unicode字符集编码。Unicode是世界通用的定长字符集，所有的字符都是16位。
	 * 字符直接量可以采用诸如：‘中’的形式，也可以采用16进制的表示形式，例如： ‘\u4e2d’。
	 * 所有汉字的Unicode范围是U+4E00～U+9FA5。
	 */
	@Test
	public void stringTest05() {
		StringBuilder builder = new StringBuilder();
		for(char c='\u4e00',i=1;c<='\u9fa5';i++,c++){
			builder.append(c);
			if(i%50==0){
				builder.append("\n");
			}
		}
		System.out.println("一共有："+builder.length()+"个汉字。");//21320
		System.out.println(builder.toString());
	}

	/**
	 * 测试正则表达式{n}，{n,}，{n,m}
	 	{n}：表示内容必须出现n次
	 	{n,}：表示内容出现至少n次
		{n,m}：表示内容出现n-m次
		 ()分组 ^开始  $结束
	 	正则表达式的开始添加"^"以及末尾添加"$"来表示一个整体
	 	() 可以将内容看做一个整体，()中可以使用"|"来表示或关系
	 */
	@Test
	public void regExTest01() {
		String regex1 ="\\w{5}";
		String regex2 ="\\d{5,}";
		String regex3 ="[a-zA-Z]{5}";
		String regex4 ="[a-zA-Z&&[^bc]]{5,8}";
		String regex5 = "\\w{5,8}";
		System.out.println("fjalk".matches(regex1));
		System.out.println("jkl".matches(regex1));
		System.out.println("152454".matches(regex2));
		System.out.println("21".matches(regex2));
		System.out.println("fkdlaj".matches(regex3));
		System.out.println("fkdjK".matches(regex3));
		System.out.println("fklajfldk".matches(regex4));
		System.out.println("adfcs".matches(regex4));
		System.out.println("kfjliop".matches(regex4));
		System.out.println("abcd1234_abcd".matches(regex5));
		System.out.println("12345".matches(regex5));
	}

	/**
	 * 邮箱的正则表达式
	 */
	@Test
	public void regExTest02() {
		String emailRegEx =
				 "^[a-zA-Z0-9_.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
				//"^[a-zA-Z0-9_-.]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
				//			  ^PatternSyntaxException
		String email = "bjlily@tarena.com.cn";
		System.out.println(email.matches(emailRegEx));
	}

	/**
	 * 测试String正则表达式split()方法
	 */
	@Test
	public void regExTest03() {
		String str ="3,tarena,3,tarena@tarena.com, 33";
		System.out.println(Arrays.toString(str.split(",\\s+")));
		String str1 = "java c# php javascript"; ;
		System.out.println(Arrays.toString(str1.split("\\s+")));
		String line = "100+200-150=150";
		String[] arr = line.split("[\\+\\-\\=]");
		//String[] arr = line.split("[\\+\\-=]");
		System.out.println(Arrays.toString(arr));
	}

	/*
	 * 校验密码强度  中文  由数字、26个英文字母或下划线组成的字符串
	 */
	@Test
	public void regExTest04() {
/*		String str=new Scanner(System.in).nextLine();
        boolean flag = Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$", str);
        System.out.println("校验密码强度："+flag);*/

/*      String str=new Scanner(System.in).nextLine();
        boolean flag = str.matches("^[\\u4e00-\\u9fa5]{0,}$");
        System.out.println("校验中文："+flag);*/

		 String str=new Scanner(System.in).nextLine();
	     boolean flag = str.matches("^\\w+$");
	     System.out.println("校验中文："+flag);
	}

	/**
	 *
	 *测试正则表达式：字符集合
	 *	\d：表示任意一个数字
	 	\D：表示任意一个非数字字符
		\w：表示任意一个单词字符（只能是 数字，字母，下划线）
		\W：表示任意一个非单词字符
		\s：表示任意一个空白字符(\t \r \n \f \x0B)
		\S：表示任意一个非空白字符
		"[]"用来描述单一字符，方括号内部可以定义这个字符的内容，也可以描述一个范围。
	 */
	@Test
	public void regExTest05() {
		String regex1="[a-z]";
		String regex2="[^a-z]";
		String regex3="[a-z&&[^bc]]";
		String regex4="\\d";//[0,9]
		String regex5="\\D";//[^0-9]
		String regex6="\\w";//[a-zA-Z0-9_]
		String regex7="\\W";
		String regex8="\\s";//[\t\r\n\f\x0B]
		String regex9="\\S";//[^\t\r\n\f\x0B]
		System.out.println("b".matches(regex1));
		System.out.println("b".matches(regex2));
		System.out.println("a".matches(regex3));
		System.out.println("2".matches(regex4));
		System.out.println("3".matches(regex5));
		System.out.println("_".matches(regex6));
		System.out.println("=".matches(regex7));
		System.out.println("\f".matches(regex8));
		System.out.println(" ".matches(regex9));
	}


	@Test
	public void calendarTest01() {
		Calendar c = Calendar.getInstance();
		System.out.println(c);
		System.out.println(c.getClass().getName());
		System.out.println(c.getTimeInMillis());
		System.out.println(c.getTime());
		System.out.println(c.getTimeZone());
		GregorianCalendar c1 = new GregorianCalendar(2014,Calendar.DECEMBER,25);
		System.out.println(c1.getTime());
	}

	@Test
	public void calendarTest02() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,2015);
		c.set(Calendar.MONTH,Calendar.NOVEMBER);
		c.set(Calendar.DATE,25);
		c.set(Calendar.DAY_OF_WEEK,8);
		System.out.println(c.getTime());
		c.set(Calendar.DATE,30);
		System.out.println(c.getTime());
	}

	/**
	 * 输入一个生产日期格式"yyyy-MM-dd",再输入一个数字(保质期的天数)。
	 * 然后经过计算输出促销日期，促销日期为:该商品过期日前2周的周三
	 * @throws ParseException
	 *
	 */
	@Test
	public void calendarTest03() throws ParseException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一个生产日期:");
		String dateStr = scanner.nextLine();

		System.out.println("请输入一个保质期天数:");
		int days = Integer.parseInt(scanner.nextLine());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//将生产日期转换为Date
		Date date = sdf.parse(dateStr);

		//创建Calendar计算时间
		Calendar calendar = Calendar.getInstance();
		//表示生产日期
		calendar.setTime(date);
		//计算过期日
		calendar.add(Calendar.DAY_OF_YEAR, days);
		//计算过日期两周前
		calendar.add(Calendar.DAY_OF_YEAR, -14);
		//设置为当周周三
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		//转换为Date
		date = calendar.getTime();
		//输出促销日期
		System.out.println("促销日期:"+sdf.format(date));
		scanner.close();
	}

}
