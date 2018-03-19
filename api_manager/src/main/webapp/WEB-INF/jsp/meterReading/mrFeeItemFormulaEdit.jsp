<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>//" />
<title>抄表收费配置</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

</head>

<body>
<div class="info">
	<div class="layer-classify classify-editable">
		<form class="inputform-layer">
			<input id="mrCalculateRuleCfgId" type="hidden" value="${mrCalculateRuleCfgId}"/>
			<input id="mrFeeItemId" type="hidden" name="mrFeeItemId" value="${mrFeeItemId}"/>
			<h2 id="caclType" data-id="">${itemName}——计费规则配置</h2>
			<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		      <tr>
				<td colspan="4">计费规则名称：<input type="text" name="name" id="calculateRuleName" class="input_text pp w200" datatype="*" nullmsg="请填写计费规则名称" value="${mrCalculateRuleCfg.name}" /></td>
		      </tr>
				<tr>
					<td colspan="4">定价方式：
						<select name="calculatePriceType" id="calculatePriceType" class="select_normal">
							<option value="1" <c:if test="${mrCalculateRuleCfg == null || mrCalculateRuleCfg.calculatePriceType == 1 }">selected="selected"</c:if>>根据读数计算</option>
							<c:if test="${mrFeeItemList != null && fn:length(mrFeeItemList) > 1}">
								<option value="2" <c:if test="${mrCalculateRuleCfg.calculatePriceType == 2}">selected="selected"</c:if>>根据其他费用的用量</option>
							</c:if>
						</select>
					</td>
				</tr>
		      <tr class="bill-type-info-1 nobg">
		      <tr class="calculatePriceType01 <c:if test="${mrCalculateRuleCfg != null && mrCalculateRuleCfg.calculatePriceType == 2}">dsn</c:if>">
		        <td colspan="4">计费规则：
					<select name="calculateRule" class="calculateRule select_normal">
						<option value="1" <c:if test="${mrCalculateRuleCfg.calculateRule == 1}">selected="selected"</c:if>>分段计费</option>
						<option value="2" <c:if test="${mrCalculateRuleCfg.calculateRule == 2}">selected="selected"</c:if>>阶梯计费</option>
					</select>
				</td>
		      </tr>
			<tr class="calculatePriceType02 <c:if test="${mrCalculateRuleCfg == null || mrCalculateRuleCfg.calculatePriceType == 1}">dsn</c:if>">
				<td colspan="4">计费规则：
					<div class="mtop10">1、根据 <select name="mrFeeItem" class="basisItem select_normal">
						<c:forEach var="mrFeeItemList" items="${mrFeeItemList}">
								<c:if test="${mrFeeItemId != mrFeeItemList.id}"><%--不显示自身的费用项&ndash;%&gt;--%>
									<option value="${mrFeeItemList.id}" <c:if test="${mrCalculateRuleCfg.basisItemId == mrFeeItemList.id}">selected="selected"</c:if>>${mrFeeItemList.name}</option>
								</c:if>
							</c:forEach>
						</select> 用量计算
					</div>
					<div class="mtop10">2、一户多表时根据 <select name="mrFeeItem" class="mrFeeItem select_normal">
							<option value="1" <c:if test="${mrCalculateRuleCfg.moreMrCalculateType == 1}">selected="selected"</c:if>>总用量</option>
							<option value="2" <c:if test="${mrCalculateRuleCfg.moreMrCalculateType == 2}">selected="selected"</c:if>>各表用量</option>
						</select> ( <input class="moreMrCalculatePercent input_text w50 t_center pp" value="${mrCalculateRuleCfg.moreMrCalculatePercent}"  datatype="*" nullmsg="请填写计费比例" />% )计算
					</div>
					<div class="mtop10">3、<select name="calculateRule" class="calculateRule select_normal">
						<option value="1" <c:if test="${mrCalculateRuleCfg.calculateRule == 1}">selected="selected"</c:if>>分段计费</option>
						<option value="2" <c:if test="${mrCalculateRuleCfg.calculateRule == 2}">selected="selected"</c:if>>阶梯计费</option>
					</select>
					</div>
				</td>
			</tr>
			  <c:forEach items="${mrifList }" var="mrif" varStatus="status">
				  <tr class="bill-type-info-2 nobg">
					<td width="20">${status.index+1 }.</td>
					<td width="30%">
						<input type="text" class="input_text noborder pp w50 grey <c:if test="${status.index == 0}">start-input</c:if> billing-start" data-id="${mrif.id }" maxlength="6"
							<c:if test="${status.index > 0}">value='<fmt:formatNumber value="${mrif.startValue }" pattern="#" type="number"/>'</c:if>
							<c:if test="${status.index == 0}">value="0"</c:if> ignore="ignore" /> 至
						<input type="text" class="input_text pp w50 billing-end billing-step" datatype="fei0zhengzhengshu|infinitySymbol" errormsg="格式有误" maxlength="6"
							<c:choose>
								<c:when test="${mrif.endValue >= 999999}" > value='~' </c:when>
								<c:otherwise> value='<fmt:formatNumber value="${mrif.endValue }" pattern="#" type="number"/>' </c:otherwise>
							</c:choose>
						/>
					</td>
				    <td>计算方式： 
						<select class="billing-calculateType select_normal">
							<option value="1" <c:if test="${mrif.calculateType == 1}">selected="selected"</c:if>>单价计算</option>
							<option value="2" <c:if test="${mrif.calculateType == 2}">selected="selected"</c:if>>定额计算</option>
						</select>
					</td>
					<td>单价： <input type="text" class="input_text pp w100 billing-end billing-price" datatype="numberFixFourGt0" errormsg="格式有误" maxlength="10" value="${mrif.unitValue }" /> 元</td>
				  </tr>
			  </c:forEach>

		      <c:forEach begin="${fn:length(mrifList)}" end="4" step="1" varStatus="i">
			      <tr class="bill-type-info-2 nobg">
				    <td width="20">${i.index+1 }.</td>
			        <td width="30%">
			        	<input type="text" class="input_text noborder pp w50 grey <c:if test="${i.index == 0}">start-input</c:if> billing-start" data-id="" maxlength="6" ignore="ignore" <c:if test="${i.index == 0}">value="0"</c:if> /> 至
			        	<input type="text" class="input_text pp w50 billing-end billing-step" datatype="fei0zhengzhengshu|infinitySymbol" errormsg="格式有误" <c:if test="${i.index != 0}">ignore="ignore"</c:if>  maxlength="6" value="" />
			        </td>
					  <td>计算方式： 
						  <select class="billing-calculateType select_normal">
							  <option value="1">单价计算</option>
							  <option value="2">定额计算</option>
						  </select>
					  </td>
			        <td>单价： <input type="text" class="input_text pp w100 billing-end billing-price" datatype="numberFixFourGt0" errormsg="格式有误" <c:if test="${i.index != 0}">ignore="ignore"</c:if> maxlength="10" value="" /> 元</td>
			      </tr>
		      </c:forEach>
		     </table>
		    
	    	<div class="mtop10 t_center">
	    		<div class="t_left">注：“~” 代表正无穷，单价最多保留4位小数</div>
	    		<input id="saveBilling" class="info-btn small-btn w80 mtop10" type="button" value="保存">
	    	</div>
	    </form>
	</div>
