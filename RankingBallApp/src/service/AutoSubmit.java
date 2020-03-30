package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoSubmit {
	
	public WebDriver driver;
	private WebElement webElement;	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/chromedriver/chromedriver.exe";
	private String base_url;
	WebDriverWait wait;

	public Boolean loginProcess(String email, String password) {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();
		base_url = "https://play.rankingball.com";
		wait = new WebDriverWait(driver, 30);
		Boolean result = false;
		
		try {
			driver.manage().window().maximize();
			driver.get(base_url);
	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login > p")));
			webElement = driver.findElement(By.cssSelector(".login > p"));
			webElement.click();
	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/form[1]/div[1]/input")));
			webElement = driver.findElement(By.xpath("/html/body/div/div/form[1]/div[1]/input"));
			webElement.sendKeys(email);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/form[1]/div[2]/input")));
			webElement = driver.findElement(By.xpath("/html/body/div/div/form[1]/div[2]/input"));
			webElement.sendKeys(password);
			
			webElement = driver.findElement(By.xpath("//*[@id='btnLogin']"));
			webElement.click();
			
			try {
				webElement = driver.findElement(By.cssSelector("body > div > div > form:nth-child(6) > div:nth-child(4) > div"));
				String errorText = webElement.getText();
				if (errorText.equals("Incorrect login ID or password")) {
					result = false;
				} else {
					result = true;
				}
			} catch (NoSuchElementException e) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}
	
	public Boolean startRankingBall() {
		Boolean result = false;
		try {
			// Count of Games
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			List<WebElement> gameElements = driver.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			int gameSize = gameElements.size();
			
			System.out.println("======= [SUBMIT] Today's Game Count = "+gameSize);
			for (int i = 0; i < gameSize; i++) {
				int totalGdc = 0;
				int totalPoint = 0;
				Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
				gameElements = driver.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
				String eachGameId = gameElements.get(i).getAttribute("id");
				webElement = driver.findElement(By.id(eachGameId));
				webElement.click();
				// Thread.sleep(1500);
				// Count of Contests
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
				List<WebElement> contestElements = driver.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
				int contestSize = contestElements.size();
				String homeTeam = driver.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-first")).getText();
				String awayTeam = driver.findElement(By.cssSelector("#container .content .game-content .game-info-content .contest-info .t-second")).getText();
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
					contestElements = driver.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
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
							String popTxt = driver.findElement(By.xpath("//*[@id='wrab']/div[3]/div/div/div/div[2]")).getText();
							System.out.println("Check Popup Text = "+popTxt);
							Thread.sleep(1500);
							if (!popTxt.isEmpty()) {
							// if ("There is not enough balance".equals(popTxt)) {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
								Thread.sleep(1000);
								WebElement popupOkButton = driver.findElement(By.cssSelector(".message-layer #submit"));
								popupOkButton.click();
							} else {
								// WebElement loadButtonElement = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
								// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='load']"))); // presenceOfElementLocated <-> visibilityOfElementLocated
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='load']")));
								wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
								WebElement loadButtonElement = driver.findElement(By.xpath("//*[@id='load']"));
								Thread.sleep(1000);
								String checkEntries = loadButtonElement.getAttribute("class");
								System.out.println("SUCCESS LOAD ENTRY - "+ j + " / "+ contestSize +" - "+ gameFee +" "+type);
								if ("entries on".equals(checkEntries)) {
									loadButtonElement.click();
									// Thread.sleep(1500);
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button")));
									WebElement selectEntryButton = driver.findElement(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button"));
									selectEntryButton.click();
									// Thread.sleep(1500);
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submit']")));
									WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));
									submitButton.click();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
									WebElement okButton = driver.findElement(By.cssSelector(".message-layer #submit"));
									okButton.click();
									Thread.sleep(1000);
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
									Thread.sleep(1000);
									WebElement secondOkButton = driver.findElement(By.cssSelector(".message-layer #submit"));
									Thread.sleep(1000);
									secondOkButton.click();
									Thread.sleep(1300);
									driver.navigate().back();
									// Thread.sleep(1500);
								} else {
									System.out.println("======= [SUBMIT] Entries Not Exist !!!!!!!! ========");
									driver.navigate().back();
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
				System.out.println("==== [SUBMIT] "+totalGdc+" TOTAL GDC");
				System.out.println("==== [SUBMIT] "+totalPoint+" TOTAL POINT");
				System.out.println("===================================");
				eachGameId = null;
				driver.navigate().back();
				Thread.sleep(2000);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMsg = e.getMessage();
			
			System.out.println("Auto Submit Exception Message = "+exceptionMsg);
			driver.navigate().back();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='105001']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='105001']")));
			WebElement lolButton = driver.findElement(By.xpath("//*[@id='105001']"));
			lolButton.click();
		/*} finally {
			result = true;
			System.out.println("======= All Submit Complete. =======");
			// driver.close();*/
		}
		return result;
	}

	public Boolean startMakeContest(Map<String, String> param) {
		Boolean result = false;
		/*{
			"lol" : "105001",
			"soccer" : "104001",
			"basketball" : "104004",
			"football" : "104003",
			"baseball" : "104002"
		}*/
		try {
			String gameType = param.get("sports");
			String currency = param.get("currency");
			String entryFee = param.get("entryFee");
			String entries = param.get("entries");
			int entryFeeIdx = Integer.parseInt(param.get("entryFeeIdx"));
			int entriesIdx = Integer.parseInt(param.get("entriesIdx"));
			int count = Integer.parseInt(param.get("count"));
			String gameId = null;
			
			if ("lol".equals(gameType)) {
				gameId = "105001";
			} else if ("soccer".equals(gameType)) {
				gameId = "104001";
			} else if ("basketball".equals(gameType)) {
				gameId = "104004";
			} else if ("football".equals(gameType)) {
				gameId = "104003";
			} else { // baseball
				gameId = "104002";
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='"+gameId+"']")));
			WebElement gameTypeElement = driver.findElement(By.xpath("//*[@id='"+gameId+"']"));
			String checkActive = gameTypeElement.getAttribute("class");
			if (!checkActive.contains("active")) {
				gameTypeElement.click();
			}
			
			// Count of Games
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			List<WebElement> gameElements = driver.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			int gameSize = gameElements.size();
			
			System.out.println("====== [CREATE] Today's Game Count = "+gameSize);
			for (int i = 0; i < gameSize; i++) {
				Boolean backFlag = true;
				System.out.println("====== [CREATE] [Game Number "+(i+1)+"] ======");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
				gameElements = driver.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
				String eachGameId = gameElements.get(i).getAttribute("id");
				webElement = driver.findElement(By.id(eachGameId));
				int eachContentSize = Integer.parseInt(driver.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[2]/div[2]/p[2]/span")).getText());
				System.out.println("====== [CREATE] [Contests Size = "+eachContentSize+"] ======");
				if (eachContentSize > 60) {
					backFlag = false;
					System.out.println("====== [CREATE] [Over 60 Contests] =====");
					Thread.sleep(2000);
				} else {
					webElement.click(); // Click one game
		
					/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
					List<WebElement> contestElements = driver.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
					int contestSize = contestElements.size();*/
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a")));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a")));
					
					// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("layer-bg loading-bg")));
					WebElement createBtnElement = driver.findElement(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a"));
					Thread.sleep(5000);
					createBtnElement.click(); // Click 'Create Contest' button
					WebElement currencyBtn = null;
					if ("gdc".equals(currency)) {
						currencyBtn = driver.findElement(By.xpath("//*[@id='assetType']/li[1]"));
					} else { // 'point'
						currencyBtn = driver.findElement(By.xpath("//*[@id='assetType']/li[2]"));
					}
					currencyBtn.click();
					List<WebElement> typeArr = driver.findElements(By.xpath("//*[@id='contestType']/li"));
					int typeSize = typeArr.size();
					for (int j = 0; j < typeSize; j++) {
						typeArr = driver.findElements(By.xpath("//*[@id='contestType']/li"));
						int childCnt = j+1;
						WebElement type = typeArr.get(j);
						String text = type.getText();
						if (!"Head-to-Head".equals(text)) {
							for (int k = 0; k < count; k ++) {
								if (eachContentSize < 61) {
									Thread.sleep(2500);
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='contestType']/li["+childCnt+"]")));
									WebElement eachType = driver.findElement(By.xpath("//*[@id='contestType']/li["+childCnt+"]"));
									eachType.click();
									createContestAction(entryFee, entries, entryFeeIdx, entriesIdx);
									System.out.println("====== [CREATE] [Create "+(k+1)+"] "+text+" [Fee] "+entryFee+" "+currency+" [Entry] "+entries+"");
									System.out.println("====== [CREATE] [contestSize] "+eachContentSize+" =====");
									eachContentSize++;
								}
							}
							System.out.println("====== [CREATE] [Create "+count+"] "+text+" Complete");
						} else {
							break;
						}
					}
					driver.navigate().back();
				}
				if (backFlag) {
					driver.navigate().back();
					Thread.sleep(2000);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMsg = e.getMessage();
			System.out.println("Auto Create Exception Message = "+exceptionMsg);
		}
		return result;
	}
	
	private void createContestAction(String entryFee, String entries, int entryFeeIdx, int entriesIdx) {
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[1]/select")));
			Select entryFeeList = new Select(driver.findElement(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[1]/select")));
			entryFeeList.selectByVisibleText(entryFee);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[2]/select")));
			Select entriesList = new Select(driver.findElement(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[5]/div[2]/select")));
			entriesList.selectByVisibleText(entries);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/div/button")));
			WebElement nextBtn = driver.findElement(By.xpath("//*[@id='container']/div[2]/div/div[3]/div/button"));
			nextBtn.click();
			
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='load']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
			WebElement loadButtonElement = driver.findElement(By.xpath("//*[@id='load']"));
			Thread.sleep(800);
			String checkEntries = loadButtonElement.getAttribute("class");
			if ("entries on".equals(checkEntries)) {
				loadButtonElement.click();
				// Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button")));
				WebElement selectEntryButton = driver.findElement(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button"));
				selectEntryButton.click();
				// Thread.sleep(1500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submit']")));
				WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));
				submitButton.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
				WebElement okButton = driver.findElement(By.cssSelector(".message-layer #submit"));
				okButton.click();
				Thread.sleep(800);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
				Thread.sleep(800);
				WebElement secondOkButton = driver.findElement(By.cssSelector(".message-layer #submit"));
				Thread.sleep(800);
				secondOkButton.click();
				Thread.sleep(1300);
				driver.navigate().back();
				// Thread.sleep(1500);
			} else {
				System.out.println("====== [SUBMIT] Entries Not Exist !!!!!!!! ======");
				driver.navigate().back();
				// break;
			}
			loadButtonElement = null;
			checkEntries = null;
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMsg = e.getMessage();
			System.out.println("Auto Create Exception Message = "+exceptionMsg);
		}
	}
	
	public ArrayList<Object> getTodaysMatch() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
		List<WebElement> gameElements = driver.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
		int gameSize = gameElements.size();
		ArrayList<Object> gameInfoArr = new ArrayList<Object>();
		Map<String, String> eachGameInfo = new HashMap<String, String>();
		/* [
		 * 		{
		 * 			"title" : "WE vs RW",
		 * 			"time" : "06:00 PM"
		 * 			"contests" : 138,
		 * 			"game_id" : 34324,
		 * 		}
		 * ]
		 * */
		for (int i = 0; i < gameSize; i++) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			gameElements = driver.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			String game_id = gameElements.get(i).getAttribute("id");
			String time = driver.findElement(By.xpath("//*[@id='"+game_id+"']/div[1]/div[1]/p")).getText();
			String homeTeam = driver.findElement(By.xpath("//*[@id='"+game_id+"']/div[2]/div[1]/div[1]/span[2]")).getText();
			String awayTeam = driver.findElement(By.xpath("//*[@id='"+game_id+"']/div[2]/div[1]/div[2]/span[2]")).getText();
			String title = homeTeam + " vs " + awayTeam;
			String contests = driver.findElement(By.xpath("//*[@id='"+game_id+"']/div[2]/div[2]/p[2]/span")).getText();
			
			eachGameInfo.put("title", title);
			eachGameInfo.put("time", time);
			eachGameInfo.put("contests", contests);
			eachGameInfo.put("game_id", game_id);
			gameInfoArr.add(eachGameInfo);
			eachGameInfo = new HashMap<String, String>();
		}
		
		return gameInfoArr;
	}
}
