package switch2019.project.DTO.deserializationDTO;

import java.util.Objects;

public class AddMemberInfoDTO {

    private String personEmail;

    public AddMemberInfoDTO() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddMemberInfoDTO that = (AddMemberInfoDTO) o;
        return personEmail.equals(that.personEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail);
    }

    /**
     * Setter for personEmail
     *
     * @param personEmail
     */
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    /**
     * Getter for personEmail
     *
     * @return personEmail
     */
    public String getPersonEmail() {
        return personEmail;
    }

}



