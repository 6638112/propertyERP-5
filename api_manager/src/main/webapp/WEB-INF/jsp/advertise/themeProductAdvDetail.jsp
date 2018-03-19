<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//"/>
<title>推广商品广告详情</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>推广商品广告详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 广告名称：</div></td>
            <td>${advertise.tittle}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 广告类型：</div></td>
            <td>
				<c:if test="${advertise.code == 'LA_EBUY'}">轻应用首页</c:if>
				<c:if test="${advertise.code == 'EBUY_AD'}">超市首页</c:if>
				<c:if test="${advertise.code == 'MAIN_BUSINESS_AD'}">首页弹框</c:if>
				<c:if test="${advertise.code == 'EBUY_THEME'}">社区店主题活动</c:if>
				<c:if test="${advertise.code == 'DREDGE_THEME'}">到家主题活动</c:if>
			</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 推广时间：</div></td>
            <td>${advertise.startTime} 至 ${advertise.endTime}</td>
          </tr>
            <c:if test="${advertise.code == 'EBUY_THEME' || advertise.code == 'DREDGE_THEME'}">
                <tr>
                    <td><div class="list-name"><span class="red">*</span> 活动说明：</div></td>
                    <td>${advertise.desc}</td>
                </tr>
                <tr>
                    <td><div class="list-name"><span class="red">*</span> 链接地址：</div></td>
                    <td>${advertise.linkUrl}</td>
                </tr>
            </c:if>
            <c:if test="${advertise.code == 'MAIN_BUSINESS_AD'}">
                <tr>
                    <td><div class="list-name"><span class="red">*</span> 弹框频率：</div></td>
                    <td>
                        <c:choose>
                            <c:when test="${advertise.frequency == 2}">点击查看后不再弹</c:when>
                            <c:otherwise>一天一次</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:if>
          <tr class="city-con01">
            <td><div class="list-name"><span class="red">*</span> 推广商品：</div></td>
            <td>
            	<ul class="address-list selected-box01">
					<c:forEach var="product" items="${products}">
                        <c:if test="${empty product.dpId}">
                            <li class="posrelative address-selected01">
                                <span class="address-name">${product.productName}</span><br>
                                <span class="grey">ID：<span class="data-obj-id">${product.productId}</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">${product.merchantName}</span></span>
                            </li>
                        </c:if>
                        <c:if test="${not empty product.dpId}">
                            <li class="posrelative address-selected01">
                                <span class="address-name">${product.dpName}</span><br>
                                <span class="grey">ID：<span class="data-obj-id">${product.dpId}</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">${product.ssName}</span></span>
                            </li>
                        </c:if>
					</c:forEach>
            	</ul>
            </td>
          </tr>
          <tr>
            <td align="right"><span class="red">*</span> 广告图片：</td>
            <td class="item-upload-img">
                <div class="uploadPreview01 mright6"><img class="imgPreview" width="82" height="82" src="${picPath}" /></div>
            </td>
          </tr>
        </table>
          
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">  
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
            <td>
				<c:if test="${areaType == 1}">全国范围</c:if>
				<c:if test="${areaType == 2}">
					<ul class="address-list selected-box">
						<c:forEach items="${areas}" var="city">
							<li class="posrelative address-selected"><span class="address-name">${city.cityName}</span></li>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${areaType == 3}">
					<ul class="address-list selected-box">
						<c:forEach items="${areas}" var="gb">
							<li class="posrelative address-selected"><span class="address-name">${gb.gbName}</span></li>
						</c:forEach>
					</ul>
				</c:if>
            </td>
          </tr>
        </table>
        
    </form>
</div>

</body>
</html>
