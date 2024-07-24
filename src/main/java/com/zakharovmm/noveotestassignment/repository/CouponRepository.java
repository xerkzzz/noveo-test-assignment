package com.zakharovmm.noveotestassignment.repository;

import com.zakharovmm.noveotestassignment.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCode(String code);

    @Query("SELECT c.code FROM Coupon c")
    Set<String> findAllCouponCodes();
}
