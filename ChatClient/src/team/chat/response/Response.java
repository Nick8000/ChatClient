package team.chat.response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Response 
{
	public Response(String response)
	{
		this.response = response;
		querryString = "";
		body = "";
		responseCode = "";
		Parse();
	}
	private void Parse()
	{
		querryString = response.substring(0, response.indexOf("code"));
		querryString.trim();
		Pattern p = Pattern.compile("code:[A-z]+");
		Matcher m = p.matcher(response);
		if(m.find())
		{
			int start = m.start();
			int end = m.end();
			responseCode = response.substring(start, end);
			responseCode = responseCode.substring(responseCode.indexOf(":")+1);
			responseCode = responseCode.trim();
		}
		if(response.indexOf("<?xml") != -1)
		body = response.substring(response.indexOf("<?xml"));
	}
	public String getQuerryString() {
		return querryString;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public String getBody() {
		return body;
	}
	public String toString()
	{
		return "Answer: "+querryString+"\nCode: "+responseCode+"\nBody: "+body;
	}
	private String response;
	private String querryString;
	private String responseCode;
	private String body;
}
