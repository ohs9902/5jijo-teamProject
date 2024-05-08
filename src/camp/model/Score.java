package camp.model;

public class Score {
    private String scoreId;
    private String score;

    public Score(String seq) {
        this.scoreId = seq;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public void setScore(double newScore) {
    }

    public String getScore() {
        return score;
    }
}
