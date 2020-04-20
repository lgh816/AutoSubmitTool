package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.ListSelectionModel;

public class AppUi {

	private AppLogin appLogin;
	private AppSubmitContest appSubmitContest;
	private AppCreateContest appCreateContest;
	public AppActionListener actionListener;
	
	private JFrame frmRankingBallAuto;
	private JPanel mainPanel;
	private JPanel sportsPanel;
	private JPanel createPanel;
	private JPanel submitPanel;
	
	private JTextField emailLable;
	private JTextField passwordLabel;
	private JTextField emailText;
	private JButton loginBtn;
	private JPasswordField passwordText;
	public JButton submitAllOkBtn;
	public JButton submitOkBtn;
	private JButton submitContestBtn;
	private JButton createContestBtn;
	private JButton createStopBtn;
	private JButton createBackBtn;
	private JButton createAllOkBtn;
	private JButton createOkBtn;
	public JButton submitStopBtn;
	public JButton submitBackBtn;
	public JTextField submitResultText;
	
	private ButtonGroup sportsBtnGroup;
	private ButtonGroup submitCurrencyBtnGroup;
	private ButtonGroup currencyBtnGroup;
	private ButtonGroup submitTypeBtnGroup;
	private ButtonGroup createTypeBtnGroup;
	
	private JTextField createCurrencyTxt;
	private JTextField txtEntryfee;
	private JTextField txtEntries;
	private JToggleButton submitCurrencyAll;
	private JToggleButton currencyGdc;
	private JComboBox entriesCombo;
	private JComboBox entryFeeCombo;
	private JTextField contestCounts;
	private JTextField txtCount;
	private JTextField createResultText;
	
	private JToggleButton sportsTypeLol;
	private JToggleButton sportsTypeBaseball;
	private JToggleButton sportsTypeSoc;
	private JToggleButton sportsTypeBsk;
	private JToggleButton sportsTypeFob;
	
