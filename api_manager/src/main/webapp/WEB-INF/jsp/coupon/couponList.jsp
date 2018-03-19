<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>电商-消费券配置-券列表</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>券列表</h2>
    <form action="<%=basePath%>/coupon/couponList.html" method="post">
        <div class="bs-example bgebeb">
        <table class="info-list" border="0">
            <tr>
                <td>
                    <div class="list-name">券属性：</div>
                </td>
                <td>
                    <select class="select_normal w131" name="useType">
                        <option value="">全部</option>
                        <option value="0" <c:if test="${!empty param.useType && param.useType==0}">selected</c:if>>通用</option>
                        <option value="1" <c:if test="${param.useType==1}">selected</c:if>>超市</option>
                        <option value="2" <c:if test="${param.useType==2}">selected</c:if>>物业</option>
                        <option value="3" <c:if test="${param.useType==3}">selected</c:if>>维修</option>
                        <option value="4" <c:if test="${param.useType==4}">selected</c:if>>车禁</option>
                        <option value="5" <c:if test="${param.useType==5}">selected</c:if>>定向商户</option>
                        <option value="6" <c:if test="${param.useType==6}">selected</c:if>>定向社区店商品</option>
                        <option value="7" <c:if test="${param.useType==7}">selected</c:if>>定向到家商品</option>
                    </select>
                </td>
                <td>
                    <div class="list-name">排序方式：</div>
                </td>
                <td>
                    <select class="select_normal w131" name="queryOrderByType">
                        <option value="5" <c:if test="${param.queryOrderByType==5}">selected</c:if>>默认</option>
                        <option value="1" <c:if test="${param.queryOrderByType==1}">selected</c:if>>结束时间顺序</option>
                        <option value="2" <c:if test="${param.queryOrderByType==2}">selected</c:if>>结束时间倒序</option>
                        <option value="3" <c:if test="${param.queryOrderByType==3}">selected</c:if>>余券顺序</option>
                        <option value="4" <c:if test="${param.queryOrderByType==4}">selected</c:if>>余券倒序</option>
                    </select>
                </td>
                <td>
                    <div class="list-name">券状态：</div>
                </td>
                <td>
                    <select class="select_normal w131" name="couponStatus">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${param.couponStatus==1}">selected</c:if>>开启</option>
                        <option value="2" <c:if test="${param.couponStatus==2}">selected</c:if>>关闭</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="list-name">券类别：</div>
                </td>
                <td>
                    <select class="select_normal w131" name="couponType">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${param.couponType==1}">selected</c:if>>现金券</option>
                    </select>
                </td>
                <td>
                    <div class="list-name">地域属性：</div>
                </td>
                <td>
                    <select class="select_normal w131" name="sendAreaType">
                        <option value="">全部</option>
                        <option value="1" <c:if test="${param.sendAreaType==1}">selected</c:if>>全国</option>
                        <option value="2" <c:if test="${param.sendAreaType==2}">selected</c:if>>城市</option>
                        <option value="3" <c:if test="${param.sendAreaType==3}">selected</c:if>>小区</option>
                    </select>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="6" class="t_center"><input class="input-btn w200" type="submit" value="搜索"></td>
            </tr>
        </table>
    </div>
    </form>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
        <display:column title="编号" property="id" />
        <display:column title="券名称" property="couponName" />
        <display:column title="券属性">
            <c:choose>
                <c:when test="${row.useType==0}">通用</c:when>
                <c:when test="${row.useType==1}">超市</c:when>
                <c:when test="${row.useType==2}">物业</c:when>
                <c:when test="${row.useType==3}">维修</c:when>
                <c:when test="${row.useType==4}">车禁</c:when>
                <c:when test="${row.useType==5}">定向商户</c:when>
                <c:when test="${row.useType==6}">定向社区店商品</c:when>
                <c:when test="${row.useType==7}">定向到家商品</c:when>
                <c:otherwise>
                    未知
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column title="券类别">
            <c:choose>
                <c:when test="${row.couponType==1}">现金券</c:when>
                <c:when test="${row.couponType==2}">折扣券</c:when>
                <c:when test="${row.couponType==3}">实物兑换券</c:when>
                <c:otherwise>
                    未知
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column title="地域">
            <c:choose>
                <c:when test="${row.sendAreaType==1}">全国</c:when>
                <c:when test="${row.sendAreaType==2}">城市</c:when>
                <c:when test="${row.sendAreaType==3}">小区</c:when>
                <c:otherwise>
                    未知
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column title="发券开始时间" property="sendStartDate" />
        <display:column title="发券截止时间" property="sendEndDate" />
        <display:column title="券状态">
            <c:if test="${row.couponStatus == 1}">开启</c:if>
            <c:if test="${row.couponStatus == 2}">关闭</c:if>
        </display:column>
        <display:column title="余券">${row.sendTotal - row.sendCount}</display:column>
        <display:column title="发券渠道">
        	<c:choose>
        		<c:when test="${(not empty row.goalType) and (row.goalType eq 1)}">超市购物</c:when>
        		<c:when test="${(not empty row.goalType) and (row.goalType eq 2)}">缴物业费</c:when>
        		<c:when test="${(not empty row.goalType) and (row.goalType eq 3)}">维修家政</c:when>
        		<c:when test="${(not empty row.goalType) and (row.goalType eq 4)}">缴停车费</c:when>
        		<c:when test="${(not empty row.goalType) and (row.goalType eq 66)}">手动领取</c:when>
        		<c:when test="${(not empty row.goalType) and (row.goalType eq 67)}">分享得券</c:when>
        	</c:choose>
        </display:column>
        <display:column title="添加人" property="addMan" />
    	<display:column title="修改人" property="updateMan" />
        <display:column title="操作">
            <c:if test="${row.couponStatus == 1}">
                <input class="input-btn w80 import-layer-bill-btn" onclick="changeStatus(${row.id}, 2)" value="关闭" type="button"/>
            </c:if>
            <c:if test="${row.couponStatus == 2}">
                <input class="input-btn w80 import-layer-bill-btn <c:if test="${!row.canOpen}">grey</c:if>" <c:if test="${!row.canOpen}">disabled</c:if>  onclick="changeStatus(${row.id}, 1)" value="开启" type="button"/>
            </c:if>
            <a class="blue viewTicket" href="<%=basePath%>/coupon/couponDetail.html?couponId=${row.id}">查看</a>
            <c:if test="${row.canEdit}">
                <a class="blue viewTicket" href="<%=basePath%>/coupon/updCoupon.html?couponId=${row.id}">编辑</a>
            </c:if>
        </display:column>
    </display:table>

</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script>
    function changeStatus(id, desStatus) {
        var confirmText;
        if(desStatus == 1) {
            confirmText = "确认开启？";
        } else if(desStatus == 2) {
            confirmText = "确认关闭？";
        }
        if(confirm(confirmText)){
            $.ajax({
                type:"post",
                url:"<%=basePath%>/coupon/switchCoupon.html",
                data:{'id': id, 'couponStatus': desStatus},
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
//                        $(tdTmp).parents('tr').remove();
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
