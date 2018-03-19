$(function(){
	//广告点击统计
	$(document).on('click', '.get-ad-click', function(){
		var $this = $(this);
		var advLink = $this.attr('href');
		var advTitle = $this.attr('data-title'); 
		var advId = $this.attr('data-id') || -1; 
		
		getNumberOfClicks(advId,advTitle,advLink,3)
	})
});

//广告统计接口	1.首页 2.街坊 3.社区店到家。advId没有传-1，positionType没有传0
function getNumberOfClicks(advId,advTitle,advLink,positionType){
	$.ajax({
		  url: "../common/toUrl.do",
		  async: false,
		  dataType:"json",
		  data:{"advId":advId, "advTitle":advTitle, "advLink":advLink, "positionType":positionType, "detailUrl":"/ebuyNew/clickAd.json"},
		  success: function(data){}
	});
}