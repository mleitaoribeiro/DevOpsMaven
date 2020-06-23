package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class AreSiblingsDTO {

    private final String emailPersonOne;
    private final String emailPersonTwo;

    public AreSiblingsDTO(String emailPersonOne, String emailPersonTwo) {
        this.emailPersonOne = emailPersonOne;
        this.emailPersonTwo = emailPersonTwo;
    }

    public String getEmailPersonOne() {
        return emailPersonOne;
    }

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
