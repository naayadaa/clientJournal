package naayadaa.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;

/**
 * Created by AnastasiiaDepenchuk on 04-Aug-17.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket enable(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(Predicates.or(PathSelectors.regex("/client-journal-admin-dashboard/.*"),
                        PathSelectors.regex("/api/swagger.*")))
                .build();
    }
}
