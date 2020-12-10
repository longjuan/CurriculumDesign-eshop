<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>商品详情-${product.pname}</title>
<meta charset="utf-8" />
<link href="css/header.css" rel="stylesheet" type="text/css">
<style>
.wrap {
	width: 500px;
	min-height: 500px;
	margin: 0 auto;
	position: relative;
	overflow: hidden;
}

.carousel {
	position: absolute;
	transition: all 0.5s ease-in 0s;
}

img {
	width: 100%;
}

.carousel div {
	float: left;
	text-align: center;
}

.button {
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="jsp/header.jsp"%>
	<br>
	<div style="width: 45%; margin-top: 50px; float: left;">
		<div class="wrap">
			<div class="carousel">
				<div>
					<img src="img/${product.img1}" />
				</div>
				<div>
					<img src="img/${product.img2}" />
				</div>
				<div>
					<img src="img/${product.img3}" />
				</div>
			</div>
		</div>
		<div class="button">
			<button class="pre">上一张</button>
			<button class="next">下一张</button>
		</div>
	</div>
	<div style="width: 55%; height: 100%; margin-top: 50px; float: left;">
		<div style="margin-right: 60px; margin-top: -20px;">
			<div>
				<p style="font-size: 24px; background: #FFE7BA;">${product.pname}</p>
			</div>
			<table style="border-spacing: 20px 0px;">
				<tr>
					<td>
						<p>价格</p>
					</td>
					<td><span></span></td>
					<td>
						<p style="font-size: 30px; color: red; margin: 0;">
							<b>￥<fmt:formatNumber type="number"
									value="${product.price*0.01}" maxFractionDigits="2"
									minFractionDigits="2" /></b>
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<p>库存</p>
					</td>
					<td><span></span></td>
					<td>
						<p style="font-size: 22px; margin: 0;">${product.stock}件</p>
					</td>
				</tr>
				<tr>
					<td>
						<p style="margin: 0;">卖家</p>
					</td>
					<td><span></span></td>
					<td>
						<p style="font-size: 22px; margin: 0;">${product.saleusername}</p>
					</td>
				</tr>
			</table>
			<div>
				<div style="width: 45%; float: left;">
					<p style="background: #FFE7BA;">${product.parameter}</p>
				</div>
				<div
					style="width: 45%; float: left; margin-top: 20px; margin-left: 20px;">
					<form style="margin-top: 50px; text-align: center;" action="customer/addCartProduct" method="Post">
					<input type="hidden" name="id" value="${product.id}">
						数量： <input type="number" value="1" min="1" step="1" style="width: 40px;" name="quantity"> 件 <br> <br>
						<input type="submit" value="加入购物车"
							style="width: 150px; height: 50px; font-size: 20px; color: #fff; background-color: red; border: 2px red none;">
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		(function() {
			var carousel = document.querySelector(".carousel");
			var img = carousel.querySelectorAll("div");
			var len = img.length;
			[].forEach.call(img, function(item) {
				item.style.width = (100 / len) + "%";
			});
			carousel.style.left = "0%";
			carousel.style.width = (100 * len) + "%";
			var pre = document.querySelector(".pre");
			var next = document.querySelector(".next");
			var i = 0;
			next.onclick = function() {
				var left = carousel.style.left;
				if (i < len - 1) {
					carousel.style.left = (parseInt(left) - 100) + "%";
					i++;
				} else if (i == len - 1) {
					carousel.style.left = "0%";
					i = 0;
				}
			};
			pre.onclick = function() {
				var left = carousel.style.left;
				if (i > 0) {
					carousel.style.left = (parseInt(left) + 100) + "%";
					i--;
				} else if (i == 0) {
					carousel.style.left = -parseInt(carousel.style.width) + 100
							+ "%";
					i = len - 1;
				}
			};
		}());
		
	</script>
</body>

</html>