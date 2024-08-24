package vi.legend.assistant.core.utils.type;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {
    private static final String DEFAULT_DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_TIME_ZONE_NAME = "Asia/Ho_Chi_Minh";

    public DateTimeUtils() {
    }

    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime) {
        return zonedDateTimeToString(zonedDateTime, DEFAULT_DATA_TIME_FORMAT);
    }

    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime, String format) {
        if (ObjectUtils.isNotEmpty(zonedDateTime) && StringUtils.isNotBlank(format)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.of(DEFAULT_TIME_ZONE_NAME));
            return zonedDateTime.format(formatter);
        } else {
            return null;
        }
    }

    public static ZonedDateTime stringToZonedDateTime(String dateString) {
        return stringToZonedDateTime(dateString, DEFAULT_DATA_TIME_FORMAT);
    }

    public static ZonedDateTime stringToZonedDateTime(String dateString, String format) {
        if (StringUtils.isNotBlank(dateString) && StringUtils.isNotBlank(format)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.of(DEFAULT_TIME_ZONE_NAME));
            return ZonedDateTime.parse(dateString, formatter);
        } else {
            return null;
        }
    }

    public static Date zonedDateTimeToDate(ZonedDateTime zonedDateTime) {
        return ObjectUtils.isNotEmpty(zonedDateTime) ? Date.from(zonedDateTime.toInstant()) : new Date();
    }

    public static ZonedDateTime dateToZonedDateTime(Date date) {
        return ObjectUtils.isNotEmpty(date) ? ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()) : ZonedDateTime.now();
    }
}
