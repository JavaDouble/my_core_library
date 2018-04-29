package com.learn.fundamental;

import java.util.Scanner;

import org.junit.Test;

//分支结构的演示 分支和循环也叫流程控制语句
/**
 *
 * 分支结构
 * 1.if语句/2.if else语句/3.else if语句/4.switch case语句
 *
 * 任何复杂的程序逻辑结构都可以通过“顺序”，分支”，“循环”这三种基本的程序结构实现。
 * 当程序在运行过程中， 需要根据不同的条件而运行不同的语句时，即可以使用分支结构，
 * Java中有专门的语法结构来实现分支：
 * 当条件满足时运行某些语句；当条件不满足时则不运行这些语句——if结构。
 * 当条件满足时运行某些语句； 当条件不满足时运行另外一些语句——if-else结构
 *
 * 1.if语句
 * if语句的执行逻辑
 * 	步骤一：执行语句0；
 * 	步骤二： 判断逻辑表达式的值，此表达式的值结果为boolean类型，即true或者false。
 * 	此处可以是关系表达式也可以是逻辑表达式。
 * 	若值为true，则执行if语句块中的语句；
 * 	若值为false，则跳过if语句块；
 * 	步骤三：执行语句3语句。
 *
 * 2.if-else语句
 * 	if-else语句的执行逻辑
 * 	语句0；
 * 		if（逻辑表达式）{
 *     		语句块1；
 * 		} else {
 *     		语句块2；
 * 		}
 * 	语句3；
 * 步骤一：执行语句0；步骤二：判断if逻辑表达式的值：若值为true，则执行语句块1；
 * 若值为false，则执行语句块2；步骤三：执行语句3语句。
 *
 * 3.else if语句
 * 	if-else语句的嵌套
 *
 * 4.switch-case语句
 * switch-case语句执行逻辑
 * switch-case是一种特殊的分支结构，与else if类似，但其应用面不如else if，只能用于特殊的情况之下，
 * switch-case可以根据一个整数值的不同取值，从不同的程序入口开始执行。语法如下所示：
 * switch(整型表达式) {
 *   case 整型常量值1:     //入口1
 *     语句1;
 *     语句2;
 *   case 整型常量值2:     //入口2
 *     语句3;
 *   ……
 *
 *   default:               //默认入口
 *     语句n;
 *  }
 * 计算整型表达式的值：
 *  若值等于整型常量值1，则从语句1开始执行，而后语句2、3，一直执行到语句n。
 *  若值等于整型常量值2，则从语句3开始执行，一直执行到语句n。
 *  若没有找到匹配的值，则只执行语句n。
 *  通过分析可以看出，switch是以case后的整型常量值作为入口的，若值相等，即开始执行其后面的语句。
 *
 *  使用switch时需要注意两个问题，
 *  	第一，case后面的常量值必须不同，
 *  	第二，switch后面的整型表达式的值必须是整型或字符型。
 *
 *	@author Double
 *
 */
public class BranchDemos {

	/**
	 * 1.if语句
	 * 	1路结构,java语法规定，当if语句块中只包含一条语句时，可以省略“{}”，
	 * 	但是if语句块也只能作用于它下面的一条语句。
	 * 	1.1 if语句的执行逻辑
	 * 	1.2 if语句流程图
	 * 	1.3 if语句用于处理分支逻辑
	 * 	1.4 if语句不要省略"{}"
	 */

	/*
	 * 收银行小程序V1.0
	 */
	@Test
	public void test1() {
		/*
		 * if语句的语法：
		 * 语句0;
		 * if（逻辑表达式）{
		 *      语句1；
		 *      语句2；
		 *   }
		 * 语句3；
		 *
		 * 执行步骤如下：
		 * 步骤一：执行语句0；
		 * 步骤二： 判断逻辑表达式的值，此表达式的值结果为boolean类型，即true或者false。
		 * 此处可以是关系表达式也可以是逻辑表达式。
		 * 若值为true，则执行if语句块中的语句；
		 * 若值为false，则跳过if语句块；
		 * 步骤三：执行语句3语句。
		 *
		 */
		int totalPrice=500;//第一步
		if(totalPrice>=500){//true
			totalPrice*=0.8;//第二步
		}
		System.out.println(totalPrice);//第三步

	}

