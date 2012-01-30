package team.chat.response;

import team.chat.client.PublicData;

public class MessageResponse 
{
	public MessageResponse(String body)
	{
		this.body = body;
		ParParser.ParseXML(body, "users_id", "message", PublicData.messagesList);
	}
	private String body;
}
