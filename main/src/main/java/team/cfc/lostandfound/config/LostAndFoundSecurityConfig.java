package team.cfc.lostandfound.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import team.cfc.lostandfound.security.config.SecurityConfig;
import team.cfc.lostandfound.service.WxUserService;

@Configuration
@EnableWebSecurity
public class LostAndFoundSecurityConfig extends SecurityConfig {

    @Autowired
    WxUserService wxUserService;

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> wxUserService.loadUserByOpenId(username);
    }
}
