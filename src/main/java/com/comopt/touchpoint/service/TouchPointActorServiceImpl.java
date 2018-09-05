package com.comopt.touchpoint.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comopt.touchpoint.model.Address;
import com.comopt.touchpoint.model.CommDetails;
import com.comopt.touchpoint.model.ExtRefId;
import com.comopt.touchpoint.model.Initiator;
import com.comopt.touchpoint.model.TouchPointActor;
import com.comopt.touchpoint.model.Touchpoint;

@Component
public class TouchPointActorServiceImpl implements TouchPointActorService {
	
	
	@Override
	public TouchPointActor getTouchPointActors() {
		
		//Integration business logic to get the data every 24 hours	
		TouchPointActor tpa = new TouchPointActor();
		List<Touchpoint> tpList = new ArrayList<>();
		
		tpa.setAppId("appId");
		tpa.setTransId("transId");
		tpa.setEtlBusinessRecordId(" etlID");
		tpa.setSourceCd("sourceCd");
		tpa.setTenantId(123456);
		tpList.add(getTouchPoint());
		tpa.setTouchpoint(tpList);

		return tpa;
	}
	
	private static Touchpoint getTouchPoint() {
		Touchpoint tp = new Touchpoint();
		
		tp.setMode("outbound");
		tp.setChannelId("mail");
		tp.setReasonCd("reasonCd");
		tp.setStatDtTs(LocalDate.now());
		tp.setExtRefId(getExtRefId());
		tp.setAboutWhom(getAboutWhom());
		tp.setCommDetails(getCommDetails());
		tp.setInitiator(getInitiator());
		tp.setInitiator(getWhReciever());
		return tp;	
		
	}
	private static ExtRefId getExtRefId() {
		ExtRefId ex = new ExtRefId();
		ex.setId("extRefId");
		ex.setSourceCd("sourceCd");
		return ex;
	}
	private static CommDetails getCommDetails() {
		CommDetails cd = new CommDetails();
		List<Address> adList = new ArrayList();
		adList.add(getAddress());
		cd.setAddress(adList);
		cd.setTypeCd("typeCd");
		return cd;
	}	
	private static Address getAddress() {
		Address ad=new Address();
		ad.setLine1("line1");
		ad.setLine2("line2");
		ad.setCityName("City");
		ad.setStateName("State");
		ad.setPostalCd("postalCd");
		return ad;
	}
	private static Initiator getInitiator() {
		
		Initiator i=new Initiator();
		i.setCategory(" i category");
		i.setConstinuencyCd(" i constinuencyCd");
		i.setId(" i id");
		return i;
	}
	private static Initiator getAboutWhom() {
		
		Initiator aw=new Initiator();
		aw.setCategory(" aw category");
		aw.setConstinuencyCd(" aw constinuencyCd");
		aw.setId(" aw id");
		return aw;
	}
	private static Initiator getWhReciever() {
		
		Initiator whr=new Initiator();
		whr.setCategory(" whr category");
		whr.setConstinuencyCd("whr constinuencyCd");
		whr.setId(" whr id");
		return whr;
	}
	
	
}
