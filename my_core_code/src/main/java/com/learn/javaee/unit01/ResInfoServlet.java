package com.learn.javaee.unit01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Unit01 案例5
 *
 * @author Double
 *
 */
public class ResInfoServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = -1935566782491208288L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//请求行
		System.out.println("请求方式:"+request.getMethod());

		System.out.println("请求的路径信息:"+request.getRequestURL());
		System.out.println("请求的资源路径:"+request.getRequestURI());
		System.out.println("请求的Servlet路径:"+request.getServletPath());

		System.out.println("请求的协议种类:"+request.getProtocol());
		//消息头
		Enumeration<String> e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String key=e.nextElement();
			String value=request.getHeader(key);
			System.out.println(key+""+value);
		}
		//实体内容

		//状态行
		//消息头
		response.setContentType("text/html");
		//实体内容
		PrintWriter pw=response.getWriter();
		pw.println("<p>HelloServlet!</p>");
		pw.close();
	}
}
