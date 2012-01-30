package team.chat.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import team.chat.controller.ApplicationController;
import team.chat.controller.RouteReader;
import team.chat.request.Request;
import team.chat.request.ServerInfo;


public class ServerListener implements Runnable
{
	// GET /listen/777/ HTTP/1.1
	public ServerListener()
	{
		serverInfo = new ServerInfo();
		ap = new ApplicationController();
		RouteReader route = new RouteReader(ap);
		route.LocalRoutes();
		Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" App Created and injected!");
	}
	private ApplicationController ap;
	private ServerInfo serverInfo;
	private Socket listen;
	private String answer = ""; 
	public void run() 
	{
		try 
		{
			listen = new Socket(serverInfo.getUrl().getHost(), serverInfo.getPort());
		} catch (UnknownHostException e1) {
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e1.getMessage());
		} catch (IOException e1) {
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e1.getMessage());
		}
		try 
		{	
			PrintWriter out = new PrintWriter(listen.getOutputStream(), true);
			out.println("GET /listen/"+PublicData.id+"/ HTTP/1.1");
			Scanner in = new Scanner(new InputStreamReader(listen.getInputStream()));
			while(in.hasNextLine())
			{
				answer = in.nextLine();
				Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+answer);
				Request request = new Request();
				request.setBody(answer);
				ap.dispatch(request);
			}			
		} 
		catch (UnknownHostException e) 
		{
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
		} 
		catch (IOException e) 
		{
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
		}
		finally
		{
			try 
			{
				listen.close();
			}
			catch (IOException e) 
			{
				Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
			}
		}
	}
}
