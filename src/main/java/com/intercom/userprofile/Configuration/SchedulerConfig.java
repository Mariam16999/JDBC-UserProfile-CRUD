package com.intercom.userprofile.Configuration;

import com.intercom.userprofile.Repository.framework.UserRepsInterface;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
@Log4j2
public class SchedulerConfig {

@Autowired
UserRepsInterface userRepsInterface;


 @Scheduled(fixedDelayString ="${fixedDelay.in.milliseconds}")
 @Async
public void count()
{

  log.info("usersCount"+userRepsInterface.userCount());
}
}
