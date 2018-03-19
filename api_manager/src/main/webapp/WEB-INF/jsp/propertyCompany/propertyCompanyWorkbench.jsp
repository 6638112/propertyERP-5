
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业-物业公司管理</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="../css/jRating.jquery.css" />
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript">
	function applySC(companyId){//申请高级合作模式
		location.href="<%=basePath%>/propertyCompany/initApplySupper.html?companyId="+companyId;
	}
	function operationMgt(type,id){
		var pcId = $("#pcId").val();
		if('del'==type){
			if(window.confirm('您确定要删除该记录吗？')){
				$.ajax({//使用ajax请求删除数据
					type: "GET",
					url: "../propertyCompany/delManagement.html",
					async:true,
					data: {id:id,},
					success: function(data, textStatus){
				        alert("操作提示："+data);
				        $(".mgtTD_"+id).parent().remove();
					},
				}); 
			}else{
				return false;
			}
		}else{
			var href = "<%=basePath%>/propertyCompany/initEditMgt.html?id="+id+"&companyId="+pcId;
			$(window.parent.document).find("#mainFrame").attr("src", href);
		}
	}
	function editpc(companyId){
		var href = "<%=basePath%>/propertyCompany/editpcMsg.html?companyId="+companyId;
		$(window.parent.document).find("#mainFrame").attr("src", href);
	}
</script>
</head>

