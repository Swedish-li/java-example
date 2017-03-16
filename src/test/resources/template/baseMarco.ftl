<#-- 在基本宏定义中使用压缩指令 -->
<#-- Freemarker模块化编程： https://chulung.com/article/13 -->
<#compress>
	<#-- base 宏定义名称，base_title,base_keywords 页面标题，关键字 -->
	<#-- base_js,base_css 页面基本的js,css -->
	<#macro base base_title base_keywords="" base_js=[] base_css=[]>
		<!doctype html>
		<html lang="zh-CN">
		<head>
			<meta charset="UTF-8" />
			<title>我的网站 | ${base_title}</title>
			<!-- 公用样式表 -->
			<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css" />
			<#list base_css as css>
			<link rel="stylesheet" href="${ctx}${css}" />
			</#list>
		</head>
		<body>
			
			<div class="container">
				<h1>公共部分</h1>
			</div>
			<!-- 嵌套部分 -->
			<#nested />
			<div class="footer">
				<h1>公共页脚</h1>
			</div>
			<!-- 公用脚本 -->
			<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
			<!-- 引入脚本 -->
			<#list base_js as js>
			<script src="${ctx}${js}"></script>
			</#list>
		</body>
		</html>
	</#macro>
</#compress>