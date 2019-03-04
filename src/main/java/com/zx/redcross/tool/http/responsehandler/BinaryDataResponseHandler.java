package com.zx.redcross.tool.http.responsehandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.zx.redcross.tool.BusinessExceptionUtils;
import com.zx.redcross.tool.http.contenttype.ResponseHanlder;

/**
 * 处理二进制数据.如文件,图片,视频等.
 * @author Daryl
 */
public class BinaryDataResponseHandler extends ResponseHanlder{

	@Override
	public void fetchContent() {
		
		try(InputStream inStream = this.connection.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream(2048);){
			byte[] bts = new byte[1024];
			int len = 0;
			while(-1 != (len = inStream.read(bts))) {
				outStream.write(bts,0,len); 
			}

			this.setContent(outStream.toByteArray());
			
//			System.out.println(this.getContentType());
//			System.out.println(new String(outStream.toByteArray()));
		} catch (IOException e) {
			BusinessExceptionUtils.throwNewBusinessException("获取响应主体内容失败" + e.getMessage());
		}
	}

}
