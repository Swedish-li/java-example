<#macro greet>
	<font size="+2">Hello Jackie</font>
	<#include "inc.ftl">
</#macro>
<#-- 调用名称，参数 -->
<#macro greet1 person>
	<font size="+2"> Hello ${person}</font>
</#macro>
<#-- 宏调用 -->
<@greet></@greet>

<@greet1 person="Mike li"></@greet1>

<#-- 嵌套内容 -->

<#macro nest>
	<table>
		<tr>
			<#nested>
		</tr>
	</table>
</#macro>

<@nest>
	this is nest content
</@nest>

<#-- 多次调用nest -->

<#macro do_thrice>
	<#nested>
	<#nested>
	<#nested>
</#macro>

<@do_thrice>
	do something in this;
</@do_thrice>

<@nest>
	<@do_thrice>
		do Something
	</@do_thrice>
</@nest>


<#macro repeat count>
<#list 1..count as x>
<#nested x, x/2, x==count>
</#list>
</#macro>


<@repeat count=4 ; c, halfc, last>
${c}. ${halfc}<#if last> Last!</#if>
</@repeat>