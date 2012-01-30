
package team.chat.request;


public class UserRequest extends Request
{
	public UserRequest(int id, String login, String pass, String info, String method, String requestString)
	{ 
		//constructor for creating request string
		super(method, requestString);
		if(id >= 0)
			this.id = id;
		this.setLogin(login);
		this.setPass(pass);
		this.setInfo(info);
	}
	
	public UserRequest(String querryString) // parse request constructor
	{
		super(querryString);
	}
	
	public String getQuerryString()
	{
		querryString = "";
		toXML();
		querryString = method+" "+requestString+" "+HTTPVersion;
		querryString += "\r\n\r\n"+params;
		querryString += "\r\n\r\n"+body;
		return querryString;
	}
	
	public void toXML()
	{
		body="";
		body+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n";
		body+="<user>\r\n";
		if(id >= 0)
			body+="<id>"+Integer.toString(id)+"</id>\r\n";
		if(!login.equals(""))
			body+="<nick>"+login+"</nick>\r\n";
		body+="<password>"+pass+"</password>\r\n";
		if(id == -1)
			body+="<info>"+info+"</info>\r\n";
		body+="</user>";
	}
	
	private String login = "";
	private String pass = "";
	private String info = "";
	private int id = -1;
	
	public String getInfo()
	{
		return info;
	}
	
	public void setInfo(String info)
	{
		this.info = info;
	}
	
	public String getPass()
	{
		return pass;
	}
	
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	
	public void setLogin(String login)
	{
		this.login = login;
	}
	
	public String getLogin() 
	{
		return login;
	}
	
}