	/**
	 * 2.if-else语句 两路结构
	 *  2.1 if else语句的执行逻辑
	 *  2.2 if else语句的路程图
	 *  2.3 if else语句用于处理分支逻辑
	 *
	 */
	/*
	 * 收银行小程序V1.1
	 */
	@Test
	public void test2() {
		/*
		 * if-else 语法：
		 * 语句0；
		 * if（逻辑表达式）{
		 *     语句块1；
		 *   } else {
		 *     语句块2；
		 *   }
		 * 语句3；
		 *
		 * 执行步骤如下：
		 * 步骤一：执行语句0；
		 * 步骤二：判断if逻辑表达式的值：
		 * 若值为true，则执行语句块1；
		 * 若值为false，则执行语句块2；
		 * 步骤三：执行语句3语句。
		 *
		 */
		int money=480;
		int totalPrice=500;//第一步
		if(money>=totalPrice){//true
			System.out.println("找零："+(money-totalPrice));//第二步
		}else{//false
			System.out.println("余额不足!");//第二步
		}
		System.out.println(totalPrice);//第三步

		int a=5,b=8;
		int max = a>b?a:b;
		if(a>b){
		  max = a;
		  System.out.println("最大值是:"+a);
		}else{
		  max = b;
		  System.out.println("最大值是:"+b);
		}
	}

	/**
	 * 3.else if语句 多路机构
	 *	3.1 if else语句的嵌套
	 *	3.2 else if语句执行逻辑
	 *
	 * 根据学员的成绩来输出等级：
	 * A（成绩大于等于90分）；B（成绩小于90分且大于等于80分）;C（成绩小于80分且大于等于60分）;D（成绩小于60）
	 *
	 */
	@Test
	public void test3() {
		int num = 2;
		if(num==1){
		  System.out.println(111);
		}else if(num==2){
		  System.out.println(222);
		}else if(num==3){
		  System.out.println(333);
		}else{
		  System.out.println(666);
		}

		int score=95;
		/*
		 * 第一种写法
		 */
		if(score>=90){
			System.out.println("A-优秀");
		}else{
		  if(score>=80 && score<90){
			  System.out.println("B-良好");
		  }else{
		    if(score>=60 && score<80){
		    	System.out.println("C-中等");
			}else{
			  if(score<60){
				  System.out.println("D-不及格");
			  }
			}
		  }
		}

		/*
		 * 第二种写法
		 */
		if(score>=90){
			System.out.println("A");
		}else if(score>=80 && score<90){
			System.out.println("B");
		}else if(score>=60 && score<80){
			System.out.println("C");
		}else{
			System.out.println("D'");
		}

		/*
		 * 第三种写法
		 */
		if(score>=90){
			System.out.println("A");
		}else if(score>=80){
			System.out.println("B");
		}else if(score>=60){
			System.out.println("C");
		}else{
			System.out.println("D'");
		}

		/*
		 * 第四种写法
		 */
		if(score>=0 && score<=100){
			if(score>=90){
				System.out.println("A-优秀");
			}else if(score>=80){
				System.out.println("B-良好");
			}else if(score>=60){
				System.out.println("C-中等");
			}else{
				System.out.println("D-不及格");
			}
		}else{
			System.out.println("成绩不合法");
		}


	}

