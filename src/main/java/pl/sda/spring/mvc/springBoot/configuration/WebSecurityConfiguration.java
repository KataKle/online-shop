package pl.sda.spring.mvc.springBoot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userServiceDetails;

    @Autowired
    public WebSecurityConfiguration(@Qualifier("userServiceDetailsImpl") UserDetailsService userServiceDetails) {
        this.userServiceDetails = userServiceDetails;
    }

    private static final String[] MATCHERS = {
            "/",
            "/products**",
            "/products/**",
            "/registration",
            "/register",
            "/login*",
            "/webjars/**"
    };

    private static final String[] MATCHERS_ADMIN = {
            "/admin**",
            "/admin/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(MATCHERS)
                .permitAll()
                .antMatchers(MATCHERS_ADMIN)
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll().and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
