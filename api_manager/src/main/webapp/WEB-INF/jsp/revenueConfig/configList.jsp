<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>物业管理-收益配置列表</title>
	<link rel="stylesheet" type="text/css" href="css/common.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css?v=1"/>
	<style type="text/css">
		html{
			height: auto;
		}
	</style>
</head>

<body>
<div class="info">
    <h2>收益配置详情列表</h2>
    <div class="bs-example bgebeb">
    	<form id="searchForm"  action="<%=basePath%>/revenueConfig/configList.html" method="post">
	        <table class="info-list" border="0">
	          <tr>
	            <td><div class="list-name">物业公司名称:</div></td>
	            <td>
	            	<%-- <c:if test="${empty sessionScope.companyId}">
	            		<input type="text" class="input_text w200" name="companyName" value="${param.companyName}" disa/>
	            	</c:if>
	            	<c:if test="${!empty sessionScope.companyId}">
	            		<span>${sessionScope.companyName}</span>
	            	</c:if> --%>
	            	<input type="hidden" name="companyId" value="${param.companyId}"/>
            		<input type="hidden" class="input_text pp" name="companyName" value="${param.companyName}"/>
            		<span>${param.companyName}</span>
	            </td>
	            <td><div class="list-name">收益项目类别:</div></td>
	            <td>
	            	<select name="projectType">
	            		<option value="">全部</option>
	            		<option value="1" <c:if test="${param.projectType==1}">selected="selected"</c:if> >认证门牌</option>
	            		<option value="2" <c:if test="${param.projectType==2}">selected="selected"</c:if> >服务类订单</option>
	            		<option value="3" <c:if test="${param.projectType==3}">selected="selected"</c:if> >物业宝佣金</option>
	            		<option value="4" <c:if test="${param.projectType==4}">selected="selected"</c:if> >超市收益</option>
	            		<option value="5" <c:if test="${param.projectType==5}">selected="selected"</c:if> >物业费实收</option>
	            		<option value="15" <c:if test="${param.projectType==15}">selected="selected"</c:if> >物业费补贴</option>
	            		<option value="7" <c:if test="${param.projectType==7}">selected="selected"</c:if> >其他代收费用</option>
	            	</select>
	            </td>
	            <td colspan="6"  class="t_center">
	            	<input class="input-btn w200" type="submit" value="搜索"/>
	            </td>
	          </tr>
	        </table>
        </form>
    </div>
   	<input class="info-btn save-set-prize-info-btn mtop20" type="button" value="添加规则"  onclick="toConfigAdd(myCallBack);" />
   	<!-- <a class="blue" onclick="" href="revenueConfig/configAdd.html">添加规则</a> -->
    <table class="mars info-list-02">
		<thead><tr class="title">
			<td>物业公司</td>
			<td>收益项目</td>
			<td>计算方式(%,元/个)</td>
			<td>收益总和</td>
			<td>物业收益</td>
			<td>代理收益</td>
			<td>平台收益</td>
			<td>推荐人收益</td>
			<td>剩余收益</td>
			<td>规则生效(开始)</td>
			<td>规则生效(截止)</td>
			<td>是否已生效</td>
			<td>操作</td>
		</tr></thead>
		<tbody>
		<c:forEach var="row" items="${resList}" varStatus="rowStatus">
			<c:forEach var="typeObj" items="${row.typeGpList}" varStatus="typeObjStatus">
				<c:forEach var="sigRc" items="${typeObj.revenueConfigList}" varStatus="sigRcStatus">
					<tr><!-- ondblclick="toConfigUpd(${sigRc.id},myCallBack);" -->
						<c:if test="${typeObjStatus.first && sigRcStatus.first}">
						<td rowspan="${row.subSize>0?row.subSize:1}">
							<div title="${row.companyName}" style="width:125px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${row.companyName}</div></td>
						</c:if>
						<c:if test="${sigRcStatus.first}">
						<td rowspan="${typeObj.subSize>0?typeObj.subSize:1}">
							<c:choose>
								<c:when test="${typeObj.projectType==1}">认证门牌</c:when>
								<c:when test="${typeObj.projectType==2}">服务类订单</c:when>
								<c:when test="${typeObj.projectType==3}">物业宝佣金</c:when>
								<c:when test="${typeObj.projectType==9}">停车宝佣金</c:when>
								<c:when test="${typeObj.projectType==4}">超市收益</c:when>
								<c:when test="${typeObj.projectType==5}">物业费实收</c:when>
								<c:when test="${typeObj.projectType==15}">物业费补贴</c:when>
								<c:when test="${typeObj.projectType==7}">其他代收费用</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</td>
						</c:if>
						<c:choose>
							<c:when test="${sigRc.ruleType==1}">
							<td>按百分比</td>
							<td><fmt:formatNumber value="${sigRc.totalValue}" pattern="#.##"/>%</td>
							<td><fmt:formatNumber value="${sigRc.companyValue}" pattern="#.##"/>%</td>
							<td><fmt:formatNumber value="${sigRc.agentValue}" pattern="#.##"/>%</td>
							<td><fmt:formatNumber value="${sigRc.platformValue}" pattern="#.##"/>%</td>
							<td><fmt:formatNumber value="${sigRc.recommenderValue}" pattern="#.##"/>%</td>
							<td><fmt:formatNumber value="${sigRc.repairValue}" pattern="#.##"/>%</td>
							</c:when>
							<c:otherwise><!-- 2 -->
							<td>按数量</td>
							<td>${sigRc.totalValue}(元/个)</td>
							<td>${sigRc.companyValue}(元/个)</td>
							<td>${sigRc.agentValue}(元/个)</td>
							<td>${sigRc.platformValue}(元/个)</td>
							<td>${sigRc.recommenderValue}(元/个)</td>
							<td>${sigRc.repairValue}(元/个)</td>
							</c:otherwise>
						</c:choose>
						
						<td>${fn:substring(sigRc.startDate, 0, 10)}</td>
						<td>${fn:substring(sigRc.endDate, 0, 10)}</td>
						<td>
							<c:choose>
								<c:when test="${sigRc.activeStatus==1}">是</c:when>
								<c:when test="${sigRc.activeStatus==2}">否</c:when>
								<c:otherwise>未知</c:otherwise>
							</c:choose>
						</td>
						<td><c:if test="${sigRcStatus.first}"></c:if>
							<c:if test="${sigRc.activeStatus==2}">
							<a class="blue" onclick="toConfigUpd(${sigRc.id},myCallBack);" href="javascript:void(0)">修改</a>
							<span class="grey">|</span><a class="blue" onclick="doRevenueConfigDel(${sigRc.id},this)" href="javascript:void(0)">删除</a>
							</c:if>
						<c:if test="${sigRcStatus.last}"></c:if></td>
					</tr>
				</c:forEach>
			</c:forEach>
    	</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.simple-dtpicker.js?v=1"></script>
	<script type="text/javascript" src="js/jquery.common.js"></script>
	<script type="text/javascript" src="js/revenue/layer.js"></script>
	<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script type="text/javascript" src="js/revenue/revenueConfig.js?v=2"></script>
	<c:import url="/common/page.jsp" />