</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/layer.min.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	$(function(){
		/*在第 5 版时，every 被添加进 ECMA-262 标准；因此在某些实现环境中不被支持。把下面的代码放到脚本的开头来解决此问题*/
		if (!Array.prototype.every) {
			Array.prototype.every = function(fun /*, thisArg */)
			{
				'use strict';

				if (this === void 0 || this === null)
					throw new TypeError();

				var t = Object(this);
				var len = t.length >>> 0;
				if (typeof fun !== 'function')
					throw new TypeError();

				var thisArg = arguments.length >= 2 ? arguments[1] : void 0;
				for (var i = 0; i < len; i++)
				{
					if (i in t && !fun.call(thisArg, t[i], i, t))
						return false;
				}

				return true;
			};
		}

		//保存计费方式
		$(".inputform-layer").Validform({
			tiptype:3,
			btnSubmit:"#saveBilling",
			postonce:false,
			tipSweep:true,
			ignoreHidden:true,
			showAllError:true,
			beforeSubmit:function(){
				//保存时校验阶梯区间是否正确
				if(!checkCaclInfo().every(checkCaclNum)){
					alert('阶梯计费信息配置有误！');
					return false;
				}
				if($(".calculatePriceType02 .basisItem").val() == null && $("#calculatePriceType").val() == 2) {
					alert("不存在其他费用项，请选择【根据读数计算】!");
					return false;
				}
				//按钮置为不能点击
				$("#saveBilling").attr("disabled", true);

				//提交
				$.post('../propertyPayConfig/saveMrCalculateRuleCfg.json',
				{mfiId: getUrlParam('mfiId'),feeItemFormulaJson: JSON.stringify(getCaclTypeInfo()), gbId:${gbId}},
				function(data,status){
					//按钮置为能点击
					$("#saveBilling").removeAttr("disabled");
					if(data.status === '0000'){
						alert('保存成功！');
						window.parent.location.href = '${pageContext.request.contextPath}/propertyPayConfig/chargeItemConfig.html?gbId=${gbId}&gbName=${gbName}';
						window.parent.layer.closeAll();
					}else{
						alert(data.message);
					}
				}, 'json'); 
				
				return false;
			}
		});
		
		//校验选择计费方式
		//selectCaclType('.bill-type');
		//校验填写阶梯计费
		checkLadderPricing();
		//校验阶梯区间
		$('.billing-end.billing-step').blur(function(){
			var thisVal = $(this).val();
			var thisStartVal = $(this).siblings('.billing-start').val();
			if(thisVal !== '' && thisVal*1 <= thisStartVal*1){
				alert('阶梯计费信息配置有误！');
				$(this).focus().val('');
			}
		});
		
		//保存时校验阶梯区间是否正确
		function checkCaclInfo(){
			var billArr = [];
			$('.billing-end.billing-step').each(function(){
				var thisVal = $(this).val();
				if(thisVal !== '' && thisVal !== '~'){
					billArr.push(thisVal);
				}
			});
			return billArr;
		}
		function checkCaclNum(value, index, arr){
			if(index < (arr.length-1)){
				return (value*1 < arr[index+1]*1);
			}else{
				return true;
			}
		}

		var inputformLayer = $('.inputform-layer').Validform();
		//校验选择计费方式
		function selectCaclType(obj){
			$(obj).change(function(){
				var arr = [0,1];
				var thisIndex = $(obj).index(this);
				var otherIndex;
				if(thisIndex === 0){
					otherIndex = 1;
				}else{
					otherIndex = 0;
				}
				if($(this).is(':checked')){
					$('.bill-type-info-'+(thisIndex + 1)).eq(0).find('input.billing-end').removeAttr('ignore').removeAttr('readonly');
					$('.bill-type-info-'+(otherIndex + 1)).find('input').not('.start-input').val('').attr('ignore', 'ignore').attr('readonly', 'readonly');
					if(!inputformLayer.check()){
						inputformLayer.submitForm();
					}
				}
			});
		}
		
		//校验填写阶梯计费
		function checkLadderPricing(){
			$('.billing-end.billing-step').keyup(function(){
				var $this = $(this);
				var $nextTr = $this.parents('tr').next('.bill-type-info-2');
				var $nextALlTr = $this.parents('tr').nextAll('.bill-type-info-2');
				if($this.val() !== '' && $this.val() !== '~' && $nextTr.length){
					$nextTr.find('.billing-start').val($this.val());
					$nextTr.find('.billing-end').removeAttr('readonly').removeAttr('ignore');
				}else if($this.val() === '~'){
					$nextALlTr.find('.billing-start').val('');
					$nextALlTr.find('.billing-end').val('').attr('ignore', 'ignore').attr('readonly', 'readonly');
				}
			});
			$('.billing-start, .billing-end').blur(function(){
				if($(this).val() !== ''){
					if(!inputformLayer.check()){
						inputformLayer.submitForm();
					}
				}
			});
		}
		
		//获取阶梯计费信息
		function getCaclTypeInfo(){
			//组装标题规则数据
			var calculatePriceType = $("#calculatePriceType").val();
			var basisItem = "";
			var moreMrCalculateType = "";
			var moreMrCalculatePercent = "";
			var calculateRule = "";
			if(calculatePriceType == 2) {
				basisItem = $(".calculatePriceType02 .basisItem").val();//根据什么费用计算
				moreMrCalculateType = $(".calculatePriceType02 .mrFeeItem").val();//根据总用量/各表用量
				moreMrCalculatePercent = $(".calculatePriceType02 .moreMrCalculatePercent").val();//百分比
				calculateRule = $(".calculatePriceType02 .calculateRule").val();//阶梯/分段
			} else {
				calculateRule = $(".calculatePriceType01 .calculateRule").val();//阶梯/分段
			}

			var caclInfo = {
				id : $("#mrCalculateRuleCfgId").val(),
				mrFeeItemId : $("#mrFeeItemId").val(),
				name : $("#calculateRuleName").val(),
				calculatePriceType : calculatePriceType,
				basisItemId : basisItem,
				moreMrCalculateType : moreMrCalculateType,
				moreMrCalculatePercent : moreMrCalculatePercent,
				calculateRule : calculateRule,
				mrFeeItemFormulas : [],
			};

			$('.bill-type-info-2').each(function(){
				var singleItemInfo = {};
				var $this = $(this);

				if($this.find('.billing-end.billing-price').val() !== ''){

					var thisId = $this.find('.billing-start').attr('data-id');
					var thisEndValue = $this.find('.billing-end.billing-step').val();
					if(thisEndValue === '' || thisEndValue === '~'){
						thisEndValue = 999999;
					}

					singleItemInfo.id = thisId;
					singleItemInfo.startValue = $this.find('.billing-start').val();
					singleItemInfo.endValue = thisEndValue;
					singleItemInfo.unitValue = $this.find('.billing-end.billing-price').val();
					singleItemInfo.calculateType = $this.find('.billing-calculateType').val();
					caclInfo.mrFeeItemFormulas.push(singleItemInfo);

				}
			});

			return caclInfo;
		}

		//切换定价方式
		$("#calculatePriceType").change(function () {
			var calculatePriceType = $(this).val();
			if(calculatePriceType == 1) {
				$(".calculatePriceType01").removeClass("dsn");
				$(".calculatePriceType02").addClass("dsn");
			} else {
				$(".calculatePriceType02").removeClass("dsn");
				$(".calculatePriceType01").addClass("dsn");
			}
			//清空价格数据
			$('.bill-type-info-2').each(function(){
				$(this).find("input").not(":first-child").val("");
			});
		});
		
	})

</script>
</html>
