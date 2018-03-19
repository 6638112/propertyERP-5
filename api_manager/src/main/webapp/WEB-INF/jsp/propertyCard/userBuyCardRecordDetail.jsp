<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="com.cnfantasia.server.ms.pub.session.UserContext" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-物业代扣卡购买列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
</head>

<body>
<div class="info">
   	<div class="info">
        <h2>账户详情 </h2>
   		  <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="145" align="right"><span class="grey">购买解放号：</span></td>
            <td> <c:out value="${userHasPropertyCard.tUserFId }"></c:out></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">累计购买金额：</span></td>
            <td><fmt:formatNumber type="number"  pattern="0.00" value="${userHasPropertyCard.cardAmount/100}" maxFractionDigits="2"/></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">累计优惠金额：</span></td>
            <td><fmt:formatNumber type="number"  pattern="0.00" value="${userHasPropertyCard.discountAmt/100 }" maxFractionDigits="2"/></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">累计实付金额：</span></td>
            <td><fmt:formatNumber type="currency" pattern="0.00"  value="${userHasPropertyCard.realPayAmt/100 }" /></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">剩余金额：</span></td>
            <td><fmt:formatNumber type="number"  pattern="0.00" value="${userHasPropertyCard.balanceAmt/100 }" /></td>
          </tr>
        </table>
     </div>
     <div class="info">
        <h2>划扣配置</h2>
        <form class="proptypeCardForm" action="<%=basePath%>/propertyCard/editPropCardOpenStatus.json" method="post">
    	 <input name="userId" value="${userHasPropertyCard.tUserFId }" type="hidden"/>
    	 <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
	          <tr>
	            <td align="right"><span class="grey">划扣小区：</span></td>
	            <td>
	            	<c:forEach items="${rooms}" var="room" varStatus="i">
	           	 		<input type="checkbox" name="configs" id="ck${i.index}" <c:if test="${room.isOpenService==1 }" >checked="checked"</c:if> style="cursor: pointer;"   value="${room.configId}_${room.realRoomId}"/>
	           	 		<label style="cursor: pointer;" for="ck${i.index}">${room.roomDesciption }</label>&nbsp;&nbsp;
	           	 		<c:if test="${i.index%5 ==0 && i.index > 0}"></br></c:if>
	            	</c:forEach>
	            </td>
	            <td>
	            	<input class="input-btn w80 subBtn" type="button" value="保存" />
	            </td>
	          </tr>
        </table>
        </form>
    </div>
     <div class="info">
	    <h2>购买记录</h2>
	    <display:table name="resList" uid="row" id="row" class="mars info-list-02 mtop20" pagesize="10" requestURI="" partialList="true" size="resultSize" >
			<%--
			<display:column title="序号" sortable="true" headerClass="sortable">
			<c:out value="${row_rowNum}"/></display:column> --%>
			<display:column title="购买金额">
				<fmt:formatNumber type="number"  pattern="0.00" value="${row.cardAmount/100 }" maxFractionDigits="2"/>
			</display:column>
			<display:column title="优惠金额">
				<fmt:formatNumber type="number"  pattern="0.00" value="${row.discountAmt/100 }" maxFractionDigits="2"/>
			</display:column>
			<display:column title="实付金额">
				<fmt:formatNumber type="number"  pattern="0.00" value="${row.realPayAmt/100 }" maxFractionDigits="2"/>
			</display:column>
			<display:column title="购买时间" property="buyTime"/>
		</display:table>
	</div>
</div>
</body>

<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.2.min.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.simple-dtpicker.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/revenue/layer.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.common.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/Validform_v5.3.2.js?v=5"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.form.js?v=5"></script>

<script type="text/javascript">
	(function($){
		//表单验证
		$(".proptypeCardForm").Validform({
			btnSubmit:".subBtn",
			tiptype:4,
			dragonfly:true,
			ajaxPost:false,
			postonce:false,
			beforeSubmit:function(curform){
				$(".proptypeCardForm").attr('onsubmit','return false;');
			},
			callback:function(data){
				$(".proptypeCardForm").ajaxSubmit(function(data) {
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
						$(".proptypeForm").Validform().resetStatus();
					} else {
						alert(data.message);
						$(".proptypeForm").Validform().resetStatus();
					}
				});
			}
		});
	})(jQuery);
</script>

</html>
