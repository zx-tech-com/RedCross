package com.zx.redcross.service.address;

import java.util.List;

import com.zx.redcross.entity.Address;

public interface AddressServ {

	void updateAddress();

	Boolean saveAddress(Address address);

	String getDetailAddress(Address address);

	Boolean updateAddressAll(Address address);

	List<Address> findAllAddressByCustomerId(Integer customerId);

	Address getAddressByAddressId(Integer addressId);

}
