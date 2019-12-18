package switch2019.project.model;

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
     * @param year
     * @param month
     * @param day
     */

    public Group(String description, int year, int month, int day){
        this.description=description;
        startingDate = Calendar.getInstance();
        setStartingDate(year, month, day);
        members = new HashSet<>();
    }
    
    public void setStartingDate(int year, int month, int day) {
        startingDate.set(year, month, day);
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
