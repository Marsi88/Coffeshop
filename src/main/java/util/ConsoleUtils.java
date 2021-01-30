package util;

import static util.Colors.ANSI_RESET;
import static util.Colors.ANSI_YELLOW;

public class ConsoleUtils {
    public static void printYellowText(String text) {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }
}
