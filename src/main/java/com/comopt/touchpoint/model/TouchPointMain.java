package com.comopt.touchpoint.model;

import java.util.List;

public class TouchPointMain {
	
		private Long appId;
		
		
		List<Touchpoint> touchpoint;


		public Long getAppId() {
			return appId;
		}


		public void setAppId(Long appId) {
			this.appId = appId;
		}


		public List<Touchpoint> getTouchpoint() {
			return touchpoint;
		}


		public void setTouchpoint(List<Touchpoint> touchpoint) {
			this.touchpoint = touchpoint;
		}

		
}
