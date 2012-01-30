package team.chat.request;

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
		Pattern p = Pattern.compile("HTTP/1.1 [0-9]{3}\\s{1}([A-z]*)(\\s{1}[A-z]+)?");
		Matcher m = p.matcher(response);
		if(m.find())
		{
			int start = m.start();
			int end = m.end();
			querryString = response.substring(start, end);
		}
		p = Pattern.compile("Response-code:\\s[A-z]*");
		m = p.matcher(response);
		if(m.find())
		{
			int start = m.start();
			int end = m.end();
			responseCode = response.substring(start, end);
			responseCode = responseCode.substring(responseCode.indexOf(":"));
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
	private String response;
	private String querryString;
	private String responseCode;
	private String body;
}
