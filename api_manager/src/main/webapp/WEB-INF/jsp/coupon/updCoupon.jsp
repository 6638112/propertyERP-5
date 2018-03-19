<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="<%=basePath%>//"/>
    <title>电商-消费券配置-新增消费券</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <style>
		.icon_dt{background: #fff url(${pageContext.request.contextPath}/images/icon_dt.jpg) no-repeat 150px 3px;}
		.w180{width: 170px;}
	</style>
</head>

<body>
<div class="info">
    <li class="posrelative merchant-selected dsn">
        <span class="address-name">招东小区</span>
        <div class="icon-delete"></div>
        <input type="hidden" name="supplyMerchantId" value="">
    </li>
    <form class="inputform" action="<%=basePath%>/coupon/updCoupon.html" method="post">
        <h2>消费券属性</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2">
                    <div align="left" class="f14 bold">消费券属性</div>
                    <input type="hidden" name="id" value="${coupon.id}">
                </td>
            </tr>
            <tr>
                <td width="20%">
                    <div class="list-name"><span class="red">*</span> 券名称：</div>
                </td>
                <td><input type="text" name="couponName" class="input_text w300 pp" maxlength="30" datatype="*1-30"
                           nullmsg="请填写券名称！" errormsg="券名称为1到30个字！" placeholder="请填写券名称" value="${coupon.couponName}"/></td>
            </tr>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 适用范围：</div>
                </td>
                <td>
                    <c:if test="${coupon.useType==0}"><input type="hidden" name="useType" id="useType" value="0">通用</c:if>
                    <c:if test="${coupon.useType==1}"><input type="hidden" name="useType" id="useType" value="1">超市</c:if>
                    <c:if test="${coupon.useType==2}"><input type="hidden" name="useType" id="useType" value="2">物业</c:if>
                    <c:if test="${coupon.useType==3}"><input type="hidden" name="useType" id="useType" value="3">维修</c:if>
                    <c:if test="${coupon.useType==4}"><input type="hidden" name="useType" id="useType" value="4">车禁</c:if>
                    <c:if test="${coupon.useType==5}"><input type="hidden" name="useType" id="useType" value="5">定向商户</c:if>
                    <c:if test="${coupon.useType==6}"><input type="hidden" name="useType" id="useType" value="6">定向社区店商品</c:if>
                    <c:if test="${coupon.useType==7}"><input type="hidden" name="useType" id="useType" value="7">定向到家商品</c:if>
                </td>
            </tr>
            <c:if test="${coupon.useType==3}">
                <input type="hidden" name="dredgeCouponConfigId" value="${dredgecCouponConfig.id}">
                <tr class="swap-con swap-box-3">
                    <td><div class="list-name"><span class="red">*</span>可用维修项目</div></td>
                    <td>
                        <select class="select_normal" name="communitySupplyTypeId">
                            <option value="-1" <c:if test="${dredgecCouponConfig.supportLevel==1}">selected</c:if>>全部维修</option>
                            <c:forEach var="type" items="${communitySupplyTypes}">
                                <option value="${type.id}" <c:if test="${dredgecCouponConfig.supportLevel==2&&dredgecCouponConfig.communitySupplyTypeId==type.id}">selected</c:if>>${type.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="swap-con swap-box-3">
                    <td><div class="list-name"><span class="red">*</span>可抵扣费用</div></td>
                    <td>
                        <select class="select_normal" name="couponFeeType">
                            <option value="2">人工费</option>
                        </select>
                    </td>
                </tr>
            </c:if>
            <tr class="swap-con swap-box-5 <c:if test="${coupon.useType!=5}">dsn</c:if>">
                <td></td>
                <td>
                    <input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
                    <input class="input-btn merchant-search-btn" type="button" value="搜索">
                </td>
            </tr>
            <tr class="swap-con swap-box-5 <c:if test="${coupon.useType!=5}">dsn</c:if>">
                <td><div class="list-name">搜索结果：</div></td>
                <td>
                    <ul class="address-list merchant-list-box">
                    </ul>
                </td>
            </tr>
            <tr class="swap-con swap-box-5 <c:if test="${coupon.useType!=5}">dsn</c:if>">
                <td><div class="list-name">已选商户：</div></td>
                <td>
                    <ul class="address-list merchant-selected-box">
                        <c:if test="${not empty supplyMerchant.name}">
                        <li class="posrelative merchant-selected address-selected-1" data-class="address-selected-1">
                            <span class="address-name">${supplyMerchant.name}</span><br>
                            <c:if test="${not empty supplyMerchant.name}">
                                <span class="grey">地址：深圳</span>
                            </c:if>
                            <div class="icon-delete"></div>
                            <input type="hidden" value="${supplyMerchant.id}" name="supplyMerchantId">
                        </li>
                        </c:if>
                    </ul>
                    <input type="hidden" class="input_text merchant-box-selected-input w80 dsn" <c:if test="${coupon.useType!=5}">ignore="ignore"</c:if> datatype="*" nullmsg="请选择商户！" value="1" />
                </td>
            </tr>
            <%-----------------------------------------------start-----------------------------------------------------%>
          <tr class="search-input01 swap-info swap-val-2 swap-con swap-box-6 <c:if test="${coupon.useType!=6}">dsn</c:if>">
            <td><div class="list-name"><span class="red">*</span> 商品：</div></td>
            <td>
            	<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn item-search-btn" type="button" value="搜索">
            </td>
          </tr>
          <tr class="city-con01 swap-info swap-val-2 swap-con swap-box-6 <c:if test="${coupon.useType!=6}">dsn</c:if>">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box01">
            	</ul>
            </td>
          </tr>
          <tr class="city-con01 swap-info swap-val-2 swap-con swap-box-6 <c:if test="${coupon.useType!=6}">dsn</c:if>">
            <td><div class="list-name">已选商品：</div></td>
            <td>
				<ul class="address-list selected-box01">
					<li class="posrelative address-selected01 dsn">
						<span class="address-name"></span><br>
						<span class="grey">ID：<span class="data-obj-id"></span></span><span class="grey mleft10">供应商：<span class="data-obj-name">海吉星</span></span>
						<div class="icon-delete"></div>
					</li>
					<c:forEach var="product" items="${products}">
						<li class="posrelative address-selected01">
							<c:if test="${empty product.dpId}">
								<span class="address-name">${product.productName}</span><br>
								<span class="grey">ID：<span class="data-obj-id">${product.shelfId}</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">${product.merchantName}</span></span>
								<input type="hidden" name="shelfIds" id="shelfIds" value="${product.shelfId }">
								<div class="icon-delete"></div>
							</c:if>
						</li>
					</c:forEach>
				</ul>
				<input type="hidden" value="${fn:length(products)}" class="select-input01 input_text w80 dsn" datatype="*" nullmsg="请选择商品！" <c:if test="${coupon.useType!=6}"> ignore="ignore"</c:if>/>
			</td>
          </tr>
          
          <tr class="search-input01 swap-info swap-val-2 swap-con swap-box-7 <c:if test="${coupon.useType!=7}">dsn</c:if>">
            <td><div class="list-name"><span class="red">*</span> 商品：</div></td>
            <td>
            	<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
				<input class="input-btn item-search-btn" type="button" value="搜索">
            </td>
          </tr>
          <tr class="city-con01 swap-info swap-val-2 swap-con swap-box-7 <c:if test="${coupon.useType!=7}">dsn</c:if>">
            <td><div class="list-name">搜索结果：</div></td>
            <td>
            	<ul class="address-list search-box01">
            	</ul>
            </td>
          </tr>
          <tr class="city-con01 swap-info swap-val-2 swap-con swap-box-7 <c:if test="${coupon.useType!=7}">dsn</c:if>">
            <td><div class="list-name">已选商品：</div></td>
            <td>
				<ul class="address-list selected-box01">
					<c:forEach var="product" items="${products}">
						<li class="posrelative address-selected01">
							<c:if test="${not empty product.dpId}">
								<span class="address-name">${product.dpName}</span><br>
								<span class="grey">ID：<span class="data-obj-id">${product.dpId}</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">${product.ssName}</span></span>
								<input type="hidden" name="shelfIds" id="shelfIds" value="${product.dpId }">
								<div class="icon-delete"></div>
							</c:if>
						</li>
					</c:forEach>
				</ul>
				<input type="hidden" value="${fn:length(products)}" class="select-input01 input_text w80 dsn" datatype="*" nullmsg="请选择商品！" <c:if test="${coupon.useType!=7}"> ignore="ignore"</c:if>/>
			</td>
          </tr>
          <%-----------------------------------------------end-------------------------------------------------------%>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 优惠方式：</div>
                </td>
                <td>
                    <select class="select_normal" name="couponType">
                        <option value="1" <c:if test="${coupon.couponType==1}">selected</c:if>>现金券</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><div class="list-name"><span class="red">*</span> 有优惠是否送券：</div></td>
                <td>
                    <input class="mtop3" name="useDiscountSend" value="Y" <c:if test="${coupon.useDiscountSend == 'Y'}">checked</c:if> type="radio" datatype="*" nullmsg="请选择是否送券！" /> 是
                    <span class="mleft20"><input class="mtop3" <c:if test="${coupon.useDiscountSend == 'N'}">checked</c:if> name="useDiscountSend" value="N" type="radio" /> 否</span>
                </td>
            </tr>
            <tr <c:if test="${coupon.useType!=6 and coupon.useType!=7}">dsn</c:if>">
                <td>
                    <div class="list-name"><span class="red">*</span> 消费券链接：</div>
                </td>
                <td><input type="text" name="linkUrl" class="input_text w300 pp" maxlength="128" value="${coupon.linkUrl}" datatype="*1-128" nullmsg="请填写消费券链接！" errormsg="消费券链接为1到128个字！" <c:if test="${coupon.useType!=6 and coupon.useType!=7}"> ignore="ignore"</c:if> placeholder="请填写消费券链接"/></td>
            </tr>
        </table>

        <h2 class="mtop10">发券规则</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr class="list-title">
                <td colspan="2">
                    <div align="left" class="f14 bold">基础信息</div>
                </td>
            </tr>
            <tr>
                <td width="20%">
                    <div class="list-name"><span class="red">*</span> 发券时间：</div>
                </td>
                <td>
                    <input type="text" name="sendStartDate" readonly="readonly" value="${coupon.sendStartDate}" class="input_text icon_dt w180 pp" id="sendStartDate" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate:'${now}',onpicked:function(){checkDate(1);},oncleared:function(){checkDate(1);}});" placeholder="请选择起始时间" datatype="*" nullmsg="请选择起始时间！"> 
                	至
                    <input type="text" name="sendEndDate" readonly="readonly" value="${coupon.sendEndDate}" class="input_text icon_dt w180 pp" id="sendEndDate" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate:'${now}',onpicked:function(){checkDate(2);},oncleared:function(){checkDate(2);}});" placeholder="请选择结束时间" datatype="*" nullmsg="请选择结束时间！">                                                 
                                                                     </td>
            </tr>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 发券总数：</div>
                </td>
                <td><input type="text" name="sendTotal" class="input_text w300 pp" maxlength="10"
                           datatype="fei0zhengzhengshu" nullmsg="请填写发券总数！" errormsg="发券总数为大于0的整数！"
                           placeholder="请填写发券总数" value="${coupon.sendTotal}"/></td>
            </tr>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 发券渠道：</div>
                </td>
                <td>
                	<select class="select_normal Validform_error" datatype="*" nullmsg="请选择发券渠道" name="goalType" onchange="changeGoalType(this)">
	                    <option value="">选择发券渠道</option>
	                    <option value="1" <c:if test="${(not empty coupon.goalType) and (coupon.goalType eq 1)}">selected</c:if>>超市购物</option>
	                    <option value="2" <c:if test="${(not empty coupon.goalType) and (coupon.goalType eq 2)}">selected</c:if>>缴物业费</option>
	                    <option value="3" <c:if test="${(not empty coupon.goalType) and (coupon.goalType eq 3)}">selected</c:if>>维修家政</option>
	                    <option value="4" <c:if test="${(not empty coupon.goalType) and (coupon.goalType eq 4)}">selected</c:if>>缴停车费</option>
	                    <option value="66" <c:if test="${(not empty coupon.goalType) and (coupon.goalType eq 66)}">selected</c:if>>手动领取</option>
	                    <option value="67" class="store-value <c:if test="${(empty coupon.goalType) or (coupon.goalType ne 67)}">dsn</c:if>" <c:if test="${(not empty coupon.goalType) and (coupon.goalType eq 67)}">selected</c:if>>分享得券</option>
	                </select>
                </td>
            </tr>

            <tr class="list-title">
                <td colspan="2">
                    <div align="left" class="f14 bold">用户条件</div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 用户范围：</div>
                </td>
                <td>
                    <select class="select_normal addressAreaSelect" datatype="*" nullmsg="请选择用户范围！" name="sendAreaType">
                        <option value="1" <c:if test="${coupon.sendAreaType==1}">selected</c:if>>全国范围</option>
                        <option value="2" <c:if test="${coupon.sendAreaType==2}">selected</c:if>>城市</option>
                        <option value="3" <c:if test="${coupon.sendAreaType==3}">selected</c:if>>小区</option>
                    </select>
                </td>
            </tr>
            <tr class="search-input dsn">
                <td></td>
                <td>
                    <input type="text" id="searchContent" maxlength="8" class="input_text w120" placeholder="请输入关键字"/>
                    <input class="input-btn user-area-search-btn" type="button" value="搜索">
                </td>
            </tr>
            <tr class="city-con dsn">
                <td>
                    <div class="list-name">搜索结果：</div>
                </td>
                <td>
                    <ul class="address-list search-box user-search-box">
                    </ul>
                </td>
            </tr>
            <tr class="city-con dsn">
                <td>
                    <div class="list-name">已选城市：</div>
                </td>
                <td>
                    <ul class="address-list selected-box">
                        <li class="posrelative address-selected dsn"><span class="address-name">招东小区</span>
                            <div class="icon-delete"></div>
                        </li>
                        <c:if test="${coupon.sendAreaType == 2}">
                            <c:forEach items="${areas}" var="item">
                                <li class="posrelative address-selected address-selected-4"
                                    data-class="address-selected-4"><span class="address-name">${item.name}</span>
                                    <div class="icon-delete"></div>
                                    <input type="hidden" name="cityIds" value="${item.id}">
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
            		<input type="hidden" class="select-input input_text w80 dsn" ignore="ignore" datatype="*" nullmsg="请选择城市！" />
                </td>
            </tr>
            <tr class="area-con dsn">
                <td>
                    <div class="list-name">搜索结果：</div>
                </td>
                <td>
                    <ul class="address-list search-box user-search-box">
                    </ul>
                </td>
            </tr>
            <tr class="area-con dsn">
                <td>
                    <div class="list-name">已选小区：</div>
                </td>
                <td>
                    <ul class="address-list selected-box">
                        <c:if test="${coupon.sendAreaType == 3}">
                            <c:forEach items="${areas}" var="item">
                                <li class="posrelative address-selected"
                                    data-class="address-selected">
                                    <span class="address-name">${item.name}</span>
                                    <div class="icon-delete"></div>
                                    <input type="hidden" name="buildingIds" value="${item.id}">
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
            		<input type="hidden" class="select-input input_text w80 dsn" ignore="ignore" datatype="*" nullmsg="请选择小区！" value="1" />
                </td>
            </tr>

            <tr class="list-title">
                <td colspan="2">
                    <div align="left" class="f14 bold">订单条件</div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 发券条件：</div>
                </td>
                <td>
                    <select class="select_normal">
                        <option value="1">满赠</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>满 <input type="text" name="leastSpendSend" maxlength="8" class="input_text w80 pp"
                             datatype="feifuzhengzhengshu" nullmsg="请填写发券条件！" value="${coupon.leastSpendSend}"/> 元赠 <input type="text" maxlength="8"
                                                                                    name="discountMoney"
                                                                                    class="input_text w80 pp"
                                                                                    datatype="fei0zhengzhengshu"
                                                                                    nullmsg="请填写发券条件！" value="${coupon.discountMoney}"/> 元
                </td>
            </tr>
            <tr id="productLimitTr" <c:if test="${(empty coupon.goalType) or (coupon.goalType ne 66)}">style="display:none;"</c:if>>
                <td>
                	<div class="list-name"><span class="red">*</span> 所属类型：</div>
                </td>
                <td>
                    <p style="text-indent: 0px;">
                    	<select id="receiveScene" class="select_normal" name="receiveScene">
                    		<c:forEach items="${couponSceneList}" var="item">
                    			<option value="${item.id}" <c:if test="${coupon.receiveScene==item.id}">selected</c:if>>${item.name}</option>
                    		</c:forEach>
                    	</select>
                    </p>
                </td>
            </tr>
        </table>

        <h2 class="mtop10">用券规则</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <tr>
                <td width="20%">
                    <div class="list-name"><span class="red">*</span> 使用结束时间类型：</div>
                </td>
                <td>
                    <input type="radio" name="useEndDateType" value="1" data-val="1" <c:if test="${coupon.useEndDateType == 1}">checked</c:if>> 固定日期
                    <input type="radio" name="useEndDateType" value="2" data-val="2" <c:if test="${coupon.useEndDateType == 2}">checked</c:if>> 领取后固定天数
                </td>
            </tr>
            <tr class="swap-con swap-val-1 <c:if test="${coupon.useEndDateType == 2}">dsn</c:if>">
                <td width="20%">
                    <div class="list-name"><span class="red">*</span> 使用结束时间：</div>
                </td>
                <td>
                    <input type="text" name="useEndDate" class="input_text icon_dt endlessTime w180 pp" id="useEndDate" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate:'${now}',onpicked:function(){checkDate(3);},oncleared:function(){checkDate(3);}});" placeholder="请选择结束时间"
                           datatype="*" nullmsg="请选择结束时间！" readonly="readonly" value="${coupon.useEndDate}"></td>
            </tr>
            <tr class="swap-con swap-val-2 <c:if test="${coupon.useEndDateType == 1}">dsn</c:if>">
                <td width="20%">
                    <div class="list-name"><span class="red">*</span> 领取后可使用天数：</div>
                </td>
                <td>
                    <input type="text" class="input_text w180" name="useDateNumber" value="${coupon.useDateNumber}"> 天
                </td>
            </tr>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 使用张数：</div>
                </td>
                <td>每笔订单限用一张</td>
            </tr>
            <tr>
                <td>
                    <div class="list-name"><span class="red">*</span> 使用条件：</div>
                </td>
                <td>
                    <select class="select_normal prizeLimitInfo" datatype="*" nullmsg="请选择使用条件！">
                        <option value="1" <c:if test="${coupon.leastSpendUse == 0}">selected</c:if>>无订单金额限制</option>
                        <option value="2" <c:if test="${coupon.leastSpendUse > 0}">selected</c:if>>有订单金额限制</option>
                    </select>
                </td>
            </tr>
            <tr class="use-limit-info dsn">
                <td></td>
                <td>订单至少满&nbsp;<input type="text" id="leastSendUse" name="leastSpendUse" class="input_text w300"
                                      maxlength="10" ignore="ignore" datatype="feifuzhengzhengshu" nullmsg="请填写限制条件！"
                                      placeholder="请填写限制条件" value="${coupon.leastSpendUse}"/> 元可用
                </td>
            </tr>
        </table>
        <input id="sumTicket" class="info-btn save-set-prize-info-btn" type="submit" value="修改"/>
	<div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js?v20160419"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.coupon.js?v20171108"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
    (function ($) {
        //表单验证
        $(".inputform").Validform({
            btnSubmit: "#sumTicket",
            tiptype: 3,
            dragonfly:true,
            ignoreHidden:true,
            beforeSubmit: function (curform) {
            	$("select[name=sendAreaType]").removeAttr("disabled");
                $(".inputform").attr('onsubmit', 'return false;');
            },
            callback: function (data) {
                $(".inputform").ajaxSubmit(function (data) {
                	if($("select[name=sendAreaType]").val()=="3"){
                		$("select[name=sendAreaType]").prop("disabled", true);
                	}
                    try {
                        data = eval(data);
                    } catch (e) {
                        data = eval("(" + data + ")");
                    }
                    if (data.status == '0000') {
                        alert("消费券修改成功！");
                        window.location.href = "<%=basePath%>/coupon/couponList.html";
                    } else {
                        alert(data.message);
                        $(".inputform").Validform().resetStatus();
                    }
                });
            }
        });
        //修复日期选择，延迟验证
        $('.input_text.icon_dt').blur(function () {
            var $this = $(this);
            setTimeout(function () {
                $(".inputform").Validform({}).check(false, $this);
            }, 600);
        });
        
        //修复日期控件位置
        $('#useEndDate').click(function(){
        	
        	var scrollt = $(window.parent.document).find(".main-right").scrollTop(); //获取滚动后的高度
        	var offsetTop = $('#useEndDate').offset().top;
        	
        	$(window.parent.document).find('body').children('div:last-child').css('top', offsetTop - scrollt - 110 - 20);	//减去控件高度、输入框高度一半
        	
        })
    })(jQuery);
    
    //校验时间先后顺序
	function checkDate(type) {
		var sendStartDate = $('#sendStartDate').val();
		var sendEndDate = $('#sendEndDate').val();
		var useEndDate = $('#useEndDate').val();
		if(type==1 || type==2){
			if(sendStartDate != '' && sendEndDate != '' && (sendStartDate > sendEndDate)){
				if(type==1){
					alert('起始时间须小于或等于结束时间！');
					$('#sendStartDate').val('');
				} else {
					alert('结束时间须大于或等于起始时间！');
					$('#sendEndDate').val('');
				}
			}
		} 
		
		if(type==2 || type==3){
			if(sendEndDate != '' && useEndDate != '' && (sendEndDate > useEndDate)){
				if(type==2){
					alert('发券结束时间须小于或等于使用结束时间！');
					$('#sendEndDate').val('');
				} else {
					alert('使用结束时间须大于发券结束时间！');
					$('#useEndDate').val('');
				}
			}
		}
	}
