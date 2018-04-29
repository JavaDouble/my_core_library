package com.learn.fundamental;

import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

//循环结构的演示
/**
 * 一.循环结构 (while、do…while、for)
 *	1.1循环:反复执行一段相同或相似的代码
 *
 * 	1.2.循环三要素:
 * 	  1)循环变量的初始化
 * 	  2)循环的条件(以循环变量为基础)
 *    3)循环变量的改变(向着循环的结束变)
 *   循环变量:在循环过程中所反复改变的那个量
 *
 *  1.3.循环结构:
 *   1)while:先判断后执行，有可能一次都不执行
 *   2)do...while:先执行后判断，至少执行一次
 *      第1要素与第3要素相同时，首选
 *   3)for:应用率最高，固定次数循环
 *
 *  1.4.break:跳出循环
 *   continue:跳过循环体中剩余语句而进入下一次循环
 *
 *  反复执行的，在软件系统中可以通过循环这种语法结构来解决。循环是程序设计语言中反复执行某些代码的一种计算机处理过程，
 *  是一组相同或相似语句被有规律的重复性执行。
 *  对于循环来说，需要考虑两个要素，其一要素为循环体， 也就是被反复执行的相同或相似的语句，其二要素为循环的条件，
 *  也就是循环得以继续执行下去的条件，常常以循环次数的方式体现。常用的循环结构有：while、do-while、for。
 *
 * 1.while语句
 * while语句的执行逻辑
 * while语句是循环的一种常见语法结构，语法如下：
 * 循环变量的初始化 	//1
 * while( boolean表达式 )//2
 * {语句块；}//4 3
 * 执行顺序：1 243 243 243 243 243 243 2....
 * while语句的执行过程为，首先计算boolean表达式的值，而后进行判断，若值为true则执行语句块，
 * 语句块执行完后再次判断boolean表达式的值，如果为true则继续执行语句块，如此循环往复，
 * 直到boolean表达式的值为false时退出while循环而执行while之后的语句。
 *
 * 需要注意的是，一般情况下，循环操作中会存在使得循环条件不满足的可能性，否则该循环将成为“死循环”。
 * “死循环”意味着会一直执行循环体操作，循环后面的语句永远不会被执行，“死循环”在软件系统中是需要避免的。
 * 使用break语句跳出循环  continue:跳过循环体中剩余语句而进入下一次循环
 *
 * 2.do-while语句
 * do-while语句的执行逻辑
 * do-while语句也是循环的一种常见语法结构，语法如下：
 * 循环变量
 * do {
 * 语句块//4 1,3
 * } while( boolean表达式 ) ;//2 执行顺讯4 1324 1324 132
 * do-while语句的执行过程为，先执行语句块，再判断boolean表达式，如果为true则再次执行语句块，
 * 如此循环往复，直到boolean表达式的值为false时止。也就是说，do-while语句，无论boolean表达式是否为true，
 * 都先执行一次语句块。
 * 与while语句一样，do-while语句也要避免“死循环”的发生。
 * 使用break语句跳出循环  continue:跳过循环体中剩余语句而进入下一次循环
 *
 * while和do-while语句的区别:
 * while与do-while都是用于执行循环结构的语句，区别在于，while循环先判断再执行，而do-while循环先执行一次，
 * 再判断。那么，当初始情况不满足循环条件时，while循环就一次都不会执行，而do-while循环不管任何情况都至少会执行一次。
 * 注意：while和do-while语句的不同仅仅体现在第一次就不满足条件的循环中；如果不是这样的情况，while与do-while
 * 可以互换。
 *
 * 3.for语句
 * for语句的执行逻辑
 * for语句是循环中最最常用的一种方式。for循环用于将某个语句或语句块重复执行预定次数的情形。
 * for ( 表达式1；表达式2；表达式3  )  {
 * 		语句块（循环体）//4
 * }执行顺序1 243 243 243 243 243243 2...
 * 可以看出，for循环的三个表达式之间通过分号；进行分隔，其执行逻辑如下所示：
 * 计算表达式1的值，通常为循环变量赋初值；
 * 计算表达式2（表达式2为逻辑表达式）的值，即判断循环条件是否为真，若值为真则执行循环体一次(语句块)，否则跳出循环；
 * 执行循环体；
 * 计算表达式3的值，此处通常写更新循环变量的赋值表达式；
 * 计算表达式2的值，若值为true则执行循环体，否则跳出循环；
 * 如此循环往复，直到表达式2的值为false。
 * for语句三个表达式特殊用法
 * 在实际使用时，for语句可以有几种特殊的使用方法：
 * 特殊方式1：表达式1位置内容为空，
 *  表达式1
 *  for (；表达式2；表达式3  )  {
 * 		语句块（循环体）//4
 * }
 * 虽然省略了表达式1，但只是将它放在了for循环的外面进行声明，只是位置不同而已。在此需要注意一点，
 * 即使for语句中不写表达式1了，表达式2前面的分号；也不能省略。
 * 特殊方式2：表达式3位置内容为空时，
 * for ( 表达式1；表达式2； )  {
 * 		语句块（循环体）//4
 * 		表达式3
 * }
 * 虽然省略了表达式3，但也只是将它放在了for循环体之中，只是位置不同而已。在此需要注意一点，
 * 即使for语句中不写表达式3了，表达式2后面的分号；也不能省略。
 * 特殊方式3：表达式1,2,3位置内容均为空时
 * for (；； )  {
 * 		语句块（循环体）//4
 * }
 * 上代码没有循环变量、没有条件控制，因此会造成死循环，而死循环在编写程序过程中是必须要避免的，
 * 可以在循环体中添加break跳出循环。
 * 特殊方式4：表达式 1 和 3 位置内容的多样化
 * for语句中的三个表达式中表达式1和表达式3可以使用逗号表达式，逗号表达式就是通过”，”
 * 运算符隔开的多个表达式组成的表达式，从左向右进行计算。
 * for ( int  i =1 , j = 6  ;  i <= 6  ;  i +=2 , j -=2 ) {
 * 		System.out.println(“ i , j = “ + i + “,” + j );
 * }
 * 上面的代码的执行逻辑如下：初始设置i为1，j为6，判断i是否小于等于6，为真执行循环体，而后执行i+=2，j-=2，
 * 即：i增2，j减2。再判断i是否小于等于6，为真继续执行循环体，以此类推，直到条件为false。本程序的输出结果为：
 * i , j = 1 , 6
 * i , j = 3 , 4
 * i , j = 5 , 2
 *
 * 二.循环问题
 * 3种循环（while、do-while、for）中，
 * 一般情况下，for循环使用得最多，而对于for循环结构，一定要分析出需要解决业务的三个部分：
 * 1、循环变量初始状态
 * 2、循环条件
 * 3、循环变量的改变
 * 分析好了上面的三个部分，for语句的结构也就定义好了，其它的问题只在于填写不同的循环体来解决不同的业务问题而已。
 *
 * 如果业务可以转换为“当……“这样的句式时，优先选择while语句来实现。
 * 如果业务可转换为”直到……”这样的句式时，优先选择do-while语句来实现。
 * 如果业务中可以获取到一个确切的循环次数时可以考虑使用for循环来实现。
 * 嵌套循环
 *
 * @author Double
 *
 */
