package SandboxProjects;

import java.util.Scanner;

public class UserInputs {
    public static String requestUserInputDate() {
        Scanner dateScan = new Scanner(System.in);
        String dateString = dateScan.nextLine();
        return dateString;
    }

    public static String requestUserInputHours() {
        Scanner hourScan = new Scanner(System.in);
        String hourString = hourScan.nextLine();
        return hourString;
    }
}
