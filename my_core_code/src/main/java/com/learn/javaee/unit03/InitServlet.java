package com.learn.javaee.unit03;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 * 这个Servlet不是用来处理具体请求的，是专门用来初始化数据的。
 * 配合FindEmpBySizeServlet和FindDeptServlet做流量统计。
 *
 * 工作时你会发现，一般的项目里都有一个或多个这样的类。
 *
 * @author Double
 *
 */
public class InitServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = 2741457727454504135L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		//tomcat启动时会优先创建context，然后创建Servlet
		//ServletContext scx=this.getServletContext();
		ServletContext scx=getServletContext();

		//String size=scx.getInitParameter("size");
		//System.out.println(size);

		//流量默认为0
		scx.setAttribute("count",0);

	}
}
