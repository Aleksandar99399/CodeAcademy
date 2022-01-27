package company.controller;

import company.model.company.Company;
import company.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@Validated
public class CompanyController
{

   private final CompanyService companyService;

   public CompanyController(CompanyService companyService)
   {
      this.companyService = companyService;
   }

   @GetMapping
   public ResponseEntity<List<Company>> getAllCompanies(){
      List<Company> allCompanies = companyService.getAllCompanies();
      return ResponseEntity.ok(allCompanies);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id){
      return ResponseEntity.ok(companyService.getCompanyById(id));
   }

   @PostMapping
   public ResponseEntity<Company> createCompany(@NotBlank(message = "Company name cannot be empty ")
                                                   @Pattern(regexp = "^[A-z 0-9]+$")
                                                   @RequestParam String companyName){
      return ResponseEntity.ok(companyService.createCompany(companyName.trim()));
   }

   @PutMapping("/{id}")
   public ResponseEntity<Company> updateCompany(@PathVariable("id") Long id, @Valid @RequestBody Company company){
      return ResponseEntity.ok(companyService.updateCompany(id, company));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteCompany(@PathVariable("id") Long id){
      companyService.deleteCompany(id);
      return ResponseEntity.ok("Successfully deleted");
   }
}
