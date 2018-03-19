<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>收费项设置</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/select2.css">
    <style>
		.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
		.w180{width: 170px;}
	</style>
</head>
<body>
<table class="info-list-02 feeItem dsn" border="0">
    <input type="hidden" data-type="new_add" class="feeItemId" value=""/>
    <tr>
        <td width="120" align="right">
            <label style="cursor: pointer;"><input id="" data-type="new_add" type="checkbox" style="cursor: pointer;" name="feeItemId" value=""></label>
        </td>
        <td class="bold">
           <div style="width: 20%">
               <select class="select_feeItem_name select2_class" style="width: 50% !important" datatype="*" nullmsg="请选择费用项！">
                   <option value="">选择费用项</option>
                   <c:forEach items="${itemList}" var="feeItem">
                       <option value="${feeItem.id}" data="${feeItem.calculateType}">${feeItem.name}</option>
                   </c:forEach>
               </select>
               <span class="Validform_checktip"></span>
           </div>
            <div style="width: 80%"></div>
        </td>
    </tr>
    <tr>
        <td align="right"><span class="red">*</span> 金额：</td>
        <td><input type="text" class="input_text pp" id="" name="totalPrice" value="" maxlength="10" datatype="numberFixTwo" nullmsg="请填写正确的金额！"> 元<span class="Validform_checktip"></span></td>
    </tr>
    <tr class="priceUnitValue dsn">
        <td align="right"><span class="red">*</span> 建筑面积：</td>
        <td><input type="text" class="input_text pp" id="" name="priceUnitValue" value="" maxlength="10" datatype="numberFixTwo" nullmsg="请填写正确建筑面积！"> 平方米<span class="Validform_checktip"></span></td>
    </tr>
    <tr class="signalPrice dsn">
        <td align="right"><span class="red">*</span> 单价：</td>
        <td><input type="text" class="input_text pp" id="" name="signalPrice" value="" maxlength="10" datatype="numberFixTwo" nullmsg="请填写正确金额！"> 元<span class="Validform_checktip"></span></td>
    </tr>
    <tr>
        <td align="right"><span class="red">*</span> 费用起始时间：</td>
        <td>
            <input type="text" id="" name="billMonthStart" title="请选择时间" value="" placeholder="请选择时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp"/><span class="Validform_checktip"></span>
        </td>
    </tr>
