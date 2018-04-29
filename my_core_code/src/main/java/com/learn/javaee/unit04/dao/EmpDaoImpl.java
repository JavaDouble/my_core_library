package com.learn.javaee.unit04.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.learn.javaee.unit04.entity.Emp;




/**
 *
 * Unit04内容：演示JSP基本语法：hello.jsp,time.jsp
 * 重构之前员工查询和增加的JSP；find_emp.jsp			位置：webapp/下
 *
 * 第二遍练习：helo.jsp time.jsp find_emp.jsp  	位置：webapp/jsp/下
 *
 * 1. Model 1
	使用一个组件处理请求、处理业务、显示数据，它在HTML和Java代码高度耦合在一起。
	Servlet/JSP+DAO+DB

	使用这种模式重构之前的员工查询和增加案例，体会这个模式优缺点。

 * @author Double
 *
 */
public class EmpDaoImpl implements EmpDao,Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public List<Emp> findAll() {
		List<Emp> list=new ArrayList<Emp>();
		Emp e1=new Emp();
		e1.setEmpno(1);
		e1.setEname("唐僧");
		e1.setJob("领导");
		e1.setSal(9000.0);
		list.add(e1);

		Emp e2=new Emp();
		e2.setEmpno(2);
		e2.setEname("孙悟空");
		e2.setJob("护卫");
		e2.setSal(7000.0);
		list.add(e2);

		Emp e3=new Emp();
		e3.setEmpno(3);
		e3.setEname("猪八戒");
		e3.setJob("保镖");
		e3.setSal(5000.0);
		list.add(e3);

		return list;
	}
	public void save(Emp e) {
		System.out.println("增加员工："+e.getEname());
	}
}
