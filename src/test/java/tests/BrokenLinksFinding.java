package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksFinding {
    @Test
    public void findBrokenLinks() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com");
        List<WebElement> urls = driver.findElements(By.tagName("a"));
        int size = urls.size();
        System.out.println("Total Links " + size);
        ArrayList<String> urlList = new ArrayList<>();

        for (WebElement url : urls) {
            String urlData = url.getAttribute("href");
            urlList.add(urlData);
        }

        urlList.parallelStream().forEach(this::brokenLinks);

    }

    public void brokenLinks(String urls) {

        try {
            URL url = new URL(urls);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setConnectTimeout(5000);
            httpUrlConnection.connect();

            if (httpUrlConnection.getResponseCode() >= 400) {
                System.out.println(urls + "-->" + httpUrlConnection.getResponseMessage() + "is broken links");
            } else {
                System.out.println(urls + "-->" + httpUrlConnection.getResponseMessage());
            }

        } catch (IOException e) {

        }

    }
}
