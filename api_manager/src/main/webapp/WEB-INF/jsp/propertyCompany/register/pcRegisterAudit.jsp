<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业公司-注册-审核界面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
    
        <h2>账号申请审核</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="145" align="right"><Span class="red">*</Span> <span class="grey">物业公司名称：</span></td>
            <td> <c:out value="${pc.name }"></c:out></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">手机号码：</span></td>
            <td>${pc.mobilePhone }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">联系人：</span></td>
            <td>${pc.linkman }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">联系邮箱：</span></td>
            <td>${pc.email }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">上传的营业执照：</span></td>
            <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoBusinessLicense }" rel="clearbox[test2]"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoBusinessLicense }" border="0" /></a></li></ul></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">上传的小区管理资质证明：</span></td>
            <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoCredentials }" rel="clearbox[test2]"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoCredentials }" border="0" /></a></li></ul></td>
          </tr>
          <tr>
            <td align="right" class="bold">所管辖小区：</td>
            <td></td>
          </tr>
          <c:forEach items="${gbrList}" var="gbr">
	          <tr>
	            <td align="right"><span class="grey">小区信息：</span></td>
	            <td>${gbr.gbrName }（${gbr.apName }&nbsp;&nbsp;${gbr.acName }&nbsp;&nbsp;${gbr.abName }&nbsp;&nbsp;${gbr.gbrAddressdesc }）</td>
	          </tr>
          </c:forEach>
        </table>
        <h2 class="mtop40">审核</h2>
      <form class="inputform" action="../propertyCompany/saveAuditResult.html" method="post">
      	<input name="mobile" value="${pc.mobilePhone }" type="hidden" />
      	<input name="email" value="${pc.email }" type="hidden" />
      	<input name="pcId" value="${pc.id }" type="hidden" />
      	<input name="pcMobilePhone" value="${pc.mobilePhone }" type="hidden" />
      	<input name="oldCooperationType" value="${pc.cooperationType }" type="hidden"/>
        <div class="bs-example">
            <table class="info-list" border="0">
              <tr>
                <td width="90" align="right"><span class="grey">审核结果：</span></td>
                <td colspan="5"><select class="select_normal w131 select-check" name="auditResult">
                            <option value="pass">通过</option>
                            <option value="notpass">不通过</option>
                            </select></td>
              </tr>
              <tr class="dsn">
                <td align="right"><span class="grey">原因：</span></td>
                <td colspan="5"><textarea class="textareas txt02" name="notPassReason" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea></td>
              </tr>
            </table>
        </div>
        <table id="createAccount" class="info-list-01 mtop40" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td width="90" align="right"><span class="grey">物业合作模式：</span></td>
				<td colspan="5">
					<select
						class="select_normal w131" name="cooperationType">
							<option value="0" selected="selected">无</option>
							<option value="1" >基础</option>
							<option value="2">高级</option>
							<option value="3">全面</option>
					</select>
				</td>
			</tr>
 		<tr>
            <td width="90" align="right"><span class="grey">分配账号：</span></td>
            <td colspan="5"><input name="omsUserName" maxlength="15" type="text" onchange="validateUserName(this)" datatype="EnglishOrNumber" nullmsg="分配账号不能为空！" /><span id = "showResult"></span> <span class="mar-left15">提醒：初始密码同分配账号名称</span></td>
          </tr>
			<tr>
				<td width="90" align="right"><span class="grey">账号角色：</span></td>
				<td colspan="5"><c:forEach items="${roleList }" var="role">
						<input class="mtop3" name="roleId" type="radio"
							value="${role.id }" datatype="*" nullmsg="请选择一个角色！" />${role.name } &nbsp; &nbsp;
           	</c:forEach></td>
			</tr>
			<tr>
            <td width="90" align="right"><span class="grey">商务跟进人：</span></td>
            <td colspan="5">
            	<select name="follower">
            		<option value="">请选择</option>
            		<c:forEach items="${userList}" var="item" >
	           			<option value="${item.realName }" <c:if test="${row.follower==item.realName }">selected</c:if> >${item.realName }</option>
           			</c:forEach>
            	</select>
            </td>
          </tr>
        </table>
        <div class="padb"><input id="sumAccount" class="info-btn" type="submit" value="提 交" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js?2016-05-20"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype: 3,
		ignoreHidden: true,
	});
});

//验证用户是否存在
function validateUserName(ths) {

    // 1获取文本框的内容
    var userName = ths.value;

    // 2将获取到的内容发送给服务器的servlet
    //使用jquery的XMLHTTPRequest对象get请求的封装
    
    $.ajax({
        type:"POST",   //http请求方式
        url:"../omsUser/validUserName.html", //发送给服务器的url
        data:"omsUserName="+userName, //发送给服务器的参数
        success:function(data, textStatus){ 
            $("#showResult").html(data);
            if(data==="已经存在该账号，请重新输入"){
           	 	$("#showResult").addClass('red');
           	 	ths.focus();
            }else{//验证通过
            	$("#showResult").removeClass('red').css("color","green");
            }
        },
    });
}

$(function(){
	$("#sumAccount").click(function(){
		if($("#showResult").hasClass('red')){
			alert("已经存在该账号，请重新输入");
			return false;
		}
	});
} )
</script>
</html>
