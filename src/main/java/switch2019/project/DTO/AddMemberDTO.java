package switch2019.project.DTO;

public class AddMemberDTO {

    private String personEmail;
    private String groupDescription;

    public AddMemberDTO(String personEmail, String groupDescription) {
        this.personEmail = personEmail;
        this.groupDescription = groupDescription;
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
     * @return groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }
}



