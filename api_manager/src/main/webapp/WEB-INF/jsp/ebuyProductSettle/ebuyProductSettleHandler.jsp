<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>结算审核</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	</head>
	<body>
		<div class="info">
		    <h2 class="mtop20">结算审核 <input class="info-btn small-btn w100 mtop3 mleft10" type="button" value="导出账单详情" onclick="exportDetailList();"></h2> 
		    <div class="bs-example bgebeb mtop10">
		        <table class="info-list" border="0">
		          <tr>
		            <td><div class="f14 bold borderb">结算信息</div></td>
		          </tr>
		          <tr>
		            <td>
		            	<span class="red bold">结算金额：<fmt:formatNumber value="${revenueApply.totalAmount}" type="currency" pattern="0.00" maxFractionDigits="2"/>元</span><br>
		            	收款账户：${revenueApply.accountName}<br>
		            	收款账号：${revenueApply.bBankNo}<br>
		            	开户银行：${revenueApply.bBankName}${revenueApply.bankBranch}<br>
		            	联系方式：${revenueApply.bPhone}<br>
		            	备注：${revenueApply.applyNote}
		            </td>
		          </tr>
		        </table>
		    </div>
			<form class="inputform" method="post" action="${pageContext.request.contextPath}/ebuyProductSettle/revenueApplyHandler.html">
			<input type="hidden" name="revenueApplyId" value="${revenueApplyId}"/>
		    <h2 class="mtop10 red">商品审核</h2>
		    <div class="bs-example">
		        <table class="info-list" border="0">
		          <tr>
		            <td width="90" align="right"><span class="grey">审核结果：</span></td>
		            <td colspan="5">
		            	<select class="select_normal w131 select-check" name="auditStatus">
	                        <option value="3">通过</option>
	                        <option value="2">不通过</option>
                        </select>
                    </td>
		          </tr>
		          <tr class="dsn">
		            <td align="right"><span class="grey">原因：</span></td>
		            <td colspan="5"><textarea class="textareas txt02" name="handlerNote" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea></td>
		          </tr>
		        </table>
		    </div>
		    <div class="padb mtop10">
		    	<input id="submitAccountCheck" class="info-btn" type="submit" value="提 交" />
		    	<input class="info-btn mar-left15 info-btn01 mtop0" type="button" onclick="history.back()" value="返 回">
		    </div>
		    </form>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript">
		(function($){
		    //表单验证
			$(".inputform").Validform({
				btnSubmit:"#submitAccountCheck", 
				ignoreHidden:true,
				tiptype:3,
				callback:function(data){
					
				}
			});
		})(jQuery);
		
		function exportDetailList(){
			location.href = "${pageContext.request.contextPath}/ebuyProductSettle/exportRevenueDetailAdmin.html?revenueApplyId=${revenueApply.id}";
		}
	</script>
</html>
