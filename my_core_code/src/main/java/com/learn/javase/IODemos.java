package com.learn.javase;
//常用IO流及其方法的演示

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.Test;


/**
 * 流根据功能分为:输入流:输入流是用来读取数据的	输出流:输出流是用来写出数据的
 *
 * 流还分为字节流(低级流)与处理流(高级流)
 * 低级流:数据源明确，真实负责"搬运数据"的流读写一定要有低级流
 * 高级流:不能独立存在，没有意义，它是用来处理其他流的，通过处理其他流来简化我们的读写操作
 *
 * @author Double
 *
 */
public class IODemos {

	/**
	 * 文件字节流：
	 * java.io.FileOutputStream:文件输出流
	 * 文件输出流FileOutputSteam是一个低级流(节点流)，作用是向文件中写出数据。
	 *
	 * java.io.FileInputStream
	 * 文件输入流FileInputStream是一个低级流(节点流)，用来从文件中读取数据
	 * @throws IOException
	 */
	@Test
	public void test1() throws IOException {
		/*
		 * FileOutputStream没有无参构造，必须传入参数,一共有5个有参构造方法：
		 * FileOutputStream(String name)
		 * FileOutputStream(String name, boolean append)
		 * FileOutputStream(File file)
		 * FileOutputStream(File file, boolean append)
		 * FileOutputStream(FileDescriptor fdObj)
		 *
		 * 支持第二个参数是一个boolean值，该值为true时，为追加写操作，那么通过该流写出的内容会被追加到文件末尾。
		 * 否则是覆盖写操作，即先将该文件原有数据全部删除，再开始本次流写入的内容。 一个参数的构造方法就是覆盖写操作。
		 */
		FileOutputStream fos = new FileOutputStream("fos.txt", true);

		String str = "天安门上太阳升";
		//将给定的字符集将当前字符转换为一组字节  不写默认是当前系统的字符集
		byte[] data = str.getBytes("UTF-8");
		fos.write(data);
		System.out.println("写出完毕!");
		fos.close();

		/*
		 * FileOutputStream没有无参构造方法，有三个有参构造方法：
		 * FileInputStream(String name)
		 * FileInputStream(File file)
		 * FileInputStream(FileDescriptor fdObj)
		 *。
		 */
		FileInputStream fis = new FileInputStream("fos.txt");

		data = new byte[100];//一次读取100个字节
		int len = fis.read(data);//实际读取字节

		//按实际读取字节，指定字符集转换成字符串
		str = new String(data, 0, len, "UTF-8");
		System.out.println(str);

		fis.close();
	}

	/**
	 * 使用文件流复制文件
	 * 使用文件输入流读取源文件，再使用文件输出流向目标文件中写。顺序从源文件中读取每个字节并写入到目标文件即可完成复制
	 * @throws IOException
	 *
	 */
	@Test
	public void test2() throws IOException {
		FileInputStream fis = new FileInputStream("Shake It Off - Taylor Swift.flac");
		FileOutputStream fos = new FileOutputStream("Shake It Off - Taylor Swift_cp1.flac");

		byte[] data = new byte[1024 * 10];
		int len = -1;
		long start=System.currentTimeMillis();
		System.out.println("复制开始，请勿移动！");
		while ((len = fis.read(data)) != -1) {
			fos.write(data, 0, len);
		}
		long end=System.currentTimeMillis();
		System.out.println("复制完毕,耗时："+(end-start)+"毫秒!");
		fis.close();
		fos.close();
	}

