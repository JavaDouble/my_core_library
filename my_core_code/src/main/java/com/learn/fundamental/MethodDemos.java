package com.learn.fundamental;

import java.util.Scanner;

import org.junit.Test;

//方法的演示和猜字符小游戏
/**
 * 一：方法
 *  方法（函数，过程）
 *    1)各种语言都有方法的概念（有的语言称其为函数或过程），
 *    2)java中也一样。方法常常用于封装一段特定的逻辑功能，例如：执行计算或操作
 *    3)方法可以在程序中反复被调用，这样，就可以减少代码的重复，更便于程序的维护。
 * 	1.1 方法的定义
 * 	 1.1.2 定义方法(函数，过程)的功能
 * 		1) 方法用于封装一个特定的功能
 * 		2) 定义时需要考虑五个要素：修饰词、返回值类型、方法名、参数列表、方法体。
 * 		    通过下面的代码进行讲解：
 * 			public  static int  sum ( int  num1 , int  num2 ) {
 *            // 方法体
 *          }
 *        其中，public static为修饰词（后续课程详细介绍这两个关键字），
 *        int为返回值类型，sum为方法名（见名知义），int num1,int num2为参数列表。
 *        一对大括号｛｝中的为方法体（具体的业务功能实现）。
 *	 1.1.3 定义参数和返回值
 *	   1)方法的参数是指：在调用时传递给方法，需要被方法处理的数据。
 *	   2)方法可以有参数也可以没有参数，绝大部分方法为有参数的，因为方法有参数可以使方法的处理更加灵活；
 *	   3)在方法定义时，需要声明该方法所需要的参数变量（注意：声明该参数变量时需添加数据类型信息）；
 *	   4)在方法调用时，会将实际的参数值（数据）传递给方法的参数变量，同时必须保证传递参数的类型和个数符合方法的声明。
 *
 *	   方法调用结束后可以返回一个数据，称之为返回值。当然，方法调用结束后也可以不返回数据。
 *	 Java语法规定:方法在声明时必须指定返回值类型，可分如下的两种情况进行处理：
 *		1)若方法不需要返回数据，将返回值类型声明为void。
 *		2)若方法需要返回数据，将返回值类型声明为特定数据类型。
 *
 * 	1.2 方法的调用
 *	 1.2.1 return语句
 * 	  1)在方法体中可通过return语句返回，return语句的作用在于结束方法且将数据返回给调用方。
 * 	  2)若方法定义的返回值类型为非void，则方法体中必须使用return返回，并且return后的表达式类型必须与方法定义
 * 		的返回类型匹配，
 * 	  3)若方法定义的返回值类型为void，则方法体中可以不使用return返回，也可以使用return返回，若使用return ，
 * 		则它的作用仅在于结束方法调用而返回调用方。
 * 	 1.2.2 调用方法时的参数传递
 *
 * 二：猜字母游戏
 *
 *
 * @author Double
 *
 */

/**
 * 笔记:
1.方法:
  1)封装一段特定的业务逻辑功能
  2)尽可能的独立，一个方法只干一件事
  3)方法可以被反复调用多次
  4)避免代码重复，有利于代码的复用，有利于团队协作开发
2.方法的定义:
    修饰词 返回值类型 方法名(参数列表){
	  方法体
	}
3.方法的调用:
  1)无返回值: 方法名(有参传参);
  2)有返回值: 数据类型 变量 = 方法名(有参传参);
4.return的用法:
  1)return 值; //1)结束方法的执行 2)返回结果给调用方法
  2)return;    //1)结束方法的执行

猜字符小游戏:
一.设计数据结构:变量
   1.char[] chs;   //随机字符数组
   2.char[] input; //用户输入的字符数组
   3.int[] result; //对比的结果
   4.int score;    //得分
     int count;    //猜错的次数
二.设计程序结构:方法
   1.主方法:
       public static void main(String[] args){
	     //...
	   }
   2.随机生成字符数组:
       public static char[] generate(){
	     char[] chs = new char[5];
		 //...
		 return chs;
	   }
   3.对比:随机字符数组与用户输入的字符数组
       public static int[] check(char[] chs,char[] input){
	     int[] result = new int[2];
		 //...
         return result;
	   }

三.设计算法:
每天练习语法-------吃糖豆	讲项目-------------吃药
String str = "abcde";
1)将字符串转换为字符数组
    char[] input = str.toCharArray();
2)将字符串转换为大写字母
    str = str.toUpperCase();
  将字符串转换为小写字母
    str = str.toLowerCase();
3)比较字符串内容相同
    if(str.equals("EXIT")){
	}

1)藏起来一个数chs
2)提示用户猜吧!接收用户输入的数input
3)将chs与input对比，得到对比结果判断
4)若猜对了，则程序结束
  若未猜对，则重复第234步

基本类型比较相等，用==
String类型比较内容相等，用equals()方法

EXIT时退出循环

 * @author Double
 *
 */
