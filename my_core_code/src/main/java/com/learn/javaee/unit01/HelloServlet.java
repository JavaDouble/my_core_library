package com.learn.javaee.unit01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Unit01 案例1
 *
 * @author Double
 *
 */
public class HelloServlet extends HttpServlet{


	/**
	 *
	 */
	private static final long serialVersionUID = -5600986664450675150L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建服务器时间
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String now="Hello Servelet,时间："+sdf.format(date);
		System.out.println(now);
		//告诉浏览器给它发送的是什么格式的内容
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		//这里偷懒了,没有拼完整网页
		pw.println(now);
		pw.close();
	}
}
