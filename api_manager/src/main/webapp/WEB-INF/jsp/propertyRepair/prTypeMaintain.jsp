<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报修类型维护</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.simple-dtpicker.css">
</head>

<body>
<div class="info" style="max-width: 960px;">
	<form class="inputform" action="../propertyRepair/updateRepairTypeBatch.html" method="post" onsubmit="return isHasChecked();">
		<input type="hidden" name="repairerTypeId" value="${prt.id}"/>
        <h2>报修类型维护</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="120" align="right"><span class="red">*</span> 管理处：</td>
            <td>
            	<select class="select_normal" name="pmId" id="pmSelect">
            		<c:forEach items="${pmList}" var="pm">
            			<c:choose>
            				<c:when test="${prt.tPropertyManagementFId == pm.id }">
			            		<option value="${pm.id }" selected="selected" >${pm.name }</option>
            				</c:when>
            				<c:otherwise>
			            		<option value="${pm.id }"> ${pm.name }</option>
            				</c:otherwise>
            			</c:choose>
            		</c:forEach>
            	</select>
            </td>
          </tr>
			<%--复制使用--%>
		 <tr class="prt-type-tr dsn">
			 <td><input type="checkbox" /> <span class="prt-type-name"></span></td>
			 <td><input type="text" class="input_text w320 pp" name="desc" />
			 <td><input type="text" class="input_text w120 pp" name="priceDesc" /></td>
				 <input type="hidden" name="priceCfgId" />
				 <input type="hidden" name="stateId" />
				 <input type="hidden" name="stateName" />
			 </td>
		 </tr>
        </table>
		<input class="dsn" type="text" name="delStateId"/>
		<input class="dsn" type="text" name="newStateId" />
		<input class="dsn" type="text" name="newStateName" />
		<input class="dsn" type="text" name="priceDescList" />
		<input class="dsn" type="text" name="priceCfgIdList" />
		<table class="info-list-02 prt-type-list" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td>维修项目</td>
				<td>参考价格</td>
				<td>备注</td>
			</tr>
		</table>
        <div class="padb mtop10"><input class="info-btn manageRepairType" type="button" value="保 存" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">

function isHasChecked(){
	var selectCount = $(".prt-type-list [type=checkbox]:checked").length;
	if(selectCount == 0) {
		alert("至少选择一个报修类型才能提交");
	}
	return selectCount > 0;
}
    
    
$(function(){
    //页面加载时获取维修类型
	selectPrtType();
	//选择管理处时获取维修类型
    $("#pmSelect").change(function(){
    	selectPrtType();
    });

	//已选中的checkbox
	var priceCfgIdList = [];
	$(document).on('change', '.selected-box', function(){
		var thisDelstateId = $(this).attr('data-priceCfgId');
		if( !$(this).is(':checked') ){
			//加入取消勾选的价格配置id
			if(thisDelstateId != '') {
				priceCfgIdList.push(thisDelstateId);
			}
		}else{
			//删除勾选的价格配置id
			priceCfgIdList.splice($.inArray(thisDelstateId,priceCfgIdList), 1)
		}
	});
	//未选中的checkbox

	$(".manageRepairType").click(function () {
		var newStateIdList = [];
		var newStateNameList = [];
		var priceDescList = [];
		var canSubmit = true;
		$(".prt-type-tr").each(function () {
			var thisNewStateId = $(this).find("[type='checkbox']").attr('data-id');
			var thisNewStateName = $(this).find('span').text();
			//管理处ID + 维修类型id + 描述 + 参考价格
			var priceDesc = $("#pmSelect").val() + "@#@" +thisNewStateId+  "@#@" + $(this).find('[name=priceDesc]').val()+ "@#@" + $(this).find('input[name=desc]').val();

			if( $(this).find("[type='checkbox']").is(':checked') ){
				//加入勾选的id,name
				newStateIdList.push(thisNewStateId);
				newStateNameList.push(thisNewStateName);
				if($(this).find('[name=priceDesc]').val().length > 45) {
					alert(thisNewStateName+"--参考价格长度不能大于45字符！");
					canSubmit = false;
					return;
				}
				if($(this).find('[name=desc]').val().length > 45) {
					alert(thisNewStateName+"--备注长度不能大于45字符！");
					canSubmit = false;
					return;
				}
				priceDescList.push(priceDesc);
			}
		});
		$('[name=newStateId]').val(newStateIdList.toString());
		$('[name=newStateName]').val(newStateNameList.toString());
		$('[name=priceDescList]').val(priceDescList.toString());
		$('[name=priceCfgIdList]').val(priceCfgIdList.toString());

		if(canSubmit) {
			$(".inputform").submit();
		}
	});

});
//获取维修类型
function selectPrtType(){
   	$.ajax({
		type:"post",
		url:"qryPRTypeListByPMId.html",
		data:{pmId: $("#pmSelect").val(),},
		dataType:"json",
		async:false,
		success:function(data){
			var $prtTypeListBox = $('.prt-type-list'),
				$prtType = $('.prt-type-tr.dsn');
				
			try {
				if (data.status == '0000') {
					//data.dataValue.prtList 物业公司定义的类型
					//data.dataValue.prtBaseList 解放区预定义的维修类型
					//两者关系： prtList[i].baseTypeId 对应的就是prtBaseList[i].id
					
					//清空之前的类型
					$prtTypeListBox.html('');
					//插入解放区预定义类型
					if(data.dataValue.dredgeTypeList.length > 0){
						var delDtateIdList = [];
						$.each(data.dataValue.dredgeTypeList,function(i,prtBaseListData){
							var $prtTypeClone = $prtType.clone(true);
							//console.log(prtBaseListData);
							$prtTypeClone.find('.prt-type-name').text(prtBaseListData.name);
							$prtTypeClone.find('[type=checkbox]').attr({'data-id':prtBaseListData.id, 'class':'unSelected-box'});
							$prtTypeClone.find('[name=priceDesc]').val(prtBaseListData.priceDesc);
							$prtTypeClone.find('[name=desc]').val(prtBaseListData.desc);
							$prtTypeClone.find('[name=priceCfgId]').val(prtBaseListData.priceCfgId);
							$prtTypeClone.find('[name=stateId]').val(prtBaseListData.id);
							$prtTypeClone.find('[name=stateName]').val(prtBaseListData.name);
							//已选中类型
							if(prtBaseListData.sys0DelState != null){
								$prtTypeClone.find('[type=checkbox]').attr({'data-DelStateId':prtBaseListData.sys0DelState,
									'data-priceCfgId':prtBaseListData.priceCfgId,
									'class':'selected-box'});
								delDtateIdList.push(prtBaseListData.sys0DelState);
								$prtTypeClone.find('[type=checkbox]').prop('checked','checked');
							}
							$prtTypeListBox.append($prtTypeClone.removeClass('dsn'));
						});
						$prtTypeListBox.prepend("<tr><td>维修项目</td><td>参考价格</td><td>备注</td></tr>");
						$('[name=delStateId]').val(delDtateIdList.toString());
					}else{
						$prtTypeListBox.prepend("该管理处下暂无报修类型。");
					}
				} else {
					alert(data.message);

				}
			} catch (e) {
				alert(e);
			}
		},  
           error: function(){  
           	alert('网络不给力，请稍后重试'); 
           }
	});
}
</script>

</html>
