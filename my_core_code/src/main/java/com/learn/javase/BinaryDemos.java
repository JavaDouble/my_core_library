package com.learn.javase;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

//二进制的演示
public class BinaryDemos {

	/*
	 * 二进制起源：计算机只能识别有电和没电,而0和1代表有电和没电(数字电子技术【数电】和模拟电子技术【模电】)
	 *
	 * 编码与解码：简单来说，编码就是把有意义的东西换成二进制的过程，解码就是把二进制换成有意义的东西的过程。
	 * 乱码：编码和解码不一致。
	 *
	 * 广义上说，从语言的诞生开始，就产生了编码和解码。狭义的说，编码和解码产生于美国，随着计算机产生而诞生。
	 *
	 * 常用编码字符集：最早的ASCII编码字符集 0-65535 2^16=65536个字符，单字节
	 * 中国汉字编码字符集：GBK(国标编码)，二个字节，2^17=65536*2=131072 	GB2312
	 * Unicode编码字符集：（统一码、万国码、单一码）：UTF-8(1-4个字节，英文1个字节，中文3个字节)、
	 * UTF-16、UTF-32
	 *
	 * 初识二进制
	 */
	@Test
	public void test1() {
		int n=45;
		System.out.println("45的十进制："+n);//表面上是输出45 实际上调用了Integer.toString()方法
		/*
		 * 源码：    public static String toString(int i) {
			        if (i == Integer.MIN_VALUE)
			            return "-2147483648";
			        int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
			        char[] buf = new char[size];
			        getChars(i, size, buf);
			        return new String(buf, true);
			    }

			    static void getChars(int i, int index, char[] buf) {
			        int q, r;
			        int charPos = index;
			        char sign = 0;

			        if (i < 0) {
			            sign = '-';
			            i = -i;
			        }

			        // Generate two digits per iteration
			        while (i >= 65536) {
			            q = i / 100;
			        // really: r = i - (q * 100);
			            r = i - ((q << 6) + (q << 5) + (q << 2));
			            i = q;
			            buf [--charPos] = DigitOnes[r];
			            buf [--charPos] = DigitTens[r];
			        }

			        // Fall thru to fast mode for smaller numbers
			        // assert(i <= 65536, i);
			        for (;;) {
			            q = (i * 52429) >>> (16+3);
			            r = i - ((q << 3) + (q << 1));  // r = i-(q*10) ...
			            buf [--charPos] = digits [r];
			            i = q;
			            if (i == 0) break;
			        }
			        if (sign != 0) {
			            buf [--charPos] = sign;
			        }
			    }

			计算机内部(Java)只有二进制数据，在显示的时候编程语言提供的API将二进制转换成十进制显示出来
			这样计算机"表面上"就支持了10机制

			toString()方法：是一个算法 将2进制数(整数)转换成数组，再次转换成10进制字符串    是JDK做的一个优化
		 */
		System.out.println(Integer.toString(n));//"45" Integer.toString()方法 作用把变量转成字符串

		/*
		 * Integer.toBinaryString() 把变量按照原本的内存中实际存储的二进制转换成二进制
		 */
		System.out.println("45的二进制："+Integer.toBinaryString(n));
		/*
		 * Integet.parseInt()方法：是一个算法 将10进制(也可以是其他类型)字符串转换成2进制
		 * 然后默认的调用了toString()方法，所以显示的还是十进制
		 * 其他类型：java.lang.NumberFormatException
		 */
		System.out.println(Integer.parseInt("45"));
	}

	/*
	 * 初识二进制运算
	 * 计算机为什么使用二进制：计算机内部实际上都是集成电路，二进制可以代表开关二种装填，成本最优。
	 */
	@Test
	public void test2() {
		/*
		 * 高位0省略，int型32位 前面0省略
		 */
		for(int i=0;i<=10;i++){
			System.out.println(Integer.toBinaryString(i));
		}

		System.out.println("---------------------------");

		System.out.println("负数的二进制：");
		/*
		 * int型32位二进制，全部32位，第一位是符号位 0代表整数 1代表负数
		 */
		for(int i=0;i>=-10;i--){
			System.out.println(Integer.toBinaryString(i));
		}
	}

	/*
	 * 16进制：用于简写二进制，原因是二进制书写过于冗长。 0~9，a,b,c,d,e,f 是全部16进制。
	 * 缩写规则：4个二进制数代表1个16进制数
	 * 编程时候凡是需要书写二进制数据的时候，都采用16进制作为缩写。
	 * 16进制书写以0x开头。
	 */
	@Test
	public void test3() {
		//16进制用于缩写2进制
		int i=0xb5;
		System.out.println("二进制："+Integer.toBinaryString(i));
		System.out.println("十进制："+i);
	}

