<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>申请提款</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css"/>
</head>

<body>
<div class="info" style="margin-top: 0">
    	<form class="inputForm"  action="<%=basePath%>${formUrl}" method="post">
    		<input type="hidden" name="miniRoleId" value="${applyData.miniRoleId}" />
    		<input type="hidden" name="goalType" value="${applyData.goalType}" />
    		<input type="hidden" name="miniRoleType" value="${applyData.miniRoleType}"/>
    		<input type="hidden" name="goalStartTime" value="${applyData.goalStartTime}"/>
    		<input type="hidden" name="goalEndTime" value="${applyData.goalEndTime}"/>
    		<input type="hidden" name="totalAmount" value="${applyData.totalAmount}"/>
	        <table class="mars info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
	          <tr>
	            <td>结算区间</td>
	            <td>
	            	<c:choose>
	            		<c:when test="${empty applyData.goalStartTime}">${applyData.goalEndTime}之前</c:when>
	            		<c:otherwise>${applyData.goalStartTime}至${applyData.goalEndTime}</c:otherwise>
	            	</c:choose>
	            </td>
	          </tr>
	          <tr>
	            <td>结算金额</td>
	            <td>${applyData.totalAmount}元</td>
	          </tr>
	          <tr>
	            <td colspan="2">
	            	<input type="button" id="sumGoods" class="info-btn save-set-prize-info-btn" value="确定"/>
	            	<input type="button" class="info-btn bnLastStep" value="取消" onclick="closeCurr();"/>
	            </td>
	          </tr>
	        </table>
        </form>
</div>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/revenue/layer.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>

<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/revenue/moment.js"></script>

<script type="text/javascript">
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
			if(${applyData.totalAmount} < 0.01){
				alert('可结算金额小于0.01元，不可提款');
				return false;
			}
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
					//closeWin("aaa");
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
