package com.learn.oop;

import org.junit.Test;

//访问控制、static和final的演示
/**
 * 3.package:
  1)作用:避免类名的冲突
  2)包名可以有层次结构
  3)建议:包名所有字母都小写
  4)类的全称: 包名.类名
  import:
  1)同包中的类可以直接访问
    不同包中的类不能直接访问，如下两种方式:
	1.1)先import声明类，再直接访问类-----建议
	1.2)类的全称---------不建议
4.访问控制修饰符
  1)public:公开的，任何类
  2)private:私有的，本类
  3)protected:受保护的，本类、子类、同包类
  4)默认的:什么也不写，本类、同包类
  注意:
    1)类的访问修饰符:public和默认的
	2)类中成员访问修饰符:4个都可以

1.访问控制修饰符:
  1)public:公开的，任何类
  2)private:私有的，本类
  3)protected:受保护的，本类、子类、同包类
  4)默认的:什么也不写，本类、同包类
  说明:
    1)类的访问修饰只能是public和默认的
	2)类中成员访问修饰如上4种都可以
2.static:静态的
  1)静态变量:
    1.1)由static修饰
	1.2)属于类的，存在方法区中，只有一份
	1.3)常常通过类名.来访问
	1.4)何时用:所有对象的数据都一样时使用
  2)静态方法:
    2.1)由static修饰
	2.2)属于类的，存在方法区中，只有一份
	2.3)常常通过类名.来访问
	2.4)静态方法没有隐式this传递，
        所以静态方法中不能直接访问实例成员
	2.5)何时用:方法的操作仅与参数相关而与对象无关
  3)静态块:
    3.1)由static修饰
	3.2)属于类的代码块，类被加载期间自动执行
	    因为类只被加载一次，所以静态块也只执行一次
	3.3)何时用:常常用于加载/初始化静态资源(图片、音频、视频等)
3.final:最终的，单独应用的几率不大
  1)修饰变量:变量不能被改变
  2)修饰方法:方法不能被重写
  3)修饰类  :类不能被继承
4.static final:常量
  1)必须声明的同时初始化
  2)常常通过类名点来访问，不能被改变
  3)建议:常量名所有字母都大写
  4)在编译时被直接替换为具体的值，效率高

 * @author Double
 *
 */

