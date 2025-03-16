package treeplanter;

public class TreeGenerator {

    private String id;
    private String name;
    private String descrip;
    private long lastTime;
    private int unitsOwned = 0;
    private int baseCost;
    private double priceRateIncrease = 1.15;
    private double multiplier = 1;
    private int treesPerSecondPerUnit;

    public TreeGenerator(String id, String name, String description, int baseCost, int treesPerSecondPerUnit) {
        lastTime = System.currentTimeMillis();
        this.id = id;
        this.name = name;
        this.descrip = description;
        this.baseCost = baseCost;
        this.treesPerSecondPerUnit = treesPerSecondPerUnit;
        TreeGenerators.treeGenerators.put(id, this);
    }

    public long getTrees() {
        long currentTime = System.currentTimeMillis();
        double seconds = (currentTime - lastTime) / 1000.0;
        long roundedSeconds = (long) seconds;

        if (seconds > roundedSeconds)
            lastTime = currentTime - (long) ((seconds - roundedSeconds) * 1000);

        return calculateTrees(roundedSeconds);
    }

    private long calculateTrees(long seconds) {
        return (long) (seconds * getTreesGeneratedPerSecond());
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

    public int getTreesGeneratedPerSecond() {
        return (int) (treesPerSecondPerUnit * unitsOwned * multiplier);
    }

    public void buy(int n) {
        unitsOwned += n;
    }

    @Override
    public String toString() {
        return UI.BOLD + name + UI.BOLDOFF + "     ID: " + id + "\n" +
                "   Your " + name + "s plant " + UI.BOLD + (calculateTrees(1)) + UI.BOLDOFF + " per second\n" +
                "   Each " + name + " plant " + UI.BOLD + (treesPerSecondPerUnit * multiplier) + UI.BOLDOFF
                + " per second\n"
                +
                "   Number: " + unitsOwned + "     Cost: " + getNextNUnitsCost(1) + " treas" + "\n" +
                "   " + descrip;
    }
}
