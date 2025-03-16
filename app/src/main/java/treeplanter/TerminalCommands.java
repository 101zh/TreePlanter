package treeplanter;

import java.util.HashMap;

public class TerminalCommands {
    public static HashMap<String, Command> terminalCommands = new HashMap<>();

    public TerminalCommands() {
        new Help();
        new Status();
        new Plant();
        new Buy();
        new Shop();
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
            System.out.println("Treas Owned: " + TreeGenerators.treas + "      Trees Planted: "
                    + TreeGenerators.treesPlanted + "\n" +
                    "Total Trees Planted Per Second: " + TreeGenerators.getTotalTreesGeneratedPerSecond());
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

    public static class Plant extends Command {
        @Override
        public void action(String[] args) {
            TreeGenerators.addTreas(1);
        }

        @Override
        public String getID() {
            return "plant";
        }

        @Override
        public String toString() {
            return "plant - plants ...";
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
            TreeGenerators.removeTreas(cost);
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

    public static class Shop extends Command {
        @Override
        public void action(String[] args) {
            for (TreeGenerator treeGenerator : TreeGenerators.treeGenerators.values()) {
                System.out.println(treeGenerator + "\n");
            }
        }

        @Override
        public String getID() {
            return "shop";
        }

        @Override
        public String toString() {
            return "shop - displays the shop";
        }
    }
}
