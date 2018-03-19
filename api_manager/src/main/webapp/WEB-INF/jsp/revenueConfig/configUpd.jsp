<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" target="_blank"/>
	<title>物业管理-修改配置</title>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css?v1" />
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css?v=1"/>
	<style type="text/css">
		html{
			height: auto;
		}
	</style>
</head>

<body>
<form class="revenueEditForm" action="<%=basePath%>/revenueConfigJson/doConfigUpd.json" enctype="multipart/form-data" method="post">
	<div class="info">
        <h2>修改配置</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
        	<tr>
        		<td width="20%"><div class="list-name"><span class="red">*</span> 物业公司：</div></td>
        		<td><input type="hidden" name="dataId" value="${detail.id}"/>${detail.companyName}</td>
        	</tr>
			<tr>
			  <td width="20%"><div class="list-name"><span class="red">*</span> 收益项目：</div></td>
			  <td>
			  	  <input type="hidden" id="projectType" name="projectType" value="${detail.projectType }"/>
			      <select class="select_normal" name="projectType1" disabled="disabled">
		          	<option value="1" <c:if test="${detail.projectType==1}">selected="selected"</c:if> >认证门牌</option>
					<option value="2" <c:if test="${detail.projectType==2}">selected="selected"</c:if> >服务类订单</option>
					<option value="3" <c:if test="${detail.projectType==3}">selected="selected"</c:if> >物业宝佣金</option>
					<option value="9" <c:if test="${detail.projectType==9}">selected="selected"</c:if> >停车宝佣金</option>
					<option value="4" <c:if test="${detail.projectType==4}">selected="selected"</c:if> >超市收益</option>
					<%-- <option value="5" <c:if test="${detail.projectType==5}">selected="selected"</c:if> >物业费实收</option> --%>
			      </select>
			  </td>
			</tr>
			<tr>
			  <td width="20%"><div class="list-name"><span class="red">*</span> 收益结算方式：</div></td>
			  <td>
			  	  <input type="hidden" name="ruleType" value="${detail.ruleType }"/>
			      <select class="select_normal" id="ruleType">
			          <option value="1" <c:if test="${detail.ruleType==1}">selected="selected"</c:if>>按百分比</option>
			          <option value="2" <c:if test="${detail.ruleType==2}">selected="selected"</c:if>>按数量</option>
			      </select>
			  </td>
			</tr>
			
			<c:choose>
				<c:when test="${detail.ruleType==1}">
				<tr>
				  <td width="20%"><div class="list-name"><span class="red">*</span>物业收益：</div></td>
				  <td><input autocomplete="off" type="text" value="<fmt:formatNumber value="${detail.companyValue}" pattern="#.##"/>" class="input_text pp" name="companyValue"/></td>
				</tr>
				<tr id="agentValueTr">
				  <td width="20%"><div class="list-name"><span class="red">*</span>代理收益：</div></td>
				  <td><input autocomplete="off" type="text" value="<fmt:formatNumber value="${detail.agentValue}" pattern="#.##"/>" class="input_text pp" name="agentValue"/></td>
				</tr>
				<tr id="platformValueTr">
				  <td width="20%"><div class="list-name"><span class="red">*</span>平台收益：</div></td>
				  <td><input autocomplete="off" type="text" value="<fmt:formatNumber value="${detail.platformValue}" pattern="#.##"/>" class="input_text pp" name="platformValue"/></td>
				</tr>
				<tr id="recommenderTr">
				  <td width="20%"><div class="list-name"><span class="red">*</span>推荐人收益：</div></td>
				  <td><input autocomplete="off" type="text" value="<fmt:formatNumber value="${detail.recommenderValue}" pattern="#.##"/>" class="input_text pp" name="recommenderValue"/></td>
				</tr>
				
				</c:when>
				<c:otherwise><!-- 2 -->
				<tr>
				  <td width="20%"><div class="list-name"><span class="red">*</span>物业收益：</div></td>
				  <td><input autocomplete="off" type="text" value="${detail.companyValue}" class="input_text pp" name="companyValue"/></td>
				</tr>
				<tr id="agentValueTr">
				  <td width="20%"><div class="list-name"><span class="red">*</span>代理收益：</div></td>
				  <td><input autocomplete="off" type="text" value="${detail.agentValue}" class="input_text pp" name="agentValue"/></td>
				</tr>
				<tr id="platformValueTr">
				  <td width="20%"><div class="list-name"><span class="red">*</span>平台收益：</div></td>
				  <td><input autocomplete="off" type="text" value="${detail.platformValue}" class="input_text pp" name="platformValue"/></td>
				</tr>
				<tr id="recommenderTr">
				  <td width="20%"><div class="list-name"><span class="red">*</span>推荐人收益：</div></td>
				  <td><input autocomplete="off" type="text" value="${detail.recommenderValue}" class="input_text pp" name="recommenderValue"/></td>
				</tr>
				</c:otherwise>
			</c:choose>
			<tr>
			  <td><div class="list-name"><span class="red">*</span> 规则起止时间：</div></td>
			  <td>
			  	<input type="text" name="startDate" autocomplete="off" value="${fn:substring(detail.startDate, 0, 10)}" class="input_text icon_dt pp " id="startDate" placeholder="请选择起始时间" datatype="*" nullmsg="请选择起始时间！" />
			  	&nbsp;至&nbsp;&nbsp;&nbsp;
			  	<input type="text" name="endDate" autocomplete="off" value="${fn:substring(detail.endDate, 0, 10)}" class="input_text icon_dt pp" id="endDate" placeholder="请选择截止时间" datatype="*" nullmsg="请选择截止时间！" />
			  	<div>
				  	<%-- <span>最近使用时间：${fn:substring(detail.usedRecLasttime, 0, 10)}&nbsp;&nbsp;</span> --%>
			  		<span>可选起始时间【${fn:substring(leftTime.start, 0, 10)}~${fn:substring(leftTime.end, 0, 10)}】;可选截止时间【${fn:substring(rightTime.start, 0, 10)}~${fn:substring(rightTime.end, 0, 10)}】.</span> 
			  	</div>
			  </td>
			</tr>
        </table>
        <div class="mtop40 f14"><span class="red">*</span> <span class="bold">是否生效：</span>
	        <input class="mtop3" name="activeStatus" value="1" <c:if test="${detail.activeStatus==1}">checked="checked"</c:if> type="radio"/> 是 
	        <input class="mtop3 mleft20" name="activeStatus" value="2" <c:if test="${detail.activeStatus==2}">checked="checked"</c:if> type="radio" datatype="*" nullmsg="请选择是否生效！"/> 否
        </div>
        <input id="sumGoods" class="info-btn" type="submit" value="保存" />
	</div>