	/**
	 * 缓冲字节流：java.io.BufferedInputStream/java.io.BufferedOutputStream
	 * 缓冲字节输入流/缓冲字节输出流是一对高级流，使用它们可以加快读写效率。
	 *
	 * 高级流可以处理其他流，但是无论添加了多少高级流，最底下都要有低级流，因为低级流是真实读写数据的流，有数据源。
	 * 高级流都是处理数据的，高级流处理其他流就形成了流的链接，并且有效的组合不同的高级流可以得到叠加效果。
	 *
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		FileOutputStream fos = new FileOutputStream("bos.txt");
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		String str = "都不知道写什么了";
		byte[] data = str.getBytes("UTF-8");

		bos.write(data);
		/*
		 * flush方法会强制将缓冲流缓冲区中已缓冲 的数据一次性写出。
		 * 如果不使用flush，缓存区满了才能写入数据，实际开发中，如果有需求即时性的问题，就需要是使用flush。
		 * 但是频繁的使用flush，会导致写入速度降低。实际开发中，要看是否考虑即时性或者效率，而是否使用flush。
		 */
		bos.flush();
		System.out.println("写出完毕!");

		bos.close();

		FileInputStream fis = new FileInputStream("bos.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);

		data = new byte[100];//一次读取100个字节
		int len = bis.read(data);//实际读取字节

		//按实际读取字节，指定字符集转换成字符串
		str = new String(data, 0, len, "UTF-8");
		System.out.println(str);

		System.out.println("读取完毕!");
		bis.close();
	}

	/**
	 * 使用缓冲流复制文件
	 * @throws IOException
	 */
	@Test
	public void test4() throws IOException {
		FileInputStream fis = new FileInputStream("DMS01 am.ts");
		/**
		 * BufferedInputStream中没有无参构造方法，有2个有参构造方法:
		 * BufferedInputStream(InputStream in)
		 * BufferedInputStream(InputStream in, int size)
		 *
		 * 源码：
		 *  创建一个 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。
		 *  private static int DEFAULT_BUFFER_SIZE = 8192;//默认8192个字节
		 *  public BufferedInputStream(InputStream in) {
        		this(in, DEFAULT_BUFFER_SIZE);//实际上就是有一个8192个字节数组缓冲
    		}

    		创建具有指定缓冲区大小的 BufferedInputStream 并保存其参数，即输入流 in，以便将来使用。
    		public BufferedInputStream(InputStream in, int size) {
        		super(in);
        		if (size <= 0) {
            		throw new IllegalArgumentException("Buffer size <= 0");
        		}
        		buf = new byte[size];
    		}
		 */
		BufferedInputStream bis = new BufferedInputStream(fis);
		//BufferedInputStream bis = new BufferedInputStream(fis,1024*10);

		FileOutputStream fos = new FileOutputStream("DMS01 am_cp2.ts");
		/**
		 * BufferedOutputStream中没有无参构造方法，有2个有参构造方法:
		 * BufferedOutputStream(OutputStream out)
		 * BufferedOutputStream(OutputStream out, int size)
		 *
		 * 源码：
		 *  创建一个新的缓冲输出流，以将数据写入指定的底层输出流。
		 	public BufferedOutputStream(OutputStream out) {
		        this(out, 8192);
		    }
		           创建一个新的缓冲输出流，以将具有指定缓冲区大小的数据写入指定的底层输出流。
		    public BufferedOutputStream(OutputStream out, int size) {
		        super(out);
		        if (size <= 0) {
		            throw new IllegalArgumentException("Buffer size <= 0");
		        }
		        buf = new byte[size];
		    }
		 */
		BufferedOutputStream bos = new BufferedOutputStream(fos);//4405毫秒
		//BufferedOutputStream bos = new BufferedOutputStream(fos,1024*10);

		int d = -1;
		/*
		 * 通过缓冲流的read方法读取一个字节， 实际上缓冲流内部维护了一个字节数组， 它会一次性读取若干字节填满该数组，然后
		 * 将第一个字节返回。
		 * 当再次调用read方法读取第二个字节时，缓冲流直接将字节数组第二个 字节返回，直到多次调用后所有字节均返回后，
		 * 才会再次读取一组字节回来。 缓冲字节输出流也是这个道理。
		 *
		 * 所以缓冲流也是通过提高了每次实际读写的数据量减少读写次数提高的读写效率。8192
		 */
		System.out.println("复制开始，请勿移动！");
		long start=System.currentTimeMillis();
		while ((d = bis.read()) != -1) {
			bos.write(d);
		}
		long end=System.currentTimeMillis();
		System.out.println("复制完毕,耗时："+(end-start)+"毫秒！");

		bis.close();
		bos.close();
	}

