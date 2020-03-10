package switch2019.project.model.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateAndTime {

    /**
     * Private Transaction variables
     */
    private LocalDate yearMonthDay;
    private LocalDateTime yearMonthDayHourMinute;

    /**
     *Public constructor to yearMonthDay
     *
     * @param year
     * @param month
     * @param day
     */
    public DateAndTime(int year, int month, int day) {
        this.yearMonthDay = LocalDate.of(year, month, day);
    }

    /**
     * Public constructor to yearMonthDayHourMinute
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     */
    public DateAndTime(int year, int month, int day, int hour, int minute) {
        this.yearMonthDayHourMinute = LocalDateTime.of(year, month, day, hour, minute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateAndTime that = (DateAndTime) o;
        return Objects.equals(yearMonthDay, that.yearMonthDay) &&
                Objects.equals(yearMonthDayHourMinute, that.yearMonthDayHourMinute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearMonthDay, yearMonthDayHourMinute);
    }


    public boolean localDateIsAfter () {
        return yearMonthDay.isAfter(LocalDate.now());
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
