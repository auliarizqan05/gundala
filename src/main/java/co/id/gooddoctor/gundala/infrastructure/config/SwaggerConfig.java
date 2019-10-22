package co.id.gooddoctor.gundala.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSettlementApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Settlement API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.id.gooddoctor.gundala.domain.settlement.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Settlement API").description("Documentation Gundala API v1.0").build());
    }

    @Bean
    public Docket swaggerUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("User API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("co.id.gooddoctor.gundala.domain.user.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("User API").description("Documentation Gundala API v1.0").build());
    }
}
