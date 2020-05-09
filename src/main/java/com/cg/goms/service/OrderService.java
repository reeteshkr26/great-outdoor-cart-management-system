package com.cg.goms.service;

import java.util.List;

import com.cg.goms.exception.OrderException;
import com.cg.goms.model.OrderModel;

public interface OrderService {
 
	 public List<OrderModel> placeOrder(List<OrderModel> model) throws OrderException;
	 public List<OrderModel> getAllOrderList(String userId);
	 public OrderModel getOrderByOrderId(String orderId);
	 public void updateDispatchStatus(String orderId,int code) throws OrderException;
}
