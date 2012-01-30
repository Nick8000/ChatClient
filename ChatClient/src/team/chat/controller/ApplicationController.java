package team.chat.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import team.chat.client.Logger;
import team.chat.request.Request;

public class ApplicationController 
{
	private static ArrayList <Route> routes = new ArrayList<Route>();
	
	public void addRoute(String responseCode, String className, String methodName)
	{
		routes.add(new Route(responseCode, className, methodName));
	}
	public static void dispatch(Request request) 																							
	{
		try
		{
			for(Route r : routes)
			{
				if(r.match(request))
				{
					Logger.Print("Found over try!!!!!!!!!11111");
					try 
					{
						// Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Found!\nClassname:"+r.getClassName()+"\nMethodname:"+r.getMethodName());
						// Object h = Class.forName(r.getClassName()).newInstance();
						 Object h = Class.forName(r.getClassName()).newInstance();
					     Object input = request;
					     Class cl = Class.forName(r.getClassName());
					     Class type = team.chat.request.Request.class;
					     Method mthd = cl.getMethod(r.getMethodName(), type);
					     mthd.setAccessible(true);
					     mthd.invoke(h,input);
					 } 
					 catch (Exception e) 
					 {
						 e.printStackTrace();
						// Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" ERROR:"+e.printStackTrace());
					 }
				}
				else
				{
					Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Not Found!!!");
				}
			}
		}
		catch(Exception ex)
		{
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" ERROR:"+ex.getMessage());
		}
	}
}