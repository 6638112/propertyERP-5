<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>运营消息列表</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css?v20161128">
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
	<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info posrelative">
	<h2>选项列表</h2>
	<div class="bs-example bgebeb">
		<form action="list.html" method="post">
			<table class="info-list" border="0">
				<tr>
					<td><div class="list-name">消息类型：</div></td>
					<td>
						<select class="select_normal" name="msgType">
							<option value="">请选择</option>
							<option value="1" <c:if test="${param.msgType ==1}">selected</c:if> >短信</option>
							<option value="2" <c:if test="${param.msgType ==2}">selected</c:if> >push</option>
						</select>
					</td>
					<td><div class="list-name">标题：</div></td>
					<td>
						<input type="text" value='${param.title }' name="title" class="input_text  pp">
					</td>
					
					<td><div class="list-name">发送范围：</div></td>
					<td>
						<select class="select_normal" name="sendRange">
							<option value="">请选择</option>
						<%-- 	<option value="1" <c:if test="${param.sendRange ==1}">selected</c:if> >全国</option> --%>
							<option value="4" <c:if test="${param.sendRange ==4}">selected</c:if> >城市/区县</option>
							<option value="3" <c:if test="${param.sendRange ==3}">selected</c:if> >小区</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><div class="list-name">添加时间：</div></td>
					<td colspan="2"><input type="text" name="addTimeStart" class="input_text pp icon_dt" id="date01" title="请选择起始时间" value="${param.addTimeStart }" readonly="readonly"> 至 <input name="addTimeEnd" type="text" class="input_text pp icon_dt" id="date02" title="请选择结束时间" value="${param.addTimeEnd }" readonly="readonly"></td>
					<td><div class="list-name">发送时间：</div></td>
					<td colspan="2"><input type="text" name="sendTimeStart" class="input_text pp icon_dt" id="date01" title="请选择起始时间" value="${param.sendTimeStart }" readonly="readonly"> 至 <input name="sendTimeEnd" type="text" class="input_text pp icon_dt" id="date02" title="请选择结束时间" value="${param.sendTimeEnd }" readonly="readonly"></td>
				</tr>
				<tr>
					<td colspan="6" class="t_center">
						<input class="input-btn w200" type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="weak-btn small-btn w150" type="button" value="新增" onclick="location.href='addNew.html'">
					</td>
				</tr>
			</table>
		</form>
	</div> 
	<display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI=""  partialList="true" size="resultSize">
		<display:column title="信息类型" >
			<c:if test="${row.msgType==1 }">短信</c:if>
			<c:if test="${row.msgType==2 }">push</c:if>
		</display:column>
		<display:column title="标题" property="title"/>
		<display:column title="发送范围">
			<c:if test="${row.sendRange ==1 }">全国</c:if>
			<c:if test="${row.sendRange ==4 }">城市/区县</c:if>
			<c:if test="${row.sendRange ==3 }">小区</c:if>
		</display:column>
		<display:column title="添加时间" property="sys0AddTime"/>
		<display:column title="发送时间" >
			<c:if test="${row.sendStatus == 1 }">${row.sendTime }</c:if>
		</display:column>
		<display:column title="状态" >
			<span class="sendStatusSpan">
				<c:if test="${row.sendStatus == 0 }">未发送</c:if>
				<c:if test="${row.sendStatus == 1 }">发送成功</c:if>
				<c:if test="${row.sendStatus == 2 }">发送失败</c:if>
			</span>
		</display:column>
		<display:column title="操作人" property="operatorUser"/>
		<display:column title="发送信息条数">
			<c:if test="${row.sendStatus == 1 }">${row.sendCount }</c:if>
		</display:column>
		<display:column title="操作">
			<a class="blue" href="view.html?id=${row.id }">查看</a>
			<c:if test="${row.sendStatus !=1 }"> <%-- 没有发送成功的，可以再次发送 --%>
				<a class="blue sendBtn" href="#" data-id="${row.id }">发送</a>
			</c:if>
			<c:if test="${row.sendStatus ==0 }"> <%-- 没有发送的，可以删除 --%>
				<a class="blue edit" href="edit.html?id=${row.id }">编辑</a> &nbsp;
				<a class="blue delete" href="delete.html?id=${row.id }">删除</a>
			</c:if>
		</display:column>
	</display:table>
</div>

</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js?v=5"></script>
<script src="${resourcePath}/commonjs/layer/layer.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20161128"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">
	$("a.delete").click(function(){
		if(!window.confirm("确认要删除吗？")){
			return false;
		}
	});

	$("a.sendBtn").click(function(e){
		e.preventDefault();
		if(window.confirm("确认要立即发送吗？该操作可能耗时较长，请稍后查看发送结果.")){
			var layermsg = layer.msg('正在发送，请稍候', {
        		icon: 16,
        		time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
        		shade: 0.5
        	});
			
			$this = $(this);
			var id = $this.attr("data-id");
			//send.html?id=${row.id }
			var $sendStatusSpan = $this.parents("tr").find(".sendStatusSpan");
			$sendStatusSpan.text("发送中");
			$.ajax({//使用ajax请求
				url : "send.json?id=" + id,
				async : true,
				success : function(data) {
					if(data.status == "0000"){
						alert("操作提示：" + data.message);
						location.reload();
					}else{
						alert("发送失败，失败原因："+ data.message);
					}
				},
				error: function(){
					layer.close(layermsg);
					alert("网络错误");
				}
			});
			return false;
		}
	});
</script>
	
</html>
