package SandboxProjects;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validations {
    public boolean isDateFormatValid(String dateToValidate) {
        String dateFormat = "dd/MMM/yy";

        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            sdf.parse(dateToValidate);
        } catch (ParseException e) {
            System.out.println(NotificationMessages.INCORRECT_DATE_FORMAT_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean isHourFormatValid(String hourToValidate) {
        String patternHourString = "\\d+" + "h";
        String patternHourMinuteString = "\\d+" + "h " + "\\d+" + "m";

        Pattern pattern = Pattern.compile(patternHourMinuteString);
        Pattern pattern2 = Pattern.compile(patternHourString);

        Matcher matcher = pattern.matcher(hourToValidate);
        Matcher matcher2 = pattern2.matcher(hourToValidate);

        boolean matches1 = matcher.matches();
        boolean matches2 = matcher2.matches();

        if (matches1 || matches2) {
            return matches1 || matches2;
        } else
            System.out.println(NotificationMessages.INCORRECT_HOUR_FORMAT_MESSAGE);
            return false;
    }

    public static String jiraDateCheck(FirefoxDriver driver) {
        WebDriverWait wait20 = new WebDriverWait(driver, 20);

        wait20.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\'log-work-date-logged-date-picker\']"))));
        String dateTime = driver.findElement(By.xpath("//*[@id=\'log-work-date-logged-date-picker\']")).getAttribute("value");
        String dateValue = dateTime.substring(0, 9);
        return dateValue;
    }
}

