package switch2019.project.DTO.deserializationDTO;

import java.util.Objects;

public class CreatePersonAccountInfoDTO {

    private String accountDenomination;
    private String accountDescription;

    public CreatePersonAccountInfoDTO() { super(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonAccountInfoDTO that = (CreatePersonAccountInfoDTO) o;
        return Objects.equals(accountDenomination, that.accountDenomination) &&
                Objects.equals(accountDescription, that.accountDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountDenomination, accountDescription);
    }

    public String getAccountDenomination() {
        return accountDenomination;
    }

    public void setAccountDenomination(String accountDenomination) {
        this.accountDenomination = accountDenomination;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }
}