public class CirculationDemos {

	/**
	 * 1.循环:反复执行一段相同或相似的代码
	 *
	 * 2.循环三要素:
	 *  1)循环变量的初始化
	 *  2)循环的条件(以循环变量为基础)
	 *  3)循环变量的改变(向着循环的结束变)
	 *    循环变量:在循环过程中所反复改变的那个量
	 *
	 * 3.循环结构:
	 *  1)while:先判断后执行，有可能一次都不执行
	 *  2)do...while:先执行后判断，至少执行一次
	 *   第1要素与第3要素相同时，首选
	 *  3)for:应用率最高，固定次数循环
	 *
	 * 4.break:跳出循环
	 *  continue:跳过循环体中剩余语句而进入下一次循环
  	 */

	/**
	 * 1。while语句
	 * 	1.1 while语句的执行逻辑
	 *  1.2 while语句的流程图
	 *  1.3 while语句用于处理循环逻辑
	 *  1.4 使用break语句跳出循环
	 */
	@Test
	public void test1() {
		/*
		 * while语句是循环的一种常见语法结构，语法如下：
		 *     while( boolean表达式 ) {
		 *     			语句块；
		 *    	}
		 *  1.while语句的执行过程为，首先计算boolean表达式的值，而后进行判断。
		 *  2.若值为true则执行语句块，语句块执行完后再次判断boolean表达式的值，如果为true则继续执行语句块，
		 *  如此循环往复，直到boolean表达式的值为false时退出while循环而执行while之后的语句。
		 *
		 *	需要注意的是，一般情况下，循环操作中会存在使得循环条件不满足的可能性，
		 * 否则该循环将成为“死循环”。“死循环”意味着会一直执行循环体操作，循环后面的语句永远不会被执行，
		 * “死循环”在软件系统中是需要避免的。
		 */
		int age=1;//1
		while(age<=100){//2
			System.out.println("马上有钱");//4
			age++;//2 执行顺序 1 243 243 243 2......
		}

		int num=1;
		while (num<=9) {
			System.out.println(num+"*9="+num*9);
			num++;
		}
		System.out.println("over");

		int times = 0; //1.循环变量的初始化
		while(times<10){ //2.循环的条件
			System.out.println("行动是成功的阶梯");
			times++;     //3.循环变量的改变
		}
		System.out.println("over");

		/*
		 * break用在循环体中用于退出循环结构
		 */
		int x = 0;
		while (x < 10) {
			if (5 == x) {
				break;
			}
			System.out.print(x+"\t");
			//输出结果为0 1 2 3 4，因为当x等于5时执行break语句直接退出循环结构了，
			//即if语句块后面的输出x的值以及x++不再被执行。
			x++;
		}
		System.out.println();
		/*
		 * continue用在循环体中用于退出本次循环，进入一下次循环
		 */
		x = 0;
		while (x < 10) {
			System.out.print(x+"\t");
			//输出结果为0 1 2 3 4 6 7 8 9 ，因为当x等于5时执行continue语句直接退出本次循环，进入下次循环了。
			x++;
			if (5 == x) {
				continue;
			}
		}

		/*
		 * 死循环
		 */
		while (true) {
			System.out.println("死循环");
		}
	}