<body>
<div class="info">
    <form class="inputform">
    	<input type="hidden" id="pcId" value="${entity.id }" />
		<h1>
			<div class="right">
				<c:choose>
					<c:when test="${entity.isEdit ==2}">
						<td width="1000"><a href="#"  onclick="editpc(${entity.id });">重新修改</a></td>
					</c:when>
					<c:when test="${entity.isEdit ==null || entity.isEdit ==1}">
						<a href="#"  onclick="editpc(${entity.id });">信息修改</a>
					</c:when>
				</c:choose>
			</div>
		</h1>
		<h2>公司基本信息</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><span class="grey">物业公司名称：</span></td>
            <td>${entity.name }</td>
            <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editName !=null && entity.editName!='' && entity.editName!='pass'}">
            		<td align="right"><span class="grey">修改公司名称：</span></td>
            		<c:if test="${entity.editName != entity.name}">
            			<td width="1000" style="color:red">${entity.editName}</td>
            		</c:if>
            		<c:if test="${entity.editName == entity.name}">
            			<td width="1000">${entity.editName}</td>
            		</c:if>
            	</c:if>
            	<c:if test="${entity.editName ==null || entity.editName==''|| entity.editName=='pass'}">
            		<td align="right"><span class="grey">修改公司名称：</span></td>
            		<td width="1000">名称未修改</td>
            	</c:if>
  			</c:when>
            </c:choose>
          </tr>
          <tr>
            <td align="right"><span class="grey">公司电话：</span></td>
            <td>${entity.tel }</td>
            <c:choose>
            	<c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editTel !=null && entity.editTel!='' && entity.editTel!='pass'}">
            		<td align="right"><span class="grey">修改公司电话：</span></td>
	            	<c:if test="${entity.editTel != entity.tel}">
	            		<td width="1000" style="color:red">${entity.editTel}</td>
	            	</c:if>
            		<c:if test="${entity.editTel == entity.tel}">
	            		<td width="1000">${entity.editTel}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editTel ==null || entity.editTel==''|| entity.editTel=='pass'}">
            		<td align="right"><span class="grey">修改公司电话：</span></td>
            		<td width="1000">电话未修改</td>
            	</c:if>
            	</c:when>
            </c:choose>
          </tr>
          <tr>
            <td width="120" align="right"><span class="grey">联系人：</span></td>
            <td>${entity.linkman }</td>
            <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editLinkman !=null && entity.editLinkman!='' && entity.editLinkman != 'pass'}">
            		<td align="right"><span class="grey">修改联系人：</span></td>
            		<c:if test="${entity.editLinkman != entity.linkman}">
	            		<td width="1000" style="color:red">${entity.editLinkman}</td>
	            	</c:if>
	            	<c:if test="${entity.editLinkman == entity.linkman}">
	            		<td width="1000">${entity.editLinkman}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editLinkman ==null || entity.editLinkman=='' || entity.editLinkman =='pass'}">
            		<td align="right"><span class="grey">修改联系人：</span></td>
            		<td width="1000">联系人未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
          <tr>
            <td align="right"><span class="grey">联系人手机：</span></td>
            <td>${entity.mobilePhone }</td>
            <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editMobilePhone !=null && entity.editMobilePhone!='' && entity.editMobilePhone!='pass'}">
            		<td align="right"><span class="grey">修改联系人手机：</span></td>
            		<c:if test="${entity.editMobilePhone != entity.mobilePhone}">
	            		<td width="1000" style="color:red">${entity.editMobilePhone}</td>
	            	</c:if>
	            	<c:if test="${entity.editMobilePhone == entity.mobilePhone}">
	            		<td width="1000">${entity.editMobilePhone}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editMobilePhone ==null || entity.editMobilePhone=='' || entity.editMobilePhone=='pass'}">
            		<td align="right"><span class="grey">修改联系人手机：</span></td>
            		<td width="1000">手机号未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>

          <tr >
            <td align="right"><span class="grey">自动结算日：</span></td>
            <td>${entity.revenueDate}</td>
             <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editRevenueDate !=null && entity.editRevenueDate != 0}">
            		<td align="right"><span class="grey">修改自动结算日：</span></td>
            		<c:if test="${entity.editRevenueDate != entity.revenueDate}">
	            		<td width="1000" style="color:red">${entity.editRevenueDate}</td>
	            	</c:if>
	            	<c:if test="${entity.editRevenueDate == entity.revenueDate}">
	            		<td width="1000">${entity.editRevenueDate}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editRevenueDate ==null || entity.editRevenueDate == 0}">
            		<td align="right"><span class="grey">修改自动结算日：</span></td>
            		<td width="1000">自动结算日未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr> 
          <tr>
            <td align="right"><span class="grey">当前合作模式：</span></td>
            <td>
            	
            <c:choose>
            	<c:when test="${entity.cooperationType==1}">
            		<span class="left mtop2">基础合作</span>&nbsp;&nbsp;
            		<input id="coperationApply" class="input-btn small-btn w150 center" type="button" value="申请高级合作模式" onclick="applySC(${entity.id });" />
            	</c:when>
            	<c:when test="${entity.cooperationType==2}">
            		<span class="left mtop2">高级合作</span>
            	</c:when>
            	<c:when test="${entity.cooperationType==3}">
            		<span class="left mtop2">全面合作</span>
            	</c:when>
			</c:choose> 
            </td>
            <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit ==2}">
            	<td align="right"><span class="grey">当前审核状态：</span></td>
            	
            		<c:if test="${entity.isEdit ==2}">
            			<td width="1000">审核未通过(${entity.editResult})</td>
            		</c:if>
            		<c:if test="${entity.isEdit ==0}">
            			<td width="1000">正在审核中</td>
            		</c:if>
            	
            </c:when>
            </c:choose>
          </tr>
         
        </table>
		<h2>公司账号信息</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
           <tr>
            <td align="right" width="120" ><span class="grey">银行卡号：</span></td>
            <td>${entity.accountNo}</td>
            <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editAccountNo !=null && entity.editAccountNo!='' && entity.editAccountNo!='pass'}">
            		<td align="right"><span class="grey">修改银行卡号：</span></td>
            		<c:if test="${entity.editAccountNo != entity.accountNo}">
	            		<td width="1000" style="color:red">${entity.editAccountNo}</td>
	            	</c:if>
	            	<c:if test="${entity.editAccountNo == entity.accountNo}">
	            		<td width="1000">${entity.editAccountNo}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editAccountNo ==null || entity.editAccountNo=='' || entity.editAccountNo=='pass'}">
            		<td align="right"><span class="grey">修改银行卡号：</span></td>
            		<td width="1000">银行卡号未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
          <tr >
            <td align="right"><span class="grey">开户行：</span></td>
            <td>${entity.bankName}</td>
            <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editBankName !=null && entity.editBankName!='' && entity.editBankName!='pass'}">
            		<td align="right"><span class="grey">修改开户行：</span></td>
            		<c:if test="${entity.editBankName != entity.bankName}">
	            		<td width="1000" style="color:red">${entity.editBankName}</td>
	            	</c:if>
	            	<c:if test="${entity.editBankName == entity.bankName}">
	            		<td width="1000">${entity.editBankName}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editBankName ==null || entity.editBankName=='' || entity.editBankName=='pass'}">
            		<td align="right"><span class="grey">修改开户行：</span></td>
            		<td width="1000">开户行未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
           <tr >
            <td align="right"><span class="grey">账户名称：</span></td>
            <td>${entity.accountName}</td>
             <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editAccountName !=null && entity.editAccountName!='' && entity.editAccountName!='pass'}">
            		<td align="right"><span class="grey">修改账户名称：</span></td>
            		<c:if test="${entity.editAccountName != entity.accountName}">
	            		<td width="1000" style="color:red">${entity.editAccountName}</td>
	            	</c:if>
	            	<c:if test="${entity.editAccountName == entity.accountName}">
	            		<td width="1000">${entity.editAccountName}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editAccountName ==null || entity.editAccountName=='' || entity.editAccountName=='pass'}">
            		<td align="right"><span class="grey">修改账户名称：</span></td>
            		<td width="1000">账户名称未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
          <tr >
            <td align="right"><span class="grey">开卡支行：</span></td>
            <td>${entity.bankBranch}</td>
             <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editBankBranch !=null && entity.editBankBranch!='' && entity.editBankBranch!='pass'}">
            		<td align="right"><span class="grey">修改开卡支行：</span></td>
            		<c:if test="${entity.editBankBranch != entity.bankBranch}">
	            		<td width="1000" style="color:red">${entity.editBankBranch}</td>
	            	</c:if>
	            	<c:if test="${entity.editBankBranch == entity.bankBranch}">
	            		<td width="1000">${entity.editBankBranch}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editBankBranch ==null || entity.editBankBranch=='' || entity.editBankBranch=='pass'}">
            		<td align="right"><span class="grey">修改开卡支行：</span></td>
            		<td width="1000">开卡支行未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
          <tr >
            <td align="right"><span class="grey">支行所在省：</span></td>
            <td>${entity.bankProvince}</td>
             <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editBankProvince !=null && entity.editBankProvince!='' && entity.editBankProvince!='pass'}">
            		<td align="right"><span class="grey">修改支行所在省：</span></td>
            		<c:if test="${entity.editBankProvince != entity.bankProvince}">
	            		<td width="1000" style="color:red">${entity.editBankProvince}</td>
	            	</c:if>
	            	<c:if test="${entity.editBankProvince == entity.bankProvince}">
	            		<td width="1000">${entity.editBankProvince}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editBankProvince ==null || entity.editBankProvince=='' || entity.editBankProvince=='pass'}">
            		<td align="right"><span class="grey">修改支行所在省：</span></td>
            		<td width="1000">支行所在省未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
          <tr >
            <td align="right"><span class="grey">支行所在市：</span></td>
            <td>${entity.bankCity}</td>
             <c:choose>
            <c:when test="${entity.isEdit ==0 || entity.isEdit==2}">
            	<c:if test="${entity.editBankCity !=null && entity.editBankCity!='' && entity.editBankCity!='pass'}">
            		<td align="right"><span class="grey">修改支行所在市：</span></td>
            		<c:if test="${entity.editBankCity != entity.bankCity}">
	            		<td width="1000" style="color:red">${entity.editBankCity}</td>
	            	</c:if>
	            	<c:if test="${entity.editBankCity == entity.bankCity}">
	            		<td width="1000">${entity.editBankCity}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${entity.editBankCity ==null || entity.editBankCity=='' || entity.editBankCity=='pass'}">
            		<td align="right"><span class="grey">修改支行所在市：</span></td>
            		<td width="1000">支行所在市未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>

			<tr >
				<td align="right"><span class="grey">AppId：</span></td>
				<td>${payConfig.appid}</td>
			</tr>
			<tr >
				<td align="right"><span class="grey">支付宝公钥：</span></td>
				<td>${payConfig.publicKey}</td>
			</tr>
			<tr >
				<td align="right"><span class="grey">支付宝私钥：</span></td>
				<td>${payConfig.privateKey}</td>
			</tr>

        </table>
        <h2 class="mtop20">所管辖管理处</h2>
        <display:table name="managementList" id="row" class="info-list-02 mtop20" cellpadding="0" cellspacing="1" pagesize="<%=OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum) %>" requestURI="" partialList="true" size="managementSize">
        	<display:column title="管理处名称" class="mgtTD_${row.id }">
        		<input id="pm_${row.id }" type="hidden" value="${row.id }" />
				${row.name }
			</display:column>
        	<display:column title="管理处电话">
				${row.tel }
			</display:column>
        	<display:column title="管理处帐号">
        		<input id="pm_uc_${row.omsUserId }" type="hidden" value="${row.omsUserId }" />
				${row.userAccount }
			</display:column>
        	<display:column title="密码">
				<input type="password" class="input_text input_noborder pp w200" value="${row.password }" readonly="readonly" />
			</display:column>
			<display:column title="状态">
				<c:if test="${row.isEdit == 1 }">
					审核通过
            	</c:if>
        		<c:if test="${row.isEdit == 2 }">
					审核失败
            	</c:if>
        		<c:if test="${row.isEdit == 0 }">
					待审核
            	</c:if>
        		<c:if test="${row.isEdit == -1 }"></c:if>
			</display:column>
        	<display:column title="操作">
        		<c:if test="${row.isEdit == -1 }">
					<input class="input-btn small-btn p010 edit-acount-btn" type="button" value="编辑" onclick="operationMgt('edit',${row.id });" /> 
	            	<%-- <input class="input-btn delete" type="button" value="删除" onclick="operationMgt('del',${row.id });" /> --%>
            	</c:if>
        		<c:if test="${row.isEdit == 1 }">
					<input class="input-btn small-btn p010 edit-acount-btn" type="button" value="编辑" onclick="operationMgt('edit',${row.id });" /> 
	            	<%-- <input class="input-btn delete" type="button" value="删除" onclick="operationMgt('del',${row.id });" /> --%>
            	</c:if>
        		<c:if test="${row.isEdit == 2 }">
					<input class="input-btn small-btn p010 edit-acount-btn" type="button" value="编辑" onclick="operationMgt('edit',${row.id });" /> 
	            	<%-- <input class="input-btn delete" type="button" value="删除" onclick="operationMgt('del',${row.id });" /> --%>
            	</c:if>
			</display:column>
        </display:table>
        <div class="padb"><input id="addNewAcount" class="info-btn" type="button" value="新增管理处" onclick="operationMgt('add',null);" /></div>
    </form>
</div>
</body>
</html>