	/**
	 * 2进制与10进制
	 * 补码：是计算机中处理有符号的算法。
		1.补码是固定位数的2进制数
		2.补码数人为根据数据计数规律分了一半作为负数
		3.补码是计算机内部数据处理规则，再输入输出时候由Java API 进行了转换处理。
		4.输入时候：将10进制有符号数转换为补码
		5.输出时候：将补码转换为10进制有符号数
	 */
	@Test
	public void test4() {
		//用于表示负数的算法 补码
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-3));
		System.out.println(Integer.toBinaryString(-4));

		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.toBinaryString(Long.MAX_VALUE));
		System.out.println(Long.MIN_VALUE);
		System.out.println(Long.toBinaryString(Long.MIN_VALUE));

		int n=0xffffffff;
		System.out.println(n);
		System.out.println(Integer.toBinaryString(n));

		n=0x7fffffff;
		System.out.println(n);
		System.out.println(Integer.toBinaryString(n));

		n=0x80000000;
		System.out.println(n);
		System.out.println(Integer.toBinaryString(n));

		System.out.println("======================");
		System.out.println("遍历-10到10的二进制数:");
		for(int i=-10;i<=10;i++){
			System.out.println(StringUtils.leftPad(Integer.toBinaryString(i),32,"0"));
		}

		System.out.println("0："+StringUtils.leftPad(Integer.toBinaryString(0),32,"0"));
		System.out.println("1："+Integer.toBinaryString(-1));

		//补码面试题 溢出不是随机数 是个确定的数字
		System.out.println("补码面试题1："+(Integer.MIN_VALUE-Integer.MAX_VALUE));
		System.out.println("补码面试题2："+(Integer.MAX_VALUE-Integer.MIN_VALUE));
		System.out.println("补码面试题3："+(Integer.MIN_VALUE-1));

		int x=8;
		/*
		 * Integer.MAX_VALUE+1代表表盘半圈 *2代表一圈，偶数倍代表N全，基数倍半圈
		 */
		int y=x+(Integer.MAX_VALUE+1)*2;//(Integer.MAX_VALUE+1)*2代表一个表盘一圈
		System.out.println("常见面试题4："+y);//8 溢出计算

		y=x+(Integer.MAX_VALUE+1)*3;//(Integer.MAX_VALUE+1)*3代表一个表盘半圈
		System.out.println("常见面试题4："+y);//-2147483640 溢出计算

	}

	/**
	 * 2进制与10进制 运算比较
	 */
	@Test
	public void test5() {
		int num=16;//10000
		System.out.println(Integer.toBinaryString(num));
		for(int i=0;i<50;i++){
			System.out.print(i+",");
			int k=i%16;
			System.out.print(k+",");
			int m=i&15;
			System.out.print(m+",");
			//System.out.print(m==k);
			System.out.print(Integer.toBinaryString(i)+",");
			System.out.print(Integer.toBinaryString(7)+",");
			System.out.print(Integer.toBinaryString(m)+",");
			System.out.println(Integer.toBinaryString(k));
		}
	}

	/**
	 * 2进制与10进制
	 * 输出24的所有2进制数 	StringUtils类leftPad()方法左对齐
	 */
	@Test
	public void test6() {
		for(int i=0;i<24;i++){
			System.out.println(Integer.toBinaryString(i));
			System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 8, "0"));
		}
		String str=StringUtils.leftPad("10", 8, "-");
		System.out.println(str);
	}

	/**
	 *  2进制与16进制
	 */
	@Test
	public void test7() {
		//16进制是2进制缩写
		/*
		11011111 11011010 11001100 11001101
		d   f    d   a    c   c    c   d
		*/
		int i=33000444;
		System.out.println(Integer.toBinaryString(i));

		/*
		01111111 11111111 11111111 11111111
		7   F    F   F    F   F    F   F
		 */
		int a=0xf7;//11110111
		System.out.println(a);//247
		System.out.println(Integer.toBinaryString(a));//11110111

		int b=0x1c9c53c;//1110010011100010100111100
		System.out.println(Integer.toBinaryString(b));
	}

	/**
	 * 二进制运算
	 * ~ 取反运算： 补码的对称现象  公式：-n=~n+1 	数据类型范围内有效
	 * & 与运算:逻辑乘法
	 * | 或运算:有1为1
	 *
	 * 移位运算：
	 * >>> 逻辑右移位运算：负数(最高位为1的数)高位 补1 正数 补0
	 * >> 数学右移位运算：2进制数字向右移动，低位溢出舍弃，高位补0	数学意义：减倍
	 * << 左移位运算：2进制数字向左移动，高位溢出舍弃，低位补0 		 数学意义：加倍
	 *
	 * ~取反运算 补码的对称现象  公式：-n=~n+1 	数据类型范围内有效
	 */
	@Test
	public void test8() {
		int n=4;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));

		n=~n+1;
		System.out.println(n);

		n=~n;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));

		System.out.println("常见面试题：8的取反计算："+(~8));
		System.out.println("常见面试题：-8的取反计算："+(~-8));

	}

	/**
	 * & 与运算:逻辑乘法
	 * 掩码运算(mask):截取一个数据的后8位，称为掩码运算(mask)。
	 */
	@Test
	public void test9() {
		int i=0x9274b9;//00000000100100100111010010111001
		int m=0xff;//00000000000000000000000011111111//Mask 掩码8
		int k=i&m;//掩码运算 截取i的低8位数据
		int k1=(i>>>8)&m;
		int k2=(i>>>16)&m;
		int k3=(i>>>24)&m;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k1), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k2), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k3), 32, "0"));

		int i1=(k3<<24)+(k2<<16)+(k1<<8)+k;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i1), 32, "0"));
		System.out.println(i1==i);//true

		int n=i&1;//截取i的低1位数据
		//00000000000000000000000000000001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
	}

	/**
	 * | 或运算:有1为1 将数据合并
	 */
	@Test
	public void test10() {
		int i=0x9274b9;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		int n=i|1;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		System.out.println("============================");
		/*
		 * 将数据合并:
		 * b1=00000000	00000000	00000000	10011101	十六机制：9D 十进制：157
		 * b2=00000000	00000000	00000000	01101111	十六机制：6F 十进制：111
		 * b3=00000000	00000000	00000000	11101111	十六机制：EF 十进制：239
		 * b4=00000000	00000000	00000000	00110011	十六机制：33 十进制：51
		 *
		 * n=(b1<<24)|(b2<<16)|(b3<<8)|b4(b4<<0)
		 * 10011101		00000000	00000000	00000000
		 * 00000000		01101111	00000000	00000000
		 * 00000000		00000000	11101111	00000000
		 * 00000000		00000000	00000000	00110011
		 * n=10011101	01101111	11101111	00110011
		 */
		int b1=0x9D;
		int b2=0x6F;
		int b3=0xEF;
		int b4=0x33;
		n=b1<<24;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		n=b2<<16;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		n=b3<<8;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		n=b4<<0;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		n=(b1<<24)|(b2<<16)|(b3<<8)|b4;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
	}

	/**
	 * >>> 逻辑右移位运算：负数(最高位为1的数)高位 补1 正数 补0
	 *
	 */
	@Test
	public void test11() {
		int i=0x9274b9;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		int n=i>>>1;
		//00000000010010010011101001011100
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>1), 32, "0"));
	}

	/**
	 * >> 数学右移位运算：2进制数字向右移动，低位溢出舍弃，高位补0
	 * 数学意义：减倍
	 *
	 */
	@Test
	public void test12() {
		int i=0x9274b9;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		int n=i>>1;
		//00000000010010010011101001011100
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
	}

	/**
	 * << 左移位运算：2进制数字向左移动，高位溢出舍弃，低位补0
	 * 数学意义：加倍
	 */
	@Test
	public void test13() {
		int i=0x9274b9;
		//00000000100100100111010010111001
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		int n=i<<1;
		//00000001001001001110100101110010
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
	}

	/**
	 * >>与>>>的区别：
	 * 正数相同，负数不同
	 */
	@Test
	public void test14() {
		int i=0xfffff3;
		System.out.println("正数比对==================");
		System.out.println(i);
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>>1), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>1), 32, "0"));
		i=~i;

		System.out.println("负数比对==================");
		System.out.println(i);
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>>1), 32, "0"));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(i>>1), 32, "0"));
	}

	@Test
	public void test15() {
		int i=50;
		System.out.println(i%16);//2
		System.out.println(i&15);//2
		System.out.println((i%16)==(i&15));//true

		//掩码计算案例:截取n的低8位数据
		int n=0xafdda;
		int m=0xff;
		int k=n&m;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32, "0"));
		//00000000000010101111110111011010
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k), 32, "0"));
		//00000000000000000000000011011010
	}
}
