<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物业公司-修改-审核界面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info">
    
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td align="right"><span class="grey">物业公司名称：</span></td>
            <td>${pc.name }</td>
            <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editName !=null && pc.editName!='' && pc.editName!='pass'}">
            		<td align="right"><span class="grey">修改公司名称：</span></td>
            		<c:if test="${pc.editName != pc.name}">
            			<td width="1000" style="color:red">${pc.editName}</td>
            		</c:if>
            		<c:if test="${pc.editName == pc.name}">
            			<td width="1000">${pc.editName}</td>
            		</c:if>
            	</c:if>
            	<c:if test="${pc.editName ==null || pc.editName==''|| pc.editName=='pass'}">
            		<td align="right"><span class="grey">修改公司名称：</span></td>
            		<td width="1000">名称未修改</td>
            	</c:if>
  			</c:when>
            </c:choose>
          </tr>
          <tr>
            <td align="right"><span class="grey">公司电话：</span></td>
            <td>${pc.tel }</td>
            <c:choose>
            	<c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editTel !=null && pc.editTel!='' && pc.editTel!='pass'}">
            		<td align="right"><span class="grey">修改公司电话：</span></td>
	            	<c:if test="${pc.editTel != pc.tel}">
	            		<td width="1000" style="color:red">${pc.editTel}</td>
	            	</c:if>
            		<c:if test="${pc.editTel == pc.tel}">
	            		<td width="1000">${pc.editTel}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editTel ==null || pc.editTel==''|| pc.editTel=='pass'}">
            		<td align="right"><span class="grey">修改公司电话：</span></td>
            		<td width="1000">电话未修改</td>
            	</c:if>
            	</c:when>
            </c:choose>
          </tr>
          <tr>
            <td width="120" align="right"><span class="grey">联系人：</span></td>
            <td>${pc.linkman }</td>
            <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editLinkman !=null && pc.editLinkman!='' && pc.editLinkman != 'pass'}">
            		<td align="right"><span class="grey">修改联系人：</span></td>
            		<c:if test="${pc.editLinkman != pc.linkman}">
	            		<td width="1000" style="color:red">${pc.editLinkman}</td>
	            	</c:if>
	            	<c:if test="${pc.editLinkman == pc.linkman}">
	            		<td width="1000">${pc.editLinkman}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editLinkman ==null || pc.editLinkman=='' || pc.editLinkman =='pass'}">
            		<td align="right"><span class="grey">修改联系人：</span></td>
            		<td width="1000">联系人未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
          <tr>
            <td align="right"><span class="grey">联系人手机：</span></td>
            <td>${pc.mobilePhone }</td>
            <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editMobilePhone !=null && pc.editMobilePhone!='' && pc.editMobilePhone!='pass'}">
            		<td align="right"><span class="grey">修改联系人手机：</span></td>
            		<c:if test="${pc.editMobilePhone != pc.mobilePhone}">
	            		<td width="1000" style="color:red">${pc.editMobilePhone}</td>
	            	</c:if>
	            	<c:if test="${pc.editMobilePhone == pc.mobilePhone}">
	            		<td width="1000">${pc.editMobilePhone}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editMobilePhone ==null || pc.editMobilePhone=='' || pc.editMobilePhone=='pass'}">
            		<td align="right"><span class="grey">修改联系人手机：</span></td>
            		<td width="1000">手机号未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr>
           <tr >
            <td align="right"><span class="grey">银行卡号：</span></td>
            <td>${pc.accountNo}</td>
            <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editAccountNo !=null && pc.editAccountNo!='' && pc.editAccountNo!='pass'}">
            		<td align="right"><span class="grey">修改银行卡号：</span></td>
            		<c:if test="${pc.editAccountNo != pc.accountNo}">
	            		<td width="1000" style="color:red">${pc.editAccountNo}</td>
	            	</c:if>
	            	<c:if test="${pc.editAccountNo == pc.accountNo}">
	            		<td width="1000">${pc.editAccountNo}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editAccountNo ==null || pc.editAccountNo=='' || pc.editAccountNo=='pass'}">
            		<td align="right"><span class="grey">修改银行卡号：</span></td>
            		<td width="1000">银行卡号未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr> 
          <tr >
            <td align="right"><span class="grey">开户行：</span></td>
            <td>${pc.bankName}</td>
            <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editBankName !=null && pc.editBankName!='' && pc.editBankName!='pass'}">
            		<td align="right"><span class="grey">修改开户行：</span></td>
            		<c:if test="${pc.editBankName != pc.bankName}">
	            		<td width="1000" style="color:red">${pc.editBankName}</td>
	            	</c:if>
	            	<c:if test="${pc.editBankName == pc.bankName}">
	            		<td width="1000">${pc.editBankName}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editBankName ==null || pc.editBankName=='' || pc.editBankName=='pass'}">
            		<td align="right"><span class="grey">修改开户行：</span></td>
            		<td width="1000">开户行未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr> 
           <tr >
            <td align="right"><span class="grey">账户名称：</span></td>
            <td>${pc.accountName}</td>
             <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editAccountName !=null && pc.editAccountName!='' && pc.editAccountName!='pass'}">
            		<td align="right"><span class="grey">修改账户名称：</span></td>
            		<c:if test="${pc.editAccountName != pc.accountName}">
	            		<td width="1000" style="color:red">${pc.editAccountName}</td>
	            	</c:if>
	            	<c:if test="${pc.editAccountName == pc.accountName}">
	            		<td width="1000">${pc.editAccountName}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editAccountName ==null || pc.editAccountName=='' || pc.editAccountName=='pass'}">
            		<td align="right"><span class="grey">修改账户名称：</span></td>
            		<td width="1000">账户名称未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr> 
          <tr >
            <td align="right"><span class="grey">开卡支行：</span></td>
            <td>${pc.bankBranch}</td>
             <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editBankBranch !=null && pc.editBankBranch!='' && pc.editBankBranch!='pass'}">
            		<td align="right"><span class="grey">修改开卡支行：</span></td>
            		<c:if test="${pc.editBankBranch != pc.bankBranch}">
	            		<td width="1000" style="color:red">${pc.editBankBranch}</td>
	            	</c:if>
	            	<c:if test="${pc.editBankBranch == pc.bankBranch}">
	            		<td width="1000">${pc.editBankBranch}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editBankBranch ==null || pc.editBankBranch=='' || pc.editBankBranch=='pass'}">
            		<td align="right"><span class="grey">修改开卡支行：</span></td>
            		<td width="1000">开卡支行未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr> 
          <tr >
            <td align="right"><span class="grey">支行所在省：</span></td>
            <td>${pc.bankProvince}</td>
             <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editBankProvince !=null && pc.editBankProvince!='' && pc.editBankProvince!='pass'}">
            		<td align="right"><span class="grey">修改支行所在省：</span></td>
            		<c:if test="${pc.editBankProvince != pc.bankProvince}">
	            		<td width="1000" style="color:red">${pc.editBankProvince}</td>
	            	</c:if>
	            	<c:if test="${pc.editBankProvince == pc.bankProvince}">
	            		<td width="1000">${pc.editBankProvince}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editBankProvince ==null || pc.editBankProvince=='' || pc.editBankProvince=='pass'}">
            		<td align="right"><span class="grey">修改支行所在省：</span></td>
            		<td width="1000">支行所在省未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr> 
          <tr >
            <td align="right"><span class="grey">支行所在市：</span></td>
            <td>${pc.bankCity}</td>
             <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editBankCity !=null && pc.editBankCity!='' && pc.editBankCity!='pass'}">
            		<td align="right"><span class="grey">修改支行所在市：</span></td>
            		<c:if test="${pc.editBankCity != pc.bankCity}">
	            		<td width="1000" style="color:red">${pc.editBankCity}</td>
	            	</c:if>
	            	<c:if test="${pc.editBankCity == pc.bankCity}">
	            		<td width="1000">${pc.editBankCity}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editBankCity ==null || pc.editBankCity=='' || pc.editBankCity=='pass'}">
            		<td align="right"><span class="grey">修改支行所在市：</span></td>
            		<td width="1000">支行所在市未修改</td>
            	</c:if>
            </c:when>
            </c:choose>
          </tr> 
          <tr >
            <td align="right"><span class="grey">自动结算日：</span></td>
            <td>${pc.revenueDate}</td>
             <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit==2}">
            	<c:if test="${pc.editRevenueDate !=null && pc.editRevenueDate != 0}">
            		<td align="right"><span class="grey">修改自动结算日：</span></td>
            		<c:if test="${pc.editRevenueDate != pc.revenueDate}">
	            		<td width="1000" style="color:red">${pc.editRevenueDate}</td>
	            	</c:if>
	            	<c:if test="${pc.editRevenueDate == pc.revenueDate}">
	            		<td width="1000">${pc.editRevenueDate}</td>
	            	</c:if>
            	</c:if>
            	<c:if test="${pc.editRevenueDate ==null || pc.editRevenueDate == 0}">
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
            	<c:when test="${pc.cooperationType==1}">
            		<span class="left mtop2">基础合作</span>&nbsp;&nbsp;
            		<input id="coperationApply" class="input-btn small-btn w150 center" type="button" value="申请高级合作模式" onclick="applySC(${pc.id });" />
            	</c:when>
            	<c:when test="${pc.cooperationType==2}">
            		<span class="left mtop2">高级合作</span>
            	</c:when>
            	<c:when test="${pc.cooperationType==3}">
            		<span class="left mtop2">全面合作</span>
            	</c:when>
			</c:choose> 
            </td>
            <c:choose>
            <c:when test="${pc.isEdit ==0 || pc.isEdit ==2}">
            	<td align="right"><span class="grey">当前审核状态：</span></td>
            	
            		<c:if test="${pc.isEdit ==2}">
            			<td width="1000">审核未通过(${pc.editResult})</td>
            		</c:if>
            		<c:if test="${pc.isEdit ==0}">
            			<td width="1000">正在审核中</td>
            		</c:if>
            	
            </c:when>
            </c:choose>
          </tr>
         
        </table>
        
        <h2 class="mtop40">审核</h2>
      <form class="inputform" action="../propertyCompany/saveEditAuditResult.html" method="post">
      	<input name="mobile" value="${pc.mobilePhone }" type="hidden" />
      	<input name="email" value="${pc.email }" type="hidden" />
      	<input name="pcId" value="${pc.id }" type="hidden" />
      	<input name="pcMobilePhone" value="${pc.mobilePhone }" type="hidden" />
        <div class="bs-example">
            <table class="info-list" border="0">
              <tr>
                <td width="90" align="right"><span class="grey">审核结果：</span></td>
                <td colspan="5"><select class="select_normal w131 select-check" name="auditResult">
                            <option value="pass">通过</option>
                            <option value="notpass">不通过</option>
                            </select></td>
              </tr>
              <tr class="dsn">
                <td align="right"><span class="grey">原因：</span></td>
                <td colspan="5"><textarea class="textareas txt02" name="notPassReason" cols="" rows="5" datatype="*4-100" nullmsg="审核结果为“不通过”时需填写原因！" errormsg="原因范围为4到100个字！"></textarea></td>
              </tr>
            </table>
        </div>
        <div class="padb"><input id="sumAccount" class="info-btn" type="submit" value="提 交" /></div>
    </form>
</div>
</body>
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/clearbox.js?v20150215"></script>
<script type="text/javascript" src="../js/jquery.common.js"></script>
<script type="text/javascript" src="../js/Validform_v5.3.2.js"></script>
</html>
