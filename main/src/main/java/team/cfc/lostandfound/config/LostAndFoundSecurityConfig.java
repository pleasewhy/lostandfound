package team.cfc.lostandfound.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import team.cfc.lostandfound.bo.AdminDetails;
import team.cfc.lostandfound.bo.WxUserDetails;
import team.cfc.lostandfound.model.Admin;
import team.cfc.lostandfound.security.config.SecurityConfig;
import team.cfc.lostandfound.service.AdminService;
import team.cfc.lostandfound.service.WxUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class LostAndFoundSecurityConfig extends SecurityConfig {

    @Autowired
    WxUserService wxUserService;

    @Autowired
    AdminService adminService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserDetails admin = adminService.loadUserByUsername(username);
            if(admin!=null) {
                return admin;
            }
            UserDetails wxUserDetails = wxUserService.loadUserByUsername(username);
            if (wxUserDetails!=null){
                return wxUserDetails;
            }
            throw new UsernameNotFoundException("用户名或者密码错误");
        };
    }
}
