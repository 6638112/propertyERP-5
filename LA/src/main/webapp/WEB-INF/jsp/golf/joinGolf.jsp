<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cnfantasia.server.domainbase.golfGroupDetail.entity.GolfGroupDetail" %>
<%@ page import="com.cnfantasia.server.domainbase.golfGroup.entity.GolfGroup" %>
<!DOCTYPE html>
<html lang = "zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="description" content="">
<meta name="HandheldFriendly" content="True">
<meta name="format-detection" content="telephone=no, email=no">
<meta name="viewport" content="width=device-width, initial-scale=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui"/>

<title>约吗？免费打高尔夫去！我们去任性，解放区来买单！</title>
<link rel="stylesheet" href="../css/golf/common.css">
</head>
<body>
<div class=" hide"><img src="../images/golf/share-slogan.png" /></div>
<section class="sectionBox shade-bg"><img class="shade" src="../images/golf/shade.png" /></section>
<header class="golf-header"><img src="../images/golf/slogan01.jpg" /></header>
<form class="inputform">
	<input id ="groupOpenid" name="groupOpenid" type="hidden" value="${golfGroup.groupOpenid}">
	<input id ="groupId" name="groupId" type="hidden" value="${golfGroup.id}">
	<input id ="openid" name="openid" type="hidden" value="${openid}">
	<input id="nickName" name="nickName" type="hidden" value="${nickName }" >
	<input id="headimgurl" name="headimgurl" type="hidden" value="${headimgurl }" >
	<input id="ggdSize" type="hidden"  value="${fn:length(ggDetailList)}" >
	<section class="sectionBox">
		<div class="main-box">
	    	<ul class="captain head01 tbl">
		    	<c:forEach items="${ggDetailList}" var="ggd">
	   				<c:if test="${ggd.isAdmin==1}">
			        	<li class="captain-head tbl-cell"><img class="hat" src="../images/golf/head-img-big.png" />
			        	<div class="img-head"><img src="${ggd.headpicUrl}" /></div></li>
			        	<li class="txt tbl-cell"><span class="orange">${ggd.nickname}</span><br/><span class="f16">请你打高尔夫咯，要去的组起~</span></li>
	       			</c:if>
		    	</c:forEach>
	        </ul>
	        <ul class="text-info">
	        	<li><input class="input-nobg wp100 icon04" type="text" value="${fn:substring(golfGroup.groupDate,0, 11) }  ${golfGroup.groupTime }" readonly /></li>
	        	<li><input class="input-nobg wp100 icon05" type="text" value="深圳南山高球地带" readonly /></li>
        		
        		<c:if  test="${golfGroup.groupOpenid == openid && fn:length(ggDetailList)<8 }">
		            <li class="info-btn mtop20 "><input id="shareBtn" class="btn-common wp100 inviteBtn" type="button" value="邀请小伙伴去打高尔夫"/></li>
        		</c:if>
        		
        		<c:set var="isInTheGroup" value="0"/>
        		<c:if test="${fn:length(ggDetailList)==8}">
        			<c:forEach items="${ggDetailList}" var="ggd" varStatus="status"> 
        				<c:if test="${ggd.openid==openid}">
        					<li class="info-btn mtop20 "><input id="noShareBtn" class="btn-common wp100 inviteBtn" type="button" value="已成团，准备出发"/></li>
		       				<c:set var="isInTheGroup" value="1"></c:set>
	       				</c:if>
        			</c:forEach>
        			<c:if test="${isInTheGroup==0 }">
        				<li class="info-btn mtop20 "><input id="btnCreateGroup" class="btn-common wp100 inviteBtn" type="button" value="来晚了，再组一团"/></li>  
        			</c:if>
        		</c:if>
        		        		
        		<c:set var="isInTheGroup" value="0"/>
        		<c:if test="${fn:length(ggDetailList) < 8}">
        			<c:forEach items="${ggDetailList}" var="ggd" varStatus="status"> 
        				<c:if test="${ggd.openid==openid }">
			        		<c:set var="isInTheGroup" value="1"></c:set>
        					<c:if test="${golfGroup.groupOpenid!=openid }">
	        					<li class="info-btn mtop20 "><input class="btn-common wp100 bggrey" type="button" value="您已报名"/></li>
        					</c:if>
	       				</c:if>
        			</c:forEach>
        			<c:if test="${isInTheGroup==0 }">
        				<li id="phoneLi"><input id="phone" class="input-normal wp100 icon01" type="text" placeholder="请输入您的手机号码" datatype="m" nullmsg="请输入手机号码！" errormsg="请输入正确的手机号码格式！" /></li>
				        <li class="info-btn mtop20"><input id ="applyBtn"  class="btn-common wp100" type="button" value="立即报名参加" onclick="return submitGroupInfo();"/></li>
        			</c:if>
        		</c:if>
        		        		 
	        </ul>
	        <div class="mtop20"><img src="../images/golf/title02.png" /></div>
	        <ul class="text-info tbl">
	        	<li class="tbl-cell w100 disblock"><img class="jfq-logo" src="../images/golf/logo-jfq.png" /></li>
	            <li class="tbl-cell download-text f16">1.下载解放区APP<br/>2.凭报名手机号登录解放区<br/>3.于2月6日登录解放区“我的信箱”查看入场券</li>
	        </ul>
	        <div class="info-btn"><a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.jiefangqu.living" target="_blank"><input class="btn-common wp100" type="button" value="立即领取入场券" /></a></div>
	        <div class="mtop40"><img src="../images/golf/title04.png" /></div>
	        <div class="partners">
	        	<ul>
	        		<c:forEach items="${ggDetailList}" var="ggd">
	            		<li><div class="guys captain-head-small">
	            			<c:if test="${ggd.isAdmin==1}">
			            		<img class="hat" src="../images/golf/head-img-big.png" />
	            			</c:if>
		            		<img id="img01" class="img-head headimgurl" src="${ggd.headpicUrl }" />
	            		</div></li>
	        		</c:forEach>
	        		
	        		<c:forEach begin="1" end ="${8-fn:length(ggDetailList)}">
		                <li><div class="guys"><img class="img-head noheadimgurl" src="../images/golf/star-jfq.png" /></div></li> 
	        		</c:forEach>
	            </ul>
	            
	            <div class="orange t-center f14 txt01">*须8位小伙伴参加才能报团成功哦</div>
	        </div>
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
<script src="../js/golf/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
		
	//校验手机号码
	function isMobil(s) {
	    var patrn = /^(13[0-9]{9})|(14[0-9])|(18[0-9])|(15[0-9][0-9]{8})$/;
	    if (!patrn.exec(s)) return false;
	    return true;
	}

	$(document).ready(function(){	
		var $headImg = $('.partners li .guys .img-head');
		var headWIdth = $headImg.width();
		$headImg.height(headWIdth);
		
		var $shadeBg = $('.shade-bg');
		if($("#ggdSize").val() == 1 && $("#groupOpenid").val() === $("#openid").val()){
			$shadeBg.show();
		}else{
			$shadeBg.hide();
		}
		
		$('#shareBtn').click(function(){
			$shadeBg.show();
		});
		$('.shade').click(function(){
			$shadeBg.hide();
		});
	
		$('.input-nobg').focus(function(){
			$(this).blur();
		});
		
		$("#btnCreateGroup").click(function(){
			window.location.href = "http://www.jiefangqu.com/wlLightApp/golf/viewGolf.html";
		});
	});
	
	// 提交组团信息
	function submitGroupInfo(){
		if(!isMobil($("#phone").val())){
			alert("请输入正确的手机号");
			return false;
		}
		
		if($("#applyBtn").hasClass("bggrey")){
			return false;
		}
		$("#applyBtn").addClass("bggrey").val("报名中");	
		
		$.ajax({//使用ajax请求
			type: "POST",
			url: "../golf/saveGroupDetailInfo.html",
			dataType:"text",
			data:{groupOpenid:$("#groupOpenid").val()
				,groupId:$("#groupId").val()
				,openid:$("#openid").val()
				,nickName:$("#nickName").val()
				,phone:$("#phone").val()
				,headimgurl:$("#headimgurl").val()
			},
			success: function(data, textStatus){
		        alert(data.substr(0,4));
		        if(data==="报名成功"){
		        	$("#phoneLi").hide();
		        	$("#applyBtn").addClass("bggrey").val("报名成功");		 
		        	$(".noheadimgurl").eq(0).attr("src", $("#headimgurl").val());
		        }
		        
		        if(data.indexOf("您已报名")>-1){
		        	$("#phoneLi").hide();
		        	$("#applyBtn").addClass("bggrey").val("您已报名, 查看所在团").click(function(){
		        		window.location.href = "http://www.jiefangqu.com/wlLightApp/golf/viewGolf.html?code=CODE&state=" + data.substr(4);
		        	});
		        }
		        
		        if(data==="名额已满"){
		        	$("#phoneLi").hide();
		        	$("#applyBtn").addClass("bggrey").val("名额已满，请重新组团");
		        }
			},
		});
		return false; 
	};
	
</script>
</body>
</html>