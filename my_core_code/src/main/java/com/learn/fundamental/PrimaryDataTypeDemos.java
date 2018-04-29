package com.learn.fundamental;

import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.Test;

//Java基本数据类型演示
/**
 * JAVA基本类型
 * 8种基本数据类型：
 * Java语言有8种基本数据类型，分别用于存储整数、浮点数、字符数据和布尔类型数据。
 * 需要注意的是：现在所介绍的仅仅是基本数据类型，后续还会介绍很多非基本数据类型。
 * 整数类型又分为了4小类（byte、short、int、long）
 * 浮点类型也分了2小类（float、double）
 *
 * 计算机的内部一切计算都是2进制计算,所以存储数据也是二进制
 * 类型/占用字节空间：
 * byte/1字节(8位二进制 2^8=256 范围-2^7——2^7-1),字节
 * short/2字节(16位二进制 2^16=66536 范围-2^15——2^15-1),短整
 * int/4字节(32位二进制 2^32 范围-2^31——2^31-1),普通整数
 * long/8字节(64位二进制 2^64 范围-2^63——2^63-1),长整
 * 正型的直接量/直面量/缺省/默认是int类型
 *
 * float/4字节(32位二进制 2^32),单精度/浮点型 小数
 * double/8字节(64位二进制 2^64),双精度/浮点型 小数
 * 浮点型的直接量/直面量/缺省/默认是double类型
 *
 * char/2字节(16位二进制 2^16 0-65535) 存储一个字符
 * 字符类型事实上是一个16位无符号整数，这个整数对应字符的Unicode字符集编码。
 *
 * int类型和char类型都可以存储汉字"达"。
 * boolean/1字节(8位二进制 2^8) 布尔类型 存储逻辑变量(true,false)
 *
 * int类型
 * int是最常用的整数类型，一个int类型的变量占用4个字节，即32位的内存空间。Int
 * 的最大范围为：-2^31～2^31-1，即-2147483648 ~2147483647，大约正负21个亿。
 * 整数直接量是int类型
 * 所谓整数直接量（literal）就是直接写出的整数:byte a=100;
 * 100为int类型，变量a为byte类型
 * byte、short、char型数据参与运算时，先一律转换为int再运算
 * 不是自动转换转换 小类型向大类型转换是自动类型转换
 *
 * 关于整数直接量，需要注意如下要点：
 * 1.整数的直接量的类型默认为int类型，如果直接写出的整数超过了int的表达范围
 * int d = 10000000000; //超出int最大值范围 编译错误
 * 2.除了通常的十进制数字形式，整数直接量也可以写成16进制的形式
 * （以0X或0x开头）或8进制的形式（以0开头）
 * int a=100000;//十进制int b=0x186a0；//16进制int c=0303240;//8进制
 *
 * 整型数据除法运算中的取整(整数类型参与运算，小数部分无条件舍弃)
 * 若对两个整数相除，会舍弃小数的部分（注意：不是四舍五入），结果也是整数
 * 运算时要防止溢出的发生
 * 当两个整数进行运算时， 其结果可能会超过整数的范围而发生溢出，
 * 正数过大而产生的溢出，结果为负数；负数过大而产生的溢出，结果为正数。
 *
 * long类型
 * 在表示整数时，如果int类型的范围不够，可以使用long类型，一个long型的变量占
 * 用8个字节（即64位），最大表示范围为：-263 ~ 263-1，
 * 即 -9223372036854775808 ~ 9223372036854775807。
 * 如果要表示long直接量，需要以 L 或 l 结尾。
 * long a = 10000000000;   //会有编译错误，
 *	//因为10000000000编译器认为是int类型，而这个值，已经超出了int的范围
 * long b = 10000000000l;   //正确
 * 使用long类型进行较大整数的运算
 * 对于较大的整数运算（超过int的表达范围），可以使用long型。
 * long distance1 = 10000l* 365 * 24 * 60 * 60 * 299792458;
 * //必须有一个long型数据参与的运算结果才是long型
 * 通过时间毫秒数来存储日期和时间
 * JDK提供 System.currentTimeMillis() 方法，
 * 返回1970年1月1日零点到此时此刻所经历的毫秒数，数据太大，故其数据类型为long。
 * long time = System.currentTimeMillis();
 *
 * double类型
 * 使用double进行浮点数的运算
 * 前面所学习的int、long都是用于存储整数的，小数即为浮点数，包括：
 * float（单精度）和double（双精度），double类型的精度值是float类型的两倍，
 * 因此而得名双精度，在实际的应用开发中，float应用极少，
 * 大多数场合使用double表示浮点数。
 *
 * 浮点数直接量是double类型
 * 浮点数的直接量有两种写法：1）通常写法，如：3.14,314,0.1,.5。
 *  2）科学计数法，如：1.25E2、1.25e2、1.25E-2。
 *  其中，1.25E2表示1.25乘以10的2次方。
 *  默认的浮点直接量为double型，如果需要表示float类型的直接量，
 *  需要加“f”或“F”后缀。例如：float f1=3.14;//编译错误
 *  浮点数运算时，有可能会出现舍入误差，所以精确场合不建议使用
 *
 *  double运算时会出现舍入误差
 *  2进制系统中无法精确的表示1/10，就好像十进制系统中无法精确的表示1/3一样，
 *  所以，2进制表示10进制会有一些舍入误差，
 *  对于一些要求精确运算的场合会导致代码的缺陷。
 *  如果需要精确的运算可以考虑放弃使用double或float而采用BigDecimal
 *  类来实现。
 *
 *  char类型
 *  字符类型char事实上是一个16位无符号整数（都是正数），这个值是对应字符的编码
 *  ，Java字符类型采用Unicode字符集编码（通用码、统一码、万国码），
 *  而Unicode是世界通用的定长字符集，所有的字符都是16位来表示。
 *  例如：字符a实际的值为97，字符A实际的值为65，字符0实际的值为48。
 *  字符直接量可以采用诸如：‘中’的形式，
 *  也可以采用16进制的表示形式，例如： ‘\u4e2d’。
 *  采用Unicode编码，每个字符都对应一个码
 *  表现的形式是char字符，但实质上是int码
 *  字符需放在单引号中，有且仅有一个
 *  特殊符号需通过\来转义

 * 对char型变量赋值
 * 在对char型变量赋值时，可以采用如下三种方式：
 * 方式一：字符直接量：
 * 形如‘A’，变量中实际存储的是该字符的Unicode编码（无符号整数值），
 * 一个char型变量只能存储一个字符。char c1 = 'A';
 * 方式二：整型直接量：范围在0~65535之间的整数，变量中实际存储的即该整数值，
 * 但表示的是该整数值所对应的Unicode字符。char c2 = 65;
 * 方式三：Unicode形式：形如‘\u0041’,Unicode字符的16进制形式。
 *	char c3 = '\u0041';
 *
 * 使用转义字符
 * 字符直接量需要包含在一对’’单引号之中，那如果想表示单引号’的字符时，
 * 需要怎么表示？想表示回车、换行符时，怎么表示？
 * 因为单引号为特殊意义的字符， 那么，对于不方便输出的字符可以采用转义字符来表示
 * ，示例代码如下：
 * char c = '\\';
 * System.out.println(c);  //输出的结果为：\
 * 常用转义字符：'\n'/表示回车符 'r'/表示换行符 '\\'/表示反斜杠(\)
 * '\''/表示单引号(') '\"'/表示双引号  '\t'表Tab
 *
 * boolean类型
 * 使用boolean变量进行关系运算
 * boolean类型适用于关系、逻辑运算， 表示某个条件是否成立，
 * 只允许取值true或false，true表示条件成立， 而false表示条件不成立。
 * boolean型变量经常用于存储关系运算的结果，
 * 所谓关系运算就是比较两个变量的大小相等等关系（此知识点，后续详细介绍）。
 *
 * 基本类型间转换
 * 类型间转换
 * 不同的基本类型直接可以相互转化，主要有两种方式：
 * 自动类型转化（隐式类型转换）：从小类型到大类型可以自动完成。
 * 数据类型从小到大依次为:
 * byte,short,int,long,float,double
 *       char,
 * 强制转化：从大类型到小类型需要强制转换符，语法如下：（需要转换成的类型）变量
 * 因为大类型的精度值大于小类型，取值范围大于小类型，所以，当使用强制转化时，
 * 有可能会造成精度的损失或者溢出，所以，在使用强制转化时要求显式的告诉编译器，
 * 正在进行强制转换。
 *
 * @author Double
 *
 */
