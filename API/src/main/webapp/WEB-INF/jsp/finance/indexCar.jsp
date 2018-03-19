<%@page import="com.cnfantasia.server.common.utils.StringUtils"%>
<%@page import="com.cnfantasia.server.common.json.JsonResponse"%>
<%@page import="com.cnfantasia.server.common.exception.FocRuntimeException"%>
<%@page import="com.cnfantasia.server.common.exception.FocException"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="cleartype" content="on">
		<meta name="HandheldFriendly" content="True">
		<meta name="MobileOptimized" content="320">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<title>停车宝</title>
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pay.common.css">
	</head>
	
	<body class="bggrey" <c:if test="${empty msg}">onload="document.forms[0].submit()"</c:if>>
		<section class="sectionBox welcome-img p00">
		    <header class="sectionBox fantasia-header borderbottomgrey">
		        <a id="breakPage" style="padding-top:10px;" class="disblock mleft15 left" href="#"><img class="back-arrow" style="width:10px;" src="${pageContext.request.contextPath}/images/arrow-back.jpg" width="10" /></a>
		        <div class="header-title">停车宝</div>
		    </header>
		    <div class="change-password-box">
		    	<c:choose>
		    		<c:when test="${empty msg}"><span><img class="pay-loading" src="${pageContext.request.contextPath}/images/loading01.gif" width="10" /><br>页面加载中…</span></c:when>
		    		<c:otherwise>${msg}</c:otherwise>
		    	</c:choose>
		    	
		    </div>
		    <form action="${financeUrl}" method="post">
		    	<input TYPE="hidden" NAME="appKey" VALUE="${appKey}" />
		    	<input TYPE="hidden" NAME="token" VALUE="${token}" />
		    	<input TYPE="hidden" NAME="liberateNum" VALUE="${liberateNum}" />
		    	<input TYPE="hidden" NAME="licensePlate" VALUE="${licensePlate}" />
		    	<input TYPE="hidden" NAME="realName" VALUE="${realName}" />
		    	<input TYPE="hidden" NAME="mobile" VALUE="${mobile}" />
		    	<input TYPE="hidden" NAME="city" VALUE="${cityName}" />
		    	<input TYPE="hidden" NAME="prefecture" VALUE="${prefecture}" />
		    	<input TYPE="hidden" NAME="province" VALUE="${province}" />
		    	<input TYPE="hidden" NAME="residential" VALUE="${residential}" />
		    	<input TYPE="hidden" NAME="building" VALUE="${building}" />
		    	<input TYPE="hidden" NAME="roomNum" VALUE="${roomNum}" />
		    	<input TYPE="hidden" NAME="parkingFee" VALUE="${parkingFee}" />
		    </form>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script>
			$(function(){
				new FastClick(document.body);
				
				//判断安卓ios系统
				var u = navigator.userAgent;
				var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
				var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
				
				//安卓
				if(isAndroid){
					$('#breakPage').click(function(param){
						window.wuyebao.closeBtn('param');
					});
				}
				
				//ios
				if(isiOS){
					function closeBtn(param01,param02){
						document.location="jfq://"+param01+":/"+param02;
					};
					
					$('#breakPage').click(function(){
						closeBtn('param01','param02');
					});
				}
		
			});
		</script>
	</body>
</html>