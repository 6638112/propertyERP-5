<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>基础收费项配置</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    <h2>基础收费项配置</h2>
    <form action="<%=basePath%>/groupBuildingBillCycle/collectFeesTypeList.html" method="post">
        <div class="bs-example bgebeb">
        <table class="info-list" border="0">
            <tr>
                <td width=""><div class="list-name">小区名称：</div></td>
                <td>
                    <select id="groupBuiliding" name="gbId" class="select_normal select2_class" datatype="*" nullmsg="请选择小区！">
                        <option value="">选择小区</option>
                    </select>
                </td>
                <td colspan="6" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
            <tr>
        </table>
    </div>
    </form>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
        <display:column title="小区名称" property="name" />
        <display:column title="操作">
            <a class="blue viewTicket" href="<%=basePath%>/propertyPayConfig/chargeItemConfig.html?gbId=${row.id}&gbName=${row.name}">收费项目设置</a>&nbsp;&nbsp;
            <a class="blue viewTicket" href="<%=basePath%>/groupBuildingBillCycle/goToCollectFeesCfg.html?gbId=${row.id}&gbName=${row.name}">收费账单设置</a>&nbsp;&nbsp;
            <a class="blue viewTicket" href="<%=basePath%>/groupBuildingBillCycle/goTolateFeeCfg.html?gbId=${row.id}&gbName=${row.name}">滞纳金设置</a>
        </display:column>
    </display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="application/javascript">
    $(function(){
        $('.select2_class').select2();

        var gbId = '${param.gbId}';
        $.ajax({
            url: '<%=basePath%>/groupBuilding/getGroupBuildings.json',
            dataType: 'json',
            success: function (data) {
                var list = JSON.parse(data);
                var strHtml = "<option value=''>选择小区</option>";
                $.each(list, function (i, item) {
                    var str = "";
                    if(gbId == item.id) {
                        str = "<option value='" + item.id + "' selected='selected'>" + item.name + "</option>";
                    } else {
                        str = "<option value='" + item.id + "'>" + item.name + "</option>";
                    }
                    strHtml += str;
                });
                $("#groupBuiliding").html(strHtml);
            }
        });
    });
</script>
</html>
