package bg.codix.chat.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Configuration
public class ConfigBean
{
   @Bean
   public KeyHolder keyHolder(){
      return new GeneratedKeyHolder();
   }

}
