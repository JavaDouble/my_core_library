package com.learn.oop;

import org.junit.Test;


//继承的演示
/**
 * 继承:
  1)作用:避免代码重复，有利于代码的复用
  2)通过extends来实现继承
  3)父类:所有子类所共有的属性和行为
    子类:子类所特有的属性和行为
  4)子继承父后，子具有: 子+父
  5)一个父类可以有多个子类，
    一个子类只能有一个父类----单一继承
  6)继承具有传递性
  7)java规定:构造子类之前必须先构造父类
    在子类构造中若不调用父类的构造，则默认super()调父类的无参构造，
	若子类构造中调用父类构造了，则不再默认提供
	super()调用父类构造必须位于子类构造的第一句
3.super:指代当前对象的父类对象
  super的用法:
    super.成员变量名------访问父类的成员变量
	super.方法名()--------调用父类的方法
	super()---------------调用父类的构造方法
4.向上造型:
  1)父类型的引用指向子类的对象
  2)能点出来什么，看引用的类型
5.方法的重写(override):
  1)发生在父子类中，方法名称相同，参数列表相同，方法体不同
  2)重写方法被调用时，看对象的类型
6.重载与重写的区别:------常见面试题
  1)重载(overload):
    1.1)发生在一个类中，方法名相同，参数列表不同，方法体不同
	1.2)遵循"编译期"绑定，看引用的类型绑定方法
  2)重写(override):
    2.1)发生在父子类中，方法名相同，参数列表相同，方法体不同
	2.2)遵循"运行期"绑定，看对象的类型调用方法
 */
/**
 * 继承的意义
 * 1.1 继承
 * 	1.1.1 泛化的过程
 * 		子类可以共享父类的数据。这个过程就是泛化的过程，也叫继承。
 *  1.1.2 extends关键字
 *  	1)使用继承可以实现代码的重用，在java语言中，需要通过extends关键字实现类的继承;
 *  	2)继承完成后，子类（Sub class）可以继承父类（Super class）的成员变量及成员方法，
 *  		同时子类也可以定义自己的成员变量和成员方法。届时，子类将具有父类的成员及本类的成员。
 *  	3)需要注意的是，Java语言不支持多重继承，即：一个类只能继承一个父类，但一个父类可以有多个子类。
 *  		继承的单一性，也叫单根性。
 *  	4)继承可以传递 子类不能继承父类的构造方法
 *  1.1.3 继承中构造方法
 *  	1)java规定，子类在构造之前必须先构造父类。
 *  	2)子类的构造方法中是必须要通过super关键字来调用父类的构造方法的，这样才可以保证妥善的初始化继承自父类的成员变量。
 *  	3)如果子类的构造方法中没有调用父类的构造方法，则java编译器会自动的加入对父类无参构造方法的调用。
 *  		并且super关键字必须位于子类构造方法的第一行，否则会有编译错误。
 *  		但是父类中又没有定义无参的构造方法，因此会发生编译错误。
 *  1.1.4 父类的引用指向子类的对象(向上造型)
 *  	一个子类的对象可以向上造型为父类的类型。即，定义父类型的引用可以指向子类的对象。
 * 1.2 重写
 * 	1.2.1  方法的重写
 * 		1)在java语言中，子类可以重写（覆盖）继承自父类的方法，即方法名和参数列表与父类的方法相同，但是方法的实现不同。
 * 		2)当子类重写了父类的方法后，该重写方法被调用时（无论是通过子类的引用调用还是通过父类的引用调用），
 * 			运行的都是子类重写后的版本。
 *  1.2.2 重写中使用super关键字
 *	super的用法:
 *    super.成员变量名------访问父类的成员变量
 *    super.方法名()--------调用父类的方法
 *    super()---------------调用父类的构造方法
 *  1.2.3 重写和重载的区别
 *  重载与重写是完全不同的语法现象，区别如下所示：
 *  	1)重载： 是指在一个类中定义多个方法名相同但参数列表不同的方法，在编译时，根据参数的个数和类型来决定绑定哪个方法。
 *  	2)重写： 是指在子类中定义和父类完全相同的方法，在程序运行时，根据对象的类型（而不是引用类型）而调用不同的方法。
 *  	3)首先，重载遵循所谓“编译期绑定”，即在编译时根据参数变量的类型判断应该调用哪个方法， 因为变量obj为Super类型引用，
 *  		 所以，Goo的g(Super)被调用，先输出g(Super)。
 *  	4)重写遵循所谓“运行期绑定”，即在运行的时候，根据引用变量所指向的实际对象的类型来调用方法，因为obj实际指向的是
 *  		子类Sub的对象，因此，子类重写后的f方法被调用，即sub.f()。
 * @author Double
 *
 */
