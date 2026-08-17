package Game_Root;

public final class ShipConfig {
    private int size;
    private int defaultCount;
    private int score;

    public  ShipConfig(int size, int defaultCount, int score) {
        this.size = size;
        this.defaultCount = defaultCount;
        this.score = score;
    }
    public int getSize() {
        return size;
    }
    public int getDefaultCount() {
        return defaultCount;
    }
    public int getScore() {
        return score;
    }
}