	/**
	 * 2.do while语句
	 *  2.1 do-while语句的执行逻辑
	 *  2.2 do-while语句的流程图
	 *  2.3 while和do-while的区别
	 */
	@Test
	public void test2() {
		/*
		 * do-while语句也是循环的一种常见语法结构，语法如下：
		 * do {
		 *   	语句块
		 *  } while( boolean表达式 ) ;
		 *
		 *  1.do-while语句的执行过程为，先执行语句块。
		 *  2.再判断boolean表达式，如果为true则再次执行语句块，如此循环往复，直到boolean表达式的值为false时止。
		 *  也就是说，do-while语句，无论boolean表达式是否为true，都先执行一次语句块。
		 *
		 *  与while语句一样，do-while语句也要避免“死循环”的发生。
		 *  无论boolean表达式是否是true，都先执行一次语句块。
		 *
		 *	while和do-while语句的区别：
		 *  while与do-while都是用于执行循环结构的语句，区别在于，while循环先判断再执行，而do-while循环
		 *  先执行一次，再判断。那么，当初始情况不满足循环条件时，while循环就一次都不会执行，
		 *  而do-while循环不管任何情况都至少会执行一次。
		 *  注意：while和do-while语句的不同仅仅体现在第一次就不满足条件的循环中；如果不是这样的情况，
		 *  while与do-while可以互换。
		 */

		Scanner sc=new Scanner(System.in);
		int pwd;
		do {
			System.out.println("请输入密码：");//4
			pwd=sc.nextInt();//1 3
		} while (123!=pwd);//2 执行顺序 4 132 132 132...

		pwd=0;
		while(123!=pwd){
			System.out.println("请输入密码：");
			pwd=sc.nextInt();
		}

		sc.close();
	}

