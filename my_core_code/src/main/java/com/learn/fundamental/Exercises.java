package com.learn.fundamental;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

//Java语法基础练习题锦集
public class Exercises {

	@Test
	public void variateExercises1() {
		//int &size=20;//编译错误
		//System.out.println(&size);
		//定义的变量&size不符合Java变量的命名规范，
		//Java变量只能包含数字、字母、下划线和$，而&不能在变量命名中使用，因此会出现编译错误。
	}

	@Test
	public void variateExercises2() {
		int age;
		//System.out.println(age);//编译错误
		//变量age未进行初始化，就在第二行中被使用了。在Java中，变量在使用之前，必须进行初始化。
	}

	@Test
	public void variateExercises3() {
		int count=30;
		count=60;
		System.out.println(count);

		int balance;
		//balance =218.50;//数据类型不匹配

	}

	/**
	 * 交换两个数字和比较两个数字的方法
	 */
	@Test
	public void primaryDataTypeExercise1() {
		int s1=(int)(Math.random()*100);
		int s2=(int)(Math.random()*100);
		System.out.println("交换前，s1:"+s1+",s2="+s2);

		s1=s1+s2;
		s2=s1-s2;
		s1=s1-s2;
		System.out.println("交换后，s1:"+s1+",s2="+s2);

		s1=(int)(Math.random()*100);
		s2=(int)(Math.random()*100);
		System.out.println("交换前，s1:"+s1+",s2="+s2);

		int temp=s1;
		s1=s2;
		s2=temp;
		System.out.println("交换后，s1:"+s1+",s2="+s2);

		int max=s1>s2?s1:s2;
		int min=s1<s2?s1:s2;
		System.out.println("最大值是："+max+",最小值是："+min);
		if(s1>s2){
			System.out.println("最大值是："+s1+",最小值是："+s2);
		}else{
			System.out.println("最大值是："+s2+",最小值是："+s1);
		}

	}

	/**
	 * 通过代码计算一段程序运行的时间
	 */
	@Test
	public void primaryDataTypeExercise2() {
		 //开始时间
        long startTime = System.currentTimeMillis();
        //计算从1加到1000000的和
        int sum = 0;
        for (int i = 1; i <= 1000000; i++) {
           sum+=i;
        }
        //结束时间
        long endTime = System.currentTimeMillis();
        //输出耗时
        System.out.println("计算从1加到1000000的和:"+sum+",耗时："+(endTime-startTime)+"毫秒");
	}

	@Test
	public void primaryDataTypeExercise3() {
		byte b1=10;
		byte b2=20;
		//byte b3=b1+b2;//编译错误 byte short char 参与运算一律先转换为int类型
	}

	@Test
	public void primaryDataTypeExercise4() {
		int a = 1, b = 10;
	    int c1 = a++;
	    int c2 = ++b;
	    System.out.println("a=" + a + ", b=" + b + ", c1=" + c1 + ", c2=" + c2);
	    //a=2, b=11, c1=1, c2=11
	}

	@Test
	public void primaryDataTypeExercise5() {
		int i = 100, j = 200;
		boolean b1 = (i > j) && (i++ > 100);
		System.out.println(b1);//false
		System.out.println(i);//100
	}

	@Test
	public void primaryDataTypeExercise6() {
        // 输入数据
        Scanner console = new Scanner(System.in);
        System.out.println("请输入单价（￥）：");
        double unitPrice = console.nextDouble();
        System.out.println("请输入数量：");
        double amount = console.nextDouble();
        System.out.println("请输入金额（￥）：");
        double money = console.nextDouble();
        console.close();
        // 计算商品总价
        double totalPrice = 0.0;
        totalPrice = unitPrice * amount;
        // 计算找零
        double change = money - totalPrice;
        System.out.println("应收金额为：￥" + totalPrice + "，找零为：￥" + change);
	}


	@Test
	public void operationExercise1() {
		int a, b, c;
		a = b = c = 100;
		System.out.println("a=" + a + ",b=" + b + ",c=" + c);
		System.out.println(5 + 6 + "" + 5 + 6);

	}

