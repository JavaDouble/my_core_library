package com.learn.fundamental;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

//Java常见排序算法的演示
/**
 * 常见排序算法：
 * 插入排序（直接插入排序、二分法插入排序、希尔排序）//1,2,3
 * 交换排序（冒泡排序、快速排序）//4,5
 * 选择排序（直接选择排序、堆排序）//6,7
 * 归并排序//8
 * 基数排序//9
 *
 * @author Double
 *
 */
public class SortDemos {

	/*
	 * 用随机数生成100000条数据的数组，随机数范围0~10000000
	 */
	public static int[] generate() {
		Random random=new Random();
		//int[] arr=new int[10];
		int[] arr=new int[100000];
		for(int i=0;i<arr.length;i++){
			//arr[i]=random.nextInt(1000);
			arr[i]=random.nextInt(1000000);
		}
		return arr;
	}

	/*
	 * 直接插入排序算法
	 * 每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），
	 * 直到全部插入排序完为止。
	 * 像是玩扑克一样，我们将牌分作两堆，每次从后面一堆的牌抽出最前端的牌，然后插入前面一堆牌的适当位置，例如：
		排序前：92 77 67 8 6 84 55 85 43 67
		[77 92] 67 8 6 84 55 85 43 67 将77插入92前
		[67 77 92] 8 6 84 55 85 43 67 将67插入77前
		[8 67 77 92] 6 84 55 85 43 67 将8插入67前
		[6 8 67 77 92] 84 55 85 43 67 将6插入8前
		[6 8 67 77 84 92] 55 85 43 67 将84插入92前
		[6 8 55 67 77 84 92] 85 43 67 将55插入67前
		[6 8 55 67 77 84 85 92] 43 67 ......
		[6 8 43 55 67 77 84 85 92] 67 ......
		[6 8 43 55 67 67 77 84 85 92] ......
	 */
	public static void insertSort1(int[] arr) {
		//System.out.println("直接插入排序前："+Arrays.toString(arr));
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j= i - 1;
			for (; j >= 0&&temp<arr[j]; j--) {
				arr[j + 1] = arr[j]; // 将大于temp的值整体后移一个单位
			}
			arr[j + 1] = temp;
		}
		//System.out.println("直接插入排序后："+Arrays.toString(arr));
	}

	/*
	 * 二分插入排序
	 */
	public static void insertSort2(int[] arr) {
		//System.out.println("二分插入排序前："+Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			int temp = arr[i];
			int left = 0;
			int right = i - 1;
			int mid = 0;
			while (left <= right) {
				mid = (left + right) / 2;
				if (temp < arr[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			for (int j = i - 1; j >= left; j--) {
				arr[j + 1] = arr[j];
			}
			if (left != i) {
				arr[left] = temp;
			}
		}
		//System.out.println("二分插入排序后："+Arrays.toString(arr));
	}

	/*
	 * 希尔排序 不稳定
	 */
	public static void chilleSort(int[] arr) {
		//System.out.println("希尔排序前："+Arrays.toString(arr));
		int d = arr.length;
		while (true) {
			d = d / 2;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < arr.length; i = i + d) {
					int temp = arr[i];
					int j;
					for (j = i - d; j >= 0 && arr[j] > temp; j = j - d) {
						arr[j + d] = arr[j];
					}
					arr[j + d] = temp;
				}
			}
			if (d == 1) {
				break;
			}
		}
		//System.out.println("希尔排序后："+Arrays.toString(arr));
	}

	/*
	 * 追尾并交换
	 */
	public static void swap(int[] arr,int i,int j) {
		int t=arr[i];
		arr[i]=arr[j];
		arr[j]=t;
	}

	/*
	 * 简单选择排序
	 * 	将要排序的对象分作两部份，一个是已排序的，一个是未排序的，从后端未排序部份选择一个最小值，
		并放入前端已排序部份的最后一个。例如：
		排序前：70 80 31 37 10 1 48 60 33 80
		[1] 80 31 37 10 70 48 60 33 80 选出最小值1
		[1 10] 31 37 80 70 48 60 33 80 选出最小值10
		[1 10 31] 37 80 70 48 60 33 80 选出最小值31
		[1 10 31 33] 80 70 48 60 37 80 ......
		[1 10 31 33 37] 70 48 60 80 80 ......
		[1 10 31 33 37 48] 70 60 80 80 ......
		[1 10 31 33 37 48 60] 70 80 80 ......
		[1 10 31 33 37 48 60 70] 80 80 ......
		[1 10 31 33 37 48 60 70 80] 80 ......
	 */
	public static void selectSort1(int[] arr) {
		//System.out.println("简单排序前："+Arrays.toString(arr));
		for (int i = 0; i < arr.length - 1; i++) {
			int m = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[m]) {
					m = j;
				}
			}
			if (i != m) {
				swap(arr, i, m);
			}
		}
		//System.out.println("简单排序后："+Arrays.toString(arr));
	}

	/*
	 * 第二种简单选择排序的写法
	 */
	public static void selectSort2(int[] arr) {
		//System.out.println("简单排序2前："+Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			int min = arr[i];
			int n = i; // 最小数的索引
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < min) { // 找出最小的数
					min = arr[j];
					n = j;
				}
			}
			arr[n] = arr[i];
			arr[i] = min;
		}
		//System.out.println("简单排序2后："+Arrays.toString(arr));
	}

	/*
	 * 堆排序
	 */
	public static void heapSort(int[] arr) {
		//System.out.println("堆排序前："+Arrays.toString(arr));
		int arrayLength = arr.length;
		// 循环建堆
		for (int i = 0; i < arrayLength - 1; i++) {
			// 建堆
			buildMaxHeap(arr, arrayLength - 1 - i);
			// 交换堆顶和最后一个元素
			swap(arr, 0, arrayLength - 1 - i);
		}
		//System.out.println("堆排序后："+Arrays.toString(arr));
	}

	//对data数组从0到lastIndex建大顶堆
	public static void buildMaxHeap(int[] data, int lastIndex) {
		//从lastIndex处节点（最后一个节点）的父节点开始
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k保存正在判断的节点
			int k = i;
			// 如果当前k节点的子节点存在
			while (k * 2 + 1 <= lastIndex) {
				// k节点的左子节点的索引
				int biggerIndex = 2 * k + 1;
				// 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
				if (biggerIndex < lastIndex) {
					// 若果右子节点的值较大
					if (data[biggerIndex] < data[biggerIndex + 1]) {
						// biggerIndex总是记录较大子节点的索引
						biggerIndex++;
					}
				}
				// 如果k节点的值小于其较大的子节点的值
				if (data[k] < data[biggerIndex]) {
					// 交换他们
					swap(data, k, biggerIndex);
					//将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
					k = biggerIndex;
				} else {
					break;
				}
			}
		}

	}

	/*
	 *  冒泡排序: 执行非常慢，概念上最简单。
		顾名思义，就是排序时，最大的元素会如同气泡一样移至右端，其利用比较相邻元素的方法
		，将大的元素交换至右端，所以大的元素会不断的往右移动，直到适当的位置为止。
		基本的气泡排序法可以利用旗标的方式稍微減少一些比较的时间，
		当寻访完阵列后都没有发生任何的交换动作，表示排序已经完成，
		而无需再进行之后的回圈比较与交换动作，例如：
		排序前：95 27 90 49 80 58 6 9 18 50
		27 90 49 80 58 6 9 18 50 [95] 95浮出
		27 49 80 58 6 9 18 50 [90 95] 90浮出
		27 49 58 6 9 18 50 [80 90 95] 80浮出
		27 49 6 9 18 50 [58 80 90 95] ......
		27 6 9 18 49 [50 58 80 90 95] ......
		6 9 18 27 [49 50 58 80 90 95] ......
		6 9 18 [27 49 50 58 80 90 95] 由于接下來不会再发生交换动作，排序提早结束
	 */
	public void bubbleSort(int[] arr) {
		//System.out.println("冒泡排序前:"+Arrays.toString(arr));
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
/*					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;*/
					swap(arr, j, j+1);
				}
			}
		}
		//System.out.println("冒泡排序后:"+Arrays.toString(arr));
	}

	/*
	 * 快速排序 一般在所有语言中效率最高 但是不稳定
	 * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,
	 * 一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
	 * 然后再用同样的方法递归地排序划分的两部分。
	 */

	//第一种写法
	public static int getMiddle(int[] arr,int low,int high){
		int temp=arr[low];//数组的第一个作为中轴 基准元素
		while(low<high){ //找到比基准元素小的元素位置
			while((low<high)&&(arr[high]>=temp)){
				high--;
			}
			arr[low]=arr[high];//比中轴小的记录移到低端
			while((low<high)&&(arr[low]<=temp)){
				low++;
			}
			arr[high]=arr[low]; //比中轴大的记录移到高端
		}
		arr[low]=temp;//中轴记录到尾
		return low;//返回中轴的位置
	}
	public static void quick1(int[] arr,int low,int high){
		if(low<high){//如果不加这个判断递归会无法退出导致堆栈溢出异常
			int middle=getMiddle(arr, low, high);//将arr数组进行一分为二
			//System.out.println(middle);
			quick1(arr, low, middle-1);//对低字表进行递归排序
			quick1(arr, middle+1, high);//对高字表进行递归排序
		}
	}

	public static void quickSort1(int[] arr){
		//System.out.println("快速排序前1:"+Arrays.toString(arr));
		if(arr.length>0){//查看数组是否为空
			quick1(arr, 0, arr.length-1);
		}
		//System.out.println("快速排序后1:"+Arrays.toString(arr));
	}

	/**
	 * 快速排序第二种写法
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void quick2(int[] arr, int start, int end) {
		if (start < end) {
			int key = arr[start];
			int i = start;
			for (int j = start + 1; j < end + 1; j++) {
				if (key > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[i + 1];
					arr[i + 1] = temp;
					i++;
				}
			}
			arr[start] = arr[i];
			arr[i] = key;
			quick2(arr, start, i - 1);
			quick2(arr, i + 1, end);
		}
	}

	public static void quickSort2(int[] arr){
		//System.out.println("快速排序前2:"+Arrays.toString(arr));
		if(arr.length>0){//查看数组是否为空
			quick2(arr, 0, arr.length-1);
		}
		//System.out.println("快速排序后2:"+Arrays.toString(arr));
	}

	/*
	 * 归并排序 稳定
	 */
	public static void merge(int[] arr, int left, int middle, int right) {
		int[] tmpArr = new int[arr.length];
		int mid = middle + 1; // 右边的起始位置
		int tmp = left;
		int third = left;
		while (left <= middle && mid <= right) {
			// 从两个数组中选取较小的数放入中间数组
			if (arr[left] <= arr[mid]) {
				tmpArr[third++] = arr[left++];
			} else {
				tmpArr[third++] = arr[mid++];
			}
		}
		// 将剩余的部分放入中间数组
		while (left <= middle) {
			tmpArr[third++] = arr[left++];
		}
		while (mid <= right) {
			tmpArr[third++] = arr[mid++];
		}
		// 将中间数组复制回原数组
		while (tmp <= right) {
			arr[tmp] = tmpArr[tmp++];
		}
	}

	public static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;
			// 对左边进行递归
			mergeSort(arr, left, middle);
			// 对右边进行递归
			mergeSort(arr, middle + 1, right);
			// 合并
			merge(arr, left, middle, right);
		}
	}

	public static void mergeSortTest(int[] arr){
		//System.out.println("归并排序前:"+Arrays.toString(arr));
		if(arr.length>0){//查看数组是否为空
			quick2(arr, 0, arr.length-1);
		}
		//System.out.println("归并排序后:"+Arrays.toString(arr));
	}

	/*
	 * 基数排序 稳定
	 */
	public static void baseSort(int[] arr) {
		//System.out.println("基数排序前:"+Arrays.toString(arr));
        //找到最大数，确定要排序几趟
         int max = 0;
        for (int i = 0; i < arr.length; i++) {
             if(max<arr[i]){
                max = arr[i];
             }
         }
         //判断位数
         int times = 0;
         while(max>0){
             max = max/10;
             times++;
         }
         //建立十个队列
        List<ArrayList> queue = new ArrayList<ArrayList>();
         for (int i = 0; i < 10; i++) {
             ArrayList queue1 = new ArrayList();
             queue.add(queue1);
         }
         //进行times次分配和收集
         for (int i = 0; i < times; i++) {
            //分配
             for (int j = 0; j < arr.length; j++) {
                int x = arr[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10, i);
                 ArrayList queue2 = queue.get(x);
                queue2.add(arr[j]);
                 queue.set(x,queue2);
             }
             //收集
             int count = 0;
             for (int j = 0; j < 10; j++) {
                 while(queue.get(j).size()>0){
                     ArrayList<Integer> queue3 = queue.get(j);
                     arr[count] = queue3.get(0);
                     queue3.remove(0);
                    count++;
                 }
            }
         }
 		//System.out.println("基数排序后:"+Arrays.toString(arr));
	}

	/**
	 * java.util.Arrays 提供的 void Arrays.sort(int[])方法
	 * 稳定 效率较高 Java优化过
	 * @param arr
	 */
	public static void sort(int[] arr){
 		//System.out.println("Java sort排序前:"+Arrays.toString(arr));
		Arrays.sort(arr);
 		//System.out.println("Java sort排序后:"+Arrays.toString(arr));
	}

	/*
	 * 测试 100000条数据，用下列排序所消耗时间
	 * 插入排序（直接插入排序、二分法插入排序、希尔排序）
	 * 交换排序（冒泡排序、快速排序）
	 * 选择排序（直接选择排序、堆排序）
	 * 归并排序
	 * 基数排序
	 */
	@Test
	public void test() {
		System.out.println("Java常见排序算法,100000条数据效率测试：");
		System.out.println("---------------------------");

		long start=System.currentTimeMillis();
		insertSort1(generate());
		long end=System.currentTimeMillis();
		System.out.println("直接插入排序耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		insertSort2(generate());
		end=System.currentTimeMillis();
		System.out.println("二分插入排序耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		chilleSort(generate());
		end=System.currentTimeMillis();
		System.out.println("希尔排序耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		selectSort1(generate());
		end=System.currentTimeMillis();
		System.out.println("简单选择排序1耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		selectSort1(generate());
		end=System.currentTimeMillis();
		System.out.println("简单选择排序2耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		heapSort(generate());
		end=System.currentTimeMillis();
		System.out.println("堆排序耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		bubbleSort(generate());
		end=System.currentTimeMillis();
		System.out.println("冒泡排序耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		quickSort1(generate());
		end=System.currentTimeMillis();
		System.out.println("快速排序1耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		quickSort2(generate());
		end=System.currentTimeMillis();
		System.out.println("快速排序2耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		mergeSortTest(generate());
		end=System.currentTimeMillis();
		System.out.println("归并排序耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		baseSort(generate());
		end=System.currentTimeMillis();
		System.out.println("基数排序耗时："+(end-start)+"毫秒！");

		start=System.currentTimeMillis();
		sort(generate());
		end=System.currentTimeMillis();
		System.out.println("Java sort排序耗时："+(end-start)+"毫秒！");
	}
}
