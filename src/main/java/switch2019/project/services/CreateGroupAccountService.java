package switch2019.project.services;


import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.GroupID;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

public class CreateGroupAccountService {

    public boolean createGroupAccount (String personName, String groupDescription, Denomination accountDenomination, Description accountDescription ) {

        PersonRepository personRepository = new PersonRepository();
        GroupsRepository groupsRepository = new GroupsRepository();
        AccountRepository accountRepository = new AccountRepository();

        Person onePerson = personRepository.findPersonByAttributes(personName);

        Group oneGroup = groupsRepository.findGroupByAttributes(groupDescription);
        GroupID oneGroupID = oneGroup.getID();

        boolean personIsGroupAdmin = oneGroup.isGroupAdmin(onePerson);

        if (personIsGroupAdmin) {
            return accountRepository.createAccount(accountDenomination,accountDescription);
        }
        return false;
    }

}
