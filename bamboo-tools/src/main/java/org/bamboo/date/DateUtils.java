package org.bamboo.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final String FORMAT_DATE_DAY = "yyyyMMdd";
	
	private static final String FORMAT_DATE_SEC = "yyyyMMddHHmmss";
	
	public static final long DAY_TO_MILLISECOND = 86400000L;

	public final static DateFormat sdfDay = new SimpleDateFormat(FORMAT_DATE_DAY);
	
	public final static DateFormat sdfSec = new SimpleDateFormat(FORMAT_DATE_SEC);
	
	/**
	 * 比较给定两个日期的差值，返回相差天数
	 * @param fromDateStr
	 * @param toDateStr
	 * @return
	 * @throws ParseException
	 */
	public static long compareDates(String fromDateStr, String toDateStr) throws ParseException {
		Date fromDate = sdfDay.parse(fromDateStr);
		Date toDate = sdfDay.parse(toDateStr);
		return (fromDate.getTime() - toDate.getTime()) / DAY_TO_MILLISECOND;
	}
	
	public static String addDay(String fromDateStr, int days) throws ParseException {
		if (days <= 0) {
			return fromDateStr;
		}
		Date fromDate = sdfDay.parse(fromDateStr);
		Calendar ca = Calendar.getInstance();
		ca.setTime(fromDate);
		ca.add(Calendar.DATE, days);
		Date toDate = ca.getTime();
		return sdfDay.format(toDate);
	}
}
