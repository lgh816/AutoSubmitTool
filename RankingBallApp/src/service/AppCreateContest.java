package service;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
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
	private JButton CREATE_BACK_BTN;
	private JButton CREATE_STOP_BTN;
	private JButton CREATE_OK_BTN;
	private JTextField CREATE_RESULT_TEXT;
	private int CREATE_COUNT;
	
	public AppCreateContest(Map<String, String> param, JTextField createResultText, JButton createBackBtn, JButton createStopBtn, JButton createOkBtn) {
		this.param = param;
		this.CREATE_SPORTS_ID = appCommon.getGameId(param.get("sports"));
		this.CREATE_BACK_BTN = createBackBtn;
		this.CREATE_STOP_BTN = createStopBtn;
		this.CREATE_OK_BTN = createOkBtn;
		this.CREATE_RESULT_TEXT = createResultText;
		this.CREATE_COUNT = appCommon.CREATE_COUNT;
	}
	
	public void run() {
		String exceptionMsg = null;
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
			
			/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='"+CREATE_SPORTS_ID+"']")));
			WebElement gameTypeElement = DRIVER.findElement(By.xpath("//*[@id='"+CREATE_SPORTS_ID+"']"));
			String checkActive = gameTypeElement.getAttribute("class");
			if (!checkActive.contains("active")) {
				gameTypeElement.click();
			}*/
			
			// Count of Games
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			List<WebElement> gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			int gameSize = gameElements.size();
			
			System.out.println("====== [CREATE] Create Count Properties "+CREATE_COUNT);
			System.out.println("====== [CREATE] Selected Game Type = "+selectedType);
			System.out.println("====== [CREATE] Today's Game Count = "+gameSize);
			
			CREATE_RESULT_TEXT.setText("Check Games......");
			for (int i = 0; i < gameSize; i++) { // Loop Today Games
				Boolean backFlag = true;
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
				gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
				String eachGameId = gameElements.get(i).getAttribute("id");
				
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
			CREATE_OK_BTN.setEnabled(true);
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
