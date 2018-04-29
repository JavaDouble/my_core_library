package com.learn.oop;

import java.util.Scanner;

import org.junit.Test;








//Java OOP 练习题锦集
public class Exercises {

	public static void operate(Tetromino ttt){
		System.out.println("1————下落，2————向左，3————向右，0————退出");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();

		while(num!=0){
		switch(num){
		case 1:ttt.drop();
		break;
		case 2:ttt.moveLeft();
		break;
		case 3:ttt.moveRight();
		break;
		}
		ttt.print();
		printWall(ttt);
		num = sc.nextInt();
	}
		System.out.println("游戏结束");
		sc.close();
	}
	public static void printWall(Tetromino tt){
		for(int i=0;i<20;i++){
			for(int j=0;j<10;j++){

				boolean flag =true;//1.假设打-
				for(int k=0;k<tt.cells.length;k++){
					if(i==tt.cells[k].row && j==tt.cells[k].col){
						flag=false;//2.
						break;
					}
				}
				if(flag){//3.判断结果
					System.out.print("- ");
				}else{
					System.out.print("* ");
				}
			}
			System.out.println();
		}
	}

	@Test
	public void exercise01() {
		Tetromino t = new T(2,5);
		t.print();
		printWall(t);
		operate(t);


/*		Tetromino j = new J(3,6);
		j.print();
		printWall(j);

		Tetromino o = new O(2,6);
		o.print();
		printWall(o);

		Tetromino l = new L(3,2);
		l.print();
		printWall(l);

		Tetromino s = new S(2,3);
		s.print();
		printWall(s);

		Tetromino z = new Z(3,4);
		z.print();
		printWall(z);

		Tetromino i = new I(4,3);
		i.print();
		printWall(i);*/
	}

	/*
	 * 接口练习:
	 * 1.声明接口Inter11，包含常量PI和抽象方法a()
	 * 2.声明接口Inter22，包含b()和c()
	 *   声明类Aaa，实现Inter22接口
	 * 3.声明接口Inter33，包含d()
	 *   声明类Bbb，实现Inter22和Inter33接口
	 * 4.声明抽象类Ccc，包含抽象方法e()
	 *   声明类Ddd，继承Ccc，实现Inter22和Inter33接口
	 * 5.声明接口Inter44，继承Inter33接口
	 *   声明类Eee，实现Inter44接口
	 * 6.在main()方法中:
	 *     Inter44 o1 = new Inter44();------???
	 *     Inter44 o2 = new Eee();---------???
	 *     Inter33 o3 = new Eee();---------???
	 */
	@Test
	public void exercise02() {
		//Inter44 i4=new Inter44();//接口是引用类型 不能被实例化
		Inter44 i4;//接口是引用类型 可以声明 不可以实例化
		Inter44 in4=new Eee();//向上造型 接口是引用类型
		//实现接口可以看成继承关系 但是实现接口不是继承关系（由接口和继承的定义决定）
		Inter33 i3=new Ddd();//向上造型
		//接口继承接口，类实现接口时 被继承的接口也可以向上造型
	}

	/*
	 * 银行ATM机系统
	 */
	@Test
	public void test3() {
		ATMABC atm = new ATMABC();
		//ICBCCard icbc = new ICBCCard(2000,"666666");
		ABCCard abc = new ABCCard(1000,"888888");
		atm.insertCard(abc);
		atm.allMenu();
		//atm.payTelBill();
	}
}

interface Inter11{//接口的演示
	public static final double PI=3.14159;
	public abstract void  a();
	//double PI=3.14159;//默认public static final 为常量
	//void a();//默认public abstract 为抽象方法
	//double NUM;//编译错误 默认为常量 必须声明的同时初始化
}
interface Inter22{//接口的实现
	//public abstract void b();
	//public abstract void c();
	void b();//默认为抽象方法 public abstract
	void c();//默认为抽象方法 public abstract
}
class Aaa implements Inter22{//接口的实现
	//void b(){}//默认访问权限小于public的权限 编译错误
	//实现类必须大于或者等于被实现的接口
	//void c(){}//默认访问权限小于public的权限 编译错误
	//实现类必须大于或者等于被实现的接口
	public void b(){}
	public void c(){}
}
interface Inter33{
	void show();//默认为抽象方法 public abstract
}
class Bbb implements Inter22,Inter33{//多个接口的实现演示
	public void b(){}
	public void c(){}
	public void show(){}
}
abstract class Ccc{
	abstract void e();//默认访问权限
}
/*class Doo implements Inter2,Inter3 extends Coo{
	//有实现接口和继承时，先继承后实现
	void e(){}//默认权限
	//public void e(){}//子类访问权限大于或者等于父类访问权限
	public void b(){}
	public void c(){}
	public void show(){}
}*/
class Ddd extends Ccc implements Inter22,Inter33{//继承和多个接口实现的演示
	void e(){}//默认访问权限
	//public void e(){}//子类访问权限大于或者等于父类访问权限
	public void b(){}
	public void c(){}
	public void show(){}
}
interface Inter44 extends Inter33{//接口的继承演示
	void say();
}
class Eee implements Inter44{//接口继承接口 类实现接口
	public void show(){}//接口的继承 要重写所有被实现接口的抽象方法
	//包括接口继承的接口的抽象方法 否则编译错误
	public void say(){}
}

