<%-- 
    Document   : 物业费账单-已过缴费时间
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
		            <div class="boxflex01 f16 t-right">300.00元</div>
		        </div>
		    </div>
		    <section class="divide-box10 bordertbgrey"></section>
		    <ul class="register-list">
		        <li class="borderbottomgrey">
			        <div class="displaybox">
			            <div class="item-standard-name height36 f16 boxflex01">其他费用</div>
			            <div class="boxflex01 f16 t-right">28.00元</div>
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
			    <div class="m010 grey">已过在线缴费时间，请选择其他缴费方式</div>
			</section>
		</section>
	</body>
</html>