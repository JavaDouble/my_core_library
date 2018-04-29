package com.learn.javase;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

//文件File类的常用方法的演示
/**
 * java.io.File
 * File的每一个实例可以表示磁盘上的一个文件或目录，用来表示文件系统中文件或目录的。
 * 通过File可以:
 * 1:访问文件或目录的属性信息(名字,大小,修改日期等)
 * 2:操作文件或目录(创建,删除)
 * 3:访问一个目录下的所有子项
 * 但是不能:访问文件内容
 *
 * @author Double
 *
 */
public class FileDemos {

	/**
	 * 读取一个指定目录文件，及其文件信息
	 */
	@Test
	public void test1() {
		/*
		 * 指定路径时的注意事项:1:使用相对路径，避免平台差异	2:目录层级分隔符使用File的常量。
		 *
		 * 相对路径在实际开发中常用的是:类加载路径(ClassLoader)
		 *
		 * "."为当前目录,在eclipse中为当前类所在项目的根目录
		 *
		 * Windows路径：D:\Java\eclipse_workspace\my_core_code 层级分隔符默认"\","/"也可以识别
		 * Linux路径只能识别"/"层级分隔符，建议使用File.separator作为层级分隔符，避免操作系统层级分隔符问题。
		 */
		File file = new File("."+File.separator+"demo.txt");
		//获取文件名
		String name = file.getName();
		System.out.println("name:"+name);
		//获取文件大小，单位是字节
		long len = file.length();
		System.out.println("len:"+len+"字节");

		//是否可读
		boolean canR = file.canRead();
		System.out.println("可读:"+canR);
		//是否可写
		boolean canW = file.canWrite();
		System.out.println("可写:"+canW);

		//查看文件路径
		String path=file.getPath();
		System.out.println("文件所在路径："+path);

		//查看文件是否是文件和目录
		boolean isFile=file.isFile();
		System.out.println("是否是文件："+isFile);
		boolean isDir=file.isDirectory();
		System.out.println("是否是目录："+isDir);

		//文件是否隐藏和存在
		boolean isHidden = file.isHidden();
		System.out.println("是否隐藏:"+isHidden);
		boolean isExists=file.exists();
		System.out.println("是否存在："+isExists);

		/*
		 * ‎查看文件最后修改时间并转换为指定格式： 2018年03月07日,21:06:55
		 */
		long time = file.lastModified();
		Date date = new Date(time);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy年MM月dd日,HH:mm:ss");

		System.out.println("最后修改日期:"+sdf.format(date));
	}

	/**
	 * 创建一个文件
	 *
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		/*
		 * 当前目录下创建一个test.txt文件
		 * "."+File.separator+"test.txt",如果是当前目录下创建文件，"."+File.separator可以不写，
		 * 默认情况下是在当前目录。当前目录是该类所在项目根目录，类加载路径。
		 */
		File file = new File("test.txt");
		//写完，当前目录并没有该文件，只是描述会在当前目录创建文件。

