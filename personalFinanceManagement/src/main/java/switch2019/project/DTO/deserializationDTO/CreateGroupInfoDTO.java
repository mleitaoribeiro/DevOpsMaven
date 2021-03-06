package switch2019.project.DTO.deserializationDTO;

import java.util.Objects;

public class CreateGroupInfoDTO {
    private String groupDescription;
    private String personEmail;

    public CreateGroupInfoDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateGroupInfoDTO that = (CreateGroupInfoDTO) o;
        return Objects.equals(groupDescription, that.groupDescription) &&
                Objects.equals(personEmail, that.personEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription, personEmail);
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonEmail() {
        return personEmail;
    }
}
