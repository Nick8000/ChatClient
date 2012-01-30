package team.chat.panels;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;

import team.chat.client.Logger;
import team.chat.client.PublicData;
import team.chat.client.ToServer;
import team.chat.our.ResponseCodes;
import team.chat.request.MessageRequest;
import team.chat.request.RoomsRequest;
import team.chat.response.MessageResponse;
import team.chat.response.Response;
import team.chat.response.UserResponse;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

public class ChatFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextArea textArea;
	private static JTextArea textArea_1;
	private boolean needEnter;
	private JList list_2;
	private JList list_1;
	private JList list;
	private JButton button;
	private JToggleButton toggleButton;
	private DefaultListModel listModel1;
	private DefaultListModel listModel2;
	/**
	 * Create the frame.
	 */
	public ChatFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Logout();
			}
		});
		setBackground(new Color(173, 255, 47));
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowActivated(WindowEvent arg0) {
//				ChangeAccesToMessageControls(false);
//			}
//		});
		setTitle("Ваш ID: "+PublicData.id);
		needEnter = false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 351);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 255, 47));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		new JScrollPane();
		setContentPane(contentPane);
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		
		button = new JButton("\u041E\u0442\u043F\u0440\u0430\u0432\u0438\u0442\u044C");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) { // send message to the current room
				SendMessageToRoom();
			}
		});
		listModel2 = new DefaultListModel();
		PublicData.listModel2 = listModel2;
		list = new JList(listModel2);
		list.setBackground(new Color(176, 224, 230));
		list.setToolTipText("\u041F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u0438 \u0432 \u0441\u0435\u0442\u0438");
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		listModel1 = new DefaultListModel();
		Collection c = PublicData.roomsList.values();
		Iterator it = c.iterator();
		while(it.hasNext())
		{
			listModel1.addElement(it.next());
		}
		
		list_1 = new JList(listModel1);
		list_1.setBackground(new Color(173, 216, 230));
		list_1.setToolTipText("\u0414\u043E\u0441\u0442\u0443\u043F\u043D\u044B\u0435 \u043A\u043E\u043C\u043D\u0430\u0442\u044B");
		list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JScrollPane scrollPane_2 = new JScrollPane();
		
		toggleButton = new JToggleButton("");
		toggleButton.setToolTipText("\u041E\u0442\u043F\u0440\u0430\u0432\u043A\u0430 \u0441\u043E\u043E\u0431\u0449\u0435\u043D\u0438\u0439 \u043F\u043E \u043D\u0430\u0436\u0430\u0442\u0438\u044E \u043A\u043B\u0430\u0432\u0438\u0448\u0438 Enter");
		toggleButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				needEnter = !needEnter;
			}
		});
		toggleButton.setIcon(new ImageIcon("C:\\Users\\\u041D\u0438\u043A\\git\\ChatClient\\ChatClient\\enter.gif"));
		
		JSeparator separator = new JSeparator();
		
		JToggleButton toggleButton_1 = new JToggleButton("");
		toggleButton_1.addMouseListener(new MouseAdapter() { // 
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				EnterToTheRoom();
			}
		});
