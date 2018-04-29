package com.learn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用配置文件连接Oracle数据库
 *
 * @author Double
 *
 */
public class DBUtility {
	private static ThreadLocal<Connection> tl;
	private static Properties properties= new Properties();
	private static String driver=null;
	private static String url=null;
	private static String username=null;
	private static String password=null;
	static{
		try {
			tl = new ThreadLocal<Connection>();
			//加载配置文件
			String path="jdbcTest/db.properties";//访问的是src/mian/resources下的文件
			/*
			 * properties就是为了读取 *.properties文件而设计的API，其底层就是文本文件IO Properties本身实现乐Map接口
			 * 内部是散列表，限定key和value都是字符串String类型。
			 * 方法：load(流)将文件就读取为散列表 load(InputStream)和load(Reader)
			 *
			 * String getProperty(key) 查询value
			*/
			properties.load(DBUtility.class.getClassLoader().getResourceAsStream(path));
			//下面这种写法也可以
			//properties.load(DBUtility.class.getClassLoader().getResource(path).openStream());

			driver=properties.getProperty("jdbc.driver");
			url=properties.getProperty("jdbc.url");
			username=properties.getProperty("jdbc.username");
			password=properties.getProperty("jdbc.password");
			Class.forName(driver);
			System.out.println("加载成功");
		} catch (Exception e) {
			System.out.println("静态资源加载失败");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection() throws SQLException {
		tl.set(DriverManager.getConnection(url, username, password));
		return DriverManager.getConnection(url, username, password);

	}
	public static void closeConnection() {
		if(tl.get()!=null){
			try {
				tl.get().close();
				tl.remove();
			} catch (Exception e) {
				System.out.println("关闭时发生异常");
				e.printStackTrace();
			}
		}
	}
/*	public static void main(String[] args) {
		System.out.println(11);//测试代码
	}*/
}
