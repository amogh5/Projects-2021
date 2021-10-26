package com.ppb.util;

public class FormatTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try
		{
		ProcessFile startFormatService = new ProcessFile();
		startFormatService.read(args[0]);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Enter file path. Exception----"+e.getMessage());
		}
	}

}
