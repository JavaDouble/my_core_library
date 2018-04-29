package com.learn.javaee.unit03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.javaee.unit03.dao.EmpDao;
import com.learn.javaee.unit03.dao.EmpDaoImpl;
import com.learn.javaee.unit03.entity.Emp;




/**
 * Unit03 	案列2 	增加员工 	模拟		引出重定向
 *
 * @author Double
 *
 */
public class AddEmp extends HttpServlet{


    /**
	 *
	 */
	private static final long serialVersionUID = -6485509374890559505L;

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.接受参数
    	request.setCharacterEncoding("utf-8");
    	String username=request.getParameter("username");
    	String job=request.getParameter("job");
    	Double sal=Double.parseDouble(request.getParameter("sal"));

    	System.out.println("姓名："+username+",工作："+job+",薪水："+sal);

    	//2.处理业务
    	EmpDao dao=new EmpDaoImpl();
    	Emp emp=new Emp();
    	emp.setEmpno(4);
    	emp.setEname(username);
    	emp.setJob(job);
    	emp.setSal(sal);

    	dao.save(emp);

    	//3.返回响应
/*    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter pw=response.getWriter();
    	pw.println("<p>增加员工成功!</p>");
    	pw.close();*/

    	//重定向到查询功能 理解：建议客户自己区访问查询功能
    	//当前：/EmpManager/add	 目标：/EmpManager/findEmp
    	response.sendRedirect("findEmp");
    }
}
