package com.zakharovmm.noveotestassignment.repository;

import com.zakharovmm.noveotestassignment.entity.Tax;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    @Query(value = "SELECT * FROM taxes WHERE :taxNumber ~ regex_pattern", nativeQuery = true)
    Optional<Tax> findTaxByTaxNumber(@Param("taxNumber") String taxNumber);
}
