package team.chat.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import team.chat.client.Logger;
import team.chat.our.ResponseCodes;

public class RouteReader 
{
	public RouteReader(ApplicationController ap)
	{
		this.ap = ap;
		path = "RouteFile.app";
	}
	private ApplicationController ap;
	private String responseCode;
	private String className;
	private String method;
	private String path;
	public void LocalRoutes()
	{
		ap.addRoute("newMsgInRoom", "team.chat.controller.MessageController", "NewMessageInRoom");
		ap.addRoute("newUserInRoom", "team.chat.controller.UserController", "NewUserInRoom");
		ap.addRoute("UserLiveAloneFromTheRoom", "team.chat.controller.UserController", "UserLiveAloneFromTheRoom");
		//ap.addRoute("", "", "");
	}
	public void ReadFromFile()
	{
		File file = new File(path);
		Scanner reader;
		try {
			reader = new Scanner(new FileReader(file));
			while(reader.hasNext())
			{
				String str = reader.next();
				int index = str.indexOf(" ");
				responseCode = str.substring(0, index);
				str = str.substring(index+1);
				index = str.indexOf(" ");
				className = str.substring(0, index);
				str = str.substring(index+1);
				method = str;
				//ap.addRoute(responseCode, className, method);
			}
		} catch (FileNotFoundException e) {
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
		}
	}
}
