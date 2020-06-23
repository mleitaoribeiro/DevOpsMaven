package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class CreateGroupDTO {
    final private String groupDescription;
    final private String personEmail;

    public CreateGroupDTO(String groupDescription, String personEmail) {
        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateGroupDTO that = (CreateGroupDTO) o;
        return Objects.equals(groupDescription, that.groupDescription) &&
                Objects.equals(personEmail, that.personEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription, personEmail);
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public String getPersonEmail() {
        return personEmail;
    }

}