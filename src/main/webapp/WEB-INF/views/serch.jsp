<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>搜索结果-${q}</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<style type="text/css">
#list>li {
	border: solid 1px #000;
	list-style: none;
	margin: 30px;
	margin-right: 160px;
	margin-left: 120px;
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
	<div style="text-align: center;">
		<form action="serch" method="get" id="from">
			<input type="text" name="q" style="height: 30px; width: 30%;" value="${q}" id="context" />
			<input type="hidden" name="page" id="pageinput" value="${page}">
			<input type="button" value="搜索" style="height: 35px; width: 5%;" onclick="tosubmit()"/>
		</form>
	</div>
	<ul id="list">
		<c:forEach var="product" items="${products}">
			<li>
			<div class="img"><img src="img/${product.img1}"></div>
			<div class="title"><a href="detail?id=${product.id}" target="_blank">${product.pname}</a></div>
			<div class="rightInfo">
				<div class="rightInfo-t"><a>￥<fmt:formatNumber type="number" value="${product.price*0.01}" maxFractionDigits="2" minFractionDigits="2"/></a></div>
				<div class="rightInfo-b">
					<a>卖家：${product.saleusername}</a><br>
					<a>库存：${product.stock}</a>
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
	<div id="div1">
		<a href="javascript:;" onclick="last();" class="pagebtn" id="last">上一页</a> 
		<a href="javascript:;" onclick="page(this);" class="page">1</a> 
		<a href="javascript:;" onclick="page(this);" class="page">2</a> 
		<a href="javascript:;" onclick="page(this);" class="page">3</a> 
		<a href="javascript:;" onclick="page(this);" class="page">4</a> 
		<a href="javascript:;" onclick="page(this);" class="page">5</a> 
		<a href="javascript:;" onclick="next();" class="pagebtn" id="next">下一页</a>
	</div>
</body>
<script type="text/javascript">
	var num = ${num};
	var pagenum = parseInt(num/8) + 1;
	var startpage = ${page} - 1;
	var pageitems = document.getElementsByClassName("page");
	var end = pagenum - ${page};
	if(startpage>0){
		var i = 0
		for (; i < (end+2); i++) {
			pageitems[i].innerText = startpage;
			startpage += 1;
		}
		for (; i < 5; i++) {
			pageitems[i].style.display="none";
		}
		pageitems[1].className = 'pagehover';
	}else{
		for (var i = pagenum ; i < 5; i++) {
			pageitems[i].style.display="none";
		}
		pageitems[0].className = 'pagehover';
	}
	
	if(pagenum == ${page}){
		document.getElementById('next').style.display="none";
	}
	if(1 == ${page }){
		document.getElementById('last').style.display="none";
	}
	
	function page(page) {
			document.getElementById('pageinput').value = page.innerText;
			document.getElementById('from').submit();
	}
	function last() {
		document.getElementById('pageinput').value = parseInt(document.getElementById('pageinput').value)-1;
		document.getElementById('from').submit();
	}
	function next() {
		document.getElementById('pageinput').value = parseInt(document.getElementById('pageinput').value)+1;
		document.getElementById('from').submit();
	}
	function tosubmit() {
		document.getElementById('pageinput').value = 1;
		document.getElementById('from').submit();
	}
</script>
</html>