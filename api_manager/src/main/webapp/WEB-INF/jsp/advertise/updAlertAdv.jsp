<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>编辑广告</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css">
    <style>
		.title{font-weight: bold;}
		.red{color:red;}
		#cityTable tbody, #gbTable tbody, #areaHasSelectedList tbody{  
			display:block;  
			max-height:346px;  
			overflow-y:scroll; 
			overflow-x:hidden; 
		}  
		
		#cityTable thead, #cityTable tr, #gbTable thead, #gbTable tr, #areaHasSelectedList thead, #areaHasSelectedList tr {  
			display:table;  
			width:100%;  
			table-layout:fixed;  
			overflow-x:hidden;
		}  
		  
		#cityTable thead, #gbTable thead, #areaHasSelectedList thead {  
			/*Standard */
			width: calc( 100% - 8px );
			/*chrome safari*/
			width: -webkit-calc( 100% - 8px );
			/*Firefox*/
			width: -moz-calc( 100% - 8px );				
		}
	</style>
</head>

<body style="display:none;">
<div class="info">
	<li class="posrelative address-selected01 dsn">
		<span class="address-name"></span><br>
		<span class="grey">ID：<span class="data-obj-id"></span></span><span class="grey mleft10">供应商：<span class="data-obj-name">海吉星</span></span>
		<div class="icon-delete"></div>
	</li>
    <li class="posrelative address-selected dsn">
        <span class="address-name">招东小区</span>
        <div class="icon-delete"></div>
        <input type="hidden" name="cityIds" value="">
    </li>
    <form class="inputform" action="${pageContext.request.contextPath}/adv/updAlertAdv.html" method="post">
    	<input type="hidden" name="advType" value="${advType}"/>
    	<input type="hidden" name="ebuyAdvertise.id" value="${advertise.id}">
        <h2>
        	<c:choose>
        		<c:when test="${advType eq 1}">编辑首页弹窗广告</c:when>
        		<c:when test="${advType eq 2}">编辑首页拦腰广告</c:when>
        		<c:when test="${advType eq 3}">编辑到家广告</c:when>
        		<c:when test="${advType eq 4}">编辑街坊广告</c:when>
        		<c:when test="${advType eq 5}">编辑到家底部广告</c:when>
        		<c:when test="${advType eq 6}">微信购物小票广告</c:when>
				<c:when test="${advType eq 7}">车禁缴费广告</c:when>
				<c:when test="${advType eq 8}">体验店banner广告</c:when>
        	</c:choose>
        </h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td width="20%"><div class="list-name"><span class="red">*</span> 广告名称：</div></td>
				<td><input value="${advertise.tittle}" type="text" name="ebuyAdvertise.tittle" class="input_text pp" maxlength="20" datatype="*" placeholder="请填写广告名称" nullmsg="请填写广告名称！"></td>
			</tr>
			<c:if test="${advType == 5}">
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第一行文案：</div></td>
					<td><input type="text" name="firstLine" value="${lines[0]}" class="input_text pp" maxlength="10" datatype="*" placeholder="第一行文案" nullmsg="第一行文案！"></td>
				</tr>
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第二行文案：</div></td>
					<td><input type="text" name="secondLine" value="${lines[1]}" class="input_text pp" maxlength="10" datatype="*" placeholder="第二行文案" nullmsg="第二行文案！"></td>
				</tr>
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第三行文案：</div></td>
					<td><input type="text" name="thirdLine" value="${lines[2]}" class="input_text pp" maxlength="10" datatype="*" placeholder="第三行文案" nullmsg="第三行文案！"></td>
				</tr>
			</c:if>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 推广时间：</div></td>
            <td>
                <input type="text" value="${fn:substring(advertise.startTime, 0, 16)}" name="ebuyAdvertise.startTime" class="input_text icon_dt" id="date05" placeholder="请选择起始时间" datatype="dateTime" nullmsg="请选择起始时间！"> 至
                <input type="text" value="${fn:substring(advertise.endTime, 0, 16)}" name="ebuyAdvertise.endTime" class="input_text icon_dt" id="date06" placeholder="请选择结束时间" datatype="dateTime" nullmsg="请选择结束时间！"></td>
          </tr>
          <c:if test="${advType eq 1}">
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 展示频率：</div></td>
	            <td>
	                <select class="select_normal" name="ebuyAdvertise.frequency" datatype="*" nullmsg="请展示频率！">
	                    <option value="">选择弹框频率</option>
	                    <option value="1" <c:if test="${advertise.frequency == 1}">selected</c:if>>每天一次</option>
	                    <option value="2" <c:if test="${advertise.frequency == 2}">selected</c:if>>展示期内用户点击后不弹</option>
	                </select>
	            </td>
	          </tr>
          </c:if>
			<c:if test="${advType != 6}">
				<tr>
					<td><div class="list-name">APP版本起始：</div></td>
					<td>
						<input value="${advertise.version}" name="ebuyAdvertise.version" type="text" class="input_text w150 pp" placeholder="开始版本号" maxlength="6" ignore="ignore" datatype="n" errormsg="版本号为数字！"> -
						<input value="${advertise.maxVersion}" name="ebuyAdvertise.maxVersion" type="text" class="input_text w150 pp" placeholder="结束版本号" maxlength="6" ignore="ignore" datatype="n" errormsg="版本号为数字！">
						<span class="red">（不填的项不限）</span>
					</td>
				</tr>
			</c:if>

            	<c:if test="${(advType eq 1) or (advType eq 3)}">
	            <tr>
	              <td><div class="list-name">code：</div></td>
	              <td>
	              	  <c:choose>
	              	      <c:when test="${advType eq 1}">
	              	          <input name="ebuyAdvertise.code" type="text" class="input_text w150 pp" value="${advertise.code}" placeholder="code" title="弹窗广告code不可编辑" maxlength="20" ignore="ignore" readonly="readonly"/>
	              	      </c:when>
	              	      <c:when test="${advType eq 3}">
		              	      	<select id="code" name="ebuyAdvertise.code" class="select_normal">
				                    <option value="WX" <c:if test="${advertise.code eq 'WX'}">selected</c:if>>APP</option>
				                    <option value="LA_WX" <c:if test="${advertise.code eq 'LA_WX'}">selected</c:if>>轻应用</option>
				                </select>
	              	      </c:when>
	              	      <c:otherwise>
	              	           <input name="ebuyAdvertise.code" type="text" class="input_text pp" placeholder="code" maxlength="20" ignore="ignore" value="${advertise.code}"/>
	              	      </c:otherwise>
	              	  </c:choose>
	              </td>
	          </tr>
	          </c:if>
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 跳转类型：</div></td>
	            <td>
	                <select id="jumpPageType" name="ebuyAdvertise.type" class="select_normal" datatype="*" nullmsg="请选择跳转类型！">
		  				<c:if test="${advType != 5 && advType != 8}">
	                    <option value="">选择跳转类型</option>
	                    </c:if>
	                    <c:if test="${(advType eq 1) or (advType eq 2)}">
	                    <option value="2" <c:if test="${advertise.type == 2}">selected</c:if>>产品(APP)</option>
	                    </c:if>
	                    <c:if test="${advType eq 3}">
	                    <option value="5" <c:if test="${advertise.type == 5}">selected</c:if>>产品(APP)</option>
	                    </c:if>
						<c:if test="${advType != 8}">
							<option value="1" <c:if test="${(advertise.type == 1) or ((advType eq 4) and (not empty advertise.linkUrl))}">selected</c:if>>H5页面</option>
						</c:if>
						<c:if test="${advType == 8}">
							<c:if test="${advertise.type == 1}">
								<option value="1">H5页面</option>
							</c:if>
							<c:if test="${advertise.type == 5}">
								<option value="5">商品详情</option>
							</c:if>
						</c:if>
	                    <c:if test="${(advType == 1) or (advType == 2)}">
	                    <option value="4" <c:if test="${(advertise.type == 4) or ((advType eq 4) and (empty advertise.linkUrl))}">selected</c:if>>不跳</option>
	                    </c:if>
	                </select>
	            </td>
	          </tr>
			<c:if test="${advType == 8}">
			<tr>
				<c:if test="${advertise.type == 5}">
					<td><div class="list-name"><span class="red">*</span> 商品ID ：</div></td>
					<td>${advertise.linkUrl}</td>
				</c:if>
			</tr>
			</c:if>
		  <c:if test="${advType != 5 && advType != 6 && advType != 8}">
	          <tr class="swap-con 
	          <c:if test="${(advType eq 1) or (advType eq 2)}"> swap-box-2 </c:if> 
	          <c:if test="${advType eq 3}"> swap-box-5 </c:if> 
	          <c:if test="${(((advType eq 1) or (advType eq 2)) and (advertise.type ne 2)) or ((advType eq 3) and (advertise.type ne 5)) or (advType eq 4)}"> dsn</c:if>">
	            <td><div class="list-name"><span class="red">*</span> 跳转页面：</div></td>
	            <td>
	                <select name="alertAppPageType" ignore="ignore" class="select_normal" datatype="*" nullmsg="请选择跳转页面！">
	                    <option value="">选择跳转页面</option>
	                    <%--<option value="1" <c:if test="${advertise.iosAddr == 'wuyebaoxiu'}">selected</c:if>>物业报修</option>--%>
	                    <c:choose>
	                    	<c:when test="${advType eq 1}">
		                    	<option value="2" <c:if test="${advertise.iosAddr == 'wuyegonggao'}">selected</c:if>>物业公告</option>
			                    <option value="3" <c:if test="${advertise.iosAddr == 'zhinengtingche'}">selected</c:if>>智能停车</option>
			                    <option value="4" <c:if test="${advertise.iosAddr == 'shangmenfuwu'}">selected</c:if>>家政维修</option>
			                    <option value="5" <c:if test="${advertise.iosAddr == 'dianpuliebiao'}">selected</c:if>>店铺列表</option>
			                    <option value="6" <c:if test="${advertise.iosAddr == 'chaoshishouye'}">selected</c:if>>超市首页</option>
			                    <option value="7" <c:if test="${advertise.iosAddr == 'wuyejiaofei'}">selected</c:if>>物业缴费</option>
	                    	</c:when>
	                    	<c:when test="${advType eq 2}">
	                    		<option value="1" <c:if test="${advertise.code == 'superRepair'}">selected</c:if>>维修</option>
			                    <option value="2" <c:if test="${advertise.code == 'wuyejiaofei'}">selected</c:if>>物业缴费</option>
	                    	</c:when>
	                    	<c:when test="${advType eq 3}">
	                    		<c:forEach items="${communitySupplyTypeList}" var="communitySupplyType">
	                    			<option value="${communitySupplyType.id}" <c:if test="${linkUrlId eq communitySupplyType.id}">selected</c:if>>${communitySupplyType.name}</option>
	                    		</c:forEach>
	                    	</c:when>
	                    </c:choose>
	                </select>
	            </td>
	          </tr>
          </c:if>
          <tr class="swap-con swap-box-1 <c:if test="${((advType ne 4) and (advertise.type != 1)) or ((advType eq 4) and ((empty advertise.linkUrl) or (advertise.linkUrl eq '#')))}">dsn</c:if>">
            <td><div class="list-name"><span class="red">*</span> H5页面地址：</div></td>
            <td><input value="${advertise.linkUrl}" ignore="ignore" name="ebuyAdvertise.linkUrl" type="text" class="input_text w300 pp" maxlength="300" datatype="*" nullmsg="请填写H5页面地址！"></td>
          </tr>
          	<c:if test="${(advType eq 1) or (advType eq 3)}">
            <tr class="swap-con swap-box-1 <c:if test="${advertise.type != 1}">dsn</c:if>">
                <td><div class="list-name">与APP交互的H5约定code：</div></td>
                <td>
                    <input <c:if test="${advertise.type == 1}">value="${advertise.iosAddr}"</c:if> name="ebuyAdvertise.androidAddr" type="text" maxlength="30" class="input_text w150 pp" placeholder="安卓code"> -
                    <input <c:if test="${advertise.type == 1}">value="${advertise.androidAddr}"</c:if> name="ebuyAdvertise.iosAddr" type="text" maxlength="30" class="input_text w150 pp" placeholder="IOS code"> <span class="red">（无则不填）</span>
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="right"><span class="red">*</span> 广告图片：</td>
                <td class="item-upload-img">
                    <div class="uploadPreview01 mright6"><input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
                        <img class="imgPreview" width="82" height="82" src="${picPath}" />
                    </div>
                    <span class="f12">注：建议尺寸640*200，小于200k，仅限一张。</span>
                </td>
            </tr>
			<tr>
          	<td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
            <td>
            	<label><input type="radio" name="areaType" value="1" <c:if test="${areaType eq 1}"> checked="checked" </c:if> onclick="toggleGb('quanguo')"/>全国</label>
				<c:if test="${advType != 6 && advType != 8}">
					<label style="margin-left: 4em;"><input type="radio" name="areaType" value="4"  <c:if test="${areaType eq 4}"> checked="checked" </c:if> onclick="toggleGb('chengshi')"/>城市/区县</label>
					<label style="margin-left: 4em;"><input type="radio" name="areaType" value="3"  <c:if test="${areaType eq 3}"> checked="checked" </c:if> onclick="toggleGb('xiaoqu')"/>小区</label>
				</c:if>
            </td>
          </tr>
        </table>
        <div id="cityRange" <c:if test="${(areaType eq 1) or (areaType eq 3)}"> style="display:none;" </c:if> >
		    <h2 class="mtop10 f14">广告城市/区县</h2>
			<div class="bs-example bgebeb">
			    <table class="info-list" border="0">
			      <tr>
			        <td><div class="list-name">所在省：</div></td>
			        <td><input type="text" id="province1" class="input_text w120"></td>
			        <td><div class="list-name">所在市：</div></td>
			        <td><input type="text" id="city1" class="input_text w120"></td>
			        <td><div class="list-name">所在区：</div></td>
			        <td><input type="text" id="block1" class="input_text w120"></td>
			        <td><div class="list-name">选择状态：</div></td>
			        <td>
			        	<select class="select_normal" id="selectStatus" onchange="filterCity()">
			        		<option value="all">所有</option>
			        		<option value="selected">已选</option>
			        		<option value="unselected">未选</option>
			        	</select>
			        </td>
			      </tr>
			    </table>
			</div> 
			<h2 class="mtop10 f14">城市/区县搜索结果</h2>
			<display:table name="blockList" id="cityTable" class="info-list-02" requestURI="" >
				<display:column title="<label><a href='javascript:void(0);'><input type='checkbox' onclick='selectAllCityBox(this)'/>全选</a></label>"  sortable="true">
					<input name="blockId" type="checkbox" value="${cityTable.blockId }" <c:if test="${cityTable.isPushed=='yes'}">checked="checked"</c:if> onclick="selectCity(this)"/>
				</display:column>
				<display:column title="省份" property="addressProvinceName"></display:column>
				<display:column title="城市" property="addressCityName"></display:column>
				<display:column title="区县" property="addressBlockName"></display:column>
			</display:table>
			<h2 class="mtop20 f14">已选择城市</h2>
		    <table id="areaHasSelectedList" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		      <thead>
			      <tr class="title">
			        <th>所在省</th>
			        <th>所在市</th>
			        <th>所在区</th>
		        	<th>操作</th>
			      </tr>
		      </thead>
		      <tbody>
		      </tbody>
		    </table>
			<div class="mtop10 t_center">
				<input class="input-btn w150" type="button" value="清空已选择城市" onclick="clearCity()"/>
			</div>
        </div>
        <div id="areaSelectBox" <c:if test="${(areaType eq 1) or (areaType eq 4)}"> style="display:none;" </c:if> >
			<div class="bs-example bgebeb">
			    <table class="info-list" border="0">
			      <tr>
			        <td><div class="list-name">所在省：</div></td>
			        <td><input type="text" id="province" class="input_text w120"></td>
			        <td><div class="list-name">所在市：</div></td>
			        <td><input type="text" id="city" class="input_text w120"></td>
			        <td><div class="list-name">所在区：</div></td>
			        <td><input type="text" id="block" class="input_text w120"></td>
			        <td><div class="list-name">小区选择状态：</div></td>
			        <td>
			        	<select class="select_normal" id="selectStatus" onchange="filterGb()">
			        		<option value="all">所有</option>
			        		<option value="selected">已选</option>
			        		<option value="unselected">未选</option>
			        	</select>
			        </td>
			      </tr>
			      <tr>
			        <td><div class="list-name">物业名称：</div></td>
			        <td><input type="text" id="pcName" class="input_text w120"></td>
			        <td><div class="list-name">小区名称：</div></td>
			        <td><input type="text" id="gbName" class="input_text w120"></td>
			        <td><div class="list-name">缴费权限：</div></td>
			        <td>
			            <select class="select_normal merchant-type" id="payOpenStatus">
			                <option value="">全部</option>
			                <option value="是">已开通缴费</option>
			                <option value="否">未开通缴费</option>
			            </select>
			        </td>
			      </tr>
			    </table>
			</div> 
			<h2 class="mtop10 f14">小区搜索结果</h2>
			<display:table name="gbList" id="gbTable" class="mars info-list-02 mtop20" requestURI="" >
				<display:column title="<input type='checkbox' onclick='selectAllBox(this)'/>全选"  sortable="true">
					<input name="gbIds" type="checkbox" value="${gbTable.id }" <c:if test="${gbTable.isPushed=='yes'}">checked="checked"</c:if> onclick="selectGb(this)"/>
				</display:column>
				<display:column title="物业名称" property="propertyCompanyName"/>
				<display:column title="小区名称" property="name"/>
				<display:column title="缴费开通状态">
					<c:choose>
						<c:when test="${gbTable.propfeeCanpay eq 1}">是</c:when>
						<c:when test="${gbTable.propfeeCanpay eq 2}">否</c:when>
					</c:choose>
				</display:column>
				<display:column title="所在省" property="addressProvinceName"></display:column>
				<display:column title="所在市" property="addressCityName"></display:column>
				<display:column title="所在区" media="html" property="addressBlockName"></display:column>
				<display:column title="详细地址" property="addressDesc" />
			</display:table>
			<h2 class="mtop20 f14">已选择小区</h2>
		    <table id="areaHasSelectedList" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		      <thead>
			      <tr class="title">
			        <th>物业名称</th>
			        <th>小区名称</th>
			        <th>缴费开通状态</th>
			        <th>所在省</th>
			        <th>所在市</th>
			        <th>所在区</th>
			        <th>详细地址</th>
			        <th>操作</th>
			      </tr>
		      </thead>
		      <tbody>
		      </tbody>
		    </table>
			<div class="mtop10 t_center">
				<input class="input-btn w150" type="button" value="清空已选择小区" onclick="clearGb()"/>
			</div> 
		</div>
        <input id="sumTicket" class="info-btn save-set-prize-info-btn" type="submit" value="保存" />
        <input class="input-btn w200 mar-left15" type="button" value="返回" onclick="history.back();" style="height:40px;"/>
        <div class="h30"></div>
    </form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.itemScreen.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script src="${resourcePath}/commonjs/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
    var layermsg = null;
    $(".inputform").Validform({
        btnSubmit:"#sumTicket",
        tiptype:3,
        ignoreHidden: true,
        beforeSubmit: function (curform) {
        	if($(":radio[name='areaType']:checked").val()=="4" && $("input[name=blockId]:checkbox:checked").length==0){
				alert("请选择的城市/区县！");
				return false;
			} else if($(":radio[name='areaType']:checked").val()=="3" && $("input[name=gbIds]:checkbox:checked").length==0){
				alert("请选择小区！");
				return false;
			} else {
				$(".inputform").attr('onsubmit', 'return false;');
	            layermsg = layer.msg('正在提交，请稍候', {
	        		icon: 16,
	        		time: 0,	//指定time为0，默认该消息不关闭；若2秒后，再弹出layer.alert('加载完成', 1);该消息同时关闭。
	        		shade: 0.5
	        	});
			}
        },
        callback: function (data) {
            $(".inputform").ajaxSubmit(function (data) {
            	layer.close(layermsg);
                try {
                    data = eval(data);
                } catch (e) {
                    data = eval("(" + data + ")");
                }
                if (data.status == '0000') {
                    <c:choose>
	            		<c:when test="${advType eq 1}">alert("首页弹窗广告修改成功");</c:when>
	            		<c:when test="${advType eq 2}">alert("首页拦腰广告修改成功");</c:when>
	            		<c:when test="${advType eq 3}">alert("到家广告修改成功");</c:when>
	            		<c:when test="${advType eq 4}">alert("街坊广告修改成功");</c:when>
	            	</c:choose>
                    window.location.href = "${pageContext.request.contextPath}/adv/alertAdvList.html";
                } else {
                    alert(data.message);
                    $(".inputform").Validform().resetStatus();
                }
            });
        }
    });

	//校验时间先后顺序
	$('.input_text.icon_dt').compareTime('#date05', '#date06');
	
	//选择商品
	var changeJumpPage = $.itemScreenStart({
		selectChangeObj: '#jumpPageType',		//下拉框对象
	});
	
	//切换下拉框，显示对应内容
	changeJumpPage.changeUserRange();
});

