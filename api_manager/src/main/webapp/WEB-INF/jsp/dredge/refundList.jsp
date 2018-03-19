<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<base href="<%=basePath%>//"/>--%>
    <title>系统管理-维修管理-退款订单列表</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info">
    <h2>退款订单列表</h2>
    <form action="<%=basePath%>/dredgeRefund/refundList.html" method="post">
        <div class="bs-example bgebeb">
            <table class="info-list" border="0">
                <tr>
                    <td>
                        <div class="list-name">订单号：</div>
                    </td>
                    <td>
                        <input class="input_text w120 pp" type="text" name="billNo" value="${param.billNo }">
                    </td>
                    <td>
                        <div class="list-name">商品名称：</div>
                    </td>
                    <td>
                        <input class="input_text w120 pp" type="text" name="dredgeProductName" value="${param.dredgeProductName}">
                    </td>
                    <td>
                        <div class="list-name">联系人：</div>
                    </td>
                    <td>
                        <input class="input_text w120 pp" type="text" name="linkName" value="${param.linkName}">
                    </td>
                    <td>
                        <div class="list-name">联系电话：</div>
                    </td>
                    <td>
                        <input class="input_text w120 pp" type="text" name="linkPhone" value="${param.linkPhone}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="list-name">支付流水号：</div>
                    </td>
                    <td>
                        <input class="input_text w120 pp" type="text" name="flowNo" value="${param.flowNo}">
                    </td>
                    <td>
                        <div class="list-name">支付方式：</div>
                    </td>
                    <td>
                        <select class="select_normal w131" name="payMethod">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${param.payMethod=='1' }"> selected="selected"</c:if>>微信支付</option>
                            <option value="2" <c:if test="${param.payMethod=='2' }"> selected="selected"</c:if>>支付宝支付</option>
                            <option value="9" <c:if test="${param.payMethod=='9' }"> selected="selected"</c:if>>银行卡支付</option>
                            <option value="4" <c:if test="${param.payMethod=='4' }"> selected="selected"</c:if>>粮票支付</option>
                            <option value="7" <c:if test="${param.payMethod=='7' }"> selected="selected"</c:if>>优惠券支付</option>
                        </select>
                    </td>
                    <td class="black">退款状态</td>
                    <td>
                        <select class="select_normal w131" name="refundStatus">
                            <option value="">全部</option>
                            <option value="1" <c:if test="${param.refundStatus=='1' }"> selected="selected"</c:if>>待审核</option>
                            <option value="2" <c:if test="${param.refundStatus=='2' }"> selected="selected"</c:if>>已退款</option>
                            <option value="3" <c:if test="${param.refundStatus=='3' }"> selected="selected"</c:if>>退款失败</option>
                        </select>
                    </td>
                    <td><div class="list-name">申请时间：</div></td>
                    <td><input type="text" value='${param.applyTimeStart }' name="applyTimeStart" class="input_text icon_dt" id="date01" title="请选择起始时间" value="请选择起始时间"> 至
                        <input type="text" value='${param.applyTimeEnd }' name="applyTimeEnd" class="input_text icon_dt" id="date02" title="请选择结束时间" value="请选择结束时间">
                    </td>
                </tr>
                <tr>
                    <td colspan="8" class="t_center">
                        <input class="input-btn w200" type="submit" value="搜索">
                        <input onclick="exportRefund()" class="weak-btn small-btn w150" type="button" value="导出">
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <display:table name="resList" id="row" class="mars info-list-02 mtop20" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="resultSize" >
        <display:column title="订单号" property="billNo" />
        <display:column title="商品名称" property="dredgeProductName" />
        <display:column title="支付时间" property="payTime"/>
        <display:column title="支付方式" property="payMethodDesc"/>
        <display:column title="支付流水号" property="flowNo"/>
        <display:column title="联系人" property="linkName" />
        <display:column title="联系电话" property="linkPhone" />
        <display:column title="订单总额" property="billTotalAmountDecimal"/>
        <display:column title="申请退款金额" property="refundAmountDecimal"/>
        <display:column title="申请退款粮票" property="refundCouponAmountDecimal"/>
        <display:column title="退款原因" property="refundReason"/>
        <display:column title="备注" property="note"/>
        <display:column title="申请退款时间" property="applyTime"/>
        <display:column title="退款完成时间" property="auditTime"/>
        <display:column title="退款方式" property="refundTypeDesc"/>
        <display:column title="退款状态" property="refundStatusDesc"/>
        <display:column title="操作">
        	<c:if test="${row.refundStatus == 1 }">
	            <a class="blue pop-box-btn" href="javascript:void(0)" data-id="${row.id }">审核</a>
            </c:if>
        	<c:if test="${row.refundStatus != 1 }">
	            <a class="blue pop-box-btn" href="javascript:void(0)" data-id="${row.id }">查看</a>
            </c:if>
        </display:column>
    </display:table>

</div>

