package auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MySecurity extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    MyDaoAuthenticationProvider myDaoAuthenticationProvider() {
        MyDaoAuthenticationProvider myDaoAuthenticationProvider = new MyDaoAuthenticationProvider();
        myDaoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        myDaoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return myDaoAuthenticationProvider;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(myDaoAuthenticationProvider()));
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode("123456");
        manager.createUser(User.withUsername("rootadmin").password(password).roles("admin").build());
        manager.createUser(User.withUsername("user").password(password).roles("admin").build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", 200);
                    result.put("message", "登陆成功");
                    ObjectMapper mapper = new ObjectMapper();
                    String s = mapper.writeValueAsString(result);
                    resp.getWriter().write(s);
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    Map<String, Object> result = new HashMap<>();
                    result.put("status", 500);
                    result.put("message", "登陆失败");
                    ObjectMapper mapper = new ObjectMapper();
                    String s = mapper.writeValueAsString(result);
                    resp.getWriter().write(s);
                })
                .permitAll()
                .and()
                .csrf().disable();
    }
}
