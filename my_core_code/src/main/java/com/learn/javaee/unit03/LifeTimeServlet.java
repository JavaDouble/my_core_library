package com.learn.javaee.unit03;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * Unit03 案例4 演示servlet的生命周期
 *
 * 默认情况下：tomcat启动，浏览器访问时：
 * 1.tomcat实例化LifeTimeServlet(只有1次)
 * 2.tomcat初始化LifeTimeServlet对象的Init()方法(初始化方法只有1次)
 * 3.tocmat调用LifeTimeServlet对象，处理请求(可以多次调用)
 * 4.tocmat销毁LifeTimeServlet对象(tomat服务器正常关闭时，销魂Servlet对象，1次)
 *
 * 修改启动tomcat时加载LifeTimeServlet:
 * 方法时在web.xml中<servlet>标签内部增加<load-on-startup>1</load-on-startup>
 * tomcat启动时加载此Servlet，中间的数字是加载的顺序(优先级) 1是最高优先级  只能是正整数
 *
 * 1.tomcat实例化LifeTimeServlet(tomcat启动时，就实例化了)
 * 2.tomcat初始化LifeTimeServlet对象的Init()方法(tomcat启动时，调用了Servlet对象的init()方法)
 * 3.tocmat调用LifeTimeServlet对象，处理请求(可以多次调用)
 * 4.tocmat销毁LifeTimeServlet对象(tomat服务器正常关闭时，销魂Servlet对象，1次)
 *
 * @author Double
 *
 */
public class LifeTimeServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 9750267528343511L;

	public LifeTimeServlet() {
		System.out.println("tomcat实例化LifeTimeServlet");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("tocmat调用LifeTimeServlet对象，处理请求");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("tomcat初始化LifeTimeServlet对象的Init()方法");
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("tocmat销毁LifeTimeServlet对象");
	}
}
