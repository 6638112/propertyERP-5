<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>结算中心-列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>结算中心</h2>
    <div class="bs-example bgebeb">
    <form action="../revenue/search.html">
        <table class="info-list" border="0">
          <tr>
            <td><div align="right">物业公司：</div></td>
            <td><input class="input_text w120" type="text" name="pcName"></td>
            <td><div align="right">小区：</div></td>
            <td>
              <select id="groupBuiliding" name="gbName" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
                  <option value="">选择小区</option>
              </select>
            </td>
            <td><div class="list-name">结算月份：</div></td>
            <td><input class="input_text w120" value="" id="begintime" name="startTime" type="text" onclick="setmonth(this)" readonly="readonly" /> 至 <input class="input_text w120" name="endTime" value="" id="begintime" type="text" onclick="setmonth(this)" readonly="readonly" /></td>
          </tr>
          <tr>
            <td colspan="6" align="center"><input class="info-btn small-btn w100" type="submit" value="查 询" />&nbsp;&nbsp;&nbsp;&nbsp;<!--  <input class="weak-btn small-btn w150" type="button" value="导出查询结果报表"> --></td>
          </tr>
        </table>
     </form>
    </div>      
    
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" decorator="org.displaytag.decorator.TotalTableDecorator">
		<display:column title="物业公司" property="pcName" sortable="true"/>
		<display:column title="小区名" property="gbName" sortable="true"/>
		<display:column title="月份" property="settleMonth" />
		<%-- <display:column title="模块" property="modelName" /> --%>
		<display:column title="收益项目" property="revItem" />
		<%-- <display:column title="收益单价"  property="f_unit" /> --%>
		<display:column title="收益结算数量" property="settleCount" style="text-align: right;" />
		<%-- <display:column title="收益额(元)" property="revAmount" total="true" /> --%>
	</display:table>
</div>
</body>

<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript" >
    $(function(){
        var gbName = '${param.gbName}';
        $.ajax({
            url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
            dataType: 'json',
            success: function (data) {
                var list = JSON.parse(data);
                var strHtml = "<option value=''>选择小区</option>";
                $.each(list, function (i, item) {
                    var str = "";
                    if(gbName == item.name) {
                        str = "<option value='" + item.name + "' selected='selected'>" + item.name + "</option>";
                    } else {
                        str = "<option value='" + item.name + "'>" + item.name + "</option>";
                    }
                    strHtml += str;
                });
                $("#groupBuiliding").html(strHtml);
                $('.select2_class').select2();
            }
        });
    });
</script>
</html>
