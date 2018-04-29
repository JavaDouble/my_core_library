package com.learn.javase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.junit.Test;


//经典算法50题 摘自互联网  不仅是Java语言的经典算法50题，其他语言也是经典算法50题(C,C++)
//所需知识：Java语法基础，OOP面向对象和JavaSE 综合性训练
public class ClassicalAlgorithm {
	/**
	 * Java经典算法50例
	 * 【程序1】   题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
	 * 小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
	 * 1.程序分析：   兔子的规律为数列1,1,2,3,5,8,13,21....
	 *
	 * 知识点：斐波那契数列Fibonacci（斐波那契数列中的每一个数字（从其开始2）都是前面两个数字的和）
	 * 斐波那契数列采用递归算法实现（递归：简单来说就是在方法里面自己调用自己  是重复调用函数自身实现循环。
	 * 								运行效率较低，消耗内存，容易出现死循环，应尽量避免递归）
	 * 与之类似的是：迭代  迭代是函数内某段代码实现循环。
	 * 迭代与普通循环的区别是：循环代码中参与运算的变量同时是保存结果的变量，当前保存的结果作为下一次循环计算的初始值。
	 * 递归循环中，遇到满足终止条件的情况时逐层返回来结束。迭代则使用计数器结束循环。
	 * 当然很多情况都是多种循环混合采用，这要根据具体需求。
	 *
	 * 迭代经典例子就是实数的累加(for循环)，比如计算1-100所有实数的和。
	 */
	public static int getNum(int i){
		  if((i==1)||(i==2)){
		   return 1;
		  }else{
			  return getNum(i-1)+getNum(i-2);
		  }
	}
	@Test
	public void arithmetic1() {
		for (int i = 1; i <= 24; i++) {
			int num = getNum(i);
			System.out.println("第" + i + "月份，兔子的数量为： " + num);
		}
	}

	/**
	 * 【程序2】   题目：判断101-200之间有多少个素数，并输出所有素数。
	 * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。
	 */
	@Test
	public void arithmetic2() {
		int count=0;
		for(int j=101;j<=200;j++){
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
		System.out.println("\n101到200，一共有"+count+"个质数");
	}

	/**
	 * 【程序3】   题目：打印出所有的 水仙花数 ，所谓 水仙花数 是指一个三位数，其各位数字立方和等于该数本身。
	 * 例如：153是一个 水仙花数 ，因为153=1的三次方＋5的三次方＋3的三次方。
	 * 1.程序分析：利用for循环控制100-999个数，每个数分解出个位，十位，百位。
	 */
	@Test
	public  void arithmetic3() {
		for (int n = 100; n < 1000; n++) {
			int s = 0;
			for (int n1 = n; n1 > 0; n1 /= 10) {
				int t = n1 % 10;
				s += t * t * t;
			}
			if (s == n) {
				System.out.print(n+" ");
			}
		}
	}

	/**
	 * 【程序4】   题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
	 * 1.程序分析：对n进行分解质因数，应先找到一个最小的质数i，然后按下述步骤完成：
	 * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
	 * (2)如果n > i，但n能被i整除，则应打印出i的值，并用n除以i的商,作为新的正整数你,重复执行第一步。
	 * (3)如果n不能被i整除，则用i+1作为i的值,重复执行第一步。
	 *
	 */

	@Test
	public  void arithmetic4() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入n的值：");
        int n = in.nextInt();
        System.out.print( "分解质因数：" + n +"=");
        for(int i=2;i<=n;i++){
            while(n%i==0 && n!=i){
                n=n/i;
                System.out.print(i+"*");
            }
            if(n==i){
                System.out.println(i);
                break;
            }
        }
        in.close();
	}

	/**
	 * 【程序5】   题目：利用条件运算符的嵌套来完成此题：
	 * 学习成绩=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
	 * 1.程序分析：(a>b)?a:b这是条件运算符的基本例子。
	 */
	@Test
	public  void arithmetic5() {
        System.out.println("请输入N的值：");
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        System.out.println(N >= 90 ?"A": (N >= 60 ? "B":"C"));
        in.close();
	}

