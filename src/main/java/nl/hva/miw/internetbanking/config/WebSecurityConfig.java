package nl.hva.miw.internetbanking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception { // Spring security vooralsnog
        // omzeilen
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .csrf()
                .disable();

        //                .authorizeRequests()
        //                .antMatchers("/css/**", "/js/**", "/images/**", "/", "/home").permitAll()
        //                .anyRequest().authenticated()
        //                .and()
        //                .formLogin()
        //                .loginPage("/login")
        //                .permitAll()
        //                .and()
        //                .logout() // (6)
        //                .permitAll()
        //                .and()
        //                .httpBasic();
    }

}
