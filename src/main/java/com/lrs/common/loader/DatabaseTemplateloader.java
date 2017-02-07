package com.lrs.common.loader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import freemarker.cache.TemplateLoader;

public class DatabaseTemplateloader implements TemplateLoader {
	private long lastModified;
	{
		lastModified = System.currentTimeMillis();
	}

	/**
	 * 当修改模板信息时需要修改更新模板最后更新时间
	 * 
	 * @param lastModified
	 */
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		// TODO 从数据库中获取模板
		return null;
	}

	@Override
	public long getLastModified(Object templateSource) {
		return lastModified;
	}

	@Override
	public Reader getReader(Object templateSource, String encoding) throws IOException {
		return new StringReader("");
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		// 在从数据库中获取模板数据时，此处可以什么也不做

	}

}
