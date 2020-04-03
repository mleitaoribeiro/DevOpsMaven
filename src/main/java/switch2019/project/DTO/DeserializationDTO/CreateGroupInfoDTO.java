package switch2019.project.DTO.DeserializationDTO;

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

    /**
     * Set groupDescription
     *
     * @param groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
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
     * set personEmail
     *
     * @param personEmail
     */
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
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