	/**
	 * 对象字节流  java.io.ObjectOutputStream/java.io.ObjectInputStream
	 * 对象流是一对高级流，作用是方便读写java中的对象。
	 *
	 * java.io.ObjectInputStream
	 * 对象输出流，是一个高级流，使用它可以很方便的将java中的对象转换为一组字节后写出
	 * java.io.ObjectInputStream
	 * 对象输入流，是一个高级流，作用是可以读取一组字节(必须是ObjectOutputStream将一个对象写出的一组字节)并还原为对象
	 * 对象反序列化  ObjectInputStream读取的字节必须是ObjectInputStream将对象序列化得到的字节，否则会抛出异常。
	 *
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void test5() throws IOException, ClassNotFoundException {
		Person person = new Person();
		person.setName("苍老师");
		person.setAge(18);
		person.setGender("女");

		List<String> otherInfo = new ArrayList<String>();
		otherInfo.add("是一名演员");
		otherInfo.add("爱好写毛笔字");
		otherInfo.add("促进中日文化交流");
		otherInfo.add("广大男性同胞的启蒙老师");
		person.setOtherInfo(otherInfo);

		FileOutputStream fos = new FileOutputStream("person.obj");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		/*
		 * writeObject方法要求写出的对象必须实现 序列化接口。否则会抛出异常。
		 *
		 * ObjectOutputStream的writeObject方法可以将给定对象转换为一组字节后写出。这些字节比该对象实际内容
		 * 要大，因为除了数据外还有结构等描述信息。
		 *
		 * 该方法首先是oos将给定对象转换为了一组字节，然后再通过fos将这组字节写入到 文件person.obj中
		 * 这里经历的两个操作都有专业名词: 将对象转换为一组字节的过程称为:对象序列化
		 * 将字节写入到磁盘的过程称为:对象持久化
		 *
		 */
		oos.writeObject(person);
		System.out.println("写出完毕");

		oos.close();

		FileInputStream fis = new FileInputStream("person.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		person = (Person) ois.readObject();
		System.out.println(person);

		ois.close();
	}

