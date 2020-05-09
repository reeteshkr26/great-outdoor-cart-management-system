package com.cg.goms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.goms.exception.OrderException;
import com.cg.goms.model.CartItemModel;
import com.cg.goms.model.OrderModel;
import com.cg.goms.service.CartItemService;
import com.cg.goms.service.OrderService;

@RestController
@RequestMapping("cart/orders")
@CrossOrigin
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartItemService cartService;
	
	@PostMapping
	public ResponseEntity<List<OrderModel>> checkOutOrder(@RequestBody HashMap<String,String> addCartRequest ) throws OrderException{
		
		try {
			String userId = addCartRequest.get("userId");
			//double totalAmt = Double.parseDouble(addCartRequest.get("total_price"));
			
				List<CartItemModel> cartItems = cartService.getCartItemsByUserName(userId);
				List<OrderModel> tmp = new ArrayList<>();
				for(CartItemModel addCart : cartItems) {
					String orderId = ""+getOrderId();
					OrderModel om=new OrderModel();
					om.setOrderId(orderId);
					om.setPaymentType(addCartRequest.get("paymentType"));
					om.setPrice(addCart.getProductPrice());
					om.setUserId(userId);
					om.setProductId(addCart.getProductId());
					om.setItemQuantity(addCart.getQuantity());
					om.setAddressId(addCartRequest.get("addressId"));
					om.setDispatchStatus(1);
					System.out.println(LocalDate.now());
					om.setOrderInitiateTime(LocalDate.now());
					om.setOrderDispatchTime(null);
					tmp.add(om);	
			
		    }
			
			return new ResponseEntity<>(orderService.placeOrder(tmp),HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<OrderModel>> getAllOrderList(@PathVariable("userId") String userId){
		ResponseEntity<List<OrderModel>> response=null;
		List<OrderModel> orderList=orderService.getAllOrderList(userId);
		if(orderList==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			response=new ResponseEntity<>(orderList,HttpStatus.OK);
		}
		return response;
	}
	@GetMapping("/OrderId/{orderId}")
	public ResponseEntity<OrderModel> getOrderByOrderId(@PathVariable("orderId") String orderId){
		ResponseEntity<OrderModel> response=null;
		OrderModel orderItem=orderService.getOrderByOrderId(orderId);
		if(orderItem==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			response=new ResponseEntity<>(orderItem,HttpStatus.OK);
		}
		return response;
	}
	@PutMapping("/cancelOrder/{orderId}")
	public ResponseEntity<?> updateOrderStatus(@PathVariable("orderId") String orderId,@RequestBody HashMap<String,String> request) throws OrderException{
		try {
			int code=Integer.parseInt(request.get("statusCode"));
			orderService.updateDispatchStatus(orderId, code);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(OrderException ex){
			throw new OrderException(ex.getMessage());
		}
		
	}
	public int getOrderId() {
	    Random r = new Random( System.currentTimeMillis() );
	    return 10000 + r.nextInt(20000);
	}

	
}
