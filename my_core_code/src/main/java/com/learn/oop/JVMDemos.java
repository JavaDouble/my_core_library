package com.learn.oop;

import javax.swing.JFrame;

//对象内存管理的演示
/**
 * JVM内存管理
 *  1)在JAVA中，有java程序、虚拟机、操作系统三个层次，其中java程序与虚拟机交互，而虚拟机与操作系统交互。
 *  	编译好的java字节码文件运行在JVM中。
 *  2)程序中无论代码还是数据，都需要存储在内存中，而java程序所需内存均由JVM进行管理分配，开发者只需关心JVM是如何管理
 *  	内存的，而无需关注某种操作系统是如何管理内存的，这就保证了java程序的平台无关性。
 *  3)JVM会将申请的内存从逻辑上划分为三个区域：堆、栈、方法区。这三个区域分别用于存储不同的数据。
 *
 * 1.1 堆内存
 *	1.1.1  对象存储在堆中
 *	 1)JVM在其内存空间开辟了一个称为“堆”的存储空间  2)这部分空间用于存储使用new关键字所创建的对象。
 *	1.1.2  成员变量的生命周期
 *	  1)访问对象需要依靠引用变量
 *	  2)当一个对象没有任何引用时，被视为废弃的对象，属于被回收的范围。该对象的所有成员变量也随之被回收。
 *	  3)成员变量的生命周期为：从对象在堆中创建(new)开始到对象从堆中被回收(没有任何引用指向)结束。
 * 	1.1.3  垃圾回收机制
 *	  1)垃圾回收器（Garbage Collection，GC）是JVM自带的一个线程（自动运行着的程序），用于回收没有任何引用所指向的对象。
 * 	  2)GC线程会从栈中的引用变量开始跟踪，从而判定哪些内存是正在使用的，若GC无法跟踪到某一块堆内存，
 * 		那么GC就认为这块内存不再使用了，即为可回收的。但是，java程序员不用担心内存管理，因为垃圾收集器会自动进行管理。
 *  1.1.4 Java程序的内存泄漏
 *    1)内存泄露是指，不再被使用的内存没有被及时的回收，严重的内存泄露会因过多的内存占用而导致程序的崩溃。
 *    	在程序中应该尽量避免不必要的内存浪费。
 *    2)GC线程判断对象是否可以被回收的依据是该对象是否有引用来指向，因此，当确定该对象不再使用时，
 *    	应该及时的将其引用设置为null，这样，该对象即不再被引用，属于可回收的范围。
 *  1.1.5 System.gc()方法
 *   1)GC的回收对程序员来说是透明的(看不到的)，并不一定一发现有无引用的对象就立即回收。
 *   2)一般情况下，当我们需要GC线程即刻回收无用对象时，可以调用System.gc（）方法。
 *   3)此方法用于建议JVM马上调度GC线程回收资源，但具体的实现策略取决于不同的JVM系统。
 *
 * 1.2 非堆-栈
 *	1.2.1  栈用于存放方法中的局部变量
 *	  1)JVM在其内存空间开辟一个称为”栈”的存储空间  	2)这部分空间用于存储程序运行时在方法中声明的所有的局部变量
 *	  	方法中的变量即为局部变量，是在栈内存中分配，若变量为值类型，则在栈中存储的就是该变量的值。
 *		若变量为引用类型，则在栈中存储的是堆中对象的地址。
 *	1.2.2  成员变量和局部变量
 *	   成员变量与局部变量的差别如下：
 *		局部变量：1) 定义在方法中；2) 没有默认值，必须自行设定初始值；
 *				3) 方法被调用时，存在栈中，方法调用结束时局部变量从栈中清除；
 *		成员变量：1) 定义在类中，方法外；2) 由系统设定默认初始值，可以不显式初始化；
 *				3) 所在类被实例化后，存在堆中，对象被回收时，成员变量失效；
 *  1.2.3 局部变量的生命周期
 *	  1)一个运行的Java程序从开始到结束会有多次方法的调用。JVM会为每一个方法的调用在栈中分配一个对应的空间，
 *		这个空间称为该方法的栈帧。
 *	  2)一个栈帧对应一个正在调用中的方法，栈帧中存储了该方法的参数、局部变量等数据。当某一个方法调用完成后，
 *		其对应的栈帧将被清除，局部变量即失效。
 *
 * 1.3 非堆-方法区
 *	1.3.1 方法区用于存放类的信息
 *    1)方法区用于存放类的信息，Java程序运行时，首先会通过类装载器载入类文件的字节码信息，经过解析后将其装入方法区。
 *    	类的各种信息（包括方法）都在方法区存储。
 *	1.3.2 方法只有一份
 *	  1)当类的信息被加载到方法区时，除了类的类型信息以外，同时类内的方法定义也被加载到方法区；
 *	  2)类在实例化对象时，多个对象会拥有各自在堆中的空间，但所有实例对象是共用在方法区中的一份方法定义的。意味着，方法只有一份。
 *	  3)当引用代表的对象调用方法区的方法时，是通过this关键字来区别是那个对象调用的。
 *
 * @author Double
 *
 */
public class JVMDemos {
	public static void main(String[] args) {
	/*	Cell cell=new Cell();
		//new Cell（）所创建的对象在堆中分配，同时成员变量亦在此分配，并赋初始值为零。引用类型变量c在栈内存中分配，
		//其中保存的数据，为对象在堆内存中的地址信息，假设对象在堆内存的地址为40DF，则c中保存的即是40DF。

		cell=null;//当将c赋值为null时，表示c不再指向刚刚分配的对象空间，此时成员变量失效。

		System.gc();

		int num=5;//存放在栈中
		//方法中的变量即为局部变量，是在栈内存中分配，若变量为值类型，则在栈中存储的就是该变量的值。若变量为引用类型，
		//则在栈中存储的是堆中对象的地址。

		JFrame f1 = new JFrame();
		JFrame f2 = new JFrame();
		f1.setSize(200, 300);
		f2.setSize(300,400);
		//对象有两个，但是setSize方法只有一份，分别针对f1指向的对象和f2指向的对象调用了两次。
*/	}
}