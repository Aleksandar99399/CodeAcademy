package company.config;

import company.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigureSecurity extends WebSecurityConfigurerAdapter
{

   private final EmployeeServiceImpl employeeService;

   @Autowired
   public ConfigureSecurity(EmployeeServiceImpl employeeService)
   {
      this.employeeService = employeeService;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception
   {
      auth.userDetailsService(employeeService);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      //here we must set exclusion - example: paths with permitAll
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.httpBasic();
         //employee
         //role
         //company
         //individual
         //invoice
         //reference
      http.csrf().disable();
   }
}
