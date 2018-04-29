package com.learn.fundamental;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

//运算符和表达式的演示
/**
 * 运算符和表达式
 *
 * 运算：算术运算，关系运算，逻辑运算，赋值运算，字符连接运算，条件(三目)运算,位移运算(了解)
 *
 * 1.算术运算：运算符加(+),减(-),乘(*),除(/),取模(%),自增(++),自减(--)
 * 取模运算（%）意为取余数，只能适用于整数及char类型。
 * Java的自增运算符（++）和自减运算符（--）继承自C++，
 * 可以使变量的值加1或减1，但其写在变量前和变量后有不同的效果：
 * 如果写在变量前，表示在使用这个变量之前加1或减1
 * 如果写在变量后，表示这个变量使用完之后再加1或减1
 *
 * 2.关系运算：
 * 使用关系运算符
 * Java中的关系运算符用于判断数据之间的大小关系，包括大于（>）、小于（<）、大于等于（>=）
 * 、小于等于（<=）、等于（==）、不等于（!=） 六个运算符。
 *
 * 3.逻辑运算：
 * 前面的关系运算符是用于比较两个数值之间的大小关系的，而逻辑运算符是用来进行逻辑运算的，
 * 它是建立在关系运算的基础之上的。当两个关系运算需要兼顾考虑时，可以使用逻辑运算符。
 * 逻辑运算符包括：与（&&）、或（||）和非（!）。
 * 参与逻辑运算的变量或表达式都是boolean类型，运算结果也为boolean类型。
 * 通过分析可以看出：
 * 当变量b1和变量b2同时为true时，&&表达式为true。
 * 当变量b1和变量b2有一个为true时，||表达式为true。
 * ！只有一个表达式，当b1为true时，!b1为false，b1为false时，!b1为true。
 * 两个boolean变量参与“&&”运算时，只有当两个变量均为true的时，运算结果才为true，
 * 否则结果为false。
 * 两个boolean变量参与“||”运算时，当两个变量有一个为true时，结果即为true，
 * 只有当两个变量均为false时结果为false。
 * “!”运算相对简单，只有一个boolean变量参与运算，运算的值与该变量相反，
 * 变量为true时结果为false，变量为false是结果为true。
 *
 * 关于“短路逻辑”的问题
 * Java逻辑运算中的&&和||有短路的特性，当第一个关系表达式就可以判断出整个表达式的结果时，
 * 就不会再去判断后面的第二个表达式。
 * 对于“&&”，当第一个操作数为false时，将不会判断第二个操作数，
 * 因为此时无论第二个操作数是什么最后的结果一定是false；
 * 对于“||”，当第一个操作数为true时，将不会判断第二个操作数，
 * 因为此时无论第二个操作数为何，最后的运算结果一定是true。
 *
 * 4.使用“=”进行赋值运算
 * “=”称为赋值运算符，用于对变量赋值。关于赋值运算符，除了将右边的表达式计算
 * 出来赋给左边以外还具备如下特点：赋值表达式本身也有值，其本身之值即为所赋之值。
 *
 * 5.字符连接运算
 * 使用“+”进行字符串连接
 * “+”除了可以进行算术运算以外，还可以实现字符串的连接，同时可以实现字符串与其他数据类型的“相连”。
 * 当 + 号作用于两个数字类型变量时，是在进行算术运算。
 * 当 + 号两边的变量有一个是字符串类型，即””括起来的时候，则其进行的是字符串的连接，连接后的结果为字符串类型。
 *
 * 6.条件（三目）运算
 * 使用条件（三目）运算符
 * 条件运算符又称“三目”运算符，其结构为：boolean表达式 ? 表达式1：表达式2。
 * 条件运算符的规则如下：
 * 先计算boolean表达式；
 * 如果boolean表达式的值为true，整个表达式的值为表达式1的值；
 * 如果boolean表达式的值为false，整个表达式的值为表达式2的值。
 *
 * @author Double
 */
public class OperationDemos {

