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
 * 使用*.asp重构Unit03 Servlet 分别是：FindEmpServlet、AddEmp PathServlet
 * 相应页面：add_emp.html 				位置：webapp/下
 * 练习第二遍的页面：add_emp.html 		位置：webapp/html/下
 *
 * LifeTimeServlet：演示的Servlet的声明周期  不重构
 * 路径规范	查询员工:/findEmp.asp	增加员工:/addEmp.asp
 *
 *
 * @author Double
 * 项目移植
 */

public class Unit03Servlet extends HttpServlet {



	/**
	 *
	 */
	private static final long serialVersionUID = 4236543643764954749L;

	/**
	 * 统一处理Servlet路径
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求路径
		String path=request.getServletPath();
		System.out.println(path);
		if("/findEmp.asp".equals(path)){
			findEmpServlet(request,response);
		}else if("/add.asp".equals(path)){
			addEmp(request,response);
		}else if ("/path.asp".equals(path)) {
			pathServlet(request, response);
		}else {
			throw new RuntimeException("查无此页");
		}
	}

	/**
	 * 重构	Unit03 案例1 FindEmpServlet代码
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findEmpServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	/**
	 * 重构	Unit03 案例2 AddEmp代码
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	 protected void addEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	//1.接受参数
	    	request.setCharacterEncoding("utf-8");
	    	String username=request.getParameter("username");
	    	String job=request.getParameter("job");
	    	Double sal=Double.parseDouble(request.getParameter("sal"));
	    	//2.处理业务
	    	EmpDao dao=new EmpDaoImpl();
	    	Emp emp=new Emp();
	    	emp.setEmpno(4);
	    	emp.setEname(username);
	    	emp.setJob(job);
	    	emp.setSal(sal);
	    	dao.save(emp);
	    	//3.返回响应
	/*    	response.setContentType("text/html;charset=utf-8");
	    	PrintWriter pw=response.getWriter();
	    	pw.println("<p>增加员工成功!</p>");
	    	pw.close();*/
	    	//重定向到查询功能 理解：建议客户自己区访问查询功能
	    	//当前：/EmpManager/add 目标：/EmpManager/findEmp
	    	response.sendRedirect("findEmp.asp");
	    }

	 	/**
	 	 * 重构 Unit03 案例3 PathServlet代码
	 	 *
	 	 * @param request
	 	 * @param response
	 	 * @throws ServletException
	 	 * @throws IOException
	 	 */
		protected void pathServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//获取访问路径
			System.out.println(request.getContextPath());//获取的是项目名称:'/my_core_code'
			System.out.println(request.getServletPath());//获取的是Servlet路径：'/path'

			System.out.println(request.getRequestURL());
			//获取的是完整路径：http://localhost:8080/my_core_code/path
			System.out.println(request.getRequestURI());//获取的是'/my_core_code/path'

			//不设置响应信息，服务器依然会自动给浏览器发送响应，仅仅是实体内容为空而已。
		}



}
