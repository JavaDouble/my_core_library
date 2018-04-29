package com.learn.javase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
//线程及其常用方法的演示
public class ThreadDemos {
	//表示图片是否下载完毕
	public static boolean isFinish = false;

	/**
	 * 第一种创建线程的方式：继承Thread类并重写run方法。
	 */
	@Test
	public void test1() {
		Thread t1 = new MyThread1();
		Thread t2 = new MyThread2();
		/*
		 * 启动线程要执行start方法，不能调用run方法，run方法是线程要执行的任务，当线程
		 * 启动后一旦被分配CPU时间片后会自动执行自己的run方法。
		 */
		t1.start();
		t2.start();
	}

	/**
	 * 第二种创建线程的方式:单独定义线程任务	实现Runnable接口并重写run方法
	 *
	 */
	@Test
	public void test2() {
		//单独定义任务
		Runnable runn1 = new MyRunnable1();
		Runnable runn2 = new MyRunnable2();

		Thread t1 = new Thread(runn1);
		Thread t2 = new Thread(runn2);

		/*
		 * 线程用于并发执行任务，多个线程运行任务，相当于现实生活中多个人各做各的事情。
		 *
		 * 线程有几点不可控:
		 * 线程何时运行，运行多久完全听从线程调度的统一安排。
		 * 1:时间片长短线程不可控。
		 * 2:时间片分配给哪个线程线程也不可控。线程只能被动被分配时间片，不能主动索取。
		 * 线程调度会尽可能均匀的将时间片分配给不同的线程，但是不保证"一人一次"
		 */

		t1.start();
		t2.start();
	}

