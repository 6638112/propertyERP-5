<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>神码行动-邀请人配置编辑</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info" style="min-height: 750px;">
	<form class="inputform" id="editForm" method="post" action="../inviteReward/saveEdit.html">
		<input type="hidden" id="inviteConfig_id" name="configId" value="${entity.id }" />
        <h2 class="mtop20">邀请人配置信息</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><Span class="red">*</Span>解放号:</td>
            <td>
				<input type="text" id="userId" name="inviteUserId" value="${entity.tInviteUserFId }" style="background-color: #dad8d6;" readonly="readonly" class="input_text w120" datatype="n" nullmsg="请选择邀请人！" />
				<c:if test="${entity.id ==null }">
				<input id="addInviteUser" onclick="showInviteUser();" class="input-btn mtop2 mar-left15" type="button" value="选择邀请人">
				</c:if>
			</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span>邀请人:</td>
            <td>
				<input type="text" id="userName" name="inviteUserName" value="${entity.inviteUser.nickName==null?entity.inviteUser.realName:entity.inviteUser.nickName }" style="background-color: #dad8d6;" readonly="readonly" class="input_text w120" />
			</td>
          </tr>
          <tr>
            <td width="120" align="right"><Span class="red">*</Span>邀请手机号：</td>
            <td><input type="text" id="userCode" name="inviteCode" value="${entity.inviteCode }" class="input_text pp w120" datatype="m" nullmsg="请输入邀请手机号！" errormsg="请输入正确手机格式" /></td>
          </tr>
           <tr>
            <td align="right" rowspan="2"><Span class="red">*</Span>邀请人类型：</td>
            <td>
            	<select name="inviteType" class="province select_normal" data-first-title="选择" title="选择"
            		 datatype="*" nullmsg="请选择邀请人类型！">
                	<!-- <option value="">选择</option> -->
                    <option value="1" <c:if test="${entity.inviteType==1 }">selected="selected"</c:if>>解放区</option>
                    <%-- <option value="2" <c:if test="${entity.inviteType==2 }">selected="selected"</c:if>>物业</option>
                    <option value="3" <c:if test="${entity.inviteType==3 }">selected="selected"</c:if>>商家</option> --%>
                </select> 
            </td>
          </tr>
        </table>
        <div class="padb mtop10">
        	<input id="goBack" class="info-btn" type="button" value="返 回" onclick="history.back();"/>
        	<input id="sbtForm" class="info-btn" type="button" onclick="valdaSubmit();" value="确认提交" />
        </div>
    </form>
<div class="layer-bill dsn" style="width: 460px; height: auto;">
	<div>
		解放号<input type="text" id="s_user_id" class="input_text w120" />
		邀请人<input type="text" id="s_user_name" class="input_text w120" />
		<input id="searchBtn" class="input-btn mtop2 mar-left15" type="button" onclick="queryUserData()" value="查询" />
	</div>
	<hr/>
	<div id="inviteUserDiv"></div>
</div>

</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/layer.min.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype:3
	});
});
function showInviteUser(){
	initUserData();
	window.parent.TuneHeight();
	//导入账单弹出层
	$.layer({
		type: 1,
		shade: [0.4,'#000000'],
		area: ['auto', 'auto'],
		title: false,
    	border : [5, 0.3, '#000'],
		page: {dom : '.layer-bill'}
	});
}
function initUserData(){
	var userId = $('#userId').val();
	jQuery.ajax({
	  url:"../inviteReward/queryInviteUser.html",
	  cache:false,
	  dataType:"json",
	  async:false,
	  data:"id="+userId,
	  success:function(data){
		  var tableHmtl = '<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">';
		  tableHmtl += '<tr><td width="120">解放号</td><td width="120">邀请人名称</td><td width="120">邀请人手机</td><td width="60">操作</td></tr>';
		  $.each(JSON.parse(data), function(i, item) {
			  var nname = item.nickName==null?item.realName:item.nickName;
			  if(typeof(nname)=="undefined" || nname==null){
				  nname = "";
			  }
			  var moblie = item.mobile;
			  if(typeof(moblie)=="undefined" || moblie==null){
				  moblie = "";
			  }
			  tableHmtl += '<tr><td>'+item.id+'</td><td>'+nname+'</td><td>'+moblie+'</td><td><a class="red" href="javascript:submitInviteUser('+item.id+',\''+nname+'\',\''+moblie+'\');">添加</a></td></tr>';
	      });
		  tableHmtl += "</table>";
		  $('#inviteUserDiv').html(tableHmtl);
	  },
	  error:function(data){
		  alert("error out"+data);
	  }
	});
}
function queryUserData(){
	var userId = $('#s_user_id').val();
	var userName = $('#s_user_name').val();
	jQuery.ajax({
	  url:"../inviteReward/queryInviteUser.html",
	  cache:false,
	  dataType:"json",
	  async:false,
	  data:"id="+userId+"&name="+userName,
	  success:function(data){
		  var tableHmtl = '<table class="info-list-02" border="0" cellpadding="0" cellspacing="1">';
		  tableHmtl += '<tr><td width="120">解放号</td><td width="120">邀请人名称</td><td width="120">邀请人手机</td><td width="60">操作</td></tr>';
		  $.each(JSON.parse(data), function(i, item) {
			  var nname = item.nickName==null?item.realName:item.nickName;
			  if(typeof(nname)=="undefined" || nname==null){
				  nname = "";
			  }
			  var moblie = item.mobile;
			  if(typeof(moblie)=="undefined" || moblie==null){
				  moblie = "";
			  }
			  tableHmtl += '<tr><td>'+item.id+'</td><td>'+nname+'</td><td>'+moblie+'</td><td><a class="red" href="javascript:submitInviteUser('+item.id+',\''+nname+'\',\''+moblie+'\');">添加</a></td></tr>';
	      });
		  tableHmtl += "</table>";
		  $('#inviteUserDiv').html(tableHmtl);
		  var tbHeight = $("#inviteUserDiv").height();
		  $(".xubox_main").height(tbHeight + 85);
		  $(".xubox_border").height(tbHeight + 95);
	  },
	  error:function(data){
		  alert("error out"+data);
	  }
	});
}
function submitInviteUser(id,name,moblie){
	$('#userId').val(id);
	$('#userName').val(name);
	$('#userCode').val(moblie);
	layer.closeAll();
}
function valdaSubmit(){
	var id = $("#inviteConfig_id").val();
	//验证邀请手机号是否重复
	var inviteMobile = $('#userCode').val();
	$.ajax({//使用ajax请求删除数据
		type:"GET",
		url:"../inviteReward/checkMobileRepeat.html",
		async:true,
		data:{id:id,mobile:inviteMobile,},
		success:function(data, textStatus){
	        if(data==0 || data=='0'){
	        	alert('您当前输入的邀请手机号已经在系统存在，不能再次配置为码神!');
	        	return false;
	        }else{
				$('#editForm').submit();
	        }
		},
	}); 
}
</script>

</html>
