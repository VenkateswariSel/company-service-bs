package com.company.service;

import com.company.entity.CompanyInfo;
import com.company.exception.CompanyAlreadyPresentException;
import com.company.exception.CompanyNotFoundException;
import com.company.repository.CompanyInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CompanyInfoRegistrationService {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    private StockInfoService stockInfoService;

    public CompanyInfo registerCompany(CompanyInfo companyInfo){
        Optional<CompanyInfo> companyOptional = companyInfoRepository.findByCompanyCode(companyInfo.getCompanyCode());
        if(!companyOptional.isPresent()){
            return companyInfoRepository.save(companyInfo);
        }
        else{
            throw new CompanyAlreadyPresentException("Company is already present exception");
        }

    }

    @Transactional
    public void deleteCompany(String companyCode){
        if(companyInfoRepository.findByCompanyCode(companyCode).isPresent()){
            companyInfoRepository.deleteByCompanyCode(companyCode);
            stockInfoService.deleteAllByCompanyCode(companyCode);
        }
        else{
            throw new CompanyNotFoundException("Company with the company code is not present");
        }

    }



}

