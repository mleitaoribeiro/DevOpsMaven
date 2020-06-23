package switch2019.project.DTO.serializationDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class SiblingsDTO extends RepresentationModel<PersonIDDTO> {
    private final String siblings;

    public SiblingsDTO(boolean siblings) {
       if (siblings) {
           this.siblings = "They are siblings.";
       } else this.siblings = "They are not siblings.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SiblingsDTO)) return false;
        SiblingsDTO that = (SiblingsDTO) o;
        return Objects.equals(siblings, that.siblings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siblings);
    }

    public String getSiblings() {
        return siblings;
    }

}
