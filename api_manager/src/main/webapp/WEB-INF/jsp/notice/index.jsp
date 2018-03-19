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
<title>物业公告管理</title>
    <%@ include file="../common/static.jsp"%>

<script type="text/javascript">
	var Common = {
	    //EasyUI用DataGrid用日期格式化 formatter="Common.TimeFormatter"
	    typeFormatter: function (value, rec, index) {
	    	if(value == '3'){
	    		return "物业公告";
	    	}else{
	    		return value;
	    	}
	    },
	    stateFormatter:function (value, rec, index) {
	    	if(value == '0'){
	    		return "有效";
	    	}else if(value == '1'){
	    		return "无效";
	    	}
	    }
	};
</script>
</head>
<body>
	<table id="dg" title="物业公告列表" fit="true" toolbar="#toolbar" pagination="true" rownumbers="true" pageSize="30"
		fitColumns="true" singleSelect="true" loadMsg="正在查询...">
		<thead>
			<tr>
				<th field="id" width="20">ID</th>
				<th field="title" width="100">公告标题</th>
				<th field="type" width="50" formatter="Common.typeFormatter">分类</th>
				<th field="content" width="250">公告内容</th>
				<th field="picUrl" width="150">图片模板路径</th>
				<th field="delState" width="150" formatter="Common.stateFormatter">是否有效</th>
				<th field="time" width="100">创建时间</th>
				<!--  
				<th field="creater" width="100">创建人</th>
				-->
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<span>公告标题:</span><input type="text" id="title" name="title"/>
	    <a href="javascript:void(0);" onclick="findData();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	    <br/>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addnotice()">新增物业公告</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editnotice()">编辑物业公告</a>
		<!-- 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tip" plain="true" onclick="viewnotice()">查看物业公告</a>
		 -->
	</div>
	
	<div id="notice" class="easyui-dialog" style="padding: 10px 20px" closed="true">
	</div>
	
	<script type="text/javascript">
	
		$('#dg').datagrid({
		    url:'<%=basePath%>/notice/list.html',
		    onDblClickRow: function(rowIndex, rowData){
		    	editnotice();
			}
		});
		
		function findData(){
	        $('#dg').datagrid('reload',{title:$('#title').val()});
		}
		
		function addnotice() {
			$("#notice").dialog({
	            title: '新增物业公告',
	            href: '<%=basePath%>/notice/addnotice.html?rand='+Math.random(),
	            iconCls: 'icon-add',
	            width: 600,
	            height: 700,
	            modal: true,
	            closed: true
	        }).dialog('open');
		}
		
		function editnotice() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$("#notice").dialog({
		            title: '编辑物业公告',
		            href: '<%=basePath%>/notice/editnotice.html?id='+row.id+'&rand='+Math.random(),
		            iconCls: 'icon-edit',
		            width: 600,
		            height: 700,
		            modal: true,
		            closed: true

		        }).dialog('open');
			}
		}
		
		function viewnotice() {
			$("#notice").dialog({
	            title: '查看物业公告',
	            href: '<%=basePath%>/notice/viewnotice.html?rand='+Math.random(),
	            iconCls: 'icon-tip',
	            modal: true,
	            closed: true
	        }).dialog('open');
		}
		
	</script>
</body>
</html>
