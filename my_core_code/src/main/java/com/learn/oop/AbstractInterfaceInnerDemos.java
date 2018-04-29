package com.learn.oop;

import java.util.Scanner;

import org.junit.Test;










//抽象类、接口、内部类的演示
/**
1.抽象方法:
  1)由abstract修饰
  2)只有方法的定义，没有具体的实现(连大括号都没有)
2.抽象类:
  1)由abstract修饰
  2)包含抽象方法的类必须是抽象类
          不包含抽象方法的类也可以声明为抽象类---我乐意 没有意义
  3)抽象类不能被实例化
  4)抽象类是需要被继承的，子类:
    4.1)重写抽象类中的所有抽象方法----建议
	4.2)也声明为抽象类----不建议
  5)抽象类的意义:
    5.1)封装子类所共有的属性和行为，实现代码复用
	5.2)为所有子类提供一种统一的类型----向上造型
	5.3)可以包含抽象方法，为所有子类提供了统一的入口,
	    子类的具体实现不同，但定义是一致的

3.接口:
  1)是一个标准、规范----制定方
          遵守这个标准就能干某件事------API之后
  2)接口也是一种数据类型(引用类型)
  3)由interface定义，只能包含常量和抽象方法
  4)接口不能被实例化
  5)接口是需要被实现/继承的，实现类/子类:
          必须重写接口中的所有抽象方法
  6)一个类可以实现多个接口，用逗号隔开
         若又继承又实现时，应先继承后实现
  7)接口可以继承接口

4.多态:
  1)多态的意义:
    1.1)同一类型的引用，指向不同的对象时，有不同的实现
	    ------行为的多态:cut(),run()...
	1.2)同一个对象，被造型为不同的类型时，有不同的功能
	    ------对象的多态:我、你、水...
  2)向上造型:
    2.1)父类型的引用指向子类的对象
	2.2)能造型成的类型: 父类+所实现的接口
	2.3)能点出来什么，看引用的类型
  3)强制类型转换，成功的条件有两种:
    3.1)引用所指向的对象，就是该类型
	3.2)引用所指向的对象，实现了该接口
  4)不符合那两种条件则转换失败，发生ClassCastException类型转换异常,
	建议在强转之前使用instanceof判断引用指向的对象是否是该类型
5.内部类
 1).成员内部类:单独应用的几率不大
  1.1)类中套类，外面的类称为外部类，里面的类称为内部类
  1.2)内部类通常只服务于外部类，对外不具备可见性
  1.3)内部类对象通常是在外部类中创建的
  1.4)内部类中可以直接访问外部类的成员(包括私有的)
              内部类中有一个隐式的引用指向了创建它的外部类对象
	  外部类名.this.
 2).匿名内部类:
  2.1)想创建一个类(子类)的对象，并且对象只创建一次，
    此时该类不必命名，称之为匿名内部类
  2)匿名内部类中访问外面的变量，该变量必须是final的
 */
