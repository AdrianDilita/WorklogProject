import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        System.out.println("How many hours are you logging? Ex. 5h 30m");
        Scanner hourScan = new Scanner(System.in);
        String text = hourScan.nextLine();

        boolean matches = isHourFormatCorrect(text);

        if (matches) {
                System.out.println("Correct format!");
        } else System.out.println("Incorrect format!");
    }

    private static boolean isHourFormatCorrect(String text) {
        String patternHourString = "\\d" + "h";
        String patternHourMinuteString = "\\d" + "h " + "\\d+" + "m";

        Pattern pattern = Pattern.compile(patternHourMinuteString);
        Pattern pattern2 = Pattern.compile(patternHourString);

        Matcher matcher = pattern.matcher(text);
        Matcher matcher2 = pattern2.matcher(text);

        boolean matches1 = matcher.matches();
        boolean matches2 = matcher2.matches();
        return matches1 || matches2;
    }
}