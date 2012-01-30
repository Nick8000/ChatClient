package team.chat.client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger 
{
	public static void Print(String message)
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		String time =  sdf.format(cal.getTime());
		message = time + ": " + message+"\r\n";
		System.out.print(message);
		ToFile(message);
		
	}
	private static void ToFile(String message)
	{
		try {
		FileWriter  file = new FileWriter ("log.chat", true);
		BufferedWriter tofile = new BufferedWriter(file);
		tofile.write(message);
		tofile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