public class PrimaryDataTypeDemos {
	/**
	 * Java8种基本数据类型：
	 * 整型（字节byte,短整short,整型int,长整long），浮点型(单精度float，双精度double),
	 * 字符型char,布尔型boolean
	 * 	补充：一个汉字 GBK编码占两个字节，UTF-8编码占三个字节
	 *
	 * Java8种基本数据类型存储空间和使用场景：
	 * 类型名称			字节空间			使用场景
	 * byte				1字节(8位)		存储字节数据(较常用)
	 * short			2字节(16位)		兼容性考虑(很少使用，主要是C语言中有)
	 * int				4字节(32位)		存储普通整数(常用)
	 * long				8字节(64位)		存储长整数(常用)
	 * float			4字节(32位)		存储浮点数(常用)
	 * double			8字节(64位)		存储双精度浮点数(常用)
	 * char				2字节(16位)		存储一个字符(常用)
	 * boolean			1字节(8位)		存储逻辑变量(true、flase)(常用)
	 *
	 * 1.int类型
	 * int是最常用的整数类型，一个int类型的变量占用4个字节，即32位的内存空间。
	 * Int的最大表示范围为：-231～231-1，即-2147483648 ~2147483647，大约正负21个亿多些。
	 *	1.1整数直接量是int类型
	 *		所谓整数直接量（literal）/直面量/缺省/默认(都是一个意思)就是直接写出的整数
	 *	1.2 整型数据除法运算中的取整
	 *		若对两个整数相除，会舍弃小数的部分（注意：不是四舍五入），结果也是整数。
	 *	1.3运算时要防止溢出的发生
	 *		当两个整数进行运算时， 其结果可能会超过整数的范围而发生溢出，正数过大而产生的溢出，结果为负数；
	 *		负数过大而产生的溢出，结果为正数。
	 *
	 * 2.long类型
	 *	2.1使用long类型进行较大整数的运算
	 *	2.2通过时间毫秒数来存储日期和时间
	 *
	 * 3.double类型
	 *	3.1使用double进行浮点数的运算
	 *	3.2浮点数直面量是double类型
	 *	3.3double运算时会出现舍入误差
	 *
	 * 4.char类型
	 *	4.1对char类型变量赋值
	 *	4.2使用转义字符
	 *
	 * 5.boolean类型
	 *	5.1使用boolean变量进行关系运算
	 *
	 * 6.基本类型转换
	 * 	6.1类型间转换
	 *
	 *	6.2强制类型转换时的精度丧失和溢出
	 *
	 *	6.3数值运算时的自动转换
	 *
	 *	6.4byte、char、short转换为int
	 *
	 * @author Double
	 */

