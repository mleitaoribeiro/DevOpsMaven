package switch2019.project.DTO;

import java.util.Objects;

public class AdminCreateGroupDTO {
    private String groupDescription;
    private String personEmail;

    public AdminCreateGroupDTO(String groupDescription, String personEmail) {
        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminCreateGroupDTO that = (AdminCreateGroupDTO) o;
        return Objects.equals(groupDescription, that.groupDescription) &&
                Objects.equals(personEmail, that.personEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription, personEmail);
    }


    /**
     * get Group Description
     *
     * @return groupDescription
     */

    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * get Person Email
     *
     * @return personEmail
     */

    public String getPersonEmail() {
        return personEmail;
    }

}