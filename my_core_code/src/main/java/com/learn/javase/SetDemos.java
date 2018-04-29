package com.learn.javase;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
//Set集合常用方法的演示
/**
 * java.util.Set:不可重复集，大部分实现类是无序的，元素是否重复是依靠元素自身equals比较的结果。
 * 常用实现类HashSet，TreeSet，LinkedHashSet
 *
 * @author Double
 *
 */
public class SetDemos {

	@Test
	public void test1() {
		Set<String> set=new HashSet<String>();
		set.add("Java");
		set.add("Php");
		set.add("JavaScript");
		set.add("Java");
		System.out.println(set);

		System.out.println("set集合大小："+set.size());

		boolean b=set.contains("Java");
		System.out.println(b);

		b=set.remove("Java");
		System.out.println(b);
		System.out.println(set);

	}

	/**
	 * TreeSet是通过红黑树实现的
	 */
	@Test
	public void test2() {
		Set<String> set=new LinkedHashSet<String>();
		set.add("Java");
		set.add("Php");
		set.add("JavaScript");
		set.add("Java");
		System.out.println(set);

		System.out.println("set集合大小："+set.size());

		boolean b=set.contains("Java");
		System.out.println(b);

		b=set.remove("Java");
		System.out.println(b);
		System.out.println(set);

	}

	@Test
	public void test3() {
		Set<String> set=new TreeSet<String>();
		set.add("Java");
		set.add("Php");
		set.add("JavaScript");
		set.add("Java");
		System.out.println(set);

		System.out.println("set集合大小："+set.size());

		boolean b=set.contains("Java");
		System.out.println(b);

		b=set.remove("Java");
		System.out.println(b);
		System.out.println(set);
	}

}
