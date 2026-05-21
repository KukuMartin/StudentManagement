package School.Model.Subject;

import School.Model.Subject.Record.Name;
import School.Model.Subject.Record.Score;

public class Activity {
    private Name name;
    private Score totalScore;
    private int currentScore;

    public Activity(Name name, Score totalScore) {
        this.name = name;
        this.totalScore = totalScore;
        this.currentScore = 0;
    }

    public String getName() {
        return name.getName();
    }

    public int getTotalScore() {
        return totalScore.getScore();
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
