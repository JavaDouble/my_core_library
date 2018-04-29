package com.learn.javase;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

//RandomAccessFile类常用方法的演示
/**
 *
 * java.io.RandomAccessFile	该类用来读写文件数据,其使用指针的形式读写文件数据.
 * RandomAccessFile总是在指针指向的位置读写字节，并且读写后指针会自动向后移动。
 * RandomAccessFile既可以读取文件数据也可以向文件中写入数据。
 * @author Double
 *
 */
public class RandomAccessFileDemos {

	/**
	 * 向指定文件中写入数据
	 * @throws IOException
	 */
	@Test
	public void test1() throws IOException {
		/*
		 * RandomAccessFile两个常用的构造方法:
		 * RandomAccessFile(File f,String mode)
		 * RandomAccessFile(String path,String mode)
		 *
		 * mode是创建模式，常用的对应两个字符串: "rw":读写模式，对文件及可以读取也可以写入 "r":只读模式，
		 * 仅对文件数据进行读取操作
		 *
		 * 如果raf.dat不存在，RandomAccessFile会自动创建在当前目录下，类路径，不能创建目录。
		 * 如果raf.dat已存在并且里面有数据，写入文件操作会只覆盖掉里面的数据一个自己的内容。
		 */
		RandomAccessFile raf = new RandomAccessFile("raf.dat", "rw");
		/*
		 * void write(int d) 向文件中写入一个字节，写的是给定的 int值对应的2进制的"低八位"
		 * 							  vvvvvvvv
		 * 00000000 00000000 00000000 00000001
		 *
		 */
		raf.write(1);
		System.out.println("写出完毕!");
		raf.close();
	}

	/**
	 * 读取指定文件中的字符串
	 * @throws IOException
	 *
	 */
	@Test
	public void test2() throws IOException {

		RandomAccessFile raf = new RandomAccessFile("raf.dat", "r");
		/*
		 * 从文件中指针当前位置读取一个字节，并 以10进制的int值返回。若返回值为-1，则 是读取到了文件末尾。
		 * 00000000 00000000 00000000 11111111
		 *
		 */
		int d = raf.read();
		System.out.println(d);

		// 文件末尾
		d = raf.read();
		System.out.println(d);

		raf.close();

	}

	/**
	 * 复制文件 一个一个字节的复制，效率感人，一般不使用
	 * @throws IOException
	 *
	 */
	@Test
	public void test3() throws IOException {
		//源文件
		RandomAccessFile src = new RandomAccessFile("I Knew You Were Trouble - Taylor Swift.ape", "r");
		//复制后的文件 目标文件
		RandomAccessFile desc = new RandomAccessFile("I Knew You Were Trouble - Taylor Swift_cp.ape", "rw");

		// 用来保存每次读取出来的字节
		int d = -1;
		System.out.println("开始复制，请勿移动！");
		long start = System.currentTimeMillis();
		while ((d = src.read()) != -1) {
			desc.write(d);
		}
		long end = System.currentTimeMillis();
		System.out.println("复制完毕!耗时:" + (end - start) + "毫秒");
		src.close();
		desc.close();
	}

	/**
	 * 通过提高每次读写的数据量减少读写次数是可以提高读写效率的
	 * @throws IOException
	 *
	 */
	@Test
	public void test4() throws IOException {
		RandomAccessFile src = new RandomAccessFile("Love Story - Taylor Swift.ape", "r");
		RandomAccessFile desc = new RandomAccessFile("Love Story - Taylor Swift_cp.ape", "rw");
		/*
		 * int read(byte[] data) 一次尝试读取给定的字节数组总长度的字节 量并存入到该数组中，返回值为实际读取到
		 * 的字节量，若返回值为-1，表示本次没有读 取到任何字节，指的还是读取到了文件末尾
		 */
		/*
		 * 10k 8192个字节为最佳  相当于开了一个10K的缓存，比windows效率还高。
		 */
		byte[] buf = new byte[1024 * 10];
		int len = -1;
		System.out.println("开始复制，请勿移动！");
		long start = System.currentTimeMillis();
		while ((len = src.read(buf)) != -1) {
			/*
			 * void write(byte[] data) 一次性将给定的字节数组中所有字节 写出
			 * 这里不能使用，因为不能保证最后一次读取源文件为10K，最后一次不是10K时，会多出一部分无效数据。
			 *
			 * void write(byte[] data,int start,int len)
			 * 将给定的字节数组中从下标为start处的字节开始的连续len个字节一次性写出
			 */
			desc.write(buf, 0, len);
		}
		long end = System.currentTimeMillis();
		System.out.println("复制完毕!耗时:" + (end - start) + "ms");
		src.close();
		desc.close();
	}

