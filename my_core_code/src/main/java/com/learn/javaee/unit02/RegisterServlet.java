package com.learn.javaee.unit02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 常见面试题：GET和POST的区别：
 * 请求方式：常用GET和POST
 * GET:1)通过路径传参(消息头) 2)参数在传递过程中可见，隐私性差 3)可以传少量数据，所有的请求默认都是GET请求 不安全
 * POST:1)采用实体内容传参(实体内容)2)参数在传递过程中不可见，隐私型强
 * 3)传递的数据大小不受限制 当form上增加了method="post"
 * 查询时用get,一般查询条件较少。	 保存，提交时用post，此时数据较多。
 *
 * Unit02 案例1：注册
 *
 * @author Double
 *
 */
public class RegisterServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = -5904993557806698321L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 处理浏览器请求的一般步骤是：
		 * 1.接受浏览器发送过来的数据
		 * 2.利用这些数据处理业务
		 * 3.给浏览器返回响应信息
		 */
		/*
		 * 编码方式和解码方式不一致是所有产生乱码的根本原因
		 * Servlet中的乱码产生原因：浏览器的编码方式与服务器的编码方式不一致
		 * 浏览器的编码方式是：ISO-8859-1	服务器的编码方式是：utf-8
		 *
		 * Servlet中的乱码解决方案
		 * 方案一：
		 * 方案二：
		 * 方案三：
		 */

		//只能解决post产生的乱码(方案3)
		request.setCharacterEncoding("utf-8");
		//1.接受浏览器发送过来的数据 	不论获取的什么数据，在服务器中都是String类型处理，如果有需要，自己转型

		/*
		 * 获得请求参数值
		 * 对于表单提交的数据，Servlet可以从容器构建的request对象中获取，如下两个方法可以在不同情况下获取表单数据：
		 * String getParameter（String paramName）
		 * request.getParameter(参数) 参数名称必须与页面name属性的值保持一致
		 *
		 * String[] getParameterValues（String paramName）
		 * 用于获取表单中一组参数名相同的控件提交的数据组，如复选框，所以该方法返回的是字符串数组。
		 * 如果用户没有选择这一组同名控件的任何一个，则该方法返回null。
		 */
		String username=request.getParameter("username");

		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String[] arr=request.getParameterValues("checkbox");

		//处理请求乱码(方案1)
		//username = new String(username.getBytes("iso-8859-1"),"UTF-8");

		//2.利用这些数据处理业务
		System.out.println("帐号:"+username);
		System.out.println("密码:"+password);
		System.out.println("性别:"+gender);
		if(arr!=null){
			for(String str:arr){
				System.out.println("兴趣:"+str);
			}
		}

		//3.给浏览器返回响应信息
		//处理响应乱码
		//response.setCharacterEncoding("utf-8");
		//可省略 省略则编码下面编码方式相同(utf-8) 服务器响应编码设置
		response.setContentType("text/html;charset=utf-8");//浏览器编码设置
		PrintWriter pw=response.getWriter();
		pw.println("<p>注册成功!</p>");
		pw.close();
	}
}
