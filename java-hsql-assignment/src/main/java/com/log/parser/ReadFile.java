package com.log.parser;

import java.io.IOException;
import java.util.HashMap;

public class ReadFile {
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		try
		{
		ParseLogFile initiate = new ParseLogFile();
		initiate.logsProcessor(args[0]);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
		

}
