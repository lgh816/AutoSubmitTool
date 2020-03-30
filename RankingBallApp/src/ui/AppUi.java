package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import service.AutoSubmit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Point;

public class AppUi {

	private JFrame frmRankingBallAuto;
	private JTextField emailLable;
	private JTextField passwordLabel;
	public AppActionListener actionListener;
	private JTextField emailText;
	private JButton loginButton;
	private AutoSubmit autoSubmit;
	private JPasswordField passwordText;
	private JButton submitStartButton;
	private JButton createContestButton;
	private JButton submitRestartButton;
	public JTextField resultText;
	private JPanel createPanel;
	private JTextField selectGameType;
	private ButtonGroup sportsBtnGroup;
	private ButtonGroup currencyBtnGroup;
	private JTextField txtCurrency;
	private JTextField txtEntryfee;
	private JTextField txtEntries;
	private JPanel mainPanel;
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
	private JTextField createErrorMsg;
	// public JTextField gameInfo;
	
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
		autoSubmit = new AutoSubmit();
		actionListener = new AppActionListener();
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
		frmRankingBallAuto.setTitle("RANKINGBALL Auto Submit Tool");
		frmRankingBallAuto.setBounds(100, 100, 346, 574);
		frmRankingBallAuto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRankingBallAuto.getContentPane().setLayout(null);
		
