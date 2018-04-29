package com.learn.javaee.unit05.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.javaee.unit05.dao.EmpDao;
import com.learn.javaee.unit05.dao.EmpDaoImpl;
import com.learn.javaee.unit05.entity.Emp;


/**
 * 模拟使用Model 2,MVC模式开发项目(servlet+jsp)
 * 相关页面：webapp/emp_list.jsp
 * 练习页面：webapp/jsp/emp_list.jsp
 *
 * 引出转发		student.jsp 演示EL表达式和JSTL	位置：webapp/jsp/下
 *
 * @author Double
 *
 */
public class FindEmpServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respnose) throws ServletException, IOException {
		//查询所有员工
		EmpDao dao= new EmpDaoImpl();
		List<Emp> list=dao.findAll();

		/*
		 * 转发：1.把所需转发的数据绑定到request对象
		 * 2.转发 写相对路径  web项目的所有路径是基于存储在tomcat服务器的路径
		 * request.getRequestDispatcher("emp_list.jsp").forward(request,respnose);
		 */

		//将数据存入request
		request.setAttribute("emps",list);

		//webapp/jsp/emp_list.jsp
		//转发：将请求转发给jsp,让它继续处理
		//目标：/my_core_code/jsp/emp_list.jsp	或者/my_core_code/emp_list.jsp
		//当前:/my_core_code/fEmp

		//request.getRequestDispatcher("jsp/emp_list.jsp").forward(request,respnose);
		request.getRequestDispatcher("emp_list.jsp").forward(request,respnose);
	}
}
