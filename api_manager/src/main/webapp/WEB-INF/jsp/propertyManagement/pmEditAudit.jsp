<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理处-修改-审核界面</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/clearbox.css" />
</head>

<body>
<div class="info"> 
    
        <h2>物业原始信息</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
          <tr>
            <td width="145" align="right"><Span class="red">*</Span> <span class="grey">物业公司名称：</span></td>
            <td> <c:out value="${pc.name }"></c:out></td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">联系人手机号码：</span></td>
            <td>${pc.mobilePhone }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span><span class="grey">联系人：</span></td>
            <td>${pc.linkman }</td>
          </tr>
          <tr>
            <td align="right"><Span class="red">*</Span> <span class="grey">公司电话：</span></td>
            <td>${pc.tel }</td>
          </tr>
        </table>
        <h2 class="mtop10">修改信息</h2>
        <table class="info-list-01" border="0" cellpadding="0" cellspacing="1">
	          <tr>
	            <td width="145" align="right"><Span class="red">*</Span> <span class="grey">管理处名称：</span></td>
	            <td> <c:out value="${pm.name }"></c:out></td>
	          </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">管理处号码：</span></td>
	            <td>${pm.tel }</td>
	          </tr>
	       <tr>
	           <td align="right" width="145"><Span class="red">*</Span><span class="grey">管理处负责人：</span></td>
	           <td>${pm.personChargeName }</td>
	       </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">负责人联系方式：</span></td>
	            <td>${pm.personChargeTel }</td>
	          </tr>
          
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">开户行：</span></td>
	            <td>${pm.bankName }</td>
	          </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">银行卡号：</span></td>
	            <td>${pm.accountNo }</td>
	          </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">账户名称：</span></td>
	            <td>${pm.accountName }</td>
	          </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">开卡支行：</span></td>
	            <td>${pm.bankBranch }</td>
	          </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">支行所在省：</span></td>
	            <td>${pm.bankProvince }</td>
	          </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">支行所在市：</span></td>
	            <td>${pm.bankCity }</td>
	          </tr>
	          <tr>
	            <td align="right" width="145"><Span class="red">*</Span> <span class="grey">自动结算日期：</span></td>
	            <td>${pm.settlementDay }</td>
	          </tr>
        </table>
        <h2 class="mtop40">审核</h2>
      <form class="inputform" action="../propertyCompany/savePMEditAuditResult.html" method="post">
      	<input name="pmId" value="${pm.id }" type="hidden" />
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
