/**
 * textArea字数统计
 * 
 * Created on : 2015-6-26, 11:49:24
 * Author     : tom lyl010991@126.com
 */
var wordTool = {
    /**
     * 初始化
     * 
     * @param json json数组格式，如[{id:"news1", max:300, isWrap:false}, {id:"news2", max:600, isWrap:true}]
     */
    init: function (json) {
    	$.each(json, function(i, item) {
			 var id = item.id;
			 var max = item.max;
			 // 创建一个匿名div，将自身加入其中，获取textarea自身的html
			 myself_html = $('<div>').append($('#'+id).clone()).html();
			 html  = '<div class="wordCount '+id+'">';
			 html += myself_html;
			 html += '<span class="wordwrap"><var class="word">'+max+'</var>/'+max+'</span>';
			 html += '</div>';
			 $('#'+id).replaceWith(html);
			 
			 wordTool.startInputNum(id, item.isWrap);
		 });
    	wordTool.initCss();
    },
    /**
     * 剩余字数统计
     * 
     * @param id textArea的id
     */
    startInputNum: function(id, isWrap){
		//先选出 textarea 和 统计字数 dom 节点
		var div = $('.'+id);
		var textArea = div.find('#'+id);
		var wordWrap = div.find(".word");
		var max = wordWrap.text();
		
		textArea[0].setAttribute("maxlength", max);
		var curLength = textArea.val().length;
		wordWrap.text(max - curLength);
		textArea.on('input propertychange', function() {
			wordWrap.text(max - $(this).val().length);
		});
		if(isWrap){
			textArea.focus(function(){
		    	$(this).attr("rows", 5);
		    }).blur(function(){
		    	if($.trim($(this).val())==""){
		    		$(this).attr("rows", 2);
		    	}
		    });
		}
	},
	/**
	 * 重新计算数字
	 */
	reStartInputNum: function(id, max){
		var div = $('.'+id);
		var textArea = div.find('#'+id);
		var curLength = textArea.val().length;
		var wordWrap = div.find(".word");
		
		wordWrap.text(max- curLength);
	},
    /**
     * 初始化样式
     */
    initCss: function(id){
		 $(".wordCount").css({"position": "relative"});
		 $(".wordCount .wordwrap").css({"position": "absolute","right": "6px","bottom": "6px"});
		 $(".wordCount .word").css({"color": "#990000", "padding": "0 4px"});
	 }
}