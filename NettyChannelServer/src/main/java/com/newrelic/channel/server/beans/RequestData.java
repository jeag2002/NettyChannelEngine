package com.newrelic.channel.server.beans;

public class RequestData {

	private int intValue;
    private String stringValue;
    
    public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	@Override
	public String toString() {
		return "intValue: " +  intValue +  " stringValue: " + stringValue;
	}

}
