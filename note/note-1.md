https://developer.mozilla.org/zh-CN/docs/Mozilla

内网穿透(https://natapp.cn/、https://github.com/fatedier/frp)
https://developer.mozilla.org/zh-CN/
https://developer.mozilla.org/zh-CN/docs/Web/HTTP
HTTP response status codes
200(表明请求已经成功. 默认情况下状态码为200的响应可以被缓存。
不同请求方式对于请求成功的意义如下:
GET: 已经取得资源，并将资源添加到响应的消息体中。
HEAD: 响应的消息体为头部信息。
POST: 响应的消息体中包含此次请求的结果。
TRACE: 响应的消息体中包含服务器接收到的请求信息。
PUT 和 DELETE 的请求成功通常并不是响应200 OK的状态码而是 204 No Content 表示无内容(或者  201  Created表示一个资源首次被创建成功)。
)
400(表示由于语法无效，服务器无法理解该请求。 客户端不应该在未经修改的情况下重复此请求。)
401(代表客户端错误，指的是由于缺乏目标资源要求的身份验证凭证，发送的请求未得到满足。)
403(代表客户端错误，指的是服务器端有能力处理该请求，但是拒绝授权访问。)
404(代表客户端错误，指的是服务器端无法找到所请求的资源。返回该响应的链接通常称为坏链（broken link）或死链（dead link），它们会导向链接出错处理(link rot)页面。)
500(是表示服务器端错误的响应状态码，意味着所请求的服务器遇到意外的情况并阻止其执行请求。)
503(是一种HTTP协议的服务器端错误状态代码，它表示服务器尚未处于可以接受请求的状态。)




function getDateTime() {
	var seperator = "";
    var date = new Date();
	var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    var currentdate = year + seperator + month + seperator +  day;
    return currentdate;
}