	/**
	 * 输出8种基本类型的最值 使用相应的包装类的静态常量
	 */
	@Test
	public void test1() {
		byte bmax=Byte.MAX_VALUE;
		byte bmin=Byte.MIN_VALUE;
		System.out.println("byte的最大值:"+bmax+",最小值："+bmin);

		short smax=Short.MAX_VALUE;
		short smin=Short.MIN_VALUE;
		System.out.println("short的最大值："+smax+",最小值："+smin);

		int imax=Integer.MAX_VALUE;
		int imin=Integer.MIN_VALUE;
		System.out.println("int的最大值:"+imax+",最小值："+imin);

		long lmax=Long.MAX_VALUE;
		long lmin=Long.MIN_VALUE;
		System.out.println("long的最大值:"+lmax+",最小值："+lmin);

		float fmax=Float.MAX_VALUE;
		float fmin=Float.MIN_NORMAL;
		System.out.println("float的最大值:"+fmax+",最小值："+fmin);
		/*
		 * 把科学技术转变为普通数字
		 */
		BigDecimal bdmax=new BigDecimal(fmax);
		BigDecimal bdmin=new BigDecimal(fmin);
		System.out.println("float的最大值:"+bdmax+",最小值："+bdmin);

		double dmax=Double.MAX_VALUE;
		double dmin=Double.MAX_VALUE;
		System.out.println("dobule的最大值:"+dmax+",最小值："+dmin);

		char cmax=Character.MAX_VALUE;
		char cmin=Character.MIN_VALUE;
		System.out.println("char的最大值:"+cmax+",最小值："+cmin);
		System.out.println(cmax==65535);//最大值65535
		System.out.println(cmin==0);//最小值为0

		boolean bo1=Boolean.TRUE;
		boolean bo2=Boolean.FALSE;
		System.out.println("boolean的值:"+bo1+","+bo2);
	}

