package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import service.AppCommon;
import service.AppCreateContest;
import service.AppLogin;
import service.AppSubmitContest;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AppUi {

	private AppLogin appLogin;
	// private AppCommon appCommon;
	private AppSubmitContest appSubmitContest;
	private AppCreateContest appCreateContest;
	public AppActionListener actionListener;
	
	private JFrame frmRankingBallAuto;
	private JPanel mainPanel;
	private JPanel createPanel;
	private JPanel submitPanel;
	
	private JTextField emailLable;
	private JTextField passwordLabel;
	private JTextField emailText;
	private JButton loginButton;
	private JPasswordField passwordText;
	public JButton submitOkBtn;
	private JButton submitContestBtn;
	private JButton createContestBtn;
	private JButton createStopBtn;
	private JButton createBackBtn;
	private JButton createOkBtn;
	public JButton submitStopBtn;
	public JButton submitBackBtn;
	public JTextField submitResultText;
	private JTextField selectGameType;
	private ButtonGroup submitSportsBtnGroup;
	private ButtonGroup submitCurrencyBtnGroup;
	private ButtonGroup sportsBtnGroup;
	private ButtonGroup currencyBtnGroup;
	private ButtonGroup typeBtnGroup;
	private JTextField txtCurrency;
	private JTextField txtEntryfee;
	private JTextField txtEntries;
	private JToggleButton submitCurrencyAll;
	private JToggleButton gameTypeLol;
	private JToggleButton gameTypeBaseball;
	private JToggleButton gameTypeSoc;
	private JToggleButton gameTypeBsk;
	private JToggleButton gameTypeFob;
	private JToggleButton currencyGdc;
	private JComboBox entriesCombo;
	private JComboBox entryFeeCombo;
	private JTextField contestCounts;
	private JTextField txtCount;
	private JTextField createResultText;
	private JToggleButton submitGameTypeLol;
	private JToggleButton submitGameTypeBaseball;
	private JToggleButton submitGameTypeSoc;
	private JToggleButton submitGameTypeBsk;
	private JToggleButton submitGameTypeFob;
	private JTextField submitSports;
	private JTextField submitCurrency;
	private JTextField createLimitText;
	private JTextField gameType;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppUi window = new AppUi();
					window.frmRankingBallAuto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppUi() {
		appLogin = new AppLogin();
		// appCommon = new AppCommon();
		actionListener = new AppActionListener();
		
		AppCommon.getReadProperties();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRankingBallAuto = new JFrame();
		frmRankingBallAuto.getContentPane().setBackground(Color.WHITE);
		frmRankingBallAuto.setBackground(Color.WHITE);
		frmRankingBallAuto.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("RankingBallIcon.ico")));
		frmRankingBallAuto.setResizable(false);
		frmRankingBallAuto.setTitle("GDC Mining Machine");
		frmRankingBallAuto.setBounds(100, 100, 346, 636);
		frmRankingBallAuto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRankingBallAuto.getContentPane().setLayout(null);
		
		JLabel mainTopImg = new JLabel("");
		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("RankingBallTop.PNG"));
		mainTopImg.setIcon(new ImageIcon(img));
		mainTopImg.setBounds(0, 0, 340, 247);
		frmRankingBallAuto.getContentPane().add(mainTopImg);

		// Main Panel ===================================================================================
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 257, 342, 351);
		frmRankingBallAuto.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel mainBottomImg = new JLabel("");
		Image bottomImg = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("RankingBallBottom.PNG"));
		mainBottomImg.setIcon(new ImageIcon(bottomImg));
		mainBottomImg.setBounds(0, 160, 342, 190);
		mainPanel.add(mainBottomImg);
		
		emailLable = new JTextField();
		emailLable.setBorder(null);
		emailLable.setBounds(7, 12, 53, 21);
		mainPanel.add(emailLable);
		emailLable.setHorizontalAlignment(SwingConstants.CENTER);
		emailLable.setEditable(false);
		emailLable.setBackground(Color.WHITE);
		emailLable.setFont(new Font("±¼¸²", Font.BOLD, 12));
		emailLable.setText("Email");
		emailLable.setColumns(10);
		
		passwordLabel = new JTextField();
		passwordLabel.setBounds(12, 39, 70, 21);
		mainPanel.add(passwordLabel);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBorder(null);
		passwordLabel.setEditable(false);
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setFont(new Font("±¼¸²", Font.BOLD, 12));
		passwordLabel.setText("Password");
		passwordLabel.setColumns(10);
		
		loginButton = new JButton("LOGIN");
		loginButton.setBounds(260, 9, 70, 52);
		mainPanel.add(loginButton);
		loginButton.setFocusable(false);
		loginButton.setActionCommand("LOGIN");
		loginButton.setFont(new Font("±¼¸²", Font.BOLD, 10));
		
		emailText = new JTextField();
		emailText.setBounds(89, 10, 160, 21);
		mainPanel.add(emailText);
		emailText.setColumns(10);
		if (AppCommon.USER_ID != null) {
			emailText.setText(AppCommon.USER_ID);
		}
		
		passwordText = new JPasswordField();
		passwordText.setBounds(89, 39, 160, 21);
		mainPanel.add(passwordText);
		
		submitContestBtn = new JButton("SUBMIT");
		submitContestBtn.setBounds(12, 92, 155, 44);
		mainPanel.add(submitContestBtn);
		submitContestBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitContestBtn.setVisible(false);
		submitContestBtn.setActionCommand("SUBMITCONTEST");
		
		createContestBtn = new JButton("CREATE");
		createContestBtn.setBounds(170, 92, 160, 44);
		mainPanel.add(createContestBtn);
		createContestBtn.setVisible(false);
		createContestBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createContestBtn.setActionCommand("CREATECONTEST");
		
		// Main Panel End=================================================================================
		
		// Create Panel ===================================================================================
		createPanel = new JPanel();
		createPanel.setVisible(false);
		createPanel.setBackground(Color.WHITE);
		createPanel.setBounds(0, 257, 342, 351);
		frmRankingBallAuto.getContentPane().add(createPanel);
		createPanel.setLayout(null);
		
		sportsBtnGroup = new ButtonGroup();
		currencyBtnGroup = new ButtonGroup();
		typeBtnGroup = new ButtonGroup();
		
		gameTypeLol = new JToggleButton("League Of Legends");
		gameTypeLol.setSelected(true);
		gameTypeLol.setActionCommand("lol");
		gameTypeLol.setName("lol");
		gameTypeLol.setBounds(15, 22, 155, 28);
		createPanel.add(gameTypeLol);
		sportsBtnGroup.add(gameTypeLol);
		
		gameTypeBaseball = new JToggleButton("Baseball");
		gameTypeBaseball.setActionCommand("baseball");
		gameTypeBaseball.setName("baseball");
		gameTypeBaseball.setEnabled(false);
		gameTypeBaseball.setBounds(172, 22, 155, 28);
		createPanel.add(gameTypeBaseball);
		sportsBtnGroup.add(gameTypeBaseball);
		
		gameTypeSoc = new JToggleButton("Soccer");
		gameTypeSoc.setActionCommand("soccer");
		gameTypeSoc.setName("soccer");
		gameTypeSoc.setEnabled(false);
		gameTypeSoc.setBounds(15, 53, 101, 28);
		createPanel.add(gameTypeSoc);
		sportsBtnGroup.add(gameTypeSoc);
		
		gameTypeBsk = new JToggleButton("Basketball");
		gameTypeBsk.setActionCommand("basketball");
		gameTypeBsk.setName("basketball");
		gameTypeBsk.setEnabled(false);
		gameTypeBsk.setBounds(226, 53, 101, 28);
		createPanel.add(gameTypeBsk);
		sportsBtnGroup.add(gameTypeBsk);
		
		gameTypeFob = new JToggleButton("Football");
		gameTypeFob.setActionCommand("football");
		gameTypeFob.setName("football");
		gameTypeFob.setEnabled(false);
		gameTypeFob.setBounds(120, 53, 102, 28);
		createPanel.add(gameTypeFob);
		sportsBtnGroup.add(gameTypeFob);
		
		selectGameType = new JTextField();
		selectGameType.setBackground(Color.WHITE);
		selectGameType.setEditable(false);
		selectGameType.setFont(new Font("±¼¸²", Font.BOLD, 12));
		selectGameType.setBorder(null);
		selectGameType.setText("Sports");
		selectGameType.setBounds(146, 0, 49, 21);
		createPanel.add(selectGameType);
		selectGameType.setColumns(10);
		
		txtCurrency = new JTextField();
		txtCurrency.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrency.setBackground(Color.WHITE);
		txtCurrency.setEditable(false);
		txtCurrency.setText("Currency");
		txtCurrency.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtCurrency.setColumns(10);
		txtCurrency.setBorder(null);
		txtCurrency.setBounds(135, 82, 69, 21);
		createPanel.add(txtCurrency);
		
		currencyGdc = new JToggleButton("GDC");
		currencyGdc.setSelected(true);
		currencyGdc.setActionCommand("gdc");
		currencyGdc.setBounds(15, 103, 156, 28);
		createPanel.add(currencyGdc);
		currencyBtnGroup.add(currencyGdc);
		
		JToggleButton currencyPoint = new JToggleButton("Point");
		currencyPoint.setActionCommand("point");
		currencyPoint.setBounds(171, 103, 156, 28);
		createPanel.add(currencyPoint);
		currencyBtnGroup.add(currencyPoint);
		
		entryFeeCombo = new JComboBox();
		entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
		entryFeeCombo.setMaximumRowCount(4);
		entryFeeCombo.setBounds(15, 157, 156, 28);
		createPanel.add(entryFeeCombo);
		
		txtEntryfee = new JTextField();
		txtEntryfee.setBackground(Color.WHITE);
		txtEntryfee.setEditable(false);
		txtEntryfee.setText("Entry Fee");
		txtEntryfee.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtEntryfee.setColumns(10);
		txtEntryfee.setBorder(null);
		txtEntryfee.setBounds(58, 135, 69, 21);
		createPanel.add(txtEntryfee);
		
		txtEntries = new JTextField();
		txtEntries.setBackground(Color.WHITE);
		txtEntries.setEditable(false);
		txtEntries.setText("Entries");
		txtEntries.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtEntries.setColumns(10);
		txtEntries.setBorder(null);
		txtEntries.setBounds(217, 135, 49, 21);
		createPanel.add(txtEntries);
		
		entriesCombo = new JComboBox();
		entriesCombo.setMaximumRowCount(20);
		entriesCombo.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		entriesCombo.setBounds(171, 157, 156, 28);
		createPanel.add(entriesCombo);
		
		createBackBtn = new JButton("<");
		createBackBtn.setForeground(Color.BLACK);
		createBackBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createBackBtn.setActionCommand("CREATEBACK");
		createBackBtn.setBounds(13, 300, 49, 35);
		createPanel.add(createBackBtn);
		
		createStopBtn = new JButton("STOP");
		createStopBtn.setActionCommand("CREATESTOP");
		createStopBtn.setForeground(Color.RED);
		createStopBtn.setEnabled(false);
		createStopBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createStopBtn.setBounds(66, 300, 130, 35);
		createPanel.add(createStopBtn);
		
		createOkBtn = new JButton("CREATE");
		createOkBtn.setActionCommand("CREATEOK");
		createOkBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createOkBtn.setForeground(Color.BLUE);
		createOkBtn.setBounds(199, 300, 130, 35);
		createPanel.add(createOkBtn);
		
		contestCounts = new JTextField();
		contestCounts.setHorizontalAlignment(SwingConstants.RIGHT);
		contestCounts.setText("0");
		contestCounts.setToolTipText("");
		contestCounts.setBounds(286, 242, 41, 21);
		createPanel.add(contestCounts);
		contestCounts.setColumns(10);
		
		txtCount = new JTextField();
		txtCount.setBackground(Color.WHITE);
		txtCount.setEditable(false);
		txtCount.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtCount.setBorder(null);
		txtCount.setText("Count");
		txtCount.setBounds(241, 242, 41, 21);
		createPanel.add(txtCount);
		txtCount.setColumns(10);
		
		createResultText = new JTextField();
		createResultText.setBorder(null);
		// createResultText.setVisible(false);
		createResultText.setBackground(Color.WHITE);
		createResultText.setEditable(false);
		createResultText.setForeground(Color.RED);
		createResultText.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createResultText.setHorizontalAlignment(SwingConstants.CENTER);
		createResultText.setBounds(15, 270, 311, 21);
		createPanel.add(createResultText);
		createResultText.setColumns(10);
		
		createLimitText = new JTextField();
		createLimitText.setBorder(null);
		createLimitText.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createLimitText.setForeground(Color.RED);
		createLimitText.setBounds(15, 242, 155, 21);
		createPanel.add(createLimitText);
		createLimitText.setColumns(10);
		
		gameType = new JTextField();
		gameType.setHorizontalAlignment(SwingConstants.CENTER);
		gameType.setText("Type");
		gameType.setFont(new Font("±¼¸²", Font.BOLD, 12));
		gameType.setEditable(false);
		gameType.setColumns(10);
		gameType.setBorder(null);
		gameType.setBackground(Color.WHITE);
		gameType.setBounds(135, 188, 69, 21);
		createPanel.add(gameType);
		
		JToggleButton typeAllBtn = new JToggleButton("All");
		typeAllBtn.setActionCommand("All");
		typeAllBtn.setSelected(true);
		typeAllBtn.setBounds(15, 209, 77, 28);
		createPanel.add(typeAllBtn);
		typeBtnGroup.add(typeAllBtn);
		
		JToggleButton typeTnmtBtn = new JToggleButton("TNMT");
		typeTnmtBtn.setActionCommand("Tournaments");
		typeTnmtBtn.setBounds(94, 209, 77, 28);
		createPanel.add(typeTnmtBtn);
		typeBtnGroup.add(typeTnmtBtn);
		
		JToggleButton typeFiftyBtn = new JToggleButton("50/50");
		typeFiftyBtn.setActionCommand("50/50");
		typeFiftyBtn.setBounds(172, 209, 77, 28);
		createPanel.add(typeFiftyBtn);
		typeBtnGroup.add(typeFiftyBtn);
		
		JToggleButton typeThirtyBtn = new JToggleButton("30/30");
		typeThirtyBtn.setActionCommand("30/30");
		typeThirtyBtn.setBounds(251, 209, 77, 28);
		createPanel.add(typeThirtyBtn);
		typeBtnGroup.add(typeThirtyBtn);
		
		// Create Panel End=================================================================================
		
		// Submit Panel ===================================================================================
		
		submitSportsBtnGroup = new ButtonGroup();
		submitPanel = new JPanel();
		submitPanel.setVisible(false);
		submitPanel.setBounds(0, 257, 342, 351);
		submitPanel.setBackground(Color.WHITE);
		frmRankingBallAuto.getContentPane().add(submitPanel);
		submitPanel.setLayout(null);
		
		submitGameTypeLol = new JToggleButton("League Of Legends");
		submitGameTypeLol.setSelected(true);
		submitGameTypeLol.setName("lol");
		submitGameTypeLol.setActionCommand("lol");
		submitGameTypeLol.setBounds(15, 22, 155, 28);
		submitPanel.add(submitGameTypeLol);
		submitSportsBtnGroup.add(submitGameTypeLol);
		
		submitGameTypeBaseball = new JToggleButton("Baseball");
		submitGameTypeBaseball.setName("baseball");
		submitGameTypeBaseball.setEnabled(false);
		submitGameTypeBaseball.setActionCommand("submitBaseball");
		submitGameTypeBaseball.setBounds(172, 22, 155, 28);
		submitPanel.add(submitGameTypeBaseball);
		submitSportsBtnGroup.add(submitGameTypeBaseball);
		
		submitGameTypeSoc = new JToggleButton("Soccer");
		submitGameTypeSoc.setName("soccer");
		submitGameTypeSoc.setEnabled(false);
		submitGameTypeSoc.setActionCommand("submitSoccer");
		submitGameTypeSoc.setBounds(15, 53, 101, 28);
		submitPanel.add(submitGameTypeSoc);
		submitSportsBtnGroup.add(submitGameTypeSoc);
		
		submitGameTypeBsk = new JToggleButton("Basketball");
		submitGameTypeBsk.setName("basketball");
		submitGameTypeBsk.setEnabled(false);
		submitGameTypeBsk.setActionCommand("submitBasketball");
		submitGameTypeBsk.setBounds(226, 53, 101, 28);
		submitPanel.add(submitGameTypeBsk);
		submitSportsBtnGroup.add(submitGameTypeBsk);
		
		submitGameTypeFob = new JToggleButton("Football");
		submitGameTypeFob.setName("football");
		submitGameTypeFob.setEnabled(false);
		submitGameTypeFob.setActionCommand("submitFootball");
		submitGameTypeFob.setBounds(120, 53, 102, 28);
		submitPanel.add(submitGameTypeFob);
		submitSportsBtnGroup.add(submitGameTypeFob);
		
		submitSports = new JTextField();
		submitSports.setBounds(146, 0, 49, 21);
		submitSports.setText("Sports");
		submitSports.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitSports.setEditable(false);
		submitSports.setColumns(10);
		submitSports.setBorder(null);
		submitSports.setBackground(Color.WHITE);
		submitPanel.add(submitSports);
		
		submitResultText = new JTextField();
		submitResultText.setBorder(null);
		submitResultText.setBounds(15, 269, 312, 21);
		submitResultText.setBackground(Color.WHITE);
		submitResultText.setForeground(Color.BLUE);
		submitResultText.setHorizontalAlignment(SwingConstants.CENTER);
		submitResultText.setEditable(false);
		submitResultText.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitResultText.setColumns(10);
		submitPanel.add(submitResultText);
		
		submitStopBtn = new JButton("STOP");
		submitStopBtn.setEnabled(false);
		submitStopBtn.setActionCommand("SUBMITSTOP");
		submitStopBtn.setForeground(Color.RED);
		submitStopBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitStopBtn.setBounds(66, 300, 130, 35);
		submitPanel.add(submitStopBtn);
		
		submitOkBtn = new JButton("SUBMIT");
		submitOkBtn.setActionCommand("SUBMITOK");
		submitOkBtn.setEnabled(true);
		submitOkBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitOkBtn.setForeground(Color.BLUE);
		submitOkBtn.setBounds(199, 300, 130, 35);
		submitPanel.add(submitOkBtn);
		
		submitBackBtn = new JButton("<");
		submitBackBtn.setForeground(Color.BLACK);
		submitBackBtn.setEnabled(true);
		submitBackBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitBackBtn.setActionCommand("SUBMITBACK");
		submitBackBtn.setBounds(13, 300, 49, 35);
		submitPanel.add(submitBackBtn);
		
		GameListRenderer gameListRenderer = new GameListRenderer();
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		/*model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");
		model.addElement("LGD vs EDG 06:00 PM 80 TEST-TEXT");*/
		
		
		/*JList<String> gameList = new JList<String>(model);
		gameList.setFocusable(false);
		gameList.setCellRenderer(gameListRenderer);
		gameList.setBounds(15, 142, 312, 119);
		gameList.setFixedCellHeight(20);
		submitPanel.add(gameList);*/
		
		submitCurrency = new JTextField();
		submitCurrency.setHorizontalAlignment(SwingConstants.CENTER);
		submitCurrency.setText("Currency");
		submitCurrency.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitCurrency.setEditable(false);
		submitCurrency.setColumns(10);
		submitCurrency.setBorder(null);
		submitCurrency.setBackground(Color.WHITE);
		submitCurrency.setBounds(138, 86, 68, 21);
		submitPanel.add(submitCurrency);
		
		submitCurrencyBtnGroup = new ButtonGroup();
		submitCurrencyAll = new JToggleButton("All");
		submitCurrencyAll.setSelected(true);
		submitCurrencyAll.setBounds(15, 109, 101, 28);
		submitCurrencyAll.setActionCommand("All");
		submitPanel.add(submitCurrencyAll);
		submitCurrencyBtnGroup.add(submitCurrencyAll);
		
		JToggleButton submitCurrencyGdc = new JToggleButton("GDC Only");
		submitCurrencyGdc.setBounds(120, 109, 101, 28);
		submitCurrencyGdc.setActionCommand("GDC");
		submitPanel.add(submitCurrencyGdc);
		submitCurrencyBtnGroup.add(submitCurrencyGdc);
		
		JToggleButton submitCurrencyPoint = new JToggleButton("Point Only");
		submitCurrencyPoint.setBounds(226, 109, 101, 28);
		submitCurrencyPoint.setActionCommand("POINT");
		submitPanel.add(submitCurrencyPoint);
		submitCurrencyBtnGroup.add(submitCurrencyPoint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(15, 150, 312, 111);
		submitPanel.add(scrollPane);
		
		// submitRestartButton.addActionListener(actionListener);
		createContestBtn.addActionListener(actionListener);
		submitOkBtn.addActionListener(actionListener);
		submitStopBtn.addActionListener(actionListener);
		submitBackBtn.addActionListener(actionListener);
		createOkBtn.addActionListener(actionListener);
		createStopBtn.addActionListener(actionListener);
		createBackBtn.addActionListener(actionListener);
		currencyGdc.addActionListener(actionListener);
		currencyPoint.addActionListener(actionListener);
		submitContestBtn.addActionListener(actionListener);
		loginButton.addActionListener(actionListener);
	}
	
	class GameListRenderer extends JLabel implements ListCellRenderer<Object> {

		@Override
		public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			System.out.println("RENDERER = "+list);
			System.out.println("RENDERER = "+value);
			System.out.println("RENDERER = "+index);
			setBorder(new LineBorder(new Color(0, 0, 0)));
			setText(value.toString());
			setFont(new Font("±¼¸²", Font.BOLD, 13));
			return this;
		}
		
	}
	
	public class AppActionListener implements ActionListener {
		// AppCommon appCommon = new AppCommon();
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			String action = ev.getActionCommand();
			if ("LOGIN".equals(action)) {
				
				String email = emailText.getText();
				char[] password = passwordText.getPassword();
				String cnvtPassword = new String(password);
				
				if (email.isEmpty() || cnvtPassword.isEmpty()) {
					return;
				}
				
				Boolean loginResult = appLogin.loginProcess(email, cnvtPassword);
				if (loginResult) {
					System.out.println("==== LOGIN SUCCESS");
					// ArrayList<Object> gameInfos = appCommon.getTodaysMatch();
					// System.out.println("==== GAME INFO = "+gameInfos);
					emailText.setEnabled(false);
					passwordText.setEnabled(false);
					loginButton.setEnabled(false);
					submitContestBtn.setVisible(true);
					createContestBtn.setVisible(true);
				} else {
					System.out.println("Login Fail");
					// appCommon.driver.close();
				}
			} else if ("SUBMITCONTEST".equals(action)) {
				mainPanel.setVisible(false);
				submitPanel.setVisible(true);
			} else if ("SUBMITOK".equals(action)) {
				System.out.println("==== SUBMIT START");
				submitProcessAction();
			} else if ("SUBMITSTOP".equals(action)) {
				appSubmitContest.interrupt();
			} else if ("SUBMITBACK".equals(action)) {
				mainPanel.setVisible(true);
				submitPanel.setVisible(false);
				submitResultText.setText("");
				submitGameTypeLol.setSelected(true);
				submitCurrencyAll.setSelected(true);
			} else if ("CREATECONTEST".equals(action)) {
				mainPanel.setVisible(false);
				createPanel.setVisible(true);
				setCreateParam();
			} else if ("CREATEOK".equals(action)) {
				getCreateParam();
			} else if ("CREATESTOP".equals(action)) {
				appCreateContest.interrupt();
			} else if ("CREATEBACK".equals(action)) {
				mainPanel.setVisible(true);
				createPanel.setVisible(false);
				clearCreateParam();
			} else if ("point".equals(action)) {
				entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "20", "50"}));
			} else if ("gdc".equals(action)) {
				entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
			}
		}
		
		private void submitProcessAction() {
			ButtonModel sportsBtnModel = submitSportsBtnGroup.getSelection();
			ButtonModel currencyBtnModel = submitCurrencyBtnGroup.getSelection();
			
			String sportsBtn = sportsBtnModel.getActionCommand();
			String currencyBtn = currencyBtnModel.getActionCommand(); // all, gdc, point
			
			submitResultText.setText("");
			submitResultText.setForeground(Color.BLUE);
			submitStopBtn.setEnabled(true);
			submitBackBtn.setEnabled(false);
			submitOkBtn.setEnabled(false);
			
			appSubmitContest = new AppSubmitContest(sportsBtn, currencyBtn, submitResultText, submitBackBtn, submitStopBtn, submitOkBtn);
			appSubmitContest.setDaemon(true);
			appSubmitContest.start();
		}

		private void getCreateParam() {
			String errorMsg = null;
			Map<String, String> createParam = new HashMap<String, String>();
			createResultText.setText("");
			createResultText.setVisible(false);
			String sportsBtn = null;
			String currencyBtn = null;
			String entryFee = null;
			String entries = null;
			String typeBtn = null;
			
			int count = 0;
			ButtonModel sportsBtnModel = sportsBtnGroup.getSelection();
			ButtonModel typeBtnModel = typeBtnGroup.getSelection();
			if (sportsBtnModel != null) {
				sportsBtn = sportsBtnModel.getActionCommand();
				ButtonModel currencyBtnmodel = currencyBtnGroup.getSelection();
				if (currencyBtnmodel != null) {
					currencyBtn = currencyBtnmodel.getActionCommand();
					try {
						count = Integer.parseInt(contestCounts.getText());
						if (count > 60) {
							errorMsg = "Please Input Under "+AppCommon.CREATE_COUNT;
						} else if (count == 0) {
							errorMsg = "Please Input Count ( ~ "+AppCommon.CREATE_COUNT+" )";
						} else {
							entryFee = String.valueOf(entryFeeCombo.getSelectedItem());
							entries = String.valueOf(entriesCombo.getSelectedItem());
						}
					} catch (NumberFormatException e) {
						errorMsg = "Please Input Number";						
					}
					
				} else {
					errorMsg = "Please Select Currency";
				}
			} else {
				errorMsg = "Please Select Sports";
			}
			typeBtn = typeBtnModel.getActionCommand();
			
			createResultText.setVisible(true);
			if (errorMsg != null) {
				createResultText.setForeground(Color.RED);
				createResultText.setText(errorMsg);
			} else {
				createBackBtn.setEnabled(false);
				createStopBtn.setEnabled(true);
				createOkBtn.setEnabled(false);
				createParam.put("sports", sportsBtn);
				createParam.put("currency", currencyBtn);
				createParam.put("entryFee", entryFee);
				createParam.put("entries", entries);
				createParam.put("type", typeBtn);
				createParam.put("entryFeeIdx", Integer.toString(entryFeeCombo.getSelectedIndex()));
				createParam.put("entriesIdx", Integer.toString(entriesCombo.getSelectedIndex()));
				createParam.put("count", Integer.toString(count));
				System.out.println(createParam);
				appCreateContest = new AppCreateContest(createParam, createResultText, createBackBtn, createStopBtn, createOkBtn);
				appCreateContest.setDaemon(true);
				appCreateContest.start();
			}
		}
		
		private void setCreateParam() {
			createLimitText.setText("Limit "+AppCommon.CREATE_COUNT+" Contests");
			createResultText.setText("");
			createResultText.setVisible(false);
			gameTypeLol.setSelected(true);
			currencyGdc.setSelected(true);
			entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
			contestCounts.setText("0");
		}
		
		private void clearCreateParam() {
			sportsBtnGroup.clearSelection();
			currencyBtnGroup.clearSelection();
		}
	}
}