/*<=====================================小区搜索=====================================*/
$('#pcName, #gbName, #payOpenStatus, #province, #city, #block').on('input propertychange', function(){
	filterGb();
});

function filterGb(){
	var payOpenStatus = $("#payOpenStatus").val();
	var province = $.trim($("#province").val());
	var city = $.trim($("#city").val());
	var block = $.trim($("#block").val());
	var pcName = $.trim($("#pcName").val());
	var gbName = $.trim($("#gbName").val());
	
	var searchKeys = [pcName, gbName, payOpenStatus, province, city, block];
	var $this = $("#gbTable tbody tr").hide();
	for(var i=0; i<searchKeys.length; i++){
		$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
	}
	
	var selectStatus = $("#selectStatus").val();
	if(selectStatus=="selected"){
		$this = $this.find(":checkbox").filter(":checked").parent().parent();
	} else if(selectStatus=="unselected"){
		$this = $this.find(":checkbox").filter(":not(:checked)").parent().parent();
	}
	
	$this.show();
	window.parent.TuneHeight();
}

function selectGb(o){
	var gbId = $(o).val();
	if($(o).is(":checked")){// insert
		var html="";
		$(o).parent().parent().find("td:gt(0)").each(function(){
			html += "<td>"+$(this).html()+"</td>";
		});
		
		html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeGb(this)\">删除</a></td>";
		html = "<tr gbId="+gbId+">"+html+"</tr>";
		if($("#areaSelectBox #areaHasSelectedList tbody tr").length==0){
			$("#areaSelectBox #areaHasSelectedList tbody").html(html);
		} else {
			$("#areaSelectBox #areaHasSelectedList tr:last").after(html);
		}
	} else {// remove
		$("#areaSelectBox #areaHasSelectedList").find("tr[gbId="+gbId+"]").remove();
	}
	window.parent.TuneHeight();
}

