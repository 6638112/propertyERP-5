<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>新增服务商品管理</title>
		<link rel="stylesheet" type="text/css" href="../css/common.css">
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<style>
			.dpTd1{width: 120px; text-align: left;}
			.dpTd2{width: 240px; text-align: left;}
			.dpTd3{width: 800px; min-width: 800px;text-align: left;}
			.pcTd1{width: 60px; text-align: left;vertical-align:center;}
			.pcTd2{text-align: left;}
			.overflowDiv{max-height: 346px; overflow-y: scroll;}
			hr{height:2px;border:none;border-top:2px dotted #185598;margin-top: 10px;}
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
		    <h2>新增服务商品管理</h2> 
		    <form class="inputform" enctype="multipart/form-data" method="post" action="productSave.html" >
			    <table class="info-list-02" style="width: 440px;min-width: 440px;margin-top: 20px;">
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>商品名称：</td>
						<td class="dpTd2"><input type="text" id="productName" name="prdtName" datatype="*2-30" nullmsg="请填写信息！" errormsg="长度2到128个字符！" class="input_text w240 pp" maxlength="128"/></td>
					</tr>
					<tr>
						<td class="dpTd1 title">商品描述：</td>
						<td class="dpTd2"><input type="text" id="productDesc" name="desc" class="input_text w240 pp" maxlength="200"/></td>
					</tr>
				</table>
				<hr>
				<h2 class="mtop20">产品所属类目</h2> 
				<table class="info-list-02" style="width: 440px;min-width: 440px;">
					<tr>
						<td class="dpTd1">选择商品所属大类：</td>
						<td class="dpTd2">
							<select class="select_normal" name="cstId" datatype="*" onchange="cstTypeChange()">
					           	<option value="">请选择</option>
								<c:forEach items="${cstTypeList}" var="item">
									<option value="${item.id}" <c:if test="${param.cstId==item.id }"> selected="selected"</c:if>>${item.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="dpTd1">选择所属需求小类：</td>
						<td class="dpTd2">
							<select class="select_normal" name="dtId" datatype="*" onchange="dtChange()">
					            <option value="">请选择</option>
					        </select>
						</td>
					</tr>
					<tr>
						<td class="dpTd1">选择产品大类：</td>
						<td class="dpTd2">
							<select class="select_normal" name="dt2Id" datatype="*">
					            <option value="">请选择</option>
					        </select>
						</td>
					</tr>
				</table>
		        <hr>
		        <table class="info-list-02">
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>选择供应商：</td>
						<td class="dpTd3">
							<select class="select_normal" name="ssId" datatype="*">
					            <option value="">请选择</option>
					            <c:forEach items="${ssList}" var="item">
									<option value="${item.id}" <c:if test="${param.ssId==item.id }"> selected="selected"</c:if>>${item.name }</option>
								</c:forEach>
					        </select>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>付款方式：</td>
						<td class="dpTd3">
							<label style="margin-right: 3em;"><input type="radio" name="payType" value="1" checked/>服务前付款</label>
							<label><input type="radio" name="payType" value="2"/>服务后付款</label>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title" style="vertical-align: top;">选择规格：</td>
						<td class="dpTd3">
							<table class="info-list-02" id="specTable">
								<thead>
									<tr>
										<td>规格</td>
										<td>销售价</td>
										<td>市场价</td>
										<td>计价单位</td>
										<td>操作</td>
									</tr>
								</thead>
								<tbody id="noBody">
								</tbody>
							</table>
							<p style="text-indent:0em;">
								<input type="button" class="input-btn mtop10" value="新增" onclick="addNo()"/>
							</p>
							<input type="hidden" name="prdtSpecJson" id="prdtSpecJson" value=""> <%--规格属性 --%>
						</td>
					</tr>
				</table>
		        <hr>
		        <h2 class="mtop20">商品图片</h2>
		        <table class="info-list-02">
					<%-- <tr>
						<td class="pcTd2">
			                <div class="uploadPreview01 mright6">
			                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="prdtPic" class="uploadImage02" />
			                    <img class="imgPreview" src="../images/addimg01.jpg" />
			                </div>
						</td>
					</tr> --%>
					<tr>
						<!-- <td class="pcTd1"><span class="red">*</span>浏览图：</td> -->
						<td class="pcTd2" id="dragScanItems">
							<div class="item-upload-img-new uploadPreview01 scanPic-new-add drag-item marb15 img-hide">
			                    <div class="img-delete-btn">删除</div>
			                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="prdtPic" class="uploadImage02" />
			                    <img class="imgPreview" width="82" height="82" src="../images/addimg01.jpg" />
			                </div>
			                <div class="item-upload-img-new uploadPreview01 marb15 mright6 drag-item">
			                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="prdtPic" class="uploadImage02" />
			                    <img class="imgPreview" src="../images/addimg01.jpg" />
			                </div>
			                <div class="uploadPreview01 img-add-btn marb15 scanPicBtn"></div>
			                <br>
			                <span class="f12">注：图片尺寸640x540像素，建议图片2M以内，最少一张，最多可上传3张。</span>
			                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传商品图片！" />
						</td>
					</tr>
				</table>
		        <hr>
		        <h2 class="mtop20">商品介绍图片</h2>
		        <table class="info-list-02">
					<tr>
						<td id="dragIntroduceItems">
							<div class="item-upload-img-new uploadPreview01 introducePic-new-add drag-item marb15 img-hide">
			                    <div class="img-delete-btn">删除</div>
			                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="introducePic" class="uploadImage02" />
			                    <img class="imgPreview" width="82" height="82" src="../images/addimg01.jpg" />
			                </div>
			                <div class="item-upload-img-new uploadPreview01 marb15 mright6 drag-item">
			                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="introducePic" class="uploadImage02" />
			                    <img class="imgPreview" src="../images/addimg01.jpg" />
			                </div>
			                <div class="uploadPreview01 imgDesc-add-btn marb15 introducePicBtn"></div>
			                <br>
			                <span class="f12">注：图片尺寸640x540像素，建议图片2M以内，可上传多张。</span>
			                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传商品图片！" />
						</td>
					</tr>
				</table>
		        <hr>
		        <h2 class="mtop20">销售范围</h2>
		        <p>
		        	<label style="margin-right: 2em;" onclick="setSellRange('1')"><input type="radio" name="sellRange" value="1" checked/>全国</label>
		        	<label style="margin-right: 2em;" onclick="setSellRange('4')"><input type="radio" name="sellRange" value="4"/>城市/区县</label>
					<label onclick="setSellRange('3')"><input type="radio" name="sellRange" value="3"/>小区</label>
		        </p>
		        <div id="cityRange">
				    <h2 class="mtop10 f14">销售城市</h2>
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
					<h2 class="mtop10 f14">城市搜索结果</h2>
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
		        <div id="gbRange">
				    <h2 class="mtop10 f14">销售小区</h2>
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
					        <!-- <td class="dsn"><div class="list-name">缴费权限：</div></td>
					        <td class="dsn"> 小昭说不需要这个过滤条件
					            <select class="select_normal merchant-type" id="payOpenStatus">
					                <option value="">全部</option>
					                <option value="是">已开通缴费</option>
					                <option value="否">未开通缴费</option>
					            </select>
					        </td> -->
					      </tr>
					    </table>
					</div> 
					<h2 class="mtop10 f14">小区搜索结果</h2>
					<display:table name="gbList" id="gbTable" class="info-list-02" requestURI="" >
						<display:column title="<label><a href='javascript:void(0);'><input type='checkbox' onclick='selectAllGbBox(this)'/>全选</a></label>"  sortable="true">
							<input name="gbId" type="checkbox" value="${gbTable.id }" onclick="selectGb(this)"/>
						</display:column>
						<display:column title="物业名称" property="propertyCompanyName"/>
						<display:column title="小区名称" property="name"/>
						<%-- <display:column title="缴费开通状态">
							<c:choose>
								<c:when test="${gbTable.propfeeCanpay eq 1}">是</c:when>
								<c:when test="${gbTable.propfeeCanpay eq 2}">否</c:when>
							</c:choose>
						</display:column> --%>
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
					        <!-- <th>缴费开通状态</th> -->
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
		        <hr>
		        <h2 class="mtop20">微信分享</h2>
		        <table class="info-list-02">
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>分享图片：</td>
						<td class="dpTd3">
							<div class="item-upload-img-new uploadPreview01 mright6">
			                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="sharePic" class="uploadImage02" />
			                    <img class="imgPreview" src="../images/addimg01.jpg" />
			                </div>
			                <br>
			                <span class="f12">注：分享到好友及朋友圈共用这张图片</span>
			                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传分享图片！" />
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>分享到好友标题：</td>
						<td class="dpTd3">
							<input type="text" id="friendTitle" name="shareFridendTitle" datatype="*2-30" nullmsg="请填写信息！" errormsg="长度2到30个字符！" class="input_text w300 pp" maxlength="30">
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>分享到好友描述内容：</td>
						<td class="dpTd3">
							<textarea name="shareContent" rows="5" cols="40" datatype="*2-30" nullmsg="请填写信息！" errormsg="长度2到512个字符！" class="textareas txt02"></textarea>
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>分享到朋友圈标题：</td>
						<td class="dpTd3">
							<input type="text" id="friendsTitle" name="shareTimelineTitle" datatype="*2-50" nullmsg="请填写信息！" errormsg="长度2到50个字符！" class="input_text w300 pp" maxlength="50">
						</td>
					</tr>
					<tr>
						<td class="dpTd1 title"><span class="red">*</span>推送图片(备用)：</td>
						<td class="dpTd3">
							<div class="item-upload-img-new uploadPreview01 mright6">
			                    <input type="file" accept="image/gif,image/jpeg,image/jpg,image/png" name="sharePushPic" class="uploadImage02" />
			                    <img class="imgPreview" src="../images/addimg01.jpg" />
			                </div>
			                <br>
			                <span class="f12">注：这张图片用做公众号推文的备用图片</span>
			                <input class="img-upload-val dsn" type="text" value="" datatype="*" nullmsg="请上传推送图片！" />
						</td>
					</tr>
				</table>
		        <input id="submitProduct" class="info-btn mtop10" type="submit" value="保存"/>
		    </form>
		</div>
		<table id="noTrTemplate" style="display:none;">
			<tbody>
				<tr>
					<td class="no">
						<input type="text" id="no" name="no" datatype="*2-30" nullmsg="请填写信息！" errormsg="长度2到30个字符！" class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="sellPrice">
						<input type="text" id="sellPrice" name="sellPrice" datatype="numberFixTwoGt0" nullmsg="请填写信息！" class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="price">
						<input type="text" id="price" name="price" datatype="numberFixTwo" nullmsg="请填写信息！"  class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="unit">
						<input type="text" id="unit" name="unit" datatype="*1-8" nullmsg="请填写信息！" errormsg="长度1到8个字符！" class="input_text w120 pp" maxlength="40"/>
						<span class="Validform_checktip"></span>
					</td>
					<td class="op">
						<input type="button" class="input-btn" value="删除" onclick="$(this).parent().parent().remove();"/>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
	<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="../js/jquery.common.js"></script>
	<script type="text/javascript" src="../js/Sortable.js"></script>
	<script type="text/javascript" src="../js/dredgeProduct.js"></script>
	<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
	
	<script type="text/javascript">
		$(function(){
		    //表单验证
			$(".inputform").Validform({
				tiptype:3,
				beforeCheck:function(curform){
					
				},
				beforeSubmit:function(curform){
					//1 封装规格参数
					var specList = [];
					$("#specTable tbody tr").each(function(key,value){
						var obj = {};
						obj.specification = $(this).find("td").eq(0).find("input").val();//规格名称
						obj.sellPrice = $(this).find("td").eq(1).find("input").val()*100;//销售价
						obj.marketPrice = $(this).find("td").eq(2).find("input").val()*100;//市场价
						obj.priceUnit = $(this).find("td").eq(3).find("input").val();//计价单位
						obj.isVisible = 1;
						specList.push(obj);
					});
					var jsonString = JSON.stringify(specList);
					console.log("jsonString: " + jsonString);
					$("#prdtSpecJson").val(jsonString);
					
					if($("#noBody").find("tr").length==0){
						alert("规格不能为空");
						//$("input[value=新增]").focous();
						return false;
					}
					
					$.Showmsg('正在提交，请稍后');
				}, 
				callback:function(data){
					console.log("calll back... ");
				}
			});
		});
		
		// 添加规格
		function addNo(){
			var tr = $("#noTrTemplate").find("tbody").html();
			if($("#noBody").find("tr").length==0){
				$("#noBody").html(tr);
			} else {
				$("#noBody tr:last").after(tr);
			}
			window.parent.TuneHeight();
		}
		
		// 销售范围选择
		function setSellRange(range){
			if(range=="1"){
				$("#gbRange").hide();
				$("#cityRange").hide();
			} else if(range=="4"){
				$("#gbRange").hide();
				$("#cityRange").show();
			} else if(range=="3"){
				$("#gbRange").show();
				$("#cityRange").hide();
			}
			window.parent.TuneHeight();
		}
		
		//拖拽排序
		var dragScanItems = document.getElementById("dragScanItems");
		new Sortable(dragScanItems, {
			draggable: ".drag-item"
		});
		
		var dragIntroduceItems = document.getElementById("dragIntroduceItems");
		new Sortable(dragIntroduceItems, {
			draggable: ".drag-item"
		});
		
		//删除图片
		$('.img-delete-btn').click(function(){
			$(this).parent('.uploadPreview01').remove();
			window.parent.TuneHeight();
		});
		
		// 增加图片
		var $scanPicNewAdd = $('.scanPic-new-add');
		var $introducePicNewAdd = $('.introducePic-new-add');
		var descNum = 0;
		$('.scanPicBtn,.introducePicBtn').click(function(){
			var newImgId = 'newImgId' + (++descNum);
			var c = $(this).attr("class");
			if(c.indexOf("scanPicBtn")!=-1){
				if($("input[name=prdtPic]:visible").length==3){
					alert("浏览图最多3张！");
				} else {
					$scanPicNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
				}
			} else {
				$introducePicNewAdd.clone(true).removeClass('img-hide').insertBefore($(this)).find('input').attr('id', newImgId);
			}
			window.parent.TuneHeight();
		});
		
		//hover删除按钮
		$('.uploadPreview01').hover(function(){
			$(this).find('.img-delete-btn').show();
		}, function(){
			$(this).find('.img-delete-btn').hide();
		});
		
		/*<=====================================小区搜索=====================================*/
		$('#pcName, #gbName, #province, #city, #block').on('input propertychange', function(){
			filterGb();
		});
		
		function filterGb(){
			var province = $.trim($("#province").val());
			var city = $.trim($("#city").val());
			var block = $.trim($("#block").val());
			var pcName = $.trim($("#pcName").val());
			var gbName = $.trim($("#gbName").val());
			
			var searchKeys = [pcName, gbName, province, city, block];
			var $this = $("#gbTable tbody tr").hide();
			for(var i=0; i<searchKeys.length; i++){
				$this = $this.find("td:eq("+(i+1)+")").filter(":contains('"+searchKeys[i]+"')").parent();
			}
			
			var selectStatus = $("#gbRange #selectStatus").val();
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
				if($("#gbRange #areaHasSelectedList tbody tr").length==0){
					$("#gbRange #areaHasSelectedList tbody").html(html);
				} else {
					$("#gbRange #areaHasSelectedList tr:last").after(html);
				}
			} else {// remove
				$("#gbRange #areaHasSelectedList").find("tr[gbId="+gbId+"]").remove();
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
			$("#gbRange #areaHasSelectedList tbody").html(allHtml);
			window.parent.TuneHeight();
		}
		
		function selectAllGbBox(o){
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
				$("#gbRange #areaHasSelectedList tbody").html(allHtml);
			} else {
				$("#gbRange #areaHasSelectedList tbody").html("");
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
			$("#gbRange #areaHasSelectedList tbody").html("");
			$("#gbTable").find(":checkbox").each(function(){
				if($(this).is(":checked")){
					$(this).prop("checked", false);
				}
			});
			window.parent.TuneHeight();
		}
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
		$(function(){
			var range = $(":radio[name='sellRange']:checked").val();
			setSellRange(range);
			$("body").show();
		});
		
		var dtId =  <c:choose><c:when test="${not empty param.dtId}">${param.dtId}</c:when>	<c:otherwise>0</c:otherwise></c:choose>;
		var dt2Id =  <c:choose><c:when test="${not empty param.dt2Id}">${param.dt2Id}</c:when>	<c:otherwise>0</c:otherwise></c:choose>;
	</script>
</html>