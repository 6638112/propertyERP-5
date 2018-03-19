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

<title>我的消费券</title>
<link rel="stylesheet" href="../css/prize.common.css?v0615">
</head>

<body class="bggrey">
<section class="sectionBox prize-list">
    <div class="get-record">
    	<c:forEach var="gift" items="${giftList.list}" varStatus="status"> 
	        <a target="_self"  class="record-list-bg <c:if test="${gift.useStatus!=1 }">out-of-date</c:if>" 
	        	<c:if test="${gift.useStatus!=1 }">href="#"</c:if>
	        	<c:if test="${gift.useStatus==1 }"><c:choose>
	        		<c:when test="${gift.type==1 || gift.type==2 }">href="../prize/amazing-prize_jfq.jsp?hbAmount=${gift.hbAmount }&expiryTimeLong=${gift.expiryTimeLong }"</c:when>
	        		<c:when test="${gift.type==3 }">href="../prize/amazing-prize_yibao.jsp"</c:when>
	        		<c:when test="${gift.type==4 }">href="../prize/amazing-prize_58.jsp"</c:when>
	        		<c:when test="${gift.type==5 }">href="../prize/amazing-prize_film.jsp?hbAmount=${gift.hbAmount }&exchCode=${gift.exchCode }&expiryTimeLong=${gift.expiryTimeLong }"</c:when>
	        		<c:when test="${gift.type==6 }">href="../prize/amazing-prize_zuche.jsp?hbAmount=${gift.hbAmount }&exchCode=${gift.exchCode }&expiryTimeLong=${gift.expiryTimeLong }"</c:when>
	        		<c:otherwise>href="../surpriseGift/prizeDetail.html?detailId=${gift.id}"</c:otherwise>
	        	</c:choose></c:if>
	        >
	            <ul class="disbox record-list" >
	                <li class="head-img01">
                	<c:choose>
	        		<%-- <c:when test="${gift.type==1 || gift.type==2 }"><img src="../images/logo-jfq.png" /></c:when> --%>
	        		<c:when test="${gift.type==3 }"><img src="../images/logo-yibao.png" /></c:when>
	        		<c:when test="${gift.type==4 }"><img src="../images/logo-58dj.png" /></c:when>
	        		<c:when test="${gift.type==5 }"><img src="../images/logo-film.png" /></c:when>
	        		<c:when test="${gift.type==6 }"><img src="../images/logo-zuche.png" /></c:when>
	        		<c:otherwise><img src="${gift.iconUrl}" /></c:otherwise>
	        		</c:choose>
	                </li>
	                <li class="record-txt"><span class="f16">${gift.name}</span><br>
	                <span class="f12">
	                	<c:if test="${gift.useStatus==1 }">
		                	有效期至：
		                	<c:set var="expiryTimeLong" value="${gift.expiryTimeLong }" />
		                    <%
		                    	String expiryTimeLong = pageContext.getAttribute("expiryTimeLong").toString();
								Date expiryTime = new java.util.Date(Long.parseLong(expiryTimeLong));
								out.write(new SimpleDateFormat("yyyy-MM-dd").format(expiryTime)); 
							%>
						</c:if>
	                	<c:if test="${gift.useStatus==2 }">已使用</c:if>
	                	<c:if test="${gift.useStatus==3 }">已过期</c:if>
	                </span>
	                </li>
	                <li id="opq" <c:choose><c:when test="${gift.type==5}">class="money bold color-red"</c:when>
		                <c:when test="${gift.type==6}">class="money bold color-green"</c:when>
		                <c:otherwise>class="money bold"</c:otherwise></c:choose>
	                 >
	                <c:choose><c:when test="${empty gift.showCount}">
	                	<c:if test="${gift.hbAmount!= null and gift.hbAmount!=0 and !(gift.hbAmount eq '')}">￥${fn:substring(gift.hbAmount,0,fn:indexOf(gift.hbAmount,"."))}</c:if>
	                </c:when>
	                <c:otherwise>${gift.showCount}</c:otherwise></c:choose>
	            </ul>
	        </a>
		</c:forEach>
		<!-- <a class="record-list-bg" href="../prize/amazing-prize_yibao.jsp" target="_self">
            <ul class="disbox record-list">
                <li class="head-img01"><img src="../images/logo-jfq.png" /></li>
                <li class="record-txt"><span class="f18">解放区消费券</span><br><span class="f12">有效期至：2015.04.16</span></li>
                <li class="money bold">￥20</li>
            </ul>
        </a>
		<a class="record-list-bg" href="../prize/amazing-prize_yibao.jsp" target="_self">
        <ul class="disbox record-list">
            <li class="head-img01 yibao-bg"><img src="../images/logo-yibao.png" /></li>
            <li class="record-txt"><span class="f18">怡宝 20元桶装水券</span><br><span class="f12">有效期至：2015.04.16</span></li>
        </ul>
        </a>
        <a class="record-list-bg" href="../prize/amazing-prize_58.jsp" target="_self">
        <ul class="disbox record-list">
            <li class="head-img01 daojia-bg"><img src="../images/logo-58dj.png" /></li>
            <li class="record-txt"><span class="f18">58到家 50元家政服务券</span><br><span class="f12">有效期至：2015.04.16</span></li>
        </ul>
        </a>
        <a class="record-list-bg out-of-date" href="#" target="_self">
            <ul class="disbox record-list">
                <li class="head-img01"><img src="../images/logo-blsh.png" /></li>
                <li class="record-txt"><span class="f18">解放区消费券</span><br><span class="f12">已失效</span></li>
                <li class="money bold">￥20</li>
            </ul>
            </a>
            <a class="record-list-bg out-of-date" href="#" target="_self">
            <ul class="disbox record-list">
                <li class="head-img01 daojia-bg"><img src="../images/logo-58dj.png" /></li>
                <li class="record-txt"><span class="f18">58到家 50元家政服务券</span><br><span class="f12">已使用</span></li>
            </ul>
            </a>
            <a class="record-list-bg out-of-date" href="#" target="_self">
				            <ul class="disbox record-list">
				                <li class="head-img01 daojia-bg"><img src="../images/logo-yibao.png" /></li>
				                <li class="record-txt"><span class="f18">怡宝 20元桶装水券</span><br>
				                <span class="f12">
					               	 已过期
				                </span></li>
				            </ul>
			            </a> -->
    </div>
</section>


<script src="js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="js/prize.common.js"></script>
</body>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?10613c5cec2e7e169835579b1c82fd77";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</html>