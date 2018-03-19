<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
</head>

<body>
<div class="info">
    <form class="inputform" action="../roomValidate/saveAuditResult.html" >
    <h2>门牌信息</h2>
    <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td align="right"><span class="grey">用户ID：</span></td>
        <td>${rvInfo.userId }</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户姓名：</span></td>
        <td>${rvInfo.userRealName }</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户手机号：</span></td>
        <td>${rvInfo.userMobile }</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户注册门牌：</span></td>
        <td>${rvInfo.rvDesc}</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户上传文件：</span></td>
        <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getImageServerUrl("/roomValidatePic/") %>/roomValidatePic/${rvInfo.picURL }" rel="lightbox"><img src="<%=OmsSysParamManager.getImageServerUrl("/roomValidatePic/") %>/roomValidatePic/${rvInfo.picURL }" border="0" /></a></li></ul></td>
      </tr>
      <tr>
        <td align="right"><span class="grey">管理费：</span></td>
        <td><input type="text" class="input_text w80" value="${rvInfo.planPropertyAmount}" name="planPropertyAmount" /> 元</td>
      </tr>
    </table>
    <h2 class="mtop40">审核</h2>
	    <div class="bs-example">
	    		<input type="hidden" name="rvId" value="${rvInfo.rvId }"/>
	    		<input type="hidden" name="rId" value="${rvInfo.rId }"/>
	    		<input type="hidden" name="rrId" value="${rvInfo.rrId }"/>
		        <table class="info-list" border="0">
		          <tr>
		            <td width="90" align="right">审核结果：</td>
		            <td colspan="5">
		                <c:choose>
							<c:when test="${rvInfo.rvStatus==2 }">待审核</c:when>
							<c:when test="${rvInfo.rvStatus==3 }">审核失败</c:when>
							<c:when test="${rvInfo.rvStatus==4 }">审核通过</c:when>
							<c:otherwise>其它</c:otherwise>
						</c:choose>
		            </td>
		          </tr>
		          <tr class="">
		            <td align="right">原因：</td>
		            <td colspan="5">
		            	<c:if test="${rvInfo.rvStatus==3 }"><%-- 审核失败，显示原因--%>
			            	${rvInfo.auditResultDesc }
		            	</c:if>
		            </td>
		          </tr>
		        </table>
	    </div>
	    <table id="createAccount" class="info-list-01 mtop10" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="180" align="right">是否享受兑换管理费粮票：</td>
            <td width="50"><input disabled="disabled" class="mtop3" name="planSwitchStatus" type="radio"  <c:if test="${rvInfo.planSwitchStatus == 1}"> checked="checked"</c:if> />是</td>
            <td><input disabled="disabled" class="mtop3" name="planSwitchStatus" type="radio" <c:if test="${rvInfo.planSwitchStatus != 1}"> checked="checked"</c:if> /> 否</td>
          </tr>
        </table>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/picbox.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>

</script>
</html>;