</div>

<script type="text/javascript">
function freshCurrPage(){
	$("#searchForm").submit();//刷新页面
}
function toConfigAdd(callback){
	layer.open({
	    type: 2,  
	    shadeClose: false,  //true点击遮罩关闭层
	    title: false,  
	    closeBtn: [0, true],  
	    shade: [0.2, '#000'],  
	    border: [0],  
	    offset: ['15px',''], // top、left 
	    area: ['80%', '95%'], //宽高
		content:'revenueConfig/configAdd.html?companyName=${param.companyName}&companyId=${param.companyId}',
		end:function(){
			freshCurrPage();
	    }
	});
	//保存callback，根据子窗口的index来索引
    if(callback && typeof(callback) === "function"){
        share.data(parent.layer.index,callback);
    };
}
function toConfigUpd(dataId,callback){
	layer.open({
	    type: 2,  
	    shadeClose: false,  //true点击遮罩关闭层
	    title: false,  
	    closeBtn: [0, true],  
	    shade: [0.2, '#000'],  
	    border: [0],  
	    offset: ['15px',''], // top、left 
	    area: ['80%', '95%'], //宽高
		content:'revenueConfig/configUpd.html?dataId='+dataId,
		end:function(){
			window.setTimeout(freshCurrPage,1000);
			//freshCurrPage();
	    }
	});
	//保存callback，根据子窗口的index来索引
    if(callback && typeof(callback) === "function"){
        share.data(parent.layer.index,callback);
    };
}
function doRevenueConfigDel(dataId,tdTmp){
	//询问框
	layer.confirm('确认要删除？', {
	    btn: ['确认','取消'] //按钮
	},function(){//确认
		layer.close();
		$.ajax({
			type:"post",
			url:"revenueConfigJson/doRevenueConfigDel.json",
			data:{'dataId': dataId},
			dataType:"TEXT",
			beforeSend:function(data){
			},
			success:function(data){
				try {
					data = eval(data);
				} catch (e) {
					data = eval("("+data+")");
				}
				var msg = '删除成功';
				try {
					if (data.status == '0000') {
						//layer.msg('删除成功', {icon: 1});
					} else {
						msg = data.message;
						//layer.msg(data.message, {icon: 6});
					}
				} catch (e) {
					msg = data.message;
					//layer.msg(data.message, {icon: 6});
				}
				layer.alert(msg, {closeBtn: 0}, function(){
					freshCurrPage();
				});
				return;
			}
		});
		
	}, function(){});
};
</script>

</body>
</html>
