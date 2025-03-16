package treeplanter;

import java.util.HashMap;

public class TreeGenerators {

    public static HashMap<String, TreeGenerator> treeGenerators = new HashMap<String, TreeGenerator>();
    static long treas;
    static long treesPlanted;

    TreeGenerators() {
        new TreeGenerator("student", "Student Volunteer", "I guess that free labor isn't free anymore `\\_(?_?)_/`", 5,
                1);
        new TreeGenerator("programmer", "Programmer",
                "\"I plant trees at O(log(n)) time)\" (This isn't true it's actually O(n), but no ones's smart enough to correct them)",
                10, 5);
        
    }

    public static void updateTreeValues() {
        for (TreeGenerator treeGenerator : treeGenerators.values()) {
            addTreas(treeGenerator.getTrees());
        }
    }

    public static void addTreas(long treas) {
        TreeGenerators.treas += treas;
        TreeGenerators.treesPlanted += treas;
    }

    public static void removeTreas(long treas) {
        TreeGenerators.treas -= treas;
    }

    public static int getTotalTreesGeneratedPerSecond() {
        int perSecValue = 0;
        for (TreeGenerator treeGenerator : treeGenerators.values()) {
            perSecValue += treeGenerator.getTreesGeneratedPerSecond();
        }

        return perSecValue;
    }
}
