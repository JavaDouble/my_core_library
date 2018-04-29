package com.learn.javaee.unit01;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 整合Unit01 案例1-5
 * 分别是：HelloServlet、TimeServlet、ResInfoServlet、DateServlet和RandomServlet
 * 使用后缀.do 重构今天代码
 *
 * @author Double
 */
public class Unit01Servlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = 4117063571133769126L;

	/**
	 * Servlet重写有三种方式：
	 * 第一种方式：重写HttpServlet类中public void service()方法
	 * 第二种方式：重写HttpServlet类中的portected void service()方法
	 * 第三种方式：重写HttpServlet类中的doGet/doPost/doXXX方法，此种方法有局限性，只能处理一种方式的请求。
	 *
	 * 推荐使用第二种方式：因为第三种方式有局限性，第一种方式要进行强制类型转换，并且第一种方式也调用了第二种方式的方法。
	 * 第二种方式可以处理所有请求。
	 */
	/*
	 * 匹配路径
	 *
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取Servlet路径
		String path=request.getServletPath();

		if("/date.do".equals(path)){
			date(request,response);
		}else if("/random.do".equals(path)){
			random(request, response);
		}else if("/resinfo.do".equals(path)){
			resinfo(request, response);
		}else if("/time.do".equals(path)){
			time(request, response);
		}else if ("/requestInfo.do".equals(path)) {
			requestInfo(request, response);
		}else{
			throw new RuntimeException("查无此页");
		}
	}

/*	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理浏览器get方式的请求，父类中没有具体逻辑，抛出异常强制开发者重写 处理逻辑
		//一般不再使用，HttpServlet已经帮我们在service()方法处理了，直接重写service()方式就可以了
	}
	//测试 doGet和doPost方法的执行时间和顺序
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理浏览器post方式的请求，父类中没有具体逻辑，抛出异常强制开发者重写 处理逻辑
		//一般不再使用，HttpServlet已经帮我们在service()方法处理了，直接重写service()方式就可以了
	}*/

	/**
	 * 重构 Unit01 案例1 HelloServlet代码
	 */
	protected void hello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	/**
	 * 重构 Unit01 案例2 TimeServlet代码
	 */
	protected void time(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.通过request接受请求数据(1.1:请求行,1.2:消息头,1.3:实体内容)
		//1.1:请求行
		System.out.println("请求方式:"+request.getMethod());//GET
		System.out.println("请求资源路径:"+request.getRequestURI());// /servlet/time
		System.out.println("请求的路径信息:"+request.getRequestURL());//http://localhost:8080/servlet/time
		System.out.println("请求的Servlet路径:"+request.getServletPath());// /time
		System.out.println("协议类型:"+request.getProtocol());//HTTP/1.1
		//1.2：消息头 按照key-value的方式存储的
		//此方法返回key的迭代器 Enumeration是更古老的迭代器
		Enumeration<String> e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String key=e.nextElement();
			String value=request.getHeader(key);
			System.out.println(key+":"+value);
			//host:localhost:8080
			//user-agent:Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.26+ (KHTML, like Gecko) Version/5.0 Safari/534.26+
			//accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
			//cache-control:max-age=0
			//accept-encoding:gzip
		}
		//1.3:实体内容
		//本案列浏览器没有给服务器传输业务数据，所以实体内容为空，后面按列在演示。

		//2.通过response发送响应数据(2.1:状态行,2.2:消息头,2.3:实体内容)
		//2.1:状态行 由服务器自动填写
		//创建服务器时间 发送内容
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		String now=sdf.format(date);
		////2.2:消息头
		//设置响应内容类型 /告诉浏览器给它发送的是什么格式的内容
		response.setContentType("text/html");
		//response.setCharacterEncoding("utf-8");
		//2.3:实体内容 获取输出流
		PrintWriter pw=response.getWriter();
		//写出 这里偷懒了，没有拼完整网页
		pw.println("<p>"+now+"</p>");
		//关闭
		pw.close();
	}
	/**
	 * 重构 Unit01 案例2增加代码 TimeServlet代码
	 */
	protected void requestInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("HttpServletRequest接口的实现类："+request.getClass().toString());
		//class org.apache.catalina.connector.RequestFacade
		System.out.println("HttpServletResponse接口的实现类："+response.getClass().toString());
		//class org.apache.catalina.connector.ResponseFacade
		System.out.println("-------------------分割线----------------------");

		//1.通过request接受浏览器的请求数据(1.1:请求行,1.2:消息头,1.3:实体内容)
		//1.1:请求行
		System.out.println("请求方式(返回值String类型):"+request.getMethod());//GET
		System.out.println("请求资源路径(返回值String类型):"+request.getRequestURI());
		// '/servlet/time'
		System.out.println("请求的路径信息(返回值StringBuffer类型):"+request.getRequestURL());
		//'http://localhost:8080/servlet/time' 这个是全部地址
		System.out.println("请求的Servlet路径(返回值String类型):"+request.getServletPath());
		// '/time'：web.xml中<servlet-mapping>下的</url-pattern>的路径
		System.out.println("协议类型(返回值String类型):"+request.getProtocol());//HTTP/1.1

		System.out.println("-------------------分割线----------------------");

		System.out.println("Authorization头："+request.getAuthType());//null
		System.out.println("编码方式："+request.getCharacterEncoding());//null
		System.out.println("内容长度："+request.getContentLength());//-1
		System.out.println("上下文地址："+request.getContextPath());//'/my_core_code' 项目名称

		System.out.println("-------------------本地信息----------------------");
		System.out.println("本地地址："+request.getLocalAddr());//0:0:0:0:0:0:0:1
		System.out.println("本地名称："+request.getLocalName());//0:0:0:0:0:0:0:1
		System.out.println("本地端口："+request.getLocalPort());//8080
		System.out.println("Locale："+request.getLocale());//zh_CN

		System.out.println("-------------------远程信息----------------------");
		System.out.println("远程地址(返回值String类型)："+request.getRemoteAddr());//0:0:0:0:0:0:0:1
		System.out.println("远程主机(返回值String类型)："+request.getRemoteHost());//0:0:0:0:0:0:0:1
		System.out.println("远程端口(返回值int类型)："+request.getRemotePort());//52245
		System.out.println("远程用户(返回值String类型)："+request.getRemoteUser());//null

		System.out.println("-------------------服务器----------------------");
		System.out.println("DispatcherType："+request.getDispatcherType());//REQUEST
		System.out.println("计划："+request.getScheme());//http
		System.out.println("服务器信息:"+this.getServletInfo());//控字符串
		System.out.println("服务器信息:"+getServletConfig().getServletContext().getServerInfo());
		//Apache Tomcat/7.0.82
		System.out.println("服务器名称："+request.getServerName());//localhost
		System.out.println("服务器端口："+request.getServerPort());//8080
		System.out.println("ServletContext："+request.getServletContext());

		System.out.println("-------------------Cookie和Session----------------------");
		System.out.println("Cookies[]："+request.getCookies());
		System.out.println("HttpSession："+request.getSession());
		System.out.println("SessionId："+request.getRequestedSessionId());//4506525A25FB4DDDD9876E482384BD0C
		System.out.println("HttpSession："+request.getUserPrincipal());

		System.out.println("-------------------分割线----------------------");
		System.out.println("地址信息："+request.getPathInfo());//null
		System.out.println("翻译后的地址："+request.getPathTranslated());//null
		System.out.println("查询数据："+request.getQueryString());//null

		//System.out.println("AsyncContext："+request.getAsyncContext());//java.lang.IllegalStateException
		System.out.println("AttributeNames："+request.getAttributeNames());

		System.out.println("-------------------分割线----------------------");

		System.out.println("消息头，String："+request.getHeaderNames());
		System.out.println("ServletInputStream："+request.getInputStream());

		System.out.println("Enumeration<Locale>："+request.getLocales());
		System.out.println("Map<String, String[]>："+request.getParameterMap());
		System.out.println("Enumeration<String>："+request.getParameterNames());
		System.out.println("Collection<Part>："+request.getParts());
		//System.out.println("BufferedReader："+request.getReader());//java.lang.IllegalStateException
		//getInputStream() has already been called for this request

		System.out.println("DIGEST："+request.DIGEST_AUTH);//DIGEST

		System.out.println("-------------------分割线----------------------");

		//1.2：消息头 按照key-value的方式存储的
		//此方法返回key的迭代器 Enumeration是更古老的迭代器
		Enumeration<String> e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String key=e.nextElement();
			String value=request.getHeader(key);
			System.out.println(key+":"+value);
			//host:localhost:8080
			//user-agent:Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.26+ (KHTML, like Gecko) Version/5.0 Safari/534.26+
			//accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
			//cache-control:max-age=0
			//accept-encoding:gzip
		}
		//1.3:实体内容
		//本案列浏览器没有给服务器传输业务数据，所以实体内容为空，后面按列在演示。

		//2.通过response发送给浏览器的响应数据(2.1:状态行,2.2:消息头,2.3:实体内容)
		//2.1:状态行 由服务器自动填写
		//创建服务器时间 发送内容
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		String now=sdf.format(date);
		////2.2:消息头
		//设置响应内容类型 /告诉浏览器给它发送的是什么格式的内容
		response.setContentType("text/html");
		//response.setCharacterEncoding("utf-8");
		//2.3:实体内容 获取输出流
		PrintWriter pw=response.getWriter();
		//写出 这里偷懒了，没有拼完整网页
		pw.println("<p>"+now+"</p>");
		//关闭
		pw.close();
	}

	/**
	 * 重构   Unit01 案例3 DateServlet代码
	 *
	 */
	protected void date(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String now=sdf.format(date);
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println(now);
		pw.close();
	}

	/**
	 * 重构 Unit01 案例4 RandomServlet代码
	 *
	 */
	protected void random(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random random=new Random();
		int num=random.nextInt(100);
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println(num);
		pw.close();
	}

	/**
	 * 重构   Unit01 案例5 ResInfoServlet代码
	 *
	 */
	protected void resinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//请求行
		System.out.println("请求方式:"+request.getMethod());

		System.out.println("请求的路径信息:"+request.getRequestURL());
		System.out.println("请求的资源路径:"+request.getRequestURI());
		System.out.println("请求的Servlet路径:"+request.getServletPath());

		System.out.println("请求的协议种类:"+request.getProtocol());
		//消息头
		Enumeration<String> e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String key=e.nextElement();
			String value=request.getHeader(key);
			System.out.println(key+""+value);
		}
		//实体内容

		//状态行
		//消息头
		response.setContentType("text/html");
		//实体内容
		PrintWriter pw=response.getWriter();
		pw.println("<p>HelloServlet!</p>");
		pw.close();
	}


}
