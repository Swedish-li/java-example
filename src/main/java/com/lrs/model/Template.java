package com.lrs.model;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Freemarker 模板信息
 * 
 * @author Swedish-li
 *
 */
@Data
@Accessors(chain = true)
public class Template {
	private Integer id;

	private String statusKey;
	/** 模板内容 */
	private String template;
	/** 最后更新时间 */
	private Date lastModified;
	/** 语言版本 */
	private String lang;

}
