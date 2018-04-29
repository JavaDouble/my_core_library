package com.learn.javaee.unit02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Unit02  案例2：增加员工
 * Oralce数据库中的t_emp_lqs表
 * 数据库文件在src/main/resources的jdbcTest包下的oracle test.sql
 *
 * @author Double
 *
 */
public class AddEmpServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1193740260974983697L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