//银行ATM机系统
class ATMABC{
	private UnionPay01 card;
	public void insertCard(UnionPay01 cards){//插卡
		if(card==null){
			this.card=cards;
		}
	}
	public void outCard(){//退卡
		card=null;
	}
	public void showBalance(){//查询余额
		System.out.println("当前余额是："+card.getbalance());
	}
	public void takeMoney(){//取款
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入金额：");
		double num = sc.nextDouble();
		if(card.drawMoney(num)){
			System.out.println("取款成功，卡内余额为："+card.getbalance());
		}else{
			System.out.println("对不起，卡内余额不足");
		}
		sc.close();
	}
	public void payTelBill(){//支付手机话费
		Scanner input = new Scanner(System.in);
		if(card instanceof ABC01){
			ABC01 abccard = (ABC01)card;
			System.out.println("请输入电话号码：");
			String telNum=input.next();
			System.out.println("请输入缴费金额：");
			double sum = input.nextDouble();
			if(abccard.payTelBill(telNum, sum)){
				System.out.println("缴费成功,卡内余额："+abccard.getbalance());
			}else{
				System.out.println("缴费失败");
			}
		}else{
			System.out.println("您的卡不是农行行卡，无法完成缴费");
		}
		input.close();
	}
	public void allMenu(){//农行ATM机的菜单
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入密码：");
		String input =sc.next();
		if(card.checkPwd(input)){
			System.out.println("请选择功能：\n 1.查看余额   2.取款   3.缴电话费");
			int choice = sc.nextInt();
			switch(choice){
			case 1:showBalance();
			break;
			case 2:takeMoney();
			break;
			case 3:payTelBill();
			break;
			default:
				System.out.println("非法输入");
			}
		}else{
			System.out.println("密码错误");
		}
		sc.close();
		this.outCard();
	}
}
interface UnionPay01{
	public double getbalance();//获取余额
	public boolean drawMoney(double number);//取款
	public boolean checkPwd(String input);//检查密码
}
interface ICBC01 extends UnionPay01{
	public void payOnline(double number);//在线支付
}
interface ABC01 extends UnionPay01{
	public boolean payTelBill(String tel,double sum);
}
class ICBCCard implements ICBC01{
	private double money;
	private String pwd;
	public ICBCCard(double money,String pwd){
		this.money=money;
		this.pwd=pwd;
	}
	@Override
	public double getbalance(){
		return money;
	}
	@Override
	public boolean drawMoney(double number){
		if(money>=number){
			money-=number;
			return true;
		}
		return false;
	}
	@Override
	public boolean checkPwd(String input){
		if(input.equals(pwd)){
			return true;
		}
		return false;
	}
	public void payOnline(double sum){
		if(money>=sum){
			money-=sum;
		}else{
			System.out.println("对不起，卡内余额不足，支付失败。");
		}
	}
}
class ABCCard implements ABC01{
	private double money;
	private String pwd;
	public ABCCard(double money,String pwd){
		this.money=money;
		this.pwd=pwd;
	}
	@Override
	public double getbalance(){
		return money;
	}
	@Override
	public boolean drawMoney(double number){
		if(money-number>=-2000){
			money-=number;
			return true;
		}
		return false;
	}
	@Override
	public boolean checkPwd(String input){
		if(input.equals(pwd)){
			return true;
		}
		return false;
	}
	public boolean payTelBill(String tel,double sum){
		if(tel.length()==11 && money>=sum){
			money-=sum;
			return true;
		}
		return false;
	}
}
