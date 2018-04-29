package com.learn.javase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

//Java中的日期操作--SimpleDateFormat类的常用方法演示
/**
 * java.text.SimpleDateFormat
 * 该类允许我们按照一个指定的日期格式在String与Date之间相互转换。
 *
 * @author Double
 *
 */
public class SimpleDateFormatDemos {

	/**
	 * Date——>String
	 * 将Date转换成指定格式的字符串  使用DateFormat中的 String format(Date date)方法
	 * DateFormat类中format方法源码：
	 * 	public final String format(Date date){
        	return format(date, new StringBuffer(),
                      DontCareFieldPosition.INSTANCE).toString();
    	}
    	DateFormat类是抽象类，继承Format类(Format也是抽象类)，
    	java.text.SimpleDateFormat继承DateFormat类

    	Date——>String
    	String format(Date date) format方法可以将给定的date对象表示的时间按照SimpleDateFormat指定的
    	日期格式转换成字符串.
	 */
	@Test
	public void test1() {
		//当前系统时间
		Date now = new Date();
		System.out.println(now);

		/*
		 * 2016-11-25 10:09:23
		 * yyyy-MM-dd HH:mm:ss
		 * 年月日
		 * y年：yyyy年——2016年	yy——16年  M月：MM月——01月 M月——1月(超过单数月会自动补成双月)
		 * d日：dd日——06日 当日——6日
		 * E星期：E-星期日(Sun) 		a AM或PM标识:a——下午(PM)
		 * 时分秒
		 * H小时(24小时) h小时(12小时) m(分钟) s(秒)
		 * a h时——下午12时 HH：mm:ss——12:45:33 hh(a):mm:ss——12(下午)：47:48
		 */
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//SimpleDateFormat sdf = new SimpleDateFormat("y年M月dd日 aH时mm分ss秒");
		//SimpleDateFormat sdf = new SimpleDateFormat("yy年M月dd日 aH时mm分ss秒");
		String str = sdf.format(now);
		System.out.println(str);

		sdf= new SimpleDateFormat("yyyy年-MM月-dd日 HH时:mm分:ss秒");
		str = sdf.format(now);
		System.out.println(str);

		sdf= new SimpleDateFormat("yyyy年M月dd日 E a h时mm分ss秒");
		str = sdf.format(now);
		System.out.println(str);

		sdf= new SimpleDateFormat("yyyy年M月dd日   ah时mm分ss秒   E");
		str = sdf.format(now);
		System.out.println(str);
	}

	/*
	 * 查看当前系统时间和16天5小时23分40秒以后的日期，显示格式：2016年05月25日 15:24:33
	 */
	@Test
	public void test2() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		System.out.println("当前系统时间："+sdf.format(date));

		//16天5小时23分40秒以后的日期
		long time=date.getTime();
		time+=1000*60*60*24*16;
		time+=1000*60*60*5;
		time+=1000*60*23;
		time+=1000*40;

		date.setTime(time);
		System.out.println("16天5小时23分40秒以后的日期:"+sdf.format(date));
	}

	/**
	 * String——>Date
	 * 将字符串按照给定的日期格式解析为Date 使用DateFormat中的 Date parse(String source)方法
	 * DateFormat类中parse方法源码：
	 *  public Date parse(String source) throws ParseException{
        	ParsePosition pos = new ParsePosition(0);
        	Date result = parse(source, pos);
        	if (pos.index == 0)
            	throw new ParseException("Unparseable date: \"" + source + "\"" ,
                	pos.errorIndex);
        return result;
    	}
    	DateFormat类是抽象类，继承Format类(Format也是抽象类)，
    	java.text.SimpleDateFormat继承DateFormat类

    	String——>Date
    	Date parse(String source)将给定的字符串按照SimpleDateFormat指定的日期格式解析成一个Date对象。

	 * @throws ParseException
	 */
	@Test
	public void test3() throws ParseException {
		String str = "1992-08-02 20:55:13";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String——>Date
		Date date = sdf.parse(str);
		System.out.println(date);
	}

	/**
	 * 编写一段代码 程序启动后要求用户在控制台输入自己的生日：格式：yyyy-MM-dd
	 * 例如：1992-08-20然后经过程序运算，输出到今天到止已经活了xxxx天以及距离10000天还有多少天。
	 * 生存10000天的纪念日是：yyyy-MM-dd
	 *
	 * @throws ParseException
	 *
	 */
	@Test
	public void test4() throws ParseException {
		System.out.println("请输入你的生日：(格式：yyyy-MM-dd)");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		//转换为Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(str);
		Date dt = new Date();
		long time = dt.getTime();
		//计算活了多少天？
		long day = (time-date.getTime())/1000/60/60/24;
		System.out.println("恭喜你！到今天为止已经活了"+day+"天！请继续保持！");
		sc.close();

		day=10000-day;
		System.out.println("距离10000天，还有"+day+"天!");

		//计算存活10000天日期
		time = date.getTime();
		time += 1000L*60*60*24*10000;
		date.setTime(time);
		System.out.println("达成10000天日期是："+sdf.format(date));
	}

}
