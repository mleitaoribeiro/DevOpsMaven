package switch2019.project.dataModel.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity(name="admins")
public class AdminsJpa {

    @Embeddable
    static class AdminsIdJpa implements Serializable {

        @ManyToOne
        @JoinColumn(name = "group_id")
        private GroupJpa groupID;

        @Column(nullable = false, updatable = false)
        private String person_ID;

        public AdminsIdJpa() {
        }

        public AdminsIdJpa(GroupJpa groupID, String personID) {
            this.groupID = groupID;
            this.person_ID = personID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AdminsIdJpa that = (AdminsIdJpa) o;
            return Objects.equals(groupID, that.groupID) &&
                    Objects.equals(person_ID, that.person_ID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(groupID, person_ID);
        }

        public GroupJpa getGroupID() {
            return groupID;
        }

        public String getPersonID() {
            return person_ID;
        }
    }

    @EmbeddedId
    AdminsIdJpa id;

    public AdminsJpa() {
    }

    public AdminsJpa(GroupJpa groupID, String personID ) {
        this.id = new AdminsIdJpa(groupID, personID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminsJpa adminsJpa = (AdminsJpa) o;
        return Objects.equals(id, adminsJpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public AdminsIdJpa getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AdminsJpa{id=" + id + '}';
    }
}
