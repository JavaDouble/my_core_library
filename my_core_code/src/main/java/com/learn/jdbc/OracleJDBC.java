package com.learn.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;




/**
 * JDBC：MySQL数据库
 *
 * @author Double
 *
 */
public class OracleJDBC {
	/**
	 * JDBC java数据库连接
	 * JDBC是JAVA提供的一套标准连接数据库的接口，规定了连接数据库的步骤和功能。不同的数据库提供商
	 * 都提供了一套JDBC的实现类，它们称为数据库驱动。JDBC主要接口有：
	 * DriverManager:用于加载驱动并建立数据库连接 Connection:表示于特定数据库的连接会话
	 * Statement：用于执行SQL语句 ResultSet：表示查询结果集
	 * 连接Oracle数据库
	 *
	 * JDBC最底层是使用Socket中TCP通信实现的
	 */
	@Test
	public void test1() {
		/*
		 * 使用JDBC连接数据库的步骤：1.加载驱动包:Class.forName()
		 * 2.调用驱动建立连接:DriverManager 3.通过连接创建语句对象：Connection
		 * 4.执行SQL语句：Statement 5.若是查询语句会得到结果集：RestultSet
		 * 6.遍历结果集获取查询结果 7.关闭连接
		 */
		Connection conn=null;
		try {
			/*
			 * 1.加载驱动包 不同数据库传入的字符串内容不一样
			 * 若抛出:java.lang.ClassNotFoundException 通常由两种情况导致：
			 * 1.没有在项目中导入驱动包 2.forName方法中字符串格式有误
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//oracle.jdbc.OracleDriver.class
			//Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("驱动加载完毕!");
			/*
			 * 通过DriverManager与数据库建立连接 使用静态方法getConnection,要传入三
			 * 个参数 参数1：:数据库地址，不同数据库格式不同
			 * url:@IP(数据库服务器的IP)：端口(1521)：sid(oracle/xe/orcl)
			 * 参数2：数据库用户名  参数3：数据库密码
			 */
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			System.out.println("连接数据库完毕!");
			//3.
			Statement stmt=conn.createStatement();
			/*
			 * Statement针对不同类型的SQL语句有不同的执行方法
			 * ResultSet executeQuery(String sql) 用来执行查询语句(DQL)的方法
			 *
			 * int ecuteUpdate(String sql)用来执行DML语句的方法，返回值为执行了
			 * 该SQL后影响了数据库表中多少条数据
			 *
			 * boolean execute(String sql)可以执行所有类型的SQL语句，但是DQL，DML
			 * 都有专门的方法，所以该方法通常用来执行DDL语句。当返回值为true时表示该SQL
			 * 语句执行后有结果集。
			 */
			//String sql="select * from emp_lqs";
			//ResultSet rs=stmt.executeQuery(sql);
			//4.
			String s="CREATE TABLE userinfo_lqs( "
									+	"id NUMBER(5), "
									+ "username VARCHAR2(30), "
									+ "password VARCHAR2(30), "
									+ "email VARCHAR2(30), "
									+ "nickname VARCHAR(30), "
									+ "account NUMBER(10,2) "
									+")";
			//System.out.println(s);
			//5.
			stmt.execute(s);
			System.out.println("执行完毕!");
			//6.
	/*		while(rs.next()){
				System.out.println(rs.getInt("empno")+","+rs.getString("ename"));
			}*/
			//7.关闭连接释放资源
			conn.close();
		} catch (Exception e) {
			System.out.println("数据库访问异常!");
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					System.out.println("释放资源发生异常");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 创建一个序列：seq_userinfo_id_lqs 从1开始，步进为1
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test2() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			//System.out.println(11);
			Statement stat= conn.createStatement();
			String sql="CREATE SEQUENCE seq_userinfo_id_lqs "+"START WITH 1 "+" INCREMENT BY 1";
			System.out.println(sql);
			stat.execute(sql);
			System.out.println("创建序列完毕!");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行DML语句
	 */
	@Test
	public void test3() {
		Scanner sc= new Scanner(System.in);
		System.out.println("欢迎注册!");
		System.out.println("请输入用户名：");
		String username=sc.nextLine();
		System.out.println("请输入密码：");
		String password=sc.nextLine();
		System.out.println("请输入邮箱：");
		String email=sc.nextLine();
		System.out.println("请输入昵称：");
		String nickname=sc.nextLine();

		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println(11);
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");
			Statement stat=conn.createStatement();
			//语句拼接尾部+空格" " 不加空格 报ORA-00933：SQL 命令未正确结束
			String sql="INSERT INTO userinfo_lqs "+"(id,username,password,email,nickname,account) " +"VALUES "+"(seq_userinfo_id_lqs.NEXTVAL," +"'"+username+"','"+password+"',"+"'"+email+"','"+nickname+"',5000)";
			System.out.println(sql);//事务自动提交
			int i=stat.executeUpdate(sql);
			if(i>0){
				System.out.println("插入成功");
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行查询语句
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test4() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			Statement stat=conn.createStatement();
			String sql="SELECT id,username,password,email,nickname,account " +"FROM userinfo_lqs";
			System.out.println(sql);
			ResultSet rs=stat.executeQuery(sql);
			/*
			 * 遍历结果集ResultSet ResultSet提供方法：boolean next()使结果集
			 * 向下表示一条记录，当查询出的结果集指针默认指向结果集第一条数据之上，当调用
			 * next方法后指针向下移动到第一条，若结果集右该条记录则返回true。再次调用next
			 * 指针会继续向下表示下一条记录。
			 */
			while(rs.next()){
				/*
				 * ResultSet提供了获取当前记录某个字段对应值的一系列方法，根据字段类型不同
				 * 方法名不同
				 * int getInt(String colName)根据给定的字段名获取该整数类型字段的值
				 * String getString(String colName)
				 * 根据给定的字段名获取该字符串类型字段的值 对应的数据类型都有对应方法
				 */
				String str=rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password")+","+rs.getString("email")+","+rs.getString("nickname")+","+rs.getDouble("account");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 分页查询
	 *
	 * 查看emp_lqs表中工资表排名的员工信息 每页显示5条 查看第2页的内容
	 * 列出empno,ename,sal,job,deptno
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test5() {
		Connection conn = null;
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("请输入页码：");
			int page=Integer.parseInt(sc.nextLine());
			System.out.println("请输入每页条目数：");
			int pageSize=Integer.parseInt(sc.nextLine());
			int start=(page-1)*pageSize+1;
			int end=page*pageSize;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			Statement stat=conn.createStatement();
			String sql="SELECT * "+"FROM(SELECT ROWNUM r,t.* "+"FROM(SELECT empno,ename,sal,job,deptno "+"FROM emp_lqs "+"ORDER BY sal DESC) t "+"WHERE ROWNUM<="+end+")"+"WHERE r>="+start;
			System.out.println(sql);
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next()){
				String str=rs.getInt("empno")+","+rs.getString("ename")+","+rs.getDouble("sal")+","+rs.getString("job")+","+rs.getInt("deptno");
				System.out.println(str);
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 编写一个方法，验证该用户名是否已经被使用
	 *
	 * @author Double
	 *
	 */
	/**
	 * 该方法用来检测userinfo表中是否已经存在
	 * 名字为给定用户名的记录
	 *
	 * @param username
	 * @return true：该用户已经存在 false：该用户不存在
	 */
	private static boolean checkUsername(String username) {
		try {
			//System.out.println(username);
			Class.forName("oracle.jdbc.OracleDriver");
			//连接数据库
			String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user="scott";
			String password="tiger";

			Connection conn=DriverManager.getConnection(url, user, password);

			System.out.println("Connection接口的实现类："+conn.getClass());

			String sql="SELECT id FROM userinfo_lqs WHERE username='"+username+"'";
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Test
	public void test6() {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入用户名：");
		String inputUsername=sc.nextLine().trim();
		if(checkUsername(inputUsername)){
			System.out.println("用户名已存在");//表中
		}else{
			System.out.println("用户名可以使用");
		}
		sc.close();
	}

	/**
	 * 编写一个方法，获取userinfo表中所有用户信息
	 * @author Double
	 *
	 */
	private static List<Userinfo> getUserinfo() {
		List<Userinfo> list = new ArrayList<Userinfo>();
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			//System.out.println(11);打桩测试是否成功
			Statement stat=conn.createStatement();
			String sql="SELECT id,username,password,email,nickname,account "+"FROM userinfo_lqs";
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String email=rs.getString("email");
				String nickname=rs.getString("nickname");
				double account=rs.getDouble("account");
				Userinfo user=new Userinfo(id, username, password, email, nickname, account);
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Test
	public void test7() {
		List<Userinfo> list=getUserinfo();
		for(Userinfo user:list){
			System.out.println(user);
		}
	}

	/**
	 * 使用DBUtility解析Oralce数据库中scott用户下的emp_lqs表
	 */
	/**
	 * 编写一个方法，获取userinfo表中所有用户信息
	 * @author Double
	 *
	 */
	private static List<Emp> parseEmp() {
		List<Emp> list = new ArrayList<Emp>();
		try {
			Connection conn=DBUtility.getConnection();
			Statement stat=conn.createStatement();
			String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno "+"FROM emp_lqs";
			System.out.println(sql);
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
			DBUtility.closeConnection();
		}
		return list;
	}
	/**
	 * 测试db.properties文件读取是否成功
	 *
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
/*	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		String path="jdbcTest/db.properties";//访问的是src/mian/resources下的文件
		Properties properties=new Properties();
		properties.load(DBUtility.class.getClassLoader().getResourceAsStream(path));

		String driver=properties.getProperty("jdbc.driver");
		String url=properties.getProperty("jdbc.url");
		String username=properties.getProperty("jdbc.user");
		String password=properties.getProperty("jdbc.password");
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(url, username, password);
		System.out.println(conn.getClass());
	}*/

	@Test
	public void test8() {
		List<Emp> list=parseEmp();
		for(Emp emp:list){
			System.out.println(emp);
		}
	}

	/**
	 * 使用Statement执行含有动态信息的SQL语句时有几个不足：
	 * 1.由于需要将动态数据拼接到SQL语句中，这导致程序复杂读高，容易出错。
	 * 2.拼接的数据若含有SQL语法内容会导致将拼接后的SQL语法含义改变而出现SQL注入攻击。
	 * 3.当大批量执行语义相同，但是含有动态数据的SQL时，效率很差。
	 *
	 * 原因：当执行一条SQL语句发送到数据库时，数据库要先将该SQL解析并生成一个执行计划
	 * (消耗资源与性能)，如果多次执行一样的SQL语句，数据库会重用执行计划，但是若多次执行语义相同
	 * 但是含有动态数据的SQL时，数据库会生成不同的执行计划，严重影响数据库开销。
	 * 例如：执行：SELECT * FROM userinfo生成一个执行计划
	 * 再执行SELECT * FROM userinfo 重用上面的执行计划
	 * 执行INSERT INTO userinfo VALUES(1,'jack',..);
	 * 执行INSERT INTO userinfo VALUES(2,'rose',..);
	 * 若执行1000次上述情况的INSERT，数据库会产生1000个执行计划
	 * Statement适合执行静态SQL语句。
	 *
	 * PreparedStatement使用它执行含有动态信息的SQL语句。优点：1.没有SQL注入问题
	 * 2.编写简单3.批量执行语义相同的SQL语句会重用执行计划。
	 *
	 *
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test9() {
		try {
			Connection conn=DBUtil.getConnection();
			System.out.println(11);//打桩测试是否连接成功
			//预编译SQL语句将动态的数据以"?"进行占位
			String sql="INSERT INTO userinfo_lqs "+"(id,username,password,email,nickname,account) "+"VALUES "+"(seq_userinfo_id_lqs.NEXTVAL,?,?,?,?,?)";
			System.out.println(sql);
			/*
			 * PreparedStatement创建的同时将与编译SQL语句传入，这时会将该SQL发送至数据库
			 * 并生成执行计划
			 */
			PreparedStatement ps=conn.prepareStatement(sql);

			for(int i=0;i<1000;i++){
				ps.setString(1,"jack"+i);
				ps.setString(2,"123456"+i);
				ps.setString(3,"jack"+i+"@qq.com");
				ps.setString(4,"jack"+i);
				ps.setDouble(5,5000.0);
				ps.executeUpdate();
			/*	int d=ps.executeUpdate();
				System.out.println(d);*/
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
	 * 使用预编译SQL解决SQL注入攻击问题
	 * 占位符？
	 * @author Double
	 *
	 */
	@Test
	public void test10() {
		try {
			Scanner sc=new Scanner(System.in);
			System.out.println("请输入用户名：");
			String username=sc.nextLine();
			System.out.println("请输入密码：");
			String password=sc.nextLine();

			Connection conn=DBUtil.getConnection();
			// 111' OR '1'='1
			String sql="SELECT id,username,password,email,nickname,account "+"FROM userinfo_lqs "+"WHERE username=? AND password=?";
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
/*			//获取列名
			ResultSetMetaData rsmd=rs.getMetaData();
			//获取第一个字段的列名
			String id=rsmd.getColumnClassName(1);*/
			if(rs.next()){
				System.out.println("登录成功!");
			}else{
				System.out.println("用户名或密码错误!");
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("数据库连接失败!");
			e.printStackTrace();
		}finally{
				DBUtil.closeConnection();
		}
	}

	/**
	 * 测试DBUtil、测试DBCP_DBUtil
	 * 其中DBCP1 1.4版本可以测试成功
	 *
	 * DBCP2 2.2测试失败
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test11() {
		try {
/*			Connection conn = DBUtil.getConnection();
			System.out.println(conn.getClass());
			System.out.println("获取连接成功!");*/

			Connection conn = DBCP_DBUtil.getConnection();
			System.out.println(conn.getClass());
			System.out.println("获取连接成功!");

/*			Connection conn = DBCP2_DBUtil.getConnection();
			System.out.println(conn.getClass());
			System.out.println("获取连接成功!");*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//DBUtil.colseConnection();
			DBCP_DBUtil.closeConnection();
			//DBCP2_DBUtil.colseConnection();
		}
	}

	/**
	 * DBCP1 1.4数据库连接池 原始连接方式 测试成功
	 *
	 * DBCP2 2.2数据库连接池 原始连接方式 测试失败
	 *
	 * @throws SQLException
	 */
	@Test
	public void test12() throws SQLException {
		String driver="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String username="scott";
		String password="tiger";
		//使用数据库连接池
		BasicDataSource bds=new BasicDataSource();
		//设置必须参数
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		//设置连接池的管理策略参数
		bds.setInitialSize(10);//初始参数
		//bds.setMaxActive(50);//老版本DBCP1.4 新版本DBCP2 2.2使用maxTotal代替
		bds.setMaxTotal(50);//最大连接数量
		//使用连接池中的数据库连接
		Connection conn=bds.getConnection();
		//执行SQL
		String sql="select 'hello' as a from dual";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			String str=rs.getString("a");
			System.out.println(str);
		}
		//归还连接到数据库连接池
		conn.close();

/*		String driver="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";

		String username="scott";
		String password="tiger";
		//使用数据库连接池
		BasicDataSource bds=new BasicDataSource();
		System.out.println(bds.getConnection());
		//设置必须参数
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);

		//设置连接池的管理策略参数
		bds.setInitialSize(10);//初始参数
		//bds.setMaxActive(50);//老版本DBCP1.4 新版本DBCP2 2.2使用maxTotal代替
		bds.setMaxTotal(50);//最大连接数量
		bds.setMaxIdle(80);  // 设置最大空闲连接
		bds.setMaxWaitMillis(6000);  // 设置最大等待时间
		bds.setMinIdle(10);  // 设置最小空闲连接

		//使用连接池中的数据库连接
		Connection conn=bds.getConnection();
		System.out.println(conn);
		//执行SQL
		String sql="select 'hello' as a from dual";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			String str=rs.getString("a");
			System.out.println(str);
		}
		//归还连接到数据库连接池
		conn.close();
*/	}

/*	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String driver="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";

		String username="scott";
		String password="tiger";

		//使用数据库连接池
		BasicDataSource bds=new BasicDataSource();
		//System.out.println(bds.getConnection());
		//设置必须参数
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);

		//设置连接池的管理策略参数
		bds.setInitialSize(10);//初始参数
		//bds.setMaxActive(50);//老版本DBCP1.4 新版本DBCP2 2.2使用maxTotal代替
		bds.setMaxTotal(50);//最大连接数量
		bds.setMaxIdle(80);  // 设置最大空闲连接
		bds.setMaxWaitMillis(6000);  // 设置最大等待时间
		bds.setMinIdle(10);  // 设置最小空闲连接

		//使用连接池中的数据库连接
		Connection conn=bds.getConnection();
		System.out.println(conn);
		//执行SQL
		String sql="select 'hello' as a from dual";
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			String str=rs.getString("a");
			System.out.println(str);
		}
		//归还连接到数据库连接池
		conn.close();
	}*/

	/**
	 * 使用 ResultSetMetaData遍历结果集
	 * 可滚动结果集
	 * 重构
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test13() {
		try {
			Connection conn=DBUtil.getConnection();
			String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM emp_lqs "+"WHERE deptno=?";
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,20);

			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsd=rs.getMetaData();
			int columnCount=rsd.getColumnCount();
			System.out.println(columnCount);
			String columnName=null;
			for(int i=1;i<=columnCount;i++){
				columnName=rsd.getColumnName(i);
				System.out.println(columnName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection();
		}
	}

	/**
	 * JDBC中对事务的控制 事务的提交与回滚是通过Connectin提供的方法来调用的。本质上事务还是
	 * 依靠数据库的实现，Connection的方法实质上也只是调用了数据库事务机制。
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test14() {
		/*
		 * 转账业务中的事务控制
		 * A用户给B用户转账那个指定金额
		 */
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入转出帐号的用户名：");
		String outName=sc.nextLine();
		System.out.println("请输入转入账户的用户名：");
		String inName=sc.nextLine();
		System.out.println("请输入转账的金额：");
		double money=Integer.parseInt(sc.nextLine());
		try {
			Connection conn=DBUtil.getConnection();
			/*
			 * JDBC中事务是默认自动提交的，即：每当执行一条DML语句后就自动COMMIT
			 */
/*			//关闭自动提交事务 默认为true
			conn.setAutoCommit(false);*/
			//开启事务(自行维护事务提交)
			DBUtil.TransBegin();
			//转出
			String outSql="SELECT account FROM userinfo_lqs "+"WHERE username=?";
			PreparedStatement ps=conn.prepareStatement(outSql);
			ps.setString(1,outName);
			ResultSet rs=ps.executeQuery();
			double account=0;
			if(rs.next()){
				account=rs.getDouble("account");
				System.out.println(account);
			}else{
				System.out.println("获取余额失败");
			}
			outSql="UPDATE userinfo_lqs SET account=account-? "+"WHERE username=?";
			PreparedStatement outPs=conn.prepareStatement(outSql);
			if(account<money){
				System.out.println("余额不足：转账失败");
				return;
			}
			outPs.setDouble(1,money);
			outPs.setString(2,outName);
			int out=outPs.executeUpdate();
			//System.out.println(out);
			if(out==0){
				System.out.println("转账失败!");
				return;
			}else{
				System.out.println("转出成功!");
			}
			//转入
			String inSql="UPDATE userinfo_lqs SET account=account+? "+"WHERE username=?";
			PreparedStatement inPs=conn.prepareStatement(inSql);
			inPs.setDouble(1,money);
			inPs.setString(2,inName);
			int in=inPs.executeUpdate();
			//System.out.println(in);
			if(in==0){
				System.out.println("转账失败");
				DBUtil.TransRollBack();
			}else{
				System.out.println("转账完毕");
				//提交事务
				DBUtil.TransCommit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
			sc.close();
		}
	}

	/**
	 * 批处理SQL语句(解决网络传输效率慢问题)
	 * 重构()
	 * 批操作：当大批量执行SQL语句时，会影响执行效率的因素主要有：
	 * 1.网络传输(SQL从客户端发送给数据库服务端)
	 * 2.执行计划的生成
	 * 3.事务的提交
	 *
	 * 使用批操作可以将大量的SQL语句缓存，然后一口气发送给服务端，减少网络调用次数，提高传输效率。
	 * 使用PreparedStatement可以有效减少执行计划的生成和注入式SQL攻击问题。
	 * 使用一次事务完成大量DML SQL语句的执行可以减少数据库实际对磁盘写的次数，从而提高DML执行效率。
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test15() {
		try {
			/*
			 * 向userinfo_lqs插入1000条数据
			 */
			Connection conn=DBUtil.getConnection();
			long start=System.currentTimeMillis();
			DBUtil.TransBegin();
			String sql="INSERT INTO userinfo_lqs "+"(id,username,password,account) "+"VALUES "+"(seq_userinfo_id_lqs.NEXTVAL,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			for(int i=1;i<=1000;i++){
				ps.setString(1,"test"+i);
				ps.setString(2,"12345"+i);
				ps.setDouble(3, 5000+i);
				ps.addBatch();//存入批中等待执行
				if(i%500==0){
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			//一次性将缓存的待执行操作全部发送给数据库
			int[] arr=ps.executeBatch();
/*			for(int i=0;i<arr.length;i++){
				System.out.print(i+" ");
			}*/
			DBUtil.TransCommit();
			long end=System.currentTimeMillis();
			System.out.println("插入1000条数据，耗时："+(end-start)/1000.0+"秒");
			System.out.println("插入成功");
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
		}
	}

	/**
	 * 自动返回主键
	 * 在进行关联插入数据时，从表中外间字段的值要保存主表中主键字段的值，那么在插入主表数据的同时
	 * 就可以返回该记录中主键字段的值就能方便的向从表中外键字段添加该值，省去单独获取主表中该主键
	 * 字段值的操作，提高效率。
	 * 例如：插入一个新部门的同时向该部门添加员工。由于部门的主键通常右序列等方式自动生成，那么在
	 * 该部门记录添加完毕后，向员工表插入员工信息时，该员工的部门号需要这个部门的部门号，那么如何
	 * 获取就是自动返回主键要解决的问题。
	 *
	 * @author Double
	 *
	 */
	@Test
	public void test16() {
		try {
			/*
			 * 向dept表中插入数据IT，BEIJING
			 * 向emp表中插入一条员工信息，其所在IT部
			 * 两条插入操作在一个事务中完成
			 */
			Connection conn=DBUtil.getConnection();
			DBUtil.TransBegin();
			String deptSql="INSERT INTO dept_lqs "+"(deptno,dname,loc) "+"VALUES "+"(seq_dept_lqs_id.NEXTVAL,?,?)";
			/*
			 * 第二个参数的意义是在通过该PS执行SQL语句后要获取该记录中给定字段的值
			 */
			System.out.println(deptSql);
			PreparedStatement dps=conn.prepareStatement(deptSql,new String[]{"deptno"});
			dps.setString(1,"IT");
			dps.setString(2,"BEIJING");
			dps.executeUpdate();
			/*
			 * 获取执行SQL后生成数据中指定字段对应的值
			 */
			ResultSet rs=dps.getGeneratedKeys();
			rs.next();
			int deptno=rs.getInt(1);
			//System.out.println(deptno);
			/*
			 * 插入员工信息
			 */
			String empSql="INSERT INTO emp_lqs "+"(empno,ename,job,sal,deptno) "+"VALUES "+"(seq_emp_lqs_id.NEXTVAL,?,?,?,?)";
			PreparedStatement eps=conn.prepareStatement(empSql);
			eps.setString(1,"jack");
			eps.setString(2,"CLERK");
			eps.setDouble(3,5000.0);
			eps.setInt(4,deptno);
			eps.executeUpdate();
			DBUtil.TransCommit();
			System.out.println("插入成功");
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
		}
	}

	/**
	 * 使用C3P0_DBUtil查询
	 */
	@Test
	public void test17() {
		try {
			Connection connection=C3P0_DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account " +"FROM userinfo_lqs";
			System.out.println(sql);

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
	public void test18() {
		try {
			Connection connection=Druid_DBUtil.getConnection();
			String sql="SELECT id,username,password,email,nickname,account " +"FROM userinfo_lqs";
			System.out.println(sql);

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
/**
 * 该类用于描述数据库中userinfo表  该类的每一个实例可以表示userinfo表中的一条记录
 * 这种用来表示数据库某张表的类称为"实体类"(PO,VO,PODO,...)
 *
 * @author Double
 *
 */
class Userinfo {
	private int id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private double account;

	public Userinfo() {

	}
	public Userinfo(int id, String username, String password, String email, String nickname, double account) {
		this();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "编号：" + id + ",姓名：" + username + ",密码：" + password + ",邮箱：" + email
				+ ",昵称：" + nickname + ",工资：" + account;
	}

}

class Emp{
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;
	private Integer deptno;

	public Emp() {
	}

	public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm,
			Integer deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Double getComm() {
		return comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comm == null) ? 0 : comm.hashCode());
		result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
		result = prime * result + ((empno == null) ? 0 : empno.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((mgr == null) ? 0 : mgr.hashCode());
		result = prime * result + ((sal == null) ? 0 : sal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emp other = (Emp) obj;
		if (comm == null) {
			if (other.comm != null)
				return false;
		} else if (!comm.equals(other.comm))
			return false;
		if (deptno == null) {
			if (other.deptno != null)
				return false;
		} else if (!deptno.equals(other.deptno))
			return false;
		if (empno == null) {
			if (other.empno != null)
				return false;
		} else if (!empno.equals(other.empno))
			return false;
		if (ename == null) {
			if (other.ename != null)
				return false;
		} else if (!ename.equals(other.ename))
			return false;
		if (hiredate == null) {
			if (other.hiredate != null)
				return false;
		} else if (!hiredate.equals(other.hiredate))
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (mgr == null) {
			if (other.mgr != null)
				return false;
		} else if (!mgr.equals(other.mgr))
			return false;
		if (sal == null) {
			if (other.sal != null)
				return false;
		} else if (!sal.equals(other.sal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate=" + hiredate
				+ ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
	}

}
