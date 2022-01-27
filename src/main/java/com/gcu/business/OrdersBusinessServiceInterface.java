package com.gcu.business;

import java.util.List;

import com.gcu.model.OrderModel;

public interface OrdersBusinessServiceInterface<T> {

	public void test();
	public List<OrderModel> getOrders();
	public void init();
	public void destroy();
	List<OrderModel> findAll();

	public int delete(long id);
	public boolean create(OrderModel order);
	public boolean update(OrderModel order);
	OrderModel findbyId(long id);
}
