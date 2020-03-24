package switch2019.project.DTO;

import java.util.Objects;

public class AccountDTO {

    private String ownerID;
    private String denomination;
    private String description;

    public AccountDTO(String ownerID, String denomination, String description) {
        this.ownerID = ownerID;
        this.denomination = denomination;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDTO)) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(ownerID, that.ownerID) &&
                Objects.equals(denomination, that.denomination) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerID, denomination, description);
    }

    /**
     * get OwnerId
     * @return ownerID
     */

    public String getOwnerID() {
        return ownerID;
    }

    /**
     * get denomination
     * @return denomination
     */

    public String getDenomination() {
        return denomination;
    }

    /**
     * get description
     * @return description
     */

    public String getDescription() {
        return description;
    }
}
