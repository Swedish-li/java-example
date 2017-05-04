package com.lrs.time;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * Joda-time参考文章：http://www.ibm.com/developerworks/cn/java/j-jodatime.html
 * 
 * @author Swedish-li
 *
 */
public class TestJodaTime {
	@Test
	public void testDateTimeInit() {
		DateTime dateTime = new DateTime();
		print(dateTime);
		DateTime dateTime2 = new DateTime(2016// year
				, 7// monthOfYear
				, 18// dayOfMonth
				, 20 // hourOfDay
				, 30 // minuteOfHour
				, 30// secondOfMinute
				, 200// millisOfSecond, zone
		);
		// 2016-07-18T20:30:30.200+08:00
		print(dateTime2);
		// 使用 java.util中的date
		DateTime dateTime3 = new DateTime(new Date());
		Date date = dateTime3.toDate();
		// 2017-02-10T14:08:46.441+08:00
		print(dateTime3);
		// Fri Feb 10 14:08:46 CST 2017
		print(date);
		// 使用Calendar
		Calendar calendar = Calendar.getInstance();
		DateTime dateTime4 = new DateTime(calendar);
		print(calendar);
		print(dateTime4);
		// DateTime是不变的，可以使用dateTime实例，生成一个新的DateTime实例
		// 使用标准格式时间字符串生成DateTime
		// 2016-01-27T03:30:00.000+08:00
		String formatStr = "2016-01-26T13:30:00-06:00";
		DateTime dateTime5 = new DateTime(formatStr);
		print(dateTime5);
		String yearMonthDay = "2006-07-18";
		DateTime dateTime6 = new DateTime(yearMonthDay);
		// 2006-07-18T00:00:00.000+08:00
		print(dateTime6);

	}

	// 计算11月中第一个星期一之后的第一个星期二：
	@Test
	public void example1() {
		LocalDate now = new LocalDate();
		LocalDate electionDate = now.monthOfYear()
				// 设置月份
				.setCopy(10)
				//
				.dayOfMonth().withMaximumValue()
				// 加6天
				.plusDays(6).dayOfWeek()
				// Monday 不存在 1,周一
				.setCopy("1").plusDays(1);
		print(electionDate);
	}

	@Test
	public void testFormatDateTime() {
		DateTime dateTime = new DateTime();
		// 02/10/2017 02:47:41.725下午
		print(dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
		// 10-02-2017 14:47:41
		print(dateTime.toString("dd-MM-yyyy HH:mm:ss"));
		// 星期五 10 二月, 2017 14:47:41 下午
		print(dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ss a"));
		// 02/10/2017 14:47 Asia/Shanghai
		print(dateTime.toString("MM/dd/yyyy HH:mm ZZZZ"));
		// 02/10/2017 14:47 +0800
		print(dateTime.toString("MM/dd/yyyy HH:mm Z"));
	}

	private void print(Object ob) {
		System.out.println(ob);
	}
}
