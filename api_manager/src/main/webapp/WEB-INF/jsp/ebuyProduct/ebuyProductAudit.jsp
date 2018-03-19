<%@page import="com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey" %>
<%@page import="com.cnfantasia.server.api.payment.constant.EbuyDict"%>
<%@page import="com.cnfantasia.server.ms.pub.constant.PathConstants" %>
<%@page import="com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品上架管理-商品审核</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clearbox.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>

</head>
<body>
<div class="info">
    <form class="inputform">
    	<input type="hidden" name="epId" value="${product.id}" />
        <h2>商品审核</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">商品属性</div></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 供应商：</div></td>
            <td>${supplyMerchantName}</td>
          </tr>
			<tr>
				<td><div class="list-name">运营主题：</div></td>
				<td>${ebuyHomeTypeName3}</td>
			</tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 货架分类：</div></td>
            <td>${productType}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 商品名称：</div></td>
            <td>${product.name}</td>
          </tr>
			<tr>
				<td><div class="list-name">短名称：</div></td>
				<td>${product.nameMini}</td>
			</tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 购买单位：</div></td>
            <td>${product.priceUnit}</td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 商品价格：</div></td>
            <td>${product.priceDiscount/100} 元</td>
          </tr>
			<tr>
				<td><div class="list-name"><span class="red">*</span> 采购价格：</div></td>
				<td>${product.purchasePrice/100} 元</td>
			</tr>
          <tr>
            <td><div class="list-name">市场价：</div></td>
            <td>${product.price/100} 元</td>
          </tr>
			<tr>
				<td><div class="list-name">库存：</div></td>
				<td>${product.leftCount}</td>
			</tr>
          <tr>
            <td><div class="list-name">商品图片：</div></td>
            <td>
                <ul class="menu-img">
                    <c:forEach var="EbuyProductPic"  items="${picList}">
                    	<li>
	                    	<a href="<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${EbuyProductPic.urlBig }" rel="clearbox[test2]">
		                    	<img src='<%=OmsSysParamManager.getImageServerUrl("/productPic/")  %>/productPic/${fn:substring(EbuyProductPic.urlBig, 0, fn:indexOf(EbuyProductPic.urlBig, "."))}/72.${fn:substringAfter(EbuyProductPic.urlBig, ".")}' border="0" />
		                    </a>
	                 	</li>
                    </c:forEach>
                </ul>
            </td>
          </tr>
			<tr>
				<td><div class="list-name">详情图片：</div></td>
				<td>
					<ul class="menu-img">
						<c:forEach var="EbuyProductPic"  items="${introducePicList}">
							<li>
								<a href="<%=OmsSysParamManager.getImageServerUrl("/productPic/") %>/productPic/${EbuyProductPic.urlBig }" rel="clearbox[test2]">
									<img src='<%=OmsSysParamManager.getImageServerUrl("/productPic/")  %>/productPic/${fn:substring(EbuyProductPic.urlBig, 0, fn:indexOf(EbuyProductPic.urlBig, "."))}/72.${fn:substringAfter(EbuyProductPic.urlBig, ".")}' border="0" />
								</a>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
        </table>
        <h2 class="mtop40 f14"><span class="red">*</span> 商品参数</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="title">
            <td width="20%">参数名称</td>
            <td>参数说明</td>
          </tr>
          <c:forEach var="EbuyProductParameters"  items="${list}">
	          <tr>
	            <td>${EbuyProductParameters.tPropName}</td>
	            <td>${EbuyProductParameters.tPropValue}</td>
	          </tr>
          </c:forEach>
        </table>
        <!--<h2 class="mtop40 f14">物流及服务</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="20%"><span class="red">*</span> 配送方式：</td>
            <td colspan="3">快递</td>
          </tr>
          <tr>
            <td rowspan="1"><span class="red">*</span> 配送费用：</td>
            <td>按订单数</td>
            <td>1 件</td>
            <td>8.00 元</td>
          </tr>
          <tr>
            <td><span class="red">*</span> 配送说明：</td>
            <td colspan="3">当日下单次日送达</td>
          </tr>
        </table>-->
        <c:choose>
        	<c:when test="${pageType eq 'audit'}">
	        	<h2 class="mtop10 red">商品审核</h2>
		        <div class="bs-example">
		            <table class="info-list" border="0">
		              <tr>
		                <td width="90" align="right"><span class="grey">审核结果：</span></td>
		                <td colspan="5"><select class="select_normal w131 select-check" name="auditResult">
		                            <option value="pass">通过</option>
		                            <option value="nopass">不通过</option>
		                            </select></td>
		              </tr>
		              <tr class="dsn">
		                <td align="right"><span class="grey">原因：</span></td>
		                <td colspan="5"><textarea id="auditReason" class="textareas txt02" name="auditReason" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea></td>
		              </tr>
		            </table>
		        </div>
		        <input id="sumPutaway" class="info-btn" type="button" onclick="auditData();" value="提 交" />
		        <input class="input-btn w200 mar-left15" type="button" value="返回" onclick="history.back();" style="height:40px;">
        	</c:when>
        	<c:otherwise>
        		<input class="input-btn" type="button" value="返回" onclick="history.back();" style="margin-top: 10px;">
        	</c:otherwise>
        </c:choose>
	 </form>