function initSelectGbs(){
	var allHtml="";
	$("#gbTable tbody tr").each(function(){
		if($(this).find(":checkbox").is(":checked")){
			var gbId = $(this).find(":checkbox").val();
			var html="";
			$(this).find("td:gt(0)").each(function(){
				html += "<td>"+$(this).html()+"</td>";
			});
			
			html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeGb(this)\">删除</a></td>";
			html = "<tr gbId="+gbId+">"+html+"</tr>";
			allHtml += html;
		}
	});
	$("#areaSelectBox #areaHasSelectedList tbody").html(allHtml);
	window.parent.TuneHeight();
}

function selectAllBox(o){
	var isChecked = $(o).is(":checked");
	$("#gbTable tbody tr").each(function(){
		if($(this).is(":visible")){
			$(this).find(":checkbox").prop("checked", isChecked);
		} else {
			if(isChecked){
				$(this).find(":checkbox").prop("checked", false);
			}
		}
	});
	
	if(isChecked){
		var allHtml="";
		$("#gbTable tbody tr").each(function(){
			if($(this).is(":visible")){
				var gbId = $(this).find(":checkbox").val();
				var html="";
				$(this).find("td:gt(0)").each(function(){
					html += "<td>"+$(this).html()+"</td>";
				});
				
				html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeGb(this)\">删除</a></td>";
				html = "<tr gbId="+gbId+">"+html+"</tr>";
				allHtml += html;
			}
		});
		$("#areaSelectBox #areaHasSelectedList tbody").html(allHtml);
	} else {
		$("#areaSelectBox #areaHasSelectedList tbody").html("");
	}
	window.parent.TuneHeight();
};

