package SandboxProjects;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validations {
    public boolean isDateFormatValid(String dateToValidate) {
        String dateFormat = "dd/MMM/yy";
        String parseDateExceptionError = "Incorrect format, please use dd/MMM/yy, ex. 12/Oct/09";

        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {
            sdf.parse(dateToValidate);
        } catch (ParseException e) {
            System.out.println(parseDateExceptionError);
            return false;
        }
        return true;
    }

   /* public static void main(String[] args) {
        System.out.println("How many hours are you logging? Ex. 5h 30m");
        Scanner hourScan = new Scanner(System.in);
        String text = hourScan.nextLine();

        boolean matches = isHourFormatValid(text);

        if (matches) {
            System.out.println("Correct format!");
        } else System.out.println("Incorrect format!");
    }*/

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
        } else {
            System.out.println("Incorrect format, please use either Hh or Hh MMm format!");
            return false;
        }
    }
}

