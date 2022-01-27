package bg.startit.user;


import bg.startit.role.Role;
import bg.startit.user.dto.ResponseUser;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService
{
   ResponseUser getById(Long id);

   User getUserByEmail(String email);

   User getUserById(Long id);

   List<ResponseUser> getAll(Pageable pageable);

   ResponseUser update(String firstName, String lastName, String email, String password, List<Role> roles, Long id);

   void delete(Long id);

   User register(String firstName, String lastName, String email, String password);

   boolean checkUserExist(Long id);
}
