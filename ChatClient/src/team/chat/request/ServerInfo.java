package team.chat.request;

import java.net.MalformedURLException;
import java.net.URL;

import team.chat.client.Logger;

public class ServerInfo 
{
	public ServerInfo()
	{
		try {
			setUrl(new URL("http://46.98.155.245"));
		} catch (MalformedURLException e) {
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" "+e.getMessage());
		}
		setPort(80);
	}
	public int getPort() {
		return port;
	}
	private void setPort(int port) {
		this.port = port;
	}
	public URL getUrl() {
		return url;
	}
	private void setUrl(URL url) {
		this.url = url;
	}
	private URL url;
	private int port;
}
