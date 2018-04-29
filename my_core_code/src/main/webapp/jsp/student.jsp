<%@page pageEncoding="utf-8" %>
<!--uri:JSP标签的命名空间	prefix:命名空间的前缀  引入所需的JSTL标签  -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/lhh-tags" prefix="s" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>查询学生(EL表达式、JSTL标签)</title>
	</head>
	<body>
		<h1>JSTL</h1>
		<!--1.if  -->
		<%--
		语法:<c:if test="" var="" scope=""></c:if> 当test属性值为true
		时,执行标签体的内容。test属性可以使用EL表达式赋值 var属性：指定一个绑定名称
		scope属性：指定绑定的范围(pageContext,request,session,application)
		注：var和scope要配合使用
		--%>
		<p>
			<c:if test="${student.gender=='M' }">男</c:if>
			<c:if test="${student.gender!='M' }">女</c:if>
		</p>


		<!--2.choose  -->
		<%--
		语法：<c:choose><c:when test=""></c:when>...
		<c:otherwise></c:otherwise></c:choose
		when表示一个处理分支,当test属性为true时会执行该分支,可以出现1次或者多次
		otherwise表示例外，可以出现0次或1次
		--%>
		<p>
			<c:choose>
				<c:when test="${student.gender=='M' }">男</c:when>
				<c:otherwise>女</c:otherwise>
			</c:choose>
		</p>


		<!--3.forEach  -->
			<%--
			用来遍历集合或者数组 语法：<c:forEach var="" items=""></c:forEach
			items属性：指定要遍历的集合，一般使用EL表达式来复制 var属性：指定一个绑定名称
			，容器每次从集合中取一个对象,然后绑定到pageContext对象上 varStatus属性：
			指定一个绑定名称，绑定值是一个由容器创建的对象，该对象封装了当前迭代的状态
			index返回正在被迭代的对象的下标,下标从0开始 count返回是第几次迭代，从1开始
			 --%>
		<p>
			<c:forEach var="i" items="${student.interests }">
				${i }
			</c:forEach>
		</p>

		<!--4.自定义标签  -->

		<!--步骤：1.编写Java类，继承SimpleTagSupport类
		2.在重写父类的doTag方法中添加处理逻辑 3.配置标签说明文件
		运行原理：容器依据JSP标签的命名空间找到标签的描述文件(.tld文件)，然后依据
		标签名字找到标签类，接下来将该标签实例化。容器会依据标签的属性给标签实例的属性
		赋值，然后调用标签实例的doTag方法 其中.tld文件 存放在WEB-INF目录下
		和web.xml平级 编写的类放在Servlet同级目录下  -->

		<p>
			日期：<s:sysdate format="yyyy年MM月dd日"></s:sysdate><br>
			时间：<s:sysdate format="HH:mm:ss"></s:sysdate>
		</p>



		<h1>EL表达式</h1>
		<!-- EL表达式的作用：1.获取Bean的属性 2.输出简单的运算结果  3.获取请求参数 -->

		<!-- 1.获取Bean的属性 -->
		<!--方式一：${对象名.属性名 }  -->
		<!-- 等效于 request.getAttribute("student").getName() -->

		<p>EL表达式的作用：1.获取Bean的属性 2.输出简单的运算结果 3.获取请求参数</p>
		<p>1.获取Bean的属性</p>
		<p>方式一：\${对象名.属性名 }</p>
		<p>姓名:${student.sname }</p>
		<p>性别:${student.gender }</p>
		<p>年龄:${student.age }</p>
		<p>兴趣:${student.interests[0] }</p><!--不能遍历集合，数组 需JSTL-->
		<p>课程:${student.course.cname }</p>
		<!-- request.getAttribute("course").getId() -->
		<p>课程ID:${student.course.id }</p>


		<!-- EL默认从下面4个对象中依次取值，这些对象都是隐含对象，他们是EL
		的取值范围：pageContext->request->session->application
		也可以明确指定取值的范围：sessionScope.student.sname
		requestScope.student.sname 举例如下：-->
		<p>范围:${sessionScope.student.sname }</p><!-- null -->
		<p>范围:${requestScope.student.sname }</p><!-- zhangsan -->

		<!--总结：1.这样设计的目的是为了让程序员写EL表达式方便
		,因为默认不必写范围了。
			2.可以明确指定范围，但通常不这样做，因为我们向这些对象中存数据时，
		完全可以避免重名的情况  -->


		<!--方式二:${对象名["属性名"] }  -->
		<p>方式二：\${对象名["属性名"] }</p>
		<%--相当与<%request.setAttribute("popname","sname");%>${student[popname]}  --%>
		<%--request.getAttribute("sname").getSname()  --%>

		<p>姓名：${student["sname"] }</p>
		<p>性别：${student['gender'] }</p>

		<!--2.输出简单的运算结果(获取数据后可以直接做运算)  -->
		<!--算术运算:"+","-","*","/","%" "+"号只能球和,不能连接字符串-->
		<!--逻辑运算:"&&","||","!"  -->
		<!--关系运算:">",">=","<","<=","==","!=",-->
		<!--empty:用来判断一个字符串是否为空,或者一个集合是否为空,一下四种
		情况结果为true:空字符串,空的集合,值为null,找不到对应的值  -->

		<p>2.输出简单的运算结果(获取数据后可以直接做运算)</p>
		<p>年龄+5:${student.age+5 }</p><!--23+5 28  -->
		<p>${"100"+"200" }</p><!--300  -->
		<p>${str=="abc" }</p><!--false  -->
		<p>${str==abc }</p><!--true  -->
		<p>${str==0 }</p><!--false  -->
		<p>20到30间:${student.age>=20 && student.age<=30 }</p><!--true-->
		<p>判断空值运算:${empty student }</p><!--false  -->


		<!-- 3.使用EL表达式获取请求参数 -->
		<!--相当于request.getParameter("user")  -->
		<p>3.使用EL表达式获取请求参数</p>
		<p>获取请求参数:${param.student }</p>
	</body>
</html>