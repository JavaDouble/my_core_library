package com.learn.javaee.unit03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Unit03 案例3 访问路径和URL、URI的演示
 *
 * 演示访问路径
 * URI和URL的区别:(常见面试题)
 * 表面理解(*)
 * URL是完整路径 URI是绝对路径 URL包含了URI
 * 深入理解(*)
 * 将URI理解为资源的名称(苍老师/cang#null) 将URL理解为资源的真名(刘苍松)
 * URI包含了URL
 */
public class PathServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = -6434174527095396792L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取访问路径
		System.out.println(request.getContextPath());//获取的是项目名称:'/my_core_code'
		System.out.println(request.getServletPath());//获取的是Servlet路径：'/path'

		System.out.println(request.getRequestURL());
		//获取的是完整路径：http://localhost:8080/my_core_code/path
		System.out.println(request.getRequestURI());//获取的是'/my_core_code/path'

		//不设置响应信息，服务器依然会自动给浏览器发送响应，仅仅是实体内容为空而已。
	}
}