public class InheritDemos {

	//T,J,O,L,S,Z,I类的测试
	public static void printWall(Tetromino tt){
		for(int i=0;i<20;i++){//行
			for(int j=0;j<10;j++){//列
		/*		if(i==tt.cells[0].row&&j==tt.cells[0].col
				 ||i==tt.cells[1].row&&j==tt.cells[1].col
				 ||i==tt.cells[2].row&&j==tt.cells[2].col
				 ||i==tt.cells[3].row&&j==tt.cells[3].col
						){
					System.out.print("* ");
				}else{
					System.out.print("- ");
				}//缺点:扩展性差      优点:效率高
		*/

				//缺点:效率低      优点:扩展性好
				boolean flag = true;//1.假设打-
				for(int k=0;k<tt.cells.length;k++){//
					if(i==tt.cells[k].row && j==tt.cells[k].col){
						flag = false;//2.修改为打*
						break;
					}
				}
				if(flag){//3.判断的结果 开关3步骤:123 并非一次判断得结果
					System.out.print("- ");
				}else{
					System.out.print("* ");
				}
			}
			System.out.println();
		}
	}

	@Test
	public void test1() {
		T t = new T(2,5);
		System.out.println("打印T类型：坐标分别是：");
		t.print();
		System.out.println();
		printWall(t);////传值的同时造型 Tetromino tt=t=new T(2,5);
		System.out.println();

		Tetromino j = new J(2,6);//向上造型
		System.out.println("打印J类型：坐标分别是：");
		t.print();
		System.out.println();
		printWall(j);//先造型后传值
		System.out.println();

		Tetromino o = new O(2,3);
		System.out.println("打印O类型：坐标分别是：");
		t.print();
		System.out.println();
		printWall(o);
		System.out.println();

		Tetromino l = new L(2,2);
		System.out.println("打印L类型：坐标分别是：");
		t.print();
		System.out.println();
		printWall(l);
		System.out.println();

		Tetromino s = new S(3,2);
		System.out.println("打印S类型：坐标分别是：");
		t.print();
		System.out.println();
		printWall(s);
		System.out.println();

		Tetromino z = new Z(2,4);
		System.out.println("打印Z类型：坐标分别是：");
		t.print();
		System.out.println();
		printWall(z);
		System.out.println();

		Tetromino i = new I(2,1);
		System.out.println("打印I类型：坐标分别是：");
		t.print();
		System.out.println();
		printWall(i);
		System.out.println();

	}

	//向上造型的演示
	// 1)父类型的引用指向子类的对象	  2)能点出来什么，看引用的类型
	@Test
	public void test2() {
		Eoo o1 = new Eoo();
		o1.e = 1;
		o1.show();
		//o1.f = 2; //编译错误，父不能访问子的

		Foo o2 = new Foo();
		o2.f = 1;
		o2.test();
		o2.e = 2; //正确，子可以访问父的
		o2.show();

		Eoo o3 = new Foo(); //向上造型
		o3.e = 1;
		o3.show();
		//o3.f = 2; //编译错误，能点出来什么，看引用的类型
	}

	//super的演示
	/*
	 * super:指代当前对象的父类对象
	 *   super的用法:
    		super.成员变量名------访问父类的成员变量
			super.方法名()--------调用父类的方法
			super()---------------调用父类的构造方法
	 */
	@Test
	public void test3() {
		Boo o = new Boo();
	}

	//重写与重载区别的演示
	@Test
	public void test4() {
		//重载看引用，重写看对象
		Eooo eoo = new Eooo();
		Cooo o = new Dooo(); //向上造型
		eoo.test(o);
	}
}

