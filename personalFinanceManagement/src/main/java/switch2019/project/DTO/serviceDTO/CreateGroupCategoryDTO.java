package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class CreateGroupCategoryDTO {

    /*
    this DTO contains all the information necessary to create a Category and associate it with a Group.
    description - Group description, necessary to find the GroupID and therefore the Group where the category will be created.
    personEmail - Person email, necessary to find the PersonID and therefore find if the Person is an admin on the Group where the category will be created.
    categoryDenomination - this String will become the denominationValue of the Denomination which is essentially the name of the Category that will be created.
     */

    private final String groupDescription;
    private final String personEmail;
    private final String categoryDenomination;

    public CreateGroupCategoryDTO(String groupDescription, String personEmail, String categoryDenomination) {
        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
        this.categoryDenomination = categoryDenomination;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getCategoryDenomination() {
        return categoryDenomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGroupCategoryDTO)) return false;
        CreateGroupCategoryDTO that = (CreateGroupCategoryDTO) o;
        return Objects.equals(groupDescription, that.groupDescription) &&
                Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(categoryDenomination, that.categoryDenomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription, personEmail, categoryDenomination);
    }
}