	/**
	 * 【程序6】   题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
	 * 1.程序分析：利用辗除法。
	 */
	 // 递归法求最大公约数
    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {// 若余数为0,返回最大公约数
            return n;
        } else { // 否则,进行递归,把n赋给m,把余数赋给n
            return maxCommonDivisor(n, m % n);
        }
    }
	// 循环法求最大公约数
    public static int maxCommonDivisor2(int m, int n) {

        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {// 在余数不能为0时,进行循环
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;// 返回最大公约数
    }

    // 求最小公倍数
    public static int minCommonMultiple(int m, int n) {
        return m * n / maxCommonDivisor(m, n);
    }

	@Test
	public  void arithmetic6() {
		Scanner scan = new Scanner(System.in);// 接收控制台输入的信息
		System.out.println("请输入第一个整数：");
		int num1 = scan.nextInt(); // 取出控制台输入的信息
		System.out.print("请输入第二个整数：");
		int num2 = scan.nextInt(); // 取出控制台输入的信息
		System.out.println("最大公约数是："+maxCommonDivisor(num1, num2));// 调用maxCommonDivisor()方法
		System.out.println("最小公倍数是："+minCommonMultiple(num1, num2));// 调用minCommonMultiple()方法
		scan.close();
	}

	/**
	 * 【程序7】   题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
	 * 1.程序分析：利用for循环语句,if条件语句。
	 */
	@Test
	public void arithmetic7() {
		System.out.println("请输入字符串：");
		Scanner sc = new Scanner(System.in);
		// 注：Scanner类中的next()方法遇到空格就不进去了，比如说输入haha nene就只会得到haha，
		// 空格后面的都被忽略了，因此要用nextLine()
		String str = sc.nextLine();
		// 用来检测中文的正则表达式
		String reg1 = "[\u4e00-\u9fa5]";
		int count1 = 0;
		// 用来检测字母的正则表达式
		String reg2 = "[a-zA-Z]";
		int count2 = 0;
		// 用于统计空格数
		int count3 = 0;
		// 用于统计数字个数
		String reg4 = "[0-9]";
		int count4 = 0;
		int others=0;//其他字符
		// 获得的键盘输入都是String，因为要 将string中的每个字符进行匹配所以将每个char都存入String数组中
		char[] charArr = str.toCharArray();
		String[] strArr = new String[charArr.length];
		for (int i = 0; i < charArr.length; i++) {
			strArr[i] = String.valueOf(charArr[i]);
			if (strArr[i].matches(reg1)) {
				count1++;
			}else if (strArr[i].matches(reg2)) {
				count2++;
			}else if (strArr[i].matches(" ")) {
				count3++;
			}else if (strArr[i].matches(reg4)) {
				count4++;
			}else{
				others++;
			}
		}
		System.out.println("汉字的个数：" + count1);
		System.out.println("字母的个数：" + count2);
		System.out.println("空格的个数：" + count3);
		System.out.println("数字的个数：" + count4);
		System.out.println("其他字符的个数：" + others);
		sc.close();
	}

	/**
	 * 【程序8】   题目：求s = a + aa + aaa + aaaa + aa...a的值，其中a是一个数字。
	 * 例如2 + 22 + 222 + 2222 + 22222(此时共有5个数相加)，几个数相加 由键盘控制。
	 * 1.程序分析：关键是计算出每一项的值。
	 */
	@Test
	public  void arithmetic8() {
		Scanner in = new Scanner(System.in);
        System.out.println("请输入a的值:");
        int a = in.nextInt();
        System.out.println("请输入n个数:");
        int n = in.nextInt();
        int s = 0,t=0;
        for (int i = 1; i <= n; i++) {
            t += a;
            a = a*10;
            s += t;
        }
        System.out.println(s);
        in.close();
	}

	/**
	 * 【程序9】 题目：一个数如果恰好等于它的因子之和，这个数就称为"完数"。
	 * 	例如6=1＋2＋3。编程找出1000以内的所有完数。
	 */
	@Test
	public void arithmetic9() {
		int s;
		for (int i = 1; i <= 1000; i++) {
			s = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					s = s + j;
				}
			}
			if (s == i) {
				System.out.print(i + " ");
			}
		}
	}

	/**
	 * 【程序10】 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在第10次落地时，
	 * 				共经过多少米？第10次反弹多高？
	 */
	@Test
	public  void arithmetic10() {
        double s = 0;
        double h = 100;
        for (int i = 1; i <= 10; i++) {
            s += h;
            h = h/2;
            s += h;
        }
        System.out.println("在第10次落地时，共经过路程："+s+"米");
        System.out.println("第10次反弹高度："+h+"米");
	}

	/**
	 * 【程序11】   题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
	 * 	1.程序分析：可填在百位、十位、个位的数字都是1、2、3、4。组成所有的排列后再去掉不满足条件的排列。
	 */
	@Test
	public void arithmetic11() {
		int count = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 4; k++) {
					if (i != j && j != k && i != k) {
						count += 1;
						if (count % 10 == 0) {
							System.out.println();
						}
						System.out.print(i * 100 + j * 10 + k + "\t");
					}
				}
			}
		}
		System.out.println("\r共" + count + "个三位数");
	}

	/**
	 * 【程序12】  题目：企业发放的奖金根据利润提成。
	 * 	利润(I)低于或等于10万元时，奖金可提10%；
	 * 	利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部分，可提成7.5%；20万到40万之间时，
	 * 															高于20万元的部分，可提成5%；
	 * 	40万到60万之间时高于40万元的部分，可提成3%；60万到100万之间时，高于60万元的部分，
	 * 									可提成1.5%，高于100万元时，超过100万元的部分按1%提成.
	 * 从键盘输入当月利润lirun，求应发放奖金总数sum？
	 * 1.程序分析：请利用数轴来分界，定位。注意定义时需把奖金定义成长整型。
	 */
	@Test
	public  void arithmetic12() {
        double sum;
        System.out.println("输入当月利润：(万元)");
        Scanner in = new Scanner(System.in);
        double lirun = in.nextDouble();
        if (lirun <= 10) {
            sum = lirun * 0.1;
        } else if (lirun <= 20) {
            sum = 10*0.1 + (lirun - 10) * 0.075;
        } else if (lirun <= 40) {
            sum = 10*0.1 + 10*0.075 + (lirun - 20) * 0.05;
        } else if (lirun <= 60) {
            sum = 10*0.1 + 10*0.075 + 10*0.05 + (lirun - 40) * 0.03;
        } else if (lirun <= 100) {
            sum = 10*0.1 + 10*0.075 + 10*0.05 + 10*0.03 + (lirun - 60) * 0.015;
        } else {
        	sum = 10*0.1 + 10*0.075 + 10*0.05 + 10*0.03 + 10*0.015 +
        												(lirun - 100) * 0.01;
        }
        System.out.println("应发的奖金是："+sum+"(万元)");
        in.close();
	}

	/**
	 * 【程序13】   题目：一个整数，它加上100后是一个完全平方数，加上168又是一个完全平方数，请问该数是多少？
	 * 1.程序分析：在10万以内判断，先将该数加上100后再开方，再将该数加上168后再开方，如果开方后的结果满足如下条件
	 * 																	，即是结果。
	 */
	@Test
	public  void arithmetic13() {
        for(int x=1;x<100000;x++){
            if(Math.sqrt(x+100)%1==0)
            if(Math.sqrt(x+100+168)%1==0)
              System.out.println(x+"加上100后是一个完全平方数，加上168又是一个完全平方数");
          }
	}

	/**
	 * 【程序14】 题目：输入某年某月某日，判断这一天是这一年的第几天？
	 * 1.程序分析：以3月5日为例，应该先把前两个月的加起来，然后再加上5天即本月的第几天，特殊情况，
	 * 												闰年且输入月份大于3时需考虑多加一天。
	 */
	@Test
	public  void arithmetic14() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年份（例如：2012）");
        int year = scanner.nextInt();
        System.out.println("请输入月份（例如：１）");
        int month = scanner.nextInt();
        System.out.println("请输入日（例如：１）");
        int day = scanner.nextInt();

        int sum = 0;
        int leap;
        /*先计算某月以前月份的总天数*/
        switch(month){
		case 1:
			sum = 0;
			break;
		case 2:
			sum = 31;
			break;
		case 3:
			sum = 59;
			break;
		case 4:
			sum = 90;
			break;
		case 5:
			sum = 120;
			break;
		case 6:
			sum = 151;
			break;
		case 7:
			sum = 181;
			break;
		case 8:
			sum = 212;
			break;
		case 9:
			sum = 243;
			break;
		case 10:
			sum = 273;
			break;
		case 11:
			sum = 304;
			break;
		case 12:
			sum = 334;
			break;
		default:
			System.out.println("data error");
			break;
		}
        /* 再加上某天的天数 */
		sum += day;
		//判断是不是闰年
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
			leap = 1;
		}else{
			leap = 0;
		}
		//判断是否是闰年
        //GregorianCalendar:判断年份是否是闰年的方法
        GregorianCalendar gre = new GregorianCalendar();
        boolean isLeapYear=gre.isLeapYear(year);//返回true:是闰年，false：不是闰年

		/* 如果是闰年且月份大于2,总天数应该加一天 */
		if (leap == 1 && month > 2) {
			sum++;
		}
		System.out.println("这一天是"+year+"年的第"+sum+"天");

        System.out.println("请输入年份（例如：2012）");
        year = scanner.nextInt();
        System.out.println("请输入月份（例如：１）");
        month = scanner.nextInt();
        System.out.println("请输入日（例如：１）");
        day = scanner.nextInt();
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        sum = cal.get(Calendar.DAY_OF_YEAR);
        System.out.println("这一天是"+year+"年的第"+sum+"天");
        scanner.close();
	}

	/**
	 * 【程序15】 题目：输入三个整数x,y,z，请把这三个数由小到大输出。
	 * 1.程序分析：我们想办法把最小的数放到x上，先将x与y进行比较，如果x>y则将x与y的值进行交换，
	 * 然后再用x与z进行比较，如果x>z则将x与z的值进行交换，这样能使x最小。
	 */
	@Test
	public  void arithmetic15() {
		 System.out.print("请输入三个数:");
		Scanner scanner=new Scanner(System.in);
		int a=scanner.nextInt();
		int b=scanner.nextInt();
		int c=scanner.nextInt();
		if(a>b){
			int tmp=a;
			a=b;
			b=tmp;
		}
		if(a>c){
			int tmp=a;
			a=c;
			c=tmp;
		}
		if(b>c){
			int tmp=b;
			b=c;
			c=tmp;
		}
		System.out.print("三个数排序后："+a+","+b+","+c);

		int[] arr=new int[3];
		for(int i=0;i<3;i++){
			arr[i]=scanner.nextInt();
		}
		Arrays.sort(arr);
		System.out.print("三个数排序后："+Arrays.toString(arr));
		scanner.close();
	}

	/**
	 * 【程序16】 题目：输出9*9口诀乘法表。
	 * 1.程序分析：分行与列考虑，共9行9列，i控制行，j控制列。
	 */
	@Test
	public  void arithmetic16() {
		for(int i=1;i<=9;i++){//控制行
			for(int j=1;j<=i;j++){//控制列
				System.out.print(j+"*"+i+"="+i*j+"\t");
			}
			System.out.println();//换行
		}
	}

	/**
	 * 【程序17】   题目：猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不瘾，
	 * 又多吃了一个第二天早上又将剩下的桃子吃掉一半，又多吃了一个。以后每天早上都吃了前一天剩
	 * 下的一半零一个。到第10天早上想再吃时，见只剩下一个桃子了。求第一天共摘了多少。
	 *
	 * 1.程序分析：采取逆向思维的方法，从后往前推断。
	 */
	@Test
	public  void arithmetic17() {
		int sum = 1;
		for (int i = 0; i < 9; i++) {
			sum = (sum + 1) * 2;
		}
		System.out.println("第一天共摘" + sum);
	}

	/**
	 * 【程序18】   题目：两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。
	 * 已抽签决定比赛名单。有人向队员打听比赛的名单。a说他不和x比，c说他不和x,z比，请编程序找出三队赛手的名单。
	 */
	@Test
	public void arithmetic18() {
		for(char i ='x';i<='z';i++){
		    for(char j ='x';j<='z';j++){
		        if(i!=j){//这里放一个判断，比赛队员不能重复比赛，判断写在外面又可以减少循环次数
		            for(char k = 'x';k<='z';k++){
		                if(i!=k&&j!=k){//同上，排除相同
		                    if(i!='x'&&k!='x'&&k!='z')//这里根据题意排除掉不符合的名单
		                      System.out.println("a和"+i+"\n"+"b和"+j+"\n"+
		                    		  			"c和"+k+"\n");
		            }
		            }
		        }
		    }
		}
	}

	/**
	 * 【程序19】  题目：打印出如下图案（菱形）
	 * 1.程序分析：先把图形分成两部分来看待，前四行一个规律，后三行一个规律，利用双重for循环，
	 * 		第一层控制行，第二层控制列。
	 * 三角形：
						*
						***
						******
						********
						******
						***
						*
		菱形：
					   *
					  ***
					 *****
					*******
					 *****
					  ***
					   *
	 */
	@Test
	public  void arithmetic19() {
		int i = 0;
		int j = 0;
		for (i = 1; i <= 4; i++) {
			for (j = 1; j <= 2 * i - 1; j++){
				System.out.print("*");
			}
			System.out.println();
		}
		for (i = 3; i >= 1; i--) {
			for (j = 1; j <= 2 * i - 1; j++){
				System.out.print("*");
			}
			System.out.println();
		}

		i = 0;
		j = 0;
		for (i = 1; i <= 4; i++) {
			for (int k = 1; k <= 4 - i; k++){
				System.out.print(" ");
			}
			for (j = 1; j <= 2 * i - 1; j++){
				System.out.print("*");
			}
			System.out.println();
		}
		for (i = 3; i >= 1; i--) {
			for (int k = 1; k <= 4 - i; k++){
				System.out.print(" ");
			}
			for (j = 1; j <= 2 * i - 1; j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}

	/**
	 * 【程序20】   题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前20项之和。
	 * 1.程序分析：请抓住分子与分母的变化规律。
	 */
	@Test
	public  void arithmetic20() {
        float fm = 1.0f;
        float fz = 1.0f;
        float temp;
        float sum = 0f;
        int count = 0;
        for (int i = 0; i < 20; i++) {
            temp = fm;
            fm = fz;
            fz = fz + temp;
            if(count++%5==0){
            	System.out.println();
            }
            System.out.print((int) fz + "/" + (int) fm+"\t");
            sum += fz / fm;
        }
        System.out.println("\n数列前20项之和："+sum);
	}

	/**
	 * 【程序21】   题目：求1+2!+3!+...+20!的和。
	 * 		1.程序分析：此程序只是把累加变成了累乘。
	 */
	@Test
	public void arithmetic21() {
		long sum = 0;
		long fac = 1;
		for (int i = 1; i <= 20; i++) {
			fac *= i;
			sum += fac;
		}
		System.out.println("1+2!+3!+...+20!的和是："+sum);
	}

	/**
	 * 【程序22】   题目：利用递归方法求5!。
	 * 	1.程序分析：递归公式：f(n)=f(n-1)*4!
	 */
	 public static long fac(int n) {
	        long value = 0;
	        if (n == 1 || n == 0) {
	            value = 1;
	        } else if (n > 1) {
	            value = n * fac(n - 1);
	        }
	        return value;
	    }
	@Test
	public  void arithmetic22() {
		System.out.println("请输入一个数：");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(n + "的阶乘为：" + fac(n));
		in.close();
	}

	/**
	 * 【程序23】   题目：有5个人坐在一起，问第五个人多少岁？他说比第4个人大2岁。问第4个人岁数，他说比第3个人大2岁。
	 * 	问第三个人，又说比第2人大两岁。问第2个人，说比第一个人大两岁。最后问第一个人，他说是10岁。请问第五个人多大？
	 *
	 * 1.程序分析：利用递归的方法，递归分为回推和递推两个阶段。要想知道第五个人岁数，需知道第四人的岁数，依次类推，
	 * 										推到第一人（10岁），再往回推。
	 */
	//递归求解
    public static int getAge(int n) {
        if (n == 1) {
            return 10;
        }
        return 2 + getAge(n - 1);
    }
	@Test
	public  void arithmetic23() {
		//直接求解
		int n = 10;
		for (int i = 0; i < 4; i++) {
			n = n + 2;
		}
		System.out.println("第五个人" + n + "岁");
		 System.out.println("第五个的年龄为" + getAge(5)+"岁");
	}

	/**
	 * 【程序24】   题目：给一个不多于5位的正整数，要求：一、求它是几位数，二、逆序打印出各位数字。
	 */
	@Test
	public  void arithmetic24() {
        System.out.println("请输入：");
        Scanner in = new Scanner(System.in);
        String str = in.next();
        if (str.matches("\\d+")) { //正则表达式
            System.out.println("输入的是" + str.length() + "位数");
            StringBuffer buf = new StringBuffer(str);
            System.out.println(buf.reverse());//字符串反转
        }
        in.close();
	}

	/**
	 * 【程序25】   题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
	 */
	@Test
	public  void arithmetic25() {
        System.out.println("请输入：");
        Scanner in = new Scanner(System.in);
        String str = in.next();
        int l = Integer.parseInt(str);//转换成整数
        if (l < 10000 || l > 99999) {
            System.out.println("输入错误！");
            System.exit(0);
        }
        boolean is=false;
        char[] ch = str.toCharArray();
        for(int i=0;i<ch.length/2;i++){
            if(ch[i]!=ch[ch.length-i-1]){
                is=false;
            }else{
                is=true;
            }
        }
        if(is){
            System.out.println("这是一个回文!");
        }else{
            System.out.println("不是一个回文!");
        }
        in.close();
	}

	/**
	 * 【程序26】   题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则继续判断第二个字母。
	 * 1.程序分析：用情况语句比较好，如果第一个字母一样，则判断用情况语句或if语句判断第二个字母。
	 */
	@Test
	public  void arithmetic26() {
		//monday,tuesday,thursday,wednesday,friday,saturday,sunday..
		  String s=JOptionPane.showInputDialog("please input the letter one by one:").trim();
	        if(s.charAt(0)=='m'){
	            System.out.println("today is monday,^_^");
	        }else if(s.charAt(0)=='w'){
	            System.out.println("today is wednesday,^_^");
	        }else if(s.charAt(0)=='f'){
	        	System.out.println("today is friday,..");
	        }else if(s.charAt(0)=='t'){
	            if(s.charAt(1)=='u'){
	            	System.out.println("today is tuesday,^_^");
	            }else if(s.charAt(1)=='h'){
	            	System.out.println("today is thursday,^_^");
	            }else{
	            	System.out.println("wrong input..");
	            }
	        }else if(s.charAt(0)=='s'){
	            if(s.charAt(1)=='a'){
	            	System.out.println("today is saturday,^_^");
	            }else if(s.charAt(1)=='u'){
	            	 System.out.println("today is sunday,^_^");
	            }else{
	                System.out.println("wrong input..");
	            }
	        }else{
	            System.out.println("wrong input..");
	        }
	}

	/**
	 * 【程序27】   题目：求100之内的素数
	 */
	@Test
	public  void arithmetic27() {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入查找质数的范围：2~");
		int num = sc.nextInt();
		sc.close();
		int count=0;//质数的个数
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
	 * 【程序28】   题目：对10个数进行排序。
	 * 1.程序分析：可以利用选择法，即从后9个比较过程中，选择一个最小的与第一个元素交换，下次类推，
	 * 	即用第二个元素与后8个进行比较，并进行交换。
	 * 本例代码为生成随机10个数排序，并输入1个数，插入重排序输出：
	 */
	@Test
	public  void arithmetic28() {
	     System.out.println("请输入10个数：");
	        Scanner in = new Scanner(System.in);
	        int[] arr = new int[10];
	        for (int i = 0; i < 10; i++) {
	            arr[i] = in.nextInt();
	        }
	        System.out.println("原数组为："+Arrays.toString(arr));
	        Arrays.sort(arr);
	        System.out.println("排序后为："+Arrays.toString(arr));
	        in.close();
	}

	/**
	 * 【程序29】   题目：求一个3*3矩阵主对角线元素之和。
	 * 1.程序分析：利用双重for循环控制输入二维数组，再将a[i][i]累加后输出。
	 */
	@Test
	public  void arithmetic29() {
        int sum = 0;
        int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 7, 8 } };
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    sum = sum + array[i][j];
            }
        System.out.println(sum);
	}

	/**
	 * 【程序30】   题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。
	 * 1.程序分析：首先判断此数是否大于最后一个数，然后再考虑插入中间的数的情况，插入后此元素之后的数，依次后移一个位置。
	 */
	@Test
	public  void arithmetic30() {
		System.out.println("请输入一个数字：");
		Scanner scanner=new Scanner(System.in);
		int number=scanner.nextInt();
		int[] arr=new int[10];
		Random random=new Random();
		for(int i=0;i<arr.length;i++){
			arr[i]=random.nextInt(100);
		}
		System.out.println("排序前数组："+Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("排序后数组："+Arrays.toString(arr));
		arr=Arrays.copyOf(arr, arr.length+1);
		arr[arr.length-1]=number;
		System.out.println("排序前新数组："+Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("排序后新数组："+Arrays.toString(arr));
		scanner.close();
	}

	/**
	 * 【程序31】   题目：将一个数组逆序输出。
	 * 	程序分析：用第一个与最后一个交换。用逆序循环控制变量输出：
	 */
	@Test
	public  void arithmetic31() {
		int[] arr=new int[10];
		Random random=new Random();
		for(int i=0;i<arr.length;i++){
			arr[i]=random.nextInt(100);
		}
		System.out.println("排序前数组："+Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.print("逆序排序后数组：");
		for(int i=arr.length-1;i>=0;i--){
			System.out.print(arr[i]+" ");
		}
	}

	/**
	 *	【程序32】   题目：取一个整数a从右端开始的第4～7位数字。
	 */
	@Test
	public  void arithmetic32() {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入一个7位以上的正整数：");
        long l = in.nextLong();
        String str = Long.toString(l);
        char[] ch = str.toCharArray();
        int j=ch.length;
        if (j<7){
        	System.out.println("输入错误！");
        } else {
            System.out.println("截取从右端开始的4～7位是："+ch[j-7]+ch[j-6]+ch[j-5]+ch[j-4]);
        }
        in.close();
	}

	/**
	 * 【程序33】   题目：打印出杨辉三角形（要求打印出10行如下图）
	 * 1.程序分析：
				1
				1   1
				1   2   1
				1   3   3   1
				1   4   6   4   1
				1   5   10   10   5   1
	 */
	@Test
	public  void arithmetic33() {
		System.out.println("how many lines you want:");
		Scanner in = new Scanner(System.in);
		int lines = in.nextInt();
		int[] a = new int[lines + 1];
		int previous = 1;
		for (int i = 1; i <= lines; i++) {
			for (int j = 1; j <= i; j++) {
				int current = a[j];
				a[j] = previous + current;
				previous = current;
				System.out.print(a[j] + " ");
			}
			System.out.println();
		}
		in.close();
	}

	/**
	 * 【程序34】   题目：输入3个数a,b,c，按大小顺序输出。
	 */
	@Test
	public  void arithmetic34() {
        System.out.println("请依次输入三个正整数：a,b,c（以空格隔开）");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int[] arr=new int[3];
        arr[0]=a;arr[1]=b;arr[2]=c;
        Arrays.sort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
        scanner.close();
	}

	/**
	 * 【程序35】   题目：输入数组，最大的与第一个元素交换，最小的与最后一个元素交换，输出数组。
	 */
	public static void swap(int[] arr,int i,int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	@Test
	public void arithmetic35() {
		System.out.println("请输入数组的长度:");
		Scanner scanner=new Scanner(System.in);
		int arrLength=scanner.nextInt();
		int[] arr=new int[arrLength];

		for(int i=0;i<arr.length;i++){
			System.out.print("输入第" + (i + 1) + "个数据");
			arr[i]=scanner.nextInt();
		}
		System.out.println("原始数组："+Arrays.toString(arr));

		//for循环找到最大值和最小值，max是最大值的下标，min是最小值的下标
		int max=0;
		int min=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]>arr[max]){
				max=i;//遍历数组,如果大于arr[max]，就把他的数组下标赋给max
			}
			if(arr[i]<arr[min]){
				min=i;//同上，如果小于arr[min],就把他的数组下标赋给min
			}
		}
		System.out.println("原始数组最大值："+arr[max]+"最小值："+arr[min]);
		//首先交换arr[0]和最大值arr[max]
	    swap(arr, 0 ,max);
		//首先交换arr[min]和最大值arr[arr.length-1]
	    swap(arr, min, arr.length-1);
		System.out.print("交换后数组："+Arrays.toString(arr));
		scanner.close();
	}

	/**
	 * 【程序36】   题目：有n个整数，使其前面各数顺序向后移m个位置，最后m个数变成最前面的m个数
	 */
	@Test
	public  void arithmetic36() {
		Scanner in = new Scanner(System.in);
		System.out.println("输入数字个数n：");
		int n = in.nextInt();
		System.out.println("输入后移位数m：");
		int m = in.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			System.out.println("请输入第" + (i + 1) + "个数:");
			list.add(in.nextInt());
		}
		System.out.println("原数据排序为："+Arrays.toString(list.toArray()));
		List<Integer> temp1 = list.subList(list.size() - m, list.size());
		List<Integer> temp2 = list.subList(0, list.size() - m);
		temp2.addAll(0, temp1);
		System.out.println("移动后排序为;"+Arrays.toString(temp2.toArray()));
		in.close();
	}

	/**
	 * 【程序37】   题目：有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，
	 * 问最后留下的是原来第几号的那位。
	 * （约瑟夫环问题，百度百科有时间复杂度最简单的数学方法）
	 *
	 * 扩展：
	 * 约瑟夫环：已知n个人(以编号1，2，3...n分别表示)围坐在一张圆桌周围。从编号为k的人开始报数，数到m的那个人出列;
	 * 他的下一个人又从1开始报数，数到m的那个人又出列;依此规律重复下去，直到圆桌周围的人全部出列。
	 *
	 * 约瑟夫环运作如下：
	 * 1、一群人围在一起坐成 环状（如：N）
	 * 2、从某个编号开始报数（如：K）
	 * 3、数到某个数（如：M）的时候，此人出列，下一个人重新报数
	 * 4、一直循环，直到所有人出列，约瑟夫环结束
	 */
	@Test
	public  void arithmetic37() {
		Scanner s = new Scanner(System.in);
        System.out.print("请输入排成一圈的人数：");
        int n = s.nextInt();
        boolean[] arr = new boolean[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = true;
        } // 数组赋值都是true

        int leftCount = n;
        int countNum = 0;
        int index = 0;
        while (leftCount > 1) {
            if (arr[index] == true) {
                countNum++;
                if (countNum == 3) {
                    countNum = 0;
                    arr[index] = false;
                    leftCount--;
                }
            }
            index++;
            if (index == n) {
                index = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == true) {
                System.out.println("原排在第" + (i + 1) + "位的人留下了。");
            }
        }
        s.close();
	}
	public static void yuesefu(int totalNum, int countNum) {
		// 初始化人数
		List<Integer> start = new ArrayList<Integer>();
		for (int i = 1; i <= totalNum; i++) {
			start.add(i);
		}
		// 从第K个开始计数
		int k = 0;
		while (start.size() > 0) {
			k = k + countNum;
			// 第m人的索引位置
			k = k % (start.size()) - 1;
			// 判断是否到队尾
			if (k < 0) {
				System.out.println(start.get(start.size() - 1));
				start.remove(start.size() - 1);
				k = 0;
			} else {
				System.out.println(start.get(k));
				start.remove(k);
			}
		}
	}
	@Test
	public  void arithmetic37_2() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入总人数：");
		int totalNum = scanner.nextInt();
		System.out.print("请输入报数的大小：");
		int cycleNum = scanner.nextInt();
		yuesefu(totalNum, cycleNum);
		scanner.close();
	}

	/**
	 * 【程序38】   题目:写一个函数，求一个字符串的长度，在main函数中输入字符串，并输出其长度。
	 */
	@Test
	public  void arithmetic38() {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String  str= in.nextLine();
		System.out.println(str.length());
		in.close();
	}

	/**
	 * 【程序39】  题目：编写一个函数，输入n为偶数时，调用函数求1/2+1/4+...+1/n,
	 * 										当输入n为奇数时，调用函数1/1+1/3+...+1/n
	 */
	@Test
	public  void arithmetic39() {
        Scanner in = new Scanner(System.in);
        System.out.println("输入n的值：");
        int n = in.nextInt();
        double result=0.0;
        if(n % 2 != 0){//奇数，1/1+1/3+...+1/n
        	for(int i=1;i<=n;i=i+2){
        		result += 1/(double)i;
        	}
        }
        if(n % 2 == 0){//偶数，1/2+1/4+...+1/n
            for (int i = 2; i <= n; i = i + 2) {
                result += 1/(double) i;
            }
        }
        System.out.println("函数和为"+result);
        in.close();
	}

	/**
	 * 【程序40】  题目：字符串排序。（利用容器类中的sort方法）
	 */
	@Test
	public  void arithmetic40() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("010102");
		list.add("010003");
		list.add("010201");
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("字符串排序后："+list.get(i));
		}
	}

	/**
	 * 【程序41】   题目：海滩上有一堆桃子，五只猴子来分。第一只猴子把这堆桃子平均分为五份，多了一个，
	 * 		这只猴子把多的一个扔入海中，拿走了一份。第二只猴子把剩下的桃子又平均分成五份，又多了一个，
	 * 它同样把多的一个扔入海中，拿走了一份，第三、第四、第五只猴子都是这样做的，问海滩上原来最少有多少个桃子？
	 */
	@Test
	public  void arithmetic41() {
        int sum = 0;
        for (int i = 6;; i++) {// 最少6个分最后一次
            sum = i;// 桃子数
            for (int j = 0; j < 5; j++) {// 分的次数循环
                if ((sum - 1) % 5 == 0 && j < 5) {// 如果扔一个后能均分5份，继续分
                    sum = (sum - 1) / 5 * 4;// 每分一次剩余桃子数
                    if (j == 4) {// 如果已分5次，且仍能除尽，输出，退出程序
                        System.out.println("海滩上原来最少有"+i+"个桃子");
                        //海滩上原来最少有3121个桃子
                        System.exit(0);
                    }
                }
            }
        }
	}

	/**
	 * 【程序42】   题目：809*??=800*??+9*??+1。其中??代表的两位数,8*??的结果为两位数，
	 * 9*??的结果为3位数。求??代表的两位数，及809*??后的结果。（本题为无解，去掉1有解）
	 */
	@Test
	public  void arithmetic42() {
	       int n = 0;
	        boolean flag = false;
	        for(int i=10; i<100; i++)
	          if(809*i == 800*i+9*i+1){
	            flag = true;
	            n = i;
	            break;
	          }
	        if(flag)
	          System.out.println(n);
	        else
	          System.out.println("无符合要求的数！");
	}

	/**
	 * 【程序43】   题目：求0—7所能组成的奇数个数。
	 */
	@Test
	public  void arithmetic43() {
		// 因为是奇数，所以个位只能是1，3，5，7共4种，前面可随便排列
        int count = 4;// 个位的4种
        // 2位时，十位有8种，个位4种，8×4
        // 3位时，8×8×4……
        for (int i = 1; i < 8; i++) {
            count = 8 * count;
        }
        System.out.println("0—7所能组成的奇数个数有:" + count+"个可能");

        /*
         * 算法思想：
         * 这个问题其实是一个排列组合的问题，设这个数为sun=a1a2a3a4a5a6a7a8,a1-a8表示这个数的某位的数值，
         * 当一个数的最后一位为奇数时，那么这个数一定为奇数，不管前面几位是什么数字。如果最后一位数为偶数，
         * 则这个数一定为偶数。
         * a1-a8可以取0-7这个八个数字，首位数字不为0。
         * 从该数为一位数到该数为8位数开始统计奇数的个数：
         * 1.当只有一位数时也就是该数的最后一位，奇数个数为4
         * 2.当该数为两位数时，奇数个数为4*7=28
         * 3.当该数为三位数时，奇数个数为：4*8*7=224
         * .....
         * 8.当该数为八位数时，奇数个数为：4*8*8*8*8*8*8*7(依次为最后一位到第一位)
         */
        int sum = 0;
      	int total = 0;
        for(int i=1; i<9; i++){ //最大位数为8位
            if(i == 1 ){
                total = 4;  // 1,3,5,7
            }else if(i ==2){
                total = total*7;
            }else{
                total *= 8;
            }
            System.out.println("0~7组成" + i + "位数，有：" + total + "个");
            sum += total;
        }
        System.out.println("总计为：" + sum);
	}

	/**
	 * 【程序44】   题目：一个偶数总能表示为两个素数之和。
	 * 			（注：哥德巴赫猜想是想证明对任何大于6的自然数n之内的所有偶数可以表示为两个素数之和）
	 */
	//判断是不是素数
    public static boolean isPrime(int a) {
        boolean flag = true;
        if (a < 2) {// 素数不小于2
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) {// 若能被整除，则说明不是素数，返回false
                    flag = false;
                    break;// 跳出循环
                }
            }
        }
        return flag;
    }
	@Test
	public  void arithmetic44() {
		System.out.println("请输入一个大于2的偶数：");
		Scanner in = new Scanner(System.in);
		int inNum = in.nextInt();
        if(inNum%2!=0){
            System.out.println("您输入的不是偶数！");
          }
		for (int i = 2; i < inNum/2+1; i++) {
			if (isPrime(i) && isPrime(inNum - i)) {
				System.out.println("偶数：" + inNum + ",可以表示为两个素数的和："
											+ inNum + "=" + i + "+" + (inNum - i));
				break;
			}
        }
		in.close();
	}

	/**
	 * 【程序45】   题目：（1）判断几个9能被一个素数整除。（2）判断一个整数能被几个9整除。
	 * 	（原题：一个素数能被几个9整除）
	 */
	@Test
	public  void arithmetic45() {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入一个整数：");
		int num = in.nextInt();
		int tmp = num;
		int count = 0;
		for (int i = 0; tmp % 9 == 0;) {
			tmp = tmp / 9;
			count++;
		}
		System.out.println(num + " 能够被 " + count + " 个9 整除。");
		in.close();
	}

	/**
	 * 【程序46】   题目：两个字符串连接程序。
	 */
	@Test
	public  void arithmetic46() {
        Scanner in = new Scanner(System.in);
        System.out.println("输入第一个字符串：");
        String s1 = in.next();
        System.out.println("输入第一个字符串：");
        String s2 = in.next();
        System.out.println("连接后：\n" + s1 + s2);

        System.out.print("请输入一个字符串：");
        String str1 = in.nextLine();
        System.out.print("请再输入一个字符串：");
        String str2 = in.nextLine();
        String str = str1+str2;
        System.out.println("连接后的字符串是："+str);

        in.close();
	}

	/**
	 * 【程序47】   题目：读取7个数（1—50）的整数值，每读取一个值，程序打印出该值个数的*。
	 */
	@Test
	public  void arithmetic47() {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            int temp = in.nextInt();
            for (int j = 0; j < temp; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        in.close();
	}

	/**
	 * 【程序48】   题目：某个公司采用公用电话传递数据，数据是四位的整数，在传递过程中是加密的，
	 * 	加密规则如下：每位数字都加上5，然后用和除以10的余数代替该数字，再将第一位和第四位交换，第二位和第三位交换。
	 */
	@Test
	public  void arithmetic48() {
	       System.out.println("请输入一个四位数");
	        Scanner sc = new Scanner(System.in);
	        int number = sc.nextInt();

	        if (!((String.valueOf(number)).matches("\\d{4}"))) {
	            System.out.println("输入的不是4位数字！");
	            System.exit(0);
	        }

	        // 分解出位数
	        int a, aa, aaa, aaaa;
	        a = number % 10;
	        aa = number / 10 % 10;
	        aaa = number / 100 % 10;
	        aaaa = number / 1000;
	        // 代替数字
	        a = (a + 5) % 10;
	        aa = (aa + 5) % 10;
	        aaa = (aaa + 5) % 10;
	        aaaa = (aaaa + 5) % 10;
	        // 第一位和第四位交换
	        int temp;
	        temp = a;
	        a = aaaa;
	        aaaa = temp;
	        // 第二位和第三位交换
	        temp = aa;
	        aa = aaa;
	        aaa = temp;
	        System.out.println("交换后的数为" + aaaa + aaa + aa + a);

	        sc.close();
	}

	/**
	 * 【程序49】   题目：计算字符串中子串出现的次数。
	 */
	@Test
	public  void arithmetic49() {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入主串：");
        String str1 = in.nextLine();
        System.out.println("请输入子串：");
        String str2 = in.nextLine();
        // 生成子串长度的N个字符串数组
        String[] sa = new String[str1.length() - str2.length() + 1];
        for (int i = 0; i < sa.length; i++) {
            sa[i] = str1.substring(i, i + str2.length());
        }
        int sum = 0;
        // 子串与N个拆开的子串比对
        for (int i = 0; i < sa.length; i++) {
            if (sa[i].equals(str2)) {
                // 成功配对，计数器+1；
                sum++;
                // 因为不计算重叠的子串，所以跳过配对之后的部分拆分子串
                i = i + str2.length();
            }
        }
        System.out.println("主串中共包含" + sum + "个字串");
        in.close();
	}

	/**
	 * 【程序50】    题目：有五个学生，每个学生有3门课的成绩，从键盘输入以上数据（包括学生号，姓名，三门课成绩），
	 * 输入的格式：如：1,zhagnsan,30,40,60.
	 * 						计算出平均成绩，并把学生的信息和计算出的平分数高低顺序存放在磁盘文"stud"中。
	 *
	 * 扩展
	 *  有一个文本文件，用以下格式纪录了每个学生的考试成绩：
		Jam     语文  90   数学 95   英语99    计算机 80
		Steven  语文  100   数学90   英语90    计算机90
		Roger   语文 80    数学100   英语90    计算机100
		请写一段程序，从文本中读取出每个学生的成绩，实现以下功能：
		1) 计算每个学生的总分和平均分，并按照总分由高到低进行排序，
		将排序好的数据输出到student_score.txt中，每一行一条纪录。
		2) 对每个科目按照分数由高到低进行排序，并将排序号的数据输出到相应的文本文件中
		，每一行一条纪录，在最后一行给出平均分。
	 * @throws IOException
	 */
	@Test
	public  void arithmetic50() throws IOException {
        Scanner input = new Scanner(System.in);
        //1,zhagnsan,30,40,60.
        System.out.println("请依次输入学生号，姓名，三门课成绩，用逗号隔开,每个学生之间用句号隔开");
        String str = input.nextLine();
        String[] students = str.split("\\.");
        for(int i=0;i<students.length;i++){
          System.out.println(students[i]);
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\stud.txt"));
        for (int i = 0; i < students.length; i++) {
            String[] scores = students[i].split("\\,");
            bw.write(students[i]);
            int[] num1 = new int[3];
            int sum = 0;
            for (int m = 0; m < 3; m++) {
                num1[m] = Integer.parseInt(scores[m + 2]);
                sum += num1[m];
            }
            bw.write(".平均分：" + sum/3);
            System.out.println(sum/3);
        }
        bw.flush();
        bw.close();
        input.close();
	}

	/**
	 * 排列组合：
	 * 全排列：从n个不同元素中任取m（m≤n）个元素，按照一定的顺序排列起来，叫做从n个不同元素中取出m个元素的一个排列。
	 * 当m=n时所有的排列情况叫全排列。公式：全排列数f(n)=n!(定义0!=1)
	 *
	 * 如1,2,3三个元素的全排列为：1,2,3/1,3,2/2,1,3/2,3,1/3,1,2/3,2,1 共3*2*1=6种。
	 * A33=3*2*1=6.
	 *
	 * 常用全排列有四种实现方式：(A)字典序法	(B)递增进位制数法	(C)递减进位制数法	(D)邻位对换法
	 * 字典序法
	 * 对给定的字符集中的字符规定了一个先后关系，在此基础上规定两个全排列的先后是从左到右逐个比较对应的字符的先后。
	 * [例]字符集{1,2,3},较小的数字较先,这样按字典序生成的全排列是:123,132,213,231,312,321。
	 * [注意] 一个全排列可看做一个字符串，字符串可有前缀、后缀。
	 *
	 * 递增进位制数法
	 *
	 * 递减进位制数法
	 *
	 * 邻位对换法
	 *
	 */

	/**
	 * 全排列输出 递归
	 *
	 * @param a[]
	 *            要输出的字符数组
	 * @param m
	 *            输出字符数组的起始位置
	 * @param n
	 *            输出字符数组的长度
	 */
	public static void permutation(char a[], int m, int n) {
		int i;
		char t;
		if (m < n - 1) {
			permutation(a, m + 1, n);
			for (i = m + 1; i < n; i++) {
				t = a[m];
				a[m] = a[i];
				a[i] = t;
				permutation(a, m + 1, n);
				t = a[m];
				a[m] = a[i];
				a[i] = t;
			}
		} else {
			printResult(a);
		}
	}

	/**
	 * 输出指定字符数组
	 *
	 * @param text
	 *            将要输出的字符数组
	 */
	public static void printResult(char[] text) {
		for (int i = 0; i < text.length; i++) {
			System.out.print(text[i]);
		}
		System.out.println();
	}

	@Test
	public void test51() {

	}

	/**
	 * 去除重复的排列 排列有先后顺序
	 */
	@Test
	public void test52() {

	}

	/**
	 * 全组合 组合没有顺序
	 * 组合（combination）是一个数学名词。一般地，从n个不同的元素中，任取m（m≤n）个元素为一组，
	 * 叫作从n个不同元素中取出m个元素的一个组合。我们把有关求组合的个数的问题叫作组合问题。
	 */
	@Test
	public void test53() {

	}



class Student{
	private Integer stuId;
	private String name;
	private Double chinese;
	private Double math;
	private Double computer;

	public Student() {

	}

	public Student(Integer stuId, String name, Double chinese, Double math, Double computer) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.chinese = chinese;
		this.math = math;
		this.computer = computer;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getChinese() {
		return chinese;
	}

	public void setChinese(Double chinese) {
		this.chinese = chinese;
	}

	public Double getMath() {
		return math;
	}

	public void setMath(Double math) {
		this.math = math;
	}

	public Double getComputer() {
		return computer;
	}

	public void setComputer(Double computer) {
		this.computer = computer;
	}

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", name=" + name + ", chinese=" + chinese + ", math=" + math + ", computer="
				+ computer + "]";
	}

	}
}