	/**
	 * 1.算术运算符：运算符加(+),减(-),乘(*),除(/),取模(%),自增(++),自减(--) 取反(~)(属于位移运算)
	 */
	@Test
	public void test1() {
		/*
		 * 1.算术运算符:+,-,*,/,%,++,--
		 *   1)%:取模/取余，余数为0即为整除
		 *   2)++/--:自增1/自减1，可放在变量前也可在变量后
		 *     2.1)单独使用时，在前在后无差别
		 *     2.2)被使用时，在前在后有差别
		 *           a++的值为a
		 *           ++a的值为a+1
		 */
		int a=5;
		int b=2;
		System.out.println(a/b);//2 int类型参与运算，小数无条件舍弃
		System.out.println(a%b);//1
		System.out.println(~a+1);//二进制 取反运算 -5
		System.out.println(-a);//-5
		System.out.println(~a);//二进制 取反运算 -6

		System.out.println(++a);//6
		System.out.println(a++);//6
		System.out.println(a);//7
		System.out.println(--a);//6
		System.out.println(a--);//6
		System.out.println(a);//5
		int c1=a++;// 先将a的值赋给c1，然后a再自加
		int c2=++b;// 先将b的值自加，然后再赋给c2
		System.out.println("a="+a+",b="+b+",c1="+c1+",c2="+c2);
		// 输出的结果为： a=6, b=3, c1=5, c2=3

		a=5;b=5;
		//1)输出a++的值5 2)a自增1变为6
		System.out.println(a++); //5 被使用
		System.out.println(a);   //6
		//1)输出++b的值6 2)b自增1变为6
		System.out.println(++b); //6
		System.out.println(b);   //6

		a=5;b=5;
		int c=a++; //1)将a++的值5赋值给c 2)a自增1变为6
		int d=++b; //1)将++b的值6赋值给d 2)b自增1变为6
		System.out.println(a); //6
		System.out.println(b); //6
		System.out.println(c); //5 被使用
		System.out.println(d); //6

		/*
		 * 只有这样才是单独使用
		 */
		a=5;b=5;
		a++; //相当于a=a+1
		++b; //相当于b=b+1
		System.out.println(a); //6
		System.out.println(b); //6
	}

	/**
	 * 2.关系运算符
	 * Java中的关系运算符用于判断数据之间的大小关系，包括大于（>）、小于（<）、
	 * 大于等于（>=）、小于等于（<=）、等于（==）、不等于（!=） 六个运算符。
	 *
	 * 注:算术运算的优先级高于关系运算，先算算术再算关系
	 */
	@Test
	public void test2() {
		/*
		 * 2.关系运算符:
		 *   1)>(大于),<(小于)
		 *     >=(大于或等于),<=(小于或等于)
		 *     ==(等于),!=(不等于)
		 *   2)关系运算的结果是boolean型，
		 *     关系成立则为true，关系不成立则为false
		 */
		int a = 5;
		int b = 6;
		System.out.println(a > b);
		System.out.println(a < b);
		System.out.println(a >= b);
		System.out.println(a <= b);
		System.out.println(a == b);
		System.out.println(a != b);
	}
	/**
	 * 3.逻辑运算
	 * 逻辑运算符是用来进行逻辑运算的，它是建立在关系运算的基础之上的。当两个关系运算需要兼顾考虑时，可以使用逻辑运算符。
	 * 逻辑运算符:&&(逻辑与 并且),||(逻辑或 或),!(逻辑非)
	 *
	 * 变量a		变量b 	a&&b 	a||b 	!a
	 * false	false	false	flase	true
	 * false	true	false	true	true
	 * true		false	false	true	false
	 * true		true	true	true	false
	 *
	 * 1.当变量a和变量b同时为true时，&&表达式为true。
	 * 当变量a和变量b有一个为true时，||表达式为true。
	 * ！只有一个表达式，当a为true时，!a为false，a为false时，!b1为true。
	 *
	 * 注：关系运算的优先级高于逻辑运算的优先级 先算关系再算逻辑
	 */
	@Test
	public void test3() {
		/*
		 *
		 * 3.逻辑运算符:
		 *   1)&&:短路与(并且)，两边都为真则为真，见false则false
		 *        若第1个数为false时发生短路(后面的不执行了)
		 *     ||:短路或(或者)，有一边为真则为真，见true则true
		 *        若第1个数为true时发生短路(后面的不执行了)
		 *     ! :逻辑非(取反)，非真则假，非假则真
		 *   2)逻辑运算是建立在关系运算的基础之上的
		 *     逻辑运算的结果也是boolean型
		 *
		 */
		/*
		 * 1.两个boolean变量参与“&&”运算时，只有当两个变量均为true的时，运算结果才为true，否则结果为false。
		 * 2.两个boolean变量参与“||”运算时，当两个变量有一个为true时，结果即为true。
		 * 只有当两个变量均为false时结果为false。
		 * 3.!”运算相对简单，只有一个boolean变量参与运算，运算的值与该变量相反，变量为true时结果为false，
		 * 变量为false是结果为true。
		 */
		boolean b1 = false;
		boolean b2 = true;
		System.out.println(b1 && b2);
		System.out.println(b1 || b2);
		System.out.println(!b1);

		int a=5;
		int b=10;
		int c=5;

		b1 = a>=b && c<b;
		System.out.println(b1);          //false&&true=false
		System.out.println(a<=c && a>b); //true&&false=false
		System.out.println(a==b && a>c); //false&&false=false
		System.out.println(a!=b && b>c); //true&&true=true

		System.out.println(a>=b || c<b); //false||true=true
		System.out.println(a<=c || a>b); //true||false=true
		System.out.println(a!=b || b>c); //true||true=true
		System.out.println(a==b || a>c); //false||false=false

		b2 = !(a>b);
		System.out.println(b2);     //!false=true
		System.out.println(!(a<b)); //!true=false

		/*
		 *  关于“短路逻辑”的问题
		 *   Java逻辑运算中的&&和||有短路的特性，当第一个关系表达式就可以判断出整个表达式的结果时，
		 *   就不会再去判断后面的第二个表达式。
		 *   1.对于“&&”，当第一个操作数为false时，将不会判断第二个操作数，
		 *   因为此时无论第二个操作数是什么最后的结果一定是false；
		 *   2.对于“||”，当第一个操作数为true时，将不会判断第二个操作数，因为此时无论第二个操作数为何，
		 *   最后的运算结果一定是true。
		 */
		boolean b3 = a>b && c++>2;
		System.out.println(b3); //false
		System.out.println(c);  //5，发生短路了

		boolean b4 = a<b || c++>2;
		System.out.println(b4); //true
		System.out.println(c);  //5,发生短路了

	    a=10;
	    b=10;
	    boolean flag=a++>b--&&b++>a--;
	    System.out.println(flag+",a="+a+",b="+b);//false,11,9
	}

