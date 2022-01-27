package company.service;

import company.model.company.Company;

import java.util.List;

public interface CompanyService
{
   Company createCompany(String name);

   List<Company> getAllCompanies();

   Company getCompanyById(Long id);

   Company updateCompany(Long id, Company company);

   void deleteCompany(Long id);
}
