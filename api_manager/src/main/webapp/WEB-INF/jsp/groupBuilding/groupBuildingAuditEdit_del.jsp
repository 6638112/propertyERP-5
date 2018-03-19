
<%@page import="com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-小区管理-编辑</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/clearbox.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/jquery.simple-dtpicker.css?v=1"/>
</head>

<body>
<div class="info">
	<form class="inputform" method="post" action="../groupBuilding/saveAuditEdit.html">
		<input type="hidden" name="gbId" value="${entity.id }" />
        <h2>小区管理-编辑</h2>
	    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
	      <tr>
	        <td width="120" align="right"><span class="red">*</span> 小区名称：</td>
	        <td><input type="text" class="input_text pp" name="gbName" value="${entity.name }" datatype="*2-20" nullmsg="请填写小区名称！" errormsg="小区名称至少2个字！"/></td>
	      </tr>
	      <tr>
	        <td align="right">小区所属物业：</td>
	        <td>${entity.propertyCompanyName }</td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 小区所在地：</td>
	        <td>
	        	<select id="province" onchange="onSelectChange(this,'city');" class="province select_normal" data-first-title="选择省" title="选择省" datatype="n" errormsg="请选择省！" nullmsg="请选择省！">
                	<option value="-">选择省</option>
                	<c:forEach items="${pcbList}" var="pcb" >
                		<option value="${pcb.id}" <c:if test="${entity.addressProvinceName==pcb.name }">selected="selected"</c:if>>${pcb.name}</option>
                	</c:forEach>
                </select> 
                <select id="city" onchange="onSelectChange(this,'block');"  class="city select_normal" data-first-title="选择市" datatype="n" errormsg="请选择市！" nullmsg="请选择市！">
                	<option value="-">选择市</option>
                	<c:if test="${entity.addressCityName!=null }"><option value="0" selected="selected">${entity.addressCityName}</option></c:if>
                </select> 
                <select id="block" name="block_id" class="area select_normal" data-first-title="选择区" datatype="n" errormsg="请选择区！" nullmsg="请选择区！">
                	<option value="-">选择区</option>
                	<c:if test="${entity.addressBlockName!=null }"><option value="${entity.tBlockFId}" selected="selected">${entity.addressBlockName}</option></c:if>
                </select>
	         </td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 详细地址：</td>
	        <td><input type="text" class="input_text pp" name="addressDesc" value="${entity.addressDesc }" datatype="*4-20" nullmsg="请填写详细地址！" errormsg="详细地址由4到20位字符组成！" /></td>
	      </tr>
	      <tr>
	        <td align="right"><span class="red">*</span> 管理处：</td>
	        <td>
	        	${entity.propertyManagementName}
	        </td>
	      </tr>
	      <tr>
	        <td align="right">小区所属街道办：</td>
	        <td><input type="text" class="input_text pp" name="streetName" value="${entity.streetName }" /></td>
	      </tr>
	      <tr>
	        <td align="right">街道办电话：</td>
	        <td><input type="text" class="input_text pp" id="street_tel" name="streetTel" value="${entity.streetTel }" /></td>
	      </tr>
	      <tr>
	        <td align="right">小区所在居委会：</td>
	        <td><input type="text" class="input_text pp" name="neighborName" value="${entity.neighborName }"/></td>
	      </tr>
	      <tr>
	        <td align="right">居委会电话：</td>
	        <td><input type="text" class="input_text pp" id="neighbor_tel" name="neighborTel" value="${entity.neighborTel }" /></td>
	      </tr>
	      <tr>
            <td width="120" align="right">合作模式：</td>
            <td>
            	<c:if test="${entity.cooperationType == 1 }" >基础 </c:if>
            	<c:if test="${entity.cooperationType == 2 }" >高级</c:if>
            	<c:if test="${entity.cooperationType == 3 }" >全面 </c:if>
            </td>
          </tr>
	      <tr>
            <td width="120" align="right">签约状态：</td>
            <td>
            	<input class="mtop3" name="signStatus" <c:if test="${entity.signStatus==0 }" >checked="checked" </c:if> type="radio" value="0" /> 未签约
            	<input class="mtop3 mleft20" name="signStatus" <c:if test="${entity.signStatus==1 }" >checked="checked" </c:if> type="radio" value="1" /> 签约
            </td>
          </tr>
			<tr>
				<td width="120" align="right">账单通知短信：</td>
				<td>
					<input type="checkbox" name="isSendWyMsg" <c:if test="${entity.isSendWyMsg==1 }">checked</c:if> >自动发送
				</td>
			</tr>
          <tr>
            <td width="120" align="right">胡萝卜广告显示状态：</td>
            <td>
            	<input class="mtop3" name="hlbSwitch" <c:if test="${entity.hlbSwitch==0 }" >checked="checked" </c:if> type="radio" value="0" /> 不显示
            	<input class="mtop3 mleft20" name="hlbSwitch" <c:if test="${entity.hlbSwitch==1 }" >checked="checked" </c:if> type="radio" value="1" /> 显示
            </td>
          </tr>
			<tr>
				<td width="120" align="right">小区参数配置：</td>
				<td>
					<select class="select_normal softwareSelectBox swap-tabs" name="softwareType">
						<option value="0">解放区平台</option>
						<option value="1" <c:if test="${config != null && config.isValid == 1}">selected</c:if>>极致软件</option>
					</select>
				</td>
			</tr>
			<tr class="swap-info swap-val-1 <c:if test="${config == null || config.isValid != 1}">dsn</c:if>">
				<td><div class="list-name"><span class="red">*</span>服务器地址</div></td>
				<td>
					<input type="text" class="input_text pp" name="ipAddress" value="${config.ipAddress}" maxlength="1000" datatype="*" nullmsg="请填写服务器地址！">
				</td>
			</tr>
			<tr class="swap-info swap-val-1 <c:if test="${config == null || config.isValid != 1}">dsn</c:if>">
				<td><div class="list-name"><span class="red">*</span>物业软件小区ID</div></td>
				<td>
					<input type="text" class="input_text pp" name="softwareGbId" value="${config.softwareGbId}" maxlength="100" datatype="*" nullmsg="请填写物业软件小区ID！">
				</td>
			</tr>
			<tr class="swap-info swap-val-1 <c:if test="${config == null || config.isValid != 1}">dsn</c:if>">
				<td><div class="list-name"><span class="red">*</span>数据库代码</div></td>
				<td>
					<input type="text" class="input_text pp" name="databaseCode" value="${config.databaseCode}" maxlength="100" datatype="*" nullmsg="请填写数据库代码！">
				</td>
			</tr>
			<tr class="swap-info swap-val-1 <c:if test="${config == null || config.isValid != 1}">dsn</c:if>">
				<td><div class="list-name"><span class="red">*</span>RSA公钥</div></td>
				<td>
					<input type="text" class="input_text pp" name="rsaPublicKey" value="${config.rsaPublicKey}" maxlength="1000" datatype="*" nullmsg="请填写RSA公钥！">
				</td>
			</tr>
			<tr class="swap-info swap-val-1 <c:if test="${config == null || config.isValid != 1}">dsn</c:if>">
				<td><div class="list-name"><span class="red">*</span>RSA私钥</div></td>
				<td>
					<input type="text" class="input_text pp" name="rsaPrivateKey" value="${config.rsaPrivateKey}" maxlength="1000" datatype="*" nullmsg="请填写RSA私钥！">
				</td>
			</tr>
			<tr class="swap-info swap-val-1 <c:if test="${config == null || config.isValid != 1}">dsn</c:if>">
				<td><div class="list-name">默认维修师傅ID</div></td>
				<td>
					<input type="text" class="input_text pp" name="defaultRepairerId" value="${config.defaultRepairerId}" maxlength="20" datatype="numb" ignore="ignore"><span class="red">不填写则不同步公共维修单</span>
				</td>
			</tr>
			<tr class="swap-info swap-val-1 <c:if test="${config == null || config.isValid != 1}">dsn</c:if>">
				<td><div class="list-name">不可缴费日期</div></td>
				<td class="">每月 <input type="text" name="cannotConnectStartDate" class="input_text pp w30 pay-date-input" value="${config.cannotConnectStartDate}" maxlength="2" datatype="numMonth" ignore="ignore"> 日 至
					<input type="text" name="cannotConnectEndDate" class="input_text pp w30 pay-date-input" value="${config.cannotConnectEndDate}" maxlength="2" datatype="numMonth" ignore="ignore"> 日</td>
			</tr>
	    </table>
	    <div class="padb"><input class="info-btn apptypeBtn" type="button" value="保 存" /></div>
    </form>
    
    <div>
    <form class="proptypeForm" action="<%=basePath%>/groupBuildingJson/editPropPayBillType.json" method="post">
    	<input type="hidden" name="gbId" value="${entity.id}" />
    	<h2>物业费缴费配置</h2>
    	<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		<tr>
		    <td align="right">费用名称：</td>
		    <td>物业费</td>
		</tr>
		<tr>
		    <td width="120" align="right">是否开启：</td>
		    <td>
			    <input class="mtop3" name="activeStatus" <c:if test="${entity.propfeeCanpay==2 }" >checked="checked" </c:if> type="radio" value="2" /> 关闭缴费
				<input class="mtop3 mleft20" name="activeStatus" <c:if test="${entity.propfeeCanpay==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启缴费
			</td>
		</tr>
		<tr>
		    <td width="120" align="right">是否开启：</td>
		    <td>
			    <input class="mtop3 verificationStatus_close" name="verificationStatus" <c:if test="${entity.verificationStatus==0 || entity.verificationStatus==null}" >checked="checked" </c:if> type="radio" value="0" /> 关闭缴费校验
				<input class="mtop3 mleft20 verificationStatus_open" name="verificationStatus" <c:if test="${entity.verificationStatus==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启缴费校验
			</td>
		</tr>
		<tr>
		    <td width="120" align="right">是否开启：</td>
		    <td>
			    <input class="mtop3 closeStatus" name="isPrefer" <c:if test="${entity.isPrefer==0 || entity.isPrefer==null}" >checked="checked" </c:if> type="radio" value="0" /> 关闭随机立减
				<input class="mtop3 mleft20" name="isPrefer" <c:if test="${entity.isPrefer==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启随机立减
			</td>
		</tr>
		<tr>
			<td align="right">缴费方式：</td>
			<td><input type="radio" class="mtop3 selectPaytimeType" name="paytimeType" value="1"  <c:if test="${paytimeType == 1 }" >checked="checked" </c:if> datatype="*" nullmsg="请选择缴费方式！"> 月度缴费
				<input type="radio" class="mtop3 mleft20 selectPaytimeType" name="paytimeType" value="2" <c:if test="${paytimeType == 2 }" >checked="checked" </c:if> data-val="1"> 周期缴费
			</td>
		</tr>
		<tr class="swap-con swap-val-1 <c:if test="${paytimeType != 2 }" >dsn</c:if>">
			<td align="right"></td>
			<td><input type="radio" class="mtop3 selectPeriodPaytimeType" name="propertyPeriodType" value="1" <c:if test="${entity.propertyPeriodType==1 || entity.propertyPeriodType==null}" >checked="checked" </c:if> datatype="*" nullmsg="请选择周期缴费方式！"> 固定周期缴费
				<input type="radio" class="mtop3 mleft20 selectPeriodPaytimeType" name="propertyPeriodType" <c:if test="${entity.propertyPeriodType==2 }" >checked="checked" </c:if> value="2" data-val="2"> 选择周期缴费
			</td>
		</tr>
		<tr class="swap-con swap-val-2 <c:if test="${entity.propertyPeriodType!=2 || paytimeType == 1}" >dsn</c:if>">
			<td align="right"></td>
			<td>
				<c:forEach items="${months}" var="months" varStatus="status">
					<input id="pk${status.index}" type="checkbox" style="cursor: pointer;" class="mtop3 <c:if test="${status.index != 0}">mleft20</c:if>"
						   <c:if test="${status.index == 0}">datatype="*" nullmsg="请选择周期缴费方式！"</c:if>
						   name="periodMonths" value="${months}"
					<c:forEach items="${alterMonths}" var="alterMonths">
						   <c:if test="${months == alterMonths}">checked="checked"</c:if>
					</c:forEach>
					> <label style="cursor: pointer;" for="pk${status.index}">${months}个月</label>
				</c:forEach>
			</td>
		</tr>
    	</table>
    	<div class="padb"><input class="info-btn w100 proptypeBtn" type="button" value="保 存" /></div>
    </form>
    </div>
    
   <div class="" > <!-- 抄表配置 -->
    <form class="otherMRTypeForm" action="<%=basePath%>/groupBuildingJson/addMRPayBillType.json" method="post">
    	<input type="hidden" name="gbId" value="${entity.id}" />
    	<input type="hidden" name="billTypeId" value="${pbtMR.id }"/>
    	<h2>抄表收费配置</h2>
    	<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		<tr>
		    <td align="right"><div class="list-name"><span class="red">*</span> 费用名称：</div></td>
		    <td>
		    	<select name="typeName" value="" datatype="*" nullmsg="请选择费用名称！"> 
		    		<option value=""></option> 
		    		<option value="水电费"  <c:if test="${pbtMR.name=='水电费' }"> selected="selected" </c:if> >水电费</option> 
		    		<option value="水费" <c:if test="${pbtMR.name=='水费' }"> selected="selected" </c:if>>水费</option> 
		    		<option value="电费"<c:if test="${pbtMR.name=='电费' }"> selected="selected" </c:if>>电费</option> 
		    	</select>
		    </td>
		</tr>
		<tr>
		    <td width="120" align="right"><div class="list-name"><span class="red">*</span> 是否开启：</div></td>
		    <td>
			   <input class="mtop3" name="activeStatus" type="radio" <c:if test="${pbtMR.activeStatus==0 }">  checked="checked"  </c:if> value="2" /> 关闭缴费
				<input class="mtop3 mleft20" name="activeStatus" type="radio" value="1" <c:if test="${pbtMR.activeStatus==1 }">  checked="checked"  </c:if> /> 开启缴费
			</td>
		</tr>
		<tr>
			<td width="120" align="right">是否开启：</td>
			<td>
				<input class="mtop3 closeStatus" name="isPrefer" <c:if test="${pbtMR.preferStatus==0 }" >checked="checked" </c:if> type="radio" value="0" /> 关闭随机立减
				<input class="mtop3 mleft20" name="isPrefer" <c:if test="${pbtMR.preferStatus==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启随机立减 
			</td>
		</tr>
    	</table>
    	<div class="padb">
    		<input class="info-btn w100 otherMRTypeBtn" type="button" value="保 存" />
    	</div>
    </form>
    </div>
    
    <!-- copyDiv -->
    <div class="baseFeeDivBase" style="display: none;">
    <form class="othertypeFormBase" action="<%=basePath%>/groupBuildingJson/operPayBillType.json" method="post">
    	<input type="hidden" name="gbId" value="${entity.id}" />
    	<input type="hidden" name="billTypeId" value=""/>
    	<h2>非物业费缴费配置</h2>
    	<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		<tr>
		    <td align="right"><div class="list-name"><span class="red">*</span> 费用名称：</div></td>
		    <td><input type="text" class="input_text pp" name="typeName" value="" datatype="*" nullmsg="请填写费用名称！"/></td>
		</tr>
		<tr>
            <td><div class="list-name"><span class="red">*</span> 图标：</div></td>
            <td>
                <div class="uploadPreview01"><input type="file" name="icon" class="uploadImage02" /><img class="imgPreview" width="44" height="44" src="" /></div>
                <span class="f12 mar-left15">注：仅限一张图片，最佳尺寸44px*44px</span>
                <div id="picDiv" style="width: 1px; height: 1px;"></div>
            </td>
        </tr>
		<tr>
		    <td width="120" align="right"><div class="list-name"><span class="red">*</span> 是否开启：</div></td>
		    <td>
			    <input class="mtop3" name="activeStatus" type="radio" checked="checked" value="2" /> 关闭缴费
				<input class="mtop3 mleft20" name="activeStatus" type="radio" value="1" /> 开启缴费
			</td>
		</tr>
		<tr>
			<td width="120" align="right">是否开启：</td>
			<td>
				<input class="mtop3 closeStatus" name="isPrefer" <c:if test="${entity.isPrefer==0 }" >checked="checked" </c:if> type="radio" value="0" /> 关闭随机立减
				<input class="mtop3 mleft20" name="isPrefer" <c:if test="${entity.isPrefer==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启随机立减
			</td>
		</tr>
    	</table>
    	<div class="padb">
    		<input class="info-btn w100 othertypeBtnBase" type="button" value="保 存" />
    		&nbsp;&nbsp;<input class="weak-btn small-btn w100" type="button" onclick="appendFeeDiv(this);" value="继续添加" />
    		&nbsp;&nbsp;<input class="weak-btn small-btn w100" type="button" onclick="removeFeeDiv(this);" value="删除" />
    	</div>
    </form>
    </div>
    
    <c:forEach items="${otherTypeList}" var="row">
    	<div class="baseFeeDiv">
	    <form class="othertypeForm othertypeFormBase" action="<%=basePath%>/groupBuildingJson/operPayBillType.json" method="post">
	    	<input type="hidden" name="gbId" value="${entity.id}" />
	    	<input type="hidden" name="billTypeId" value="${row.id}"/>
	    	<h2>非物业费缴费配置</h2>
	    	<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
			<tr>
			   <td align="right"><div class="list-name"><span class="red">*</span> 费用名称：</div></td>
			    <td>
			    	<c:if test="${row.name!='物业费' && row.isPropFee !=1}">
			    		<input type="text" class="input_text pp" name="typeName" value="${row.name}" datatype="*" nullmsg="请填写费用名称！"/>
			    	</c:if>
			    	<c:if test="${row.name=='物业费' && row.isPropFee ==1}">
			    		${row.name}
			    	</c:if>
			    </td>
			</tr>
			<tr>
	            <td><div class="list-name"><span class="red">*</span> 图标：</div></td>
	            <td>
	            	<c:if test="${row.name!='物业费' && row.isPropFee !=1}">
		                <div class="uploadPreview01"><input type="file" name="icon" class="uploadImage02" /><img class="imgPreview" width="44" height="44" src="${row.icon}" /></div>
		                <span class="f12 mar-left15">注：仅限一张图片，最佳尺寸44px*44px</span>
		                <div id="picDiv" style="width: 1px; height: 1px;"></div>
	                </c:if>
	                <c:if test="${row.name=='物业费' && row.isPropFee ==1}">
			    		 <div class="uploadPreview01"><img class="imgPreview" width="44" height="44" src="${row.icon}" /></div>
			    	</c:if>
	            </td>
	        </tr>
			<tr>
			    <td width="120" align="right"><div class="list-name"> 是否开启：</div></td>
			    <td>
			    	<c:if test="${row.name!='物业费' && row.isPropFee !=1}">
			    		<input class="mtop3" name="activeStatus" type="radio" <c:if test="${row.activeStatus == 2}">checked="checked"</c:if> value="2" /> 关闭缴费
						<input class="mtop3 mleft20" name="activeStatus" type="radio" <c:if test="${row.activeStatus == 1}">checked="checked"</c:if> value="1" /> 开启缴费
			    	</c:if>
			    	<c:if test="${row.name=='物业费' && row.isPropFee ==1}">
			    		<c:if test="${row.activeStatus == 1}">缴费已开启</c:if>
			    		<c:if test="${row.activeStatus == 2}">缴费已关闭</c:if>
			    	</c:if>
				</td>
			</tr>
			<tr>
				<td width="120" align="right">是否开启：</td>
				<td>
					<c:if test="${row.name!='物业费' && row.isPropFee !=1}">
						<input class="mtop3 closeStatus" name="isPrefer" <c:if test="${row.isPrefer==0 }" >checked="checked" </c:if> type="radio" value="0" /> 关闭随机立减
						<input class="mtop3 mleft20" name="isPrefer" <c:if test="${row.isPrefer==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启随机立减
					</c:if>
					<c:if test="${row.name=='物业费' && row.isPropFee ==1}">
						<c:if test="${row.isPrefer == 1}">随机立减已开启</c:if>
						<c:if test="${row.isPrefer == 0}">随机立减已关闭</c:if>
					</c:if>
				</td>
			</tr>
	    	</table>
	    	<div class="padb">
	    		<c:if test="${row.name!='物业费' && row.isPropFee !=1}">
		    		<input class="info-btn w100 othertypeBtn" type="button" value="保 存" />
		    		&nbsp;&nbsp;<input class="weak-btn small-btn w100" type="button" onclick="appendFeeDiv(this);" value="继续添加" />
		    		&nbsp;&nbsp;<input class="weak-btn small-btn w100" type="button" onclick="removeFeeDiv(this);" value="删除" />
	    		</c:if>
	    		<c:if test="${row.name=='物业费' && row.isPropFee ==1}">
		    		<input class="weak-btn small-btn w100" type="button" onclick="appendFeeDiv(this);" value="继续添加" />
		    	</c:if>
	    	</div>
	    </form>
	    </div>
    </c:forEach>
    <c:if test="${fn:length(otherTypeList)==0}">
    <div class="baseFeeDiv">
    <form class="othertypeForm othertypeFormBase" action="<%=basePath%>/groupBuildingJson/operPayBillType.json" method="post">
    	<input type="hidden" name="gbId" value="${entity.id}" />
    	<input type="hidden" name="billTypeId" value=""/>
    	<h2>非物业费缴费配置</h2>
    	<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		<tr>
		    <td align="right"><div class="list-name"><span class="red">*</span> 费用名称：</div></td>
		    <td><input type="text" class="input_text pp" name="typeName" value="" datatype="*" nullmsg="请填写费用名称！"/></td>
		</tr>
		<tr>
            <td><div class="list-name"><span class="red">*</span> 图标：</div></td>
            <td>
                <div class="uploadPreview01"><input type="file" name="icon" class="uploadImage02" /><img class="imgPreview" width="44" height="44" src="" /></div>
                <span class="f12 mar-left15">注：仅限一张图片，最佳尺寸44px*44px</span>
                <div id="picDiv" style="width: 1px; height: 1px;"></div>
            </td>
        </tr>
		<tr>
		    <td width="120" align="right"><div class="list-name"><span class="red">*</span> 是否开启：</div></td>
		    <td>
			    <input class="mtop3" name="activeStatus" type="radio" checked="checked" value="2" /> 关闭缴费
				<input class="mtop3 mleft20" name="activeStatus" type="radio" value="1" /> 开启缴费
			</td>
		</tr>
		<tr>
			<td width="120" align="right">是否开启：</td>
			<td>
				<input class="mtop3 closeStatus" name="isPrefer" checked="checked" type="radio" value="0" /> 关闭随机立减
				<input class="mtop3 mleft20" name="isPrefer" checked="checked" type="radio" value="1" /> 开启随机立减
			</td>
		</tr>
    	</table>
    	<div class="padb">
    		<input class="info-btn w100 othertypeBtn" type="button" value="保 存" />
    		&nbsp;&nbsp;<input class="weak-btn small-btn w100" type="button" onclick="appendFeeDiv(this);" value="继续添加" />
    		&nbsp;&nbsp;<input class="weak-btn small-btn w100" type="button" onclick="removeFeeDiv(this);" value="删除" />
    	</div>
    </form>
    </div>
    </c:if>
    
