<%@page import="com.cnfantasia.server.common.CommConstants"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>单品录入</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<style>
			.input_text{height:30px !important;}
		</style>
	</head>
	<body>
		<div class="info posrelative">
		    <form class="inputform" id="addForm" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/ebuyProduct/addEbuyProduct.html">
		        <h2>录入商品</h2>
		        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		          <tr class="list-title">
		            <td colspan="2"><div align="left" class="f14 bold">商品属性</div></td>
		          </tr>
		          <tr>
		            <td width="20%"><div class="list-name"><span class="red">*</span>供应商：</div></td>
		            <td>
		                <select class="select_normal" datatype="*" nullmsg="请选择供应商" name="supplyMerchant" id="supplyMerchant">
		                    <option value="">选择供应商</option>
		                    <c:forEach var="supplyMerchant"  items="${supplyMerchants}">
		                    	<option value="${supplyMerchant.id}">${supplyMerchant.name}</option>
		                    </c:forEach>
		                </select>
		            </td>
		          </tr>
		          <tr>
		            <td><div class="list-name">运营主题：</div></td>
		            <td>
		                <select class="select_normal" name="theme">
		                    <option value="">选择主题</option>
		                    <c:forEach var="ebuyHomeType"  items="${ebuyHomeTypes}">
		                    	<option value="${ebuyHomeType.id}">${ebuyHomeType.name3}</option>
		                    </c:forEach>
		                </select>
		            </td>
		          </tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 货架分类：</div></td>
		            <td>
		                <select class="select_normal" datatype="*" nullmsg="请选择货架分类" name="productType">
		                    <option value="">选择分类</option>
		                    <%-- 后台默认查询app类型 --%>
		                    <c:forEach var="ebuyProductType"  items="${ebuyProductTypes}">
		                    	<option value="${ebuyProductType.id}">${ebuyProductType.typeName}</option>
		                    </c:forEach>
		                </select>
		            </td>
		          </tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 商品名称：</div></td>
		            <td><input type="text" class="input_text w300" name="productName" maxlength="100" datatype="*1-100" nullmsg="请完善商品信息" errormsg="商品名称为1-100个字符" /></td>
		          </tr>
		          <tr>
		            <td><div class="list-name">短名称：</div></td>
		            <td><input type="text" class="input_text pp w300" name="nameMini" value="" maxlength="45" ignore="ignore" datatype="*" nullmsg="请完善商品信息" /></td>
		          </tr>
					<tr id="codeTr" class="dsn">
						<td><div class="list-name">乐掌柜商品编码：</div></td>
						<td><input type="text" class="input_text pp w300" id="code" name="code" value="" maxlength="45" ignore="ignore" datatype="*" nullmsg="请完善商品信息" /></td>
					</tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 商品价格：</div></td>
		            <td><input type="text" class="input_text  w120" name="priceDiscount" maxlength="16" value="" placeholder="标准售价" datatype="numberFixTwo" nullmsg="请完善商品信息" errormsg="请填写数字，可带两位小数" /> 元</td>
		          </tr>
		          <tr>
		            <td><div class="list-name"><span class="red">*</span> 采购价格：</div></td>
		            <td><input type="text" class="input_text  w120" name="purchasePrice" maxlength="16" value="" placeholder="采购价格" datatype="numberFixTwo" nullmsg="请完善商品信息" errormsg="请填写数字，可带两位小数" /> 元</td>
		          </tr>
		          <tr>
		            <td><div class="list-name">市场价：</div></td>
		            <td><input type="text" class="input_text  w120" name="price" placeholder="须高于售价" maxlength="16" value="" ignore="ignore" datatype="numberFixTwo" nullmsg="请完善商品信息" errormsg="请填写数字，可带两位小数" /> 元</td>
		          </tr>
		          <tr>
		            <td><div class="list-name">库存：</div></td>
		            <td><input type="text" class="input_text  w120" name="leftCount" maxlength="16" placeholder="如：999" value="" ignore="ignore" datatype="fei0zhengzhengshu" nullmsg="请完善商品信息" errormsg="请填写大于0的整数" /></td>
		          </tr>
					<tr>
						<td><div class="list-name">商品描述：</div></td>
						<td><input type="text" class="input_text w500" name="productDesc" maxlength="100" placeholder="商品描述" value="" ignore="ignore" datatype="*"/></td>
					</tr>
					<tr>
		            <td><div class="list-name">浏览图片：</div></td>
		            <td class="item-upload-img">
		                <div class="uploadPreview01 img-new-add marb15 img-hide">
		                	<div class="img-delete-btn">删除</div>
		                	<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="productPic" class="uploadImage02" />
		                	<img class="imgPreview" width="82" height="82" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
		                </div>
		                <div class="uploadPreview01 marb15 mright6">
		                	<div class="img-delete-btn">删除</div>
		                	<input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="productPic" class="uploadImage02" />
		                	<img class="imgPreview" width="82" height="82" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
		                </div>
		                <div class="uploadPreview01 img-add-btn marb15"></div><br />
		                <span class="f12">注：图片尺寸640x540像素，建议图片2M以内，最多可上传5张</span>
		                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传商品图片！" />
		                <div id="picDiv" style="width: 1px; height: 1px;"></div>
		            </td>
		          </tr>
		          <tr>
		            <td><div class="list-name">商品详情图片：</div></td>
		            <td id="dragItems">
		                <div class="uploadPreview01 imgDesc-new-add drag-item marb15 img-hide">
		                    <div class="img-delete-btn">删除</div>
		                    <div class="img-desc-text">添加描述</div>
		                    <div class="img-desc-box dsn"><span class="desc-text-info">文字描述</span><span class="icon-edit"><img width="14" height="14" src="${pageContext.request.contextPath}/images/icon-edit.png" /></span></div>
		                    <input class="desc-text-input dsn" type="text" name="descText" />
		                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
		                    <img class="imgPreview" width="82" height="82" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
		                </div>
		                <div class="uploadPreview01 marb15 mright6 drag-item">
		                	<div class="img-delete-btn">删除</div>
		                    <div class="img-desc-text">添加描述</div>
		                    <div class="img-desc-box dsn"><span class="desc-text-info">文字描述</span><span class="icon-edit"><img width="14" height="14" src="${pageContext.request.contextPath}/images/icon-edit.png" /></span></div>
		                    <input class="desc-text-input dsn" type="text" name="descText" value="" />
		                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="photoimage" class="uploadImage02" />
		                    <img class="imgPreview" src="${pageContext.request.contextPath}/images/addimg01.jpg" />
		                </div>
		                <div class="uploadPreview01 imgDesc-add-btn marb15"></div><br />
		                <span class="f12 marb15">注：图片尺寸640x540像素，建议图片2M以内，不限张数</span>
		            </td>
		          </tr>
		        </table>
				<h2 class="mtop40 f14"><span class="red">*</span> 商品参数  <input id="createParam" class="input-btn mleft10" type="button" value="添加商品参数" /></h2>
				<table class="info-list-02 product_property" border="0" cellpadding="0" cellspacing="1">
					<tr class="title">
						<td width="20%">参数名称</td>
						<td>
							参数说明
						</td>
					</tr>
				</table>
		        <input type="hidden" name="auditStatus" id="auditStatus"/>
		        <input class="info-btn btnSubmit" type="submit" value="申请上架" onclick="$('#auditStatus').val(3);"/> 
		        <input class="info-btn mar-left15 info-btn01 mtop0 btnSubmit" type="submit" value="保存草稿" onclick="$('#auditStatus').val(2)"/>
		    </form>
			<table style="display:none;">
				<tr class="paramAdded dsn">
					<td align="right"><div align="right"><input class="input_text pp w100 t_right" name="propName" type="text" width="auto" maxlength="45"/>：</div></td>
					<td><input class="input_text pp w500" name="propValue" type="text" width="auto"  maxlength="200"/> <a class="blue mar-left15 param-delete" href="#">删除</a></td>
				</tr>
			</table>
		</div>
		
		<div class="img-desc-text-box dsn">
		    <h3>添加图片描述</h3>
		    <textarea class="textareas txt02 w470" name="" cols="" rows="5" ></textarea>
		    <div class="mar-left15">还可输入<span class="leftNum">100</span>字</div>
		    <div>
		        <input id="checkDescBtn" class="info-btn mar-left15" type="submit" value="确定" /> 
		        <input id="backDescBtn" class="info-btn mar-left15 info-btn01 mtop0" type="submit" value="取消" />
		    </div>
		</div>
		<div class="shape-box dsn"></div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tool.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/Sortable.js"></script>
	<script type="text/javascript">
		(function($){
		    //表单验证
			$(".inputform").Validform({
				tiptype:3,
				dragonfly:false,
				btnSubmit:".btnSubmit",
				beforeCheck:function(curform){
					var price = $.trim($("input[name=price]").val());
					var priceDiscount = $.trim($("input[name=priceDiscount]").val());
					if(price*100<priceDiscount*100){
						alert("市场价格应高于商品价格！");
						return false;
					} 
				},
				beforeSubmit:function(curform){
					$.Showmsg('正在提交，请稍后');
				}
			});	
		    
			//拖拽排序
			var dragItems = document.getElementById('dragItems');
			new Sortable(dragItems,{
				draggable: ".drag-item"	  // 指定哪些选项需要排序
			});
			<%--
			//上传图片预览
			$(".uploadImage02").change(function() {
				var $pic = $(this).siblings(".imgPreview"),
					$file = $(this); 
			 
				var ext=$file.val().substring($file.val().lastIndexOf(".")+1).toLowerCase();
			 
				 // gif在IE浏览器暂时无法显示
				 if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
					 alert("图片的格式必须为png或者jpg或者jpeg格式！"); 
					 return;
				 }
				 var isIE = navigator.userAgent.match(/MSIE/)!= null;
			 
				 if(isIE) {
					$file.select();
					//转移input焦点，兼容Ie9
					document.getElementById("picDiv").focus();
					var reallocalpath = document.selection.createRange().text;
			 		//设置预览图片路径
					$pic.attr('src', reallocalpath);
				 }else {			
					var file = $file[0].files[0];
					var reader = new FileReader();
					reader.readAsDataURL(file);
					reader.onload = function(e){
						$pic[0].src=this.result;
					}
				 }
				 
			});	
			$(".item-upload-img .uploadImage02").change(function() {		 
				var $imgUploadVal = $('.img-upload-val');
				var uploadNum = 0;
				uploadNum += 1;
				$imgUploadVal.val(uploadNum);
			});	
			--%>
			
			//增加删除图片
			var $imgNewAdd = $('.img-new-add');
			var num = 0;
			$('.img-add-btn').click(function(){
				var newImgId = 'newImgId' + num;
				var imgLength = $(this).prevAll('.uploadPreview01:visible').length;
				if(imgLength == 5){
					alert('最多上传5张图片');	
				}else{
					$imgNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
				}
				num += 1;
			});
			
			//删除浏览图片
			$('.item-upload-img .img-delete-btn').click(function(){
				var imgLength = $(this).parents('td').find('.uploadPreview01:visible').length;
				var thisImgText = $(this).parents('td').prev('td').find('.list-name').text();
				if(imgLength === 2){
					alert('至少要上传一张' + thisImgText);
					return false;
				}else{
					$(this).parent('.uploadPreview01').remove();
					window.parent.TuneHeight();// 重置高度
				}
			});
			
			//删除详情图片
			$('#dragItems .img-delete-btn').click(function(){
				$(this).parent('.uploadPreview01').remove();
				window.parent.TuneHeight();// 重置高度
			});
			
			//增加删除商品详情图片、描述
			var $imgDescNewAdd = $('.imgDesc-new-add');
			var descNum = 0;
			$('.imgDesc-add-btn').click(function(){
				var newImgId = 'newImgId' + descNum;
				/*var imgLength = $(this).prevAll('.uploadPreview01:visible').length;
				if(imgLength == 10){
					alert('最多上传10张图片');	
				}else{
					$imgDescNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
				}*/
				$imgDescNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
				descNum += 1;
				window.parent.TuneHeight();// 重置高度
			});
			
			//添加描述按钮
			var $shapeBox = $('.shape-box');
			var $imgDescText = $('.img-desc-text');
			var $imgDescTextBox = $('.img-desc-text-box');
			$imgDescText.click(function(){
				var noImgUrl = $(this).siblings('.imgPreview').attr('src');
				if(noImgUrl.indexOf('addimg01.jpg') > -1){
					alert('请先上传图片');	
				}else{
					var thisVal = $(this).siblings('input.desc-text-input').val();
					$(this).siblings('input.desc-text-input').addClass('current-input');
					$imgDescTextBox.find('.textareas').val(thisVal);
					$imgDescTextBox.fadeIn();
					$shapeBox.fadeIn();
				}
			});
			//编辑描述按钮
			var $imgDescBox = $('.img-desc-box');
			$imgDescBox.click(function(){
				var thisVal = $(this).siblings('input.desc-text-input').val();
				$(this).siblings('input.desc-text-input').addClass('current-input');
				$imgDescTextBox.find('.textareas').val(thisVal);
				$imgDescTextBox.fadeIn();
				$shapeBox.fadeIn();
			});
			
			//描述确定按钮
			$('#checkDescBtn').click(function(){
				var curText = $('.textareas').val();
				var omitText = $.trim(curText).substring(0,4);
				var $thisImgDescBox = $('.current-input').siblings('.img-desc-box');
				var $thisImgDescText = $('.current-input').siblings('.img-desc-text');
				$thisImgDescBox.find('.desc-text-info').text(omitText + '…');
				if($thisImgDescBox.is(':hidden')){
					$('.current-input').siblings('.img-desc-text').hide();
					$thisImgDescBox.show();
				}else if($.trim(curText) == ''){
					$thisImgDescBox.hide();
					$thisImgDescText.show();
				}
				$('.current-input').val(curText).removeClass('current-input');
				$imgDescTextBox.fadeOut();
				$shapeBox.fadeOut();
			});
			//描述取消按钮
			$('#backDescBtn').click(function(){
				$('.current-input').removeClass('current-input');
				$imgDescTextBox.fadeOut();
				$shapeBox.fadeOut();
			});
			
			//hover删除按钮
			$('.uploadPreview01').hover(function(){
				$(this).find('.img-delete-btn').show();
			},function(){
				$(this).find('.img-delete-btn').hide();
			});

			var $paramAdded = $('.paramAdded.dsn');
			$('#createParam').click(function(){
				$paramAdded.clone(true).show().appendTo($(this).parents().next('table'));
				window.parent.TuneHeight();
			});

			$('.textareas').on('input propertychange', function() {
				var valLength = $.trim($(this).val()).length;
				var currentVal = $.trim($(this).val());
				if(valLength > 99){
					$(this).val(currentVal.substring(0,100));	
				}
				var num = 100 - valLength;
				if(num<0){
					num = 0;
				}
				$('.leftNum').text(num);
			});

			$('#supplyMerchant').change(function () {
			    var supplyMerchatId = $('#supplyMerchant').val();
				if (supplyMerchatId == '${experienceStoreId}') {
				    $('#codeTr').removeClass('dsn');
                } else {
                    $('#code').val('');
                    $('#codeTr').addClass('dsn');
                }
            });
		})(jQuery);
	</script>
</html>