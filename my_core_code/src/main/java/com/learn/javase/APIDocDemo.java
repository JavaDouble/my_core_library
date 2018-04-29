package com.learn.javase;
//文档注释的演示 Java中的注释分为三类：当行注释：// 多行注释:/**/ 文档注释:/***/
/**
 * 文档注释是功能性注释，只定义在三个地方，分别是类，常量，方法上。
 * 类上定义，是用来说明当前类的功能
 * 文档注释最终可以javadoc命令所解释，然后生成一个文档手册。
 * @version 1.0 2018/02/28
 * @see java.lang.String
 * @since JDK1.0
 * @author Double
 *
 */
//选中，右键，export,Java-Javadoc
public class APIDocDemo {
	/**
	 * 该常量是在sayHello中的问候语
	 */
	public static final String INFO="你好！";
	/**
	 * 该方法是为给定用户添加一个问候语
	 * @param name
	 * 			该参数指定向谁打招呼
	 * @return
	 * 			返回打招呼的字符串
	 */
	public String sayHello(String name){
		return INFO+name;
	}
}
