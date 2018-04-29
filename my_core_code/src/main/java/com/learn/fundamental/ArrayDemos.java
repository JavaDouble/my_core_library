package com.learn.fundamental;

import java.util.Arrays;

import org.junit.Test;

//数组的演示
/**
 * 1 数组
 * 	1)分支结构、循环结构，解决的都是算法问题
 * 	2)Pascal之父Nicklaus Wirth说过一句话并因此而得了图灵奖， 程序即为：算法+数据结构
 * 	3)所谓数据结构，简单的说就是把数据按照特定的某种结构来保存，设计合理的数据结构是解决问题的前提条件。
 * 	4)数组，就是最基本的、用得最多的一种数据结构。
 *
 * 1.1 数组的定义
 *  1)数组为相同数据类型的元素组成的集合，
 *  2)数组元素按线性顺序排列，所谓线性顺序是指除第一个元素外，每一个元素都有唯一的前驱元素；除最后一个元素外，
 *  	每一个元素都有唯一的后继元素（“一个跟一个”）。
 * 	3)可以通过元素所在位置的顺序号（下标/索引）做标识访问每一个元素（Java规定：下标从0开始，最大到元素个数-1）。
 *
 * 1.1.1 定义基本类型数组
 * 声明数组的语法为： 数据类型[] 数组名 = new 数据类型 [ 大小 ]//int []   arr =  new  int[10] ;
 * int[]为数组类型，表示数组中的每一个元素为int类型；数组也是在内存中用于存储数据的，并且是存储一组数据，
 * 同样需要一个对它的引用，该引用即为arr，arr称为数组类型变量（引用）；new为特定的声明数组的关键字，后面会详细讲解，
 * 现在先记住。数组的声明必须要有元素个数，10即为数组中元素的个数，也称为数组长度。
 *
 * 1)定义基本类型数组的要点包括：1.确切的数据类型：用于开辟空间大小2. 整体的数组名字：用于对数据的引用 3.不能缺少的“ [] ”
 * 2)注意在定义数组时使用的new关键字， 正是因为new语句，才使得数组分配到了指定大小的空间（后面详细讲解）。
 * 3)声明数组的时候，int[] arr 与 int arr [](这种方式是C语言中继承而来的)两种写法均可。常用方式为int[] arr。
 * 4)声明数组时不规定数组长度（可以看到声明时仅指定了int[]，未指定长度）,
 * new关键字分配空间时需指定分配的空间大小(new int[10])。
 *
 * 1.2 数组的初始化
 * 	1)基本类型 （数据元素为基本类型）的数组创建后，默认为其数组元素设置了初始值，元素的初始值如下所示：
 *		byte、short、char、int、long为0； float和double为0.0； boolean为false。
 * 注意，此处强调的是基本类型数组的默认值，后期会介绍数据元素为非基本类型的，它的默认初始值与基本类型不同。引用类型默认null
 * 2)在程序中很多时候需要手动设置初始值，可以在数组声明的同时进行初始化 int [] arr = { 10,23,30,-10,21 } ;
 * 3)元素的个数即为数组的长度。但是此种写法只能用于声明时的初始化，不能用于先声明后赋值的情况，
 * 0---char的默认值为码0，而不是字符 '0' int a = 8; //4个字节	int[] arr = new int[10]; //40个字节
 * 声明布尔型数组b，包含26个元素		每个元素都是boolean型，默认值为false
 *
 * 1.3 数组的访问
 * 获取数组的长度：在程序中，调用数组的length属性可以获取数组的长度
 * 通过下标访问数组元素：数组的下标从0开始，最大到length-1
 *
 * 遍历数组元素:第一种方式：for(int i=0;i<arr.length-1;i++)循环 正序遍历
 * 第二种方式：for(int i=arr.length-1;i>=0;i--)倒叙遍历
 * 第三种方式：使用Arrays类API中的Arrays.toString(arr);遍历
 * 第四种方式：使用新循环遍历
 *
 * 1.4 数组的复制：
 * 第一种方式：使用System.arraycopy方法实现数组复制//System类 API
 * public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);
 * src：源数组 						srcPos：源数组中的起始位置		 dest：目标数组
 * destPos: 目标数组中的起始位置		length：要复制的数组元素的数量
 *
 * 第二种方式：使用Arrays.copyOf方法实现数组复制
 * java.util.Arrays类copyOf：类型[] newArray=Arrays.copyOf(类型[] original,int newLength)
 * 生成的新数组是原始数组的副本；newLength小于源数组，则进行截取；（自己通过代码演示效果）；
 * newLength大于源数组，则用0或 null进行填充；
 *
 * 两种方式比较：第一种方式比第二种方式灵活并高效，第二种方式常常用于数组的扩容或者缩容
 *
 * 1.5 数组的扩容和缩容：
 * 数组的“扩容”/缩容
 * Java语法规定，数组的长度在创建后是不可改变的，这点需要明确。而所谓的扩容实际上是指创建一个更大的新数组并将原有数组的
 * 内容复制到其中。可以通过Arrays.copyOf()方法，简便的实现数组的扩展。
 *
 * 1.6 数组排序：第一种方式：冒泡排序  第二种方式：java.util.Arrays类中的Arrays.sort()方法
 *
 * @author Double
 *
 */
