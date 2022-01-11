package org.sid.customerservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; //database courant !!!

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()  //ignorer la sécurité de..
                .antMatchers("/resources/**", "/static/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()  // Login Page de Spring Security // add login authentication also the login page has been created
                .loginPage("/login");
        http.authorizeRequests().antMatchers("/List**/**", "/template**/**").hasRole("ADMIN"); //droit d'accès au List ADMIN
        http.authorizeRequests().antMatchers("/template**/**").hasRole("USER"); //droit d'accès au tamplate USER
        http.csrf(); //activation contre attaque csrf + .disable()
        http.authorizeRequests().antMatchers("/user/***","/login","//maxcdn**/**","//code**/**").permitAll(); // les requests avec user/... est autorisé
        http.authorizeRequests().anyRequest().authenticated(); // all requests http has been authenticated
        http.exceptionHandling().accessDeniedPage("/notAuthorized");  //you need to add /errorPage "Vous n'étes pas autorisé"
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        System.out.println("#########################");
        System.out.println("admin encrypt: "+passwordEncoder.encode("1234"));
        System.out.println("#########################");
        //Authentication avec user ou admin, creation basic via JDBC
        auth.jdbcAuthentication()
                .dataSource(dataSource)       //principal            //credentials
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .passwordEncoder(passwordEncoder)
                .rolePrefix("ROLE_");

        /* Authentication avec user ou admin, creation basic via inMemoryAuthentication
        auth.
                inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN", "USER");
        auth.
                inMemoryAuthentication()
                .withUser("driss")
                .password(passwordEncoder.encode("driss"))  //{noop}+ = no password encoder
                .roles("USER");
        auth.
                inMemoryAuthentication()
                .withUser("hanane")
                .password(passwordEncoder.encode("hanane"))
                .roles("USER");
         */

    }
}
