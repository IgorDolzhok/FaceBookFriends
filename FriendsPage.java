package Pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendsPage {
	
	WebDriver driver;

	public FriendsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="uiHeaderTitle")
	WebElement theEnd;
	
	@FindBy(className="_3d0")
	WebElement quantityOfFriends;
	
	@FindBy(id="tab_load_indicator")
	WebElement loader;
	 
	/**
	 * не работает
	 * @return
	 */
	public boolean isTheEndVisible() {
		WebElement friendsButton = driver.findElement(By.cssSelector("#pagelet_timeline_medley_friends"));
		return  friendsButton.isDisplayed();
	}	
	
	/**
	 * работает
	 */
	public void scrollingJs() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.manage().window().maximize();
		js.executeScript("window.scrollBy(0, 1000)");
	}
	
	public void getBlockContents() {
		List<WebElement> blockContents =  driver.findElements(By.className("uiProfileBlockContent"));
		System.out.println(blockContents.size());
		//String html = blockContents.get(1).getAttribute("")
		for(WebElement e: blockContents) {
			String html = e.getAttribute("innerHTML");
			Document doc = Jsoup.parse(html);
			String name = doc.select("a[data-hovercard-prefer-more-content-show='1']").text();
			System.out.println(name);			
		}
		
	}
	 
	public void scrollingJs20times(WebDriverWait wait) throws InterruptedException {
		List<WebElement> lists = getLists();
		List<WebElement> items = getItemsFromList(lists);
		System.out.println("На входе получено "+items.size()+" друзей из "+lists.size()+" списков");
		Iterator it;
		
		for(int i=0; i<40; i++) { 
			int x = getLists().size();
			scrollingJs();
			wait.until(ExpectedConditions.invisibilityOf(loader));
			System.out.println("На "+(i+1)+" итерации ");
			int c=0;	
			if(getLists().size()>x) {
				 List<WebElement> tempLists = new ArrayList();
				 List<WebElement> tempItems = new ArrayList();
				 it=getLists().listIterator(x);
				 while(it.hasNext()) {
					tempLists.add((WebElement)it.next());
					c++;					  
				 }
			     tempItems = getItemsFromList(tempLists);	
			     for(WebElement item: tempItems) {
			    	 items.add(item);
			     }
			System.out.println("На "+i+" итерации добавлено "+c+" элементов с "+x+" позиции");
			};			
		}
		System.out.println("На выходе получено "+items.size()+" друзей");
	}
	
	
	
	public List<WebElement> getLists() {
		WebElement collectionFriends = driver.findElement(By.id("collection_wrapper_2356318349"));
		List<WebElement> friends = collectionFriends.findElements(By.cssSelector("ul[class*='uiList']"));
		//System.out.println("Получено "+friends.size()+" списков друзей");
		return friends;
	}
	
	public List<WebElement> getItemsFromList(List<WebElement> friends){
		List<WebElement> friends1=new ArrayList();
		for(WebElement e:friends) {
			friends1.addAll(e.findElements(By.tagName("li")));            
		}
		/*friends.get(0).findElements(By.tagName("li"));
		System.out.println("Получено "+friends1.size()+" друзей");*/
		return friends1;
	}
	
	public void getName(List<WebElement> items) {
		for(WebElement e: items) {
			String html = e.findElement(By.className("uiProfileBlockContent")).getAttribute("innerHTML");
			Document doc = Jsoup.parse(html);
			String name = doc.select("a[data-hovercard-prefer-more-content-show='1']").text();
			System.out.println(name);
		}
		/*WebElement profileContent=items.get(6).findElement(By.className("uiProfileBlockContent"));
		String html = profileContent.getAttribute("innerHTML");
		Document doc = Jsoup.parse(html);
		String name = doc.select("a[data-hovercard-prefer-more-content-show='1']").text();
		System.out.println(name);	
		return name;*/
	}
	
	

}
