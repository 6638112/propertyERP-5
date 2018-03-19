
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
    <form class="proptypeForm" action="<%=basePath%>/groupBuildingJson/saveGbConfig.json" method="post">
    	<input type="hidden" name="id" value="${entity.id}" />
    	<h2>物业费缴费配置</h2>
    	<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
    	<tr>
		    <td width="160" align="right">入款设置：</td>
		    <td>
				<select class="select_normal" name="isPayToPc" >
					<option value="0" <c:if test="${propertyCompanyThirdPayCfg.id == 1 }"> selected </c:if> >入款至解放区平台</option>
					<option value="1" <c:if test="${propertyCompanyThirdPayCfg.id != 1 }"> selected </c:if>>入款至物业公司</option>
				</select>
			</td>
		</tr>
    	<tr>
		    <td width="160" align="right">物业支付宝账号：</td>
		    <td>
				<div><c:if test="${propertyCompanyThirdPayCfg.id != 1}"> ${propertyCompanyThirdPayCfg.appid} </c:if></div>
			</td>
		</tr>
    	<tr>
		    <td width="160" align="right">是否开启银行托收：</td>
		    <td>
				<input class="mtop3 mleft20" name="openBankCollection" <c:if test="${entity.openBankcollection eq 1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启
			    <input class="mtop3" name="openBankCollection" <c:if test="${(empty entity.openBankcollection) or (entity.openBankcollection eq 0)}" >checked="checked" </c:if> type="radio" value="0" /> 关闭
			</td>
		</tr>
		<tr>
		    <td width="160" align="right">是否开启缴费权限：</td>
		    <td>
				<input class="mtop3 mleft20" id="openAllPayButton" name="propfeeCanpay" <c:if test="${entity.propfeeCanpay==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启
			    <input class="mtop3" id="closeAllPayButton" name="propfeeCanpay" <c:if test="${entity.propfeeCanpay==2 || entity.propfeeCanpay==null}" >checked="checked" </c:if> type="radio" value="2" /> 关闭
			</td>
		</tr>
		<tr>
		    <td width="160" align="right">是否开启缴费校验：</td>
		    <td>
				<input class="mtop3 mleft20 verificationStatus_open openPayStatus" name="verificationStatus" <c:if test="${entity.verificationStatus==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启
			    <input class="mtop3 verificationStatus_close closeAllPayButton" name="verificationStatus" <c:if test="${entity.verificationStatus==0 || entity.verificationStatus==null}" >checked="checked" </c:if> type="radio" value="0" /> 关闭
			</td>
		</tr>
		<tr>
		    <td width="160" align="right">是否开启随机立减：</td>
		    <td>
				<input class="mtop3 mleft20 openPayStatus" name="isPrefer" <c:if test="${entity.isPrefer==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启
			    <input class="mtop3 closeAllPayButton" name="isPrefer" <c:if test="${entity.isPrefer==0 || entity.isPrefer==null}" >checked="checked" </c:if> type="radio" value="0" /> 关闭
			</td>
		</tr>
		<tr>
		    <td width="160" align="right">常规收费模式权限：</td>
		    <td>
				<input class="mtop3 mleft20 openAllPayButton openPayStatus" name="fixedFeeStatus" <c:if test="${entity.fixedFeeStatus==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启
			    <input class="mtop3 closeAllPayButton" name="fixedFeeStatus" <c:if test="${entity.fixedFeeStatus==0 || entity.fixedFeeStatus==null}" >checked="checked" </c:if> type="radio" value="0" /> 关闭
			</td>
		</tr>
		<tr>
			<td width="160" align="right">抄表收费模式权限：</td>
			<td>
				<input class="mtop3 mleft20 openAllPayButton openPayStatus" name="meterFeeStatus" <c:if test="${entity.meterFeeStatus==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启
				<input class="mtop3 closeAllPayButton" name="meterFeeStatus" <c:if test="${entity.meterFeeStatus==0 || entity.meterFeeStatus==null}" >checked="checked" </c:if> type="radio" value="0" /> 关闭
			</td>
		</tr>
		<tr>
			<td width="160" align="right">临时收费模式权限：</td>
			<td>
				<input class="mtop3 mleft20 openAllPayButton openPayStatus" name="tempFeeStatus" <c:if test="${entity.tempFeeStatus==1 }" >checked="checked" </c:if> type="radio" value="1" /> 开启
				<input class="mtop3 closeAllPayButton" name="tempFeeStatus" <c:if test="${entity.tempFeeStatus==0 || entity.tempFeeStatus==null}" >checked="checked" </c:if> type="radio" value="0" /> 关闭
			</td>
		</tr>
		<tr>
			<td width="160" align="right">选择周期缴费模式权限：</td>
			<td>
				<input class="mtop3 mleft20 openAllPayButton openPayStatus" name="propertyPeriodType" <c:if test="${entity.propertyPeriodType==2 }" >checked="checked" </c:if> type="radio" value="2" /> 开启
				<input class="mtop3 closeAllPayButton" name="propertyPeriodType" <c:if test="${entity.propertyPeriodType==1 || entity.propertyPeriodType==null}" >checked="checked" </c:if> type="radio" value="1" /> 关闭
			</td>
		</tr>
    	</table>
    	<div class="padb"><input class="info-btn w100 proptypeBtn" type="button" value="保 存" /></div>
    </form>
    </div>
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
				var isOpenPayChild = false;
				var isCanSubmit = false;

				if($("#closeAllPayButton").is(":checked")) {
					$(".openPayStatus").each(function () {
						if($(this).is(":checked")) {
							isCanSubmit = true;
						}
					});
					if(isCanSubmit) {
						alert("请开启缴费模式！");
						return false;
					}
				}
				if($("#openAllPayButton").is(":checked")) {
					$(".openAllPayButton").each(function () {
						if($(this).is(":checked")) {
							isOpenPayChild = true;
						}
					});
					if(!isOpenPayChild) {
						alert("至少开启一种收费模式！");
						return false;
					}
				}

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

	(function($){
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
	//物业软件切换
	selectOptionChange('.softwareSelectBox');

	//关闭收费开关
	$("#closeAllPayButton").click(function () {
		$(".closeAllPayButton").prop("checked",true);
	});

	$("select[name='isPayToPc']").change(function () {
		$(this).parents("tr").next().toggle();
    });

</script>
</body>


</html>
