<%@page pageEncoding="utf-8"%>
<!-- 1.先写HTML -->
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>第一个jsp</title>
</head>
<body>
	<!-- 2.3JSP声明（声明方法）-->
	<%!public int pf(int n) {
		return n * n;
	}%>
	<!-- 2.1JSP脚本（完整的代码段） -->
	<ul>
		<%
			for (int i = 0; i < 10; i++) {
		%>
		<!-- 2.2JSP表达式（输出变量） -->
		<li><%=pf(i)%></li>
		<%
			}
		%>
	</ul>
	<!-- 引入date.jsp，相当于将date.jsp中的内容复制到此处。 -->
	<%@include file="date.jsp"%>
</body>
</html>