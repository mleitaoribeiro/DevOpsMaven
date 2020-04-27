package switch2019.project.DTO.serializationDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class AccountDTO extends RepresentationModel<CategoryDTO> {

    private final String ownerID;
    private final String denomination;
    private final String description;

    public AccountDTO(String ownerID, String denomination, String description) {
        this.ownerID = ownerID.toUpperCase();
        this.denomination = denomination.toUpperCase();
        this.description = description.toUpperCase();
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
