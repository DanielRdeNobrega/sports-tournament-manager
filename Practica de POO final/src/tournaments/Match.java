package tournaments;

public class Match {
    private final Participant participant1;
    private final Participant participant2;

    public Match(Participant participant1, Participant participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
    }

    public boolean existMatch(Participant participant){
        return (participant == this.participant1 || participant == this.participant2);
    }

    public boolean existMatch(Participant participant1, Participant participant2){
        return (this.existMatch(participant1) || this.existMatch(participant2));
    }
}