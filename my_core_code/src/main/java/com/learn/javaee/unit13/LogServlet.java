package com.learn.javaee.unit13;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;



/**
 * 演示Log4j和Log4j2的使用
 * @author Double
 *
 */
public class LogServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		System.out.println(path);
		if("/jcl.log".equals(path)){
			jclServlet(request, response);
		}else if("/slf4j.log".equals(path)){
			slf4jServlet(request, response);
		}else if("/log4j2JCL.log".equals(path)){
			log4j2JCLServlet(request, response);
		}else if("/log4j2Slf4j.log".equals(path)){
			log4j2Slf4jServlet(request, response);
		}else {
			throw new RuntimeException("查无此页！");
		}
	}

	/**
	 * 模拟演示JCL+log4j的日志
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void jclServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//实现的时commoms.logging规范
		Log log = LogFactory.getLog(LogServlet.class);
		try {
			log.info("进入Servlet");
			int i=1/0;
			log.info("进入Servlet结束");
		} catch (Exception e) {
			log.error("计算异常",e);
		}
	}

	/**
	 * 模拟演示JCL+log4j的日志
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void slf4jServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//实现的时slf4j规范
		org.slf4j.Logger logger = LoggerFactory.getLogger(LogServlet.class);

		try {
			logger.info("进入Servlet");
			int i=Integer.valueOf("a");
			logger.info("进入Servlet结束");
		} catch (Exception e) {
			logger.error("格式转换异常",e);
		}
	}

	private void log4j2JCLServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private void log4j2Slf4jServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
