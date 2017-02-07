<#-- 标签测试模板    -->

<#if user??>
	<#if user=="jackie chen">
	I'm jackie chen
	<#else>
	I'm not jackie chen
	</#if>
</#if>

<#if price??>
	<#-- 大于号的使用方式  -->
	<#--  用符号代替，> gt, >=  gte ,< lt , <= lte  -->
	<#if (price gt 100)>
	价格太高了
	<#elseif price gte 50>
	价格适中
	<#else>
	价格过低
	</#if>
</#if>
<#if users??>
	<#list users as item>
		<h1>${item}</h1>
	</#list>
	
</#if>

<#if fruits??>
	<#list fruits as item>
		<h2>${item}<h2>
	</#list>
</#if>

<#-- include标签  -->

<#include "/inc.ftl">


<#-- 处理不存在的变量 -->
<#-- 使用默认值来避免变量丢失  -->

<h1>Welcome ${user!"Anonymous"}!<h1>
<#--变量不存在忽略 -->
<#if user??><h1>Welcome ${user}!</h1></#if>