<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>收费账单设置</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
    <style>
    	.cfgContainer{margin-top:5px;}
    </style>
</head>

<body>
<div class="info">
    <h2>${gbName}--收费账单设置</h2>
    <%--复制使用--%>
    <div class="bs-example bgebeb cfgContainer copyDataDiv dsn">
        <form class="inputform" action="<%=basePath%>/groupBuildingBillCycle/saveCollectFeesCfg.json" method="post">
            <input type="hidden" name="tGbId" value="${gbId}"/>
            <table class="info-list-02" border="0">
                <tr>
                    <td width="180">
                        <div class="list-name">账单名称</div>
                    </td>
                    <td>
                        <input type="text" name="billName" class="input_text w120 pp" value="" maxlength="30">
                    </td>
                <tr>
                <tr>
                    <td width="180">
                        <div class="list-name">收费模式</div>
                    </td>
                    <td>
                        <select name="chargingMode" class="select_normal chargingMode" onchange="modelChange(this)">
                            <option value="1">定期缴费</option>
                            <option value="2">选择周期缴费</option>
                            <option value="3">预存收费</option>
                        </select>
                    </td>
                <tr>
                <tr>
                    <td>
                        <div class="list-name">收费期限</div>
                    </td>
                    <td class="collectFeesTime">
                        <div class="payBillDay" style="display: inline-block;">
                            <input type="text" value='' readonly="readonly" name="billPayStart" class="input_text icon_dt nocheck" title="请选择起始时间" value="请选择起始时间"> 至
                            <input type="text" value='' readonly="readonly" name="billPayEnd" class="input_text icon_dt nocheck" title="请选择结束时间" value="请选择结束时间">收取
                            <font color="red">（请选择小于等于28号的日期）</font>
                        </div>
                        <div class="billMonth" style="display: inline-block;">
                            <input class="input_text date_picker w120 ept billMonth" name="billMonthStart" value="" type="text" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择账单周期！">至
                            <input class="input_text date_picker w120 ept billMonth" name="billMonthEnd" value="" type="text" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择账单周期！">月费用
                        </div>
                        <div class="rechargePayBillDay" style="display: none;">
                        	本月<input type="text" id="startDay" name="startDay" class="input_text pp w80" value="" maxlength="2" placeholder="1-31内的整数">至
                        	<select name="rechargeMonthMode" class="select_normal">
	                            <option value="0">本月</option>
	                            <option value="1">下月</option>
	                        </select>
			           		<input type="text" id="endDay" name="endDay" class="input_text pp w80" value="" maxlength="2" placeholder="1-31内的整数">号
                        </div>
                    </td>
                <tr>
                <tr class="feeItemTr">
                    <td>
                        <div class="list-name">所含收费项</div>
                    </td>
                    <td class="copy_fee_items fee_items_old fee_items_check">

                    </td>
                    <td class="fee_items_new dsn fee_items_check">

                    </td>
                <tr>
                <tr class="chang_months dsn monthsTr">
                    <td>
                        <div class="list-name">可选月数</div>
                    </td>
                    <td class="can_change_months">
                        <c:forEach begin="0" end="23" varStatus="status">
                            <label style="cursor: pointer;"><input id="pk${status.index}" type="checkbox" style="cursor: pointer;" name="months" value="${status.index + 1}">${status.index + 1}个月</label>
                        </c:forEach>
                    </td>
                <tr>
                <tr class="isBankCollectionTr">
                    <td>
                        <div class="list-name">是否银行托收</div>
                    </td>
                    <td>
                        <c:if test="${isBankCollection}">
                            <input class="mtop3" name="bankCollectionStatus" type="radio" value="1" />是&nbsp;&nbsp;
                        </c:if>
                        <input class="mtop3" name="bankCollectionStatus" checked="checked" type="radio" value="2" />否
                    </td>
                <tr>
                <tr class="arrearsTransferTr">
                    <td>
                        <div class="list-name">是否自动计算欠费</div>
                    </td>
                    <td>
                        <input class="mtop3" name="arrearsTransfer" type="radio" value="2" />是&nbsp;&nbsp;
                        <input class="mtop3" name="arrearsTransfer" checked="checked" type="radio" value="1" />否
                    </td>
                <tr>
                <tr class="rechargeTr dsn">
                    <td>
                        <div class="list-name">收费金额限制</div>
                    </td>
                    <td>
                    	<p>
                    		可缴金额范围：
                    		<input class="mtop3 input_text w120 pp" id="minRecharge" name="minRecharge" type="number" min="0.01" max="20000" step="0.01"/>——
                        	<input class="mtop3 input_text w120 pp" id="maxRecharge" name="maxRecharge" type="number" min="0.01" max="20000" step="0.01"/>
                    	</p>
                    	<p><font color="red">（范围：0.01 —— 20,000.00，保留两位小数）</font></p>
                    </td>
                <tr>
            </table>
            <div class="padb">
                <input class="info-btn w100 saveCollectFeesCfg" type="button" value="保 存" />
                <input class="info-btn w100 delCollectFeesCfg" type="button" value="删除" />
            </div>
        </form>
    </div>

    <c:forEach items="${resList}" var="entity">
        <div class="bs-example bgebeb cfgContainer">
            <form class="inputform" action="<%=basePath%>/groupBuildingBillCycle/saveCollectFeesCfg.json" method="post">
                <input type="hidden" name="id" value="${entity.id}"/>
                <input type="hidden" name="tGbId" value="${entity.tGbId}"/>
                <table class="info-list-02" border="0">
                    <tr>
                        <td width="180">
                            <div class="list-name">账单名称</div>
                        </td>
                        <td>
                            <input type="text" name="billName" class="input_text w120 pp" value="${entity.billName}">
                        </td>
                    <tr>
                    <tr>
                        <td width="180">
                            <div class="list-name">收费模式</div>
                        </td>
                        <td>
                        	<c:choose>
                        		<c:when test="${entity.chargingMode eq 1}">定期缴费</c:when>
                        		<c:when test="${entity.chargingMode eq 2}">选择周期缴费</c:when>
                        		<c:when test="${entity.chargingMode eq 3}">预存收费</c:when>
                        	</c:choose>
                            <input type="hidden" name="chargingMode" value="${entity.chargingMode}" class="chargingMode"/>
                        </td>
                    <tr>
                    <tr>
                        <td>
                            <div class="list-name">收费期限</div>
                        </td>
                        <td class="collectFeesTime">
                        	<c:if test="${entity.chargingMode eq 1 or entity.chargingMode eq 2}">
                            <div class="payBillDay">
                                <input type="text" value='${fn:substring(entity.billPayStart, 0, 10) }' readonly="readonly" name="billPayStart" class="input_text icon_dt" id="date03" title="请选择起始时间" value="请选择起始时间"> 至
                                <input type="text" value='${fn:substring(entity.billPayEnd, 0, 10) }' readonly="readonly" name="billPayEnd" class="input_text icon_dt" id="date04" title="请选择结束时间" value="请选择结束时间">收取
                                <font color="red">（请选择小于等于28号的日期）</font>
                            </div>
                            <div class="billMonth">
                                <input class="input_text date_picker w120 ept billMonth" name="billMonthStart" value="${fn:substring(entity.billMonthStart, 0, 7) }" type="text" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择账单周期！">
                                至<input class="input_text date_picker w120 ept billMonth" name="billMonthEnd" value="${fn:substring(entity.billMonthEnd, 0, 7) }" type="text" onclick="setmonth(this)" readonly="readonly" datatype="*" nullmsg="请选择账单周期！">
                                月费用
                            </div>
                            </c:if>
                            <c:if test="${entity.chargingMode eq 3}">
	                          	  本月<input type="text" id="startDay" name="startDay" value="${entity.startDay}" class="input_text pp w80" value="" maxlength="2" placeholder="1-31内的整数">至
	                        	<select name="rechargeMonthMode" class="select_normal">
		                            <option value="0" <c:if test="${entity.rechargeMonthMode eq 0}"> selected</c:if>>本月</option>
		                            <option value="1" <c:if test="${entity.rechargeMonthMode eq 1}"> selected</c:if>>下月</option>
		                        </select>
				           		<input type="text" id="endDay" name="endDay" value="${entity.endDay}" class="input_text pp w80" value="" maxlength="2" placeholder="1-31内的整数">号
                            </c:if>
                        </td>
                    <tr>
                    <c:if test="${entity.chargingMode eq 1 or entity.chargingMode eq 2}">
                    <tr>
                        <td>
                            <div class="list-name">所含收费项</div>
                        </td>
                        <td class="fee_items_old fee_items_check">
                            常规收费项：
                            <c:if test="${not empty groupBuildingHasFeeItemEntity.fixedFeeItems or not empty entity.fixedFeeItems}">
                                <br>
                                <c:forEach items="${groupBuildingHasFeeItemEntity.fixedFeeItems}" var="fixeds">
                                    <label>
                                        <input name='fixedFeeItemsIds' type='checkbox' value='${fixeds.id}'/>${fixeds.name}&nbsp;&nbsp;
                                    </label>
                                </c:forEach>
                                <c:forEach items="${entity.fixedFeeItems}" var="fixeds">
                                    <label>
                                        <input name='fixedFeeItemsIds' type='checkbox' checked="checked" value='${fixeds.id}'/>${fixeds.name}&nbsp;&nbsp;
                                    </label>
                                </c:forEach>
                            </c:if>
                            <br>
                            <c:if test="${entity.chargingMode eq 1}"><%--固定收费模式才显示 抄表和临时--%>
                                抄表收费项：
                                <c:if test="${not empty groupBuildingHasFeeItemEntity.mrFeeItems or not empty entity.mrFeeItems}">
                                    <br>
                                    <c:forEach items="${groupBuildingHasFeeItemEntity.mrFeeItems}" var="mrs">
                                        <label>
                                            <input name='mrFeeItemsIds' type='checkbox' value='${mrs.id}'/>${mrs.name}&nbsp;&nbsp;
                                        </label>
                                    </c:forEach>
                                    <c:forEach items="${entity.mrFeeItems}" var="mrs">
                                        <label>
                                            <input name='mrFeeItemsIds' type='checkbox' checked="checked" value='${mrs.id}'/>${mrs.name}&nbsp;&nbsp;
                                        </label>
                                    </c:forEach>
                                </c:if>
                                <br>
                                临时收费项：
                                <c:if test="${not empty groupBuildingHasFeeItemEntity.tmpFeeItems or not empty entity.tmpFeeItems}">
                                    <br>
                                    <c:forEach items="${groupBuildingHasFeeItemEntity.tmpFeeItems}" var="temps">
                                        <label>
                                            <input name='tmpFeeItemsIds' type='checkbox' value='${temps.id}'/>${temps.name}&nbsp;&nbsp;
                                        </label>
                                    </c:forEach>
                                    <c:forEach items="${entity.tmpFeeItems}" var="temps">
                                        <label>
                                            <input name='tmpFeeItemsIds' type='checkbox' checked="checked" value='${temps.id}'/>${temps.name}&nbsp;&nbsp;
                                        </label>
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </td>
                        <td class="fee_items_new dsn fee_items_check">

                        </td>
                    <tr>
                    <tr class="chang_months <c:if test="${entity.chargingMode eq 1}">dsn</c:if>">
                        <td>
                            <div class="list-name">可选月数</div>
                        </td>
                        <td class="can_change_months">
                            <c:forEach begin="0" end="23" varStatus="status">
                                <c:if test="${(status.index + 1) % entity.billMonthSize == 0}">
                                    <label style="cursor: pointer;">
                                        <input id="pk${status.index}" type="checkbox"
                                        <c:forEach items="${entity.months}" var="alterMonths">
                                               <c:if test="${(status.index + 1) == alterMonths}">checked="checked"</c:if>
                                        </c:forEach>
                                               style="cursor: pointer;" name="months" value="${status.index + 1}">${status.index + 1}个月
                                    </label>
                                </c:if>
                            </c:forEach>
                        </td>
                    <tr>
                    <tr>
                        <td>
                            <div class="list-name">是否银行托收</div>
                        </td>
                        <td>
                            <c:if test="${isBankCollection}">
                                <input class="mtop3" name="bankCollectionStatus" <c:if test="${entity.bankCollectionStatus==1 }" >checked="checked" </c:if> type="radio" value="1" />是&nbsp;&nbsp;
                            </c:if>
                            <input class="mtop3" name="bankCollectionStatus" <c:if test="${entity.bankCollectionStatus==2 }" >checked="checked" </c:if> type="radio" value="2" />否
                        </td>
                    <tr>
                    <tr>
                        <td>
                            <div class="list-name">是否自动计算欠费</div>
                        </td>
                        <td>
                            <input class="mtop3" name="arrearsTransfer" <c:if test="${entity.arrearsTransfer==2 }" >checked="checked" </c:if> type="radio" value="2" />是&nbsp;&nbsp;
                            <input class="mtop3" name="arrearsTransfer" <c:if test="${entity.arrearsTransfer==1 }" >checked="checked" </c:if> type="radio" value="1" />否
                        </td>
                    <tr>
					</c:if>
                    <c:if test="${entity.chargingMode eq 3}">
				<tr>
                    <td>
                        <div class="list-name">收费金额限制</div>
                    </td>
                    <td>
                    	<p>
                    		可缴金额范围：
                    		<input class="mtop3 input_text w120 pp" id="minRecharge" name="minRecharge" type="number" value="${entity.minRecharge}" min="0.01" max="20000" step="0.01"/>——
                        	<input class="mtop3 input_text w120 pp" id="maxRecharge" name="maxRecharge" type="number" value='${entity.maxRecharge}' min="0.01" max="20000" step="0.01"/>
                    	</p>
                    	<p><font color="red">（范围：0.01 —— 20,000.00，保留两位小数）</font></p>
                    </td>
                <tr>
					</c:if>
                </table>
                <div class="padb">
                    <input class="info-btn w100 saveCollectFeesCfg" type="button" value="保 存" />
                    <input class="info-btn w100 delCollectFeesCfg" type="button" value="删除" />
                </div>
            </form>
        </div>
    </c:forEach>
    <div class="padb">
        <input class="info-btn w100 addCollectFeesCfg" type="button" value="添加" />
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/merchant/Validform_v5.3.2.js?v20151029"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/DatePicker.js"></script>
<script type="text/javascript">
	function isInteger(obj) {
 		return /^[0-9]+?$/.test(obj);
	}

	function isTwoNum(obj){
		return /^[0-9]+(\.[0-9]{0,2})?$/.test(obj);
	}

    $(function(){
        var gbId = '${gbId}';
        //添加
        var trNum = 4;
        $(".addCollectFeesCfg").click(function () {
            $.ajax({
                url: '<%=basePath%>/groupBuildingBillCycle/getGroupBuildingHasFeeItems.json?gbId='+gbId,
                dataType: 'json',
                success: function (data, status) {

                    var divObj = $(".copyDataDiv:hidden").clone(true);

                    //设置收费项信息
                    divObj.find(".copy_fee_items").empty();
                    divObj.find(".copy_fee_items").html(data.dataValue);

                    trNum++;
                    var newId01 = 'newdate' + trNum;
                    divObj.find('.input_text.icon_dt').eq(0).attr('id', newId01);
                    trNum++;
                    var newId02 = 'newdate' + trNum;
                    divObj.find('.input_text.icon_dt').eq(1).attr('id', newId02);

                    divObj.removeClass("dsn");
                    $(".info").append(divObj);

                   $('input[id=' + newId01 + '], input[id=' + newId02 + ']').appendDtpicker({
                        "closeOnSelected":true,
                        "locale":"cn",
                        "autodateOnStart":false
                    });
                    window.parent.TuneHeight();
                }
            });
        });

        var isSubmit = true;
        //保存
        $(".saveCollectFeesCfg").click(function () {
            if(!isSubmit) {
                return false;
            }
            //表单验证
            var formObj = $(this).parents("form");
            if(formObj.find("input[name='billName']").val() == null || "" == formObj.find("input[name='billName']").val()) {
                alert("请填写账单名称！");
                return false;
            } else if(formObj.find("input[name='billName']").val().length > 30) {
                alert("账单名称不能大于30字！");
                return false;
            }
            if(formObj.find(".chargingMode").val() == 1 || formObj.find(".chargingMode").val() == 2) {
            	 if(formObj.find("input[name='billPayStart']").val() == null || "" == formObj.find("input[name='billPayStart']").val()) {
                     alert("缴费开始时间不能为空！");
                     return false;
                 }
                 if(formObj.find("input[name='billPayEnd']").val() == null || "" == formObj.find("input[name='billPayEnd']").val()) {
                     alert("缴费截止时间不能为空！");
                     return false;
                 }
                 if(formObj.find("input[name='billMonthStart']").val() == null || "" == formObj.find("input[name='billMonthStart']").val()) {
                     alert("缴费月份不能为空！");
                     return false;
                 }
                 if(formObj.find(".fee_items_check").find("input:checkbox:checked").length==0){
                     alert("请选择“费用项”！");
                     return false;
                 }
            } else {
            	 var startDay = formObj.find("input[name='startDay']").val();
            	 if(startDay == null || "" == startDay) {
                     alert("收费期限开始时间不能为空！");
                     return false;
                 } else if(!isInteger(startDay) ||startDay<=0||startDay>31){
					 alert("收费期限开始日期范围为1~31之间的整数！");
                     return false;
				 }
				 
                 var endDay = formObj.find("input[name='endDay']").val();
                 if(endDay == null || "" == endDay) {
                     alert("收费期限截止时间不能为空！");
                     return false;
                 } else if(!isInteger(endDay) ||endDay<=0||endDay>31){
					 alert("收费期限截止日期范围为1~31之间的整数！");
                     return false;
				 }
				 
				 var minRecharge = formObj.find("input[name='minRecharge']").val();
            	 if(minRecharge == null || "" == minRecharge) {
                     alert("可缴金额最小值不能为空！");
                     return false;
                 } else if(!isTwoNum(minRecharge)){
					 alert("可缴金额最小值范围为0.01~20000，最多只能输入两位小数！");
                     return false;
				 } else if(minRecharge<0.01||minRecharge>20000){
					 alert("可缴金额最小值范围为0.01~20000！");
                     return false;
				 }

				 var maxRecharge = formObj.find("input[name='maxRecharge']").val();
            	 if(maxRecharge == null || "" == maxRecharge) {
                     alert("可缴金额最大值不能为空！");
                     return false;
                 } else if(!isTwoNum(maxRecharge)){
					 alert("可缴金额最大值范围为0.01~20000，最多只能输入两位小数！");
                     return false;
				 } else if(maxRecharge<0.01||maxRecharge>20000){
					 alert("可缴金额最大值范围为0.01~20000！");
                     return false;
				 }
				 if(minRecharge*1.0>maxRecharge*1.0) {
                     alert("可缴金额最小值不能大于最大值！");
                     return false;
                 }
			}

            if(formObj.find(".chargingMode").val() == 2) {
                var start = formObj.find("input[name='billMonthStart']").val();
                var end = formObj.find("input[name='billMonthEnd']").val();
                var size = getMonthSize(start, end);
                var monthArray = [];//用户选择的月数
                formObj.find(".can_change_months").find("input:checkbox:checked").each(function(){
                    monthArray.push($(this).val());
                });
                if(monthArray.length <= 0) {
                    alert("请选择“可缴月份”！");
                    return false;
                }

                var monthArray02 = [];//总的可选月数
                for (var i = 1; i <= 24 ; i ++) {
                    if(i % size == 0) {
                        monthArray02.push(i);
                    }
                }
                var isRight = 0;
                for(var i = 0; i < monthArray.length; i++) {
                    for (var j = 0; j < monthArray02.length; j++) {
                        if (monthArray[i] == monthArray02[j]) {
                            isRight += 1;
                        }
                    }
                }

                if(isRight != monthArray.length) {
                    alert("可缴月份与时间跨度不匹配！");
                    return false;
                }

            }
            //增加菊花
            var layermsg = null;
            layermsg = layer.msg('正在保存数据，请稍候', {
                icon: 16,
                time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
                shade: 0.5
            });

           isSubmit = false;
           formObj.ajaxSubmit({
                success: function(data){
                    isSubmit = true;
                    alert(data.message);
                    window.location.href = "<%=basePath%>/groupBuildingBillCycle/goToCollectFeesCfg.html?gbName=${gbName}&gbId="+${gbId};
                },
                error: function(){
                    isSubmit = true;
                    alert("系统异常，保存失败！");
                }
            });
        });

        /**校验可选月数 选择周期*/
        $(".billMonth").on('blur',function(e){
            var cycleType = $(this).parents(".inputform").find(".select_normal").val();
            if(cycleType == undefined) {
                cycleType = $(this).parents(".inputform").find(".chargingMode").val();
            }
            if(cycleType == 1) {
                return false;
            }

            var strat = "";
            var end = "";
            if($(this).attr("name") == "billMonthEnd") {
                end = $(this).val();
                strat = $(this).siblings("input[name='billMonthStart']").val();
            } else {
                strat = $(this).val();
                end = $(this).siblings("input[name='billMonthEnd']").val();
            }
            var size = getMonthSize(strat, end);
            if(size >= 1) {
                var html = "";
                for (var i = 1; i <= 24 ; i ++) {
                    if(i % size == 0) {
                        html +="<label style=\"cursor: pointer;\"><input type=\"checkbox\" style=\"cursor: pointer;\" name=\"months\" value=\""+i+"\">"+i+"个月</label>";
                    }
                }
                $(this).parents(".inputform").find(".can_change_months").html(html);
            }
        });

        /**
         * 删除收费账单配置
         * */
        $(".delCollectFeesCfg").click(function () {
            var id = $(this).parents(".inputform").find("input[name='id']").val();
            if(id == "" || id == null || id == undefined) {
            	var delDom = $(this).parent().parent().parent();
            	delDom.animate({height:"0px"}, 500).delay(400).queue(function(){
					delDom.remove();
				});
                return false;
            }
            $.ajax({
                url: '<%=basePath%>/groupBuildingBillCycle/delCollectFeesCfg.json?gbId=${gbId}&id='+id,
                dataType: 'json',
                success : function (data, status) {
                    alert(data.message);
                    window.location.href = "<%=basePath%>/groupBuildingBillCycle/goToCollectFeesCfg.html?gbName=${gbName}&gbId="+${gbId};
                }
            })
        });
    });

    function getMonthSize(strat, end) {
        var startDate=new Date(strat.replace("-", "/").replace("-", "/"));
        var endDate=new Date(end.replace("-", "/").replace("-", "/"));
        var number = 0;
        var yearToMonth = (endDate.getFullYear() - startDate.getFullYear()) * 12;
        number += yearToMonth;
        var monthToMonth = endDate.getMonth() - startDate.getMonth();
        number += monthToMonth;
        return number + 1;
    }

    /**
     * 收费模式切换
     */
    function modelChange(obj) {
        var gbId = '${gbId}';
        var cycelType = obj.value;
        if(cycelType==1 || cycelType==2){
        	$.ajax({
                url: '<%=basePath%>/groupBuildingBillCycle/getGroupBuildingHasFeeItems.json?cycleType='+cycelType+'&gbId='+gbId,
                dataType: 'json',
                success: function (data, status) {
                    //设置收费项信息
                    $(obj).parents(".inputform").find(".fee_items_old").addClass("dsn");
                    $(obj).parents(".inputform").find(".fee_items_new").removeClass("dsn");
                    $(obj).parents(".inputform").find(".fee_items_new").html(data.dataValue);
                }
            });
        }
        if(cycelType == 2) {//选择周期
            $(".rechargeTr").hide();
            $(".feeItemTr").show();
            $(".monthsTr").show();
            $(".isBankCollectionTr").show();
            $(".arrearsTransferTr").show();
            
            $(".payBillDay").show();
            $(".billMonth").show();
            $(".rechargePayBillDay").hide();
        } else if(cycelType == 1){
            $(".rechargeTr").hide();
            $(".feeItemTr").show();
            $(".monthsTr").hide();
            $(".isBankCollectionTr").show();
            $(".arrearsTransferTr").show();
            
            $(".payBillDay").show();
            $(".billMonth").show();
            $(".rechargePayBillDay").hide();
        } else if(cycelType == 3){
        	$(".rechargeTr").show();
            $(".feeItemTr").hide();
            $(".monthsTr").hide();
            $(".isBankCollectionTr").hide();
            $(".arrearsTransferTr").hide();

            $(".payBillDay").hide();
            $(".billMonth").hide();
            $(".rechargePayBillDay").show();
        }
    }

    $(function(){
        $("tr").unbind();
    });
</script>
</html>
