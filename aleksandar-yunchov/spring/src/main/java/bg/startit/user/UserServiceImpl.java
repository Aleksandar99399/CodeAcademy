package bg.startit.user;

import bg.startit.exception.global.NotFoundException;
import bg.startit.exception.userexceptions.ExistUserException;
import bg.startit.role.Role;
import bg.startit.role.RoleService;
import bg.startit.user.dto.ResponseUser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService
{
   private final UserRepository        repository;
   private final RoleService           roleService;

   private final ModelMapper           modelMapper = new ModelMapper();
   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   public UserServiceImpl(UserRepository userRepository, RoleService roleService)
   {
      this.repository = userRepository;
      this.roleService = roleService;
   }

   @Override
   public ResponseUser getById(Long id)
   {

      Optional<User> byId = repository.findById(id);

      if (!byId.isPresent()){
         throw new NotFoundException("Не е намерен потребител с ID: " + id);
      }
//      ResponseUser responseUser = repository.findById(id)
//         .map(e -> this.modelMapper.map(e, ResponseUser.class))
//         .orElseThrow(() -> new NotFoundException("Не е намерен потребител с ID: " + id));

      return modelMapper.map(byId.get(),ResponseUser.class);
   }

   @Override
   public User getUserByEmail(String email)
   {
      if (!checkUserExist(email)) {
         throw new NotFoundException("Потребителят не съществува");
      }
      return repository.findByEmail(email).get();
   }


   @Override
   public User getUserById(Long id)
   {

      if (checkUserExist(id)) {
         return repository.findById(id).get();
      }
      else {
         throw new NotFoundException("Не е намерен потребител с ID: " + id);
      }

   }

   @Override
   public List<ResponseUser> getAll(Pageable pageable)
   {
      if (repository.count() > 0) {
         Page<User> users = repository.findAll(pageable);
         List<ResponseUser> collect = users.stream()
            .map(u -> this.modelMapper.map(u, ResponseUser.class)).collect(Collectors.toList());
         return collect;
      }
      else {
         throw new NotFoundException("Не са намерени съществуващи потребители");
      }
   }


   @Override
   public void delete(Long id)
   {
      User user = repository.findById(id)
         .orElseThrow(() -> new NotFoundException("Не е намерен потребител с ID: " + id));
      repository.delete(user);

   }

   @Override
   public User register(String firstName, String lastName, String email, String password)
   {
      if (checkUserExist(email)) {
         throw new ExistUserException("User with this email already exist");
      }
      User user = insertDataForUser(firstName, lastName, email, password);
      Role role = roleService.findByRoleName("USER");
      user.setRoles(Arrays.asList(role));
      return repository.save(user);


   }

   @Override
   public ResponseUser update(String firstName,
                              String lastName,
                              String email,
                              String password,
                              List<Role> inputRoles,
                              Long id)
   {

      // Ако не съществува exception
      User user = this.getUserById(id);
      List<Role> roles = user.getRoles();
      roles.addAll(inputRoles);

      user = insertDataForUser(firstName, lastName, email, password);
      user.setId(id);
      user.setRoles(roles);
      repository.save(user);

      return this.modelMapper.map(user, ResponseUser.class);

   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
   {
      if (!repository.findByEmail(email).isPresent()) {
         throw new UsernameNotFoundException("Не съществува такъв User");
      }
      User user = repository.findByEmail(email).get();

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());

   }

   private User insertDataForUser(String firstName, String lastName, String email, String password)
   {
      User user = new User();
      user.setFirstName(firstName);
      user.setLastName(lastName);
      user.setEmail(email);
      password = passwordEncoder.encode(password);
      user.setPassword(password);
      return user;
   }

   @Override
   public boolean checkUserExist(Long id)
   {
      return repository.findById(id).isPresent();
   }

   private boolean checkUserExist(String email)
   {
      return repository.findByEmail(email).isPresent();
   }
}
