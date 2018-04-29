<%@page pageEncoding="utf-8" import="java.util.*,
day04.entity.*,day04.dao.*"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>查询员工</title>
		<style>
			table{
				border:1px solid #ccc;
				width:200px;
				border-collapse:collapse;
			}
			td{
				border:1px solid #eee;
			}
		</style>
	</head>
	<body>
		<table>
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>职位</td>
				<td>薪资</td>
			</tr>	
			<%
				EmpDao dao=new EmpDaoImpl();
				List<Emp> list=dao.findAll();
				for(Emp emp:list){
			%>
				<tr>
					<td><%=emp.getEmpno() %></td>
					<td><%=emp.getEname() %></td>
					<td><%=emp.getJob() %></td>
					<td><%=emp.getSal() %></td>
				</tr>
			<%
				}
			%>	
		</table>
	</body>
</html>
