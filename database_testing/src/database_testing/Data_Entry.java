package database_testing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;

public class Data_Entry extends JFrame{
	
	final static String loginPanel = "Login";
	final static String userPanel = " ";

	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String protocol = "jdbc:derby:";
	private static String dbName = "Music";
	
	private static String USER = "root";
	private static String PASS = "p4ssw0rd";
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtYourUsername;
	private JTextField txtYourPassword;
	private Integer userPrivilege;
	private boolean loggedIn = false;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Statement statement = null;
		Connection conn = null;
		ResultSet rs = null;
		ArrayList<Music> timeList = new ArrayList<Music>();
		
//		Music hellsbells = new Music("Hell's Bells", "ACDC");
		
		//checks database connection and table access.
//		try{
//			Class.forName(driver);
//			conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
//			statement = conn.createStatement();
//			System.out.println("connection established");
//			String createTable = "create Table MusicRecords (RecordName varchar(20), RecordArtist varchar(20))";
//			String getFromMusicRecords = "Select * from MusicRecords";
//			String addToRecords = "insert into MusicRecords Values('Hells Bells','ACDC')";
//			String del = "drop table MusicRecords";
//			try{
//				statement.executeUpdate(del);
//			}catch(SQLException e){
//				e.printStackTrace();
//				System.out.println("Table deletion failed");
//			}
//			try{
//				statement.executeUpdate(createTable);
//				System.out.println("Table Created Successfully");
//				try{
//					statement.executeUpdate(addToRecords);
//					System.out.println("Records added successfully");
//				}catch(SQLException e){
//					e.printStackTrace();
//					System.out.println("Table addition failed");
//				}
//				try{
//					statement.executeQuery(getFromMusicRecords);
//				}catch(SQLException e){
//					e.printStackTrace();
//					System.out.println("Table request failed");
//				}
//			}catch(SQLException e){
//				e.printStackTrace();
//				System.out.println("Table creation failed");
//			}
//		}catch(SQLException e){
//			e.printStackTrace(System.err);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Data_Entry frame = new Data_Entry();
					frame.setVisible(true);
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Data_Entry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		//ExitListener thanks to Baarn from StackOverflow
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ExitListener());
		mnFile.add(mntmQuit);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				//----------------------------------------------------------------//
			}
		});
		
		mnFile.add(mntmRefresh);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
