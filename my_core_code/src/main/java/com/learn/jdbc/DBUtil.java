package com.learn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 没有使用数据库连接池的工具类
 *
 * @author Double
 *
 */
public class DBUtil {
	/*
	 * ThreadLocal用于线程跨方法共享数据使用
	 * ThreadLocal内部有一个Map，key为需要共享数据的线程本身，value就是其需要共享的数据。
	 */
	private static ThreadLocal<Connection> tl;
	private static Properties properties=new Properties();
	private static String driver=null;
	private static String url=null;
	private static String user=null;
	private static String password=null;

	static{
		/*
		 * peoperties文件
		 * peoperties文件常被用作配置文件，结构简单。
		 */
		try {
			tl=new ThreadLocal<Connection>();
			String path="jdbcTest/db.properties";
			//默认的是src/main/java下的路径
			properties.load(DBUtil.class.getClassLoader().getResourceAsStream(path));
			/*
			 * src/main/java
			 * src/main/reso
			 * 在包里的数据 一般使用ClassLoader()加载
			 * Java建议：DBUtil.class.getClassLoader().getResourceAsStream(path)
			 *
			 * Java不建议：写FileInputStream()有时候会出现问题
			 * FileInputStream加载硬盘上的数据
			 */
			//默认是的项目名路径 类路径
			//properties.load(new FileInputStream(path));

			driver=properties.getProperty("jdbc.driver");
			url=properties.getProperty("jdbc.url");
			user=properties.getProperty("jdbc.username");
			password=properties.getProperty("jdbc.password");
			Class.forName(driver);

			System.out.println("driver:"+driver);
			System.out.println("url:"+url);
			System.out.println("username:"+user);
			System.out.println("password:"+password);
		} catch (Exception e) {
			System.out.println("加载静态资源失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection conn=DriverManager.getConnection(url, user, password);
		tl.set(conn);
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * @return
	 */
	public static void closeConnection() {
		if(tl.get()!=null){
			try {
				//关闭连接前，提交事务
				TransCommit();
				tl.get().close();
				tl.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 开启事务
	 * @throws Exception
	 */
	public static void TransBegin() throws Exception {
		try {
			tl.get().setAutoCommit(false);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public static void TransRollBack() {
		try {
			tl.get().rollback();
			tl.get().setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public static void TransCommit() throws SQLException {
		tl.get().commit();
		tl.get().setAutoCommit(true);
	}
}
