package School.Model.Subject;

public class Activity {

    private int id;
    private String name;
    private int totalScore;
    private int currentScore;

    public Activity(int id, String name, int totalScore) {
        this.id = id;
        this.name = name;
        this.totalScore = totalScore;
        this.currentScore = 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getTotalScore() { return totalScore; }
    public int getCurrentScore() { return currentScore; }

    public double getPercent() {
        if (totalScore <= 0) return 0.0;
        return (double) currentScore / totalScore;
    }

    public void update(Activity activity) {
        this.name = activity.name;
        this.totalScore = activity.totalScore;
        this.currentScore = activity.currentScore;
    }
}