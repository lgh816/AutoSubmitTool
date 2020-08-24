package service;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import data.CommonData;
import ui.AppUi;

public class AppSubmitContest extends Thread implements CommonData {
	private AppCommon appCommon = new AppCommon();
	private WebElement webElement;	
	private WebDriverWait wait = new WebDriverWait(DRIVER, 30);
	private String SUBMIT_SPORTS_ID;
	private Integer GAME_DATE_IDX;
	private String CURRENCY;
	private String SUBMIT_GAME_TYPE;
	private JTextField SUBMIT_RESULT_TEXT;
	private JButton SUBMIT_BACK_BTN;
	private JButton SUBMIT_STOP_BTN;
	private JButton SUBMIT_ALL_OK_BTN;
	private JButton SUBMIT_OK_BTN;
	private String SUBMIT_SELECTED_GAME_ID;
	private JList SUBMIT_GAME_LIST;
	
	public AppSubmitContest(String gameType, String currencyBtn, String gameTypeBtn, JTextField submitResultText, JButton submitBackBtn, JButton submitStopBtn, JButton submitAllOkBtn, JButton submitOkBtn, String selectedGameId, JList submitTodayGameList, JComboBox gameDateList) {
		this.SUBMIT_SPORTS_ID = appCommon.getGameId(gameType);
		this.GAME_DATE_IDX = gameDateList.getSelectedIndex();
		this.SUBMIT_GAME_TYPE = gameTypeBtn;
		this.SUBMIT_RESULT_TEXT = submitResultText;
		this.SUBMIT_BACK_BTN = submitBackBtn;
		this.SUBMIT_STOP_BTN = submitStopBtn;
		this.SUBMIT_ALL_OK_BTN = submitAllOkBtn;
		this.SUBMIT_OK_BTN = submitOkBtn;
		this.SUBMIT_SELECTED_GAME_ID = selectedGameId;
		this.SUBMIT_GAME_LIST = submitTodayGameList;
		this.CURRENCY = currencyBtn;
	}
	
