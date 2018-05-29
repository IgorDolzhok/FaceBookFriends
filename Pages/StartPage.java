package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {
	
	WebDriver driver;
	

	public StartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	WebElement loginInput;
	
	@FindBy(id="pass")
	WebElement passwordInput;
	
	@FindBy(id="u_0_2")
	WebElement submitButton;
	
	public void startPage(String url, WebDriverWait wait) {
		driver.get(url);
		wait.until(ExpectedConditions.visibilityOf(loginInput));		 
	}
	
	public void authorization(String login, String password, WebDriverWait wait){
		loginInput.sendKeys(login);
		passwordInput.sendKeys(password);
		submitButton.click();
		driver.navigate().back();
	}

}
