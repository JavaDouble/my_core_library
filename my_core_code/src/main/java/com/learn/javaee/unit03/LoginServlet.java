package com.learn.javaee.unit03;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Unit03 案例5 模拟一个网页游戏的登录功能
 * ServletConfig使用的演示
 *
 * @author Double
 *
 */
public class LoginServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 6616856275541887153L;

	//tomcat创建并初始化Servlet的过程:LoginServlet s=new LoginServlet();
	//ServletConfig c=new ServletConfig();

	//config中有静态块，首次访问时就执行了，静态块中加载了web.xml的参数 c.静态块{}
	//加载了web.xml的参数
	//s.init(c);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//必须与web.xml中相关<servlet>标签下<init-param>中<param-name>的名称相同
		String max=config.getInitParameter("maxOnline");
		System.out.println("init()中的max:"+max);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//获取的就是init()传入的那个config
		ServletConfig sc=getServletConfig();

		//必须与web.xml中相关<servlet>标签下<init-param>中<param-name>的名称相同
		String max=sc.getInitParameter("maxOnline");
		System.out.println("service()中的max："+max);

		System.out.println("正在登录------");

		System.out.println("根据参数max判断是否达到最大人数");

	}
}
