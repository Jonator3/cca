package datatypes;

import java.util.Arrays;

public class PossibleDate {
    private TimeData date;
    private String[] possible_participants;

    public PossibleDate(TimeData date, String[] possible_participants){
        this.date = date;
        this.possible_participants = possible_participants;
    }

    public PossibleDate(String s){
        String[] data = s.split(";");
        this.date = new TimeData(data[0]);
        this.possible_participants = data[1].split(":");
    }

    public TimeData getDate() {
        return date;
    }

    public void setPossible_participants(String[] possible_participants) {
        this.possible_participants = possible_participants;
    }

    @Override
    public String toString() {
        String pp = "";
        for (int i=0;i<possible_participants.length;i++){
            if (i!=0){
                pp += ":";
            }
            pp += possible_participants[i];
        }
        return date.toString() + ";" + pp;
    }

    public String[] getPossible_participants() {
        return possible_participants;
    }
    
    public String toDisplayString() {
    	return date.toDisplayString()+": "+Arrays.toString(possible_participants);
    }
}
