package switch2019.project.controllerLayer.cli;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import switch2019.project.applicationLayer.*;

@Profile("test")
@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public US001AreSiblingsService serviceUS001() {
        return Mockito.mock(US001AreSiblingsService.class);
    }

    @Bean
    @Primary
    public US002_1CreateGroupService serviceUS002_1() {
        return Mockito.mock(US002_1CreateGroupService.class);
    }


    @Bean
    @Primary
    public US003AddMemberToGroupService serviceUS003() {
        return Mockito.mock(US003AddMemberToGroupService.class);
    }

    @Bean
    @Primary
    public US004GetFamilyGroupsService serviceUS004() {
        return Mockito.mock(US004GetFamilyGroupsService.class);
    }


    @Bean
    @Primary
    public US005_1AdminAddsCategoryToGroupService serviceUS005_1() {
        return Mockito.mock(US005_1AdminAddsCategoryToGroupService.class);
    }


    @Bean
    @Primary
    public US006CreatePersonAccountService serviceUS006() {
        return Mockito.mock(US006CreatePersonAccountService.class);
    }

    @Bean
    @Primary
    public US007CreateGroupAccountService serviceUS007() {
        return Mockito.mock(US007CreateGroupAccountService.class);
    }
}