/**
 * 1 访问控制
 * 	1.1 包的概念
 * 	 1.1.1 package语句
 * 		1)定义类时需要指定类的名称，但是如果仅仅将类名作为类的唯一标识，则不可避免的出现命名冲突问题，
 * 			这会给组件复用以及团队间的合作造成很大的麻烦！因为原则上来说，类名是不可以重复的。
 * 		2)在Java语言中，命名冲突问题是用包（package）的概念来解决的，也就是说，在定义一个类时，
 * 			除了定义类的名称一般还要指定一个包的名称，定义包名的语法：package 包名；
 * 		3)包名一般小些	 需要注意的是，在定义包时，package语句必须写在Java源文件的最开始处，即在类定义之前.
 * 		4)一旦使用package指定了包名，则类的全称应该是“包名.类名”,全部限定类名，又称全限类名
 * 		5)命名包名时，包名可以有层次结构，在一个包中可以包含另外一个包，可以按照如下的方法定义package语句:
 * 			package 包名1.包名2…包名n;
 * 		6)在实际应用中，包的命名常常是多层次的。因为如果各个公司或开发组织的程序员都随心所欲的命名包名的话，
 * 			依然不能从根本上解决命名冲突的问题，依然不利于软件的复用。因此，在指定包名时应该按照一定的规范，例如：
 * 			org.apache.commons.lang.StringUtil
 * 			其中，StringUtil是类名，org.apache.commons.lang是多层包名，其含义如下：
 * 			org.apache表示公司或组织的信息（是这个公司或组织域名的反写）；commons表示项目的名称信息；
 * 			lang表示模块的名称信息。
 *
 * 			建议:     域名反写 . 	项目名称 . 		模块名称       . 		类名(具体按照公司规定)
 * 			   		cn.tedu  . manager  .  studentmanager . Aoo
 * 	 1.1.2 import语句
 * 		1)访问一个类时，需要使用该类的全称，但这样的书写方式过于繁琐；
 * 			org.whatisjava.core.Point p = new org.whatisjava.core.Point();
 * 		2)可以通过import语句对类的全称进行声明，import语句的语法如下所示：import 类的全局限定名（即包名+类名）；
 * 			import org.whatisjava.core.Point;
 * 		3)通过import语句声明了类的全称后，该源文件中就可以直接使用类名来访问了.
 * 			有时，在import语句中也可以使用“*”符号,不建议，会造成不必要的程序负担。
 * 			在Eclipse中，可以使用“Ctrl+Shift+O”，自动完成import语句。
 *  1.2 访问控制修饰符
 *   1.2.1 封装的意义
 *    1)对外提供可调用的、稳定的功能;
 *    2)在软件系统中，常常通过将容易变化的、具体的实现细节封装起来，外界不可访问，
 *    而对外提供可调用的、稳定的功能（店员），这样的意义在于：(数据私有化，行为公开化。)
 *     ——降低代码出错的可能性，更便于维护。
 *     ——当内部实现细节改变时，只要保证对外的功能定义不变，其他的模块不需要更改。
 *    在软件系统中，封装常常需要依靠一些访问控制修饰符来实现。
 *
 *   1.2.2 public和private
 *    !)private与public为最最常用的两个访问控制修饰符，其中，private修饰的成员变量和方法仅仅只能在本类中调用，
 *    	而public修饰的成员变量和方法可以在任何地方调用。
 *    2)private修饰的内容是对内实现的封装， 如果“公开”它将会增加维护的成本。
 *    	public修饰的内容是对外提供的可以被调用的功能，需要相对稳定，
 *   1.2.3 protected和默认访问控制
 *    1)protected和默认访问控制也是两种访问修饰。其中，使用protected修饰的成员变量和方法可以被子类及同一个包中的类使用。
 *    2)默认访问控制即不书写任何访问控制符，默认访问控制的成员变量和方法可以被同一个包中的类调用。
 *   1.2.4 访问控制修饰类
 *    1)对于类的修饰可以使用public和默认方式。 其中，public修饰的类可以被任何一个类使用，而默认访问控制的类只可以
 *    		被同一个包中的类使用。
 *    2)protected和private访问修饰符是不可以修饰类的，但其可以修饰内部类（后面课程详细介绍）。
 *   1.2.5 访问控制修饰成员
 *    1)4种访问修饰（public、private、protected、默认），都可以修饰成员
 *    2)其中，public 修饰符，在任何地方都可以访问；protected可以在本类、同一包中的类、子类中访问，
 *    	除此之外的其它类不可以访问；默认方式为可以本类及同一包中的类访问，除此之外其它类不可以访问；
 *    	private只可以在本类中访问，其它任何类都不可以。
 *
 * 2 static和final
 * 	2.1 static关键字
 * 	 2.1.1 static修饰成员变量
 * 	  1)static关键字可以修饰成员变量，它所修饰的成员变量不属于对象的数据结构，而是属于类的变量，
 * 		通常通过类名来引用static成员。
 * 	  2)当创建对象后，成员变量是存储在堆中的，而static成员变量和类的信息一起存储在方法区, 而不是在堆中，
 * 	  3)一个类的static成员变量只有“一份”（存储在方法区），无论该类创建了多少对象。
 * 	 2.1.2 static修饰方法
 *    1)通常的方法都会涉及到对具体对象的操作，这些方法在调用时，需要隐式的传递对象的引用（this）
 *    2)static修饰的方法则不需要针对某些对象进行操作，其运行结果仅仅与输入的参数有关，调用时直接用类名引用.
 *    3)由于static在调用时没有具体的对象，因此在static方法中不能对非static成员(对象成员)进行访问。
 *    	static方法的作用在于提供一些“工具方法”和“工厂方法”（后面课程详细介绍）等。
 * 	 2.1.3 static块
 *	  1)static块为属于类的代码块，在类加载期间执行的代码块，只执行一次，可以用来在软件中加载静态资源（图像、音频等等）
 *  2.2 final关键字
 *   2.2.1 final修饰变量
 *    1)final关键字修饰变量，意为不可改变。
 *    2)final可以修饰成员变量，也可以修饰局部变量，当final修饰成员变量时，可以有两种初始化方式：
 *    	——声明同时初始化
 *    	——构造函数中初始化
 *    3)final关键字修饰局部变量，在使用之前初始化即可。
 *   2.2.2 final修饰方法
 *    1)final关键字修饰的方法不可以被重写。
 *    2)使一个方法不能被重写的意义在于：防止子类在定义新方法时造成的“不经意”重写。
 *   2.2.3 final修饰类
 *    1)final关键字修饰的类不可以被继承。
 *    2)JDK中的一些基础类库被定义为final的，例如：String、Math、Integer、Double 等等。
 *    3)使一个类不能被继承的意义在于：可以保护类不被继承修改，可以控制滥用继承对系统造成的危害。
 *   2.2.4 static final常量
 *	  1)static final 修饰的成员变量称为常量，必须声明同时初始化，并且不可被改变。常量建议所有字母大写。
 *	  2)实际应用中应用率较广，因为static final常量是在编译期被替换的，可以节约不必要的开支
 * @author Double
 *
 */