	/**
	 * 4.赋值运算符:
	 *   1)简单赋值运算符:=(使用“=”进行赋值运算)
	 *   2)扩展赋值运算符:+=,-=,*=,/=,%=(使用扩展赋值表达式 +=,-=,*=,/=,%=)
	 *
	 *  注：赋值运算的优先级不仅低于逻辑运算，还低于条件运算
	 */
	@Test
	public void test4() {
		/*
		 * 1.“=”称为赋值运算符，用于对变量赋值。关于赋值运算符，除了将右边的表达式计算出来赋给左边以外
		 * 还具备如下特点：赋值表达式本身也有值，其本身之值即为所赋之值。
		 */
		int num = 18, index;
		System.out.println(index = num % 5);  // 结果为：3，赋值表达式本身也有值
		System.out.println(index);  // 结果为：3
		int a, b, c;
		a = b = c = 100;
		// c=100 整个表达式的值为100， 将其赋值给b，同样b=（c=100）整个表达式的值也为100，
		//然后有将这个值赋给了a，所以a 的值也是100。

		/*
		 *  2.使用扩展赋值表达式
		 *  在赋值运算符”= ”前加上其它运算符，即为扩展赋值运算符，其效率高于赋值运算符，推荐使用
		 *
		 *  扩展运算符
		 *  运算符		表达式		计算			结果(假设x=10)
		 *  +=			x += 5		x=x+5		15
		 *  -=			x -= 5		x=x-5		10
		 *  *=			x *= 5		x=x*5		50
		 *  /=			x /= 5		x=x/5		2
		 *  %=			x %= 5		x=x%5		0
		 */
		System.out.println(a+=b);
		System.out.println(b-=c);
		System.out.println(a/=c);

		a = 5; //将5赋值给整型变量a
		a+=10; //相当于a=a+10
		System.out.println(a); //15
		a*=2; //相当于a=a*2
		System.out.println(a); //30
		a/=5; //相当于a=a/5
		System.out.println(a); //6

	}
	/**
	 * 5.字符连接运算
	 * 使用“+”进行字符串连接
	 * “+”除了可以进行算术运算以外，还可以实现字符串的连接，同时可以实现字符串与其他数据类型的“相连”。
	 * 1.当 + 号作用于两个数字类型变量时，是在进行算术运算。
	 * 2.当 + 号两边的变量有一个是字符串类型，即””括起来的时候，则其进行的是字符串的连接，连接后的结果为字符串类型。
	 */
	@Test
	public void test5() {
		/*
		 * 5.字符串连接运算符:
		 *  1)+:
		 *    1.1)若两边都是数字，则做加法运算
		 *    1.2)若有一边为字符串，则做字符串连接
		 */
		int a = 100;
		String msg = "a=" + a;
		System.out.println(msg);    //a=100，字符串拼接
		msg = "" + 100 + 200;
		System.out.println(msg);   //结果为： 100200，””+100，为字符串100再拼上200为100200
		msg = 100 + 200 + "";
		System.out.println(msg);   //结果为：300，100+200为算术运算结果为300，再加上””为300

		System.out.println(10+20+""); //"30"
		System.out.println(10+""+20); //"1020"
		System.out.println(""+10+20); //"1020"

		int age = 37;
		System.out.println("age="); //age=
		System.out.println(age);    //37
		System.out.println("age="+age); //age=37
		System.out.println("我的年龄是:"+age);
		System.out.println("我今年"+age+"岁了");
	}

