<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="myTag" uri="http://eshop.com/RedStarTag/"%>

<!DOCTYPE html>
<html>
<head>
<title>商品详情-${product.pname}</title>
<meta charset="utf-8" />
<link href="../css/header.css" rel="stylesheet" type="text/css">
<script src="../jquery/jquery.min.js"></script>
<script type="text/javascript">
function up(btn) {
	btn.disabled = 'disabled';
	$.ajax({
	    url: "addMyProduct",
	    type: "POST",
	    data: new FormData($("#saveFileForm")[0]),
	    processData: false,
	    contentType: false,
	    success: function (result) {
	    	if(result['code']!=200){
	        	alert(result['message']);
	        	btn.removeAttribute("disabled");
	    	}else{
	    		window.location.href="manage";
	    	}
	    },
	    error: function (e) {
	    	alert("请填写完全部项目");
	    	btn.removeAttribute("disabled");
	       }
	});
}
</script>
</head>
<body>
	<%@ include file="jsp/header.jsp"%>
	<br>
	<h1 style="margin-left: 300px;">新增商品</h1>
	<div style="width: 510px;margin: 0 auto;border: 1px #000 solid;">
		<form role="form" id="saveFileForm" enctype="multipart/form-data"><table>
			<tr><td></td><td>带<myTag:out></myTag:out>为必填项</td></tr>
			<tr><td>商品标题：</td><td><input type="text" name="pname" style="width: 400px;"></td><td><myTag:out></myTag:out></td></tr>
			<tr><td>选择图片1:</td><td><input type="file" name="img"></td><td><myTag:out></myTag:out></td></tr>
			<tr><td>选择图片2:</td><td><input type="file" name="img"></td><td><myTag:out></myTag:out></td></tr>
			<tr><td>选择图片3:</td><td><input type="file" name="img"></td><td><myTag:out></myTag:out></td></tr>
			<tr><td>商品价格：</td><td><input type="number" value="99999.99" min="0" step="0.01" style="width: 80px;" name="pricedouble"></td><td><myTag:out></myTag:out></td></tr>
			<tr><td>商品数量：</td><td><input type="number" value="1" min="1" step="1" style="width: 50px;" name="stock"></td><td><myTag:out></myTag:out></td></tr>
			<tr><td>商品参数：</td><td><textarea style="width: 400px; height: 250px" name="parameter"></textarea></td><td><myTag:out></myTag:out></td></tr>
			<tr><td></td><td><input type="button" value="确认新增商品" style="font-size: 16px;" onclick="up(this)"></td></tr>
		</table></form>
	</div>

</body>
</html>