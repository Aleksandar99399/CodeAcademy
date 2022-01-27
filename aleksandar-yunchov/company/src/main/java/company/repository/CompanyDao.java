package company.repository;

import company.model.company.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDao
{
   List<Company> getAllCompanies();

   Company createCompany(String name);

   Optional<Company> getCompanyById(Long id);

   Company updateCompany(Long id, Company company);

   void deleteCompany(Long id);
}