</form>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js?v=1"></script>
<script type="text/javascript" src="js/revenue/layer.js?v2"></script>
<script type="text/javascript" src="js/jquery.common.js?v2015091802"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/revenue/revenueConfig.js?v=12"></script>
<script type="text/javascript" src="js/revenue/moment.js"></script>
<script type="text/javascript">
function checkIsIe8(){
	var isIE=!!window.ActiveXObject;
	var isIE6=isIE&&!window.XMLHttpRequest;
	var isIE8=isIE&&!!document.documentMode;
	var isIE7=isIE&&!isIE6&&!isIE8;
	if (isIE || isIE6 || isIE8 || isIE7){
		return true;
	}else{
		return false;
	}
}

function checkDate(obj){
	var date01 = $('[id=startDate]').val();
	var date02 = $('[id=endDate]').val();
	if(date01 != '' && date02 != ''){
		if(moment(date01).diff(moment(date02))>0){
			if(obj==null){alert('开始时间须小于截止时间！');}
			if (checkIsIe8()){
				//$(obj).val('');
			}else{//ie8及以下不支持
				alert('开始时间须小于截止时间！');
				$('[id=startDate]').handleDtpicker('setDate', new Date(start_maxDate));
				$('[id=endDate]').handleDtpicker('setDate', new Date(end_minDate));
				if($(obj).val() == date01){
					$(obj).handleDtpicker('setDate', new Date(date02));val(date02);
				}else if($(obj).val() == date02){
					$(obj).handleDtpicker('setDate', new Date(date01));
				}else{
					$(obj).val('');
				}
			}
			return false;
		}
		
	}
	return true;
}

(function($){
    //表单验证
	$(".revenueEditForm").Validform({
		btnSubmit:"#sumGoods",
		tiptype:3,
		dragonfly:true,
		ajaxPost:false,
		beforeSubmit:function(curform){
			$(".revenueEditForm").attr('onsubmit','return false;');
			//if(!checkDate(null)){return false;}
		},
		callback:function(data){
			$(".revenueEditForm").ajaxSubmit(function(data) {
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
					parent.layer.msg('操作成功', {shade: 0.3});
					//closeWin("aaa");
					var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
					parent.layer.close(index);
				} else {
					parent.layer.msg(data.message, {shade: 0.3});
					$(".revenueEditForm").Validform().resetStatus();
				}
			});
			//return false;
		}
	});
})(jQuery);


