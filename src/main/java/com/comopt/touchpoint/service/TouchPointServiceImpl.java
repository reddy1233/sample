package com.comopt.touchpoint.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.comopt.touchpoint.model.Touchpoint;

@Component
public class TouchPointServiceImpl implements TouchPointService {
	
	public List<Touchpoint> getTouchpoint() {

		List<Touchpoint> tpExList = new ArrayList();
		
		Touchpoint tp = new Touchpoint();
		
		tp.setMode("outbound");
		tp.setChannelId("mail");
		tp.setReasonCd("reasonCd");
		tp.setStatDtTs(LocalDate.now());
		
		tpExList.add(tp);
		
		return tpExList;
	}
}
