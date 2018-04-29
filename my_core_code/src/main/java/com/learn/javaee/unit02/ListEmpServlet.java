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
 * Unit02  案例2：查询员工信息
 * 查询Oralce数据库中的t_emp_lqs表中的所有信息  
 * 数据库文件在src/main/resources的jdbcTest包下的oracle test.sql
 *
 * @author Double
 *
 */
public class ListEmpServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = 8507855304136757563L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
