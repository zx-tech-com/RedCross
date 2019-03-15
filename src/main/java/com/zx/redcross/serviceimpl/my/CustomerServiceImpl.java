package com.zx.redcross.serviceimpl.my;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zx.redcross.dao.my.CustomerMapper;
import com.zx.redcross.dao.my.OsDistrictMapper;
import com.zx.redcross.dao.social.SocialMapper;
import com.zx.redcross.entity.Customer;
import com.zx.redcross.service.my.CustomerService;
import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.Constant;
import com.zx.redcross.tool.FileUtils;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private OsDistrictMapper	osDistrictMapper;
	@Autowired
	private CustomerMapper	customerMapper;
	@Autowired
	private SocialMapper 	socialMapper;
	
	@Override
	public Map<String, Object> findOsdistrictById(Integer districtId) {
		return osDistrictMapper.findOsdistrictById(districtId);
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerMapper.saveCustomer(customer);
	}

	@Override
	public Integer findCustomerByTel(String tel) {
		return customerMapper.findCustomerByTel(tel);
	}

	@Override
	public List<Map<String, Object>> findByUpid(Integer id) {
		return osDistrictMapper.findByUpid(id);
	}

	@Override
	public Customer findCustomer(String tel, String password) {
		return customerMapper.findCustomer(tel,password);
	}

	@Override
	public Boolean deleteMyTopic(Integer topicId, Integer customerId) {
		Map<String,Object> topic=socialMapper.findTopicById(topicId,customerId);
		if(!customerMapper.deleteMyTopic(topicId,customerId)) {
			return false;
		}
		if((boolean) topic.get("hasVideo")) {
			FileUtils.removeFile((String)topic.get("videoUrl"));
		}else {
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> imgs=(List<Map<String,Object>>) topic.get("imgs");
			if(imgs.size()>0) {
				for(Map<String,Object> img:imgs) {
					FileUtils.removeFile((String) img.get("imgUrl"));
				}	
			}
		}
		return true;
	}
	

	@Override
	public Boolean deleteMyTopicComent(Integer topicComentId, Integer customerId) {
		return customerMapper.deleteMyTopicComent(topicComentId,customerId);
	}

	@Override
	public Integer findMyTopicComent(Integer topicComentId, Integer customerId) {
		return customerMapper.findMyTopicComent(topicComentId,customerId);
	}

	
	@Override
	public Boolean updatePersonalInfoWithNoAvatarUrl(Customer customer) {
		Customer oldCustomer = customerMapper.findCustomerById(customer.getId());
		if(oldCustomer == null)
			BusinessExceptionUtils.throwNewBusinessException("用户Id不正确");
		if(customer.getOsDistrict() != null
				&& customer.getOsDistrict() != null)
			customer.setDetailAddress(getDetailAddress(customer));
		return customerMapper.updatePersonalInfo(customer);
	}

	@Override
	public Boolean updatePersonalAvatarUrl(Integer customerId, MultipartFile avatar) {
		Customer customer = customerMapper.findCustomerById(customerId);
		if(customer == null)
			BusinessExceptionUtils.throwNewBusinessException("用户Id不正确");
		String newAvatarUrl = FileUtils.saveFile(Constant.ABSOLUTE_BASE_PATH + Constant.AVATAR_URL, avatar);
		String oldAvatarUrl = customer.getAvatarUrl();
		customer.setAvatarUrl(newAvatarUrl);
		boolean flag = customerMapper.updatePersonalInfo(customer);
		
		if(!Constant.CUSTOMER_DEFAULT_AVATAR_URL.equalsIgnoreCase(oldAvatarUrl))
			FileUtils.removeFile(oldAvatarUrl);
		return flag;
	}

	
	public String getDetailAddress(Customer customer) {
		if(customer.getDistrictId() == null)
			customer.setDistrictId(customer.getOsDistrict().getId());
		if(customer.getOsDistrict().getId() == null)
			customer.getOsDistrict().setId(customer.getDistrictId());
		Map<String, Object> osDistrict=osDistrictMapper.findOsdistrictById(customer.getOsDistrict().getId());
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
		String detailAddress = customer.getDetailAddress();
		detailAddress = detailAddress==null?"" : detailAddress;
		return path + detailAddress;
	}
	@Override
	public String findDistrictPath(Customer customer) {
		if(customer.getDistrictId()==null){
			return "";
		}
		Map<String, Object> osDistrict=osDistrictMapper.findOsdistrictById(customer.getDistrictId());
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
		return path;
	}

	@Override
	public Customer getMyselfMessage(Integer customerId) {
		return customerMapper.getMyselfMessage(customerId);
	}

	@Override
	public Integer findMyTopic(Integer topicId, Integer customerId) {
		return customerMapper.findMyTopic(topicId,customerId);
	}

	@Override
	public Boolean updateMotto(Integer customerId, String motto) {
		return customerMapper.updateMotto(customerId,motto);
	}

	

}
