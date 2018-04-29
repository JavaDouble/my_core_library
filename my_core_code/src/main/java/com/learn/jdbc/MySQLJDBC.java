package com.learn.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/**
 * JDBC：MySQL数据库
 *
 * @author Double
 *
 */
public class MySQLJDBC {
	/**
	 * DDL语句
	 */
	@Test
	public void test1() {
		/*
		 * 使用JDBC连接数据库的步骤：1.加载驱动包:Class.forName()
		 * 2.调用驱动建立连接:DriverManager 3.通过连接创建语句对象：Connection
		 * 4.执行SQL语句：PreparedStatement 5.若是查询语句会得到结果集：RestultSet
		 * 6.遍历结果集获取查询结果 7.关闭连接
		 */
		Connection conn=null;
		try {
			/*
			 * 1.加载驱动包 不同数据库传入的字符串内容不一样
			 * 若抛出:java.lang.ClassNotFoundException 通常由两种情况导致：
			 * 1.没有在项目中导入驱动包 2.forName方法中字符串格式有误
			 */
			//1.加载驱动包:Class.forName()
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("oracle.jdbc.OracleDriver");两种路径都可以
			System.out.println("驱动加载完毕!");

			//2.调用驱动建立连接:DriverManager:DriverManager 获取mysql数据库连接
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=utf-8", "root", "root");
			//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf-8", "root", "root");
			//conn=DriverManager.getConnection("jdbc:mysql///test?useUnicode=true&amp;characterEncoding=utf-8", "root", "root");
			System.out.println("连接数据库完毕!");

			/*
			 * 增加操作(插入一条表中记录) DDL语句(insert语句，update语句,delete语句)
			 */
			//3.拼写sql语句
			String sql="CREATE TABLE userinfo_lqs( "
					+ "id INT(4) ZEROFILL PRIMARY KEY AUTO_INCREMENT , "
					+ "username VARCHAR(20), "
					+ "password INT, "
					+ "email VARCHAR(20), "
					+ "nickname VARCHAR(20), "
					+ "account DOUBLE "
					+")";
			//输出sql语句
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			//5.
			ps.execute();
			System.out.println("执行完毕!");
			//7.关闭连接释放资源
			conn.close();
		} catch (Exception e) {
			System.out.println("数据库访问异常!");
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					//7.关闭数据库连接，释放资源。
					conn.close();
				} catch (Exception e) {
					System.out.println("释放资源发生异常");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * DML语句
	 */
	@Test
	public void test2() {
		Connection conn=null;
		try {
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql:///test?useUnicode=true&amp;characterEncoding=utf-8";
			String username="root";
			String password="root";
			Class.forName(driver);
			conn=DriverManager.getConnection(url, username, password);
			String sql="insert into userinfo_lqs"+"(username,account) " +"VALUES "+"('clearlove',5000)";
			PreparedStatement ps=conn.prepareStatement(sql);
			int num=ps.executeUpdate();
			if(num>0){
				System.out.println("插入成功！");
			}else{
				System.out.println("插入失败!");
			}
		} catch (Exception e) {
			System.out.println("数据库访问异常!");
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					//7.关闭数据库连接，释放资源。
					conn.close();
				} catch (Exception e) {
					System.out.println("释放资源发生异常");
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * DQL语句
	 */
	@Test
	public void test3() {
		try {
			Connection connection=DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account " +"FROM userinfo_lqs";
			System.out.println(sql);
			//Can't call commit when autocommit=true
			//MySQL事务是默认提交的。
			connection.setAutoCommit(false);//关闭自动提交
			ResultSet rs=connection.prepareStatement(sql).executeQuery();
			while(rs.next()){
				String str=rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("nickname")+","+rs.getDouble("account");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection();
		}
	}


	/**
	 * 使用DBUtility解析Oralce数据库中scott用户下的emp_lqs表
	 * 使用OracleJDBC类下的Emp实体类
	 *
	 * 编写一个方法，获取emp_lqs表中所有用户信息
	 * @author Double
	 *
	 */
	private static List<Emp> parseEmp() {
		List<Emp> list = new ArrayList<Emp>();
		try {
			Connection conn=DBUtil.getConnection();
			Statement stat=conn.createStatement();
			String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno "+"FROM emp_lqs";
			System.out.println(sql);
			DBUtil.TransBegin();
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next()){
				Integer empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				String job=rs.getString("job");
				Integer mgr=rs.getInt("mgr");
				Date hiredate=rs.getDate("hiredate");
				Double sal=rs.getDouble("sal");
				Double comm=rs.getDouble("comm");
				Integer deptno=rs.getInt("deptno");
				Emp emp=new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection();
		}
		return list;
	}
	@Test
	public void test4() {
		List<Emp> list=parseEmp();
		for(Emp emp:list){
			System.out.println(emp);
		}
	}

	/**
	 * 批量插入数据  注入式SQL
	 */
	@Test
	public void test5() {
		try {
			Connection conn=DBUtil.getConnection();

			//预编译SQL语句将动态的数据以"?"进行占位
			String sql="INSERT INTO userinfo_lqs "+"(username,password,email,nickname,account) "+"VALUES "+"(?,?,?,?,?)";
			System.out.println(sql);
			/*
			 * PreparedStatement创建的同时将与编译SQL语句传入，这时会将该SQL发送至数据库并生成执行计划
			 */
			DBUtil.TransBegin();
			PreparedStatement ps=conn.prepareStatement(sql);
			for(int i=0;i<1000;i++){
				ps.setString(1,"clearlove"+i);
				ps.setString(2,"12"+i);
				ps.setString(3,"jack"+i+"@qq.com");
				ps.setString(4,"jack"+i);
				ps.setDouble(5,5000.0+i);
				int num=ps.executeUpdate();
				if(num<=0){
					System.out.println("插入失败！");
				}
			}
			System.out.println("插入完毕");
		} catch (Exception e) {
			System.out.println("数据库连接失败!");
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection();
		}
	}

	/**
	 * 分页查询
	 * 查看emp_lqs表中工资表排名的员工信息 每页显示5条 查看第2页的内容
	 * 列出empno,ename,sal,job,deptno
	 */
	@Test
	public void test6() {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("请输入页码：");
			int page=Integer.parseInt(sc.nextLine());
			System.out.println("请输入每页条目数：");
			int pageSize=Integer.parseInt(sc.nextLine());
			int start=(page-1)*pageSize;
			int end=pageSize;
			Connection connection=DBCP_DBUtil.getConnection();

			String sql="select empno,ename,sal,job,deptno from emp_lqs limit "+start+","+end;
			System.out.println(sql);
			DBCP_DBUtil.TransBegin();
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String str=rs.getInt("empno")+","+rs.getString("ename")+","+rs.getDouble("sal")+","+rs.getString("job")+","+rs.getInt("deptno");
				System.out.println(str);
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBCP_DBUtil.closeConnection();
		}
	}

	/**
	 * 使用DBCP_DBUtil查询
	 */
	@Test
	public void test7() {
		try {
			Connection connection=DBCP_DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account " +"FROM userinfo_lqs";
			System.out.println(sql);
			//Can't call commit when autocommit=true
			//MySQL事务是默认提交的。
			DBCP_DBUtil.TransBegin();
			ResultSet rs=connection.prepareStatement(sql).executeQuery();
			while(rs.next()){
				String str=rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("nickname")+","+rs.getDouble("account");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBCP_DBUtil.closeConnection();
		}
	}

	/**
	 * 使用DBCP2_DBUtil查询
	 */
	@Test
	public void test8() {

	}

	/**
	 * 使用C3P0_DBUtil查询
	 */
	@Test
	public void test9() {
		try {
			Connection connection=C3P0_DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account " +"FROM userinfo_lqs";
			System.out.println(sql);
			//Can't call commit when autocommit=true
			//MySQL事务是默认提交的。
			C3P0_DBUtil.TransBegin();
			ResultSet rs=connection.prepareStatement(sql).executeQuery();
			while(rs.next()){
				String str=rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("nickname")+","+rs.getDouble("account");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0_DBUtil.closeConnection();
		}
	}

	/**
	 * 使用Druid_DBUtil查询
	 */
	@Test
	public void test10() {
		try {
			Connection connection=Druid_DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account " +"FROM userinfo_lqs";
			System.out.println(sql);
			//Can't call commit when autocommit=true
			//MySQL事务是默认提交的。
			Druid_DBUtil.TransBegin();
			ResultSet rs=connection.prepareStatement(sql).executeQuery();
			while(rs.next()){
				String str=rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("nickname")+","+rs.getDouble("account");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Druid_DBUtil.closeConnection();
		}
	}
}

