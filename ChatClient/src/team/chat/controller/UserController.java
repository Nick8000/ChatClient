package team.chat.controller;

import java.util.Collection;
import java.util.Iterator;

import team.chat.client.Logger;
import team.chat.client.PublicData;
import team.chat.request.Request;

public class UserController 
{
	public UserController(){}
	public void NewUserInRoom(Request request)
	{
		this.request = request;
		Parser();
		PublicData.usersList.put(id, login);
		PublicData.listModel2.clear();
		Collection c = PublicData.usersList.values();
		Iterator it = c.iterator();
		while(it.hasNext())
		{
			PublicData.listModel2.addElement(it.next());
		}
	}
	public void UserLiveAloneFromTheRoom(Request request)
	{
		this.request = request;
		Parser();
		PublicData.listModel2.removeElement(login);
	}
	private void Parser()
	{
		try
		{
			int idBeginIndex = -1;
			int idEndIndex = -1;
			int nameBeginIndex = -1;	
			int nameEndIndex = -1;
			
			idBeginIndex = request.getBody().indexOf("<id>") + 4;
			String userId = request.getBody().substring(idBeginIndex);
			
			idEndIndex = userId.indexOf("</id>");
			userId = userId.substring(0, idEndIndex);
			
			nameBeginIndex = request.getBody().indexOf("<nick>") + 6;
			String message = request.getBody().substring(nameBeginIndex);
			
			nameEndIndex = message.indexOf("</nick>");
			message = message.substring(0, nameEndIndex);
			this.id = Integer.parseInt(userId);
			this.login = message;
		}
		catch(IndexOutOfBoundsException e)
		{
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
		}
	}
	private Request request;
	private int id;
	private String login;
	private String info;
}
