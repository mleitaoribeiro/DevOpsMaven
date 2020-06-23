package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class GetGroupTransactionInDateRangeDTO {

    private final String groupDescription;
    private final String personEmail;
    private final String initialDate;
    private final String finalDate;

    public GetGroupTransactionInDateRangeDTO(String groupDescription, String personEmail, String initialDate, String finalDate) {
        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetGroupTransactionInDateRangeDTO that = (GetGroupTransactionInDateRangeDTO) o;
        return Objects.equals(groupDescription, that.groupDescription) &&
                Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(initialDate, that.initialDate) &&
                Objects.equals(finalDate, that.finalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription, personEmail, initialDate, finalDate);
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

}