//		toggleButton_1.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				list_2.setVisible(showFriends);
//				showFriends = !showFriends;
//			}
//		});
		toggleButton_1.setToolTipText("\u0414\u0440\u0443\u0437\u044C\u044F");
		toggleButton_1.setIcon(new ImageIcon("C:\\Users\\\u041D\u0438\u043A\\git\\ChatClient\\ChatClient\\icon_friends.png"));
		
		list_2 = new JList();
		list_2.setToolTipText("\u0414\u0440\u0443\u0437\u044C\u044F");
		list_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list_2.setVisible(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addComponent(list_2, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(toggleButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button)
							.addGap(111)
							.addComponent(toggleButton_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(toggleButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(toggleButton_1))
					.addGap(0))
		);
		textArea_1 = new JTextArea();
		textArea_1.setBackground(new Color(224, 255, 255));
		scrollPane_2.setViewportView(textArea_1);
		textArea_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(!needEnter)
					return;
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					SendMessageToRoom();
			}
		});
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		
		addPopup(list_1, popupMenu_1);
		
		JMenuItem menuItem_3 = new JMenuItem("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043A\u043E\u043C\u043D\u0430\u0442\u0443");
		
		popupMenu_1.add(menuItem_3);
		
		JMenuItem menuItem_5 = new JMenuItem("\u0412\u043E\u0439\u0442\u0438 \u0432 \u043A\u043E\u043C\u043D\u0430\u0442\u0443");
			
		popupMenu_1.add(menuItem_5);
		
		JMenuItem menuItem_4 = new JMenuItem("\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043A\u043E\u043C\u043D\u0430\u0442\u0443");
		popupMenu_1.add(menuItem_4);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(list, popupMenu);
		
		JMenuItem menuItem = new JMenuItem("\u0418\u043D\u0444\u043E");
		popupMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u041D\u0430\u043F\u0438\u0441\u0430\u0442\u044C \u0441\u043E\u043E\u0431\u0449\u0435\u043D\u0438\u0435");
		popupMenu.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0432 \u0434\u0440\u0443\u0437\u044C\u044F");
		popupMenu.add(menuItem_2);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(255, 222, 173));
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
		
		PublicData.messageField = textArea_1;
		PublicData.messageArea =  textArea;
		PublicData.roomsControl = list_1;
		PublicData.friends = list_2;
		
	}
	public static void PrintMessage(String sender, String message)
	{
		if(message.equals(""))
			return;
		textArea.setText(textArea.getText()+sender+" написал:\n"+message+"\n\n");
		textArea_1.setText("");
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public static int getCurrentRoomId(Object room)
	{
		int id = -1;
		for (Entry<Integer, String> entry : PublicData.roomsList.entrySet()) {
            if (entry.getValue().equals(room)) {
                id = entry.getKey();
            }
        }
		return id;
	}
	private void EnterToTheRoom()
	{
		Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" EnterToTheRoom");
		if(PublicData.currentRoom.equals(listModel1.get(list_1.getSelectedIndex())))
				return;
		if(!PublicData.currentRoom.equals("") && !PublicData.currentRoom.equals(listModel1.get(list_1.getSelectedIndex())))
			ExitFromRoom();
		textArea.setText("");
		PublicData.usersList.clear();
		PublicData.currentRoom = (String) listModel1.get(list_1.getSelectedIndex());
		RoomsRequest room = new RoomsRequest("POST", "/chatroom/member/", getCurrentRoomId(listModel1.get(list_1.getSelectedIndex())), ResponseCodes.addToChatRoom.toString());
		String query = room.getQuery();
		ToServer toserver = new ToServer(query);
		String answer = toserver.send();
		Response response =  new Response(answer);
		UserResponse users = new UserResponse(response.getBody());	
		MessageResponse messages = new MessageResponse(response.getBody());
		PublicData.messagesList.clear();
		for (Entry<Integer, String> entry : PublicData.messagesList.entrySet()) {
            PrintMessage(entry.getKey().toString(), entry.getValue());
        }
		
		listModel2.clear();
		Collection c = PublicData.usersList.values();
		Iterator it = c.iterator();
		while(it.hasNext())
		{
			listModel2.addElement(it.next());
		}
		Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Enter To The Room with Id "+PublicData.currentRoom);
	}
	private void SendMessageToRoom()
	{
		if(getCurrentRoomId(PublicData.currentRoom) == -1)
		{
			JOptionPane.showMessageDialog(null, "Войдите в одну из комнат!");
			textArea_1.setText("");
			return;
		}
		if(textArea_1.getText().equals(""))
			return;
		MessageRequest message = new MessageRequest("POST", "/chatroom/message/", textArea_1.getText(), PublicData.id, getCurrentRoomId(listModel1.get(list_1.getSelectedIndex())));
		String query = message.getQuerryString();
		ToServer toserver = new ToServer(query);
		query = toserver.send();
		if(query.contains("MessageIsDelivered"))
			PrintMessage(Integer.toString(PublicData.id),  textArea_1.getText());
		else
			Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" SendMessageToRoom\nMessage:"+textArea_1.getText());
	}
	public void Logout()
	{
		String h = "";
		if(getCurrentRoomId(PublicData.currentRoom) == -1)
			h = "777";
		else
		{
			ExitFromRoom();
			h = Integer.toString(getCurrentRoomId(PublicData.currentRoom));
		}
		String req = "GET /logout/"+PublicData.id+"/room/"+h+"/ HTTP/1.1";
		ToServer toserver = new ToServer(req);
		toserver.send();
		Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" Send to the server that the user leave room!");
	}
	public void ExitFromRoom()
	{
		if(getCurrentRoomId(PublicData.currentRoom) == -1)
			return;
		String req = "DELETE /chatroom/"+getCurrentRoomId(PublicData.currentRoom)+"/member/"+PublicData.id+"/ HTTP/1.1";
		ToServer toserver = new ToServer(req);
		toserver.send();
		Logger.Print(Thread.currentThread().getStackTrace()[1].getClassName()+" was Logout and exit!!");
	}
}
