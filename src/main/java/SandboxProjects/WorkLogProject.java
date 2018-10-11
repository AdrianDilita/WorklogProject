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
        System.out.println("On what date are you logging time? Ex. 12/Oct/18");
        Scanner dateScan = new Scanner(System.in);

        while (!dateScan.toString().equals("")) {
            String dateToValidate = dateScan.nextLine();
            String dateFormat = "dd/MMM/yy";
            Validations dateValidator = new Validations();

            while (dateValidator.isDateFormatValid(dateToValidate, dateFormat)) {
                System.out.println("How many hours are you logging? Ex. 5h 30m");
                Scanner hourScan = new Scanner(System.in);

                String hourToValidate = hourScan.nextLine();
                Validations hourValidator = new Validations();

                while (hourValidator.isHourFormatValid(hourToValidate)) {
                    FirefoxDriver driver = new FirefoxDriver();
                    driver.get("https://minditsoftware.atlassian.net/browse/DA-3412"); //TODO add user defined Jira ID to add worklog to
                    WebElement username = driver.findElement(By.xpath("//*[@id='username']"));
                    username.click();
                    username.sendKeys("adrian.dilita@mindit.io");
                    username.sendKeys(Keys.RETURN);
                    WebDriverWait wait5 = new WebDriverWait(driver, 5);

                    WebElement password = wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']")));
                    password.click();
                    password.sendKeys("Indifferent24");
                    password.sendKeys(Keys.RETURN);

                    WebDriverWait wait20 = new WebDriverWait(driver, 20);

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

                        System.out.println("Worklog successfully added!");
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

                        System.out.println("Worklog successfully added for different day!");
                    }
                }
            }
            dateScan = new Scanner(System.in);
        }
    }
}