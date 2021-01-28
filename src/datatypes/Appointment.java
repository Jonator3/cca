package datatypes;

public class Appointment {

    private String name;
    private String description;
    private String location;
    private TimeData duration;
    private String[] planned_participants;
    private PossibleDate[] dates;
    private TimeData deadline;
    private boolean isFinal;
    private int id;
    private int group_id;

    public Appointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline, boolean isFinal, int id, int group_id) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.duration = duration;
        this.planned_participants = planned_participants;
        this.dates = dates;
        this.deadline = deadline;
        this.isFinal = isFinal;
        this.id = id;
        this.group_id = group_id;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public int getId() {
        return id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public PossibleDate[] getDates() {
        return dates;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public TimeData getDeadline() {
        return deadline;
    }

    public TimeData getDuration() {
        return duration;
    }

    public String[] getPlanned_participants() {
        return planned_participants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDuration(TimeData duration) {
        this.duration = duration;
    }

    public void setPlanned_participants(String[] planned_participants) {
        this.planned_participants = planned_participants;
    }

    public void setDates(PossibleDate[] dates) {
        this.dates = dates;
    }

    public void setDeadline(TimeData deadline) {
        this.deadline = deadline;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
