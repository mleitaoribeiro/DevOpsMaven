package switch2019.project.DTO.deserializationDTO;

import java.util.Objects;

public class MemberInfoDTO {

    private String personEmail;

    public MemberInfoDTO() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberInfoDTO that = (MemberInfoDTO) o;
        return personEmail.equals(that.personEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail);
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonEmail() {
        return personEmail;
    }

}



