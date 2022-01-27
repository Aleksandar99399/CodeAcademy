package bg.startit.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>
{
   List<User> findByFirstNameOrLastName(String firstName, String lastName);

   Optional<User> findByEmail(String email);
}
