package switch2019.project.DTO;

public class GroupCategoryCreationInputDTO {

    private String groupDescription;
    private String personEmail;
    private String categoryDenomination;

    public GroupCategoryCreationInputDTO() {
        super();
    }

    public GroupCategoryCreationInputDTO(String groupDescription, String personEmail, String categoryDenomination) {
        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
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
