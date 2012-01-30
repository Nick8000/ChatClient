package team.chat.panels;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import team.chat.client.Logger;
import team.chat.client.PublicData;
import team.chat.client.ServerListener;
import team.chat.client.ToServer;
import team.chat.controller.RoomController;
import team.chat.request.UserRequest;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JFrame myframe;
	private JTextField textField;

	private void setFrame(JFrame frame)
	{
		myframe = frame;
	}
	public Login() {
		setFrame(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblId = new JLabel("\u0412\u0430\u0448 ID");
		contentPane.add(lblId);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(20);
		
		JLabel label_1 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		contentPane.add(passwordField);
		
		JButton button = new JButton("\u0412\u043E\u0439\u0442\u0438");
		button.addMouseListener(new MouseAdapter() { // login pressed
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				try
				{
					Integer.parseInt(textField.getText());	
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "ID некоректен!");
					return;
				}
				if(!CheckEnteredInfo.isPasswordEntered(passwordField.getPassword()))
				{
					JOptionPane.showMessageDialog(null, "Пароль не введен!");
					return;
				}
				String answer = "";
				String md5PasswordSum = CheckEnteredInfo.Password_To_Md5_Hash_String(CheckEnteredInfo.PasswordToString(passwordField.getPassword()));
				UserRequest request = new UserRequest(Integer.parseInt(textField.getText()), "", md5PasswordSum, "", "POST", "/users/signIn/");
				String temp = request.getQuerryString();
				ToServer toserver = new ToServer(temp);
				answer = toserver.send();
				
				if(answer == "ERROR")
				{
					Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Server is not helth!");
					CheckEnteredInfo.PrintMessage("Сервер не отвечает, попробуйте позже");
				}
				else
				{
					if(answer.contains("UserNotFound"))
					{
						JOptionPane.showMessageDialog(null, "Пользователь не существует или введен неверный пароль!");
						textField.setText("");
						passwordField.setText("");
						return;
					}
					Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Answer is:"+answer);
					RoomController rooms = new RoomController(answer);
					PublicData.id = Integer.parseInt(textField.getText());
					Runnable r = new ServerListener();
					Thread t = new Thread(r);
					t.start();
					ChatFrame chat = new ChatFrame();
					chat.setVisible(true);
					myframe.setVisible(false);
					myframe.dispose();
					Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" User logging with id="+Integer.parseInt(textField.getText()));
				}
				
			}
		});
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Registration regFrame = new Registration();
				regFrame.setVisible(true);
				myframe.setVisible(false);
				myframe.dispose();
			}
		});
		contentPane.add(button_1);
	}
}
