<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="topdiv">
	<ul>
		<li><a href="/eshop">商城首页</a><span>|</span></li>
		<c:if test="${user==null || user.usertype==1}">
		<li><a href="/eshop/customer/shopCart">购物车</a><span>|</span></li>
		<li><a href="/eshop/customer/order">我的订单</a><span>|</span></li>
		</c:if>
		<c:if test="${user!=null && user.usertype==2}">
		<li><a href="/eshop/merchant/manage">商家中心</a><span>|</span></li>
		</c:if>
		<li><a href="/eshop/state">获取帮助</a></li>
	</ul>
	<div class="rightdiv">
		<c:if test="${user!=null}">
			<ul>
				<li><a href="/eshop/login?switch=true">切换账号</a></li>
				<li><a href="/eshop/logout">注销</a><span>|</span></li>
				<li><a>欢迎您：${user.username}</a><span>|</span></li>

			</ul>
		</c:if>
		<c:if test="${user==null}">
			<ul>
				<li><a href="/eshop/login">登录</a></li>
				<li><a href="/eshop/register">注册</a><span>|</span></li>
			</ul>
		</c:if>
	</div>
</div>