	/**
	 * 4.switch...case:多路结构
	 * 	优点:效率高、结构清晰	缺点:整数、相等
	 *  break:跳出switch
	 *  在很多情况下,switch-case可以代替else if结构，而switch-case实现分支功能的效率要高于else if结构
	 * ，并且结构更清晰，所以推荐使用。从JDK 7.0开始，switch-case可以支持字符串表达式，将更加方便程序的操作。
	 *
	 *  4.1 switch case语句执行逻辑
	 *  4.2 switch case和break的联合使用
	 *  4.3 switch case语句用于分支
	 *  4.4 swtich case的优势
	 *
	 *
	 */
	@Test
	public void test4() {
		/*
		 * 4.1 switch-case是一种特殊的分支结构，与else if类似，但其应用面不如else if，只能用于特殊的情况之下，
		 * switch-case可以根据一个整数值的不同取值，从不同的程序入口开始执行。语法如下:
		 *  switch(整型表达式) {
		 *  	case 整型常量值1:     //入口1
		 *               语句1;
		 *               语句2;
		 *      case 整型常量值2:     //入口2
		 *               语句3;
		 *       		 ……
		 *      default:          //默认入口
		 *               语句n;
		 *      }
		 *
		 *  执行逻辑如下：
		 *  计算整型表达式的值：
		 *  1.若值等于整型常量值1，则从语句1开始执行，而后语句2、3，一直执行到语句n。
		 *  2.若值等于整型常量值2，则从语句3开始执行，一直执行到语句n。
		 *  3.若没有找到匹配的值，则只执行语句n。
		 *
		 *  通过分析可以看出，switch是以case后的整型常量值作为入口的，若值相等，即开始执行其后面的语句。
		 *  使用switch时需要注意两个问题，第一，case后面的常量值必须不同，
		 *  第二，switch后面的整型表达式的值必须是整型或字符
		 *
		 *  4.2  switch-case和break联合使用
		 *  前面的代码中判断整型表达式的值，若其值等于某个整型常量值，则会以此作为入口，依次执行其后面所有的语句。
		 *  但是在实际应用中，通常case1、case2、…、caseN对应完全不同的操作，即： 若表达式的值等于case1，
		 *  则只执行case1后的语句，不会再执行case2、caseN等后面的语句。这种情况下可以和break语句配合使用，
		 *  执行完相应语句后即退出switch块，不继续执行下面的语句。语法如下：
		 *  switch(整型表达式) {
		 *      case 整型常量值1:     //入口1
		 *             语句1;
		 *             语句2;
		 *             		break;
		 *      case 整型常量值2:     //入口2
		 *             语句3;
		 *                 break;
		 *             ……
		 *      default:               //默认入口
		 *             语句n;
		 *    }
		 *
		 *  上程序中的break语句的作用在于跳出switch结构，其执行逻辑如下：
		 *  计算整型表达式的值：
		 *  1.如果值等于整型常量值1，则执行语句1、语句2，跳出switch结构结束;
		 *  2.若值等于整型常量值2，则执行语句3，跳出switch结构结束;
		 *  3.如果没有找到匹配的值，则执行语句n，结束。default后可以不写break。
		 *
		 *	4.3 switch-case语句用于分支
		 *	4.4	switch-case的优势
		 *		1.switch-case结构在实际应用中较广泛， 常常和break语句结合使用实现分支的功能。
		 *		2.在很多情况下,switch-case可以代替else if结构，而switch-case实现分支功能的效率要高于
		 *			else if结构，并且结构更清晰，所以推荐使用。
		 *		3.从JDK 7.0开始，switch-case可以支持字符串表达式，将更加方便程序的操作。
		 *
		 */

		int num = 2;
		switch (num) {
		case 1:
			System.out.println("呼叫教学部");
			break;
		case 2:
			System.out.println("呼叫人事部");
			break;
		default:
			System.out.println("人工服务");
		}
		//输出结果为“呼叫人事部“，因为匹配case 2输出后，即break跳出switch语句了。

		Scanner sc =new Scanner(System.in);
		System.out.println("请选择功能：1.显示全部记录 2.查询登录记录 0.退出");
		int command = sc.nextInt();
		switch(command){
		case 1:
			System.out.println("显示全部记录");
			break;
		case 2:
			System.out.println("查询登录记录");
			break;
		case 0:
			System.out.println("退出");
			break;
		default:
			System.out.println("输入错误");
		}
		sc.close();
	}

}
