package com.zx.redcross.dao.address;

import java.util.List;

import com.zx.redcross.entity.Address;

public interface AddressMapper {

	void updateAddress();

	Boolean saveAddress(Address address);

	Boolean updateAddressAll(Address address);

	List<Address> findAllAddressByCustomerId(Integer customerId);

	Address getAddressByAddressId(Integer addressId);

}
