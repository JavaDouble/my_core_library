<%--JSP指令 语法规则: --%>
<%@page pageEncoding="utf-8"%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>First JSP</title>
	</head>
	<body>
		<!--3.jsp声明-->
		<%!
			public int pf(int d){
			return d*d;
		}
		%>
		<ul>
			<%--1.jsp脚本 (JSP特有的注释)--%>
			<%
			for(int i=0;i<10;i++){
			%>
				<!-- 2.jsp表达式 (HTML注释)-->
				<li><%=pf((int)(Math.random()*10)) %>
			<%	
			}
			%>
		</ul>
		<!--将time.jsp引入到此处，相当于将它内部的代码复制到此处。  -->
		<%@include file="time.jsp" %>

	</body>
</html>