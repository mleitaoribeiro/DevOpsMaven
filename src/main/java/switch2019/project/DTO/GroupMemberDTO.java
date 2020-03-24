package switch2019.project.DTO;

import java.util.Objects;

public class GroupMemberDTO {

    private String personName;
    private String memberEmail;

    public GroupMemberDTO(String personName, String memberEmail) {
        this.personName = personName;
        this.memberEmail = memberEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberDTO that = (GroupMemberDTO) o;
        return Objects.equals(personName, that.personName) &&
                Objects.equals(memberEmail, that.memberEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, memberEmail);
    }

    /**
     * get method to the person name
     *
     * @return person name
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * get method to the member email
     *
     * @return member email
     */
    public String getMemberEmail() {
        return memberEmail;
    }
}
