/*start*/
window.onerror = function (errMsg, scriptURI, lineNumber, columnNumber, errorObj) {
    setTimeout(function () {
        var rst = {
            "错误信息：": errMsg,
            "出错文件：": scriptURI,
            "出错行号：": lineNumber,
            "出错列号：": columnNumber,
            "错误详情：": errorObj
        };

        alert('出错了，下一步将显示错误信息');
        alert(JSON.stringify(rst, null, 10));
    });
};

document.querySelector('input').addEventListener('change', function () {
    var that = this;

    lrz(that.files[0], {
        //width: 800
    })
        .then(function (rst) {
			console.log(rst,rst.base64);
            var img = new Image(),
                div = document.createElement('div'),
                p = document.createElement('p'),
                sourceSize = toFixed2(that.files[0].size / 1024),
                resultSize = toFixed2(rst.fileLen / 1024),
                scale = parseInt(100 - (resultSize / sourceSize * 10));

            p.style.fontSize = 13 + 'px';
            p.innerHTML      = '源文件：<span class="text-danger">' +
                sourceSize + 'KB' +
                '</span> <br />' +
                '压缩后传输大小：<span class="text-success">' +
                resultSize + 'KB (省' + scale + '%)' +
                '</span> ';

            div.className = 'col-sm-6';
            div.appendChild(img);
            div.appendChild(p);

            img.onload = function () {
                document.querySelector('#upload-container').appendChild(div);
            };

            img.src = rst.base64;

            /*            /!* ==================================================== *!/
             // 原生ajax上传代码，所以看起来特别多 ╮(╯_╰)╭，但绝对能用
             // 其他框架，例如ajax处理formData略有不同，请自行google，baidu。
             var xhr = new XMLHttpRequest();
             xhr.open('POST', '/upload');

             xhr.onload = function () {
             if (xhr.status === 200) {
             // 上传成功
             } else {
             // 处理其他情况
             }
             };

             xhr.onerror = function () {
             // 处理错误
             };

             // issues #45 提到似乎有兼容性问题,关于progress
             xhr.upload.onprogress = function (e) {
             // 上传进度
             var percentComplete = ((e.loaded / e.total) || 0) * 100;
             };

             // 添加参数和触发上传
             rst.formData.append('a', '我是参数');
             xhr.send(rst.formData);
             /!* ==================================================== *!/*/

            return rst;
        });
});

function toFixed2 (num) {
    return parseFloat(+num.toFixed(2));
}

/*end*/









/**
 *
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 */