	/**
	 * 用户从控制台接收两个整数，通过程序找出两个数中的最大值。
	 */
	@Test
	public void operationExercise2() {
        System.out.println("请依次输入两个整数：a，b（以空格隔开）");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        int result=a>b?a:b;
        System.out.println("最大值为"+result);
	}

	/**
	 * 使用程序为用户所录入的 3 个数值进行升序排列，并将排序后的结果输出到控制台。
	 */
	@Test
	public void branchExercise1() {
	     Scanner scanner = new Scanner(System.in);
	        System.out.println("请依次输入三个整数：a，b，c（以空格隔开）");
	        int a = scanner.nextInt();
	        int b = scanner.nextInt();
	        int c = scanner.nextInt();
	        scanner.close();
	        System.out.println("您输入的是：");
	        System.out.println("a=" + a + ", b=" + b + ", c=" + c);
	        int temp = 0;
	        if (a > b) {
	            temp = a;
	            a = b;
	            b = temp;
	        }
	        if (a > c) {
	            temp = a;
	            a = c;
	            c = temp;
	        }
	        if (b > c) {
	            temp = b;
	            b = c;
	            c = temp;
	        }
	        System.out.println("升序排列后，结果为：");
	        System.out.println("a=" + a + ", b=" + b + ", c=" + c);

	}

	/**
	 * 本案例需要使用交互的方式判断某年是否为闰年：用户从控制台输入需要判断的年份值，
	 * 由程序使用if-else判断该年是否为闰年，并将判断结果输出到控制台。
	 */
	@Test
	public void branchExercise2() {
	    Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份（例如：2012）");
        int year = scanner.nextInt();
        scanner.close();
        if((year % 4 == 0 && year % 100 != 0) || year % 400==0 ){
            System.out.println("是闰年");
        }else{
            System.out.println("不是闰年");
        }
	}

