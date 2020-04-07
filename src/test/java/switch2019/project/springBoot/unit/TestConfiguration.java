package switch2019.project.springBoot.unit;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import switch2019.project.applicationLayer.US003AddMemberToGroupService;

@Profile("test")
@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    public US003AddMemberToGroupService service() {
        return Mockito.mock(US003AddMemberToGroupService.class);
    }
}
