<%@ page import="com.cnfantasia.server.common.CommConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../error/404.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<title>订单信息</title>
		<style>
			table tr{ vertical-align: top;}
			table tr td:first-child{ text-align: right;}
			.container{padding: 10px 5px 0 5px;}
			.container .code{text-align: center; margin-top: 50%;}
			.mleft10{ margin-left: 10px;}
			.grey{ color:#8e8e93 !important;}
			#code{width: 80%;}
			.order{padding: 10px;}
			.box-shadow{ -webkit-box-shadow: 0 0 6px rgba(161,163,171,0.4); box-shadow: 0 0 6px rgba(161,163,171,0.4);}
			.order p{margin: 0; padding: 0;}
			.info_div{margin-bottom: 20px; font-size: 14px;}
			.order_template{display: none; font-size: 14px;}
			input[type=text]{ 
				outline: none;
				border: solid 1px #ddd;
			    background-color: #fff;
			    padding: 4px;
			    height: 30px;
			    line-height: 30px;
			    font-size: 18px;
			    text-align: center;
			    font-weight: bold;
			    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
			    box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
			    -webkit-transition: all 0.2s linear 0s;
			    transition: all 0.2s linear 0s;}
			input[type=button]{
			    width: 80%;
			    margin: 10px auto;
			    padding: 0 10px;
			    display: block;
			    border: none;
			    text-align: center;
			    color: #fff;
			    border-radius: 4px;
			    height: 42px;
			    line-height: 42px;
			    font-size: 16px;}
		    .bgred { background: #da372a; }
		</style>
	</head>
	<body>
		<div class="container">
			<div class="code">
				<p>请输入提取码</p>
				<p>
					<input type="hidden" id="uuid" name="uuid" value="${uuid}"/>
					<input type="text" id="code" name="code" autofocus="autofocus"/>
				</p>
				<p>
					<input class="bgred" type="button" value="提交" onclick="queryCopyOrder()"/>
				</p>
			</div>
		</div>
		<div class="order_template">
			<div class="info_div">
				<div class="order box-shadow">
					<table border="0">
			        	<tr>
				            <td width="85"><span class="grey">序号：</span></td>
				            <td><span class="rankNo">1</span></td>
			        	</tr>
			        	<tr>
				            <td><span class="grey">订单号：</span></td>
				            <td><span class="orderNo"></span></td>
			        	</tr>
			        	<tr>
				            <td><span class="grey">下单时间：</span></td>
				            <td><span class="buyTime"></span></td>
			        	</tr>
			        	<tr>
				            <td><span class="grey">联系人手机：</span></td>
				            <td><span class="mobile"></span></td>
			        	</tr>
			        	<tr>
				            <td><span class="grey">联系人姓名：</span></td>
				            <td><span class="linkName"></span></td>
			        	</tr>
			        	<tr>
				            <td><span class="grey">收货地址：</span></td>
				            <td><span class="address"></span></td>
			        	</tr>
			        	<tr>
				            <td><span class="grey">订单信息：</span></td>
				            <td class="item-list"></td>
			        	</tr>
					<!-- <p><span class="grey">序号：</span><span class="rankNo">1</span></p>
					<p><span class="grey">订单号：</span><span class="orderNo">2017052316255187775</span></p>
					<p><span class="grey">下单时间：</span><span class="buyTime">2017-05-23 17：45</span></p>
					<p><span class="grey">联系人手机：</span><span class="mobile">13725590679</span></p>
					<p><span class="grey">联系人姓名：</span><span class="linkName">张三</span></p>
					<p><span class="grey">收货地址：</span><span class="address">广东省深圳市南山区湖北宝丰花园2-1-201</span></p>
					<p><span class="grey">订单信息：</span></p> -->
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		function queryCopyOrder(){
			$(".container .info_div").remove();
			
			var uuid = $.trim($("#uuid").val());
			var code = $.trim($("#code").val());
			if(code==""){
				alert("请输入提取码!");
				$("#code").focus();
			} else {
				$.post("${pageContext.request.contextPath}/queryCopyOrder.json", {"uuid":uuid, "code":code}, function(data){
					if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
						var orders = data.dataValue;
						var allOrderHtml = "";
						for(var i=0; i<orders.length; i++){
							var orderTemplate = $(".order_template").clone();
							var order = orders[i];
							orderTemplate.find(".rankNo").text(i + 1);
							orderTemplate.find(".orderNo").text(order.orderNo);
							orderTemplate.find(".buyTime").text(order.downTime);
							orderTemplate.find(".mobile").text(order.mobile);
							orderTemplate.find(".linkName").text(order.linkName);
							orderTemplate.find(".address").text(order.address);

							var orderInfoList = order.orderInfoList;
							if(orderInfoList){
								var orderInfoHtml = "";
								for(var k=0; k<orderInfoList.length; k++){
									var orderInfo = orderInfoList[k];
									orderInfoHtml += "<p class=\"detail\"><span class=\"grey\">商品：<\/span>" + orderInfo.productName + " <span class=\"grey mleft10\">数量：<\/span>" + orderInfo.num + "</p>";
								}
								orderTemplate.find(".item-list").append(orderInfoHtml); 
							}
							
							allOrderHtml += orderTemplate.html();
						}
						$(".container .code").hide();
						$(".container").html(allOrderHtml);
					} else {
						alert(data.message);
					}
				});
			}
		}
	</script>
</html>