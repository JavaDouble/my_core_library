package com.learn.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;


import java.sql.Types;


/**
 * 测试Oracle中存储过程的演示
 *
 * @author Double
 *
 */
public class MySQLProcedureDemos {

	/**
	 * 测试使用标准SQL DQL语句查询一条数据，消耗时间：395毫秒
	 */
	@Test
	public void testSQL() {
		try {
			//起始时间
			long start=System.currentTimeMillis();

			String sql="select count(empno)"+" from emp_lqs where deptno=?";
			Connection conn=DBUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, 20);
			ps.executeQuery();

			//结束时间
			long end=System.currentTimeMillis();
			System.out.println("标准SQL查询，耗时："+(end-start)+"毫秒！");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConnection();
		}

	}
	/**
	 * 测试使用存储过程查询一条数据，消耗时间：443毫秒 存储过程效率更高
	 *
	 * create procedure pro_findById(in dno int,out number int)
	 */
		@Test
		public void testProcedure(){
			//{call <procedure-name>[(<arg1>,<arg2>, ...)]}
			String sql = "{call pro_findById(?,?)}";

			try {
				//起始时间
				long start=System.currentTimeMillis();

				Connection conn = DBUtil.getConnection();
				System.out.println(sql);
				CallableStatement  call = conn.prepareCall(sql);

				//对于in参数，赋值
				call.setInt(1,20);

				//对于out参数，申明
				call.registerOutParameter(2, Types.INTEGER);

				//执行
				call.execute();

				//结束时间
				long end=System.currentTimeMillis();
				System.out.println("存储过程，耗时："+(end-start)+"毫秒！");

				//输出
				int num=call.getInt(2);
				System.out.println("20号部门有"+num+"人！");

			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeConnection();
			}
		}

	/**
	 * 测试存储函数
	 * create function queryEmpIncome(eno in number) return number
	 */
	@Test
	public void testFunction() {
		// {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
		String sql = "{?=call name_from_emp_lqs(?)}";

		try {
			//起始时间
			long start=System.currentTimeMillis();

			Connection conn = DBUtil.getConnection();
			CallableStatement call = conn.prepareCall(sql);

			call.setInt(1, 20);

			// 执行
			call.execute();

			// 取出年收入
			int income = call.getInt(1);

			System.out.println(income);
			//结束时间
			long end=System.currentTimeMillis();
			System.out.println("存储过程，耗时："+(end-start)+"毫秒！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
	}


}
