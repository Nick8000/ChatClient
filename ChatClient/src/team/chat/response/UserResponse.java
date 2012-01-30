package team.chat.response;

import team.chat.client.PublicData;

public class UserResponse 
{
	public UserResponse(String body)
	{
		this.body = body;
		ParParser.ParseXML(body, "id", "nick", PublicData.usersList);
	}
	private String body;
}
