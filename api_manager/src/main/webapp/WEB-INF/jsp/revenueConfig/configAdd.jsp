<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" target="_blank"/>
	<title>物业管理-新增配置</title>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css?v1" />
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css?v=1"/>
	<style type="text/css">
		html{
			height: auto;
		}
	</style>
</head>

<body <c:if test="${!empty param.companyName and empty sessionScope.companyId}"> onload="qryPropCompList('${param.companyName}');" </c:if> >
<form class="revenueEditForm" action="<%=basePath%>/revenueConfigJson/doConfigAdd.json" enctype="multipart/form-data" method="post">
	<div class="info">
        <h2>新增配置</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
        	<tr>
        		<td width="20%"><div class="list-name"><span class="red">*</span> 物业公司：</div></td>
        		<%-- <c:if test="${!empty sessionScope.companyId}">
	        		<td>${sessionScope.companyName}</td>
        		</c:if>
        		<c:if test="${empty sessionScope.companyId}">
        			<td>
        			<div style="position:relative;">
	        			<input id="companyName" type="text" class="input_text pp" value="" datatype="*" nullmsg="物业公司名称不能为空" placeholder="请输入物业公司名称" autocomplete="off" onkeyup="qryPropCompList($(this).val());" onclick="qryPropCompList($(this).val());" onmouseout="$('#companyName').val($('#companyId').attr('vv'));"/>
	        			<input id="companyId" type="text" style="width:0;height:0" value="" vv="" name="companyId" datatype="*" nullmsg="物业公司名称有误"/>
	        			<div id="companyNameQryList"/>
	        		</div>
        			</td>
        		</c:if> --%>
        		<td>
        		<div style="position:relative;">
        			${companyName}
        			<input id="companyId" type="hidden" name="companyId" value="${companyId}"/>
        		</div>
        		</td>
        	</tr>
			<tr>
			  <td width="20%"><div class="list-name"><span class="red">*</span> 收益项目：</div></td>
			  <td>
			      <select class="select_normal" name="projectType">
		          	<option value="1" <c:if test="${param.projectType==1 or empty param.projectType}">selected="selected"</c:if> >认证门牌</option>
					<option value="2" <c:if test="${param.projectType==2}">selected="selected"</c:if> >维修收益</option>
					<option value="3" <c:if test="${param.projectType==3}">selected="selected"</c:if> >物业宝佣金</option>
					<option value="9" <c:if test="${param.projectType==9}">selected="selected"</c:if> >停车宝佣金</option>
					<option value="4" <c:if test="${param.projectType==4}">selected="selected"</c:if> >超市收益</option>
					<%-- <option value="5" <c:if test="${param.projectType==5}">selected="selected"</c:if> >物业费实收</option> --%>
			      </select>
			  </td>
			</tr>
			<tr>
			  <td width="20%"><div class="list-name"><span class="red">*</span> 计算方式：</div></td>
			  <td>
			  	  <input type="hidden" name="ruleType"/>
			      <select class="select_normal" id="ruleType">
			          <option value="1">按百分比</option>
			          <option value="2">按数量</option>
			      </select>
			  </td>
			</tr>
			<tr>
			  <td width="20%"><div class="list-name"><span class="red">*</span>物业收益：</div></td>
			  <td><input type="text" value="" class="input_text pp" name="companyValue" autocomplete="off"/></td>
			</tr>
			<tr id="agentValueTr">
			  <td width="20%"><div class="list-name"><span class="red">*</span>代理收益：</div></td>
			  <td><input type="text" value="" class="input_text pp" name="agentValue" autocomplete="off"/></td>
			</tr>
			<tr id="platformValueTr">
			  <td width="20%"><div class="list-name"><span class="red">*</span>平台收益：</div></td>
			  <td><input type="text" value="" class="input_text pp" name="platformValue" autocomplete="off"/></td>
			</tr>
			<tr id="recommenderTr">
			  <td width="20%"><div class="list-name"><span class="red">*</span>推荐人收益：</div></td>
			  <td><input id="recommendInput" type="text" value="" class="input_text pp" name="recommenderValue" autocomplete="off"/></td>
			</tr>
			<tr>
			  <td><div class="list-name"><span class="red">*</span> 规则起止时间：</div></td>
			  <td>
			  	<div id="dataSelectTool"><!-- 初始状态数据 -->
				  	<input type="text" id="startDate" autocomplete="off" name="startDate" class="input_text icon_dt" placeholder="请选择起始时间" datatype="*" nullmsg="请选择起始时间！" />&nbsp;至&nbsp;&nbsp;&nbsp;<input type="text" id="endDate" autocomplete="off" name="endDate" class="input_text icon_dt" placeholder="请选择截止时间" datatype="*" nullmsg="请选择截止时间！" />
			  	</div>
			  	<div id="dataSelectDesc"></div>
			  	<span></span>
			  </td>
			</tr>
        </table>
        <div class="mtop10 f14"><span class="red">*</span> <span class="bold">是否生效：</span> <input class="mtop3" name="activeStatus" value="1" type="radio"/> 是 <input class="mtop3 mleft20" name="activeStatus" value="2" checked="checked" type="radio" datatype="*" nullmsg="请选择是否生效！"/> 否</div>
        <input id="sumGoods" class="info-btn" type="submit" value="新增" />
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
(function($){
	//1.更新起止时间
	$(document).on('change','[name=projectType]',function (){
		qryMinMaxDate();
	});
	//2.创建日期对象
	//3.日期选择时,交互及校验
	//4...focus();
	$(document).on('click','[id=startDate],[id=endDate]',function (){
		if ($("[id=companyId]").val().replace(/(^s*)|(s*$)/g,"").length ==0){
			$("[id=companyName]").focus();
		}
		//alert($(".revenueEditForm").validate().element("[id=companyName]"));//.check();//TODO
	});
	qryMinMaxDate();
})(jQuery);


