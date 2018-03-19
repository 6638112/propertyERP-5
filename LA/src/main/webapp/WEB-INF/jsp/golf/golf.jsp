<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="com.cnfantasia.wl.wechat.WeChatConstant" %>

<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="format-detection" content="telephone=no, email=no">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui"/>

<title>约吗？免费打高尔夫去！我们去任性，解放区来买单！</title>
<link rel="stylesheet" href="../css/golf/common.css">
</head>

<body>
<div class=" hide"><img src="../images/golf/share-slogan.png" /></div>
<section class="sectionBox shade-bg"><img class="shade" src="../images/golf/shade.png" /></section>
<header class="golf-header"><img src="../images/golf/slogan.jpg" /></header>

<form id="submitForm" class="inputform" method="post" action="../golf/saveGroupInfo.html">
	<input id ="shareURL" name="shareURL" type="hidden" value="${shareURL}">
	<input id ="groupOpenid" name="groupOpenid" type="hidden" value="${groupOpenid}">
	<input id ="openid" name="openid" type="hidden" value="${openid}">
	<input id="nickName" name="nickName" type="hidden" value="${nickName }" >
	<input id="headimgurl" name="headimgurl" type="hidden" value="${headimgurl }" >
	
	<section class="sectionBox">
		<div class="main-box">
	    	<ul class="captain">
	        	<li class="captain-head"><img class="hat" src="../images/golf/head-img-big.png" /><div class="img-head"><img src="${headimgurl}" /></div></li>
	        	<li class="orange">${nickName}</li>
	        </ul>
	        <ul class="text-info">
	        	<li><input  id="phone" name="phone" class="input-normal wp100 icon01" type="text" placeholder="请输入您的手机号码" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！" /></li> 
	        	<li><input id="realName" name="realName" class="input-normal wp100 icon02" type="text" placeholder="您的姓名" datatype="*" nullmsg="请输入姓名！" /></li>
	        	<li>
	            	<select id="groupDate" onchange="onSelectChange()" class="select-normal wp100 icon03" name="groupDate" datatype="*" nullmsg="请选择日期！">
	                	<option value="">请选择日期</option>
	                	<c:forEach items="${dateList}" var="date">
		                	<option value="${date }">${date } </option>
	                	</c:forEach>
	                </select>
	            </li>
	        	<li>
	            	<select id ="groupTime" class="select-normal wp100 icon03a" name ="groupTime"  datatype="*" nullmsg="请选择时间！">
	                	<option value="">请选择时间</option>
	                	<option value="14:00-15:00">14:00-15:00</option>
	                	<option value="15:00-16:00">15:00-16:00</option>
	                	<option value="16:00-17:00">16:00-17:00</option>
	                </select>
	            </li>
	            <li class="info-btn mtop20"><input id="btnSubmit" class="btn-common wp100 inviteBtn" type="submit" value="免费邀朋友打高尔夫" onclick="return checkBeforeSave();" /></li>
	            <li class="orange t-center f14">*须有7位小伙伴共同参加才可报团成功哦</li>
	        </ul>
	        <div class="mtop20"><img src="../images/golf/title02.png" /></div>
	        <ul class="text-info tbl">
	        	<li class="tbl-cell w100 disblock"><img class="jfq-logo" src="../images/golf/logo-jfq.png" /></li>
	            <li class="tbl-cell download-text">1.下载解放区APP<br/>2.凭报名手机号登录解放区<br/>3.于2月6日登录解放区“我的信箱”查看入场券</li>
	        </ul>
	        <div class="info-btn"><a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living" target="_blank"><input class="btn-common wp100" type="button" value="立即领取入场券" /></a></div>
	        <div class="mtop40"><img src="../images/golf/title03.png" /></div>
	        <ul class="text-rules">
	        	<li>“约吗，打高尔夫去”由解放区APP联合花样年文旅公司联合承办；</li>
	            <li>活动地点位于<span class="orange">深圳南山高球地带</span>，仅限深圳地区用户参加。成团后我们将会电话联系团长进行确认，请团长填写准确的联系电话。</li>
	            <li>活动预约时间：<br/><span class="orange">2015年2月1日-2015年2月6日18:00；</span></li>
	            <li>高尔夫活动时间：<br/><span class="orange">2015年2月7日14:00-2015年2月15日17:00，周末、法定节假日通用。</span></li>
	            <li><span class="orange">活动免费，每次时间60分钟</span>，项目包含视频教学、教练示范、学员试打。</li>
	        </ul>
	    </div>
	</section>
</form>
<footer class="golf-header"><a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living" target="_blank"><img src="../images/golf/foot.png" /></a></footer>

<script src="../js/golf/jquery-1.11.2.min.js"></script> 
<script src="../js/golf/Validform_v5.3.2.js"></script> 
<script src="../js/golf/fastclick.js"></script>
<script>
	(function($){
	    //表单验证
		$(".inputform").Validform({
			tiptype:3,
			callback:function(data){
				var $shadeBg = $('.shade-bg');
				$('.inviteBtn').click(function(){
					$shadeBg.show();
				});
				$('.shade').click(function(){
					$shadeBg.hide();
				});
			}
		});
	})(jQuery);

	$(function(){
		new FastClick(document.body);
	});
	
	function onSelectChange(){
		$("#groupTime").html("");
		
		$.ajax({//日期，时间段
			  type: "POST",
			  url: "../golf/getTimes.html",
			  dataType:"json",
			  cache: false,
			  data:{groupDate: $("#groupDate").val()},
			  success: function(data){
			    $.each(JSON.parse(data), function(i, item) {
			    	$("<option value='" + item + "'>" + item + "</option>").appendTo($("#groupTime"));
			    });
			  }
		});
	}
	
	function checkBeforeSave(){
		var checkResult = true;
		$.ajax({//日期，时间段
			  type: "POST",
			  url: "../golf/checkDateAndTime.html",
			  dataType:"text",
			  async:false,
			  data:{groupDate: $("#groupDate").val(), groupTime: $("#groupTime").val()},
			  success: function(data){
			     if(data==="已被使用"){
			    	 alert("选中的时间段已被他人占用，请选择其它时间段");
			    	 onSelectChange();
			    	 checkResult =  false;
			     }
			  }
		});
		
		return checkResult;
	}
	
/* 	$(function(){
		var $shadeBg = $('.shade-bg');
		$('.inviteBtn').click(function(){
			$shadeBg.show();
		});
		$('.shade').click(function(){
			$shadeBg.hide();
		});
	}); */
	
	/*
	// 提交组团信息
	function submitGroupInfo(){		
		$.ajax({//使用ajax请求
			type: "POST",
			url: "../golf/saveGroupInfo.html",
			async:false,
			dataType:"text",
			data:{groupOpenid:$("#groupOpenid").val()
				,openid:$("#openid").val()
				,nickName:$("#nickName").val()
				,phone:$("#phone").val()
				,realName:$("#realName").val()
				,groupDate:$("#groupDate").val()
				,groupTime:$("#groupTime").val()
				,headimgurl:$("#headimgurl").val()
			},
			async:false,
			success: function(data, textStatus){
				alert(data);
				//var newUrl = "http://www.jiefangqu.com/wlLightApp/golf/viewGolf.html?state="
			}
		});
		return false; 
	};	
	*/
</script>
</body>
</html>