package switch2019.project.model.frameworks;

import switch2019.project.model.person.Person;

public interface Owner {

    /**
     * method to create a new Account
     *
     * @param accountDenomination
     * @param accountDescription
     */
    //este método vai ser alterado posteriormente quando existirem os IDS
    public boolean createAccount(String accountDenomination, String accountDescription);

    /**
     * method to create a new Category
     *
     * @param nameOfCategory
     * @param categoryCreator
     */
    //este método vai ser alterado posteriormente quando existirem os IDS
    public boolean createCategory(String nameOfCategory, Person categoryCreator);
}
