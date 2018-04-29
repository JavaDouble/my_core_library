package com.learn.javaee.unit03;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Unit03 案例7 通过分页和流量统计演示ServletContext对象既可以读取常量，也可以读取变量。
 *
 * @author Double
 *
 */
public class FindDeptServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 3206414538294794949L;

	//1.tomcat在启动时会首先创建context,则context内的静态块被执行，加载了
	//web.xml中预置的参数。
	//2.tomcat给Servlet提供了方法可以获取次context对象。
	//3.tomcat内有且仅有一个context。一个context对应多个Servlet
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取分页条件size
		ServletContext scx=getServletContext();
		String size=scx.getInitParameter("size");
		System.out.println("size:"+size);
		//执行分页查询(模拟)
		System.out.println("使用size进行分页查询部门数据");

		//统计项目的流量
		Integer count=(Integer)scx.getAttribute("count");
		scx.setAttribute("count",++count);
		System.out.println("当前流量:"+count);
	}
}
