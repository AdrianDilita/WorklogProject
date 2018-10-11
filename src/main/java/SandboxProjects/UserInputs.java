package SandboxProjects;

import java.util.Scanner;

public class UserInputs {
    public static String requestUserInputDate() {
        Scanner dateScan = new Scanner(System.in);
        return dateScan.nextLine();
    }
}
