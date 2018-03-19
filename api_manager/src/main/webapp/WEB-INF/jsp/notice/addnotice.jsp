<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增物业公告</title>
<%@ include file="../common/static.jsp"%>
</head>
<body>
	
		<div class="ftitle">物业公告信息</div>
		<form id="fm" method="post" novalidate enctype="multipart/form-data">
			<div class="fitem">
				<label>公告标题:</label> <input name="title" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>类别:</label> <input name="type" class="easyui-validatebox" required="true" value="物业公告" readonly="readonly">
			</div>
			<div class="fitem">
				<label>公告图片模板:</label> <input type="file" name="imgfile" class="easyui-validatebox" >
			</div>
			<div class="fitem">
				<label>推送小区:</label>
				<label>
					<div class="easyui-panel" style="width: 370px;height: 250px;overflow: scroll;">
				        <ul id="grouptree" class="easyui-tree" checkbox="true" >
				        </ul>
				    </div>
			    </label>
			</div>
			<div class="fitem">
				<label>公告内容:</label> <textarea rows="10" cols="50" name="content" class="easyui-validatebox" required="true"></textarea>
			</div>
			<input type="hidden" name="address" id="address" value=""/>
			<input type="hidden" name="delState" id="delState" value="0"/>
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
				url : '<%=basePath%>/notice/doAddNotice.html?rand='+Math.random(),
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
