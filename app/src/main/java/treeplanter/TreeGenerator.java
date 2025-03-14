package treeplanter;

public class TreeGenerator {

    private long lastTime;
    private int unitsOwned = 0;
    private int baseCost;
    private double priceRateIncrease = 1.15;
    private double multiplier = 1;
    private int treesPerSecondPerUnit;

    public TreeGenerator(int baseCost, int treesPerSecondPerUnit) {
        lastTime = System.currentTimeMillis();
        this.baseCost = baseCost;
        this.treesPerSecondPerUnit = treesPerSecondPerUnit;
    }

    public long getTrees() {
        long currentTime = System.currentTimeMillis();
        long seconds = (lastTime - currentTime) / 1000;
        lastTime = currentTime;

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

}
