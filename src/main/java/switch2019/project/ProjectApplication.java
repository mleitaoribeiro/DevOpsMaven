package switch2019.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import switch2019.project.infrastructure.dataLoader.Bootstrapper;

@SpringBootApplication(scanBasePackages = {"switch2019.project.controllerLayer",
        "switch2019.project.infrastructure", "switch2019.project.applicationLayer"})

public class ProjectApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Autowired
    Bootstrapper bootstrapper;

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("ApplicationRunner - Started");
        bootstrapper.bootstrapping();
        System.out.println("ApplicationRunner - Finished");
    }
}