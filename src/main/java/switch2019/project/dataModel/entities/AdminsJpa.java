package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

        @Override
        public String toString() {
            return "AdminsIdJpa{" +
                    "groupID=" + groupID.toString() +
                    ", person_ID='" + person_ID + '\'' +
                    '}';
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

    public String getPersonID() {
        return id.getPersonID();
    }

    @Override
    public String toString() {
        return "AdminsJpa{id=" + id + '}';
    }
}