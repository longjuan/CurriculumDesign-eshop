<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>二手商城首页</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div>
		<%@ include file="jsp/header.jsp"%>
		<div class="midd">
			<ul>
				<li><img alt="" src="img/logo.gif" height="80px"><span></span><span></span></li>
				<li><a href="serch?q=促销">促销</a><span></span></li>
				<li><a href="serch?q=精品">精品</a><span></span></li>
				<li><a href="serch?q=最热">最热商品</a><span></span></li>
				<li><a href="serch?q=新品">最新上架</a></li>
			</ul>
			<div class="midright">
				<form action="serch" method="get">
					<input name="q" type="text" style="width: 350px; height: 30px;">
					<input type="submit" value="搜索" style="height: 35px;">
				</form>

			</div>
		</div>
	</div>
	<div class="main">
		<div class="leftmenu">
			<ul>
				<li><div>
						<a href="serch?q=苹果">苹果手机</a>
					</div></li>
				<li><div>
						<a href="serch?q=安卓">安卓手机</a>
					</div></li>
				<li><div>
						<a href="serch?q=平板">平板电脑</a>
					</div></li>
				<li><div>
						<a href="serch?q=配件">数码配件</a>
					</div></li>
				<li><div>
						<a href="serch?q=笔记本">笔记本电脑</a>
					</div></li>
				<li><div>
						<a href="serch?q=显示器">显示器</a>
					</div></li>
				<li><div>
						<a href="serch?q=外设">电脑外设</a>
					</div></li>
				<li><div>
						<a href="serch?q=家电">智能家电</a>
					</div></li>
			</ul>
		</div>
		<div class="img">
			<img style="height: 461px; margin-top: 16px;" src="img/timg.jpg">
		</div>
	</div>

</body>
</html>