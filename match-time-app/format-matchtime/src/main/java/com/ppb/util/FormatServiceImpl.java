package com.ppb.util;

import java.util.Map;

public class FormatServiceImpl implements FormatService {

	@Override
	
	public boolean isTimeNegative(double oldTimeMinutes)
	{
		if(oldTimeMinutes<0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	public void displayNewMatchTime()
	{
		for (Map.Entry<String, String> entry : ProcessFile.timeFormats.entrySet())
		{
			System.out.println(entry.getKey()+"    -----------------------------------------   "+entry.getValue());
			
		}
	}

	public void convertMatchTime(String time)
	{
		String period=null;
		String oldTime=null;
		try
		{
			if(time.contains(" "))
			{
				period= time.split(" ")[0];
				oldTime= time.split(" ")[1];

				if(ProcessFile.periodsMap.containsKey(period))
				{
					double minutes = Double.parseDouble(oldTime.split(":")[0]);
					if(isTimeNegative(minutes))
					{
						//						isValid=false;
						ProcessFile.timeFormats.put(time, ProcessFile.INVALID_STRING);
					}
					else
					{
						double seconds =Double.parseDouble(oldTime.split(":")[1]);

						String newFormat = convertToNewFormat(minutes, seconds, period);
						ProcessFile.timeFormats.put(time, newFormat);
					}
				}
				else
				{
					ProcessFile.timeFormats.put(time, ProcessFile.INVALID_STRING);

				}

			}
			else
			{
				ProcessFile.timeFormats.put(time, ProcessFile.INVALID_STRING);

			}



		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception-"+e.getMessage());
		}


	}
	
	public String convertToNewFormat(double minutes, double seconds,String period)
	{

		int formattedSeconds=(int) Math.round(seconds);
		int newMinutes = (int) minutes;


		StringBuilder newTime = new StringBuilder();

		if(newMinutes<45)
		{
			if(minutes<10)
			{
				newTime.append("0");
			}	
			newTime.append(newMinutes).append(":");
			if(formattedSeconds<10)
			{
				newTime.append("0");
			}
			newTime.append(formattedSeconds).append(" - ").append(ProcessFile.periodsMap.get(period));

		}

		else if(newMinutes>=45 && newMinutes<90) 
		{
			int minuteDiff = newMinutes-45;

			newTime.append("45:");

			if(ProcessFile.periodsMap.get(period).equals("FIRST_HALF"))
			{

				newTime.append("00\s");
				if(minuteDiff<10)
				{
					newTime.append("+0");
				}
				newTime.append(minuteDiff).append(":");

			}
			if(formattedSeconds<10)
			{
				newTime.append("0");
			}
			newTime.append(formattedSeconds).append(" - ").append(ProcessFile.periodsMap.get(period));

		}

		else if(newMinutes>=90) 
		{
			int minuteDiff = newMinutes-90;

			newTime.append("90:");
			if(ProcessFile.periodsMap.get(period).equals("SECOND_HALF"))
			{
				newTime.append("00\s");
				if(minuteDiff<10)
				{
					newTime.append("+0");
				}
				newTime.append(minuteDiff).append(":");
			}
			if(formattedSeconds<10)
			{
				newTime.append("0");
			}
			newTime.append(formattedSeconds).append(" - ").append(ProcessFile.periodsMap.get(period));

		}

//				System.out.println(" "+newTime);	


		return newTime.toString();
	}
	
	
}
