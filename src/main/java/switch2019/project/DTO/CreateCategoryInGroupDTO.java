package switch2019.project.DTO;

public class CreateCategoryInGroupDTO {

    /**this DTO contains all the information necessary to create a Category and associate it with a Group.*/

    //description - Group description, necessary to find the GroupID and therefore the Group where the category will be created.
    //personEmail - Person email, necessary to find the PersonID and therefore find if the Person is an admin on the Group where the category will be created.
    //categoryDenomination - this String will become the denominationValue of the Denomination which is essentially the name of the Category that will be created.

    private String groupDescription;
    private String personEmail;
    private String categoryDenomination;

    public CreateCategoryInGroupDTO(String groupDescription, String personEmail, String categoryDenomination) {
        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
        this.categoryDenomination = categoryDenomination;
    }

    /**
     * Getters used to obtain the attribute Strings, outside of this class:
     * @return
     */

    public String getGroupDescription() {
        return groupDescription;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getCategoryDenomination() {
        return categoryDenomination;
    }
}

