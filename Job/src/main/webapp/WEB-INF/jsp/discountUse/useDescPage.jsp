<%@page import="com.cnfantasia.server.api.room.constant.RoomDict"%>
<%@page import="com.cnfantasia.server.api.pub.header.HeaderConstant"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path2;
	String currPath = basePath2+"/signalStyle/discountUse/";
%>
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

<title>折扣兑换说明</title>
<base href="<%=currPath%>" target="_blank">
<link rel="stylesheet" href="css/common.css">
</head>

<body>
<!-- <header class="fantasia-header">
	<a class="disblock p010 left" href="index.html"><img class="back-arrow" src="images/back-arrow.png" /></a>
    <div class="header-title">折扣兑换说明</div>
</header> -->
<section class="divide-box bordertopgrey"></section>
<section class="sectionBox">
   <div class="info pb0">
   		<%if(request.getAttribute("showIndex").equals(1)){%><div class="user-status">您当前身份</div><%}%>
        <div class="item-standard">
            <div class="title"><span class="left f16 bold ptb10">门牌未验证用户<br/><span class="grey f14">无法兑换粮票</span></span></div>
        </div>
    </div>
</section>
<section class="divide-box"></section>
<section class="sectionBox">
   <div class="info pb0">
   		<%if(request.getAttribute("showIndex").equals(2)){%><div class="user-status">您当前身份</div><%}%>
        <div class="item-standard pb0">
        	<div class="title"><span class="left f16 bold ptb10">门牌通过验证用户<br/><span class="grey f14">每月最低折扣可兑换粮票金额</span></span><span class="item-arrow-show animated <%if(request.getAttribute("showIndex").equals(2)){%> rotateIn<%}%>"></span></div>
            <div class="hide" <%if(request.getAttribute("showIndex").equals(2)){%> style="display:block"<%}%>>
            <div class="p010 mb20">
                <table class="bordered grey">
                  <tr>
                    <th width="49%">折扣</th>
                    <th width="50%">可兑粮票金额（元）</th>
                  </tr>
                  <tr>
                    <td>0.0至0.9</td>
                    <td>10.00</td>
                  </tr>
                  <tr>
                    <td>1.0至4.9</td>
                    <td>5.00</td>
                  </tr>
                  <tr>
                    <td>5.0至7.9</td>
                    <td>3.00</td>
                  </tr>
                  <tr>
                    <td>8.0至8.9</td>
                    <td>2.00</td>
                  </tr>
                  <tr>
                    <td>9.0至9.9</td>
                    <td>1.00</td>
                  </tr>
                </table>
            </div>
            <%
            if(RoomDict.RoomValidte_VerifyStatus.UNDO.compareTo( (Integer)(request.getAttribute("roomVerifyStatus")) )==0){
            	%><div class="check-num-btn">现在就去验证门牌></div><%
            }else if(RoomDict.RoomValidte_VerifyStatus.DOING.compareTo( (Integer)(request.getAttribute("roomVerifyStatus")) )==0){
            	%><div class="check-num-btn">门牌验证中</div><%
            }else if(RoomDict.RoomValidte_VerifyStatus.FAILED.compareTo( (Integer)(request.getAttribute("roomVerifyStatus")) )==0){
            	%><div class="check-num-btn">验证失败，查看原因></div><%
            }else if(RoomDict.RoomValidte_VerifyStatus.SUCCESS.compareTo( (Integer)(request.getAttribute("roomVerifyStatus")) )==0){
            	
            }
            %>
            </div>
        </div>
    </div>
</section>
<section class="divide-box"></section>
<section class="sectionBox">
   <div class="info pb0">
   		<%if(request.getAttribute("showIndex").equals(3)){%><div class="user-status">您当前身份</div><%}%>
        <div class="item-standard">
        	<div class="title"><span class="left f16 bold ptb10">5户活动用户<br/><span class="grey f14">每月最低折扣可兑换粮票金额为：<br/>物业费X(10-折扣)/10</span></span><span class="item-arrow-show animated <%if(request.getAttribute("showIndex").equals(3)){%> rotateIn<%}%>"></span></div>
            <div class="special bordertopgrey f14 hide" <%if(request.getAttribute("showIndex").equals(3)){%> style="display:block"<%}%>>
                <div class="hot-price p010">5户活动说明：<br/>同1小区5户业主一起上传自家物业费账单，账单审核通过后，此5户业主每月可兑换物业费折扣粮票，每5户成团，以此类推。
                <br/>加入5户活动群：458449740，体验每月物业费打折福利。</div>
            </div>
        </div>
    </div>
</section>
<section class="divide-box"></section>
<section class="sectionBox">
   <div class="info pb0">
        <div class="item-standard">
        	<div class="title"><span class="left f16 bold ptb10">已同解放区全面签约的小区<br/><span class="grey f14">当期折扣只能在缴费周期内给物业费打折，逾期未使用的折扣可以兑换粮票</span></span></div>
        </div>
    </div>
</section>
<section class="divide-box"></section>
<section id="appleDesc" class="sectionBox bordertopgrey borderbottomgrey">
   <div class="  p20" style="padding-left:30px"><span class="grey f14">抽奖活动与苹果公司无关，最终解释权归解放区所有</span></div>
</section>
<section class="divide-box"></section>

<script src="js/jquery-1.11.2.min.js"></script>
<script src="js/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
	});
</script>
<script src="js/exchange.common.js"></script>
<script type="text/javascript">
	//验证门牌按钮
	$(function(){
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		
		//安卓
		if(isAndroid){
			$('.check-num-btn').click(function(){
				window.desc.checkNumBtn(<%=request.getAttribute("roomVerifyStatus")%>);
			});
			$('#appleDesc').hide();
		}
		
		//ios
		if(isiOS){
			function checkNum(cmd,parameter){
				document.location="jfq://"+cmd+":/"+parameter;
            }
            $('.check-num-btn').click(function(){
				checkNum('discountExplain',<%=request.getAttribute("roomVerifyStatus")%>);
			});
		}
		
	});
</script>
</body>
</html>