package team.chat.controller;

import team.chat.client.Logger;
import team.chat.panels.ChatFrame;
import team.chat.request.Request;

public class MessageController 
{
	public MessageController(){}
	public void NewMessageInRoom(Request request)
	{
		this.request = request;
		Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Found! In METHOD MSG in ROOM!!!!!!!1" );
		Parser();
		ChatFrame.PrintMessage(Integer.toString(userId), message);
		Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Must be a new message on form!");
	}
	private void Parser()
	{
		try
		{
			int idBeginIndex = -1;
			int idEndIndex = -1;
			int nameBeginIndex = -1;	
			int nameEndIndex = -1;
			
			idBeginIndex = request.getBody().indexOf("<users_id>") + 10;
			String userId = request.getBody().substring(idBeginIndex);
			
			idEndIndex = userId.indexOf("</users_id>");
			userId = userId.substring(0, idEndIndex);
			
			nameBeginIndex = request.getBody().indexOf("<msg>") + 5;
			String message = request.getBody().substring(nameBeginIndex);
			
			nameEndIndex = message.indexOf("</msg>");
			message = message.substring(0, nameEndIndex);
			this.userId = Integer.parseInt(userId);
			this.message = message;
		}
		catch(IndexOutOfBoundsException e)
		{
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
		}
	}
	private Request request;
	private int userId;
	private String message;
	
}