	/**
	 * 个人所得税是国家对本国公民、居住在本国境内的个人的所得和境外个人来源于本国的所得征收的一种所得税。
	 * 目前，北京地区的个人所得税的计算公式为：应纳税额＝（工资薪金所得－扣除数）×适用税率－速算扣除数。
	 * 						其中，扣除数为3500元，
	 */
	@Test
	public void branchExercise3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的工资的税前金额（￥）：");
        double salary = scanner.nextDouble();
        scanner.close();
        double tax = 0.0;
        double taxIncome = salary - 3500;
        if (taxIncome <= 0) {
            tax = 0.0;
        } else if (taxIncome <= 1500) {
            tax = taxIncome * 0.03;
        } else if (taxIncome <= 4500) {
            tax = taxIncome * 0.10 - 105;
        } else if (taxIncome <= 9000) {
            tax = taxIncome * 0.20 - 555;
        } else if (taxIncome <= 35000) {
            tax = taxIncome * 0.25 - 1005;
        } else if (taxIncome <= 55000) {
            tax = taxIncome * 0.30 - 2755;
        } else if (taxIncome <= 80000) {
            tax = taxIncome * 0.35 - 5505;
        } else {
            tax = taxIncome * 0.45 - 13505;
        }
        System.out.println("你应该缴纳的个人所得税是：￥" + tax);
	}

	/**
	 * 输入年份和月份，输出该月的天数（使用switch-case）
	 * 一年有 12 个月，而每个月的天数是不一样的。其中，有7个月为 31 天，称为大月，分别为1、3、5、7、8、10、12月；
	 * 有 4个月为 30 天，称为小月，分别为4、6、9、11月；还有二月比较特殊，平年的二月只有28天，而闰年的二月有 29 天。
	 * 本案例需要使用交互的方式计算某年某月的天数：由用户在控制台输入年份和月份值，程序计算该年该月的天数，
	 * 												并将结果输出在控制台。
	 */
	@Test
	public void branchExercise4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份（例如：2012）");
        int year = scanner.nextInt();
        System.out.println("请输入月份（例如：１）");
        int month = scanner.nextInt();
        scanner.close();
        // 某月的天数
        int days = 0;
        switch (month) {
        case 2:
            // 判断是否为闰年，闰年29天，非闰年28天
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
            break;
        // 4，6，9，11为小月
        case 4:
        case 6:
        case 9:
        case 11:
            days = 30;
            break;
        // 其余为大月
        default:
            days = 31;
        }
        System.out.println(year + "年" + month + "月有" + days + "天");
	}

	/**
	 * 用户从控制台接收三个整数，通过程序找出三个数中的最大值。
	 */
	@Test
	public void branchExercise5() {
        System.out.println("请依次输入三个整数：a,b,c（以空格隔开）");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();
        int result=(a>b?a:b)<c?c:(a>b?a:b);
        System.out.println("最大值为"+result);
	}


	/**
	 *
	 * do-while循环
	 * 先执行后判断，至少执行一次 第1要素与第3要素相同时，首选
	 *
	 * while语句用于处理循环逻辑
	 * 执行顺序:1 243 243 243 243 243 243 2....
	 * 使用break语句跳出循环  continue:跳过循环体中剩余语句而进入下一次循环
	 */
	@Test
	public void circulationExercise1() {
		int num=0;// 记录循环次数
		int words = 27;
		do {
			num++;
			words--;
		} while (words <= 18);
		System.out.println(num);

		int s = 105;//1
		int result = 0;
		while (s > 0) {//2
			int m = s % 10;
			result += m;
			s /= 10;//3
		}
		System.out.println(result);//6 //4
	}

	/**
	 * 数列求和
	 * 有数列为：9，99，999，...，9999999999。要求使用程序计算此数列的和，并在控制台输出结果。
	 * 另有数列：1+1/2+1/3…+1/n（n>=2）。要求使用交互的方式计算此数列的和：用户在控制台录入需要计算的整数 n 的值，
	 * 						程序计算此数列的和，并在控制台输出结果。
	 */
	@Test
	public void circulationExercise2() {
	    // 数列求和
        long nine = 9;
        long result = nine;
        for (int i = 2; i <= 10; i++) {
            nine = nine * 10 + 9;
            result += nine;
        }
        System.out.println("9+99+999+...+9999999999=" + result);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入整数（例如10）：");
        int n = scanner.nextInt();
        double result1 = 0;
        for (int i = 1; i < n; i++) {
            result1 += 1.0 / i;
            if(i==1){
                System.out.print("1+");
            }else{
                System.out.print("1/" + i + "+");
            }
        }
        result += 1.0 / n;
        System.out.print("1/" + n + "=" + result1);
        scanner.close();
	}

	/**
	 * 本案例要求使用交互的方式找出从 2 开始到某个数值范围内的所有质数，并输出结果。
	 * 因为输出的质数可能较多，要求分行输出，每行最多输出 10 个质数。
	 */
	@Test
	public void circulationExercise3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入查找质数的范围：2~");
		int num = sc.nextInt();
		sc.close();
		int count=0;//质数的个数
		/*
		int numOfPrime = 0;
	    for (int n = 2; n <= num; n++) {
	         // 判断n是否是质数
	         int m = (int) Math.sqrt(n);
	         int i = 2;
	         for (; i <= m; i++) {
	             if (n % i == 0)
	                 break;
	          }
	          if (i > m) {
	              System.out.print(n + " ");
	              if (++numOfPrime % 10 == 0)
	               System.out.println();
	          }
	     }
	     System.out.println("\n共有" + numOfPrime + "个质数");*/
		for(int j=2;j<=num;j++){
			boolean flag =true;//1.假设真是质数
			for(int i=2;i<=(int)Math.sqrt(j);i++){
				if(j%i==0){
					flag=false;//2.修改为假不是质数
					break;
				}
			}
			if(flag){//3.判断结果
				System.out.print(j+"\t");
				if(++count%10==0){
					System.out.println();
				}
			}
		}
		System.out.println("\n共有"+count+"个质数");
	}

	/**
	 * 求数组元素的最大值和最小值并输出(要求数组：int[10],数组元素为100以内的随机数)
	 */
	@Test
	public void arrayExercise1() {
		int[] arr=new int[10];
		for(int i=0;i<arr.length-1;i++){
			arr[i]=(int)(Math.random()*100);
			System.out.print(arr[i]+" ");
		}

		int max=arr[0];//则修改max的值为较大的
		int min=arr[0];//则修改mix的值为较小的
		for(int i=0;i<arr.length-1;i++){ //遍历剩余元素
			if(max<arr[i]){//若剩余元素大于max
				max=arr[i];//则修改max的值为较大的
			}
			if(min>arr[i]){//若剩余元素小于mix
				min=arr[i];//则修改mix的值为较小的
			}
		}
		System.out.println("\n数组最大值："+max+",最小值:"+min);
	}

	/**
	 * 给数组扩容并将最大值max赋给数组中的最后一个元素(要求:数组int[10],数组元素是100以内的随机数)
	 */
	@Test
	public void arrayExercise2() {
		int[] arr=new int[10];
		for(int i=0;i<arr.length-1;i++){
			arr[i]=(int)(Math.random()*100);
		}
		System.out.println("源数组："+Arrays.toString(arr));

		int max=arr[0];//则修改max的值为较大的
		for(int i=0;i<arr.length-1;i++){ //遍历剩余元素
			if(max<arr[i]){//若剩余元素大于max
				max=arr[i];//则修改max的值为较大的
			}
		}
		System.out.println("数组最大值："+max);
		arr=Arrays.copyOf(arr, arr.length+1);//扩容
		arr[arr.length-1]=max;//将最大值max赋给arr中的最后一个元素
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}

	/**
	 * 'A-Z a-z 0-9' char[62]数组，并转换成字符串输出
	 */
	@Test
	public void arrayExercise3() {
		//'A'=65
		char[] chs = new char[26];
		for(int i=0;i<chs.length;i++){
			chs[i]=(char)(i+65);
		}
		//System.out.println((char)65);
		System.out.println("26个英文字母大写字符数组："+Arrays.toString(chs));

		//'a'=97 相差32
		char[] cha = new char[26];
		for(int i=0;i<cha.length;i++){
			cha[i]=(char)(i+97);
		}
		System.out.println("26个英文字母小写字符数组："+Arrays.toString(cha));

		chs=Arrays.copyOf(chs, 52);
		for(int i=26;i<chs.length;i++){
			chs[i]=(char)(i-26+97);
		}
		System.out.println("52个英文字母大小写字符数组："+Arrays.toString(chs));

		//'0'=48
		chs=Arrays.copyOf(chs, 62);
		for(int i=52;i<chs.length;i++){
			chs[i]=(char)(i-52+48);
		}
		System.out.println("52个英文字母大小写字符和0-9数组："+Arrays.toString(chs));
		//Arrays.toString(chs) 返回值是String 已经转换成字符串
	}

	/**
	 * 查询数组最小值，并将数组扩容形成新数组
	 * 创建程序，实现查询数组中最小值的功能，并将最小值放入数组的第一位。需求为：
	 * 创建一个长度为 10 的数组，数组内放置 10 个 0 到 99 之间（包含0，包含99）的随机整数作为数组元素，
	 * 要求查询出数组中的最小值，并打印显示在界面上。然后，将数组的长度扩容为 11，
	 * 将查询到的数组最小值记载为数组的第一个元素，并打印扩容后的数组的内容。
	 */
	@Test
	public void arrayExercise4() {
        //创建一个 10 个长度的数组
        int[] arr = new int[10];

        //随机生成 10 个0－99之间的数值，放入数组
        Random ran = new Random();
        for(int i=0;i<arr.length;i++){
            arr[i] = ran.nextInt(100);
        }
        //打印数组中的数据
        System.out.println("数组中的数据为：" + Arrays.toString(arr));

        //查询最小值
        int min = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(min > arr[i]) {
                min = arr[i];
            }
        }
        System.out.println("最小值是：" + min);

        //创建新数组
        int[] arrNew = new int[arr.length + 1];
        //将原数组的元素拷贝进入新数组
        System.arraycopy(arr, 0, arrNew, 1, arrNew.length-1);

        //最小值放入新数组第一位，并打印新数组
        arrNew[0] = min;
        System.out.println("新数组中的数据为：" + Arrays.toString(arrNew));
	}

	/**
	 * 冒泡排序
	 */
	@Test
	public void arrayExercise5() {
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
	}

	/**
	 * 随机生成数组
	 * 封装一个方法generateArray，该方法实现生成指定长度的int数组，
	 * 该数组的元素为0到指定范围内的随机数，并将该数组返回。
	 */
    /**
     * 该方法实现生成指定元素数目的数组，该数组的元素为指定范围内的随机数，并返回该数组
     *
     * @param length
     *            指定数组的长度
     * @param max
     *            指定范围的最大值
     * @return 生成的数组
     */
    public static int[] generateArray(int length, int max) {
        // 创建长度为length的数组
        int[] arr = new int[length];
        // 循环生成length个数值，并赋值给数组的元素
        Random ran = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = ran.nextInt(max);
        }
        // 返回生成的数组
        return arr;
    }
	@Test
	public void methodExercise1() {
		//调用生成数组的方法
        int[] arr=generateArray(6,100);
        //输出生成的数组
        System.out.println("生成的数组为："+Arrays.toString(arr));
	}

	/**
	 *  猜字母游戏——实现游戏等级
	 *  为猜字母游戏添加游戏等级。游戏等级设为三等：5、7和9，代表所需要猜测的字母个数。游戏开始时，
	 *  由玩家选择游戏等级（5，7，9）。如果选择7，则会随机产生7个字符，然后玩家输入一个字符串包含7个字符，
	 *  看这7个字符和随机产生的7个字符比较，看是否正确，并统计分数。另外，如果输入其它，重新提示输入游戏等级。
	 */
	 /**
     * 随机生成需要猜测的字母序列
     *
     * @return 存储随机字符的数组
     */
    public static char[] generate(int level) {
        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z' };
        boolean[] flags = new boolean[letters.length];
        char[] chs = new char[level];
        for (int i = 0; i < chs.length; i++) {
            int index;
            do {
                index = (int) (Math.random() * (letters.length));
            } while (flags[index]);// 判断生成的字符是否重复
            chs[i] = letters[index];
            flags[index] = true;
        }
        return chs;
    }
    /**
     * 比较玩家输入的字母序列和程序所生成的字母序列，逐一比较字符及其位置，并记载比较结果
     *
     * @param chs
     *            程序生成的字符序列
     * @param input
     *            玩家输入的字符序列
     * @return 存储比较的结果。返回值int数组 的长度为2，其中，索引为0的位置
     *         用于存放完全猜对的字母个数(字符和位置均正确)，索引为1的位置用于存放猜对的字母个数(字符正确，但是位置不正确)。
     */
    public static int[] check(char[] chs, char[] input) {
        int[] result = new int[2];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < chs.length; j++) {
                if (input[i] == chs[j]) {// 判断字符是否正确
                    result[1]++;
                    if (i == j) {// 判断位置是否正确
                        result[0]++;
                    }
                    break;
                }
            }
        }
        return result;
    }
	@Test
	public void methodExercise2() {
	       // 表示玩家猜测的次数
        int count = 0;
        // 用于保存判断的结果
        int[] result = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("GuessingGame>欢迎尝试猜字母游戏！");
        // 表示游戏等级，默认为5
        int level = 5;
        do {
            System.out.print("GuessingGame>请输入游戏级别（5、7、9）？");
            level = scanner.nextInt();
        } while (level != 5 && level != 7 && level != 9);
        // 表示猜测的字符串
        char[] chs = generate(level);
        System.out.println(chs);
        System.out.println("GuessingGame>游戏开始，请输入你所猜的5个字母序列：（exit——退出）");
        while (true) {
            String inputStr = scanner.next().trim().toUpperCase();
            if ("EXIT".equals(inputStr)) {
                System.out.println("GuessingGame>谢谢你的尝试，再见！");
                break;
            }
            char[] input = inputStr.toCharArray();
            result = check(chs, input);
            if (result[0] == chs.length) {// 完全猜对的情况
                int score = 100 * chs.length - count * 10;
                System.out.println("GuessingGame>恭喜你猜对了！你的得分是：" + score);
                break;
            } else {
                count++;
                System.out.println("GuessingGame>你猜对" + result[1] + "个字符，其中"
                        + result[0] + "个字符的位置正确！（总次数=" + count + "，exit——退出）");
            }
        }
        scanner.close();
	}

}
