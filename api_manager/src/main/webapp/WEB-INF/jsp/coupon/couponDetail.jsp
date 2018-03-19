<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="<%=basePath%>//"/>
    <title>电商-消费券配置-消费券详情</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>消费券属性</h2>
    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
        <tr class="list-title">
            <td colspan="2">
                <div align="left" class="f14 bold">消费券属性</div>
            </td>
        </tr>
        <tr>
            <td width="20%">
                <div class="list-name"><span class="red">*</span> 券名称：</div>
            </td>
            <td>${coupon.couponName}</td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 适用范围：</div>
            </td>
            <td>
                <c:choose>
                    <c:when test="${coupon.useType==0}">通用</c:when>
                    <c:when test="${coupon.useType==1}">超市</c:when>
                    <c:when test="${coupon.useType==2}">物业</c:when>
                    <c:when test="${coupon.useType==3}">维修</c:when>
                    <c:when test="${coupon.useType==4}">车禁</c:when>
                    <c:when test="${coupon.useType==5}">定向商户</c:when>
                    <c:when test="${coupon.useType==6}">定向社区店商品</c:when>
                    <c:when test="${coupon.useType==7}">定向到家商品</c:when>
                    <c:otherwise>
                        未知
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <c:if test="${coupon.useType==3}">
            <tr class="swap-con swap-box-3">
                <td><div class="list-name">可用维修项目</div></td>
                <td>
                    <c:if test="${dredgecCouponConfig.supportLevel==1}">全部维修</c:if>
                    <c:if test="${dredgecCouponConfig.supportLevel==2}">${communitySupplyTypeName}</c:if>
                </td>
            </tr>
            <tr class="swap-con swap-box-3">
                <td><div class="list-name">可抵扣费用</div></td>
                <td>
                    <c:if test="${dredgecCouponConfig.couponFeeType == 1}">全部费用类型</c:if>
                    <c:if test="${dredgecCouponConfig.couponFeeType == 2}">人工费</c:if>
                    <c:if test="${dredgecCouponConfig.couponFeeType == 3}">其它费</c:if>
                </td>
            </tr>
        </c:if>
        <c:if test="${coupon.useType==5}">
            <tr class="swap-con swap-box-5">
                <td><div class="list-name">已选商户：</div></td>
                <td>
                    <ul class="address-list selected-box">
                    <li>
                        <span class="address-name">${supplyMerchant.name}</span><br>
                        <c:if test="${not empty supplyMerchant.name}">
                            <span class="grey">地址：深圳</span>
                        </c:if>
                    </li>
                    </ul>
                </td>
            </tr>
        </c:if>
        <c:if test="${(coupon.useType==6 or coupon.useType==7) and (not empty products)}">
	        <tr class="swap-con swap-box-6">
				<td><div class="list-name">已选商品：</div></td>
				<td>
					<ul class="address-list selected-box01">
						<li class="posrelative address-selected01 dsn">
							<span class="address-name"></span><br>
							<span class="grey">ID：<span class="data-obj-id"></span></span><span class="grey mleft10">供应商：<span class="data-obj-name">海吉星</span></span>
						</li>
						<c:forEach var="product" items="${products}">
							<li class="posrelative address-selected01">
								<c:if test="${empty product.dpId}">
									<span class="address-name">${product.productName}</span><br>
									<span class="grey">ID：<span class="data-obj-id">${product.shelfId}</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">${product.merchantName}</span></span>
									<input type="hidden" name="shelfIds" value="${product.shelfId }">
								</c:if>
								<c:if test="${not empty product.dpId}">
									<span class="address-name">${product.dpName}</span><br>
									<span class="grey">ID：<span class="data-obj-id">${product.dpId}</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">${product.ssName}</span></span>
									<input type="hidden" name="shelfIds" value="${product.dpId }">
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</c:if>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 优惠方式：</div>
            </td>
            <td>
                <c:choose>
                    <c:when test="${coupon.couponType==1}">现金券</c:when>
                    <c:when test="${coupon.couponType==2}">折扣券</c:when>
                    <c:when test="${coupon.couponType==3}">实物兑换券</c:when>
                    <c:otherwise>
                        未知
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td><div class="list-name"><span class="red">*</span> 有优惠是否送券：</div></td>
            <td>
                <c:if test="${coupon.useDiscountSend == 'Y'}">是</c:if>
                <c:if test="${coupon.useDiscountSend == 'N'}">否</c:if>
            </td>
        </tr>
        <tr <c:if test="${coupon.useType!=6 and coupon.useType!=7}">dsn</c:if>">
            <td><div class="list-name"><span class="red">*</span> 消费券链接：</div></td>
            <td>
                ${coupon.linkUrl}
            </td>
        </tr>
    </table>

    <h2 class="mtop10">发券规则</h2>
    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
        <tr class="list-title">
            <td colspan="2">
                <div align="left" class="f14 bold">基础信息</div>
            </td>
        </tr>
        <tr>
            <td width="20%">
                <div class="list-name"><span class="red">*</span> 发券时间：</div>
            </td>
            <td>${coupon.sendStartDate} 至 ${coupon.sendEndDate}</td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 余券数量：</div>
            </td>
            <td>${coupon.sendTotal - coupon.sendCount}</td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 发券总量：</div>
            </td>
            <td>${coupon.sendTotal}</td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 发券渠道：</div>
            </td>
            <td>
	            <c:choose>
	        		<c:when test="${(not empty coupon.goalType) and (coupon.goalType eq 1)}">超市购物</c:when>
	        		<c:when test="${(not empty coupon.goalType) and (coupon.goalType eq 2)}">缴物业费</c:when>
	        		<c:when test="${(not empty coupon.goalType) and (coupon.goalType eq 3)}">维修家政</c:when>
	        		<c:when test="${(not empty coupon.goalType) and (coupon.goalType eq 4)}">缴停车费</c:when>
	        		<c:when test="${(not empty coupon.goalType) and (coupon.goalType eq 66)}">手动领取</c:when>
	        		<c:when test="${(not empty coupon.goalType) and (coupon.goalType eq 67)}">分享得券</c:when>
	        	</c:choose>
            </td>
        </tr>

        <tr class="list-title">
            <td colspan="2">
                <div align="left" class="f14 bold">用户条件</div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 用户范围：</div>
            </td>
            <td>
                <c:choose>
                    <c:when test="${coupon.sendAreaType==1}">全国</c:when>
                    <c:when test="${coupon.sendAreaType==2}">城市</c:when>
                    <c:when test="${coupon.sendAreaType==3}">小区</c:when>
                    <c:otherwise>
                        未知
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

        <tr>
            <td>
                <div class="list-name">已选：</div>
            </td>
            <td>
                <ul class="address-list selected-box">
                    <c:forEach items="${areas}" var="item">
                        <li><span class="address-name">${item.name}</span><br><span class="grey">${item.addressDesc}</span></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>

        <tr class="list-title">
            <td colspan="2">
                <div align="left" class="f14 bold">订单条件</div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 发券条件：</div>
            </td>
            <td>满赠</td>
        </tr>
        <tr>
            <td></td>
            <td>满 ${coupon.leastSpendSend} 元赠 ${coupon.discountMoney} 元
            </td>
        </tr>
        <tr>
            <td><div class="list-name">所属类型：</div></td>
            <td>
            	<c:choose>
                    <c:when test="${not empty couponSceneName}">${couponSceneName}</c:when>
                    <c:otherwise>
                      	  未知
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

    <h2 class="mtop10">用券规则</h2>
    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
        <tr>
            <td width="20%">
                <div class="list-name"><span class="red">*</span> 使用结束时间：</div>
            </td>
            <td>
                <c:if test="${coupon.useEndDateType == 1}">${coupon.useEndDate}</c:if>
                <c:if test="${coupon.useEndDateType != 1}">领券后 ${coupon.useDateNumber} 天可用</c:if>
            </td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 使用张数：</div>
            </td>
            <td>每笔订单限用一张</td>
        </tr>
        <tr>
            <td>
                <div class="list-name"><span class="red">*</span> 使用条件：</div>
            </td>
            <td>
                订单满&nbsp;${coupon.leastSpendUse} 元可用
            </td>
        </tr>
    </table>
</div>

</body>
</html>
