package service;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.CommonData;

public class AppLogin extends AppCommon implements CommonData  {
	
	private WebElement webElement;	
	private WebDriverWait wait;

	public Boolean loginProcess(String email, String password) {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		wait = new WebDriverWait(DRIVER, 30);
		Boolean result = false;
		AppCommon.getReadProperties();
		try {
			DRIVER.manage().window().maximize();
			if (BASE_URL == null) {
				
			} else {
				DRIVER.get(BASE_URL);
			}
	
			checkPopup();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login > p")));
			webElement = DRIVER.findElement(By.cssSelector(".login > p"));
			webElement.click();
	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/form[1]/div[1]/input")));
			webElement = DRIVER.findElement(By.xpath("/html/body/div/div/form[1]/div[1]/input"));
			webElement.sendKeys(email);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/form[1]/div[2]/input")));
			webElement = DRIVER.findElement(By.xpath("/html/body/div/div/form[1]/div[2]/input"));
			webElement.sendKeys(password);
			
			webElement = DRIVER.findElement(By.xpath("//*[@id='btnLogin']"));
			webElement.click();
			
			try {
				webElement = DRIVER.findElement(By.cssSelector("body > div > div > form:nth-child(6) > div:nth-child(4) > div"));
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
}
