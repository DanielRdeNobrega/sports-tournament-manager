package users.player;

public class Statistic {

    private double statistic;
    private final String title;

    public Statistic(double value, String name){
        this.statistic = value;
        this.title = name;
    }

    public double getStatistic() {
        return this.statistic;
    }

    public String getName() {
        return this.title;
    }

    public void setStatistic(double statistic) {
        this.statistic = statistic;
    }
}
