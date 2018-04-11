package com.lrs.common;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

//Calendar,Date
public class TestFastDateFormat {

    private final static Logger logger = LoggerFactory.getLogger(TestFastDateFormat.class);

    private final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final static String DATE_DEFAULT_PATTERN = "yyyy-MM-dd";

    private final static String TIME_DEFAULT_PATTERN = "HH:mm:ss";

    public FastDateFormat getDateFormat(String pattern) {
        return FastDateFormat.getInstance(pattern);
    }

    // Date=>String
    @Test
    public void date2Str() {
        long currentMillis = System.currentTimeMillis();
        logger.info("当前时间（毫秒）：{}", currentMillis);
        Date date = new Date(currentMillis);

        // 日期，时间
        FastDateFormat fdf = getDateFormat(DEFAULT_PATTERN);
        String datetimeStr = fdf.format(date);

        logger.info("时间(yyyy-MM-dd HH:mm:ss):{}", datetimeStr);

        // 年月日
        FastDateFormat dateFdf = getDateFormat(DATE_DEFAULT_PATTERN);

        String dateStr = dateFdf.format(date);
        logger.info("日期（yyyy-MM-dd）:{}", dateStr);

        // 时分秒
        FastDateFormat timeFdf = getDateFormat(TIME_DEFAULT_PATTERN);
        String timeStr = timeFdf.format(date);
        logger.info("时间(HH:mm:ss):{}", timeStr);

    }

    // China Standard Time UT+8:00 (CST)
    // String => date
    @Test
    public void str2Date() {
        String datetimeStr = "2016-10-01 08:10:05";
        String dateStr = "2016-10-01";
        String timeStr = "08:10:05";

        try {
            Date datetime = getDateFormat(DEFAULT_PATTERN).parse(datetimeStr);
            logger.info("Datetime:{}", datetime);
            Date date = getDateFormat(DATE_DEFAULT_PATTERN).parse(dateStr);
            logger.info("Date:{}", date);
            Date time = getDateFormat(TIME_DEFAULT_PATTERN).parse(timeStr);
            logger.info("Time:{}", time);
        } catch (ParseException e) {
            // 时间转换异常
            e.printStackTrace();
        }

    }

    // 使用StopWatch记录代码执行时间
    // DateUtil ：将时间字符串转换为Date
    // DateFormatUtil:将Date格式化为字符串
    @Test
    public void testDateUtil() {
        StopWatch watch = StopWatch.createStarted();
        String datetimeStr = "2016-10-01 08:10:05";
        Date date = null;
        try {
            date = DateUtils.parseDate(datetimeStr, DEFAULT_PATTERN);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        logger.info("Datetime:{}", date);
        String str = DateFormatUtils.format(date, DEFAULT_PATTERN);

        assertEquals(datetimeStr, str);
        watch.stop();
        long nanos = watch.getNanoTime();

        logger.info("执行时间（纳秒）：{}", nanos);

        long millis = watch.getTime();
        logger.info("执行时间（毫秒）：{}", millis);
        long seconds = watch.getTime(TimeUnit.SECONDS);
        logger.info("执行时间（秒）：{}", seconds);

    }
}