/**
 * 程序=算法+数据结构
  	1)算法:解决问题的流程/步骤(顺序、分支、循环结构)
  	2)数据结构:将数据按照某种特定的结构来保存	数怎么存    	设计良好的数据结构会导致好的算法
   4.数组:
  	1)相同数据类型元素的集合
  	2)数组也是数据类型(引用类型)
  	3)数组的定义:      int[] arr = new int[4];
  	4)数组的初始化:
      int[] arr = new int[3];       //0,0,0
	  int[] arr = {1,4,7};          //1,4,7
	  int[] arr = new int[]{1,4,7}; //1,4,7
	  int[] arr;
	  arr = {1,4,7}; //编译错误
      arr = new int[]{1,4,7}; //正确
  	5)数组的访问:
    	5.1)通过(数组名.length)获取数组的长度
	      int[] arr = new int[3];
		  System.out.println(arr.length); //3
		5.2)通过下标来访问数组中的元素
	                 从0开始，最大到(数组的长度-1)
		  int[] arr = new int[3];
		  arr[0] = 100; //给第1个元素赋值为100
		  arr[1] = 200;
		  arr[2] = 300;
		  arr[3] = 400; //数组下标越界异常
		  System.out.println(arr[arr.length-1]);
  	6)数组的遍历:
      int[] arr = new int[5];
	  for(int i=0;i<arr.length;i++){
	    arr[i] = 100;
	  }
	  for(int i=0;i<arr.length;i++){ //正序输出
	    System.out.println(arr[i]);
	  }
      for(int i=arr.length-1;i>=0;i--){ //倒序输出
	    System.out.println(arr[i]);
	  }
  7)数组的复制:
      System.arraycopy(a,1,a1,0,4);
	  int[] a1 = Arrays.copyOf(a,6);
	  a = Arrays.copyOf(a,a.length+1);
  8)数组的排序:
      Arrays.sort(arr); //升序
      冒泡原理:
        1)四个数冒三轮
        2)每一轮都是从第1个元素开始冒
          每一次都是和它的下一个元素比
        3)冒出来的就不带它玩了
 *
 * @author Double
 *
 */
public class ArrayDemos {

	/*
	 * 1.1 数组的定义
	 */
	@Test
	public void test1() {
		int[] arr=new int[10];
		//声明整型数组arr，包含10个元素 每个元素都是int型，默认值为0
		int arr1[]=new int[10];
		//从C语言继承而来，一般不这样写 可以这样写 不推荐
		char[] arr2=new char[10];
		//声明字符型数组arr2，包含10个元素 每个元素都是char型，默认值为0码，不是字符'0'
		double[] arr3=new double[10];
		//声明浮点型数组arr3，包含10个元素 每个元素都是double型，默认值为0.0
		boolean[] flag=new boolean[26];
		//声明浮点型数组flag，包含10个元素 每个元素都是boolean型，默认值为false
	}

	/*
	 * 1.2 数组的初始化
	 */
	@Test
	public void test2() {
		int[] arr = new int[3]; //0,0,0
		int[] arr1 = {2,5,8}; //2,5,8
		int[] arr2 = new int[]{2,5,8}; //2,5,8
		//int[] ar2 = new int[3]{2,5,8}; //编译错误
		int[] arr3;
		//arr3 = {2,5,8}; //编译错误，此方式只能声明的同时初始化
		arr3 = new int[]{2,5,8}; //正确

		//错误写法
		//int[] arr6=new int[3]{1,2,3};//第一种错误写法
		int[] arr7;
		//arr7={1,2,3};///第二种错误写法
		int[] arr8;
		//arr8=new int[3]{1,2,3};//第三种错误
	}


