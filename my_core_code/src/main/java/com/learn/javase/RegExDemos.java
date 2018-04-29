package com.learn.javase;

import java.util.Scanner;

import org.junit.Test;

/**
 * Java中正则表达式的演示  一组特殊的字符串，通配验证，作用：用来 验证(Validation)特殊格式的字符串
 * 正则表达式只关注格式的验证，至于有效性的验证跟正则表达式无关。
 * 正则表达式不是Java特有，各种语言中都有，Java只是做了支持的API,有自己的体系。
 *  测试正则表达式：字符集合
 *	\d：表示任意一个数字
 	\D：表示任意一个非数字字符
	\w：表示任意一个单词字符（只能是 数字，字母，下划线）
	\W：表示任意一个非单词字符
	\s：表示任意一个空白字符(\t \r \n \f \x0B)
	\S：表示任意一个非空白字符
	"[]"用来描述单一字符，方括号内部可以定义这个字符的内容，也可以描述一个范围。

 * @author Double
 *
 */
public class RegExDemos {

	/**
	 * String支持正则表达式方法一: boolean matches(String regex)
	 * 判断当前字符串是否满足给定的正则表达式的格式
	 */
	@Test
	public void test1() {
		String mail = "fancq@tedu.cn";
		/*
		 * 邮箱的正则表达式:
		 * [a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\.[a-zA-Z]+)+
		 * 1:因为"."在正则表达式中表示任意一个字符，为了使其仅表示".",所以要将其转意"\.".
		 * 2:在java中使用正则表达式，需要先用字符串来表示该正则表达式，由于字符串中"\"也是转意字符，而
		 *   "\."会被认为是将"."转意，在字符串中"."不需要转意，所以出现了冲突，实际上是要让字符串表示
		 *   "\.",因此在字符串中要将"\"当做普通的"\"看待。为此应当写为"\\."
		 */
		String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z]+)+";
		System.out.println(regex);

		boolean tf = mail.matches(regex);
		if(tf){
			System.out.println("是邮箱地址");
		}else{
			System.out.println("不是邮箱地址");
		}

		String str="\\\\";
		System.out.println(str.toString());
	}

	/**
	 * 验证手机号码 要求用户输入一个手机号，然后经过验证输出是否是手机号码
	 */
	@Test
	public void test2() {
		System.out.println("请输入一个手机号码：");
		Scanner scanner=new Scanner(System.in);
		String phone=scanner.nextLine();
		//(\+86|0086)?\s*1[0-9]{10}
		String regex="(\\+86|0086)?\\s*1[0-9]{10}";//"1[0-9]{10}"
		if(phone.matches(regex)){
			System.out.println("是手机号码");
		}else{
			System.out.println("不是手机号码");
		}
		scanner.close();
	}

	/**
	 *  String支持正则表达式的方法二: String[] split(String regex)
	 *  将当前字符串中按照满足给定正则表达式的部分进行拆分，然后将拆分出的内容以一个数组形式 返回。
	 */
	@Test
	public void test3() {
		String str = "abc123def456ghi789jkl";
		/*
		 * 按照数字部分进行拆分
		 * 当字符中连续匹配了两次，那么中间会拆分出一个空字符串。但是在字符串末尾连续匹配，则所有被拆分的空字符串被忽略。
		 *
		 */
		String[] array = str.split("[0-9]+");
		System.out.println("len:"+array.length);
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}

		array = str.split("[0-9]");
		System.out.println("len:"+array.length);
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}

		str="abc123def456ghi789jkl11111";
		array = str.split("[0-9]");
		System.out.println("len:"+array.length);
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
	}

	/**
	 * 图片重命名	  871623781987123.jpg
	 */
	@Test
	public void test4() {
		String imgName = "1.jpg";
//		String[] names = imgName.split("\\.");
//		imgName = System.currentTimeMillis()+"."+names[names.length-1];
//		System.out.println(imgName);

		//截取后缀部分
		String name = imgName.substring(imgName.lastIndexOf("."));
		imgName = System.currentTimeMillis()+name;
		System.out.println(imgName);
	}

	/**
	 * String replaceAll(String regex,String str)
	 * 将当前字符串中满足正则表达式的部分替换为给定的字符串。
	 */
	@Test
	public void test5() {
		String str = "abc123def456ghi789jkl";
		/*
		 * 将数字部分替换为"#NUMBER#"
		 */
		str = str.replaceAll("[0-9]+", "#NUMBER#");
		System.out.println(str);
	}

	/**
	 * 使用String replaceAll实现图片重命名
	 */
	@Test
	public void test6() {
		String imgName = "1.jpg";
		imgName = imgName.replaceAll(".+\\.", System.currentTimeMillis()+".");
		System.out.println(imgName);
	}

	/**
	 * 和谐用语
	 */
	@Test
	public void test7() {
		String regex="(mdzz|wqnmlgdsb|cnm|nc|djb|sb|nmd|mlgb|nmslwsnd|py)";
		String message="wqnmlgdsb!你怎么这么nc!mdzz!cnm!你个djb!";
		message = message.replaceAll(regex, "***");
		System.out.println(message);
	}

	/**
	 * 测试正则表达式：字符集合
	 *	\d：表示任意一个数字
	 	\D：表示任意一个非数字字符
		\w：表示任意一个单词字符（只能是 数字，字母，下划线）
		\W：表示任意一个非单词字符
		\s：表示任意一个空白字符(\t \r \n \f \x0B)
		\S：表示任意一个非空白字符
		"[]"用来描述单一字符，方括号内部可以定义这个字符的内容，也可以描述一个范围。
	 */
	@Test
	public void test8() {
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

	/**
	 * 测试正则表达式 “*”，“+”，“？”
	 	"+"：表示内容可以连续出现至少1次以上
		"*"：表示内容出现0-若干次
		"?"：表示内容出现0-1次
	 */
	@Test
	public void test9() {
		String regex1 = "[a-z]*";
		String regex2 = "\\w+\\.jar";
		String regex3 = "\\d?[a-z]*";
		System.out.println("jiqnfg".matches(regex1));
		System.out.println("".matches(regex1));
		System.out.println("util.jar".matches(regex2));
		System.out.println("".matches(regex2));
		System.out.println("fkdas".matches(regex3));
		System.out.println("".matches(regex3));
		System.out.println("5".matches(regex3));
	}

	/**
	 * 验证图片，文档，视频，网页  身份证号码
	 */
	@Test
	public void test10() {

	}

}
