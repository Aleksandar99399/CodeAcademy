package company.repository;

import company.model.reference.Reference;

import java.util.List;
import java.util.Optional;

public interface ReferenceDao
{
   List<Reference> getAll();

   Optional<Reference> getReferenceByExtId(Long id);

   Reference createReference(Long indivId);

   Reference changeIndivIdOfReference(Long externalRef, Long indivId);

   void deleteReference(Long externalRef);
}
