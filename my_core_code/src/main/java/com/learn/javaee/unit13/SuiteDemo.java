package com.learn.javaee.unit13;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试套件的演示
 * @author Double
 *
 */
@RunWith(Suite.class)
//@Suite.SuiteClasses({JUnitDemos.class,LogDemos.class})
@Suite.SuiteClasses({JUnitDemos.class})
public class SuiteDemo {
	/*  测试套件类
	  * 测试套件是用来组织多个测试类一起运行的，使用 @RunWith注解
	  * 更改测试运行器为Suite.class，将要测试的类作为数组传入
	  * 到Suite.SuiteClasses({})中，测试套件类不能包含其他测试方法
	  *
	  * 测试套件也可以包含其他的测试套件，具体用法和包含多个测试类是一样的
	 */
}
