package batuozk.gistapitest.base;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {


    public static String getDateWithOffset(int months){
        return ZonedDateTime.now(ZoneId.of("Asia/Istanbul")).minusMonths(months).format( DateTimeFormatter.ISO_INSTANT );
    }
}
