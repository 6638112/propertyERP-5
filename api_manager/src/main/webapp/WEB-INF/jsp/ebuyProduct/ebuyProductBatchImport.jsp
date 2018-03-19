<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>体验店商品批量导入</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<style>
			.input_text{height:30px !important;}
			h3{font-size: 15px;}
			.content{width:80%;}
			.typeInfo td{border: 1px solid #eeeeee;text-align:center;}
		</style>
	</head>
	<body>
		<div class="info posrelative content">
			<h2>体验店商品批量导入</h2>
			<form enctype="multipart/form-data" id="productForm" class="inputform1" action="${pageContext.request.contextPath}/ebuyProduct/saveBatchImport4JFQStore.html" method="post">
		        <h3>第一步:下载模板</h3>
		        <div class="bs-example bgebeb">
		            <table class="info-list" border="0">
		                <tr>		                    
		                    <td><a id="downPpInfoBtn" class="blue" href="${pageContext.request.contextPath}/docs/jfq_store_product_import_template.xls"><img src="../images/download-icon.jpg" />下载导入模版</a></td>
		                </tr>
		                <tr>
		                  	<td style="padding-top:10px;">
		                  		<p>说明：</p>
		                  		<p>1、【货品条码】为商品唯一识别码，系统通过此码更新商品信息.</p>
		                  		<p>2、【货品条码】或【货品名称】为空的商品记录，将不进行导入.</p>
		                  		<p>3、【货品名称】显示为轻应用&APP的【商品名称】.</p>
		                  		<p>4、【类别】需调整为对应客户端的分类.</p>
		                  		<p>5、【销售价】等于【零售价】*售价比率.</p>
		                  		<p>6、【市场价】对应APP&轻应用原价。注意：若【市场价】为空或小于等于【零售价】时，导入时系统会自动调整为【零售价】的1.05倍.</p>
		                  		<p>7、【当前库存】均按表格中实际数值进行导入，包含小数点的库存取整导入.</p>
		                  		<p>8、【上下架状态】导入成功的商品自动更新为上架状态，下架需在客户端操作.</p>
		                  		<p>9、【进货价】对应单品录入中的【采购价】.</p>
		                  	</td>
		                </tr>
		            </table>
		        </div>
		        <h3>第二步:上传数据</h3>
		        <div class="bs-example bgebeb">
		            <table class="info-list" border="0">
		                <tr>
		                    <td>
		                        <input id="excelFile" name="excelFile" type="file" style="width: 300px;" title="上传" />
		                  		<input class="input-btn w160" type="button" value="上传" onclick="uploadProducts()"/>
		                  	</td>
		                </tr>
		            </table>
		        </div>
		        <h3>第三步:设置分类/比例</h3>
		        <div class="bs-example bgebeb">
		            <table class="info-list typeInfo" border="0">
		            	<thead>
			            	<tr>
			                    <td>乐掌柜类别名称</td>
			                    <td>对应体验店分类</td>
			                    <td>设置售价比率（如0.8，代表线上售价为线下售价的0.8倍）</td>
			                </tr>
		            	</thead>
		            	<tbody></tbody>
		            </table>
		        </div>
		        <input class="input-btn w160" type="button" value="完成" id="doneBtn" onclick="doneUpload()"/>
		    </form>		    
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript">		
		function checkFile(){
			var excelFile = $("#excelFile").val();
			if(excelFile==null || excelFile=="" || excelFile.indexOf(".xls")==-1){
				alert("上传文件请选择Excel文件！");
				return false;
			}
			return true;
		}
		
	    // excel文件上传（商品类型）
		function uploadProducts(){
			if(checkFile()){
				$('#productForm').attr("action", "${pageContext.request.contextPath}/ebuyProduct/uploadJfqStoreProductType.json");
				$.cxhd.loading();
				$('#productForm').ajaxForm({  
	                dataType: 'json',  
	                success: function(data){  
	                	$.cxhd.hide();
	                	if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
	                		var dataValue = data.dataValue;
	                		var jfqStoreProductTypeInfoList = dataValue.jfqStoreProductTypeInfoList;
	                		var ebuyProductTypeList = dataValue.ebuyProductTypeList;
	                		var html = "";
	                		for(var i=0; i<jfqStoreProductTypeInfoList.length; i++){
	                			html += "<tr>";
	                			html += "    <td>"+jfqStoreProductTypeInfoList[i].name+"<input type='hidden' name='typeName' value='"+jfqStoreProductTypeInfoList[i].name+"'/></td>";
	                			html += "    <td>";
	                			html += "        <select id='typeId' name='typeId' class='select_normal'>";
	                			html += "        <option value=''>请选择</option>";
	                			for(var k =0; k<ebuyProductTypeList.length; k++){
	                				var selected = "";
	                				if(jfqStoreProductTypeInfoList[i].typeId == ebuyProductTypeList[k].id){
	                					selected = " selected";
	                				}
	                				html += "        <option value='"+ebuyProductTypeList[k].id+"'"+selected+">"+ebuyProductTypeList[k].typeName+"</option>";
	                			}
	                			html += "        </select>";
	                			html += "    </td>";
	                			html += "    <td><input type='number' id='sellPriceRate' name='sellPriceRate' class='input_text' min='0.01' step='0.01' value='";
	                			var sellPriceRate = "";
	                			if(jfqStoreProductTypeInfoList[i].sellPriceRate!=null && jfqStoreProductTypeInfoList[i].sellPriceRate!='undefined' && jfqStoreProductTypeInfoList[i].sellPriceRate>0){
	                				sellPriceRate = jfqStoreProductTypeInfoList[i].sellPriceRate;
	                			}
	                			html += sellPriceRate + "'/></td>";
	                			html += "</tr>";
	                		}
	                		$(".typeInfo tbody").html(html);
	                		window.parent.TuneHeight();
	                	} else {
	                		alert(data.message);
	                	}
	                }
	            }).submit();  
			}
		}
		
		// 批量导入完成
		function doneUpload(){
			if(checkFile()){
				var pass = true;
				$(".typeInfo tbody tr").each(function(){
					var typeId = $(this).find("#typeId").val();
					if(pass && typeId==""){
						alert("请选择客户端分类！");
						$(this).focus();
						pass = false;
					}
					var sellPriceRate = $(this).find("#sellPriceRate").val();
					if(pass && sellPriceRate==""){
						alert("售价比率不能为空！");
						$(this).focus();
						pass = false;
					}
					if(pass && sellPriceRate<=0.01){
						alert("售价比率必须>=0.1！");
						$(this).focus();
						pass = false;
					}
				});
				if(pass){
					$.cxhd.loading();
					$("#productForm").attr("action", "${pageContext.request.contextPath}/ebuyProduct/saveBatchImport4JFQStore.html");
					$('#productForm').ajaxForm({  
		                dataType: 'json',  
		                success: function(data){  
		                	$.cxhd.hide();
		                	alert(data.message);
		                	if(data.status == '<%=CommConstants.ResponseStatus.SUCCESS%>') {
		                		location = location;
		                	}	                	
		                }
		            }).submit(); 
				}
			}
		}		
	</script>
</html>