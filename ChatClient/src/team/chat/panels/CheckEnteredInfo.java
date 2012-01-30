package team.chat.panels;

import java.math.BigInteger;
import java.security.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import team.chat.client.Logger;

public class CheckEnteredInfo 
{
	public static  String PasswordToString(char [] password)
	{
		String pass_str = "";
		for(int temp = 0; temp < password.length; temp++)
			pass_str+=password[temp];
		return pass_str;
	}
	
	
	public static  String Password_To_Md5_Hash_String(String password)
	{
		MessageDigest m = null;
		try 
		{
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		while(hashtext.length() < 32 )
		{
		  hashtext = "0"+hashtext;
		}
		return hashtext;
	}
	public static  boolean isPasswordEntered(char [] password)
	{
		if("".equals(PasswordToString(password)))
		{
			PrintMessage("Необходимо ввести пароль!");
			return false;
		}
		return true;
	}
	public static  boolean checkLogin(String login)
	{
		Pattern pat = Pattern.compile("[a-zA-Z0-9_]{3,20}");
		Matcher m = pat.matcher(login);
		if(!m.matches())
		{
			PrintMessage("Логин неверный\nЛогин может содержать латинские буквы, цифры и знак _");
			return false;
		}
		return true;
	}
	
	public static boolean Check_Info_From_NewUserForm(char [] password, String login)
	{
		if(!checkLogin(login))
		{
			return false;
		}
		else if(!isPasswordEntered(password))
		{
			return false;
		}
		return true;
	}
	
	public static void PrintMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
}
