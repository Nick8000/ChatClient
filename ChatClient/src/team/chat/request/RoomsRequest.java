package team.chat.request;

import team.chat.client.PublicData;

public class RoomsRequest extends Request
{
	public RoomsRequest(String method, String requestString, int roomId, String code)
	{
		super(method, requestString);
		setRoomId(roomId);
		setCode(code);
		
	}
	private String createBody()
	{
		String body = "";
		body+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n";
		body+="<chatroom>\r\n";
			body+="<users_id>"+Integer.toString(PublicData.id)+"</users_id>\r\n";
			body+="<chatroom_id>"+getRoomId()+"</chatroom_id>\r\n";
		body+="</chatroom>";
		return body;
	}
	public String getQuery()
	{
		String temp = "";
		temp += getMethod()+" " +getRequestString()+" "+ HTTPVersion + "\r\ncode:" +getCode()+"\r\n" + createBody();
		return temp;
	} 
	
	public int getRoomId() {
		return roomId;
	}
	private void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getCode() {
		return code;
	}
	private void setCode(String code) {
		this.code = code;
	}
	private String code;
	private int roomId;
}