	/*
	 * 1.3 数组的访问
	 */
	@Test
	public void test3() {
		/*
		 *  获取数组的长度:调用数组的length属性可以获取数组的长度
		 */
		int[] arr=new int[10];
		System.out.println("数组arr的长度是："+arr.length);
		/*
		 * 通过下标(索引)访问数组元素：Java规定[数组的下标从0开始，最大到length-1]
		 */
		System.out.println("数组arr第一个元素是："+arr[0]);
		System.out.println("数组arr最后一个元素是："+arr[9]);
		System.out.println("数组arr最后一个元素是："+arr[arr.length-1]);
		//System.out.println(arr[10]);
		//java.langArrayIndexOutOfBoundsException 数组下标越界异常 	运行异常

		int[] a=new int[]{1,11,111};
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		/*
		 *遍历数组元素
		 */
		arr=new int[10];
		//arr.length获取数组长度，arr[i]通过下标访问数组元素  正序遍历数组元素 第一种方式
		System.out.println("第一种方式:正序遍历数组元素：");
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*100);
			System.out.print(arr[i]+" ");
		}

		System.out.println("\n"+"第二种方式：逆序遍历数组元素：");
		//第二种方式：逆序遍历数组元素
		for(int i=arr.length-1;i>=0;i--){
			System.out.print(arr[i]+" ");
		}

		System.out.println("\n"+"第三种方式：使用Arrays类提供的Arrays.toString(arr)方法 遍历数组：");
		//第三种方式：使用java.util.Arrays类提供的Arrays.toString(arr)方法 遍历数组
		System.out.print(Arrays.toString(arr));//使用Arrays类api遍历数组

		System.out.println("\n"+"第四种方式：使用新循环遍历数组：");
		//第四种方式：使用新循环遍历数组(增强型循环 jdk1.5新特性)
		for(int i:arr){
			System.out.print(i+" ");
		}
	}
	/*
	 * 1.4 数组的复制
	 */
	@Test
	public void test4() {
		/*
		 * System.arraycopy方法用于数组复制
		 * 第一种方式：public static void arraycopy
		 * 		(Object src, int srcPos,Object dest, int destPos, int length)
		 * 	src：源数组	srcPos：源数组中的起始位置		dest：目标数组
		 * 	destPos	: 目标数组中的起始位置				length：要复制的数组元素的数量
		 *
		 * 第二种方式：Arrays.copyOf方法用于数组复制
		 * 使用java.util.Arrays类的copyOf方法可实现数组的复制
		 * 类型[ ]  newArray = Arrays.copyOf (类型[ ]  original, int  newLength)
		 * original:源数组	newLength：新数组长度
		 * 1)生成的新数组是原始数组的副本；
		 * 2)newLength小于源数组，则进行截取；（自己通过代码演示效果）；
		 * 3)newLength大于源数组，则用0或 null进行填充；
		 *
		 * 两种方式比较：第一种方式比第二种方式灵活并高效，第二种方式常常用于数组的扩容或者缩容
		 *
		 */
		int[] src=new int[10];
		for(int i=0;i<src.length;i++){
			src[i]=(int)(Math.random()*100);
		}
		System.out.println("源数组:"+Arrays.toString(src));

		int[] dest=new int[10];
		//System.out.println("目标数组:"+Arrays.toString(dest));
		//第一种方式：使用System.arraycopy方法
		//第一种情况1：源数组长度>=目标数组 复制全部
		System.arraycopy(src, 0, dest, 0, 10);
		System.out.println("目标数组:"+Arrays.toString(dest));

		//第一种情况2：源数组长度>=目标数组 复制部分 复制源数组后面8个元素 前两个默认值填充 0,0
		dest=new int[10];
		System.arraycopy(src, 2, dest, 2, 8);
		System.out.println("目标数组:"+Arrays.toString(dest));//[0, 0....]

		//第一种情况3：源数组长度>=目标数组 复制部分 从第4个元素复制源数组后面8个元素 源数组只有7个元素 不能满足8个
		//无法复制
		dest=new int[10];
		//System.arraycopy(src, 3, dest, 2, 8);
		//System.out.println("目标数组:"+Arrays.toString(dest));//数组下标越界
		//java.lang.ArrayIndexOutOfBoundsException

		//第二种情况：源数组长度<目标数组
		dest=new int[12];
		System.arraycopy(src, 0, dest, 2, 10);
		System.out.println("目标数组:"+Arrays.toString(dest));
		dest=new int[12];
		System.arraycopy(src, 0, dest, 0, 10);
		System.out.println("目标数组:"+Arrays.toString(dest));
		dest=new int[12];
		//System.arraycopy(src, 0, dest, 0, 12);
		//System.out.println("目标数组:"+Arrays.toString(dest));//数组下标越界

		//第二种方式：类型[] newArray=Arrays.copyOf(类型[] original,int newLength)
		dest=Arrays.copyOf(src, 8);//小于源数组 从0，7截取源数组
		System.out.println("目标数组:"+Arrays.toString(dest));
		dest=Arrays.copyOf(src, 12);//大于源数组 从0，9截取源数组，剩余部分用0或null进行填充
		System.out.println("目标数组:"+Arrays.toString(dest));
	}

	/*
	 * 1.5 数组的扩容和缩容
	 *  1)Java语法规定，数组的长度在创建后是不可改变的，这点需要明确。而所谓的扩容实际上是指创建一个更大的新数组
	 *  	并将原有数组的内容复制到其中。
	 * 	2)可以通过Arrays.copyOf()方法，简便的实现数组的扩展。
	 */
	@Test
	public void test5() {
		int[] src=new int[10];
		for(int i=0;i<src.length;i++){
			src[i]=(int)(Math.random()*100);
		}
		System.out.println("源数组:"+Arrays.toString(src));
		src=Arrays.copyOf(src, src.length+1);
		System.out.println("扩容后数组:"+Arrays.toString(src));
		src=Arrays.copyOf(src, src.length-2);
		System.out.println("缩容后数组:"+Arrays.toString(src));
	}
	/**
	 * 1.6 数组的排序
	 * 	1)对数组所施加的算法有很多，其中最常用的即为排序算法。
	 * 	2)所谓排序，是指将数组元素按照从小到大或从大到小的顺序重新排列。
	 * 	3)当数组元素数较多时， 排序算法的优劣至关重要。
	 * 	4)因为它将直接影响程序的执行效率，一般情况下，通过排序过程中数组元素的交换次数来衡量排序算法的优劣。
	 *	5)常用排序算法有：插入排序、冒泡排序、快速排序等。今天介绍的是冒泡排序。
	 *
	 *	冒泡排序原理：
	 *	冒泡排序是一个非常经典的排序算法，它的排序原则为：
	 *	 1)比较相邻的元素，如果违反最后的顺序准则（从大到小或是从小到大），则交换。
	 *	 2)可以简化理解为：第一次找到所有元素中最大(或最小)的放在最后一个位置上，不再变动；
	 *	  	 第二次找到剩余所有元素中最大(或最小)的放在倒数第二个位置上，不再变动，以此类推，直到排序完成。
	 *	 3)在进行比较时既可以采用“下沉”的方式，也可以使用“上浮”的方式实现。
	 */
	/*
	 * 要求:
	 * 1)声明整型数组arr，包含10个元素
	 *   给每一个元素都赋值为0到99之间的随机数
	 *   输出每一个元素
	 * 2)冒泡方式对arr进行升序排列
	 *   实验: Arrays.sort(arr);
	 * 3)输出arr中的每一个元素
	 */
	@Test
	public void test6() {
		int[] arr=new int[10];
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*100);
		}
		System.out.println("排序前，数组:"+Arrays.toString(arr));
		//冒泡排序 6行代码
		for(int i=1;i<arr.length;i++){//控制轮
			//外层轮可以从0开始(<arr.length-1或者<=arr.length)，
			//也可以从1开始(<arr.length或者<=arr.length-1)
			for(int j=0;j<arr.length-i;j++){//控制每一轮的次数 内层次数必须从0开始
				if(arr[j]>arr[j+1]){//每一次都和它的下一个元素比
					int tmp=arr[j];//满足条件则交换
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
					//若前数大于后数则交换，保证前数小于后数---升序
					//若前数小于后数则交换，保证前数大于后数---降序
				}
			}
		}
		System.out.println("冒泡排序：升序排序后1，数组:"+Arrays.toString(arr));

		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
					int tmp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
				}
			}
		}
		System.out.println("冒泡排序：升序排序后2，数组:"+Arrays.toString(arr));

		for(int i=1;i<arr.length;i++){//控制轮
			for(int j=0;j<arr.length-i;j++){//控制次
				if(arr[j]<arr[j+1]){//每次和它的下一个元素比
					int tmp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
				}
			}
		}
		System.out.println("冒泡排序：降序排序后1，数组:"+Arrays.toString(arr));

		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-1-i;j++){
				if(arr[j]<arr[j+1]){
					int tmp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
				}
			}
		}
		System.out.println("冒泡排序：降序排序后2，数组:"+Arrays.toString(arr));

		//JDK提供的Arrays.sort()方法封装了数组的排序算法 效率Arrays.sort()比冒泡强太多
		Arrays.sort(arr);
		System.out.println("sort排序：升序排序后，数组:"+Arrays.toString(arr));
	}
}
