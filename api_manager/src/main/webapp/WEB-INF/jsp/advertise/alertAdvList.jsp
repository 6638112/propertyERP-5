<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>广告列表</h2>
    <form action="<%=basePath%>/adv/alertAdvList.html" method="post">
      <div class="bs-example bgebeb">
        <table class="info-list" border="0">
          <tr>
            <td><div class="list-name">广告名称：</div></td>
            <td><input type="text" name="advName" value="${param.advName}" class="input_text w120 pp"></td>
            <td><div class="list-name">状态：</div></td>
            <td>
              <select name="advStatus" class="select_normal">
                <option value="">全部</option>
                <option value="2" <c:if test="${param.advStatus eq 2}">selected</c:if>>进行中</option>
                <option value="3" <c:if test="${param.advStatus eq 3}">selected</c:if>>已结束</option>
                <option value="1" <c:if test="${param.advStatus eq 1}">selected</c:if>>未开始</option>
              </select>
            </td>
            <td><div class="list-name">筛选时间：</div></td>
            <td><input type="text" value="${param.advStartTime}" name="advStartTime" class="input_text icon_dt" id="date01" title="请选择起始时间"> 至
              <input type="text" value="${param.advEndTime}" name="advEndTime" class="input_text icon_dt" id="date02" title="请选择结束时间">
            </td>
          </tr>
          <tr>
            <td><div class="list-name">广告类型：</div></td>
            <td>
              <select name="advType" class="select_normal">
                <option value="">全部</option>
                <option value="1" <c:if test="${param.advType eq 1}">selected</c:if>>首页弹窗广告</option>
                <option value="2" <c:if test="${param.advType eq 2}">selected</c:if>>首页拦腰广告</option>
                <option value="3" <c:if test="${param.advType eq 3}">selected</c:if>>到家广告</option>
                <option value="4" <c:if test="${param.advType eq 4}">selected</c:if>>街坊广告</option>
                <option value="5" <c:if test="${param.advType eq 5}">selected</c:if>>到家底部广告</option>
                <option value="6" <c:if test="${param.advType eq 6}">selected</c:if>>微信购物小票广告</option>
                <option value="7" <c:if test="${param.advType eq 7}">selected</c:if>>车禁缴费广告</option>
                <option value="8" <c:if test="${param.advType eq 8}">selected</c:if>>体验店banner广告</option>
              </select>
            </td>
            <td><div class="list-name">跳转类型：</div></td>
            <td>
              <select name="jumpType" class="select_normal">
              	<option value="" <c:if test="${empty param.jumpType}">selected</c:if>>全部</option>
               	<option value="2" <c:if test="${param.jumpType eq 2}">selected</c:if>>产品(APP)</option>
                <option value="1" <c:if test="${param.jumpType eq 1}">selected</c:if>>H5页面</option>
                <option value="4" <c:if test="${param.jumpType eq 4}">selected</c:if>>不跳</option>
              </select>
            </td>
          </tr>
          <tr>
            <td colspan="6" class="t_center">
            	<input class="input-btn w200" type="submit" value="搜索"/>
            	<input class="input-btn w100" type="button" value="新增广告" style="margin-left: 1em;" onclick="openAddAdvDialog()"/>
            </td>
          </tr>
        </table>
      </div>
    </form>

  <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="total" >
    <display:column title="广告类型">
    	<c:choose>
    		<c:when test="${row.advType eq 1}">首页弹窗广告</c:when>
    		<c:when test="${row.advType eq 2}">首页拦腰广告</c:when>
    		<c:when test="${row.advType eq 3}">到家广告</c:when>
    		<c:when test="${row.advType eq 4}">街坊广告</c:when>
    		<c:when test="${row.advType eq 5}">到家底部广告</c:when>
    		<c:when test="${row.advType eq 6}">微信购物小票广告</c:when>
    		<c:when test="${row.advType eq 7}">车禁缴费广告</c:when>
            <c:when test="${row.advType eq 8}">体验店banner广告</c:when>
    		<c:otherwise>未知</c:otherwise>
    	</c:choose>
    </display:column>
    <display:column title="编号" property="id" />
    <display:column title="起始时间" property="startTime" />
    <display:column title="结束时间" property="endTime" />
    <display:column title="广告名称" property="tittle" />
    <display:column title="广告类型">
      <c:choose>
          <c:when test="${(row.advType eq 1) or row.advType eq 3}">
              <c:if test="${(row.advType eq 1) and (row.type eq 2)}">产品(APP)</c:if>
              <c:if test="${(row.advType eq 3) and (row.type eq 5)}">产品(APP)</c:if>
      		  <c:if test="${row.type eq 1}">H5页面</c:if>
              <c:if test="${row.type eq 4}">不跳</c:if>
          </c:when>
          <c:when test="${row.advType eq 2}">
          	  <c:if test="${row.type eq 3}">产品(APP)</c:if>
      		  <c:if test="${row.type eq 2}">H5页面</c:if>
              <c:if test="${row.type eq 1}">不跳</c:if>
          </c:when>
		  <c:when test="${row.advType eq 5 || row.advType eq 8}">
          	  <c:if test="${row.type eq 5}">商品详情</c:if>
      		  <c:if test="${row.type eq 1}">H5页面</c:if>
          </c:when>
      </c:choose>
    </display:column>
    <display:column title="状态">
      <c:choose>
        <c:when test="${row.status eq 1}">未开始</c:when>
        <c:when test="${row.status eq 2}">进行中</c:when>
        <c:when test="${row.status eq 3}">已结束</c:when>
        <c:otherwise>
         	 未知
        </c:otherwise>
      </c:choose>
    </display:column>
    <display:column title="添加人" property="addMan" />
    <display:column title="修改人" property="updateMan" />
    <display:column title="操作">
      <a class="blue" href="<%=basePath%>/adv/alertAdvDetail.html?advId=${row.id}&advType=${row.advType}">查看</a>&nbsp;&nbsp;
      <a class="blue" href="<%=basePath%>/adv/updAlertAdv.html?advId=${row.id}&advType=${row.advType}">编辑</a>
      <c:if test="${row.status == 2}">
        &nbsp;&nbsp;<a class="blue" href="javascript:void(0);" onclick="confirmFinish(${row.id}, ${row.advType})">结束</a>
      </c:if>
    </display:column>
  </display:table>
