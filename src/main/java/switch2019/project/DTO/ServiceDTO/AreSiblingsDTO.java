package switch2019.project.DTO.ServiceDTO;

import java.util.Objects;

public class AreSiblingsDTO {

    final private String emailPersonOne;
    final private String emailPersonTwo;

    public AreSiblingsDTO(String emailPersonOne, String emailPersonTwo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreSiblingsDTO)) return false;
        AreSiblingsDTO that = (AreSiblingsDTO) o;
        return Objects.equals(emailPersonOne, that.emailPersonOne) &&
                Objects.equals(emailPersonTwo, that.emailPersonTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailPersonOne, emailPersonTwo);
    }
}
