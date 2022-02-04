package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.OrderModel;

@Service
public class OrdersDataService implements DataAccessInterface<OrderModel>{



@SuppressWarnings("unused")
@Autowired
private DataSource dataSource;
@Autowired
private JdbcTemplate jdbcTemplateObject;


public OrdersDataService(DataSource dataSource) {
	this.dataSource = dataSource;
	this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	
	
}
	@Override
	public List<OrderModel>findAll() {
	String sql = "SELECT * FROM orders";
	List<OrderModel> orders = new ArrayList<OrderModel>();
	
	try {
		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
		while(srs.next()) {
			orders.add(new OrderModel(srs.getLong("ID"),
												srs.getString("ORDER_NO"),
												srs.getString("PRODUCT_NAME"),
												srs.getFloat("PRICE"),
												srs.getInt("QUANTITY")));
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
		// TODO Auto-generated method stub
		return orders;
	}


	public boolean create(OrderModel order) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO orders(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?,?,?,?)";
		
		try {
			int rows = jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity());
			return rows == 1 ? true : false;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	

	
	@Override
	public int deleteByID(long id) {
		// TODO Auto-generated method stub
		return jdbcTemplateObject.update("DELETE FROM orders WHERE id=?", id);
	
	}
	@Override
	public List<OrderModel> findById(long id) {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public OrderModel findbyId1(long id) {
		
		try {
		OrderModel order = jdbcTemplateObject.queryForObject("SELECT * FROM orders WHERE id = ?", BeanPropertyRowMapper.newInstance(OrderModel.class), id);
		return order;
		}
		catch (EmptyResultDataAccessException e) {
		      System.out.println("No record found in database for "+ id);
		      return null;
		    }
		
	

		
	}
	
	public boolean update(OrderModel order) {

				//System.out.println(order.getId()  + "order passed in from update");
				String sql = "UPDATE orders SET ORDER_NO = ?, PRODUCT_NAME = ?, PRICE = ?, QUANTITY = ? WHERE id = ?";
				
				try {
					int rows = jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity(), order.getId());
					return rows == 1 ? true : false;
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				return false;
//		
	}
	
	@Override
	public String FindByOrderID(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
