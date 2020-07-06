package service;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.CommonData;
import ui.AppUi;

public class AppCreateContest extends Thread implements CommonData {
	private WebElement webElement;	
	private WebDriverWait wait = new WebDriverWait(DRIVER, 30);
	private AppCommon appCommon = new AppCommon();
	private Map<String, String> param;
	private String CREATE_SPORTS_ID;
	private Integer GAME_DATE_IDX;
	private JButton CREATE_BACK_BTN;
	private JButton CREATE_STOP_BTN;
	private JButton CREATE_OK_BTN;
	private JButton CREATE_ALL_OK_BTN;
	private JTextField CREATE_RESULT_TEXT;
	private String CREATE_SELECTED_GAME_ID;
	private JList CREATE_GAME_LIST;
	private int CREATE_COUNT;
	
	public AppCreateContest(Map<String, String> param, JTextField createResultText, JButton createBackBtn, JButton createStopBtn, JButton createAllOkBtn, JButton createOkBtn, String selectedGameId, JList createTodayGameList, JComboBox gameDateList) {
		this.param = param;
		this.CREATE_SPORTS_ID = appCommon.getGameId(param.get("sports"));
		this.GAME_DATE_IDX = gameDateList.getSelectedIndex();
		this.CREATE_BACK_BTN = createBackBtn;
		this.CREATE_STOP_BTN = createStopBtn;
		this.CREATE_ALL_OK_BTN = createAllOkBtn;
		this.CREATE_OK_BTN = createOkBtn;
		this.CREATE_SELECTED_GAME_ID = selectedGameId;
		this.CREATE_GAME_LIST = createTodayGameList;
		this.CREATE_RESULT_TEXT = createResultText;
		this.CREATE_COUNT = AppCommon.CREATE_COUNT;
	}
	
