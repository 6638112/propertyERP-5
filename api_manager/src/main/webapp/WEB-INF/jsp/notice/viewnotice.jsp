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
<title>查看物业公告</title>
    <%@ include file="../common/static.jsp"%>
</head>
<body>
	
		<div class="ftitle">物业公告信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>公告标题:</label> <input name="title" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>类别:</label> <input name="type" class="easyui-validatebox" required="true" value="物业公告" readonly="readonly">
			</div>
			<div class="fitem">
				<label>公告图片模板:</label> <input type="file" name="imgfile" class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>推送小区:</label>
				<label>
					<div class="easyui-panel" style="width: 370px;height: 250px">
				        <ul id="grouptree" class="easyui-tree" checkbox="true" url="data/tree_data.json">
				            <li>
				                <span>全部小区</span>
				                <ul>
				                    <li>
				                        <span id="1">深圳市</span>
				                        <ul>
				                            <li id="2">
				                               <span>福年小区</span>
				                            </li>
				                            <li id="3">
				                               <span>龙庭小区</span>
				                            </li>
				                            <li id="3">
				                               <span>香蜜湖小区</span>
				                            </li>
				                        </ul>
				                    </li>
				                    <li data-options="state:'closed'">
				                        <span>广州市</span>
				                        <ul>
				                            <li>天河小区</li>
				                            <li>岗顶小区</li>
				                            <li>番禺小区</li>
				                            <li>大学城小区</li>
				                        </ul>
				                    </li>
				                    <li>平湖小区</li>
				                    <li>宝安小区</li>
				                    <li>水榭花都小区</li>
				                </ul>
				            </li>
				        </ul>
				    </div>
			    </label>
			</div>
			<div class="fitem">
				<label>公告内容:</label> <textarea rows="10" cols="50" name="content" class="easyui-validatebox" required="true"></textarea>
			</div>
		</form>
		
		<div class="ftitle"></div>
		<div align="center">
			<button id="subbtn" class="easyui-linkbutton" iconCls="icon-ok">提交</button>
			<button id="cancelbtn" class="easyui-linkbutton" iconCls="icon-cancel">取消</button>
		</div>
		
	<script type="text/javascript">
		$("#subbtn").bind("click",function(){ 
			var checkeds = $('#grouptree').tree('getChecked');
			for(var i=0;i<checkeds.length;i++){
				alert(checkeds[i].id);
			}
			
		});
		
		$("#cancelbtn").bind("click",function(){
			$('#notice').dialog('close');
		});
	</script>
	
</body>
</html>
