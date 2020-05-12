package switch2019.project.dataModel.entities;
import javax.persistence.*;

@Entity(name ="persons")
public class PersonJpa {

    @Id
    private String email;
    private String name;
    private String birthDate;
    private String birthPlace;

    protected PersonJpa() {};

    public PersonJpa(String id, String name, String birthDate, String birthPlace) {
        this.email = id;
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }
}