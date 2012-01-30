package team.chat.client;

import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class PublicData 
{
	public static String currentRoom = "";
	public static JTextArea messageArea;
	public static JTextArea messageField;
	public static DefaultListModel listModel2;
	public static JList roomsControl;
	public static JList friends;
	public static int id;
	public static String login;
	public static HashMap <Integer, String> roomsList = new HashMap<Integer, String>();
	public static HashMap <Integer, String> usersList = new HashMap<Integer, String>();
	public static HashMap <Integer, String> messagesList = new HashMap<Integer, String>();
//	for(Integer i : roomsList)
//	{
//		roomsList.get(i);
//	}
}