	private JToggleButton currencyPoint;
	private JTextField submitSports;
	private JTextField submitCurrencyTxt;
	private JTextField submitGameType;
	private JTextField createLimitText;
	private JTextField createGameType;
	public JList submitTodayGameList;
	public JList createTodayGameList;;
	
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
		initMainPanel();
		initSportsPanel();
		initCreatePanel();
		initSubmitPanel();
		initActionListener();
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
		mainTopImg.setBounds(-12, 0, 354, 247);
		frmRankingBallAuto.getContentPane().add(mainTopImg);
	}
	
	private void initMainPanel() {
		// Main Panel ===================================================================================
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 257, 342, 351);
		frmRankingBallAuto.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		Image bottomImg = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("RankingBallBottom.PNG"));
		
		emailLable = new JTextField();
		emailLable.setBorder(null);
		emailLable.setBounds(7, 47, 53, 21);
		mainPanel.add(emailLable);
		emailLable.setHorizontalAlignment(SwingConstants.CENTER);
		emailLable.setEditable(false);
		emailLable.setBackground(Color.WHITE);
		emailLable.setFont(new Font("±¼¸²", Font.BOLD, 12));
		emailLable.setText("Email");
		emailLable.setColumns(10);
		
		passwordLabel = new JTextField();
		passwordLabel.setBounds(12, 74, 70, 21);
		mainPanel.add(passwordLabel);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBorder(null);
		passwordLabel.setEditable(false);
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setFont(new Font("±¼¸²", Font.BOLD, 12));
		passwordLabel.setText("Password");
		passwordLabel.setColumns(10);
		
		loginBtn = new JButton("LOGIN");
		loginBtn.setBounds(260, 45, 70, 52);
		mainPanel.add(loginBtn);
		loginBtn.setFocusable(false);
		loginBtn.setActionCommand("LOGIN");
		loginBtn.setFont(new Font("±¼¸²", Font.BOLD, 10));
		
		emailText = new JTextField();
		emailText.setBounds(89, 47, 160, 21);
		mainPanel.add(emailText);
		emailText.setColumns(10);
		if (AppCommon.USER_ID != null) {
			emailText.setText(AppCommon.USER_ID);
		}
		
		passwordText = new JPasswordField();
		passwordText.setBounds(89, 74, 160, 21);
		mainPanel.add(passwordText);
		
		JLabel mainBottomImg = new JLabel("");
		mainBottomImg.setBounds(0, 161, 342, 190);
		mainPanel.add(mainBottomImg);
		mainBottomImg.setIcon(new ImageIcon(bottomImg));
		
		passwordText.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginBtn.doClick();
				}
			}
		});
		
		// Main Panel End=================================================================================
	}
	
	private void initSportsPanel() {
		sportsPanel = new JPanel();
		sportsPanel.setVisible(false);
		sportsPanel.setBackground(Color.WHITE);
		sportsPanel.setBounds(0, 257, 342, 351);
		frmRankingBallAuto.getContentPane().add(sportsPanel);
		sportsPanel.setLayout(null);
		
		sportsBtnGroup = new ButtonGroup();

		submitSports = new JTextField();
		submitSports.setBounds(146, 0, 49, 21);
		submitSports.setText("Sports");
		submitSports.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitSports.setEditable(false);
		submitSports.setColumns(10);
		submitSports.setBorder(null);
		submitSports.setBackground(Color.WHITE);
		sportsPanel.add(submitSports);
		
		sportsTypeLol = new JToggleButton("League Of Legends");
		sportsTypeLol.setSelected(true);
		sportsTypeLol.setName("lol");
		sportsTypeLol.setActionCommand("lol");
		sportsTypeLol.setBounds(15, 22, 155, 28);
		sportsPanel.add(sportsTypeLol);
		sportsBtnGroup.add(sportsTypeLol);
		
		sportsTypeBaseball = new JToggleButton("Baseball");
		sportsTypeBaseball.setName("baseball");
		sportsTypeBaseball.setActionCommand("baseball");
		sportsTypeBaseball.setBounds(172, 22, 155, 28);
		sportsPanel.add(sportsTypeBaseball);
		sportsBtnGroup.add(sportsTypeBaseball);
		
		sportsTypeSoc = new JToggleButton("Soccer");
		sportsTypeSoc.setName("soccer");
		sportsTypeSoc.setEnabled(false);
		sportsTypeSoc.setActionCommand("soccer");
		sportsTypeSoc.setBounds(15, 53, 101, 28);
		sportsPanel.add(sportsTypeSoc);
		sportsBtnGroup.add(sportsTypeSoc);
		
		sportsTypeBsk = new JToggleButton("Basketball");
		sportsTypeBsk.setName("basketball");
		sportsTypeBsk.setActionCommand("basketball");
		sportsTypeBsk.setBounds(226, 53, 101, 28);
		sportsPanel.add(sportsTypeBsk);
		sportsBtnGroup.add(sportsTypeBsk);
		
		sportsTypeFob = new JToggleButton("Football");
		sportsTypeFob.setName("football");
		sportsTypeFob.setActionCommand("football");
		sportsTypeFob.setBounds(120, 53, 102, 28);
		sportsPanel.add(sportsTypeFob);
		sportsBtnGroup.add(sportsTypeFob);
		
		submitContestBtn = new JButton("SUBMIT");
		submitContestBtn.setBounds(12, 92, 155, 44);
		sportsPanel.add(submitContestBtn);
		submitContestBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitContestBtn.setActionCommand("SUBMITCONTEST");
		
		createContestBtn = new JButton("CREATE");
		createContestBtn.setBounds(170, 92, 160, 44);
		sportsPanel.add(createContestBtn);
		createContestBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createContestBtn.setActionCommand("CREATECONTEST");
		
		Image bottomImg = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("RankingBallBottom.PNG"));
		
		JLabel mainBottomImg = new JLabel("");
		mainBottomImg.setBounds(0, 161, 342, 190);
		sportsPanel.add(mainBottomImg);
		mainBottomImg.setIcon(new ImageIcon(bottomImg));
	}
	 
	private void initCreatePanel() {
		// Create Panel ===================================================================================
		createPanel = new JPanel();
		createPanel.setVisible(false);
		createPanel.setBackground(Color.WHITE);
		createPanel.setBounds(0, 257, 342, 351);
		frmRankingBallAuto.getContentPane().add(createPanel);
		createPanel.setLayout(null);
		
		currencyBtnGroup = new ButtonGroup();
		createTypeBtnGroup = new ButtonGroup();
		
		createCurrencyTxt = new JTextField();
		createCurrencyTxt.setHorizontalAlignment(SwingConstants.CENTER);
		createCurrencyTxt.setBackground(Color.WHITE);
		createCurrencyTxt.setEditable(false);
		createCurrencyTxt.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createCurrencyTxt.setColumns(10);
		createCurrencyTxt.setBorder(null);
		createCurrencyTxt.setBounds(0, 2, 342, 21);
		createPanel.add(createCurrencyTxt);
		
		currencyGdc = new JToggleButton("GDC");
		currencyGdc.setSelected(true);
		currencyGdc.setActionCommand("gdc");
		currencyGdc.setBounds(15, 23, 156, 28);
		createPanel.add(currencyGdc);
		currencyBtnGroup.add(currencyGdc);
		
		currencyPoint = new JToggleButton("Point");
		currencyPoint.setActionCommand("point");
		currencyPoint.setBounds(171, 23, 156, 28);
		createPanel.add(currencyPoint);
		currencyBtnGroup.add(currencyPoint);
		
		entryFeeCombo = new JComboBox();
		entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
		entryFeeCombo.setMaximumRowCount(4);
		entryFeeCombo.setBounds(15, 77, 156, 28);
		createPanel.add(entryFeeCombo);
		
		txtEntryfee = new JTextField();
		txtEntryfee.setBackground(Color.WHITE);
		txtEntryfee.setEditable(false);
		txtEntryfee.setText("Entry Fee");
		txtEntryfee.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtEntryfee.setColumns(10);
		txtEntryfee.setBorder(null);
		txtEntryfee.setBounds(58, 55, 69, 21);
		createPanel.add(txtEntryfee);
		
		txtEntries = new JTextField();
		txtEntries.setBackground(Color.WHITE);
		txtEntries.setEditable(false);
		txtEntries.setText("Entries");
		txtEntries.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtEntries.setColumns(10);
		txtEntries.setBorder(null);
		txtEntries.setBounds(217, 55, 49, 21);
		createPanel.add(txtEntries);
		
		entriesCombo = new JComboBox();
		entriesCombo.setMaximumRowCount(20);
		entriesCombo.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		entriesCombo.setBounds(171, 77, 156, 28);
		createPanel.add(entriesCombo);
		
		createBackBtn = new JButton("<");
		createBackBtn.setForeground(Color.BLACK);
		createBackBtn.setMargin(new Insets(2, 0, 2, 0));
		createBackBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createBackBtn.setActionCommand("CREATEBACK");
		createBackBtn.setBounds(13, 300, 40, 35);
		createPanel.add(createBackBtn);
		
		createStopBtn = new JButton("STOP");
		createStopBtn.setActionCommand("CREATESTOP");
		createStopBtn.setForeground(Color.RED);
		createStopBtn.setMargin(new Insets(2, 0, 2, 0));
		createStopBtn.setEnabled(false);
		createStopBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createStopBtn.setBounds(54, 300, 86, 35);
		createPanel.add(createStopBtn);
		
		createOkBtn = new JButton("CREATE");
		createOkBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createOkBtn.setMargin(new Insets(2, 0, 2, 0));
		createOkBtn.setEnabled(false);
		createOkBtn.setActionCommand("CREATEOK");
		createOkBtn.setForeground(Color.BLUE);
		createOkBtn.setBounds(141, 300, 86, 35);
		createPanel.add(createOkBtn);
		
		createAllOkBtn = new JButton("CREATE All");
		createAllOkBtn.setActionCommand("CREATEALLOK");
		createAllOkBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createAllOkBtn.setMargin(new Insets(2, 0, 2, 0));
		createAllOkBtn.setForeground(Color.BLUE);
		createAllOkBtn.setBounds(228, 300, 99, 35);
		createPanel.add(createAllOkBtn);
		
		contestCounts = new JTextField();
		contestCounts.setHorizontalAlignment(SwingConstants.RIGHT);
		contestCounts.setText("0");
		contestCounts.setToolTipText("");
		contestCounts.setBounds(286, 162, 41, 21);
		createPanel.add(contestCounts);
		contestCounts.setColumns(10);
		
		txtCount = new JTextField();
		txtCount.setBackground(Color.WHITE);
		txtCount.setEditable(false);
		txtCount.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtCount.setBorder(null);
		txtCount.setText("Count");
		txtCount.setBounds(241, 162, 41, 21);
		createPanel.add(txtCount);
		txtCount.setColumns(10);
		
		createResultText = new JTextField();
		createResultText.setBorder(null);
		createResultText.setBackground(Color.WHITE);
		createResultText.setEditable(false);
		createResultText.setForeground(Color.RED);
		createResultText.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createResultText.setHorizontalAlignment(SwingConstants.CENTER);
		createResultText.setBounds(0, 270, 342, 21);
		createPanel.add(createResultText);
		createResultText.setColumns(10);
		
		createLimitText = new JTextField();
		createLimitText.setBorder(null);
		createLimitText.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createLimitText.setForeground(Color.RED);
		createLimitText.setBounds(15, 162, 155, 21);
		createPanel.add(createLimitText);
		createLimitText.setColumns(10);
		
		createGameType = new JTextField();
		createGameType.setHorizontalAlignment(SwingConstants.CENTER);
		createGameType.setText("Type");
		createGameType.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createGameType.setEditable(false);
		createGameType.setColumns(10);
		createGameType.setBorder(null);
		createGameType.setBackground(Color.WHITE);
		createGameType.setBounds(135, 108, 69, 21);
		createPanel.add(createGameType);
		
		JToggleButton typeAllBtn = new JToggleButton("All");
		typeAllBtn.setActionCommand("All");
		typeAllBtn.setSelected(true);
		typeAllBtn.setBounds(15, 129, 77, 28);
		createPanel.add(typeAllBtn);
		createTypeBtnGroup.add(typeAllBtn);
		
		JToggleButton typeTnmtBtn = new JToggleButton("TNMT");
		typeTnmtBtn.setActionCommand("Tournaments");
		typeTnmtBtn.setBounds(94, 129, 77, 28);
		createPanel.add(typeTnmtBtn);
		createTypeBtnGroup.add(typeTnmtBtn);
		
		JToggleButton typeFiftyBtn = new JToggleButton("50/50");
		typeFiftyBtn.setActionCommand("50/50");
		typeFiftyBtn.setBounds(172, 129, 77, 28);
		createPanel.add(typeFiftyBtn);
		createTypeBtnGroup.add(typeFiftyBtn);
		
		JToggleButton typeThirtyBtn = new JToggleButton("30/30");
		typeThirtyBtn.setActionCommand("30/30");
		typeThirtyBtn.setBounds(251, 129, 77, 28);
		createPanel.add(typeThirtyBtn);
		createTypeBtnGroup.add(typeThirtyBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 186, 313, 74);
		createPanel.add(scrollPane);
		
		createTodayGameList = new JList(new DefaultListModel());
		createTodayGameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		createTodayGameList.setFont(new Font("±¼¸²", Font.BOLD, 13));
		createTodayGameList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (createTodayGameList.getSelectedIndex() != -1) {
					if (!createOkBtn.isEnabled()) {
						createOkBtn.setEnabled(true);
					}
					System.out.println("Selecte Game Index = "+createTodayGameList.getSelectedIndex());
				}
			}
		});
		scrollPane.setViewportView(createTodayGameList);
		// Create Panel End=================================================================================
	}
	
	private void initSubmitPanel() {
		// Submit Panel ===================================================================================
		submitCurrencyBtnGroup = new ButtonGroup();
		submitTypeBtnGroup = new ButtonGroup();
		
		submitPanel = new JPanel();
		submitPanel.setVisible(false);
		submitPanel.setBounds(0, 257, 342, 351);
		submitPanel.setBackground(Color.WHITE);
		frmRankingBallAuto.getContentPane().add(submitPanel);
		submitPanel.setLayout(null);
		
		submitResultText = new JTextField();
		submitResultText.setBorder(null);
		submitResultText.setBounds(15, 270, 312, 21);
		submitResultText.setBackground(Color.WHITE);
		submitResultText.setForeground(Color.BLUE);
		submitResultText.setHorizontalAlignment(SwingConstants.CENTER);
		submitResultText.setEditable(false);
		submitResultText.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitResultText.setColumns(10);
		submitPanel.add(submitResultText);
		
		submitBackBtn = new JButton("<");
		submitBackBtn.setMargin(new Insets(2, 0, 2, 0));
		submitBackBtn.setForeground(Color.BLACK);
		submitBackBtn.setEnabled(true);
		submitBackBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitBackBtn.setActionCommand("SUBMITBACK");
		submitBackBtn.setBounds(13, 300, 40, 35);
		submitPanel.add(submitBackBtn);
		
		submitStopBtn = new JButton("STOP");
		submitStopBtn.setMargin(new Insets(2, 0, 2, 0));
		submitStopBtn.setEnabled(false);
		submitStopBtn.setActionCommand("SUBMITSTOP");
		submitStopBtn.setForeground(Color.RED);
		submitStopBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitStopBtn.setBounds(54, 300, 86, 35);
		submitPanel.add(submitStopBtn);
		
		submitOkBtn = new JButton("SUBMIT");
		submitOkBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitOkBtn.setMargin(new Insets(2, 0, 2, 0));
		submitOkBtn.setEnabled(false);
		submitOkBtn.setActionCommand("SUBMITOK");
		submitOkBtn.setForeground(Color.BLUE);
		submitOkBtn.setBounds(141, 300, 86, 35);
		submitPanel.add(submitOkBtn);
		
		submitAllOkBtn = new JButton("SUBMIT All");
		submitAllOkBtn.setMargin(new Insets(2, 0, 2, 0));
		submitAllOkBtn.setActionCommand("SUBMITALLOK");
		submitAllOkBtn.setEnabled(true);
		submitAllOkBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitAllOkBtn.setForeground(Color.BLUE);
		submitAllOkBtn.setBounds(228, 300, 99, 35);
		submitPanel.add(submitAllOkBtn);
		
		submitCurrencyTxt = new JTextField();
		submitCurrencyTxt.setHorizontalAlignment(SwingConstants.CENTER);
		submitCurrencyTxt.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitCurrencyTxt.setEditable(false);
		submitCurrencyTxt.setColumns(10);
		submitCurrencyTxt.setBorder(null);
		submitCurrencyTxt.setBackground(Color.WHITE);
		submitCurrencyTxt.setBounds(0, 2, 342, 21);
		submitPanel.add(submitCurrencyTxt);
		
		submitCurrencyAll = new JToggleButton("All");
		submitCurrencyAll.setSelected(true);
		submitCurrencyAll.setBounds(15, 23, 101, 28);
		submitCurrencyAll.setActionCommand("All");
		submitPanel.add(submitCurrencyAll);
		submitCurrencyBtnGroup.add(submitCurrencyAll);
		
		JToggleButton submitCurrencyGdc = new JToggleButton("GDC Only");
		submitCurrencyGdc.setBounds(120, 23, 101, 28);
		submitCurrencyGdc.setActionCommand("GDC");
		submitPanel.add(submitCurrencyGdc);
		submitCurrencyBtnGroup.add(submitCurrencyGdc);
		
		JToggleButton submitCurrencyPoint = new JToggleButton("Point Only");
		submitCurrencyPoint.setBounds(226, 23, 101, 28);
		submitCurrencyPoint.setActionCommand("POINT");
		submitPanel.add(submitCurrencyPoint);
		submitCurrencyBtnGroup.add(submitCurrencyPoint);
		
		submitGameType = new JTextField();
		submitGameType.setHorizontalAlignment(SwingConstants.CENTER);
		submitGameType.setText("Type");
		submitGameType.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitGameType.setEditable(false);
		submitGameType.setColumns(10);
		submitGameType.setBorder(null);
		submitGameType.setBackground(Color.WHITE);
		submitGameType.setBounds(135, 55, 69, 21);
		submitPanel.add(submitGameType);
		
		JToggleButton submitTypeAllBtn = new JToggleButton("All");
		submitTypeAllBtn.setActionCommand("All");
		submitTypeAllBtn.setSelected(true);
		submitTypeAllBtn.setBounds(15, 77, 77, 28);
		submitPanel.add(submitTypeAllBtn);
		submitTypeBtnGroup.add(submitTypeAllBtn);
		
		JToggleButton submitTypeTnmtBtn = new JToggleButton("TNMT");
		submitTypeTnmtBtn.setActionCommand("tnmt");
		submitTypeTnmtBtn.setBounds(94, 77, 77, 28);
		submitPanel.add(submitTypeTnmtBtn);
		submitTypeBtnGroup.add(submitTypeTnmtBtn);
		
		JToggleButton submitTypeFiftyBtn = new JToggleButton("50/50");
		submitTypeFiftyBtn.setActionCommand("ct50");
		submitTypeFiftyBtn.setBounds(172, 77, 77, 28);
		submitPanel.add(submitTypeFiftyBtn);
		submitTypeBtnGroup.add(submitTypeFiftyBtn);
		
		JToggleButton submitTypeThirtyBtn = new JToggleButton("30/30");
		submitTypeThirtyBtn.setActionCommand("ct30");
		submitTypeThirtyBtn.setBounds(251, 77, 77, 28);
		submitPanel.add(submitTypeThirtyBtn);
		submitTypeBtnGroup.add(submitTypeThirtyBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 114, 313, 146);
		submitPanel.add(scrollPane);
		
		submitTodayGameList = new JList(new DefaultListModel());
		submitTodayGameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		submitTodayGameList.setFont(new Font("±¼¸²", Font.BOLD, 13));
		submitTodayGameList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (submitTodayGameList.getSelectedIndex() != -1) {
					if (!submitOkBtn.isEnabled()) {
						submitOkBtn.setEnabled(true);
					}
					System.out.println("Selecte Game Index = "+submitTodayGameList.getSelectedIndex());
				}
			}
		});
		
		scrollPane.setViewportView(submitTodayGameList);
	}
	
	private void initActionListener() {
		createContestBtn.addActionListener(actionListener);
		submitOkBtn.addActionListener(actionListener);
		submitAllOkBtn.addActionListener(actionListener);
		submitStopBtn.addActionListener(actionListener);
		submitBackBtn.addActionListener(actionListener);
		createOkBtn.addActionListener(actionListener);
		createAllOkBtn.addActionListener(actionListener);
		createStopBtn.addActionListener(actionListener);
		createBackBtn.addActionListener(actionListener);
		currencyGdc.addActionListener(actionListener);
		currencyPoint.addActionListener(actionListener);
		submitContestBtn.addActionListener(actionListener);
		loginBtn.addActionListener(actionListener);
	}
	
	public class AppActionListener implements ActionListener {
		List<String> gameIdArr = new ArrayList<String>();
		
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
					mainPanel.setVisible(false);
					sportsPanel.setVisible(true);
				} else {
					System.out.println("Login Fail");
					// appCommon.driver.close();
				}
			} else if ("SUBMITCONTEST".equals(action)) {
				submitTodayGameList.setModel(new DefaultListModel());
				ButtonModel sportsBtnModel = sportsBtnGroup.getSelection();
				String sportsBtn = sportsBtnModel.getActionCommand();
				gameIdArr = new ArrayList<String>();
				sportsPanel.setVisible(false);
				submitPanel.setVisible(true);
				gameIdArr = AppCommon.getTodaysMatch(submitTodayGameList, sportsBtn, submitCurrencyTxt);
			} else if ("SUBMITOK".equals(action)) {
				int gameIdx = submitTodayGameList.getSelectedIndex();
				String gameId = gameIdArr.get(gameIdx);
				submitProcessAction(gameId);
			} else if ("SUBMITALLOK".equals(action)) {
				System.out.println("==== SUBMIT START");
				submitProcessAction(null);
			} else if ("SUBMITSTOP".equals(action)) {
				appSubmitContest.interrupt();
			} else if ("SUBMITBACK".equals(action)) {
				submitTodayGameList.setModel(new DefaultListModel());
				submitPanel.setVisible(false);
				submitResultText.setText("");
				submitCurrencyAll.setSelected(true);
				submitOkBtn.setEnabled(false);
				sportsPanel.setVisible(true);
			} else if ("CREATECONTEST".equals(action)) {
				createTodayGameList.setModel(new DefaultListModel());
				ButtonModel sportsBtnModel = sportsBtnGroup.getSelection();
				String sportsBtn = sportsBtnModel.getActionCommand();
				gameIdArr = new ArrayList<String>();
				sportsPanel.setVisible(false);
				createPanel.setVisible(true);
				setCreateParam();
				gameIdArr = AppCommon.getTodaysMatch(createTodayGameList, sportsBtn, createCurrencyTxt);
			} else if ("CREATEOK".equals(action)) {
				int gameIdx = createTodayGameList.getSelectedIndex();
				String gameId = gameIdArr.get(gameIdx);
				getCreateParam(gameId);
			} else if ("CREATEALLOK".equals(action)) {
				getCreateParam(null);
			} else if ("CREATESTOP".equals(action)) {
				appCreateContest.interrupt();
			} else if ("CREATEBACK".equals(action)) {
				createTodayGameList.setModel(new DefaultListModel());
				createPanel.setVisible(false);
				sportsPanel.setVisible(true);
				currencyBtnGroup.clearSelection();
			} else if ("point".equals(action)) {
				entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "20", "50"}));
			} else if ("gdc".equals(action)) {
				entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
			}
		}
		
		private void submitProcessAction(String gameId) {
			ButtonModel sportsBtnModel = sportsBtnGroup.getSelection();
			ButtonModel currencyBtnModel = submitCurrencyBtnGroup.getSelection();
			ButtonModel gameTypeBtnModel = submitTypeBtnGroup.getSelection();
			
			String sportsBtn = sportsBtnModel.getActionCommand();
			String currencyBtn = currencyBtnModel.getActionCommand(); // all, gdc, point
			String gameTypeBtn = gameTypeBtnModel.getActionCommand(); // All, Tournaments, 50/50, 30/30
			
			submitResultText.setText("");
			submitResultText.setForeground(Color.BLUE);
			submitStopBtn.setEnabled(true);
			submitBackBtn.setEnabled(false);
			submitOkBtn.setEnabled(false);
			submitAllOkBtn.setEnabled(false);
			submitTodayGameList.setEnabled(false);
			
			appSubmitContest = new AppSubmitContest(sportsBtn, currencyBtn, gameTypeBtn, submitResultText, submitBackBtn, submitStopBtn, submitAllOkBtn, submitOkBtn, gameId, submitTodayGameList);
			appSubmitContest.setDaemon(true);
			appSubmitContest.start();
		}

		private void getCreateParam(String gameId) {
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
			ButtonModel typeBtnModel = createTypeBtnGroup.getSelection();
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
				createResultText.setForeground(Color.BLUE);
				createBackBtn.setEnabled(false);
				createStopBtn.setEnabled(true);
				createOkBtn.setEnabled(false);
				createAllOkBtn.setEnabled(false);
				createTodayGameList.setEnabled(false);
				createParam.put("sports", sportsBtn);
				createParam.put("currency", currencyBtn);
				createParam.put("entryFee", entryFee);
				createParam.put("entries", entries);
				createParam.put("type", typeBtn);
				createParam.put("entryFeeIdx", Integer.toString(entryFeeCombo.getSelectedIndex()));
				createParam.put("entriesIdx", Integer.toString(entriesCombo.getSelectedIndex()));
				createParam.put("count", Integer.toString(count));
				System.out.println(createParam);
				
				appCreateContest = new AppCreateContest(createParam, createResultText, createBackBtn, createStopBtn, createAllOkBtn, createOkBtn, gameId, createTodayGameList);
				appCreateContest.setDaemon(true);
				appCreateContest.start();
			}
		}
		
		private void setCreateParam() {
			createLimitText.setText("Limit Contests : "+AppCommon.CREATE_COUNT);
			createResultText.setText("");
			createResultText.setVisible(false);
			//createSportsTypeLol.setSelected(true);
			currencyGdc.setSelected(true);
			entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
			contestCounts.setText("0");
		}
	}
}