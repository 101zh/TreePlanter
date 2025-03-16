package treeplanter;

import java.util.HashMap;

public class TreeGenerators {

    public static HashMap<String, TreeGenerator> treeGenerators = new HashMap<String, TreeGenerator>();
    static long treas;
    static long treesPlanted;

    TreeGenerators() {
        new TreeGenerator("volunteer", "Student Volunteer", 5, 5);
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
