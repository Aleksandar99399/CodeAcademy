package bg.startit.security;


import bg.startit.user.User;
import bg.startit.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
   @Autowired
   private UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
   {
      if (!userRepository.findByEmail(email).isPresent()) {
         throw new UsernameNotFoundException("Не съществува такъв User");
      }
      User user = userRepository.findByEmail(email).get();

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());

   }
}
