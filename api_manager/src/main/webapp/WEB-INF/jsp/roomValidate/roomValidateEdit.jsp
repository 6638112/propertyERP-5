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
    <input type="hidden" name="gbId" value="${rvInfo.gbId }" />
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
        <td align="right"><span class="grey">修改小区名称：</span></td>
        <td><input type="text" id="gbName" value="${rvInfo.gbName}" /> <input type="button" id="modifyGbNameBtn" value="确认修改"/> </td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户上传文件：</span></td>
        <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getImageServerUrl("/roomValidatePic/") %>/roomValidatePic/${rvInfo.picURL }" rel="lightbox"><img src="<%=OmsSysParamManager.getImageServerUrl("/roomValidatePic/") %>/roomValidatePic/${rvInfo.picURL }" border="0" /></a></li></ul></td>
      </tr>
      <tr>
        <td align="right"><span class="grey">管理费：</span></td>
        <td><input type="text" class="input_text w80" value="${rvInfo.planPropertyAmount}" name="planPropertyAmount" /> 元</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">码神解放号：</span></td>
        <td>${rvInfo.inviteUserId}</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">用户邀请码：</span></td>
        <td>${rvInfo.inviteNo}</td>
      </tr>
      <tr>
        <td align="right"><span class="grey">邀请人类型：</span></td>
        <td>
        	<c:choose>
	        	<c:when test="${rvInfo.inviteType==1 }">解放区</c:when>
	        	<c:when test="${rvInfo.inviteType==2 }">物业</c:when>
	        	<c:when test="${rvInfo.inviteType==3 }">商家</c:when>
        	</c:choose>
         </td>
      </tr>
    </table>
    <h2 class="mtop40">审核</h2>
	    <div class="bs-example">
	    		<input type="hidden" id="rvId" name="rvId" value="${rvInfo.rvId }"/>
	    		<input type="hidden" name="rId" value="${rvInfo.rId }"/>
	    		<input type="hidden" name="rrId" value="${rvInfo.rrId }"/>
	    		<input type="hidden" name="userId" value="${rvInfo.userId }"/>
		        <table class="info-list" border="0">
		          <tr>
		            <td width="90" align="right">审核结果：</td>
		            <td colspan="5">
	            		<select class="select_normal w131 select-check" name="auditResult" <c:if test="${rvInfo.rvStatus==4}">disabled="disabled"</c:if> >
	                        <option value="pass">通过</option>
	                        <option value="notpass">不通过</option>
                        </select></td>
		          </tr>
		          <tr class="dsn">
		          	<td align="right">原因：</td>
		            <td colspan="5">
		            	<input name="failReason" class="iptFail" type="radio" value="请上传与当前门牌地址一致的清晰有效账单图片，感谢您对解放区的支持。" />请上传与当前门牌地址一致的清晰有效账单图片，感谢您对解放区的支持。<br>
		            	<input name="failReason" class="iptFail" type="radio" value="请上传与当前门牌地址一致（含小区名称）的清晰公章或者财务章账单图片，感谢您对解放区的支持。" />请上传与当前门牌地址一致(含小区名称）的清晰公章或者财务章账单图片，感谢您对解放区的支持。<br>
		            	<input name="failReason" class="iptFail" type="radio" value="请不要手动填写楼栋房号而是选择app已有正确的楼栋房号格式，谢谢！" />请不要手动填写楼栋房号而是选择app已有正确的楼栋房号格式，谢谢！<br>
		            	<input name="failReason" class="iptFail" type="radio" value="参加5户活动，请上传物业费账单，谢谢！" />参加5户活动，请上传物业费账单，谢谢！<br>
		            	<input name="failReason" class="iptFail" type="radio" value="请在单据上注明小区名称，谢谢！" />请在单据上注明小区名称，谢谢！<br>
		            	<input name="failReason" class="iptFail" type="radio" value="请在单据或app上备注手机号，感谢您对解放区的支持。" />请在单据或app上备注手机号，感谢您对解放区的支持。<br>
		            	<textarea class="textareas txt02" name="auditNotPassResultDesc" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea>
		            </td>
		          </tr>
		        </table>
	    </div>
	    <table id="createAccount" class="info-list-01 mtop10" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="180" align="right">是否享受兑换管理费粮票：</td>
            <td width="50"><input class="mtop3" name="planSwitchStatus" type="radio" value="1" <c:if test="${rvInfo.planSwitchStatus == 1}"> checked="checked"</c:if> />是</td>
            <td><input class="mtop3" name="planSwitchStatus" type="radio" value="2" <c:if test="${rvInfo.planSwitchStatus != 1}"> checked="checked"</c:if> /> 否</td>
          </tr>
        </table>
	    <div class="padb height240"><input id="sumAddresses" class="info-btn" type="submit" value="提 交" /></div>
    </form>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/picbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$(function(){
	    //表单验证
		$(".inputform").Validform({
			tiptype:3,
			ignoreHidden: true
		});
	});
	
	$(".iptFail").click(function(){
		$(".textareas").text($(this).val());
	});
	
	$("#modifyGbNameBtn").click(function(){
		if(window.confirm("确认要修改小区的名称吗？")){
			var gbName = $("#gbName").val();
			var rvId = $("#rvId").val();
			$.ajax({
				url: "../roomValidate/updateGroupBuildingName.html",
				async:false,
				data:{rvId:rvId,gbName:gbName,},
				success:function(data, textStatus){
			        alert("操作提示："+data);
				},
				error:function(data){
					alert("修改小区名称失败");
				}
			});
		}else{
			return false;
		}
	});
</script>
</html>