	/**
	 * 对int类型变量和int类型参与运算的规则的测试
	 * int:整型，4个字节，-21个多亿到21个多亿
	 */
	@Test
	public void test2() {
		/*
		 * 1.1整数直接量是int类型
		 * 关于整数直接量，需要注意如下要点：
		 * 1.整数的直接量的类型默认为int类型，如果直接写出的整数超过了int的表达范围，将会出现编译错误，
		 * 下面的语句，就是因为超出了整数的范围而导致的编译错误。
		 *
		 * 2.除了通常的十进制数字形式，整数直接量也可以写成16进制的形式（以0X或0x开头）或8进制的形式（以0开头），
		 *请看如下直接量三种表现形式。
		 */
		int a=100;
		//int b=10000000000;
		// 编译错误10000000000这个数值写出来就是错误的，因为Java认为所有直接写出的整数都是int类型，
		//而这个数值超过了int的表达范围。
		int aa = 100000; // 10进制
		int b = 0x186a0; // 16进制
		int c = 0303240; // 8进制

		/*
		 * 1.2整型数据除法运算中的取整
		 * 若对两个整数相除，会舍弃小数的部分（注意：不是四舍五入），结果也是整数。
		 */
		int cc = 5/3;
		System.out.println(cc);  // c的值为1，取整
		int total = 87;
		int error = 23;
		int percent = error / total * 100;
		System.out.println(percent+"%");  //结果为0%，23除以87整数部分为0，乘以100，为0
		percent = 100 * error / total;
		System.out.println(percent + "%");  // 结果为26%，230除以87整数部分为26

		/*
		 * 1.3运算时要防止溢出的发生
		 * 当两个整数进行运算时， 其结果可能会超过整数的范围而发生溢出，正数过大而产生的溢出，结果为负数；
		 * 负数过大而产生的溢出，结果为正数。
		 * 注：溢出不是编译错误，是需要避免的。
		 */
		a = 2147483647;    //int类型整数的上限
		b = -2147483648;    //int类型整数的下限
		a = a + 1;
		b = b - 1;
		System.out.println("a=" + a);    //输出结果： a=-2147483648 溢出，结果错误。
		System.out.println("b=" + b);    //输出结果： b=2147483647溢出，结果错误。
	}

