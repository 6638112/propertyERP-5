var turnplate={
	restaraunts:[],				//大转盘奖品名称
	colors:[],					//大转盘奖品区块对应背景颜色
	outsideRadius:192,			//大转盘外圆的半径
	textRadius:120,				//大转盘奖品位置距离圆心的距离
	insideRadius:68,			//大转盘内圆的半径
	startAngle:0,				//开始角度

	bRotate:false				//false:停止;ture:旋转
};

$(function(){
	//动态添加大转盘的奖品与奖品区域背景颜色
	//var namesArray = $('.turnplate').attr('data-names').replace('[','').replace(']','').split(',');
	
//	turnplate.restaraunts = ["Vivo X7", "儿童智能手表", "精美卡通自拍杆", "长城50M宽带", "电动车", "谢谢参与"];
//	turnplate.colors = ["#FFF4D6", "#FFFFFF"];
//	turnplate.pic = ["../images/a01.png", "../images/a02.png", "../images/a03.png", "../images/a04.png","../images/a05.png", "../images/a06.png"];
//	turnplate.picRound = ["../images/award_01.png", "../images/award_02.png", "../images/award_03.png", "../images/award_04.png","../images/award_05.png", "../images/award_06.png"];

	turnplate.restaraunts = ["一等奖", "二等奖", "三等奖", "阳光普照奖"];
	turnplate.colors = ["#FFF4D6", "#FFFFFF"];
	turnplate.pic = ["../images/a01.png", "../images/a02.png", "../images/a03.png", "../images/a04.png"];
	turnplate.picRound = ["../images/award_01.png", "../images/award_02.png", "../images/award_03.png", "../images/award_04.png"];

	var rotateTimeOut = function (){
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:2160,
			duration:8000,
			callback:function (){
				layer.alert('网络超时，请检查您的网络设置！');
			}
		});
	};

	//旋转转盘 item:奖品位置; txt：提示语;
	var rotateFn = function (item, txt, desc, phoneNum){
		var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length*2));
		if(angles<270){
			angles = 270 - angles;
		}else{
			angles = 360 - angles + 270;
		}
		$('#wheelcanvas').stopRotate();
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:angles+1800,
			duration:8000,
			callback:function (){
				setAwardInfo(item, txt, desc, phoneNum);
				turnplate.bRotate = !turnplate.bRotate;
			}
		});
	};

	$('#awardPointer').click(function (){
		if(turnplate.bRotate)return;
		turnplate.bRotate = !turnplate.bRotate;

		resetPopBoxSize();
		
		var userId = $('#tabBox').attr('data-userId');
		isToAward('../lotteryDraw/drawLottery.json?userId=' + userId);
	});

	resetPopBoxSize();

	//条件判断+延迟执行，避免resize事件频繁触发
	var isResizing = true;
	$(window).resize(function(){
		if(isResizing){
			setTimeout(resetPopBoxSize,500);
			isResizing = false;
		}
	});
	//重设top高度
	var imgComplete = setInterval(function(){
		if($(".top-bg").height() !== 0){
			resetBannerSize();
			clearInterval(imgComplete);
		}
	},100);

	//关闭中奖信息
	$('#closeTipsBtn').click(function(){
		$('#wrapBox').removeClass('heightp100');
		$('.pop-box02').addClass('dsn');
	});

	//重设弹窗尺寸
	function resetPopBoxSize(){
		isResizing = true;
		var windowWidth = $('#wrapBox').width();
		var windowHeight = $('body').height();
		if((windowWidth/windowHeight) > 460/657){
			$('.desc-box').width(460*windowHeight*0.8/657).css({'top':'3%', 'margin-left':(windowWidth-460*windowHeight*0.8/657)/2});
		}
		if($('.desc-box').width() === 0){
			$('.desc-box').width($('.wrap-bg').width() * 0.8).css({
				'top': '3%',
				'margin-left': (windowWidth - $('.wrap-bg').width() * 0.8) / 2
			});
		}
		resetBannerSize();
	}
	function resetBannerSize(){
		$(".banner").height($(".top-bg").height());
	}

	//点击抽奖按钮，访问接口
	function isToAward(url){
		$.ajax({
			url: url,
			dataType: 'json',
			async: true,
			timeout: 10000,
			success: function(data){
				if(data.status=="0000"){
					if(data.dataValue.id){
						for(var i=0; i<turnplate.restaraunts.length; i++){
							if(data.dataValue.name==turnplate.restaraunts[i]){
								//设置中奖的奖品序号
								var item = i+1;
								var desc = data.dataValue.desc;
								var phoneNum = data.dataValue.mobile;
								//转盘开始转动
								rotateFn(item, turnplate.restaraunts[item-1], desc, phoneNum);
								break;
							}
						}
					}
				} else {
					layer.alert(data.message);
					turnplate.bRotate = !turnplate.bRotate;
					return false;
				}
			},
			complete: function(XMLHttpRequest,status){
				if(status === 'timeout'){
					layer.alert('网络不给力，请重试');
					turnplate.bRotate = !turnplate.bRotate;
				}
			}
		});
	}

	//已中奖，设置转盘位置
	var goToAwardRotateFn = function (item){
		var angles = item * (360 / turnplate.restaraunts.length) - (360 / (turnplate.restaraunts.length*2));
		if(angles<270){
			angles = 270 - angles;
		}else{
			angles = 360 - angles + 270;
		}
		$('#wheelcanvas').stopRotate();
		$('#wheelcanvas').rotate({
			angle:0,
			animateTo:angles,
			duration:1000,
			callback:function (){
				turnplate.bRotate = !turnplate.bRotate;
			}
		});
	};

	var awardName = $(".main-part01").attr('data-id');
	for(var i=0; i<turnplate.restaraunts.length; i++){
		if(awardName==turnplate.restaraunts[i]){
			var awardId = i;
//			$("#awardImgRound").attr('src', turnplate.picRound[i]);
			goToAwardRotateFn(awardId+1);
		}
	}
});

