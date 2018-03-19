<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业公司-注册-审核界面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/picbox.css" />
<link rel="stylesheet" type="text/css" href="../css/select2.css">
</head>

<body>
<div class="info">
    
        <h2>物业公司信息</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr><input type="hidden" value="${pc.name }" id="pcName"/>
            <td width="145" align="right"><Span class="red">*</Span> <span class="grey">物业公司名称：</span></td>
            <td> <c:out value="${pc.name }"></c:out></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">手机号码：</span></td>
            <td>${pc.mobilePhone }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">联系人：</span></td>
            <td>${pc.linkman }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">联系邮箱：</span></td>
            <td>${pc.email }</td>
          </tr>
          <tr>
            <td align="right"><span class="grey">上传的营业执照：</span></td>
            <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivatePicBasePathList) %>${pc.photoBusinessLicense }" rel="lightbox"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoBusinessLicense }" border="0" /></a></li></ul></td>
          </tr>
          <tr>
            <td align="right"><span class="grey">上传的小区管理资质证明：</span></td>
            <td><ul class="menu-img"><li><a href="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivatePicBasePathList) %>${pc.photoCredentials }" rel="lightbox"><img src="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl) %>${pc.photoCredentials }" border="0" /></a></li></ul></td>
          </tr>
           <tr >
            <td align="right"><span class="grey">银行卡号：</span></td>
            <td>${pc.accountNo}</td>
          </tr> 
          <tr >
            <td align="right"><span class="grey">开户行：</span></td>
            <td>${pc.bankName}</td>
          </tr> 
          <tr >
            <td align="right"><span class="grey">账户名称：</span></td>
            <td>${pc.accountName}</td>
          </tr> 
          <tr >
            <td align="right"><span class="grey">开卡支行：</span></td>
            <td>${pc.bankBranch}</td>
          </tr>
          <tr >
            <td align="right"><span class="grey">支行所在省：</span></td>
            <td>${pc.bankProvince}</td>
          </tr>
          <tr >
            <td align="right"><span class="grey">支行所在市：</span></td>
            <td>${pc.bankCity}</td>
          </tr>
          <tr >
            <td align="right"><span class="grey">自动结算日：</span></td>
            <td>${pc.revenueDate}</td>
          </tr>
          <tr>
            <td align="right" class="bold">所管辖小区：</td>
            <td></td>
          </tr>
          <c:forEach items="${gbrList}" var="gbr">
	          <tr>
	            <td align="right"><span class="grey">小区信息：</span></td>
	            <td>${gbr.apName }&nbsp;&nbsp;${gbr.acName }&nbsp;&nbsp;${gbr.abName }&nbsp;&nbsp;${gbr.gbrName }&nbsp;&nbsp;${gbr.gbrAddressdesc }</td>
	          </tr>
          </c:forEach>
        </table>
        <h2 class="mtop40">可修改如下配置</h2>
      <form class="inputform" action="../propertyCompany/saveEditAfterAudited.html" method="post">
      	<input name="mobile" value="${pc.mobilePhone }" type="hidden" />
      	<input name="email" value="${pc.email }" type="hidden" />
      	<input name="pcId" value="${pc.id }" type="hidden" />
      	<input name="pcMobilePhone" value="${pc.mobilePhone }" type="hidden" />
      	<input name="omsUserId" value="${pc.adminId }" type="hidden" />
      	<input name="oldCooperationType" value="${pc.cooperationType }" type="hidden"/>
        <table id="createAccount" class="info-list-01 mtop40" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td width="90" align="right"><span class="grey">物业合作模式：</span></td>
				<td colspan="5">
					<select
						class="select_normal w131" name="cooperationType">
							<option value="0"  <c:if test="${pc.cooperationType==0 }"> selected="selected"</c:if> >无</option>
							<option value="1" <c:if test="${pc.cooperationType==1 }"> selected="selected"</c:if> >基础</option>
							<option value="2" <c:if test="${pc.cooperationType==2 }"> selected="selected"</c:if> >高级</option>
							<option value="3" <c:if test="${pc.cooperationType==3 }"> selected="selected"</c:if> >全面</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="90" align="right"><span class="grey">账号角色：</span></td>
				<td colspan="5">
				<c:forEach items="${roleList }" var="role">
					<c:choose>
						<c:when test="${role.status==3}">
							<input class="mtop3" name="roleId" type="radio" checked="checked"
							value="${role.id }" datatype="*" nullmsg="请选择一个角色！" />${role.name } &nbsp; &nbsp;
						</c:when>
						<c:otherwise><input class="mtop3" name="roleId" type="radio" 
							value="${role.id }" datatype="*" nullmsg="请选择一个角色！" />${role.name } &nbsp; &nbsp;
						</c:otherwise>
					</c:choose>
           		</c:forEach></td>
			</tr>
			<tr>
            <td width="90" align="right"><span class="grey">商务跟进人：</span></td>
            <td colspan="5">
            	<select name="follower">
            		<option value="">请选择</option>
            		<c:forEach items="${userList}" var="item" >
	           			<option value="${item.realName }" <c:if test="${pc.follower==item.realName }">selected</c:if> >${item.realName }</option>
           			</c:forEach>
            	</select>
            </td>
          </tr>
          <c:if test="${isHasPc == false }">
          <tr>
          	<td width="90" align="right"><span class="grey">合作代理信息：</span></td>
            <td colspan="5">
            	<input class="mtop3 cooper-type" name="cooperType" type="radio" data-val="company"/> 代理商
            	<span class="mleft20"><input class="mtop3 cooper-type" name="cooperType" type="radio" data-val="person" /> 代理人</span>
            </td>
          </tr>
          <tr class="swap-con swap-val-company" style="display: none;">
            <td></td>
            <td>
            	<select class="select2_class select_normal" name="channelPartnerFId">
                </select>
            </td>
          </tr>
          <tr class="swap-con swap-val-person" style="display: none;">
            <td></td>
            <td>
            	<select class="select2_class select_normal" name="channelPartnerFId">
                </select>
            </td>
          </tr>
          </c:if>
           <c:if test="${isHasPc == true }">
          <tr>
          	<td width="90" align="right"><span class="grey">合作代理信息：</span></td>
            <td colspan="5">
            	<c:forEach items="${cpList}" var="item" >
            		<c:if test="${item.partnertype=='company' }">
            			${item.companyName }&nbsp; &nbsp;
            		</c:if>
            		<c:if test="${item.partnertype=='person' }">
            			${item.name }-${item.idNumber }&nbsp; &nbsp;
            		</c:if>
         		</c:forEach>
            </td>
          </tr>
          </c:if>
        </table>
        <div class="padb"><input id="sumAccount" class="info-btn" type="submit" value="提 交" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/picbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.cxselect.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../js/select2.js"></script>

