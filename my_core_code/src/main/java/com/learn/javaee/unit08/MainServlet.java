package com.learn.javaee.unit08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 演示过滤器filter
 * @author Double
 *
 */
public class MainServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if("/addCost.cost".equals(path)){
			addCost(request, response);
		}else if("/deleteCost.cost".equals(path)){
			deleteCost(request, response);
		}else if("/logout.cost".equals(path)){
			logout(request, response);
		}else{
			throw new RuntimeException("查无此页");
		}
	}

	/**
	 * 模拟增加资费
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("增加资费");
		request.setAttribute("user", "zhangsan");
		request.removeAttribute("user");
		request.setAttribute("user", "lisi");
	}

	/**
	 * 模拟删除资费
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("删除资费");
	}

	/**
	 * 监听器-使用session统计在线人数 业务逻辑
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		HttpSession session=request.getSession();
		session.invalidate();
		pw.close();
	}
}
