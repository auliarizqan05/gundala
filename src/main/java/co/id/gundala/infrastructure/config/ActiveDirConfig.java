// package co.id.gundala.infrastructure.config;

// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

// @Configuration
// @PropertySource("classpath:application.properties")
// @Slf4j
// public class ActiveDirConfig {

//     @Value("${ad.domain:localhost}")
//     private String domain;

//     @Value("${ad.url:localhost}")
//     private String url;

//     @Bean
//     public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
//         ActiveDirectoryLdapAuthenticationProvider provider =
//                 new ActiveDirectoryLdapAuthenticationProvider(domain, url);

//         provider.setSearchFilter("ou=GDT Users");
//         provider.setConvertSubErrorCodesToExceptions(true);
//         provider.setUseAuthenticationRequestCredentials(true);

//         log.info("url for active directory : {} -  {}", domain, url);

//         return provider;
//     }

// }
