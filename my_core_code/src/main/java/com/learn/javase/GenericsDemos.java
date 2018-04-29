package com.learn.javase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//泛型的演示
public class GenericsDemos {

	@Test
	public void test1() {
		// 定义泛型类Gen的一个Integer版本
        Gen<Integer> intOb = new Gen<Integer>(88);
        intOb.showType();
        int i = intOb.getOb();
        System.out.println("value= " + i);
        System.out.println("----------------------------------");
        // 定义泛型类Gen的一个String版本
        Gen<String> strOb = new Gen<String>("Hello Gen!");
        strOb.showType();
        String s = strOb.getOb();
        System.out.println("value= " + s);
	}

	@Test
	public void test2() {
        // 定义类Gen2的一个Integer版本
        Gen2 intOb = new Gen2(new Integer(88));
        intOb.showTyep();
        int i = (Integer) intOb.getOb();
        System.out.println("value= " + i);
        System.out.println("---------------------------------");
        // 定义类Gen2的一个String版本
        Gen2 strOb = new Gen2("Hello Gen!");
        strOb.showTyep();
        String s = (String) strOb.getOb();
        System.out.println("value= " + s);
	}

	@Test
	public void test3() {
		List<? extends Fruit> list = new ArrayList<>();
		// list.add(new Apple());
		// list.add(new Orange());
		// list.add(new Fruit());
		list.add(null);

		list.contains(new Apple());
		list.indexOf(new Apple());

		Apple apple = (Apple) list.get(0);
	}

	@Test
	public void test4() {
        List<? super Apple> list = new ArrayList<>();
        list.add(new Apple());
        //list.add(new Fruit());

        Object apple = list.get(0);
	}

	/**
	 * 测试泛型的应用
	 * 提供者和使用者
	 * Point1111类只提供了int类型，当需求发生变更时，int类型无法满足需求，代码可复用率低。
	 * Point1112类只提供了Object类型，当需求发生变更时，1)需要强转 2)太灵活
	 * 这样问题出在，实际上开发中，提供者不能满足使用者的需求，需求是提供者提的，而不是使用者提的。
	 *
	 * 泛型就是变被动为主动，需要什么类型就提供什么类型，泛型就是这样产生的。
	 *
	 * 泛型 参数化类型 只对引用有效 Array不能泛型
	 * 泛型是JDK1.5以后推出的新特性，也称为"参数化类型"
	 * 即:将一个类中属性的类型，方法参数的类型以及方法返回值类型的定义权从当前类移交给了使用者。这样
	 * 可以更满足使用者的需求。
	 */
	@Test
	public void test5() {
		Point1111 p1 = new Point1111(1, 2);
		System.out.println("p1:" + p1);
		p1.setX(2);
		System.out.println("p1:" + p1);
		int x1 = p1.getX();
		System.out.println("x1:" + x1);

		Point1112 p2 = new Point1112(1.1, 2.2);
		System.out.println("p2:" + p2);
		p2.setX(2.2);
		System.out.println("p2:" + p2);
		double x2 = (double)p2.getX();
		System.out.println("x2:" + x2);

		Point1112 p3 = new Point1112("一", "二");
		p3.setX("二");
		String x3 = (String)p3.getX();
		System.out.println("p3:" + p3);
		System.out.println("x3:" + x3);

		Point1113<Integer> p111 = new Point1113<Integer>(1, 2);
		System.out.println("泛型后，p111:" + p111);
		p111.setX(2);
		System.out.println("泛型后，p111:" + p111);
		int x111 = p111.getX();
		System.out.println("泛型后，x111:" + x111);

		Point1113<Double> p112 = new Point1113<Double>(1.1, 2.2);
		System.out.println("泛型后，p112:" + p112);
		p112.setX(2.2);
		System.out.println("泛型后，p112:" + p112);
		double x112 = p112.getX();
		System.out.println("泛型后，x112:" + x112);

		Point1113<String> p113 = new Point1113<String>("一", "二");
		p113.setX("二");
		String x113 = p113.getX();
		System.out.println("泛型后，p113:" + p113);
		System.out.println("泛型后，x113:" + x113);
	}

