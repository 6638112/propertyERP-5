<%-- 
    Document   : 物业费账单
    Created on : 2016-3-15, 11:49:24
    Author     : Liyl lyl010991@126.com
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">
		<title>物业费账单</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shopping.common.css">
	</head>
	<body class="bggrey">
		<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
			<section id="tabBox" class="tabBox pos-relative minheight100 zindex10">
				<section class="sectionBox">
				    <section class="divide-box10 bordertbgrey"></section>
				    <ul class="register-list">
				        <li class="borderbottomgrey">
				            <div class="displaybox">
				                <div class="item-standard-name height36 f16 boxflex01">缴费地址</div>
				                <div class="boxflex01 f16 lineheight140 t-right">深圳市南山区兰园商住楼A1栋三单元1103楼</div>
				            </div>
				        </li>
				        <li class="borderbottomgrey">
				            <div class="displaybox">
				                <div class="item-standard-name height36 f16 boxflex01">业主姓名</div>
				                <div class="boxflex01 f16 t-right">周三三</div>
				            </div>
				        </li>
				        <li>
				            <div class="displaybox">
				                <div class="item-standard-name height36 f16 boxflex01">本期账单月份</div>
				                <div class="boxflex01 f16 lineheight140 t-right">6月</div>
				            </div>
				        </li>
				    </ul>
				    <section class="divide-box10 bordertbgrey"></section>
				    <div class="list-box bgwhite">
				        <div class="displaybox">
				            <div class="item-standard-name height36 f16 boxflex01">本月管理费账单</div>
				            <div class="boxflex01 f16 t-right red">300.00元</div>
				        </div>
				    </div>
				    <section class="divide-box10 bordertbgrey"></section>
				    <ul class="register-list">
				        <li class="borderbottomgrey">
					        <div class="displaybox">
					            <div class="item-standard-name height36 f16 boxflex01">其他费用</div>
					            <div class="boxflex01 f16 t-right red">28.00元</div>
					        </div>
				        </li>
				        <li class="borderbottomgrey">
				            <div class="displaybox align-start lineheight180">
				                <div class="item-standard-name pright10 w100">专项维修基金</div>
				                <div class="boxflex01">
				                	<span class="grey">单价</span>
				                	<div>￥3.5</div>
				                </div>
				                <div class="boxflex01 t-center">
				                	<span class="grey">用量</span>
				                	<div>5</div>
				                </div>
				                <div class="boxflex01 t-right">
				                	<span class="grey">合计</span>
				                	<div>￥17.5</div>
				                </div>
				            </div>
				        </li>
				        <li>
				            <div class="displaybox align-start lineheight180">
				                <div class="item-standard-name pright10 w100">污水处理费污水处理费</div>
				                <div class="boxflex01">
				                	<span class="grey">单价</span>
				                	<div>￥3.5</div>
				                </div>
				                <div class="boxflex01 t-center">
				                	<span class="grey">用量</span>
				                	<div>3</div>
				                </div>
				                <div class="boxflex01 t-right">
				                	<span class="grey">合计</span>
				                	<div>￥10.5</div>
				                </div>
				            </div>
				        </li>
				    </ul>
					<section class="sectionBox wp100 ptb10 border-radius-bottom bggrey">
					    <div class="m010 mtop10"><input class="btn-submit btnSubmit btn-next bgred noborder white" type="button" value="确认缴费" /></div>
					</section>
				</section>
			</section>
			<section class="sectionBox wrap-bg pop-box02 dsn">
				<form class="inputform">
					<div class="popup-box slideInUp animated05s">
						<div class="t-center borderbottomgrey">
							<div class="f18 height48" onclick="location.href='${pageContext.request.contextPath}/payProperty/payDoneDetails.jsp'">微信支付</div>
				    		<section class="divide-box5 bordertbgrey"></section>
							<div class="f18 height48 back-btn">取消</div>
						</div>	
					</div>
				</form>
			</section>
		</section>
		<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/fastclick.js"></script>
		<script>
		$(function(){
			new FastClick(document.body);
		
			$('.btnSubmit').click(function(){
				$('#wrapBox').addClass('heightp100');
				$('.pop-box02').removeClass('dsn');
			});
		
			$('.back-btn').click(function(){
				$('#wrapBox').removeClass('heightp100');
				$(this).parents('.wrap-bg').addClass('dsn');
			});
		})
		</script>
	</body>
</html>