	/**
	 * RandomAccessFile提供了方便读写基本类型数据的方法
	 * @throws IOException
	 */
	@Test
	public void test5() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("raf.dat", "rw");
		/*
		 * 获取当前指针位置 RandomAccessFile提供了long getFilePointer()方法
		 */
		System.out.println("当前指针位置："+raf.getFilePointer());

		/* 向文件中写入一个int最大值  虽然给了一个int值，但是实际上写入的是int值二进制的最后八位，低八位
		 * 011111111 11111111 11111111 11111111 int最大值的二进制
		 * 可以使用位移操作
		 */
		int max=Integer.MAX_VALUE;
		//raf.write(max);// 虽然给了一个int值，但是实际上写入的是int值二进制的最后八位，低八位
		raf.write(max>>>24);
		System.out.println("当前指针位置："+raf.getFilePointer());
		raf.write(max>>>16);
		System.out.println("当前指针位置："+raf.getFilePointer());
		raf.write(max>>>8);
		System.out.println("当前指针位置："+raf.getFilePointer());
		raf.write(max);//写入4个字节
		System.out.println("当前指针位置："+raf.getFilePointer());

		/* 实际上，RandomAccessFile还提供了写入基本类型的方法
		 * wirteByte,writeShort,writeChar,writeInt,writeLong,writeFloat,writeDouble
		 *
		 * writeInt源码就是使用位移操作实现的,源码这样写的:
		 *  public final void writeInt(int v) throws IOException {
		        write((v >>> 24) & 0xFF);
		        write((v >>> 16) & 0xFF);
		        write((v >>>  8) & 0xFF);
		        write((v >>>  0) & 0xFF);
		        //written += 4;
	    	}
		 */
		raf.writeInt(max);//写入8个字节
		System.out.println("当前指针位置："+raf.getFilePointer());
		raf.writeLong(1234L);//写入16个字节
		System.out.println("当前指针位置："+raf.getFilePointer());
		raf.writeDouble(123.123);//写入24个字节
		System.out.println("当前指针位置："+raf.getFilePointer());

		//移动指针到初始位置
		raf.seek(0);
		System.out.println("当前指针位置："+raf.getFilePointer());

		//读取数据
		int data=raf.readInt();
		System.out.println(data);//java.io.EOFException EOF end of file 文件末尾 指针在文件末尾
		//原因是上面写入操作时，指针会自动移动到下一位，所以指针在文件末尾。
		//所以当我们每次写入数据最后，一定要把指针移动到初始位置0,避免开发遇到问题。
		/*
		 * RandomAccessFile提供了针对指针操作的方法 long getFilePointer() 获取当前指针位置
		 * 源码：    public native long getFilePointer() throws IOException;
		 * native修饰，使用其他语言实现的C或者C++
		 *
		 * 还提供了 void seek(long pos)方法 操作指针位置0即初始位置
		 */

		data=raf.readInt();
		System.out.println(data);
		System.out.println("当前指针位置："+raf.getFilePointer());

		long l=raf.readLong();
		System.out.println(l);
		System.out.println("当前指针位置："+raf.getFilePointer());

		double d=raf.readDouble();
		System.out.println(d);
		System.out.println("当前指针位置："+raf.getFilePointer());

		//指针移动到初始位置，一次读一个long值
		raf.seek(0);
		l=raf.readLong();
		System.out.println(l);
		System.out.println("当前指针位置："+raf.getFilePointer());

		raf.close();
	}
	/**
	 * 向指定文件写入字符串 指定字符集
	 * @throws IOException
	 *
	 */
	@Test
	public void test6() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("raf.txt", "rw");

		String message = "摩擦摩擦,是魔鬼的步伐!";
		/*
		 * byte[] getBytes() 将当前字符串按照系统默认的字符集转换为 对应的一组字节
		 *
		 * byte[] getBytes(String csn) 将当前字符串按照给定的字符集转换为一组 字节
		 *
		 */
		byte[] data = message.getBytes("GBK");

		raf.write(data);

		System.out.println("写入完毕!");
		raf.close();
	}

	/**
	 * 读取指定文件中的字符串  指定字符集
	 *
	 */
	@Test
	public void test7() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("raf.txt", "r");

		byte[] data = new byte[100];

		int len = raf.read(data);
		System.out.println("实际读取到的字节量:" + len);

		String str = new String(data, 0, len, "GBK");
		System.out.println(str);

		raf.close();
	}

}
