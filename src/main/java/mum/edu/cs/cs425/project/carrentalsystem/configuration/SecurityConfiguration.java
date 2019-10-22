package mum.edu.cs.cs425.project.carrentalsystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Qualifier("custom")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
//                .dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // URLs matching for access rights
                .antMatchers("/").permitAll()
                .antMatchers("/car/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/forgot/password").permitAll()
                .antMatchers("/reset/password").permitAll()
                .antMatchers("/security/question/**").permitAll()
                .antMatchers("/cust/register").permitAll()
                .antMatchers("/cust/save").permitAll()
               // .antMatchers("/home/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SELLER", "ROLE_BUYER")
                //.anyRequest().authenticated()
                .antMatchers("/", "/login", "/home","/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/seller/**").hasAuthority("SELLER")
                .antMatchers("/buyer/**").hasAuthority("BUYER")
                .anyRequest().authenticated() //all other urls can be access by any authenticated role
                .and()
                .formLogin() //enable form login instead of basic login
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/").successForwardUrl("/car/list")
                .loginProcessingUrl("/perform_login")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied")
                .and().csrf()
                //.ignoringAntMatchers("/h2-console/**") //don't apply CSRF protection to /h2-console
                .and()
                .exceptionHandling().accessDeniedPage("/error/access-denied")
                .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository());

        http.rememberMe().rememberMeParameter("remember-me").key("uniqueAndSecret");
        http.headers().frameOptions().disable();

             http.csrf().disable().authorizeRequests();
//        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/h2-console/**");
    }
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }
}