(function($){
	$('input[id=startDate]').appendDtpicker({
		"closeOnSelected":true,
		"locale":"cn",
		"autodateOnStart":false,
		"dateOnly": true,
		"dateFormat": "YYYY-MM-DD",
		"todayButton": false,
		//"minDate":"${fn:substring(leftTime.start, 0, 10)}",
		//"maxDate":"${fn:substring(leftTime.end, 0, 10)}",
	});
	$('input[id=endDate]').appendDtpicker({
		"closeOnSelected":true,
		"locale":"cn",
		"autodateOnStart":false,
		"dateOnly": true,
		"dateFormat": "YYYY-MM-DD",
		"todayButton": false,
		//"minDate":"${fn:substring(rightTime.start, 0, 10)}",
		//"maxDate":"${fn:substring(rightTime.end, 0, 10)}",
	});
	/* $("[id=startDate]").keyup(function (){
		checkDate(this);
	});
	$("[id=endDate]").keyup(function (){
		checkDate(this);
	}); */
})(jQuery);


(function($){
		var demo = $(".revenueEditForm").Validform();
		function beautyOption(){
	    	if($("[id=ruleType]").val()=='1'){
	    		$("[memeda='percent']").remove();
	    		$("[name=companyValue],[name=agentValue],[name=platformValue],[name=recommenderValue]").after('<span memeda="percent">%</span>');
	    		demo.addRule([
	    	   		{
	    	   	    ele:"[name=companyValue],[name=agentValue],[name=platformValue]",
	    	   	 	datatype:"/^\\d{1,2}(\\.\\d{1})?$/|/^100$/",
	    	   	    nullmsg:"不能为空！",
	    	   	    errormsg:"请输入0-100的数值，最多一位小数！"
	    	   	    }
	           ]);
	    	}else if($("[id=ruleType]").val()=='2'){
	    		$("[memeda='percent']").remove();
	    		$("[name=companyValue],[name=agentValue],[name=platformValue],[name=recommenderValue]").after('<span memeda="percent">元/个</span>');
	    		demo.addRule([
		      		{
		      	    ele:"[name=companyValue],[name=agentValue],[name=platformValue],[name=recommenderValue]",
		      	    datatype:"/(?!^[0]{2,})(?!^0[1-9]+)^\\d{1,4}(\\.\\d{1,2})?$/",
		      	    //datatype:"/^\\d{1,4}(\\.\\d{1,2})?$/",
		      	    nullmsg:"不能为空！",
		      	    errormsg:"数据格式不正确，最多两位小数！"
		      	    }
	          ]);
	    	}
		}
		
		function optSelect(){
			$("[id='ruleType']").removeAttr("disabled");
			if($("[name=projectType]").val()=='3'||$("[name=projectType]").val()=='9'|| $("[name=projectType]").val()=='4'){
				$("[id='ruleType']").val("1");
				$("[id='ruleType']").attr("disabled","disabled");
				//
				$("[name='ruleType']").val($("[id='ruleType']").val());
				beautyOption();
			}else if($("[name=projectType]").val()=='1'){
				$("[id='ruleType']").val("2");
				$("[id='ruleType']").attr("disabled","disabled");
				//
				$("[name='ruleType']").val($("[id='ruleType']").val());
				beautyOption();
			}
			
			if($("[name=projectType]").val()=='2' ){
				$("#recommenderTr").insertAfter('#platformValueTr').show();
			} else {
				$("#recommenderTr").hide().appendTo('body');
			}
			
			if($("[name=projectType]").val()=='4'){
				$("#platformValueTr").hide().appendTo('body');
			} else {
				$("#platformValueTr").insertAfter('#agentValueTr').show();
			}
		}
		$(document).on('change','[name=projectType1]',function (){
			optSelect();
		});
		$(document).on('change','[id=ruleType]',function (){
			//
			$("[name='ruleType']").val($("[id='ruleType']").val());
			beautyOption();
		});
		//初始操作
		optSelect();
		$("[name='ruleType']").val($("[id='ruleType']").val());
		beautyOption();
})(jQuery);
</script>
</body>
</html>