		// Main Panel =============================================================================
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 257, 342, 288);
		frmRankingBallAuto.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		emailLable = new JTextField();
		emailLable.setBounds(7, 12, 53, 21);
		mainPanel.add(emailLable);
		emailLable.setHorizontalAlignment(SwingConstants.CENTER);
		emailLable.setBorder(null);
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
		
		passwordText = new JPasswordField();
		passwordText.setBounds(89, 39, 160, 21);
		mainPanel.add(passwordText);
		
		submitStartButton = new JButton("SUBMIT START");
		submitStartButton.setBounds(12, 92, 155, 44);
		mainPanel.add(submitStartButton);
		submitStartButton.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitStartButton.setVisible(false);
		submitStartButton.setActionCommand("START");
		
		createContestButton = new JButton("CREATE CONTEST");
		createContestButton.setBounds(170, 92, 160, 44);
		mainPanel.add(createContestButton);
		createContestButton.setVisible(false);
		createContestButton.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createContestButton.setActionCommand("CREATECONTEST");
		
		submitRestartButton = new JButton("SUBMIT RESTART");
		submitRestartButton.setBounds(12, 92, 318, 44);
		mainPanel.add(submitRestartButton);
		submitRestartButton.setFont(new Font("±¼¸²", Font.BOLD, 12));
		submitRestartButton.setVisible(false);
		submitRestartButton.setActionCommand("RESTART");
		
		resultText = new JTextField();
		resultText.setBounds(63, 67, 210, 21);
		mainPanel.add(resultText);
		resultText.setBackground(Color.WHITE);
		resultText.setVisible(false);
		resultText.setForeground(Color.RED);
		resultText.setHorizontalAlignment(SwingConstants.CENTER);
		resultText.setEditable(false);
		resultText.setBorder(null);
		resultText.setFont(new Font("±¼¸²", Font.BOLD, 12));
		resultText.setColumns(10);
		
		JLabel label = new JLabel("");
		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("RankingBllImg.PNG"));
		label.setIcon(new ImageIcon(img));
		label.setBounds(-14, 0, 354, 247);
		frmRankingBallAuto.getContentPane().add(label);
		
		// Main Panel End=================================================================================
		
		createPanel = new JPanel();
		createPanel.setVisible(false);
		createPanel.setBackground(Color.WHITE);
		createPanel.setBounds(0, 257, 342, 288);
		frmRankingBallAuto.getContentPane().add(createPanel);
		createPanel.setLayout(null);
		
		sportsBtnGroup = new ButtonGroup();
		currencyBtnGroup = new ButtonGroup();
		
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
		entryFeeCombo.setBounds(16, 155, 156, 28);
		createPanel.add(entryFeeCombo);
		
		txtEntryfee = new JTextField();
		txtEntryfee.setBackground(Color.WHITE);
		txtEntryfee.setEditable(false);
		txtEntryfee.setText("Entry Fee");
		txtEntryfee.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtEntryfee.setColumns(10);
		txtEntryfee.setBorder(null);
		txtEntryfee.setBounds(59, 133, 69, 21);
		createPanel.add(txtEntryfee);
		
		txtEntries = new JTextField();
		txtEntries.setBackground(Color.WHITE);
		txtEntries.setEditable(false);
		txtEntries.setText("Entries");
		txtEntries.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtEntries.setColumns(10);
		txtEntries.setBorder(null);
		txtEntries.setBounds(220, 133, 49, 21);
		createPanel.add(txtEntries);
		
		entriesCombo = new JComboBox();
		entriesCombo.setMaximumRowCount(20);
		entriesCombo.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		entriesCombo.setBounds(172, 155, 156, 28);
		createPanel.add(entriesCombo);
		
		JButton createCancelBtn = new JButton("CANCEL");
		createCancelBtn.setActionCommand("CANCEL");
		createCancelBtn.setForeground(Color.RED);
		createCancelBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createCancelBtn.setBounds(15, 235, 156, 35);
		createPanel.add(createCancelBtn);
		
		JButton createOkBtn = new JButton("CREATE");
		createOkBtn.setActionCommand("CREATEOK");
		createOkBtn.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createOkBtn.setForeground(Color.BLUE);
		createOkBtn.setBounds(171, 235, 156, 35);
		createPanel.add(createOkBtn);
		
		contestCounts = new JTextField();
		contestCounts.setHorizontalAlignment(SwingConstants.RIGHT);
		contestCounts.setText("0");
		contestCounts.setToolTipText("100 \uAE4C\uC9C0\uB9CC");
		contestCounts.setBounds(286, 189, 41, 21);
		createPanel.add(contestCounts);
		contestCounts.setColumns(10);
		
		txtCount = new JTextField();
		txtCount.setBackground(Color.WHITE);
		txtCount.setEditable(false);
		txtCount.setFont(new Font("±¼¸²", Font.BOLD, 12));
		txtCount.setBorder(null);
		txtCount.setText("Count");
		txtCount.setBounds(242, 189, 41, 21);
		createPanel.add(txtCount);
		txtCount.setColumns(10);
		
		createErrorMsg = new JTextField();
		createErrorMsg.setVisible(false);
		createErrorMsg.setBackground(Color.WHITE);
		createErrorMsg.setEditable(false);
		createErrorMsg.setForeground(Color.RED);
		createErrorMsg.setFont(new Font("±¼¸²", Font.BOLD, 12));
		createErrorMsg.setBorder(null);
		createErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		createErrorMsg.setBounds(15, 209, 311, 21);
		createPanel.add(createErrorMsg);
		createErrorMsg.setColumns(10);
		
		submitRestartButton.addActionListener(actionListener);
		createContestButton.addActionListener(actionListener);
		createOkBtn.addActionListener(actionListener);
		createCancelBtn.addActionListener(actionListener);
		currencyGdc.addActionListener(actionListener);
		currencyPoint.addActionListener(actionListener);
		submitStartButton.addActionListener(actionListener);
		loginButton.addActionListener(actionListener);
	}
	
	public class AppActionListener implements ActionListener {
		
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
				
				Boolean loginResult = autoSubmit.loginProcess(email, cnvtPassword);
				if (loginResult) {
					System.out.println("==== LOGIN SUCCESS");
					// hideElement();
					ArrayList<Object> gameInfos = autoSubmit.getTodaysMatch();
					System.out.println("==== GAME INFO = "+gameInfos);
					// loginAfterPanel.setVisible(true);
					emailText.setEnabled(false);
					passwordText.setEnabled(false);
					loginButton.setEnabled(false);
					submitStartButton.setVisible(true);
					createContestButton.setVisible(true);
				} else {
					System.out.println("Login Fail");
					autoSubmit.driver.close();
				}
			} else if ("START".equals(action)) {
//				resultText.setVisible(true);
//				resultText.setForeground(Color.BLUE);
//				resultText.setText("Processing.....");
				System.out.println("==== SUBMIT START");
				submitProcessAction();
			} else if ("RESTART".equals(action)) {
				resultText.setVisible(false);
				resultText.setText("");
				System.out.println("==== SUBMIT RESTART");
				submitProcessAction();
			} else if ("CREATECONTEST".equals(action)) {
				mainPanel.setVisible(false);
				createPanel.setVisible(true);
				setDefaultParam();
			} else if ("point".equals(action)) {
				entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "20", "50"}));
			} else if ("gdc".equals(action)) {
				entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
			} else if ("CREATEOK".equals(action)) {
				getCreateParam();
			} else if ("CANCEL".equals(action)) {
				mainPanel.setVisible(true);
				createPanel.setVisible(false);
				setClearSelection();
			}
		}
		
		private void getCreateParam() {
			String errorMsg = null;
			Map<String, String> createParam = new HashMap<String, String>();
			createErrorMsg.setText("");
			createErrorMsg.setVisible(false);
			String sportsBtn = null;
			String currencyBtn = null;
			String entryFee = null;
			String entries = null;
			
			int count = 0;
			ButtonModel sportsBtnmodel = sportsBtnGroup.getSelection();
			if (sportsBtnmodel != null) {
				sportsBtn = sportsBtnmodel.getActionCommand();
				ButtonModel currencyBtnmodel = currencyBtnGroup.getSelection();
				if (currencyBtnmodel != null) {
					currencyBtn = currencyBtnmodel.getActionCommand();
					try {
						count = Integer.parseInt(contestCounts.getText());
						if (count > 100) {
							errorMsg = "Please Input Under 100";
						} else if (count == 0) {
							errorMsg = "Please Input Count ( ~ 100 )";
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
			
			createErrorMsg.setVisible(true);
			if (errorMsg != null) {
				createErrorMsg.setForeground(Color.RED);
				createErrorMsg.setText(errorMsg);
			} else {
				Boolean result;
				createParam.put("sports", sportsBtn);
				createParam.put("currency", currencyBtn);
				createParam.put("entryFee", entryFee);
				createParam.put("entries", entries);
				createParam.put("entryFeeIdx", Integer.toString(entryFeeCombo.getSelectedIndex()));
				createParam.put("entriesIdx", Integer.toString(entriesCombo.getSelectedIndex()));
				createParam.put("count", Integer.toString(count));
				System.out.println(createParam);
				result = autoSubmit.startMakeContest(createParam);
				if (result) {
					createErrorMsg.setForeground(Color.BLUE);
					createErrorMsg.setText("Create Complete");
				} else {
					createErrorMsg.setForeground(Color.RED);
					createErrorMsg.setText("Create Fail. Please Try Again");
				}
			}
		}
		
		private void setDefaultParam() {
			createErrorMsg.setText("");
			createErrorMsg.setVisible(false);
			gameTypeLol.setSelected(true);
			currencyGdc.setSelected(true);
			entryFeeCombo.setModel(new DefaultComboBoxModel(new String[] {"25", "50", "100", "250"}));
			contestCounts.setText("0");
		}
		
		private void setClearSelection() {
			sportsBtnGroup.clearSelection();
			currencyBtnGroup.clearSelection();
		}
		
		private void submitProcessAction() {
			/*resultText.setText("");
			resultText.setVisible(false);*/
			
			/*gameInfo.setText("");
			gameInfo.setVisible(false);*/
			
			Boolean processResult = false;
			String resultMsg = null;
			if (submitStartButton.isVisible()) {
				submitStartButton.setEnabled(false);
				createContestButton.setEnabled(false);
			} else if (submitRestartButton.isVisible()){
				submitRestartButton.setEnabled(false);			
			}
			processResult = autoSubmit.startRankingBall();
			// Thread startSubmit = new Thread(autoSubmit.startRankingBall());
			if (processResult) {
				if (submitRestartButton.isVisible()) {
					submitRestartButton.setEnabled(true);
					submitRestartButton.setVisible(false);						
				}
				if (!submitStartButton.isVisible()) {
					submitStartButton.setVisible(true);	
					createContestButton.setVisible(true);
				}
				submitStartButton.setEnabled(true);
				createContestButton.setEnabled(true);
				resultMsg = "Submit Entry Success";
				resultText.setForeground(Color.BLUE);
			} else {
				if (submitStartButton.isVisible()) {
					submitStartButton.setEnabled(true);
					submitStartButton.setVisible(false);
					createContestButton.setEnabled(true);
					createContestButton.setVisible(false);
				}
				if (!submitRestartButton.isVisible()) {
					submitRestartButton.setVisible(true);
				}
				submitRestartButton.setEnabled(true);
				resultMsg = "Submit Fail. Please Try Again";
				resultText.setForeground(Color.RED);
			}
			resultText.setVisible(true);
			resultText.setText(resultMsg);
		}
		
		/*public void setMatchTitle(String title) {
			System.out.println("MATCH TITLE = "+title);
			gameInfo.setVisible(true);
			gameInfo.setText(title);
		}*/
	}
}
