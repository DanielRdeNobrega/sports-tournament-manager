package matchmaking;

import java.util.ArrayList;

public class MatchOperation {
    private ArrayList<Strategy>matchOperations;

    public MatchOperation(){
        this.matchOperations = new ArrayList<>();
        this.matchOperations.add(new StrategyM());
        this.matchOperations.add(new StrategyA());
    }

    public Strategy getStrategy(String parameter){
        for(int i=0; i<this.matchOperations.size(); i++)
            if(matchOperations.get(i).getIdentifier().equalsIgnoreCase(parameter))
                return matchOperations.get(i);
        return null;
    }
}
