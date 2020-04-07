package switch2019.project.springBoot.unit;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;

@Profile("test")
@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public US003AddMemberToGroupService service() {
        return Mockito.mock(US003AddMemberToGroupService.class);
    }

    @Bean
    @Primary
    public US007CreateGroupAccountService serviceUS007 ()  {
        return Mockito.mock(US007CreateGroupAccountService.class);
    }
}
