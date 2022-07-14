package com.company.controller;

import com.company.dto.CompanyInfoDetails;
import com.company.dto.Response;
import com.company.entity.CompanyInfo;
import com.company.service.CompanyInfoDetailsService;
import com.company.service.CompanyInfoRegistrationService;
import com.company.service.StockInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/market/company")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoRegistrationService companyInfoRegistrationService;

    @Autowired
    private CompanyInfoDetailsService companyInfoDetailsService;

    @Autowired
    private StockInfoService stockInfoService;

    public CompanyInfoController(CompanyInfoRegistrationService companyInfoRegistrationService, CompanyInfoDetailsService companyInfoDetailsService, StockInfoService stockInfoService){
        this.companyInfoRegistrationService = companyInfoRegistrationService;
        this.companyInfoDetailsService = companyInfoDetailsService;
        this.stockInfoService = stockInfoService;
    }

    @PostMapping("/register")
    public ResponseEntity<CompanyInfo> registerCompany(@Validated @RequestBody CompanyInfo company){
        log.info("Request received for adding new Company");
        CompanyInfo registeredCompany = companyInfoRegistrationService.registerCompany(company);
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }

    @PostMapping("/add/{companycode}")
    public ResponseEntity<Response> addStock(@RequestParam Double stock, @PathVariable("companycode") String companyCode){
        log.info("Request received for adding stock to a Company with companycode {}",companyCode);
        companyInfoDetailsService.addStock(companyCode,stock);
        Response response = new Response("Stock was added successfully",HttpStatus.OK.value(), ZonedDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/info/{companycode}")
    public ResponseEntity<CompanyInfoDetails> retrieveCompanyDetails(@PathVariable("companycode") String companyCode){
        log.info("Request received for retrieving company details with companycode as {}",companyCode);
        CompanyInfoDetails companyInfoDetails = companyInfoDetailsService.getCompanyDetails(companyCode);
        return new ResponseEntity<CompanyInfoDetails>(companyInfoDetails,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CompanyInfoDetails>> retrieveAllCompanyDetails(){
        log.info("Request received for retrieving all company details");
        List<CompanyInfoDetails> companyInfoDetailsList = companyInfoDetailsService.getAllCompaniesDetails();
        return new ResponseEntity<List<CompanyInfoDetails>>(companyInfoDetailsList,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{companycode}")
    public String deleteCompany(@PathVariable("companycode") String companyCode){
        log.info("Request received to delete the company details with companycode as {}",companyCode);
        companyInfoRegistrationService.deleteCompany(companyCode);
        return "Deleted Successfully";

    }

}
