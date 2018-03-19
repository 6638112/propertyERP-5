<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>收费项设置</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
    	.td_right{text-align: right;}
    	.td_center{text-align: center;}
    </style>
</head>
<body>
<div class="info">
    <h2 class="mtop20">${gbName}-收费项基础数据管理</h2>
    <div class="bs-example bgebeb">
        <form id="searchForm" method="post" action="${pageContext.request.contextPath}/fixedFeeCfg/jumpToCreateFeeDetail.html">
            <input type="hidden" id="gbId" name="gbId" value="${gbId}"/>
            <input type="hidden" id="gbName" name="gbName" value="${gbName}"/>
            <table class="info-list" border="0">
                <tr>
                    <td><div class="list-name">楼栋：</div></td>
                    <td><input type="text" value="${param.bName }" class="input_text pp w120" name="bName" /> </td>
                    <td><div class="list-name">单元：</div></td>
                    <td><input type="text" class="input_text pp w120" value="${param.unitName }" name="unitName" /></td>
                    <td><div class="list-name">房号：</div></td>
                    <td><input type="text" class="input_text pp w120" value="${param.roomNumTail }" name="roomNumTail" /></td>
                </tr>
                <tr>
                    <td align="center" colspan="6">
                        <div class="mtop20">
                            <input class="info-btn small-btn w150" type="submit" value="查 询">
                            <input id="importBill" class="weak-btn small-btn w100 mleft20" type="button" value="导入数据">
                            <%--<input id="clearBtn" class="weak-btn small-btn w100 mleft20" type="button" value="清除数据">--%>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
	<table class="mars info-list-02 mtop20" id="feeDataTable">
		<thead>
		  <tr class="title" style="text-align: center;">
			<td rowspan="2">楼栋</td>
			<td rowspan="2">单元</td>
			<td rowspan="2">房号</td>
			<c:forEach items="${feeItems}" var="feeItem">
				<c:choose>
					<c:when test="${feeItem.calculateType eq 2}">
						<td colspan="2">${feeItem.name}</td>
					</c:when>
					<c:otherwise>
						<td colspan="4">${feeItem.name}</td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<td rowspan="2">操作</td>
		  </tr>
		  <tr class="title" style="text-align: center;">
			  <c:forEach items="${feeItems}" var="feeItem">
					<c:choose>
						<c:when test="${feeItem.calculateType eq 1}">
							<td>费用合计</td>
							<td>单价</td>
							<td>建筑面积</td>
							<td>费用起始时间</td>
						</c:when>
						<c:when test="${feeItem.calculateType eq 2}">
							<td>费用合计</td>
							<td>费用起始时间</td>
						</c:when>
						<c:when test="${feeItem.calculateType eq 3}">
							<td>费用合计</td>
							<td>单价</td>
							<td>用量</td>
							<td>费用起始时间</td>
						</c:when>
					</c:choose>
			  </c:forEach>
		  </tr>
		</thead>
		<tbody>
			<c:forEach items="${resList}" var="row">
				<tr>
					<td>${row.bName}</td>
					<td>${row.unitName}</td>
					<td>${row.roomNumTail}</td>
				<%-- <c:set var="totalFee" value="0"/> --%>
			        <c:forEach items="${feeItems}" var="feeItem">
			        	<c:set var="isExistItem" value="0"/>
		                <c:forEach items="${row.hasFeeItemList}" var="hasFeeItem">
		                    <c:if test="${hasFeeItem.tFixedFeeItemId == feeItem.id}">
		                    	<c:set var="isExistItem" value="1"/>
								<td class="td_right">
									<fmt:formatNumber value="${(hasFeeItem.totalPrice+0.00000000001)/100}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
								</td>
								<c:if test="${feeItem.calculateType ne 2}">
									<td class="td_right">
										<fmt:formatNumber value="${(hasFeeItem.signalPrice+0.00000000001)/100}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
									</td>
									<td class="td_right">
										<fmt:formatNumber value="${(hasFeeItem.priceUnitValue+0.00000000001)/100}" type="currency" pattern="#,##0.00" maxFractionDigits="2"/>
									</td>
								</c:if>
								<td>
		                        	<c:if test="${not empty hasFeeItem.billMonthStart}">
		                        		${fn:substring(hasFeeItem.billMonthStart,0,10)}
		                        	</c:if>
		                        </td>
		                        <%-- <c:set value="${totalFee + hasFeeItem.totalPrice}" var="totalFee"/> --%>
		                    </c:if>
		                </c:forEach>
		                <c:if test="${isExistItem eq 0}">
			                <c:choose>
								<c:when test="${(feeItem.calculateType eq 1) or (feeItem.calculateType eq 3)}">
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</c:when>
								<c:when test="${feeItem.calculateType eq 2}">
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</c:when>
							</c:choose>
		                </c:if>
			        </c:forEach>
			        <td class="td_center">
				        <a class="blue view-room-btn" href="${pageContext.request.contextPath}/fixedFeeCfg/qryFeeDetail.html?dataId=${row.fixedFeeItemHasRoomDataId}">查看</a> |
			            <a class="blue edit-room-btn" href="${pageContext.request.contextPath}/fixedFeeCfg/editFeeDetail.html?dataId=${row.fixedFeeItemHasRoomDataId}">修改</a> |
			            <%--<a class="blue" data-id="${row.fixedFeeItemHasRoomDataId}" href="javascript:void(0);" onclick="deleteItemData(this)">删除</a>--%>
			        </td>
		        </tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
	<jsp:include page="/common/page.jsp"></jsp:include> 
