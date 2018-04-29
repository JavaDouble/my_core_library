package com.learn.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 该类用于维护数据库连接的工具类
 * 使用C3P0连接池和事物管理ThreadLocal工具类
 * 读取src/main/resources包jdbcTest下的db.properties配置文件
 *
 * @author Double
 *
 */
public class C3P0_DBUtil {
	private static ThreadLocal<Connection> tl;
	private static ComboPooledDataSource cpds=null;
	private static Properties properties=new Properties();
	static{

		try {
			tl=new ThreadLocal<Connection>();

			String path="jdbcTest/db.properties";
			properties.load(C3P0_DBUtil.class.getClassLoader().getResourceAsStream(path));

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


			cpds=new ComboPooledDataSource();
			cpds.setDriverClass(driver);
			cpds.setJdbcUrl(url);
			cpds.setUser(user);
			cpds.setPassword(psw);

			cpds.setMaxPoolSize(maxActive);
			cpds.setMaxIdleTime(maxWait);

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
		Connection conn=cpds.getConnection();
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
			C3P0_DBUtil.getConnection();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			C3P0_DBUtil.closeConnection();
		}
	}
}
