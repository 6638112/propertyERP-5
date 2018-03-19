<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="cleartype" content="on">
<meta name="viewport" content="width=device-width, user-scalable=no, minimal-ui">
<link rel="dns-prefetch" href="//jiefangqu.com">

<title>订单详情</title>
<script src="${resourcePathHttps}/commonjs/pageLoadingStatus.js"></script>
<link rel="stylesheet" href="../css/evaluate.css?20160815">
</head>

<body style="background:none; display: none;">
<section id="wrapBox" class="sectionBox pos-relative minheight100 heightp100" style="background:none">
	<section class="sectionBox wrap-bg evaluate_box">
		<div id="evaluateBg" class="wp100 wrap-bg evaluate_bg"></div>
		<div class="tips-box f16 pb20">
			<div id="tipCloseText" class="tip-close-text">轻触关闭</div>
			<div class="t-center mtop15 ptb20">请评价本次服务</div>
			
			<c:forEach items="${cpList}" var="item">
				<ul class="displaybox stars_list">
				<li class="ptb10 mright10">${item.name}</li>
				<li class="boxflex01">
					<ul class="displaybox evaluate_stars" data-itemId="${item.id}" data-starsNum="">
						<li class="boxflex01 pointer"><img src="../images/star_grey.png"/></li>
						<li class="boxflex01 pointer"><img src="../images/star_grey.png"/></li>
						<li class="boxflex01 pointer"><img src="../images/star_grey.png"/></li>
						<li class="boxflex01 pointer"><img src="../images/star_grey.png"/></li>
						<li class="boxflex01 pointer"><img src="../images/star_grey.png"/></li>
					</ul>	
				</li>
				</ul>
			</c:forEach>
			<div id="evaluateSubmitBtn" class="pointer"><input class="btn-submit bgred white" type="button" value="提交" /></div>
			<div class="wp100 t-center f14"><a class="blue" href="tel:400-960-2228">投诉</a></div>
		</div>
	</section>
</section>
<script src="${resourcePathHttps}/commonjs/jquery-1.11.2.min.js"></script>
<script src="${resourcePathHttps}/commonjs/Validform_v5.3.2.js"></script>
<script src="${resourcePathHttps}/commonjs/fastclick.js"></script>
<script>
	$(function(){
		new FastClick(document.body);
		
		//判断安卓ios系统
		var u = navigator.userAgent, app = navigator.appVersion;
		var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
		var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		
		//安卓
		if(isAndroid){
			//背景层关闭
			document.getElementById("evaluateBg").onclick = function(){
				window.evaluate.evaluateBgClose();
			};
			document.getElementById("tipCloseText").onclick = function(){
				window.evaluate.evaluateBgClose();
			};
		}
		
		//ios
		if(isiOS){
			//背景层关闭
			document.getElementById("evaluateBg").onclick = function(){
				document.location="jfq://"+'evaluateBgClose';
			};
			document.getElementById("tipCloseText").onclick = function(){
				document.location="jfq://"+'evaluateBgClose';
			};
		}
		
		//提交评价
		var issubmited = false;
		$('#evaluateSubmitBtn').click(function(){
			var starsNumTotal = 0;
			var evaluateNum = 0;
			var pointsListParams = {};
			
			//获取维度星级，维度个数
			$('.evaluate_stars').each(function(){
				var thisStarsNum = $(this).data('starsNum');
				var thisItemId = $(this).attr('data-itemId');
				
				if(thisStarsNum){
					pointsListParams[thisItemId] = thisStarsNum;
					
					evaluateNum += 1;
					starsNumTotal += thisStarsNum*1;
				}

			});
			
			pointsListParams = JSON.stringify(pointsListParams);
			
			var levelAverage = starsNumTotal/evaluateNum;
			
			//防止二次提交
			if(issubmited){
				return false;
			}
			$.ajax({
				type:"get",
				url:"../comments/postComment.json",
				data:{goalType:'122', goalId:'${masterId}', goalId2nd:'${dredgeBillId}', starLevel:levelAverage, pointsList:pointsListParams},
				dataType:"json",
				async:false,
				success:function(data){
					
					//已成功提交，设置issubmited为true
					issubmited = true;
					
					$.Showmsg('提交成功');
					
					setTimeout(function(){
						//安卓 按钮关闭
						if(isAndroid){
							window.evaluate.evaluateBgClose();
						}
						//ios 按钮关闭
						if(isiOS){
							document.location="jfq://"+'evaluateBgClose';
						}
					},1000)
				},
				error:function(data){
					$.Showmsg(data.message);
				}
			});
		});
		
		//选择星级
		$(document).on('click', '.evaluate_stars li', function(){
			
			var $this = $(this);
			var $thisEvaluateStars = $this.parent('.evaluate_stars');
			var thisIndex = $this.index();
			
			if($this.hasClass('on') && !$this.next('li').hasClass('on')){
				$this.removeClass('on').find('img').attr('src', '../images/star_grey.png');
				if(thisIndex === 0){
					thisIndex = '';
				}
				$thisEvaluateStars.data('starsNum', thisIndex);
				
			}else if($this.hasClass('on') && $this.next('li').hasClass('on')){
				$this.nextAll('li').removeClass('on').find('img').attr('src', '../images/star_grey.png');
				$thisEvaluateStars.data('starsNum', thisIndex + 1);
				
			}else{
				$this.addClass('on').find('img').attr('src', '../images/star_red.png');
				$this.prevAll('li').addClass('on').find('img').attr('src', '../images/star_red.png');	
				$thisEvaluateStars.data('starsNum', thisIndex + 1);
			}
			
			
		});
		
	})
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261123817'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1261123817' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>