</table>
<div class="info">
    <form class="inputform">
        <h2>收费项配置</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr>
                <td width="120" align="right">小区名称：</td>
                <td>${entity.gbName}</td>
            </tr>
            <tr>
                <td align="right">楼栋：</td>
                <td>${entity.bName}</td>
            </tr>
            <tr>
                <td align="right">单元号：</td>
                <td>${entity.unitName}</td>
            </tr>
            <tr>
                <td align="right">房间号：</td>
                <td>${entity.roomNumTail}</td>
            </tr>
        </table>
        <c:forEach items="${entity.editFeeItemList}" var="feeItem">
        <table class="info-list-02" border="0">
            <input type="hidden" class="feeItemId" value="${feeItem.id}"/>
            <tr>
                <td width="120" colspan="2" class="bold">
                    <label style="cursor: pointer;"><input id="" type="checkbox" style="cursor: pointer;" name="feeItemId" value="${feeItem.id}">
                            ${feeItem.name}
                    </label>
                </td>
            </tr>
            <tr>
                <td  width="120" align="right"><span class="red">*</span> 金额：</td>
                <td><input type="text" class="input_text pp" id="${feeItem.id}_totalPrice" name="totalPrice" value="${feeItem.totalPrice div 100}" maxlength="10" datatype="numberFixTwo" nullmsg="请填写金额！"> 元</td>
            </tr>
            <c:if test="${(feeItem.calculateType eq 1) or (feeItem.calculateType eq 3)}"><%--单价*建筑面积--%>
                <tr>
                    <td align="right"><span class="red">*</span> 建筑面积：</td>
                    <td><input type="text" class="input_text pp" id="${feeItem.id}_priceUnitValue" name="priceUnitValue" value="${feeItem.priceUnitValue div 100}" maxlength="10" datatype="numberFixTwo" nullmsg="请填写建筑面积！"> 平方米</td>
                </tr>
                <tr>
                    <td align="right"><span class="red">*</span> 单价：</td>
                    <td><input type="text" class="input_text pp" id="${feeItem.id}_signalPrice" name="signalPrice" value="${feeItem.signalPrice div 100}" maxlength="10" datatype="numberFixTwo" nullmsg="请填写金额！"> 元</td>
                </tr>
            </c:if>
           <tr>
               <td align="right"><span class="red">*</span> 费用起始时间：</td>
               <td>
                   <input type="text" id="${feeItem.id}_billMonthStart" name="billMonthStart" title="请选择时间" value="${feeItem.billMonthStart}" placeholder="请选择时间" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd'});" class="input_text pp w180 icon_dt pp">
               </td>
           </tr>
        </table>
        </c:forEach>
        <div class="padb">
            <input id="addRoomOpt" class="info-btn" type="button" value="添 加" />
            <input id="saveRoomOpt" class="info-btn" type="button" value="保 存" />
            <input id="delRoomOpt" class="info-btn" type="button" value="删除已勾选项" />
            <input id="returnList" class="info-btn" type="button" value="返回" />
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/setMainFrameUrl.js"></script> <!--仅限本地专用-->
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>
<script type="text/javascript">
    $(function(){
        //表单验证
        $(".inputform").Validform({
            tiptype:3,
            btnSubmit: '#saveRoomOpt',
            postonce:false,
            ignoreHidden: true,
            beforeSubmit:function(){
                //生成数据
                var items = getTotalItemInfo();
                if(items == '') {
                    return false;
                }
                console.log("items=" +items);
                //提交
                $.post('${pageContext.request.contextPath}/fixedFeeCfg/updateFeeDetail.json', {"items":JSON.stringify(items),"realRoomId":${entity.realRoomId},"gbId":${entity.gbId}}, function(data,status){
                    if(data.status=="0000"){
                        alert(data.message);
                        location="${pageContext.request.contextPath}/fixedFeeCfg/editFeeDetail.html?dataId=${dataId}";
                    } else {
                        alert(data.message);
                    }
                });

                return false;
            }
        });

        function getTotalItemInfo() {
            var checkStatus = true;
            var itemInfo = [];
            $(".feeItemId").each(function () {
                var id = $(this).val();
                var totalPrice = $("#"+id+"_totalPrice").val();
                var priceUnitValue = $("#"+id+"_priceUnitValue").val();
                var signalPrice = $("#"+id+"_signalPrice").val();
                var billMonthStart = $("#"+id+"_billMonthStart").val();

                if(totalPrice==""){
                	alert("金额不能为空！");
                    checkStatus = false;
                	return false;
                }
                if(billMonthStart==""){
                	alert("费用起始时间不能为空！");
                    checkStatus = false;
                	return false;
                }
                var singleItemInfo = {};
                if(id !== '') {
                    //修改--对应的是房间数据ID
                    if($(this).attr("data-type") !== "new_add") {
                        singleItemInfo.id = id;
                    } else {
                        //新增的只有费用项ID
                        singleItemInfo.fixedFeeItemId = id;
                    }
                    if(totalPrice !== '') {
                        singleItemInfo.totalPrice = dealDoubleError(totalPrice*100);
                    }
                    if(priceUnitValue !== '') {
                        singleItemInfo.priceUnitValue = dealDoubleError(priceUnitValue*100);
                    }
                    if(signalPrice !== '') {
                        singleItemInfo.signalPrice = dealDoubleError(signalPrice*100);
                    }
                    singleItemInfo.billMonthStart = billMonthStart;
                    itemInfo.push(singleItemInfo);
                }
            });
            return checkStatus ? itemInfo : "";
        }

        //添加收费项：输入格式框
        $("#addRoomOpt").click(function () {
            var divObj = $(".feeItem:hidden").clone(true);
            divObj.removeClass("dsn");
            divObj.insertBefore('.padb');
            divObj.find('.select2_class').select2();
            window.parent.TuneHeight();
        });
        //费用项下拉改变时改变数据
        $(".select_feeItem_name").change(function () {
            var feeItemId = $(this).find("option:selected").val();
            var calculateType = $(this).find("option:selected").attr("data");
            if(calculateType == undefined) {
                calculateType = 0;
            }
            if(calculateType == 1 || calculateType == 3) {
                $(this).parents("table").find(".priceUnitValue").removeClass("dsn");
                $(this).parents("table").find(".signalPrice").removeClass("dsn");
                $(this).parents("table").find("input[name=priceUnitValue]").attr("id", feeItemId + "_priceUnitValue");
                $(this).parents("table").find("input[name=signalPrice]").attr("id", feeItemId + "_signalPrice");
            } else {
                $(this).parents("table").find(".priceUnitValue").addClass("dsn");
                $(this).parents("table").find(".signalPrice").addClass("dsn");
                $(this).parents("table").find("input[name=priceUnitValue]").attr("id", "");
                $(this).parents("table").find("input[name=signalPrice]").attr("id", "");
            }
            $(this).parents("td").find("input[type=checkbox]").val(feeItemId);
            $(this).parents("table").find("input[type=hidden]").val(feeItemId);
            $(this).parents("table").find("input[name=totalPrice]").attr("id", feeItemId + "_totalPrice");
            $(this).parents("table").find("input[name=billMonthStart]").attr("id", feeItemId + "_billMonthStart");
            window.parent.TuneHeight();
        });
        //删除收费项
        $("#delRoomOpt").click(function () {
            var ids = "";
            $("input[name=feeItemId]").each(function () {
                if($(this).attr("data-type") !== "new_add") {
                    if($(this).is(':checked')){
                        ids += $(this).val() + ",";
                    }
                }
            });
            if(ids.length > 1) {
                //提交
                $.post('${pageContext.request.contextPath}/fixedFeeCfg/delFeeDetail.json', {"ids":ids,"realRoomId":${entity.realRoomId},"gbId":${entity.gbId}}, function(data,status){
                    if(data.status=="0000"){
                        alert(data.message);
                        location="${pageContext.request.contextPath}/fixedFeeCfg/editFeeDetail.html?dataId=${dataId}";
                    } else {
                        alert(data.message);
                    }
                });
            } else {
                location="${pageContext.request.contextPath}/fixedFeeCfg/editFeeDetail.html?dataId=${dataId}";
            }
        });

        $("#returnList").click(function () {
            window.location.href = "${pageContext.request.contextPath}/fixedFeeCfg/jumpToCreateFeeDetail.html?gbId=${entity.gbId}&gbName=${entity.gbName}";
        });
    });

    function dealDoubleError(number) {
        var number = number;      // 3637 ----36369999999999995
        number.toFixed(2);           //    "0.10"
        typeof number.toFixed(2);    //    "string"
        number = +number.toFixed(2); //
        return number;
    }
</script>
</html>