</script>
<script type="text/javascript">
    $(function () {

        //用户范围初始化
        var sendareatype = ${coupon.sendAreaType};
        selectAreaType = sendareatype;
        if (sendareatype == '' || sendareatype == 1) {
            $('.area-con,.search-input').hide();
            $('.city-con,.search-input').hide();
        } else if (sendareatype == 2) {
            $('.area-con').hide();
            $('.city-con,.search-input').show();
        } else if (sendareatype == 3) {
            $('.city-con').hide();
            $('.area-con,.search-input').show();
        }

        var leastspenduse = ${coupon.leastSpendUse};
        if (leastspenduse > 0) {
            $('.use-limit-info').show();
        } else {
            $('.use-limit-info').hide();
            $('#leastSendUse').val('0');
        }

        //选择用户范围
        $('.addressAreaSelect').change(function () {
            var curVal = $(this).val();
            selectAreaType = curVal;
    		if(curVal == '' || curVal == 1){
    			$('.area-con,.search-input').hide();
    			$('.city-con,.search-input').hide();
    			$('.area-con, .city-con').find('input').attr('ignore', 'ignore');
    			selectCountNum = 0;
    		}else if(curVal == 2){
    			$('.area-con').hide();
    			$('.city-con,.search-input').show();
    			$('.city-con').find('input').attr('ignore', '');
    			$('.area-con').find('input').attr('ignore', 'ignore');
    			//获取已选城市个数
				var curNum = $('.address-list.selected-box:visible li:visible').length;
    			if( curNum == ''){
    				selectCountNum = 0;
    			}else{
    				selectCountNum = curNum;
    			}
    			
    		}else if(curVal == 3){
    			$('.city-con').hide();
    			$('.area-con,.search-input').show();
    			$('.area-con').find('input').attr('ignore', '');
    			$('.city-con').find('input').attr('ignore', 'ignore');
    			//获取已选小区个数
				var curNum = $('.address-list.selected-box:visible li:visible').length;
    			if( curNum == ''){
    				selectCountNum = 0;
    			}else{
    				selectCountNum = curNum;
    			}
    		}
            //重置页面高度
            window.parent.TuneHeight();
        });
        //选择使用条件
        $('.prizeLimitInfo').change(function () {
            var curVal = $(this).val();
            if (curVal == 2) {
                $('.use-limit-info').show();
    			$('.use-limit-info').find('input').attr('ignore', '');
                $('#leastSendUse').val('');
            } else {
                $('.use-limit-info').hide();
    			$('.use-limit-info').find('input').attr('ignore', 'ignore');
                $('#leastSendUse').val('0');
            }
            //重置页面高度
            window.parent.TuneHeight();
        });

        //选择城市/小区
        var $addressSelectedLi = $('.address-selected.dsn');
        var addressSelectedNum = 0;
    	var selectCountNum = 0;

        $(document).on("click", '.address-list.search-box li:visible', function () {
            var $this = $(this);
            //选择
            if (!$this.hasClass('on')) {
                var addressSelectedClass = 'address-selected-';
                var id = $this.find('span').attr('data-itemid');
                addressSelectedNum += 1;
                
    			selectCountNum += 1;
    			//已选城市/小区，校验通过
                addressSelectedClass = addressSelectedClass + addressSelectedNum;

                var $addressSelectedLiClone = $addressSelectedLi.clone(true);
                var thisName = $this.find('.address-name').text();

                $this.addClass('on ' + addressSelectedClass).attr('data-class', addressSelectedClass);
                $addressSelectedLiClone.find('.address-name').text(thisName);
                if(selectAreaType == 2) {
                    $addressSelectedLiClone.append('<input type="hidden" name="cityIds" value="'+id+'">');
                } else if(selectAreaType == 3) {
                    $addressSelectedLiClone.append('<input type="hidden" name="buildingIds" value="'+id+'">');
                }

                $addressSelectedLiClone.removeClass('dsn').addClass(addressSelectedClass).attr('data-class', addressSelectedClass).appendTo('.selected-box:visible');
                //反选
            } else {
                var thisUnSelectedClass = $this.attr('data-class');
                $this.removeClass();
                $('.selected-box').find('.' + thisUnSelectedClass).remove();
    			selectCountNum -= 1;
    			//没有选择城市/小区，校验不通过
    			if(selectCountNum == 0){
    				$('.address-list:visible').siblings('.select-input').val('');
    				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
    			}
            }
            //重置页面高度
            window.parent.TuneHeight();
        })

        //删除城市/小区
        $(document).on("click", '.address-list.selected-box li .icon-delete', function () {
            var $this = $(this);
            var thisDeleteClass = $this.parent('li').attr('data-class');
            if ($('.' + thisDeleteClass)) {
                $('.' + thisDeleteClass).removeClass('on');
            }
            $this.parent('li').remove();

			selectCountNum -= 1;
			//没有选择城市/小区，校验不通过
			if($('.address-list.selected-box:visible li:visible').length == 0){
				$('.address-list:visible').siblings('.select-input').val('');
				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input'));
			}
            //重置页面高度
            window.parent.TuneHeight();
        });


        var $searchList = $('.search-list-con.dsn');
        //搜索内容
        function ajaxSearch(url, objBox, isInited) {
            $.getJSON(url, function (data) {

                var $objBox = $(objBox);
                var searchList = '';

                var $searchListClone = $searchList.clone(true);
                $.each(data, function (i, aaList) {
                    //小区搜索，带详细地址
                    if (aaList.addressDesc) {
                    	var isSelected = false;
                    	var c="";
                    	$(".area-con .selected-box li").each(function(){
                    		var v = $(this).find("input[name=buildingIds]").val();
                    		console.log("v="+v+"   aaList.id="+aaList.id);
                    		if(v==aaList.id){
                    			isSelected = true;
                    			c = $(this).attr("data-class");
                    			return;
                    		}
                    	});
                    	if(aaList.signStatus == 1){
                    		searchList += '<li ';
                        	if(!isInited && isSelected){
                        		searchList += 'class="on '+c+'" data-class="'+c+'"';
                        	}
                        	searchList += '><span class="address-name red" data-itemid="'+aaList.id+'">' + aaList.name + '</span><br><span class="grey">地址：' + aaList.addressDesc + '</span></li>';
                    	}else{
                    		searchList += '<li ';
                        	if(!isInited && isSelected){
                        		searchList += 'class="on '+c+'" data-class="'+c+'"';
                        	}
                        	searchList += '><span class="address-name" data-itemid="'+aaList.id+'">' + aaList.name + '</span><br><span class="grey">地址：' + aaList.addressDesc + '</span></li>';
                    	}
                        //城市搜索，不带详细地址
                    } else if (aaList.name.indexOf(currentVal) > -1) {
                    	if(aaList.signStatus == 1){
                        	searchList += '<li><span class="address-name red" data-itemid="'+aaList.id+'">' + aaList.name + '</span></li>';
                    	}else{
                    		searchList += '<li><span class="address-name" data-itemid="'+aaList.id+'">' + aaList.name + '</span></li>';
                    	}
                    }
                });
                if (searchList == '') {
                    searchList = '<span>' + '没有找到相关内容' + '</span>';
                }
                $objBox.html(searchList);
                
                if(isInited){
                	$(".area-con .user-search-box li").each(function(){
             			var li = $(this).find(".address-name").parent();
             			var item = $(this).find(".address-name").attr("data-itemid");
             			$(".area-con .selected-box input[name=buildingIds]").each(function(){
             				if(item==$(this).val()){
             					$(this).parent().remove();
             					$(li).trigger('click');
             				}
             			});
             		});
                }
                //重置页面高度
                window.parent.TuneHeight();
            });
        }

        //开始搜索
        $('.user-area-search-btn').click(function () {
        	userAreaSearch();
        });
        
        function userAreaSearch(isInited){
        	var url;
            var searchContent = $('#searchContent').val();
            var merchantId = $.trim($(".inputform input:hidden[name=supplyMerchantId]").val());
            searchContent = encodeURI(searchContent, "utf-8");
            if(selectAreaType == 2) {
                url = '<%=basePath%>/addressCity/getAddressCityIdByName.json?name=' + searchContent;
            } else if(selectAreaType == 3){
                url = '<%=basePath%>/groupBuildingJson/getBuildingListByNameAndCityId.json?name=' + searchContent+"&merchantId="+merchantId;
            }

            var thisSearchBox = $('.user-search-box:visible');
            currentVal = $.trim($('.search-input input.input_text').val());
            ajaxSearch(url, thisSearchBox, isInited);
        }
    	///////////////////////////////////////////////////////////////////////////////
    	
    	//选择定向商户
    	var itemScreenMerchant = $.itemScreenStart({
    			selectChangeObj: '.merchantSelectBox',		//下拉框对象
    			//选择配置项
    			selectObj: '.merchant-list-box li:visible',	//要选择的对象
    			deleteObj: '.merchant-selected-box li .icon-delete',	//要删除的对象
    			itemsSelected: '.merchant-selected-box li:visible',		//已选对象
    			itemDemo: '.merchant-selected.dsn',	//用来克隆的对象		
    			itemDemoBox: '.merchant-selected-box',	//用来插入克隆的容器对象	
    			validInput: '.merchant-box-selected-input',	//校验已选数量
    			multiple: false,
    			singleTipsMsg: '只能选择一个商户！',
    			itemSelectedCountNum: 1
    			
    		});
    		
    	//切换下拉框，显示对应内容
    	itemScreenMerchant.changeUserRange();
    	
    	//选择、反选、删除
    	itemScreenMerchant.itemSelectAndDelete();
    	
    	//开始搜索
    	$('.merchant-search-btn').click(function(){
    		var url = '<%=basePath%>/supplyMerchant/getSupplyMerchantListByName.json?name=';
    		var thisSearchBox = $(this).parents('tr').next('.swap-con').find('.merchant-list-box');
    		currentVal = $.trim($(this).siblings('.input_text').val());
            var searchContent = encodeURI(currentVal, "utf-8");
    		if(!currentVal == ''){
    			itemScreenMerchant.ajaxSearch(url + searchContent, thisSearchBox);
    		}
    	});
    	
    	if("${supplyMerchant.type}"=="2"){// 楼下店
    		$(".addressAreaSelect").val("3").trigger("change").prop("disabled", true);
    		$('#searchContent').val("");
    		//userAreaSearch(true);
    	}
    	
///////////////////////////////////////================
    	var $searchList = $('.search-list-con.dsn');
    	//搜索内容
    	function ajaxSearch01(url,objBox){
    		$.getJSON(url, function(data){

    			var $objBox = $(objBox);
    			var searchList = '';

    			var $searchListClone = $searchList.clone(true);
    			if (data.dataValue != null) {
                    $.each(data.dataValue.list, function (i, aaList) {
                        //搜索
                        searchList += '<li><span class="address-name" data-itemid="'+aaList.dpId+'">' + aaList.prdtName + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.dpId + '</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">'+aaList.ssName+'</span></span></li>';
                    });
                } else {
                    $.each(data, function (i, aaList) {
                        //搜索
                        searchList += '<li><span class="address-name" data-itemid="'+aaList.shelfId+'">' + aaList.productName + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.shelfId + '</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">'+aaList.merchantName+'</span></span></li>';
                    });
                }

    			if(searchList == ''){
    				searchList = '<span>' + '没有找到相关内容' + '</span>';
    			}
    			$objBox.html(searchList);
    			//重置页面高度
    			window.parent.TuneHeight();
    		});
    	}

        //选择体验店，显示分享领券项
        $(document).on('click', '.address-list.merchant-list-box li', function(){
            if($(this).hasClass('on') && $(this).find('.address-name').hasClass('is-the-store')){
                $('.store-value').removeClass('dsn');
            } else {
                $('.store-value').addClass('dsn');
            }
        });

    	//开始搜索
    	$('.item-search-btn').click(function(){
    		var thisSearchBox = $('.search-box01:visible');
    		var currentVal = $.trim($('.search-input01:visible input.input_text').val());
    		var useType = $("#useType").val();
    		var url = "";
    		if (useType == 6) {
    			url = '<%=basePath%>/adv/getShelfProductForAdv.json?qryStr=' + encodeURI(currentVal, "utf-8");
            } else if(useType==7){
            	url = "<%=basePath%>/dredge/productList.json?upShelfState=1&dpName=" + encodeURI(currentVal, "utf-8");
            }
    		if(url!="" && currentVal != ""){
    			ajaxSearch01(url,thisSearchBox);
    		}
    	});
    	
    	var $addressSelectedLi01 = $('.address-selected01.dsn');
    	var addressSelectedNum01 = 0;
    	var selectCountNum01 = 0;
    	
    	$(document).on("click", '.address-list.search-box01:visible li:visible', function(){
    		var $this = $(this);
    		//选择
    		if(!$this.hasClass('on')){
    			var addressSelectedClass = 'address-selected01-';
    			var $addressSelectedLiClone01 = $addressSelectedLi01.clone(true);
    			var thisName = $this.find('.address-name').text();
    			var thisId = $this.find('.data-obj-id').text();
    			var thisSupplierName = $this.find('.data-obj-name').text();
    			var id = $this.find('span').attr('data-itemid');
    			//防止重复选择
    			var areaSelectedNum = 0;
    			$('.address-list.selected-box01:visible li:visible').each(function(){
    				var thisAreaName = $(this).find('#shelfIds').val();
    				if(thisAreaName === id){
    					areaSelectedNum += 1;
    				}
    			})
    			if(areaSelectedNum > 0){
    				alert('请勿重复选择！');
    				return false;
    			}

    			addressSelectedNum01 += 1;
    			selectCountNum01 += 1;

    			//已选，校验通过
    			$('.address-list:visible').siblings('.select-input01:visible').val(selectCountNum01);
    			$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));

    			addressSelectedClass = addressSelectedClass + addressSelectedNum01;

    			$this.addClass('on ' + addressSelectedClass).attr('data-class',addressSelectedClass);
    			$addressSelectedLiClone01.find('.address-name').text(thisName);
    			$addressSelectedLiClone01.find('.data-obj-id').text(thisId);
    			$addressSelectedLiClone01.find('.data-obj-name').text(thisSupplierName);
    			$addressSelectedLiClone01.append('<input type="hidden" name="shelfIds" id="shelfIds" value="'+id+'">');
    			$addressSelectedLiClone01.removeClass('dsn').addClass(addressSelectedClass).attr('data-class',addressSelectedClass).appendTo('.selected-box01:visible');
    		//反选
    		}else{
    			var thisUnSelectedClass = $this.attr('data-class');
    			$this.removeClass();
    			$('.selected-box01:visible').find('.' + thisUnSelectedClass).remove();
    			selectCountNum01 -= 1;
    			//没有选择，校验不通过
    			if(selectCountNum01 == 0){
    				$('.address-list:visible').siblings('.select-input01').val('');
    				$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));
    			}
    		}
    		//重置页面高度
    		window.parent.TuneHeight();
    	})

    	//删除
    	$(document).on("click", '.address-list.selected-box01:visible li .icon-delete', function(){
    		var $this = $(this);
    		var thisDeleteClass = $this.parent('li').attr('data-class');
    		if($('.' + thisDeleteClass)){
    			$('.' + thisDeleteClass).removeClass('on');
    		}
    		$this.parent('li').remove();

    		selectCountNum01 -= 1;
    		//没有选择，校验不通过
    		if(selectCountNum01 == 0){
    			$('.address-list:visible').siblings('.select-input01').val('');
    			$(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));
    		}
    		//重置页面高度
    		window.parent.TuneHeight();
    	});
    	
    	//选择体验店，显示分享领券项
    	$(document).on('click', '.address-list.merchant-list-box li', function(){
    		if($(this).hasClass('on') && $(this).find('.address-name').hasClass('is-the-store')){
    			$('.store-value').removeClass('dsn');
    		}
    	})
    	
    	//使用结束时间类型切换
    	radioChange('[name=useEndDateType]');
    });
    
    function changeGoalType(o){
		var goalType = $(o).val();
		if(goalType==66){
			$("#productLimitTr").show();
		} else {
			$("#productLimitTr").hide();
		}
	}
</script>
</html>