</div>
<div class="layer-classify classify-editable dsn">
    <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
      <tr class="title">
        <td colspan="2">将该商品上架到</td>
      </tr>
      <tr>
        <td <c:if test="${countEqOnApp>0}"> colspan="2"</c:if>>
        	<label><input type="checkbox" name="shelfAddress" value="<%=EbuyDict.WlAppType.Jfq%>" checked="checked"> app</label>
        </td>
        <c:if test="${countEqOnApp eq 0}">
	        <td>
	        	<label><input type="checkbox" name="shelfAddress" value="<%=EbuyDict.WlAppType.Jfq_Light_App%>" checked="checked"> 轻应用</label>
	        </td>
        </c:if>
      </tr>
    </table>
    <div class="mtop20 t_center">
    	<input class="info-btn small-btn w80" type="button" value="确定" onclick="auditPass();" >
    	<input class="input-btn w80 mar-left15 classify-cancel-btn" type="button" value="取消" />
    </div>
</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		tiptype:3,
		dragonfly:true,
		ignoreHidden:true
	});
    
	$('.classify-cancel-btn').click(function(){
		layer.closeAll();
	});
})(jQuery);

// 跳转进来时搜索条件
var searchParam = JSON.parse('${searchForm}');
var formParam = "";
for(var i=0; i<searchParam.length; i++){ 
	if(i>0){formParam += "&";}
	formParam += searchParam[i].name+"="+searchParam[i].value; 
}

function auditData(){
	var auditResult = $("select[name=auditResult] option:selected").val();
	if(auditResult=="pass"){
		$.layer({
			type: 1,
			shade: [0.4,'#000000'],
			area: ['auto', 'auto'],
			title: false,
			border : [5, 0.3, '#000'],
			page: {dom : '.classify-editable'}
		});
	} else {
		var epId= $("input[name='epId']").val();
		var auditReason = $("#auditReason").val();
		$.post("${pageContext.request.contextPath}/ebuyProduct/auditBatchUpShelf.html",{epId:epId,auditResult:auditResult,auditReason:auditReason},function(data) {
			if(data=="审核成功!!!"){
				alert("操作成功！", 2000);
				location.href="${pageContext.request.contextPath}/ebuyProduct/auditSearch.html?state=up&"+formParam;
			}else{
				alert("操作失败！");
			}
		});
	}
}

function auditPass(){
	var shelfAddress = "";
	$("input[name='shelfAddress']:checkbox").each(function(){ 
 	    if($(this).is(":checked")){
 			shelfAddress += $(this).val()+",";
 		}
     });
	if(shelfAddress.length==0){
		alert("请选择上架的平台！");
		return;
	}
	
	var params = new Array();
	params.push({"epId":$("input[name='epId']").val(), "shelfId":"${shelfId}", "wlAppType":"${wlAppType}"});
	$.post("${pageContext.request.contextPath}/ebuyProduct/upShelf.html",{"params":JSON.stringify(params), "shelfAddress":shelfAddress},function(data) {
		if($.trim(data)=="上架成功!!!"){
			alert("操作成功！");
			location.href="${pageContext.request.contextPath}/ebuyProduct/auditSearch.html?state=up&"+formParam;
		} else{
			alert("操作失败！");
		}
	});
}
</script>
</html>
