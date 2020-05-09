package com.cg.goms.service;

import java.util.List;

import com.cg.goms.model.CartItemModel;

public interface CartItemService {
	
	public List<CartItemModel> getCartItemsByUserName(String userName);
	public CartItemModel addToCart(CartItemModel cartItem) throws Exception;
	void updateQty(CartItemModel item);
	
	public void removeCartItemsByUserNameAndCartId(String userName,long cartId);
	public void removeAllCartByUserName(String userName);
}
