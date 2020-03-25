package switch2019.project.DTO;

public class SiblingsDTO {

    private String emailPersonOne;
    private String emailPersonTwo;

    public SiblingsDTO(String emailPersonOne, String emailPersonTwo) {
        this.emailPersonOne = emailPersonOne;
        this.emailPersonTwo = emailPersonTwo;
    }


    /**
     * get email of PersonOne
     * @return emailPersonOne
     */

    public String getEmailPersonOne() {
        return emailPersonOne;
    }

    /**
     * get email of PersonTwo
     * @return emailPersonTwo
     */

    public String getEmailPersonTwo() {
        return emailPersonTwo;
    }


}