	public void run() {
		String exceptionMsg = null;
		int gameDepth = 0;
		try {
			System.out.println("====== [SUBMIT] Thread RUN");
			appCommon.selectSports(SUBMIT_SPORTS_ID);
			appCommon.checkPopup();
			
			Actions moveToGameArea = new Actions(DRIVER);
			int gameSize = 1;
			List<WebElement> gameElements = null;
			
			gameDepth = (GAME_DATE_IDX + 1) * 2;
			// Count of Games
			if (SUBMIT_SELECTED_GAME_ID == null) { // Click 'SUBMIT All'
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div")));
				gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div"));
				gameSize = gameElements.size();
			} else {
				System.out.println("====== [SUBMIT] Select One Game");
			}
			
			System.out.println("====== [SUBMIT] Selected Game Type = "+SUBMIT_GAME_TYPE);
			System.out.println("====== [SUBMIT] Today's Game Count = "+gameSize);
			
			SUBMIT_RESULT_TEXT.setText("Check Games......");
			for (int i = 0; i < gameSize; i++) { // Loop Today Games
				int totalGdc = 0;
				int totalPoint = 0;
				Thread.sleep(1500);
				
				String eachGameId = null;
				if (SUBMIT_SELECTED_GAME_ID == null) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div")));
					gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li["+gameDepth+"]/div"));
					eachGameId = gameElements.get(i).getAttribute("id");
				} else {
					eachGameId = SUBMIT_SELECTED_GAME_ID;
				}
				
				webElement = DRIVER.findElement(By.id(eachGameId));
				moveToGameArea.moveToElement(webElement);
				moveToGameArea.perform();
				
				String checkJoined = DRIVER.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[1]/span[1]")).getAttribute("style");
				System.out.println("====== [SUBMIT] CHECK JOINED "+eachGameId+" = "+checkJoined);
				if (checkJoined.isEmpty()) { // Check joined or not
					
					webElement.click(); // Click one game
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
					List<WebElement> contestElements = DRIVER.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
					int contestSize = contestElements.size();
					String homeTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-first")).getText();
					String awayTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-second")).getText();
					int eachMatch = i + 1;
					String matchTitle = "[Match "+eachMatch+"/"+gameSize+"] - ["+homeTeam+" vs " + awayTeam+"]";
					
					System.out.println("===================================");
					System.out.println("====== [SUBMIT] "+homeTeam+" vs " + awayTeam);
					System.out.println("====== [SUBMIT] "+contestSize+" CONTESTS");
					System.out.println("====== [SUBMIT] "+matchTitle);
					System.out.println("====== [SUBMIT] Selected Currency = "+CURRENCY);
					System.out.println("====== [SUBMIT] Selected Game Type = "+SUBMIT_GAME_TYPE);
					
					for (int j = 0 ; j < contestSize; j++) {
						Thread.sleep(1000);
						
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
						contestElements = DRIVER.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
						String joinStatus = contestElements.get(j).findElement(By.cssSelector(".entry-txt")).getText(); // JOIN, JOINED, ENTRY FULL
						
						if (!"ENTRY FULL".equals(joinStatus)) {
							String kindOfFee = contestElements.get(j).findElement(By.cssSelector(".entry-money span")).getAttribute("class"); // GDC, POINT
							String gameFeeTxt = contestElements.get(j).findElement(By.cssSelector(".entry-money span")).getText();
							String type = null;
							int gameFee = 0;
							if (!"FREE".equals(gameFeeTxt)) {
								gameFeeTxt = gameFeeTxt.replaceAll("\\,", "");
								gameFee = Integer.parseInt(gameFeeTxt);
							}
							if ("asset-121015".equals(kindOfFee)) { // GDC
								type = "GDC";
							} else if ("asset-121017".equals(kindOfFee)) { // Point
								type = "POINT";
							} else { // Free
								type = "FREE";
							}
							Boolean checkCurrencyType = true;
							
							if (CURRENCY != "All" && type != "FREE") { // Check GDC or Point
								if (CURRENCY != type) {
									System.out.println("====== [SUBMIT] Selected Currency And Game Type Is Not Matched");
									checkCurrencyType = false;
								}
							}
							
							if (checkCurrencyType) {
								if (!"JOINED".equals(joinStatus)) {
									String checkGameTypeCss = contestElements.get(j).findElement(By.cssSelector(".row")).getAttribute("class");
									// System.out.println("====== [SUBMIT] Game Type = "+checkGameTypeCss);
									if (SUBMIT_GAME_TYPE == "All" || checkGameTypeCss.contains(SUBMIT_GAME_TYPE)) {
										Thread.sleep(1000);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".entry-fee")));
										WebElement eachContestBtn = contestElements.get(j).findElement(By.cssSelector(".entry-fee"));
										Actions moveAction = new Actions(DRIVER);
										moveAction.moveToElement(eachContestBtn);
										moveAction.perform(); // Move Each Contest Button
										
										eachContestBtn.click(); // Click Each Contest
										
										String popTxt = "";
										try {
											popTxt = DRIVER.findElement(By.xpath("//*[@id='wrab']/div[3]/div/div/div/div[2]")).getText();
										} catch (Exception e) {
											
										}
										Thread.sleep(1500);
										if (!popTxt.isEmpty()) {
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
											Thread.sleep(1000);
											WebElement popupOkButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
											popupOkButton.click();
										} else {
											appCommon.submitCommonProcess("SUBMIT");
										}
									}
								} else if ("JOINED".equals(joinStatus)) {
									/*if ("asset-121015".equals(kindOfFee)) { // GDC
										totalGdc += gameFee;
									} else { // asset-121017 -> Point
										totalPoint += gameFee;
									}*/
								}
								if ("asset-121015".equals(kindOfFee)) { // GDC
									totalGdc += gameFee;
								} else { // asset-121017 -> Point
									totalPoint += gameFee;
								}
							}
						}
						// ================= Calculate Percent ===================
						double percent = (double) ( (double)(j+1) / (double)contestSize) * 100;
						DecimalFormat form = new DecimalFormat("0");
						String progressText = matchTitle + " - " + form.format(percent)+"%";
						SUBMIT_RESULT_TEXT.setText(progressText);
						System.out.println("====== [SUBMIT] Check Contest "+form.format(percent)+"%");
						progressText = null;
						// ================================================
					}

					System.out.println("====== [SUBMIT] "+totalGdc+" TOTAL GDC");
					System.out.println("====== [SUBMIT] "+totalPoint+" TOTAL POINT");
					System.out.println("===================================");

					eachGameId = null;
					DRIVER.navigate().back();
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMsg = e.getMessage();
			System.out.println("====== [SUBMIT] Exception Message = "+exceptionMsg);
		} finally {
			SUBMIT_BACK_BTN.setEnabled(true);
			SUBMIT_STOP_BTN.setEnabled(false);
			SUBMIT_ALL_OK_BTN.setEnabled(true);
			SUBMIT_OK_BTN.setEnabled(true);
			SUBMIT_GAME_LIST.setEnabled(true);
			if (exceptionMsg != null) {
				SUBMIT_RESULT_TEXT.setForeground(Color.RED);
				if (exceptionMsg.contains("sleep interrupted")) { // Click Stop Button
					System.out.println("====== [SUBMIT] CLICK STOP BUTTON");
					SUBMIT_RESULT_TEXT.setText("Submit Stoped");
				} else if (exceptionMsg.contains("Expected condition failed")) {
					System.out.println("====== [SUBMIT] OCCUR EXCEPTION");
					SUBMIT_RESULT_TEXT.setText("Submit Fail. Please Try Again");
				}
				appCommon.selectSports(SUBMIT_SPORTS_ID);
				
				/*Timer timer = new Timer();
				TimerTask timerTask = new TimerTask() {
					@Override
					public void run() {
						AppUi appUi = new AppUi();
						appUi.restartSubmit();
					}
				};
				timer.schedule(timerTask, 5000);*/
			} else {
				System.out.println("====== [SUBMIT] SUBMIT SUCCESS");
				SUBMIT_RESULT_TEXT.setForeground(Color.BLUE);
				SUBMIT_RESULT_TEXT.setText("Submit Entry Success");
			}
		}
	}
}
