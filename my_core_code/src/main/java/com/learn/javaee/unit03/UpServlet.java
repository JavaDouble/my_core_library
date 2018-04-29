package com.learn.javaee.unit03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Unit03 案例8
 * 涨薪水演示Servlet线程安全问题(模拟)
 *
 * @author Double
 *
 */
public class UpServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	double salary=3000.0;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized (this) {
			//模拟涨工资
			salary += 100;
			try {
				//模拟模拟网络延迟
				Thread.sleep(8000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//输出响应信息
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw=response.getWriter();
			pw.println(salary);
			pw.close();
		}
	}
}