var minStartDate = "";
var maxEndDate = "";
function checkDate(obj){
	var date01 = $('[id=startDate]').val();
	var date02 = $('[id=endDate]').val();
	//if(date01 != '' && date02 != '' && date01 > date02){
	if(date01 != '' && date02 != ''){
		/* if(obj==null){alert('开始时间须小于截止时间！');}
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
		} */
		
		if(date01 > date02){
			alert('开始时间须小于截止时间');
			return false;
		}
		if(date01 < minStartDate && date02 > minStartDate){
			alert('请选择' + minStartDate + '~' + maxEndDate + '之外的时间');
			return false;
		}
		if(date02 > maxEndDate && date01 < maxEndDate){
			alert('请选择' + minStartDate + '~' + maxEndDate + '之外的时间');
			return false;
		}
		
	}
	return true;
}

function qryMinMaxDate(){
	var companyId = $("[name=companyId]").val();
	var projectType = $("[name=projectType]").val();
	if(companyId!=null && projectType!=null){
		$.ajax({
			type:"post",
			url:"revenueConfigJson/qryMinMaxDate.json",
			data:{'companyId':companyId,'projectType': projectType},
			dataType:"TEXT",
			success:function(data){
				try {
					data = eval("("+data+")");
				} catch (e) {
					try {
						data = eval(data);
					} catch (e2) {}
				}
				if (data.status == '0000') {
					$("#dataSelectDesc").html("");
					var dataValue = data.dataValue;
					if(dataValue!=null){
						minStartDate = dataValue.minStartDate;
						maxEndDate = dataValue.maxEndDate;
					}
					var start_maxDate = "";
					var end_minDate = "";
					if(dataValue!=null){
						start_maxDate = moment(minStartDate).subtract(1, 'days').format('YYYY-MM-DD');
						end_minDate = moment(maxEndDate).add(1, 'days').format('YYYY-MM-DD');
					}
					
					$("#dataSelectTool").html("");
					$("#dataSelectTool").append("<input type=\"text\" id=\"startDate\" autocomplete=\"off\" name=\"startDate\" value=\"\" class=\"input_text icon_dt\" placeholder=\"请选择起始时间\" datatype=\"*\" nullmsg=\"请选择起始时间！\" />&nbsp;至&nbsp;&nbsp;&nbsp;<input type=\"text\" id=\"endDate\"  autocomplete=\"off\" name=\"endDate\" value=\"\" class=\"input_text icon_dt\" placeholder=\"请选择截止时间\" datatype=\"*\" nullmsg=\"请选择截止时间！\" />");
					if(dataValue!=null){
						$("#dataSelectDesc").append("请选择【<span id=\"minStartDate\">"+minStartDate+"</span>~<span id=\"maxEndDate\">"+maxEndDate+"</span>】之外的时间.");
					}
					
					//2.创建日期对象
					$('input[id=startDate]').appendDtpicker({
						"closeOnSelected":false,
						"locale":"cn",
						"autodateOnStart":false,
						"dateOnly": true,
						"dateFormat": "YYYY-MM-DD",
						"todayButton": false,
						//"maxDate":start_maxDate,
					});
					$('input[id=endDate]').appendDtpicker({
						"closeOnSelected":true,
						"locale":"cn",
						"autodateOnStart":false,
						"dateOnly": true,
						"dateFormat": "YYYY-MM-DD",
						"todayButton": false,
						//"minDate":end_minDate,
					});
					/* if(dataValue!=null){
						if (checkIsIe8()){}else{//ie8及以下不支持
							$('[id=startDate]').handleDtpicker('setDate', new Date(start_maxDate));
							$('[id=endDate]').handleDtpicker('setDate', new Date(end_minDate));
						}
					} */
					/* var htmlS = $("#minStartDate").html();
					if(htmlS!=undefined){
						$('[id=startDate]').handleDtpicker('setDate', new Date(htmlS));
					}
					var htmlE = $("#maxEndDate").html();
					if(htmlE!=undefined){
						$('[id=endDate]').handleDtpicker('setDate', new Date(htmlE));
					} */
					//3.日期选择时,交互及校验//.focus();
					(function($){
						$("[id=startDate]").keyup(function (){
							$("[id=startDate]").attr("name","startDate");
							var html = $("#minStartDate").html();
							if(html!=undefined){
								var tmpDay = moment(html).subtract(1, 'days').format('YYYY-MM-DD');
								//$("[id=endDate]").val(tmpDay);
								$("[id=endDate]").attr("name","");
							}
							//checkDate(this);
						});
						$(document).on('keyup','[id=endDate]',function (){
							$("[id=endDate]").attr("name","endDate");
							var html = $("#maxEndDate").html();
							if(html!=undefined){
								var tmpDay = moment(html).add(1, 'days').format('YYYY-MM-DD');
								//$("[id=startDate]").val(tmpDay);
								$("[id=startDate]").attr("name","");
							}
							//checkDate(this);
						});
						$("[id=startDate]").val("");
						$("[id=endDate]").val("");
					})(jQuery);
					//4...
					/* $(document).on('click','[id=startDate],[id=endDate]',function (){
						//alert($(".revenueEditForm").validate().element("[name=companyId]"));//.check();//TODO
					}); */
				} else {
					alert(data.message);
					return;
				}
			}
		});
	}
}

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
	
