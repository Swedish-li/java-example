package com.lrs.common.decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 数字格式化
 * 
 * @author Swedish-li
 *
 */
public class TestDecimalFormat {

	private DecimalFormat getDecimalFormat(String pattern) {
		DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
		format.applyPattern(pattern);
		return format;
	}

	private DecimalFormat getDefault() {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		return df;
	}

	@Test
	// 系统默认小数位数为3
	public void testScale() {
		DecimalFormat df = getDefault();
		BigDecimal decimal = new BigDecimal("1.382243201");
		// default
		assertEquals("1.382", df.format(decimal));
		df.setMaximumFractionDigits(5);
		assertEquals("1.38224", df.format(decimal));
	}

	// 将数字转化为百分比输出
	@Test
	public void testPercentPattern() {
		DecimalFormat df = getDecimalFormat("##.##%");
		BigDecimal decimal = new BigDecimal("0.135689");

		assertEquals("13.57%", df.format(decimal));

		// 添加千分号
		df.applyPattern(".##\u2030");
		assertEquals("13.57‰", df.format(decimal));
	}

	@Test
	public void testGroup() {
		DecimalFormat df = getDefault();
		df.setGroupingSize(5);
		df.setMaximumFractionDigits(10);
		BigDecimal decimal = new BigDecimal("1680398872.2472002");

		assertEquals("16803,98872.2472002", df.format(decimal));
		// 禁用分组
		df.setGroupingUsed(false);
		assertEquals("1680398872.2472002", df.format(decimal));

	}

	@Test
	// 设置小数为必须为2位
	public void testPattern2() {
		// 保留2位小数，如果小数点后面不够2位小数会补零
		DecimalFormat df = getDecimalFormat(".00");
		BigDecimal decimal = new BigDecimal("1.2");

		assertEquals("1.20", df.format(decimal));
	}

	@Test
	// 即保留2位小数
	public void testPatter3() {
		DecimalFormat df = getDecimalFormat(".##");
		// 说明：如果小数点后面不够2位小数，不会补零.参见Rounding小节
		df.setRoundingMode(RoundingMode.HALF_UP);

		BigDecimal decimal1 = new BigDecimal("1.5");
		BigDecimal decimal2 = new BigDecimal("1.578");

		assertEquals("1.5", df.format(decimal1));
		assertEquals("1.58", df.format(decimal2));
	}

	@Test
	// 添加前、后修饰字符串，记得要用单引号括起来
	public void testPattern4() {
		DecimalFormat df = getDecimalFormat("'这是人民币'##.##'人民币'");
		BigDecimal decimal = new BigDecimal("1000.289");

		assertEquals("这是人民币1000.29人民币", df.format(decimal));
	}

	// 定义正负数模板,记得要用分号隔开
	@Test
	public void testPattern5() {
		DecimalFormat df = getDecimalFormat("0.0;'@'-#.0");
		assertEquals("10.3", df.format(10.321));
		assertEquals("@-10.3", df.format(-10.321));

		String pattern = "'my moneny'###,###.##'RMB';'ur money'###,###.##'US'";
		df.applyPattern(pattern);
		
		assertEquals("my moneny1,223,233.46RMB", df.format(1223233.456));
		assertEquals("ur money1,223,233.46US", df.format(-1223233.456));
	}

}
