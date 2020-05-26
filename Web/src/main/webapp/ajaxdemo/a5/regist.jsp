<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<!-- 演示get -->
<html>
<head>
    <title>Insert title here</title>
</head>

<body style="font-size: 30px;">
<form method="get">
    用户名:<input id="username" name="username" onblur="check_uname();"/>
    <span id="username_msg"></span> <br/>
    密码<input type="password" name="pwd"/><br/>
    <input type="submit" value="注册"/>
</form>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
    function check_uname() {
        //1.初始化XMLHttpRequest对象
        var xhr = getXhr();
        //2.绑定一个事件处理函数
        //	xhr.onreadystatechange=processResponse;
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var txt = xhr.responseText;
                alert($('username').value);
                $('username_msg').innerHTML = txt;
            }
        }
        /*
         *3.建立到服务器的链接
           第一个参数：method ： 请求类型 get  post
               二   	 ： url:路径字符串，指向你所请求的服务器上的那个文件
              三 	： asynch 表示请求是否要异步传输，默认值为true（异步)
         */
        var uri = 'check_uname.do?username=' + $F('username');
        alert(uri);
        xhr.open('get', encodeURI(uri), true);
        //4.发送请求，send方法可为已经待命的请求发送指令
        //若选用的是get 请求，则不会发送任何数据，给send 方法传递null即可。
        xhr.send(null);
    }
</script>
</body>
</html>