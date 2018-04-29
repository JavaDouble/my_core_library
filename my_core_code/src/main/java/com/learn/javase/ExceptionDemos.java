package com.learn.javase;

import java.awt.AWTException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
//Java异常处理机制的演示
public class ExceptionDemos {

	/**
	 * 认识异常
	 * java.lang.NullPointException
	 */
	@Test
	public void test1() {
		System.out.println("程序开始了！");
		String str=null;
		System.out.println(str);
		System.out.println(str.length());//java.lang.NullPointException
		System.out.println("程序结束了！");
	}

	/**
	 * java异常捕获机制中的try-catch
	 * try块用来包含可能出错的代码片段
	 * catch通常跟在try块之后，用来捕获try块中的代码出现的异常并解决。
	 *
	 * try块不能独立存在，后面必须跟catch块或finally块
	 *
	 */
	@Test
	public void test2() {
		System.out.println("程序开始了");
		try{

			String str = "a";
			System.out.println(str.length());
			System.out.println(str.charAt(0));
			/*
			 * JVM在执行代码的过程中若发现了一个异常，则会实例化这个异常的实例，并将代码整个执行过程完整设置到
			 * 异常中来表示该错误报告，设置完毕后该异常抛出。若抛出异常的这句代码有被try{}包围，则JVM会检查
			 * catch是否关注该异常，关注则交给catch处理，否则会将异常抛出到当前方法外，由调用当前方法的代码
			 * 处理该异常。
			 */
			System.out.println(Integer.parseInt(str));
			//java.lang.NumberFormatException 数字格式异常
			System.out.println("!!!!!");
		/*
		 * catch可以定义多个，针对try代码中不同的异常有不同解决手段时，应当单独捕获这些异常并处理
		 */
		}catch(NullPointerException e){
			System.out.println("出现了一个空指针!");
		}catch(StringIndexOutOfBoundsException e){
			System.out.println("字符串下标越界了!");
		/*
		 * 应当养成一个好习惯，能处理的异常捕获后，一定处理。不能处理的，最后一定捕获Exception避免因未捕获异常导致中断。
		 * 当多个异常间存在父子类继承关系时，应当先捕获子类异常再捕获父类异常。
		 */
		}catch(Exception e){
			System.out.println("反正就是出了个错!");
		}

		System.out.println("程序结束了");
	}
	/**
	 * finally块
	 * finally块只能定义在异常捕获机制的最后，且只能定义一次。
	 * finally块可以保证其中的代码一定执行，无论try块中的代码是否抛出异常。
	 *
	 * 所以通常在finally语句中可以进行资源的消除工作，如关闭打开的文件、删除临时文件，关闭流等，释放其资源。
	 */
	@Test
	public void test3() {
		System.out.println("程序开始了!");

		try {
			String str = "";
			System.out.println(str.length());
		} catch (Exception e) {
			System.out.println("出错了!");
		} finally{
			System.out.println("finally中的代码被执行了");
		}

		System.out.println("程序结束了!");
	}

	/**
	 * 异常捕获机制在IO中的应用
	 *
	 */
	@Test
	public void test4() {
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream("fos.dat");
			fos.write(1);
		}catch(IOException e){
			System.out.println("读写出异常了!");
		}finally{
			if(fos!=null){
				try{
					fos.close();
				}catch(Exception e){

				}
			}
		}
	}

	/**
	 * 测试异常的抛出与捕获
	 */
	@Test
	public void test5() {
		Person p = new Person();
		/*
		 * 当调用一个含有throws声明异常抛出 的方法时，编译器要求必须处理该异常 处理方式有两种:
		 * 1:使用try-catch捕获并处理
		 * 2:在当前方法上继续使用throws声明该异常的抛出 		不要在main方法上使用throws。
		 */
		try {
			p.setAge(10000);
		} catch (IllegalAgeException e) {
			e.printStackTrace();
		}

		System.out.println(p.getAge());
	}

	/**
	 * 异常常用API
	 */
	@Test
	public void test6() {
		System.out.println("程序开始了");
		try {
			String str = "a";
			System.out.println(Integer.parseInt(str));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出错了");

			String str  = e.getMessage();
			System.out.println("message:"+str);
		}
		System.out.println("程序结束了");
	}


	/**
	 * 年龄不合法异常	自定义异常，通常是用来描述某个业务逻辑上出现的问题。自定义异常的名字应当做到见名知义
	 * 当年龄不在0-100之间时则认为是年龄不合法
	 *
	 */
	class IllegalAgeException extends Exception{

		/**
		 *
		 */
		private static final long serialVersionUID = 494464225445593210L;

		public IllegalAgeException() {
			super();
		}

		public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

		public IllegalAgeException(String message, Throwable cause) {
			super(message, cause);
		}

		public IllegalAgeException(String message) {
			super(message);
		}

		public IllegalAgeException(Throwable cause) {
			super(cause);
		}

	}

	/**
	 * 使用该类测试异常的抛出
	 *
	 * @author Double
	 *
	 */
	class Person {
		private int age;

		public int getAge() {
			return age;
		}

		/**
		 * 当一个方法中使用throw抛出一个异常时，就要在方法上使用throws声明该类异常的抛出已通知调用者处理。
		 * 只有RuntimeExceptino及其子类异常使用throw抛出时不强制要求必须使用throws声明其他异常都是
		 * 强制要求的，否则编译不通过。
		 *
		 * @param age
		 * @throws IllegalAgeException
		 */
		public void setAge(int age)throws IllegalAgeException{
			if(age<0||age>100){
				//JDK API常见
				throw new IllegalAgeException("年龄不合法!");
			}
			this.age = age;
		}

	}

	/**
	 *
	 * 子类重写父类含有throws声明异常抛出方法时，对throws的重写规则
	 *
	 * @author Double
	 *
	 */
	class ExceptionDemo5{
		public void dosome() throws IOException,AWTException{

		}
	}

	class Son extends ExceptionDemo5{
		//不再抛出任何异常
//		public void dosome(){
//
//		}

		//只抛出父类方法抛出的部分异常
//		public void dosome()throws IOException{
//
//		}

		//抛出父类方法抛出异常的子类型异常
//		public void dosome()throws FileNotFoundException{
//
//		}

		//不可以抛出额外异常
//		public void dosome()throws SQLException{
//
//		}

		//不可以抛出父类方法抛出异常的父类型异常
//		public void dosome()throws Exception{
//
//		}
	}
}