public class MethodDemos {
	//无参无返回值
	public static void say() {
		System.out.println("大家好，这里是Java学习中心！");
	}

	//有参无返回值
	public static void sayHi(String name) {
		System.out.println("大家好，我叫"+name);
		return;//1.结束方法的执行--一般都是在某种特定条件下来结束方法的执行
	}

	//无参有返回值
	public static int getNum() {
		//return; //编译错误，必须返回一个值
		//return 5.5; //编译错误，返回值类型不匹配
		return 5;//1.结束方法的执行  2.返回结果给调用方
	}

	//有参有返回值
	public static double plus(double a,int b) {
		//double num = num1+num2;
		//return num; //返回的是num所存的那个数
		return a+b;
	}

	public static void a(){
		System.out.println(111);
		b();
		System.out.println(222);
	}
	public static void b(){
		System.out.println(333);
		c();
	}
	public static void c(){
		System.out.println(444);
	}
	/*
	 * 方法的定义和调用
	 */
	@Test
	public void test1() {
		//say();

		//sayHi(); //编译错误，有参必须传参
		//sayHi(25); //编译错误，参数类型必须匹配
		//sayHi("zhangsan"); //String name="zhangsan"
		//sayHi("lisi"); //String name="lisi"
		//sayHi("wangwu"); //String name="wangwu"

		//int a = getNum(); //getNum()的值就是return后的那个数
		//System.out.println(a); //5

		double b = plus(5.0,6); //double a=5.0,double b=6
		System.out.println(b); //11.0

		double m=5.0;
		int n=6;
		double c = plus(m,n); //double a=m(5.0),int b=n(6)
		System.out.println(c);

		//a(); //方法的嵌套调用

		System.out.println("over");
	}

	/*
	 * 生成随机字符数组
	 */
	public static char[] generate0() {
		char[] chs=new char[5];//随机字符数组
		char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' }; //随机字符范围
		boolean[] flags=new boolean[26];//开关数组
		for(int i=0;i<chs.length;i++){//遍历随机字符数组
			int index;
			do{
				index=(int)(Math.random()*26);//生成0到25之间的随机数
			}while(flags[index]==true);//当下标对应的开关为true时，表明已存过，则重新生成下标
										//当下标对应的开关为false时，表明未存过，则循环结束
			chs[i]=letters[index];//基于index下标到letters中获取字符并将其赋值给chs中的每一个元素
			flags[index]=true;//将下标对应的开关改为true，表明已存过
		}
		return chs;
	}

	/*
	 * 随机生成字符数组和用户输入字符数组比对
	 * 位置对是在字符对的基础之上的
	 */
	public static int[] check0(char[] chs,char[] input) {
		int[] result=new int[2];//0,0  result[0]位置对，result[1]字符对
		for(int i=0;i<chs.length;i++){ //遍历随机字符数组
			for(int j=0;j<input.length;j++){ //遍历用户输入的字符数组
				if(chs[i]==input[j]){//字符对 判断字符是否正确
					result[1]++;//字符对个数增1
					if(i==j){//位置对  判断位置是否正确
						result[0]++;//位置对个数增1
					}
					break;//input剩余元素不再比较了
				}
			}
		}
		return result;
	}
	/*
	 * 猜字符小游戏 V1.0
	 */
	@Test
	public void test2() {
		int count=0;//猜错的次数 计数器
		char[] chs=generate0();//获取随机字符数组
		System.out.println(chs);//作弊
		Scanner scanner=new Scanner(System.in);

		while(true){//自造死循环
			System.out.println("猜吧！");
			String str=scanner.next().toUpperCase();//接收用户输入的字符串并转换为大写字母
			char[] input=str.toCharArray(); //将用户输入的字符串转换为字符数组
			if("EXIT".equals(str)){
				System.out.println("欢迎下次再来！");
				break;
			}
			int[] result=check0(chs, input);//对比:随机字符数组与用户输入的字符数组
			if(result[0]==chs.length){ //猜对了
				int score=100*chs.length-10*count; //一个字符100分，猜错一次扣10分
				System.out.println("恭喜你，猜对了！得分："+score);
				break;
			}else{ //猜错了
				count++;//猜错次数增1
				System.out.println("字符对:"+result[1]+"个，位置对:"+result[0]+"个");
			}
		}
		scanner.close();

	}

