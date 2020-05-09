package com.cg.goms.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.goms.entity.CartItemEntity;


@Repository
public interface CartRepo extends JpaRepository<CartItemEntity,Long>{
	
	public List<CartItemEntity> findAllByUserName(String userName);
	
	@Query("Select sum(c.productPrice) FROM CartItemEntity c WHERE c.userName=:user_name")
	double getTotalAmountByUserName(@Param("user_name") String userName);
	
	@Modifying
    @Transactional
	@Query("update CartItemEntity addCart set addCart.quantity=:qty,addCart.productPrice=:price WHERE addCart.cartId=:cart_id")
	void updateQtyByCartId(@Param("cart_id") long cartId, @Param("price") double productPrice,@Param("qty") long quantity);
	
	@Query("Select addCart  FROM CartItemEntity addCart WHERE addCart.productId= :product_id and addCart.userName=:user_name")
	Optional<CartItemEntity> getCartByProductIdAnduserId(@Param("product_id") String productId,@Param("user_name") String userName);
	
	@Modifying
    @Transactional
	@Query("DELETE  FROM CartItemEntity c WHERE c.cartId =:cart_id   and c.userName=:user_name")
	void deleteCartByIdAndUserName(@Param("user_name")String userName,@Param("cart_id")long cartId);
	@Modifying
    @Transactional
	@Query("DELETE  FROM CartItemEntity c WHERE   c.userName=:user_name")
	void deleteAllCartByUserName(@Param("user_name")String userName);
	


}
