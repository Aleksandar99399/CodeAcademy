package company.service.impl;

import company.exception.reference.ReferenceNotFoundException;
import company.model.reference.Reference;
import company.repository.ReferenceDao;
import company.service.IndividualService;
import company.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ReferenceServiceImpl implements ReferenceService
{

   private final ReferenceDao referenceDao;
   private final IndividualService individualService;

   @Autowired
   public ReferenceServiceImpl(ReferenceDao referenceDao, IndividualService individualService)
   {
      this.referenceDao = referenceDao;
      this.individualService = individualService;
   }

   @Override
   public List<Reference> getAll()
   {

      return referenceDao.getAll();
   }

   @Override
   public Reference getReferenceByExt(Long id)
   {
      return referenceDao.getReferenceByExtId(id)
         .orElseThrow(() -> new ReferenceNotFoundException("Reference not found"));
   }

   @Override
   public Reference createReference(Long indivId)
   {
      individualService.getIndividualById(indivId);
      return referenceDao.createReference(indivId);
   }

   @Override
   public Reference changeIndivIdOfReference(Long extId, Long indivId)
   {
      individualService.getIndividualById(indivId);
      Reference referenceByExt = this.getReferenceByExt(extId);
      return referenceDao.changeIndivIdOfReference(referenceByExt.getExternalRef(), indivId);
   }

   @Override
   public void deleteReference(Long extId)
   {
       this.getReferenceByExt(extId);
      referenceDao.deleteReference(extId);
   }
}
