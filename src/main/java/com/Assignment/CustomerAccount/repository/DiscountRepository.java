package com.Assignment.CustomerAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Assignment.CustomerAccount.entity.DiscountDetails;


public interface DiscountRepository extends JpaRepository<DiscountDetails, Integer>{



	@Query(value="select * from discount_details where min_quantity<:min_quantity order by min_quantity desc limit 1",nativeQuery=true)
	DiscountDetails findByMinQuantity(@Param("min_quantity")int min_quantity);

	DiscountDetails findByPromoCode(String code);

	
	//SELECT * FROM discount_details WHERE min_quantity<3 ORDER BY min_quantity DESC LIMIT 1;
	
}

