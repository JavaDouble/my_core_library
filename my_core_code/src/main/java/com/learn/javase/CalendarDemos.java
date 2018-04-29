package com.learn.javase;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

//Java中的日期操作--Calendar类的常用方法演示
/**
 * java.util.Calendar 日历类
 * 日历类是用来操作时间的API,Calendar是一个抽象类，规定了操作时间的相关方法。
 * 常用的实现类:GregorianCalendar 即:阳历历法(格里高利历)，使用	日历类是为了对时间进行相关的操作
 * 比如计算时间
 *
 * @author Double
 *
 */
public class CalendarDemos {

	/**
	 * Calendar的getInstance()，getTime()，setTime()方法
	 * getInstance()创建当前系统时间
	 * getTime()把Calendar->Date setTime()把Calendar->Date
	 */
	@Test
	public void test1() {
		/*
		 * Calendar提供了一个静态方法getInstance() 默认创建的日历实例表示当前系统时间
		 * 可以获取一个当前系统所在地区适用的实现类,大部分地区获取的都是阳历历法实现类
		 */
		Calendar calendar= Calendar.getInstance();
		/*
		 * Calendar的实现类都重写了toString，只是直观看不出具体日期，不能直接反映表示的日期
		 * 需要转换成Date。
		 */
		System.out.println(calendar);
		System.out.println("获取Calendar的实现类："+calendar.getClass().getName());
		System.out.println("获取Calendar的实现类："+calendar.getClass().getCanonicalName());
		System.out.println(calendar.getTimeInMillis());
		System.out.println(calendar.getTime());
		System.out.println(calendar.getTimeZone());

		/*
		 * Calendar->Date
		 * 使用Calendar提供的方法:Date getTime() 该方法会将当前Calendar表示的日期以一个Date类型实例返回。
		 */
		Date date = calendar.getTime();
		System.out.println("当前系统时间:"+date);
		Date dt=new Date();
		dt.setTime(calendar.getTimeInMillis());
		System.out.println("当前系统时间:"+date);

		//表示明天这一时刻
		date.setTime(date.getTime()+1000*60*60*24);
		System.out.println("明天此刻时间："+date);

		/*
		 * Date->Calendar
		 * Calendar提供的方法:void setTime(Date date) 该方法可以使当前Calendar表示给定的Date所表示的日期
		 */
		calendar.setTime(date);//表示明天此刻时间，不可读
	}

	/**
	 * Calendar提供了一个方法:针对不同的时间分量单独设置值的方法。
	 * void set(int field,int value)
	 * 其中第一个参数为：时间分量,例如：年,月,日,时,分,秒等等,第二个参数为对应的值。
	 * 对给定的时间分量是一个int值，设置一个指定的值时间分量对应的是Calendar提供的常量。
	 *
	 */
	@Test
	public void test2() {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		/*
		 * 2008-08-08 20:08:08
		 */
		// 设置年
		calendar.set(Calendar.YEAR, 2008);

		/*
		 * 设置月
		 * 月的值在Calendar中也有常量对应 若直接使用数字，则:0表示1月，1表示2月,....
		 * 或者可以使用具体月的常量
		 */
		//calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.MONTH, Calendar.AUGUST);

		/*
		 * 设置日
		 * 天有多个时间分量: DATE:月中的天 DAY_OF_MONTH:月中的天，与DATE一致。 DAY_OF_WEEK:周中的天,星期几。
		 * DAY_OF_YEAR:年中的天。
		 */
		//calendar.set(Calendar.DATE, 8);//也可以这样写
		calendar.set(Calendar.DAY_OF_MONTH, 8);
		System.out.println(calendar.getTime());

		/*
		 * 时分秒 毫秒
		 */
		calendar.set(Calendar.HOUR_OF_DAY, 20);//24小时制的时间
		//calendar.set(Calendar.HOUR, 20);//12小时制的时间
		calendar.set(Calendar.MINUTE, 8);
		calendar.set(Calendar.SECOND, 8);
		//calendar.set(Calendar.MILLISECOND, 8);//毫秒
		System.out.println(calendar.getTime());

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		System.out.println(calendar.getTime());
	}

	/**
	 * 获取一个Calendar表示的日期中各个时间分量对应的值
	 * 获取指定时间分量所对应的值 int get(int field)
	 *
	 */
	@Test
	public void test3() {
		Calendar calendar = Calendar.getInstance();
		//获取年月日
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		System.out.println(year + "-" + month + "-" + day);

		//获取时分秒
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		System.out.println(h + ":" + m + ":" + s);

		//今天过了多少天
		int doy = calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println("今天是今年的第:" + doy + "天");

		//获取星期几
		int dow = calendar.get(Calendar.DAY_OF_WEEK);
		//每周的第一天是星期日，在美国人眼中。
		System.out.println("星期" + dow);
		//更改
		System.out.println("星期" + (dow==1?7:dow-1));
		String[] data = { "日", "一", "二", "三", "四", "五", "六" };
		System.out.println("星期" + data[dow - 1]);
	}

	/**
	 * 获取给定时间分量所允许的最大值  参照时间为当前Calendar表示的日期
	 * 两个常用的时间分量：DAY_OF_YEAR(365,366),DAY_OF_MONTH(28天,30天,31天)
	 */
	@Test
	public void test4() {
		Calendar calendar = Calendar.getInstance();
		//当月有多少天？
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("本月有"+days+"天");

		//查看今年2月有多少天？
		calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
		System.out.println("今年2月有："+calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+"天");

		//当年有多少天？
		days = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		System.out.println("今年有"+days+"天");

	}

	/**
	 * Calendar中计算时间的方法
	 * void add(int field,int value)
	 * 对指定的时间分量加上给定的值，若该值为负数，则是减去给定的值。
	 *
	 */
	@Test
	public void test5() {
		Calendar calendar = Calendar.getInstance();
		/*
		 * 查看3年2个月零25天以后的那周的周五 是哪天?
		 */
		//加上3年
		calendar.add(Calendar.YEAR, 3);
		System.out.println(calendar.getTime());
		//加上2个月
		calendar.add(Calendar.MONTH, 2);
		System.out.println(calendar.getTime());
		//加上25天
		calendar.add(Calendar.DAY_OF_YEAR, 25);
		System.out.println(calendar.getTime());

		System.out.println("3年2个月零25天以后:"+calendar.getTime());

		calendar.set(Calendar.DAY_OF_WEEK, 6);
		System.out.println(calendar.getTime());

		//两年前
		calendar.add(Calendar.YEAR, -2);
		System.out.println(calendar.getTime());
	}

	/**
	 * 实际开发中，Java日期时间流程
	 */
	@Test
	public void test6() {
		/*
		 * 当遇到类似开发需求：
		 * 首先.要求用户输入一个日期，一般是显示出日历，让用户点选，然后确定，这样是为了防止用户输入不标准的日期，
		 * 不容易处理，减少不必要的麻烦。
		 * 其次.获取用户确定后的日期，然后对该日期进行一系列的的操作运算，再将计算后的日期按照公司规定的格式显示给用户。
		 * 具体流程如下：
		 * 1.获取用户输入的日期字符串
		 * 2.使用SimpleDateFormat将其转换为Date
		 * 3.创建	一个Calendar，使其表示Date表示的日期
		 * 4.使用Calendar根据需求计算时间
		 * 5.将Calendar转换为一个Date
		 * 6.使用SimpleDateFormat将Date转换为字符串后显示给用户
		 */
	}

}
