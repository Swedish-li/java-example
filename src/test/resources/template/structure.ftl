<#-- 
	模板(ftl)组成：
	Text ： 文本，原样输出
	Interpolation : 插值，使用计算的值来替换，${user}
	FTL tags ： FTL标签，类似HTML标签
	Comments注释 ：会被Freemarker忽略
-->


${html?html}
${cap_first?cap_first}
${lower_case?lower_case}
${upper_case?upper_case}
${trim?trim}
${size?size}
${int?int}

<#noparse>
	<#-- Freemark不会在 noparse中间查找ftl标签 -->
	${html}
	${trim?html}
</#noparse>
<#-- 移除多余空白 -->
<#compress>
		<html>
			<head>
				<title> this is a title</title>
			</head>
			
			<body>
				<h1>
					<h2>
						<p>这个   是段落</p>
					</h2>
				</h1>
			</body>
		</html>
</#compress>

<#escape x as x?upper_case>
	User name: ${userName}
	City name: ${cityName}
	Country name: ${countryName}
</#escape>>

<#assign ex = "<html>html label</html>">

<#macro m1>
  m1: ${ex}
</#macro>
<#-- escape命令的作用是在模板的解析时间而不是模板处理的时间 -->
<#escape x as x?html>
	<#macro m2>
		m2 ： ${ex}
	</#macro>>
	<#noescape>
		在此之间关闭转义：${ex}
	</#noescape>
	${ex}
	<@m1 />
</#escape>

${ex}

<@m2/>