</div>
<div class="layer-bill dsn">
    <h3>常规收费项数据导入</h3>
    <div class="mtop20">第一步：<a id="downLoadBtn" class="blue" href="javascript:exportExcelModel(${gbId})"><img src="${pageContext.request.contextPath}/images/download-icon.jpg" /> 下载账单模版</a></div>
    <form id="inputFileForm" class="mtop10" enctype="multipart/form-data" action="${pageContext.request.contextPath}/fixedFeeCfg/importFeeDetail.html" method="post">
        <input type="hidden" name="gbId" value="${gbId }" />
        <input type="hidden" name="gbName" value="${gbName}" />
        <div class="mtop10">第二步：
            上传文件：<input type="file" id="excelFileInput" name="excelFile" size="50" title="上传新账单"><br />
            <input class="input-btn w80 mtop20" type="button" id="uploadBtn02" onclick="submitValidate();" value="上传" />
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    $(function(){
        //计算方式配置
        $('.calculateType').click(function(){
            var $thisInput = $(this).parents('tr').find('.calculate-name');
            var thisInputVal = $thisInput.val();
            //var thisId = $thisInput.attr('data-itemid');
            if(thisInputVal == ''){
                alert('请先填写收费名称！');
            }
        });

        //清除数据
        $("#clearBtn").click(function(){
            var rows = $("#feeDataTable tbody tr").length;
            if(rows > 0) {
                if(confirm('确定清除所有数据？')){
                    $.get("${pageContext.request.contextPath}/fixedFeeCfg/delAllDetail.json", {"gbId":$.trim($("#gbId").val())}, function(data, status){
                        if(data.status=="0000"){
                            alert(data.message);
                            location.reload();
                        } else {
                            alert(data.message);
                        }
                    });
                }
            } else {
                alert("没有任何数据，无需清除！");
            }
        });

        //清除数据
        $(".delete-room-btn").click(function(){
            if(confirm('确定删除本条数据？')){
                $(this).parents('tr').remove();
                alert('清除成功！');
            }
        })

    });

    function submitValidate(){
        var fullFileName = $("#excelFileInput").val(); //选中文件的全名，包含路径
        var d = fullFileName.length - ".xls".length;
        if(fullFileName == ""){
            alert("请先选中一个上传文件");
            return false;
        }else if(fullFileName.indexOf(".xls") !=d){
            alert("上传的必须是后缀名为xls的Excel文件，请先下载模板进行编辑后再上传");
            return false;
        }
        $("#uploadBtn02").attr("disabled",true);
        $("#uploadBtn02").css({"background":"#DCDCDC","color":"#FFFFFF","border":"0px","cursor":"wait"});
        //增加菊花
        var layermsg = null;
        layermsg = layer.msg('正在导入，请稍候', {
            icon: 16,
            time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
            shade: 0.5
        });
        $("#inputFileForm").submit();
    }

    function exportExcelModel(gbId){
        location.href= "${pageContext.request.contextPath}/fixedFeeCfg/exportTemplate.html?gbId="+gbId;
    }
    
  	//删除收费项明细
    function deleteItemData(o){
    	var $this = $(o);
        if(confirm('确认删除该项？')){
            $.get("${pageContext.request.contextPath}/fixedFeeCfg/delFixedFeeDetail.json", {id: $this.attr('data-id')}, function(data, status){
                if(data.status=="0000"){
                    alert(data.message);
                    location.reload();
                } else {
                    alert(data.message);
                }
            });
        }
    }
</script>
</html>
