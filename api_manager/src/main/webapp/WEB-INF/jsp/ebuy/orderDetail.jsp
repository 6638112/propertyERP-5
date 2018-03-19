<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理-订单详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../themes/metro/easyui.css?v20150306">
<link rel="stylesheet" type="text/css" href="../themes/icon.css">
</head>

<body>
<div class="info">
	<h2>订单详情</h2>
	<div class="bs-example bgebeb">
	<!-- <form class="inputform"> -->
        <h3>商品属性</h3>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        <%--<div style="width:800px;height:1px;margin:0px auto;padding:0px;background-color:#D5D5D5;overflow:hidden;"></div> --%>
        <table class="mtop10" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td style="padding-left:50px;" width="50%">
            	<table border="0" cellpadding="0" cellspacing="0">
		          <tr><td>供应商：</td><td>${orderDetail.supplyName }</td></tr>
		        </table>
            </td>
            <td style="padding-left:50px;" width="50%" valign="top">
            	<table border="0" cellpadding="0" cellspacing="0">
		          <tr><td>联系方式：</td><td>${orderDetail.supplyPhone}<c:if test="${empty orderDetail.supplyPhone}">&nbsp;</c:if></td></tr>
		        </table>
            </td>
          </tr>
        </table>
        
        <table class="info-list mtop20" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td><h3>订单信息</h3></td>
            <td><h3>配送信息</h3></td>
          </tr>
        </table>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        
        <table class="mtop20" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td style="padding-left:50px;" width="50%">
            	<table border="0" cellpadding="0" cellspacing="0">
		          <tr><td>订单号：</td><td>${orderDetail.orderNo}</td></tr>
		          <tr><td>下单时间：</td><td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderDetail.buyTime }"/></td></tr>
		          <tr><td>支付时间：</td><td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderDetail.payTime }"/></td></tr>
		          <c:if test="${!isSupply }">
		          	<tr><td>支付方式：</td><td>${orderDetail.payMethodStr}</td></tr>
		          	<tr><td>支付流水：</td><td>${ebuyPayRecord.flowNo}</td></tr>
		          </c:if>
		          <tr><td>商品总额：</td><td>${orderDetail.amount/100}</td></tr>
		          <tr><td>+运费：</td><td>${orderDetail.deliveryFee/100}</td></tr>
		          <tr class="red"><td>运单总额：</td><td>${(orderDetail.amount + orderDetail.deliveryFee)/100}</td></tr>
		          
		          <c:if test="${!isSupply }">
					  <tr><td>-优惠：</td><td>${orderDetail.deliveryOrderCoupon div 100}</td></tr>
					  <tr><td>实付金额：</td><td>${orderDetail.deliveryOrderAmount div 100}</td></tr>
		          </c:if>
		          <tr>
					  <td>运单状态：</td>
					  <td>
						  <c:choose>
							  <c:when test="${orderDetail.status == 3 && orderDetail.deliveryStatus <= 1 && orderDetail.refundStatus != 1 && orderDetail.refundStatus != 2}">待发货</c:when>
							  <c:when test="${orderDetail.refundStatus == 2 }">已退款</c:when>
							  <c:when test="${orderDetail.refundStatus == 1}">退款中</c:when>
							  <c:when test="${orderDetail.deliveryStatus == 2 }">待收货</c:when>
							  <c:when test="${orderDetail.deliveryStatus == 3}">交易完成</c:when>
							  <c:otherwise>已取消</c:otherwise>
						  </c:choose>
					  </td>
				  </tr>
		          <tr>
		          	<td>退款状态：</td>
		          	<c:choose>
		          	  <c:when test="${orderDetail.refundStatus == 2 }"><td><a href="../refundOrder/refundDetail.html?refundOrderId=${orderDetail.refundOrderId}"><div class="blue">有退款</div></a></td></c:when>
		          	  <c:otherwise><td>无退款</td></c:otherwise>
		          	</c:choose>
		          </tr>
					<tr>
						<td>身份证：</td><td>${orderDetail.identifyNo }</td>
					</tr>
		        </table>
            </td>
            <td style="padding-left:50px;" width="50%" valign="top">
            	<table border="0" cellpadding="0" cellspacing="0">
		          <tr><td>收货人：</td><td>${orderDetail.delivPeopleName}<c:if test="${empty orderDetail.delivPeopleName}">&nbsp;</c:if></td></tr>
		          <tr><td>联系电话：</td><td>${orderDetail.delivPhone}</td></tr>
		          <tr>
					  <td>配送地址：</td>
					  <td>
						  <c:if test="${orderDetail.userDeliveryType == 2}">自提</c:if>
						  <c:if test="${orderDetail.userDeliveryType != 2}">${orderDetail.delivAddressArea} ${orderDetail.delivAddressDetail}</c:if>
					  </td>
				  </tr>
		        </table>
            </td>
          </tr>
        </table>
        
        <h3 class="mtop20">商品清单</h3>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        <display:table class="info-list-02 mtop20" name="orderDetail.productList" id="row" >
        	<display:column title="分类" property="productType"></display:column>
        	<display:column title="商品名称">
        	<div class="blue">
        	  <c:choose><c:when test="${empty row.productName2}">${row.productName}</c:when><c:otherwise>${row.productName2}</c:otherwise></c:choose>
        	</div>
        	</display:column>
        	<display:column title="商品单价">${row.price == null ? "" : (row.price/100)}</display:column>
        	<display:column title="购买数量" property="qty"></display:column>
        	<display:column title="尺寸" property="size"></display:column>
        	<display:column title="颜色" property="colour"></display:column>
        	<display:column title="活动"><div class="red">${row.opName }</div></display:column>
        	<display:column title=""><div class="bold">${row.forDisplay}</div></display:column>
        	<display:column title="小计">
        		<c:choose>
        			<c:when test="${empty row.forDisplay}">${row.totalPrice/100}</c:when>
        			<c:otherwise><div class="red">${row.totalPrice/100}</div></c:otherwise>
        		</c:choose>
        	</display:column>
        </display:table>
        
        <h3 class="mtop20">物流跟踪</h3>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        <table class="mtop20" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td style="padding-left:50px;" class="bold">物流名称：${orderDetail.expressName}<c:if test="${empty orderDetail.expressName}">-</c:if></td>
          </tr>
           <tr>
            <td style="padding-left:50px;" class="bold">物流单号：${orderDetail.expressNo }<c:if test="${empty orderDetail.expressNo}">-</c:if></td>
          </tr>
          <tr>
			  <c:if test="${!isSelfExpress}">
				  <td style="padding-left:50px;" class="bold"><c:if test="${content != null}">
					  <iframe style="margin-left:40px;" name="kuaidi100"
							  src="${content}" width="550"
							  height="350" marginwidth="0" marginheight="0" hspace="0" vspace="0"
							  frameborder="0" scrolling="no">
					  </iframe>
				  </c:if>
					  <c:if test="${content == null}">暂无物流信息</c:if>
				  </td>
			  </c:if>
			  <c:if test="${isSelfExpress}">
				  <td style="padding-left:50px;" class="bold">
					  <c:if test="${empty content}">暂无物流信息</c:if>
					  <c:forEach var="trace" items="${content}">
						  <p>${trace.time} : ${trace.context}</p>
					  </c:forEach>
				  </td>
			  </c:if>
          </tr>
        </table>
        
        <!-- order comment begin by Liyl-->
        <h3 class="mtop20">订单备注</h3>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        <table class="mtop20" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td style="padding-left:50px;" class="bold">
            	<textarea id="comment" class="textareas txt02" maxlength=50 placeholder="订单备注内容" rows="5"></textarea>
            </td>
          </tr>
           <tr>
              <td style="padding-left:50px;padding-top:5px;">
                  <input type="button" id="orderNoteSaveBtn" class="input-btn" onclick="saveOrderComment()" value="保存"/>
              </td>
          </tr>
          <tr>
	          <td style="padding-left:50px;padding-top:20px;">
	          	<div class="comment_list">
		          	<c:forEach items="${orderComments}" var="orderComment">
					    <p style="font-family:'Applied Font Regular', 'Applied Font';text-indent: 0em;">
					        <span style="padding-right: 3em;">${orderComment.addTime}</span>
					        <span>${orderComment.comment}</span>
					    </p>
					</c:forEach>
	          	</div>
	          </td>
          </tr>
        </table>
        <!-- order comment end  by Liyl-->
        <div class="padb mtop20"><input id="sumShopList" class="info-btn" type="button" value="返 回" onclick="history.back();"/></div>
    <!-- </form> -->
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" >

	// 保存运单备注
	function saveOrderComment(){
		var comment = $.trim($("#comment").val());
		if(comment.length==0){
			$.messager.alert('提示','订单备注内容不能为空！', 'Warning');
		} else {
			$.post("../order/saveOrderComment.html", {"orderId":"${orderId}", "comment":comment}, function(data){
				$.messager.alert('操作提示', data.message, 'Warning');
				if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
					$("#comment").val("");
					var commentHtml = "";
					commentHtml += "<p style=\"font-family:'Applied Font Regular', 'Applied Font';text-indent: 0em;\">";
					commentHtml += "    <span style=\"padding-right: 3em;\">"+data.dataValue+"</span>";
					commentHtml += "    <span>"+comment+"</span>";
					commentHtml += "</p>";
					if($(".comment_list p").length==0){
						$(".comment_list").html(commentHtml);
					} else {
						$(".comment_list p:first").before(commentHtml);
					}
				};
			});
		}
	}
</script>
</html>