function qryPropCompList(obj){
	/* $.ajax({
		type:"post",
		url:"revenueConfigJson/qryPropCompanyList.json",
		data:{'companyName': obj},
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
				$("#companyNameQryList").find("ul").remove();
				var arrList = data.dataValue.list;
				for(var i=0;i<arrList.length;i++){
					var currVal = arrList[i];
					$("#companyNameQryList").append("<ul><li><input type=\"hidden\" value=\""+currVal.id+"\" /></li><li onclick=\"$('#companyId').val($(this).prev().find('input').val());$('#companyId').attr('vv',$(this).html());$('#companyName').val($(this).html());$('#companyNameQryList').attr('style','display:none'); qryMinMaxDate(); \">"+currVal.name+"</li></ul>");
				}
				 $('#companyNameQryList').attr('style','position:absolute;float:top;width:210px;background-color:white;border:1px #f5f5f5 solid;display:visibility;overflow:auto;overflow-x:hidden;max-height:310px;');//maxHeight:150px;
			} else {
				alert(data.message);
				return;
			}
		}
	}); */
}

function alertInfo(info,callBack){//TODO ?如何调用函数
	callBack();
	/* var f = callBack();
	layer.alert(info, {
	    shade: 0.1,
	    closeBtn: 0
	},f); */
	alert(info);
}
(function($){
    //表单验证
	$(".revenueEditForm").Validform({
		btnSubmit:"#sumGoods",
		tiptype:4,
		dragonfly:true,
		ajaxPost:false,
		ignoreHidden:true,
		beforeSubmit:function(curform){
			$(".revenueEditForm").attr('onsubmit','return false;');
			if(!checkDate(null)){return false;}
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
		var demo = $(".revenueEditForm").Validform();
		function beautyOption(){
	    	if($("[id=ruleType]").val()=='1'){
	    		$("[memeda='percent']").remove();
	    		$("[name=companyValue],[name=agentValue],[name=recommenderValue],[name=platformValue]").after('<span memeda="percent">%</span>');
	    		demo.addRule([
	    	   		{
	    	   	    ele:"[name=companyValue],[name=agentValue],[name=recommenderValue],[name=platformValue]",
	    	   	    datatype:"/^\\d{1,2}(\\.\\d{1})?$/|/^100$/",
	    	   	 	//datatype:"n1-2|/^100$/",
	    	   	    //datatype:"/^\\d{1,4}(\\.\\d{1,2})?$/",
	    	   	    nullmsg:"不能为空！",
	    	   	    errormsg:"请输入0-100的数值，最多一位小数！"
	    	   	    }//此处不要加逗号，IE8不兼容
	           ]);
	    	}else if($("[id=ruleType]").val()=='2'){
	    		$("[memeda='percent']").remove();
	    		$("[name=companyValue],[name=agentValue],[name=recommenderValue],[name=platformValue]").after('<span memeda="percent">元/个</span>');
	    		demo.addRule([
		      		{
		      	    ele:"[name=companyValue],[name=agentValue],[name=recommenderValue],[name=platformValue]",
		      	    datatype:"/(?!^[0]{2,})(?!^0[1-9]+)(?!^0\\.[0]*$)^\\d{1,4}(\\.\\d{1,2})?$/",
		      	    //datatype:"/^\\d{1,4}(\\.\\d{1,2})?$/",
		      	    nullmsg:"不能为空！",
		      	    errormsg:"数据格式不正确，最多两位小数！"
		      	    }//此处不要加逗号，IE8不兼容
	          ]);
	    	}
	    	
		}
		
		function optSelect(){
			$("[id='ruleType']").removeAttr("disabled");
			
			if($("[name=projectType]").val()=='3' || $("[name=projectType]").val()=='9' || $("[name=projectType]").val()=='4'){
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
			
			if($("[name=projectType]").val()=='2' || $("[name=projectType]").val()=='3'){
				$("#platformValueTr").insertAfter('#agentValueTr').show();
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
		$(document).on('change','[name=projectType]',function (){
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