	public void run() {
		String exceptionMsg = null;
		int gameDepth = 0;
		try {
			System.out.println("====== [CREATE] Thread RUN");
			appCommon.selectSports(CREATE_SPORTS_ID);
			appCommon.checkPopup();

			String currency = param.get("currency");
			String entryFee = param.get("entryFee");
			String entries = param.get("entries");
			String selectedType = param.get("type");
			
			int entryFeeIdx = Integer.parseInt(param.get("entryFeeIdx"));
			int entriesIdx = Integer.parseInt(param.get("entriesIdx"));
			int count = Integer.parseInt(param.get("count"));
			
			Actions moveToGameArea = new Actions(DRIVER);
			int gameSize = 1;
			List<WebElement> gameElements = null;
			
			gameDepth = (GAME_DATE_IDX + 1) * 2;
			// Count of Games
			if (CREATE_SELECTED_GAME_ID == null) { // Click 'SUBMIT All'
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div")));
				gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div"));
				gameSize = gameElements.size();
			} else {
				System.out.println("====== [CREATE] Select One Game");
			}
			/*
			// Count of Games
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			List<WebElement> gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			int gameSize = gameElements.size();*/
			
			System.out.println("====== [CREATE] Create Count Properties "+CREATE_COUNT);
			System.out.println("====== [CREATE] Selected Game Type = "+selectedType);
			System.out.println("====== [CREATE] Today's Game Count = "+gameSize);
			
			CREATE_RESULT_TEXT.setText("Check Games......");
			for (int i = 0; i < gameSize; i++) { // Loop Today Games
				Boolean backFlag = true;
				
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
				// gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
				// String eachGameId = gameElements.get(i).getAttribute("id");
				
				String eachGameId = null;
				if (CREATE_SELECTED_GAME_ID == null) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div")));
					gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div"));
					eachGameId = gameElements.get(i).getAttribute("id");
				} else {
					eachGameId = CREATE_SELECTED_GAME_ID;
				}
				
				webElement = DRIVER.findElement(By.id(eachGameId));
				moveToGameArea.moveToElement(webElement);
				moveToGameArea.perform();
				
				String checkJoined = DRIVER.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[1]/span[1]")).getAttribute("style");
				System.out.println("====== [CREATE] CHECK JOINED = "+checkJoined);
				if (checkJoined.isEmpty()) {  // Check joined or not
					int eachContestSize = Integer.parseInt(DRIVER.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[2]/div[2]/p[2]/span")).getText());
					System.out.println("====== [CREATE] [Contests Size = "+eachContestSize+"] ======");
					if (eachContestSize > CREATE_COUNT) {
						backFlag = false;
						System.out.println("====== [CREATE] [Over "+CREATE_COUNT+" Contests] =====");
						Thread.sleep(2000);
					} else {
						webElement.click(); // Click one game
			
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
						List<WebElement> contestElements = DRIVER.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
						int contestSize = contestElements.size();
						String homeTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-first")).getText();
						String awayTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-second")).getText();
						int eachMatch = i + 1;
						String matchTitle = "[Match "+eachMatch+"/"+gameSize+"] - ["+homeTeam+" vs " + awayTeam+"]";
						
						System.out.println("===================================");
						System.out.println("====== [CREATE] "+homeTeam+" vs " + awayTeam);
						System.out.println("====== [CREATE] "+contestSize+" CONTESTS");
						System.out.println(matchTitle);
						System.out.println("====== [CREATE] Selected Currency = "+currency);
						System.out.println("====== [CREATE] Selected Game Type = "+selectedType);
						
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a")));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a")));
						
						WebElement createBtnElement = DRIVER.findElement(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a"));
						Thread.sleep(5000);
						createBtnElement.click(); // Click 'Create Contest' button
						WebElement currencyBtn = null;
						if ("gdc".equals(currency)) {
							currencyBtn = DRIVER.findElement(By.xpath("//*[@id='assetType']/li[1]"));
						} else { // 'point'
							currencyBtn = DRIVER.findElement(By.xpath("//*[@id='assetType']/li[2]"));
						}
						currencyBtn.click(); // Click 'Currency'
						List<WebElement> typeArr = DRIVER.findElements(By.xpath("//*[@id='contestType']/li"));
						int typeSize = typeArr.size();
						
						for (int j = 0; j < typeSize; j++) {
							typeArr = DRIVER.findElements(By.xpath("//*[@id='contestType']/li"));
							int childCnt = j+1;
							WebElement type = typeArr.get(j);
							String text = type.getText(); // Tournaments, 50/50, 30/30
							
							if (!"Head-to-Head".equals(text) && (selectedType.equals("All") || selectedType.equals(text))) {
								for (int k = 0; k < count; k ++) {
									if (eachContestSize <= CREATE_COUNT) {
										Thread.sleep(2500);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='contestType']/li["+childCnt+"]")));
										WebElement eachType = DRIVER.findElement(By.xpath("//*[@id='contestType']/li["+childCnt+"]"));
										eachType.click();
										setCreateOptions(entryFee, entries, entryFeeIdx, entriesIdx);
										eachContestSize =  eachContestSize + 1;
										System.out.println("====== [CREATE] [Create "+(k+1)+"] "+text+" [Fee] "+entryFee+" "+currency+" [Entry] "+entries+"");
										System.out.println("====== [CREATE] [ContestSize] "+eachContestSize+" =====");
										
										// ================= Calculate Percent ===================
										double percent = (double) ( (double)(k+1) / (double)count) * 100;
										DecimalFormat form = new DecimalFormat("0");
										String cnvtText = null;
										if (text == "Tournaments") {
											cnvtText = "TNMT";
										} else {
											cnvtText = text;
										}
										String progressText = matchTitle + " - [" +cnvtText+ "] - " + form.format(percent)+"%";
										CREATE_RESULT_TEXT.setText(progressText);
										System.out.println("====== [CREATE] Check Contest "+form.format(percent)+"%");
										progressText = null;
										// ================================================
									} else {
										break;
									}
								}
								System.out.println("====== [CREATE] [Create "+count+"] "+text+" Complete");
							}
						}
						DRIVER.navigate().back();
					}
					if (backFlag) {
						DRIVER.navigate().back();
						Thread.sleep(2000);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMsg = e.getMessage();
			System.out.println("====== [CREATE] Exception Message = "+exceptionMsg);
		} finally {
			CREATE_BACK_BTN.setEnabled(true);
			CREATE_STOP_BTN.setEnabled(false);
			CREATE_ALL_OK_BTN.setEnabled(true);
			CREATE_OK_BTN.setEnabled(true);
			CREATE_GAME_LIST.setEnabled(true);
			if (exceptionMsg != null) {
				CREATE_RESULT_TEXT.setForeground(Color.RED);
				if (exceptionMsg.contains("sleep interrupted")) { // Click Stop Button
					System.out.println("====== [CREATE] CLICK STOP BUTTON");
					CREATE_RESULT_TEXT.setText("Create Stoped");
				} else if (exceptionMsg.contains("Expected condition failed")) {
					System.out.println("====== [CREATE] OCCUR EXCEPTION");
					CREATE_RESULT_TEXT.setText("Create Fail. Please Try Again");
				}
				appCommon.selectSports(CREATE_SPORTS_ID);
				
				/*Timer timer = new Timer();
				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						AppUi appUi = new AppUi();
						appUi.restartCreate();
					}
				};
				timer.schedule(timerTask, 5000);*/
			} else {
				System.out.println("====== [CREATE] CREATE SUCCESS");
				CREATE_RESULT_TEXT.setForeground(Color.BLUE);
				CREATE_RESULT_TEXT.setText("Create Success");
			}
		}
	}
	
	private void setCreateOptions(String entryFee, String entries, int entryFeeIdx, int entriesIdx) {
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[1]/select")));
			Select entryFeeList = new Select(DRIVER.findElement(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[1]/select")));
			entryFeeList.selectByVisibleText(entryFee);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[2]/select")));
			Select entriesList = new Select(DRIVER.findElement(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[2]/select")));
			entriesList.selectByVisibleText(entries);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/div/button")));
			WebElement nextBtn = DRIVER.findElement(By.xpath("//*[@id='container']/div[2]/div/div[3]/div/button"));
			nextBtn.click();
			
			appCommon.submitCommonProcess("CREATE");
		} catch (Exception e) {
			appCommon.submitCommonProcess("CREATE");
			String exceptionMsg = e.getMessage();
			System.out.println("====== [CREATE] setCreateOptions Exception Message = "+exceptionMsg);
		}
	}
}
