package Pages;

import java.awt.Robot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendsPage {
	
	WebDriver driver;

	public FriendsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="uiHeaderTitle")
	WebElement theEnd;
	
	
	public void scrollUntillTheEnd(Robot tobot, WebDriverWait wait) {
		 //сделать метод для робота тобота, чтобы он листал страницу
	}
	
	public boolean isTheEndVisible() {
		return theEnd.isDisplayed();
	}
	
	

}
