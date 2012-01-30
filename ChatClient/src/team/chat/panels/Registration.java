package team.chat.panels;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import team.chat.client.Logger;
import team.chat.client.PublicData;
import team.chat.client.ToServer;
import team.chat.request.UserRequest;
import team.chat.response.RegistrationResponse;
import team.chat.response.Response;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JFrame myframe;

	public Registration() {
		myframe = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("\u041B\u043E\u0433\u0438\u043D");
		contentPane.add(label);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(20);
		
		JLabel label_1 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		contentPane.add(passwordField);
		
		JLabel label_2 = new JLabel("\u0415\u0449\u0435 \u0440\u0430\u0437");
		contentPane.add(label_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		contentPane.add(passwordField_1);
		
		JLabel label_3 = new JLabel("\u0418\u043D\u0444\u043E");
		contentPane.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(5);
		scrollPane.setViewportView(textArea);
		
		JButton button = new JButton("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		button.addMouseListener(new MouseAdapter() { // registration clicked on
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(!CheckEnteredInfo.Check_Info_From_NewUserForm(passwordField.getPassword(), textField.getText()))
					return;
				if(!CheckEnteredInfo.PasswordToString(passwordField.getPassword()).equals(CheckEnteredInfo.PasswordToString(passwordField_1.getPassword())))
				{
					JOptionPane.showMessageDialog(null, "Пароли не совпадают!");
					return;
				}
				
				String answer = "";
				String md5PasswordSum = CheckEnteredInfo.Password_To_Md5_Hash_String(CheckEnteredInfo.PasswordToString(passwordField.getPassword()));
				UserRequest request = new UserRequest(-1, textField.getText(), md5PasswordSum, "", "POST", "/users/");
				String temp = request.getQuerryString();
				//JOptionPane.showMessageDialog(null, temp);
				ToServer toserver = new ToServer(temp);
				answer = toserver.send();
				
				if(answer == "ERROR")
				{
					Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Server is not helth!");
					CheckEnteredInfo.PrintMessage("Сервер не отвечает, попробуйте позже");
				}
				else
				{
					Response response = new Response(answer);
					//CheckEnteredInfo.PrintMessage("Послан запрос: \n\n" + request + "\n\nПолучен ответ: \n\n" + answer + "\n" + response);	
					RegistrationResponse regResp = new RegistrationResponse(response.getBody());
					PublicData.id = regResp.getId();
					JOptionPane.showMessageDialog(null, "Вы успешно зарегестрированы!\nВаш ID: "+PublicData.id+", запомните его\nон будет использоваться при логин!");
					Login chat = new Login();
					chat.setVisible(true);
					myframe.setVisible(false);
					myframe.dispose();
					Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" User logging with id="+PublicData.id);
				}
			}
		});
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		button_1.addMouseListener(new MouseAdapter() { // cancel pressed
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Login login = new Login();
				login.setVisible(true);
				myframe.setVisible(false);
				myframe.dispose();
			}
		});
		contentPane.add(button_1);
	}

}
