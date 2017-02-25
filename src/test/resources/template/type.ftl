<#-- Date数据格式化化  -->
<#assign currentDate = date?datetime>
<#-- 预置日期格式化  date,datetime,time 后边也可以加上格式-->
默认显示： ${currentDate} 
date: ${currentDate?date}
datetime: ${currentDate?datetime}
time: ${currentDate?time}

string.short: ${currentDate?string.short}
string.medium: ${currentDate?string.medium}
string.long: ${currentDate?string.long}
string.full: ${currentDate?string.full}
string.xs: ${currentDate?string.xs}
string.iso: ${currentDate?string.iso}

<#-- 转换为指定格式 -->
date pattern(yyyy-MM-dd HH:mm:ss): ${currentDate?string('yyyy-MM-dd HH:mm:ss')}
date pattern(yyyy-MM-dd hh:mm:ss:SSS): ${currentDate?string('yyyy-MM-dd hh:mm:ss:SSS')}

