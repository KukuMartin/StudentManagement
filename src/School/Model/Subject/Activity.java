package School.Model.Subject;

import Tool.IntHolder;
import Tool.StringHolder;

public class Activity {
    private StringHolder name;
    private IntHolder totalScore;
    private int currentScore;

    public Activity(StringHolder name, IntHolder totalScore) {
        this.name = name;
        this.totalScore = totalScore;
        this.currentScore = 0;
    }

    public String getName() {
        return name.getString();
    }

    public int getTotalScore() {
        return totalScore.getInt();
    }

    public int getCurrentScore() {
        return currentScore;
    }
    
    public double getGrade(){
    }

    public void setName(String name) {
    }
    
    public void setTotalScore(int totalScore) {
    }
    
    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
