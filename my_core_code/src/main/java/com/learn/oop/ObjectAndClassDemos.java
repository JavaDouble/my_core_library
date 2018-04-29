package com.learn.oop;

import org.junit.Test;


//对象和类的演示
/**
 * 1 对象和类(上)
 * 	1.1 面向对象程序设计
 * 	 1.1.1  面向过程的结构化程序设计
 *   1.1.2 什么是抽象数据类型
 *   1.1.3 什么是类
 *  1.2. 定义一个类
 *   1.2.1. 定义类的成员变量
 *   1.2.2. 定义类的方法
 *  1.3. 创建并使用对象
 *   1.3.1. 使用new关键字创建对象
 *   1.3.2. 引用类型变量
 *   1.3.3. 访问对象的成员变量、调用方法
 *   1.3.4. 引用类型变量的赋值
 *   1.3.5. null和NullPointerException
 *
 * 2 对象和类(下)
 *  2.1 方法的重载
 *	 2.1.1 方法的签名
 *	 2.1.2 方法重载及其意义
 *	 2.1.3 编译时根据签名绑定调用方法
 *  2.2 构造方法
 *   2.2.1 构造方法语法结构
 *   2.2.2 通过构造方法初始化成员变量
 *   2.2.3 this关键字的使用
 *   2.2.4 默认的构造方法
 *   2.2.5  构造方法的重载
 *
 * @author Double
 *
 */
