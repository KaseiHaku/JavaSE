package kasei.javase.frequently_used.date;

import java.time.*;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

public class Jdk8DateTime {


    public static void main(String[] args) {

        Instant instant = Instant.now();

        ZonedDateTime zonedDateTime = ZonedDateTime.now();


        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofHours(8));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").withZone(zoneId);
        String format = dateTimeFormatter.format(instant);
        System.out.println(format);
        Period period = Period.ZERO;

        Duration duration = Duration.ZERO;
    }


}