	/**
	 * 3.for语句
	 *  3.1 for语句的执行逻辑
	 *  3.2 for语句的流程图
	 *  3.3 for语句用于实现固定次数循环
	 *  3.4 for语句三个表达式特殊用法
	 *  3.5  在循环中使用break语句
	 *  3.6  在循环中使用continue语句
	 */
	@Test
	public void test3() {
		/*
		 * for语句是循环中最最常用的一种方式。for循环用于将某个语句或语句块重复执行预定次数的情形。语法如下：
		 * for ( 表达式1；表达式2；表达式3  )  {
		 * 		语句块（循环体）
		 * }
		 * 可以看出，for循环的三个表达式之间通过分号；进行分隔，其执行逻辑如下所示：
		 *
		 * 1.计算表达式1的值，通常为循环变量赋初值；
		 * 2.计算表达式2（表达式2为逻辑表达式）的值，即判断循环条件是否为真，若值为真则执行循环体一次(语句块)，
		 * 	否则跳出循环；
		 * 3.执行循环体；
		 * 4.计算表达式3的值，此处通常写更新循环变量的赋值表达式；
		 * 5.计算表达式2的值，若值为true则执行循环体，否则跳出循环；
		 * 6.如此循环往复，直到表达式2的值为false。
		 *
		 */
		for(int i=0;i<10;i++){
			System.out.print(i+" ");
		}

		System.out.println();

		for(int num=1;num<=9;num++){
			System.out.print(num+"*9="+num*9+"\t");
		}
		System.out.println();

		for(int count=0;count<10;count++){
			System.out.print("行动是成功的阶梯"+"\t");
		}
		System.out.println();

		//累加: 1+2+3+4+...+99+100=?
		int sum = 0; //和
		for(int num=1;num<=100;num++){
			sum += num;
		}
		System.out.println("1到100的和："+sum);

		//想求10的阶乘，即1*2*3*…*10
		sum = 1; //和
		for(int num=1;num<=10;num++){
			sum *=num;
		}
		System.out.println("10的阶乘："+sum);

		/*
		 * for语句三个表达式特殊用法
		 *
		 */
		sum = 0;
		int num=1;
		for(;num<=100;num++){
			sum = sum+num;
		}
		System.out.println("sum="+sum);

		sum = 0;
		for(num=1;num<=100;){
			sum = sum+num;
			num++;
		}
		System.out.println("sum="+sum);

		/*
		 * or语句中的三个表达式中表达式1和表达式3可以使用逗号表达式，逗号表达式就是通过”，”运算符隔开的多个表达式
		 * 组成的表达式，从左向右进行计算。
		 */
		for (int i=1,j=6;i<=6;i+=2,j-=2){
			System.out.println("i,j=" + i + "," + j);
		}
		//初始设置i为1，j为6，判断i是否小于等于6，为真执行循环体，而后执行i+=2，j-=2，即：i增2，j减2。
		//再判断i是否小于等于6，为真继续执行循环体，以此类推，直到条件为false。

		/*
		 * break在for循环中的用法
		 */
		sum = 0;
		for(num=1;num<=100;num++){
			if(num%10==3){ //个位为3时，退出循环
				break;
			}
			sum = sum+num;
		}
		System.out.println("sum="+sum);

		/*
		 * continue在for循环的用法
		 */
		sum = 0;
		for(num=1;num<=100;num++){
			if(num%10==3){ //跳过个位为3的
				continue;
			}
			sum = sum+num;
		}
		System.out.println("sum="+sum);

		sum = 0;
		for(num=1;num<=100;num++){
			if(num%10!=3){ //各位不为3时则累加
				sum = sum+num;
			}
		}
		System.out.println("sum="+sum);

/*		for(;;){ //没有条件的循环就是死循环
			System.out.println("我要学习....");
		}
*/

	}

