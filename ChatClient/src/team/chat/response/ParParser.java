package team.chat.response;

import java.util.HashMap;

import team.chat.client.Logger;

public class ParParser 
{
	public static void ParseXML(String body, String param1, String param2, HashMap map)
	{
		while(body.indexOf("<"+param1+">") != -1)
		{
				int idBeginIndex = -1;
				int idEndIndex = -1;
				int nameBeginIndex = -1;	
				int nameEndIndex = -1;
				
				idBeginIndex = body.indexOf("<"+param1+">") + param1.length()+2;
				String userId = body.substring(idBeginIndex);
				
				idEndIndex = userId.indexOf("</"+param1+">");
				userId = userId.substring(0, idEndIndex);
				
				nameBeginIndex = body.indexOf("<"+param2+">") + param2.length()+2;
				String userName = body.substring(nameBeginIndex);
				
				nameEndIndex = userName.indexOf("</"+param2+">");
				userName = userName.substring(0, nameEndIndex);
				
				body = body.substring(nameEndIndex+7);
				
				map.put(Integer.parseInt(userId), userName);
		}
	}
}
