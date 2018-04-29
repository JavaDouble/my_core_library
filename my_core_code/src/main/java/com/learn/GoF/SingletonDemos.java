package com.learn.GoF;

//23种常见设计模式之单例模式
/**
 * 基础设计模式(GoF)
 * 计模式分为三种类型，共23种。
 *  创建型模式：单例模式、抽象工厂模式、建造者模式、工厂模式、原型模式。5种
 *  结构型模式：适配器模式、桥接模式、装饰模式、组合模式、外观模式、享元模式、代理模式。7种
 *  行为型模式：模版方法模式、命令模式、迭代器模式、观察者模式、中介者模式、备忘录模式、
 *     解释器模式（Interpreter模式）、状态模式、策略模式、职责链模式(责任链模式)、访问者模式。11种

 * 单例模式(Singleton)是最基本的设计模式之一，许多的项目中都会见到它的身影。
 * 然而，虽然单例模式概念简单，但要真正实现得好就不那么容易了。
 *
 * 定义：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * 单例模式(饿汉模式和懒汉模式)
 * @author Double
 *
 */
public class SingletonDemos {
	public static void main(String[] args) {
		Girl g=Girl.getOne();
		Girl g1=Girl.getOne();
		System.out.println(g==g1);
	}
}

/**
 * 懒汉模式(懒惰) 调用时，对象没有存在		双重校验锁
 * @author Double
 *
 */
class Boy{
	private volatile static Boy one;
	private Boy() {
	}
	/**
	 * 先读后写会出现并发问题 加synchronized
	 * @return
	 */
	public synchronized static Boy getOne() {
		if(one==null){
			one=new Boy();
		}
		return one;
	}
}

/**
 * 饿汉模式(非懒惰) 性能最优 调用时，对象已存在
 * @author Double
 *
 */
class Girl{
	private static Girl one=new Girl();
	private Girl() {
	}
	public static Girl getOne() {
		return one;
	}
}