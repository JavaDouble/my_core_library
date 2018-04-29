package com.learn.javaee.unit03.entity;

import java.io.Serializable;
/**
 * 满足JavaBean规范：
 * 1.有package2.实现序列化接口3.有默认构造方法4.通常有get/set方法
 * PO(实体类) VO(视图实体类)
 *
 * 建议:1.尽量采用封装类型 2.日期采用java.sql下的日期
 * java.sql.Date 年月日 java.sql.Time 时分秒
 * java.sql.Timestamp 年月日时分秒 		他们都继承自java.util.Date
 *
 * @author Double
 *
 */
public class Emp implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 8423329068330177099L;


	private Integer empno;
	private String ename;
	private String job;
	private Double sal;
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}

}