<div class="layer-classify audit-info dsn" style="padding-bottom: 60px;">
    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
      <tr class="title">
        <td>订单号</td>
        <td>商品名称</td>
        <td>订单总额</td>
        <td>支付方式</td>
        <td>支付时间</td>
        <td>优惠券抵扣</td>
        <td>粮票抵扣</td>
        <td>用户实付</td>
      </tr>
      <tr>
        <td class="order-no"></td>
        <td class="item-name"></td>
        <td class="order-total"></td>
        <td class="pay-type"></td>
        <td class="pay-time"></td>
        <td class="coupon-reduce"></td>
        <td class="ticket-reduce"></td>
        <td class="user-pay"></td>
      </tr>
    </table>
    <table class="info-list mtop10" border="0">
        <tr>
            <td width="100" align="right">退款方式：</td>
            <td colspan="3" class="refund-type"></td>
        </tr>
        <tr>
            <td align="right">实付金额退款：</td>
            <td width="30%"><span class="refund-amount"></span> 元</td>
            <td width="80" align="right">粮票退款：</td>
            <td><span class="refund-coupon-amount"></span> 元</td>
        </tr>
        <tr>
            <td align="right">退款原因：</td>
            <td colspan="3" class="refund-reason"></td>
        </tr>
        <tr>
            <td align="right">备注：</td>
            <td colspan="3" class="note"></td>
        </tr>
        <tr>
            <td align="right">申请账号：</td>
            <td colspan="3" class="apply-user"></td>
        </tr>
        <tr class="audit-info-row dsn">
            <td align="right">审核说明：</td>
            <td colspan="3">
				<textarea id="auditReason" class="textareas" style="width:50%; height: 50px;" name="auditReason" cols="" rows="3"></textarea>
			</td>
        </tr>
        <tr class="view-info-row dsn">
            <td align="right">审核说明：</td>
            <td colspan="3" class="audit-reason"></td>
        </tr>
        <tr class="view-info-row dsn">
            <td align="right">审核账号：</td>
            <td colspan="3" class="audit-acount"></td>
        </tr>
        <tr class="view-info-row dsn">
            <td align="right">审核结果：</td>
            <td colspan="3" class="audit-result"></td>
        </tr>
    </table>
    <div class="mtop20 t_center audit-info-row dsn">
	    <input class="info-btn small-btn w80 audit-check-btn" type="button" value="审核通过">
	    <input class="input-btn w100 mar-left15 audit-check-btn" type="button" value="审核不通过" />
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script>
$(function(){

	var refundId;
	//审核、查看弹窗
	$('.pop-box-btn').click(function(){

		var curBtnText = $(this).text();
		refundId = $(this).attr('data-id');
		$(this).addClass('cur-pop-btn');
		//获取对应订单信息
		$.ajax({
			type: 'get',
			url: '../dredgeRefund/refundDetail.html',
			data: {'refundId': refundId},
			success: function(data){
				if(data.status === '0000'){
					//审核订单
					if(curBtnText === '审核'){
						getRefundInfo('.audit-info', data.dataValue.detail);
						$('.view-info-row').addClass('dsn');
						$('.audit-info-row').removeClass('dsn');

						$.layer({
							type: 1,
							shade: [0.4,'#000000'],
							area: ['auto', 'auto'],
							title: false,
							border : [5, 0.3, '#000'],
							page: {dom : '.audit-info'}
						});

					//查看订单
					}else{
						getRefundInfo('.audit-info', data.dataValue.detail);
						$('.audit-info').find('.audit-acount').text(data.dataValue.detail.auditUser);
						$('.audit-info').find('.audit-result').text(data.dataValue.detail.refundStatusDesc);
						$('.audit-info').find('.audit-reason').text(data.dataValue.detail.auditReason);

						$('.audit-info-row').addClass('dsn');
						$('.view-info-row').removeClass('dsn');

						$.layer({
							type: 1,
							shade: [0.4,'#000000'],
							area: ['auto', 'auto'],
							title: false,
							border : [5, 0.3, '#000'],
							page: {dom : '.audit-info'}
						});
					}
				}
			}
		});

	});

	//审核结果
	$('.audit-check-btn').click(function(){
		var result = $(this).val() === '审核通过' ? 'pass' : 'notPass';
		var auditReason = $.trim($('#auditReason').val());
		
		if(auditReason === '' && result === 'notPass'){
			alert('请输入审核说明');
			return false;
		}
		
		if(!confirm('确认操作？')){
			return false;
		}
		$.ajax({
			type: 'post',
			url: '../dredgeRefund/audit.html',
			data: {'refundId': refundId, 'result': result, 'auditReason': $('#auditReason').val()},
			success: function(data){
				if(data.status === '0000'){
					alert('操作成功！');
					if(result === 'pass'){
						$('.cur-pop-btn').parent('td').prev('td').text('退款成功');
					}else{
						$('.cur-pop-btn').parent('td').prev('td').text('退款失败');
					}
					$('.cur-pop-btn').text('查看').removeClass('cur-pop-btn');
					layer.closeAll();
				}else{
					alert(data.message);
					$('.cur-pop-btn').removeClass('cur-pop-btn');
				}
			}
		});
	});

})

function getRefundInfo(obj, data){
	$(obj).find('.order-no').text(data.billNo);
	$(obj).find('.item-name').text(data.dredgeProductName);
	$(obj).find('.order-total').text((data.billTotalAmountDecimal).toFixed(2));
	$(obj).find('.pay-type').text(data.payMethodDesc);
	$(obj).find('.pay-time').text(data.payTime);
	$(obj).find('.coupon-reduce').text((data.couponDiscountMoney/100).toFixed(2));
	$(obj).find('.ticket-reduce').text(((data.orderCouponAmount - data.couponDiscountMoney)/100).toFixed(2));
	$(obj).find('.user-pay').text((data.orderAmount/100).toFixed(2));
	
	$(obj).find('.refund-type').text(data.refundTypeDesc);
	$(obj).find('.refund-amount').text((data.refundAmountDecimal).toFixed(2));
	$(obj).find('.refund-coupon-amount').text((data.refundCouponAmountDecimal).toFixed(2));
	$(obj).find('.refund-reason').text(data.refundReason);
	$(obj).find('.note').text(data.note);
	$(obj).find('.apply-user').text(data.applyUser);
}

function exportRefund() {
    window.location.href="export.html";
}
</script>
</html>
