package com.learn.javaee.unit08;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤器演示过滤敏感词
 * @author soft01
 *
 */
public class AgilityFilter implements Filter {
	//此成员变量是为了获取init方法中的city(配置参数)
	private FilterConfig config;

	/**
	 * 演示获取web.xml的参数
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("初始化AgilityFilter");
		String city=filterConfig.getInitParameter("city");
		System.out.println("城市名称："+city);
		config=filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter中获取配置参数："+config.getInitParameter("city"));
		System.out.println("过滤敏感词-前");
		chain.doFilter(request, response);
		System.out.println("过滤敏感词-后");
	}

	public void destroy() {
		System.out.println("销毁AgilityFilter");
	}
}
