<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- 演示post -->
<html>
<head>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	function check_uname() {
		//step1.获得ajax对象
		var xhr = getXhr();
		//step2.发送请求   post方式在send中传输参数。
		xhr.open('post', 'check_uname.do', true);
		//可以在此设置请求标头
		xhr.setRequestHeader('content-type',
				'application/x-www-form-urlencoded11111');
		xhr.onreadystatechange = function() {
			//step4.处理服务器返回的数据
			if (xhr.readyState == 4 && xhr.status == 200) {
				var txt = xhr.responseText;
				$('username_msg').innerHTML = txt;
			}
		};
		xhr.send('username=' + $F('username'));
	}
</script>
</head>
<body style="font-size: 30px;">
	<form action="regist.do" method="post">
		用户名:<input id="username" name="username" onblur="check_uname();" /> <span
			id="username_msg"></span> <br /> 密码:<input type="password"
			name="pwd" /><br /> <input type="submit" value="注册" />
	</form>
</body>
</html>