//		if(!loggedIn){
//			mnTools.setVisible("false");
//		}
		
		JMenuItem mntmAddMusic = new JMenuItem("Add Music");
		mnTools.add(mntmAddMusic);
		
		JMenuItem mntmSearchMusic = new JMenuItem("Search Music");
		mnTools.add(mntmSearchMusic);
		
		JMenuItem mntmSearch = new JMenuItem("Search ");
		mnTools.add(mntmSearch);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());
		
		JPanel loginCard = new JPanel();
		contentPane.add(loginCard, loginPanel);
		GridBagLayout gbl_loginCard = new GridBagLayout();
		gbl_loginCard.columnWidths = new int[]{204, 0};
		gbl_loginCard.rowHeights = new int[]{198, 0};
		gbl_loginCard.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_loginCard.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		loginCard.setLayout(gbl_loginCard);

		JPanel userCard = new JPanel();
		contentPane.add(userCard,userPanel);
		GridBagLayout gbl_userCard = new GridBagLayout();
		gbl_userCard.columnWidths = new int[]{413, 0};
		gbl_userCard.rowHeights = new int[]{34, 0, 0};
		gbl_userCard.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_userCard.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		userCard.setLayout(gbl_userCard);

		JPanel newUserCard = new JPanel();
		contentPane.add(newUserCard,"new User");
		GridBagLayout gbl_newUserCard = new GridBagLayout();
		gbl_newUserCard.columnWidths = new int[]{0, 0};
		gbl_newUserCard.rowHeights = new int[]{0, 0};
		gbl_newUserCard.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_newUserCard.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		newUserCard.setLayout(gbl_newUserCard);
		
		JPanel addUserFinish = new JPanel();
		contentPane.add(addUserFinish,"addUserFinish");
		GridBagLayout gbl_addUserFinish = new GridBagLayout();
		gbl_addUserFinish.columnWidths = new int[]{0, 0};
		gbl_addUserFinish.rowHeights = new int[]{0, 0, 0};
		gbl_addUserFinish.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_addUserFinish.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		addUserFinish.setLayout(gbl_addUserFinish);
		
		JPanel addMusic = new JPanel();
		contentPane.add(addMusic,"addMusic");
		GridBagLayout gbl_addMusic = new GridBagLayout();
		gbl_addMusic.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_addMusic.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_addMusic.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_addMusic.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		addMusic.setLayout(gbl_addMusic);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		addMusic.add(label, gbc_label);
		
		JLabel lblAddMusic = new JLabel("Add Music");
		GridBagConstraints gbc_lblAddMusic = new GridBagConstraints();
		gbc_lblAddMusic.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddMusic.gridx = 4;
		gbc_lblAddMusic.gridy = 1;
		addMusic.add(lblAddMusic, gbc_lblAddMusic);
		
		JLabel lblBySong = new JLabel("by song");
		GridBagConstraints gbc_lblBySong = new GridBagConstraints();
		gbc_lblBySong.insets = new Insets(0, 0, 5, 0);
		gbc_lblBySong.gridx = 4;
		gbc_lblBySong.gridy = 2;
		addMusic.add(lblBySong, gbc_lblBySong);
		
		JLabel lblByFile = new JLabel("by file");
		GridBagConstraints gbc_lblByFile = new GridBagConstraints();
		gbc_lblByFile.insets = new Insets(0, 0, 5, 0);
		gbc_lblByFile.gridx = 4;
		gbc_lblByFile.gridy = 7;
		addMusic.add(lblByFile, gbc_lblByFile);
		
		JLabel lblFileName = new JLabel("File Name");
		GridBagConstraints gbc_lblFileName = new GridBagConstraints();
		gbc_lblFileName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFileName.gridx = 2;
		gbc_lblFileName.gridy = 8;
		addMusic.add(lblFileName, gbc_lblFileName);
		
		textField_6 = new JTextField();
		textField_6.setText(" ");
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 4;
		gbc_textField_6.gridy = 8;
		addMusic.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JButton btnImportFromFile = new JButton("Import From File");
		GridBagConstraints gbc_btnImportFromFile = new GridBagConstraints();
		gbc_btnImportFromFile.gridx = 4;
		gbc_btnImportFromFile.gridy = 9;
		addMusic.add(btnImportFromFile, gbc_btnImportFromFile);
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//NEW USER START<---------------------------------------------------------------------->
		////////////////////////////////////////////////////////////////////////////////////////
		JPanel newUserPanel = new JPanel();
		newUserPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_newUserPanel = new GridBagConstraints();
		gbc_newUserPanel.fill = GridBagConstraints.BOTH;
		gbc_newUserPanel.gridx = 0;
		gbc_newUserPanel.gridy = 0;
		newUserCard.add(newUserPanel, gbc_newUserPanel);
		GridBagLayout gbl_newUserPanel = new GridBagLayout();
		gbl_newUserPanel.columnWidths = new int[]{0, 133, 0};
		gbl_newUserPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_newUserPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_newUserPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		newUserPanel.setLayout(gbl_newUserPanel);
		
		JLabel newName = new JLabel("Name:");
		GridBagConstraints gbc_newName = new GridBagConstraints();
		gbc_newName.fill = GridBagConstraints.HORIZONTAL;
		gbc_newName.insets = new Insets(0, 0, 5, 5);
		gbc_newName.gridx = 0;
		gbc_newName.gridy = 0;
		newUserPanel.add(newName, gbc_newName);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		newUserPanel.add(textField, gbc_textField);
		
		JLabel newUserName = new JLabel("User Name:");
		GridBagConstraints gbc_newUserName = new GridBagConstraints();
		gbc_newUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_newUserName.insets = new Insets(0, 0, 5, 5);
		gbc_newUserName.gridx = 0;
		gbc_newUserName.gridy = 1;
		newUserPanel.add(newUserName, gbc_newUserName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		newUserPanel.add(textField_1, gbc_textField_1);
		
		JLabel newPassword = new JLabel("Password:");
		GridBagConstraints gbc_newPassword = new GridBagConstraints();
		gbc_newPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPassword.insets = new Insets(0, 0, 5, 5);
		gbc_newPassword.gridx = 0;
		gbc_newPassword.gridy = 2;
		newUserPanel.add(newPassword, gbc_newPassword);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		newUserPanel.add(textField_2, gbc_textField_2);
		
		JLabel newPhoneNumber = new JLabel("Phone Number:");
		GridBagConstraints gbc_newPhoneNumber = new GridBagConstraints();
		gbc_newPhoneNumber.anchor = GridBagConstraints.EAST;
		gbc_newPhoneNumber.insets = new Insets(0, 0, 5, 5);
		gbc_newPhoneNumber.gridx = 0;
		gbc_newPhoneNumber.gridy = 3;
		newUserPanel.add(newPhoneNumber, gbc_newPhoneNumber);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		newUserPanel.add(textField_3, gbc_textField_3);
		
		JLabel newEmail = new JLabel("Email:");
		GridBagConstraints gbc_newEmail = new GridBagConstraints();
		gbc_newEmail.anchor = GridBagConstraints.WEST;
		gbc_newEmail.insets = new Insets(0, 0, 5, 5);
		gbc_newEmail.gridx = 0;
		gbc_newEmail.gridy = 4;
		newUserPanel.add(newEmail, gbc_newEmail);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		newUserPanel.add(textField_4, gbc_textField_4);
		
		JButton newUserCancel = new JButton("Cancel");
		GridBagConstraints gbc_newUserCancel = new GridBagConstraints();
		gbc_newUserCancel.insets = new Insets(0, 0, 0, 5);
		gbc_newUserCancel.gridx = 0;
		gbc_newUserCancel.gridy = 5;
		newUserPanel.add(newUserCancel, gbc_newUserCancel);
		
		JButton newUserAdd = new JButton("Add");
		GridBagConstraints gbc_newUserAdd = new GridBagConstraints();
		gbc_newUserAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_newUserAdd.gridx = 1;
		gbc_newUserAdd.gridy = 5;
		newUserPanel.add(newUserAdd, gbc_newUserAdd);
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//NEW USER ENDT<---------------------------------------------------------------------->


	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//TOP PANEL<-------------------------------------------------------------------------->
		
		JPanel top_panel = new JPanel();
		GridBagConstraints gbc_top_panel = new GridBagConstraints();
		gbc_top_panel.anchor = GridBagConstraints.WEST;
		gbc_top_panel.gridx = 0;
		gbc_top_panel.gridy = 0;
		loginCard.add(top_panel, gbc_top_panel);
		top_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel figure_panel = new JPanel();
		top_panel.add(figure_panel);
		
		JPanel login_panel = new JPanel();
		top_panel.add(login_panel);
		GridBagLayout gbl_login_panel = new GridBagLayout();
		gbl_login_panel.columnWidths = new int[]{0, 0, 0};
		gbl_login_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_login_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_login_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		login_panel.setLayout(gbl_login_panel);
		
		
		JLabel lblUserName = new JLabel("User Name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 0;
		login_panel.add(lblUserName, gbc_lblUserName);
		
		txtYourUsername = new JTextField();
		txtYourUsername.setText("Your UserName");
		GridBagConstraints gbc_txtYourUsername = new GridBagConstraints();
		gbc_txtYourUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtYourUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYourUsername.gridx = 1;
		gbc_txtYourUsername.gridy = 0;
		login_panel.add(txtYourUsername, gbc_txtYourUsername);
		txtYourUsername.setColumns(10);
		
		JLabel lblPassWord = new JLabel("Password:");
		GridBagConstraints gbc_lblPassWord = new GridBagConstraints();
		gbc_lblPassWord.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassWord.gridx = 0;
		gbc_lblPassWord.gridy = 1;
		login_panel.add(lblPassWord, gbc_lblPassWord);
		
		txtYourPassword = new JTextField();
		txtYourPassword.setText("Your Password");
		GridBagConstraints gbc_txtYourPassword = new GridBagConstraints();
		gbc_txtYourPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtYourPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYourPassword.gridx = 1;
		gbc_txtYourPassword.gridy = 1;
		login_panel.add(txtYourPassword, gbc_txtYourPassword);
		txtYourPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 2;
		login_panel.add(btnLogin, gbc_btnLogin);
		
		JButton btnNewUser = new JButton("New User");
		GridBagConstraints gbc_btnNewUser = new GridBagConstraints();
		gbc_btnNewUser.gridx = 1;
		gbc_btnNewUser.gridy = 2;
		login_panel.add(btnNewUser, gbc_btnNewUser);
	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//TOP PANEL END <------------------------------------------------------------------>

	  //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		//LOGGED IN PANEL <---------------------------------------------------------------->
		
		JPanel logged_in = new JPanel();
		GridBagConstraints gbc_logged_in = new GridBagConstraints();
		gbc_logged_in.fill = GridBagConstraints.BOTH;
		gbc_logged_in.insets = new Insets(0, 0, 5, 0);
		gbc_logged_in.gridx = 0;
		gbc_logged_in.gridy = 0;
		userCard.add(logged_in, gbc_logged_in);
		GridBagLayout gbl_logged_in = new GridBagLayout();
		gbl_logged_in.columnWidths = new int[]{0, 351, 0};
		gbl_logged_in.rowHeights = new int[]{0, 0};
		gbl_logged_in.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_logged_in.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		logged_in.setLayout(gbl_logged_in);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 0, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		logged_in.add(lblUsername, gbc_lblUsername);
		
		JButton btnLogout = new JButton("Logout");
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.anchor = GridBagConstraints.EAST;
		gbc_btnLogout.gridx = 1;
		gbc_btnLogout.gridy = 0;
		logged_in.add(btnLogout, gbc_btnLogout);
		
		JPanel userCards = new JPanel();
		GridBagConstraints gbc_userCards = new GridBagConstraints();
		gbc_userCards.fill = GridBagConstraints.BOTH;
		gbc_userCards.gridx = 0;
		gbc_userCards.gridy = 1;
		userCard.add(userCards, gbc_userCards);
		GridBagLayout gbl_userCards = new GridBagLayout();
		gbl_userCards.columnWidths = new int[]{413, 0};
		gbl_userCards.rowHeights = new int[]{0, 0};
		gbl_userCards.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_userCards.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		userCards.setLayout(gbl_userCards);
		
		//LOGGED IN END <------------------------------------------------------------>
		
		//OUTPUT START <------------------------------------------------------------->
		
		JPanel output_Panel = new JPanel();
		GridBagConstraints gbc_output_Panel = new GridBagConstraints();
		gbc_output_Panel.anchor = GridBagConstraints.WEST;
		gbc_output_Panel.fill = GridBagConstraints.VERTICAL;
		gbc_output_Panel.gridx = 0;
		gbc_output_Panel.gridy = 0;
		userCards.add(output_Panel, gbc_output_Panel);
		GridBagLayout gbl_output_Panel = new GridBagLayout();
		gbl_output_Panel.columnWidths = new int[]{0, 312, 0};
		gbl_output_Panel.rowHeights = new int[]{0, 0};
		gbl_output_Panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_output_Panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		output_Panel.setLayout(gbl_output_Panel);
		
		JToolBar toolBar = new JToolBar(null, JToolBar.VERTICAL);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.fill = GridBagConstraints.VERTICAL;
		gbc_toolBar.insets = new Insets(0, 0, 0, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		output_Panel.add(toolBar, gbc_toolBar);
		
		JPanel panel = new JPanel();
		toolBar.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{119, 0};
		gbl_panel.rowHeights = new int[]{31, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSearchMusic = new JLabel("Search Music");
		GridBagConstraints gbc_lblSearchMusic = new GridBagConstraints();
		gbc_lblSearchMusic.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchMusic.gridx = 0;
		gbc_lblSearchMusic.gridy = 0;
		panel.add(lblSearchMusic, gbc_lblSearchMusic);
		
		JCheckBox chckbxSongTitle = new JCheckBox("Song Title");
		GridBagConstraints gbc_chckbxSongTitle = new GridBagConstraints();
		gbc_chckbxSongTitle.anchor = GridBagConstraints.WEST;
		gbc_chckbxSongTitle.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSongTitle.gridx = 0;
		gbc_chckbxSongTitle.gridy = 1;
		panel.add(chckbxSongTitle, gbc_chckbxSongTitle);
		
		JCheckBox chckbxArtistTitle = new JCheckBox("Artist");
		GridBagConstraints gbc_chckbxArtistTitle = new GridBagConstraints();
		gbc_chckbxArtistTitle.anchor = GridBagConstraints.WEST;
		gbc_chckbxArtistTitle.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxArtistTitle.gridx = 0;
		gbc_chckbxArtistTitle.gridy = 2;
		panel.add(chckbxArtistTitle, gbc_chckbxArtistTitle);
		
		textField_5 = new JTextField();
		textField_5.setText(" ");
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 0;
		gbc_textField_5.gridy = 3;
		panel.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 0;
		output_Panel.add(table, gbc_table);
		
		//OUTPUT END<--------------------------------------------------------------------->
		
		//addUserResult Start
		

		JLabel lblResultOfAdding = new JLabel("Add new user result");
		GridBagConstraints gbc_lblResultOfAdding = new GridBagConstraints();
		gbc_lblResultOfAdding.insets = new Insets(0, 0, 5, 0);
		gbc_lblResultOfAdding.gridx = 0;
		gbc_lblResultOfAdding.gridy = 0;
		addUserFinish.add(lblResultOfAdding, gbc_lblResultOfAdding);
		
		JButton returnToLogin = new JButton("Return to login screen");
		GridBagConstraints gbc_returnToLogin = new GridBagConstraints();
		gbc_returnToLogin.gridx = 0;
		gbc_returnToLogin.gridy = 1;
		addUserFinish.add(returnToLogin, gbc_returnToLogin);
		
		//action handlers<---------------------------------------------------------------->
		
		//Login button actions for visibility settings.
		btnLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,userPanel);
			}
		});
		
		btnLogout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,loginPanel);
				pack();
			}
		});	
		btnNewUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,"new User");
				pack();
			}
		});
		newUserCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,loginPanel);
				pack();
			}
		});
		newUserAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,"addUserFinish");
				pack();
			}
		});
		returnToLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane,loginPanel);
				pack();
			}
		});
	}
}
