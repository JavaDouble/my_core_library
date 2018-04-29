package com.learn.javase;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;




//JavaSE 面试题集锦 	温故知新计划：JavaSE最多7天，文档/笔记/Demos/练习/面试题
public class Interview {

	public  void a(B b) {
		a((B)new C());
	}
	public  void a(C c) {
	}

	@Test
	public void test001() {
		Interview a=new Interview();
		a.a((B)new C());//java.lang.StackOverflowError 栈溢出错误
	}

	/**
	 * 日期和时间
	 * 	1）如何取得年月日、小时分钟秒？
	 *	2）如何取得从1970年1月1日0时0分0秒到现在的毫秒数？
	 *	3）如何取得某月的最后一天？ 取得一年的最后一天?
	 *	4）如何格式化日期？
	 *	5)打印昨天的当前时刻?
	 *
	 */
	/**
	 * 1）如何取得年月日、小时分钟秒？
	 */
	@Test
	public void test002() {
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH)+1);
		System.out.println(calendar.get(Calendar.DATE));
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println(calendar.get(Calendar.MINUTE));
		System.out.println(calendar.get(Calendar.SECOND));
	}

	/**
	 * 2）如何取得从1970年1月1日0时0分0秒到现在的毫秒数？
	 */
	@Test
	public void test003() {
		System.out.println(Calendar.getInstance().getTimeInMillis());
		System.out.println(System.currentTimeMillis());
	}

	/**
	 * 3）如何取得某月的最后一天？ 取得一年的最后一天?
	 */
	@Test
	public void test004() {
		Calendar calendar = Calendar.getInstance();
		//获取当前系统时间中当月的最后一天(日所允许的最大值)
		int max=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("当月的最后一天为:"+max);
		int maxOfYear=calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		System.out.println("当年的最后一天为："+maxOfYear);
	}

	/**
	 * 4）如何格式化日期？
	 */
	@Test
	public void test005() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(Calendar.getInstance().getTime()));
		System.out.println(sdf.format(new Date()));
	}

	/**
	 * 5)打印昨天的当前时刻?
	 */
	@Test
	public void test006() {
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		System.out.println(calendar.getTime());
	}

	/**
	 * 数据结构
	 * 集合Collection,Set,List,Map,Deque,Queue 集合排序
	 * 相关笔试题
	 *
	 */
	/**
	 * 数组转换集合 使用数组的工具类Arrays的静态方法asList
	 * 需要注意，仅能转换为List集合
	 *
	 * 集合转换成数组
	 * Collection提供了方法toArray()可以将当前集合转换为数组
	 *
	 * java.util.List
	 * List是Collection的子类接口，其实现类都是可重复集，且有序。
	 * List提供了一组支持使用下标操作元素的方法。Set集合不具备这些方法。
	 * 常用实现类：ArrayList,LinkedList
	 * ArrayList:内部由数组实现，查询快 LinkedList:内部由链表实现，
	 * 首尾增删快。
	 */
	@Test
	public void test007() {
		String[] arr={"user","notebook","note"};
		List<String> list=Arrays.asList(arr);
		for(String str:list){
			System.out.print(str+",");
		}
		arr=list.toArray(new String[] {});
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Collection提供了统一的遍历集合元素的方式：迭代器模式
	 * java.util.Iterator是迭代器的接口
	 * Collection提供的方法：Iterator iterator()
	 * 该方法会返回一个可以遍历当前集合元素的迭代器实现类。我们无须记住每个迭代器实现类的名字。
	 * 只将其看待为是迭代器Iterator即可。
	 * 迭代器遍历集合元素遵循：问，取，删的步骤 其中删除元素不是必须操作。
	 *
	 */
	@Test
	public void test008() {
		String[] arr={"user","notebook","note"};
		List<String> list=Arrays.asList(arr);
		Iterator<String> it=list.iterator();
		while(it.hasNext()){
			String str=it.next();
			System.out.print(str+",");
		}
	}

	/**
	 * java.util.Map
	 * 查找表，常用的实现类：HashMap(散列表)
	 * Map的每一个元素分为两部分：[key,value](键值对),其中key在Map中不允许重复（equals判断 unique唯一的）
	 *
	 *
	 *
	 * Map的遍历 遍历Map有三种方式：
	 * 1.遍历所有的key
	 * 2.遍历每一组键值堆（Entry）
	 * 3.遍历所有的value(相对不常用)
	 */
	@Test
	public void test009() {
		Map<String,Integer> map=new HashMap<String, Integer>();
		map.put("语文", 120);
		map.put("英文", 135);
		map.put("数学", 145);
		for(Integer score:map.values()){
			System.out.println(score);
		}
	}

	/**
	 * String相关的笔试题
	 *
	 * 写一个方法，实现字符串的反转
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		int length = str.length();
		StringBuffer result = new StringBuffer(length);
		//System.out.println(result);
		for (int i = length - 1; i >= 0; i--)
			result.append(str.charAt(i));
		return result.toString();
	}

	@Test
	public void test010() {
		System.out.println(reverse("abc"));
	}

	/*
	 * byte[] getBytes()
	 * 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
	 * getBytes(Charset charset)
	 * 使用给定的 charset 将此 String 编码到 byte 序列，并将结果存储到新的 byte 数组。
	 *
	 */
	@Test
	public void test011() throws UnsupportedEncodingException {
		String str="jfkdjalfjla3uoi2";
		byte[] bytes=str.getBytes();
		System.out.println(Arrays.toString(bytes));
		//[106, 102, 107, 100, 106, 97, 108, 102, 106, 108, 97, 51, 117, 111, 105, 50]
		bytes=str.getBytes("utf-8");
		System.out.println(Arrays.toString(bytes));
		//[106, 102, 107, 100, 106, 97, 108, 102, 106, 108, 97, 51, 117, 111, 105, 50]
	}


	/**
	 * 约瑟夫环：已知n个人(以编号1，2，3...n分别表示)围坐在一张圆桌周围。从编号为k的人开始报数，数到m的那个人出列;
	 * 他的下一个人又从1开始报数，数到m的那个人又出列;依此规律重复下去，直到圆桌周围的人全部出列。
	 */
	public static void yuesefu(int totalNum, int countNum) {
		// 初始化人数
		List<Integer> start = new ArrayList<Integer>();
		for (int i = 1; i <= totalNum; i++) {
			start.add(i);
		}
		// 从第K个开始计数
		int k = 0;
		while (start.size() > 0) {
			k = k + countNum;
			// 第m人的索引位置
			k = k % (start.size()) - 1;
			// 判断是否到队尾
			if (k < 0) {
				System.out.println(start.get(start.size() - 1));
				start.remove(start.size() - 1);
				k = 0;
			} else {
				System.out.println(start.get(k));
				start.remove(k);
			}
		}
	}

	@Test
	public void test012() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入总人数：");
		int totalNum = scanner.nextInt();
		System.out.print("请输入报数的大小：");
		int cycleNum = scanner.nextInt();
		yuesefu(totalNum, cycleNum);
		scanner.close();
	}

	/**
	 * 编写一个截取字符串的方法，输入一个字符串和字节数，
	 * 输出按字节截取的字符串,
	 * 但是要保证汉字不被截半个，如“我ABC, 4，应该截为“我AB”，
	 * 输入“我ABC汉DEF”，6，应该输出为“我ABC”而不是“我ABC+汉的半个。
	 */
	public static String mySplit(String src,int num) {
		byte[] b=src.getBytes();
		String str=new String(b, 0, num);
		if(!src.startsWith(str)){
			str=new String(b, 0, num-1);
		}
		return str;
	}
	@Test
	public void test013() {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个字符串:");
		String src=sc.nextLine();
		System.out.println("请输入字节数:");
		Integer num=Integer.parseInt(sc.nextLine());
		System.out.print(mySplit(src, num));
		sc.close();
	}

	/**
	 * 去除ArrayList中的重复元素，
	 *
	 */
	@Test
	public void test014() {
		List<String> aList=new ArrayList<String>();
		aList.add("abc");
		aList.add("ab");
		aList.add("abc");
		aList.add("bc");
		aList.add("abc");
		aList.add("bc");
		System.out.println(aList);
		//第一种方法  创建一个新的集合 把不重复的元素装进新集合 清空原集合 添加新集合到原集合中
/*		List<String> list=new ArrayList<String>();
		for(int i=0;i<aList.size();i++){
			if(!list.contains(aList.get(i))){
				list.add(aList.get(i));
			}
		}
		aList.clear();
		aList.addAll(list);
		*/
		//第二种方式 set集合是不重复集合 添加到创建一个set集合中  清空原集合 添加set集合到元集合中
/*		Set<String> set=new HashSet<String>(aList);
		aList.clear();
		aList.addAll(set);*/
		//第三种方式1 for循环嵌套
/*		for(int i=0;i<aList.size();i++){
			for(int j=i+1;j<aList.size();j++){
				if(aList.get(j).equals(aList.get(i))){
					aList.remove(j);
				}
			}
		}*/
		//第三种方式2 for循环嵌套
		for(int i=0;i<aList.size();i++){
			for(int j=aList.size()-1;j>i;j--){
				if(aList.get(j).equals(aList.get(i))){
					aList.remove(j);
				}
			}
		}
		System.out.println(aList);
	}

	/**
	 *  有一个文本文件，用以下格式纪录了每个学生的考试成绩：
		Jam     语文  90   数学 95   英语99    计算机 80
		Steven  语文  100   数学90   英语90    计算机90
		Roger   语文 80    数学100   英语90    计算机100
		请写一段程序，从文本中读取出每个学生的成绩，实现以下功能：
		1) 计算每个学生的总分和平均分，并按照总分由高到低进行排序，
		将排序好的数据输出到student_score.txt中，每一行一条纪录。
		2) 对每个科目按照分数由高到低进行排序，并将排序号的数据输出到相应的文本文件中
		，每一行一条纪录，在最后一行给出平均分。
	 */
	@Test
	public void test015() {

	}

}
class B{

}
class C extends B{

}