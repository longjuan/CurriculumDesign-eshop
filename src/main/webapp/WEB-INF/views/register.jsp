<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/loginAndRegister.css" rel="stylesheet" type="text/css" />
<title>用户注册</title>
</head>
<body>
	<div class="loginAndRegister_box">
		<div class="loginAndRegister_l_img">
			<img src="img/loginAndRegister-img.png" />
		</div>
		<div class="loginAndRegister">
			<div class="loginAndRegister_logo">
				<a><img src="img/reg_logo.png" /></a>
			</div>
			<div class="loginAndRegister_name">
				<p>&nbsp;&nbsp;用户注册&nbsp;&nbsp;${message}</p>
			</div>
			<form method="post" action="register">
				<a style="height: 1px; color: #fff; cursor: text; font-size: 1px;">&nbsp;</a>
				<span id="username_text"
					onclick="this.style.display='none';document.getElementById('username').style.display='block';document.getElementById('username').focus().select();">用户名</span>
				<input name="username" type="text" id="username"
					style="display: none;"
					onblur="if(this.value==''){document.getElementById('username_text').style.display='block';this.style.display='none'};">
				<span id="password_text"
					onclick="this.style.display='none';document.getElementById('password').style.display='block';document.getElementById('password').focus().select();">密码</span>
				<input name="password" type="password" id="password"
					style="display: none;"
					onblur="if(this.value==''){document.getElementById('password_text').style.display='block';this.style.display='none'};" />
				<a style="color: black; cursor: text; font-size: 16px">注册身份：</a> <input
					type="radio" name="usertype" value="1" checked><a
					style="color: black; cursor: text; font-size: 16px">买家</a>&nbsp;&nbsp;
				<input type="radio" name="usertype" value="2"><a
					style="color: black; cursor: text; font-size: 16px">卖家</a> <br>
				<br> <input value="注册" style="width: 100%;" type="submit">
			</form>
			<br> <a href="login"
				style="display: block; text-align: right; font-size: 16px">前往登录</a>
		</div>
	</div>
</body>
</html>