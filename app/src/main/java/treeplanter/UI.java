package treeplanter;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import static treeplanter.TerminalCommands.terminalCommands;

public class UI {

    public static final String BOLD = "\033[0;1m";
    public static final String BOLDOFF = "\033[0;0m";

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);

        introduction(scanner);

        while (true) {
            invokeCommand(scanner.nextLine());
        }

        // scanner.close();
    }

    private static void introduction(Scanner scanner) {
        typeOutMessageln("After finishing one of your projects you decide it's time to go outside");
        clearAfterUserInput(scanner);
        String a = "                                    .......   ....." + "\n" +
                "                                     `$$$$$..*******." + "\n" +
                "                                      ````$$$*****$$$$." + "\n" +
                "                                    .%%%%%%&&***&%%%%***." + "\n" +
                "                                      **%&#####&%***." + "\n" +
                "                  you -> ^_^           `   @@@" + "\n" +
                "                      _____________________@@@_______" + "\n" +
                "                     /                           ___/" + "\n" +
                "                ____/                     ______/" + "\n" +
                "            ___/                        _|" + "\n" +
                "           /                           /" + "\n" +
                "         _/                            \\\\_" + "\n" +
                "        /                                 \\";

        typeOutMessage(a, TimeUnit.MILLISECONDS, 2);
        System.out.println();
        typeOutMessageln("You climb and scale the closest mountain to you, finding a cherry blossom tree atop it");
        clearAfterUserInput(scanner);

        typeOutMessageln("Hello my child, I see great potential within you");
    }

    private static void typeOutMessageln(String messsage) {
        typeOutMessage(messsage, TimeUnit.MILLISECONDS, 50);
        System.out.println();
    }

    private static void invokeCommand(String str) {
        TreeGenerators.updateTreeValues();
        str = str.toLowerCase().trim().replaceAll("\\s+", " ") + " ";
        int index = str.indexOf(" ");
        try {
            terminalCommands.get(str.substring(0, index)).action(str.substring(index + 1).split(" "));
        } catch (NullPointerException e) {
            System.out.println("That's not a valid command");
        }
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
                System.out.print("\b "); // There's a space here otherwise \b won't work ¯\_(ツ)_/¯
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
