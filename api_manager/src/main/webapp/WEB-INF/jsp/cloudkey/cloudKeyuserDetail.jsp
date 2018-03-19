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
					            <td width="20%"><div class="list-name"><span class="red">*</span> ${cloudKeyUserData.label}：</div></td>
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
		<input id="sumPutaway1" class="info-btn" type="button" onclick="backload();" value="返回" />
	</form>
</div>

</body>

<script type="text/javascript" src="../js/clearbox.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
function backload(){
	history.back();
}
</script>
</html>
