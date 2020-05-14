
package switch2019.project.dataModel.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name ="groups")
public class GroupJpa {

    @Id
    @Column(name = "group_id")
    private String id;
    @Column(name = "person_id")
    private String groupCreator;
    private String creationDate;

    //@OneToMany(mappedBy = "id.groupJpa", cascade = CascadeType.ALL)
    //private List<MembersJpa> members;

    //@OneToMany(mappedBy = "id.groupJpa", cascade = CascadeType.ALL)
    //private List<AdminsJpa> administrators;

    protected GroupJpa() {};

    public GroupJpa(String groupDescription, String groupCreator, String creationDate) {
        this.id = groupDescription;
        this.groupCreator = groupCreator;
        this.creationDate = creationDate;
        //this.members = new ArrayList<MembersJpa>();
        //this.administrators = new ArrayList<AdminsJpa>();
    }

    public String getId() {
        return id;
    }

    public String getGroupCreator() {
        return groupCreator;
    }

    /*public List<MembersJpa> getMembers() {
        return members;
    }

    public List<AdminsJpa> getAdministrators() {
        return administrators;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupJpa groupJpa = (GroupJpa) o;
        return id.equalsIgnoreCase(groupJpa.id) &&
                Objects.equals(groupCreator, groupJpa.groupCreator) &&
                Objects.equals(creationDate, groupJpa.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupCreator, creationDate);
    }
}
