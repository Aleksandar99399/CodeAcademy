package bg.startit.security;

import bg.startit.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

   @Autowired
   private UserServiceImpl userService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception
   {
      auth.userDetailsService(userService);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      System.out.println("In Security");
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.httpBasic().and()
         .csrf().disable()
         .authorizeRequests()
         //books
         .mvcMatchers(HttpMethod.GET, "/api/v1/books/**").authenticated()
         .mvcMatchers(HttpMethod.POST, "/api/v1/books").hasAnyRole("ADMIN", "LIBRARIAN")
         .mvcMatchers(HttpMethod.PUT, "/api/v1/books").hasAnyRole("ADMIN", "LIBRARIAN")
         .mvcMatchers(HttpMethod.DELETE, "/api/v1/books").hasAnyRole("ADMIN", "LIBRARIAN")
         //comments
         .mvcMatchers("/api/v1/comments/**").authenticated()
         //history
         .mvcMatchers(HttpMethod.GET,"/api/v1/history/current_user_books").authenticated()
         .mvcMatchers(HttpMethod.PUT,"/api/v1/history/return_book/{history_id}").authenticated()
         .mvcMatchers("/api/v1/history/**").hasAnyRole("ADMIN", "LIBRARIAN")
         //role
         .mvcMatchers("/api/v1/roles/**").hasAnyRole("ADMIN", "LIBRARIAN")
         //user
         .mvcMatchers(HttpMethod.GET,"/api/v1/users").hasAnyRole("ADMIN", "LIBRARIAN")
         .mvcMatchers(HttpMethod.GET,"/api/v1/users/**").authenticated()
         .mvcMatchers(HttpMethod.DELETE,"/api/v1/users/**").hasAnyRole("ADMIN", "LIBRARIAN")
         .mvcMatchers(HttpMethod.PUT,"/api/v1/users/**").authenticated()
         .mvcMatchers(HttpMethod.POST,"/api/v1/users/register").permitAll()
         .mvcMatchers("/h2").permitAll();



      http.headers().frameOptions().disable();
   }


}
