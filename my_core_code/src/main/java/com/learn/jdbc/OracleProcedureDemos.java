package com.learn.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import org.junit.Test;


import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;

/**
 * 测试Oracle中存储过程的演示
 *
 * @author Double
 *
 */
public class OracleProcedureDemos {

	/**
	 * 测试使用标准SQL DQL语句查询一条数据，消耗时间：326毫秒
	 */
	@Test
	public void testSQL() {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			//起始时间
			long start=System.currentTimeMillis();

			String sql="select ename,sal,job"+" from emp_lqs where empno=?";
			conn=OracleUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 7839);
			ps.executeQuery();

			//结束时间
			long end=System.currentTimeMillis();
			System.out.println("标准SQL查询，耗时："+(end-start)+"毫秒！");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {

		}

	}
	/**
	 * 测试使用存储过程查询一条数据，消耗时间：311毫秒 存储过程效率更高
	 *
	 * create or replace procedure queryEmpInformation(eno in number,
	                                                pename out varchar2,
	                                                psal   out number,
	                                                pjob   out varchar2)
	 */
		@Test
		public void testProcedure(){
			//{call <procedure-name>[(<arg1>,<arg2>, ...)]}
			String sql = "{call queryEmpInformation(?,?,?,?)}";

			Connection conn = null;
			CallableStatement call = null;
			try {
				//起始时间
				long start=System.currentTimeMillis();

				conn = OracleUtils.getConnection();
				call = conn.prepareCall(sql);

				//对于in参数，赋值
				call.setInt(1,7839);

				//对于out参数，申明	OracleTypes需要导入ojdbc6的jar
				call.registerOutParameter(2, OracleTypes.VARCHAR);
				call.registerOutParameter(3, OracleTypes.NUMBER);
				call.registerOutParameter(4, OracleTypes.VARCHAR);

				/*
				//第二种方式
				call.registerOutParameter(2, Types.CHAR);
				call.registerOutParameter(3, Types.DOUBLE);
				call.registerOutParameter(4, Types.CHAR);
				*/

				//执行
				call.execute();

				//结束时间
				long end=System.currentTimeMillis();
				System.out.println("存储过程，耗时："+(end-start)+"毫秒！");

				//输出
				String name = call.getString(2);
				double sal = call.getDouble(3);
				String job = call.getString(4);
				System.out.println(name+"\t"+sal+"\t"+job);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				OracleUtils.release(conn, call, null);
			}
		}

	/**
	 * 测试存储函数
	 * create or replace function queryEmpIncome(eno in number) return number
	 */
	@Test
	public void testFunction() {
		// {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
		String sql = "{?=call queryEmpIncome(?)}";

		Connection conn = null;
		CallableStatement call = null;
		try {
			//起始时间
			long start=System.currentTimeMillis();

			conn = OracleUtils.getConnection();
			call = conn.prepareCall(sql);

			call.registerOutParameter(1, OracleTypes.NUMBER);
			call.setInt(2, 7839);

			// 执行
			call.execute();

			// 取出年收入
			double income = call.getDouble(1);

			System.out.println(income);
			//结束时间
			long end=System.currentTimeMillis();
			System.out.println("存储过程，耗时："+(end-start)+"毫秒！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleUtils.release(conn, call, null);
		}
	}

	/**
	 * 很多个out参数	out游标	测试调用out游标
	 */
	@Test
	public void testCursor() {
		String sql = "{call mypackage.QUERYEMPLIST(?,?)}";

		Connection conn = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try {
			conn = OracleUtils.getConnection();
			call = conn.prepareCall(sql);

			// 对于in参数，赋值
			call.setInt(1, 20);

			// 对于out参数，申明
			call.registerOutParameter(2, OracleTypes.CURSOR);

			// 执行
			call.execute();

			// 取出结果
			rs = ((OracleCallableStatement) call).getCursor(2);
			while (rs.next()) {
				// 取出一个员工
				String name = rs.getString("ename");
				double sal = rs.getDouble("sal");
				System.out.println(name + "\t" + sal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleUtils.release(conn, call, rs);//光标已被关闭
		}

	}
}
