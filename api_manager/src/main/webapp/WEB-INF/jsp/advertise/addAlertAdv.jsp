<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <base href="<%=basePath%>//"/>
    <title>新增推广商品广告</title>
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
		<span class="grey">ID：<span class="data-obj-id"></span></span><span class="grey mleft10">描述：<span class="data-obj-name">海吉星</span></span>
		<div class="icon-delete"></div>
	</li>
    <li class="posrelative address-selected dsn">
        <span class="address-name">招东小区</span>
        <div class="icon-delete"></div>
    </li>
    <form class="inputform" action="${pageContext.request.contextPath}/adv/addAlertAdv.html" method="post">
    	<input type="hidden" name="advType" value="${advType}"/>
        <h2>
        	新增广告
        </h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><div class="list-name">广告类型：</div></td>
            <td>
	            <c:choose>
	        		<c:when test="${advType eq 1}">首页弹窗广告</c:when>
	        		<c:when test="${advType eq 2}">首页拦腰广告</c:when>
	        		<c:when test="${advType eq 3}">到家广告</c:when>
	        		<c:when test="${advType eq 4}">街坊广告</c:when>
	        		<c:when test="${advType eq 5}">到家底部广告</c:when>
	        		<c:when test="${advType eq 6}">微信购物小票广告</c:when>
	        		<c:when test="${advType eq 7}">车禁缴费广告</c:when>
					<c:when test="${advType eq 8}">体验店banner广告</c:when>
	        	</c:choose>
            </td>
          </tr>
			<tr>
				<td width="20%"><div class="list-name"><span class="red">*</span> 广告名称：</div></td>
				<td><input type="text" name="ebuyAdvertise.tittle" class="input_text pp" maxlength="20" datatype="*" placeholder="请填写广告名称" nullmsg="请填写广告名称！"></td>
			</tr>
			<c:if test="${advType eq 5}">
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第一行文案：</div></td>
					<td><input type="text" name="firstLine" class="input_text pp" maxlength="10" datatype="*" placeholder="第一行文案" nullmsg="第一行文案！"></td>
				</tr>
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第二行文案：</div></td>
					<td><input type="text" name="secondLine" class="input_text pp" maxlength="10" datatype="*" placeholder="第二行文案" nullmsg="第二行文案！"></td>
				</tr>
				<tr>
					<td width="20%"><div class="list-name"><span class="red">*</span> 第三行文案：</div></td>
					<td><input type="text" name="thirdLine" class="input_text pp" maxlength="10" datatype="*" placeholder="第三行文案" nullmsg="第三行文案！"></td>
				</tr>
			</c:if>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 推广时间：</div></td>
            <td>
                <input type="text" name="ebuyAdvertise.startTime" class="input_text icon_dt" id="date05" placeholder="请选择起始时间" datatype="dateTime" nullmsg="请选择起始时间！"> 至
                <input type="text" name="ebuyAdvertise.endTime" class="input_text icon_dt" id="date06" placeholder="请选择结束时间" datatype="dateTime" nullmsg="请选择结束时间！"></td>
          </tr>
          <c:if test="${advType eq 1}">
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 展示频率：</div></td>
	            <td>
	                <select class="select_normal" name="ebuyAdvertise.frequency" datatype="*" nullmsg="请展示频率！">
	                    <option value="">选择弹框频率</option>
	                    <option value="1">每天一次</option>
	                    <option value="2">展示期内用户点击后不弹</option>
	                </select>
	            </td>
	          </tr>
          </c:if>
			<c:if test="${advType != 5 && advType != 6}">
			  <tr>
					<td><div class="list-name">APP版本起始：</div></td>
					<td>
						<input name="ebuyAdvertise.version" type="text" class="input_text w150 pp" placeholder="开始版本号" maxlength="6" ignore="ignore" datatype="n" errormsg="版本号为数字！"> -
						<input name="ebuyAdvertise.maxVersion" type="text" class="input_text w150 pp" placeholder="结束版本号" maxlength="6" ignore="ignore" datatype="n" errormsg="版本号为数字！">
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
		              	          <input name="ebuyAdvertise.code" type="text" class="input_text w150 pp" value="MAIN_BUSINESS_AD" placeholder="code" title="弹窗广告code不可编辑" maxlength="20" ignore="ignore" readonly="readonly"/>
		              	      </c:when>
		              	      <c:when test="${advType eq 3}">
			              	      	<select name="ebuyAdvertise.code" class="select_normal">
					                    <option value="WX">APP</option>
					                    <option value="LA_WX">轻应用</option>
					                </select>
		              	      </c:when>
		              	      <c:otherwise>
		              	           <input name="ebuyAdvertise.code" type="text" class="input_text pp" placeholder="code" maxlength="20" ignore="ignore"/>
		              	      </c:otherwise>
		              	  </c:choose>
		              </td>
		          </tr>
	          </c:if>
			<c:if test="${advType != 5 && advType != 8}">
	          <tr>
	            <td><div class="list-name"><span class="red">*</span> 跳转类型：</div></td>
	            <td>
	                <select id="jumpPageType" name="ebuyAdvertise.type" class="select_normal" datatype="*" nullmsg="请选择跳转类型！">
	                    <option value="">选择跳转类型</option>
	                    <c:if test="${(advType eq 1) or (advType eq 2)}">
	                    <option value="2">产品(APP)</option>
	                    </c:if>
	                    <c:if test="${advType eq 3}">
	                    <option value="5">产品(APP)</option>
	                    </c:if>
	                    <option value="1">H5页面</option>
	                    <c:if test="${(advType == 1) or (advType == 2)}">
	                    	<option value="4">不跳</option>
	                    </c:if>
	                </select>
	            </td>
	          </tr>
			</c:if>
			<c:if test="${advType == 5 || advType == 8}">
				<tr>
		            <td><div class="list-name"><span class="red">*</span> 跳转类型：</div></td>
		            <td>
		                <select id="jumpPageType" name="ebuyAdvertise.type" class="select_normal" onchange="checkNull()">
		                    <option value="5">商品详情</option>
		                    <option value="1">H5页面</option>
		                </select>
		            </td>
		          </tr>
				<tr class="search-input01 swap-info swap-val-2 swap-con swap-box-5">
					<td><div class="list-name"><span class="red">*</span> 推广商品：</div></td>
					<td>
						<input type="text" maxlength="8" class="input_text w120" placeholder="请输入关键字" />
						<input class="input-btn item-search-btn" type="button" value="搜索">
					</td>
				</tr>
				<tr class="city-con01 swap-info swap-val-2 swap-con swap-box-5">
					<td><div class="list-name">搜索结果：</div></td>
					<td>
						<ul class="address-list search-box01">
						</ul>
					</td>
				</tr>
				<tr class="city-con01 swap-info swap-val-2 swap-con swap-box-5">
					<td><div class="list-name">已选商品：</div></td>
					<td>
						<ul class="address-list selected-box01">
						</ul>
						<input type="hidden" id="productTip" class="select-input01 input_text w80 dsn" datatype="*" nullmsg="请选择商品！" />
					</td>
				</tr>
			</c:if>
		    <c:if test="${advType != 5 && advType != 6 && advType != 8}">
	          <tr class='swap-con 
	          	<c:if test="${(advType eq 1) or (advType eq 2)}"> swap-box-2 </c:if> 
	          	<c:if test="${advType eq 3}"> swap-box-5 </c:if> 
	          dsn'>
	            <td><div class="list-name"><span class="red">*</span> 跳转页面：</div></td>
	            <td>
	                <select name="alertAppPageType" class="select_normal" datatype="*" nullmsg="请选择跳转页面！">
	                    <option value="">选择跳转页面</option>
	                    <%--<option value="1">物业报修</option>--%>
	                    <c:choose>
	                    	<c:when test="${advType eq 1}">
		                    	<option value="2">物业公告</option>
			                    <option value="3">智能停车</option>
			                    <option value="4">家政维修</option>
			                    <option value="5">店铺列表</option>
			                    <option value="6">超市首页</option>
			                    <option value="7">物业缴费</option>
	                    	</c:when>
	                    	<c:when test="${advType eq 2}">
	                    		<option value="1">维修</option>
			                    <option value="2">物业缴费</option>
	                    	</c:when>
	                    	<c:when test="${advType eq 3}">
	                    		<c:forEach items="${communitySupplyTypeList}" var="communitySupplyType">
	                    			<option value="${communitySupplyType.id}">${communitySupplyType.name}</option>
	                    		</c:forEach>
	                    	</c:when>
	                    </c:choose>
	                </select>
	            </td>
	          </tr>
          </c:if>
	          <tr class="swap-con swap-box-1 dsn">
	            <td><div class="list-name"><span class="red">*</span> H5页面地址：</div></td>
	            <td><input name="ebuyAdvertise.linkUrl" id="linkUrlTip" type="text" class="input_text w300 pp" maxlength="300" datatype="*" nullmsg="请填写H5页面地址！" <c:if test="${advType eq 5}">ignore="ignore"</c:if>></td>
	          </tr>
          <c:if test="${(advType eq 1) or (advType eq 3)}">
            <tr class="swap-con swap-box-1 dsn">
                <td><div class="list-name"><span class="red">*</span> 与APP交互的H5约定code：</div></td>
                <td>
                    <input name="ebuyAdvertise.androidAddr" type="text" maxlength="30" class="input_text w150 pp" placeholder="安卓code"> -
                    <input name="ebuyAdvertise.iosAddr" type="text" maxlength="30" class="input_text w150 pp" placeholder="IOS code"> <span class="red">（无则不填）</span>
                </td>
            </tr>
            </c:if>
          <tr>
            <td align="right"><span class="red">*</span> 广告图片：</td>
            <td class="item-upload-img">
                <div class="uploadPreview01 mright6">
                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
                    <img class="imgPreview" width="82" height="82" src="images/addimg01.jpg" />
                </div>
                <span class="f12">注：建议尺寸640*200，小于200k，仅限一张。</span>
                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传广告图片！" />
            </td>
          </tr>
          <tr>
          	<td width="20%"><div class="list-name"><span class="red">*</span> 用户范围：</div></td>
            <td>
            	<label><input type="radio" name="areaType" value="1" checked="checked" onclick="toggleGb('quanguo')"/>全国</label>
				<c:if test="${advType != 6 && advType != 8}">
					<label style="margin-left: 2em;"><input type="radio" name="areaType" value="4" onclick="toggleGb('chengshi')"/>城市/区县</label>
					<label style="margin-left: 2em;"><input type="radio" name="areaType" value="3" onclick="toggleGb('xiaoqu')"/>小区</label>
				</c:if>
            </td>
          </tr>
        </table>
        <div id="cityRange" style="display:none;">
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
					<input name="blockId" type="checkbox" value="${cityTable.blockId }" onclick="selectCity(this)"/>
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
        <div id="areaSelectBox" style="display:none;">
        	<h2 class="mtop10 f14">广告小区</h2>
			<div class="bs-example bgebeb">
			    <table class="info-list" border="0">
			      <tr>
			        <td><div class="list-name">所在省：</div></td>
			        <td><input type="text" id="province" class="input_text w120"></td>
			        <td><div class="list-name">所在市：</div></td>
			        <td><input type="text" id="city" class="input_text w120"></td>
			        <td><div class="list-name">所在区：</div></td>
			        <td><input type="text" id="block" class="input_text w120"></td>
			        <td><div class="list-name">选择状态：</div></td>
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
        
        <input id="sumTicket" class="info-btn" type="submit" value="发布" />
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
	var layermsg = null;
    //表单验证
    $(".inputform").Validform({
        btnSubmit:"#sumTicket",
        tiptype:3,
        ignoreHidden: true,
        beforeSubmit: function (curform) {
        	if($(":radio[name='areaType']:checked").val()=="4" && $("input[name=blockId]:checkbox:checked").length==0){
				alert("请选择城市/区县！");
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
	            		<c:when test="${advType eq 1}">alert("首页弹窗广告添加成功");</c:when>
	            		<c:when test="${advType eq 2}">alert("首页拦腰广告添加成功");</c:when>
	            		<c:when test="${advType eq 3}">alert("到家广告添加成功");</c:when>
	            		<c:when test="${advType eq 4}">alert("街坊广告添加成功");</c:when>
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

//开始搜索
$('.item-search-btn').click(function(){
    var thisSearchBox = $('.search-box01:visible');
    currentVal = $.trim($('.search-input01 input.input_text').val());
    var url = '<%=basePath%>/adv/getDredgeProductList.json?name=' + encodeURI(currentVal, "utf-8");
    var advType = ${advType};
    if(advType == 8) {
		url = '<%=basePath%>/adv/getShelfProductForAdv.json?appType=1&experienceStore=true&qryStr=' + encodeURI(currentVal, "utf-8");
    }
    if(!currentVal == ''){
        ajaxSearch01(url,thisSearchBox, advType);
    }
});

var $searchList = $('.search-list-con.dsn');
//搜索内容
function ajaxSearch01(url,objBox, advType){
    $.getJSON(url, function(data){

        var $objBox = $(objBox);
        var searchList = '';

        var $searchListClone = $searchList.clone(true);
        $.each(data, function (i, aaList) {
            //搜索
			if (advType == 5) {
                searchList += '<li><span class="address-name" data-itemid="'+aaList.id+'">' + aaList.name + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.id + '</span></span><span class="grey mleft10">描述：<span class="data-obj-name">'+aaList.desc+'</span></span></li>';
            }
            if (advType == 8) {
                searchList += '<li><span class="address-name" data-itemid="'+aaList.shelfId+'">' + aaList.productName + '</span><br><span class="grey">ID：<span class="data-obj-id">' + aaList.shelfId + '</span></span><span class="grey mleft10">供应商：<span class="data-obj-name">'+aaList.merchantName+'</span></span></li>';
            }
        });

        if(searchList == ''){
            searchList = '<span>' + '没有找到相关内容' + '</span>';
        }
        $objBox.html(searchList);
        //重置页面高度
        window.parent.TuneHeight();
    });

    //删除
    $(document).on("click", '.address-list.selected-box01 li .icon-delete', function(){
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

    var $addressSelectedLi01 = $('.address-selected01.dsn');
    var addressSelectedNum01 = 0;
    var selectCountNum01 = 0;
    $(document).on("click", '.address-list.search-box01 li:visible', function () {
        var $this = $(this);
        //选择
        if (!$this.hasClass('on')) {
            var addressSelectedClass = 'address-selected01-';
            var $addressSelectedLiClone01 = $addressSelectedLi01.clone(true);
            var thisName = $this.find('.address-name').text();
            var thisId = $this.find('.data-obj-id').text();
            var thisSupplierName = $this.find('.data-obj-name').text();
            var id = $this.find('span').attr('data-itemid');
            //防止重复选择
            var areaSelectedNum = 0;
            $('.address-list.selected-box01 li:visible').each(function () {
                var thisAreaName = $(this).find('.address-name').text();
                if (thisAreaName === thisName) {
                    areaSelectedNum += 1;
                }
            })
            if (areaSelectedNum > 0) {
                alert('请勿重复选择！');
                return false;
            }

            if (selectCountNum01 > 0) {
                alert("只能选择一个！");
                return
            }
            addressSelectedNum01 += 1;
            selectCountNum01 += 1;

            //已选，校验通过
            $('.address-list:visible').siblings('.select-input01').val(selectCountNum01);
            $(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));

            addressSelectedClass = addressSelectedClass + addressSelectedNum01;

            $this.addClass('on ' + addressSelectedClass).attr('data-class', addressSelectedClass);
            $addressSelectedLiClone01.find('.address-name').text(thisName);
            $addressSelectedLiClone01.find('.data-obj-id').text(thisId);
            $addressSelectedLiClone01.find('.data-obj-name').text(thisSupplierName);
            if (${advType == 5}) {
                $addressSelectedLiClone01.append('<input type="hidden" name="dredgeProductId" value="' + id + '">');
            } else if (${advType == 8}) {
                $addressSelectedLiClone01.append('<input type="hidden" name="storeShelfId" value="' + id + '">');
            }
            $addressSelectedLiClone01.removeClass('dsn').addClass(addressSelectedClass).attr('data-class', addressSelectedClass).appendTo('.selected-box01:visible');
            //反选
        } else {
            var thisUnSelectedClass = $this.attr('data-class');
            $this.removeClass();
            $('.selected-box01').find('.' + thisUnSelectedClass).remove();
            selectCountNum01 -= 1;
            //没有选择，校验不通过
            if (selectCountNum01 == 0) {
                $('.address-list:visible').siblings('.select-input01').val('');
                $(".inputform").Validform({}).check(false, $('.address-list:visible').siblings('.select-input01'));
            }
        }
        //重置页面高度
        window.parent.TuneHeight();
    });
}

function checkNull(){
	var jumpPageType = $("#jumpPageType").val();
	if(jumpPageType==5){
		$("#productTip").attr("ignore", "ignore");
		$("#linkUrlTip").removeAttr("ignore");
	} else if(jumpPageType==1){
		$("#productTip").removeAttr("ignore", "ignore");
		$("#linkUrlTip").attr("ignore");
	}
}
</script>
</html>
