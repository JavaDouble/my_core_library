package com.learn.javase;

import org.junit.Test;

//包装类的演示
public class WrapClassDemos {

	/*
	 * java的8个基本类型：byte short int long float double char boolean
	 * 是以值的形式直接存在的，所以他们并没有面向对象的特性，自然也不继承自Object。所以不能使用多态。用Object的角度去接收
	 * 基本类型。
	 */
	@Test
	public void test1() {
		/*
		 * 基本类型->包装类
		 * Integer缓存一个字节的整数 -128-127超过会new Integer 2^8
		 * 可以直接new,也可以使用包装类的静态方法valueOf（推荐）
		 * Integer的valueOf会重用1个字节之内的整数包装类对象，超过直接new.
		 */
		/*
		 * 01111111+1 对于二进制第一数字永远是符号为 0代表正数，1代表负数
		 * 10000000
		 *
		 *  11111111+1
		 * 100000000
		 */
		Integer a = Integer.valueOf(-129);
		Integer b = Integer.valueOf(-129);
		System.out.println(a==b);//false
		System.out.println(a.equals(b));//true

		int i = a.intValue();
		System.out.println(i);

		double d = a.doubleValue();
		System.out.println(d);

		short s = b.shortValue();
		System.out.println(s);

		Integer k=new Integer(125);
		Integer j=new Integer(125);
		System.out.println(k==j);//false
		k=125;j=125;
		System.out.println(k==j);//true
	}

	/*
	 * 包装类
	 * 包装类的出现是为了解决基本类型不能直接参与面向对象开发的问题。
	 * 6个数字类型的包装类继承自Number。Number是一个抽象类，提供了可以在6个基本类型数字间转换的相关方法。
	 *
	 * 由于基本类型不能直接参与面向对象开发，所以java为8个基本类型分别定义了一个类，
	 * 使得基本类型可以以对象形式存在参与面向对象开发。
	 * 其中6个数字类型的包装类继承自Number，剩下两个	继承Object
	 * Number是一个抽象类，定义了一些抽象方法，要求每一个具体的包装类可以将其表示的数字以所有基本数字类型返回。
	 *
	 */
	@Test
	public void test2() {
		/*
		 * 基本类型->包装类
		 * Integer缓存一个字节的整数 -128-127超过会new Integer
		 * 可以直接new,也可以使用包装类的静态方法valueOf（推荐）
		 * Integer的valueOf会重用1个字节之内的整数包装类对象，超过直接new.
		 */
		Integer a = Integer.valueOf(-129);
		Integer b = Integer.valueOf(-129);
		System.out.println(a==b);//false
		System.out.println(a.equals(b));//true

		Integer i1 = new Integer(1);
		//推荐使用静态方法valueOf转换
		Integer i2 = Integer.valueOf(1);

		int i = i1.intValue();
		double d = i1.doubleValue();
		float f = i1.floatValue();

		/*
		 * 数字类型包装类有两个常用的常量MAX_VALUE,MIN_VALUE
		 * 分别表示的是该包装类对应的基本类型 的取值范围的最大值与最小值
		 */
		int imax = Integer.MAX_VALUE;
		System.out.println(imax);
		int imin = Integer.MIN_VALUE;
		System.out.println(imin);

		long lmax = Long.MAX_VALUE;
		System.out.println(lmax);
	}

	/*
	 * 包装类的最大值和最小值
	 */
	@Test
	public void test3() {
		Byte bmax=Byte.MAX_VALUE;
		Byte bmin=Byte.MAX_VALUE;
		System.out.println("bmax:"+bmax);
		System.out.println("bmin:"+bmin);

		Short smax=Short.MAX_VALUE;
		Short smin=Short.MIN_VALUE;
		System.out.println("smax:"+smax);
		System.out.println("smin:"+smin);

		Integer imax = Integer.MAX_VALUE;
		Integer imin = Integer.MIN_VALUE;
		System.out.println("imax:"+imax);
		System.out.println("imin:"+imin);

		Long lmax = Long.MAX_VALUE;
		Long lmin = Long.MIN_VALUE;
		System.out.println("lmax:"+lmax);
		System.out.println("lmin:"+lmin);

		Float fmax=Float.MAX_VALUE;
		Float fmin=Float.MIN_VALUE;
		System.out.println("fmax:"+fmax);
		System.out.println("fmin:"+fmin);

		Double dmax = Double.MAX_VALUE;
		Double dmin = Double.MAX_VALUE;
		System.out.println("dmax:"+dmax);
		System.out.println("dmin:"+dmin);

		Character cmax=Character.MAX_VALUE;
		Character cmin=Character.MIN_VALUE;
		System.out.println("cmax:"+cmax.toString());
		System.out.println("cmin:"+String.valueOf(cmin));

		Boolean b1=Boolean.FALSE;
		Boolean b2=Boolean.TRUE;
		System.out.println("b1:"+b1);
		System.out.println("b2:"+b2);

	}

