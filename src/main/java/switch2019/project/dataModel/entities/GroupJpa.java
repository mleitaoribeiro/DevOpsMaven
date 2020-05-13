
package switch2019.project.dataModel.entities;
import javax.persistence.*;
import java.util.Objects;


@Entity(name ="groups")
public class GroupJpa {

    @Id
    @Column(name = "group_id")
    private String id;
    @Column(name = "person_id")
    private String groupCreator;
    private String creationDate;

    protected GroupJpa() {};

    public GroupJpa(String groupDescription, String groupCreator, String creationDate) {
        this.id = groupDescription;
        this.groupCreator = groupCreator;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }


    public String getGroupCreator() {
        return groupCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupJpa groupJpa = (GroupJpa) o;
        return Objects.equals(id, groupJpa.id) &&
                Objects.equals(groupCreator, groupJpa.groupCreator) &&
                Objects.equals(creationDate, groupJpa.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupCreator, creationDate);
    }
}
