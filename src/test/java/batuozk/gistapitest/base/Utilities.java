package batuozk.gistapitest.base;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Utilities {

    /**
     * Returns ISO-8601 type date value as String
     * @param months Number of months to substract from '<code>now</code>'
     * @return ISO-8601 date String
     */
    public static String getDateWithOffset(int months){
        return ZonedDateTime.now(ZoneId.of("Asia/Istanbul")).minusMonths(months).format( DateTimeFormatter.ISO_INSTANT );
    }
    /**
     * Creates a universally unique identifier
     * @return UUID string
     */
    public static String createUUID(){
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * Prints each gist's filename & gist owner
     * @param arrayList Response body as a list
     */
    public static void printUserGists(ArrayList arrayList){
        for(int i = 0; i < arrayList.size(); i++){
            System.out.println("Gist " + (i+1) + "--------");
            var fileName = ((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("files")).keySet().toArray()[0];
            System.out.println(fileName);
            System.out.println(((LinkedHashMap)((LinkedHashMap)arrayList.get(i)).get("owner")).get("login"));
        }
    }
}
