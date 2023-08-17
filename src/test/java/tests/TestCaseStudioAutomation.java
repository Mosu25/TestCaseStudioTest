package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class TestCaseStudioAutomation {

    @Test
    public void bookCartApplication() {

        ChromeOptions co = new ChromeOptions();
        co.addExtensions(new File("C:/Users/Arasu/Downloads/Unconfirmed 237337.crdownload")); // download crx file
        WebDriver driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("chrome-extension://loopjjegnlccnhgfehekecpanpmielcj/testCaseStudio/studioWindow.html");
        String testStudioWID = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://bookcart.azurewebsites.net");
        driver.findElement(By.xpath("//span[.='Login']")).click();
        driver.findElement(By.cssSelector("input[data-placeholder='Username']")).sendKeys("Mosu2023");
        driver.findElement(By.cssSelector("input[data-placeholder='Password']")).sendKeys("Welcome@2023");
        driver.findElement(By.xpath("//button[@color='primary']//span[1]")).click();
        WebElement element = driver.findElement(By.xpath("//button[contains(@class,'mat-focus-indicator mat-menu-trigger')]//span[1]"));
        String userID = element.getText().trim();
        String userIDText = userID.split(" ")[1];
        System.out.println("userID: " + userIDText);
        Assert.assertEquals(userIDText,"Mosu2023");
        driver.close();

        driver.switchTo().window(testStudioWID);
        driver.findElement(By.cssSelector("input[placeholder='Set the TestCase Name here']")).sendKeys("BookCartLogin");
        driver.findElement(By.id("save_btn")).click();
//        driver.quit();

    }
}
