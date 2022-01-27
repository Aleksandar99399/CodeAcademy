package bg.startit.user;

import bg.startit.exception.global.NotFoundException;
import bg.startit.role.Role;
import bg.startit.role.RoleService;
import bg.startit.role.dto.RegisterRoleDto;
import bg.startit.user.dto.RegisterUserDto;
import bg.startit.user.dto.ResponseUser;
import bg.startit.user.dto.UpdateUserDto;
import bg.startit.validation.group.ValidationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated(ValidationSequence.class)
public class UserController
{

   // След Post връщаме Location за get

   private final UserService userService;
   private final RoleService roleService;


   @Autowired
   public UserController(UserService userService, RoleService roleService)
   {
      this.userService = userService;
      this.roleService = roleService;
   }

   @GetMapping
   public ResponseEntity<List<ResponseUser>> getAll(@Min(value = 0) @RequestParam(defaultValue = "0") Integer pageNumber,
                                                    @Min(value = 1) @Max(100) @RequestParam(defaultValue = "100")
                                                       Integer pageCapacity)
   {

      List<ResponseUser> all = this.userService.getAll(PageRequest.of(pageNumber, pageCapacity));
      return new ResponseEntity<>(all, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ResponseUser> getById(@PathVariable(name = "id") @Min(value = 0, message = "Невалидно ID") Long id)
   {
      ResponseUser user = userService.getById(id);
      return new ResponseEntity<>(user, HttpStatus.OK);
   }


   @PostMapping("/register")
   public ResponseEntity<Void> register(@Valid @RequestBody RegisterUserDto registerUser)
   {
      User registeredUser = userService.register(registerUser.getFirstName(),
         registerUser.getLastName(), registerUser.getEmail(), registerUser.getPassword());

      URI location = ServletUriComponentsBuilder
         .fromCurrentContextPath()
         .path("/api/v1/users/{id}")
         .buildAndExpand(registeredUser.getId())
         .toUri();

      return ResponseEntity.created(location).build();

   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable("id") Long id)
   {
      userService.delete(id);

   }

   @PutMapping("/{id}")
   public ResponseEntity<ResponseUser> update(@PathVariable(name = "id") Long id,
                                              @Valid @RequestBody UpdateUserDto updateUser)
   {
      if (!userService.checkUserExist(id)) {
         throw new NotFoundException("Не съществува клиент с ID: " + id);
      }

      List<Role> roles = new ArrayList<>();
      for (RegisterRoleDto role : updateUser.getRoles()) {
         Role byId = roleService.getById(role.getId());
         roles.add(byId);
      }

      ResponseUser insert = userService.update(updateUser.getFirstName(),
         updateUser.getLastName(), updateUser.getEmail(), updateUser.getPassword(), roles, id);

      return new ResponseEntity<>(insert, HttpStatus.OK);
   }

   //може да се добави метод за промяна на паролата
}
