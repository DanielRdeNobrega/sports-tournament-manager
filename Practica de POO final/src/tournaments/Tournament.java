package tournaments;

import tournaments.data.TimePeriod;
import tournaments.data.TournamentData;
import users.player.Player;
import static views.errors.TournamentError.NO_ENOUGH_PARTICIPANTS;
import static views.errors.TournamentError.EXIST_MATCH;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Tournament {
    private final TournamentData tournamentData;
    private ArrayList<Participant> participants;
    private ArrayList<Match> matches;

    public Tournament(String name, String beginDate, String endDate,
                      String league, String sport, String statistic){
        this.participants = new ArrayList<>();
        TimePeriod timePeriod = new TimePeriod(beginDate, endDate);
        this.tournamentData = new TournamentData(name,statistic,timePeriod, league, sport);
        this.matches = new ArrayList<>();
    }

    public void register(Participant participant){
        if(!this.participants.contains(participant))
            participants.add(participant);
    }

    public void shuffleParticipants(){
        Collections.shuffle(this.participants);
    }

    public void rank(){
        ArrayList<Participant> newSolution = new ArrayList<>();
        Participant bestParticipant = this.participants.get(0);
        double bestScore = bestParticipant.getStatistics(this.tournamentData.getStatistic());

        while(!this.participants.isEmpty()) {
            for (int i = 1; i < this.participants.size(); i++) {
                Participant participant = this.participants.get(i);
                double score = participant.getStatistics(this.tournamentData.getStatistic());
                if (score > bestScore) {
                    bestScore = score;
                    bestParticipant = participant;
                }
            }
            newSolution.add(bestParticipant);
            this.participants.remove(bestParticipant);
        }
        this.participants = newSolution;
    }

    public String getName(){
        return this.tournamentData.getName();
    }

    public boolean isOnCurse(){
        return (LocalDate.now().isAfter(this.tournamentData.getTimePeriod().getBeginDate())&&
                LocalDate.now().isBefore(this.tournamentData.getTimePeriod().getEndDate()));
    }

    public void randomMatchMake(){
        this.shuffleParticipants();
        if(this.participants.size()%2 == 0){
            for(int i=0; i<this.participants.size()-1; i+=2){
                Match match = new Match(participants.get(i), participants.get(i+1));
                this.matches.add(match);
            }
        }
        else
            NO_ENOUGH_PARTICIPANTS.showError();
    }

    public void manualMatchMake(Participant firstParticipant, Participant secondParticipant){
        boolean exist = false;
        for(int i=0; i<this.matches.size() && !exist; i++){
            Match match = this.matches.get(i);
            if(match.existMatch(firstParticipant, secondParticipant))
                exist = true;
        }
        if(exist)
            EXIST_MATCH.showError();
        else
            this.matches.add(new Match(firstParticipant,secondParticipant));
    }

    public boolean containsPlayer(Player player){
        boolean solution = false;
        for(int i=0; i<participants.size() && !solution; i++)
            solution = participants.get(i).containsPlayer(player);
        return solution;
    }

    public boolean containsTeam(String name){
        boolean solution = false;
        for(int i=0; i<participants.size() && !solution; i++)
            solution = participants.get(i).getID().equalsIgnoreCase(name);
        return solution;
    }

    public Participant contains(String ID){
        for(int i=0; i<participants.size(); i++)
            if(participants.get(i).getID().equalsIgnoreCase(ID))
                return participants.get(i);
        return null;
    }

    public void removeParticipant(Participant participant){
        this.participants.remove(participant);
        for(int i=0; i<this.matches.size(); i++){
            Match match = this.matches.get(i);
            if(match.existMatch(participant))
                this.matches.remove(match);
        }
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }
}