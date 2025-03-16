package treeplanter;

public class TreeGenerator {

    private String id;
    private String name;
    private long lastTime;
    private int unitsOwned = 0;
    private int baseCost;
    private double priceRateIncrease = 1.15;
    private double multiplier = 1;
    private int treesPerSecondPerUnit;

    public TreeGenerator(String id, String name, int baseCost, int treesPerSecondPerUnit) {
        lastTime = System.currentTimeMillis();
        this.id = id;
        this.name = name;
        this.baseCost = baseCost;
        this.treesPerSecondPerUnit = treesPerSecondPerUnit;
        TreeGenerators.treeGenerators.put(id, this);
    }

    public long getTrees() {
        long currentTime = System.currentTimeMillis();
        long seconds = (lastTime - currentTime) / 1000;
        lastTime = currentTime;

        return calculateTrees(seconds);
    }

    private long calculateTrees(long seconds) {
        return (long) (seconds * unitsOwned * treesPerSecondPerUnit * multiplier);
    }

    public void addMultiplier(double multiplier) {
        this.multiplier *= multiplier;
    }

    public int getNextNUnitsCost(int n) {
        n -= 1;
        if (n < 0) {
            throw new IllegalArgumentException("n < 1");
        } else if (n == 0) {
            return (int) (baseCost * Math.pow(1.15, n));
        }

        return (int) (baseCost * ((Math.pow(priceRateIncrease, n + 1) - 1) / (priceRateIncrease - 1)));
    }

    public void buy(int n) {
        unitsOwned += n;
    }

    @Override
    public String toString() {
        return UI.BOLD + name + UI.BOLDOFF + "     ID: " + id + "\n" +
                "   Trees Generated Per Second: " + (int) (calculateTrees(1)) + "\n" +
                "   Number: " + unitsOwned + "     Cost: " + getNextNUnitsCost(1) + " treas";
    }
}
