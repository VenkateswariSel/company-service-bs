package com.company.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Companyinfo {
    @Id
    @Column(name="companycode")
    private String companyCode;
    @NotNull(message="companyName must not be null")
    @Column(name="companyname")
    private String companyName;
    @NotNull(message="ceo must not be null")
    private String ceo;
    @NotNull(message="companyTurnOver must not be null")
    @Min(value = 100000000, message = "companyTurnOver should not be less than 100000000")
    @Column(name="companyturnover")
    private Integer companyTurnOver;
    @NotNull(message="companyWebsiteUrl must not be null")
    @Column(name="companywebsiteurl")
    private String companyWebsiteUrl;
    @NotNull(message="stockExchange must not be null")
    @Column(name="stockexchange")
    private String stockExchange;
    @Column(name="stockprice")
    private Double stockPrice;
}
