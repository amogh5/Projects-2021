package com.ppb.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProcessFile {

	public static Map<String,String> timeFormats = new LinkedHashMap<String,String>();


	public static final Map<String,String> periodsMap;
	static
	{
		periodsMap = new HashMap<String, String>();
		periodsMap.put("[PM]", "PRE_MATCH");
		periodsMap.put("[H1]", "FIRST_HALF");
		periodsMap.put("[HT]", "HALF_TIME");
		periodsMap.put("[H2]", "SECOND_HALF");
		periodsMap.put("[FT]", "FULL_TIME");
	}

	public static final String INVALID_STRING ="INVALID";

	public void read(String fileName) throws IOException
	{
		String line = "";
		FormatServiceImpl formatService = new FormatServiceImpl();
		BufferedReader br =null;
		try {
			//TestData.csv
			br = new BufferedReader(new FileReader(fileName));

			while ((line = br.readLine()) != null)
			{
				formatService.convertMatchTime(line);
			}

			formatService.displayNewMatchTime();

		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			br.close();
		}

	}


}
