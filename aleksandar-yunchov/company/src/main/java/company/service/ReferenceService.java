package company.service;

import company.model.reference.Reference;

import java.util.List;

public interface ReferenceService
{
   List<Reference> getAll();

   Reference getReferenceByExt(Long id);

   Reference createReference(Long indivId);

   Reference changeIndivIdOfReference(Long extId, Long indivId);

   void deleteReference(Long extId);
}
