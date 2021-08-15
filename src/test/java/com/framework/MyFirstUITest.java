package com.framework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MyFirstUITest {

    @Test

    void userNameIsCorrectOnOverviewTab()
    {
        //Arrange

        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        ChromeOptions options =new ChromeOptions().addArguments("start-fullscreen");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String user ="andrejs-ps";
        driver.get("https://github.com/" + user);

        //Act

        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert

        Assertions.assertEquals(user, actualUserName);
        System.out.println(user + " is matching with " + actualUserName);

        driver.close();
    }

    @Test
    void repoLinkGoesToCorrectRepo()
    {
        //Arrange
        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        ChromeOptions options =new ChromeOptions().addArguments("start-fullscreen");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String user ="andrejs-ps";
        driver.get("https://github.com/" + user);

        //Act
        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selenium";
        WebElement repoLink = driver.findElement(By.linkText(repo));

        repoLink.click();

        String actualURL = driver.getCurrentUrl();

        //Assert
        Assertions.assertEquals( "https://github.com/andrejs-ps/" + repo, actualURL);

        driver.close();
    }

    @Test
    void repositoryCountIsCorrect()
    {
        //Arrange
        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        ChromeOptions options =new ChromeOptions().addArguments("start-fullscreen");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Act
        driver.get("https://github.com/andrejs-ps?tab=repositories");
        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));

        //Assert
        Assertions.assertEquals(9, repos.size());
        driver.close();

    }

}
