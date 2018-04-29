package com.learn.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;


/**
 * 该类用于维护数据库连接的工具类
 * 使用Alibaba Druid连接池，JDBC_oracle_db.properties和事物管理ThreadLocal工具类
 *
 * @author Double
 *
 */
public class Druid_DBUtil {
	/*
	 * ThreadLocal用于线程跨方法共享数据使用
	 * ThreadLocal内部有一个Map，key为需要共享数据的线程本身，value就是其需要共享的数据。
	 */
	private static ThreadLocal<Connection> tl;
	private static DruidDataSource dds=null;
	private static Properties properties=new Properties();

	static{
		/*
		 * peoperties文件
		 * peoperties文件常被用作配置文件，结构简单。
		 */
		try {
			tl=new ThreadLocal<Connection>();
			//加载配置文件
			/*
			 * 第一种路径写法
			 */
			String path="jdbcTest/db.properties";
			//默认的是src/main/resources下的路径
			properties.load(Druid_DBUtil.class.getClassLoader().getResourceAsStream(path));

			/*
			 * 第二种路径写法
			 */
			//String path="src/main/java/jdbc/oracle/resources/JDBC_oracle_db.properties";
			//默认是的项目名路径
			//properties.load(new FileInputStream(path));
			//读取db.properties中的数据：driver,url,user,psw
			String driver=properties.getProperty("jdbc.driver");
			String url=properties.getProperty("jdbc.url");
			String user=properties.getProperty("jdbc.username");
			String psw=properties.getProperty("jdbc.password");

			System.out.println("driver:"+driver);
			System.out.println("url:"+url);
			System.out.println("user:"+user);
			System.out.println("password:"+psw);

			int maxActive=Integer.parseInt(properties.getProperty("jdbc.maxTotal"));
			int maxWait=Integer.valueOf(properties.getProperty("jdbc.maxWait"));

			System.out.println("maxActive:"+maxActive);
			System.out.println("maxWait:"+maxWait);

			//初始化连接池
			dds=new DruidDataSource();
			//Class.forName(driver);
			dds.setDriverClassName(driver);
			//DriverManager.getConnection(url, user, password)
			dds.setUrl(url);
			dds.setUsername(user);
			dds.setPassword(psw);
			//设置最大连接数
			dds.setMaxActive(maxActive);
			//设置最大等待时间
			dds.setMaxWait(maxWait);
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
		//调用getConnection()方法时，返回的是dds.getConnection()
		//org.apache.commons.dbcp.SQLNestedException: Cannot get a connection
/*		tl.set(dds.getConnection());
		return dds.getConnection();*/
		Connection conn=dds.getConnection();
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

	/**
	 * 测试DBUtil工具类
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Druid_DBUtil.getConnection();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Druid_DBUtil.closeConnection();
		}
	}
}
