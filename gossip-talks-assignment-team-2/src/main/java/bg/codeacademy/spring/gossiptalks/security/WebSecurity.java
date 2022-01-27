package bg.codeacademy.spring.gossiptalks.security;

import bg.codeacademy.spring.gossiptalks.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter
{
   private final UserServiceImpl userService;

   public WebSecurity(UserServiceImpl userService)
   {
      this.userService = userService;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception
   {
      auth.userDetailsService(userService);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      http
         .httpBasic().and()
         .csrf().disable()
         .authorizeRequests()
         .mvcMatchers(HttpMethod.POST, "/api/v1/users/me").authenticated()
         .mvcMatchers(HttpMethod.POST, "/api/v1/users/{username}/follow").authenticated()
         .mvcMatchers(HttpMethod.GET, "/api/v1/users/**").authenticated()
         .mvcMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
         .mvcMatchers("/api/v1/gossips").authenticated();
      http.headers().frameOptions().disable();
   }
}
