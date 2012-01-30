package team.chat.response;

public class RegistrationResponse  
{
	public RegistrationResponse(String response)
	{
		body = response;
		ParseRegistrationBody();
	}
	private void ParseRegistrationBody()
	{
		body = body.substring(body.indexOf("<id>") + 4);
		String idStr = body.substring(0, body.indexOf("</id>"));
		setId(Integer.parseInt(idStr));
	}
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	private String body = "";
	private int id;
}