	/**
	 * long类型变量的测试
	 * long:长整型，8个字节，很大很大很大
	 */
	@Test
	public void test3() {
		/*
		 * 2.1使用long类型进行较大整数的运算
		 * 在表示整数时，如果int类型的范围不够，可以使用long类型，一个long型的变量占用8个字节（即64位），
		 * 最大表示范围为：-263 ~ 263-1，即 -9223372036854775808 ~ 9223372036854775807。
		 * 如果要表示long直接量，需要以 L 或 l 结尾。
		 */
		//long a = 10000000000;
		//会有编译错误，因为10000000000编译器认为是int类型，而这个值，已经超出了int的范围
		long b = 10000000000l;   //正确
		long c=250;//250L为长整型的直接量 这里发生了自动类型转换

		/*
		 * 对于较大的整数运算（超过int的表达范围），可以使用long型。
		 */
		long distance1 = 10000 * 365 * 24 * 60 * 60 * 299792458l;
		//必须有一个long型数据参与的运算结果才是long型
		System.out.println("distance1="+distance1);
		//distance1=547836957965889536 结果正确

		long distance2 = 10000 * 365 * 24 * 60 * 60 * 299792458;
		System.out.println("distance2="+ distance2);
		//distance2=-1973211136 溢出，=号后面的数据默认为int类型，超出了范围，发生溢出。

		//long类型参与的大数运算 一般在第一个数字上面加上l或者L，避免溢出
		long distance3 = 10000l * 365 * 24 * 60 * 60 * 299792458;
		System.out.println("distance3="+ distance3);

		long d = 1000000000*2*10L;
		System.out.println(d); //200亿
		long e = 1000000000*3*10L;
		System.out.println(e); //发生溢出了
		long f = 1000000000L*3*10;
		System.out.println(f); //300亿

		/*
		 * 2.2通过时间毫秒数来存储日期和时间
		 * JDK提供 System.currentTimeMillis() 方法，
		 * 返回1970年1月1日零点到此时此刻所经历的毫秒数，数据太大，故其数据类型为long。
		 */
		long time=System.currentTimeMillis();
		System.out.println("自1970年1月1日零点，过了"+time+"秒！");
	}

	/**
	 * 对double类型变量的测试
	 * double:浮点型，8个字节，很大很大很大
	 */
	@Test
	public void test4() {
		/*
		 * 3.1使用double进行浮点数的运算
		 * 前面所学习的int、long都是用于存储整数的，小数即为浮点数，包括： float（单精度）和double（双精度），
		 * double类型的精度值是float类型的两倍，因此而得名双精精，在实际的应用开发中，float应用极少，
		 * 大多数场合使用double表示浮点数。
		 */
		double pi = 3.14;
		double r = 8;
		double s = pi * r * r;
		System.out.println("s=" + s);   // 输出的结果为：s=200.96

		/*
		 * 3.2浮点数直面量是double类型
		 * 浮点数直接量是double类型
		 * 浮点数的直接量有两种写法：1）通常写法，如：3.14、314、0.1、.5。 2）科学计数法，
		 * 如：1.25E2、1.25e2、1.25E-2。其中，1.25E2表示1.25乘以10的2次方。
		 * 默认的浮点直接量为double型，如果需要表示float类型的直接量，需要加“f”或“F”后缀。
		 */
		//float f1 = 3.14   //编译错误，应该写成3.14f

		/*
		 * 3.3double运算时会出现舍入误差
		 *	2进制系统中无法精确的表示1/10，就好像十进制系统中无法精确的表示1/3一样，
		 *  所以，2进制表示10进制会有一些舍入误差，对于一些要求精确运算的场合会导致代码的缺陷
		 */
		double b=3.0,c=2.9;
		System.out.println(b-c); //0.10000000000000009，舍入误差
		//如果需要精确的运算可以考虑放弃使用double或float而采用BigDecimal 类来实现。
	}

