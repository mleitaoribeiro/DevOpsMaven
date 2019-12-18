package switch2019.project.model;

import java.util.Calendar;
import java.util.HashSet;

public class Group {
    String description;
    Calendar startingDate;
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
    }
    
    public void setStartingDate(int year, int month, int day) {
        startingDate.set(year, month, day);
    }

}
