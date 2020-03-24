package switch2019.project.DTO;

public class CreateCategoryInGroupDTO {

    /**this DTO contains all the information necessary to create a Category and associate it with a Group.*/

    //description - Group description, necessary to find the GroupID and therefore the Group where the category will be created.
    //personEmail - Person email, necessary to find the PersonID and therefore find if the Person is an admin on the Group where the category will be created.
    //categoryDenomination - this String will become the denominationValue of the Denomination which is essentially the name of the Category that will be created.

    private String groupDenomination;
    private String personEmail;
    private String categoryDenomination;

    public CreateCategoryInGroupDTO(String groupDescription, String personEmail, String categoryDenomination) {
        this.groupDenomination = groupDescription;
        this.personEmail = personEmail;
        this.categoryDenomination = categoryDenomination;
    }

    /**
     * Getter for the groupDescription
     * @return groupDescription
     */
    public String getGroupDescription() {
        return groupDenomination;
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

