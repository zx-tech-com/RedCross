package com.zx.redcross.serviceimpl.address;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.address.AddressMapper;
import com.zx.redcross.dao.my.OsDistrictMapper;
import com.zx.redcross.entity.Address;
import com.zx.redcross.service.address.AddressServ;
@Service
public class AddressServImpl implements AddressServ{
	@Autowired
	private AddressMapper mapper;
	@Autowired
	private OsDistrictMapper	osDistrictMapper;

	@Override
	public void updateAddress() {
		mapper.updateAddress();
	}

	@Override
	public Boolean saveAddress(Address address) {
		return mapper.saveAddress(address);
	}

	@Override
	public String getDetailAddress(Address address) {
		if(address.getDistrictId() == null)
			address.setDistrictId(address.getOsDistrict().getId());
		if(address.getOsDistrict().getId() == null)
			address.getOsDistrict().setId(address.getDistrictId());
		Map<String, Object> osDistrict=osDistrictMapper.findOsdistrictById(address.getOsDistrict().getId());
		Integer level=(int) osDistrict.get("level");
		String path=(String) osDistrict.get("name");
		Boolean flage=true;
		while(flage){
			if(level!=1){
				osDistrict=osDistrictMapper.findOsdistrictById((Integer) osDistrict.get("upid"));
				path=(String) osDistrict.get("name") + path;
				level--;
			}else {
				flage=false;
			}
		}
		String detailAddress = address.getDetailAddress();
		detailAddress = detailAddress==null?"" : detailAddress;
		return path + detailAddress;
	}

	@Override
	public Boolean updateAddressAll(Address address) {
		return mapper.updateAddressAll(address);
	}

	@Override
	public List<Address> findAllAddressByCustomerId(Integer customerId) {
		return mapper.findAllAddressByCustomerId(customerId);
	}

	@Override
	public Address getAddressByAddressId(Integer addressId) {
		return mapper.getAddressByAddressId(addressId);
	}
}
