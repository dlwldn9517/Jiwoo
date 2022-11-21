package com.gdu.app13.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app13.service.UserService;

@EnableScheduling  // @Scheduled 하려면 필요한 애너테이션
@Component
public class SleepUserScheduler {
	
	@Autowired
	private UserService userService;
	
	// 매일 새벽 1시 		@Scheduled(cron = "0 0 1 * * *") (초 분 시 일 요일 월)

	@Scheduled(cron = "0 1 0 * * *")	// 복구되더라도 로그인을 안하면 스케줄러에 의해 계속 휴면으로 빠짐
	public void execute() {
		userService.sleepUserHandle();
	}

}
