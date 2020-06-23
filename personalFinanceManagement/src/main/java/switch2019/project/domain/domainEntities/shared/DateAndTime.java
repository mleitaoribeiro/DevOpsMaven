package switch2019.project.domain.domainEntities.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateAndTime {

    private final LocalDate yearMonthDay;
    private final LocalDateTime yearMonthDayHourMinute;

    public DateAndTime(int year, int month, int day) {
        yearMonthDay = LocalDate.of(year, month, day);
        yearMonthDayHourMinute = null;
    }

    public DateAndTime(int year, int month, int day, int hour, int minute) {
        yearMonthDayHourMinute = LocalDateTime.of(year, month, day, hour, minute);
        yearMonthDay = null;
    }

    public DateAndTime() {
        yearMonthDay = LocalDate.now();
        yearMonthDayHourMinute = LocalDateTime.now().withSecond(0).withNano(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateAndTime that = (DateAndTime) o;
        return Objects.equals(yearMonthDay, that.yearMonthDay) &&
                Objects.equals(yearMonthDayHourMinute,
                        that.yearMonthDayHourMinute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearMonthDay, yearMonthDayHourMinute);
    }

    public boolean isInTheFuture() {
        if (yearMonthDay != null) return yearMonthDay.isAfter(LocalDate.now());
        else return yearMonthDayHourMinute.isAfter(LocalDateTime.now());
    }

    public boolean isInThePast(LocalDateTime date) {
        return yearMonthDayHourMinute.isBefore(date);
    }

    public boolean isInTheFuture(LocalDateTime date) {
        return yearMonthDayHourMinute.isAfter(date);
    }

    public boolean isInThePast() {
        if (yearMonthDay != null) return yearMonthDay.isBefore(LocalDate.now());
        else return yearMonthDayHourMinute.isBefore(LocalDateTime.now());
    }

    public String yearMonthDayToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return yearMonthDay.format(formatter);
    }

    public String yearMonthDayHourMinuteToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return yearMonthDayHourMinute.format(formatter);
    }

    public LocalDate getYearMonthDay() {
        return yearMonthDay;
    }

    public LocalDateTime getYearMonthDayHourMinute() {
        return yearMonthDayHourMinute;
    }

}
