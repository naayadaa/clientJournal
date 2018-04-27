package naayadaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN").and()
                .withUser("user1").password("pass").roles("USER");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public APIAuthenticationFilter apiAuthenticationProcessingFilter() throws Exception {
        APIAuthenticationFilter authenticationFilter = new APIAuthenticationFilter("/sign_in");
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "/login.html","/css/**","/js/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**",
                "/swagger-resources/**", "/swagger-resources").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
                .and().addFilterAt(apiAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();

    }


}
