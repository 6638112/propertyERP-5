$(function() {
	//选择日期
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.defaultOpt = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
        startYear: currYear, //开始年份
        endYear: currYear + 1 //结束年份
	};

  	var optDateTime = $.extend(opt['datetime'], opt['defaultOpt']);
    $("#startTime").mobiscroll(optDateTime).datetime(optDateTime);
    $("#endTime").mobiscroll(optDateTime).datetime(optDateTime);

    //利用遮罩层触发确定按钮
    $(document).on('click', '.opcityBtn', function(){
		$('.dwbw.dwb-s span.dwb').click();
    });
  	//判断预约时间，冒泡触发外层容器
    $(document).on('click', '.dwbw.dwb-s', function(){
    	validDateTime();
    });
});

function validDateTime(){
	var curDate = new Date(),
	curTime = getNowFormatDate().substring(0,16),
	startTime = $('#startTime').val();
	endTime = $('#endTime').val();
	/*if(startTime < curTime){
		$('#startTime').val('');
		$.Showmsg('开始时间须晚于当前时间');
	}*/
	if(endTime != '' && startTime != '' && endTime <= startTime){
		$.Showmsg('结束时间须晚于开始时间');
		return false;
	}else{
		return true;
	}
}

//获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS” 
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	var strHours = date.getHours();
	var strMin = date.getMinutes();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	if (strHours >= 0 && strHours <= 9) {
		strHours = strHours;
		strHours = "0" + strHours;
	}else{
		strHours = strHours;
	}
	if (strMin >= 0 && strMin <= 9) {
		strMin = "0" + strMin;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + strHours + seperator2 + strMin
			+ seperator2 + date.getSeconds();
	return currentdate;
}
