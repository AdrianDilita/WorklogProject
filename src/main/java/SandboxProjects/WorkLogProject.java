package SandboxProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;
import java.util.Scanner;

public class WorkLogProject {
    public static void main(String[] args) {
        String dateFormat = "dd/MMM/yy";
        boolean isItComplete = false;

        System.out.println("On what date are you logging time? Ex. 12/Oct/18");
        Scanner dateScan = new Scanner(System.in);

        while (!dateScan.toString().equals("")) {
            String dateToValidate = dateScan.nextLine();
            Validations dateValidator = new Validations();

            while ((dateValidator.isDateFormatValid(dateToValidate, dateFormat)) && (!isItComplete)) {
                System.out.println("How many hours are you logging? Ex. 5h 30m");
                Scanner hourScan = new Scanner(System.in);

                String hourToValidate = hourScan.nextLine();
                Validations hourValidator = new Validations();

                while ((hourValidator.isHourFormatValid(hourToValidate)) && (!isItComplete)) {
                    FirefoxDriver driver = new FirefoxDriver();
                    WebDriverWait wait5 = new WebDriverWait(driver, 5);
                    WebDriverWait wait20 = new WebDriverWait(driver, 20);

                    driver.get("https://minditsoftware.atlassian.net/browse/DA-3412"); //TODO add user defined JIRA ID to add worklog to
                    WebElement username = driver.findElement(By.xpath("//*[@id='username']"));
                    username.click();
                    username.sendKeys("adrian.dilita@mindit.io");
                    username.sendKeys(Keys.RETURN);

                    wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']")));
                    WebElement password = driver.findElement(By.xpath(("//*[@id='password']")));
                    password.click();
                    password.sendKeys("Indifferent24");
                    password.sendKeys(Keys.RETURN);

                    wait20.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"log-work-link\"]")));
                    driver.findElement(By.xpath("//*[@id=\"log-work-link\"]")).sendKeys(Keys.RETURN);

                    wait20.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\'log-work-date-logged-date-picker\']"))));
                    String dateTime = driver.findElement(By.xpath("//*[@id=\'log-work-date-logged-date-picker\']")).getAttribute("value");
                    String dateValue = dateTime.substring(0, 9);

                    if (dateValue.equals(dateToValidate)) {
                        WebElement timeSpent = driver.findElement(By.xpath("//*[@id='log-work-time-logged']"));
                        timeSpent.click();
                        timeSpent.sendKeys(hourToValidate);
                        timeSpent.sendKeys(Keys.RETURN);
                        System.out.println("Work log successfully added!");
                        isItComplete = true;
                    } else {
                        WebElement dateStarted = driver.findElement(By.xpath("//*[@id='log-work-date-logged-date-picker']"));
                        dateStarted.click();
                        dateStarted.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                        dateStarted.sendKeys(Keys.BACK_SPACE);
                        dateStarted.sendKeys(dateToValidate + " 1:00 PM");
                        WebElement timeSpent = driver.findElement(By.xpath("//*[@id='log-work-time-logged']"));
                        timeSpent.click();
                        timeSpent.sendKeys(hourToValidate);
                        timeSpent.sendKeys(Keys.RETURN);
                        System.out.println("Work log successfully added for different day!");
                        isItComplete = true;
                    }
                    System.out.println("Runnable will now close");
                    System.exit(0);
                }
            }
            dateScan = new Scanner(System.in);
        }
    }
}
