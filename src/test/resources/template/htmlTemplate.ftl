<#include "baseMarco.ftl">
<#assign ctx="https://github.com/Swedish-li">
<@base base_title="Markdown编辑器" base_keywords="key words" base_css=["/css/markdown/markdown.css","/css/markdown/edit.css"] base_js=["/markdown/Markdown.js","/markdown/edit.js"]>
	<div class="layout">
		<div class="markdown-edit">
			<h3>这是一个markdown</h3>
		</div>
	</div>
</@base>

<!-- 宏嵌套 -->

<#macro page title keywords="" js=[] css=[]>
	<#include "baseMarco.ftl">
	<@base base_title=title base_keywords=keywords base_css=css base_js=js>
		<div class="page-layout">
			<!-- 此处放置嵌套内容  -->
			<#nested>
		</div>
	</@base>
</#macro>

<@page title="Blog" js=["/common/blog.js"] css=["/css/common/blog.css"]>
	<h1>这是Blog的主要内容</h1>
</@page>

