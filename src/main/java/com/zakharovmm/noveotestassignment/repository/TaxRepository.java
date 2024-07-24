package com.zakharovmm.noveotestassignment.repository;

import com.zakharovmm.noveotestassignment.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
