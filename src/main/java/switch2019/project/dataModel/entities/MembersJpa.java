package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name="members")
public class MembersJpa {

    @Embeddable
    static class MembersIdJpa implements Serializable {

        @ManyToOne
        @JoinColumn(name = "group_id")
        private GroupJpa groupID;

        @Column(nullable = false, updatable = false)
        private String person_ID;

        public MembersIdJpa() {
        }

        public MembersIdJpa(GroupJpa groupID, String personID) {
            this.groupID = groupID;
            this.person_ID = personID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MembersIdJpa that = (MembersIdJpa) o;
            return Objects.equals(groupID, that.groupID) &&
                    Objects.equals(person_ID, that.person_ID);
        }

        @Override
        public int hashCode() {
            return Objects.hash(groupID, person_ID);
        }

        @Override
        public String toString() {
            return "MembersIdJpa{" +
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
    MembersIdJpa id;

    public MembersJpa() {
    }

    public MembersJpa(GroupJpa groupID, String personID) {
        this.id = new MembersIdJpa(groupID, personID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembersJpa that = (MembersJpa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public MembersIdJpa getId() {
        return id;
    }

    public String getPersonID() {
        return id.getPersonID();
    }

    @Override
    public String toString() {
        return "MembersJpa{" + id.toString() + "}";
    }
}