	/**
	 * 6.条件(三目)运算
	 * 条件运算符又称“三目”运算符，其结构为：boolean表达式 ? 表达式1：表达式2。
	 * 条件运算符的规则如下：
	 * 1.先计算boolean表达式；
	 * 2.如果boolean表达式的值为true，整个表达式的值为表达式1的值；
	 * 3.如果boolean表达式的值为false，整个表达式的值为表达式2的值。
	 */
	@Test
	public void test6() {
		/*
		 * 6.三目/条件运算符:
		 *  1)语法:
		 *    boolean?数1:数2
		 *  2)执行过程:
		 *    计算boolean的值:
		 *     若为true，则整个表达式的结果为数1
		 *     若为false，则整个表达式的结果为数2
		 */
		int a=55,b=8;
		int max = a>b?a:b;//判断连个数的最大值
		System.out.println("max="+max);
		int min = a<b?a:b;//判断连个数的最小值
		System.out.println("min="+min);

		a = -3;
		String r = a > 0 ? "正数" : (a == 0 ? "0":"负数");//判断一个数是整数还是负数
		System.out.println(r);
		//结果为负数，因为 a 的值小于 0，即boolean 表达式的值为 false ，
		//则取问号后第二个表达式的值作为表达式的结果。而问号后的第二个表达式也是一个三目运算符所构成的表达式。
		//因为a==0表达式的值为false，则取"负数"为表达式的结果
	}

