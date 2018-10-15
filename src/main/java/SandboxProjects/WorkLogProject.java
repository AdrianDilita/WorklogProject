package SandboxProjects;

import org.openqa.selenium.firefox.FirefoxDriver;

public class WorkLogProject {
    public static void main(String[] args) {
        boolean isItComplete = false;

        System.out.println(NotificationMessages.DATE_CONFIRMATION_MESSAGE);
        String dateToValidate = UserInputs.requestUserInputDate();

        while (!dateToValidate.equals("")) {
            Validations dateValidator = new Validations();

            while ((dateValidator.isDateFormatValid(dateToValidate)) && (!isItComplete)) {
                System.out.println(NotificationMessages.HOUR_CONFIRMATION_MESSAGE);
                String hourToValidate = UserInputs.requestUserInputHours();

                while (!hourToValidate.equals("")) {
                    Validations hourValidator = new Validations();

                    if (hourValidator.isHourFormatValid(hourToValidate)) {
                        FirefoxDriver driver = new FirefoxDriver();
                        BrowserActions.userLogin(driver);
                        Validations.jiraDateCheck(driver);

                        if (Validations.jiraDateCheck(driver).equals(dateToValidate)) {
                            BrowserActions.logWork(hourToValidate, driver);
                            System.out.println(NotificationMessages.WORK_LOG_SUCCESSFUL_MESSAGE);
                            isItComplete = true;
                        } else {
                            BrowserActions.logWorkDiffDay(hourToValidate, driver, dateToValidate);
                            System.out.println(NotificationMessages.WORK_LOG_DIFF_DAY_SUCCESSFUL_MESSAGE);
                            isItComplete = true;
                        }
                        System.out.println(NotificationMessages.CLOSING_NOTIFICATION_MESSAGE);
                        System.exit(0);
                    }
                    hourToValidate = UserInputs.requestUserInputHours();
                }
            }
            dateToValidate = UserInputs.requestUserInputDate();
        }
    }
}