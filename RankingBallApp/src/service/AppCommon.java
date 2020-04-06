package service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.CommonData;

public class AppCommon implements CommonData {
	private WebDriverWait wait;
	public static String BASE_URL;
	public static String USER_ID;
	public static String CREATE_COUNT;
	
	public static void getReadProperties() {
		try {
			FileReader resource = new FileReader("config.properties");
			Properties properties = new Properties();
			properties.load(resource);
			BASE_URL = properties.getProperty("URL");
			USER_ID = properties.getProperty("ID");
			CREATE_COUNT = properties.getProperty("CREATE");
			System.out.println("[ConfigFile - URL ] = "+BASE_URL);
			System.out.println("[ConfigFile - ID ] = "+USER_ID);
			System.out.println("[ConfigFile - CREATE ] = "+CREATE_COUNT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectSports(String sportsId) {
		try {
			Actions moveAction = new Actions(DRIVER);
			
			WebElement sportsBtn = null;
			WebDriverWait wait = new WebDriverWait(DRIVER, 5);
			int btnCheck = DRIVER.findElements(By.xpath("//*[@id='"+sportsId+"']")).size();
			if (btnCheck > 0) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='"+sportsId+"']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='"+sportsId+"']")));
				sportsBtn = DRIVER.findElement(By.xpath("//*[@id='"+sportsId+"']"));
				System.out.println("[Select Sports] = "+sportsId);
			} else {
				System.out.println("[Select RANKINGBALL Icon]");
				WebElement rankingballIconBtn = DRIVER.findElement(By.xpath("//*[@id='container']/div[1]/div/h1/a")); 
				moveAction.moveToElement(rankingballIconBtn);
				moveAction.perform();
				rankingballIconBtn.click(); // RANKINGBALL Icon Click on top
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='"+sportsId+"']")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='"+sportsId+"']")));
				sportsBtn = DRIVER.findElement(By.xpath("//*[@id='"+sportsId+"']"));
			}
			moveAction.moveToElement(sportsBtn);
			moveAction.perform();
			
			sportsBtn.click();
		} catch (Exception e) {
			String exceptionMsg = e.getMessage();
			System.out.println("[Select Sports] Exception Message = "+exceptionMsg);
		}
	}
	
	public String getGameId(String type) {
		String gameId = null;
		if ("lol".equals(type)) {
			gameId = "105001";
		} else if ("soccer".equals(type)) {
			gameId = "104001";
		} else if ("basketball".equals(type)) {
			gameId = "104004";
		} else if ("football".equals(type)) {
			gameId = "104003";
		} else if ("baseball".equals(type)){ // baseball
			gameId = "104002";
		}
		return gameId;
	}
	
	public void checkPopup() {
		try {
			Boolean result = false;
			WebDriverWait wait = new WebDriverWait(DRIVER, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnClose']")));
			result = DRIVER.findElement(By.xpath("//*[@id='btnClose']")).isDisplayed();
			System.out.println("CHECK POPUP = "+result);
			if (result) {
				DRIVER.findElement(By.xpath("//*[@id='btnClose']")).click();
			}
		} catch (Exception e) {
			String exceptionMsg = e.getMessage();
			System.out.println("[Check Event Popup] Exception Message = "+exceptionMsg);
		}
	}
	
	public void submitCommonProcess(String type) {
		try {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='load']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='load']")));
			WebElement loadButtonElement = DRIVER.findElement(By.xpath("//*[@id='load']"));
			Thread.sleep(800);
			String checkEntries = loadButtonElement.getAttribute("class");
			if ("entries on".equals(checkEntries)) {
				loadButtonElement.click(); // Click 'Load other entires'
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button")));
				WebElement selectEntryButton = DRIVER.findElement(By.xpath("//*[@id='wrab']/div[4]/div/div[2]/footer/button"));
				selectEntryButton.click(); // Click 'Select Entry'
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submit']")));
				WebElement submitButton = DRIVER.findElement(By.xpath("//*[@id='submit']"));
				submitButton.click(); // Click 'Submit'
				
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
				// DRIVER.navigate().back();
			} else {
				System.out.println("====== ["+type+"] Entries Not Exist !!!!!!!! ======");
			}
			loadButtonElement = null;
			checkEntries = null;
			DRIVER.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
			String exceptionMsg = e.getMessage();
			System.out.println("====== ["+type+"] SubmitProcess Exception Message = "+exceptionMsg);
		}
	}
	
	/*public ArrayList<Object> getTodaysMatch() {
		wait = new WebDriverWait(DRIVER, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
		List<WebElement> gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
		int gameSize = gameElements.size();
		ArrayList<Object> gameInfoArr = new ArrayList<Object>();
		Map<String, String> eachGameInfo = new HashMap<String, String>();
		 [
		 * 		{
		 * 			"title" : "WE (2 - 6) vs RW (4 - 4)",
		 * 			"time" : "06:00 PM"
		 * 			"contests" : 138,
		 * 			"game_id" : 34324,
		 * 		}
		 * ]
		 * 
		for (int i = 0; i < gameSize; i++) {
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div")));
			gameElements = DRIVER.findElements(By.xpath("//*[@id='container']/div[2]/div/div[3]/ul/li[2]/div"));
			String game_id = gameElements.get(i).getAttribute("id");
			String time = DRIVER.findElement(By.xpath("//*[@id='"+game_id+"']/div[1]/div[1]/p")).getText();
			String homeTeam = DRIVER.findElement(By.xpath("//*[@id='"+game_id+"']/div[2]/div[1]/div[1]/span[2]")).getText();
			String cnvtHomeTeam[] = homeTeam.split("\n");
			String awayTeam = DRIVER.findElement(By.xpath("//*[@id='"+game_id+"']/div[2]/div[1]/div[2]/span[2]")).getText();
			String cnvtAwayTeam[] = awayTeam.split("\n");
			String title = cnvtHomeTeam[0] + " vs " + cnvtAwayTeam[0];
			String contests = DRIVER.findElement(By.xpath("//*[@id='"+game_id+"']/div[2]/div[2]/p[2]/span")).getText();
			
			String result = title + " " + time + " " + contests + " Contests";
					
			eachGameInfo.put("title", title);
			eachGameInfo.put("time", time);
			eachGameInfo.put("contests", contests);
			eachGameInfo.put("game_id", game_id);
			eachGameInfo.put("result", result);
			gameInfoArr.add(eachGameInfo);
			eachGameInfo = new HashMap<String, String>();
		}
		
		return gameInfoArr;
	}*/
}
