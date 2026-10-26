package tournaments.data;

public class TournamentData {
    private final String name;
    private String statistic;
    private final TimePeriod timePeriod;
    private final String league;
    private final String sport;
    private boolean endOfInscriptions;

    public TournamentData(String name, String statistic, TimePeriod timePeriod,
                          String league, String sport) {
        this.name = name;
        this.statistic = statistic;
        this.timePeriod = timePeriod;
        this.league = league;
        this.sport = sport;
        this.endOfInscriptions = false;
    }

    public String getLeague() {
        return this.league;
    }

    public TimePeriod getTimePeriod() {
        return this.timePeriod;
    }

    public String getSport() {
        return this.sport;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEndOfInscriptions() {
        return endOfInscriptions;
    }

    public void setEndOfInscriptions(boolean endOfInscriptions) {
        this.endOfInscriptions = endOfInscriptions;
    }

    public String getStatistic() {
        return statistic;
    }
}
