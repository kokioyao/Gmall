package cn.footman.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

	public static void main(String[] args) {

	}

	public static Date getMyDate(int i) {
		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, i);

		return c.getTime();
	}

	public static String getMyDateString(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		String str = sdf.format(new Date());
		return str;
	}

}
