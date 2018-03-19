<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>轻应用活动运营-活动管理-活动列表</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>活动列表</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm"  action="<%=basePath%>/prizeActivity/activityList.html" method="post">
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">活动名称：</div></td>
            <td><input type="text" class="input_text w120" name="name" value="${param.name }" /></td>
            <td><div class="list-name">活动状态：</div></td>
            <td>
                    <select class="select_normal" name="activityStatus">
                        <option value="0" <c:if test="${param.activityStatus eq 0}">selected</c:if>>全部</option>
                        <option value="1" <c:if test="${param.activityStatus eq 1}">selected</c:if>>已开启</option>
                        <option value="2" <c:if test="${param.activityStatus eq 2}">selected</c:if>>未开启</option>
                        <option value="3" <c:if test="${param.activityStatus eq 3}">selected</c:if>>进行中</option>
                        <option value="4" <c:if test="${param.activityStatus eq 4}">selected</c:if>>已结束</option>
                    </select>
            </td>
            <td><div class="list-name">活动时间：</div></td>
            <td><input type="text" class="input_text icon_dt" id="date01" title="请选择起始时间" value="${param.startTime }" name="startTime"/> 至 <input type="text" class="input_text icon_dt" id="date02" title="请选择结束时间" value="${param.endTime }" name="endTime"/></td>
          </tr>
          <tr>
            <td colspan="6" class="t_center"><input class="input-btn w200" type="submit" value="搜索"/></td>
          </tr>
        </table>
        </form>
    </div>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
		<display:column title="活动编号" property="id" />
		<display:column title="活动名称" property="name" />
		<display:column title="开始时间" property="startTime" />
		<display:column title="结束时间" property="endTime" />
		<display:column title="活动状态">
			<c:if test="${row.qryStatus==1}">已开启</c:if>
			<c:if test="${row.qryStatus==2}">未开启</c:if>
			<c:if test="${row.qryStatus==3}">进行中</c:if>
			<c:if test="${row.qryStatus==4}">已结束</c:if>
		</display:column>
			
		<display:column title="操作">
		 	<a class="blue prize-info-btn" href="<%=basePath%>/prizeActivity/activityDetail.html?actId=${row.id }">查看</a> 
		 	<c:if test="${row.qryStatus==2 or row.qryStatus==1}">
		 		<span class="grey">|</span><a class="blue prize-info-btn" href="<%=basePath%>/prizeActivity/activityUpd.html?actId=${row.id }">编辑</a>
		 		<span class="grey">|</span> <a class="blue" onclick="doActivityDel(${row.id},this)" href="javascript:void(0)">删除</a>
		 	</c:if>
		</display:column>
    </display:table>
</div>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
<script type="text/javascript" src="busiJS/prizeActivity.js"></script>
<script type="text/javascript">
	function doActivityDel(actId,tdTmp){
		if(confirm("确认删除?")){
			$.ajax({
				type:"post",
				url:"<%=basePath%>/prizeActivityJson/doActivityDel.json",
				data:{'actId': actId},
				dataType:"TEXT",
				beforeSend:function(data){
				},
				success:function(data){
					try {
						data = eval(data);
					} catch (e) {
						data = eval("("+data+")");
					}
					try {
						if (data.status == '0000') {
							alert("删除活动成功!");
							$(tdTmp).parents('tr').remove();//TODO 刷新本页面？
						} else {
							alert(data.message);
							return;
						}
					} catch (e) {
						alert(data.message);
					}
				}
			});
		}
	};
</script>
</body>

</html>
