<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业信息审核管理-列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>


<body>
<div class="info">
    <h2>修改申请管理</h2>
    <div class="bs-example bgebeb">
    	<form action="../propertyCompany/editSearch.html" method="post">
	        <table class="info-list" border="0">
	          <tr>
	            <td width="90"><div class="list-name">物业公司名称：</div></td>
	            <td width="90"><input type='text' class='input_text' name='pcName' value="${param.pcName }"/> </td>
	            <td width="90"><div class="list-name">申请时间：</div></td>
	            <td width="340"><input type="text" class="input_text icon_dt" id="date01" value="${param.startTime }" name="startTime" title="请选择起始时间" > 至
					<input type="text" class="input_text icon_dt" id="date02" value="${param.endTime }" name="endTime" title="请选择结束时间" ></td>
	            <td width="90"><div align="right">修改类型：</div></td>
	            <td width="130"><select class="select_normal w131" name="editType">
	                    <option value="-1" <c:if test="${param.editType==-1 }"> selected="selected"</c:if>>全部</option>
	                    <option value="1" <c:if test="${param.editType==1 }"> selected="selected"</c:if>>物业信息修改</option>
	                    <option value="2" <c:if test="${param.editType==2 }"> selected="selected"</c:if>>管理处信息修改</option>
	                    </select></td>
	            <td width="130"><select class="select_normal w131" name="isEdit">
	                    <option value="-1" <c:if test="${param.isEdit==-1 }"> selected="selected"</c:if>>全部</option>
	                    <option value="0" <c:if test="${param.isEdit==0 }"> selected="selected"</c:if>>待审核</option>
	                    <option value="1" <c:if test="${param.isEdit==1 }"> selected="selected"</c:if>>已审核</option>
	                    <option value="2" <c:if test="${param.isEdit==2 }"> selected="selected"</c:if>>未通过</option>
	                    </select></td>
	            <td width="20"></td>
	            <td><input class="input-btn w80" type="submit" value="查询"></td>
	          </tr>
	        </table>
    	</form>
    </div>    
        
     <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="修改类型" >
			<c:if test="${row.propertyManagementId=='' }">
				物业信息修改
			</c:if>
			<c:if test="${row.propertyManagementId!='' }">
				管理处信息修改
			</c:if>
		</display:column>
		<display:column title="申请物业公司" property="name" />
		<display:column title="申请者手机号码" property="mobilePhone" />
		<display:column title="物业公司电话" property="tel" />
		<display:column title="物业联系人" property="linkman" />
		<display:column title="提交时间" >
			<c:if test="${row.propertyManagementId=='' }">
				<c:out value='${fn:substringBefore(row.sys0EditTime,".") }'></c:out>
			</c:if>
			<c:if test="${row.propertyManagementId!='' }">
				<c:out value='${fn:substringBefore(row.pmSys0UpdTime,".") }'></c:out>
			</c:if>
		</display:column>
		<c:if test="${row.propertyManagementId=='' }">
			<display:column title="状态" >
				<c:if test="${row.isEdit==0 }"><span class="red">待审核</span></c:if>
				<c:if test="${row.isEdit==1}">已审核</c:if>
				<c:if test="${row.isEdit==2}">未通过</c:if>
			</display:column>
			<display:column title="操作"  >
				<c:if test="${row.isEdit==1  || row.isEdit==2}"><a class="blue checkAccount" href="../propertyCompany/auditView.html?pcId=${row.id }">查看</a></c:if>
				<c:if test="${row.isEdit==0 }"><a class="blue checkAccount audit " href="../propertyCompany/editAudit.html?pcId=${row.id }" pcId='${row.id }' >审核</a></c:if>
			</display:column>
		</c:if>
		<c:if test="${row.propertyManagementId!='' }">
			<display:column title="状态" >
				<c:if test="${row.pmIsAudited==0 }"><span class="red">待审核</span></c:if>
				<c:if test="${row.pmIsAudited==1}">已审核</c:if>
				<c:if test="${row.pmIsAudited==2}">未通过</c:if>
			</display:column>
			<display:column title="操作"  >
				<c:if test="${row.pmIsAudited==1  || row.pmIsAudited==2}"><a class="blue checkAccount" href="../propertyCompany/auditPMView.html?pmId=${row.propertyManagementId }">查看</a></c:if>
				<c:if test="${row.pmIsAudited==0 }"><a class="blue checkAccount audit " href="../propertyCompany/editPMAudit.html?pmId=${row.propertyManagementId }" pcId='${row.id }' >审核</a></c:if>
			</display:column>
		</c:if>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>

</html>
