package com.lrs.common.view;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 * Poi官方文档：http://poi.apache.org/spreadsheet/quick-guide.html
 * 
 * @author liruishi
 *
 */
public class ExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 从数据模型model上获取数据，渲染到Excel中,文件名
		// 表头 (head,数组）
		// 表头对应属性（field，数组）
		// 表格内容(list<Map<String,Object>),
		// 每个sheet不超过50000条数据
		Sheet sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth(12);

		for (int i = 0; i < 1000; i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j < 50; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue("测试数据" + i + " " + j);
			}
		}
		String fileName = "测试文件测试文件测试文件测试文件测试文件测试文件测试文件测试文件测试文件测试文";
		response.setHeader("Content-disposition", "attachment;filename=" + encodeFilename(fileName, request) + ".xls");
	}

	/**
	 * 设置下载文件中文件的名称
	 * 
	 * @param filename
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeFilename(String filename, HttpServletRequest request)
			throws UnsupportedEncodingException {
		// new String(fileName.getBytes("utf-8"), "ISO-8859-1")
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		// ie浏览器
		if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
			String newFileName = URLEncoder.encode(filename, "UTF-8");
			newFileName = StringUtils.replace(newFileName, "+", "%20");
			/*
			 * if (newFileName.length() > 150) { newFileName = new
			 * String(filename.getBytes("UTF-8"), "ISO8859-1"); newFileName =
			 * StringUtils.replace(newFileName, " ", "%20"); }
			 */
			return newFileName;
		}
		// if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
		// return MimeUtility.encodeText(filename, "UTF-8", "B");

		return URLEncoder.encode(filename, "UTF8");

	}

}
