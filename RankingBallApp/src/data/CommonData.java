package data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public interface CommonData {
	final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	final String WEB_DRIVER_PATH = "C:/chromedriver/chromedriver.exe";
	final String BASE_URL = "https://play.rankingball.com";
	final WebDriver DRIVER = new ChromeDriver();
}
