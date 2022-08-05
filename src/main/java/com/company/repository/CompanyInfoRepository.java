package com.company.repository;

import com.company.entity.Companyinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends JpaRepository<Companyinfo, String> {

    Optional<Companyinfo> findByCompanyCode(String companyCode);

    void deleteByCompanyCode(String companyCode);
}
