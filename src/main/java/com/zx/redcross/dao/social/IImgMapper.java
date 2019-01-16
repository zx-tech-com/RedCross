package com.zx.redcross.dao.social;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Img;

public interface IImgMapper {
	
	@Insert("INSERT INTO img(foreign_id,img_type,iindex,img_url) "
			+ "VALUES(#{img.foreignId},#{img.imgType},#{img.iindex},#{img.imgUrl})")
	Boolean saveImg(@Param("img") Img img);
	
}
