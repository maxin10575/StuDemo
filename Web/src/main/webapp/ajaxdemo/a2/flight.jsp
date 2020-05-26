<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.4.3.js">
</script>
<script type="text/javascript">
	$(function(){
		//toggle，点击时，（）里面函数切换执行。
		$('a.s1').toggle(f1,f2);
	});
	function f1(){
		//先找到航班号
		var flight = $(this).parent().siblings().eq(0).text();
		/*
			load会向服务器发送异步请求,并且,将服务器返回
			的数据添加到div节点之上.
		*/
		$(this).next().load('getFlights.do','flight=' + flight);
	}
	function f2(){
		$(this).next().empty();
	}
</script>
</head>
<body style="font-size:30px;">
<table border="1" style="width:80%">
		<tr>		
			<td>航班号</td>
			<td>机型</td>
			<td>经济舱价格</td>
			<td>其他</td>
			<td>订票</td>
		</tr>
		<tr>		
			<td>CA1234</td>
			<td><a href="#">波音747</a></td>
			<td>1200</td>
			<td><a href="javascript:;" class="s1">查看其他价格</a>
				<div></div>
			</td>
			<td><input type="button" value="订票"></td>
		</tr>
		<tr>		
			<td>MU1494</td>
			<td><a href="#">空客320</a></td>
			<td>800</td>
			<td><a href="javascript:;" class="s1">查看其他价格</a>
				<div></div>
			</td>
			<td><input type="button" value="订票"></td>
		</tr>
	</table>
</body>
</html>