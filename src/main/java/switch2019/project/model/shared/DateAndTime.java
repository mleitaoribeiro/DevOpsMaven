package switch2019.project.model.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

    /**
     * Private Transaction variables
     */
    private LocalDate yearMonthDay;
    private LocalDateTime yearMonthDayHourMinute;


    /**
     * Public constructor to yearMonthDay
     *
     * @param date
     */
    public DateAndTime(LocalDate date) {
        setYearMonthDay(date);
    }

    /**
     * Public constructor to yearMonthDayHourMinute
     *
     * @param date
     */
    public DateAndTime(LocalDateTime date) {
        setYearMonthDayHourMinute(date);
    }

    /**
     * Methood toString() of yearMonthDay
     */
    public String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    /**
     * Methood toString() of yearMonthDayHourMinute
     */
    public String dateHourMinuteToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    /**
     * Set yearMonthDay
     *
     * @param date
     */
    private void setYearMonthDay(LocalDate date) {
        if(date == null)
            throw new IllegalArgumentException("The Date Can't be Null.");
        else this.yearMonthDay = date;
    }

    /**
     * Set yearMonthDayHourMinute
     *
     * @param date
     */
    private void setYearMonthDayHourMinute(LocalDateTime date) {
        if(date == null)
            throw new IllegalArgumentException("The Date Can't be Null.");
        else this.yearMonthDayHourMinute = date;
    }

    /**
     * Get yearMonthDay
     */
    public String getYearMonthDay() {
        return dateToString(this.yearMonthDay);
    }

    /**
     * Get yearMonthDayHourMinute
     */
    public String getYearMonthDayHourMinute() {
        return dateHourMinuteToString(this.yearMonthDayHourMinute);
    }
}
