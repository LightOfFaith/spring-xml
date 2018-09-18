<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div
		style="margin-left: 8px; margin-top: 8px; margin-bottom: 8px; margin-right: 8px;">
		<div>
			<span> </span> <br> <span> <font
				face="微软雅黑, Microsoft Yahei, Heiti" size="2">
					<h1
						style="font-size: 18px; font-family: '微软雅黑', 'Microsoft Yahei', 'Heiti'; margin-bottom: 30px;">
						尊敬的<span style="border-bottom: 1px dashed #ccc; z-index: 1" t="7"
							onclick="return false;" data="">系统</span>用户，
					</h1>
					<p style="font-family: '微软雅黑', 'Microsoft Yahei', 'Heiti';">
						您的系统应用异常告警被触发。详细异常信息，您可登录云服务器查看异常告警日志。</p>
					<h2
						style="font-size: 16px; font-family: '微软雅黑', 'Microsoft Yahei', 'Heiti'; margin-top: 20px;">告警详情</h2>
					<ul style="font-family: '微软雅黑', 'Microsoft Yahei', 'Heiti';">
						<li style="line-height: 1.5em;">项目：${exception.project}</li>
						<li style="line-height: 1.5em;">告警对象：${exception.instance}</li>
						<li style="line-height: 1.5em;">告警条件：${exception.condition}</li>
						<li style="line-height: 1.5em;">异常码值：${exception.code} <#if
								exception.subCode?? && exception.subCode !="">,${exception.subCode}</#if></li>
						<li style="line-height: 1.5em;">异常描述：${exception.msg} <#if
								exception.subMsg?? && exception.subMsg !="">,${exception.subMsg}</#if></li>
						<li style="line-height: 1.5em;">触发时间：<span
							style="border-bottom: 1px dashed #ccc;">${exception.timestamp}</span>
						</li>
					</ul>
			</font>
				<p
					style="color: #27303f; margin-top: 60px; margin-bottom: 10px; font-family: '微软雅黑', 'Microsoft Yahei', 'Heiti'">系统监控</p>

				<table border="0" width="100%" cellpadding="0" cellspacing="0"
					style="margin: 10px 0">
					<tbody>
						<tr>
							<td
								style="background: none; border-bottom: 1px dashed #d6d5e1; height: 1px; line-height: 0; width: 100%; margin: 0px 0px 0px 0px;">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<p
					style="color: gray; font-family: '微软雅黑', 'Microsoft Yahei', 'Heiti';">
					本邮件为系统推送，请不要直接回复。如有任何问题，请<a href="#" style="color: #0071ce;"
						rel="noopener" target="_blank">联系我们</a>获取帮助
				</p> <font></font>
			</span> <br> <span> </span>
		</div>
	</div>
</body>
</html>