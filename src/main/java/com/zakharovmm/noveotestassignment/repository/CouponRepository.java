package com.zakharovmm.noveotestassignment.repository;

import com.zakharovmm.noveotestassignment.entity.Coupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCode(String code);
}
