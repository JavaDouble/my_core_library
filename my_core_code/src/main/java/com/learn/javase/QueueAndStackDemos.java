package com.learn.javase;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import org.junit.Test;

//队列和栈的演示 队列和栈都是为了解决特定需求的，实际开发中，使用率不多。

public class QueueAndStackDemos {

	/**
	 * java.util.Queue
	 * 队列
	 * 队列也可以存放一组元素，但是存取元素必须遵循先进先出原则，队列的遍历是一次性的，队列很少遍历。
	 *
	 * 常用实现类:java.util.LinkedList
	 * 由于队列只能首尾进出元素，所以就是首尾增删元素,而LinkedList既可以存放一组元素，首尾增删元素
	 * 效率又高，所以java将LinkedList也实现了队列接口,单向，还有一种双端队列,Java中栈就是双端队列实现的。
	 *
	 * 实际开发中，队列并不多用，使用集合即可满足开发。
	 * offer() poll() peek() size()
	 */
	@Test
	public void test1() {
		Queue<String> queue = new LinkedList<String>();
		/*
		 * boolean offer(E e) 入队操作，元素被添加到队列末尾
		 */
		queue.offer("one");
		queue.offer("two");
		queue.offer("three");
		queue.offer("four");
		queue.offer("five");
		queue.offer("six");

		System.out.println(queue);

		/*
		 * E poll() 获取队首元素，获取后该元素即从队列中被删除。
		 */
		String str = queue.poll();
		System.out.println(str);
		System.out.println(queue);

		/*
		 * E peek() 引用队首元素，获取队首元素，但是该元素不会被移除
		 */
		str = queue.peek();
		System.out.println(str);
		System.out.println(queue);

/*		//队列遍历
		System.out.println("第一次遍历开始");
		System.out.println("size:"+queue.size());
		for(int i=0;i<queue.size();i++){
			str=queue.poll();
			System.out.print(str+" ");//two three four
		}
		System.out.println("\n第一次遍历结束");
		System.out.println("第一次便利后的队列："+queue);//five, six
		//没有遍历成功，原因是没法遍历5次，poll()取元素会被移除，size的值一直在改变，
*/
/*		System.out.println("第二次遍历开始");
		System.out.println("size:"+queue.size());
		for(int i=queue.size();i>0;i--){
			str=queue.poll();
			System.out.print(str+" ");//two three four five six
		}
		System.out.println("\n第二次遍历结束");
		System.out.println("便利后的队列是否为空："+queue.isEmpty());//true
*/
/*		System.out.println("第三次遍历开始");
		System.out.println("开始遍历");
		// 使用poll遍历
		while (queue.size() > 0) {
			str = queue.poll();
			System.out.print(str+" ");
		}
		System.out.println("第三次遍历结束");
		System.out.println(queue);
		System.out.println("\n便利后的队列是否为空："+queue.isEmpty());*/

		//迭代器遍历队列，元素不会被移除
		System.out.println("第四次遍历开始");
		for (String s : queue) {
			System.out.print(s+" ");
		}
		System.out.println("\n第四次遍历结束");
		System.out.println(queue);
		System.out.println("便利后的队列是否为空："+queue.isEmpty());

	}

	/**
	 * 栈 双端队列
	 * 栈用来存储一组元素，存取必须遵循先进后出原则，使用栈通常用来实现"后退"功能。
	 * "前进"就是两个栈，互相挪移。栈的遍历也是一次性的。
	 * java.util.Deque
	 * 双端队列，两端都可以进出队的队列，当只调用从一端进出队列操作时，就形成了栈结构。
	 * 因此，双端队列为栈结构提供了两个方法，push,pop：从同一端进出队的方法，就形成了栈的结构。
	 *
	 * offer() poll() peek()   push()==offerLast() pop()==pollFirst()
	 */
	@Test
	public void test2() {
		Deque<String> stack = new LinkedList<String>();
		/*
		 * void push(E e) 入栈操作，最后入栈的元素在栈顶(第一个位置)
		 */
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");

		System.out.println(stack);
		/*
		 * E pop() 出栈操作，取出栈顶元素后，该元素即从 栈中被移除。
		 */
		String str = stack.pop();
		System.out.println(str);//4
		System.out.println(stack);//3 2 1

		str=stack.peek();
		System.out.println(str);//3
		System.out.println(stack);//3 2 1

		for(String str1:stack){
			System.out.print(str1+" ");
		}
		System.out.println("\n新循环遍历后："+stack);

		while (stack.size() > 0) {
			System.out.print(stack.pop()+" ");
		}
		System.out.println("\n"+stack);
	}

	/**
	 * 栈
	 * 栈用来存储一组元素，存取必须遵循先进后出原则，使用栈通常用来实现"后退"功能。
	 * "前进"就是两个栈，互相挪移。栈的遍历也是一次性的。
	 * java.util.Stack
	 * 双端队列，两端都可以进出队的队列，当只调用从一端进出队列操作时，就形成了栈结构。
	 * 因此，双端队列为栈结构提供了两个方法，push,pop：从同一端进出队的方法，就形成了栈的结构。
	 *
	 * 一共5个方法：
	 * peek()   push()==offerLast() pop()==pollFirst() empty() search(Object o)
	 * 还可以使用父类Vector的方法，Vector类是线程安全的额，Stack类也是线程安全的。
	 */
	@Test
	public void test3() {
		Stack<String> stack=new Stack<String>();
		/*
		 * void push(E e) 入栈操作，最后入栈的元素在栈顶(第一个位置)
		 */
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");

		System.out.println(stack);
		/*
		 * E pop() 出栈操作，取出栈顶元素后，该元素即从 栈中被移除。
		 */
		String str = stack.pop();
		System.out.println(str);//4
		System.out.println(stack);//3 2 1

		str=stack.peek();
		System.out.println(str);//3
		System.out.println(stack);//3 2 1

		for(String str1:stack){
			System.out.print(str1+" ");
		}
		System.out.println("\n新循环遍历后："+stack);

		while (stack.size() > 0) {
			System.out.print(stack.pop()+" ");
		}
		System.out.println("\n"+stack);
	}

	/**
	 * Vector 类可以实现可增长的对象数组。与数组一样，它包含可以使用整数索引进行访问的组件。
	 * 但是，Vector 的大小可以根据需要增大或缩小，以适应创建 Vector 后进行添加或移除项的操作.
	 * 与新 collection 实现不同，Vector 是同步的。
	 */
	@Test
	public void test4() {
		Vector<String> vector=new Vector<String>();
		vector.add("1");
		vector.add("2");
		vector.add("3");
		vector.add("4");
		System.out.println(vector);
	}
}
