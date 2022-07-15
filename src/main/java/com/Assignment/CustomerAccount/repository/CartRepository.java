package com.Assignment.CustomerAccount.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Assignment.CustomerAccount.entity.Cart;


@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer>{

	
	@Query(value="select * from Cart where checked_out=FALSE and cus_id=:cus_id",nativeQuery=true)
	List<Cart> findAllBycustomer(@Param("cus_id") int cus_id);

	List<Cart> findBycusId(int cusId);
	
	@Modifying
	@Query(value="UPDATE Cart SET checked_out = TRUE where cart_id = :cart_id",nativeQuery=true)
	void updateCheckedout(@Param("cart_id") int cart_id);

}
