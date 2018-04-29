package com.learn.oop;

import org.junit.Test;









//数组-2的演示 引用类型数组的演示
/**
 * 数组--2
 * 1.引用类型数组
 * 	1.1 数组是对象
 * 	 1)在java中，数组属于引用数据类型
 * 	 2)数组对象存放在堆中存储，数组变量属于引用类型，存储数组对象的地址信息，指向数组对象。
 * 	 3)而数组的元素可以看成数组对象的成员变量（只不过类型全部相同）。
 *
 *	int[ ]   arr  = new   int [ 3 ];
 *	在堆内存中分配了数组对象，分配三个int型空间，并将每个元素赋初始值为0，栈中存储对堆中数据的引用，即堆中int数组的首地址。
 *
 * 	1.2 引用类型数组的声明
 * 	 除了基本类型数组以外，也可以声明引用类型数组。所谓引用类型数组，即数组元素的类型不是基本类型(int,char,float。。) ,
 *  而是引用类型	Cell [ ]    cells  = new  Cell [ 4 ] ;
 *	内存分配：new Cell[4]实际是分配了4个空间用于存放4个Cell类型的引用，并赋初始值为null，
 *	而并非是分配了4个Cell类型的对象。
 *
 * 	1.3 引用类型数组的初始化
 *	 1)基本类型数组的默认值同成员变量默认的初始值（如int类型的数组初始值为0）,而引用类型数组的默认初始值都是null。
 *	 2)如果希望每一个元素都指向具体的对象，则需要针对每一个数组元素进行“new”运算。与基本类型数组一样，
 *		也可以采用静态初始化的方式进行初始化操作。
 *
 *  1.4 数组的类型是基本类型数组
 *	 1)数组的元素或为基本类型int，或为引用类型Cell，而数组本身也是一种数据类型，当然也可以作为数组的元素。
 *		int [ ][ ] arr = new int[3][ ];
 * 		数组可以用来表示类似“矩阵”这样的数据结构（3行4列）
 *  2)在其他的一些语言中有专门表示这样结构的所谓二维数组；但严格的讲，Java语言中没有真正的二维数组,Java中称之为数组的数组。
 * @author Double
 *
 */
