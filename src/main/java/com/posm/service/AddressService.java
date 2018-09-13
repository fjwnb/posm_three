package com.posm.service;

import java.util.List;

import com.posm.bean.Address;

public interface AddressService {
	public Integer add(Address address);
	public List<Address> getByuid(Integer uid);
}
