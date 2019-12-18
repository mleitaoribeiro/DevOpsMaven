package switch2019.project.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;

public class Group {
    String description;
    String startingDate;
    HashSet<Person> members; 


    /**
     * Default Person constructor
     * @param description
     * @param startingDate
     */

    public Group(String description,String startingDate){
        this.description=description;
        this.startingDate=startingDate;
        members = new HashSet<>();
    }
    
    public void setStartingDate(String startingDate) {
        this.startingDate=startingDate;
    }

    /**
     * Override of equals for Group
     *
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(description, group.description) &&
                Objects.equals(startingDate, group.startingDate) &&
                Objects.equals(members, group.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, startingDate, members);
    }

}


