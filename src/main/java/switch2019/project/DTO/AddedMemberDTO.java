package switch2019.project.DTO;

import java.util.Objects;

public class AddedMemberDTO {

    private String memberAdded;

    public AddedMemberDTO(boolean wasMemberAdded, String personEmail, String groupDescription) {
        if (wasMemberAdded) memberAdded = personEmail + " was added to group " + groupDescription.toLowerCase();
        else memberAdded = personEmail + " is already on group " + groupDescription.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddedMemberDTO that = (AddedMemberDTO) o;
        return Objects.equals(memberAdded, that.memberAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberAdded);
    }

    /**
     * method to get memberAdded attribute
     *
     * @return memberAdded
     */
    public String getMemberAdded() {
        return memberAdded;
    }

}
