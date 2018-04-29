package com.learn.javaee.unit08;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 过滤日志 演示过滤器的拦截功能
 * @author Double
 *
 */
public class LogFilter implements Filter {


	/**
	 * tomcat启动时会自动实例化Filter时，然后自动调用它的init()方法来初始化Filter。
	 * 调用此方法时会传入config，该对象和Filter时1对1的关系，可以给Filter预置参数(web.xml).
	 * 该对象和ServletConfig用法完全一样
	 * tomcat中只能初始化一次 单例模式
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("初始化LogFilter");
	}

	/**
	 * 该方法是处理公共业务的方法
	 * Filter相当于是Servlet的管家，tomcat在调用Servlet之前会将请求提交给Filter，Filter
	 * 有权让请求继续，也有权让请求终止。
	 * tomcat就是调用doFilter方法让Filter统一处理请求的，调用前它会创建好request和reponse并传入，
	 * 创建的类型：RequestFacade和ResponseFacade
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("记日志-前");
		//让请求继续执行，向下执行 如果不写，后面请求终止，不再继续
		chain.doFilter(request, response);
		System.out.println("记日志-后");
	}

	/**
	 * 正常关闭tomcat时，自动调用此方法。tomcat在销毁Filter时，自动调用它的destroy()方法。
	 * 服务器(tomcat)停止时，LoginFilter销毁只执行一次
	 */
	@Override
	public void destroy() {
		System.out.println("销毁LoginFilter");
	}
}