public class StaticFinalDemos {

	/*
	 * 访问控制的演示
	 */
	@Test
	public void test1() {

	}

	/*
	 * static的演示
	 */
	@Test
	public void test2() {
		Eoooo o1 = new Eoooo();
		o1.show(); //1,1
		Eoooo o2 = new Eoooo();
		o2.show(); //1,2
		Eoooo o3 = new Eoooo();
		o3.show(); //1,3
		System.out.println(Eoooo.b); //建议:类名点来访问
		System.out.println(o1.b);  //不建议

		Foooo.test(); //通过类名点来访问

		Goooo o4 = new Goooo();
		Goooo o5 = new Goooo();
		Goooo o6 = new Goooo();
	}

	/*
	 * final的演示
	 */
	@Test
	public void test3() {

	}

	/*
	 *static final 的演示
	 */
	@Test
	public void test4() {
		System.out.println(Ooo.PI); //常常通过类名点来访问
		//Ooo.PI = 3.1415926; //编译错误，常量不能被改变

		//1.加载Poo.class到方法区中
		//2.将num存储在方法区中
		//3.到方法区中获取num的值并输出
		System.out.println(Poo.num);

		//编译器在编译时将常量直接替换为具体的值---效率高
		//相当于System.out.println(5);
		System.out.println(Poo.COUNT);
	}

}

//访问控制的演示
class Fooo {
	public int a;    //任何类
	protected int b; //本类、子类、同包类
	int c;           //本类、同包类
	private int d;   //本类

	void show(){
		a = 1;
		b = 2;
		c = 3;
		d = 4;
	}
}
class Goo{ //演示private
	void show(){
		Fooo o = new Fooo();
		o.a = 1;
		o.b = 2;
		o.c = 3;
		//o.d = 4;
	}
}



class Goooo{ //演示静态块的
	static{
		System.out.println("静态块");
	}
	Goooo(){
		System.out.println("构造方法");
	}
}


class Foooo{ //演示静态方法
	int a;
	static int b;
	void show(){
		System.out.println(a);
		System.out.println(b);
	}
	static void test(){ //没有隐式的this
		//实例成员a必须通过对象来访问，
		//而静态方法没有this，意味着没有对象，
		//所以静态方法中不能直接访问实例成员
		//System.out.println(a); //编译错误
		System.out.println(b);
	}
}

class Eoooo{ //演示静态变量
	int a; //实例变量
	static int b; //静态变量
	Eoooo(){
		a++;
		b++;
	}
	void show(){
		System.out.println("a="+a);
		System.out.println("b="+b);
	}
}


//final的演示
final class Koo{}
//class Loo extends Koo{} //编译错误，final修饰的类不能被继承

class Moo{}
final class Noo extends Moo{} //正确的

class Ioo{ //演示final修饰方法
	void show(){}
	final void test(){}
}
class Joo extends Ioo{
	void show(){}
	//void test(){} //编译错误，final修饰的方法不能被重写
}

/*
 * final修饰成员变量，只有两种初始化方式:
 *   1)声明的同时初始化
 *   2)在构造方法中初始化
 * final修饰局部变量，只要在用之前初始化即可:
 */
class Hoo{ //演示final修饰变量
	final int a = 5; //1.声明同时初始化
	final int b;
	Hoo(){
		b = 6; //2.构造方法中初始化
	}

	void show(){
		final int c; //用之前初始化即可
		//a = 55; //编译错误，final的变量不能被改变
	}
}

//static final的演示
class Poo{
	public static int num = 5; //静态变量
	public static final int COUNT = 5; //常量
}

class Ooo{
	public static final double PI = 3.14159;
	//public static final int NUM; //编译错误，必须声明的同时初始化

}