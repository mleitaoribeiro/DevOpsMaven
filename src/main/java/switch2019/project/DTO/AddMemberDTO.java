package switch2019.project.DTO;

import java.util.Objects;

public class AddMemberDTO {

    private String personEmail;
    private String groupDescription;


    public AddMemberDTO(String personEmail, String groupDescription) {
        this.personEmail = personEmail;
        this.groupDescription = groupDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMemberDTO that = (AddMemberDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(groupDescription, that.groupDescription);
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
     * @return
     */
    public String getGroupDescription() {
        return groupDescription;
    }
}



