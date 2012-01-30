package team.chat.controller;

import team.chat.client.Logger;
import team.chat.request.Request;

public class Route 
{
	public Route(String responseCode, String className, String methodName)
	{
		this.responseCode = responseCode;
		this.className = className;
		this.methodName = methodName;
	}
	private String responseCode;
	private String className;
	private String methodName;
	public boolean match(Request r)
	{
		String responseCode2 = r.getBody().substring(r.getBody().indexOf("code:")+6, r.getBody().indexOf("<?")-1);
		responseCode2.trim();
		
		if(responseCode.equals(responseCode2))
		{
			Logger.Print("FOUND!AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			return true;
		}
		return false;
	}
	public String getClassName() 
	{
		return className;
	}
	public String getMethodName() 
	{
		return methodName;
	}
}
