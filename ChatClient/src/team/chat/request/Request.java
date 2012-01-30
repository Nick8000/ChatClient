package team.chat.request;

public class Request 
{
	protected String HTTPVersion = "HTTP/1.1";
	protected String querryString = "";
	protected String body = "";
	protected String params = "Content-type: text/xml";
	protected String requestString = "";
	protected String method = "";
	protected String host = "http://46.98.155.245:80";
	public Request()
	{}
	protected Request(String method, String requestString) // Create request constructor
	{
		this.setMethod(method);
		this.setRequestString(requestString);
		this.setParams(params);
	}
	
	protected Request(String querryString) // parse request constructor
	{
		this.setQuerryString(querryString);
	}
	
	public String getBody() 
	{
		return body;
	}
	
	public void setBody(String body) 
	{
		this.body = body;
	}

	public String getParams() 
	{
		return params;
	}

	public void setParams(String params) 
	{
		this.params = params;
	}

	public String getRequestString() 
	{
		return requestString;
	}

	public void setRequestString(String requestString) 
	{
		this.requestString = requestString;
	}

	public String getMethod() 
	{
		return method;
	}

	public void setMethod(String method) 
	{
		this.method = method;
	}

	public String getHost() 
	{
		return host;
	}

	public void setHost(String host) 
	{
		this.host = host;
	}
	
	public String toString()
	{
		return "Method: "+getMethod()+"\nQuery string: "+getRequestString()+"\nBody: "+getBody();
	}

	public String getQuerryString() {
		return querryString;
	}

	public void setQuerryString(String querryString) {
		this.querryString = querryString;
	}
}
