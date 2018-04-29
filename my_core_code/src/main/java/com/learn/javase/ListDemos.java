package com.learn.javase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

//List集合常用方法的演示
/**
 * java.util.List
 * List是Collection的子类接口，其实现类都是可重复集，且有序，特点是可以根据下标操作元素。
 * List提供了一组支持使用下标操作元素的方法。Set集合不具备这些方法。
 *
 * 常用实现类:ArrayList,LinkedList
 * ArrayList:内部由动态数组实现，查询快。
 * LinkedList:内部由链表实现，通过链表的指针，实现首尾呼应，首尾增删更快。
 *
 * 链表是由三部分组成左中右，中间存放元素，左右为指针，第一元素的右指针指向第二个元素的左指针，
 * 第一元素的左指针指向最后一个元素的右指针，链表通常闭合，形成循环。
 *
 * Stack, Vector也实现了List集合
 * @author Double
 *
 */
public class ListDemos {

	@Test
	public void test1() {
		List<String> list = new ArrayList<String>();
		//List<String> list = new ArrayList<String>(1000);
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");

		/*
		 * E get(int index) 获取指定下标对应的元素 从0开始
		 */
		String str = list.get(1);
		System.out.println(str);
		//System.out.println(list.get(4)); //下标越界
		System.out.println("集合汇总最后一个元素："+list.get(list.size()-1));

		//传统循环遍历List集合
		for (int i = 0; i < list.size(); i++) {
			str = list.get(i);
			System.out.println(str);
		}
		/*
		 * E set(int index,E e) 将给定元素设置到指定位置，返回值为原位置对应的元素。所以是替换元素操作。
		 * [one,two,three,four]		[one,2,three,four]
		 */
		String old = list.set(1, "2");
		System.out.println(list);
		System.out.println(old);

		list.set(4, "five");//java.lang.IndexOutOfBoundsException 下标越界
	}

	/**
	 * List提供了一对重载的add,remove方法
	 *
	 */
	@Test
	public void test2() {
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		/*
		 * void add(int index,E e) 向集合指定位置插入元素。
		 * [one,two,2,three,four]
		 */
		list.add(2, "2");
		System.out.println(list);

		/*
		 * E remove(int index) 从集合中删除并返回指定位置处的元素
		 * [one,2,three,four]
		 */
		String old = list.remove(1);
		System.out.println(list);
		System.out.println(old);
	}

	/**
	 * List提供了获取子集的方法
	 * List subList(int start,int end)	获取指定范围内的子集  同样含头不含尾[)
	 *
	 */
	@Test
	public void test3() {
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		System.out.println("集合全部袁术："+list);

		List<Integer> subList = list.subList(3, 8);
		System.out.println("截取3到8的子集合："+subList);

		// 对子集每个元素扩大10倍
		for (int i = 0; i < subList.size(); i++) {
			subList.set(i, subList.get(i) * 10);
		}
		System.out.println("子集元素扩大10倍："+subList);
		/*
		 * 对子集的操作就是对原集合该部分元素的 操作
		 */
		System.out.println("原集合全部元素："+list);

		/*
		 * 删除3-7部分
		 */
		list.subList(3, 8).clear();
		System.out.println("删除3到8后，集合全部元素："+list);
	}

	/**
	 * 集合转换为数组
	 * Collection提供了方法toArray()可以将当前集合转换为数组
	 * <T> T[] toArray(T[] a);
	 * Object[] toArray(); 不常用
	 * 因为是在Collection中定义的，Set集合也可以转换成数组，所有的集合都可以转换成数组。
	 */
	@Test
	public void test4() {
		Collection<String> c = new ArrayList<String>();
		c.add("one");
		c.add("two");
		c.add("three");

		//Object[] array = c.toArray();

		String[] array = c.toArray(new String[c.size()]);
		System.out.println("数组长度："+array.length);
		for (String str : array) {
			System.out.println(str);
		}

		/*
		 * 若给定的数组可用(数组可以存放集合所有元素)时，则使用该数组，若不可用，会自动创建一个与给定数组同类的数组。
		 */

		array = c.toArray(new String[10]);
		System.out.println("数组长度："+array.length);
		for (String str : array) {
			System.out.println(str);
		}

		array = c.toArray(new String[1]);
		System.out.println("数组长度："+array.length);
		for (String str : array) {
			System.out.println(str);
		}

	}

