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
 * Unit01 案例3
 *
 * @author Double
 *
 */
public class DateServlet extends HttpServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = -1579754922788562756L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String now=sdf.format(date);
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println(now);
		pw.close();
	}
}