	/**
	 * 随机生成需要猜测的字母序列
	 *
	 * @return 存储随机字符的数组
	 */
	public static char[] generate1() {

		char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };
		boolean[] flags = new boolean[letters.length];
		char[] chs = new char[5];
		for (int i = 0; i < chs.length; i++) {
			int index;
			do {
				index = (int) (Math.random() * (letters.length));
			} while (flags[index]);// 判断生成的字符是否重复
			chs[i] = letters[index];
			flags[index] = true;
		}
		return chs;
	}

	/**
	 * 比较玩家输入的字母序列和程序所生成的字母序列，逐一比较字符及其位置，并记载比较结果
	 *
	 * @param chs
	 *     程序生成的字符序列
	 * @param input
	 *     玩家输入的字符序列
	 * @return 存储比较的结果。返回值int数组 的长度为2，其中，索引为0的位置
	 *   用于存放完全猜对的字母个数(字符和位置均正确)，索引为1的位置用于存放猜对的字母个数(字符正确，但是位置不正确)。
	 */
	public static int[] check(char[] chs, char[] input) {
		int[] result = new int[2];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < chs.length; j++) {
				if (input[i] == chs[j]) {// 判断字符是否正确
					result[1]++;
					if (i == j) {// 判断位置是否正确
						result[0]++;
					}
					break;
				}
			}
		}
		return result;
	}

	/*
	 * 猜字符小游戏 V1.0.1
	 */
	@Test
	public void test3() {
		// 表示玩家猜测的次数
		int count = 0;
		// 用于保存判断的结果
		int[] result = new int[2];
		Scanner scanner = new Scanner(System.in);
		System.out.println("GuessingGame>欢迎尝试猜字母游戏！");
		// 表示猜测的字符串
		char[] chs = generate1();
		System.out.println(chs);
		System.out.println("GuessingGame>游戏开始，请输入你所猜的5个字母序列：（exit——退出）");
		while (true) {
			String inputStr = scanner.next().trim().toUpperCase();
			if ("EXIT".equals(inputStr)) {
				System.out.println("GuessingGame>谢谢你的尝试，再见！");
				break;
			}

			char[] input = inputStr.toCharArray();
			result = check(chs, input);
			if (result[0] == chs.length) {// 完全猜对的情况
				int score = 100 * chs.length - count * 10;
				System.out.println("GuessingGame>恭喜你猜对了！你的得分是：" + score);
				break;
			} else {
				count++;
				System.out.println("GuessingGame>你猜对" + result[1] + "个字符，其中"
						+ result[0] + "个字符的位置正确！（总次数=" + count + "，exit——退出）");
			}
		}
		scanner.close();
	}

	public static char[] generate(){
		char[] chr=new char[5];

		char[] chs=new char[26];
		char num =65;
		for(int i=0;i<chs.length;i++){
			chs[i]=(char)(i+num);
			//System.out.print(chs[i]+" ");
		}
		boolean[] flags=new boolean[chs.length];
		for(int i=0;i<chr.length;i++){
			int index;
			do{
				index=(int)(Math.random()*chs.length);
			}while(flags[index]);
			chr[i]=chs[index];
			flags[index]=true;
		}
		return chr;
	}
	/*
	 * 猜字符小游戏 V1.0.2
	 */
	@Test
	public void test4() {
		// 表示玩家猜测的次数
		int count = 0;
		// 用于保存判断的结果
		int[] result = new int[2];
		Scanner scanner = new Scanner(System.in);
		System.out.println("GuessingGame>欢迎尝试猜字母游戏！");
		// 表示猜测的字符串
		char[] chs = generate1();
		System.out.println(chs);
		System.out.println("GuessingGame>游戏开始，请输入你所猜的5个字母序列：（exit——退出）");
		while (true) {
			String inputStr = scanner.next().trim();
			if ("EXIT".equalsIgnoreCase(inputStr)) {
				System.out.println("GuessingGame>谢谢你的尝试，再见！");
				break;
			}

			char[] input = inputStr.toCharArray();
			result = check(chs, input);
			if (result[0] == chs.length) {// 完全猜对的情况
				int score = 100 * chs.length - count * 10;
				System.out.println("GuessingGame>恭喜你猜对了！你的得分是：" + score);
				break;
			} else {
				count++;
				System.out.println("GuessingGame>你猜对" + result[1] + "个字符，其中"
						+ result[0] + "个字符的位置正确！（总次数=" + count + "，exit——退出）");
			}
		}
		scanner.close();
	}

}
