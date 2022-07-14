package com.company.dto;

import lombok.Data;

@Data
public class CompanyInfoDetails {

    private String companyCode;
    private String companyName;
    private String ceo;
    private Integer companyTurnOver;
    private String companyWebsiteUrl;
    private String stockExchange;
    private Double stockPrice;
}