		/*
		 * boolean exists()	判断当前File表示的文件或目录是否已经真实存在
		 * 判断文件是否不存在
		 */
		if(!file.exists()){
			/*
			 * 创建新文件
			 */
			file.createNewFile();
			System.out.println("创建完毕:");
		}else{
			System.out.println("该文件已存在!");
		}
	}

	/**
	 * 删除一个已有文件
	 */
	@Test
	public void test3() {
		/*
		 * 将当前目录下的test.txt文件删除
		 */
		File file = new File("test.txt");
		if(file.exists()){
			file.delete();
			System.out.println("文件已删除!");
		}else{
			System.out.println("文件不存在!");
		}
	}

	/**
	 * 创建一个目录 目录就是文件夹
	 */
	@Test
	public void test4() {
		/*
		 * 在当前目录下创建一个名为demo的目录
		 */
		File dir = new File("demo");
		if(!dir.exists()){
			//创建目录
			dir.mkdir();
			System.out.println("创建目录完毕!");
		}else{
			System.out.println("该目录已存在!");
		}
	}

	/**
	 * 创建多级目录
	 */
	@Test
	public void test5() {
		/*
		 * 在当前目录下创建目录:a/b/c/d/e/f
		 *
		 */
		File dir = new File("a"+File.separator+"b"+File.separator+"c"+File.separator+
											"d"+File.separator+"e"+File.separator+"f");
		if(!dir.exists()){
			/*
			 * mkdirs在创建当前目录的同时会将所有不存在的父目录一同创建出来
			 */
			dir.mkdirs();
			System.out.println("创建完毕!");
		}else{
			System.out.println("该目录已存在!");
		}
	}

	/**
	 * 删除目录	只有空目录才可以被删除
	 */
	@Test
	public void test6() {
		File dir = new File("demo");
		if(dir.exists()){
			/*
			 * 只有空目录才可以被删除
			 */
			dir.delete();
			System.out.println("删除完毕!");
		}else{
			System.out.println("该目录不存在!");
		}
	}

	/**
	 * 获取一个目录的所有子项
	 */
	@Test
	public void test7() {
		/*
		 * 获取当前目录下的所有子项
		 */
		File dir = new File(".");
		/*
		 * boolean isFile() 当前File表示的是否为文件 boolean isDirectory() 当前File表示的是否为目录
		 */
		if (dir.isDirectory()) {
			/*
			 * File[] listFiles() 该方法可以获取当前目录下的所有子项
			 */
			File[] subs = dir.listFiles();
			for (File sub : subs) {
				if (sub.isFile()) {
					System.out.print("文件:");
				} else if (sub.isDirectory()) {
					System.out.print("目录:");
				}
				System.out.println(sub.getName());
			}
		}
	}

	/**
	 * 仅获取当前目录下所有的文件
	 *
	 * 重载的listFiles方法可以额外传入一个文件过滤器该方法会将当前目录中所有子项都经过一次文件过滤 器，
	 * 然后将满足过滤器要求的子项返回。
	 */
	@Test
	public void test8() {
		/*
		 * 仅获取当前目录下所有的文件
		 */
		File dir = new File(".");

		File[] subs = dir.listFiles(new FileFilter(){
			//当前目录下文件都进入了过滤器，只不过返回的是过滤后的符合条件的。
			@Override
			public boolean accept(File file) {
				System.out.println("正在过滤:"+file.getName());
				//return file.getName().startsWith(".");//获取以"."开头的子项
				return file.isFile();//只返回文件，过滤目录
			}
		});

		for(File sub : subs){
			System.out.println(sub.getName());
		}
	}

	/**
	 * 实现方法来删除给定的File表示的文件或目录		删除多级目录	递归删除
	 */
	/**
	 * 将给定的File表示的文件或目录删除
	 *
	 * 1)方法里面调本身的方法就是递归，整个流程要重新走N遍时适合递归.	2)要有条件的递归，不能100%使用，不然死循环。
	 * 在实际开发中，递归能不用就不用，内存开销比较大，整个流程要重新走N遍时最适合递归。
	 * @param f
	 */
	public static void delete(File f){
		if(f.isDirectory()){
			//先将其所有子项删除
			File[] subs = f.listFiles();
			for(File sub : subs){
				delete(sub);//递归
			}
		}
		f.delete();
	}

	@Test
	public void test9() {
		File f = new File("a");
		delete(f);
	}

	/**
	 * 递归训练： 1:编写一段代码，不超过20，要求完成从1+2+3...+100,并输出最终结果
	 * 在代码中不能出现for,while关键字
	 */
	@Test
	public void test10() {

	}

	/**
	 * 递归训练：2:有20块钱，买汽水，1块钱一瓶，然后3个瓶盖可以换一瓶汽水，2个空瓶也可以换一瓶汽水，总共能买
	 * 多少瓶汽水?
	 */
	@Test
	public void test11() {

	}

}
