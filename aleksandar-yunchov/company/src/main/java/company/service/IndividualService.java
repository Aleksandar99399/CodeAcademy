package company.service;

import company.model.individual.CreateIndividualRequest;
import company.model.individual.Individual;
import company.model.individual.IndividualByCriteriaResponse;
import company.model.individual.UpdateIndividualRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IndividualService
{
   Individual createIndividual(CreateIndividualRequest individual);

   List<Individual> getAllIndividuals();

   Individual getIndividualById(Long id);

   Individual updateIndividual(Long id, UpdateIndividualRequest request);

   void deleteIndividual(Long id);

   List<IndividualByCriteriaResponse> getAllIndividualsByCriteria(Pageable pageable, Map<String, String> search);
}
