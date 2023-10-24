package learn.jpa.demo.security;
import learn.jpa.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig {
    // tell spring security to look into database for authorization config
    // dataSource will be injected based on connection properties
    // use custom security table
    /*
    @Bean
    @Autowired
    public UserDetailsManager userDetailsManager (DataSource dataSource) {
        // provide query for spring security
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailsManager
                .setUsersByUsernameQuery("select id, password, active from users where id=?");
        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("select u.name, r.name from users u inner join users_roles ur on u.id = ur.user_id inner join roles r on ur.role_id = r.id where u.name = ?");
        return theUserDetailsManager;
    }

     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    // set up security config
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity https) throws Exception{
        // set up access level based on role
        // admin: all access level of student table
        // student: only read access of course
        https.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/mvc/registerForm").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mvc/saveUser").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mvc/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/mvc/**").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/mvc/**").hasAnyRole("STUDENT", "ADMIN")
                        .anyRequest().authenticated()
        );

        // use custom login form
        https.formLogin(form -> form
                .loginPage("/mvc/showLoginForm") // need a controller request mapping
                .loginProcessingUrl("/authenticateTheUser") // no need a controller request mapping, spring magic
                .permitAll());
        // Add logout support for default URL /logout
        https.logout(logout -> logout.permitAll());

        // use HTTP Basic authentication
        https.httpBasic(withDefaults());
        https.csrf((csrf) -> csrf.disable());

        return https.build();
    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}


