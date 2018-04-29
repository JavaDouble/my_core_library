package com.learn.javaee.unit03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.javaee.unit03.dao.EmpDao;
import com.learn.javaee.unit03.dao.EmpDaoImpl;
import com.learn.javaee.unit03.entity.Emp;






/**
 * Unit03 案例1 查询员工信息 模拟
 *
 * @author Double
 *
 */
public class FindEmpServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = 8433766911294641183L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//三大步骤：1.接收参数 2.处理业务 3.输出相应

		//2.查询所有员工
		EmpDao dao=new EmpDaoImpl();
		List<Emp> list=dao.findAll();

		//3.返回响应信息
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<p>员工信息列表</p>");
		pw.println("<a href='add_emp.html'>增加员工</a>");
		pw.println("<table border='1' cellspacing='0' width='40%'>");
		pw.println("	<tr>");
		pw.println("		<td>编号</td>");
		pw.println("		<td>姓名</td>");
		pw.println("		<td>工作</td>");
		pw.println("		<td>薪资</td>");
		pw.println("	</tr>");
		for(Emp emp:list){
			pw.println("<tr>");
			pw.println("	<td>"+emp.getEmpno()+"</td>");
			pw.println("	<td>"+emp.getEname()+"</td>");
			pw.println("	<td>"+emp.getJob()+"</td>");
			pw.println("	<td>"+emp.getSal()+"</td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.close();
	}
}