/*public class ReferenceTypeArrayDemos {

	@Test
	public void test1() {
		//声明整型数组arr，包含4个元素
		//每个元素都是int类型，默认值为0
		int[]  arr=new   int[4];

		//声明Cell型数组cells，包含4个元素
		//每个元素都是Cell型，默认值为null
		Cell[] cells=new Cell[4];//创建Cell数组对象
		cells[0] = new Cell();//创建Cell对象
		cells[1] = new Cell();
		cells[2] = new Cell();
		cells[3] = new Cell();

		cells[0] = new Cell(0,4);
		cells[1] = new Cell(1,3);
		cells[2] = new Cell(1,4);
		cells[3] = new Cell(1,5);

		int[] array = new int[]{1,4,6,8};
		Cell[] cs = new Cell[]{
		      new Cell(2,5),
			  new Cell(2,6),
			  new Cell(2,7),
			  new Cell(3,6)
		    };
	}


	@Test
	public void test2() {
		int   [] array   = new int[4];
		Cell  [] cells = new Cell[4];

		//声明整型数组的数组arr，包含3个元素
		//每个元素都是int[]型，默认值为null
		int[] [] arr   = new int[3][];
		arr[0] = new int[2];
		arr[1] = new int[3];
		arr[2] = new int[2];
		//给arr中第2个元素中的第1个元素赋值为100
		arr[1][0] = 100;

		arr[0] = new int[4];
		arr[1] = new int[4];
		arr[2] = new int[4];

		arr = new int[3][4]; //3行4列 矩形
		for(int i=0;i<arr.length;i++){
		  for(int j=0;j<arr[i].length;j++){
		    arr[i][j] = 100;
		  }
		}
	}

	*//**
	 * 1)找对象:一堆T/J/L/I/O/S/Z型对象
	 * 2)抽类:T/J/L/I/O/S/Z类
	 * 3)设计类中的成员变量和方法
	 * 4)创建对象并测试
	 *
	 * 测试俄罗斯方块七种类型格子 T,J,I,O,L,S,Z
	 *//*
	@Test
	public void test3() {
		//T类的测试
		T t = new T(2,5);
 *
		System.out.println("初始位置");
		t.print();

		t.drop();
		System.out.println("降落后");
		t.print();

		t.moveLeft();
		System.out.println("左移后");
		t.print();

		t.moveRight();
		System.out.println("右移后");
		t.print();

		//J类的测试
		J j = new J(3,6);

		System.out.println("初始位置");
		j.print();

		j.drop();
		System.out.println("降落后");
		j.print();

		j.moveLeft();
		System.out.println("左移后");
		j.print();

		j.moveRight();
		System.out.println("右移后");
		j.print();



		O o = new O(2,3);

		System.out.println("初始位置");
		o.print();

		o.drop();
		System.out.println("下落后");
		o.print();

		o.moveLeft();
		System.out.println("左移后");
		o.print();

		o.moveRight();
		System.out.println("右移后");
		o.print();
		L l = new L(2,2);

		System.out.println("初始位置");
		l.print();

		l.drop();
		System.out.println("下落后");
		l.print();

		l.moveLeft();
		System.out.println("左移后");
		l.print();

		l.moveRight();
		System.out.println("右移后");
		l.print();

		I i = new I(2,1);

		System.out.println("初始位置");
		i.print();

		i.drop();
		System.out.println("下落后");
		i.print();

		i.moveLeft();
		System.out.println("左移后");
		i.print();

		i.moveRight();
		System.out.println("右移后");
		i.print();

		S s= new S(3,2);

		System.out.println("初始位置");
		s.print();

		s.drop();
		System.out.println("下落后");
		s.print();

		s.moveLeft();
		System.out.println("左移后");
		s.print();

		s.moveRight();
		System.out.println("右移后");
		s.print();

		Z z = new Z(2,4);

		System.out.println("初始位置");
		z.print();

		z.drop();
		System.out.println("下落后");
		z.print();

		z.moveLeft();
		System.out.println("左移后");
		z.print();

		z.moveRight();
		System.out.println("右移后");
		z.print();
	}
}

*//**
 * J型类
 * @author Double
 *
 *//*
class J {
	Cell[] cells;

	J() {
		this(0, 0);
	}

	J(int row, int col) {
		cells = new Cell[4];
		cells[0] = new Cell(row, col);
		cells[1] = new Cell(row, col + 1);
		cells[2] = new Cell(row, col + 2);
		cells[3] = new Cell(row + 1, col + 2);
	}

	void drop() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].row++;
		}
	}

	void moveLeft() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].col--;
		}
	}

	void moveRight() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].col++;
		}
	}

	void print() {
		for (int i = 0; i < cells.length; i++) {
			String str = cells[i].getCellInfo();
			System.out.println(str);
		}
	}
}

*//**
 * T类型格子
 *
 * @author Double
 *
 *//*
class T {
	Cell[] cells;// 格子数组

	T() {
		this(0, 0);
	}

	T(int row, int col) {
		cells = new Cell[4];// 创建格子数组对象
		cells[0] = new Cell(row, col);// 创建格子对象
		cells[1] = new Cell(row, col + 1);
		cells[2] = new Cell(row, col + 2);
		cells[3] = new Cell(row + 1, col + 1);
	}

	void drop() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].row++;
		}
	}

	void moveLeft() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].col--;
		}
	}

	void moveRight() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].col++;
		}
	}

	void print() {
		for (int i = 0; i < cells.length; i++) {
			String str = cells[i].getCellInfo();
			System.out.println(str);
		}
	}
}

//I类型
class I {
	Cell[] cells;

	I(){
		this(0,0);
	}
	I(int row,int col){
		this.cells=new Cell[4];
		this.cells[0]=new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row,col+2);
		this.cells[3] = new Cell(row,col+3);
	}

	void drop(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].row++;
		}
	}

	void moveLeft(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col--;
		}
	}

	void moveRight(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col++;
		}
	}

	void print(){
		for(int i=0;i<this.cells.length;i++){
			String str = this.cells[i].getCellInfo();
			System.out.println(str);
		}
	}
}

//L类型
class L {
	Cell[] cells;

	L(){
		this(0,0);
	}

	L(int row,int col){
		this.cells = new Cell[4];
		this.cells[0] = new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row,col+2);
		this.cells[3] = new Cell(row+1,col);
	}

	void drop(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].row++;
		}
	}

	void moveLeft(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col--;
		}
	}

	void moveRight(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col++;
		}
	}

	void print(){
		for(int i=0;i<this.cells.length;i++){
			String str = this.cells[i].getCellInfo();
			System.out.println(str);
		}
	}
}

//O类型
class O {
	Cell[] cells;

	O(){
		this(0,0);
	}

	O(int row,int col){
		this.cells = new Cell[4];
		this.cells[0]= new Cell(row,col);
		this.cells[1]= new Cell(row,col+1);
		this.cells[2]= new Cell(row+1,col);
		this.cells[3]= new Cell(row+1,col+1);
	}

	void drop(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].row++;
		}
	}

	void moveLeft(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col--;
		}
	}

	void moveRight(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col++;
		}
	}

	void print(){
		for(int i=0;i<this.cells.length;i++){
			String str=this.cells[i].getCellInfo();
			System.out.println(str);
		}
	}
}

//S类型
class S {
	Cell[] cells;

	S(){
		this(0,0);
	}

	S(int row,int col){
		this.cells = new Cell[4];
		this.cells[0]=new Cell(row,col);
		this.cells[1]=new Cell(row,col+1);
		this.cells[2]=new Cell(row-1,col+1);
		this.cells[3]=new Cell(row-1,col+2);
	}

	void drop(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].row++;
		}
	}

	void moveLeft(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col--;
		}
	}

	void moveRight(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col++;
		}
	}

	void print(){
		for(int i=0;i<this.cells.length;i++){
			String str = this.cells[i].getCellInfo();
			System.out.println(str);
		}
	}
}

//Z类型
class Z {
	Cell[] cells;

	Z(){
		this(0,0);
	}
	Z(int row,int col){
		this.cells = new Cell[4];
		this.cells[0] = new Cell(row,col);
		this.cells[1] = new Cell(row,col+1);
		this.cells[2] = new Cell(row+1,col+1);
		this.cells[3] = new Cell(row+1,col+2);
	}
	void drop(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].row++;
		}
	}
	void moveLeft(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col--;
		}
	}
	void moveRight(){
		for(int i=0;i<this.cells.length;i++){
			this.cells[i].col++;
		}
	}
	void print(){
		for(int i=0;i<this.cells.length;i++){
			String str = this.cells[i].getCellInfo();
			System.out.println(str);
		}
	}
}*/
