package com.log.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import com.log.database.HSQLDAO;

public class ParseLogFile {

	private static HashMap<String, EventDetailsModel> eventMap = new HashMap<>();



	public void logsProcessor(String jsonFilePath)
	{
		try
		{
			ParseLogFile fileReader = new ParseLogFile();
//			String jsonFilePath = "..\\java-hsql-assignment\\logFile.txt";

			fileReader.parseLogFile(jsonFilePath);

			HSQLDAO daoObj = new HSQLDAO();
			daoObj.createTable();
			daoObj.insertRecord(eventMap);
			System.out.println("Logs processed!");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception:"+e.getMessage());
		}

	}


	public void parseLogFile(String jsonFilePath){

		File jsonFile = new File(jsonFilePath);
		JsonFactory jsonfactory = new JsonFactory(); 

		try {
			JsonParser jsonParser = jsonfactory.createJsonParser(jsonFile); 
			EventDetailsModel event = new EventDetailsModel();
			JsonToken jsonToken = jsonParser.nextToken();



			while (jsonToken!= JsonToken.END_ARRAY){ 

				String fieldName = jsonParser.getCurrentName(); 

				if (Constants.EVENT_ID.equals(fieldName)) {
					jsonToken = jsonParser.nextToken(); 
					event.setEventId(jsonParser.getText());
				}
				if (Constants.STATE.equals(fieldName)) {
					jsonToken = jsonParser.nextToken();
					event.setState(jsonParser.getText());
				}
				if (Constants.TYPE.equals(fieldName)) {
					jsonToken = jsonParser.nextToken();
					event.setType(jsonParser.getText());
				}
				if (Constants.HOST.equals(fieldName)) {
					jsonToken = jsonParser.nextToken();
					event.setHost(jsonParser.getText());
				}
				if (Constants.TIMESTAMP.equals(fieldName)) {
					jsonToken = jsonParser.nextToken();
					event.setTimestamp(jsonParser.getLongValue());
				}


				if(jsonToken==JsonToken.END_OBJECT){

					if(eventMap.containsKey(event.getEventId()))
					{
						EventDetailsModel tempEvent = eventMap.get(event.getEventId());
						long duration= event.getTimestamp()-tempEvent.getTimestamp();
						event.setEventDuration(duration);
						if(duration>4)
						{
							event.setAlert(true);
						}
						else
						{
							event.setAlert(true);
						}
						eventMap.replace(event.getEventId(), event);
					}
					else
					{
						eventMap.put(event.getEventId(), event);
					}

					event = new EventDetailsModel();
				}
				jsonToken = jsonParser.nextToken();
			}

		}
		catch(IOException e) {
			System.out.println("IO Exception:"+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println("Null pointer Exception:"+e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Exception:"+e.getMessage());
		}
	}
}