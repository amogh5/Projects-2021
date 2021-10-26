package com.ppb.util;

public interface FormatService {
		
	public void convertMatchTime(String time);
	
	public boolean isTimeNegative(double oldTimeMinutes);
	
	public String convertToNewFormat(double minutes, double seconds,String period);
	public void displayNewMatchTime();

}
