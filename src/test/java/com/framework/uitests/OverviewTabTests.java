package com.framework.uitests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverviewTabTests extends BaseTestClass {

    String user ="andrejs-ps";

    @BeforeEach
    void overviewTabSetup()
    {
        //Arrange
        driver.get(BASE_URL + user);
    }

    @AfterEach
    void localCleanup()
    {
        //Can be added if anything in the future.
    }

    @Test
    void userNameIsCorrectOnOverviewTab()
    {
        //Act
        String actualUserName = driver.findElement(By.className("p-nickname")).getText();
        //Assert
        assertEquals(user, actualUserName);
        System.out.println(user + " is matching with " + actualUserName);
    }

    @Test
    void repoLinkGoesToCorrectRepo()
    {
        //Act
        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selenium";
        WebElement repoLink = driver.findElement(By.linkText(repo));
        repoLink.click();
        String actualURL = driver.getCurrentUrl();
        //Assert
        assertEquals( "https://github.com/andrejs-ps/" + repo, actualURL);
    }
}