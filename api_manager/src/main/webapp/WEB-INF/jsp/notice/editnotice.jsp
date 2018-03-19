<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	String pathNoUri = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑物业公告</title>
<%@ include file="../common/static.jsp"%></head>
<body>
	
		<div class="ftitle">物业公告信息</div>
		<form id="fm" method="post" novalidate enctype="multipart/form-data">
			<div class="fitem">
				<label>公告标题:</label> <input name="title" class="easyui-validatebox" required="true" value="${bean.title}">
			</div>
			<div class="fitem">
				<label>类别:</label> <input name="type" class="easyui-validatebox" required="true" value="物业公告" readonly="readonly">
			</div>
			<div class="fitem">
				<label>公告图片模板:</label> <input type="file" name="imgfile">
			</div>

			 <div class="fitem">
				<label>已选推送小区:</label>
				<label>
					<table class="easyui-datagrid" style="width:370px;height:240px" url="<%=basePath%>/notice/addresslist.html?msgId=${bean.id}" singleSelect="true" loadMsg="正在查询...">
				        <thead>
							<tr>
								<th field="province" width="70">省名</th>
								<th field="city" width="70">城市</th>
								<th field="block" width="70">辖区</th>
								<th field="groupbuilding" width="120">小区名称</th>
							</tr>
						</thead>
			   	 	</table>
				</label>
			</div>
			 
			<div class="fitem">
				<label>公告内容:</label> <textarea rows="10" cols="50" name="content" class="easyui-validatebox" required="true" value="${bean.content}">${bean.content}</textarea>
			</div>
			<div class="fitem">
				<label>是否有效:</label>
				 <select class="easyui-combobox" name="delState" style="width:150px;">
	       			 <option value="0" <c:if test="${bean.delState == 0}">selected="selected"</c:if>>有效</option>
	       			 <option value="1" <c:if test="${bean.delState == 1}">selected="selected"</c:if>>无效</option>
	       		 </select>
			</div>
			<input type="hidden" name="address" id="address" value=""/>
			<input type="hidden" name="id" id="id" value="${bean.id}"/>
		</form>
		
		<div class="ftitle"></div>
		<div align="center">
			<button id="subbtn" class="easyui-linkbutton" iconCls="icon-ok">提交</button>
			<button id="cancelbtn" class="easyui-linkbutton" iconCls="icon-cancel">取消</button>
		</div>
		
	<script type="text/javascript">
		$("#subbtn").bind("click",function(){ 
			var checkeds = $('#grouptree').tree('getChecked');
			var address="";
			for(var i=0;i<checkeds.length;i++){
				if(i != checkeds.length - 1){
					address += checkeds[i].id+",";
				}else{
					address += checkeds[i].id;
				}
			}
			$('#address').val(address);
			
			$('#fm').form('submit', {
				url : '<%=basePath%>/notice/doEditNotice.html?rand='+Math.random(),
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(data) {
					if(!data.message)$('#notice').dialog('close');
					var data = eval('(' + data + ')');
					if (data.message) {
						$.messager.alert('友情提示',data.message,data.result,function(){
							if(data.result != 'error'){
								$('#notice').dialog('close');
								$('#dg').datagrid('reload');
							}
						});
					}
				}
				
			});
		});
		
		$("#cancelbtn").bind("click",function(){
			$('#notice').dialog('close');
		});
		
		$('#grouptree').tree({
		    url: '<%=basePath%>/notice/treedata.html',
		    loadFilter: function(data){
		        if (data.d){
		            return data.d;
		        }else{
		        	return data;
		        }
		    }
		});
		
	</script>
	
</body>
</html>
