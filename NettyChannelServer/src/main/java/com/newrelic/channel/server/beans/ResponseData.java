package com.newrelic.channel.server.beans;

public class ResponseData {
	
	private int intValue;

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	
	@Override
	public String toString() {
		return "intValue " + intValue;
	} 
	
	
		
	
}
