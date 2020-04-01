package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.controllerLayer.controllersCli.US003AddMemberToGroupController;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

@SpringBootApplication(scanBasePackages = {"switch2019.project.infrastructure.repositories",
        "switch2019.project.applicationLayer", "switch2019.project.controllerLayer"})
@RestController
public class US003AddMemberToGroupControllerRest implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(US003AddMemberToGroupControllerRest.class, args);
    }

    @Autowired
    PersonRepository personRepository;
    @Autowired
    GroupsRepository groupRepository;
    @Autowired
    US003AddMemberToGroupService service;
    @Autowired
    US003AddMemberToGroupController controller;

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("ApplicationRunner - Started");
        Bootstrapping();
        System.out.println("ApplicationRunner - Finished");
    }

    private void Bootstrapping() {

        //Add people to Repository
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jose.cardoso@hotmail.com"));
        personRepository.createPerson("José", new DateAndTime(1995, 12, 13), new Address("Miragaia"),
                new Address("Rua X", "Porto", "4520-266"), new Email("father@isep.ipp.pt"));
        personRepository.createPerson("José Cardoso", new DateAndTime(1995, 1, 13), new Address("Miragaia"),
                new Address("Rua das Flores", "Porto", "4000-189"), new Email("jo.cardoso@hotmail.com"));

        //Add groups to Repository
        groupRepository.createGroup(new Description("familia"),
                personRepository.findPersonByEmail(new Email("jose.cardoso@hotmail.com")));
        groupRepository.createGroup(new Description("canto"),
                personRepository.findPersonByEmail(new Email("father@isep.ipp.pt")));
    }

    @GetMapping("/addMemberToGroup")
    public String addMemberToGroup(@RequestParam(value = "personEmail") String personEmail,
                                   @RequestParam(value = "groupDescription") String groupDescription){

        boolean memberAdded = controller.addMemberToGroup(personEmail, groupDescription);

        return String.format("Result %b!", memberAdded);
    }
}