//设置中奖信息
function setAwardInfo(item, txt, desc, phoneNum){

	//$("#awardImg").attr('src', turnplate.pic[item-1]);

	if(txt !== '谢谢参与'){
		$(".award-name").text(txt);
//		$(".award-phone").text(phoneNum);

//		$("#awardImgRound").attr('src', turnplate.picRound[item-1]);
		$('#awardText').text(desc);
		$("#awardPointer").attr('src', '../images/turnplate-pointer-bingo.png').off('click');

		$(".main-part01a").show();

	}else{
		$(".award-name").remove();
		$("#awardText").remove();

		$("#popBoxBg").attr('src', '../images/pop_bg_sorry.png');
		$("#awardPointer").attr('src', '../images/turnplate-pointer-sorry.png').off('click');

	}

	//弹出中奖信息
	$('#wrapBox').addClass('heightp100');
	$('.pop-box02').removeClass('dsn');

	$("#awardPointer").click(function(){
		layer.alert('1个用户仅有1次抽奖机会');
	});
}

function rnd(n, m){
	var random = Math.floor(Math.random()*(m-n+1)+n);
	return random;

}

//页面所有元素加载完毕后执行drawRouletteWheel()方法对转盘进行渲染
window.onload=function(){
	drawRouletteWheel();
};

function drawRouletteWheel() {
	var canvas = document.getElementById("wheelcanvas");
	if (canvas.getContext) {
		//根据奖品个数计算圆周角度
		var arc = Math.PI / (turnplate.restaraunts.length/2);
		var ctx = canvas.getContext("2d");
		//在给定矩形内清空一个矩形
		ctx.clearRect(0,0,422,422);
		//strokeStyle 属性设置或返回用于笔触的颜色、渐变或模式
		ctx.strokeStyle = "#FFBE04";
		//font 属性设置或返回画布上文本内容的当前字体属性
		ctx.font = '16px Microsoft YaHei';
		for(var i = 0; i < turnplate.restaraunts.length; i++) {
			var angle = turnplate.startAngle + i * arc;
			if(i%2 === 0){
				ctx.fillStyle = turnplate.colors[1];
			}else{
				ctx.fillStyle = turnplate.colors[0];
			}
			ctx.beginPath();
			//arc(x,y,r,起始角,结束角,绘制方向) 方法创建弧/曲线（用于创建圆或部分圆）
			ctx.arc(211, 211, turnplate.outsideRadius, angle, angle + arc, false);
			ctx.arc(211, 211, turnplate.insideRadius, angle + arc, angle, true);
			ctx.stroke();
			ctx.fill();
			//锁画布(为了保存之前的画布状态)
			ctx.save();

			//----绘制奖品开始----
			ctx.fillStyle = "#E5302F";
			var text = turnplate.restaraunts[i];
//		  var line_height = 17;
			//translate方法重新映射画布上的 (0,0) 位置
			ctx.translate(211 + Math.cos(angle + arc / 2) * turnplate.textRadius, 211 + Math.sin(angle + arc / 2) * turnplate.textRadius);

			//rotate方法旋转当前的绘图
			ctx.rotate(angle + arc / 2 + Math.PI / 2);

			ctx.font = 'bold 22px Microsoft YaHei';
			ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
			/** 下面代码根据奖品类型、奖品名称长度渲染不同效果，如字体、颜色、图片效果。(具体根据实际情况改变) **/

			//添加对应图标
//			var img= new Image();
//			img.src = turnplate.pic[i];
//			ctx.drawImage(img,-30,10,60,73);

			//把当前画布返回（调整）到上一个save()状态之前
			ctx.restore();
			//----绘制奖品结束----
		}
	}
}
