<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业缴费管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css"/>
<link rel="stylesheet" type="text/css" href="../css/style.css"/>
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css"/>
</head>

<body>
<div class="info">
    <h2>欠费详情 </h2>
    <div class="mtop10">
        <table  class="info-list-01" border="0" style="margin-top:10px;text-align: center;">
            <thead>
                <tr>
                    <td rowspan="2" align="center" width="150">月份</td>
                    <td colspan="${colspan}" align="center">费用名称</td>
                </tr>
                <tr>
                    <c:forEach items="${unPaidPayBillEntityList }" var="item">
                        <c:forEach items="${item.title}" var="item1">
                            <td>${item1}</td>
                        </c:forEach>
                    </c:forEach>
                    <td>合计</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${unPaidPayBillEntityList }" var="item" varStatus="unpaidList">
                    <c:if test="${not unpaidList.first}">
                        <c:forEach items="${item}" var="map">
                            <tr>
                                <td>${map.key}</td>
                                <c:forEach items="${map.value}" var="detail" varStatus="amountIndex">
                                    <c:if test="${not amountIndex.last}">
                                        <td>
                                            <c:if test="${detail > 0}">
                                                ${detail}
                                            </c:if>
                                        </td>
                                    </c:if>
                                    <c:if test="${amountIndex.last}">
                                        <td>
                                            <a class="blue" href="javascript:void(0)" onclick="mark('${detail}')">欠费结清</a>
                                        </td>
                                    </c:if>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>   
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker01.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript" >
    function mark(payBillId){
        if (window.confirm('您确定要结清欠费？')) {
            $.post("../payBill/unpaidSettle.json", {"id":payBillId}, function(data){
                if(data.status == "0000"){
                    alert("操作提示：" + data.message);
                    document.location.reload();
                }else{
                    alert("失败");
                }
            });
        }
    }
</script>
</html>
