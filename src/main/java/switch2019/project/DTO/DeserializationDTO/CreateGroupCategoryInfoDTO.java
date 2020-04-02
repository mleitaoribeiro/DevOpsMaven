package switch2019.project.DTO.DeserializationDTO;

public class CreateGroupCategoryInfoDTO {

    private String groupDescription;
    private String personEmail;
    private String categoryDenomination;

    public CreateGroupCategoryInfoDTO() {
        super();
    }

    /**
     * Setter for group Description
     * @param groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * Setter for personEmail
     * @param personEmail
     */
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    /**
     * Setter for categoryDenomination
     * @param categoryDenomination
     */
    public void setCategoryDenomination(String categoryDenomination) {
        this.categoryDenomination = categoryDenomination;
    }

    /**
     * Getter for the groupDescription
     * @return groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Getter for the personEmail
     * @return personEmail
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * Getter for the categoryDenomination
     * @return
     */
    public String getCategoryDenomination() {
        return categoryDenomination;
    }

}