//格子类
class Cell {
	int row; //行号
	int col; //列号
	Cell(){
		this(0,0);
	}
	Cell(int n){
		this(n,n);
	}
	Cell(int row,int col){
		this.row = row;
		this.col = col;
	}

	void drop(){ //下落一格
		row++;
	}
	void moveLeft(int n){ //左移n格
		col-=n;
	}
	String getCellInfo(){ //获取行号和列号
		return row+","+col;
	}

	void drop(int n){ //下落n格
		row+=n;
	}
	void moveLeft(){ //左移一格
		col--;
	}
}

//四格拼板---父类
class Tetromino {
	Cell[] cells; //格子数组
	Tetromino(){
		cells = new Cell[4];
	}

	void drop(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].row++;
		}
	}
	void moveLeft(){
		for(int i=0;i<cells.length;i++){
			cells[i].col--;
		}
	}
	void moveRight(){
		for(int i=0;i<cells.length;i++){
			cells[i].col++;
		}
	}
	void print(){
		for(int i=0;i<this.cells.length;i++){
			String str = this.cells[i].getCellInfo();
			System.out.print(str+" ");
		}
	}
}

/*//J型类
class J extends Tetromino{
	J() {
		this(0, 0);
	}
	J(int row, int col) {
		cells[0] = new Cell(row, col);
		cells[1] = new Cell(row, col + 1);
		cells[2] = new Cell(row, col + 2);
		cells[3] = new Cell(row + 1, col + 2);
	}
}

//T类型
class T extends Tetromino{
	T() {
		this(0, 0);
	}

	T(int row, int col) {
		cells[0] = new Cell(row, col);// 创建格子对象
		cells[1] = new Cell(row, col + 1);
		cells[2] = new Cell(row, col + 2);
		cells[3] = new Cell(row + 1, col + 1);
	}
}

//I类型
class I extends Tetromino{

	I(){
		this(0,0);
	}
	I(int row,int col){
		this.cells[0]=new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row,col+2);
		this.cells[3] = new Cell(row,col+3);
	}
}

//L类型
class L extends Tetromino{
	L(){
		this(0,0);
	}
	L(int row,int col){
		this.cells[0] = new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row,col+2);
		this.cells[3] = new Cell(row+1,col);
	}
}

//O类型
class O extends Tetromino{
	O(){
		this(0,0);
	}

	O(int row,int col){
		this.cells[0]= new Cell(row,col);
		this.cells[1]= new Cell(row,col+1);
		this.cells[2]= new Cell(row+1,col);
		this.cells[3]= new Cell(row+1,col+1);
	}
}

//S类型
class S extends Tetromino{

	S(){
		this(0,0);
	}

	S(int row,int col){
		this.cells[0]=new Cell(row,col);
		this.cells[1]=new Cell(row,col+1);
		this.cells[2]=new Cell(row-1,col+1);
		this.cells[3]=new Cell(row-1,col+2);
	}
}

//Z类型
class Z extends Tetromino{

	Z(){
		this(0,0);
	}
	Z(int row,int col){
		this.cells[0] = new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row+1,col+1);
		this.cells[3] = new Cell(row+1,col+2);
	}
}*/


//J型类
class J extends Tetromino{
	J(){
		this(0,0);
	}
	J(int row,int col){
		super();
		cells[0] = new Cell(row,col);
		cells[1] = new Cell(row,col+1);
		cells[2] = new Cell(row,col+2);
		cells[3] = new Cell(row+1,col+2);
	}

	void print(){ //输出每个格子的行号和列号
		System.out.println("I am a J:");
		super.print();
	}
}

//T类型
class T extends Tetromino{
	T(){
		this(0,0);
	}
	T(int row,int col){
		super(); //默认调用父类的无参构造
		super.cells[0] = new Cell(row,col); //创建格子对象
		super.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row,col+2);
		this.cells[3] = new Cell(row+1,col+1);
	}

	void print(){ //输出每个格子的行号和列号
		System.out.println("I am a T:");
		super.print(); //调用父类的print()方法
	}
}

//I类型
class I extends Tetromino{

	I(){
		this(0,0);
	}
	I(int row,int col){
		super();
		this.cells[0]=new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row,col+2);
		this.cells[3] = new Cell(row,col+3);
	}
	void print(){ //输出每个格子的行号和列号
		System.out.println("I am a J:");
		super.print(); //调用父类的print()方法
	}
}

