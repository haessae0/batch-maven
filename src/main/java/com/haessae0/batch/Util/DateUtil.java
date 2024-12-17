package com.haessae0.batch.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 날짜 연산을 위한 유틸리티 클래스입니다.
 */
public class DateUtil {
    /**
     * 주어진 패턴에 따라 현재 날짜를 문자열로 반환합니다.
     *
     * @param format 날짜-시간 패턴 문자열
     * @return 형식화된 현재 날짜 문자열
     */
    public static String getCurDate(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }
}
