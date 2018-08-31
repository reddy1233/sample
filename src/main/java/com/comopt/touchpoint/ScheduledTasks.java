package com.comopt.touchpoint;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.comopt.touchpoint.service.TouchPointActorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

   // @Scheduled(cron="")
   // @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
    
    @Autowired
  private  TouchPointActorService service;
    
    @Scheduled(fixedRate = 60000)
    public void usecaseName() {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String json;
		try {
			json = mapper.writeValueAsString(service.getTouchPointActors());
			log.info("Touch Point JSON String "+json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    }
}
