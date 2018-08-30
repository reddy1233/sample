package com.comopt.touchpoint.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comopt.touchpoint.model.TouchPointActor;

@Component
public class TouchPointActorServiceImpl implements TouchPointActorService {

	@Override
	public List<TouchPointActor> getTouchPointActors() {

		//Integration business logic to get the data every 24 hours
		List<TouchPointActor> list = new ArrayList<>();
		
		TouchPointActor tpa = new TouchPointActor();
		tpa.setId(1);
		tpa.setName("abc");
		list.add(tpa);
		
		tpa = new TouchPointActor();
		tpa.setId(2);
		tpa.setName("xyz");
		list.add(tpa);
		
		return list;
	}

}
