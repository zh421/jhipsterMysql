package aiot.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import aiot.java.type.ZoneType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

    /**
     * 日期時區轉換器
     *
     * @param instant
     *            日期
     * @param zoneId_1
     *            來源時區
     * @param zoneId_2
     *            目的時區
     * @param convertFormat
     *            轉換格式
     * @return 轉換後字串
     */
    public static String convertZone(Instant instant, ZoneId zoneId_1, ZoneId zoneId_2, String convertFormat) {
        ZonedDateTime time1 = ZonedDateTime.ofInstant(instant, zoneId_1);
        ZonedDateTime time2 = time1.withZoneSameInstant(zoneId_2);
        return DateTimeFormatter.ofPattern(convertFormat).format(time2);
    }

    /**
     * 日期格式轉換器
     *
     * @param instant
     *            日期
     * @param convertFormat
     *            轉換格式
     * @return 轉換後字串
     */
    public static String convertDate(Instant instant, String convertFormat) {
        Date date = Date.from(instant);
        return new SimpleDateFormat(convertFormat).format(date);
    }

    /**
     * 日期格式轉換器
     *
     * @param date
     *            日期
     * @param convertFormat
     *            轉換格式
     * @return 轉換後字串
     */
    public static String convertDate(Date date, String convertFormat) {
        return new SimpleDateFormat(convertFormat).format(date);
    }

    /**
     * 取得當前日期
     *
     * @param convertFormat
     *            轉換格式
     * @return 當前日期
     */
    public static String getDate(String convertFormat) {
        Date date = new Date();
        return new SimpleDateFormat(convertFormat).format(date);
    }

    /**
     * 日期格式轉換器
     *
     * @param date
     *            日期
     * @param convertFormat
     *            轉換格式
     * @return 轉換後字串
     */
    public static Instant convertInstant(Date date, String convertFormat) {
        if (null == date)
            return null;
        String datetime = convertDate(date, convertFormat);
        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern(convertFormat).toFormatter()
                .withZone(ZoneType.TAIPEI.getZoneId()); /* <- needed time zone here */
        ZonedDateTime zdtOriginal = ZonedDateTime.parse(datetime, dtf);
        return zdtOriginal.toInstant();
    }

    /**
     * 日期格式轉換器
     *
     * @param date
     *            日期
     * @param convertFormat
     *            轉換格式
     * @param zoneType
     *            轉換時區
     * @return 轉換後字串
     */
    public static Instant convertInstant(Date date, String convertFormat, ZoneType zoneType) {
        if (null == date)
            return null;
        String datetime = convertDate(date, convertFormat);
        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern(convertFormat).toFormatter()
                .withZone(zoneType.getZoneId()); /* <- needed time zone here */
        ZonedDateTime zdtOriginal = ZonedDateTime.parse(datetime, dtf);
        return zdtOriginal.toInstant();
    }

    /**
     * 日期格式轉換器
     *
     * @param dateTime
     *            日期
     * @param convertFormat
     *            轉換格式
     * @return 轉換後字串
     */
    @Deprecated
    public static Instant convertInstant(String dateTime, String convertFormat) {
        if (null == dateTime)
            return null;
        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern(convertFormat)
                .parseDefaulting(ChronoField.NANO_OF_DAY, 0).toFormatter()
                .withZone(ZoneId.of("GMT") /* <- needed time zone here */);
        ZonedDateTime zdtOriginal = ZonedDateTime.parse(dateTime, dtf);
        return zdtOriginal.toInstant();
    }

    /**
     * 日期格式轉換器
     *
     * @param date
     *            日期
     * @param convertFormat
     *            轉換格式
     * @return 轉換後字串
     */
    public static Instant convertDateInstant(String date, String convertFormat, ZoneType zoneType) {
        if (null == date)
            return null;
        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern(convertFormat)
                .parseDefaulting(ChronoField.NANO_OF_DAY, 0).toFormatter().withZone(zoneType.getZoneId());
        ZonedDateTime zdtOriginal = ZonedDateTime.parse(date, dtf);
        return zdtOriginal.toInstant();
    }

    /**
     * 日期時間格式轉換器
     *
     * @param dateTime
     *            日期時間
     * @param convertFormat
     *            轉換格式
     * @return 轉換後字串
     */
    public static Instant convertDateTimeInstant(String dateTime, String convertFormat, ZoneType zoneType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(convertFormat);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneType.getZoneId());
        return Instant.from(zonedDateTime);
    }

    /**
     * 取得當前日期前一月
     *
     * @param convertFormat
     *            轉換格式
     * @return 前月份日期
     */
    public static String getBeforeMonDate(String convertFormat) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(convertFormat);
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);

        return sdFormat.format(calendar.getTime());
    }

    public static boolean isDate(String dateString, String format) {
        boolean retValue = false;
        if (dateString != null && dateString.length() == format.length()) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatter.setLenient(false);
            try {
                formatter.parse(dateString);
                retValue = true;
            } catch (ParseException ignored) {
            }
        }
        return retValue;
    }

    public static Long getDiffSecond(ZonedDateTime start, ZonedDateTime end) {
        long el = end.toInstant().getEpochSecond();
        long sl = start.toInstant().getEpochSecond();
        return el - sl;
    }

    /**
     *
     * @param strTime
     *            2020-11-14 03:01:07
     * @return
     *            0109-11-14 03:01:07
     *使用於資料的通報時間以及各通報作業表頭的開始時間以及結束時間
     */
    public static String toTwTime(String strTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // log.info("\t來的時間是:{}", strTime);
            Date date = sdf.parse(strTime);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(date.getTime());
            int year = c.get(Calendar.YEAR) - 1911;
            // System.out.print("民國" + year + "年");
            int month = c.get(Calendar.MONTH) + 1;
            // System.out.print(month + "月");
            int day = c.get(Calendar.DAY_OF_MONTH);
            // System.out.println(day + "日");
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
            int ss = c.get(Calendar.SECOND);

            StringBuilder strb = new StringBuilder();
            strb.append(String.format("%04d", year)).append("-").append(String.format("%02d", month)).append("-")
                .append(String.format("%02d", day));
            strb.append(" ");
            strb.append(String.format("%02d", hh)).append(":").append(String.format("%02d", mm)).append(":")
                .append(String.format("%02d", ss));

            // log.info("\t轉換後的時間是:{}", strb.toString());
            return strb.toString();

        } catch (ParseException e) {
//            log.error(ExceptionUtils.getStackTrace(e));
            return strTime;
        }

    }
}
