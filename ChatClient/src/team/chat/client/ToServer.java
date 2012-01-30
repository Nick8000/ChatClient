package team.chat.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import team.chat.request.ServerInfo;

public class ToServer 
{
	private String request;
	private ServerInfo serverInfo;
	public ToServer(String request)
	{
		this.request = request;
		serverInfo = new ServerInfo();
	}
	public String send()
	{
		String answer = "";
		Socket s;
		try 
		{
			s = new Socket(serverInfo.getUrl().getHost(), serverInfo.getPort());
			OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
			out.write(request);
			out.flush();
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Sending done!\nWas send: "+request);
			s.shutdownOutput();
			Scanner read = new Scanner(new InputStreamReader(s.getInputStream()));
			while(read.hasNext())
				answer += read.nextLine();
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Have answer: " + answer);
			return answer;
		} 
		catch (UnknownHostException e) 
		{
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
			return "ERROR";
		} 
		catch (IOException e1) 
		{
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e1.getMessage());
			return "ERROR";
		}
	}
}
