package treeplanter;

import java.util.HashMap;

public class TerminalCommands {
    public static HashMap<String, Command> terminalCommands = new HashMap<>();

    public TerminalCommands() {
        new Help();
        new Status();
        new Buy();
    }

    public static class Help extends Command {
        @Override
        public void action(String[] args) {
            if (args[0].isEmpty()) {
                System.out.println("Commands:");
                for (Command command : terminalCommands.values()) {
                    System.out.println("\t" + command);
                }
            } else {
                Command command = terminalCommands.get(args[0]);
                if (command == null)
                    System.out.println("That's not a command");
                else
                    System.out.println(terminalCommands.get(args[0]));
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
        public void action(String[] args) {
            System.out.println("I do thingy " + args[0]);
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

    public static class Buy extends Command {

        @Override
        public void action(String[] args) {
            // Checking for valid parameters
            if (args.length != 2) {
                System.out.println("Error: you have the wrong number of arguments");
                return;
            }
            TreeGenerator treeGenerator = TreeGenerators.treeGenerators.get(args[0]);
            int amountBought;

            if (treeGenerator == null) {
                System.out.println("That's not something you can buy");
                return;
            }
            try {
                amountBought = Integer.parseInt(args[1]);
                if (amountBought < 1) {
                    System.out.println("You can't buy that much");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not a number");
                return;
            }
            int cost = treeGenerator.getNextNUnitsCost(amountBought);
            if (cost > TreeGenerators.treas) {
                System.out.println("You don't have enough treas");
                return;
            }

            treeGenerator.buy(amountBought);
            TreeGenerators.treas -= cost;
        }

        @Override
        public String getID() {
            return "buy";
        }

        @Override
        public String toString() {
            return "buy - buys ...";
        }
    }

}
