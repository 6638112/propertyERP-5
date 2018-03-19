<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<base href="<%=basePath%>//"/>--%>
    <title>拼购列表</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>拼购列表</h2>
    <form action="<%=basePath%>/ebuyProductFightGroups/list.html" method="post">
        <div class="bs-example bgebeb">
            <table class="info-list" border="0">
                <tr>
                    <td><div class="list-name">商品ID：</div></td>
                    <td><input type="number" name="productId" class="input_text w120 pp" value="${param.productId}"></td>
                    <td><div class="list-name">截团时间：</div></td>
                    <td><input type="text" name="expireDate" value="${param.expireDate}" readonly="readonly" class="input_text icon_dt" id="date01" title="请选择起始时间" placeholder="请选择起始时间"></td>
                    <td><div class="list-name">拼购状态：</div></td>
                    <td>
                        <select class="select_normal" name="fightStatus">
                            <option value="">全部</option>
                            <option value="0" <c:if test="${!empty param.fightStatus && param.fightStatus==0}">selected</c:if>>未开始</option>
                            <option value="1" <c:if test="${param.fightStatus==1}">selected</c:if>>进行中</option>
                            <option value="3" <c:if test="${param.fightStatus==3}">selected</c:if>>已结束</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><div class="list-name">商品名称：</div></td>
                    <td><input type="text" value="${param.productName}" name="productName" class="input_text w120 pp"></td>
                    <td><div class="list-name">供应商：</div></td>
                    <td><input type="text" value="${param.supplyMerchantName}" name="supplyMerchantName" class="input_text w120 pp"></td>
                    <td><div class="list-name">排序方式：</div></td>
                    <td>
                        <select class="select_normal" name="sortType">
                            <option value="">无</option>
                            <option value="1" <c:if test="${param.sortType=='1'}">selected</c:if>>新增时间倒序</option>
                            <option value="2" <c:if test="${param.sortType=='2'}">selected</c:if>>开始时间倒序</option>
                            <option value="3" <c:if test="${param.sortType=='3'}">selected</c:if>>结束时间倒序</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><div class="list-name">自提点：</div></td>
                    <td><input type="text" name="ziTiDianName" class="input_text w120 pp" value="${param.ziTiDianName}"></td>
                </tr>
                <tr>
                    <td colspan="8" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
                </tr>
            </table>
        </div>
    </form>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
        <display:column title="<input id='checkAll' type='checkbox' name='checkbox' value=''>全选"  style="width:50px;">
     		<input name="closeIds" type="checkbox" value="${row.productFightGroupsId}"></div>
    	</display:column>
        <display:column title="商品ID" property="ebuyProduct.id" />
        <display:column title="供应商" property="ebuySupplyMerchant.name" />
        <display:column title="商品名称" property="ebuyProduct.name" />
        <display:column title="自提点名称" property="ziTiDianName" />
        <display:column title="拼购价格(元)">
            ${row.ebuyProductFightGroups.priceDiscount div 100}
        </display:column>
        <display:column title="起拼人数" property="ebuyProductFightGroups.fightNum" />
        <display:column title="开始时间" property="ebuyProductFightGroups.startDate" />
        <display:column title="截团时间" property="ebuyProductFightGroups.expireDate" />
        <display:column title="状态">
            <c:choose>
                <c:when test="${row.ebuyProductFightGroups.fightStatus==0}">未开始</c:when>
                <c:when test="${row.ebuyProductFightGroups.fightStatus==1}">进行中</c:when>
                <c:when test="${row.ebuyProductFightGroups.fightStatus==3}">已结束</c:when>
                <c:otherwise>
                    未知
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column title="添加人" property="addMan" />
    	<display:column title="修改人" property="updateMan" />
        <display:column title="操作">
            <a class="blue viewTicket" href="<%=basePath%>/ebuyProductFightGroups/productGroupsDetail.html?groupId=${row.productFightGroupsId}">查看</a>
            <c:if test="${row.ebuyProductFightGroups.fightStatus!=1}">
                <a class="blue viewTicket" href="<%=basePath%>/ebuyProductFightGroups/updProductGroups.html?groupId=${row.productFightGroupsId}">编辑</a>
            </c:if>
            <c:if test="${row.ebuyProductFightGroups.fightStatus==0 || row.ebuyProductFightGroups.fightStatus==1}">
                <input class="input-btn w80 import-layer-bill-btn"  onclick="closeProductGroup(${row.productFightGroupsId})" value="关闭" type="button"/>
            </c:if>
        </display:column>
    </display:table>
    
    <div class="overflow mtop20">
	    <input class="input-btn left" onclick="batchClose()"  type="button" value="批量关闭">
    </div>

</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script>
    function batchClose() {
        var boxs = document.getElementsByName('closeIds');
        var ids = [];
        for(var i = 0; i < boxs.length; i++){
            if(boxs[i].checked){
                ids[i] = boxs[i].value;
            }
        }
        console.log(ids.length);
        if(ids.length <= 0) {
            alert("请先选择关闭项！");
        }
        var confirmText = "确认批量关闭?";
        if(confirm(confirmText)) {
            $.ajax({
                type:"post",
                url:"<%=basePath%>/ebuyProductFightGroups/batchClose.html",
                data:{'closeIds[]': ids},
                beforeSend:function(data){
                },
                success:function(data){
                    try {
                        data = eval(data);
                    } catch (e) {
                        data = eval("("+data+")");
                    }
                    if (data.status == '0000') {
                        alert("操作成功!");
                        window.location.reload();
                    } else {
                        alert(data.message);
                        return;
                    }
                }
            });
        }
    }
    function closeProductGroup(id) {
        var confirmText = "确认关闭？";
        if(confirm(confirmText)){
            $.ajax({
                type:"post",
                url:"<%=basePath%>/ebuyProductFightGroups/switchProductGroupsStatus.html",
                data:{'id': id},
                beforeSend:function(data){
                },
                success:function(data){
                    try {
                        data = eval(data);
                    } catch (e) {
                        data = eval("("+data+")");
                    }
                    if (data.status == '0000') {
                        alert("操作成功!");
                        window.location.reload();
                    } else {
                        alert(data.message);
                        return;
                    }
                }
            });
        }
    }
</script>
</html>
