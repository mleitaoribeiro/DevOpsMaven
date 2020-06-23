package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class CreatePersonAccountDTO {

    private final String personEmail;
    private final String accountDenomination;
    private final String accountDescription;

    public CreatePersonAccountDTO (String personEmail, String accountDenomination, String accountDescription) {
        this.personEmail = personEmail;
        this.accountDenomination = accountDenomination;
        this.accountDescription = accountDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonAccountDTO that = (CreatePersonAccountDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(accountDenomination, that.accountDenomination) &&
                Objects.equals(accountDescription, that.accountDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, accountDenomination, accountDescription);
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getAccountDenomination() {
        return accountDenomination;
    }

    public String getAccountDescription() {
        return accountDescription;
    }
}
