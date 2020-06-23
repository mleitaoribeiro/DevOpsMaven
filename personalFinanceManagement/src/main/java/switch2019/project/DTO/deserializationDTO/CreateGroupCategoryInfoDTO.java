package switch2019.project.DTO.deserializationDTO;

import java.util.Objects;

public class CreateGroupCategoryInfoDTO {

    private String personEmail;
    private String categoryDenomination;

    public CreateGroupCategoryInfoDTO() {
        super();
    }

    /**
     * Setter for personEmail
     * @param personEmail for personEmail
     */
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    /**
     * Setter for categoryDenomination
     * @param categoryDenomination for categoryDenomination
     */
    public void setCategoryDenomination(String categoryDenomination) {
        this.categoryDenomination = categoryDenomination;
    }

    /**
     * Getter for the personEmail
     * @return String
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * Getter for the categoryDenomination
     * @return String
     */
    public String getCategoryDenomination() {
        return categoryDenomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGroupCategoryInfoDTO)) return false;
        CreateGroupCategoryInfoDTO that = (CreateGroupCategoryInfoDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(categoryDenomination, that.categoryDenomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, categoryDenomination);
    }
}
