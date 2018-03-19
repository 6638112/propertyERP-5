<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>举报</title>
<link rel="stylesheet" href="../css/shopping.common.css?v02" type="text/css">
</head>

<body class="bggrey">
<form class="inputform" action="../microblog/microblogReport.html">
<div class="shop-part01">   
    <section class="sectionBox item-details-info pos-relative">
        <section class="divide-box bordertbgrey"></section>
        <ul class="register-list">
        	<li class="img-add-num grey mtop15 f14">举报<span class="blue">@${nickName}</span>的信息：</li>
        	<li class="report-info-list f16">
        		<input class="dsn" type="hidden" name="reportInfo" value="垃圾营销" />
        		<input class="dsn" type="hidden" name="id" value="${id}" />
        		<div class="displaybox">
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon on left"></div><div class="left">垃圾营销</div></span>
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon left"></div><div class="left">不实信息</div></span>
        		</div>
        		<div class="displaybox">
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon left"></div><div class="left">有害信息</div></span>
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon left"></div><div class="left">违法信息</div></span>
        		</div>
        		<div class="displaybox">
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon left"></div><div class="left">淫秽色情</div></span>
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon left"></div><div class="left">人身攻击我</div></span>
        		</div>
        		<div class="displaybox">
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon left"></div><div class="left">违规有奖活动</div></span>
        			<span class="boxflex01 freight-setting-list"><div class="freight-check-icon left"></div><div class="left">其他</div></span>
        		</div>
        	</li>
            <li class="grey displaybox"><textarea class="area-text boxflex01 textareas" name="reportTextAreaInfo" rows="3" maxlength="100" type="text" placeholder="请详细填写，以确保举报能够被受理"></textarea></li>
            <li class="grey p00">还可以输入<span class="leftNum">100</span>字</li>
            <li><input class="btn-submit bgred create-shopping-btn" type="submit" name="button" value="举报" /></li>
        </ul>
    </section>
    
</div>
       

</form>

<script src="../js/jquery-1.11.2.min.js"></script>
<script src="../js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	})
</script>
<script src="../js/Validform_v5.3.2.js"></script>
<script>
$(function(){
	
	//表单验证
	$(".inputform").Validform({
		tiptype:1,
		ignoreHidden:true
	});

	$('.freight-setting-list').click(function(){
		var $thisCheck = $(this).find('.freight-check-icon');
		var thisText = $(this).find('.report-txt').text();
		
		$('.freight-setting-list').find('.freight-check-icon').removeClass('on');
		$thisCheck.addClass('on');
		$('[name=reportInfo]').val(thisText);
	});
	
	//输入框限制字数
	$('.textareas').keyup(function(){
		var valLength = $.trim($(this).val()).length;
		var currentVal = $.trim($(this).val());
		if(valLength > 99){
			$(this).val(currentVal.substring(0,100));	
		}
		var num = 100 - valLength;
		if(num < 0){
			num = 0;
		}
		$('.leftNum').text(num);
	});
	
});
	
</script>

</body>
</html>