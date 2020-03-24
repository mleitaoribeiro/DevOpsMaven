package switch2019.project.DTO;

public class SiblingsDTO {

    private String emailPersonOne;
    private String emailPersonTwo;

    public SiblingsDTO(String emailPersonOne, String emailPersonTwo) {
        this.emailPersonOne = emailPersonOne;
        this.emailPersonTwo = emailPersonTwo;
    }

    public String getEmailPersonOne() {
        return emailPersonOne;
    }

    public String getEmailPersonTwo() {
        return emailPersonTwo;
    }
}