</div>

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/revenue/layer.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/Validform_v5.3.2.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.form.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/revenue/moment.js?v=5"></script>


<script type="text/javascript">
	//物业费用类别配置
	(function($){
	    //表单验证
		$(".proptypeForm").Validform({
			btnSubmit:".proptypeBtn",
			tiptype:4,
			dragonfly:true,
			ajaxPost:false,
			ignoreHidden: true,
			beforeSubmit:function(curform){
				$(".proptypeForm").attr('onsubmit','return false;');
			},
			callback:function(data){
				$(".proptypeForm").ajaxSubmit(function(data) {
					data = data.replace("<PRE>","");
					data = data.replace("</PRE>","");
					try {
						data = eval("("+data+")");
					} catch (e) {
						try {
							data = eval(data);
						} catch (e2) {}
					}
					if (data.status == '0000') {
						alert('操作成功');
						if (data.dataValue.preferStatus == 0) {
							$(".info").find('.closeStatus').each(function(e) {
								$(this).prop("checked",true);
							});
						}
						$(".proptypeForm").Validform().resetStatus();
					} else {
						alert(data.message);
						$(".proptypeForm").Validform().resetStatus();
					}
				});
			}
		});
	})(jQuery);

	//其它费用类别配置
	(function($){
		function submitOtherForm(curBtnObj){
			var curForm = curBtnObj.closest('form'); 
		    //表单验证
			$(curForm).Validform({
				btnSubmit:curBtnObj,
				tiptype:4,
				dragonfly:true,
				ajaxPost:false,
				beforeSubmit:function(curform){
					$(curForm).attr('onsubmit','return false;');
				},
				callback:function(data){
					$(curForm).ajaxSubmit(function(data) {
						data = data.replace("<PRE>","");
						data = data.replace("</PRE>","");
						try {
							data = eval("("+data+")");
						} catch (e) {
							try {
								data = eval(data);
							} catch (e2) {}
						}
						if (data.status == '0000') {
							alert('操作成功');
							$(curForm).Validform().resetStatus();
						} else {
							alert(data.message);
							$(curForm).find('.closeStatus').prop("checked",true);
							$(curForm).Validform().resetStatus();
						}
					});
				}
			});
		}
		
		$(".othertypeBtn").each(function(){
			submitOtherForm(this);
		});
	    
		$(".otherMRTypeBtn").each(function(){
			submitOtherForm(this);
		});
	    
	    //提示业主姓名是否为空
	    $(".verificationStatus_open").click(function(){
	    	var gbId = $("input[name='gbId']").val();
	    	$.ajax({
				type:"post",
				url:"<%=basePath%>/groupBuildingJson/isHasEmptyProprietor.json",
				data:{'gbId': gbId},
				dataType:"TEXT",
				success:function(data){
					var obj = jQuery.parseJSON(data);
					if(obj.dataValue.emptyType == 3) {
						alert("小区下没有门牌信息");
						$(".verificationStatus_close").prop("checked",true);
						$(".verificationStatus_open").prop("checked",false);
					}
					if(obj.dataValue.emptyType == 2) {
						alert("小区门牌下业主信息未登记");
						$(".verificationStatus_close").prop("checked",true);
						$(".verificationStatus_open").prop("checked",false);
					}
					if(obj.dataValue.emptyType == 1) {
						alert("小区门牌下有部分业主信息未登记");
					}
				}
			});
	    });
	    
	})(jQuery);


	function appendFeeDiv(obj){
		//$(obj).parents('.baseFeeDiv').clone(true).appendTo('.info');//ok
		//$(obj).parents('.baseFeeDiv').after($(obj).parents('.baseFeeDiv').clone(true));//ok
		var cloneDiv = $('.baseFeeDivBase').clone(true);
		cloneDiv.attr("style","");
		cloneDiv.attr("class","baseFeeDiv");
		$(obj).parents('.baseFeeDiv').after(cloneDiv);
		window.parent.TuneHeight();
		
		var tmpForm =  cloneDiv.find(".othertypeFormBase");
		var clickBtn =  cloneDiv.find(".othertypeBtnBase");
		(function($){
			tmpForm.Validform({
				btnSubmit:clickBtn,
				tiptype:4,
				dragonfly:true,
				ajaxPost:false,
				beforeSubmit:function(curform){
					tmpForm.attr('onsubmit','return false;');
				},
				callback:function(data){
					tmpForm.ajaxSubmit(function(data) {
						data = data.replace("<PRE>","");
						data = data.replace("</PRE>","");
						try {
							data = eval("("+data+")");
						} catch (e) {
							try {
								data = eval(data);
							} catch (e2) {}
						}
						if (data.status == '0000') {
							alert('操作成功');
							tmpForm.find("[name='billTypeId']").val(data.dataValue.billTypeId);
							tmpForm.Validform().resetStatus();
						} else {
							alert(data.message);
							tmpForm.Validform().resetStatus();
						}
					});
				}
			});
		})(jQuery);
	}
	
	function removeFeeDiv(obj){
		var length = $(obj).parents('.baseFeeDiv').siblings('.baseFeeDiv').length;
		if(length>0){//有兄弟元素则可以移除
			//$(obj).parent().parent().parent().remove();
			var toRemoveDiv = $(obj).parents('.baseFeeDiv');
			
			var gbId = toRemoveDiv.find("[name=gbId]").val();
			var billTypeId = toRemoveDiv.find("[name=billTypeId]").val();
			if(billTypeId ==null || billTypeId =='' ){
				toRemoveDiv.remove();
			}else{
				$.ajax({
					type:"post",
					url:"<%=basePath%>/groupBuildingJson/delPayBillType.json",
					data:{'gbId': gbId,'billTypeId': billTypeId},
					dataType:"TEXT",
					beforeSend:function(data){
					},
					success:function(data){
						try {
							data = eval("("+data+")");
						} catch (e) {
							try {
								data = eval(data);
							} catch (e2) {}
						}
						if (data.status == '0000') {
							alert('删除成功');
							toRemoveDiv.remove();
						} else {
							alert(data.message);
						}
					}
				});
			}
			
		}
	}
	
	$(function(){
		 //表单验证
		$(".inputform").Validform({
			btnSubmit:".apptypeBtn",
			tiptype:3,
			ignoreHidden: true,
			beforeSubmit:function(){
				var val01 = $('.pay-date-input').eq(0).val()*1;
				var val02 = $('.pay-date-input').eq(1).val()*1;
				if(val01 !== '' && val02 !== '' && val01 > val02){
					alert('[不可缴费日期]结束日期需大于起始日期');
					return false;
				}
				submitValidate();
			}
		});
		
		$('.pay-date-input').keyup(function(){
			if($(this).val() !== ''){
				$('.pay-date-input').removeAttr('ignore');
			}else if($('.pay-date-input').eq(0).val() === '' && $('.pay-date-input').eq(1).val() === ''){
				$('.pay-date-input').attr('ignore', 'ignore');
			}
		})
	});
	function submitValidate(){
		//如果居委会和街道办电话有录入，则需要验证电话格式
		var isPhone=/^(([0-9]{3,4}-)?[0-9]{7,8})|(((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|14[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7}))$/;
		if($("#street_tel").val()){
			if(isPhone.test($("#street_tel").val())==false){
		        alert('街道办电话号码格式不正确！');
		        return false;
		    }
		}
		if($("#neighbor_tel").val()){
			if(isPhone.test($("#neighbor_tel").val())==false){
		        alert('居委会电话号码格式不正确！');
		        return false;
		    }
		}
	}

	//选择缴费周期
	radioChange('.selectPaytimeType');
	radioChange('.selectPeriodPaytimeType');
	//物业软件切换
	selectOptionChange('.softwareSelectBox');
</script>
</body>


</html>
