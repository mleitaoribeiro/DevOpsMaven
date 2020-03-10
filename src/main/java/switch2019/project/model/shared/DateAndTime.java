package switch2019.project.model.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateAndTime {

    /**
     * Private Transaction variables
     */
    private LocalDate birthDate;
    private LocalDate startingGroupDate;
    private LocalDateTime transactionDate;
    private LocalDateTime scheduleDate;

    /**
     * Public constructor to startingDate
     */
    public DateAndTime () {
        this.startingGroupDate = LocalDate.now();
    }

    /**
     * Public constructor to birthDate
     *
     * @param birthDate
     */
    public DateAndTime(LocalDate birthDate) {
        setBirthDate(birthDate);
    }

    /**
     * Public constructor to transactionDate and scheduleDate
     *
     * @param transactionDate
     * @param scheduleDate
     */
    public DateAndTime(LocalDateTime transactionDate, LocalDateTime scheduleDate) {
        setTransactionDate(transactionDate);
        setScheduleDate(scheduleDate);
    }

    /**
     * method to @override equals
     *
     * @param o
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateAndTime that = (DateAndTime) o;
        return Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(startingGroupDate, that.startingGroupDate);
    }

    /**
     * method to @override hashcode
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(birthDate, startingGroupDate);
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
     * Set birthDate
     *
     * @param date
     */
    public void setBirthDate(LocalDate date) {
        if(birthDate == null)
            throw new IllegalArgumentException(("Birth Date Can't be Null."));
        else if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth Date Not Supported.");
        }
        else this.birthDate = date;
    }

    /**
     * Set and format transactionDate
     *
     * @param date
     */
    public void setTransactionDate(LocalDateTime date) {
        if (date == null) {
            LocalDateTime dateNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.transactionDate = LocalDateTime.parse(dateNow.format(formatter), formatter);
        } else this.transactionDate = date;
    }

    /**
     * Set scheduleDate
     *
     * @param date
     */
    public void setScheduleDate(LocalDateTime date) {
        if(scheduleDate == null)
            throw new IllegalArgumentException(("Schedule Date Can't be Null."));
        else if (scheduleDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Schedule Date Not Supported.");
        }
        else this.scheduleDate = date;
    }

    /**
     * Get getStartingGroupDate
     */
    public String getStartingGroupDate() {
        return dateToString(this.startingGroupDate);
    }

    /**
     * Get getBirthDate
     */
    public String getBirthDate() {
        return dateToString(this.birthDate);
    }

    /**
     * Get transactionDate
     */
    public String getTransactionDate() {
        return dateHourMinuteToString(this.transactionDate);
    }

    /**
     * Get scheduleDate
     */
    public String getScheduleDate() {
        return dateHourMinuteToString(this.scheduleDate);
    }
}
