<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限-新增角色</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>新增角色</h2>
	<form id="saveForm" class="inputform" name="saveForm" method="post" action="<%=basePath%>/omsPermiRole/roleSave.html">
	    <div class="distr">
	        <div class="bs-example">
		            <ul class="ranking">
		            	<input type="hidden" value="${role.id }" name="roleId"/> 
		            	<li>角色编码： <input type="text" name="roleCode" class="input_text pp" title="请输入角色名" value="${role.code }" datatype="*4-16" ignore="ignore" errormsg="帐号由4到16位字符组成！"></li>
		            	<li>角色名： <input type="text" name="roleName" class="input_text pp" title="请输入角色名" value="${role.name }" datatype="*4-16" nullmsg="请填写帐号！" errormsg="帐号由4到16位字符组成！"></li>
		                <li>角色描述： <input type="text" name="roleDesc" class="input_text w500 pp" title="请输入角色描述" value="${role.desc }"></li>
		            </ul>
		            
				   <display:table name="opfList" id="row" class="mars info-list-02 mtop20" requestURI="" >
						<display:column title="权限名称" sortable="true">
							<label>
								<input name="opfId" 
									   type="checkbox" 
									   value="${row.id }" 
									   id="${row.id }" 
									   <c:if test="${row.level==2 }">style="margin-left:3em;" </c:if>
									   <c:if test="${row.level==3 }">style="margin-left:6em;" </c:if>
									   <c:if test="${row.level==4 }">style="margin-left:9em;" </c:if>
									   level = "${row.level}"
									   parentid = "${row.parnetid }"
									   onclick = "setRelateCheckBox(${row.id }, '${row.level}')" <c:if test="${row.isAssignedToRole==1 }">checked="checked"</c:if>/>${row.name }
							</label>
						</display:column>
						<display:column title="权限描述"></display:column>
						<display:column title="级次" media="html" property="level">
						</display:column>
					</display:table>
	      	</div>
	    </div>
 		<input id="sumRole" class="info-btn" type="submit" value="提 交"/> </div></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$(function(){
	    //表单验证
		$(".inputform").Validform({
			tiptype:3
		});
	});
	
	//提交保存
	$("#sumRole").click(function(){
		if($("input[name=opfId]:checked").length==0){
			alert("至少要选择一个权限分配给角色！");
			return false;
		}
	});
	
	// 设置相关的父、子checkbox
	function setRelateCheckBox(id, level){
		var thisTd = $("#"+id).parent().parent().parent();
		var isChecked = $("#"+id).is(":checked");
		var parentId = $("#"+id).attr("parentid");
		setRelateChildren(thisTd, level, isChecked);
		if(level!=1){
			setRelateParent(parentId, level, isChecked);
		}
	}
	
	// 设置相关的子checkbox
	function setRelateChildren(thiz, level, isChecked){
		var nextNode = $(thiz).next().find(":checkbox");
		if(nextNode.attr("level")>level){
			nextNode.prop("checked", isChecked);
			setRelateChildren(nextNode.parent().parent().parent(), level, isChecked);
		}
	}
	// 设置相关的父checkbox
	function setRelateParent(thizParentId, level, isChecked){
		if(thizParentId!=null && thizParentId!=""){
			var parentId = $("#"+thizParentId).attr("parentid");
			if(isChecked){
				$("#"+thizParentId).prop("checked", isChecked);
				if(parentId!=null && parentId!=""){
					setRelateParent(parentId, level, isChecked);
				}
			} else {
				// 判断兄弟checkbox是否都没选中
				var brotherCheckedNum = $(":checkbox[parentid="+thizParentId+"]:checked").length;
				if(brotherCheckedNum==0){
					$("#"+thizParentId).prop("checked", isChecked);
					setRelateParent(parentId, level, isChecked);
				}
			}
		}
	}
</script>
</html>
