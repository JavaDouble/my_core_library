package com.learn.javaee.unit02;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用后缀*.php 重构Unit02代码 3个案例 分别是：RegisterServlet、ListEmpServlet和AddEmpServlet
 * 一个项目中，web.xml网名只能出现一个*.do
 *
 * 相应页面register.html addEmp.html 			位置：webapp/下
 * 练习第二遍的页面：register.html addEmp.html	位置：webapp/html/下
 *
 * @author Double
 *
 */
public class Unit02Servlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = 6705143735632076480L;

	/**
	 * 统一处理路径
	 *
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if("/addEmp.php".equals(path)){
			addEmpServlet(request, response);
		}else if("/listEmp.php".equals(path)){
			listEmpServlet(request, response);
		}else if("/register.php".equals(path)){
			registerServlet(request, response);
		}else{
			throw new RuntimeException("查无此页");
		}
	}

	/**
	 * 重构 Unit02 案例1 注册案例	 RegisterServlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void registerServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//只能解决post产生的乱码(方案3)
		request.setCharacterEncoding("utf-8");
		//1.接受浏览器发送过来的数据
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String[] arr=request.getParameterValues("checkbox");
		//处理请求乱码(方案1)
		//username = new String(username.getBytes("iso-8859-1"),"UTF-8");
		//2.利用这些数据处理业务
		System.out.println("帐号:"+username);
		System.out.println("密码:"+password);
		System.out.println("性别:"+gender);
		if(arr!=null){
			for(String str:arr){
				System.out.println("兴趣:"+str);
			}
		}
		//3.给浏览器返回响应信息
		//处理响应乱码
		//response.setCharacterEncoding("utf-8");
		//可省略 省略则编码下面编码方式相同(utf-8) 服务器响应编码设置
		response.setContentType("text/html;charset=utf-8");//浏览器编码设置
		PrintWriter pw=response.getWriter();
		pw.println("<p>注册成功!</p>");
		pw.close();
	}

	/**
	 * 重构 Unit02 案例2 	查询案例：ListEmpServlet
	 * 查询Oralce数据库中的t_emp_lqs表中的所有信息
	 * 数据库文件在src/main/resources的jdbcTest包下的oracle test.sql
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void listEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			PreparedStatement ps=conn.prepareStatement("SELECT * FROM t_emp_lqs");
			ResultSet rs=ps.executeQuery();

			pw.println("<html>");
			pw.println("<head meta='charset='UTF-8''></head>");
			pw.println("<body>");
			pw.println("<table border='1' cellpadding='0' cellspacing='0' width='60%'>");
			pw.println("<caption>员工信息列表</caption>");
            pw.println("<tr><td>ID</td><td>姓名</td><td>薪水</td><td>年龄</td></tr>");
			while(rs.next()){
				int id=rs.getInt("id");
				String username=rs.getString("username");
				double salary=rs.getDouble("salary");
				int age=rs.getInt("age");
				pw.println("<tr><td>"+
                        id+"</td><td>"+
                        username+"</td><td>"+
                        salary+"</td><td>"+
                        age+"</td></tr>");
			}
			pw.print("</table>");
			pw.print("<a href='addEmp.html'>增加员工信息</a>");
			pw.println("</BODY>");
			pw.println("</HTML>");
			pw.flush();
		} catch (Exception e) {
			pw.println("系统繁忙，请稍后重试");
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					pw.println("数据库关闭失败!");
				}
			}
		}
		pw.close();
	}

	/**
	 * 重构 Unit02 	案例3 	增加员工案例：AddEmpServlet
	 * 向Oralce数据库中的t_emp_lqs表插入一条员工信息
	 * 数据库文件在src/main/resources的jdbcTest包下的oracle test.sql
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter pw=response.getWriter();
		String username=request.getParameter("username");
		double salary=Double.valueOf(request.getParameter("salary"));
		int age=Integer.parseInt(request.getParameter("age"));

		//查看是否获取到页面数据
		System.out.println("姓名："+username+"，薪水："+salary+"，年龄："+age);

		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			String sql="INSERT INTO t_emp_lqs VALUES "+"(emp_id_seq.NEXTVAL,?,?,?)";
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql);

			ps.setString(1,username);
			ps.setDouble(2,salary);
			ps.setInt(3,age);
			ps.executeUpdate();

			pw.println("添加成功");
		} catch (Exception e) {
			pw.println("系统繁忙，稍后重试");
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (Exception e) {
					pw.println("数据库关闭失败");
				}
			}
		}
		pw.println("<h1>员工信息</h1>");
		pw.println("<h1>姓名:"+username+"</h1>");
		pw.println("<h1>薪水:"+salary+"</h1>");
		pw.println("<h1>年龄:"+age+"</h1>");
		pw.close();
	}


}
