<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>//" />
	<title>收费项配置</title>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css?v1" />
	<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css?v=1"/>
	<style type="text/css">
		html{
			height: auto;
		}
	</style>
</head>

<body>
<div class="info">
    <form class="inputform">
		<h2>抄表收费项目数据管理：1、配置收费项---->2、设置计费规则</h2>
        <h2>${gbName}-抄表收费配置</h2>
        <input id="gbId" type="hidden" value='${gbId}' />
        <table id="wrapTable" class="info-list-02 mtop20" border="0" cellpadding="0" cellspacing="1">
			<thead>
				<tr class="title">
					<th width="80%">名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tr class="tobeclone-tr dsn">
				<td><input type="text" class="input_text pp w200 calculate-name" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="" /></td>
				<td>
					<a class="blue calculateType" href="javascript:void(0)">计算方式配置</a> <span class="grey">|</span></span>
					<a class="blue deleteCharges" href="javascript:void(0)">删除</a>
				</td>
			</tr>
			
			<c:forEach var="item" items="${mriList }">
				<tr class="tobeclone-tr">
					<input id="mriId" type="hidden" value='${item.id }' />
					<td><input type="text" class="input_text pp w200 calculate-name" data-itemid="${item.id }" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="${item.name }" /></td>
					<td>
						<a class="blue calculateType" href="javascript:void(0)">计算方式配置</a> <span class="grey">|</span></span>
						<a class="blue deleteCharges" href="javascript:void(0)" data-itemid="${item.id }">删除</a>
					</td>
				</tr>
			</c:forEach>
			<!-- 
			<tr class="tobeclone-tr">
				<td><input type="text" class="input_text pp w200 calculate-name" data-itemid="01" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="电费" /></td>
				<td>
					<a class="blue calculateType" href="javascript:void(0)">计算方式配置</a> <span class="grey">|</span></span>
					<a class="blue deleteCharges" href="javascript:void(0)" data-id="01">删除</a>
				</td>
			</tr> -->
		</table>
        
        <div class="mtop20">
        	<input id="addCharges" class="info-btn small-btn w150" type="button" value="新增收费项">
        	<input id="saveChargesOption" class="weak-btn small-btn w100 mleft20" type="button" value="保存">
        </div>
    </form>
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


		//增加收费项
		$('#addCharges').click(function(){
			$('.tobeclone-tr.dsn').clone(true).removeClass('dsn').appendTo('#wrapTable');
		});
		
		//删除收费项
		$(document).on('click', '.deleteCharges', function(){
			var $this = $(this);
			if(confirm('确认删除该项？')){
				if($this.attr('data-itemid') === undefined){
					$this.parents('.tobeclone-tr').remove();
				}else{
					$.get('../meterReading/mrFeeItemDelete.json', {feeItemId: $this.attr('data-itemid')}, function(data, status){
						if(data.status === '0000'){
							$this.parents('.tobeclone-tr').remove();
							alert('删除成功！');
						}else{
							alert('删除失败！');
						}
					});
				}
			}
		});
		
		//计算方式配置
		$('.calculateType').click(function(){
			var $thisInput = $(this).parents('tr').find('.calculate-name');
			var thisInputVal = $thisInput.val();
			var thisId = $thisInput.attr('data-itemid');
			if(thisInputVal == ''){
				alert('请先填写收费名称！');
				return;
			}
			if(thisId == '' || thisId == undefined ){
				alert('请先保存！');
				return;
			}
			//iframe层
			$.layer({
			  type: 2,
			  shadeClose: true,
			  title: false,
			  closeBtn: [0, true],
			  shade: [0.8, '#000'],
			  border: [0],
			  offset: ['80px',''],
			  area: ['800px', ($(window).height() - 250) +'px'],
			  iframe: {src: '../meterReading/mrFeeItemFormulaEdit.html?mfiId='+thisId}
			}); 
			$("#caclType").attr('data-id', thisId);
		});
		
		//保存收费项
		$(".inputform").Validform({
			tiptype:3,
			btnSubmit:"#saveChargesOption",
			postonce:false,
			ignoreHidden: true,
			beforeSubmit:function(){
				//提交
				$.post('../meterReading/mrFeeItemSave.json', 
				{gbId: $('#gbId').val(), feeItemJson: JSON.stringify(getTotalItemInfo())}, 
				function(data,status){
					if(data.status === '0000'){
						alert('保存成功！');
						location.reload();// 如果是新增保存成功的话，保存成功后要更新其 ID
					}else{
						alert(data.message);
					}
				});
				
				return false;
			}
		});
		
		//获取收费项信息
		function getTotalItemInfo(){
			var totalItemSelectedInfo = [];
			$('.tobeclone-tr:visible').each(function(){
			
				var singleItemInfo = {};
				var $thisInput = $(this).find('.calculate-name');
				
				if($thisInput.val() !== ''){
					
					var thisId = $thisInput.attr('data-itemid');
					var thisName = $thisInput.val();
					if(thisId !== undefined){
						singleItemInfo.id = thisId;
					}
					singleItemInfo.name = thisName;
					
					//totalItemSelectedInfo.push(singleItemInfo);
					totalItemSelectedInfo.push({"id": thisId, "name": thisName});
				}
			});
			return totalItemSelectedInfo;
		}
		
	})

</script>

</html>

