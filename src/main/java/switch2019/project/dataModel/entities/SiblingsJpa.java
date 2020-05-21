package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "siblings")
public class SiblingsJpa {

    @Embeddable
    static class SiblingsIdJpa implements Serializable {

        @Column(name = "OwnerID")
        private String ownerEmail;

        @Column(name = "SiblingID")
        private String siblingEmail;

        public SiblingsIdJpa() {
        }

        public SiblingsIdJpa(String ownerEmail, String siblingEmail) {
            this.ownerEmail = ownerEmail;
            this.siblingEmail = siblingEmail;
        }

        public String getOwnerEmail() {
            return ownerEmail;
        }

        public String getSiblingEmail() {
            return siblingEmail;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SiblingsIdJpa that = (SiblingsIdJpa) o;
            return Objects.equals(ownerEmail, that.ownerEmail) &&
                    Objects.equals(siblingEmail, that.siblingEmail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ownerEmail, siblingEmail);
        }
    }

    @EmbeddedId
    SiblingsIdJpa id;

    public SiblingsJpa() {
    }

    public SiblingsJpa(String ownerId, String siblingId) {
        this.id = new SiblingsIdJpa(ownerId, siblingId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiblingsJpa that = (SiblingsJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public SiblingsIdJpa getId() {
        return id;
    }
}
