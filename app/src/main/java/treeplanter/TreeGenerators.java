package treeplanter;

import java.util.HashMap;

public class TreeGenerators {

    public static HashMap<String, TreeGenerator> treeGenerators = new HashMap<String, TreeGenerator>();
    public static long treas;

    TreeGenerators() {
        new TreeGenerator("volunteer", "Student Volunteer", 100, 5);
    }

    public void updateTreeValues() {
        for (TreeGenerator treeGenerator : treeGenerators.values()) {
            treas += treeGenerator.getTrees();
        }
    }
}
