package com.posm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posm.bean.Address;
import com.posm.dao.AddressDao;
import com.posm.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;
	@Override
	public Integer add(Address address) {
		return addressDao.add(address);
	}
	@Override
	public List<Address> getByuid(Integer uid) {
		return addressDao.getByuid(uid);
	}
	@Override
	public Address getByid(Integer id) {
		return addressDao.getByid(id);
	}

}
