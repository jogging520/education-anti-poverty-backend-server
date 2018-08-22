package com.northbrain.util.timer;

import java.util.Calendar;
import java.util.Date;

public class Clock {
    /**
     * 方法：获取当前时间
     * @return 当前时间
     */
    public static Date currentTime() {
        return new Date();
    }

    /**
     * 方法：获取当天0点时间
     * @return 当天0点时间
     */
    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 方法：获取明天时间
     * @return 明天时间（时分秒一致）
     */
    public static Date tomorrowTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);

        return calendar.getTime();
    }

    /**
     * 方法：获取明天0点时间
     * @return 明天0点时间
     */
    public static Date tomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, 1);

        return calendar.getTime();
    }

    /**
     * 方法：根据时间秒数获取时间
     * @param timeMillis 时间秒数（1970）
     * @return 转换后的时间
     */
    public static Date getDate(Long timeMillis) {
        return new Date(timeMillis);
    }
}
