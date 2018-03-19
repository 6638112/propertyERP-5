<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>帮帮忙查询列表</title>
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="stylesheet" type="text/css" href="../css/style.css?v20161128">
	<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
	<link rel="stylesheet" type="text/css" href="../styles/displaytag-css/alternative.css">
	<style type="text/css">
		.emoji{ width: 18px; height: auto; margin-left: 2px; vertical-align: text-bottom;}
	</style>
	<script src="https://twemoji.maxcdn.com/2/twemoji.min.js"></script>
</head>

<body>
<div class="info posrelative">
	<h2>用户内容管理</h2>
	<div class="bs-example bgebeb">
		<form id="searchForm" action="<%=basePath%>/userQuestion/userQuestionList.html" method="post">
			<table class="info-list" border="0">
				<tr>
					<td><div class="list-name">小区所在地：</div></td>
					<td><input type="text" name="location" value="${param.location}" class="input_text w120 pp"></td>
					<td><div class="list-name">小区名称：</div></td>
					<td><input type="text" name="gbName" value="${param.gbName}" class="input_text w120 pp"></td>
					<td><div class="list-name">内容关键字：</div></td>
					<td><input type="text" name="content" value="${param.content}" class="input_text w120 pp"></td>
					<td><div class="list-name">状态：</div></td>
					<td>
						<select class="select_normal" name="status">
							<option value="-1">请选择</option>
							<option value="1" <c:if test="${param.status==1 }"> selected="selected"</c:if>>待处理</option>
							<option value="5" <c:if test="${param.status==5 }"> selected="selected"</c:if>>解放区回复</option>
							<option value="6" <c:if test="${param.status==6 }"> selected="selected"</c:if>>物业回复</option>
							<option value="3" <c:if test="${param.status==3 }"> selected="selected"</c:if>>已转公共维修</option>
							<option value="4" <c:if test="${param.status==4 }"> selected="selected"</c:if>>已转普通维修</option>
							<%-- <option value="2" <c:if test="${param.status==2 }"> selected="selected"</c:if>>已通知片区经理</option> --%>
						</select>
					</td>
				</tr>
				<tr>
					<td><div class="list-name">发布解放号：</div></td>
					<td><input type="text" name="huaId" value="${param.huaId}" class="input_text w120 pp"></td>
					<td><div class="list-name">发布时间：</div></td>
					<td><input type="text" value='${param.pushTimeBegin }' name="pushTimeBegin" class="input_text icon_dt" id="date01" title="请选择起始时间" value="请选择起始时间"> 至
						<input type="text" value='${param.pushTimeEnd }' name="pushTimeEnd" class="input_text icon_dt" id="date02" title="请选择结束时间" value="请选择结束时间">
					</td>
					<td colspan="4" class="t_center"><input id="searchBtn" class="input-btn w200" type="submit" value="搜索"></td>
				</tr>
			</table>
		</form>
	</div>
  
	<display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI=""  partialList="true" size="resultSize">
		<display:column title="发布小区" property="gbName"/>
		<display:column title="小区所在地" property="location"/>
		<display:column title="楼栋" property="buildingName"/>
		<display:column title="门牌号" property="roomNum"/>
		<display:column title="解放号" property="huaId"/>
		<display:column title="手机号" property="mobile"/>
		<display:column title="发布内容">
			<c:choose>
				<c:when test="${fn:length(row.content) > 20}">
					${fn:substring(row.content, 0, 20) }...
				</c:when>
				<c:otherwise>${row.content }</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="发布时间" property="addTime"/>
		<display:column title="状态">
			<c:choose>
				<c:when test="${row.status==1 }">待处理</c:when>
				<c:when test="${row.status==5 }">解放区回复</c:when>
				<c:when test="${row.status==6 }">物业回复</c:when>
				<c:when test="${row.status==3 }">已转 <a href="../propertyRepair/searchRepair.html?repairId=${row.serviceId}" class="blue">公共维修</a></c:when>
				<c:when test="${row.status==4 }">已转 <a href="../dredge/dredgeOrderList.html?dredgeBillId=${row.serviceId}" class="blue">普通维修</a></c:when>
				<c:when test="${row.status==7 }">已转 <a href="../propertyRepair/searchRepair.html?repairId=${row.serviceId}" class="blue">公共维修</a></c:when>
				<c:when test="${row.status==2 }">已通知片区经理</c:when>
			</c:choose>
		</display:column>
		<display:column style="max-width: 200px;white-space: normal;" title="回复内容">
			<%-- <c:choose>
				<c:when test="${row.status==5 }">解放区回复:</c:when>
				<c:when test="${row.status==6 }">物业回复:</c:when>
			</c:choose> --%>
			${row.answerContent}
		</display:column>
		<display:column title="操作人" property="updateUserName"/>
		<display:column title="操作时间" property="updateTime"/>
		<display:column title="操作" media="html">
			<a class="blue trackBtn" href="../userQuestion/viewUserQuestionDetail.html?uqId=${row.uqId }">查看</a>
		</display:column>
	</display:table>
</div>

</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/jquery.form.js?v=5"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js?v20161128"></script>
<script type="text/javascript">
	$("#searchBtn").click(function(){//查找
		$("#searchForm").attr("action","<%=basePath%>/userQuestion/userQuestionList.html");
		$("#searchForm").submit();
	});

	$(function(){
		//表单验证
		$(".inputform").Validform({
			tiptype:3,
			btnSubmit: '.sendBillCheckBtn',
			dragonfly:true,
			ajaxPost:false,
			ignoreHidden: true,
			postonce:false,
			beforeSubmit:function(curform){
				if($(".inputform").find("input[name='otherFee']").val() !== '' && $(".inputform").find("input[name='laborFee']").val() === '') {
					$(".inputform").find("input[name='laborFee']").siblings('.Validform_checktip').addClass('Validform_wrong').text('请填写人工费');
					return false;
				}
				$(".inputform").attr('onsubmit','return false;');
			},
			callback:function(data){
				//console.log(data);
				$(".inputform").ajaxSubmit(function(data) {
					console.log(data);
					try {
						data = eval("("+data+")");
					} catch (e) {
						try {
							data = eval(data);
						} catch (e2) {}
					}
					if (data.status == '0000') {
						alert('操作成功');
						location.reload();
					} else {
						alert("推送失败，原因：" + data.message);
					}
				});
			}
		});

	});
</script>
<script type="text/javascript">
	twemoji.parse(document.body, {"size":72});
</script>
</html>
