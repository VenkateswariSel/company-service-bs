package com.company.repository;

import com.company.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {

    Optional<CompanyInfo> findByCompanyCode(String companyCode);

    void deleteByCompanyCode(String companyCode);
}
