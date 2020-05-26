<%@page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<html>
	<head>
		<script type="text/javascript" src="js/ajax.js">
		</script>
		<script type="text/javascript">
			function showNumber(){
				// var xhr = getXhr();
				// xhr.open('get','getNumber.do?random=' + Math.random()*10,true);
				// xhr.onreadystatechange=function(){
				// 	if(xhr.readyState == 4 &&
				// 			xhr.status == 200){
				// 		var txt = xhr.responseText;
				// 		$('d1').innerHTML = txt;
				// 	}
				// };
				// xhr.send(null);
                var rndnumb="";
                for (i=1;i<=32;i++){
                    n = Math.floor(Math.random()*10);
                    rndnumb= rndnumb + n;
                }
                $('d1').innerHTML = rndnumb;
			}
		</script>
	</head>
	<body style="font-size:30px;">
		<a href="javascript:showNumber();">点击，显示一个随机数</a><br/>
		<div id="d1"></div>
	</body>
</html>
