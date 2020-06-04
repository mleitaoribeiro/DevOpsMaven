package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class GetPersonalTransactionInDateRangeDTO {

    private final String personEmail;
    private final String initialDate;
    private final String finalDate;

    public GetPersonalTransactionInDateRangeDTO(String personEmail, String initialDate, String finalDate) {
        this.personEmail = personEmail;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPersonalTransactionInDateRangeDTO that = (GetPersonalTransactionInDateRangeDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(initialDate, that.initialDate) &&
                Objects.equals(finalDate, that.finalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, initialDate, finalDate);
    }

    /**
     * get Person Email
     *
     * @return personEmail
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * get Initial Date
     *
     * @return initialDate
     */
    public String getInitialDate() {
        return initialDate;
    }


    /**
     * get Initial Date
     *
     * @return finalDate
     */
    public String getFinalDate() {
        return finalDate;
    }
}
