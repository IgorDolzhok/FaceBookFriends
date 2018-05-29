package Common;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.FriendsPage;
import Pages.StartPage;

public class Main {

	public static void main(String[] args) throws AWTException, InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
		 WebDriverWait wait = new WebDriverWait(driver, 50, 1000);
		 Robot tobot= new Robot();
		 StartPage startPage = new StartPage(driver);
		 FriendsPage friendsPage = new FriendsPage(driver);
		 String url = "https://www.facebook.com/xxxxxx/friends";
		 String login ="xxxxxxxx@gmail.com";
		 String password = "xxxxxxxxx";
		 
		 startPage.startPage(url, wait);
		 startPage.authorization(login, password, wait);
		 System.out.println(friendsPage.isTheEndVisible());
		

	}

}
