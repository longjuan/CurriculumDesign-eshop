<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>购物车</title>
<link href="../css/header.css" rel="stylesheet" type="text/css">
<style type="text/css">
#list>li {
	border: solid 1px #000;
	list-style: none;
	margin: 20px;
	margin-right: 250px;
	margin-left: 200px;
	height: 200px;
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
	width: 40%;
	flex: 2;
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
	margin-top: 20px;
}

</style>
<script type="text/javascript">
function up(form) {
	xmlHttp = new XMLHttpRequest();
	xmlHttp.open('POST', "settlement", true);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) { 
			if(xmlHttp.status == 200){
				var json = JSON.parse(xmlHttp.responseText);
				alert(json['message']);
				if(json['callback']!=null){
					window.location.href=json['callback'];
				}
			}
		}
	};
	var data = new FormData(form);
	xmlHttp.send(data);
}
</script>
</head>
<body>
	<%@ include file="jsp/header.jsp"%>
	<br>
	<h1 style="margin-left: 200px;">购物车</h1>
	<ul id="list">
		<c:forEach var="cartProduct" items="${ShopCart}">
			<li>
			<div class="img"><img src="../img/${cartProduct.product.img1}"></div>
			<div class="title"><a href="../detail?id=${cartProduct.id}" target="_blank">${cartProduct.product.pname}</a></div>
			<div class="rightInfo">
					<a>数量：${cartProduct.quantity}</a><br>
					<a>单价：￥<fmt:formatNumber type="number"	value="${cartProduct.product.price*0.01}" maxFractionDigits="2"	minFractionDigits="2" /></a><br>
					<a>参考库存：${cartProduct.product.stock}</a><br>
					<br>
					<a style="color: red;font-size: 24px;">小计：￥<fmt:formatNumber type="number"	value="${cartProduct.product.price*0.01*cartProduct.quantity}" maxFractionDigits="2"	minFractionDigits="2" /></a><br>
					<c:if test="${cartProduct.product.stock<cartProduct.quantity}"><a style="color: red;">该商品库存数量可能少于结算数量</a></c:if>
					<br>
					<a href="delectCartProduct?id=${cartProduct.id}">删除</a>
			</div>
		</li>
		</c:forEach>
	</ul>
	<div style="text-align: center;">
	<form>
		<input type="hidden" value="${ShopCartVersion}" name="ShopCartVersion">
		<input type="button" value="结算商品"
							style="width: 150px; height: 50px; font-size: 20px; color: #fff; background-color: red; border: 2px red none;" onclick="up(this.form)">
	</form>
	</div>
</body>
</html>