<script type="text/javascript">
$(function(){
    //表单验证
	$(".inputform").Validform({
		tiptype: 3,
		ignoreHidden: true,
	});
    
	$('.select2_class').select2();

    radioChange02('.cooper-type');
});

//radio单选切换
function radioChange02(obj){
    $(obj).click(function(){
        var $checkTrCon = $(this).parents('tr').nextAll('tr.swap-con');
        var thisVal = $(this).attr('data-val');
        var $thisSwapVal = $(this).parents('tr').nextAll('.swap-val-' + thisVal);
        var pcName = $("#pcName").val();
        var channelType = thisVal;
        $checkTrCon.hide();
        if($thisSwapVal){
            $.ajax({
                type:"POST",   //http请求方式
                url:"../channelPartner/getCompanyChannelPartnerList.json", //发送给服务器的url
                data:{'cpName':pcName,'channelType':channelType}, //发送给服务器的参数
                success:function(data){
                    $thisSwapVal.find("select").html(data);
                },
            });
            $thisSwapVal.show();
        }
        window.parent.TuneHeight();
    });
}

//验证用户是否存在
function validateUserName(ths) {

    // 1获取文本框的内容
    var userName = ths.value;

    // 2将获取到的内容发送给服务器的servlet
    //使用jquery的XMLHTTPRequest对象get请求的封装
    
    $.ajax({
        type:"POST",   //http请求方式
        url:"../omsUser/validUserName.html", //发送给服务器的url
        data:"omsUserName="+userName, //发送给服务器的参数
        success:function(data, textStatus){ 
            $("#showResult").html(data);
            if(data==="已经存在该账号，请重新输入"){
           	 	$("#showResult").addClass('red');
           	 	ths.focus();
            }else{//验证通过
            	$("#showResult").removeClass('red').css("color","green");
            }
        },
    });
}

$(function(){
	$("#sumAccount").click(function(){
		if($("#showResult").hasClass('red')){
			alert("已经存在该账号，请重新输入");
			return false;
		}
	});
} )

</script>
</html>
