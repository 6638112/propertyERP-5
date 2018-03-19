<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单管理-订单详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	    <table class="info-list mtop20" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td><h3>退款申请</h3></td>
          </tr>
        </table>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        <table class="mtop20" border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td style="padding-left:50px;" width="50%">
            	<table border="0" cellpadding="0" cellspacing="0">
		          <tr class="red"><td>申请时间：${refundOrder.createTime}</td></tr>
		          <tr>
			          <td>
			          		<c:choose>
								<c:when test="${refundOrder.status ==1}"><div class="red">退款方式：部分退款</div></c:when>
								<c:when test="${refundOrder.status ==2}"><div class="red">退款方式：全额退款</div></c:when>
							</c:choose>
			          </td>
		          </tr>
		          <tr class="red"><td>申请金额：${refundOrder.applyFee}</td></tr>
		          <tr class="red"><td>退款原因：${refundOrder.applyReason}</td></tr>
		          <tr class="red"><td>退款说明：${refundOrder.reason}</td></tr>
		          <tr class="red"><td>退款状态：
			         <c:choose>
						<c:when test="${refundOrder.refundStatus ==1}">退款处理中</c:when>
						<c:when test="${refundOrder.refundStatus ==2}">退款成功</c:when>
						<c:when test="${refundOrder.refundStatus ==3}">退款失败</c:when>
					</c:choose>
		          </td></tr>
		  		<tr><td clase="red">退款凭证：</td></tr>
		        </table>
            </td>
            <td style="padding-left:50px;" width="50%" valign="top">
			        <div class="bs-example">
			            <table class="info-list" border="0">
			              <tr class="red"><td>退款粮票：</td><td>${refundOrder.refundRedEnvelope}</td></tr>
			              <tr class="red"><td>退款现金：</td><td>${refundOrder.refundMoney}</td></tr>
			              <tr>
			                <td width="80" align="left"><span class="red">审核结果：</span></td>
			                <td colspan="5"><select class="select_normal w131 select-check" name="auditResult">
			                            <option value="pass">通过</option>
			                            <option value="nopass">不通过</option>
			                            </select></td>
			              </tr>
			              <tr class="dsn">
			                <td align="right"><span class="grey">原因：</span></td>
			                <td colspan="5"><textarea id="auditReason" class="textareas txt02" name="auditReason" cols="" rows="5" datatype="*4-30" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到30个字！"></textarea></td>
			              </tr>
			            </table>
			        </div>
            </td>
          </tr>
          <tr>
           <table style="margin-left:51px;" border="0" cellpadding="0" cellspacing="0">
           <tr><td><div class="list-name"></div></td>
            <td>
                <ul class="menu-img">
                    <c:forEach var="EbuyProductPic"  items="${picList}">
                    	<li>
	                    	<a href="<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${EbuyProductPic }" rel="clearbox[test2]">
		                    	<img src='<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${EbuyProductPic }' border="0" />
		                    </a>
	                 	</li>
                    </c:forEach>
                </ul>
            </td>
          </tr>
          </table>
          </tr>
          
        </table>
        
        <h3 class="mtop20">退款商品</h3>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        <display:table class="info-list-02 mtop20" name="refundproductList" id="refundpro" >
        	<display:column title="分类" property="productType"></display:column>
        	<display:column title="商品名称"><div class="blue">${refundpro.refundproductName}</div></display:column>
        	<display:column title="商品单价">
        		<c:if test="${refundpro.productPrice !=null}">${refundpro.productPrice/100}</c:if>
        	</display:column>
        	<display:column title="购买数量">
        		<c:if test="${refundpro.productQty !=null}">${refundpro.productQty}</c:if>
        		<c:if test="${refundpro.productQty ==null}">总计</c:if>
        	</display:column>
        	<display:column title="小计">
        		<c:if test="${refundpro.productQty !=null}">${refundpro.productPrice/100 * refundpro.productQty}</c:if>
        		<c:if test="${refundpro.productQty ==null}">${refundTotal/100}</c:if>
        	</display:column>
        </display:table>
        <div class="padb">
			<input id="sumAccount" class="info-btn" type="submit" onclick="applyAudit(${refundOrder.id});" value="提 交">
		</div>
	<h2>订单详情</h2>
	<div class="bs-example bgebeb">
	<form class="inputform">
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
					<tr class="red"><td>运单金额：</td><td>${(orderDetail.amount + orderDetail.deliveryFee)/100}</td></tr>
					<c:if test="${!isSupply }">
						<tr><td>-优惠：</td><td>${orderDetail.deliveryOrderCoupon div 100}</td></tr>
						<tr><td>实付金额：</td><td>${orderDetail.deliveryOrderAmount div 100}</td></tr>
					</c:if>
					<tr><td>运单状态：</td>
						<td>
							<c:choose>
								<c:when test="${orderDetail.deliveryStatus == 0}"><div class="red">未启动</div></c:when>
								<c:when test="${orderDetail.deliveryStatus == 1}"><div class="red">待发货</div></c:when>
								<c:when test="${orderDetail.deliveryStatus == 2}"><div class="red">待收货</div></c:when>
								<c:when test="${orderDetail.deliveryStatus == 3}"><div class="red">确认收货</div></c:when>
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
					<c:if test="${!isSupply }">
						<tr><td>身份证：</td><td>${orderDetail.identifyNo }</td></tr>
					</c:if>
		        </table>
            </td>
            <td style="padding-left:50px;" width="50%" valign="top">
            	<table border="0" cellpadding="0" cellspacing="0">
		          <tr><td>收货人：</td><td>${orderDetail.delivPeopleName}<c:if test="${empty orderDetail.delivPeopleName}">&nbsp;</c:if></td></tr>
		          <tr><td>联系电话：</td><td>${orderDetail.delivPhone}</td></tr>
		          <tr><td>配送地址：</td><td>${orderDetail.delivAddressArea} ${orderDetail.delivAddressDetail}</td></tr>
		        </table>
            </td>
          </tr>
        </table>
        
        <h3 class="mtop20">商品清单</h3>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        <display:table class="info-list-02 mtop20" name="orderDetail.productList" id="row" >
        	<display:column title="分类" property="productType"></display:column>
        	<display:column title="商品名称"><div class="blue">${row.productName}</div></display:column>
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
	          <td style="padding-left:50px;" class="bold"><c:if test="${content != null}">
		          <iframe style="margin-left:40px;" name="kuaidi100"
		                src="${content}" width="550"
		                height="350" marginwidth="0" marginheight="0" hspace="0" vspace="0"
		                frameborder="0" scrolling="no">
	              </iframe>
                </c:if>
                <c:if test="${content == null}">暂无物流信息</c:if>
	          </td>
          </tr>
        </table>
        <h3 class="mtop20">订单备注</h3>
        <hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/>
        
        <div class="padb mtop20"><input id="sumShopList" class="info-btn" type="button" value="返 回" onclick="history.back();"/></div>
    </form>
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/clearbox.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" >
function applyAudit(refundId){
	var audit = $('select option:selected').val();
	var auditReason = $("#auditReason").val();
	if(audit =='pass'){
		if(confirm("退款金额将返还至用户粮票 / 付款账户,是否操作？")){
			$("#sumAccount").attr({value:"提交中",onclick:""});
			$.post("../refundOrder/saveRefund.html",{refundId:refundId,audit:audit,auditReason:auditReason},function(data) {
				if (data.status == '0000') {
					$("#sumAccount").attr({value:"操作成功",onclick:""});
					setTimeout(function(){
						location.reload();
					},1500);
				}else if(data.status == '0001'){
					alert(data.message);
					setTimeout(function(){
						history.back();
					},1000);
				}else if(data.status == '0002'){
					alert("系统异常，请联系管理员！！！");
					history.back();
			}
			});
		}
	}else if(audit =='nopass'){
		if(auditReason.length>4 && auditReason.length<31){
			if(confirm("确认操作")){
				$("#sumAccount").attr({value:"提交中",onclick:""});
				$.post("../refundOrder/saveRefund.html",{refundId:refundId,audit:audit,auditReason:auditReason},function(data) {
					if (data.status == '0000') {
						$("#sumAccount").attr({value:"操作成功",onclick:""});
						setTimeout(function(){
							location.reload();
						},1500);
					}else if(data.status == '0001'){
						alert(data.message);
						setTimeout(function(){
							history.back();
						},1000);
					}else if(data.status == '0002'){
						alert("系统异常，请联系管理员！！！");
						history.back();
				}
				});
			}
		}else{
			alert("原因不能少于四个字,多于三十个字！！！");
		}
	}
}	
</script>
</html>
