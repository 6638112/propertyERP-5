<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@ include file="/head.jsp"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务列表</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>
<div class="info"  style="width:800px; margin:10px auto; " >
     <table id="row" class="mars info-list-02 mtop20" border="0" cellpadding="0" cellspacing="0">
      <thead>
      <tr class="title">
        <th colspan="2"><div align="left">任务列表</div></th>
      </tr>
      </thead>
    
      <tr class="total">
		<td width="700">物业宝抵扣账单</td>
        <td>
        	<span class="executeTask blue" data-taksId='financeDeductionTask' data-method='financeDeduction'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">海吉星商品同步</td>
        <td>
        	<span class="executeTask blue" data-taksId='productImpotter4HJXTask' data-method='executeImportTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">流量充值</td>
        <td>
        	<span class="executeTask blue" data-taksId='flowRechargeTask' data-method='financeDeduction'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">其它代收费</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synPropertyOtherFeeForPropertyCompany'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">物业实收</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synPropertyRealPayAmoutForPropertyCompany'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">电商收益</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synEbuyForSupply'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">师傅提现</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synDredgePayAmoutForMaster'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">物业收益</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synFinance'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">停车费</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synCar'>执行</span>
        </td>
      </tr>
      
      
      <tr class="total">
		<td width="700">物业宝抵扣</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synFianceDedu'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">停车宝抵扣</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueTask' data-method='synCarDedu'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">合并提款单</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueApplyPush2EASTask' data-method='mergeTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">推送提款单</td>
        <td>
        	<span class="executeTask blue" data-taksId='revenueApplyPush2EASTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">生成补贴提款单</td>
        <td>
        	<span class="executeTask blue" data-taksId='propertySubsidyAmoutAutoApplyCompany' data-method='execTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">易谷网订单推送</td>
        <td>
        	<span class="executeTask blue" data-taksId='eguTask' data-method='eguOrderPush'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">易谷网快递单推送</td>
        <td>
        	<span class="executeTask blue" data-taksId='eguTask' data-method='eguOrderExpressTraceTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">消息推送（502之前版本）</td>
        <td>
        	<span class="executeTask blue" data-taksId='msgpushTask' data-method='autoSendTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">消息推送（503及之后版本)</td>
        <td>
        	<span class="executeTask blue" data-taksId='msgpushbean' data-method='pushTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">微信消息推送</td>
        <td>
        	<span class="executeTask blue" data-taksId='weChatMsgTask' data-method='sendNotification'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">短信通知发送</td>
        <td>
        	<span class="executeTask blue" data-taksId='smsNotificationTask' data-method='sendNotification'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">对超过发券时间的券作关闭处理</td>
        <td>
        	<span class="executeTask blue" data-taksId='couponTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      <tr class="total">
		<td width="700">对超过用券时间的券作过期失效处理</td>
        <td>
        	<span class="executeTask blue" data-taksId='userCouponTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">新维修订单对不同等级的师傅推送</td>
        <td>
        	<span class="executeTask blue" data-taksId='dredgeTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      
      
      <tr class="total">
		<td width="700">现金流量汇总表数据生成</td>
        <td>
        	<span class="executeTask blue" data-taksId='cashFlowSummaryTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">小区账单平均金额计算 </td>
        <td>
        	<span class="executeTask blue" data-taksId='groupBuildingBillAvgSynTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">初始化用户和设备缴费记录</td>
        <td>
        	<span class="executeTask blue" data-taksId='userAndDevicePayCountInitSynTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">滞纳金计算</td>
        <td>
        	<span class="executeTask blue" data-taksId='alterPeriodSynTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">一元夺宝抽奖，发送信息</td>
        <td>
        	<span class="executeTask blue" data-taksId='flashDealActivitySynTask' data-method='execTask'>执行</span>
        </td>
      </tr>
      
      <tr class="total">
		<td width="700">物业代扣卡自动划扣日期</td>
        <td>
        	<span class="executeTask blue" data-taksId='propertyAccountDeduTask' data-method='execTask'>执行</span>
        </td>
      </tr>

         <tr class="total">
             <td width="700">未缴费账单自动发短信通知</td>
             <td>
                 <span class="executeTask blue" data-taksId='payBillMsgAutoSendTask' data-method='execTask'>执行</span>
             </td>
         </tr>

         <tr class="total">
             <td width="700">向物业软件推维修单</td>
             <td>
                 <span class="executeTask blue" data-taksId='propertySoftwareTask' data-method='pushRepairTask'>执行</span>
             </td>
         </tr>

         <tr class="total">
             <td width="700">向物业软件同步维修单</td>
             <td>
                 <span class="executeTask blue" data-taksId='propertySoftwareTask' data-method='getRepairDetailTask'>执行</span>
             </td>
         </tr>

         <tr class="total">
             <td width="700">月卡车到期推送</td>
             <td>
                 <span class="executeTask blue" data-taksId='carMsgTask' data-method='pushTask'>执行</span>
             </td>
         </tr>
         <tr class="total">
             <td width="700">缴停车费推送</td>
             <td>
                 <span class="executeTask blue" data-taksId='carMsgTask' data-method='pushTask'>执行</span>
             </td>
         </tr>
         <tr class="total">
             <td width="700">缴停车费短信</td>
             <td>
                 <span class="executeTask blue" data-taksId='carMsgTask' data-method='sendMsgTask'>执行</span>
             </td>
         </tr>
         
          <tr class="total">
             <td width="700">银行划扣</td>
             <td>
                 <span class="executeTask blue" data-taksId='bankCollectionTask' data-method='execTask'>执行</span>
             </td>
         </tr>
         
         <tr class="total">
             <td width="700">运营消息</td>
             <td>
                 <span class="executeTask blue" data-taksId='operateMsgTask' data-method='execTask'>执行</span>
             </td>
         </tr>
         
        <tr class="total">
             <td width="700">还原商品售价 </td>
             <td>
                 <span class="executeTask blue" data-taksId='limitBuyPriceSysTask' data-method='execTask'>执行</span>
             </td>
         </tr>
      </table>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>

<script>
	$(".executeTask").click(function() {
		
		var x = $(this);
		console.log(x.attr("data-taksId"));
		
		if (window.confirm('您确定要执行一次任务？')) {
			$.ajax({//使用ajax请求
				type : "POST",
				url : "../task/executeTask.json",
				async : false,
				data: {taskName: x.attr("data-taksId"),  methodName: x.attr("data-method")},
				success : function(data) {
					if(data.status == "0000"){
						alert("操作提示：执行成功");
					}else{
						alert("执行失败，原因：" + data.message);
					}
				},
				error: function(){
					alert("操作提示：执行失败");
				}
			});
			return false;
		} else {//取消操作
			return false;
		}
	});
</script>
</html>
