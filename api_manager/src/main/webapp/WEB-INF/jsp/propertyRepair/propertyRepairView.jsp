<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修工单管理-报修单详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css">
<style type="text/css">
	.emoji{ width: 18px; height: auto; margin-left: 2px; vertical-align: text-bottom;}
</style>
<script src="https://twemoji.maxcdn.com/2/twemoji.min.js"></script>
</head>

<body>
<div class="info">
	<form class="inputform">
        <h2>报修处理流程 
            <span class="f12 mar-left15">
                <span class="step"><span class="f16 bold">流程 1</span>：报修单管理</span>
                <span class="step orange"><span class="grey">-></span> <span class="f16 bold">流程 2</span>：分配处理人</span>
                <span class="step"><span class="grey">-></span> <span class="f16 bold">流程 3</span>：师傅上门处理</span>
                <span class="step"><span class="grey">-></span> <span class="f16 bold">流程 4</span>：管理处关闭工单</span>
            </span>
        </h2>
        <h2 class="mtop20">报修详情 <span class="f12 red">（待分配处理人）</span></h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right">报修门牌号：</td>
            <td>${pr.address } ${dredgeBill.address}</td>
          </tr>
          <tr>
            <td width="140" align="right">用户联系方式：</td>
            <td>${pr.tel } ${dredgeBill.userPhone}</td>
          </tr>
          <tr>
            <td align="right">解放号：</td>
            <td>${pr.sys0AddUser } ${dredgeBill.userId}</td>
          </tr>
          <tr>
            <td align="right">创建时间：</td>
            <td>${pr.sys0AddTime } ${dredgeBill.submitDate}</td>
          </tr>
          <tr>
            <td align="right">报修类型：</td>
            <td>${pr.repairTypeName } ${dredgeBill.type}</td>
          </tr>
          <tr>
            <td align="right"><span class="red">业主期望师傅上门时间：</span></td>
            <td>${fn:substring(pr.expectDate,0,10)} ${fn:substring(pr.expectTimeBegin,0,5)} ${fn:substring(dredgeBill.expectDate,0,16)}</td>
          </tr>
          <tr>
            <td align="right">问题描述：</td>
            <td>${pr.repairContent } ${dredgeBill.content}</td>
          </tr>
          <tr>
            <td align="right">现场图片：</td>
            <td>
            	<ul class="menu-img">
            		<c:forEach items="${prPicList }" var="pic">
           				<li><a href="${pic }" rel="lightbox-group"><img src="${pic }" border="0" /></a></li>
            		</c:forEach>
	           	</ul>
            </td>
          </tr>
        </table>
        <h2 class="mtop20">处理流程</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="140" align="right">分配处理师傅：</td>
            <td>${pr.propertyRepairer.name } ${dredgeBill.repairName}</td>
          </tr>
          <tr>
            <td align="right">联系电话：</td>
            <td>${pr.propertyRepairer.tel } ${dredgeBill.repairPhone}</td>
          </tr>
          <tr>
            <td align="right">师傅预计上门时间：</td>
            <td>${pr.estimateDoorTimeBegin } ${dredgeBill.estimateDoorTime}</td>
          </tr>
          <tr>
            <td align="right">备注：</td>
            <td>${pr.asignDesc}</td>
          </tr>
          <tr>
            <td align="right">派单时间：</td>
            <td>${pr.asignTime} ${dredgeBill.acceptTime}
            </td>
          </tr>
          <tr>
            <td align="right">工单完成时间：</td>
            <td>${pr.finishedTime}
                <c:if test="${dredgeBill.status == 3 || dredgeBill.status == 5 || dredgeBill.status == 7 || dredgeBill.status == 8}">
                    ${dredgeBill.updTime}
                </c:if>
            </td>
          </tr>
        </table>
        
        <c:if test="${fn:length(processRecordList)>0 }">
	        <h2 class="mtop20">师傅服务流程记录</h2>
	        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1" style="white-space: normal;">
	        	<c:forEach items="${processRecordList }" var="item" varStatus="status">
		          <tr>
		            <td width="140" align="right">流程记录${status.count}</td>
		            <td>
		            	${item.prAddTimeStr } ${item.prDesc} <br/>
		            	<ul class="menu-img">
		            		<c:forEach items="${item.picList }" var="pic">
	            				<li><a href="${pic }" rel="lightbox-group"><img src="${pic }" border="0" /></a></li>
		            		</c:forEach>
	           			</ul>
		            </td>
		          </tr>
	        	</c:forEach>
	        </table>
        </c:if>

        <c:if test="${order != null }">
            <h2 class="mtop20">支付信息</h2>
            <table class="info-list-02" border="0" cellpadding="0" cellspacing="1" style="white-space: normal;">
                <tr>
                    <td width="140" align="right">订单总额：</td>
                    <td>${(order.amount + order.totalCouponAmount) div 100} 元</td>
                </tr>
                <tr>
                    <td align="right">用户缴费：</td>
                    <td>${order.amount div 100} 元</td>
                </tr>
                <tr>
                    <td align="right">解放区补贴：</td>
                    <td>${order.totalCouponAmount div 100} 元</td>
                </tr>

                <c:if test="${fn:length(dredgeBill.amountList) > 0}">
                    <c:forEach items="${dredgeBill.amountList}" var="amount">
                        <tr>
                            <td align="right">${amount.feeName}：</td>
                            <td>${amount.feeAmount} 元</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <tr>
                    <td align="right">支付方式：</td>
                    <td><c:choose>
                        <c:when test="${order.payMethod==1}">微信支付</c:when>
                        <c:when test="${order.payMethod==2}">支付宝</c:when>
                        <c:when test="${order.payMethod==3}">银联支付</c:when>
                        <c:when test="${order.payMethod==4}">纯粮票支付</c:when>
                        <c:when test="${order.payMethod==5}">纯积分支付</c:when>
                        <c:when test="${order.payMethod==6}">微信轻应用支付</c:when>
                        <c:when test="${order.payMethod==7}">纯优惠券支付</c:when>
                        <c:when test="${order.payMethod==8}">纯折扣支付</c:when>
                        <c:when test="${order.payMethod==9}">双乾支付</c:when>
                        <c:when test="${order.payMethod==10}">免费</c:when>
                    </c:choose></td>
                </tr>
                <tr>
                    <td align="right">用户解放号：</td>
                    <td>${order.buyerId}</td>
                </tr>
                <tr>
                    <td align="right">支付时间：</td>
                    <td>${order.sys0UpdTime}</td>
                </tr>
                <tr>
                    <td align="right">支付流水号：</td>
                    <td>${payRecord.flowNo}</td>
                </tr>
            </table>
        </c:if>

        <h2 class="mtop20">业主评价</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="140" align="right">师傅打分：</td>
            <td>
            	<fmt:formatNumber value="${starLevel }" pattern="#0.00"/>
             </td>
          </tr>
          <tr>
            <td align="right">业主评价：</td>
            <td>${commentContent}</td>
          </tr>
        </table>
        <div class="padb mtop10"><input class="info-btn checkRepair" type="button" value="返 回" onclick="window.history.back();"/></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/picbox.js"></script>
<script type="text/javascript">
	twemoji.parse(document.body, {"size":72});
</script>
</html>
