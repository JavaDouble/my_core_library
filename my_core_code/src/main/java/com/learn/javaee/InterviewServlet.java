package com.learn.javaee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterviewServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if("/clientInfo.inter".equals(path)){
			getClientInfo(request, response);
		}else if("/clientIP.inter".equals(path)){
			clientIP(request, response);
		}else{
			throw new RuntimeException("查无此页");
		}
	}
	/**
	 * 面试题1. 编写一个servlet,要求：记录访问该servlet的客户端的ip和访问时间，写入
	 * 到client_if_log文件。client_if.log格式：9:28 2014-4-8
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getClientInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getRemoteAddr()获取客户端IP
		String ip=request.getRemoteAddr();
		//System.out.println(ip);
		//request.getLocalAddr()获取本地的IP
		//System.out.println("服务器IP："+request.getLocalAddr());
		
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm yyyy-MM-dd");
		String date=sdf.format(now);
		//System.out.println(date);
		File file=new File("client_ip.log");
		PrintWriter pw=new PrintWriter(new FileOutputStream(file,true));
		pw.println(date+" "+ip);	
		pw.close();
		response.sendRedirect("clientIP.inter");
	}
	protected void clientIP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ip=request.getRemoteAddr();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("你的IP:"+ip);
		pw.close();
	}
}