	/**
	 * 使用匿名内部类形式完成线程的两种方式创建  推荐
	 *
	 */
	@Test
	public void test3() {
		//使用匿名内部类创建第一种方式的线程
		new Thread(){
			public void run(){
				for(int i=0;i<100000;i++){
					System.out.println("你是谁啊?");
				}
			}
		}.start();

		//使用匿名内部类创建第二种方式的线程
		new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<100000;i++){
					System.out.println("我是查水表的!");
				}
			}
		}).start();
	}

	/**
	 * 线程提供了一个静态方法:static Thread currentThread()	该方法可以获取运行当前方法的线程。
	 * 源码：public static native Thread currentThread();
	 */
	public static void dosome(){
		Thread t = Thread.currentThread();
		System.out.println("运行dosome方法的线程是:"+t);
	}

	@Test
	public void test4() {
		/*
		 * 获取运行test4测试方法的线程
		 *
		 * 线程的toString()方法的格式：
		 * Thread[getName()线程的名字,getPriority()线程优先级, group.getName()所在线程组的名字]
		 * 线程默认优先级为5
		 *
		 * 一般情况下，不会给线程起名字，有程序自己创建名字，如：Thread-0，Thread-1等，
		 * 除非特别重要的线程，如：main()方法 程序入口。
		 */
		Thread test = Thread.currentThread();
		System.out.println("运行test4测试方法的线程是:"+test);

		dosome();

		Thread t = new Thread(){
			public void run(){
				Thread t = Thread.currentThread();
				System.out.println("自定义线程:"+t);
				dosome();
			}
		};
		t.start();
	}

	/**
	 * 获取线程相关信息的方法
	 *
	 */
	@Test
	public void test5() {
		//获取test5测试方法的线程main
		Thread main = Thread.currentThread();
		//long getId():返回该线程的标识符  唯一标识
		long id = main.getId();
		System.out.println("id:"+id);//1

		//String getName():返回该线程的名称
		String name = main.getName();
		System.out.println("name:"+name);//main

		//int getPriority():返回线程的优先级 默认为5 一共有10个优先级，1-10
		int priority = main.getPriority();
		System.out.println("priority:"+priority);//5

		//State getState():获取线程的状态
		System.out.println("ThreadState:"+main.getState());//RUNNABLE

		//boolean isAlive():是否处于活动状态
		boolean isAlive = main.isAlive();
		System.out.println("isAlive:"+isAlive);//true

		//boolean isDaemon():是否为后台线程(守护线程)
		boolean isDaemon = main.isDaemon();
		System.out.println("isDaemon:"+isDaemon);//false

		//boolean isInterrupted():是否被中断
		boolean isInterrupted = main.isInterrupted();
		System.out.println("isInterrupted:"+isInterrupted);//false
	}

	/**
	 * 线程优先级
	 * 线程的时间片分配完全听线程调度的，线程只能被动的被分配时间，对于线程调度的工作不能干预。
	 * 但是可以通过提高线程的优先级来达到尽可能干预的目的。
	 *
	 * 线程优先级有10个等级，对应的数字是1-10,其中1是最低优先级，10是最高优先级
	 * 理论上，优先级越高的线程获取CPU时间片的次数多
	 * 线程提供了常量对应了区间范围的最高与最低和默认:
	 * Thread.MAX_PRIORITY:最高优先级，对应数字10
	 * Thread.MIN_PRIORITY:最低优先级，对应数字1
	 * Thread.NORM_PRIORITY:默认优先级，对应数字5
	 *
	 */
	@Test
	public void test6() {
		Thread max = new Thread(){
			public void run(){
				for(int i=0;i<10000;i++){
					System.out.println("max");
				}
			}
		};

		Thread min = new Thread(){
			public void run(){
				for(int i=0;i<10000;i++){
					System.out.println("min");
				}
			}
		};

		Thread norm = new Thread(){
			public void run(){
				for(int i=0;i<10000;i++){
					System.out.println("nor");
				}
			}
		};

		max.setPriority(Thread.MAX_PRIORITY);
		min.setPriority(Thread.MIN_PRIORITY);

		min.start();
		norm.start();
		max.start();
	}

	/**
	 * sleep阻塞
	 * 线程提供了一个静态方法:static void sleep(long ms)
	 * 该方法可以导致调用该方法的线程进入阻塞状态指定的毫秒，当超时后线程会自动回到runnable状态等待分配
	 * 时间片继续并发运行。通常使用sleep做周期性间隔使用。
	 *
	 */
	@Test
	public void test7() {
		/*
		 * 实现电子表功能  每秒输出当前系统时间，格式:HH:mm:ss
		 */
		SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
		while(true){
			System.out.println(sdf.format(new Date()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 守护线程，又称为后台线程
	 * 当一个进程中的所有前台线程都结束时，进程结束，那么正在运行的后台线程会被强制结束。
	 * 默认创建的线程都是前台线程，后台线程需要单独设置
	 *
	 * @Test测试方法与main会有差异 使用main调用下列代码
	 */
	@Test
	public void test8() {
		/*
		 * rose:扮演者	为前台线程
		 */
		Thread rose = new Thread(){
			public void run(){
				for(int i=0;i<10;i++){
					System.out.println("rose:let me go!!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
				System.out.println("rose:啊啊啊啊AAAAAAaaaa.....");
				System.out.println("音效:噗通!");
			}
		};

		/*
		 *	jack:扮演者	为后台线程
		 */
		Thread jack = new Thread(){
			public void run(){
				while(true){
					System.out.println("jack:you jump!i jump!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
		};

		/*
		 * 设置为守护线程，必须在线程启动前进行设置
		 */
		jack.setDaemon(true);

		rose.start();
		jack.start();

		while(true){
			//以前是main方法运行完毕程序结束，现在是前台线程结束，程序结束。
		}
	}

	/**
	 * join方法: 线程的join方法可以做到线程同步运行,用于等待当前线程结束，该方法抛出InterruptedException
	 *
	 * join方法会将调用该方法的线程置于阻塞状态，直到其等待的线程执行完毕才会解除阻塞继续运行。
	 *
	 * 同步:有先后顺序的执行代码为同步执行
	 * 异步:多线程并发运行通常就是异步执行，各执行各的互相没有牵制。
	 * 线程同步:指的就是线程运行方式从各执行各的变为 有先后顺序的执行。
	 *
	 * @Test测试方法与main会有差异 使用main调用下列代码
	 */
	@Test
	public void test9() {
		/*
		 * 当一个方法中的局部内部类中想引用该方法的其他局部变量，那么该变量必须是final的
		 * 这是因为JVM虚拟机内存分配问题导致的，Java 8.0之后没有此问题了。
		 *
		 */
		final Thread download = new Thread(){
			public void run(){
				System.out.println("down:开始下载图片...");
				for(int i=1;i<=100;i++){
					System.out.println("down:"+i+"%");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
				}
				System.out.println("down:图片下载完毕!");
				isFinish = true;
			}
		};

		Thread show = new Thread(){
			public void run(){
				System.out.println("show:开始显示图片...");
				/*
				 * 先等待download将图片下载完毕再尝试加载
				 */
				try {
					download.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}


				if(!isFinish){
					throw new RuntimeException("图片没有下载完毕!");
				}
				System.out.println("show:显示图片完毕!");
			}
		};


		download.start();
		show.start();

	}

	/**
	 * 线程同步
	 *
	 * 当多个线程并发访问同一资源时，就会形成"抢"的现象。由于线程切换时机不确定，会导致线程代码执行顺序混乱，出现代码执行
	 * 未按代码设计方式执行的情况。严重时会出现系统瘫痪等情况。
	 * @Test测试方法与main会有差异 使用main调用下列代码
	 */
	@Test
	public void test10() {
		final Table table = new Table();
		Thread t1 = new Thread(){
			public void run(){
				while(true){
					int bean = table.getBean();
					//模拟线程发生切换
					Thread.yield();
					System.out.println(getName()+":"+bean);
				}
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				while(true){
					int bean = table.getBean();
					//模拟线程发生切换
					Thread.yield();
					System.out.println(getName()+":"+bean);
				}
			}
		};
		t1.start();
		t2.start();
	}

	/**
	 * 有效地缩小同步范围可以在保证并发安全的同时提高执行效率。
	 * @Test测试方法与main会有差异 使用main调用下列代码
	 */
	@Test
	public void test11() {
		final Shop shop = new Shop();
		Thread t1 = new Thread(){
			public void run(){
				shop.buy();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				shop.buy();
			}
		};
		t1.start();
		t2.start();
	}

	/**
	 * 静态方法使用synchronized修饰后，该方法一定具有同步效果，与对象无关，因为静态方法时属于类的，只有一份。
	 *
	 * 静态方法锁的当前类的类对象，即Class的实例
	 * JVM加载每一个类的时候都会实例化一个Class类的实例用于描述当前类的信息，
	 * 并且在JVM内部只有一个Class的实例与当前类对应，锁的就是这个Class的实例。
	 *
	 * @Test测试方法与main会有差异 使用main调用下列代码
	 */
	@Test
	public void test12() {
		Thread t1 = new Thread(){
			public void run(){
				Foo.dosome();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				Foo.dosome();
			}
		};
		t1.start();
		t2.start();
	}

	/**
	 * 互斥锁
	 * 当synchronized修饰几段不同的代码片段，但是同步监视器对象相同时，那么这几段代码间就存在了互斥关系，
	 * 即:多个线程不能同时执行这几段代码。
	 *
	 * @Test测试方法与main会有差异 使用main调用下列代码
	 */
	@Test
	public void test13() {
		final Boo boo = new Boo();
		Thread t1 = new Thread(){
			public void run(){
				boo.methodA();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				boo.methodB();
			}
		};
		t1.start();
		t2.start();
	}

	/**
	 * 死锁现象  多个方法中同时锁定对方的对象锁，然后方法中调用对方的方法
	 *  @Test测试方法与main会有差异 使用main调用下列代码
	 */
	@Test
	public void test14() {
		final Coo coo = new Coo();
		Thread t1 = new Thread(){
			public void run(){
				coo.methodA();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				coo.methodB();
			}
		};
		t1.start();
		t2.start();
	}

	/**
	 * 线程安全和非线程安全API
	 * Collections提供了一组静态方法可以将现有的List，Set，Map的实现类转换为线程安全的。
	 */
	@Test
	public void test15() {
		/*
		 * ArrayList,LinkedList均不是线程安全的 不适合在并发中异步使用
		 */
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		System.out.println(list);

		// 将给定List集合转换为线程安全的
		list = Collections.synchronizedList(list);
		System.out.println(list);
		System.out.println(list.getClass().getName());
		//java.util.Collections$SynchronizedRandomAccessList Collections中的内部类

		/*
		 * HashSet就是HashMap，所有元素都存在 该HashMap的key上。
		 */
		Set<String> set = new HashSet<String>(list);
		System.out.println(set);
		// 将给定的Set集合转换为线程安全的
		set = Collections.synchronizedSet(set);
		System.out.println(set);
		System.out.println(set.getClass().getName());
		//java.util.Collections$SynchronizedSet Collections中的内部类

		/*
		 * HashMap不是线程安全的
		 */
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 99);
		map.put("数学", 98);
		map.put("英语", 97);
		System.out.println(map);
		map = Collections.synchronizedMap(map);
		System.out.println(map);
		System.out.println(map.getClass().getName());
		//java.util.Collections$SynchronizedMap  Collections中的内部类

		/*
		 * API手册有说明：
		 * 同一个对象synchronized不同代码，list,set,map增删方法互斥，不能同时进行。所以所有线程安全的集合
		 * 增删方法互斥，但唯独不和遍历互斥。
		 *
		 * 就算是线程安全的集合，也不与迭代器的遍历操作互斥。但是迭代器在遍历集合时不能通过集合增删元素否则会抛出异常，
		 * 所以在这种情况下需要自行维护互斥关系。
		 */
	}

	/**
	 * 线程池
	 * 线程池主要解决两个问题: 1:控制线程数量2:重用线程
	 *
	 * 应用场景1:并发量多大会导致CPU过度切换，导致拖慢系统，耗费系统资源，严重时可能导致系统瘫痪。
	 * 应用场景12:频繁创建与销毁线程也会给系统带来很多负担，所以遇到上述情况应当使用线程池。
	 *
	 */
	@Test
	public void test16() {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 5; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					Thread t = Thread.currentThread();
					System.out.println(t + ":正在运行任务...");
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.out.println("线程被中断了！");
					}
					System.out.println(t+ ":运行任务完毕!");
				}
			};
			System.out.println("将任务" + i + "指派给了线程池");
			threadPool.execute(runnable);
		} // for over

		System.out.println("停止线程池");

		/**
		 * 关闭线程有2个方法：
		 * 1.ExecutorService接口中的 List<Runnable> shutdownNow();  立刻停止线程执行任务 中断线程池
		 * 2.Executor接口中的   void shutdown();  线程池执行完任务后停止
		 * ExecutorService接口继承自Executor接口
		 */
		threadPool.shutdownNow();
	}

	@Test
	public void test17() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		Runnable myRunnable =new Runnable() {
		    @Override
		    public void run() {
		        try {
		            Thread.sleep(2000);
		            System.out.println(Thread.currentThread().getName() + " run");
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }

		    }
		};

		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);
		System.out.println("---先开三个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());

		executor.execute(myRunnable);
		executor.execute(myRunnable);
		executor.execute(myRunnable);

		System.out.println("---再开三个---");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("----8秒之后----");
		System.out.println("核心线程数" + executor.getCorePoolSize());
		System.out.println("线程池数" + executor.getPoolSize());
		System.out.println("队列任务数" + executor.getQueue().size());
	}

}



/**
 * 第一种创建线程的方式有两个不足:
 * 1:由于java是单继承的，所以导致当前类继承了Thread就不能再继承其他类，在实际开发中经常会出现继承冲突。
 * 2:由于线程内部重写了run方法并定义了线程的任务，这就导致该线程与其执行的任务有一个必然的耦合关系，不利于线程的重用。
 *
 * @author Double
 *
 */
class MyThread1 extends Thread{
	public void run(){
		for(int i=0;i<100000;i++){
			System.out.println("你是谁啊?");
		}
	}
}

class MyThread2 extends Thread{
	public void run(){
		for(int i=0;i<100000;i++){
			System.out.println("我是查水表的!");
		}
	}
}

class MyRunnable1 implements Runnable{
	public void run(){
		for(int i=0;i<100000;i++){
			System.out.println("你是谁啊?");
		}
	}
}
class MyRunnable2 implements Runnable{
	public void run(){
		for(int i=0;i<100000;i++){
			System.out.println("我是查水表的!");
		}
	}
}

class Table{
	//桌子上有20个豆子
	private int beans = 20;
	/**
	 * 当一个方法被synchronized修饰后，该方法称为 “同步方法”，即:多个线程不能同时进到方法内部执行代码，
	 * 这就导致该方法从多线程异步执行变为同步执行，不存在抢的问题，所以解决了并发执行的安全问题。
	 *
	 * 在方法上使用synchronized，那么上锁的对象就是当前方法所属对象，即:方法内部看到的this。
	 *
	 * 对于成员方法而言，synchronized会在一个线程调用该方法，使将该方法所属对象加锁，其他线程在执行该方法时由于执行
	 * 方法的线程没有释放锁，所以只能在方法外阻塞，直到持有方法锁的线程将方法执行完毕。所以，解决多线程并发执行安全问题的
	 * 办法就是将"抢"变为"排队"。
	 *
	 * 实际开发中，给方法加synchronized的应用很少，多数用于给代码块加synchronized。
	 * 既可以兼顾效率，又可以兼顾同步安全。
	 * @return
	 */
	public synchronized int getBean(){
		if(beans==0){
			throw new RuntimeException("没有豆子了!");
		}
		//模拟线程发生切换
		Thread.yield();
		return beans--;
	}
}

class Shop{
	public void buy(){
		try {
			Thread t = Thread.currentThread();
			System.out.println(t.getName()+":正在挑选衣服..");
			Thread.sleep(5000);
			/*
			 * 同步块
			 * 同步块可以更精确的包含需要多线程同步执行的代码片段，这样可以在保证并发安全的前提下尽可能的提高并发效率。
			 * 但是需要注意，使用同步块时指定的同步监视器对象(即圆括号中指定的对象，上锁的对象)必须
			 * 保证多个需要同步执行的线程看到的是同一个才可以。
			 * synchronized (同步监视器对象this) {
					需要同步代码块
				}
				所谓同步执行即：多个线程必须排队执行
				所谓异步执行即：多个线程可以同时执行
			 */
			synchronized (this) {
				System.out.println(
					t.getName()+":正在试衣服...");
				Thread.sleep(5000);
			}

			System.out.println(t.getName()+":结账离开");
		} catch (Exception e) {
		}

	}
}

class Foo{
	public synchronized static void dosome(){
		Thread t = Thread.currentThread();
		System.out.println(t.getName()+":正在执行dosome方法");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getName()+":运行dosome方法完毕!");
	}
}

class Boo{
	//synchronized同样可以使用代码块，实现互斥锁，只要保证调用方法的对象一样即可。
	public synchronized void methodA(){
		Thread t = Thread.currentThread();
		System.out.println(t.getName()+":正在执行A方法...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getName()+":A方法运行完毕!");
	}

	public synchronized void methodB(){
		Thread t = Thread.currentThread();
		System.out.println(t.getName()+":正在执行B方法...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getName()+":B方法运行完毕!");
	}
}

/**
 * 死锁现象 多个方法中同时锁定对方的对象锁，然后方法中调用对方的方法
 *
 */
class Coo{
	private Object o1 = new Object();
	private Object o2 = new Object();

	public void methodA(){
		Thread t = Thread.currentThread();
		System.out.println(t.getName()+":正在执行A方法...");
		try {
			synchronized (o1) {
				Thread.sleep(5000);
				methodB();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getName()+":A方法运行完毕!");
	}

	public void methodB(){
		Thread t = Thread.currentThread();
		System.out.println(t.getName()+":正在执行B方法...");
		try {
			synchronized (o2) {
				Thread.sleep(5000);
				methodA();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getName()+":B方法运行完毕!");
	}
}