package bg.startit.role;

import bg.startit.exception.userexceptions.InvalidUserRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{

   private final RoleRepository repository;

   @Autowired
   public RoleServiceImpl(RoleRepository repository)
   {
      this.repository = repository;
   }

   @Override
   public Role getById(Long id)
   {
      return repository.findById(id).get();
   }

   @Override
   public List<Role> getAll()
   {
      return repository.findAll();
   }

   @Override
   public Role insert(Role role)
   {
      Role saveRole = new Role();
      saveRole.setRole_name(role.getRole_name());

      return repository.save(saveRole);
   }

   @Override
   public void delete(Long id)
   {
      repository.deleteById(id);
   }

   @Override
   public Role findByRoleName(String name)
   {
      try {
         Role.ROLE_NAME roleName = Role.ROLE_NAME.valueOf(name.toUpperCase());
         return repository.findByRole_name(roleName);
      }
      catch (IllegalArgumentException ex) {
         throw new InvalidUserRoleException("Role not exist");
      }
   }
}
