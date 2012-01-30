package team.chat.request;

import team.chat.our.ResponseCodes;

public class MessageRequest extends Request
{
	public MessageRequest(String method, String requestString, String message, int userID, int roomId)
	{
		super(method, requestString);
		this.setId(userID);
		this.setMessage(message);
		this.roomId = roomId;
	}
	
	public MessageRequest(String querryString)
	{
		super(querryString);
	}
	
	public String getQuerryString()
	{
		querryString = "";
		toXML();
		querryString = method+" "+requestString+" "+HTTPVersion;
		querryString += "\r\ncode:"+ResponseCodes.sendMessageToChatRoom.toString();
		querryString += "\r\n"+body;
		return querryString;
	}
	public void toXML()
	{
		body="";
		body+="<?xml version=\"1.0\"?>\r\n";
		body+="<Message>\r\n";
		body+="<message>"+message+"</message>";
		body+="<users_id>"+userId+"</users_id>\r\n";
		body+="<chatroom_id>"+roomId+"</chatroom_id>\r\n";
		body+="</Message>";
	}
	private String message = "";
	private int userId = -1;
	private int roomId;
	
	public int getId()
	{
		return userId;
	}
	
	public void setId(int id)
	{
		this.userId = id;
	}
	public String toString()
	{
		return "Method: "+getMethod()+"\nQuery string: "+getRequestString()+"\nBody: "+getBody();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
