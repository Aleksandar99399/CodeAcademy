package company.controller;

import company.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController
{
   private final RoleService roleService;

   @Autowired
   public RoleController(RoleService roleService)
   {
      this.roleService = roleService;
   }

   @PostMapping
   public void createRole(@RequestParam String name){
      roleService.createRole(name);
   }
}