	/*
	 * 包装类支持一个静态方法：parseXXX(String str)
	 * 可以将给定的字符串解析为对应的基本类型数据，前提是该字符串能正确描述基本类型可以保存的值
	 */
	@Test
	public void test4() {
		String iStr = "123";
		int i = Integer.parseInt(iStr);
		System.out.println(i+1);
		double d = Double.parseDouble(iStr);
		System.out.println(d);

		iStr = "123.123";
		i = Integer.parseInt(iStr);//NumberFormatException 数字格式异常
		System.out.println(i+1);
		d = Double.parseDouble(iStr);
		System.out.println(d);

		iStr = "a";
		i = Integer.parseInt(iStr);
		System.out.println(i+1);
		d = Double.parseDouble(iStr);
		System.out.println(d);
	}

	/**
	 *
	 * JDK 1.5之后推出了一个新的特性:
	 * 自动拆装箱
	 * 这个特性是编译器认可而不是虚拟机认可。
	 * 该特性实际上是编译器在编译源程序时帮我们补充代码来自动完成包装类与基本类型的转换工作。
	 *
	 * 编译器在编译源程序时发现基本类型与引用类型需要转换时会自动补全转换代码。
	 * 好处是程序员无需再关注基本类型与其他类型对应的包装类之间的转换工作
	 */
	@Test
	public void test5() {
		/*
		 * 将基本类型值赋给引用类型变量时，触发:
		 * 自动装箱特性
		 * 编译器会将代码补全为:Integer in = Integer.valueOf(123);以完成转换工作。
		 */
		Integer in = 123;
		/*
		 * 将引用类型赋值给基本类型变量会触发:
		 * 自动拆箱特性
		 * 下面的代码会被编译器补全代码为：int i = int.intValue()
		 */
		int i = new Integer(123);

		/*
		 * 自动拆装箱是编译器认可，而不是虚拟机认可。编译器在将源程序编译时自动补充代码来完成基本类型与包装类型之间的转换
		 * 下面代码触发了自动拆箱：在class文件中，下面的代码被编译改为：
		 * int ii=new Integer(1).intValue();
		 */
		int ii=new Integer(1);
		/*
		 * 下面代码触发了自动装箱：
		 * 在class文件中，下面代码被编译器改为：
		 * Integer iii=Integer.valueOf(123);
		 */
		Integer iii=123;

		/*
		 *触发自动拆箱，即：补全代码转为基本类型 下面的代码在编译后.class文件中的样子：
		 *int i=Integer.valueOf(123).intValue();
		 */
		i = Integer.valueOf(123);
		/*
		 *触发自动拆箱，即：补全代码转为基本类型 下面的代码在编译后.class文件中的样子：
		 *integer in=Integer.valueOf(123);
		 */
		in = 123;
		System.out.println(in.equals(i));//true
		System.out.println(i==in);//true
	}

	/**
	 * 自动拆箱，自动装箱
	 */
	@Test
	public void test6() {
		/**
		 * 当我们需要将基本类型转换为包装类时，我们可以调用包装类的一个静态方法valueOf():
		 */
		Integer ii=Integer.valueOf(1);
		Double dd=Double.valueOf(1.1);
		/**
		 * 当我们需要将包装类转换为基本类型时，我们可以使用包装类的方法xxxValue()方法
		 * （这里不同的包装类方法名不完全一致，但都是以Value结尾）:
		 */
		Integer i=new Integer(1);
		int n=i.intValue();

		Double d=new Double(1.1);
		double dn=d.doubleValue();
		/**
		 * 虽然我们可以通过上述的方法在基本类型与包装类之间相互转换。但是在实际编写代码时
		 * 相对还是比较麻烦的。java在1.5版本后推出了一个新的特性:自动拆装箱。
		 */
		int iii=new Integer(1);//可以自动将包装类转换为基本类型  自动拆箱
		Integer in=1;//可以自动将基本类型转换为包装类  自动装箱

		/**
		 * 事实上JVM不支持该特性，自动拆装箱只是编译器在编译过程中的"预处理"操作。编译器在
		 * 看到需要在包装类与基本类型之间转换时，会在编译为字节码时进行改变:源代码中
		 * 编译后的字节码中
		 */
		Integer a = 100; //=> Integer a = Integer.valueOf(100);装箱
		Integer b = 200; //=> Integer b = Integer.valueOf(200);装箱
		Integer c = a+b; //拆箱再装箱
		//=> Integer c=Integer.valueOf(a.intValue()+b.intValue( ));
		double ddd = c; 	//=> double d = c . doubleValue( );拆箱
		System.out.println(ddd);//300.0
	}

	/**
	 * 测试NUMBER的intValue方法和doubleValue方法
	 */
	@Test
	public void test7() {
		Number d=123.88;
		Number n=123;
		System.out.println(d.getClass().getName());//java.lang.Double
		System.out.println(n.getClass().getName());//java.lang.Integer
		//System.out.println(d+n);//编译错误 不能直接参与运算

		int intValue=d.intValue();
		double doubleValue=n.doubleValue();
		System.out.println(intValue+","+doubleValue);//123,123.0

		intValue=n.intValue();
		doubleValue=d.doubleValue();
		System.out.println(intValue+","+doubleValue);//123,123.88
	}

}
