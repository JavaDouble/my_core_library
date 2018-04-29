package com.learn.javase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;
//Map 查找表常用方法的演示
/**
 *
 * java.util.Map
 * 查找表，在Java中就是多行两列的表格 		常用的实现类:HashMap(散列表)，TreeMap(二叉树)
 *
 * Map的每一个元素分为两部分:key,value 其中key在Map中不允许重复(equals判断)
 *
 * @author Double
 *
 */
public class MapDemos {

	/**
	 * HashMap的演示 散列表
	 * HashMap实现原理：
	 * 其内部也是由数组实现的，当一组元素key-value存入(put)HashMap中时，
	 * HashMap不会直接存入集合，会经过散列算法，1.散列算法会调用key的hashCode()方法的int类型的值;
	 * 2.散列算法会	根据hashCode值，经过一系列算法，算出数组(散列桶)的下标，位置;
	 * 3.把entry<key,value>的实例存入相应位置。
	 *
	 * 取元素时，get(key)，散列算法根据key的hashCode值，经过算法，算出数据下标，
	 * key是否跟给的key相同(equals比较)，一样取出，不一样返回null。
	 *
	 * 如果两个元素key不同，hashCode值相同，都存入一个下标下的数组，entry还有一个方法，两个在一起，相当于链表。
	 * entry实例越多，查找效率越慢，最优情况hashCode的值永远不相同，数组下标只存放一个entry实例。
	 *
	 * 作为key的hashCode()方法和equals()方法的结果直接影响到HashMap查询数据的性能，
	 * 因此要妥善的重写hashCode()和equals()方法，Java规则，当重写一个类的equals()方法时，
	 * 应该连同重写hashCode()方法，使其保持一致。
	 *
	 * key可以泛型，实际上是Object，Object中定了hashCode()方法，
	 * 源码：public native int hashCode(); native修饰，是由C语言实现的，如果想看源码，使用Java中JNI工具。
	 */
	@Test
	public void test1() {
		Map<String, Integer> map = new HashMap<String, Integer>();

		/*
		 * V put(K k,V v) 将给定的key与value存入到Map中。 由于Map要求key不允许重复，所以，若使用
		 * Map已有的key存入，则是替换Value操作， 那么返回值为该key对应的原有value。 若给定的key不存在，
		 * 则返回值为null。
		 *
		 * 如果需要使用返回值，不要使用基本类型，使用引用类型，否则报NullPointException
		 * int value = map.put("化学", 99);
		 * 编译器会自动拆箱 map.put("化学", 99).intValue();map.put("化学", 99)为空，Null.intValue()
		 * 会引发NullPointException。
		 *
		 */
		map.put("语文", 99);
		map.put("数学", 98);
		map.put("英语", 97);
		map.put("物理", 96);
		Integer value = map.put("化学", 99);
		System.out.println("value:" + value);
		System.out.println(map);

		value = map.put("语文", 60);
		System.out.println("value:" + value);
		System.out.println(map);

		/*
		 * V get(K k) 根据给定的key获取对应的value 若给定的key不存在，则返回值为NULL。
		 * 接收也要使用引用类型，不使用基本类型，避免自动拆箱，否则也会引发NullPointException。
		 *
		 * int v=map.get("语文");//NullPointException。
		 */
		value = map.get("语文");
		System.out.println("语文:" + value);

		value = map.get("体育");
		System.out.println("体育:" + value);

		/*
		 * V remove(K k) 将给定的key所对应的记录从Map中删除 并且将其对应的value返回。
		 */
		System.out.println("删除语文");
		value = map.remove("语文");
		System.out.println("value:" + value);
		System.out.println(map);
	}

