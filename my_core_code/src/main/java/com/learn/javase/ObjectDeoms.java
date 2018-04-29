package com.learn.javase;


import org.junit.Test;

//Object类的演示
public class ObjectDeoms {

	//测试所哟类都直接或间接的继承了Object类
	@Test
	public void test1() {
		Person p1=new Person();
		System.out.println(p1.toString());
		//com.learn.javase.Person@443b7951 类名+@hashCode 证实继承自Object类的toString()方法
		/*
		 * public String toString() {
        	return getClass().getName() + "@" + Integer.toHexString(hashCode());
    		}
		 */
		System.out.println(p1);
		System.out.println(p1.hashCode());
		System.out.println(p1.getClass().getName().toString());
		System.out.println(p1.getClass());
		System.out.println(p1.getClass().getName());
	}

	/**
	 * 测试重写Object类的toString()和equals()方法
	 */
	@Test
	public void test2() {
		Point p = new Point();
		p.setX(1);
		p.setY(2);
		/*
		 * Object提供的toString返回的是该引用变量指向对象的地址
		 * 格式为:类名@地址
		 * 我们也称该格式为该对象的句柄但它没有什么实际意义。
		 * java手册上有说明，通常我们需要使用一个类的toString方法时，就应当重写它。
		 */
		String str = p.toString();
		System.out.println(str);
		/*
		 * System.out.println(Object o)
		 * 该方法会输出给定对象toString方法返回的字符串到控制台。
		 */
		/*
		 * 	public void println(Object x) {
        	String s = String.valueOf(x);
        	synchronized (this) {
            print(s);
            newLine();
        	}
    	}
		 * 该方法会将给定对象的toString返回的字符串输出到控制台
		 */
		System.out.println(p);

		Point p1 = new Point();
		p1.setX(1);
		p1.setY(2);
		System.out.println("p1:"+p1);

		System.out.println(p==p1);//false
		/*
		 * Object的equals方法就是用==比较的
		 * 所以若需要使用equals比较两个对象内容是否一致则需要重写该方法。
		 *
		 * Object的equals内部就是使用“==”比较的，所以若不重写就没有实际价值
		 *
		 */
		System.out.println(p.equals(p1));

		//Java API 提供的类都重写了toString方法，String也重写了toString 返回自身
		String string="fdafd";
		System.out.println(string);
	}

	class Person {
		private int age;
	}
}


//使用该类测试Object相关方法的重写。该类的每一个实例表示直角坐标系上的一个点
//使用该类的实例作为集合中的元素对集合进行排序
class Point implements Comparable<Point>{
	private int x;
	private int y;

	public Point() {
		super();
	}
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	/*
	 * get()和set() 设定的目的：
	 * 数据私有化，行为公开化，数据私有化之后，无法主动访问数据，
	 * 为了变被动为主动获取和设值，java设定了get()和set()的方法
	 * 具体方式get/set+大写变量名字,get方法返回变量类型，set方法不返回值
	 */
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 重写toString
	 * toString返回的字符串格式和内容并没有具体要求，可以根据将来实际开发中的需求而定。但是原则上应当包含当前类的属性信息
	 * 可以直观看到当前对象需要知道的属性值。
	 */
	public String toString(){
		return "(" + x + "," + y + ")";
	}

	/**
	 * 重写Object提供的equals方法
	 * 该方法的设计目的是比较当前对象与给定对象的内容是否一样，即:两个对象"像不像"
	 * 而==比较时，是比较是否为同一个对象，所以作用不同。
	 * equals在比较内容是，就是比较两个对象的属性值是否一样，但不要求必须所有属性都相同。这可以根据实际需求而定。
	 *
	 * equals方法的作用是判断当前对象与给定对象的内容是否一致。
	 * 该方法并不要求所有属性都已样才可以，这个要结合实际需求而定。 注意三点要素 前二点重写都一样 第三点看具体需求
	 *
	 * 通常，当我们需要使用一个类的equals方法时，就应当重写它。
	 * equals方法的作用是比较当前对象与给定对象内容是否一样(长的像不像)。
	 * Object提供的equals方法内部就是用"=="实现的，所有没有意义。
	 * equals比较时不一定要求所有属性值都要相同，这要结合实际需要而定。
	 *
	 * 具体重写格式：
	 * 	if(o == null){
			return false;
		}
		if(o == this){
			return true;
		}
		if(o instanceof XXX){
			XXX x = (XXX)o;
			return ...;
		}
		return false;

		只有自己定义的类需要重写，Java API提供的类基本上都重写了equals方法
	 */
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(o == this){
			return true;
		}
		if(o instanceof Point){
			Point p = (Point)o;
			return this.x==p.x&&this.y==p.y;
		}
		return false;
	}
	/**
	 * 当实现了Comparable接口后，需要重写下面的方法，该方法的作用是定义当前对象与给定参数对象比较大小的规则。
	 *
	 * 该方法的作用是用于判断当前对象this与参数对象o比较大小。
	 * 返回值为一个int支，不关注具体取值，只关注取值范围:
	 * 当返回值>0:当前对象大于参数对象
	 * 当返回值<0:当前对象小于参数对象
	 * 当返回值=0:两个对象相等
	 */
	@Override
	public int compareTo(Point o) {
		//比较规则，点到原点的距离长的点大
		int len = this.x*this.x+this.y*this.y;
		int olen = o.x*o.x+o.y*o.y;
		return len-olen;
	}
}