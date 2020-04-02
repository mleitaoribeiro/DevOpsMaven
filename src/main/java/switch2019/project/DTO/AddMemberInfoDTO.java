package switch2019.project.DTO;

import java.util.Objects;

public class AddMemberInfoDTO {

    private String personEmail;
    private String groupDescription;

    public AddMemberInfoDTO(){}

    public AddMemberInfoDTO(String personEmail, String groupDescription) {
        this.personEmail = personEmail;
        this.groupDescription = groupDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMemberInfoDTO that = (AddMemberInfoDTO) o;
        return personEmail.equals(that.personEmail) &&
                groupDescription.equals(that.groupDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, groupDescription);
    }

    /**
     * Getter for personEmail
     * @return personEmail
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * Getter for groupDescription
     *
     * @return groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }
}


