package bg.startit.role;

import java.util.List;

public interface RoleService
{

   Role getById(Long id);

   List<Role> getAll();

   Role insert(Role book);

   void delete(Long id);

   Role findByRoleName(String name);
}
