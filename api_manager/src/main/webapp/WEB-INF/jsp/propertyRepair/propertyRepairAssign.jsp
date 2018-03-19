<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修管理-报修工单管理-分配处理人</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
	<form class="inputform" method="post"
          <c:choose>
              <c:when test="${pr != null}"> action="../propertyRepair/assignRepairSave.html"</c:when>
              <c:otherwise> action="../dredge/assignMaster.html"</c:otherwise>
          </c:choose>
    >
		<input type="hidden" name="repairId" value="${pr.id}"/>
		<input type="hidden" name="dredgeId" value="${db.id}"/>
        <h2 class="phoneViewBox">报修处理流程
            <span class="f12 mar-left15">
                <span class="step"><span class="f16 bold">流程 1</span>：报修单管理</span>
                <span class="step orange"><span class="grey">-></span> <span class="f16 bold">流程 2</span>：分配处理人</span>
                <span class="step"><span class="grey">-></span> <span class="f16 bold">流程 3</span>：师傅上门处理</span>
                <span class="step"><span class="grey">-></span> <span class="f16 bold">流程 4</span>：管理处关闭工单</span>
            </span>
        </h2>
        <h2 class="mtop20">报修详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right">报修门牌号：</td>
            <td>${pr.address } ${db.address}</td>
          </tr>
          <tr>
            <td width="140" align="right">用户联系方式：</td>
            <td>${pr.tel } ${db.tel}</td>
          </tr>
          <tr>
            <td align="right">创建时间：</td>
            <td>${pr.sys0AddTime }${db.sys0AddTime }</td>
          </tr>
          <tr>
            <td align="right">报修类型：</td>
            <td>${pr.repairTypeName } ${dredgeTypeName}</td>
          </tr>
          <tr>
            <td align="right"><span class="red">业主期望师傅上门时间：</span></td>
            <td>${fn:substring(pr.expectDate,0,10)} ${fn:substring(pr.expectTimeBegin,0,5)} ${fn:substring(db.expectdate,0,16)}</td>
          </tr>
          <tr>
            <td align="right">问题描述：</td>
            <td>${pr.repairContent } ${db.content }</td>
          </tr>
        </table>
        <h2 class="mtop20">分配处理人</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="140" align="right">师傅预计上门时间：</td>
            <td><input type="text" name="estimateDoorTimeBegin" class="input_text icon_dt nocheck" id="date05" title="请选择起始时间" alt="请选择起始时间"></td>
          </tr>
            <c:if test="${db == null}">
                <tr>
                    <td align="right"></td>
                    <td>至 <input name="estimateDoorTimeEnd" type="text" class="input_text icon_dt nocheck" id="date06" title="请选择结束时间" alt="请选择结束时间"></td>
                </tr>
            </c:if>

          <tr>
            <td align="right">员工：</td>
            <td>
            	<select name="propertyRepairerId" class="select_normal" datatype="*" nullmsg="请选择处理人">
	                <option value=""></option>
                    <c:choose>
                        <c:when test="${db != null}">
                            <c:forEach items="${propertyRepairerList}" var="repairer">
                                <option value="${repairer.tUserFId }">${repairer.name }</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${propertyRepairerList}" var="repairer">
                                <option value="${repairer.id }">${repairer.name }</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                 </select>
            </td>
          </tr>
            <c:if test="${db == null}">
                <tr>
                    <td align="right">备注：</td>
                    <td><input name="asignDesc" type="text" class="input_text w500"></td>
                </tr>
            </c:if>

        </table>
        <div class="padb mtop10"><input class="info-btn checkRepair" type="submit" value="提 交" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js?v20160520"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		beforeSubmit:function(){
		    //校验预约时间  		
	   		var date01 = $('#date01').val();
	   		var date02 = $('#date02').val();
	   		if( date01 > date02){
	   			alert('师傅预计上门时间有误，请重新选择！');
	   			return false;
	   		}
		}
	});
});
</script>

</html>
