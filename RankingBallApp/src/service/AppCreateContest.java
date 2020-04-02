package service;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.CommonData;

public class AppCreateContest extends Thread implements CommonData {
	// private AppCommon appCommon = new AppCommon();
	private WebElement webElement;	
	private WebDriverWait wait = new WebDriverWait(DRIVER, 30);
	
	public Boolean startMakeContest(Map<String, String> param) {
		// checkPopup();
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
			WebElement gameTypeElement = DRIVER.findElement(By.xpath("//*[@id='"+gameId+"']"));
			String checkActive = gameTypeElement.getAttribute("class");
			if (!checkActive.contains("active")) {
				gameTypeElement.click();
			}
			
			// Count of Games
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			List<WebElement> gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			int gameSize = gameElements.size();
			
			System.out.println("====== [CREATE] Today's Game Count = "+gameSize);
			for (int i = 0; i < gameSize; i++) {
				Boolean backFlag = true;
				System.out.println("====== [CREATE] [Game Number "+(i+1)+"] ======");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
				gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
				String eachGameId = gameElements.get(i).getAttribute("id");
				String checkJoined = DRIVER.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[1]/span[1]")).getAttribute("style");
				System.out.println("CREATE CHECK JOINED = "+checkJoined);
				if (checkJoined.isEmpty()) {
					webElement = DRIVER.findElement(By.id(eachGameId));
					int eachContentSize = Integer.parseInt(DRIVER.findElement(By.xpath("//*[@id='"+eachGameId+"']/div[2]/div[2]/p[2]/span")).getText());
					System.out.println("====== [CREATE] [Contests Size = "+eachContentSize+"] ======");
					if (eachContentSize > 60) {
						backFlag = false;
						System.out.println("====== [CREATE] [Over 60 Contests] =====");
						Thread.sleep(2000);
					} else {
						webElement.click(); // Click one game
			
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#container div.content div div.contest-content.new-contest .contest")));
						List<WebElement> contestElements = DRIVER.findElements(By.cssSelector("#container div.content div div.contest-content.new-contest .contest"));
						int contestSize = contestElements.size();
						
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a")));
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a")));
						
						// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("layer-bg loading-bg")));
						WebElement createBtnElement = DRIVER.findElement(By.xpath("//*[@id='container']/div[2]/div/div[2]/div[1]/a"));
						Thread.sleep(5000);
						createBtnElement.click(); // Click 'Create Contest' button
						WebElement currencyBtn = null;
						if ("gdc".equals(currency)) {
							currencyBtn = DRIVER.findElement(By.xpath("//*[@id='assetType']/li[1]"));
						} else { // 'point'
							currencyBtn = DRIVER.findElement(By.xpath("//*[@id='assetType']/li[2]"));
						}
						currencyBtn.click();
						List<WebElement> typeArr = DRIVER.findElements(By.xpath("//*[@id='contestType']/li"));
						int typeSize = typeArr.size();
						for (int j = 0; j < typeSize; j++) {
							typeArr = DRIVER.findElements(By.xpath("//*[@id='contestType']/li"));
							int childCnt = j+1;
							WebElement type = typeArr.get(j);
							String text = type.getText();
							if (!"Head-to-Head".equals(text)) {
								for (int k = 0; k < count; k ++) {
									if (eachContentSize < 61) {
										Thread.sleep(2500);
										wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='contestType']/li["+childCnt+"]")));
										WebElement eachType = DRIVER.findElement(By.xpath("//*[@id='contestType']/li["+childCnt+"]"));
										eachType.click();
										setCreateOptions(entryFee, entries, entryFeeIdx, entriesIdx);
										System.out.println("====== [CREATE] [Create "+(k+1)+"] "+text+" [Fee] "+entryFee+" "+currency+" [Entry] "+entries+"");
										System.out.println("====== [CREATE] [contestSize] "+eachContentSize+" =====");
										eachContentSize =  eachContentSize + 1;
									}
								}
								System.out.println("====== [CREATE] [Create "+count+"] "+text+" Complete");
							} else {
								break;
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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMsg = e.getMessage();
			System.out.println("Auto Create Exception Message = "+exceptionMsg);
		}
		return result;
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
			
			submitProcess();
		} catch (Exception e) {
			submitProcess();
			String exceptionMsg = e.getMessage();
			System.out.println("[setCreateOptions] Exception Message = "+exceptionMsg);
		}
	}
	
	private void submitProcess() {
		try {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='load']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
			WebElement loadButtonElement = DRIVER.findElement(By.xpath("//*[@id='load']"));
			Thread.sleep(800);
			String checkEntries = loadButtonElement.getAttribute("class");
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
				Thread.sleep(800);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-layer #submit")));
				Thread.sleep(800);
				WebElement secondOkButton = DRIVER.findElement(By.cssSelector(".message-layer #submit"));
				Thread.sleep(800);
				secondOkButton.click();
				Thread.sleep(1300);
				DRIVER.navigate().back();
			} else {
				System.out.println("====== [SUBMIT] Entries Not Exist !!!!!!!! ======");
				DRIVER.navigate().back();
				// break;
			}
			loadButtonElement = null;
			checkEntries = null;
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMsg = e.getMessage();
			System.out.println("[submitProcess] Exception Message = "+exceptionMsg);
		}
	}
}
