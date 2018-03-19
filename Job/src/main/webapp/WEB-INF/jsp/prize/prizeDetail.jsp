<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no, email=no">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>${gift.name}</title>
<link rel="stylesheet" href="../css/prize.common.css">
</head>

<body>
<section class="sectionBox">
	<ul class="jfq-quan"> 
    	<li class="quan-money">
    		<c:choose><c:when test="${empty gift.showCount}">
           	<c:if test="${gift.hbAmount!= null and gift.hbAmount!=0 and !(gift.hbAmount eq '')}">￥${fn:substring(gift.hbAmount,0,fn:indexOf(gift.hbAmount,"."))}</c:if>
            </c:when>
            <c:otherwise>${gift.showCount}</c:otherwise></c:choose>
    	</li>
        <li class="quan-date">
        	有效期至：
             <c:set var="expiryTimeLong" value="${gift.expiryTimeLong }" />
                <%String expiryTimeLong = pageContext.getAttribute("expiryTimeLong").toString();
				Date expiryTime = new java.util.Date(Long.parseLong(expiryTimeLong));
				out.write(new SimpleDateFormat("yyyy-MM-dd").format(expiryTime));%>
        </li>
    </ul>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">奖品</div>
</section>
<section class="sectionBox">
	<div class="common-info f14">${gift.ext_valueCode}</div>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">使用说明</div>
</section>
<section class="sectionBox">
	<div class="common-info f14">${gift.ext_useDesc}</div>
</section>
<section class="sectionBox">
	<div class="common-title f14 bordertopgrey borderbottomgrey">联系我们</div>
</section>
<section class="sectionBox">
    <ul class="use-date f16 disbox">
        <li>客服电话</li>
        <li class="t-right"><a class="darkblue" href="tel:400-960-2228">400-960-2228</a></li>
    </ul>
    <ul class="use-date f16 disbox user-qq bordertopgrey">
        <li>用户Q群</li>
        <li class="t-right darkblue">384432710</li>
    </ul>
</section>
<c:if test="${not empty gift.ext_linkUrl}">
<section class="divide-box bordertopgrey borderbottomgrey" style="padding-bottom:56px;"></section>
<section class="sectionBox fixed-info">
    <a class="fixed-text bordertopgrey" href="${gift.ext_linkUrl}" target="_blank">使用${gift.name}</a>
</section>
</c:if>
<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="js/prize.common.js?0706"></script>
</body>
</html>