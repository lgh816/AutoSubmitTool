package service;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.CommonData;

public class AppSubmitContest extends Thread implements CommonData {
	private AppCommon appCommon = new AppCommon();
	private WebElement webElement;	
	private WebDriverWait wait = new WebDriverWait(DRIVER, 30);
	private String SPORTS_ID;
	private String CURRENCY;
	private JTextField RESULT_TEXT;
	private JButton SUBMIT_BACK_BTN;
	private JButton SUBMIT_STOP_BTN;
	private JButton SUBMIT_OK_BTN;
	
	public AppSubmitContest(String gameType, String currencyBtn, JTextField resultText, JButton submitBackBtn, JButton submitStopBtn, JButton submitOkBtn) {
		this.SPORTS_ID = appCommon.getGameId(gameType);
		this.RESULT_TEXT = resultText;
		this.SUBMIT_BACK_BTN = submitBackBtn;
		this.SUBMIT_STOP_BTN = submitStopBtn;
		this.SUBMIT_OK_BTN = submitOkBtn;
		this.CURRENCY = currencyBtn;
	}
	
	public void run() {
		String exceptionMsg = null;
		try {
			System.out.println("Submit Thread RUN");
			
			appCommon.checkPopup();
			Boolean result = false;
			// Count of Games
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			List<WebElement> gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			int gameSize = gameElements.size();
			
			System.out.println("======= [SUBMIT] Today's Game Count = "+gameSize);
			
			for (int i = 0; i < gameSize; i++) { // Loop Today Games
				int totalGdc = 0;
				int totalPoint = 0;
				Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
				gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
				String eachGameId = gameElements.get(i).getAttribute("id");
				String checkJoined = DRIVER.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[1]/span[1]")).getAttribute("style");
				System.out.println("SUBMIT CHECK JOINED = "+checkJoined);
				if (checkJoined.isEmpty()) { // Check joined or not
					webElement = DRIVER.findElement(By.id(eachGameId));
					webElement.click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
					List<WebElement> contestElements = DRIVER.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
					int contestSize = contestElements.size();
					String homeTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-first")).getText();
					String awayTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-second")).getText();
					System.out.println("===================================");
					System.out.println("==== [SUBMIT] "+homeTeam+" vs " + awayTeam);
					System.out.println("==== [SUBMIT] "+contestSize+" CONTESTS");
					int eachMatch = i + 1;
					String matchTitle = "Match "+eachMatch+" / "+gameSize+" - "+homeTeam+" vs " + awayTeam;
					System.out.println(matchTitle);
					// appUi.actionListener.setMatchTitle(matchTitle);
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
							System.out.println("==== [SUBMIT] Selected Currency = "+CURRENCY);
							if (CURRENCY != "All" && type != "FREE") {
								if (CURRENCY != type) {
									System.out.println("==== [SUBMIT] Selected Currency And Game Type Is Not Matched");
									checkCurrencyType = false;
								}
							}
							if (checkCurrencyType) {
								if (!"JOINED".equals(joinStatus)) {
									Thread.sleep(1000);
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".entry-fee")));
									contestElements.get(j).findElement(By.cssSelector(".entry-fee")).click(); // Click Each Contest
									String popTxt = "";
									try {
										popTxt = DRIVER.findElement(By.xpath("//*[@id='wrab']/div[3]/div/div/div/div[2]")).getText();
									} catch (Exception e) {
										
									}
									System.out.println("Check Popup Text = "+popTxt);
									Thread.sleep(1500);
									if (!popTxt.isEmpty()) {
										// if ("There is not enough balance".equals(popTxt)) {
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
										Thread.sleep(1000);
										WebElement popupOkButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
										popupOkButton.click();
									} else {
										// WebElement loadButtonElement = new WebDriverWait(DRIVER, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
										// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='load']"))); // presenceOfElementLocated <-> visibilityOfElementLocated
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='load']")));
										wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
										WebElement loadButtonElement = DRIVER.findElement(By.xpath("//*[@id='load']"));
										Thread.sleep(1000);
										String checkEntries = loadButtonElement.getAttribute("class");
										System.out.println("SUCCESS LOAD ENTRY - "+ j + " / "+ contestSize +" - "+ gameFee +" "+type);
										if ("entries on".equals(checkEntries)) {
											loadButtonElement.click();
											// Thread.sleep(1500);
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button")));
											WebElement selectEntryButton = DRIVER.findElement(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button"));
											selectEntryButton.click();
											// Thread.sleep(1500);
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submit']")));
											WebElement submitButton = DRIVER.findElement(By.xpath("//*[@id='submit']"));
											submitButton.click();
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
											WebElement okButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
											okButton.click();
											Thread.sleep(1000);
											wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
											Thread.sleep(1000);
											WebElement secondOkButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
											Thread.sleep(1000);
											secondOkButton.click();
											Thread.sleep(1300);
											DRIVER.navigate().back();
										} else {
											System.out.println("======= [SUBMIT] Entries Not Exist !!!!!!!! ========");
											DRIVER.navigate().back();
											// break;
										}
										loadButtonElement = null;
										checkEntries = null;
									}
								} else if ("JOINED".equals(joinStatus)) {
									if ("asset-121015".equals(kindOfFee)) { // GDC
										totalGdc += gameFee;
									} else { // asset-121017 -> Point
										totalPoint += gameFee;
									}
								}
							}
						}
						// =========== Calculate Percent ==================
						double percent = (double) ( (double)(j+1) / (double)contestSize) * 100;
						String dispPattern = "0";
						DecimalFormat form = new DecimalFormat(dispPattern);
						String progressText = matchTitle + " " + form.format(percent)+"%";
						RESULT_TEXT.setText(progressText);
						System.out.println("==== [SUBMIT] Check Contest "+form.format(percent)+"%");
						progressText = null;
						// ==========================================
					}

					System.out.println("==== [SUBMIT] "+totalGdc+" TOTAL GDC");
					System.out.println("==== [SUBMIT] "+totalPoint+" TOTAL POINT");
					System.out.println("===================================");

					eachGameId = null;
					DRIVER.navigate().back();
					Thread.sleep(2000);
				}
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			exceptionMsg = e.getMessage();
			System.out.println("Auto Submit Exception Message = "+exceptionMsg);
			// Stop Thread Msg = 'sleep interrupted'
			// 'Expected condition failed'
			
			/*DRIVER.navigate().back();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='105001']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='105001']")));
			WebElement lolButton = DRIVER.findElement(By.xpath("//*[@id='105001']"));
			lolButton.click();
			startSubmitContest();*/
		} finally {
			
			SUBMIT_BACK_BTN.setEnabled(true);
			SUBMIT_STOP_BTN.setEnabled(false);
			SUBMIT_OK_BTN.setEnabled(true);
			if (exceptionMsg != null) {
				if (exceptionMsg.contains("sleep interrupted")) { // Click Stop Button
					RESULT_TEXT.setForeground(Color.RED);
					System.out.println("CLICK STOP BUTTON");
					RESULT_TEXT.setText("Submit Stoped");
				} else if (exceptionMsg.contains("Expected condition failed")) {
					RESULT_TEXT.setForeground(Color.RED);
					System.out.println("OCCUR EXCEPTION");
					RESULT_TEXT.setText("Submit Fail. Please Try Again");
					System.out.println("SPORTS_ID = "+SPORTS_ID);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='"+SPORTS_ID+"']")));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='"+SPORTS_ID+"']")));
					WebElement sportsBtn = DRIVER.findElement(By.xpath("//*[@id='"+SPORTS_ID+"']"));
					sportsBtn.click();
				}
			} else {
				System.out.println("SUBMIT SUCCESS");
				RESULT_TEXT.setForeground(Color.BLUE);
				RESULT_TEXT.setText("Submit Entry Success");
			}
			
			/*submitStopBtn.setEnabled(false);
			submitBackBtn.setEnabled(true);
			submitOkBtn.setEnabled(true);*/
			
