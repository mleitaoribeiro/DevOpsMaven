package switch2019.project.model.frameworks;

import switch2019.project.model.person.Person;

public interface Owner {

    /**
     * method to create a new Category
     *
     * @param nameOfCategory
     * @param categoryCreator
     */
    //este método vai ser alterado posteriormente quando existirem os IDS
    boolean createCategory(String nameOfCategory, Person categoryCreator);

    /**
     * method to get the Owner ID
     * @return owner's ID
     */
    ID getID();
}
