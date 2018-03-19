<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<base href="<%=basePath%>//"/>--%>
    <title>自提点列表</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info">
    <h2>自提点列表</h2>

    <form action="<%=basePath%>/ebuyFightSupplyMerchant/list.html" method="post">
        <div class="bs-example bgebeb">
            <table class="info-list" border="0">
                <tr>
                    <td><div class="list-name">城市：</div></td>
                    <td><input type="text" class="input_text w120 pp" name="cityName" value="${param.cityName}"></td>
                    <td><div class="list-name">自提点名称：</div></td>
                    <td><input type="text" class="input_text w120 pp" name="fightMerchantName" value="${param.fightMerchantName}"></td>
                    <td><div class="list-name">小区名称：</div></td>
                    <td><input type="text" class="input_text w120 pp" name="groupBuildingName" value="${param.groupBuildingName}"></td>
                </tr>
                <tr>
                    <td colspan="8" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
                </tr>
            </table>
        </div>
    </form>

    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize">
        <display:column title="城市" property="cityName"/>
        <display:column title="自提点名称" property="ebuyFightSupplyMerchant.name"/>
        <display:column title="自提地址" property="ebuyFightSupplyMerchant.delivAddress"/>
        <display:column title="联系电话" property="ebuyFightSupplyMerchant.tel"/>
        <display:column title="覆盖小区数" property="groupBuildingCount"/>
        <display:column title="添加人" property="addMan" />
    	<display:column title="修改人" property="updateMan" />
        <display:column title="操作">
            <a class="blue viewTicket" href="<%=basePath%>/ebuyFightSupplyMerchant/fightMerchantDetail.html?fightMerchantId=${row.fightMerchantId}">查看</a>
            <a class="blue viewTicket" href="<%=basePath%>/ebuyFightSupplyMerchant/updFightMerchant.html?fightMerchantId=${row.fightMerchantId}">编辑</a>
        </display:column>
    </display:table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
</html>