/*			appUi.submitOkBtn.setEnabled(true);
			appUi.submitStopBtn.setEnabled(false);
			appUi.submitBackBtn.setEnabled(true);*/
		}
	}
	
	/*public Boolean startSubmitContest() {
		// wait = new WebDriverWait(DRIVER, 30);
		appCommon.checkPopup();
		Boolean result = false;
		try {
			// Count of Games
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			List<WebElement> gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			int gameSize = gameElements.size();
			
			System.out.println("======= [SUBMIT] Today's Game Count = "+gameSize);
			
			for (int i = 0; i < gameSize; i++) { // Loop Today Games
				int totalGdc = 0;
				int totalPoint = 0;
				Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
				gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
				String eachGameId = gameElements.get(i).getAttribute("id");
				String checkJoined = DRIVER.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[1]/span[1]")).getAttribute("style");
				System.out.println("SUBMIT CHECK JOINED = "+checkJoined);
				if (checkJoined.isEmpty()) { // Check joined or not
					webElement = DRIVER.findElement(By.id(eachGameId));
					webElement.click();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
					List<WebElement> contestElements = DRIVER.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
					int contestSize = contestElements.size();
					String homeTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-first")).getText();
					String awayTeam = DRIVER.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-second")).getText();
					System.out.println("===================================");
					System.out.println("==== [SUBMIT] "+homeTeam+" vs " + awayTeam);
					System.out.println("==== [SUBMIT] "+contestSize+" CONTESTS");
					int eachMatch = i + 1;
					String matchTitle = "Match "+eachMatch+" / "+gameSize+" - "+homeTeam+" vs " + awayTeam;
					System.out.println(matchTitle);
					// appUi.actionListener.setMatchTitle(matchTitle);
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
								gameFee = Integer.parseInt(gameFeeTxt);
							}
							if ("asset-121015".equals(kindOfFee)) { // GDC
								type = "GDC";
							} else if ("asset-121017".equals(kindOfFee)) { // Point
								type = "POINT";
							} else { // Free
								type = "FREE";
							}
							if (!"JOINED".equals(joinStatus)) {
								Thread.sleep(1000);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".entry-fee")));
								contestElements.get(j).findElement(By.cssSelector(".entry-fee")).click(); // Click Each Contest
								String popTxt = "";
								try {
									popTxt = DRIVER.findElement(By.xpath("//*[@id='wrab']/div[3]/div/div/div/div[2]")).getText();
								} catch (Exception e) {
									
								}
								System.out.println("Check Popup Text = "+popTxt);
								Thread.sleep(1500);
								if (!popTxt.isEmpty()) {
									// if ("There is not enough balance".equals(popTxt)) {
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
									Thread.sleep(1000);
									WebElement popupOkButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
									popupOkButton.click();
								} else {
									// WebElement loadButtonElement = new WebDriverWait(DRIVER, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
									// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='load']"))); // presenceOfElementLocated <-> visibilityOfElementLocated
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='load']")));
									wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
									WebElement loadButtonElement = DRIVER.findElement(By.xpath("//*[@id='load']"));
									Thread.sleep(1000);
									String checkEntries = loadButtonElement.getAttribute("class");
									System.out.println("SUCCESS LOAD ENTRY - "+ j + " / "+ contestSize +" - "+ gameFee +" "+type);
									if ("entries on".equals(checkEntries)) {
										loadButtonElement.click();
										// Thread.sleep(1500);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button")));
										WebElement selectEntryButton = DRIVER.findElement(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button"));
										selectEntryButton.click();
										// Thread.sleep(1500);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submit']")));
										WebElement submitButton = DRIVER.findElement(By.xpath("//*[@id='submit']"));
										submitButton.click();
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
										WebElement okButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
										okButton.click();
										Thread.sleep(1000);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
										Thread.sleep(1000);
										WebElement secondOkButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
										Thread.sleep(1000);
										secondOkButton.click();
										Thread.sleep(1300);
										DRIVER.navigate().back();
									} else {
										System.out.println("======= [SUBMIT] Entries Not Exist !!!!!!!! ========");
										DRIVER.navigate().back();
										// break;
									}
									loadButtonElement = null;
									checkEntries = null;
								}
							} else if ("JOINED".equals(joinStatus)) {
								if ("asset-121015".equals(kindOfFee)) { // GDC
									totalGdc += gameFee;
								} else { // asset-121017 -> Point
									totalPoint += gameFee;
								}
							}
						}
						// =========== Calculate Percent ==================
						double percent = (double) ( (double)(j+1) / (double)contestSize) * 100;
						String dispPattern = "0";
						DecimalFormat form = new DecimalFormat(dispPattern);
						System.out.println("==== [SUBMIT] Check Contest "+form.format(percent)+"%");
						// ==========================================
					}

					System.out.println("==== [SUBMIT] "+totalGdc+" TOTAL GDC");
					System.out.println("==== [SUBMIT] "+totalPoint+" TOTAL POINT");
					System.out.println("===================================");

					eachGameId = null;
					DRIVER.navigate().back();
					Thread.sleep(2000);
				}
			}
			result = true;
		} catch (Exception e) {
			// e.printStackTrace();
			String exceptionMsg = e.getMessage();
			
			System.out.println("Auto Submit Exception Message = "+exceptionMsg);
			DRIVER.navigate().back();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='105001']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='105001']")));
			WebElement lolButton = DRIVER.findElement(By.xpath("//*[@id='105001']"));
			lolButton.click();
			startSubmitContest();
		}
		return result;
	}*/
}