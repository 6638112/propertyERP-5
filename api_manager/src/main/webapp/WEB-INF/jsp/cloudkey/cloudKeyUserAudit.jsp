<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登记信息</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>

</head>
<body>
<div class="info">
    <form class="inputform">
    	<input type="hidden" name="cloudId" value="${cloudKeyAudit.id}" />
        <h2>用户登记信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">用户审核</div></td>
          </tr>
          <tr>  
            <td width="20%"><div class="list-name"><span class="red">*</span> 用户解放号：</div></td>
            <td>${cloudKeyAudit.huaId}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 门牌号：</div></td>
            <td>${cloudKeyAudit.gbName}-${cloudKeyAudit.buildingname}-${cloudKeyAudit.unitName}-${cloudKeyAudit.roomNum}</td>
          </tr>
          <c:choose>
              <c:when test="${fn:length(cloudKeyUserDatas)>0}">  
	              <c:forEach items="${cloudKeyUserDatas}" var="cloudKeyUserData">
		          	  <c:choose>
		          	  	 <c:when test="${cloudKeyUserData.inputType eq '3'}">  
			          	  	 <tr>
					            <td><div class="list-name">${cloudKeyUserData.label}：</div></td>
					            <td>
					                <ul class="menu-img">
				                    	<li>
					                    	<a href="<%=OmsSysParamManager.getImageServerUrl("/doorKeyPic/") %>/doorKeyPic/${cloudKeyUserData.value}" rel="clearbox[test2]">
						                    	<img src='<%=OmsSysParamManager.getImageServerUrl("/doorKeyPic/")  %>/doorKeyPic/${cloudKeyUserData.value}' border="0" />
						                    </a>
					                 	</li>
					                </ul>
					            </td>
					          </tr>
					     </c:when>
					     <c:otherwise> 
					         <tr>
					            <td><div class="list-name"><span class="red">*</span> ${cloudKeyUserData.label}：</div></td>
					            <td>${cloudKeyUserData.value}</td>
					          </tr>
					     </c:otherwise>
		          	  </c:choose>
		          </c:forEach>
              </c:when>
              <c:otherwise> 
              	  <%--兼容旧数据--%>
	              <tr>
		            <td><div class="list-name"><span class="red">*</span> 姓名：</div></td>
		            <td>${cloudKeyAudit.userName}</td>
		          </tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 身份证号：</div></td>
		            <td>${cloudKeyAudit.userNo}</td>
		          </tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 手机号：</div></td>
		            <td>${cloudKeyAudit.phoneNo}</td>
		          </tr>
		          <tr>
		            <td><div class="list-name">用户上传图片：</div></td>
		            <td>
		                <ul class="menu-img">
	                    	<li>
		                    	<a href="<%=OmsSysParamManager.getImageServerUrl("/doorKeyPic/") %>/doorKeyPic/${cloudKeyAudit.photoUrl }" rel="clearbox[test2]">
			                    	<img src='<%=OmsSysParamManager.getImageServerUrl("/doorKeyPic/")  %>/doorKeyPic/${cloudKeyAudit.photoUrl }' border="0" />
			                    </a>
		                 	</li>
		                </ul>
		            </td>
		          </tr>
              </c:otherwise>
          </c:choose>
        </table>
		<h2 class="mtop10 red">审核</h2>
			<div class="bs-example">
				<table class="info-list" border="0">
					 <tr>
					     <td width="90" align="right"><span class="grey">审核结果：</span></td>
					     <td colspan="5">
					     	<select class="select_normal w131 select-check" name="auditResult">
					            <option value="pass">通过</option>
					            <option value="nopass">不通过</option>
				            </select>
					     </td>
					  </tr>
		              <tr class="dsn">
		                <td align="right"><span class="grey">原因：</span></td>
		                <td colspan="5">
		                	<select class="select_normal w131 select-check" name="auditReason">
					            <option value="图片不清晰" selected>图片不清晰</option>
					        </select>
					    </td>
					  </tr>
				</table>
			</div>
			<input id="sumPutaway" class="info-btn" type="button" onclick="auditData();" value="提 交" />
	   </form>
	</div>
</body>

<script type="text/javascript" src="../js/clearbox.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
		ignoreHidden:true
	});
})(jQuery);

function auditData(){
	var cloudId= $("input[name='cloudId']").val();
	var auditResult = $("select[name=auditResult] option:selected").val();
	var auditReason = $("select[name=auditReason]  option:selected").val();
	$.post("../cloudkey/buildingKeyAudit.html",{cloudId:cloudId,auditResult:auditResult,auditReason:auditReason},function(data) {
		if(data.status == '0000'){
			$("#sumPutaway").attr({value:"操作成功！",onclick:""});
			setTimeout(function(){
				var href ="<%=basePath%>/cloudkey/buildingKeyList.html";
				$(window.parent.document).find('#mainFrame').attr('src',href);
			},1500);
		}else if(data.status == '0001'){
			alert(data.message);
			setTimeout(function(){
				history.back();
			},800);
		}else if(data.status == '0002'){
			alert("系统异常，请联系管理员！！！");
			history.back();
		}
	});
}

function backload(){
	history.back();
}
</script>
</html>
