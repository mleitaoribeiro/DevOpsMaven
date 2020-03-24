package switch2019.project.DTO;

public class AdminCreateGroupDTO {
    private String groupDescription;
    private String personEmail;

    public AdminCreateGroupDTO(String groupDescription, String personEmail){
        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public String getPersonEmail() {
        return personEmail;
    }
}
