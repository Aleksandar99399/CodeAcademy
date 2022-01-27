package bg.startit;

import bg.startit.book.Book;
import bg.startit.book.BookRepository;
import bg.startit.role.Role;
import bg.startit.role.RoleRepository;
import bg.startit.user.User;
import bg.startit.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

@SpringBootApplication
@EnableTransactionManagement
public class FirstApplication
{

   public static void main(String[] args)
   {
      ConfigurableApplicationContext cxt = SpringApplication.run(FirstApplication.class, args);
      insertBook(cxt);
      insertRole(cxt);
      insertUser(cxt);
   }

   public static void insertBook(ConfigurableApplicationContext cxt)
   {

      BookRepository br = cxt.getBean(BookRepository.class);
      if (br.count() == 0) {
         Random generator = new Random();

         Book firstBook = new Book();
         firstBook.setAuthor("Иван Вазов");
         firstBook.setPages(generator.nextInt(500) + 50);
         firstBook.setIsbn("452154323242");
         firstBook.setYear(2005);
         firstBook.setTitle("Под игото");
         firstBook.setStock(3);
         firstBook.setPrice(BigDecimal.valueOf(generator.nextInt(267) + ((double) generator.nextInt(100)) / 100));
         br.save(firstBook);

         Book secondBook = new Book();
         secondBook.setAuthor("Димитър Димов");
         secondBook.setPages(generator.nextInt(500) + 50);
         secondBook.setIsbn("585815534453");
         secondBook.setYear(1903);
         secondBook.setTitle("Тютюн");
         secondBook.setStock(3);
         secondBook.setPrice(BigDecimal.valueOf(generator.nextInt(267) + ((double) generator.nextInt(100)) / 100));
         br.save(secondBook);

         Book thirdBook = new Book();
         thirdBook.setAuthor("Елин Пелин");
         thirdBook.setPages(generator.nextInt(500) + 50);
         thirdBook.setIsbn("44575675");
         thirdBook.setYear(1933);
         thirdBook.setTitle("Ян Бибиян");
         thirdBook.setStock(1);
         thirdBook.setPrice(BigDecimal.valueOf(generator.nextInt(267) + ((double) generator.nextInt(100)) / 100));
         br.save(thirdBook);


      }
   }

   public static void insertUser(ConfigurableApplicationContext cxt)
   {
      BCryptPasswordEncoder passwordEncoder = cxt.getBean(BCryptPasswordEncoder.class);
      RoleRepository roleRepository = cxt.getBean(RoleRepository.class);
      UserRepository userRep = cxt.getBean(UserRepository.class);
      if (userRep.count() == 0) {
         User user = new User();
         user.setFirstName("Георги");
         user.setLastName("Георгиев");
         user.setPassword(passwordEncoder.encode("admin"));

         Role userRole = roleRepository.findByRole_name(Role.ROLE_NAME.USER);
         Role librarian = roleRepository.findByRole_name(Role.ROLE_NAME.LIBRARIAN);
         Role admin = roleRepository.findByRole_name(Role.ROLE_NAME.ADMIN);
         user.setRoles(Arrays.asList(userRole, librarian, admin));
         user.setEmail("admin@startit.bg");

         userRep.save(user);
      }
   }

   public static void insertRole(ConfigurableApplicationContext cxt)
   {
      RoleRepository roleRepository = cxt.getBean(RoleRepository.class);
      if (roleRepository.count() == 0) {
         Role librarian = new Role();
         librarian.setRole_name(Role.ROLE_NAME.LIBRARIAN);
         roleRepository.save(librarian);

         Role user = new Role();
         user.setRole_name(Role.ROLE_NAME.USER);
         roleRepository.save(user);

         Role admin = new Role();
         admin.setRole_name(Role.ROLE_NAME.ADMIN);
         roleRepository.save(admin);

      }
   }
}
