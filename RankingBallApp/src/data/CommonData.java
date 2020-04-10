package data;

import org.openqa.selenium.JavascriptExecutor;
// import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public interface CommonData {
	// static final Logger log = Logger.getRootLogger();
	final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	final String WEB_DRIVER_PATH = "C:/chromedriver/chromedriver.exe";
	// final String BASE_URL = "https://play.rankingball.net";
	final WebDriver DRIVER = new ChromeDriver();
	final JavascriptExecutor js = (JavascriptExecutor) DRIVER;
}
