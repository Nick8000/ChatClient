package team.chat.controller;

import team.chat.client.PublicData;
import team.chat.response.ParParser;

public class RoomController 
{
	private String body;
	public RoomController(String body)
	{
		this.body = body;
		ParParser.ParseXML(body, "id", "name", PublicData.roomsList);
	}
}