	/**
	 * 补充知识点：二进制运算
	 *
	 * 位运算(二进制运算):位运算符 ~,&，|,^
	 *
	 *  ~:取反运算符:		如果位为0，结果是1，如果位为1，结果是0
	 *  &:与运算符：逻辑乘法 	位都为1，结果才为1，否则结果为0
	 *  |:或运算符:			位只要有一个为1，那么结果就是1，否则就为0
	 *  ^:				两个操作数的位中，相同则结果为0，不同则结果为1
	 *
	 */
	@Test
	public void test7() {
		/*
		 * ~ 取反运算
		 */
		int n=4;
		System.out.println("数字4的32位二进制：  "+StringUtils.leftPad(
												Integer.toBinaryString(n), 32, "0"));
		//4的32位二进制表示方式
		n=~n+1;//4的取反运算+1
		System.out.println("数字4的取反运算+1："+n);
		n=~n;
		System.out.println("数字-4的32位二进制："+StringUtils.leftPad(
												Integer.toBinaryString(n), 32, "0"));
		System.out.println("数字-4的取反操作："+n);

		/*
		 * & 与运算:逻辑乘法
		 */
		int i=0x9274b9;//00000000100100100111010010111001
		int m=0xff;//00000000000000000000000011111111//Mask 掩码8
		int k=i&m;//掩码运算 截取i的低8位数据
		int k1=(i>>>8)&m;
		int k2=(i>>>16)&m;
		int k3=(i>>>24)&m;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k1), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k2), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k3), 32, "0"));

		int i1=(k3<<24)+(k2<<16)+(k1<<8)+k;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i1), 32, "0"));
		System.out.println(i1==i);//true

		n=i&1;//截取i的低1位数据
		//00000000000000000000000000000001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));

		/*
		 * | 或运算:有1为1
		 */
		i=0x9274b9;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		n=i|1;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));

		/*
		 * ^
		 */




		i=50;
		System.out.println(i%16);//2
		System.out.println(i&15);//2
		System.out.println((i%16)==(i&15));//true

		//掩码计算案例:截取n的低8位数据
		n=0xafdda;
		m=0xff;
		k=n&m;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		//00000000000010101111110111011010
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k), 32, "0"));
		//00000000000000000000000011011010

	}

	/**
	 * 补充知识点：二进制运算
	 *
	 * 移位运算(二进制运算)
	 *  <<:左移位运算符，将运算符左边的对象向左移动运算符右边指定的位数（在低位补0）
	 *  >>:数学右移位符  "有符号"右移运算符，将运算符左边的对象向右移动运算符右边指定的位数。使用符号扩展机制，
	 *  	也就是说，如果值为正，则在高位补0，如果值为负，则在高位补1.
	 *  >>>:逻辑右移位  "无符号"右移运算符，将运算符左边的对象向右移动运算符右边指定的位数。采用0扩展机制，
	 *  也就是说，无论值的正负，都在高位补0.
	 */
	@Test
	public void test8() {
		/*
		 * <<:左移位运算符
		 * 将运算符左边的对象向左移动运算符右边指定的位数（在低位补0）
		 */
		int a=4;
		System.out.println("数字4的32位二进制：  "+StringUtils.leftPad(
												Integer.toBinaryString(a), 32, "0"));
		a=a<<4;
		System.out.println("数字4左移4位:"+a);
		System.out.println("数字4左移4位后的32位二进制：  "+StringUtils.leftPad(
										Integer.toBinaryString(a), 32, "0"));
		/*
		 * >> 数学右移位运算：2进制数字向右移动，低位溢出舍弃，高位补0
		 * 数字意义：减倍
		 *
		 */
		int i=0x9274b9;
		//00000000100100100111010010111001
		System.out.println("i="+i);
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		int n=i>>1;
		//00000000010010010011101001011100
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		System.out.println("i右移移位后："+n);

		a=4;
		a=a>>1;
		System.out.println("a右移1位："+a);

		/*
		 * >>> 逻辑右移位运算：负数(高位为1的数)高位 补1 正数 补0
		 *
		 */
		i=0x9274b9;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		n=i>>>1;
		//00000000010010010011101001011100
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>1), 32, "0"));

		/*
		 * >>与>>>的区别：
		 * 正数相同，负数不同
		 */
		i=0xfffff3;
		System.out.println("正数比对==================");
		System.out.println(i);
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>>1), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>1), 32, "0"));
		i=~i;

		System.out.println("负数比对==================");
		System.out.println(i);
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>>1), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>1), 32, "0"));
	}

	/**
	 * Java中各种运算符的优先级的测试
	 * 	从上到下，优先级依次递减
	 *	1."."(点)双目(双元), "()"(圆括号), "[]"(方括号) 从左到右
	 *
	 *  2."+"(正号),"-"(负号),"++"(自增) 前缀增，后缀增,"--"(自减) 前缀减，后缀减,
	 *  	"~"(按位非/取补运算),"！"(逻辑非) “!”不可以与“=”联用  	从右到左 	单目(单元)
	 *
	 *  3."*"(乘),"/"(除),"%"(取模，取余) 从左到右 双目(双元)
	 *  4."+"(加),"-"(减) 从左到右 双目(双元)
	 *	5."<<"(左移位运算符),">>"(带符号右移位运算符),>>>"(无符号右移) 从左到右 双目(双元)
	 *
	 *	6."<"(小于),"<="(小于或等于),">"(大于),">="(大于或等于),"instanceof"(确定某对象是否属于指定的类)
	 *		从左到右	双目(双元)
	 *
	 *  7."=="(等于),"!="(不等于)	从左到右	双目(双元)
	 *	8."&"(按位与)		从左到右	双目(双元)
	 *	9."|"(按位或)		从左到右	双目(双元)
	 *
	 *	10."^"(按位异或)		从左到右	双目(双元)
	 *	11."&&"(短路与)		从左到右	双目(双元)
	 *	12."||"(短路或)		从左到右	双目(双元)
	 *	13."? :"(条件运算符)	从右到左	三目(三元)
	 *
	 *	14."="(赋值运算符),"+=","-=","*=","/=","%=",(扩展赋值运算符)
	 *		"&=","|=","^=","<<=",">>=",">>>="(混合赋值运算符 )	从右到左	双目(双元)
	 *
	 *  一共42个运算符，分为14个级别 作为了解知识
	 *  大概这样记忆：
	 *  '(),[]' > '算术运算符' > '移位运算符' > '关系运算符' > '逻辑运算符' > '三目运算符' > '赋值运算符'
	 */
	@Test
	public void test9() {
		int a=5;
		int b=4;
		System.out.println(a>(b+1));//false 说明()>算术运算符>关系运算符

		boolean flag=a-b>0?a<b&&a+1>b:a>b;
		System.out.println(flag);//false
		//证明'算术运算符'>'关系运算符'>'逻辑运算符'>'三目运算符'>'赋值运算符'

		flag=(a-b>0)?((a<b)&&(a+1>b)):(a>b);
		System.out.println(flag);//false
		//证明'()' > '算术运算符' > '关系运算符' > '逻辑运算符' > '三目运算符' > '赋值运算符'
	}

}
