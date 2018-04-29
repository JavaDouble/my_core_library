package com.learn.javase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;
//Collection的演示
/**
 * java.util.Collection
 * 集合:集合和数组一样，用于保存一组元素，但是其提供了用于操作和维护集合元素的相关方法，使用起来更便捷
 * Collection接口定义了所有集合都具备的功能
 * 其派生了两个子接口:
 * java.util.List:可重复集且有序
 * java.util.Set:不可重复集，大部分实现类是无序的，元素是否重复是依靠元素自身equals比较的结果。
 *
 * @author Double
 *
 */
public class CollectionDemos {

	@Test
	public void test1() {
		Collection c = new ArrayList();
		/*
		 * boolean add(E e)	将给定元素添加到集合中添加成功则返回true 增
		 */
		c.add("one");
		c.add("two");
		c.add("three");
		c.add("four");
		c.add("five");
		System.out.println(c);
		/*
		 * int size() 返回集合的元素个数 从0开始，没有为0
		 */
		int size = c.size();
		System.out.println("size:"+size);
		/*
		 * boolean isEmpty() 是否为空集:集合不含有任何元素 空位true，非空false
		 */
		boolean isEmpty = c.isEmpty();
		System.out.println("isEmpty:"+isEmpty);

		/*
		 * void clear() 清空集合元素 删
		 */
		c.clear();
		System.out.println("清空集合元素");
		System.out.println("isEmpty:"+isEmpty);
	}

	/**
	 * boolean contains(E e) 判断集合是否包含给定元素
	 *
	 */
	@Test
	public void test2() {
		Collection c = new ArrayList();
		c.add(new Point(1,2));
		c.add(new Point(2,3));
		c.add(new Point(4,5));
		c.add(new Point(1,2));
		System.out.println(c);

		Point p = new Point(1,2);
//		c.add(p);
//		System.out.println(c);
		/*
		 * boolean contains(E e)
		 * 该方法会根据给定元素与集合现有元素equals比较的结果来判定集合是否包含该元素.true表示包含,false表示不包含
		 */
		boolean tf = c.contains(p);
		System.out.println("包含:"+tf);

		/*
		 * boolean remove(E e)
		 * 从集合中删除给定元素集合会使用给定元素与集合现有元素顺序进行equals比较，并只删除第一个比较为true的元素
		 */
		c.remove(p);
		System.out.println(c);
	}

	/**
	 * 集合存放的是元素的引用
	 */
	@Test
	public void test3() {
		Collection c = new ArrayList();
		Point p = new Point(1,2);
		c.add(p);

		System.out.println("p:"+p);//(1,2)
		System.out.println("c:"+c);// [(1,2)]

		p.setX(2);
		System.out.println("p:"+p);//(2,2)
		System.out.println("c:"+c);//[(2,2)]
	}

	/**
	 * 集合批量操作
	 */
	@Test
	public void test4() {
		Collection c1 = new ArrayList();
		c1.add("java");
		c1.add("c++");
		c1.add("ios");
		c1.add(".net");
		System.out.println("c1："+c1);

		Collection c2 = new HashSet();
		c2.add("android");
		c2.add("ios");
		c2.add("linux");
		c2.add("ios");//重复元素 不能添加
		System.out.println("c2："+c2);

		/*
		 * 去并集
		 * boolean addAll(Collection c)
		 * 将给定的集合中的元素添加到当前集合中，添加完毕后，只要当前集合的元素个数发生改变,则返回值为true
		 */
		boolean b=c1.addAll(c2);
		System.out.println(b);
		System.out.println("c2元素添加到c1后："+c1);

		b=c2.addAll(c1);
		System.out.println(b);
		System.out.println("c1元数添加到c2后："+c2);

		Collection c3 = new ArrayList();
		c3.add("c++");
		c3.add("android");
		c3.add("php");
		/*
		 *	boolean containsAll(Collection c)
		 *  判断集合是否包含给定集合中的所有元素,包含依然是根据元素equals比较的结果,都包含返回true，否则返回false
		 */
		boolean tf = c1.containsAll(c3);
		System.out.println("都包含:"+tf);

		/*
		 * 删除交集  从c1中删除与c3共有元素
		 */
		c1.removeAll(c3);
		System.out.println(c1);
	}
	/**
	 * 集合的遍历
	 * Collection提供了统一的遍历集合元素的方式:迭代器模式
	 * java.util.Iterator 是迭代器的接口
	 *
	 * Collection提供的方法:Iterator iterator()
	 * 该方法会返回一个可以遍历当前集合元素的迭代器实现类。
	 * 我们无需记住每个迭代器实现类名字，只将其看待为是迭代器Iterator即可。
	 *
	 * 迭代器遍历集合元素遵循:问，取，删的步骤，其中删除元素不是必须操作。
	 */
	@Test
	public void test5() {
		Collection c = new ArrayList();
		c.add("one");
		c.add("#");
		c.add("two");
		c.add("#");
		c.add("three");
		c.add("#");
		c.add("four");
		c.add("#");
		c.add("five");
		//获取用于遍历当前集合的迭代器
		Iterator it = c.iterator();
		/*
		 * boolean hasNext() 通过迭代器询问集合是否还有元素可以取出
		 */
		while(it.hasNext()){
			/*
			 * E next() 从集合中取出下一个元素
			 */
			String str = (String)it.next();
			System.out.println(str);
			if("#".equals(str)){
				/*
				 * 在使用迭代器遍历集合的过程中是不可以通过集合的方法增删元素,否则会引发异常
				 * ConcurrentModificationException
				 */
				//c.remove(str);//ConcurrentModificationException
				/*
				 * 迭代器提供的remove方法可以将调用迭代器next方法取出的元素从集合中删除。
				 */
				it.remove();
			}
		}
		System.out.println(c);

	}

	/**
	 * 泛型在集合中的应用
	 * 所有集合都可以指定一个泛型，用来声明集合中的元素类型。
	 */
	@Test
	public void test6() {
		Collection<String> c = new ArrayList<String>();
		// 添加元素仅能添加字符串类型
		c.add("one");
		c.add("two");
		c.add("three");
		c.add("four");
		//集合输出
		System.out.println(c);

		// 新循环遍历
		for (String str : c) {
			System.out.println(str);
		}
		/*
		 * 迭代器也支持泛型，与其遍历的集合的 泛型一致即可。
		 */
		Iterator<String> it = c.iterator();
		while (it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
	}

}
