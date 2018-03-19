<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业账号申请管理-列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>


<body>
<div class="info">
    <h2>账号申请管理</h2>
    <div class="bs-example bgebeb">
    	<form action="../propertyCompany/search.html">
	        <table class="info-list" border="0">
	          <tr>
	            <td width="90"><div class="list-name">物业公司名称：</div></td>
	            <td width="90"><input type='text' class='input_text' name='pcName' value="${param.pcName }"/> </td>
	            <td width="90"><div class="list-name">申请时间：</div></td>
	            <td width="340"><input type="text" class="input_text icon_dt" id="date01" value="${param.startTime }" name="startTime" title="请选择起始时间" > 至
					<input type="text" class="input_text icon_dt" id="date02" value="${param.endTime }" name="endTime" title="请选择结束时间" ></td>
	            <td width="90"><div align="right">状态：</div></td>
	            <td width="130"><select class="select_normal w131" name="isAudited">
	                    <option value="-1" <c:if test="${param.isAudited==-1 }"> selected="selected"</c:if>>全部</option>
	                    <option value="0" <c:if test="${param.isAudited==0 }"> selected="selected"</c:if>>待审核</option>
	                    <option value="1" <c:if test="${param.isAudited==1 }"> selected="selected"</c:if>>已审核</option>
	                    </select></td>
	            <td width="20"></td>
	            <td><input class="input-btn w80" type="submit" value="查询"></td>
	          </tr>
	        </table>
    	</form>
    </div>    
        
     <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="申请物业公司" >
			<a class="blue checkAccount" href="../propertyCompany/auditView.html?pcId=${row.id }">${row.name }</a>
		</display:column>
		<display:column title="申请者手机号码" property="mobilePhone" />
		<display:column title="提交时间" >
			<c:out value='${fn:substringBefore(row.sys0AddTime,".") }'></c:out>
		</display:column>
		<display:column title="最后登录时间">
			<c:out value='${fn:substringBefore(row.lastLoginTime,".") }'></c:out>
		</display:column>
		<display:column title="是否锁定物业" >
			<input type="checkbox" disabled="disabled" class='${row.id }'
				<c:if test="${row.isLocked ==1}">checked="checked"</c:if>
			/>
		</display:column>
		<display:column title="商务跟进人" >
			<select name="follower">
           		<option value=""></option>
           		<c:forEach items="${userList}" var="item" >
	           		<option value="${item.realName }" <c:if test="${row.follower==item.realName }">selected</c:if> >${item.realName }</option>
           		</c:forEach>
            </select>
            	<a id='${row.id}' class='blue aModifyFollow' href="javascript:void(0);">修改跟进人</a>
		</display:column>
		<display:column title="审核状态" >
			<c:if test="${row.isAudited==0 }"><span class="red">待审核</span></c:if>
			<c:if test="${row.isAudited==1}">已审核</c:if>
		</display:column>
		<display:column title="审核结果描述" property="auditResult"> </display:column>
		<display:column title="合作状态" >
			<c:choose>
				<c:when test="${row.cooperationType==0 }">无</c:when>
				<c:when test="${row.cooperationType==1 }">基础</c:when>
				<c:when test="${row.cooperationType==2 }">高级</c:when>
				<c:when test="${row.cooperationType==3 }">全面</c:when>
			</c:choose>
		</display:column>
		<display:column title="操作"  >
			<c:if test="${row.isAudited==1}">
				<a class="blue checkAccount" href="../propertyCompany/auditView.html?pcId=${row.id }">查看</a> &nbsp;&nbsp;
				<a class="blue checkAccount" href="../propertyCompany/initEditAfterAuditView.html?pcId=${row.id }">编辑</a>
			</c:if>
			<c:if test="${row.isAudited==0 }"><a class="blue checkAccount audit " href="../propertyCompany/audit.html?pcId=${row.id }" pcId='${row.id }' >审核</a>&nbsp;&nbsp;<a class="blue" href="../propertyCompany/delete.html?pcId=${row.id }">删除</a></c:if>
		</display:column>
	</display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" >
	$(document).ready(function(){
		$('.aModifyFollow').click(function(){
			if(window.confirm('确认要修改商务跟进人？')){
				var $a = $(this);
				var url = '../propertyCompany/modifyFollower.html';
				var pcId = $a.attr("id");
				var follower = $a.siblings('select').val();
				$.post(url,
						{id:pcId,follower:follower},
						function(data,status){
							if(status==='success')
						      alert("修改成功");
						    }
						);
			}
		});
	});
	
	$('a.audit').click(function(){
		var pcId = $(this).attr('pcId');
		var $checkbox = $('input[type=checkbox].'+pcId);
		if($checkbox.is(':checked') && !window.confirm("已被锁定物业公司，确认要审核吗？")){
			return false;
		}
	});
</script>

</html>
