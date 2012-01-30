package team.chat.client;

import team.chat.panels.Login;

public class Engine 
{
	public static int localUserID = -1;
	public static void main(String[] args) 
	{ 
		Login frame = new Login();
		frame.setVisible(true);
		// "+ResponseCodes.UserSignedIn.toString()+"
//		String temp = "HTTP/1.1 200 OK\r\n\r\nResponse-code: \r\n\r\n<?xml";
//		Response resp = new Response(temp);
//		ApplicationController app = new ApplicationController();
//		app.dispatch(resp);
	}
}
