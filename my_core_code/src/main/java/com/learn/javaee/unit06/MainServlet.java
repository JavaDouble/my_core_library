package com.learn.javaee.unit06;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie的演示
 *
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
		if("/addCookie.net".equals(path)){
			addCookieServlet(request, response);
		}else if("/queryCookie.net".equals(path)){
			queryCookieServlet(request, response);
		}else if("/main/login.net".equals(path)){
			loginServlet(request, response);
		}else if("/main/index.net".equals(path)){
			indexServlet(request, response);
		}else if("/cost/findCost.net".equals(path)){
			findCostServlet(request, response);
		}else{
			throw new RuntimeException("查无此页");
		}

	}

	/**
	 * 演示浏览器 最后一个cookie会覆盖前一个cookie
	 * 设置cookie 服务器中设置不同的cookie
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCookieServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		//创建cookie
		Cookie c1=new Cookie("uname", "kitty");
		Cookie c2=new Cookie("city", "beijing");

		//添加cookie到response
		response.addCookie(c1);
		response.addCookie(c2);
		pw.close();
	}

	/**
	 * 演示浏览器 最后一个cookie会覆盖前一个cookie
	 * 获取cookie 浏览器中展示cookie内容
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryCookieServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		if(!("".equals(cookies))){
			for(int i=0;i<cookies.length;i++){
				String name=cookies[i].getName();
				String value=cookies[i].getValue();
				System.out.println("名称："+name+",值："+value);
			}
		}else{
			System.out.println("无cookie存在");
		}
	}
	/**
	 * 模拟登录功能 演示cookie
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.接受参数(账号密码)
		 * 2.校验账号密码
		 * 3.转发或重定向
		 * 4.校验完成后，讲账号存入cookie
		 */
		//接受参数(账号密码)
		String code=request.getParameter("code");

		//存入cookie
		//cookie中只能存一份数据，如果存入两份数据，后面一份数据会覆盖上一份数据，只能存字符串。
		Cookie cookie=new Cookie("code", code);

		//System.out.println(cookie.getPath());//null
		//修改cookie的生存时间 以秒为单位 MaxAge默认值-1 负数存到内存中(默认) 0：立刻删除此数据 正数：存到硬盘上
		cookie.setMaxAge(600000);

		//设置cookie的有效路径(静态和动态)
		//cookie.setPath("/my_core_code");
		cookie.setPath(request.getContextPath());

		//添加到response，将cookie发送给浏览器，它会自动保存。
		response.addCookie(cookie);

		//cookie中(编码方式ASCII码)不允许直接存中文，存储中文就报错，必须编码后才能存中文 	编码
		//c2会覆盖掉cookie '北京'取代'code'
		Cookie c2=new Cookie("city", URLEncoder.encode("北京","utf-8"));
		response.addCookie(c2);
	}
	/**
	 * 模拟登录之后的主页 演示cookie
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void indexServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//想浏览器输入主页的内容(略)	获取浏览器传入的所有cookie
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			response.setContentType("text/html;charset=utf-8");
			//浏览器中查看所有cookie
			PrintWriter pw=response.getWriter();
			for(Cookie c:cookies){
				//解码	与编码字符集必须保持一致
				pw.println(c.getName()+":"+URLDecoder.decode(c.getValue(), "utf-8")+"<br>");
			}
			pw.close();
		}
	}
	/**
	 * 模拟资费查询 演示cookie的有效范围   修改cookie的有效路径：112行代码
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findCostServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw=response.getWriter();
			for(Cookie c:cookies){
				pw.println(c.getName()+":"+c.getValue());
			}
			pw.close();
		}
	}
}
