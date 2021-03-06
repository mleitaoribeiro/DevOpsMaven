package switch2019.project.domain.domainEntities.person;

import switch2019.project.utils.StringUtils;

import java.util.Objects;

public class PersonName {

    private final String name;

    public PersonName(String name) {
        this.name = standardPersonName(name);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonName that = (PersonName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() { return name;}

    public String getPersonName() {
        return name;
    }

    public String getFirstAndLastName() {
        String[] words = name.split(" ");
        return words[0] + " " + words[words.length - 1];
    }

    private String standardPersonName(String personName) {
        if(personName == null || personName.isEmpty())
            throw new IllegalArgumentException("The name can't be empty or null.");
        else
            return StringUtils.normalizePersonName(personName);
    }

}
