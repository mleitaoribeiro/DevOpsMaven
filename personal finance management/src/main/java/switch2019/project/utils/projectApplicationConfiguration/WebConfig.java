package switch2019.project.utils.projectApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //This stops problems when using emails and collections of emails in the URI
        configurer.useRegisteredExtensionsOnly(true);
        configurer.replaceMediaTypes(Collections.emptyMap());
        configurer.favorParameter(true);
    }
}