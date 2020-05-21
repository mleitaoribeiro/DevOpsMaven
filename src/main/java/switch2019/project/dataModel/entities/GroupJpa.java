
package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name ="groups")
public class GroupJpa implements Serializable {

    @Id
    @Column(name = "group_id")
    private String id;
    @Column(name = "person_id")
    private String groupCreator;
    private String creationDate;

    @OneToMany(mappedBy = "id.groupID", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MembersJpa> members;

    @OneToMany(mappedBy = "id.groupID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminsJpa> administrators;

    protected GroupJpa() {};

    public GroupJpa(String groupDescription, String groupCreator, String creationDate) {
        this.id = groupDescription;
        this.groupCreator = groupCreator;
        this.creationDate = creationDate;
        this.members = new ArrayList<>();
        addMember(groupCreator);
        this.administrators = new ArrayList<>();
        addAdmin(groupCreator);
    }

    public boolean addAdmin(String adminId){
        AdminsJpa adminsJpa = new AdminsJpa(this, adminId);
        MembersJpa membersJpa = new MembersJpa(this, adminId);
        if(members.contains(membersJpa) && !administrators.contains(adminsJpa)) {
            return this.administrators.add(adminsJpa);
        }
        else return false;
    }

    public List<AdminsJpa> getAdministrators() {
        return administrators;
    }

    public boolean addMember(String memberId) {
        MembersJpa memberJpa = new MembersJpa(this, memberId);
        if (!members.contains(memberJpa))
            return members.add(memberJpa);
        else return false;
    }

    public List<MembersJpa> getMembers() {
        return members;
    }

    public String getId() {
        return id;
    }

    public String getGroupCreator() {
        return groupCreator;
    }

    public String getCreationDate() {
        return creationDate;
    }

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

    @Override
    public String toString() {
        return "Group ID: " + id + "; Group Creator: " + groupCreator + "; Creation Date: " + creationDate;
    }
}
