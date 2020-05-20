package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity (name = "siblings")
public class SiblingsJpa {

    @Id
    @Column(name = "OwnerID")
    private String ownerEmail;


    @Column(name = "SiblingID")
    private String siblingEmail;


    public SiblingsJpa (String id, String id_2){
        this.ownerEmail = id;
        this.siblingEmail= id_2;
    }

    public String getEmailPersonOne() {
        return ownerEmail;
    }

    public String getEmailPersonTwo() {
        return siblingEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiblingsJpa that = (SiblingsJpa) o;
        return Objects.equals(ownerEmail, that.ownerEmail) &&
                Objects.equals(siblingEmail, that.siblingEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerEmail, siblingEmail);
    }
}
