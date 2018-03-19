<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>结算标记</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css"/>
</head>

<body>
<div class="info" style="margin-top: 0">
	<h2>物业费结算标记</h2>
	<form class="inputForm"  action="<%=basePath%>/revenueAmountJson/markHistory.json" method="post">
		<input type="hidden" name="companyId" value="${param.companyId}"/>
		<table class="mars info-list-02" style="margin-top:0 !important" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td><div class="list-name"><span class="red">*</span> 物业公司名称：</div> </td>
				<td>${param.companyName}</td> 
			</tr>
			<c:if test="${haveMonth==true}">
			<tr>
				<td><div class="list-name"><span class="red">*</span> 缴费月份：</div></td>
				<td>
					<input type="text" class="input_text w120" name="startMonth" type="text" onclick="setmonth(this)" readonly="readonly"/>
					至 
					<input type="text" class="input_text w120" name="endMonth" type="text" onclick="setmonth(this)" readonly="readonly"/>
					<!-- <input type="checkbox" name="isAll" value="true" onclick="checkClick();"/> -->
					<br/>
					<input type="button"onclick="setAllMonth();" class="weak-btn small-btn w150 mtop10" value="标记所有历史月份">
				</td>
			</tr>
			</c:if>
			<tr>
			   <td><div class="list-name"><span class="red"></span> 可选历史月份：</div></td>
			   <td align="left">
			   		<c:if test="${haveMonth==true}">
			   		<%-- <input type="text" name="minMonth" value="${minMonth}" readonly="readonly"/>
					至 <input type="text" name="maxMonth" value="${maxMonth}" readonly="readonly"/> --%>
					<span id="minMonth">${minMonth}</span>至 <span id="maxMonth">${maxMonth}</span>
			   		</c:if>
			  		<c:if test="${haveMonth==false}">暂无</c:if>
			   </td>
			</tr>
		</table>
		<c:if test="${haveMonth==true}">
			<input type="button" id="sumGoods" class="info-btn small-btn w100 mtop20" value="确定"/>
			<input type="button" class="weak-btn small-btn w100 mtop20" value="取消" onclick="closeCurr();"/>
		</c:if>
		<c:if test="${haveMonth==false}">
			<input type="button" class="weak-btn small-btn w100 mtop20" value="知道了" onclick="closeCurr();"/>
		</c:if>
	</form>
	<script type="text/javascript" src="js/DatePicker.js"></script>
</div>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/revenue/layer.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>

<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/revenue/moment.js"></script>


<script type="text/javascript">
function setAllMonth(){
	$("[name=startMonth]").val($("[id=minMonth]").text());
	$("[name=endMonth]").val($("[id=maxMonth]").text());
}
/* function setAllMonth(){
	var checked =  $("input[name='isAll']").is(':checked');
	$("input[name='isAll']").prop("checked",!checked);
	checkClick();
}
function checkClick(){
	var checkedRes =  $("input[name='isAll']").is(':checked');
	if(checkedRes==true){
		$("[name=startMonth]").val($("[name=minMonth]").val());
		$("[name=endMonth]").val($("[name=maxMonth]").val());
	}else{
		$("[name=startMonth]").val('');
		$("[name=endMonth]").val('');
	}
} */

function closeCurr(){
	var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
	parent.layer.close(index);
}
(function($){
    //表单验证
	$(".inputForm").Validform({
		btnSubmit:"#sumGoods",
		tiptype:4,
		dragonfly:true,
		ajaxPost:false,
		beforeSubmit:function(curform){
			$(".inputForm").attr('onsubmit','return false;');
		},
		callback:function(data){
			$(".inputForm").ajaxSubmit(function(data) {
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
					var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
					parent.layer.close(index);
				} else {
					parent.layer.msg(data.message, {shade: 0.3});
					$(".inputForm").Validform().resetStatus();
				}
			});
			//return false;
		}
	});
})(jQuery);
</script>
</body>
</html>
