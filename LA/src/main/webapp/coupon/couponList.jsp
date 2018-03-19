<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    String path2 = request.getContextPath();
    String basePath2 = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path2;
%>
<!DOCTYPE html>
<html>
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

<title>我的消费券</title>
<link rel="stylesheet" href="../css/coupon.common.css?20171114">
</head>

<body class="bggrey pos-relative">
<section class="sectionBox prize-list">
	<section class="sectionBox">
	   <div class="info pb0">
	        <div class="input-search-box">
	            <div class="title disbox m010">
	                <div class="search-text"><input id="code" class="input-search" type="text" maxlength="32" name="code" placeholder="输入优惠码兑换消费券"></div>
	                <div id="exchangeBtn" class="btn-exchange">兑换</div>
	            </div>
	        </div>
	    </div>
	</section>
    <div class="get-record">
        <c:forEach items="${couponList}" var="coupon">
            <a class="record-list-bg bgred" <c:if test="${coupon.jumpType == 5 && not empty coupon.jumpData}">href="${coupon.jumpData}&jfqsource=weixin"</c:if>  target="_self">
                <ul class="displaybox record-list p10 mleft5 noborderleft">
                    <c:if test="${coupon.couponType == 1}">
                        <li class="new-ticket-num">
		               		 ￥<span class="f26">${coupon.discountMoney }</span>
		                	<c:choose>
							     <c:when test="${coupon.leastSpendUse == 0 }">  
	    							<div class="f12 mtop10">消费即可用</div>
							     </c:when>
							     <c:otherwise> 
									<div class="f12 mtop10">满${coupon.leastSpendUse }元可用</div>
							     </c:otherwise>
							</c:choose>
                        </li>
                    </c:if>
                    <c:if test="${coupon.couponType == 2}">
                        <li class="new-ticket-num"><span class="f26">${coupon.discountValue} 折</span></li>
                    </c:if>
                    <li class="record-txt boxflex01 borderleft">
                        <span class="f16"><span class="f16">${coupon.couponName}</span>
                        <div class="f12 grey"> ${coupon.couponDesc}</div>
                        <div class="f12 grey mtop5">有效期至 ${coupon.useEndDate}</div>
                        <c:if test="${!empty coupon.code}"><div class="f12">券码:${coupon.code}</div></c:if>
                    </li> 
                    <c:if test="${coupon.jumpType == 4 || coupon.jumpType == 5 }">
                        <li class="pos-relative">
                        	<div class="goto-item-btn red">立即使用</div>
                            <img style="width:4px" src="../images/arrow-right.png" />
                        </li>
                    </c:if>
                </ul>
            </a>
        </c:forEach>
        <c:if test="${empty couponList }">
        	<div class="t-center mtop20">无可用消费券</div>
        </c:if>
    </div>
</section>
<!-- 消息提示-->
<section class="pop-tips hide">
	<div class="pop-tips-text"></div>
</section>

</body>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script>
	$(function(){
		$('#exchangeBtn').click(function(){
			
			var codeVal = $.trim($('#code').val());
			
			if(codeVal !== ''){
				
				$.ajax({
					url: "../common/toUrl.do",
					data: {"detailUrl":"/coupon/exchange.json", "code": codeVal},
					type: "post",
					success: function(data){
						
						showMsg(data.message);
						
						if(data.status === '0000'){
							setTimeout(function(){
								location.reload();
							},2000)
						}
					}
				})
				
			}else{
				showMsg('请输入兑换码');
			}
		})
	})

	function showMsg(txt){
		if($(".pop-tips").is(':visible')){
			return;
		}
		$(".pop-tips-text").text(txt);
		$(".pop-tips").fadeIn();
		setTimeout(function(){
			$(".pop-tips").fadeOut();
		}, 2000)
	}
	
	var cnzz_s_tag = document.createElement('script');
	cnzz_s_tag.type = 'text/javascript';
	cnzz_s_tag.async = true;
	cnzz_s_tag.charset = 'utf-8';
	cnzz_s_tag.src = 'https://s11.cnzz.com/z_stat.php?id=1261123817&async=1';
	var root_s = document.getElementsByTagName('script')[0];
	setTimeout(function(){
		root_s.parentNode.insertBefore(cnzz_s_tag, root_s);
	},4000);
</script>
</html>