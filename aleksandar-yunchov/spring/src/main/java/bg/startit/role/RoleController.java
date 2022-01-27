package bg.startit.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController
{

   private final RoleService roleService;

   @Autowired
   public RoleController(RoleService roleService)
   {
      this.roleService = roleService;
   }

   @GetMapping("/getAll")
   public ResponseEntity<List<Role>> getAll()
   {
      List<Role> all = roleService.getAll();
      return new ResponseEntity<>(all, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Role> getById(@PathVariable(name = "id") Long id)
   {
      Role role = roleService.getById(id);
      return new ResponseEntity<>(role, HttpStatus.OK);
   }

   @PostMapping("/create")
   public ResponseEntity<Role> createRole(@RequestBody Role role)
   {
      Role insert = roleService.insert(role);
      return new ResponseEntity<>(insert, HttpStatus.CREATED);
   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable("id") Long id)
   {
      roleService.delete(id);

   }

   @PutMapping
   public ResponseEntity<Role> update(@RequestBody Role role)
   {
      Role insert = roleService.insert(role);
      return new ResponseEntity<>(insert, HttpStatus.OK);
   }

}
