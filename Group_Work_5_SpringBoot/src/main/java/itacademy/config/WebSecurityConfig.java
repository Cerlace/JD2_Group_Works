package itacademy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests.requestMatchers(antMatcher("/engines/add")).hasAuthority("ADMIN")
                        .requestMatchers(antMatcher("/engines/delete/*")).hasAuthority("ADMIN")
                        .requestMatchers(antMatcher("/engines/edit/*")).hasAuthority("ADMIN")
                        .requestMatchers(antMatcher("/engines")).hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(antMatcher("/cars/save/*")).hasAuthority("ADMIN")
                        .requestMatchers(antMatcher("/cars/delete/*")).hasAuthority("ADMIN")
                        .requestMatchers(antMatcher("/cars/*")).hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/cars", true)
                        .permitAll()).logout(logout -> logout.logoutSuccessUrl("/login"));

        return http.build();
    }
}
