package com.gcu.data;

import java.util.List;

import com.gcu.model.OrderModel;

public interface DataAccessInterface<T> {

	public List<T> findAll();
	public List<OrderModel> findById(long id);
	public boolean create(T t);
	
	
	public int deleteByID(long id);
	
	OrderModel findbyId1(long id);
	
//	OrderModel update(OrderModel order);
	
	public String FindByOrderID(long id);
	
	public boolean update(OrderModel order);
}