/**
 * 抽象类、接口和内部类
 * 1.1 抽象类
 *	1.1.1 抽象方法和抽象类
 *	 1)由abstract修饰的方法为抽象方法，抽象方法即只有方法的定义，没有方法体实现，用一个分号结尾。
 *		即方法五要素中，抽象方法缺少了一个要素（即：方法体）。也可以将抽象方法理解为不完整的方法。
 *	 2)若将抽象方法包含在类中，则该类也应该为抽象的，可以理解为，该类也不完整。抽象类由abstract关键字声明。
 *		一个类中如果包含抽象方法，该类应该用abstract关键字声明为抽象类。
 *	 3)抽象类是不能实例化对象的，而一个类不能实例化是没有意义的，所以，需要定义类来继承抽象类，而如果一个类继承了抽象类，
 *		则其必须重写其抽象方法（变不完整为完整），除非该类也声明为抽象类。
 *		如果一个类继承了抽象类，必须重写其抽象方法(除非该类也声明为抽象类)。
 *	1.1.2 抽象类不可以实例化
 *	 1)抽象类不可以实例化
 *	 2)即使一个类中没有抽象方法，也可以将其定义为抽象类，同样，该类不可以实例化。
 *	 3)abstract和final关键字不可以同时用于修饰一个类，因为final关键字使得类不可继承
 *		，而abstract修饰的类如果不可以继承将没有任何意义。两者放在一起，会起冲突。报编译错误。
 *	1.1.3 继承抽象类
 *	  	一个类继承抽象类后，必须实现其抽象方法，不同的子类可以有不同的实现。
 *	1.1.4 抽象类的意义
 *	 1)为其子类提供一个公共的类型（父类引用指向子类对象）；
 *	 2)封装子类中的重复内容（成员变量和方法）;
 *	 3)定义有抽象方法，子类虽然有不同的实现，但该方法的定义是一致的。(子类需要实现此抽象方法)。
 *
 * 1.2 接口
 *	1.2.1 定义一个接口
 *	 1)接口可以看成是特殊的抽象类。即只包含抽象方法和常量的抽象类。可以通过interface关键字来定义接口。
 *	 2)可以省略public abstract。因其默认就是public abstract的。
 *	1.2.2 实现接口
 *	 1)与继承不同，一个类可以实现多个接口，实现的接口直接用逗号分隔。当然，该类需要实现这些接口中定义的所有方法；
 *   2)一个类可以通过implements关键字”实现”接口。一个类实现了某个接口后必须实现该接口中定义的所有方法。
 *   3)接口可以作为一种类型声明变量，一个接口类型的变量可以引用实现了该接口的类的对象；
 *   	通过该变量可以调用该接口中定义的方法（具体的实现类提供了方法的实现）。
 *   	一个接口类型变量，引用了子类的对象。调用时，调用的是子类对象的具体的实现。
 *	1.2.3 接口的继承
 *   接口间可以存在继承关系，一个接口可以通过extends关键字继承另外一个接口。子接口继承了父接口中定义的所有方法。
 *	1.2.4 接口和抽象类的区别
 *	1)一个类只能继承一个抽象类，但可以实现多个接口。
 *	2)抽象类中可以包含抽象方法和非抽象方法，而接口中的所有方法均为抽象的。
 *	3)子类继承抽象类必须实现抽象类中所有抽象方法，否则子类也必须是抽象类。而子类实现接口则必须实现接口中的所有抽象方法。
 *
 * 1.3 多态
 *	1.3.1 多态的意义
 *	 多态即多种形态，主要有两个方面的表现。
 *	  1)一个类型的引用在指向不同的对象时会有不同的实现      行为的多态
 *	  2)同样一个对象，造型成不同的类型时，会有不同的功能  对象的多态
 *	1.3.2 向上造型
 *	 1)父类的引用指向子类的对象现象，叫做向上造型。
 *	 2)一个类的对象可以向上造型的类型有：父类的类型及其实现的接口类型。
 *	 3)当发生向上造型时，Java编译器会根据类型检查调用方法是否匹配。
 *		重写看对象，重载看引用(参数列表)，造型能点出来什么，看引用的类型
 *	1.3.3 强制转型(向下造型)
 *   1)可以通过强制转换将父类型变量转换为子类型变量，前提是该变量指向的对象确实是该子类类型。
 *   2)也可通过强制转换将变量转换为某种接口类型，前提是该变量指向的对象确实实现了该接口。
 *   3)如果在强制转换过程中出现违背上述两个前提，将会抛出ClassCastException。
 *	1.3.4 instanceof关键字
 *	 1)在强制转型中，为了避免出现ClassCastException，可以通过instanceof关键字判断某个引用指向的对象是否为指定类型。
 * 1.4 内部类
 *	1.4.1 定义成员内部类
 *	 1)一个类可以定义在另外一个类的内部，定义在类内部的类称之为Inner，其所在的类称之为Outer；
 *	 2)Inter定义在Outer的内部，通常只服务于Outer，对外不具备可见性，Inter可以直接调用Outer的成员及方法（包括私有的）
 *	1.4.2 创建内部类对象
 *	 1)一般情况下，Inner对象会在Outer对象中创建（构造方法或其他方法）;
 *	 2)Inner对象中会有一个隐式的引用指向创建它的Outer类对象。
 *	1.4.3 定义匿名内部类
 *   1)如果在一段程序中需要创建一个类的对象（通常这个类需要实现某个接口或者继承某个类），而且对象创建后，
 *   	这个类的价值也就不存在了，这个类可以不必命名，称之为匿名内部类。
 *   2)SuperType obj = new SuperType (…) {… … …};
 *   其中，第一个SuperType为用匿名类所实现的接口或所继承的父类类型声明的引用；
 *   第二个SuperType为匿名类所要实现的接口或继承的父类；小括号（）中为构造方法参数；大括号中为匿名中定义的成员变量或方法。
 *
 *   匿名内部类中访问外面的变量，该变量必须是final的
 * @author Double
 *
 */
