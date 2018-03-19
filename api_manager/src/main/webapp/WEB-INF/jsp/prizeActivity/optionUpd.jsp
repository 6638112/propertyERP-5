<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>//" />
<title>轻应用活动运营-奖项管理-编辑奖项</title>
<link rel="stylesheet" type="text/css" href="css/citySelect.css"/>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.simple-dtpicker.css"/>
<link href="umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div class="info">
    <form class="inputform" action="<%=basePath%>/prizeActivityJson/doOptionUpd.json" enctype="multipart/form-data" method="post">
        <h2>编辑奖项</h2>
        <input type="hidden" name="optId" value="${detail.id}"/>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">奖项描述</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 奖项名称：</div></td>
            <td><input type="text" name="name" value="${detail.name}" class="input_text w300" datatype="*1-10" nullmsg="请填写奖项名称！" errormsg="奖项名称范围为1到10个字！" placeholder="请填写奖项名称" /></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 奖项有效期：</div></td>
           
            <td><input type="text" name="valiStartTime" value="${fn:substring(detail.valiStartTime, 0, 16)}" class="input_text icon_dt" id="date01" placeholder="" datatype="*" nullmsg="请选择起始时间！" /> 至 <input type="text" name="valiEndTime" value="${fn:substring(detail.valiEndTime, 0, 16)}" class="input_text icon_dt" id="date02" placeholder="" datatype="*" nullmsg="请选择结束时间！" /></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 派奖范围：</div></td>
            <td>
                <select class="select_normal prizeAreaSelect" name="isGlobal">
                    <option value="1" <c:if test="${isGlobal==1 }">selected="selected"</c:if>>全国</option> 
                    <option value="2"  <c:if test="${isGlobal==2 }">selected="selected"</c:if>>部分城市</option>
                </select>
                <input id="citySelectBtn" class="input-btn small-btn w150 <c:if test="${isGlobal==1}">dsn</c:if>" type="button" 
                	<c:if test="${isGlobal==1}">value="选择城市"</c:if>
                	<c:if test="${isGlobal==2}">value="已选择${fn:length(detail.cityNameList)}个城市"</c:if>
                />
            </td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 奖项图标：</div></td>
            <td>
                <div class="uploadPreview01"><input type="file" name="icon" class="uploadImage02" /><img class="imgPreview" width="82" height="82" src="${iconPath}" /></div>
                <span class="f12 mar-left15">注：仅限一张图片，最佳尺寸88px*88px</span>
                <div id="picDiv" style="width: 1px; height: 1px;"></div>
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 手气如何：</div></td>
            <td><input type="text" name="luckDesc" value="${detail.luckDesc}" class="input_text w300" datatype="*1-20" nullmsg="请填手气如何说明！" errormsg="手气如何说明范围为1到20个字！" placeholder="粘粘喜气页面，用户抽奖列表的文案" /></td>
          </tr>
          <tr>
            <td><div class="list-name"><span class="red">*</span> 奖项说明：</div></td>
            <%-- <td><textarea class="textareas txt02" name="useDesc" cols="" rows="5" datatype="*4-300" nullmsg="请填写奖项说明！" errormsg="奖项说明范围为4到300个字！">${detail.useDesc}</textarea></td> --%>
            <td>
			    <div class="container">
			        <script id="editor" name="useDesc" type="text/plain" style="padding:5px 10px; width:78.8%;"></script>
			    </div>
            </td>
          </tr>
          <tr>
            <td><div class="list-name">备注信息：</div></td>
            <td><textarea class="textareas txt02" name="comment" cols="" rows="5" placeholder="自定义奖品描述，包括奖品种类、使用期限，奖品库存等信息">${detail.comment}</textarea></td>
          </tr>
          <tr>
            <td><div class="list-name">跳转URL：</div></td>
            <td><input type="text" name="linkUrl" value="${detail.linkUrl}" ignore="ignore" class="input_text pp w500" placeholder="如跳转外链，填写外链URL地址" datatype="url" nullmsg="请填写跳转URL！" errormsg="请填写正确的URL地址！" /></td>
          </tr>
        </table>
        <div class="mtop40 f14"><span class="red">*</span> <span class="bold">是否开启奖项：</span> 
	        <input class="mtop3" name="status" value="2" type="radio" <c:if test="${detail.status == 2}">checked="checked"</c:if> /> 是 
	        <input class="mtop3 mleft20" name="status" value="1" type="radio" datatype="*" nullmsg="请选择是否开启奖项！" <c:if test="${detail.status == 1}">checked="checked"</c:if>/> 否
        </div>
        
        <input id="sumGoods" class="info-btn save-set-prize-info-btn" type="submit" value="保存" />
        <!-- <table class="info-list-02 mtop20 prize-upload dsn" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">奖项配置</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name"><span class="red">*</span> 奖品信息：</div></td>
            <td><a id="downLoadBtn" class="blue" href="#"><img src="images/download-icon.jpg"> 下载奖品模版</a><br /><div class="mtop20">请选择要上传的奖品：<input name="excelFile" size="50" title="上传新账单" type="file"></div><br /><input class="input-btn w80" value="上传" type="button"> <input id="importBill" class="input-btn small-btn w100" type="button" value="奖品详情"></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">奖项总数：</div></td>
            <td></td>
          </tr>
        </table> -->
        <%@ include file="/citySelect.jsp"%>
    </form>
    <input type="checkbox" class="cityNameList hide" name="cityNameList" hidden="true" value="NULL_" checked="checked"/>
</div>

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript" src="js/layer.min.js"></script>
<script type="text/javascript" src="js/jquery.common.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="busiJS/prizeActivity.js?v5"></script>
<script type="text/javascript">
(function($){
    //表单验证
	$(".inputform").Validform({
		btnSubmit:"#sumGoods", 
		tiptype:3,
		dragonfly:true,
		ajaxpost:true,
		beforeSubmit:function(curform){
			$(".inputform").attr('onsubmit','return false;');
		},
		callback:function(data){
			$(".inputform").ajaxSubmit(function(data) {
				try {
					data = eval(data);
				} catch (e) {
					data = eval("("+data+")");
				}
				if (data.status == '0000') {
					$('.prize-upload').show();
					window.parent.TuneHeight();
					alert("奖项编辑成功！");
					window.location.href="<%=basePath%>/prizeActivity/optionDetail.html?optId="+data.dataValue.optId;
				} else {
					alert(data.message);
					$(".inputform").Validform().resetStatus();
				}
			});
			//return false;
		}
	});
	
	$('.uploadImage01').change(function(){
		$('.img-upload-status').val('1');
	});
})(jQuery);
</script>
<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    var um = UM.getEditor('editor', {
	//最基本的控件 toolbar:['source | undo redo | bold italic underline | justifyleft justifycenter justifyright justifyjustify |']
    toolbar:['source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |', 
             'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' , 
             '| justifyleft justifycenter justifyright justifyjustify |', 
             'link unlink | emotion image video  | map',
             '| horizontal print preview fullscreen', 'drafts', 'formula']
	});   
	um.setContent('${detail.useDesc}');//必须要用单引号括起来
</script>
</body>

</html>

