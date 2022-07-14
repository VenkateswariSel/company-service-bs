package com.company.service;

import com.company.dto.CompanyInfoDetails;
import com.company.entity.CompanyInfo;
import com.company.exception.CompanyNotFoundException;
import com.company.repository.CompanyInfoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyInfoDetailsService {

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private ModelMapper modelMapper;

    public CompanyInfoDetails getCompanyDetails(String companyCode){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Optional<CompanyInfo> companyOptional = companyInfoRepository.findByCompanyCode(companyCode);
        if(companyOptional.isPresent()){
            CompanyInfo companyInfo = companyOptional.get();
            CompanyInfoDetails companyInfoDetails = modelMapper.map(companyInfo, CompanyInfoDetails.class);
            return companyInfoDetails;
        }
        else{
            throw new CompanyNotFoundException("No company found for the companyCode");
        }

    }

    public List<CompanyInfoDetails> getAllCompaniesDetails() {
        List<CompanyInfoDetails> companyDetailsList = new ArrayList<>();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        companyInfoRepository.findAll().stream().forEach((company)->{
            CompanyInfoDetails companyDetails = modelMapper.map(company, CompanyInfoDetails.class);
            companyDetailsList.add(companyDetails);
        });
        return companyDetailsList;
    }

    public void addStock(String companyCode, Double stock) {
        Optional<CompanyInfo> optionalCompany = companyInfoRepository.findByCompanyCode(companyCode);
        if(optionalCompany.isPresent()){
            CompanyInfo company = optionalCompany.get();
            company.setStockPrice(stock);
            companyInfoRepository.save(company);
            stockInfoService.sendStockDetails(companyCode,stock);
        }
        else{
            throw new CompanyNotFoundException("No company found for the companyCode");
        }

    }
}

