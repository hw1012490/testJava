package com.example.demo.sms.tool;


import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {

  public static final String DATA_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

  public static final String DATA_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

  public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
  public static final String DATE_FORMAT_GMT = "EEE,d MMM yyyy HH:mm:ss 'GMT'";
  public static final String GMT = "GMT";


  public static String formatDateGMT() {
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_GMT, Locale.ENGLISH);
    sdf.setTimeZone(TimeZone.getTimeZone(GMT));
    return sdf.format(Calendar.getInstance().getTime());
  }

  /**
   * 转换时间为带时区的时间
   *
   * @param date 时间
   * @return 带时区的时间
   */
  public static String formatDateUTC(Date date) {

    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_UTC);
    return sdf.format(date);
  }

  /**
   * 获取当前的时间 [UTC].
   */
  public static String getCurrentUTC() {

    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_UTC);
    return sdf.format(new Date());
  }

  /**
   * 获取当前的时间 [UTC].
   */
  public static String getCurrentUTCOfYMDHMS() {

    SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT_yyyy_MM_dd_HH_mm_ss);
    return sdf.format(new Date());
  }

  public static Long diffTime(String source1, String source2) {
    Long diffTime = 0l;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT_yyyy_MM_dd_HH_mm_ss);

      Date source1Date = dateFormat.parse(source1);

      Date source2Date = dateFormat.parse(source2);
      diffTime = (source2Date.getTime() - source1Date.getTime()) / 1000;
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      return diffTime;
    }
  }

  public static String formatWithTimestamp(String timestamp) {
    Date date = new Date(Long.parseLong(String.valueOf(timestamp)));
    SimpleDateFormat targetFormat = new SimpleDateFormat(DATA_FORMAT_yyyy_MM_dd_HH_mm_ss);
    String formatTime = targetFormat.format(date);
    return formatTime;
  }

  public static String format(String source) {
    return format(source, DATE_FORMAT_UTC, null);
  }


  public static String format(String source, String pattern, String timeZone) {
    String formatTime = null;
    try {
      SimpleDateFormat sourceFormat = new SimpleDateFormat(pattern);
      if (!StringUtils.isEmpty(timeZone)) {
        sourceFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
      }
      Date sourceDate = sourceFormat.parse(source);

      SimpleDateFormat targetFormat = new SimpleDateFormat(DATA_FORMAT_yyyy_MM_dd_HH_mm_ss);
      formatTime = targetFormat.format(sourceDate);
    } catch (ParseException e) {
    } finally {
      return formatTime;
    }
  }


  /**
   * 想要获取的日期与传入日期的差值
   *
   * 比如想要获取传入日期前四天的日期 day=-4
   */
  public static String getSomeDay(Date date, int day) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, day);

    SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT_yyyy_MM_dd);
    return sdf.format(calendar.getTime());
  }

}
