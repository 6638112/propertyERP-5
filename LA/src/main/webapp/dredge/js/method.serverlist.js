$(function () {

	//轮播
    TouchSlide({ 
        slideCell:"#slideBox",
        titCell:"#slideDot ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell:"#slidePic section", 
        effect:"left", 
        autoPage:true,//自动分页
        autoPlay:true //自动播放
    });
	
    //重置banner高度
    var liLength = document.getElementById("slideList").children.length;
	if(liLength === 1){
		document.getElementById("slideDot").style.display = 'none';
	}
	
});