	/**
	 * 字符流
	 * 字符流是以字符为单位进行读写数据的，所以字符流也仅能读写文本数据。字符流都是高级流，
	 * 本质上字符流读写字符是会自动与字节进行转换进行读写，所以底层还是要读写字节的。
	 *
	 * java.io.Reader是一个抽象类，所有字符输入流都继承它,字符输入流的顶级父类。
	 * java.io.Writer是一个抽象类，所有字符输出流都继承它,字符输出流的顶级父类。
	 * 它们规定了所有字符流都应当具备的读写字符的方法
	 *
	 * 字符转换流
	 * java.io.InputStreamReader:该流是Reader的实现类，很多字符流都只能处理其他字符流，但是低级流都是字节
	 * 流，所以在就需要先将字节流转换为字符流才可以被其他高级流使用，这时候常用转换流进行转换。
	 * java.io.OutputStreamWriter:Writer的实现类，作用是可以将字节输出流转换为字符输出流
	 *
	 * 转换流还有一个作用是可以指定读写字符的字符集，字节流只能处理字节流，大多数字符流只能处理其他字符流，
	 * 字符转换流是连接字节流和其他字符流中间的流。
	 * 之所以称为转换流 是因为大多数的字符流都只能处理其他字符流，而低级流又是字节流，这就导致字符流不能处理字节流的问题，
	 * 转换流相当于是一个转换器的作用，它们可以将字节流先转换成字符流，这样其它的字符流就可以处理了。
	 *
	 */
	@Test
	public void test6() throws IOException {
		/*
		 * 向文件中写出字符串
		 */
		FileOutputStream fos= new FileOutputStream("osw.txt");
		/*
		 * java.io.OutputStreamWriter没有无参构造方法，有4个有参构造方法：
		 * OutputStreamWriter(OutputStream out, String charsetName)
		 * OutputStreamWriter(OutputStream out)
		 * OutputStreamWriter(OutputStream out, Charset cs)
		 * OutputStreamWriter(OutputStream out, CharsetEncoder enc)
		 */
		/*
		 * 第二个参数不指定则使用系统默认字符集，指定字符集则按照给定字符集转换字符串
		 */
		OutputStreamWriter osw= new OutputStreamWriter(fos,"GBK");
		/*
		 * write有三个重载方法：
		 * write(int c)
		 * write(char cbuf[], int off, int len)
		 * write(String str, int off, int len)
		 *
		 * write(String str)是父类write的方法并且已经在父类中实现，可以直接使用。
		 */
		osw.write("锄禾日当午");//GBK一个字符占2个字节，UTF-8一般占3个字节
		osw.write("清明上河图");

		System.out.println("写出完毕!");
		osw.close();

		FileInputStream fis= new FileInputStream("osw.txt");
		/*
		 * java.io.InputStreamReader也没有无参构造，有4个有参构造：
		 * InputStreamReader(InputStream in)
		 * InputStreamReader(InputStream in, String charsetName)
		 * InputStreamReader(InputStream in, Charset cs)
		 * InputStreamReader(InputStream in, CharsetDecoder dec)
		 */
		/*
		 * 第二个参数不指定则使用系统默认字符集，指定字符集则按照给定字符集转换字符串
		 */
		InputStreamReader isr= new InputStreamReader(fis,"GBK");

/*		int d = -1;
		while((d=isr.read())!=-1){
			System.out.print((char)d);
		}*/

		char[] data = new char[100];
		int len = -1;
		/*
		 * read方法有2个重载方法
		 * read() read(char cbuf[], int offset, int length)
		 * 父类中有4个:3个已经实现，1个抽象方法 没有直接读取字符串的方法
		 */
		len = isr.read(data);

		String str = String.valueOf(data, 0, len);
		System.out.println(str);
		isr.close();
	}

