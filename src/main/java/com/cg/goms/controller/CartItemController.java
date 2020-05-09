package com.cg.goms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.goms.model.CartItemModel;
import com.cg.goms.service.CartItemService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/{userName}")
	public ResponseEntity<List<CartItemModel>> getCartItemsByUserName(@PathVariable("userName") String userName){
		ResponseEntity<List<CartItemModel>> response=null;
		List<CartItemModel> cartItemList=cartItemService.getCartItemsByUserName(userName);
		if(cartItemList==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			response=new ResponseEntity<>(cartItemList, HttpStatus.OK);
		}
		return response;
	}

	@PostMapping("/addToCart")
	public ResponseEntity<CartItemModel> addToCart( @RequestBody CartItemModel cartItem) throws Exception{
	     cartItem = cartItemService.addToCart(cartItem);
		return new ResponseEntity<>(cartItem,HttpStatus.OK);
		
	}
	@PutMapping("/updateCartItem")
	public ResponseEntity<?> updateToCart(@RequestBody CartItemModel cartItem){
	     cartItemService.updateQty(cartItem);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@DeleteMapping("/removeProductFromCart/{userName}/{cartId}")
	public ResponseEntity<CartItemModel> deletCartItemsByUserNameAndProductId(@PathVariable("userName") String userName,@PathVariable("cartId") long cartId){
		try {
			cartItemService.removeCartItemsByUserNameAndCartId(userName, cartId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception ex){
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
	
	}
	@DeleteMapping("removeProductFromCart/{userName}")
  	public ResponseEntity<?> deleteCartByUserName(@PathVariable("userName") String userName) {
		try {

			cartItemService.removeAllCartByUserName(userName);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
   }
}
