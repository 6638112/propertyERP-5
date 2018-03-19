<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统管理-周边上门业务-师傅审核</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>师傅审核</h2>
    <div class="bs-example bgebeb">
    <form action="masterlist.html">
        <table class="info-list citySelect" border="0">
          <tr>
            <td align="right">认证状态：</td>
            <td><select name="auditStatus" class="select_normal w131">
                    <option value="-1">全部</option>
                    <option value="0" <c:if test="${param.auditStatus==0 }"> selected="selected"</c:if> >未提交认证</option>
                    <option value="1" <c:if test="${param.auditStatus==1 }"> selected="selected"</c:if>>待认证</option>
                    <option value="2" <c:if test="${param.auditStatus==2 }"> selected="selected"</c:if>>认证成功</option>
                    <option value="3" <c:if test="${param.auditStatus==3 }"> selected="selected"</c:if>>认证失败</option>
                    </select></td>
            <td><div class="list-name">服务类别：</div></td>
            <td>
                <select name="dtId" class="select_normal w200">
                 <option value="-1">全部</option>
                 <c:forEach items="${dtList}" var="dt">
                 	<option value="${dt.dtId }" <c:if test="${param.dtId==dt.dtId }"> selected="selected"</c:if> >${dt.dtFullName }</option>
                 </c:forEach> 
                </select>
            </td>
            <td><div class="list-name">注册手机号：</div></td>
            <td><input name="mobile" type="text" class="input_text pp w120" value="${param.mobile }"></td>
            <td><div class="list-name">真实姓名：</div></td>
            <td><input name="realName" type="text" class="input_text pp w120" value="${param.realName }"></td>
            <td><div class="list-name">身份证号码：</div></td>
            <td><input name="idNumber" type="text" class="input_text pp w120" value="${param.idNumber }"></td>
          </tr>
          <tr>
            <td align="right">服务区域：</td>
            <td class="citySelect" colspan="3">
            <select name="apId" id="province" onchange="onSelectChange(this,'city');" class="province select_normal" data-first-title="选择省" title="选择省">
            	<option value="-1">选择省</option> 
               	<c:forEach items="${pcbList}" var="pcb" >
               		<option value="${pcb.id}" <c:if test='${param.apId == pcb.id }'> selected </c:if> >${pcb.name}</option>
	            </c:forEach>
            </select> 
            <select name="acId" id="city" onchange="onSelectChange(this,'block');" class="city select_normal" data-first-title="选择市">
            	<option value="-1">选择市</option>
            </select> 
            <select name="abId" id="block" class="area select_normal" data-first-title="选择区">
            	<option value="-1">选择区</option>
            </select>
            </td>
            <td><div class="list-name">注册时间：</div></td>
            <td colspan="3"><input type="text" name="registTimeBegin" class="input_text pp icon_dt" id="date01" title="请选择起始时间" value="${param.registTimeBegin }"> 至 <input name="registTimeEnd" type="text" class="input_text pp icon_dt" id="date02" title="请选择结束时间" value="${param.registTimeEnd }"></td>
            <td><div class="list-name">解放号：</div></td>
            <td><input name="huaId" type="text" class="input_text pp w120" value="${param.huaId }"></td>
          </tr>
        </table>
  		<div class="mtop10 t_center"><input class="input-btn w200" type="submit" value="搜索"></div>
  		</form>
    </div>
    
    <display:table class="info-list-02 mtop20" cellpadding="0" cellspacing="1"  name="resList" id="row" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize"> 
    	<display:column title="编号" property="dwId" />
    	<display:column title="解放号" property="huaId" />
    	<display:column title="注册手机号" property="mobile"  />
    	<display:column title="姓名" property="realName" />
    	<display:column property="registTime" title="注册时间" />
    	<display:column property="auditDesc" title="认证状态" />
    	<display:column property="idNumber" title="身份证号" />
    	<display:column  title="操作">
    		<a class="blue checkMasterInfo" href="masterViewDetail.html?dwId=${row.dwId }">查看</a>
    		<c:choose>
    			<c:when test="${row.certificateStatus==1 }"><a class="blue checkMasterInfo" href="masterAudit.html?dwId=${row.dwId }">审核</a></c:when>
    		</c:choose>
    	</display:column>
    	<display:column title="抽佣比例配置"> 
    		<c:if test="${row.certificateStatus==2}"><span class='blue' onclick="toConfigAdd('${row.dwId }');"> 配置规则 </span></c:if>
    	</display:column>
    </display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/provinceCityBlock.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>

<script>
	function toConfigAdd(dwId){
		//iframe层
		$.layer({
		  type: 2,
		  shadeClose: true,
		  title: false,
		  closeBtn: [0, false],
		  shade: [0.5, '#000'],
		  border: [0],
		  offset: ['20px',''],
		  area: ['650px', ($(window).height() - 450) +'px'],
		  iframe: {src: 'configAdd.html?dwId=' + dwId}
		}); 
	}
	
	$(function(){

	var apId = '${param.apId}';
		if (apId != undefined && apId != '') {
			$("#province").change();
			var acId = '${param.acId}';
			$("#city").val(acId);
			$("#city").change();
			
			setTimeout(function(){
				var abId = '${param.abId}';
				$("#block").val(abId);
				},800
			)
		}
	})
</script>
</html>