//L类型
class L extends Tetromino{
	L(){
		this(0,0);
	}
	L(int row,int col){
		super();
		this.cells[0] = new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row,col+2);
		this.cells[3] = new Cell(row+1,col);
	}
	void print(){ //输出每个格子的行号和列号
		System.out.println("I am a L:");
		super.print(); //调用父类的print()方法
	}
}

//O类型
class O extends Tetromino{
	O(){
		this(0,0);
	}
	O(int row,int col){
		super();
		this.cells[0]= new Cell(row,col);
		this.cells[1]= new Cell(row,col+1);
		this.cells[2]= new Cell(row+1,col);
		this.cells[3]= new Cell(row+1,col+1);
	}
	void print(){ //输出每个格子的行号和列号
		System.out.println("I am a O:");
		super.print(); //调用父类的print()方法
	}
}

//S类型
class S extends Tetromino{
	S(){
		this(0,0);
	}
	S(int row,int col){
		super();
		this.cells[0]=new Cell(row,col);
		this.cells[1]=new Cell(row,col+1);
		this.cells[2]=new Cell(row-1,col+1);
		this.cells[3]=new Cell(row-1,col+2);
	}
	void print(){ //输出每个格子的行号和列号
		System.out.println("I am a S:");
		super.print(); //调用父类的print()方法
	}
}

//Z类型
class Z extends Tetromino{
	Z(){
		this(0,0);
	}
	Z(int row,int col){
		super();
		this.cells[0] = new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row+1,col+1);
		this.cells[3] = new Cell(row+1,col+2);
	}
	void print(){ //输出每个格子的行号和列号
		System.out.println("I am a Z:");
		super.print(); //调用父类的print()方法
	}
}

//向上造型的演示
class Eoo{
	int e;
	void show(){}
}
class Foo extends Eoo{
	int f;
	void test(){}
}

//super的演示
class Coo{
	Coo(int a){
	}
}
class Doo extends Coo{
	Doo(){
		super(2);
	}
}
class Aoo{
	Aoo(){
		System.out.println("父类构造");
	}
}
class Boo extends Aoo{
	Boo(){
		super(); //默认
		System.out.println("子类构造");
	}
}

/*
 * 重写需遵循"两同两小一大"原则:
 * 1.两同:
 *   1)方法名称相同
 *   2)参数列表相同
 * 2.两小:
 *   1)子类方法的返回值类型小于或等于父类的
 *     1.1)void时，必须相同
 *     1.2)基本类型时，必须相同
 *     1.3)引用类型时，小于或等于
 *   2)子类方法抛出的异常小于或等于父类的------异常之后
 * 3.一大:
 *   1)子类方法的访问权限大于或等于父类的------访问修饰符之后
 */
//重写的演示
//父类大，子类小
class A{
	public void show(){}
	double test(){ return 0.0; }
	B sayHi(){ return null; }
	A say(){ return null; }
}
class B extends A{
	//int show(){ return 1; } //编译错误，void时必须相同
	//int test(){ return 0.0; } //编译错误，基本类型时必须相同
	//A sayHi(){ return null; } //编译错误，引用类型小于或等于
	B say(){ return null; } //正确
	//void show(){} //编译错误，访问权限必须大于等于父类的
}

/*
 * 1.创建Cooo类，包含show方法，输出父类show
 * 2.创建Dooo类，继承Cooo，重写show方法，输出子类show
 * 3.创建Eooo类，包含:
 *   1)test方法，参数为Cooo类型的o，方法中:
 *       输出父型参数，o.show()
 *   2)test方法，参数为Dooo类型的o，方法中:
 *       输出子型参数，o.show()
 * 4.main()方法中:
 *   1)创建Eooo对象eoo
 *   2)声明Cooo型引用o指向Dooo的对象
 *   3)eoo.test(o);
 */
class Eooo{
	void test(Cooo o){
		System.out.println("父型参数");
		o.show();
	}
	void test(Dooo o){
		System.out.println("子型参数");
		o.show();
	}
}
class Cooo{
	void show(){
		System.out.println("父类show");
	}
}
class Dooo extends Cooo{
	void show(){
		System.out.println("子类show");
	}
}
