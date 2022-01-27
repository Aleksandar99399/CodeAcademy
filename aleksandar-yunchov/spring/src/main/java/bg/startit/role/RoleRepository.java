package bg.startit.role;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long>
{
   @Query("select r from Role r where r.role_name=:name")
   Role findByRole_name(Role.ROLE_NAME name);
}
