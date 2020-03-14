package switch2019.project.model.frameworks;

import switch2019.project.model.person.Person;

public interface Owner {

    //este método vai ser alterado posteriormente quando existirem os IDS
    public boolean createAccount(String accountDenomination, String accountDescription);

    //este método vai ser alterado posteriormente quando existirem os IDS
    public boolean createCategory(String nameOfCategory, Person categoryCreator);
}
