<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>维修订单详情</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clearbox.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/picbox.css">
<script src="https://twemoji.maxcdn.com/2/twemoji.min.js"></script>
</head>

<body>
<div class="info">
    <form class="inputform">
        <h2>维修订单详情</h2>
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">报修详情</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">订单号：</div></td>
            <td>${detail.billNo}</td>
          </tr>
           <tr>
            <td width="20%"><div class="list-name">维修类型：</div></td>
            <td>
            ${detail.dredgeType1}-${detail.dredgeType2}<c:if test="${!empty detail.dredgeType3}">-${detail.dredgeType3}</c:if>
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">服务供应商：</div></td>
            <td>${detail.serviceSupplier}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">服务详情：</div></td>
            <td>
                <c:if test="${not empty detail.productList}">
                    【${detail.productList[0].productName}】：<br/>
                </c:if>
             <c:forEach var="product" items="${detail.productList }">
             	<div style="float: left;">${product.specName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div><fmt:formatNumber value="${product.buyPrice }" pattern="###,###,##0.00"/>元 / ${product.priceUnit } x ${product.buyCount }</div>
             </c:forEach>
            </td>
          </tr>
         
          <tr>

            <td width="20%"><div class="list-name">报修地址：</div></td>
            <td>${detail.provice}-${detail.city}<c:if test="${!empty detail.district }">-${detail.district}</c:if>-${detail.address}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">下单解放号：</div></td>
            <td>${detail.userId}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">下单手机号：</div></td>
            <td>
            	${detail.userPhone}
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">联系人：</div></td>
            <td>${detail.linkName}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">联系电话：</div></td>
            <td>${detail.userLinkTel}</td>
          </tr>
          
          <tr>
            <td width="20%"><div class="list-name">下单时间：</div></td>
            <td><fmt:formatDate value="${detail.addServiceTm}" pattern="yyyy.MM.dd HH:mm" /></td>
          </tr>
          
          
          <tr>
            <td width="20%"><div class="list-name">期望时间：</div></td>
            <td><fmt:formatDate value="${detail.expectTm}" pattern="yyyy.MM.dd HH:mm" /></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">问题描述：</div></td>
            <td>${detail.serviceDesc}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">现场图片：</div></td>
            <td>
            	<c:forEach var="picUrl"  items="${detail.picUrlList}">
	            	<div class="uploadPreview01 marb15 mright6 drag-item">
	            		<a href="<%=OmsSysParamManager.getImageServerUrl(PathConstants.DREDGE_PIC)+PathConstants.DREDGE_PIC%>${picUrl}?x-oss-process=image/resize,m_fill,w_1000/format,jpg/interlace,1/quality,q_80" rel="clearbox[test2]">
                    		<img width="82" height="82" src='<%=OmsSysParamManager.getImageServerUrl(PathConstants.DREDGE_PIC)+PathConstants.DREDGE_PIC%>${picUrl}?x-oss-process=image/resize,m_fill,w_160,h_160/format,jpg/interlace,1/quality,q_80' border="0" />
                    	</a>
	                </div>
            	</c:forEach>
            	<%--
            	<c:forEach items="${picURL }" var="picurl">
             	<ul class="menu-img"><li><a href="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" rel="clearbox[test1]"><img src="<%= OmsSysParamManager.getImageServerUrl("/communitySupplyPic/") %>/communitySupplyPic/${picurl.picUrl}" border="0" /></a></li></ul>
            	</c:forEach>
            	 --%>
            </td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">订单状态：</div></td>
            <td>
                <c:choose>
                    <c:when test="${detail.combineStatus==1}">
                        <c:if test="${detail.billType == 5}">
                            <span title="已下单/未支付/未分配">待付款</span>
                        </c:if>
                    </c:when>
                    <c:when test="${detail.combineStatus==2}">
                        <c:if test="${detail.billType == 5}">
                            <span title="已下单/已支付/未分配">待分配</span>
                        </c:if>
                        <c:if test="${detail.billType != 5}">
                            <span title="已下单/未支付/未分配">待分配</span>
                        </c:if>
                    </c:when>
                    <c:when test="${detail.combineStatus==3}">
                        <c:if test="${detail.billType == 5}">
                            <span title="已下单/已支付/已分配">待服务</span>
                        </c:if>
                        <c:if test="${detail.billType != 5}">
                            <span title="已下单/未支付/已分配">待服务</span>
                        </c:if>
                    </c:when>
                    <c:when test="${detail.combineStatus==4}">
                        <c:if test="${detail.billType == 5}">
                            <span title="已下单/已支付/已服务">已服务</span>
                        </c:if>
                        <c:if test="${detail.billType != 5}">
                            <span title="已下单/未支付/已服务">已服务</span>
                        </c:if>
                    </c:when>
                    <c:when test="${detail.combineStatus==5}">
                        <c:if test="${detail.billType == 5}">
                            <span title="已完成/已支付/已服务">已完成</span>
                        </c:if>
                        <c:if test="${detail.billType != 5}">
                            <span title="已完成/已支付/已服务">已完成</span>
                        </c:if>
                    </c:when>
                    <c:when test="${detail.combineStatus==6}">
                        <c:if test="${detail.billType == 5}">
                            <span title="已取消/未支付/未分配">已取消</span>
                        </c:if>
                        <c:if test="${detail.billType != 5}">
                            <span title="已取消/未支付">已取消</span>
                        </c:if>
                    </c:when>
                    <c:when test="${detail.combineStatus==7}">
                        <c:if test="${detail.billType == 5}">
                            <span title="退款中">退款中</span>
                        </c:if>
                    </c:when>
                    <c:when test="${detail.combineStatus==8}">
                        <c:if test="${detail.billType == 5}">
                            <span title="已退款">已退款</span>
                        </c:if>
                        <c:if test="${detail.billType != 5}">
                            <span title="已退款">已退款</span>
                        </c:if>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
			</td>
          </tr>
        </table>
        
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">处理流程</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">师傅解放号：</div></td>
            <td>${detail.process.masterUserId}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">师傅姓名：</div></td>
            <td>${detail.process.masterName}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">师傅手机：</div></td>
            <td>${detail.process.masterTel}</td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">接单时间：</div></td>
            <td><c:if test="${!empty detail.process.masterUserId}"><fmt:formatDate value="${detail.process.acceptTm}" pattern="yyyy.MM.dd HH:mm" /></c:if></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">上门时间：</div></td>
            <td><c:if test="${!empty detail.process.masterUserId}"><fmt:formatDate value="${detail.process.expectWorkTm}" pattern="yyyy.MM.dd HH:mm" /></c:if></td>
          </tr>
          <%--
          <tr>
            <td width="20%"><div class="list-name">师傅完成时间：</div></td>
            <td>系统暂无记录师傅完成时间</td>
          </tr>
           --%>
         </table>
         <table  class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">流程记录</div></td>
          </tr>
          <c:choose>
          	<c:when test="${empty processRecordList }">
          		<tr><td colspan="2">无流程记录</td></tr>
          	</c:when>
          	<c:otherwise>
          		<c:forEach items="${processRecordList }" var="item" varStatus="status">
		          <tr>
		            <td width="20%" align="right">记录${fn:length(processRecordList) - (status.count - 1)}</td>
		            <td>
		            	${item.prAddTimeStr } ${item.prDesc} <br/>
		            	<ul class="menu-img">
		            	<!-- 
		            		<c:forEach items="${item.picList }" var="pic">
		           				<li><a href="${pic }" rel="lightbox-group"><img src="${pic }" border="0" /></a></li>
		            		</c:forEach>
		            		 -->
		            		<c:forEach var="pic"  items="${item.picList}">
				            	<div class="uploadPreview01 marb15 mright6 drag-item">
				            		<a href="${pic }" rel="clearbox[test2]">
			                    		<img width="82" height="82" src='${pic}' border="0" />
			                    	</a>
				                </div>
			            	</c:forEach>
            	
		          			</ul>
		            </td>
		          </tr>
		       	</c:forEach>
       		  <%--
          	  <c:set value="${fn:length(detail.processRecordingList)}" var="indexI"/>
          	  <c:forEach var="proRecord" items="${detail.processRecordingList }">
          	   <tr>
	       	  	<td width="20%"><div class="list-name">记录${indexI}：</div><c:set value="${indexI - 1}" var="indexI"/></td>
            	<td>
            		<fmt:formatDate value="${proRecord.recordingTm}" pattern="yyyy.MM.dd HH:mm:ss" />
            		${proRecord.processDesc}
            	</td>
               </tr>
	       	  </c:forEach>
	       	   --%>
          	</c:otherwise>
          </c:choose>
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">支付信息</div></td>
          </tr>
        </table>
        
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
		  <thead>
		  	<tr><th>订单总额</th><th>人工费</th><th>耗材费</th><th>其它费</th><th>支付方式</th><th>支付流水号</th><th>支付时间</th><th>解放号</th><th>用户支付</th><th>解放区补贴</th></tr>
		  </thead>
		  <tbody>
	  		<tr>
	  			<c:choose>
	          		<c:when test="${detail.payType == 1}">服务前付款</c:when>
	          		<c:otherwise>服务后付款</c:otherwise>
	          	  </c:choose>
	          	<td>
	          	  <c:choose>
	          		<c:when test="${detail.orderAmount > 0}"><fmt:formatNumber value="${detail.orderAmount}" pattern="###,###,##0.00"/></c:when>
	          		<c:when test="${detail.dredgeBillAmount != null}"><fmt:formatNumber value="${detail.dredgeBillAmount}" pattern="###,###,##0.00"/></c:when>
	          		<c:otherwise>0.00</c:otherwise>
	          	  </c:choose>
	          	</td>
	          	<td>
	          	  <c:forEach var="detailAmount" items="${detail.amountDetailList }">
	          	  	<c:if test="${detailAmount.adType == 0 }">
	          	  	  <c:choose>
		          		<c:when test="${detailAmount.payAmount != 0}"><fmt:formatNumber value="${detailAmount.payAmount}" pattern="###,###,##0.00"/></c:when>
		          		<c:otherwise>0.00</c:otherwise>
		          	  </c:choose>
	          	  	</c:if>
	          	  </c:forEach>
	          	</td>
	          	<td>
	          	  <c:choose>
	          		<c:when test="${detail.orderAmount - detail.dredgeBillAmount > 0}"><fmt:formatNumber value="${detail.orderAmount - detail.dredgeBillAmount}" pattern="###,###,##0.00"/></c:when>
	          		<c:otherwise>0.00</c:otherwise>
	          	  </c:choose>
	          	</td>
	          	<td>
	          	  <c:forEach var="detailAmount" items="${detail.amountDetailList }">
	          	  	<c:if test="${detailAmount.adType == 1 }">
	          	  	  <c:choose>
		          		<c:when test="${detailAmount.payAmount != 0}"><fmt:formatNumber value="${detailAmount.payAmount}" pattern="###,###,##0.00"/></c:when>
		          		<c:otherwise>0.00</c:otherwise>
		          	  </c:choose>
	          	  	</c:if>
	          	  </c:forEach>
	          	</td>
	          	<td>${detail.payMethodStr}</td>
	          	<td>${detail.payFlow}</td>
	          	<td><fmt:formatDate value="${detail.payTm}" pattern="yyyy.MM.dd HH:mm:ss" /></td>
	          	<td>${detail.userId}</td>
	          	<td>
	          	  <c:choose>
	          		<c:when test="${detail.realPay != 0}"><fmt:formatNumber value="${detail.realPay}" pattern="###,###,##0.00"/></c:when>
	          		<c:otherwise>0.00</c:otherwise>
	          	  </c:choose>
	          	</td>
	          	<td>
	          	  <c:choose>
	          		<c:when test="${detail.couponAmount != 0}"><fmt:formatNumber value="${detail.couponAmount}" pattern="###,###,##0.00"/></c:when>
	          		<c:otherwise>0.00</c:otherwise>
	          	  </c:choose>
	          	</td>
	          	<%-- 
	          	<td>
	          	  <c:choose>
	          		<c:when test="${detail.payType == 1}"><fmt:formatNumber value="${detail.orderAmount}" pattern="###,###,##0.00"/></c:when>
	          		<c:otherwise>0.00</c:otherwise>
	          	  </c:choose>
	          	</td>
	          	--%>
	        </tr>
          </tbody>
        </table>
        
        <table class="info-list-02" border="0" cellpadding="0" cellspacing="1">
          <tr class="list-title">
            <td colspan="2"><div align="left" class="f14 bold">业主评价</div></td>
          </tr>
          <tr>
            <td width="20%"><div class="list-name">用户评分：</div></td>
            <td>
            	<c:if test="${empty detail.commentList}">用户暂无评价</c:if>
            	<c:forEach var="comment" items="${detail.commentList }">
            		${comment.name }: ${comment.value } <br/>
            	</c:forEach>
            </td>
          </tr>
        </table>
	        
        <input class="info-btn save-set-prize-info-btn" type="button" onclick="history.back()" value="返回" />
        <div class="h30"></div>
    </form>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clearbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/picbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.common.js"></script>
</html>