	/**
	 * 对char类型变量的测试 转义字符
	 * char:字符型，2个字节，必须放在单引号中，有且仅有一个
	 * 字符a实际的值为97，字符A实际的值为65，字符0实际的值为48。
	 * char对应的0-65535个整数(ACSII码)，一共65536个整数 2^16次方 可以代表全世界的单字符
	 */
	@Test
	public void test5() {
		/*
		 * 1.字符类型char事实上是一个16位无符号整数（都是正数），这个值是对应字符的编码。
		 * 2.Java字符类型采用Unicode字符集编码（通用码、统一码、万国码），而Unicode是世界通用的定长字符集，
		 * 所有的字符都是16位来表示。例如：字符a实际的值为97，字符A实际的值为65，字符0实际的值为48。
		 * 3.字符直接量可以采用诸如：‘中’的形式，也可以采用16进制的表示形式，例如： ‘\u4e2d’
		 */
		char c1 = '中';   //c1中存的是”中”的编码
		char c2 = '\u4e2d';   //‘4e2d’为‘中’所对应的16位Unicode编码的16进制表示形式
		System.out.println(c1);
		System.out.println(c2);
		//c1的值为中，c2值也为中，但c1和c2内部存储的其实是”中”这个字符所对应的Unicode码，即：一个无符号的整数。

		/*
		 * 4.1对char类型变量赋值
		 *  在对char型变量赋值时，可以采用如下三种方式：
		 *  方式一：字符直接量：形如‘A’，变量中实际存储的是该字符的Unicode编码（无符号整数值），
		 *  一个char型变量只能存储一个字符。
		 *  方式二：整型直接量：范围在0~65535之间的整数，变量中实际存储的即该整数值，
		 *  但表示的是该整数值所对应的Unicode字符。
		 *  方式三：Unicode形式：形如‘\u0041’,Unicode字符的16进制形式。
		 *
		 */
		char c3='A';
		char c4=65;
		char c5='a';
		char c6=97;

		char c7='B';
		char c8=66;
		char c9='b';
		char c10=98;

		char c11='0';
		char c12=48;
		char c13='1';
		char c14=49;

		System.out.println("字符'A'对应的char数值是否为65："+(c3==c4));
		System.out.println("字符'a'对应的char数值是否为97："+(c5==c6));
		System.out.println("字符'B'对应的char数值是否为66："+(c7==c8));
		System.out.println("字符'b'对应的char数值是否为98："+(c9==c10));
		System.out.println("字符'0'对应的char数值是否为48："+(c11==c12));
		System.out.println("字符'1'对应的char数值是否为49："+(c13==c14));

		//char c5 = 男; //编译错误，必须放在单引号中
		//char c6 = ''; //编译错误，必须有字符
		//char c7 = '男性'; //编译错误，只能有1个字符
		System.out.println(2+2);     //4
		System.out.println('2'+'2'); //100，'2'的码50，加上，'2'的码50

		/*
		 * 回车 缩进(Tab)...叫控制字符，需要使用转义字符
		 *
		 * 4.2使用转义字符：特殊符号需通过\来转义
		 * 字符直接量需要包含在一对’’单引号之中，那如果想表示单引号’的字符时，需要怎么表示？想表示回车、换行符时，
		 * 怎么表示？因为单引号为特殊意义的字符， 那么，对于不方便输出的字符可以采用转义字符来表示。
		 *
		 * 常用转义字符：'\n'表示回车符,'\r'表示换行符,'\\'表示反斜杠(\),'\''表示单引号,'\'''表示双引号。
		 * '\t'表示Tab
		 * 补充：Windows操作系统目录间隔是"\",Windows也能识别"/",Linux系统目录是"/",以"/"写目录结构
		 */
		char c = '\\';
		System.out.println(c);  //输出的结果为：\
		System.out.println('\n');//回车符
		System.out.println('\r');//换行符
		System.out.println('\\');//反斜杠
		System.out.println('\'');//单引号
		System.out.println('\"');//双引号
		System.out.println('\t');//tab
	}

	/**
	 * 对boolean的测试：boolean:布尔型，1个字节
	 * 使用boolean变量进行关系运算
	 */
	@Test
	public void test6() {
		/*
		 * 1.boolean类型适用于关系、逻辑运算， 表示某个条件是否成立，一般用户程序的流程控制。
		 * 2.只允许取值true或false，true表示条件成立，而false表示条件不成立。
		 * 3.boolean型变量经常用于存储关系运算的结果，所谓关系运算就是比较两个变量的大小相等等关系
		 * （此知识点，后续详细介绍）。
		 */
		boolean b1 = true;  //true为boolean的直接量
		boolean b2 = false; //false为boolean的直接量
		//boolean b3 = 250; //编译错误，数据类型不匹配
		int age=18;
		System.out.println(age<120);//true
		System.out.println(age>20);//false
	}