	/**
	 * Map的遍历
	 * 遍历Map有三种方式:1:遍历所有的key  2:遍历每一组键值对(Entry)	3:遍历所有的value(相对不常用)
	 *
	 */
	@Test
	public void test2() {
		//直接把容量设置1000000，可以存放750000条数据 不必重新散列
		Map<String, Integer> map = new HashMap<String, Integer>(1000000);
		System.out.println(map.getClass().getName());//java.util.HashMap
		map.put("语文", 99);
		map.put("数学", 98);
		map.put("英语", 97);
		map.put("物理", 96);
		map.put("化学", 95);
		System.out.println(map);

		/*
		 * 遍历所有的key Set keySet() 将Map中所有的key存入一个Set集合后返回 遍历该Set集合等于遍历了所有的key
		 */
		Set<String> keySet = map.keySet();
		System.out.println(map.keySet().getClass().getName());
		//java.util.HashMap$KeySet 返回的是HashMap中的内部类
		//HashMap源码：final class KeySet extends AbstractSet<K>{......}
		for (String key : keySet) {
			System.out.print("key:" + key+" ");
		}
		System.out.println();

		/*
		 * 遍历每一组键值对 	Map中每一组键值对都是用一个Entry的实例 保存的，Entry是Map的内部类
		 *
		 * java.util.Map.Entry,有两个方法：getKey,getValue,可以分别获取这一组键值对的key和value值。
		 *
		 * Set<Map.Entry<K, V>> entrySet(); 泛型套泛型 K,V跟map保持一致
		 * 该方法会将Map中每组键值对(若干Entry实例) 存入一个Set集合后返回。
		 */
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		System.out.println(map.entrySet().getClass().getName());
		//java.util.HashMap$EntrySet HashMap中的内部类
		//HashMap源码：final class EntrySet extends AbstractSet<Map.Entry<K,V>>{......}
		for (Entry<String, Integer> e : entrySet) {
			String key = e.getKey();
			Integer value = e.getValue();
			System.out.print(key + ":" + value+" ");
		}
		System.out.println();

		/*
		 * 遍历所有的value
		 * Collection values() 该方法会将所有的value存入一个集合后返回
		 * 不返回Set，因为Set是不重复集 	不返回List，因为List是有序集
		 * 返回的实际上是：java.util.HashMap$Values HashMap的内部类
		 */
		Collection<Integer> values = map.values();
		System.out.println(map.values().getClass().getName());
		//java.util.HashMap$Values HashMap的内部类
		//HashMap源码：final class Values extends AbstractCollection<V> {......}
		for (Integer value : values) {
			System.out.print("value:" + value+" ");
		}
	}

	/**
	 * TreeMap 二叉树 实际开发中二叉树常常用于描述路径 	二叉分支，左侧放小的，右侧放大的，以此类推
	 */
	@Test
	public void test3() {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("语文", 99);
		map.put("数学", 98);
		map.put("英语", 97);
		map.put("物理", 96);
		Integer value = map.put("化学", 99);
		System.out.println("value:" + value);
		System.out.println(map);

		value = map.put("语文", 60);
		System.out.println("value:" + value);
		System.out.println(map);

		System.out.println(1 << 30);//1073741824
	}

}
/**
 * 作为Map的key的元素若重写equals方法就要连同重写hashcode方法
 * 影响HashMap查询性能的情况是HashMap中出现链表。
 * 而出现链表的主要原因是，作为key的元素出现了hashcode方法返回的数字一样，但是彼此间equals
 * 比较的结果不为true。要是也为true就是相同的key，Map的处理是替换value(参照put方法的说明)
 *
 * 重写equals与hashcode方法的原则:
 * 当两个对象equals方法比较为true，那么hashcode方法返回的数字必须相同。反之则不是必须的，但是
 * 应当尽量保证两个对象hashcode方法返回值相同时，equals比较也为true，否则会在HashMap中出现链表。
 *
 * hashcode返回的值应当是一个稳定的值，意思是在
 * 当前对象参与equals比较的属性值没有发生改变的前提下，多次调用hashcode方法返回的数字应当相同。
 *
 *
 *
 * 当一个类的实例作为HashMap的key时，它的equals方法与hashCode方法的重写直接影响着散列表(HashMap)的查询性能。
 * 在API文档中Object对这两个方法的重写做了说明：当我们重写一个类的equals方法时，就应当连同重写hashCode方法。
 * 这两个方法重写应当遵循：
 * 1：一致性，当两个对象equals比较为true时，hashCode方法返回的额数字必须相等。反过来虽然不是必须的，但也应当遵循，
 * 	否则在HashMap中会形成链表，影响查询性能。所以两个对象hashCode值相同，equals比较也应当为true。
 * 2：稳定性：hashCode方法多次调用后返回的int类型的数字应当相同，不应该是一个变化的值，除非参与equals比较的属性值
 * 	发生了改变。
 *
 */
class Key{
	private int x;
	private int y;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
