<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>文件上传页面</title>
	</head>
	<script type="text/javascript">
    	alert("${info}");
	</script>
	<body>
		<!-- 上传文件是上传到服务器上，而保存到数据库是文件名 -->
		<!-- 上传文件是以文件转换为二进制流的形式上传的 提交方式必须是post -->
		<!-- enctype="multipart/form-data"需要设置在form里面，否则无法提交文件 -->
		<!-- 第一种方式 Servlet+JSP 使用流的方式上传
		<form action="upload.d" method="post" enctype="multipart/form-data">
		    <table>
		        <tr>
		            <td></td>
		            <td><h1>文件上传</h1></td>
		        </tr>
		        <tr>
		            <td>文件描述:</td>
		            <td><input type="text" name="desc"/></td>
		        </tr>
		        <tr>
		            <td>上传文件:</td>
		            <td><input type="file" name="file"/></td>
		        </tr>
		        <tr>
		            <td></td>
		            <td><input type="submit" value="上传文件"/></td>
		        </tr>
		    </table>
		</form>
		-->
		<!--  -->
		<form action="upload2.d" enctype="multipart/form-data" method="post">
       	 	<p>上传用户：<input type="text" name="username"></p>
        	<p>上传文件1：<input type="file" name="file1"></p>
        	<p>上传文件2：<input type="file" name="file2"></p>
        	<p><input type="submit" value="上传"></p>
    	</form>
	</body>
</html>