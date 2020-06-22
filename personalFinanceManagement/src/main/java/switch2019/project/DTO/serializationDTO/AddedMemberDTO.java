package switch2019.project.DTO.serializationDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class AddedMemberDTO extends RepresentationModel<AddedMemberDTO>  {

    private String memberAdded;

    public AddedMemberDTO(boolean wasMemberAdded, String personEmail, String groupDescription) {
        if(groupDescription != null) groupDescription = groupDescription.toLowerCase();
        if (wasMemberAdded) memberAdded = personEmail + " was added to group " + groupDescription;
        else memberAdded = personEmail + " is already on group " + groupDescription;
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
     * @return String
     */
    public String getMemberAdded() {
        return memberAdded;
    }

    @Override
    public String toString() {
        return memberAdded;
    }
}
