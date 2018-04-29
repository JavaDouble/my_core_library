package com.learn.javaee.unit13;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * Java日志的演示
 * JUL和logging、 slf4j和logback、 log4j和log4j 2
 *
 * @author Double
 *
 */
public class LogDemos {

	/**
	 * JUL:java.util.logging，JDK自带的日志系统，比较混乱，不经常使用.
	 *
	 * Logger中有2个比较重要的概念，分别是记录器(Logger)与处理器(Handler)，二者分别完成以下功能：
	 * （1）Logger：记录日志，设置日志级别等。    （2）Handler：确定输出位置等。
	 *
	 * @throws IOException
	 * @throws SecurityException
	 */
	@Test
	public void test1() throws SecurityException, IOException {
		/*
		 * 使用JUL的步骤：
		 * 1.创建日志记录器		2.创建日志处理器		3.设置日志级别及内容
		 * 4.处理器设置日志格式	5.注册处理器		6.记录日志消息
		 */
		/* 1.创建Logger对象
		 * 一般通过getLogger来获取对象，而不能通过构造函数直接构造:
		 * static Logger getLogger(String name)：为指定子系统查找或创建一个 logger。
		 * static Logger getLogger(String name, String resourceBundleName)
		 *  为指定子系统查找或创建一个 logger。
		 * 注意：name是Logger的名称，当名称相同时候，同一个名称的Logger只创建一个。
		 *
		 * Logger原则上可以任意命名，但实际上一般是与Logger所在包或者所有类的名称相同。
		 *
		 */

		//日志记录器
		Logger logger = Logger.getLogger("LogDemos");
		//日志处理器
		FileHandler fileHandler = new FileHandler("d://test.txt");

		/*
		 * Logger的级别： 比log4j的级别详细，全部定义在java.util.logging.Level里面。
		 * 设置日志级别：SEVERE（最高值）、WARNING、INFO、CONFIG、FINE、FINER和FINEST（最低值）
		 * 此外，还有一个级别 OFF，可用来关闭日志记录，使用级别 ALL 启用所有消息的日志记录。
		 *
		 * logger默认的级别是INFO，比INFO更低的日志将不显示。Logger的默认级别定义是在jre安装目录的lib下面。
		 * # Limit the message that are printed on the console to INFO and above.
		 * java.util.logging.ConsoleHandler.level = INFO
		 *
		 * 通过Level属性，可以简单的修改Logger的级别，以达到开关日志的目的。
		 */
		//需要记录的日志消息
		LogRecord lr = new LogRecord(Level.INFO, "This is a text log.");

		/*
		 * Logger的Formatter
		 * Formatter 为格式化 LogRecords 提供支持。
		 * 一般来说，每个日志记录 Handler 都有关联的 Formatter。Formatter 接受 LogRecord，
		 * 并将它转换为一个字符串。有些 formatter（如 XMLFormatter）
		 * 需要围绕一组格式化记录来包装头部和尾部字符串。可以使用 getHeader 和 getTail 方法来获得这些字符串。
		 *
		 * LogRecord 对象用于在日志框架和单个日志 Handler 之间传递日志请求。
		 * LogRecord(Level level, String msg):用给定级别和消息值构造 LogRecord。
		 *
		 */

		//为处理器设置日志格式：Formatter
		SimpleFormatter sf = new SimpleFormatter();
		fileHandler.setFormatter(sf);
		//注册处理器
		logger.addHandler(fileHandler);
		//记录日志消息
		logger.log(lr);
	}

	/**
	 * slf4j+logback：logback一般使用.xml文件配置日志信息
	 *
	 */
	@Test
	public void test2() {
		//1.日志的基本模板
		//org.slf4j.Logger logger = LoggerFactory.getLogger("com.learn.javaee.unit13.LogDemos");
		org.slf4j.Logger logger = LoggerFactory.getLogger(LogDemos.class);
	    logger.debug("Hello world.");
	    //slf4j规定日志级别：trace、debug、info、warn、error四个级别，从大到小。默认debug级别
	    //TRACE < DEBUG < INFO <  WARN < ERROR
	    logger.trace("日志级别trace：");
	    logger.debug("日志级别debug：");
	    logger.info("日志界别info:");
	    logger.warn("日志级别warn：");
	    logger.error("日志级别error:");
	    //2.打印记录器状态:打印内部状态
	    LoggerContext lc =(LoggerContext) LoggerFactory.getILoggerFactory();
	    StatusPrinter.print(lc);
	}

