<%@ page language="java"  import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>国际化(i18n)测试</title>
  </head>
  <body>
    <%
      //加载资源文件，request.getLocale()获取访问用户所在的国家地区
      ResourceBundle res = ResourceBundle.getBundle("i18nTest.login",request.getLocale());
	    String str=request.getParameter("language");
	    if(str!=null){
		     if("zh".equals(str)){
		    	 res=ResourceBundle.getBundle("i18nTest.login",Locale.CHINA);
		     }else if("en".equals(str)){
		    	 res=ResourceBundle.getBundle("i18nTest.login",Locale.US);
		     }
	    }
  	%>
        <form action="" method="post">
          	<a href="i18n.jsp?language=zh" rel="external nofollow" >简体中文</a>|<a href="i18n.jsp?language=en" rel="external nofollow" >English</a>
            <p><%=res.getString("username")%>：<input type="text" name="username"/></p>
            <p><%=res.getString("password")%>：&nbsp;&nbsp;&nbsp;<input type="password" name="password"/></p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="<%=res.getString("submit")%>"></p>
        </form>
  </body>
</html>