	/**
	 * 猜数字小游戏
	 */
	@Test
	public void test4() {
		Scanner scanner=new Scanner(System.in);
		int number=(int)(Math.random()*1000+1);//1到1000之内的随机数
		System.out.println(number);//作弊
/*
		System.out.println("猜吧！");
		int guess=scanner.nextInt();
		while (number!=guess) {
			if(guess==0){
				break;
			}
			if(number<guess){
				System.out.println("猜大了！");
			}else{
				System.out.println("猜小了！");
			}
			System.out.println("猜吧！");
			guess=scanner.nextInt();
		}
		if(number==guess){
			System.out.println("恭喜你，猜对了！");
		}else {
			System.out.println("欢迎下次再来！");
		}
*/
		/*
		 * 改造成 do-while循环结构
		 */
		int guess;
		do{ //300,200,250
			System.out.println("猜吧!");
			guess = scanner.nextInt(); //1,3
			if(guess==0){
				break;
			}
			if(guess>number){
				System.out.println("太大了");
			}else if(guess<number){//注意与while的区别
				System.out.println("太小了");
			}
		}while(guess!=number); //2
		if(guess==number){
			System.out.println("恭喜你，猜对了!");
		}else{
			System.out.println("欢迎下次再来!");
		}
	}

	/**
	 * 随机加法运算器
	 * 其规则为：程序依次出 10 道加法题目，由用户输入题目的答案。用户每答完一道题， 由系统提示结果：答错了或者答对了。
	 * 10 道题目答完之后，系统计算得分并输出。如果用户希望提前结束，则可以输入－1提前退出。
	 */
	@Test
	public void test5() {
		Scanner scanner=new Scanner(System.in);
		int score=0;//总分
		for(int i=1;i<=10;i++){//循环10次
			int a=(int)(Math.random()*100);//加数a
			int b=(int)(Math.random()*100);//加数b
			int result=a+b;//保存结果
			System.out.println("("+i+")"+a+"+"+b+"=?"); //1.出题
			int answer=scanner.nextInt();//2.答题
			if(answer==-1){//输入-1时结束
				System.out.println("欢迎再次再来！");
				break;
			}
			if(answer==result){//3.判题
				score+=10;//答对一题加10分
			}else{
				System.out.println("很遗憾，算错了！");
			}
		}
		System.out.println("总得分是："+score);
	}

	/**
	 * 二、循环问题
	 * 1.三种循环结构的更佳适用情况:
	 *  1)while:"当..."循环
	 *  2)do...while:"直到..."循环
	 *  	第1要素与第3要素相同时首选
	 *  3)for:固定次数循环，应用率最高
	 *
	 *  选择循环结构的规则:
	 *  1.先看是否是固定次数循环?
	 *   1)固定次数时，选择for循环
	 *   2)不固定次数时，再看要素1与要素3是否相同?
	 *    2.1)相同时，首选do...while
	 *    2.1)不同时，首选while
	 *
	 * 2.嵌套循环:
	 *   1)循环中套循环，一般多行多列时使用，外层控制行，内层控制列
	 *   2)执行规则:外层循环走一次，内层循环走所有次
	 *   3)建议:层数越少越好，能用一层就不用两层，能用两层就不用三层
	 *       若需要必须通过三层以上才能实现，说明设计有问题
	 *   4)break只能跳出一层循环
	 */
	/*
	 * 九九乘法表
	 */
	@Test
	public void test6() {
		/*
		 * 原始写法
		 */
		int num=1;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=2;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=3;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=4;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=5;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=6;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=7;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=8;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		num=9;
		for(int i=1;i<=num;i++){
			System.out.print(i+"*"+num+"="+i*num+"\t");
		}
		System.out.println();
		System.out.println("-----------------分割线------------------");

		/*
		 * 嵌套循环 对于多行多列的嵌套循环问题，一般外层循环控制行，内层循环控制列
		 *
		 * 嵌套循环:
		 *  1)循环中套循环，一般多行多列时使用，外层控制行，内层控制列
		 *  2)执行规则:外层循环走一次，内层循环走所有次
		 *  3)建议:层数越少越好，能用一层就不用两层，能用两层就不用三层
		 *  	若需要必须通过三层以上才能实现，说明设计有问题
		 *  4)break只能跳出一层循环  跳出多层循环------这个需求几乎没有
		 *
		 * "\t"转义字符:制表符 Tab键，在Java中一个制表符固定占8个字节：例如：1*9=9	空3个加上原本5个 8个
		 */
		for(int i=1;i<=9;i++){//控制行
			for(int j=1;j<=i;j++){//控制列
				System.out.print(j+"*"+i+"="+i*j+"\t");
			}
			System.out.println();//换行
		}
	}

}
