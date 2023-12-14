package com.Selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Keka {

	List<String> storingList = new ArrayList<String>();
	WebDriver driver;
	String sheet = "sheet1";
	private static final Logger logger = Logger.getLogger(Keka.class.getName());

	@BeforeMethod
	public void setUp() {
		System.out.println("in the Before method");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		String url = "https://app.keka.com/Account/Login?ReturnUrl=%2F";
		driver.get(url);
		driver.manage().window().maximize();

	}

	@Test
	public void exp() throws InterruptedException {
		String email = "kkandikuppa@msystechnologies.com";
		String password = "Kamesh@123";
		String note = "Not Feeling well";
		String notify = "Deepak Kumar R";
		String name1 = "Ajin Alex";
		String name2 = "Deepak Kumar R";
		System.out.println("in the method");
		System.out.println(email + password + note + notify + name1 + name2);
		System.out.println("in the method");

		storingList.add("first line");

		logger.info("Testing started");
		storingList.add("Scenario:  1  -->  login to keka");
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.xpath("//p[text()='Google']")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(password);
		driver.findElement(By.id("passwordNext")).click();

		String profileName = driver.findElement(By.xpath("//span[@class='profile-name']")).getText();
		logger.info(" Successfuly logged int to the profile of " + profileName);
		String conformationString = "Successfuly logged int to the profile of " + profileName + "\n";
		storingList.add(conformationString);

		// up latest holiday

		logger.info("collecting holiday details");
		storingList.add("Scenario:  2  --> Collect holidays , Current Time , Leave balance ");

		String curHoliday = driver.findElement(By.xpath("//home-holidays-widget//h1")).getText();
		String curHolidayDate = driver.findElement(By.xpath("//home-holidays-widget//h1/following-sibling::*"))
				.getText();

		storingList.add(curHoliday + " , " + curHolidayDate);

		// remaining coming holidays

		driver.findElement(By.linkText("View All")).click();
		List<WebElement> holidayList = driver.findElements(By.xpath(
				"//modal-container/descendant::h4/parent::*/preceding-sibling::*/parent::*/following-sibling::*/*/p"));
		List<WebElement> dayList = driver.findElements(By.xpath(
				"//modal-container/descendant :: h4/parent :: */preceding-sibling::*/parent::*/following-sibling::*/*/following-sibling::*/p"));
		List<WebElement> monthList = driver
				.findElements(By.xpath("//modal-container/descendant :: h4/parent :: */preceding-sibling::*/p"));
		List<WebElement> dateList = driver.findElements(By.xpath("//modal-container/descendant :: h4"));

		storingList.add(" Upcoming holidays: ");

		for (int i = 0; i < dateList.size(); i++) {

			String holiday = holidayList.get(i).getText();

			String day = dayList.get(i).getText();
			String month = monthList.get(i).getText();
			String date = dateList.get(i).getText();

			storingList.add(month + " " + date + " " + day + " " + holiday);

		}

		driver.findElement(By.xpath("//modal-container/descendant::h5/parent::*/following-sibling::*")).click();

		// current date and time
		logger.info("Current time and date: ");
		String curDate = driver.findElement(By.xpath("//p[@title='Time Today']/following-sibling::span")).getText();
		String curTime = driver.findElement(By.xpath("//home-attendance-clockin-widget//h1")).getText();
		storingList.add("current date and time " + curDate + " " + curTime);

		storingList.add(" ");

		// leaves and balance
		logger.info("Fetching leave balance");
		List<WebElement> leaveList = driver
				.findElements(By.xpath("//fusioncharts/parent::*//parent::*//following-sibling::p"));
		List<WebElement> leaveBalList = driver.findElements(By.tagName("text"));

		for (int i = 0; i < leaveList.size(); i++) {

			String leaveString = leaveList.get(i).getText();
			String leaveBalString = leaveBalList.get(i).getText();
			storingList.add(leaveString + " --> " + leaveBalString);

		}

		// to scrolling

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500);");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));// 10 seconds timeout
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Request Leave']")));
		Thread.sleep(3000);

		// form for apply leave

		logger.info("Filling the leave form");
		driver.findElement(By.xpath("//a[text()='Request Leave']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='From']")));

		driver.findElement(By.xpath("//span[text()='From']")).click();
		driver.findElement(By.xpath("//span[text()='22']")).click();
		driver.findElement(By.xpath("//span[text()='23']")).click();

		driver.findElement(By.xpath("//*[text()='Select']/parent::*/parent::*")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Earned Leave']")));
		driver.findElement(By.xpath("//span[text()='Earned Leave']")).click();

		driver.findElement(By.id("note")).sendKeys(note);

		String empNameNotifyString = notify;
		driver.findElement(By.cssSelector("input[placeholder='Search Employee']")).sendKeys(empNameNotifyString);

		driver.findElement(By.cssSelector("span[title='" + empNameNotifyString + "']")).click();

//	    System.out.println("\n Form values ");
//	    System.out.println("==================== ");
//	    System.out.println("\nLeave From -->  "+ fromDateString+
//	    		            "\n To  -->  "+ToDateString+
//	    		            "\n Leave Type & Availability -->  "+leaveType+
//	    		            "\n Leave note -->  "+note+
//	    		            "\n Notifing to -->  "+notify+"\n");

		driver.findElement(By.xpath("//button[text()='Cancel']")).click();

//      login to kamesh profile

		logger.info("searching another profile");

		String scrNameString = name1;
		driver.findElement(By.cssSelector("input[typeaheadoptionfield='name']")).sendKeys(scrNameString);

		// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("span[title='"+scrNameString+"']")));
		driver.findElement(By.cssSelector("span[title='" + name1 + "']")).click();

		String empName = driver.findElement(By.xpath("//h2")).getText();
		String empJobTitle = driver.findElement(By.xpath("//label[text()='Job Title']/following-sibling::p")).getText();
		String empMail = driver
				.findElement(By.xpath("//employee-profile//h2/parent::*/parent::*/following-sibling::*//a")).getText();
		String empId = driver.findElement(By.xpath("//label[text()='Emp No']/following-sibling::p")).getText();

		storingList.add(empName);
		storingList.add(empJobTitle);
		storingList.add(empMail);
		storingList.add(empId);

//      login to deepak profile

		String scrNameString2 = name2;
		driver.findElement(By.cssSelector("input[typeaheadoptionfield='name']")).sendKeys(scrNameString2);
		driver.findElement(By.cssSelector("span[title='" + name2 + "']")).click();

		String empName2 = driver.findElement(By.xpath("//h2")).getText();
		String empJobTitle2 = driver.findElement(By.xpath("//label[text()='Job Title']/following-sibling::p"))
				.getText();
		String empMail2 = driver
				.findElement(By.xpath("//employee-profile//h2/parent::*/parent::*/following-sibling::*//a")).getText();
		String empId2 = driver.findElement(By.xpath("//label[text()='Emp No']/following-sibling::p")).getText();

		storingList.add(empName2);
		storingList.add(empJobTitle2);
		storingList.add(empMail2);
		storingList.add(empId2);

		String numbReportingString = driver
				.findElement(By.xpath("//h4[text()='Reporting Team']/following-sibling::span")).getText();

		storingList.add("number of members reporting: " + numbReportingString);

		driver.findElement(
				By.xpath("//h4[text()='Reporting Team']/parent::*/following-sibling::*/following-sibling::*")).click();

		List<WebElement> empNamesList = driver.findElements(By.xpath("//*[@class='employee-details']/*/p"));
		List<WebElement> empDecList = driver.findElements(By.xpath("//*[@class='employee-details']/p"));

		for (int i = 0; i < empNamesList.size(); i++) {

			String empNameString = empNamesList.get(i).getText();
			String empDecString = empDecList.get(i).getText();

			storingList.add(empNameString + " --> " + empDecString);
		}

	}

	@AfterMethod
	public void tearDown() throws Exception {
//		String filePathString = "D:\\SELENIUM\\Selenium Eclipse WorkSpace\\Selenium Training Projects\\ExcelFiles\\KekaDetailsStore6.xls";
//
//		ListToExel.writeExcelData(filePathString, sheet, storingList);
		for (String data : storingList) {
			System.out.print(data);
		}
		driver.close();
	}

}
