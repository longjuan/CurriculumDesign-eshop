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
	<h1 style="margin-left: 200px;">我的订单</h1>
	<ul id="list">
		<c:forEach var="order" items="${orders}">
			<li>
			<div style="width: 100%;"><a>订单编号：${order.orderid}</a>&emsp;<a>下单日期：${order.date}</a></div>
			<div style="width: 100%;">
				<ul style="list-style: none;margin-top: 10px;">
				<c:forEach var="orderproduct" items="${order.products}">
					<li style="height: 100px;margin: 3px;display: flex;">
						<img src="../img/${orderproduct.img}" style="height: 100px;width: 100px;">
						<a style="flex: 2;">${orderproduct.pname}</a>
						<div style="flex: 1;">
						<a>数量：${orderproduct.quantity }</a><br>
						<a>价格：￥<fmt:formatNumber type="number" value="${orderproduct.price*0.01}" maxFractionDigits="2"	minFractionDigits="2" /></a><br>
						<a>小计：￥<fmt:formatNumber type="number" value="${orderproduct.price*0.01*orderproduct.quantity}" maxFractionDigits="2"	minFractionDigits="2" /></a>
						</div>
					</li>
				</c:forEach>
				</ul>
			</div>
			<div style="text-align: right;">
				<p>总价：￥<fmt:formatNumber type="number" value="${order.tprice*0.01}" maxFractionDigits="2"	minFractionDigits="2" /></p>
			</div>
			</li>
		</c:forEach>
	</ul>

</body>
</html>