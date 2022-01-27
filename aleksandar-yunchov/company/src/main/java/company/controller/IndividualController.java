package company.controller;

import company.model.individual.CreateIndividualRequest;
import company.model.individual.Individual;
import company.model.individual.IndividualByCriteriaResponse;
import company.model.individual.UpdateIndividualRequest;
import company.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/individual")
@Validated
public class IndividualController
{
   private final IndividualService individualService;

   @Autowired
   public IndividualController(IndividualService individualService)
   {
      this.individualService = individualService;
   }

   @GetMapping
   public ResponseEntity<List<Individual>> getAllIndividuals()
   {
      List<Individual> allIndividuals = individualService.getAllIndividuals();
      return ResponseEntity.ok(allIndividuals);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Individual> getIndividualById(@PathVariable("id") Long id)
   {

      return ResponseEntity.ok(individualService.getIndividualById(id));
   }

   @PostMapping
   public ResponseEntity<Individual> createIndividual(@Valid @RequestBody CreateIndividualRequest request)
   {
      return ResponseEntity.ok(individualService.createIndividual(request));
   }

   @PatchMapping("/{id}")
   public ResponseEntity<Individual> updateIndividual(@PathVariable("id") Long id,
                                                      @Valid @RequestBody UpdateIndividualRequest request)
   {
      return ResponseEntity.ok(individualService.updateIndividual(id, request));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteIndividual(@PathVariable("id") Long id)
   {
      individualService.deleteIndividual(id);
      return ResponseEntity.ok("Successfully deleted");
   }

   @GetMapping("/search_criteria")
   public ResponseEntity<List<IndividualByCriteriaResponse>> getAllIndividualsByCriteria(
      Pageable pageable,
      @RequestParam(required = false) Map<String, String> allParams)
   {
      return ResponseEntity.ok(
         individualService.getAllIndividualsByCriteria(pageable, allParams));
   }
}
