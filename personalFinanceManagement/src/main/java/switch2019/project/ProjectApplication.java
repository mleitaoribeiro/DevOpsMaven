package switch2019.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import switch2019.project.infrastructure.dataLoader.DataBaseLoader;

@SpringBootApplication(scanBasePackages = {"switch2019.project.controllerLayer",
        "switch2019.project.infrastructure", "switch2019.project.applicationLayer",
        "switch2019.project.utils.projectApplicationConfiguration"})

@EnableWebMvc
public class ProjectApplication implements ApplicationRunner {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProjectApplication.class, args);

        //Necessary for 404:
        DispatcherServlet dispatcher = (DispatcherServlet) context.getBean("dispatcherServlet");
        dispatcher.setThrowExceptionIfNoHandlerFound(true);
    }

    @Autowired
    DataBaseLoader dataBaseLoader;

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("ApplicationRunner - Started");
        dataBaseLoader.bootstrapping();
        System.out.println("ApplicationRunner - Running");
    }
}