</div>
<%-- 广告类型弹框选择 --%>
<div class="addAdvDialog dsn" style="height: auto;padding-top:15px;padding-bottom:50px;width: 300px;">
	 <input id="printPayBillId" type="hidden" value=""/>
	 <input id="printType" type="hidden" value=""/>
	 <h2 style="margin-left: 1em;">广告类型选择</h2>
     <div class="bs-example bgebeb">
	     <table class="info-list" border="0">
	     	 <tr>
	     	 	<td>
	     	 		广告类型：
	     	 		<select class="select_normal" id="advType">
	     	 			<option value="1">首页弹窗广告</option>
	     	 			<option value="2">首页拦腰广告</option>
	     	 			<option value="3">到家广告</option>
	     	 			<option value="4">街坊广告</option>
	     	 			<option value="5">到家底部广告</option>
	     	 			<option value="6">微信购物小票广告</option>
	     	 			<option value="7">车禁缴费广告</option>
	     	 			<option value="8">体验店banner广告</option>
	     	 		</select>
	     	 	</td>
	     	 </tr>
	         <tr>
	           <td style="text-align: right;">
	           	   <input class="input-btn w160" type="button" value="确定" onclick="goAddAdvPage();"/>
	           	   <input class="input-btn w160" type="button" value="取消" onclick="closeAddAdvDialog();"/>
	           </td>
	         </tr>
	     </table>
     </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script>
  function confirmFinish(id, advType){
	  if(confirm("您确定要结束该广告吗？")){
		  $.post("<%=basePath%>/adv/finishAdv.html", {"id": id, "advType":advType}, function(data){
			  if (data.status == '0000') {
				  alert("操作成功!");
		          window.location.reload();
		      } else {
		          alert(data.message);
		      }
		  });
	  }
  }
  
  var layerPrint = null;
  function openAddAdvDialog(){
	  layerPrint = $.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
			border : [5, 0.3, '#000'],
			page: {dom : '.addAdvDialog'}
	 	});
  }
  
  function closeAddAdvDialog(){
		layer.close(layerPrint);
  }
  
  function goAddAdvPage(){
	  location = "${pageContext.request.contextPath}/adv/addAlertAdv.html?advType="+$("#advType").val();
  }
</script>
</html>
