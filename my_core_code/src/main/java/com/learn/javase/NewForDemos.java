package com.learn.javase;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

//增强型for循环的演示
/**
 * 增强for循环也称为：新循环，增强循环，for each
 * 新循环是JDK1.5以后推出的一个新特性,新循环仅用来遍历集合或数组元素使用
 *
 * JDK1.5以后增加的特性，都称之为心疼性(JDK1.5,1.6,1.7,1.8,1.9,1.10 也叫JDK5,6,7,8,9,10)，
 * 也会人叫JavaSE5,6,7,8,9,10，或者Java5,6,7,8,9,10，都是同一个意思。
 *
 * 新特性是为了方便开发，提高开发效率，是编译器帮助开发者增加代码后运行的。
 *
 * JDK1.5的新特性：Scanner,自动拆装箱，枚举(Enum),泛型(Generics),增强型for循环,
 *
 * @author Double
 *
 */
public class NewForDemos {

	/**
	 * 新循环遍历数组
	 */
	@Test
	public void test1() {
		String[] array = {"one","two","three","four","five"};
		for(int i=0;i<array.length;i++){
			String str = array[i];
			System.out.print(str+" ");
		}

		System.out.println();

		for(String str:array){
			System.out.print(str+" ");
		}
	}

	/**
	 * 新循环遍历集合
	 */
	@Test
	public void test2() {
		Collection c = new ArrayList();
		c.add("one");
		c.add("two");
		c.add("three");
		c.add("four");
		c.add("five");
		System.out.println(c);
		/*
		 * 新循环并非新的语法，意思是这是编译器认可的而非虚拟机认可。
		 * 编译器在编译源代码时若发现使用新循环遍历集合元素时会将代码改为使用迭代器遍历。
		 * 所以使用新循环遍历集合元素时不可以通过集合的方法增删元素，因为本质就是迭代器遍历.
		 * 相当于编译器：
		 * Iterator it=c.iterator();
		 * 	while(it.hasNext()){
		 * 		Object o=it.next();
				String str = (String)o;
				System.out.println(str);
				if("four".equals(str)){
					c.remove(str);
				}
			}
		 */
		for(Object o:c){
			String str = (String)o;
			System.out.println(str);
			if("four".equals(str)){
				c.remove(str);
			}
		}
	}

	@Test
	public void test3() {

	}


	@Test
	public void test4() {

	}

	@Test
	public void test5() {

	}

	@Test
	public void test6() {

	}

}
