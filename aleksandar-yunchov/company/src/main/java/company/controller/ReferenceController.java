package company.controller;

import company.model.reference.Reference;
import company.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reference")
@Validated
public class ReferenceController
{
   private final ReferenceService referenceService;

   @Autowired
   public ReferenceController(ReferenceService referenceService)
   {
      this.referenceService = referenceService;
   }


   @GetMapping
   public ResponseEntity<List<Reference>> getAllReference(){
      return ResponseEntity.ok(referenceService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Reference> getReferenceByExtId(@PathVariable("id") Long id){
      return ResponseEntity.ok(referenceService.getReferenceByExt(id));
   }

   @PostMapping
   public ResponseEntity<Reference> createReference(@RequestParam Long indivId){
      return ResponseEntity.ok(referenceService.createReference(indivId));
   }


   @PatchMapping("/{id}")
   public ResponseEntity<Reference> changeIndivIdOfReference(@PathVariable("id") Long extId,
                                                             @RequestParam Long indivId){
      return ResponseEntity.ok(referenceService.changeIndivIdOfReference(extId, indivId));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteReference(@PathVariable("id") Long extId){
      referenceService.deleteReference(extId);
      return ResponseEntity.ok("Successfully deleted");
   }
}
