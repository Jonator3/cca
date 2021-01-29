package datatypes;

public class PossibleDate {
    private TimeData date;
    private String[] possible_participants;

    public PossibleDate(TimeData date, String[] possible_participants){
        this.date = date;
        this.possible_participants = possible_participants;
    }

    public TimeData getDate() {
        return date;
    }

    public void setPossible_participants(String[] possible_participants) {
        this.possible_participants = possible_participants;
    }

    @Override
    public String toString() {
        return ""; // TODO
    }

    public String[] getPossible_participants() {
        return possible_participants;
    }
}
