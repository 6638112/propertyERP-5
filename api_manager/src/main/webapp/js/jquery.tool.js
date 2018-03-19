(function($) {
    //给页面装载CSS样式
    var LG = 'linear-gradient(top, #fafafa, #eee)', CSS = '<style type="text/css">' +
            '#zxxBlank{position:fixed;z-index:2000;left:0;top:0;width:100%;height:100%;background:black;}' +
            '.wrap_out{ border-radius:0px;padding:0px;background-color:#ebebeb; -moz-box-shadow:5px 5px 5px rgba(0, 0, 0, .7); -webkit-box-shadow:0 0 5px rgba(0, 0, 0, .7); box-shadow:0 0 5px rgba(0, 0, 0, .7); overflow:hidden;position:fixed;z-index:2000;left:-9999px;}' +
            '.wrap_in{ box-shadow:5px 5px 5px rgba(0, 0, 0, .7)}' +
            '.wrap_bar{border-bottom:1px solid #ddd;background:##fbfbfb;font-size:13px;}' +
            '.wrap_title{line-height:24px;padding-left:10px;margin:0;font-size:13px;font-family:微软雅黑}' +
            '.wrap_close{position:relative;}' +
            '.wrap_close a{width:20px;height:20px;text-align:center;margin-top:-22px;color:#34538b;font:bold 1em/20px Tahoma;text-decoration:none;cursor:pointer;position:absolute;right:6px;}' +
            '.wrap_close a:hover{text-decoration:none;color:#f30;}' +
            '.wrap_body{font-family:微软雅黑;font-size:13px;op}' +
            '.wrap_remind{line-height:40px;text-align:center}' +
            '.wrap_remind p{margin:10px 0 0;}' +
            '#wrapOperate{text-align:right;padding:10px}' +
            '.wrap_operate{padding:10px;margin-bottom:20px; text-align:right;}' +
            '.submit_btn, .cancel_btn{margin: 2px;border-radius: 4px;font-family:微软雅黑;font-size:13px;display:inline-block;padding:3px 8px 3px;line-height:10px;border:1px solid;cursor:pointer;overflow:visible;background:#eee;border-color:#f0f0f0 #bbb #bbb #f0f0f0;color:#333;}' +
            '.submit_btn:hover{background:#486aaa;border-color:#a0b3d6 #34538b #34538b #a0b3d6;color:#f3f3f3;}' +
            '.submit_btns{text-decoration:none;color:#fff;}' +
            '.cancel_btn:hover{background:#486aaa;border-color:#a0b3d6 #34538b #34538b #a0b3d6;color:#f3f3f3;}' +
            '1wrap_out{border:1px solid #fff; -moz-border-radius:3px; -webkit-border-radius:3px; border-radius:3px; background-color:#ebebeb; -moz-box-shadow:0 0 5px rgba(0, 0, 0, .7); -webkit-box-shadow:0 0 5px rgba(0, 0, 0, .7); box-shadow:0 0 5px rgba(0, 0, 0, .7); overflow:hidden;}' +
            '</style>';
    $("head").append(CSS);

    var WRAP = '<div id="zxxBlank" onselectstart="return false;"></div>' +
            '<div class="wrap_out" id="wrapOut" style="position: fixed">' +
            '' +
            '<div id="wrapBar" class="wrap_bar" onselectstart="return false;">' +
            '<h4 id="wrapTitle" class="wrap_title"></h4>' +
            '<div class="wrap_close"><a href="javasctipt:" id="wrapClose" title="closed"></a></div>' +
            '</div>' +
            '<table  width="100%"><tr><td style="border:none" class="wrap_body" id="wrapBody" ></td></tr><table>' +
            '<div id="wrapOperate"><button id="zxxSureBtn" class="submit_btn"><label chinese="确定" english="Sure"></label></button>&nbsp;&nbsp;<button id="zxxCancelBtn" class="cancel_btn"><label chinese="取消" english="Cancel"></label></button></div>' +
            '' +
            '</div>';
    var langBase = "chinese";
    var imgs = "data:image/gif;base64,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"
    $.fn.cxhd = function(options) {
        options = options || {};
        var s = $.extend({}, cxhdDefault, options);
        return this.each(function() {
            var node = this.nodeName.toLowerCase();
            if (node === "a" && s.ajaxTagA) {
                $(this).click(function() {
                    var href = $.trim($(this).attr("href"));
                    if (href && href.indexOf("javascript:") != 0) {
                        if (href.indexOf('#') === 0) {
                            $.cxhd($(href), options);
                        } else {
                            //加载图片
                            $.cxhd.loading();
                            var myImg = new Image(), element;
                            myImg.onload = function() {
                                var w = myImg.width, h = myImg.height;
                                if (w > 0) {
                                    var element = $('<img src="' + href + '" width="' + w + '" height="' + h + '" />');
                                    options.protect = false;
                                    $.cxhd(element, options);
                                }
                            };
                            myImg.onerror = function() {
                                //显示加载图片失败
                                $.cxhd.ajax(href, {}, options);
                            };
                            myImg.src = href;
                        }
                    }
                    return false;
                });
            } else {
                $.cxhd($(this), options);
            }
        });
    };

    $.cxhd = function(elements, options) {
        if (!elements) {
            return;
        }

        var s = $.extend({}, cxhdDefault, options || {});

        //弹框的显示
        var eleOut = $("#wrapOut"), eleBlank = $("#zxxBlank");

        if (eleOut.size()) {
            eleOut.show();
            eleBlank[s.bg ? "show" : "hide"]();
        } else {
            $("body").append(WRAP);
            $("label").each(function() {
                $(this).text($(this).attr(langBase || s.langBase))
            });
        }
        if (typeof (elements) === "object") {
            elements.show();
        } else {
            elements = $(elements);
        }
        //一些元素对象
        $.o = {
            s: s,
            ele: elements,
            bg: eleBlank.size() ? eleBlank : $("#zxxBlank"),
            out: eleOut.size() ? eleOut : $("#wrapOut"),
            tit: $("#wrapTitle"),
            bar: $("#wrapBar"),
            clo: $("#wrapClose"),
            bd: $("#wrapBody"),
            but: $("#wrapOperate")
        };

        // 标题以及关闭内容
        $.o.tit.html(s.title[s.langBase]);
        $.o.clo.html(s.shut);

        //装载元素
        $.o.bd.empty().append(elements);

        if ($.isFunction(s.onshow)) {
            s.onshow();
        }
        //尺寸
        $.cxhd.setSize();
        //定位
        $.cxhd.setPosition();

        if (s.fix) {
            $.cxhd.setFixed();
        }
        if (s.drag) {
            $.cxhd.drag();
        } else {
            $(window).resize(function() {
                $.cxhd.setPosition();
            });
        }
        if (!s.bar) {
            $.cxhd.barHide();
        } else {
            $.cxhd.barShow();
        }

        if (!s.buttonStatus) {
            $.cxhd.buttonHide();
        } else {
            $.cxhd.buttonShow();
        }
        if (!s.bg) {
            $.cxhd.bgHide();
        } else {
            $.cxhd.bgShow();
        }
        if (!s.btnclose) {
            $.cxhd.closeBtnHide();
        } else {
            $.o.clo.click(function() {
                $.cxhd.hide();
                return false;
            });
        }
        if (s.bgclose) {
            $.cxhd.bgClickable();
        }
        if (s.delay > 0) {
            setTimeout($.cxhd.hide, s.delay);
        }
    };
    $.extend($.cxhd, {
        setSize: function() {
            if (!$.o.bd.size() || !$.o.ele.size() || !$.o.bd.size()) {
                return;
            }
            //主体内容的尺寸
            $.o.out.css({
                "width": $.o.s.width,
                "height:": $.o.s.height
            });
            return $.o.out;
        },
        setPosition: function(flag) {
            flag = flag || false;
            if (!$.o.bg.size() || !$.o.ele.size() || !$.o.out.size()) {
                return;
            }

            var w = document.body.clientWidth, h = document.body.clientHeight, st = $(window).scrollTop(), ph = $("body").height();
            if (ph < h) {
                ph = h;
            }

            $.o.bg.css("opacity", "0.5");
            $.o.out.css("opacity", $.o.s.opacity);
            //主体内容的位置
            //获取当前主体元素的尺寸
            var xh = $.o.out.outerHeight(), xw = $.o.out.outerWidth();
            var t = (h - xh) / 2, l = (w - xw) / 2;
            if ($.o.s.fix && window.XMLHttpRequest) {
                t = (h - xh) / 2;
            }
            if (flag === true) {
                $.o.out.animate({
                    top: 200,
                    left: l
                });
            } else {
                $.o.out.css({
                    top: 200,
                    left: l,
                    zIndex: $.o.s.index
                });
            }
            if ($.o.s.left && $.o.s.top)
                $.o.out.css("left", $.o.s.left);
            if ($.o.s.top)
                $.o.out.css("top", $.o.s.top);
            if ($.o.s.right)
                $.o.out.css("right", $.o.s.right);
            if ($.o.s.bottom)
                $.o.out.css("bottom", $.o.s.bottom);

            return $.o.out;
        },
        //定位
        setFixed: function() {
            if (!$.o.out || !$.o.out.size()) {
                return;
            }
            if (window.XMLHttpRequest) {
                $.cxhd.setPosition().css({
                    position: "fixed"
                });
            } else {
                $(window).scroll(function() {
                    $.cxhd.setPosition();
                });
            }
            return $.o.out;
        },
        //背景可点击
        bgClickable: function() {
            if ($.o.bg && $.o.bg.size()) {
                $.o.bg.click(function() {
                    $.cxhd.hide();
                });
            }
        },
        //背景隐藏
        bgHide: function() {
            if ($.o.bg && $.o.bg.size()) {
                $.o.bg.hide();
            }
        },
        //背景层显示
        bgShow: function() {
            if ($.o.bg && $.o.bg.size()) {
                $.o.bg.show();
            } else {
                $('<div id="zxxBlank"></div>').prependTo("body");
            }
        },
        buttonHide: function() {
            if ($.o.but && $.o.but.size()) {
                $.o.but.hide();
            }

        },
        buttonShow: function() {
            if ($.o.but && $.o.but.size()) {
                $.o.but.show();
            }
        },
        //标题栏隐藏
        barHide: function() {
            if ($.o.bar && $.o.bar.size()) {
                $.o.bar.hide();
            }
        },
        //标题栏显示
        barShow: function() {
            if ($.o.bar && $.o.bar.size()) {
                $.o.bar.show();
            }
        },
        //关闭按钮隐藏
        closeBtnHide: function() {
            if ($.o.clo && $.o.clo.size()) {
                $.o.clo.hide();
            }
        },
        //弹框隐藏
        hide: function() {
            if ($.o.ele && $.o.out.size() && $.o.out.css("display") !== "none") {
                $.o.out.fadeOut("fast", function() {
                    if ($.o.s.protect && (!$.o.ele.hasClass("wrap_remind") || $.o.ele.attr("id"))) {
                        $.o.ele.hide().appendTo($("body"));
                    }
                    $(this).remove();
                    if ($.isFunction($.o.s.onclose)) {
                        $.o.s.onclose();
                    }
                });
                if ($.o.bg.size()) {
                    $.o.bg.fadeOut("fast", function() {
                        $(this).remove();
                    });
                }
            }
            return false;
        },
        //拖拽
        drag: function() {
            if (!$.o.out.size() || !$.o.bar.size()) {
                $(document).unbind("mouseover").unbind("mouseup");
                return;
            }
            var bar = $.o.bar, out = $.o.out;
            var drag = false;
            var currentX = 0, currentY = 0, posX = out.css("left"), posY = out.css("top");
            bar.mousedown(function(e) {
                drag = true;
                currentX = e.pageX;
                currentY = e.pageY;
            }).css("cursor", "move");
            $(document).mousemove(function(e) {
                if (drag) {
                    var nowX = e.pageX, nowY = e.pageY;
                    var disX = nowX - currentX, disY = nowY - currentY;
                    out.css("left", parseInt(posX) + disX).css("top", parseInt(posY) + disY);
                }
            });
            $(document).mouseup(function() {
                drag = false;
                posX = out.css("left");
                posY = out.css("top");
            });
        },
        //预载
        loading: function() {
            var element = $('<div class="wrap_remind"><img src="' + imgs + '"></div>');
            $.cxhd(element, {
                bar: true,
                buttonStatus: false,
                opacity: 1
            });
        },
        //confirm询问方法
        confirm: function(options) {
            var element;
            if (typeof options === "string") {
                element = $('<div class="wrap_remind">' + options + '<p></p></div>');
                options = null;
            } else if (typeof options === "object") {
                if (options.message) {
                    element = $('<div class="wrap_remind">' + options.message[options.langBase || langBase] + '<p></p></div>');
                } else {
                    return;
                }
            } else {
                return;
            }
            $.cxhd(element, options);
            //回调方法
            $("#zxxSureBtn").click(function() {
                if ($.isFunction($.o.s.sureCall)) {
                    $.o.s.sureCall.call(this);
                }
                if (!options.display) {
                    $.cxhd.hide();
                }
                return true;
            });
            $("#zxxCancelBtn").click(function() {
                if ($.o.s.cancelCall && $.isFunction($.o.s.cancelCall)) {
                    $.o.s.cancelCall.call(this);
                }
                $.cxhd.hide();
                return false;
            });
        },
        //remind提醒方法
        alert: function(options) {
            var element;
            if (typeof options === "string") {
                element = $('<div class="wrap_remind">' + options + '<p></p></div>');
                options = null;
            } else if (typeof options === "object") {
                if (options.message) {
                    element = $('<div class="wrap_remind">' + options.message[options.langBase] + '<p></p></div>');
                } else {
                    return;
                }
            } else {
                return;
            }
            $.cxhd(element, options);
            $(".cancel_btn").hide();
            $("#zxxSureBtn").click(function() {
                //回调方法               
                if ($.o.s.sureCall && $.isFunction($.o.s.sureCall)) {
                    $.o.s.sureCall.call(this);
                }
                $.cxhd.hide();
            });
        },
        prompt: function(options) {
            var element, html;

            html = ' <table width="100%"><tr><td valign="bottom" align="right"><img src="images/ext/pen.png" width="60px" height="60px"></td><td valign="bottom" align="left"><input id="warp_text" type="text"></td></tr></table>';
            element = $('<div class="wrap_remind">' + html + '<p></p></div>');

            $.cxhd(element, options);

            $("#zxxSureBtn").click(function() {
                if ($.isFunction($.o.s.sureCall)) {
                    $.o.s.sureCall.call(this, $("#warp_text").val());
                }
                $.cxhd.hide();
            });
            $("#zxxCancelBtn").click(function() {
                if ($.o.s.cancelCall && $.isFunction($.o.s.cancelCall)) {
                    $.o.s.cancelCall.call(this);
                }
                $("#warp_text").attr("value", "");
                $.cxhd.hide();
                return false;
            });

        },
        delayAlert: function(options) {
            var element;
            if (typeof options === "string"){
                element = $('<div class="wrap_remind">' + options + '<p></p></div>'), options = null;
            }
            if (typeof options === "object") {
                if (typeof options.message === "string") {

                    element = $('<div class="wrap_remind">' + options.message + '<p></p></div>');
                } else {
                    element = $('<div class="wrap_remind">' + options.message[options.langBase] + '<p></p></div>');
                }
            }
            $.cxhd(element, options);
            $.cxhd.buttonHide();
        },
        //uri Ajax方法
        ajax: function(uri, params, options) {
            if (uri) {
                $.cxhd.loading();
                options = options || {};
                options.protect = false;
                $.ajax({
                    url: uri,
                    data: params || {},
                    success: function(html, other) {
                        $.cxhd(html, options);
                    },
                    error: function() {
                        $.cxhd.alert("加载出了点问题。");
                    }
                });
            }
        }
    });
    var cxhdDefault = {
        title: {
            chinese: "对话框",
            english: "Dialog"
        },
        shut: "×",
        index: 2000,
        opacity: 1.0,
        width: "350",
        height: "100",
        bar: true, //是否显示标题栏
        bg: true, //是否显示半透明背景
        btnclose: true, //是否显示关闭按钮
        buttonStatus: true, //是否显示button	
        fix: false, //是否弹出框固定在页面上
        bgclose: false, //是否点击半透明背景隐藏弹出框
        drag: false, //是否可拖拽
        ajaxTagA: true, //是否a标签默认Ajax操作
        protect: "auto", //保护装载的内容
        onshow: $.noop, //弹窗显示后触发事件
        onclose: $.noop, //弹窗关闭后触发事件
        delay: 0, //弹窗打开后关闭的时间, 0和负值不触发
        langBase: "chinese",
        left: null, //位置
        top: null,
        right: null,
        bottom: null,
        message: null, //需要显示的信息
        sureCall: false, //确定之后执行的函数
        cancelCall: false  //取消之后执行的函数
    };
})(jQuery);
function steveAlert(i, time) {
    try {
        langBase = langBase ? langBase : "chinese";
    } catch (e) {
        langBase = "chinese";
    }

    var str = ["save", "del", "add", "load", "procesing", "sent", "none", "update"], chinese, english;
    $.map(str, function(s, n) {
        if (s == i) {
            i = n;
        }
    });
    chinese = i;
    if (i == 0)
        chinese = "保存成功!", english = "Saved!";
    if (i == 1)
        chinese = "删除成功!", english = "Delete!";
    if (i == 2)
        chinese = "新增成功!", english = "Add!";
    if (i == 3)
        chinese = "加载中...!", english = "Loading...!";
    if (i == 4)
        chinese = "执行中!", english = "procesing!";
    if (i == 5)
        chinese = "发送成功!", english = "Sent!";
    if (i == 6)
        chinese = "无数据!", english = "No Record Return!!";
    if (i == 7)
        chinese = "更新成功!", english = "Update!";
    if (i == 8)
        chinese = "更新失败!", english = "Fail!";
    if (i == 30)
        chinese = "缺少数据，无法处理！", english = "Missing required field, unable to process!";
    if (i == 31)
        chinese = "有问题,保存失败！", english = "Problem to save!";
    if (i == 32)
        chinese = "删除操作未能执行！", english = "Delete operation could not be executed!";
    if (i == 33)
        chinese = "只能删除空分组！", english = "You can only delete empty group!";
    if (i == 34)
        chinese = "该部门已存在！", english = "The department already exists!";
    if (i == 35)
        chinese = "对不起,您的环境不支持开启摄像头", english = "Sorry, your environment does not support open camera.";
    if (i == 36)
        chinese = "请输入密码", english = "Please enter a new password.";
    if (i == 37)
        chinese = "两次密码不一致，请重新输入", english = "The passwords you entered are not the same. Please re-enter your password.";
    if (i == 38)
        chinese = "该用户已存在，请重新输入", english = "The loginId you entered is exist.";
    if (i == 39)
        chinese = "请输入用户名", english = "Please type your login id.";
    if (i == 40)
        chinese = "该部门没有发布任务", english = "The Department did not release any task.";
    if (i == 41)
        chinese = "加载失败", english = "Failed to load";
    if (i == 42)
        chinese = "数据已建立", english = "Data has been established";
    if (i == 43)
        chinese = "提交失败", english = "Failed to submit";
    if (i == 44)
        chinese = "提交成功", english = "";


    $.cxhd.delayAlert({
        message: {
            chinese: chinese,
            english: english
        },
        langBase: langBase || "chinese",
        delay: time || 2000,
        opacity: 1
    });
}
function steveConfirm(i, sureCall, cancelCall, display) {
    var delays;
    try {
        langBase = langBase ? langBase : "chinese";
    } catch (e) {
        langBase = "chinese";
    }
    var str = ["save", "del", "reset", "download", "print", "sent", "procesing", "off", "update"], chinese, english;
    $.map(str, function(s, n) {
        if (s == i)
            i = n;
    });
    chinese = i;
    if (i == 0)
        chinese = "确定要保存吗？", english = "Sure to save?";
    if (i == 1)
        chinese = "确定要删除吗？", english = "Sure to delete?";
    if (i == 2)
        chinese = "确定要重置吗？", english = "Continue to Reset?";
    if (i == 3)
        chinese = "确定要下载吗？", english = "Sure to download?";
    if (i == 4)
        chinese = "确定要打印吗？", english = "Sure to print?";
    if (i == 5)
        chinese = "确定发送吗？!", english = "Sure to sent!";
    if (i == 6)
        chinese = "确定执行吗?", english = "Are you sure to proceed?";
    if (i == 7)
        chinese = "确定退出吗？", english = "Are you sure to log off?";
    if (i == 8)
        chinese = "确定更新吗？", english = "Are you sure to update?";
    if (i == 9)
        chinese = "确定提交吗？", english = "Are you sure to submit?";
    if (i == 10)
        chinese = "确定启用吗？", english = "Are you sure to use?";
    if (i == 11)
        chinese = "确定停用吗？", english = "Are you sure to stop?";
    if (i == 50)
        chinese = "当前数据会被覆盖，确定执行？", english = "Current data will be overwritten, Are you sure to submit?";
    if (i == 51)
        chinese = "是否上传个人头像？", english = "Do you want to upload your phone?";
    if (i == 52)
        chinese = "确定收款吗？", english = "？";

    $.cxhd.confirm({
        message: {
            chinese: chinese,
            english: english
        },
        sureCall: sureCall,
        cancelCall: cancelCall,
        langBase: langBase,
        buttonStatus: true,
        display: ((typeof(cancelCall)=="boolean")?cancelCall:false) || display || false
    });

}
Date.prototype.Format = function(fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
jQuery.fn.center = function() {
    this.css('position', 'fixed');
    this.css('top', (document.body.clientHeight - this.height()) / 2 + 'px');
    this.css('left', (document.body.clientWidth - this.width()) / 2 + 'px');

    return this;
}