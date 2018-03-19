<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-店铺管理-店铺审核</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
	<form class="inputform" action="../communitySupply/auditCSTmpSave.html">
		<input name="id" type="hidden" value="${entity.id}" />
		<input name="mobile" type="hidden" value="${entity.companyPhone}" />
        <table class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <thead>
          <tr class="title">
            <th width="150"><div align="left">审核类型</div></th>
            <th><div align="left">提交时间</div></th>
          </tr>
          </thead>
          <tr>
            <td>商家新增</td>
            <td>${entity.sys0AddTime }</td>
          </tr>
          <tr>
            <td>新增来源</td>
            <td>
            	<c:if test="${entity.addType==null || entity.addType==1}">APP新增</c:if>
            	<c:if test="${entity.addType!=null && entity.addType==2}">物业新增</c:if>
            	<c:if test="${entity.addType!=null && entity.addType==4}">渠道-代理商新增</c:if>
            </td>
          </tr>
        </table>
        <h2 class="mtop20">店铺审核</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺类别：</td>
            <td>${entity.supplyType }</td>
          </tr>
          <tr>
            <td width="120" align="right"><Span class="red">*</Span> 店铺名称：</td>
            <td>${entity.supplyName }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 店铺所在地：</td>
            <td>${ entity.apName}${entity.acName}${entity.abName}</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 详细地址：</td>
            <td>${entity.addressDetail }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> 联系人：</td>
            <td>${entity.companyName }</td>
          </tr>
          <c:choose>
          	<c:when test="${entity.addType==null || entity.addType==1}">
          		 <tr>
		            <td align="right"><Span class="red">*</Span> 店铺电话：</td>
		            <td>${entity.contectPhone }</td>
	         	 </tr>
          	</c:when>
          	<c:otherwise> <%-- entity.addType==2,3,4 --%>
          		<tr>
	            <td align="right"><Span class="red">*</Span> 店铺电话：</td>
	            <td>
	            <div>
					<c:forEach items="${contects }" varStatus="i" var="contect">
		            	电话${i.index+1}:${contect.contectInfo }&nbsp;&nbsp;
		            </c:forEach>
	            </div>
	           </td>
	          </tr>
          	</c:otherwise>
          </c:choose>
         
          <tr>
            <td align="right"><Span class="red">*</Span> 店主手机号：</td>
            <td>${entity.companyPhone }</td>
          </tr>
          <tr>
            <td align="right">店铺证件：</td>
            <td>
            	<c:forEach items="${comPicURL }" var="picurl" >
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communityCompanyListPic/") %>/communityCompanyListPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communityCompanyListPic/") %>/communityCompanyListPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td align="right">店铺图标：</td>
            <td>
            	<c:forEach items="${tmpPicURL }" var="picurl">
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            </td>
          </tr>
        </table>
        <h2 class="mtop20">店铺服务小区</h2>
        <c:if test="${entity.addType==null || entity.addType==1 }">
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <thead>
          <tr class="title">
            <th width="150"><div align="left">小区名字</div></th>
            <th><div align="left">详细地址</div></th>
          </tr>
          </thead>
          <tr>
            <td>${groupBuilding.name }</td>
            <td>${groupBuilding.addressDesc}</td>
          </tr>
        </table>
        </c:if>
        <c:if test="${entity.addType!=null || entity.addType==2 }">
        <display:table name="serviceGBList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" requestURI="" >
        	<display:column title="小区名字">${row.name}</display:column>
        	<display:column title="所在省">${row.addressProvinceName}</display:column>
        	<display:column title="所在市">${row.addressCityName}</display:column>
        	<display:column title="区">${row.addressBlockName}</display:column>
        	<display:column title="小区详细地址">${row.addressDesc}</display:column>
        </display:table>
        </c:if>
        <h2 class="mtop20">审核</h2>
        <div class="bs-example">
            <table class="info-list" border="0">
              <tr>
                <td width="90" align="right"><span class="grey">审核结果：</span></td>
                <td colspan="5">
                	<select class="select_normal w131 select-check" name="auditResult">
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
        <table id="createAccount" class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
          <thead>
              <tr class="title">
                <th colspan="2"><div align="left">账号分配</div></th>
              </tr>
          </thead>
          <tr>
            <td width="90" align="right"><span class="grey">分配账号：</span></td>
            <td colspan="5">
            	<c:choose>
            		<c:when test="${empty omsUserId }">
		            	<input name="omsUserName" type="text" onchange="validateUserName(this)" datatype="*" nullmsg="分配账号不能为空！"/>
		            	<span id = "showResult"></span> <span class="mar-left15">提醒：初始密码同分配账号名称</span>
            		</c:when>
            		<c:when test="${not empty omsUserId }">
            			<input name="omsUserId" type="hidden" value="${omsUserId }" />
            			<span class="mar-left15">提醒：该用户非首次审核店铺，已经有了后台账号</span>
            		</c:when>
            	</c:choose>
            </td>
          </tr>
          <c:if test="${empty omsUserId }">
	          <tr>
	            <td width="90" align="right"><span class="grey">账号角色：</span></td>
	            <td>
	            	<c:forEach items="${roleList}" var="role"  >
			            <input class="mtop3" name="roleId" type="radio" value="${role.id}" datatype="*" nullmsg="请选择一个角色！" /> ${role.name} &nbsp; &nbsp;
	            	</c:forEach>
	            </td>
	          </tr>
          </c:if>
        </table>
        <div class="padb mtop10"><input id="sumAccount" class="info-btn" type="submit" value="提 交" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	 $(".inputform").Validform({
		tiptype:3,
		ignoreHidden:true,
	}); 
});

//验证用户是否存在
function validateUserName(ths) {

    // 1获取文本框的内容
    var userName = ths.value;

    // 2将获取到的内容发送给服务器的servlet
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
});
</script>

</html>
