package com.learn.javase;

import java.util.Date;

import org.junit.Test;

//Java中的日期操作--Date类的常用方法演示
/**
 * Date现在仅仅用它表示时间，其他都不在使用。
 * Date类设计存在缺陷，缺陷主要体现在两点:1)千年虫问题 2)没有考虑时区问题
 *
 * java.util.Date
 * Date的每一个实例用于表示一个具体的日期
 * 内部维护一个long值，该值为1970年1月1日 00:00:00到其表示的日期之间所经过的毫秒值。
 * 正数是70年以后的日期，负数是70年以前的日期。
 * 由于Date存在设计缺陷:千年虫，时区问题，所以Date仅用于表示日期，而不再对日期进行操作。
 * 从JDK1.2开始，Date中大部分方法被设置为"过时的"，不再建议使用。
 *
 * @author Double
 *
 */
public class DateDemos {

	@Test
	public void test1() {
		//默认创建即表示当前系统时间 使用默认构造方法创建的Date表示当前系统时间。
		Date now = new Date();
		/*
		 * Date重写了toString，返回的字符串显示的就是当前Date表示的时间。
		 * 格式：Sat Mar 03 22:10:39 CST 2018
		 * 输出的日期格式对于非英语地区而言不是很友好
		 */
		System.out.println(now);

		now.getYear();//被设置为"过时的方法"，不再建议使用。Date的大部分方法不建议使用了

		/*
		 * 获取Date内部维护的long值
		 */
		long time = now.getTime();
		System.out.println("time:"+time);
		//明天这一刻的毫秒值
		time += 1000*60*60*24;
		/*
		 * void setTime(long time)设置一个long值 是Date表示该long值所表示的时间
		 * 将long值设置到Date中使其表示其日期
		 */
		now.setTime(time);
		System.out.println(now);
	}

	@Test
	public void test2() {

	}

	@Test
	public void test3() {

	}


}