function removeGb(o){
	var delTr = $(o).parent().parent();
	var gbId = delTr.attr("gbId");
	$("#gbTable tbody tr").find(":checkbox[value="+gbId+"]").prop("checked", false);
	delTr.remove();
	window.parent.TuneHeight();
}

function clearGb(){
	$("#areaSelectBox #areaHasSelectedList tbody").html("");
	$("#gbTable").find(":checkbox").each(function(){
		if($(this).is(":checked")){
			$(this).prop("checked", false);
		}
	});
	window.parent.TuneHeight();
}

function toggleGb(type){
	if(type=='quanguo'){
		$("#areaSelectBox").hide();
		$("#cityRange").hide();
	} else if(type=='xiaoqu'){
		$("#areaSelectBox").show();
		$("#cityRange").hide();
	} else {
		$("#cityRange").show();
		$("#areaSelectBox").hide();
	}
	window.parent.TuneHeight();
}

$(function(){
	if($("#code").val()=="LA_WX"){
		$("#jumpPageType option:eq(1)").hide();
	}
	
	initSelectGbs();
	initSelectCitys();
	
	$("#code").change(function(){
		if($("#code").val()=="LA_WX"){
			$("#jumpPageType option:eq(1)").hide();
		} else {
			$("#jumpPageType option:eq(1)").show();
		}
	});
	
	$("#gbTable").find("tr:eq(0) th:eq(0) a").attr("href", "javascript:void(0);");
	$("#cityTable").find("tr:eq(0) th:eq(0) a").attr("href", "javascript:void(0);");
	$("body").show();
});
/*=====================================小区搜索=====================================>*/
/*<=====================================城市搜索=====================================*/
	$('#province1, #city1, #block1').on('input propertychange', function(){
		filterCity();
	});
	
	function filterCity(){
		var province = $.trim($("#province1").val());
		var city = $.trim($("#city1").val());
		var block = $.trim($("#block1").val());
		
		var searchKeys = [province, city, block];
		var $this = $("#cityTable tbody tr").hide();
		for(var i=0; i<searchKeys.length; i++){
			$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
		}
		
		var selectStatus = $("#cityRange #selectStatus").val();
		if(selectStatus=="selected"){
			$this = $this.find(":checkbox").filter(":checked").parent().parent();
		} else if(selectStatus=="unselected"){
			$this = $this.find(":checkbox").filter(":not(:checked)").parent().parent();
		}
		
		$this.show();
		window.parent.TuneHeight();
	}
	
	function selectCity(o){
		var blockId = $(o).val();
		if($(o).is(":checked")){// insert
			var html="";
			$(o).parent().parent().find("td:gt(0)").each(function(){
				html += "<td>"+$(this).html()+"</td>";
			});
			
			html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeCity(this)\">删除</a></td>";
			html = "<tr blockId="+blockId+">"+html+"</tr>";
			if($("#cityRange #areaHasSelectedList tbody tr").length==0){
				$("#cityRange #areaHasSelectedList tbody").html(html);
			} else {
				$("#cityRange #areaHasSelectedList tr:last").after(html);
			}
		} else {// remove
			$("#cityRange #areaHasSelectedList").find("tr[blockId="+blockId+"]").remove();
		}
		window.parent.TuneHeight();
	}
	
	function initSelectCitys(){
		var allHtml="";
		$("#cityTable tbody tr").each(function(){
			if($(this).find(":checkbox").is(":checked")){
				var blockId = $(this).find(":checkbox").val();
				var html="";
				$(this).find("td:gt(0)").each(function(){
					html += "<td>"+$(this).html()+"</td>";
				});
				html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeCity(this)\">删除</a></td>";
				html = "<tr blockId="+blockId+">"+html+"</tr>";
				allHtml += html;
			}
		});
		$("#cityRange #areaHasSelectedList tbody").html(allHtml);
		window.parent.TuneHeight();
	}
	
	function selectAllCityBox(o){
		var isChecked = $(o).is(":checked");
		$("#cityTable tbody tr").each(function(){
			if($(this).is(":visible")){
				$(this).find(":checkbox").prop("checked", isChecked);
			} else {
				if(isChecked){
					$(this).find(":checkbox").prop("checked", false);
				}
			}
		});
		
		if(isChecked){
			var allHtml="";
			$("#cityTable tbody tr").each(function(){
				if($(this).is(":visible")){
					var blockId = $(this).find(":checkbox").val();
					var html="";
					$(this).find("td:gt(0)").each(function(){
						html += "<td>"+$(this).html()+"</td>";
					});
					
					html += "<td><a class=\"blue\" href=\"javascript:void(0);\" onclick=\"removeCity(this)\">删除</a></td>";
					html = "<tr blockId="+blockId+">"+html+"</tr>";
					allHtml += html;
				}
			});
			$("#cityRange #areaHasSelectedList tbody").html(allHtml);
		} else {
			$("#cityRange #areaHasSelectedList tbody").html("");
		}
		window.parent.TuneHeight();
	};
	
	function removeCity(o){
		var delTr = $(o).parent().parent();
		var blockId = delTr.attr("blockId");
		$("#cityTable tbody tr").find(":checkbox[value="+blockId+"]").prop("checked", false);
		delTr.remove();
		window.parent.TuneHeight();
	}
	
	function clearCity(){
		$("#cityRange #areaHasSelectedList tbody").html("");
		$("#cityTable").find(":checkbox").each(function(){
			if($(this).is(":checked")){
				$(this).prop("checked", false);
			}
		});
		window.parent.TuneHeight();
	}
	/*=====================================城市搜索=====================================>*/
</script>
</html>
