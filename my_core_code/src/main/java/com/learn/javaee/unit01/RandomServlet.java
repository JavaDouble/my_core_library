package com.learn.javaee.unit01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Unit01 案例4
 *
 * @author Double
 *
 */
public class RandomServlet extends HttpServlet {



	/**
	 *
	 */
	private static final long serialVersionUID = -2071080323171301780L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random random=new Random();
		int num=random.nextInt(100);
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println(num);
		pw.close();
	}
}
