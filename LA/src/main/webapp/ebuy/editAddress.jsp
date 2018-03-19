<%@page import="com.cnfantasia.wl.wechat.constant.AddressConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>修改收货地址</title>
<link rel="stylesheet" href="../css/address.common.css">
</head>

<body class="bggrey">
<form class="inputform" action="${action}" method="post">

<input name="groupBuildingId" type=text class="dsn" value="${addresInfo.gbId }"/>
<input name="addressDetail" type=text class="dsn" value=""/>

<div class="main-part01 shop-part01">  
	<section id="wrapBox" class="sectionBox bggrey pos-relative minheight100">
		<section class="sectionBox">
		    <section class="divide-box borderbottomgrey"></section>
		    <ul class="register-list">
		        <li class="borderbottomgrey">
		            <div class="displaybox">
		                <div class="item-standard-name height36 f16 w50">收货人</div>
		                <div class="boxflex01 f16 t-right grey">
							<input type="hidden" name="id" value="${addresInfo.id}">
							<input id="userName" name="userName" class="input-text wp100 t-right" type="text" placeholder="请输入收货人姓名" value="${addresInfo.userName }" maxlength="100" datatype="*" nullmsg="请填写收货人" />
						</div>
		            </div>
		        </li>
		        <li class="borderbottomgrey">
		            <div class="displaybox">
		                <div class="item-standard-name height36 f16 w50">手机号码</div>
		                <div class="boxflex01 f16 t-right grey"><input id="userPhone" class="input-text wp100 t-right"  name="userPhone" type="text" placeholder="请输入收货人手机号码" value="${addresInfo.userPhone }" maxlength="11" datatype="m" nullmsg="请输入手机号码" errormsg="请输入正确的手机号码格式" /></div>
		            </div>
		        </li>
	            <li class="borderbottomgrey">
		            <div class="displaybox">
		                <div class="item-standard-name height36 f16">省份</div>
		                <div class="boxflex01 f16 lineheight140">
			                <select id="province" name="province" onchange="onSelectChange(this.value,'city');" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择省份" >
			                    <option style="color: #8e8e93;"  value="">请选择</option>
			                    <c:forEach items="${pcbList}" var="pcb" >
				                   	<option value="${pcb.id}" <c:if test="${pcb.id == addresInfo.provinceId}">selected</c:if> >${pcb.name}</option>
			                   	</c:forEach>
			                </select>
		                </div>
		            </div>
	            </li>
	            <li class="borderbottomgrey">
		            <div class="displaybox">
		                <div class="item-standard-name height36 f16">城市</div>
		                <div class="boxflex01 f16 lineheight140">
			                <select id="city" name="city" data-id="${addresInfo.cityId }" onchange="onSelectChange(this.value,'block');" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择城市" >
			                    <option value="">请选择</option>
			                </select>
		                </div>
		            </div>
	            </li>
		        <li class="borderbottomgrey">
		            <div class="displaybox">
		                <div class="item-standard-name height36 f16">行政区</div>
		                <div class="boxflex01 f16 lineheight140">
				            <select id="block" name="blockId" data-id="${addresInfo.blockId }" class="input-text wp100 list-arrow directionRtl pright15 mtop3" datatype="*" nullmsg="请选择行政区">
				                <option value="">请选择</option>
				            </select>
		                </div>
		            </div>
		        </li>
		        <li class="borderbottomgrey">
		            <div class="displaybox">
		                <div class="item-standard-name height36 f16">小区</div>
		                <div class="boxflex01 f16 lineheight140 list-arrow pright15" style="background-position-y: 6px;">
				            <input id="shopCreateSearchBox" class="building-search shop-create-search mright15 mtop3 t-right f16" placeholder="点击搜索小区" readonly="readonly" type="text" name="gbName" value="${addresInfo.gbName }" />
		                </div>
		            </div>
		        </li>
		    </ul>
		    <section class="sectionBox item-details-info pos-relative borderbottomgrey">
				 <div class="modifyAddressBox ">
			        <ul id="citySelectBox" class="register-list">
			        	<input type="hidden" name="areaSearchValid" datatype="*" nullmsg="请选择小区" value="1" />
			            <li class="pos-relative searchBox" style="min-height:37px;">
				            <div class="header-title displaybox shop-create-header">
				            	<input id="userRoomNum" class="order-search shop-create-search f16" style="text-indent:0" placeholder="请输入详细地址，如门牌号等" type="text" name="" value="${fn:substringAfter(addresInfo.addressDetail, addresInfo.gbName)}" datatype="*" nullmsg="请输入详细地址" />
				            </div>
			            </li>
			        </ul>
		        </div>
		    </section>
			<section class="sectionBox bggrey">
			    <div class="m010 mtop15"><input class="btn-submit btnSubmit btn-next" type="button" value="保存" /></div>
			</section>
		    <section class="divide-box"></section>
		
		</section>
	</section>
</div>

<div class="sectionBox shop-part02 bggrey dsn">
	 <div class="modifyAddressBox">
        <ul id="citySelectNewBox" class="register-list displaybox">
        	<li id="goBackBtn" class="f14">取消</li>
            <li class="pos-relative searchBox boxflex01" style="min-height:37px; margin-left: 0;">
	            <div class="mtop5 displaybox shop-create-header">
		            <input id="shopCreateSearchBtn" class="order-search boxflex01 shop-create-search inputBorder" placeholder="请输入小区名称" type="text" name="search" />
		            <div class="quick-delete dsn"></div>
	            </div>
            </li>
        	<li id="searchBtn" class="f14 mleft5">搜索</li>
        </ul>
	    <section class="sectionBox paddingMenu item-details-info pos-relative">
	        <li class="displaybox shop-create-check-single pointer hide">
	            <div class="area-info-text boxflex01 bordertopgrey">
	                <span class="area-info-name f16" gbId="" ></span>
	                <br><span class="area-info-address grey"></span>
	            </div>
	        </li>
	        <ul class="register-list shop-create-check">
	        </ul>        
		</section>
     </div>
</div>
</form>
<div class="sectionBox loading grey bordertopgrey dsn"><img src="../images/loading01.gif" /> 加载中…</div>
<div class="sectionBox creatInfo grey bordertbgrey pointer dsn">搜索不到您的小区？<div class="blue" style="display:inline-block">将此地址录入为小区名</div></div>
<div class="sectionBox reSearch grey bordertbgrey pointer dsn"><div class="blue">重新搜索</div></div>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script src="${resourcePathHttps}/commonjs/jquery.cookie.js"></script>
<script src="../js/ebuy.address.js"></script>
</body>
</html>