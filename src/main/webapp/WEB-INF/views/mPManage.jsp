<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>商家管理中心</title>
<link href="../css/header.css" rel="stylesheet" type="text/css">
<style type="text/css">
#list>li {
	border: solid 1px #000;
	list-style: none;
	margin: 10px;
	margin-right: 350px;
	margin-left: 300px;
	height: 100px;
	display: flex;
}

#list>li>div {
	height: 100%;
}

#list>li div img {
	height: 200px;
	width: 200px;
}

.img {
	width: 200px;
}

.title {
	width: 50%;
	flex: 3;
	margin-top: 10px;
	margin-left: 20px;
	margin-right: 20px;
}

.title a {
	font-size: 20px;
}

.rightInfo {
	width: auto;
	flex: 1;
	float: left;
}

.rightInfo-t {
	height: 75%;
	width: 100%;
	text-align: center;
	display: table;
}

.rightInfo-t a {
	display: table-cell;
	vertical-align: middle;
	font-size: 22px;
}

.rightInfo-b {
	height: 25%;
	width: 100%;
}

.page {
	width: 17px;
	height: 20px;
	line-height: 20px;
	border: 1px solid blue;
	color: blue;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}

.pagehover {
	width: 17px;
	height: 20px;
	line-height: 20px;
	border: 1px solid blue;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	background: #30F;
	color: #FFF;
}

.page:hover {
	background: #30F;
	color: #FFF;
}

.pagebtn {
	width: 64px;
}

#div1 {
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="jsp/header.jsp"%>
	<br>
	<h1 style="margin-left: 300px;">商家中心-已上架商品</h1>
	<input type="submit" value="新增商品" style="width: 120px; height: 40px; font-size: 18px;margin-left: 1000px;" onclick="location.href='addMyProduct'" >
	<ul id="list">
		<c:forEach var="product" items="${products}">
		<li>
			<img src="../img/${product.img1}" style="height: 100px;width: 100px;">
			<a style="flex: 2;" href="../detail?id=${product.id}" target="_blank">${product.pname}</a>
			<div style="flex: 1;">
			<a>当前库存：${product.stock }</a><br>
			<a>价格：￥<fmt:formatNumber type="number" value="${product.price*0.01}" maxFractionDigits="2"	minFractionDigits="2" /></a><br>
			<br>
			<a href="deleteMyProduct?id=${product.id}">删除该商品</a>
			</div>
		</li>
		</c:forEach>
	</ul>
</body>
</html>