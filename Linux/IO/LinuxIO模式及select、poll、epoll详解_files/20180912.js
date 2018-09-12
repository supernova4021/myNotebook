/**
 * Created by sf on 2018/8/3.
 */
var areaArr = []

var trackHost = 'https://segmentfault.com';
var viewApi = trackHost + '/ad/track/view'
var clickApi = trackHost + '/ad/track/click'

function ajax(opt) {
    opt = opt || {};//opt以数组方式存参，如果参数不存在就为空。
    opt.method = opt.method.toUpperCase() || 'POST';//转为大写失败，就转为POST
    opt.url = opt.url || '';//检查URL是否为空
    opt.async = opt.async || true;//是否异步
    opt.data = opt.data || null;//是否发送参数，如POST、GET发送参数
    opt.success = opt.success || function () {}; //成功后处理方式
    var xmlHttp = null;//定义1个空变量
    if (XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();//如果XMLHttpRequest存在就新建，IE大于9&&非IE有效
    }
    else {
        xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');//用于低版本IE
    }
    var params = [];//定义1个空数组
    for (var key in opt.data){
        params.push(key + '=' + opt.data[key]);//将opt里的data存到push里
    }
    var postData = params.join('&');//追加个& params
    if (opt.method.toUpperCase() === 'POST') {
        xmlHttp.open(opt.method, opt.url, opt.async);//开始请求
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');//发送头信息，与表单里的一样。
        xmlHttp.send(postData);//发送POST数据
    }
    else if (opt.method.toUpperCase() === 'GET') {
        xmlHttp.open(opt.method, opt.url, opt.async);//GET请求
        xmlHttp.send(null);//发送空数据
    }
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {//readyState有5个状态，可以百度一下看看都有什么。当请求完成，并且返回数据成功
            opt.success(xmlHttp.responseText);//返回成功的数据
        }
    };
}

var sTitle="";
window.d = function(o) {
    sTitle = o.getAttribute('stitle');
    document.getElementById("gridMapHoverBox").style.display = "block"
}

window.e = function(o) {
    sTitle = "";
    document.getElementById("gridMapHoverBox").style.display = "none"
}

window.c = function(id) {

    var clickApi2 = clickApi + '?id=' + id

    ajax({url: clickApi2, method: 'GET'})
};

// 这里 data 需要注入
[{"id":"1750000016197834","user_id":"1030000000091295","box_ad_id":"0","started":"1535558400","ended":"1536768000","cycles":"2","status":"0","banner":"\/412\/922\/4129223266-5b866ab553713","link":"https:\/\/segmentfault.com\/l\/1500000016299614?utm_source=grid_ad","title":"\u51ef\u5a01\u6559\u4f60\u5b66 Python","area_info":{"area":"A12:O15","height":"68","width":"255","left":"0","top":"187","pos":{"rowTop":12,"rowBottom":15,"columnLeft":1,"columnRight":15},"size":60},"created":"1535535745","modified":"1536648480"},{"id":"1750000016293298","user_id":"1030000016263412","box_ad_id":"0","started":"1536595200","ended":"1537200000","cycles":"1","status":"0","banner":"\/274\/450\/2744503121-5b95c9ba5791c","link":"https:\/\/www.3tee.cn\/index.html?source=segmentfault","title":"\u5b8c\u7f8e\u517c\u5bb9WebRTC\uff0c\u5feb\u901f\u5d4c\u5165\uff0c\u5927\u534e\u80a1\u4efd\u7b49\u90fd\u5728\u7528","area_info":{"area":"N1:O2","height":"34","width":"34","left":"221","top":"0","pos":{"rowTop":1,"rowBottom":2,"columnLeft":14,"columnRight":15},"size":4},"created":"1536211270","modified":"1536543171"},{"id":"1750000016325058","user_id":"1030000015588360","box_ad_id":"0","started":"1536508800","ended":"1537113600","cycles":"1","status":"0","banner":"\/132\/598\/1325985962-5b948452b86cd","link":"https:\/\/www.accesshub.cn","title":"\u70b9\u51fb\u51e0\u4e0b\uff0c\u5168\u7403\u7ec4\u7f51","area_info":{"area":"B4:C5","height":"34","width":"34","left":"17","top":"51","pos":{"rowTop":4,"rowBottom":5,"columnLeft":2,"columnRight":3},"size":4},"created":"1536459104","modified":"1536460047"}].forEach(function(item) {
    var html = '<area shape="rect" ' +
        'target="_blank" ' +
        'onmouseover="d(this)" ' +
        'onmouseout="e(this)" ' +
        'onclick="c(' + item.id + ')" ' +
        'coords="' + item.area_info.left + ',' + item.area_info.top + ',' + ((+item.area_info.left)+(+item.area_info.width)) + ',' + ((+item.area_info.top)+(+item.area_info.height)) + '" ' +
        'href="' + item.link + '" ' +
        'stitle="' + item.title + '" />'

    areaArr.push(html)
})

document.getElementById('gridsMap').innerHTML = areaArr.join('')

document.getElementById('gridsMap').onmousemove = function(e) {
    var pos = getMousePos(e)

    document.getElementById("gridMapHoverBox").style.left = pos.x + 20 + 'px'
    document.getElementById("gridMapHoverBox").style.top = pos.y + 20 + 'px'

    document.getElementById("gridMapHoverBox").innerHTML = sTitle
}

// 增加 view 统计
setTimeout(function() {
    isShow = document.querySelector('img[src^="https://static.segmentfault.com/sponsor"]').clientHeight > 0
    if (isShow) ajax({url: viewApi, method: 'GET'})
}, 0)

function getMousePos(event) {
    var e = event || window.event;
    var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
    var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
    var x = e.pageX || e.clientX + scrollX;
    var y = e.pageY || e.clientY + scrollY;
    return { 'x': x, 'y': y };
}