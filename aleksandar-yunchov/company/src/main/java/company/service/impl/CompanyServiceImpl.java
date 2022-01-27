package company.service.impl;

import company.exception.company.CompanyInvalidNameException;
import company.exception.company.CompanyNotFoundException;
import company.model.company.Company;
import company.repository.CompanyDao;
import company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService
{
   private final CompanyDao companyDao;

   @Autowired
   public CompanyServiceImpl(CompanyDao companyDao)
   {
      this.companyDao = companyDao;
   }


   //TODO проверка дали съществува компания с такова име
   @Override
   public Company createCompany(String name)
   {
      if (name.equalsIgnoreCase("null")){
         throw new CompanyInvalidNameException("Invalid name for company");
      }
      return companyDao.createCompany(name);
   }

   @Override
   public List<Company> getAllCompanies()
   {
      return companyDao.getAllCompanies();
   }

   @Override
   public Company getCompanyById(Long id)
   {
      return companyDao.getCompanyById(id).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
   }

   @Transactional
   @Override
   public Company updateCompany(Long id, Company company)
   {
      return companyDao.updateCompany(this.getCompanyById(id).getCompanyId(),company);
   }

   @Transactional
   @Override
   public void deleteCompany(Long id)
   {
      companyDao.deleteCompany(this.getCompanyById(id).getCompanyId());
   }
}
