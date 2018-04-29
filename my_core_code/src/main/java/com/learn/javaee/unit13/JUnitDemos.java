package com.learn.javaee.unit13;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * 断言以及JUnit4的演示
 *
 * @author Double
 *
 */
@RunWith(Parameterized.class)
public class JUnitDemos {

	/*
	 * 断言的使用：1.定义断言 2.启动断言
	 * 1. 断言可以有两种形式	1).assert Expression1	2).assert Expression1:Expression2
	 * 其中Expression1应该总是一个布尔值，Expression2是断言失败时输出的失败消息的字符串。
	 * 如果Expression1为假，则抛出一个 AssertionError，这是一个错误，而不是一个异常，
	 * 也就是说是一个不可控制异常（unchecked Exception),AssertionError由于是错误，
	 * 所以可以不捕获，但不推荐这样做，因为那样会使你的系统进入不稳定状态。
	 *
	 * 2.启动断言
	 * 断言在默认情况下是关闭的，要在编译时启用断言，需要使用source1.4标记 既javac source1.4 Test.java,
	 * 在运行时启用断言需要使用-ea参数 。要在系统类中启用和禁用断言可以使用 -esa 和 -dsa参数。
	 *
	 * 断言的额意义：没有实际意义，主要是因为C++有，Java就引入了。
	 */
	@Test
	public void test1() {
		int num=100;
		/*
		 * 断言：   1. 定义断言: 当num的内容为300时，抛出‘错误：num的内容不是300’
		 * 		2. 启动断言: 复制test1()中代码到D:/Demo.java,外加main方法，和public class Demos
		 * 				在dos中输入javac Demo.java，再次输入java -ea Demo,可以看到断言起作用了。
		 */
		assert num==300:"错误：num的内容不是300";
		System.out.println(num);
	}

	/**
	 *目前支持的主要注解有：
	 *	@BeforeClass 全局只会执行一次，而且是第一个运行。
	 *	@Before 在测试方法运行之前运行
	 *	@Test 测试方法
	 *	@After 在测试方法运行之后允许
	 *	@AfterClass 全局只会执行一次，而且是最后一个运行
	 *	如果有此注解maven中不能添加<scope>test</scope>
	 * 	<!-- 单元测试JUnit4 GAV -->
		 	<dependency>
		 		<groupId>junit</groupId>
		 		<artifactId>junit</artifactId>
		 		<version>4.12</version>
		 		<!-- <scope>test</scope> -->
		 	</dependency>
		 	BeforeClass和AfterClass不能与Before、After、test一起使用
	 */
	/**
	 * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行 因为是static修饰的静态方法，所有只会执行一次。
	 * 通常用来进行一些资源的加载，如通过JUnit测试Spring相关的东西时，可以在这个方法 中加载Spring的配置文件。
	 */
/*	@BeforeClass
	public void test2() {
		System.out.println("@BeforeClass");
	}*/

	/**
     * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次有多少个测试方法就会执行多少次
	 */
	@Before
	public void test3() {
		System.out.println("@Before");
	}


	/**
	 * 被测试方法：演示JUnit4的断言方法assertEquals()
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static int add(int a, int b) {
		return a + b;
	}

	/**
	 * 断言方法测试	绿色代表测试通过，红色代表测试失败
     * assertEquals这个方法是一个断言方法
     * 第一个参数表示预期的结果,第二个参数表示程序的执行结果
     * 当预期结果与执行结果是一致的时候，则表示单元测试成功
     */
	@Test
	public void test4() {
		System.out.println(add(1, 3));
		assertEquals(4, add(1, 3));
	}

	/**
	 * 限时测试：@Test(timeout = 1000) 单位毫秒		绿色代表没有超过1000毫秒，红色代表超过1000毫秒
	 */
	@Test(timeout = 1000)
	public void test5() {
		assertEquals(4, add(1, 3));
	}

	/**
	 * 测试异常：@Test(expected = ArithmeticException.class)
	 * 绿色代表除数为0，红色代表除数不为0
	 */
	@Test(expected = ArithmeticException.class)
	public void test6() {
		assertEquals(4, 1/0);
	}

	/**
	 * 参数化测试
	 * 我们可能遇到过这样的函数，它的参数有许多特殊值，或者说他的参数分为很多个区域。例如，测试一下“计算一个数的平方”
	 * 这个函数，暂且分三类：正数、0、负数。在编写测试的时候，至少要写3个测试，把这3种情况都包含了，
	 * 这确实是一件很麻烦的事情。
	 * 为了简化类似的测试，JUnit4提出了“参数化测试”的概念，只写一个测试函数，把这若干种情况作为参数传递进去，
	 * 一次性的完成测试。
	 */
	/**
	  * 1、更改测试运行器为RunWith(Parameterized.class)
	  * 2、声明变量用来存放预期值与结果值
	  * 3、声明一个返回值为Collection的公共静态方法，并使用@Parameters进行修饰
	  * 4、位测试类声明一个带有参数的公共构造方法，并在其中为声明变量赋值
	 */
	private int except;  //用来存储预期结果
	private int input1;  //用来存储第一个输入参数
	private int input2;  //用来存储第二个输入参数

	@Parameters
	public static Collection<Object[]> initTestData(){
	     return Arrays.asList(
	         new Object[][]{
	         {3,1,2},
	         {10,5,5},
	         {6,4,2},
	         {7,3,4}}
	         );
	     }

	// 构造方法，对变量进行初始化
	public JUnitDemos(int except, int input1, int input2) {
		this.except = except;
		this.input1 = input1;
		this.input2 = input2;
	}
	@Test
	public void test7() {
		//测试了4次
		assertEquals(except, add(input1, input2));
	}

	/**
	 * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次有多少个测试方法就会执行多少次
	 */
	@After
	public void test8() {
		System.out.println("@After");
	}


	/**
	 *
     * @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源。
	 */
/*	@AfterClass
	public void test9() {
		System.out.println("@AfterClass");
	}*/

}
