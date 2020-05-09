package com.cg.goms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.goms.dao.CartRepo;
import com.cg.goms.entity.CartItemEntity;
import com.cg.goms.model.CartItemModel;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private CartRepo repo; 
	
	private CartItemEntity of(CartItemModel source) {
		CartItemEntity result=null;
		if(source!=null) {
			result = new CartItemEntity();
			result.setCartId(0);
			result.setProductId(source.getProductId());
			result.setUserName(source.getUserName());
			result.setQuantity(source.getQuantity());
			result.setProductName(source.getProductName());
			result.setProductPrice(source.getProductPrice());
		}
		return result;
	}
    private CartItemModel of(CartItemEntity source) {
    	CartItemModel result=null;
		if(source!=null) {
			result = new CartItemModel();
			result.setCartId(source.getCartId());
			result.setProductId(source.getProductId());
			result.setUserName(source.getUserName());
			result.setQuantity(source.getQuantity());
			result.setProductName(source.getProductName());
			result.setProductPrice(source.getProductPrice());
		}
		return result;
	}
	@Override
	public List<CartItemModel> getCartItemsByUserName(String userName) {
		
		return repo.findAllByUserName(userName).stream().map((entity)->of(entity)).collect(Collectors.toList());
	}

	@Override
	public CartItemModel addToCart(CartItemModel cartItem) throws Exception {
		if(repo.getCartByProductIdAnduserId(cartItem.getProductId(), cartItem.getUserName()).isPresent()){
			throw new Exception("Product is already exist.");
		}
	
		return of(repo.save(of(cartItem)));
	}
	
	@Override
	public void updateQty(CartItemModel item) {
		repo.updateQtyByCartId(item.getCartId(), item.getProductPrice(), item.getQuantity());
		
	}
	@Override
	public void removeCartItemsByUserNameAndCartId(String userName, long cartId) {
		repo.deleteCartByIdAndUserName(userName, cartId);
		
	}
	@Override
	public void removeAllCartByUserName(String userName) {
		repo.deleteAllCartByUserName(userName);
		
	}
	
	

}
