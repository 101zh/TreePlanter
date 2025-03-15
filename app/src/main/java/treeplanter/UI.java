package treeplanter;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import static treeplanter.TerminalCommands.terminalCommands;

public class UI {

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);

        // typeOutMessageln("After finishing one of your projects you...");
        // clearAfterUserInput(scanner);

        while (true) {
            invokeCommand(scanner.nextLine());
        }
    }

    private static void typeOutMessageln(String messsage) {
        typeOutMessage("After finishing one of your projects you", TimeUnit.MILLISECONDS, 50);
        System.out.println();
    }

    private static void invokeCommand(String str) {
        str = str.toLowerCase().trim().replaceAll("\\s+", " ") + " ";
        int index = str.indexOf(" ");
        terminalCommands.get(str.substring(0, index)).action(str.substring(index + 1).split(" "));
    }

    private static void typeOutMessage(String message, TimeUnit unit, long delay) {
        char[] charArray = message.toCharArray();

        try {
            for (int i = 0; i < charArray.length; i++) {
                int iMod = i % 2;
                if (iMod == 1)
                    System.out.print("\b");
                System.out.print(charArray[i]);
                if (iMod == 0)
                    System.out.print("_");
                unit.sleep(delay);
            }
            if (message.length() % 2 == 1) {
                System.out.print("\b");
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    private static void clearAfterUserInput(Scanner scanner) {
        System.out.println("(ENTER)");
        scanner.nextLine();
        clearConsole();
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
            ex.fillInStackTrace();
        }
    }

}