	/**
	 * 数组转换为集合
	 * 使用数组的工具类Arrays的静态方法asList需要注意，仅能转换为List集合
	 * 只能转换数组为List集合的主要原因是：Set不能存放重复元素。所以若转换为Set集合可能出现丢失元素的情况。
	 *
	 */
	@Test
	public void test5() {
		String[] array = { "one", "two", "three", "four" };

		List<String> list = Arrays.asList(array);
		System.out.println(list);

		/*
		 * 向集合中添加一个元素 java.lang.UnsupportedOperationException 不支持操作异常
		 * 实际上下面的代码会抛出异常，原因在于，该集合是由数组转换过来的，那么该集合就表示原来的数组，数组是定长的。
		 * 对集合的操作就是对数组的操作，那么添加元素会导致数组扩容，就不能表示原来的数组了，所以不允许向该集合添加新元素。
		 */
//		list.add("five");
//		System.out.println(list);

		//将集合中第二个元素设置为"2" 修改集合元素，数组元素也会改变
		list.set(1, "2");
		System.out.println(list);

		//数组中对应的第二个元素也被改为"2"
		for (String str : array) {
			System.out.print(str+" ");
		}
		System.out.println();

		/*
		 * 若希望增删元素，需要另创建一个集合
		 */
		List<String> list1 = new ArrayList<String>();
		list1.addAll(list);
		list1.add("five");
		System.out.println(list1);

		/*
		 * 所有集合都支持一个可以用Collection作为参数的构造方法。 该构造方法的作用是在创建当前集合的同时
		 * 包含给定集合中的所有元素。
		 * 该构造方法称为：复制构造器，作用是在创建集合的同时，集合中包含给定集合中的所有元素，只要是集合都可以。
		 */
		List<String> list2 = new ArrayList<String>(list);
		list2.addAll(list);
		list2.add("five");
		System.out.println(list2);
	}

	/**
	 * 集合的排序
	 * 使用集合的工具类Collections的静态方法sort，需要注意的是，仅能对List集合排序。
	 * sort方法会对List集合元素进行自然排序(从小到大)
	 */
	@Test
	public void test6() {
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			list.add(random.nextInt(100));
		}
		System.out.println(list);

		Collections.sort(list);

		System.out.println(list);
	}

	/**
	 * 排序自定义元素的集合
	 */
	@Test
	public void test7() {
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(3, 5));
		list.add(new Point(1, 9));
		list.add(new Point(4, 6));
		list.add(new Point(2, 1));
		list.add(new Point(5, 8));

		System.out.println(list);
		/*
		 * sort方法要求集合元素必须实现Comparable接口，该接口有一个抽象方法是用来规定该 比较大小的规则。
		 *
		 * 该sort方法有个明显的缺点: 当我们在使用sort方法排序时，该方要求集合元素必须实现Comparable接口，这就
		 * 导致sort方法对我们的类有较强侵入性。
		 *
		 * 我们想使用sort方法排序集合，但是该方法要求我们的集合元素必须实现Comparable接口并且定义比较规则，这种
		 * 我们想使用某个功能，而它要求我们修改程序的现象称为"侵入性"，修改的代码越多，侵入性越强，耦合度也越大，越
		 * 不利于程序的扩展。
		 */
		Collections.sort(list);

		System.out.println(list);
	}

	/**
	 * public static <T extends Comparable<? super T>> void sort(List<T> list) {
        	list.sort(null);
    	}

	 * Collections.sort()另外一个重载方法 	比较器
	 * public static <T> void sort(List<T> list, Comparator<? super T> c) {
        	list.sort(c);
    	}
	 */
	@Test
	public void test8() {
		List<String> list1 = new ArrayList<String>();
		list1.add("jerry");
		list1.add("tom");
		list1.add("merry");
		list1.add("rose");
		list1.add("jack");
		list1.add("Bilili");
		list1.add("lady gaga");
		list1.add("JACKSON");
		list1.add("JAMES");

		System.out.println(list1);
		Collections.sort(list1);
		/*
		 * 排字母是按Unicode的编码排序，第一字母a-zA-Z排序，大写在前，小写在后。第一相同，拍第二个，以此类推。
		 */
		System.out.println(list1);


		List<String> list = new ArrayList<String>();
		list.add("范范");
		list.add("小泽老师");
		list.add("苍老师");

		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);//按照Unicode编码排序，排序中文没有意义。

		/*
		 * 重载的sort方法有两个好处:
		 * 1:由于提供了额外的比较器，所以不要求集合元素必须实现Comparable接口， 这就没有侵入性。
		 * 2:有些元素自身已经实现了Comparable接口 并且定义了比较规则，但是又不满足这里 排序需求，
		 * 也可以使用额外的比较器来定义比较规则满足排序。
		 *
		 * 重载的sort方法要求传入一个额外的比较器，该方法不再要求集合元素必须实现Comparable接口，并且
		 * 也不再使用集合元素自身的比较规则排序了，而是根据给定的这个额外的比较器的比较规则对集合元素进行排序。
		 * 实际开发中也推荐使用这种方式排序集合元素，若集合元素是自定义的。
		 * 创建比较器时也推荐使用匿名内部类的形式。
		 */
		// MyComparator com = new MyComparator();
		// Collections.sort(list,com);

		//匿名内部类形式创建  字符串字符从小到大 多的大
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}

		});

		//匿名内部类形式创建  字符串字符从大到小  多的大
		System.out.println(list);
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
		System.out.println(list);
	}
}
/**
 * 定义一个额外的比较器
 * @author Double
 *
 */
class MyComparator implements Comparator<String>{
	/**
	 * 该方法用来定义o1和o2的比较
	 * 若返回值>0:o1>o2
	 * 若返回值<0:o1<o2
	 * 若返回值=0:两个对象相等
	 */
	public int compare(String o1, String o2) {
		//字符串中字符多的大
		return o1.length()-o2.length();
	}

}
