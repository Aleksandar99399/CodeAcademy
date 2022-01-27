package company.service.impl;

import company.exception.InvalidTypeEntityException;
import company.exception.SortTypeInvalidException;
import company.exception.individual.IndividualNotFoundException;
import company.model.SortType;
import company.model.TypeEntity;
import company.model.individual.CreateIndividualRequest;
import company.model.individual.Individual;
import company.model.individual.IndividualByCriteriaResponse;
import company.model.individual.UpdateIndividualRequest;
import company.repository.IndividualDao;
import company.service.CompanyService;
import company.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class IndividualServiceImpl implements IndividualService
{
   private final IndividualDao  individualDao;
   private final CompanyService companyService;

   @Autowired
   public IndividualServiceImpl(IndividualDao individualDao, CompanyService companyService)
   {
      this.individualDao = individualDao;
      this.companyService = companyService;
   }

   @Override
   public List<Individual> getAllIndividuals()
   {
      return individualDao.getAllIndividuals();
   }

   @Override
   public Individual getIndividualById(Long id)
   {
      return individualDao.getIndividualById(id)
         .orElseThrow(() -> new IndividualNotFoundException("Not found individual"));
   }

   @Override
   public Individual createIndividual(CreateIndividualRequest request)
   {
      Individual individual = new Individual();
      individual.setName(request.getName());

      individual.setCompanyId(
         companyService.getCompanyById(request.getCompanyId()).getCompanyId()
      );

      try {
         TypeEntity typeEntity = TypeEntity.valueOf(request.getTypeEntity().toUpperCase());
         individual.setTypeEntity(typeEntity);
      }
      catch (Exception ex) {
         throw new InvalidTypeEntityException("Invalid type entity");
      }

      return individualDao.createIndividual(individual);
   }

   @Override
   public Individual updateIndividual(Long id, UpdateIndividualRequest request)
   {
      Individual individual = this.getIndividualById(id);
      companyService.getCompanyById(request.getCompanyId());

      try {
         request.setTypeEntity(request.getTypeEntity().toUpperCase());
         TypeEntity.valueOf(request.getTypeEntity());
      }
      catch (Exception ex) {
         throw new InvalidTypeEntityException("Invalid type entity");
      }

      return individualDao.updateIndividual(individual.getIndivId(), request);
   }

   @Override
   public void deleteIndividual(Long id)
   {
      Individual individual = this.getIndividualById(id);
      individualDao.deleteIndividual(individual.getIndivId());
   }

   @Override
   public List<IndividualByCriteriaResponse> getAllIndividualsByCriteria(Pageable pageable, Map<String, String> search)
   {

      String regex = this.getRegex(this.getParametersForSearch(search));
      Sort sort;
      String sortType;
      if (!pageable.getSort().isSorted()) {
         sortType = SortType.values()[0].toString();
         sort = Sort.by(sortType).ascending();

      }
      else {
         try {
            String[] splitParameter = pageable.getSort().toString().split(":\\s*");
            sortType = SortType.valueOf(splitParameter[0].toUpperCase()).toString();

            if (sortType.equals("COMPANY_NAME")) {
               sortType = "C2.NAME";
            }
            else if (sortType.equals("ISSUE_DATE")) {
               sortType = "INV.ISSUE_DATE";
            }
            else if (sortType.equals("INDIV_NAME")) {
               sortType = "INDIV.NAME";
            }
            sort = Sort.by(sortType);

            if (splitParameter[1].equalsIgnoreCase("DESC")) {
               sort = sort.descending();
            }else {
               sort = sort.ascending();
            }
         }
         catch (Exception ex) {
            throw new SortTypeInvalidException("Passing sort type is invalid");
         }
      }
      pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), sort);

      return individualDao.getAllIndividualsByCriteria(pageable, regex);
   }

   private Map<String, String> getParametersForSearch(Map<String, String> search)
   {
      Map<String, String> rowAndValue = new HashMap<>();
      for (Map.Entry<String, String> entry : search.entrySet()) {

         if (entry.getKey().equalsIgnoreCase("company_name")) {
            rowAndValue.put("C2.NAME", entry.getValue());
         }
         else if (entry.getKey().equalsIgnoreCase("indiv_name")) {
            rowAndValue.put("INDIV.NAME", entry.getValue());
         }
      }
      return rowAndValue;
   }

   private String getRegex(Map<String, String> parameters)
   {
      StringBuilder builder = new StringBuilder();

      for (Map.Entry<String, String> entry : parameters.entrySet()) {
         builder.append(" AND REGEXP_LIKE(" + entry.getKey() + ", '" + entry.getValue() + "', 'i')");
      }
      return builder.toString();
   }

}