	/**
	 * slf4j+logback: 日志界别TRACE < DEBUG < INFO <  WARN < ERROR
	 */
	@Test
	public void test3() {
		// get a logger instance named "com.foo". Let us further assume that the
		// logger is of type  ch.qos.logback.classic.Logger so that we can
		// set its level
		ch.qos.logback.classic.Logger logger =
		        (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.learn");
		//set its Level to INFO. The setLevel() method requires a logback logger
		logger.setLevel(ch.qos.logback.classic.Level.INFO);
		ch.qos.logback.classic.Logger barlogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo.javaee");

		// This request is enabled, because WARN >= INFO
		logger.warn("Low fuel level.");

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting search for nearest gas station.");

		// The logger instance barlogger, named "com.foo.Bar",
		// will inherit its level from the logger named
		// "com.foo" Thus, the following request is enabled
		// because INFO >= INFO.
		barlogger.info("Located nearest gas station.");

		// This request is disabled, because DEBUG < INFO.
		barlogger.debug("Exiting gas station search");

	}

	/**
	 * slf4j+logback:官方建议使用.xml配置logback日志
	 *
	 * Logback可以以编程方式配置，也可以使用XML或Groovy格式表示的配置脚本进行配置。
	 * 可以在src/main/resources下载成功logback.xml，
	 * 无法src/main/resources/logTest加载，怎么解决？web项目在web.xml可以指定配置路径
	 * 	<context-param>
        	<param-name>log4j2Configuration</param-name>
        	<param-value>classpath:/conf/log4j.properties</param-value>
    	</context-param>

	 * slf4j-api-1.7.25.jar+logback-classic-1.2.3.jar
	 */
	@Test
	public void test4() {
		org.slf4j.Logger logger = LoggerFactory.getLogger(LogDemos.class);
		logger.info("logback 成功了");
		logger.error("logback 成功了");
		logger.debug("logback 成功了");
	}

	/**
	 * JCL+log4j: log4j 一般使用.properties配置文件
	 * commons-logging-1.2.jar和log4j-1.2.7.jar
	 */
	@Test
	public void test5() {
		//实现的时commoms.logging规范
		Log log = LogFactory.getLog(LogDemos.class);

		// 输出不同级别的提示
		log.debug("调试信息");
		log.info("信息提示");
		log.warn("警告");
		log.error("异常");

	}

	/**
	 * slf4j+log4j: log4j 一般使用.properties配置文件
	 * log4j+slf4j，必须导入slf4j-log4j12-1.7.25.jar关联包
	 * 以及slf4j-api-1.7.25.jar包和log4j-1.2.7.jar包
	 */
	@Test
	public void test6() {
		//实现的时slf4j规范
		org.slf4j.Logger logger = LoggerFactory.getLogger(LogDemos.class);

		// 输出不同级别的提示
		logger.debug("调试信息");
		logger.info("信息提示");
		logger.warn("警告");
		logger.error("异常");

	}


	/**
	 * JCL+log4j2: log4j2不再支持properties文件了，只支持xml或者json。
	 * commons-logging-1.2.jar和log4j2-1.2.7.jar
	 */
	@Test
	public void test7() {

	}


	/**
	 * slf4j+log4j2: log4j2不再支持properties文件了，只支持xml或者json。
	 * 默认不指定位置，可以放在src/main/resources下。如果需要自定义位置，需要在上面的web.xml中添加
	 * 	<context-param>
        	<param-name>log4j2Configuration</param-name>
        	<param-value>classpath:/conf/log4j2.xml</param-value>
    	</context-param>

		所需jar包：slf4j-api-1.7.25.jar,log4j-core-2.11.0.jar+log4j-api-2.11.0.jar
		+log4j-slf4j-impl-2.11.0.jar+log4j-web-2.11.0.jar+disrupto-3.4.2.jar
		6个必须jar。
	 */
	@Test
	public void test8() {
		/**
		 * 这里通过logger的名字来获取logger，LoggerFactory和Logger都是slf4j包里的，
		 * 没有用到log4j2的任何类，这就是把log4j2的scope配置为runtime的原因
		 */
		org.slf4j.Logger logger = LoggerFactory.getLogger("com.learn.javaee.LogDemos");

		System.out.printf("\n-- START %s\n", LocalDateTime.now());
		try {
			while (true) {
				logger.info("show something " + LocalDateTime.now());
			}
		} finally {
			System.out.printf("\n-- DONE %s\n", LocalDateTime.now());
		}
	}

}
