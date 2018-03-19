<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	String feeType = request.getParameter("feeType");
	String feeName = request.getParameter("feeName");
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
		<link rel="dns-prefetch" href="//jiefangqu.com">

		<title>${param.feeName}</title>
		<link rel="stylesheet" href="../css/feepay.css">
	</head>

	<body class="bggrey pos-relative">
		<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100vh-minu-footer">
			<form class="inputform">
				<input type="hidden" name="feeTypeId" value="${param.feeType}" />
				<section class="sectionBox">
					<section class="divide-box borderbottomgrey"></section>
					<c:if test="${param.feeType == 1 || param.feeType == 2 || param.feeType == 3}">
						<ul class="fee-pay-list borderbottomgrey">
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="height36 f16 w65 justify">户号</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="chargeObject" type="text" placeholder="请填写${fn:substring(param.feeName,0,fn:length(param.feeName)-1)}表编号" value="${historyRecord.chargeObject}" maxlength="50" datatype="*" nullmsg="请填写${fn:substring(param.feeName,0,fn:length(param.feeName)-1)}表编号" /></div>
								</div>
							</li>
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="height36 f16 w65 justify">小区</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="gbName" type="text" placeholder="请填写您的小区名称" value="${historyRecord.groupBuildingName}" maxlength="30" datatype="*" nullmsg="请填写您的小区名称" /></div>
								</div>
							</li>
							<li>
								<div class="displaybox">
									<div class="height36 f16 w65 justify">门牌号</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="roomNum" type="text" placeholder="请填写您的门牌号" value="${historyRecord.roomNumuber}" maxlength="30" datatype="*" nullmsg="请填写您的门牌号" /></div>
								</div>
							</li>
						</ul>
						<section class="divide-box"></section>
						<ul class="fee-pay-list">
							<li>
								<div class="displaybox">
									<div class="height36 f16 w65 justify">金额</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="amount" type="number" placeholder="请填写缴费金额" value="" maxlength="24" datatype="numberFixTwo" nullmsg="请填写缴费金额" errormsg="请填写数字，可带两位小数" /></div>
								</div>
							</li>
						</ul>
						<section class="divide-box"></section>
						<ul class="fee-pay-list">
							<li>
								<div class="displaybox">
									<div class="height36 f16 w65 justify">联系电话</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="linkTel" type="number" placeholder="缴费信息有误时，可与您联系" value="${historyRecord.linkTel}" maxlength="11" datatype="m" nullmsg="请填写手机号码" errormsg="请填写正确的手机号码格式" /></div>
								</div>
							</li>
						</ul>
					</c:if>
					
					<c:if test="${param.feeType == 4 || param.feeType == 5}">
						<ul class="fee-pay-list borderbottomgrey">
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="height36 f16 w65 justify">充值号码</div>
									<div>：</div>
									<c:if test="${param.feeType == 4}">
										<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="chargeObject" type="number" placeholder="请填写手机号码" value="${historyRecord.linkTel}" maxlength="11" datatype="m" nullmsg="请填写手机号码" errormsg="请填写正确的手机号码格式" /></div>
									</c:if>
									<c:if test="${param.feeType == 5}">
										<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="chargeObject" type="text" placeholder="请填写固话号码" value="${historyRecord.linkTel}" maxlength="13" datatype="phoneNumber" nullmsg="请填写固话号码" errormsg="请填写正确的固话号码格式" /></div>
									</c:if>
								</div>
							</li>
							<li>
								<div class="displaybox">
									<div class="height36 f16 w65 justify">充值金额</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="amount" type="number" placeholder="请填写充值金额" value="" maxlength="24" datatype="numberFixTwo" nullmsg="请填写充值金额" errormsg="请填写数字，可带两位小数" /></div>
								</div>
							</li>
						</ul>
					</c:if>
					
					<c:if test="${param.feeType == 6}">
						<ul class="fee-pay-list borderbottomgrey">
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="height36 f16 w65 justify">运营商</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5">
										<select name="spId" id="operaters" class="input-text wp100 list-arrow" datatype="*" nullmsg="请选择运营商" >
								            <option value="">请选择运营商</option>
								            <c:forEach items="${livingFeeSpList}" var="livingFeeSp">
									            <option value="${livingFeeSp.id }" <c:if test="${historyRecord.tSpId == livingFeeSp.id}">selected</c:if> data-desc="${livingFeeSp.desc }">${livingFeeSp.name }</option>
								            </c:forEach>
								        </select>
									</div>
								</div>
								<input type="hidden" name="chargeObject" value="${historyRecord.chargeObject}" />
							</li>
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="height36 f16 w65 justify">小区</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="gbName" type="text" placeholder="请填写您的小区名称" value="${historyRecord.groupBuildingName}" maxlength="30" datatype="*" nullmsg="请填写您的小区名称" /></div>
								</div>
							</li>
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="height36 f16 w65 justify">门牌号</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="roomNum" type="text" placeholder="请填写您的门牌号" value="${historyRecord.roomNumuber}" maxlength="30" datatype="*" nullmsg="请填写您的门牌号" /></div>
								</div>
							</li>
							<li class="borderbottomgrey">
								<div class="displaybox">
									<div class="height36 f16 w65 justify">姓名</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="name" type="text" placeholder="请填写您的姓名" value="${historyRecord.name}" maxlength="30" datatype="*" nullmsg="请填写您的姓名" /></div>
								</div>
							</li>
							<li>
								<div class="displaybox">
									<div class="height36 f16 w65 justify">身份证号</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="cardId" type="text" placeholder="请填写您的身份证号" value="${historyRecord.cardId}" maxlength="30" datatype="idcard" nullmsg="请填写您的身份证号" /></div>
								</div>
							</li>
						</ul>
						<section class="divide-box"></section>
						<ul class="fee-pay-list">
							<li>
								<div class="displaybox">
									<div class="height36 f16 w65 justify">金额</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="amount" type="number" placeholder="请填写缴费金额" value="" maxlength="24" datatype="numberFixTwo" nullmsg="请填写缴费金额" errormsg="请填写数字，可带两位小数" /></div>
								</div>
							</li>
						</ul>
						<section class="divide-box"></section>
						<ul class="fee-pay-list">
							<li>
								<div class="displaybox">
									<div class="height36 f16 w65 justify">联系电话</div>
									<div>：</div>
									<div class="boxflex01 f16 grey mleft5"><input class="input-text wp100" name="linkTel" type="number" placeholder="缴费信息有误时，可与您联系" value="${historyRecord.linkTel}" maxlength="11" datatype="m" nullmsg="请填写手机号码" errormsg="请填写正确的手机号码格式" /></div>
								</div>
							</li>
						</ul>
					</c:if>

					<div class="bggrey">
						<section class="displaybox pt20">
							<img class="w16 mleft15 fee-check has-checked" src="../images/fee_icon_checked.png" /> <span class="mleft5 grey">在您进行支付时，表示您已经同意了<a class="blue" href="../feePay/paymentAgreement.htm">《解放区代收费协议》</a></span>
						</section>
						<c:if test="${param.feeType == 1}">
							<section class="displaybox pt10">
								<img class="w16 mleft15" src="../images/fee_icon_mind.png" /> <span class="mleft5 grey">温馨提示：若您的水费由物业公司代收，请勿在此缴费</span>
							</section>
						</c:if>
						<c:if test="${param.feeType == 2}">
							<section class="displaybox pt10">
								<img class="w16 mleft15" src="../images/fee_icon_mind.png" /> <span class="mleft5 grey">温馨提示：若您的电费由物业公司代收，请勿在此缴费</span>
							</section>
						</c:if>
						<c:if test="${param.feeType == 3}">
							<section class="displaybox pt10">
								<img class="w16 mleft15" src="../images/fee_icon_mind.png" /> <span class="mleft5 grey">温馨提示：若您的燃气费由服务点刷卡充值，请勿在此缴费</span>
							</section>
						</c:if>
						<c:if test="${param.feeType == 6}">
							<section class="operater-desc dsn">
								<section class="displaybox pt10">
									<img class="w16 mleft15" src="../images/fee_icon_mind.png" /> 
									<span class="mleft5 grey operater-text dsn">温馨提示：<span></span></span>
								</section>
							</section>
						</c:if>
						<section class="sectionBox bggrey wp72 mtop15 ptb10">
							<div><input class="btn-submit btnSubmit btn-next box-shadow border-radius4" type="button" value="立即支付" /></div>
						</section>
					</div>
				</section>
			</form>
			<div class="service_call t-center"><a class="blue" href="tel:13217328487">联系客服</a></div>
		</section>
		<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
		<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
		<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
		<script src="../js/method.common.js"></script>
		<script src="../js/feepay.type.js?20171122"></script>

	</body>

</html>