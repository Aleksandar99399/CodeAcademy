package bg.codeacademy.spring.gossiptalks.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Beans
{
   @Bean
   public ModelMapper modelMapper()
   {
      return new ModelMapper();
   }

   @Bean
   public BCryptPasswordEncoder getEncoder()
   {
      return new BCryptPasswordEncoder();
   }

}
