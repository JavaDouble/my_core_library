<%--指令:page指令 pageEncoding:声明此jsp文件的编码。
contentType:告诉浏览器输出的内容的格式。
import 导包 --%>
<%@page pageEncoding="utf-8" contentType="text/html" 
import="java.util.*,java.text.*" %>
<%
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年M月dd日 E a h时mm分ss秒");
	String now=sdf.format(date);
%>
<div>
	<%=now%>
</div>