	/**
	 * 基本数据类型转换
	 *    数据类型从小到大依次为:
	 *	  byte,short,int,long,float,double
	 *	        char,
	 *  1)两种形式:
	 *    1.1)自动类型转换:从小类型转到大类型
	 *    1.2)强转类型转换:从大类型转到小类型
	 * 	     (要转换成的数据类型)变量
	 * 		强转有可能会溢出或丢失精度
	 *   2)两点规则:
	 *    2.1)整数直接量可以直接赋值给byte,short,char，但不能超范围
	 *    2.2)byte、short、char型数据参与运算时， 先一律转换为int再运算
	 */
	@Test
	public void test7() {
		int a = 5;
		long b = a; //自动类型转换(小到大)
		int c = (int)b; //强制类型转换(大到小)

		long d = 5;   //自动类型转换
		double e = 5; //自动类型转换

		long f = 10000000000L;
		int g = (int)f;
		System.out.println(g); //强转有可能会发生溢出

		double h = 356.896547;
		int i = (int)h;
		System.out.println(i); //强转有可能会丢失精度

		byte b1 = 5;
		byte b2 = 6;
		byte b3 = (byte)(b1+b2);
		/*
		 * 不同的基本类型直接可以相互转化，主要有两种方式：
		 * 1.自动类型转化（隐式类型转换）：从小类型到大类型可以自动完成。类型的大小关系如下所示：
		 * 	byte  short  int  long  float  double
		 * 		  char
		 * 2.强制转化：从大类型到小类型需要强制转换符，语法如下：（需要转换成的类型）变量
		 * 因为大类型的精度值大于小类型，取值范围大于小类型，所以，当使用强制转化时，
		 * 有可能会造成精度的损失或者溢出，所以，在使用强制转化时要求显式的告诉编译器，正在进行强制转换
		 */
		/*
		 * 强制转换时的精度丧失和溢出 基本类型转化如下示例所示，注意强制转换时可能会造成的精度丧失和溢出。
		 */
		long l1 = 1024l;
		//int i = (int) l1;  //需要加强制转化符由于1024在int的范围内，所以没有产生溢出
		long l = 1024L * 1024 * 1024 * 4;
		int j = (int) l;    //会产生溢出
		System.out.println(j);  // 结果为：0
		double pi = 3.1415926535897932384;
		//float f = (float) pi;   //会造成精度的损失，因为单精度的精确度小于double
		System.out.println(f);  //结果为：3.1415927

		/*
		 * 数值运算时的自动转换 如果在一个表达式中出现了多种数据类型，则运算结果会自动的向较大的类型进行转化，
		 */
		//由于有long型的直接量参与，整个表达式的结果为long
		long distance = 10000 * 365 * 24 * 60 * 60 * 299792458l;
		//由于有double型的直接量599.0参与，整个表达式的结果为 double
		double change = 800 - 599.0;
		//结果为0.0，右边都是int型数据运算结果也为int类型，结果为0，再赋值给double型，将0转化为 0.0
		double persent1 = 80 / 100;
		//结果为0.8，右边表达式有double型直接量参与， 运算结果为double型
		double persent2 = 80.0 / 100;

		/*
		 * byte、char、short转换为int
		 * 1.在前面所介绍的8种数据类型中，byte、char、short、int、long都表示整数类型，
		 *  而整型的直接量为int，在实际使用中，为了方便使用，遵循了如下的规则：
		 *  int直接量可以直接赋值给byte、char和short，只要不超过其表示范围。
		 * 2.byte、char、short三种类型参与运算时，先一律转换成int类型再进行运算。
		 */
		byte  x  = 97;
		short  s  = 97;
		char  z  = 97;
		int  num = x + x;   //num的值为194
	}

	/**
	 * Scanner的用法测试
	 */
	@Test
	public void test8() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入年龄：");
		int age=scanner.nextInt();
		//java.util.InputMismatchException 输入不匹配异常  第一个异常
		//发生原因：输入数据的类型和接收数据的类型不匹配
		System.out.println("请输入价格：");
		double price=scanner.nextDouble();

		System.out.println("年龄："+age+"岁");
		System.out.println("价格:"+price+"元");
	}

}
