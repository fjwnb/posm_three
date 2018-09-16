package com.posm.dao;

import java.util.List;

import com.posm.bean.Address;

public interface AddressDao {
	public Integer add(Address address);
	public List<Address> getByuid(Integer uid);
	public Address getByid(Integer id);
	
}