/*public class ObjectAndClassDemos {

	*//**
	 * 1.1.1 面向过程的结构化程序设计
	 * 缺陷：	1.缺乏对数据的封装	2.数据和方法(操作数据)的分离
	 *//*
	*//** 打印员工信息的方法   *//*
	public static void printEmpInfo(String name,int age,char gender,double salary) {
		System.out.println("-----------------------");
		System.out.println("姓名"+name);
		System.out.println("姓名"+age);
		System.out.println("姓名"+gender);
		System.out.println("姓名"+salary);
	}
	*//** 打印雇员信息    *//*
	@Test
	public void test1() {
		//雇员1
		String emp1Name="黄河大虾";
		int emp1Age=25;
	     char emp1Gender = '男';
	     double emp1Salary = 8000.00;
	     //打印员工信息
	     printEmpInfo(emp1Name, emp1Age, emp1Gender, emp1Salary);
	     //修改员工工资（增长20%）并打印
	     emp1Salary *= 120.0 / 100.0;
	     printEmpInfo(emp1Name, emp1Age,emp1Gender, emp1Salary);
	}

	*//**
	 * 1.1.2 什么是抽象数据类型
	 * 面向对象的第一步就是抽象数据类型，所谓抽象数据类型可以理解为：将不同类型的数据的集合组成个整体用来描述一种新的事物。
	 * 1.2 类和对象：
	 *   1)现实世界是由很多对象组成的（万事万物皆对象）		基于对象抽出了类
	 *   2)对象:真实存在的单个个体						类:类型/类别，代表一类个体
	 *   3)类中可以包含:
	 *     3.1)所有对象所共有的属性/特征--------成员变量
	 *     3.2)所有对象所共有的行为-------------方法
	 *   4)一个类可以创建多个对象					            同一类型的多个对象，结构相同，数据不同
	 *   5)类是对象的模板，对象是类的具体的实例
	 *
	 * 2.如何创建类？如何创建对象？如何访问成员？
	 *//*
	//格子类打墙+打格方法
	public static void printWall(Cell cc) {
		for(int i=0;i<=20;i++){//行
			for(int j=0;j<=10;j++){//列
				if(i==cc.row && j==cc.col){//行列匹配
					System.out.print("* ");
				}else {
					System.out.print("- ");
				}
			}
			System.out.println();//换行
		}
	}
	@Test
	public void test2() {
		员工类的访问及调用
		//类定义完成后，可以使用new关键字来创建对象。new运算的语法为： new 类名（）；
		//此创建对象的过程也通常称为实例化。
		Emp emp =new Emp();
		emp.name="白发魔女";
		emp.age=24;
		emp.gender='女';
		emp.salary=6000.0;

		emp.printEmpInfo();
		//emp.salary =emp.salary+emp.salary*0.25;//7500.0
		emp.salary *=125.0/100;//7500.0
		//emp.salary *=125/100;//6000.0
		//emp.salary =emp.salary*125/100;//7500.0
		emp.printEmpInfo();


		//学生类的访问及调用
		Student stu =new Student();
		//给成员变量赋初值
		stu.name="张三";
		stu.gender='男';
		stu.age=25;
		stu.address="黑龙江佳木斯";

		//调用方法
		stu.study();
		stu.sayHi();

		//Student stu1=new Student();
		stu.name="WKJ";
		stu.gender='女';
		stu.age=26;
		stu.address="河北廊坊";

		//调用方法
		stu.study();
		stu.sayHi();

		//new对象后，所有成员变量都有默认值
		Student nu= new Student();
		nu.study();
		nu.sayHi();

		//格子类的访问及调用
		Cell c = new Cell();//创建格子对象
		c.row = 2;//访问成员变量并给成员变量赋值
		c.col = 5;
		c.drop();//调用方法
		c.moveLeft(3);//左移3个
		String str = c.getCellInfo();
		System.out.println(str); //3,2


		Cell 	cc 					= 		new Cell();
		//类型	引用（引用类型变量）		指向		对象
		cc.row = 2;
		cc.col = 5;
		System.out.println("原始位置坐标："+cc.getCellInfo());
		printWall(cc); //Cell cc=c;

		cc.drop();
		System.out.println("下落后坐标："+cc.getCellInfo());
		printWall(cc);

		cc.moveLeft(3);
		System.out.println("左移3格后坐标："+cc.getCellInfo());
		printWall(cc);

		//A类的访问及调用
		A aaa=new A();
		aaa.show();
	}

	*//**
	 * 3.引用类型之间画等号:  1)指向同一个对象	  2)对其中一个引用的修改影响另外一个    		eg:房子钥匙
	 *   基本类型之间画等号:  1)赋值		  2)对其中一个变量的修改不会影响另外一个    	eg:身份证复印件
	 * 4.null:空，没有指向任何对象
	 *  若引用的值为null，则该引用不能再进行任何操作了
	 *  若操作则NullPointerException空指针异常
	 *  引用类型与null的演示
	 *//*
	@Test
	public void test3() {
		//引用类型变量，简称引用  引用类型变量存储的是对象的地址信息，在JVM的栈中，指向堆中的对象
		//对引用类型变量的赋值， 除了使用上面的new关键字以外，还可以有另外一种赋值方式，即：相同类型的引用类型变量
		//之间相互赋值。需要注意的是：引用类型变量之间的赋值不会创建新的对象，但有可能会使两个以上的引用指向同一个对象。
		Cell c = new Cell();
		Cell cc = c;//指向同一个对象
		c.row=2;
		cc.row=5;
		System.out.println(cc.row);//5，因为对象只有一个，内存中的row也只有一个

		//基本类型变量，简称变量 存储的是数值 在JVM内存中的栈中
		int a=5;
		int b=a;//赋值
		b=8;
		System.out.println(a); //5，因为a和b是完全不同的两个变量，只是初始值是一样的
		System.out.println(b);

		Cell ccc=c;
		ccc.row=6;
		ccc=null;
		ccc.row=8;//空，没有指向任何对象
		System.out.println(ccc.row);//空指针异常 nullPointerException
		//需要注意：当一个引用的值为null的时候，如果通过引用访问对象成员变量或者调用方法是不合逻辑的
		//（因其没有指向某对象，自然不会有属性和方法）。此时，会产生NullPointerException（空指针异常）。
	}

	*//**
	 * 方法的重载演示
	 *  补充: 1.一个文件中可以包含多个类	 2.public修饰的类只能有一个  3.public修饰的类必须与文件名相同
	 *//*
	@Test
	public void test4() {
		Cashier cashier=new Cashier();
		cashier.pay(8888.88);
		cashier.pay1("12345678","666666");
		cashier.pay1("Java", 8888.88);

		Aoo o = new Aoo();
		o.say(); //调1
		o.say("zhangsan"); //调2
		o.say(25); //调3
		o.say("zhangsan", 25); //调4
		o.say(25, "zhangsan"); //调5
	}

	*//**
	 * 构造方法的演示
	 *//*
	@Test
	public void test5() {
		Cell c1 = new Cell();    //0,0
		//干了2件事 1.创建格子对象	2.调用了Cell类的无参构造方法
		Cell c2 = new Cell(3);   //3,3
		Cell c3 = new Cell(2,5); //2,5

		//创建对象时，构造方法写在new关键字之后，可以理解为：“new”创建了对象，而构造方法对该对象进行了初始化。
		Cell cell=new Cell(15,6);
		System.out.println("原始坐标："+cell.getCellInfo());
		cell.drop();
		cell.drop(2);
		cell.moveLeft();
		cell.moveLeft(2);
		cell.moveRight();
		cell.moveRight(2);
		System.out.println("移动后坐标："+cell.getCellInfo());
	}
}


*//**
 * 1.2. 定义一个类
 *  1.2.1. 定义类的成员变量
 * 抽象成员工类
 * @author Double
 * 类定义了一种抽象数据类型，而类不但定义了抽象数据类型的组成（成员变量），同时还定义了对该类型可以实施的操作（方法）。
 * 实现对数据的封装，并且实现数据与方法的统一。这种方式即为面向对象方式，即:以对象为中心来构建软件系统。
 *//*
class Emp{
	*//**
	 * 类是一种引用数据类型。类为对象的模板，简单的说就是分类。
	 * 类的定义包括“成员变量”的定义和“方法”的定义，其中“成员变量”用于描述一类对象共同的数据结构。
	 *
	 * 成员变量有默认值：对象创建之后，其成员变量可以按照默认的方式初始化
	 * 数值类型（byte,short,int,long,float,double） 默认值为0；
	 * boolean型 默认值为false char型默认值是/u000
	 * 引用类型默认值为null
	 *
	 * 类中除了定义成员变量，还可以定义方法，用于描述对象的形为，封装对象的功能。
	 *//*
	String name;
	int age;
	char gender;
	double salary;

	void printEmpInfo(){
		System.out.println("-----------------------");
		System.out.println("姓名"+name);
		System.out.println("姓名"+age);
		System.out.println("姓名"+gender);
		System.out.println("姓名"+salary);
	}
}
*//**
 * 学生类
 * @author Double
 *
 *//*
class Student{
	//成员变量
	String name;
	char gender;
	int age;
	String address;
	//方法
	public void study(){
		System.out.println(name+"在学习...");
	}
	void sayHi(){
		System.out.println("大家好，我叫"+name+" 性别："+gender+" 今年"+age
				+"岁，家住"+address);
	}

}

*//**
 * 格子类
 *  2.2 构造方法
 * 3.构造方法:构造函数、构造器、构建器
  	1)常常用于给成员变量赋初值
  	2)与类同名，没有返回值类型
  	3)在创建(new)对象时被自动调用
  	4)若自己不写构造，则编译器默认提供一个无参空构造
    	若自己写了构造，则不再默认提供
  	5)构造方法可以重载
   4.this:指代当前对象，哪个对象调指的就是哪个对象	this只能用在方法中，
	   方法中访问成员变量之前默认有个this.
  	this的用法:
    1)this.成员变量名--------访问成员变量 常用
	2)this.方法名()----------调用方法 不常用，几乎不用
	3)this()-----------------调用构造方法 常用

 *   2.2.1 构造方法语法结构
 *   	构造方法是在类中定义的方法， 但不同于其他的方法，构造方法的定义有如下两点规则：
 *   	1)构造方法的名称必须与类名相同。 2)构造方法没有返回值，但也不能写void。
 *   2.2.2 通过构造方法初始化成员变量
 *   	Java语言中的构造方法常常用于实现对对象成员变量的初始化
 *   2.2.3 this关键字的使用
 *   	this关键字用在方法体中，用于指代调用该方法的当前对象，
 *   	简单的说：哪个对象调用方法，this指的就是哪个对象。
 *   	严格来讲，在方法中需要通过this关键字指明当前对象。
 *
 *   2.2.4 默认的构造方法
 *   	1)JAVA语法规定，任何一个类都必须含有构造方法;
 *   	2)假如源程序中没有定义，则编译器在编译时将为其添加一个无参的空构造方法（此方法称之为“默认的构造方法”）。
 *   	3)当类定义了构造方法后，Java编译器将不再添加默认的构造方法
 *   2.2.5  构造方法的重载
 *   	1)为了使用的方便，可以对一个类定义多个构造方法，这些构造方法都有相同的名称（类名），只是方法的参数不同，
 *   		称之为构造方法的重载。
 *		2)在创建对象时，Java编译器会根据不同的参数调用来不同构造方法。
 *
 * @author Double
 *
 *//*
class Cell{
	int row;//行号 成员变量 实参的一种 存储在堆中 随类存在释放
	int col;//列号 成员变量

	Cell(){
		row=0;
		col=0;
	}

	Cell(int n){
		row=n;
		col=n;
	}

	Cell(int row1,int col1){
		row=row1;
		col=col1;
	}

	Cell(){
		this(0);
		//this(0,0);
	}

	Cell(int n){
		this(n,n);
	}

	Cell(int row ,int col){//局部变量 形参 存储在栈中 方法调用时创建，结束时释放
		this.row=row;
		this.col=col;
	}

	void drop() {//下落一格
		row++;//行号增1
	}

	void drop(int n){//下落n格
		row+=n;
	}

	void moveLeft(){//左移一格
		col--;
	}

	void moveLeft(int n) {//左移n格
		col-=n;//列号减n
	}

	void moveRight(){//右移一格
		col++;
	}

	void moveRight(int n){//右移n格
		col +=n;
	}
	String getCellInfo(){//获取行号和列号
		return row+","+col;//返回行列号
	}
}

*//**
 * 类A 测试成员变量的默认值
 * @author Double
 *
 *//*
class A{
	char a;
	int b;
	String c;

	public void show(){
		System.out.println((a+"").trim()+"||"+b+"||"+c);
		System.out.println(a+""+"||"+b+"||"+c);
		System.out.println("good");
	}
}

*//**
 * 2 对象和类(下)
 *  2.1 方法的重载
 *
 *  1.方法的签名:方法名+参数列表
	2.方法的重载(Overload):
  	1)发生在一个类中，方法名称相同，参数列表不同
  	2)编译器在编译时会根据方法的签名自动绑定调用不同的方法

 *	 2.1.1 方法的签名
 * 		1)方法的签名包含如下两个方面：方法名和参数列表。
 * 		2)Java语法规定，一个类中不可以有两个方法签名完全相同的方法，
 * 				即：一个类中不可以有两个方法的方法名和参数列表都完全相同，
 * 		3)如果一个类的两个方法只是方法名相同而参数列表不同，是可以的。参数列表不同是指参数的类型不同
 *	 2.1.2 方法重载及其意义
 *		在Java语言中，允许多个方法的名称相同，但参数列表不同，即签名不同，此种方式称为方法的重载(overload)。
 *		方法重载的意义在于：代码的可用性高，便于服用，维护，调用时简单方便
 *	 2.1.3 编译时根据签名绑定调用方法
 *		当调用重载的方法时，编译器会在编译时根据签名的不同来绑定调用不同的方法，可以把重载的方法看成是完全不同的方法，
 *		只不过，恰好方法名称相同而已。
 *		调用方法:（只管调用即可，由编译器来根据签名绑定不同的方法）
 * @author Double
 *
 *//*
class Cashier{
	public boolean pay(double money) {
		return false;
	}
	public boolean pay(double money) {//编译错误 签名相同，这在java语法中是不允许出现的。
		return false;
	}
	public boolean pay(int money) {//参数类型不同，即参数列表不同
		return false;
	}

	//----------A方式
	void payByCash(double money) {  }
	void payByCard(String cardId,String cardPwd) {  }
	void payByCheck(String compayName,double money) {  }
	//-----------B方式
	void pay1(double money) {  }
	void pay1(String cardId,String cardPwd) {  }
	void pay1(String compayName,double money) {  }
}

class Aoo{
	void say(){}
	void say(String name){}
	void say(int age){}
	void say(String name,int age){}
	void say(int age,String name){}

	//int say(){ return 1;} //编译错误，重载与返回值类型无关
	//void say(String address){} //编译错误，重载与参数名称无关
}*/