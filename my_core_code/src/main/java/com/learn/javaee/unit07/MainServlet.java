package com.learn.javaee.unit07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session的演示
 * @author Double
 *
 */
public class MainServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if("/main/login.jsd".equals(path)){
			loginServlet(request, response);
		}else if("/main/index.jsd".equals(path)){
			indexServlet(request, response);
		}else{
			throw new RuntimeException("查无此页");
		}
	}

	/**
	 * 模拟登录功能 演示session
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接受参数
		String code=request.getParameter("code");
		//存入session
		HttpSession session=request.getSession();
		//session中可以存储任意类型的数据(Object) cookie只能存储字符串
		session.setAttribute("code", code);
		//服务器向浏览器发送响应时,会自动创建一个Cookie,然后将SID存入此cookie,再将cookie发送给浏览器。
		//Cookie cookie=new Cookie("JSESSION",session.getId());
	}

	/**
	 * 登录之后的主页 演示session
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void indexServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session
		//由于本次请求浏览器传入SID，服务器就根据此SID找到那个旧的session,可以从中读取之前存的数据
		HttpSession session=request.getSession();
		System.out.println("获取HttpSession接口的实现类："+session);
		String code=(String)session.getAttribute("code");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println(code);
		pw.close();
	}
}
