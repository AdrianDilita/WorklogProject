package SandboxProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;

public class BrowserActions {
    public static void userLogin(FirefoxDriver driver) {
        WebDriverWait wait5 = new WebDriverWait(driver, 5);
        WebDriverWait wait20 = new WebDriverWait(driver, 20);

        driver.get(UserInfo.jiraInfo()); //TODO add user defined JIRA ID to add worklog to
        WebElement username = driver.findElement(By.xpath("//*[@id='username']"));
        username.click();
        username.sendKeys(UserInfo.usernameInfo());
        username.sendKeys(Keys.RETURN);

        wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']")));
        WebElement password = driver.findElement(By.xpath(("//*[@id='password']")));
        password.click();
        password.sendKeys(UserInfo.passwordInfo());
        password.sendKeys(Keys.RETURN);

        wait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"log-work-link\"]")));
        driver.findElement(By.xpath("//*[@id=\"log-work-link\"]")).sendKeys(Keys.RETURN);
    }

    public static void logWork(String hourToValidate, FirefoxDriver driver) {
        WebElement timeSpent = driver.findElement(By.xpath("//*[@id='log-work-time-logged']"));
        timeSpent.click();
        timeSpent.sendKeys(hourToValidate);
        timeSpent.sendKeys(Keys.RETURN);
    }

    public static void logWorkDiffDay(String hourToValidate, FirefoxDriver driver, String dateToValidate) {
        WebElement dateStarted = driver.findElement(By.xpath("//*[@id='log-work-date-logged-date-picker']"));
        dateStarted.click();
        dateStarted.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateStarted.sendKeys(Keys.BACK_SPACE);
        dateStarted.sendKeys(dateToValidate + " 1:00 PM");
        WebElement timeSpent = driver.findElement(By.xpath("//*[@id='log-work-time-logged']"));
        timeSpent.click();
        timeSpent.sendKeys(hourToValidate);
        timeSpent.sendKeys(Keys.RETURN);
    }
}