	/**
	 * 缓冲字符流
	 * BufferedWriter/BufferedReader  	特点是可以按行读写字符串
	 *
	 * java.io.BufferedWriter是缓冲字符输出流
	 * java.io.PrintWriter是具有自动行刷新的缓冲字符输出流，其更常用，而且内部默认嵌套BufferedWriter作为缓冲。
	 *
	 * @throws IOException
	 */
	@Test
	public void test7() throws IOException {
		/*
		 * PrintWriter也没有无参构造 有9个有参构造，其中1个是私有的：
		 * PrintWriter (Writer out)
		 * PrintWriter(Writer out,boolean autoFlush)
		 * PrintWriter(OutputStream out)
		 * PrintWriter(OutputStream out, boolean autoFlush)
		 * PrintWriter(String fileName)
		 * private PrintWriter(Charset charset, File file)
		 * PrintWriter(String fileName, String csn)
		 * PrintWriter(File file)
		 * PrintWriter(File file, String csn)
		 *
		 */
		/*
		 * PrintWriter提供了方便写文件的构造方法 PrintWriter(File file) PrintWriter(String path)
		 * 并且上述两个构造方法还支持第二个参数 去指定字符集。
		 *
		 */
		PrintWriter pw = new PrintWriter("pw.txt", "GBK");

		pw.println("夜空中最亮的星");
		pw.println("是否记起，那仰望的人心里来的孤独和叹。");

		System.out.println("写出完毕!");
		pw.close();

		/*
		 * 缓冲字符输入流只能处理其他字符输入流,所以要先用InputStreamReader将字节流转换为字符流
		 */
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("pw.txt"),"GBK"));
		/*
		 * String readLine()
		 * 该方法会连续读取若干字符，直到读取到换行符为止，然后将换行符之前的所有字符组成一个字符串返回。
		 * 注意，返回的字符串中不含有最后的换行符。当返回值为NULL时，说明读取到文件末尾。
		 */
		String line = null;
		while((line = br.readLine())!=null){
			System.out.println(line);
		}
		br.close();

	}
	/**
	 * PrintWriter处理其他流	提供的构造方法及可以传入字节流也可以传入字符流
	 * @throws IOException
	 *
	 */
	@Test
	public void test8() throws IOException {

		FileOutputStream fos = new FileOutputStream("pw1.txt");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
		PrintWriter pw = new PrintWriter(osw);

		pw.println("大王叫我来巡山");
		pw.println("我把人间转一转");
		pw.println("打起我的鼓来敲起我的锣");
		pw.println("生活充满节奏感");
		System.out.println("写出完毕!");

		pw.close();

		/*
		 * 缓冲字符输入流只能处理其他字符输入流,所以要先用InputStreamReader将字节流转换为字符流
		 */
		FileInputStream fis=new FileInputStream("pw1.txt");
		InputStreamReader isr=new InputStreamReader(fis,"GBK");
		BufferedReader br=new BufferedReader(isr);
		/*
		 * String readLine()
		 * 该方法会连续读取若干字符，直到读取到换行符为止，然后将换行符之前的所有字符组成一个字符串返回。
		 * 注意，返回的字符串中不含有最后的换行符。当返回值为NULL时，说明读取到文件末尾。
		 */
		String line = null;
		while((line = br.readLine())!=null){
			System.out.println(line);
		}

		br.close();
	}

	/**
	 * 读取本类源代码
	 * @throws IOException
	 */
	@Test
	public void test9() throws IOException {
		//\src\main\java\com\learn\javase
		FileInputStream fis = new FileInputStream("src"+File.separator+"main"+
									File.separator+"java"+
									File.separator+"com"+
									File.separator+"learn" +
									File.separator + "javase"+
									File.separator+"IODemos.java");
		InputStreamReader isr = new InputStreamReader(fis);
		/*
		 * 缓冲字符输入流只能处理其他字符输入流 所以要先用InputStreamReader将字节流 转换为字符流
		 */
		BufferedReader br = new BufferedReader(isr);

		/*
		 * String readLine() 该方法会连续读取若干字符，直到读取到 换行符为止，然后将换行符之前的所有字符
		 * 组成一个字符串返回。注意，返回的字符串 中不含有最后的换行符。 当返回值为NULL时，说明读取到文件末尾。
		 */
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		br.close();
	}

	/**
	 * 简易记事本
	 *
	 * 程序启动后，要求用户输入一个文件名，然后创建该文件。
	 * 之后用户输入的每一行字符串都按行写入到该文件中,当用户输入"exit"时，退出程序。
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 *
	 */
	@Test
	public void test10() throws UnsupportedEncodingException, FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入文件名:");
		String fileName = scanner.nextLine();

		FileOutputStream fos = new FileOutputStream(fileName);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
		/*
		 * 当PrintWriter处理的是一个流时，则支持第二个参数，该参数为boolean,若为true，
		 * 则具有自动行刷新，即:每次使用println方法写出字符串后会自动flush.
		 */
		PrintWriter pw = new PrintWriter(osw, true);

		System.out.println("请开始输入内容:");

		String line = null;
		while (true) {
			line = scanner.nextLine();
			if ("exit".equals(line)) {
				System.out.println("再见!");
				break;
			}
			pw.println(line);
			//pw.flush();
		}

		pw.close();
		scanner.close();
	}

	/**
	 * 使用该类来测试对象流的对象读写操作  当前类只有实现了Serializable接口才可以被对象流进行读写
	 *
	 * 如果写成内部类的形式，内部类必须使用static修饰，不然依然没抛出异常 java.io.NotSerializableException
	 *
	 * java里面static一般用来修饰成员变量或函数。
	 * 但有一种特殊用法是用static修饰内部类，普通类是不允许声明为静态的，只有内部类才可以。
	 * 被static修饰的内部类可以直接作为一个普通类来使用，而不需实例一个外部类.
	 * 原因：
	 * 非静态内部类拥有对外部类的全部成员的全然訪问权限，包含实例字段和方法。
	 * 为实现这一行为，非静态内部类存储着对外部类的实例的一个隐式引用。
	 * 序列化时要求全部的成员变量是Serializable,如今外部的类并没有implements Serializable,
	 * 所以就抛出java.io.NotSerializableException异常。
	 *
	 * 所以写成员内部类时，这个成员内部类需要被序列化时最好加上static修饰，JDK源码中也都是这样写的。
	 *
	 * 静态内部类，定义在类中，任何方法外，用static定义；静态内部类只能访问外部类的静态成员。
	 * 生成（new）一个静态内部类不需要外部类成员：这是静态内部类和成员内部类的区别。
	 * 静态内部类的对象可以直接生成：Outer.Inner in=new Outer.Inner()；
	 * 而不需要通过生成外部类对象来生成。这样实际上使静态内部类成为了一个顶级类。可以定义私有静态内部类。
	 * 1）一般情况下，如果一个内部类不是被定义成静态内部类，那么在定义成员变量或者成员方法的时候，
	 *  是不能够被定义成静态成员变量与静态成员方法的。也就是说，在非静态内部类中不可以声明静态成员
       2）一般非静态外部类可以随意访问其外部类的成员变量以及方法（包括声明为private的方法），
    	但是如果一个内部类被声明为static，则其在访问包括自身的外部类会有诸多的限制。静态内部类不能访问其外部类的非静态成
    	员变量和方法
   	   3）在一个类中创建非静态成员内部类的时候，有一个强制性的规定，即内部类的实例一定要绑定在外部类的实例中。
   		然后要在一个外部类中定义一个静态的内部类，不需要利用关键字new来创建内部类的实例。即在创建静态类内部对象时，
   		不需要其外部类的对象
	 * Java静态内部类使用场景：实际开发中使用使用几率不多
	 *
	 * @author Double
	 *
	 */
	 static class Person implements Serializable{
		/**
		 * 如果在类没有实现序列化接口的时候，使用该类的对象流，会抛出 java.io.NotSerializableException异常。
		 * wirteObject()抛出的异常，没有实现序列化接口。
		 * 所以当一个类需要被对象流读写，那么此类必须实现序列化接口java.io.Serializable。
		 */
		/**
		 * 当一个类实现了Serializable接口后，应当定义一个常量serialVersionUID，这个是序列化版本号，
		 * 该值直接决定着当前类实例在进行反序列化时的成功与否。
		 * 当使用ObjectInputStream对一个已经序列化的对象进行反序列化时，会首先检查该对象的版本号与当前 类的版本
		 * 是否一致，不一致则直接反序列化失败。
		 *
		 * 若一致则可以还原。那么若当前类的结构发生了改变， 则在进行反序列化时启用兼容模式，即: 原有的属性现在依然有的则还原，
		 * 原有属性现在没有了则忽略，现有新的属性则使用默认值。 若不指定版本号，编译器会在
		 * 编译当前类时根据当前类结构生成一个版本号，那么当前类结构只要发生改变， 版本号一定改变，那么反序列化就一定失败了。
		 * 会抛出java.io.InvalidClassException:这个类已经无效了，序列号版本号不同。
		 */
		private static final long serialVersionUID = 6630640047565058611L;

		private String name;
		private int age;
		private String gender;
		/*
		 * transient关键字修饰的属性在进行对象序列化时会被忽略，将不需要的属性值忽略可以达到对象"瘦身"的目的，
		 * 减少资源消耗。
		 */
		private transient List<String> otherInfo;

		public Person() {

		}

		public Person(String name, int age, String gender, List<String> otherInfo) {
			super();
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.otherInfo = otherInfo;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public List<String> getOtherInfo() {
			return otherInfo;
		}

		public void setOtherInfo(List<String> otherInfo) {
			this.otherInfo = otherInfo;
		}

		public String toString() {
			return name + "," + age + "," + gender + "," + otherInfo;
		}

	}

}
