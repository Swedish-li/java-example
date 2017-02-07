package com.lrs.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Freemarker 模板信息
 * 
 * @author Swedish-li
 *
 */
public class Template {
	private Integer id;
	
	private String statusKey;
	/** 模板内容  */
	private String template;
	/** 最后更新时间  */
	private Date lastModified;
	/** 语言版本  */
	private String lang;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getStatusKey() {
		return statusKey;
	}

	public void setStatusKey(String statusKey) {
		this.statusKey = statusKey;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