	/**
	 * 泛型也是编译器认可而非虚拟机认可
	 * 当一个属性被声明为泛型类型时，其实际原型就是Object。当为泛型赋值时，编译器会检查实际值是否
	 * 符合泛型类型要求，不符合则编译不通过。当获取一个泛型的值时，编译器会补充强制类型转换代码，将Object
	 * 转换为泛型的指定类型。
	 */
	@Test
	public void test6() {
		/*
		 * 这里指定泛型的实际类型为Integer,但实际上，创建的Point1113对象中，x,y属性类型时Object，
		 * 这里只是应当将它当做Integer看待
		 */
		Point1113<Integer> p111 = new Point1113<Integer>(1, 2);
		//由于参数是T,这时编译器检查实际的值是否为Integer，若不是，则编译错误！可以传入基本类型，因为自动装箱。
		p111.setX(2);
//		p111.setX("12");//编译不通过，不是Integer值

		/*
		 * 编译后的class文件中有强制类型转换	int x1 = (Integer)p1.getX();
		 */
		int x111 = p111.getX();
		System.out.println("泛型后，x111:" + x111);

		/*
		 * 可以不指定泛型，不指定则使用原型Object
		 */
		Point1113 p112 = p111;
		p112.setX("一");
		String x112 = (String)p112.getX();
		System.out.println("x2:"+x112);

		x111 = p111.getX();//类造型异常 	面试题常见大坑之一
		System.out.println("x1:"+x111);//java.lang.ClassCastException 类转换异常
	}

}

//使用了泛型
class Gen<T> {
    private T ob; // 定义泛型成员变量

    public Gen(T ob) {
        this.ob = ob;
    }
    public T getOb() {
        return ob;
    }
    public void setOb(T ob) {
        this.ob = ob;
    }

    public void showType() {
        System.out.println("T的实际类型是: " + ob.getClass().getName());
    }
}

//没有使用泛型
class Gen2 {
    private Object ob; // 定义一个通用类型成员

    public Gen2(Object ob) {
        this.ob = ob;
    }

    public Object getOb() {
        return ob;
    }

    public void setOb(Object ob) {
        this.ob = ob;
    }

    public void showTyep() {
        System.out.println("T的实际类型是: " + ob.getClass().getName());
    }
}

//通配符和泛型上界和下界	1.上界< ? extends Class>
class Fruit{}
class Apple extends Fruit{}
class Orange extends Fruit{}

class Point1111{
	private int x;
	private int y;

	public Point1111() {
		super();
	}
	public Point1111(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
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
	@Override
	public String toString() {
		return "Point1111 [x=" + x + ", y=" + y + "]";
	}

}

class Point1112{
	private Object x;
	private Object y;

	public Point1112() {
		super();
	}
	public Point1112(Object x, Object y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Object getX() {
		return x;
	}
	public void setX(Object x) {
		this.x = x;
	}
	public Object getY() {
		return y;
	}
	public void setY(Object y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point1112 [x=" + x + ", y=" + y + "]";
	}

}
/*
 * 泛型：<T>不确定这个类型，使用者需要什么类型就提供什么类型，可以是数字和字母组合，大小写都可以，但是数字不能在前。
 * 建议都是T，E类型，大写，一般1个，最多2个，所以泛型叫做参数化类型。
 * Java建议在不确定变量类型时，使用泛型，泛型必须是引用类型，面向对象。
 * 泛型实际上解决的是类属性类型的定义从提供者身上转移到使用者身上，这样是开发更便捷，精准。
 */
class Point1113<T>{
	private T x;
	private T y;

	public Point1113() {
		super();
	}
	public Point1113(T x, T y) {
		super();
		this.x = x;
		this.y = y;
	}
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	public T getY() {
		return y;
	}
	public void setY(T y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point1113 [x=" + x + ", y=" + y + "]";
	}

}