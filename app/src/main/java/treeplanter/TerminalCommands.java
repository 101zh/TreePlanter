package treeplanter;

import java.util.HashMap;

public class TerminalCommands {
    public static HashMap<String, Command> terminalCommands = new HashMap<>();

    public TerminalCommands() {
        new Help();
        new Status();
    }

    public static class Help extends Command {
        @Override
        public void action(String[] str) {
            if (str[0].isEmpty()) {
                System.out.println("Commands:");
                for (Command command : terminalCommands.values()) {
                    System.out.println("\t" + command);
                }
            } else {
                Command command = terminalCommands.get(str[0]);
                if (command == null)
                    System.out.println("That's not a command");
                else
                    System.out.println(terminalCommands.get(str[0]));
            }
        }

        @Override
        public String getID() {
            return "help";
        }

        @Override
        public String toString() {
            return "help - shows ...";
        }
    }

    public static class Status extends Command {

        @Override
        public void action(String[] str) {
            System.out.println("I do thingy " + str[0]);
        }

        @Override
        public String getID() {
            return "status";
        }

        @Override
        public String toString() {
            return "status - shows ....";
        }
    }

}