public class AbstractInterfaceInnerDemos {

	/*
	 * 抽象类和抽象方法
	 */
	@Test
	public void test1() {

	}

	/*
	 * 接口
	 */
	@Test
	public void test2() {

	}

	/*
	 * 多态
	 */
	@Test
	public void test3() {
		Ff o1 = new Gg(); //向上造型
		Gg o2 = (Gg)o1; //o1所指向的对象Gg就是Gg类型
		Inter7 o3 = (Inter7)o1; //o1所指向的对象Goo实现了Inter7接口 向下造型 强转
		//Hh o4 = (Hh)o1; //既不是Hoo类型也没有实现Hoo接口
		                    //ClassCastExcption类型转换异常
		if(o1 instanceof Hh){
			Hh o5 = (Hh)o1;
		}

		System.out.println("over");
	}

	/*
	 * 内部类:成员内部类和匿名内部类
	 */
	@Test
	public void test4() {
		Mama m = new Mama();
		//Baby b = new Baby(); //编译错误，内部类对外不具备可见性

		//1.创建了MyInter1的一个子类，但是没有名字
		//2.为该子类创建了一个对象，名为o1
		//3.大括号中的为子类的类体
		MyInter1 o1 = new MyInter1(){
		};

		//1.创建了MyInter1的一个子类，但是没有名字
		//2.为该子类创建了一个对象，名为o2
		//3.大括号中的为子类的类体
		MyInter1 o2 = new MyInter1(){
		};

		final int num = 5;
		MyInter2 o3 = new MyInter2(){
			public void show(){
				System.out.println("showshow");
				System.out.println(num);
			}
		};
		o3.show();
	}

	/*
	 * 根据多边形周长求面积
	 */
	public static void maxArea(Shape[] shape){
		double max = shape[0].area();
		int maxIndex = 0;
		for(int i=1;i<shape.length;i++){
			double area = shape[i].area();
			if(area>max){
				max=area;
				maxIndex=i;
			}
		}
		System.out.println("最大面积是："+max+" 下标是"+maxIndex);
	}

	@Test
	public void Demo1() {
		//Shape s = new Shape(); //编译错误，抽象类不能被实例化
		Shape[] shapes = new Shape[6];//创建Shape数组对象
		shapes[0]= new Square(1); //向上造型
		shapes[1]= new Square(2);
		shapes[2]= new Circle(1);
		shapes[3]= new Circle(2); //大
		shapes[4] = new Six(1);
		shapes[5] = new Six(2);
		maxArea(shapes);
	}

	/*
	 * 银行ATM机系统
	 */
	@Test
	public void Demo2() {
		System.out.println("请输入密码：");
		char[] pwd = new char[6];// 随机生成六位数密码
		Scanner sc = new Scanner(System.in);
		String input = sc.next();

		System.out.println("请输入金额：");
		double money = sc.nextDouble();
		ICBCCards cards = new ICBCCards();// 工行卡
		ICBC card = new ICBCCards();// 向上造型 工行接口工行卡
		UnionPay up = new ICBCCards();// 向上造型 银联接口工行卡

/*		ABCATM atm = new ABCATM();//创建atm机对
		UnionPay card = new ABCCards();//银联卡--农行卡
		atm.insertCard(card);//插卡
		atm.payTelBill();
		System.out.println("over");*/
	}
}

