package tournaments.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TimePeriod {
    private final LocalDate beginDate;
    private final LocalDate endDate;

    public TimePeriod(String beginDate, String endDate){
        DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toFormatter();
        this.beginDate = LocalDate.parse(beginDate, format);
        this.endDate = LocalDate.parse(endDate, format);
    }

    public LocalDate getBeginDate() {
        return this.beginDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }
}
