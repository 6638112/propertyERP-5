<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>收费项设置</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css?v=1"/>
	<style type="text/css">
		html{height: auto;}
		hr{height:2px;border:none;border-top:2px dotted #185598;margin-top: 10px;margin-top: 25px;}
		h1{text-align: center;margin-bottom: 20px;}
	</style>
</head>
<body>
<div class="info">
	<h1><u>${gbName}</u>收费项目设置</h1>
    <form class="inputform" id="fixedItemForm">
        <h2>常规收费项设置</h2>
        <table id="wrapTable" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <thead>
            <tr class="title">
                <th width="30%">收费项名称</th>
                <th width="20%">费用类型</th>
                <th width="30%">计费方式</th>
                <th width="15%">是否计算滞纳金</th>
                <th>操作</th>
            </tr>
            </thead>
            <tr class="tobeclone-tr dsn">
                <td><input type="text" item_id="" class="input_text pp w200 calculate-name" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="" /></td>
                <td>常规收费</td>
                <td>
                    <select class="select_normal w131" name="calculateType">
                        <option value="1">单价*建筑面积</option>
                        <option value="3">单价*用量</option>
                        <option value="2">指定金额</option>
                    </select>
                </td>
                <td>
                    <select class="select_normal w50" name="isCalculateLatefee">
                        <option value="1">是</option>
                        <option value="2" selected="selected">否</option>
                    </select>
                </td>
                <td>
                    <a class="blue deleteCharges" href="#" data-id="">删除</a>
                </td>
            </tr>
            <c:forEach items="${itemList}" var="item">
                <tr class="tobeclone-tr">
                    <td><input type="text" name="itemName" item_id="${item.id}" class="input_text pp w200 calculate-name" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="${item.name}" /></td>
                    <td>常规收费</td>
                    <td>
                        <select class="select_normal w131" name="calculateType">
                            <option value="1" <c:if test="${item.calculateType == 1}">selected="selected"</c:if>>单价*建筑面积</option>
                            <option value="3" <c:if test="${item.calculateType == 3}">selected="selected"</c:if>>单价*用量</option>
                            <option value="2" <c:if test="${item.calculateType == 2}">selected="selected"</c:if>>指定金额</option>
                        </select>
                    </td>
                    <td>
                        <select class="select_normal w50" name="isCalculateLatefee">
                            <option value="1" <c:if test="${item.isCalculateLatefee == 1}">selected="selected"</c:if>>是</option>
                            <option value="2" <c:if test="${item.isCalculateLatefee == 2}">selected="selected"</c:if>>否</option>
                        </select>
                    </td>
                    <td>
                        <a class="blue deleteCharges" data-id="${item.id}" href="#">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="mtop20">
            <input id="addCharges" class="info-btn small-btn w150" type="button" value="新增收费项">
            <c:if test="${fn:length(itemList)>0}">
            	<input class="info-btn small-btn w150 mleft20" type="button" value="常规收费项数据管理" onclick="jumpToCreateFeeDetail()">
            </c:if>
            <input id="savefixedItemBtn" class="weak-btn small-btn w100 mleft20" type="button" value="保存">
        </div>
    </form>
    <hr>
    <form class="inputform" id="mrItemForm">
        <h2>抄表收费项设置</h2>
        <table id="wrapTable" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
			<thead>
				<tr class="title">
					<th width="10%">名称</th>
					<th width="20%">费用类型</th>
                	<th width="20%">计费方式</th>
                    <th width="10%">是否计算滞纳金</th>
                	<th width="20%" colspan="2">计费规则</th>
					<th>操作</th>
				</tr>
			</thead>
			<tr class="tobeclone-tr dsn">
				<td><input type="text" class="input_text pp w200 calculate-name" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="" /></td>
				<td>抄表收费</td>
				<td>单价*用量</td>
                <td>
                    <select class="select_normal w50" name="isCalculateLatefee">
                        <option value="1">是</option>
                        <option value="2" selected="selected">否</option>
                    </select>
                </td>
				<td>
					<%--<a class="blue deleteCharges" href="javascript:void(0)">删除</a>--%>
				</td>
			</tr>
			
			<c:forEach var="item" items="${mriList }">
				<tr class="tobeclone-tr">
					<input id="mriId" type="hidden" value='${item.id }' />
					<td rowspan="${fn:length(item.calculateRuleCfgList)}"><input type="text" class="input_text pp w200 calculate-name" data-itemName="${item.name}" data-itemid="${item.id }" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="${item.name }" /></td>
					<td rowspan="${fn:length(item.calculateRuleCfgList)}">抄表收费</td>
					<td rowspan="${fn:length(item.calculateRuleCfgList)}">单价*用量</td>
                    <td rowspan="${fn:length(item.calculateRuleCfgList)}">
                        <select class="select_normal w50" name="isCalculateLatefee">
                            <option value="1" <c:if test="${item.isCalculateLatefee == 1}">selected="selected"</c:if>>是</option>
                            <option value="2" <c:if test="${item.isCalculateLatefee == 2}">selected="selected"</c:if>>否</option>
                        </select>
                    </td>
                    <c:choose>
                        <c:when test="${fn:length(item.calculateRuleCfgList) >= 1}">
                            <c:set value="${item.calculateRuleCfgList[0]}" var="firstRule"/>
                            <td align="center">
                                <a class="blue calculateType" data-mrCalculateRuleCfgId="${firstRule.id }" data-itemName="${item.name}" data-itemid="${item.id }" data-gbId="${gbId}" data-gbName="${gbName}" href="javascript:void(0)">1.&nbsp;&nbsp;${firstRule.name}</a>
                            </td>
                            <td>
                                <a class="blue delMrCalculateRuleCfg" href="javascript:void(0)" data-itemid="${firstRule.id }">删除</a>
                            </td>
                        </c:when>
                        <c:otherwise><td></td><td></td></c:otherwise>
                    </c:choose>
					<td rowspan="${fn:length(item.calculateRuleCfgList)}">
                        <a class="blue calculateType" href="javascript:void(0)" data-itemName="${item.name}" data-itemid="${item.id }" data-gbId="${gbId}" data-gbName="${gbName}">新增规则</a>
						<a class="blue deleteCharges" href="javascript:void(0)" data-itemid="${item.id }">删除收费项</a>
					</td>
				</tr>
                <c:if test="${fn:length(item.calculateRuleCfgList) > 1}">
                    <c:forEach var="item0" items="${item.calculateRuleCfgList}" varStatus="i" begin="1">
                        <tr>
                            <td align="center">
                                <a class="blue calculateType" data-mrCalculateRuleCfgId="${item0.id }" data-itemName="${item.name}" data-itemid="${item.id }" data-gbId="${gbId}" data-gbName="${gbName}" href="javascript:void(0)">${i.index + 1}.&nbsp;&nbsp;${item0.name}</a>
                            </td>
                            <td>
                                <a class="blue delMrCalculateRuleCfg" href="javascript:void(0)" data-itemid="${item0.id }">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
			</c:forEach>
		</table>
        
        <div class="mtop20">
        	<input id="addCharges" class="info-btn small-btn w150" type="button" value="新增收费项">
            <c:if test="${fn:length(mriList)>0}">
                <input class="info-btn small-btn w150 mleft20" type="button" value="抄表收费标准管理" onclick="jumpToGetStandardList()">
            </c:if>
        	<input id="saveMrItemBtn" class="weak-btn small-btn w100 mleft20" type="button" value="保存">
        </div>
    </form>
    <hr>
    <form class="inputform" id="tmpItemForm">
        <h2>临时收费项设置</h2>
        <table id="wrapTable" class="info-list-02" border="0" cellpadding="0" cellspacing="1">
            <thead>
            <tr class="title">
                <th width="30%">收费项名称</th>
                <th width="20%">费用类型</th>
                <th width="30%">计费方式</th>
                <th width="15%">是否计算滞纳金</th>
                <th>操作</th>
            </tr>
            </thead>
            <tr class="tobeclone-tr dsn">
                <td><input type="text" item_id="" class="input_text pp w200 calculate-name" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="" /></td>
                <td>临时收费</td>
                <td>
                    <select class="select_normal w131" name="calculateType">
                        <option value="1">单价*建筑面积</option>
                        <option value="3">单价*用量</option>
                        <option value="2">指定金额</option>
                    </select>
                </td>
                <td>
                    <select class="select_normal w50" name="isCalculateLatefee">
                        <option value="1">是</option>
                        <option value="2" selected="selected">否</option>
                    </select>
                </td>
                <td>
                    <a class="blue deleteCharges" href="#" data-id="">删除</a>
                </td>
            </tr>
            <c:forEach items="${tmpFeeItemList}" var="item">
                <tr class="tobeclone-tr">
                    <td>
                    	<input type="text" name="itemName" item_id="${item.id}" class="input_text pp w200 calculate-name" datatype="*" nullmsg="请填写收费名称" maxlength="10" value="${item.name}" />
                    </td>
                    <td>临时收费</td>
                    <td>
                        <select class="select_normal w131" name="calculateType">
                            <option value="1" <c:if test="${item.calculateType == 1}">selected="selected"</c:if>>单价*建筑面积</option>
                            <option value="3" <c:if test="${item.calculateType == 3}">selected="selected"</c:if>>单价*用量</option>
                            <option value="2" <c:if test="${item.calculateType == 2}">selected="selected"</c:if>>指定金额</option>
                        </select>
                    </td>
                    <td>
                        <select class="select_normal w50" name="isCalculateLatefee">
                            <option value="1" <c:if test="${item.isCalculateLatefee == 1}">selected="selected"</c:if>>是</option>
                            <option value="2" <c:if test="${item.isCalculateLatefee == 2}">selected="selected"</c:if>>否</option>
                        </select>
                    </td>
                    <td>
                        <a class="blue deleteCharges" data-id="${item.id}" href="#">删除</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <div class="mtop20">
            <input id="addCharges" class="info-btn small-btn w150" type="button" value="新增收费项">
            <input id="saveTmpItemBtn" class="weak-btn small-btn w100 mleft20" type="button" value="保存">
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
	/*----------------------常规收费项设置start----------------------*/
    $(function(){
        //增加收费项
        $('#fixedItemForm #addCharges').click(function(){
            $('#fixedItemForm .tobeclone-tr.dsn').clone(true).removeClass('dsn').appendTo('#fixedItemForm #wrapTable');
            window.parent.TuneHeight();
        });

        //删除收费项
        $(document).on('click', '#fixedItemForm .deleteCharges', function(){
            var $this = $(this);
            if(confirm('确认删除该项？')){
                if($this.attr('data-id') == undefined || $this.attr('data-id')==""){
                    $this.parents('#fixedItemForm .tobeclone-tr').remove();
                    window.parent.TuneHeight();
                }else{
                    $.get("${pageContext.request.contextPath}/fixedFeeCfg/delFeeItem.json", {id: $this.attr('data-id')}, function(data, status){
                        if(data.status=="0000"){
                            alert(data.message);
                            location.reload();
                        } else {
                            alert(data.message);
                        }
                        window.parent.TuneHeight();
                    });
                }
            }
        });

        //保存收费项
        $("#fixedItemForm").Validform({
            tiptype:3,
            btnSubmit:"#savefixedItemBtn",
            postonce:false,
            ignoreHidden: true,
            beforeSubmit:function(){
                //生成数据
                var items = getTotalItemInfo("#fixedItemForm");
                if(items == '') {
                    return false;
                }
                //提交
                $.post('${pageContext.request.contextPath}/fixedFeeCfg/saveFeeItem.json', {"gbId":"${gbId}", "items":JSON.stringify(items.totalItemSelectedInfo)}, function(data,status){
                    if(data.status=="0000"){
                        alert(data.message);
                        location.reload();
                    } else {
                        alert(data.message);
                    }
                });
                return false;
            }
        });
    });
	
    //获取收费项信息
    function getTotalItemInfo(typeClass){
        var checkStatus = false;//判断收费项是否重复
        var itemInfo = {
            totalItemSelectedInfo : []
        };

        $(typeClass+' .tobeclone-tr:visible').each(function(){
            var singleItemInfo = {};
            var $thisInput = $(this).find('.calculate-name');

            if($thisInput.val() !== ''){
                var thisCalculateType = $thisInput.parent("td").siblings("td").children("select[name='calculateType']").val();
                var isCalculateLatefee = $thisInput.parent("td").siblings("td").children("select[name='isCalculateLatefee']").val();
                var thisName = $.trim($thisInput.val());
                var thisId = $.trim($thisInput.attr("item_id"));
                singleItemInfo.name = thisName;
                singleItemInfo.id = thisId;
                singleItemInfo.calculateType = thisCalculateType;
                singleItemInfo.isCalculateLatefee = isCalculateLatefee;
                var items = itemInfo.totalItemSelectedInfo;
                $.each(items,function (i) {
                    if($.trim(items[i].name) == thisName) {
                        alert("收费项名称重复！！");
                        checkStatus = true;
                    }
                });
                itemInfo.totalItemSelectedInfo.push(singleItemInfo);
            }
        });
        if(checkStatus) {//收费项重复，置空
            return '';
        } else {
            return itemInfo;
        }
    }
	
	function jumpToCreateFeeDetail(){
		location="${pageContext.request.contextPath}/fixedFeeCfg/jumpToCreateFeeDetail.html?gbId=${gbId}&gbName=${gbName}";
	}
    /*----------------------常规收费项设置end----------------------*/
	/*----------------------抄表收费项start----------------------*/
    $(function(){
		/*在第 5 版时，every 被添加进 ECMA-262 标准；因此在某些实现环境中不被支持。把下面的代码放到脚本的开头来解决此问题*/
		if (!Array.prototype.every) {
			Array.prototype.every = function(fun)
			{
				'use strict';
				if(this === void 0 || this === null)throw new TypeError();
				var t = Object(this);
				var len = t.length >>> 0;
				if(typeof fun !== 'function')throw new TypeError();

				var thisArg = arguments.length >= 2 ? arguments[1] : void 0;
				for (var i = 0; i < len; i++){
					if (i in t && !fun.call(thisArg, t[i], i, t))return false;
				}

				return true;
			};
		}

		//增加收费项
		$('#mrItemForm #addCharges').click(function(){
			$('#mrItemForm .tobeclone-tr.dsn').clone(true).removeClass('dsn').appendTo('#mrItemForm #wrapTable');
			window.parent.TuneHeight();
		});
		
		//删除收费项
		$(document).on('click', '#mrItemForm .deleteCharges', function(){
			var $this = $(this);
			if(confirm('确认删除该项？')){
				if($this.attr('data-itemid') === undefined){
					$this.parents('.tobeclone-tr').remove();
					window.parent.TuneHeight();
				}else{
					$.get('${pageContext.request.contextPath}/meterReading/mrFeeItemDelete.json', {feeItemId: $this.attr('data-itemid')}, function(data, status){
						if(data.status === '0000'){
							alert('删除成功！');
                            window.location.href = '${pageContext.request.contextPath}/propertyPayConfig/chargeItemConfig.html?gbId=${gbId}&gbName=${gbName}';
						}else{
							alert(data.message);
						}
						window.parent.TuneHeight();
					});
				}
			}
		});

        /**
         * 删除计费规则
         */
        $(document).on('click', '.delMrCalculateRuleCfg', function(){
            var $this = $(this);
            if(confirm('确认删除该项？')){
                $.get('${pageContext.request.contextPath}/propertyPayConfig/delMrCalculateRuleCfg.json', {id: $this.attr('data-itemid')}, function(data, status){
                    if(data.status === '0000'){
                        alert('删除成功！');
                        window.location.href = '${pageContext.request.contextPath}/propertyPayConfig/chargeItemConfig.html?gbId=${gbId}&gbName=${gbName}';
                    }else{
                        alert(data.message);
                    }
                });
            }
        });

		//计算方式配置
		$('#mrItemForm .calculateType').click(function(){
			var thisId = $(this).attr('data-itemid');
			var itemName = $(this).attr('data-itemName');
			var mrCalculateRuleCfgId = $(this).attr('data-mrCalculateRuleCfgId');
			var gbId = $(this).attr('data-gbId');
			var gbName = $(this).attr('data-gbName');
			if(itemName == ''){
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
			  area: ['1000px', ($(window).height() - 250) +'px'],
			  iframe: {src: '${pageContext.request.contextPath}/propertyPayConfig/goToAddMrCalculateRuleCfg.html?gbName='+gbName+'&gbId='+gbId+'&mrCalculateRuleCfgId='+mrCalculateRuleCfgId+'&itemName='+itemName+'&mfiId='+thisId}
			}); 
			$("#mrItemForm #caclType").attr('data-id', thisId);
		});
		
		//保存收费项
		$("#mrItemForm").Validform({
			tiptype:3,
			btnSubmit:"#saveMrItemBtn",
			postonce:false,
			ignoreHidden: true,
			beforeSubmit:function(){
				//提交
				$.post('${pageContext.request.contextPath}/meterReading/mrFeeItemSave.json', 
				{gbId: "${gbId}", feeItemJson: JSON.stringify(getTotalItemInfo())}, 
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
			$('#mrItemForm .tobeclone-tr:visible').each(function(){
				var singleItemInfo = {};
				var $thisInput = $(this).find('.calculate-name');
				
				if($thisInput.val() !== ''){
					var thisId = $thisInput.attr('data-itemid');
					var thisName = $thisInput.val();
                    var isCalculateLatefee = $thisInput.parent("td").siblings("td").children("select[name='isCalculateLatefee']").val();
					if(thisId !== undefined){
						singleItemInfo.id = thisId;
					}
					singleItemInfo.name = thisName;
					singleItemInfo.isCalculateLatefee = isCalculateLatefee;

					totalItemSelectedInfo.push({"id": thisId, "name": thisName, "isCalculateLatefee": isCalculateLatefee});
				}
			});
			return totalItemSelectedInfo;
		}
	});
    function jumpToGetStandardList(){
        location="${pageContext.request.contextPath}/propertyPayConfig/jumpToGetStandardList.html?gbId=${gbId}&gbName=${gbName}";
    }
	/*----------------------抄表收费项end----------------------*/
	/*----------------------临时收费项设置start----------------------*/
    $(function(){
        //增加收费项
        $('#tmpItemForm #addCharges').click(function(){
            $('#tmpItemForm .tobeclone-tr.dsn').clone(true).removeClass('dsn').appendTo('#tmpItemForm #wrapTable');
            window.parent.TuneHeight();
        });

        //删除收费项
        $(document).on('click', '#tmpItemForm .deleteCharges', function(){
            var $this = $(this);
            if(confirm('确认删除该项？')){
                if ($this.attr('data-id') == undefined || $this.attr('data-id')==""){
                    $this.parents('#tmpItemForm .tobeclone-tr').remove();
                    window.parent.TuneHeight();
                } else {
                    $.get("${pageContext.request.contextPath}/propertyPayConfig/delTmpFeeItem.json", {id: $this.attr('data-id')}, function(data, status){
                        if(data.status=="0000"){
                            alert(data.message);
                            location.reload();
                        } else {
                            alert(data.message);
                        }
                        window.parent.TuneHeight();
                    });
                }
            }
        });

        //保存收费项
        $("#tmpItemForm").Validform({
            tiptype:3,
            btnSubmit:"#saveTmpItemBtn",
            postonce:false,
            ignoreHidden: true,
            beforeSubmit:function(){
                //生成数据
                var items = getTotalItemInfo("#tmpItemForm");
                if(items == '') {
                    return false;
                }
                //提交
                $.post('${pageContext.request.contextPath}/propertyPayConfig/saveTmpFeeItem.json', {"gbId":"${gbId}", "items":JSON.stringify(items.totalItemSelectedInfo)}, function(data,status){
                    if(data.status=="0000"){
                        alert(data.message);
                        location.reload();
                    } else {
                        alert(data.message);
                    }
                });
                return false;
            }
        });
    });
    /*----------------------临时收费项设置end----------------------*/
</script>
</html>