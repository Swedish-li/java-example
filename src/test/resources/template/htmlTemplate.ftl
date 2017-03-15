<#macro htmlTemplate subTitle>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<!-- meta -->
	<title>Html template | ${subTitle}</title>
	<!-- 样式表 -->	
</head>
<body>
	<#nested>
	<!-- js脚本 -->
</body>
</html>

</#macro>

<@htmlTemplate "sub title">
	<h1>主标题</h1>
	<h2>副标题</h2>
	<p>这是一个很长的段落</p>
</@htmlTemplate>