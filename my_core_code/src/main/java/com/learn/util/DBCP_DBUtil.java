package com.learn.util;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;




/**
 * DBCP数据库连接池工具类  dbcp1.4版本
 * 使用DBCP连接池和事物管理ThreadLocal工具类
 * 读取src/main/resources包jdbcTest下的db.properties配置文件
 *
 * @author Double
 *
 */
public class DBCP_DBUtil {
	private static ThreadLocal<Connection> tl;
	private static Properties properties=new Properties();
	private static BasicDataSource bds=null;

	static{
		try {
			tl=new ThreadLocal<Connection>();
			String path="jdbcTest/db.properties";
			properties.load(DBCP_DBUtil.class.getClassLoader().getResourceAsStream(path));

			String driver=properties.getProperty("jdbc.driver");
			String url=properties.getProperty("jdbc.url");
			String user=properties.getProperty("jdbc.username");
			String psw=properties.getProperty("jdbc.password");

			System.out.println("driver:"+driver);
			System.out.println("url:"+url);
			System.out.println("user:"+user);
			System.out.println("password:"+psw);

			int initialSize=Integer.valueOf(properties.getProperty("jdbc.initialSize"));
			int maxTotal=Integer.parseInt(properties.getProperty("jdbc.maxTotal"));
			int maxIdle=Integer.valueOf(properties.getProperty("jdbc.maxIdle"));
			long maxWait=Long.valueOf(properties.getProperty("jdbc.maxWait"));

			System.out.println("initialSize:"+initialSize);
			System.out.println("maxTotal:"+maxTotal);
			System.out.println("maxIdle:"+maxIdle);
			System.out.println("maxWait:"+maxWait);


			bds=new BasicDataSource();
			bds.setDriverClassName(driver);
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(psw);

			//MySQL配置
			//通过这个池创建连接的默认自动提交状态。如果不设置，则setAutoCommit 方法将不被调用。
			//true自动提交，false不自动提交
			bds.setDefaultAutoCommit(false);

			bds.setInitialSize(initialSize);
			bds.setMaxActive(maxTotal);
			bds.setMaxWait(maxWait);
			bds.setMaxIdle(maxIdle);

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
		tl.set(bds.getConnection());
		return bds.getConnection();
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

	/**
	 * 测试DBUtil工具类
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DBCP_DBUtil.getConnection();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBCP_DBUtil.closeConnection();
		}
	}
}
