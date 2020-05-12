
package switch2019.project.dataModel.entities;
import javax.persistence.*;


@Entity(name ="groups")
public class GroupJpa {

    @Id
    private String id;
    private String groupCreator;
    private String creationDate;

    protected GroupJpa() {};

    public GroupJpa(String groupDescription, String groupCreator, String creationDate) {
        this.id = groupDescription;
        this.groupCreator = groupCreator;
        this.creationDate = creationDate;
    }

 }