abstract class Shape{//抽象类
	protected double c;//周长
	public abstract double area(); //抽象方法
}
class Square extends Shape{
	public Square(double c){//重写抽象方法--变不完整为完整
		this.c=c;
	}
	public double area(){
		return 0.0625*c*c;
	}
}
class Circle extends Shape{
	public Circle(double c){
		this.c=c;
	}
	public double area(){
		return 0.0796*c*c;
	}
}
class Six extends Shape{
	public Six(double c){
		this.c=c;
	}
	public double area(){
		return 0.0721*c*c;
	}
}

//演示接口的继承
interface Inter5{
	void show();
}
interface Inter6 extends Inter5{
	void say();
}
class Dd implements Inter6{
	public void say(){}
	public void show(){}
}


//演示接口的语法
interface Inter1{
	public static final double PI=3.14159;//public static final 顺序可以更改（不建议）
	public abstract void show();
	int COUNT = 5; //默认public static final
	void test(); //默认public abstract

	//int NUM; //编译错误，常量必须声明同时初始化
	//void say(){} //编译错误，抽象方法不能有方法体
}
//演示接口的实现
interface Inter2{
	void show();//默认为抽象方法 public abstract
	void test();//默认为抽象方法 public abstract
}
class Aa implements Inter2{
	//void b(){}//默认访问权限小于public的权限 编译错误
	//实现类必须大于或者等于被实现的接口
	//void c(){}//默认访问权限小于public的权限 编译错误
	//实现类必须大于或者等于被实现的接口
	public void show(){}
	public void test(){}
}
//演示接口的多实现，又继承又实现时应先继承后实现
interface Inter3{
	void show();
}
interface Inter4{
	void test();
}
abstract class Bb{
	abstract void say();
}
class Cc extends Bb implements Inter3,Inter4{
	public void show(){}
	public void test(){}
	void say(){}
}

//多态的演示
interface Inter7{
}
class Ff{
}
class Gg extends Ff implements Inter7{
}
class Hh extends Ff{
}

//成员内部类的演示
class Mama{
	private String name;
	Baby b = new Baby();
	Baby createBaby(){
		return new Baby();
	}
	class Baby{
		void showMamaName(){
			System.out.println(name);
			System.out.println(Mama.this.name);
			//System.out.println(this.name); //编译错误 内部类不能访问外部类的成员变量
			//编译错误，当前Baby类中没有name属性
		}
	}
}

//匿名内部类的演示
interface MyInter2{
	void show();
}

interface MyInter1{

}

interface UnionPay {// 银联接口
	public double getBalance();// 查询余额
	public boolean drawMoney(double number);// 取款
	public boolean checkPwd(String input);// 检查密码
}
interface ICBC extends UnionPay {// 工行接口
	public void payOnline(double number);// 在线支付
}
interface ABC extends UnionPay {// 农行接口
	public boolean payTelBill(String Tel, double number);// 支付电话费
}
class ABCATM{//农行ATM
	private UnionPay card;//银联卡
	public void insertCard(UnionPay card){//插卡
		this.card=card;
	}
	public void showBalance(){
		System.out.println(card.drawMoney(2000));
	}
	public void payTelBill(){
		if(card instanceof ABC){//不判断 可能产生ClassCastException类型转换异常
			ABC abcCard = (ABC)card;
			abcCard.payTelBill("16899168666", 888);
		}else{
			System.out.println("不是农行卡，不能支付电话费");
		}
	}
}
class ICBCCards implements ICBC {
	public double getBalance() {
		return 0.0;
	}
	public boolean drawMoney(double number) {
		return true;
	}
	public boolean checkPwd(String input) {
		return true;
	}
	public void payOnline(double number) {
	}
}
class ABCCards implements ABC {
	public double getBalance() {
		return 0.0;
	}
	public boolean drawMoney(double number) {
		return true;
	}
	public boolean checkPwd(String input) {
		return true;
	}
	public boolean payTelBill(String Tel, double number) {
		System.out.println("支付电话费成功");
		return true;
	}
}