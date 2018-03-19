<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>个人中心</title>
<link rel="stylesheet" href="css/recommendaward.css">
</head>

<body class="bggrey">
<section class="tabBox pos-relative">
    <ul class="displaybox setting-head-box">
        <li class="setting-head pos-relative">
            <div class="setting-head-img-box"><img class="setting-head-img" src="${sessionScope.snsUser.headimgurl }" /></div>
        </li>
        <li class="setting-text boxflex01"><span class="f16">${regist3rdResponse.nickName }</span><br><span class="f14 grey">解放号：${regist3rdResponse.userId }</span></li>
    </ul>
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <a class="displaybox" href="../order/qryOrderList.do">
            <div class="item-standard-name f16 boxflex01 list-icon list-icon07">超市订单</div>
            <div class="boxflex01 box-arrow t-right grey"><%-- <span class="tip-nums">${ebuyOrderCount }</span> --%></div>
        </a>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <a id="myAppointmentHref" class="displaybox" href="myAppointment.do">
            <div class="item-standard-name f16 boxflex01 list-icon list-icon08">我的预约</div>
            <c:if test="${empty clickStatus or clickStatus==1 }"> <!--没有红点，或红点已点 -->
	            <div class="boxflex01 box-arrow t-right grey"></div> 
            </c:if>
            <c:if test="${clickStatus==0 }"> <!--有红点未点 -->
           		<div class="boxflex01 box-arrow t-right grey"><span class="tip-nums"></span></div>
            </c:if>
        </a>
    </div>

    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <a class="displaybox" href="../dredge/myRedEnvelope.do">
            <div class="item-standard-name f16 boxflex01 list-icon list-icon12">我的粮票</div>
            <div class="boxflex01 box-arrow t-right grey"></div>
        </a>
    </div>

    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <a class="displaybox" href="../coupon/couponList.do">
            <div class="item-standard-name f16 boxflex01 list-icon list-icon11">我的消费券</div>
            <div class="boxflex01 box-arrow t-right grey"></div>
        </a>
    </div>
    
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite">
        <a class="displaybox" href="qryMyAccount.do">
            <div class="item-standard-name f16 boxflex01 list-icon list-icon09">推荐奖励</div>
            <div class="boxflex01 box-arrow t-right grey"><!-- <span class="tip-nums"></span> --></div>
        </a>
    </div>
    <section class="divide-box bordertbgrey"></section>
    <div class="list-box bgwhite borderbottomgrey">
        <a class="displaybox" href="accountSetting.jsp">
            <div class="item-standard-name f16 boxflex01 list-icon list-icon10">设置</div>
            <div class="boxflex01 box-arrow t-right grey"></div>
        </a>
    </div>
</section>


<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script>
	$(function(){
		$('#myAppointmentHref').click(function(){
			
			try {
				$.get("../dredge/clickRedpointInfo.json",function(data,status){
				   console.log("Data: " + data + "\nStatus: " + status);
				});
			}
			catch (e) {
				 console.log(e);
			}
		});
	});
</script>

</body>
</html>