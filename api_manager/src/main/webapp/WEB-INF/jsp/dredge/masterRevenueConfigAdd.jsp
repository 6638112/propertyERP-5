<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" target="_blank"/>
	<title>师傅收益配置-新增配置</title>
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
<form class="revenueEditForm" action="../dredge/configSave.json" enctype="multipart/form-data" method="post">
	<div class="info">
        <h2>新增配置</h2> 
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
        	<tr>
        		<td width="20%"><div class="list-name"><span class="red">*</span> 供应商：</div></td>
        		<td>
        		<div style="position:relative;">${dw.realName} <input id="companyId" type="hidden" name="userId" value="${dw.huaId}"/>
        		</div>
        		</td>
        	</tr>
			<tr>
			  <td width="20%"><div class="list-name"><span class="red">*</span> 收益项目：</div></td>
			  <td>维修收益</td>
			</tr>
			<tr>
			  <td width="20%"><div class="list-name"><span class="red">*</span> 计算方式：</div></td>
			  <td>
			  	  <input type="hidden" name="ruleType"/>
			      <select class="select_normal" id="ruleType">
			          <option value="1" <c:if test="${not empty dwrc and dwrc.ruleType == 1}"> selected='selected' </c:if> >按百分比</option>
			        <%--   <option value="2" <c:if test="${not empty dwrc and dwrc.ruleType == 2}"> selected='selected' </c:if> >按数量</option> --%>
			      </select>
			  </td>
			</tr>
			
			<tr id="platformValueTr">
			  <td width="20%"><div class="list-name"><span class="red">*</span>平台抽佣：</div></td>
			  <td><input type="text" class="input_text pp" name="platformValue" autocomplete="off" <c:if test="${not empty dwrc}"> value='${dwrc.platformValue }' </c:if> /></td>
			</tr>
        </table>
        <div class="mtop10 f14"><span class="red">*</span> <span class="bold">是否生效：</span> <input class="mtop3" name="activeStatus" value="1" type="radio" <c:if test="${not empty dwrc and dwrc.activeStatus == 1}"> checked="checked" </c:if> /> 是 
        <input class="mtop3 mleft20" name="activeStatus" value="2" <c:if test="${(empty dwrc) or (not empty dwrc and dwrc.activeStatus == 2)}"> checked="checked" </c:if> type="radio" datatype="*" nullmsg="请选择是否生效！"/> 否</div>
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
		},
		callback:function(data){
			$(".revenueEditForm").ajaxSubmit(function(data) {
				console.log(data);
				try {
					data = eval("("+data+")");
				} catch (e) {
					try {
						data = eval(data);
					} catch (e2) {}
				}
				if (data.status == '0000') {
					alert(data.message);
					//parent.layer.msg('操作成功', {shade: 0.3});
					//closeWin("aaa");
					//var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
					//parent.layer.close(index);
				} else {
					alert(data.message);
					//parent.layer.msg(data.message, {shade: 0.3});
					//$(".revenueEditForm").Validform().resetStatus();
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
	    		$("[name=platformValue]").after('<span memeda="percent">%</span>');
	    		demo.addRule([
	    	   		{
	    	   	    ele:"[name=platformValue]",
	    	   	    datatype:"/^\\d{1,2}(\\.\\d{1})?$/|/^100$/",
	    	   	    nullmsg:"不能为空！",
	    	   	    errormsg:"请输入0-100的数值，最多一位小数！"
	    	   	    }//此处不要加逗号，IE8不兼容
	           ]);
	    	}else if($("[id=ruleType]").val()=='2'){
	    		$("[memeda='percent']").remove();
	    		$("[name=platformValue]").after('<span memeda="percent">元/个</span>');
	    		demo.addRule([
		      		{
		      	    ele:"[name=platformValue]",
		      	    datatype:"/(?!^[0]{2,})(?!^0[1-9]+)(?!^0\\.[0]*$)^\\d{1,4}(\\.\\d{1,2})?$/",
		      	    nullmsg:"不能为空！",
		      	    errormsg:"数据格式不正确，最多两位小数！"
		      	    }//此处不要加逗号，IE8不兼容
	          ]);
	    	}
		}
		
		function optSelect(){
			$("[id='ruleType']").removeAttr("disabled");
		}

		$(document).on('change','[id=ruleType]',function (){
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

