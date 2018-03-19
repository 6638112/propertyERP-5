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
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>物业宝</title>
<link rel="stylesheet" href="../css/pay.common.css">
</head>

<body class="bggrey" <c:if test="${empty msg}">Onload="document.forms[0].submit()"</c:if>>
<section class="sectionBox welcome-img p00">
    <header class="sectionBox fantasia-header borderbottomgrey">
        <a id="breakPage" style="padding-top:10px;" class="disblock mleft15 left" href="#"><img class="back-arrow" style="width:10px;" src="../images/arrow-back.jpg" width="10" /></a>
        <div class="header-title">物业宝</div>
    </header>
    <div class="change-password-box">
    	<c:choose>
    		<c:when test="${empty msg}"><span><img class="pay-loading" src="../images/loading01.gif" width="10" /><br>页面加载中…</span></c:when>
    		<c:otherwise>${msg}</c:otherwise>
    	</c:choose>
    	
    </div>
    <form action="${financeUrl}" method="post">
    	<INPUT TYPE="HIDDEN" NAME="appKey" VALUE="${appKey}" />
    	<INPUT TYPE="HIDDEN" NAME="token" VALUE="${token}" />
    	<INPUT TYPE="HIDDEN" NAME="liberateNum" VALUE="${liberateNum}" />
    	<INPUT TYPE="HIDDEN" NAME="roomId" VALUE="${roomId}" />
    	<INPUT TYPE="HIDDEN" NAME="realName" VALUE="${realName}" />
    	<INPUT TYPE="HIDDEN" NAME="mobile" VALUE="${mobile}" />
    	<INPUT TYPE="HIDDEN" NAME="province" VALUE="${province}" />
    	<INPUT TYPE="HIDDEN" NAME="city" VALUE="${city}" />
    	<INPUT TYPE="HIDDEN" NAME="prefecture" VALUE="${prefecture}" />
    	<INPUT TYPE="HIDDEN" NAME="residential" VALUE="${residential}" />
    	<INPUT TYPE="HIDDEN" NAME="building" VALUE="${building}" />
    	<INPUT TYPE="HIDDEN" NAME="roomNum" VALUE="${roomNum}" />
    	<INPUT TYPE="HIDDEN" NAME="propertyFees" VALUE="${propertyFees}" />
    	<INPUT TYPE="HIDDEN" NAME="channel" VALUE="${channel}" />
    </form>
</section>
<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		
		//判断安卓ios系统
		var u = navigator.userAgent, app = navigator.appVersion;
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

	})
</script>
</body>
</html>