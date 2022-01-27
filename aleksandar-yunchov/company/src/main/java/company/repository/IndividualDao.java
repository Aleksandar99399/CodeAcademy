package company.repository;

import company.model.individual.Individual;
import company.model.individual.IndividualByCriteriaResponse;
import company.model.individual.UpdateIndividualRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IndividualDao
{
   Individual createIndividual(Individual individual);

   List<Individual> getAllIndividuals();

   Optional<Individual> getIndividualById(Long id);

   Individual updateIndividual(Long indivId, UpdateIndividualRequest request);

   void deleteIndividual(Long indivId);

   List<IndividualByCriteriaResponse> getAllIndividualsByCriteria(Pageable pageable, String regex);
}
