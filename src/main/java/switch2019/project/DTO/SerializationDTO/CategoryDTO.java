package switch2019.project.DTO.SerializationDTO;

import java.util.Objects;

public class CategoryDTO {

   /* The Category DTO transfers strings that refer to Category attributes:
    denomination .- a String refering to the Category Denomination attribute
    categoryID .- a String refering to the Category Denomination and the OwnerID (which can be either a PersonID or GroupID)*/

    private String denomination;
    private String ownerID;

    public CategoryDTO(String denomination, String ownerID) {
        this.denomination = denomination;
        this.ownerID = ownerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO dto = (CategoryDTO) o;
        return Objects.equals(denomination, dto.denomination) &&
                Objects.equals(ownerID, dto.ownerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, ownerID);
    }

    @Override
    public String toString() {
        return "This " + this.denomination + " category was added to " + this.ownerID + " categories list!";
    }

    /**
     * Getter for the Category description
     * @return
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Getter for the CategoryID
     * @return
     */
    public String getOwnerID() {
        return ownerID;
    }
}



