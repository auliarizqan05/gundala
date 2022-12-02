package co.id.gundala.infrastructure.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerSettlementApi() {
        return new OpenAPI()
                .info(new Info().title("Gundala API's")
                .description("Documentation Gundala API v1.0")
                .version("v1.0"));
    
    }

    // Define an API group that'll include specific version. Can be helpful in versioning the APIs.
    // @Bean
    // public GroupedOpenApi hideApis() {
    //     return GroupedOpenApi.builder().group("default")
    //             .pathsToExclude("/api/v2/**", "/v2/**", "/**/v3/**")
    //             .pathsToMatch("/v1/**", "/api/v1/**")
    //             .build();

                
    // @Bean
    // public Docket swaggerUserApi() {
    //     return new Docket(DocumentationType.SWAGGER_2)
    //             .groupName("User API")
    //             .select()
    //             .apis(RequestHandlerSelectors.basePackage("co.id.gooddoctor.gundala.domain.user.controller"))
    //             .paths(PathSelectors.any())
    //             .build()
    //             .apiInfo(new ApiInfoBuilder().version("1.0").title("User API").description("Documentation Gundala API v1.0").build());
    // }
}
