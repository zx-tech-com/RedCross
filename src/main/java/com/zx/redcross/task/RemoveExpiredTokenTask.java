package com.zx.redcross.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zx.redcross.tool.TokenManager;

/**
 * 每天都清除TokenMap中的已过期token
 * @author Daryl
 *
 */
@Component
public class RemoveExpiredTokenTask {

	Logger logger = LogManager.getLogger(com.zx.redcross.tool.TokenManager.class);
	/**
	 * 每天凌晨0:30分清理
	 */
	@Scheduled(cron="0 10 12 * * ?")
	public void removeTokenExpired() {
		TokenManager.remveExpiredToken();
	}
	
}
